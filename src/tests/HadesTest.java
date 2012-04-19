package tests;

import strategy.HadesStrategy;

public class HadesTest {
	
	/*
	public static void main(String args[]){
		for(int i=0; i<52; i++){
			assert (CardConversion.cardToInt(CardConversion.intToCard(i)) == i) : "Error";
			System.out.println(i+" : " + CardConversion.intToCard(i) +" : " + CardConversion.cardToInt(CardConversion.intToCard(i)));
		}
		System.out.println("All tests complete.");
	}
	*/
	
	/*
	 *  HadesObject ho and then do ho.playerOneCard = whatever and then trace(ho.whatToDo) if that makes sense
	 */

	public static void main(String args[]){
		HadesStrategy hadesO = new HadesStrategy();
		
		hadesO.turn = 1;
		hadesO.cardsOnTable = new int[0];
		
		hadesO.numberOfPlayers = 6;
		
		hadesO.playerPosition = 5;
		hadesO.dealerPosition = 0;
		
		hadesO.playerCardOne = 32;
		hadesO.playerCardTwo = 33;
		
		hadesO.whatToDo();
		
		System.out.println("testing ended.");
	}
}
