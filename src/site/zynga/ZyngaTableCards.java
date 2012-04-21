package site.zynga;

import util.UniquePixel;

public class ZyngaTableCards {
	
	private static final String IMAGE_PATH = "Numbers/";
	private static final String FILE_PATH = "src/site/zynga/ZyngaTableCards.txt";
	
	public static void main(String args[]){
		
		UniquePixel up = new UniquePixel(IMAGE_PATH, FILE_PATH);
		up.writeInfoToFile();
		
	}
	
}
