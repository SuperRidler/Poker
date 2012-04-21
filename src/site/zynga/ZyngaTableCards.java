package site.zynga;

import util.UniquePixel;
import util.UniquePixelSuit;

public class ZyngaTableCards {
	
	private static final String IMAGE_PATH = "Numbers/";
	private static final String RANK_FILE_PATH = "src/site/zynga/ZyngaTableCardsRank.txt";
	private static final String SUIT_FILE_PATH = "src/site/zynga/ZyngaTableCardsSuit.txt";
	
	public static void main(String args[]){
		
		UniquePixel up = new UniquePixel(IMAGE_PATH, RANK_FILE_PATH);
		up.writeInfoToFile();
		UniquePixelSuit sup = new UniquePixelSuit("Numbers/Suits/", SUIT_FILE_PATH);
		sup.writeToFile();
		
	}
	
}
