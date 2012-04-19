package tests;

import util.CardConversion;

public class CardConversionTest {
	
	public static void main(String args[]){
		for(int i=0; i<52; i++){
			assert (CardConversion.cardToInt(CardConversion.intToCard(i)) == i) : "Error";
			System.out.println(i+" : " + CardConversion.intToCard(i) +" : " + CardConversion.cardToInt(CardConversion.intToCard(i)));
		}
		System.out.println("All tests complete.");
	}
	
}
