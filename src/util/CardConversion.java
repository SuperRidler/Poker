package util;

public class CardConversion {
	
	private CardConversion(){
		
	}
	
	public static int cardToInt(String str){
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
		int card = 4*(face)+suit;
		return card;
	}
	
	public static String intToCard(int num){
		int faceNum = 0;
		while(num >= 4){
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
