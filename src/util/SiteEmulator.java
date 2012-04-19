package util;

import java.util.ArrayList;
import java.util.Random;

import site.InternalSite;

public class SiteEmulator {
	
	//Player state 0 = waiting on them to decide move.
	//Player state 1 = has checked/bet.
	//Player state -1 = folded.
	//Player state -2 = bankrupt.
	
	FiveEval fv;
	final int MAX_PLAYERS = 12;
	final int PLAYER_CHIPS = 500;
	int numberOfPlayers = 0;
	int[][] playerCards;
	int[] cardsOnTable;
	int[] playerChips;
	int[] playerState;
	int currentPot;
	int dealerPosition;
	int[] playerHandScore;
	int[] bettingAmount;
	int maxBet;
	int numberOfCardsOnTable = 0;
	InternalSite[] iss;
	Random r;
	ArrayList<Integer> cardsUsed;
	
	
	/*public static void main(String args[]){
		new SiteEmulator();
	}*/
	
	public SiteEmulator(){
		
		assert (intToCard(cardToInt("CA")).equals("CA")) : "";
		
		fv = new FiveEval();
		iss = new InternalSite[MAX_PLAYERS];
		r = new Random();
	}
	
	public void joinGame(InternalSite is){
		iss[numberOfPlayers] = is;
		numberOfPlayers++;
	}
	
	public void startGame(){
		playerState = new int[numberOfPlayers];
		for(int i=0; i<numberOfPlayers; i++){
			playerState[i] = 0;
		}
		while(!gameFinished()){
			cardsUsed = new ArrayList<Integer>();
			playerCards = new int[numberOfPlayers][2];
			cardsOnTable = new int[5];
			setupPlayerChips();
			dealPlayersCards();
			doBetting();
			playFlop();
			doBetting();
			playTurn();
			doBetting();
			playRiver();
			doBetting();
			int winner = determineWinner();
			System.out.println(iss[winner]);
			checkForBankruptPlayers();
			rotateDealerPosition();
		}
	}
	
	private void setupPlayerChips(){
		for(int i=0; i<numberOfPlayers; i++){
			playerChips[i] = PLAYER_CHIPS;
		}
	}
	
	private void dealPlayersCards(){
		for(int i=0; i<numberOfPlayers; i++){
			for(int j=0; j<2; j++){
				int randomCard = 0;
				do{
					randomCard = r.nextInt(52);
				}while(cardsUsed.contains((Integer)randomCard));
				playerCards[i][j] = randomCard;
			}
		}
	}
	
	private void playFlop(){
		for(int i=0; i<3; i++){
			int randomCard = 0;
			do{
				randomCard = r.nextInt(52);
			}while(cardsUsed.contains((Integer)randomCard));
			cardsOnTable[i] = randomCard;
		}
		numberOfCardsOnTable = 3;
	}
	
	private void playTurn(){
		int randomCard = 0;
		do{
			randomCard = r.nextInt(52);
		}while(cardsUsed.contains((Integer)randomCard));
		cardsOnTable[3] = randomCard;
		numberOfCardsOnTable++;
	}
	
	private void playRiver(){
		int randomCard = 0;
		do{
			randomCard = r.nextInt(52);
		}while(cardsUsed.contains((Integer)randomCard));
		cardsOnTable[4] = randomCard;
		numberOfCardsOnTable++;
	}
	
	private void doBetting(){
		for(int i=0; i<numberOfPlayers; i++){
			if(playerState[i] != -2 && playerState[i] != -3){
				playerState[i] = 0;
			}
		}
		bettingAmount = new int[numberOfPlayers];
		maxBet = 0;
		while(!bettingFinished()){
			for(int i=0; i<numberOfPlayers; i++){
				if(playerState[i] != -1){
					bettingAmount[i] = iss[i].siteEmulator();
					playerChips[i] -= bettingAmount[i];
					playerState[i] = 1;
				}else{
					bettingAmount[i] = -1;
				}
			}
		}
		for(int i=0; i<numberOfPlayers; i++){
			if(bettingAmount[i] != -1){
				currentPot += bettingAmount[i];
			}
		}
	}
	
	private boolean bettingFinished(){
		for(int i=0; i<numberOfPlayers; i++){
			if(bettingAmount[i] == -1 || bettingAmount[i] == maxBet){
				if(playerState[i] != 1){
					return false;
				}
			}
		}
		return true;
	}
	
	private int determineWinner(){
		int maxScore = 0;
		int winner = 0;
		for(int i=0; i<numberOfPlayers; i++){
			if(playerState[i]==1){
				//Player still in on this hand.
				playerHandScore[i] = fv.getBestRankOf(playerCards[i][0], playerCards[i][1],
						cardsOnTable[0],cardsOnTable[1],cardsOnTable[2],cardsOnTable[3],cardsOnTable[4]);
				if(playerHandScore[i] > maxScore){
					maxScore = playerHandScore[i];
					winner = i;
				}
			}else{
				playerHandScore[i] = 0;
			}
		}
		playerChips[winner] += currentPot;
		currentPot = 0;
		return winner;
	}
	
	private void rotateDealerPosition(){
		if(dealerPosition==numberOfPlayers){
			dealerPosition = 0;
		}else{
			dealerPosition++;
		}
	}
	
	private boolean gameFinished(){
		int playersStillIn = 0;
		for(int i=0; i<numberOfPlayers; i++){
			if(playerState[i] != -2){
				playersStillIn++;
			}
		}
		return (playersStillIn>1);
	}
	
	private void checkForBankruptPlayers(){
		for(int i=0; i<numberOfPlayers; i++){
			if(playerChips[i] < 1){
				playerState[i] = -2;
			}
		}
	}
	
	public Info getPlayerInfo(int player){
		Info info = new Info();
		info.cardsOnTable = getCardsCurrentlyOnTable();
		info.currentChips = playerChips[player];
		info.dealerPos = dealerPosition;
		info.opponentMove = playerState;
		info.playerCardOne = playerCards[player][0];
		info.playerCardTwo = playerCards[player][1];
		return info;
	}
	
	private int[] getCardsCurrentlyOnTable(){
		int[] returnArray = new int[numberOfCardsOnTable];
		for(int i=0; i<numberOfCardsOnTable; i++){
			returnArray[i] = cardsOnTable[i];
		}
		return returnArray;
	}
	
	//This code is wrong.
	private int cardToInt(String str){
		char c1 = str.charAt(0);
		char c2 = str.charAt(1);
		int suit = 0;
		switch(c1){
			case 'S': suit = 0; break;
			case 'H': suit = 1; break;
			case 'D': suit = 2; break;
			case 'C': suit = 3; break;
		}
		int face = 0;
		switch(c2){
		 case 'A': face = 0; break;
		 case 'K': face = 1; break;
		 case 'Q': face = 2; break;
		 case 'J': face = 3; break;
		 case 'T': face = 4; break;
		 case '9': face = 5; break;
		 case '8': face = 6; break;
		 case '7': face = 7; break;
		 case '6': face = 8; break;
		 case '5': face = 9; break;
		 case '4': face = 10; break;
		 case '3': face = 11; break;
		 case '2': face = 12; break;
		}
		int card = (face+1)*(suit+1)-1;
		return card;
	}
	
	//This code is wrong.
	private String intToCard(int num){
		int faceNum = 0;
		while(num > 4){
			num -= 4;
			faceNum++;
		}
		char suit = ' ';
		switch(num){
		 case 0: suit = 'S'; break;
	 	 case 1: suit = 'H'; break;
		 case 2: suit = 'D'; break;
		 case 3: suit = 'C'; break;
		}
		char face = ' ';
		switch(faceNum){
		 case 0: face = 'A'; break;
		 case 1: face = 'K'; break;
		 case 2: face = 'Q'; break;
		 case 3: face = 'J'; break;
		 case 4: face = 'T'; break;
		 case 5: face = '9'; break;
		 case 6: face = '8'; break;
		 case 7: face = '7'; break;
		 case 8: face = '6'; break;
		 case 9: face = '5'; break;
		 case 10: face = '4'; break;
		 case 11: face = '3'; break;
		 case 12: face = '2'; break;
		}
		String card = String.valueOf(suit)+String.valueOf(face);
		return card;
	}
	
}
