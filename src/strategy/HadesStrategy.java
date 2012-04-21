package strategy;

import util.Info;

public class HadesStrategy extends Strategy {

	public int[] cardsOnTable;
	
	public int playerPosition;//0-8
	public int dealerPosition;//0-8
	
	public int turn;
	
	public int playerCardOne;
	public int playerCardTwo;
	
	public int numberOfPlayers;//1-9
	
	//String playerName = "BobbyDrop";
	
	@Override
	public void update(Info info) {
		//Example on info extraction from object.
		cardsOnTable = info.getCardsOnTable();
	}

	@Override
	public int whatToDo() {
		if(numberOfCardsOnTable() == 0){
			if(turn == 1){
				cheatSheetStartStrategy();
			}
			// TODO when it is not the first round of betting
		}else{
			// TODO Post-Flop strategy
		}
		return 0;
	}
	
	private void cheatSheetStartStrategy(){
		if(!handIsGrouped()){
			//fold
			playerAction(1);
		} else {
			if(someoneHasBet()){
				//if you are not the blinds
				if(!playerIsBlinds()){
					if(handInGroupA() || handInGroupB()){
						//raise
						playerAction(3);
					} else {
						//fold
						playerAction(1);
					}
				} else {
					//need to add the bit for if you are the blinds
				}
			}
			if(getPositionStrength() == 3){
				//raise
				playerAction(3);
			} else if (getPositionStrength() == 2){
				if(handInGroupA() ||
				   handInGroupB() ||
				   handInGroupC() ||
				   handInGroupD()){
					//raise
					playerAction(3);
				} else {
					//fold
					playerAction(1);
				}
			} else if (getPositionStrength() == 1){
				if(handInGroupA() ||
				   handInGroupB() ||
				   handInGroupC()){
					//raise
					playerAction(3);
				} else {
					//fold
					playerAction(1);
				}
			} else {
				//panic
				playerAction(0);
			}
		}
	}
	
	private boolean playerIsBlinds() {
		//needs to 
		// TODO return if the player is blinds.
		return false;
	}

	//the logic on position seems dodgey (not the code, but the strategy logic) with less than 9 players. kind of worrying.
	private int getPositionStrength(){
		//dealer is always latest and strongest position.
		//blinds are weird.
		//they are considered the weakest and worst position, but before the flop the big blind acts last
		//but stats show that the big blind is the position that looses the most money.
		//so for now this will return them as weakest 0.
		//looking at the table counter clockwise.
		//
		//if there are more than 5 players, next player is late
		//the remaining players are divided into middle and early. more early positions.
		int strengthNumber = 4;
		
		
		//THIS ONLY WORKS FOR MORE THAN 5 PLAYERS CURRENTLY.
		//NOT GOOD
		if(numberOfPlayers > 5){
			
			trace("number of players > 5");
			
			//late
			boolean atDealerPos = dealerPosition == playerPosition;
			boolean oneRightOfDealer = dealerPosition != 0 && dealerPosition-1 == playerPosition;
			boolean oneRightOfDealerAtZero = dealerPosition == 0 && numberOfPlayers-1 == playerPosition;
			
			if(atDealerPos || oneRightOfDealer || oneRightOfDealerAtZero){
				strengthNumber = 3;
			}
			
			
			//blinds
			boolean smallBlind = dealerPosition+1 == playerPosition;
			boolean bigBlind = dealerPosition+2 == playerPosition;
			
			boolean smallBlindDealerAtCap = dealerPosition == numberOfPlayers && 0 == playerPosition;
			boolean bigBlindDealerAtCap = dealerPosition == numberOfPlayers && 1 == playerPosition;
			
			boolean smallBlindDealerAtCapTwo = dealerPosition+1 == numberOfPlayers && numberOfPlayers == playerPosition;
			boolean bigBlindDealerAtCapTwo = dealerPosition+1 == numberOfPlayers && 0 == playerPosition;
			
			if(smallBlind || bigBlind ||
				smallBlindDealerAtCap || bigBlindDealerAtCap || 
				smallBlindDealerAtCapTwo || bigBlindDealerAtCapTwo){
					strengthNumber = 0;
			}
			
			//early
			int leftToSort = numberOfPlayers-3;
			int earlysInt = leftToSort/2;
			
			for (int i = 0; i < earlysInt; i = i + 1) {
				int positionToCheck = dealerPosition + 3 + i;
				
				//dunno if this will work or not, how reassuring
				if(positionToCheck>numberOfPlayers){
					positionToCheck = positionToCheck - numberOfPlayers - 1;
				}
				
				if(positionToCheck == playerPosition){
					strengthNumber = 1;
				}
			} 
			
			//middle
			if(strengthNumber == 4){
				strengthNumber = 2;
			}
			
		}
		
		//strengthNumber either 1,2,3. early,middle,late position. Higher is better.
		//maybe use zero for blinds
		
		trace("positionStr: "+strengthNumber);
		return strengthNumber;
	}
	

	private boolean playerHandMatches(char checkValueOne,char checkValueTwo, boolean onlySuited){
		//just uses the players card into this method, makes things look more tidy.
		return handMatches(playerCardOne,playerCardTwo,checkValueOne,checkValueTwo, onlySuited);
	}
	
	private boolean handMatches(int cardOneNum,int cardTwoNum,char checkValueOne,char checkValueTwo, boolean onlySuited){
		char cardOneVal =  intToCard(cardOneNum).charAt(1);
		char cardTwoVal =  intToCard(cardTwoNum).charAt(1);
		
		if(onlySuited){
			char cardOneSuit =  intToCard(cardOneNum).charAt(0);
			char cardTwoSuit =  intToCard(cardTwoNum).charAt(0);
			
			//comparing chars
			if(cardOneSuit != cardTwoSuit){
				return false;
			}
		}
		
		//comparing chars
		//check if cards are equal to the provided chars. Both way round.
		if(cardOneVal == checkValueOne && cardTwoVal == checkValueTwo){
			return true;
		} else if(cardOneVal == checkValueTwo && cardTwoVal == checkValueOne){
			return true;
		}
		
		return false;
	}
	
	private String intToCard(int intToChange) {
		//just uses CardConversion intocard. 
		return util.CardConversion.intToCard(intToChange);
	}

	private boolean handIsGrouped(){
		//returns false if hand is not in any groups.
		
		if(handInGroupA() ||
		   handInGroupB() ||
		   handInGroupC() ||
		   handInGroupD() ||
		   handInGroupE()){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean handInGroupA(){
		//should return true if hand is in group A.
		return
			playerHandMatches('A','A',false) ||
			playerHandMatches('K','K',false) ||
			playerHandMatches('A','K',true);
	}
	
	private boolean handInGroupB(){
		//should return true if hand is in group B.
		return
			playerHandMatches('Q','Q',false) ||
			playerHandMatches('A','K',false) ||
			playerHandMatches('J','J',false) ||
			playerHandMatches('T','T',false);
	}
	
	private boolean handInGroupC(){
		//should return true if hand is in group C.
		return
			playerHandMatches('A','Q',true) ||
			playerHandMatches('9','9',false) ||
			playerHandMatches('8','8',false) ||
			playerHandMatches('A','J',true);
	}
	
	private boolean handInGroupD(){
		//should return true if hand is in group D.
		return
			playerHandMatches('7','7',false) ||
			playerHandMatches('K','Q',true) ||
			playerHandMatches('6','6',false) ||
			playerHandMatches('A','T',true) ||
			playerHandMatches('5','5',false) ||
			playerHandMatches('A','J',false);
	}
	
	private boolean handInGroupE(){
		//should return true if hand is in group E.
		return
			playerHandMatches('K','Q',false) ||
			playerHandMatches('4','4',false) ||
			playerHandMatches('K','J',true) ||
			playerHandMatches('3','3',false) ||
			playerHandMatches('2','2',false) ||
			playerHandMatches('A','T',false) ||
			playerHandMatches('Q','J',true);
		
	}
	
	private static void playerAction(int actionNum){
		if(actionNum == 0){
			trace("PANIC!");
		} else if(actionNum == 1){
			trace("Fold");
		} else if (actionNum ==2){
			trace("Call");
		} else if (actionNum ==3){
			trace("Raise");
		}
	}
	
	private boolean someoneHasBet(){
		// TODO should return true if someone has be before you.
		return false;
	}
	
	private static void trace(String PrintMeOut){
		//using trace because its fucking awesome.
		//similar to as3 (which im used to)
		//comment one line to remove all traces.
		//could change the out to a file to act as a log.
		//yeah i know, im sick.
		System.out.println(PrintMeOut);
	}
	
	//Example of private method.
	private int numberOfCardsOnTable(){
		return cardsOnTable.length;
	}

}
