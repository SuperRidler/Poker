package strategy;

import util.Info;

public class HadesStrategy extends Strategy {

	int[] cardsOnTable;
	
	int playerPosition;
	int dealerPosition;
	
	int turn;
	
	String handGroup;
	
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
		}else{
			//Post-Flop
		}
		return 0;
	}
	
	private void cheatSheetStartStrategy(){
		if(handIsGrouped()){
			//fold
		} else {
			if(someoneHasBet()){
				//if you are not the blinds
				//need to add the bit for if you are the blinds
				if(handInGroupA() || handInGroupB()){
					//re raise
				} else {
					//fold
				}
			}
			if(getPositionStrength() == 3){
				//raise
			} else if (getPositionStrength() == 2){
				if(handInGroupA() ||
				   handInGroupB() ||
				   handInGroupC() ||
				   handInGroupD()){
					//raise
				}
			} else if (getPositionStrength() == 2){
				if(handInGroupA() ||
				   handInGroupB() ||
				   handInGroupC()){
					//raise
				}
			} else {
				//panic
			}
		}
	}
	
	private int getPositionStrength(){
		//this function needs finishing, it is fairly simple but needs more information to calculate its result.
		int strengthNumber = 0;
		
		if(dealerPosition == playerPosition){
			strengthNumber = 3;
		}
		
		//strengthNumber either 1,2,3. early,middle,late position. Higher is better.
		return strengthNumber;
	}
	
	private boolean handIsGrouped(){
		//returns false if hand is not in any groups.
		
		/*if(handInGroupA() ||
		   handInGroupB() ||
		   handInGroupC() ||
		   handInGroupD() ||
		   handInGroupE()){
			return true;
		} else {
			return false;
		}*/
		
		return handInGroupA() || handInGroupB() || handInGroupC() || handInGroupD() || handInGroupE();
		
	}
	
	private boolean handInGroupA(){
		//should return true if hand is in group A.
		return false;
	}
	
	private boolean handInGroupB(){
		//should return true if hand is in group B.
		return false;
	}
	
	private boolean handInGroupC(){
		//should return true if hand is in group C.
		return false;
	}
	
	private boolean handInGroupD(){
		//should return true if hand is in group D.
		return false;
	}
	private boolean handInGroupE(){
		//should return true if hand is in group E.
		return false;
	}
	
	private boolean someoneHasBet(){
		//should return true if someone has be before you.
		return false;
	}
	
	//Example of private method.
	private int numberOfCardsOnTable(){
		return cardsOnTable.length;
	}

}
