//Object to store large amount of info to pass between classes.
//Sites create an info object and send it to strategy class.

package util;

public class Info {
	
	//Info you want to store.
	int playerCardOne, playerCardTwo, currentChips, dealerPos;
	int[] cardsOnTable, opponentMove, opponentPostion;

	public int[] getCardsOnTable(){
		//Get the cardsOnTableArray.
		return cardsOnTable;
	}
	
	public void setCardsOnTable(int[] cardsOnTable){
		//Set cardsOnTable array to a specified array.
		this.cardsOnTable = cardsOnTable;
	}
	
	public void addCardToCardsOnTable(int card){
		//Add a single card to the array.
		int[] newArray = new int[cardsOnTable.length+1];
		int i;
		for(i=0; i<cardsOnTable.length; i++){
			newArray[i] = cardsOnTable[i];
		}
		newArray[i] = card;
	}
	
}
