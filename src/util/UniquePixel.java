package util;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UniquePixel {
	
	private String IMAGE_PATH = "Numbers/";
	private static final String RED_PATH = "Red/";
	private static final String BLACK_PATH = "Black/";
	private static final String ACE = "A.png";
	private static final String KING = "K.png";
	private static final String QUEEN = "Q.png";
	private static final String JACK = "J.png";
	private static final String TEN = "10.png";
	private static final String NINE = "9.png";
	private static final String EIGHT = "8.png";
	private static final String SEVEN = "7.png";
	private static final String SIX = "6.png";
	private static final String FIVE = "5.png";
	private static final String FOUR = "4.png";
	private static final String THREE = "3.png";
	private static final String TWO = "2.png";
	
	int[][][][] imageArray = new int[2][13][][]; 
	int[][][] pixelPosition = new int[2][13][2];
	
	private String FILE_NAME = "";
	
	public UniquePixel(String IMAGE_PATH, String FILE_NAME){
		this.IMAGE_PATH = IMAGE_PATH;
		this.FILE_NAME = FILE_NAME;
		loadImages();
		findPixelPositions();
		System.out.println(this);
	}
	
	
	public void writeInfoToFile(){
		try {
			FileWriter fstream = new FileWriter(FILE_NAME);
			BufferedWriter bout = new BufferedWriter(fstream);
			
			bout.write("A:"+pixelPosition[0][0][0]+":"+pixelPosition[0][0][1]+":"+pixelPosition[1][0][0]+":"+pixelPosition[1][0][1]+":Color:"+imageArray[0][0][pixelPosition[0][0][0]][pixelPosition[0][0][1]]+":"+imageArray[1][0][pixelPosition[1][0][0]][pixelPosition[1][0][1]]+"\n");
			bout.write("K:"+pixelPosition[0][1][0]+":"+pixelPosition[0][1][1]+":"+pixelPosition[1][1][0]+":"+pixelPosition[1][1][1]+":Color:"+imageArray[0][1][pixelPosition[0][1][0]][pixelPosition[0][1][1]]+":"+imageArray[1][1][pixelPosition[1][1][0]][pixelPosition[1][1][1]]+"\n");
			bout.write("Q:"+pixelPosition[0][2][0]+":"+pixelPosition[0][2][1]+":"+pixelPosition[1][2][0]+":"+pixelPosition[1][2][1]+":Color:"+imageArray[0][2][pixelPosition[0][2][0]][pixelPosition[0][2][1]]+":"+imageArray[1][2][pixelPosition[1][2][0]][pixelPosition[1][2][1]]+"\n");
			bout.write("J:"+pixelPosition[0][3][0]+":"+pixelPosition[0][3][1]+":"+pixelPosition[1][3][0]+":"+pixelPosition[1][3][1]+":Color:"+imageArray[0][3][pixelPosition[0][3][0]][pixelPosition[0][3][1]]+":"+imageArray[1][3][pixelPosition[1][3][0]][pixelPosition[1][3][1]]+"\n");
			bout.write("T:"+pixelPosition[0][4][0]+":"+pixelPosition[0][4][1]+":"+pixelPosition[1][4][0]+":"+pixelPosition[1][4][1]+":Color:"+imageArray[0][4][pixelPosition[0][4][0]][pixelPosition[0][4][1]]+":"+imageArray[1][4][pixelPosition[1][4][0]][pixelPosition[1][4][1]]+"\n");
			bout.write("9:"+pixelPosition[0][5][0]+":"+pixelPosition[0][5][1]+":"+pixelPosition[1][5][0]+":"+pixelPosition[1][5][1]+":Color:"+imageArray[0][5][pixelPosition[0][5][0]][pixelPosition[0][5][1]]+":"+imageArray[1][5][pixelPosition[1][5][0]][pixelPosition[1][5][1]]+"\n");
			bout.write("8:"+pixelPosition[0][6][0]+":"+pixelPosition[0][6][1]+":"+pixelPosition[1][6][0]+":"+pixelPosition[1][6][1]+":Color:"+imageArray[0][6][pixelPosition[0][6][0]][pixelPosition[0][6][1]]+":"+imageArray[1][6][pixelPosition[1][6][0]][pixelPosition[1][6][1]]+"\n");
			bout.write("7:"+pixelPosition[0][7][0]+":"+pixelPosition[0][7][1]+":"+pixelPosition[1][7][0]+":"+pixelPosition[1][7][1]+":Color:"+imageArray[0][7][pixelPosition[0][7][0]][pixelPosition[0][7][1]]+":"+imageArray[1][7][pixelPosition[1][7][0]][pixelPosition[1][7][1]]+"\n");
			bout.write("6:"+pixelPosition[0][8][0]+":"+pixelPosition[0][8][1]+":"+pixelPosition[1][8][0]+":"+pixelPosition[1][8][1]+":Color:"+imageArray[0][8][pixelPosition[0][8][0]][pixelPosition[0][8][1]]+":"+imageArray[1][8][pixelPosition[1][8][0]][pixelPosition[1][8][1]]+"\n");
			bout.write("5:"+pixelPosition[0][9][0]+":"+pixelPosition[0][9][1]+":"+pixelPosition[1][9][0]+":"+pixelPosition[1][9][1]+":Color:"+imageArray[0][9][pixelPosition[0][9][0]][pixelPosition[0][9][1]]+":"+imageArray[1][9][pixelPosition[1][9][0]][pixelPosition[1][9][1]]+"\n");
			bout.write("4:"+pixelPosition[0][10][0]+":"+pixelPosition[0][10][1]+":"+pixelPosition[1][10][0]+":"+pixelPosition[1][10][1]+":Color:"+imageArray[0][10][pixelPosition[0][10][0]][pixelPosition[0][10][1]]+":"+imageArray[1][10][pixelPosition[1][10][0]][pixelPosition[1][10][1]]+"\n");
			bout.write("3:"+pixelPosition[0][11][0]+":"+pixelPosition[0][11][1]+":"+pixelPosition[1][11][0]+":"+pixelPosition[1][11][1]+":Color:"+imageArray[0][11][pixelPosition[0][11][0]][pixelPosition[0][11][1]]+":"+imageArray[1][11][pixelPosition[1][11][0]][pixelPosition[1][11][1]]+"\n");
			bout.write("2:"+pixelPosition[0][12][0]+":"+pixelPosition[0][12][1]+":"+pixelPosition[1][12][0]+":"+pixelPosition[1][12][1]+":Color:"+imageArray[0][12][pixelPosition[0][12][0]][pixelPosition[0][12][1]]+":"+imageArray[1][12][pixelPosition[1][12][0]][pixelPosition[1][12][1]]);
			
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString(){
		String s = "";
		s+= "R ACE: "+pixelPosition[0][0][0]+","+pixelPosition[0][0][1]+" Color: "+imageArray[0][0][pixelPosition[0][0][0]][pixelPosition[0][0][1]]+"\n";
		s+= "R KING: "+pixelPosition[0][1][0]+","+pixelPosition[0][1][1]+" Color: "+imageArray[0][1][pixelPosition[0][1][0]][pixelPosition[0][1][1]]+"\n";
		s+= "R QUEEN: "+pixelPosition[0][2][0]+","+pixelPosition[0][2][1]+" Color: "+imageArray[0][2][pixelPosition[0][2][0]][pixelPosition[0][2][1]]+"\n";
		s+= "R JACK: "+pixelPosition[0][3][0]+","+pixelPosition[0][3][1]+" Color: "+imageArray[0][3][pixelPosition[0][3][0]][pixelPosition[0][3][1]]+"\n";
		s+= "R TEN: "+pixelPosition[0][4][0]+","+pixelPosition[0][4][1]+" Color: "+imageArray[0][4][pixelPosition[0][4][0]][pixelPosition[0][4][1]]+"\n";
		s+= "R NINE: "+pixelPosition[0][5][0]+","+pixelPosition[0][5][1]+" Color: "+imageArray[0][5][pixelPosition[0][5][0]][pixelPosition[0][5][1]]+"\n";
		s+= "R EIGHT: "+pixelPosition[0][6][0]+","+pixelPosition[0][6][1]+" Color: "+imageArray[0][6][pixelPosition[0][6][0]][pixelPosition[0][6][1]]+"\n";
		s+= "R SEVEN: "+pixelPosition[0][7][0]+","+pixelPosition[0][7][1]+" Color: "+imageArray[0][7][pixelPosition[0][7][0]][pixelPosition[0][7][1]]+"\n";
		s+= "R SIX: "+pixelPosition[0][8][0]+","+pixelPosition[0][8][1]+" Color: "+imageArray[0][8][pixelPosition[0][8][0]][pixelPosition[0][8][1]]+"\n";
		s+= "R FIVE: "+pixelPosition[0][9][0]+","+pixelPosition[0][9][1]+" Color: "+imageArray[0][9][pixelPosition[0][9][0]][pixelPosition[0][9][1]]+"\n";
		s+= "R FOUR: "+pixelPosition[0][10][0]+","+pixelPosition[0][10][1]+" Color: "+imageArray[0][10][pixelPosition[0][10][0]][pixelPosition[0][10][1]]+"\n";
		s+= "R THREE: "+pixelPosition[0][11][0]+","+pixelPosition[0][11][1]+" Color: "+imageArray[0][11][pixelPosition[0][11][0]][pixelPosition[0][11][1]]+"\n";
		s+= "R TWO: "+pixelPosition[0][12][0]+","+pixelPosition[0][12][1]+" Color: "+imageArray[0][12][pixelPosition[0][12][0]][pixelPosition[0][12][1]]+"\n";
		s+= "B ACE: "+pixelPosition[1][0][0]+","+pixelPosition[1][0][1]+" Color: "+imageArray[1][0][pixelPosition[1][0][0]][pixelPosition[1][0][1]]+"\n";
		s+= "B KING: "+pixelPosition[1][1][0]+","+pixelPosition[1][1][1]+" Color: "+imageArray[1][1][pixelPosition[1][1][0]][pixelPosition[1][1][1]]+"\n";
		s+= "B QUEEN: "+pixelPosition[1][2][0]+","+pixelPosition[1][2][1]+" Color: "+imageArray[1][2][pixelPosition[1][2][0]][pixelPosition[1][2][1]]+"\n";
		s+= "B JACK: "+pixelPosition[1][3][0]+","+pixelPosition[1][3][1]+" Color: "+imageArray[1][3][pixelPosition[1][3][0]][pixelPosition[1][3][1]]+"\n";
		s+= "B TEN: "+pixelPosition[1][4][0]+","+pixelPosition[1][4][1]+" Color: "+imageArray[1][4][pixelPosition[1][4][0]][pixelPosition[1][4][1]]+"\n";
		s+= "B NINE: "+pixelPosition[1][5][0]+","+pixelPosition[1][5][1]+" Color: "+imageArray[1][5][pixelPosition[1][5][0]][pixelPosition[1][5][1]]+"\n";
		s+= "B EIGHT: "+pixelPosition[1][6][0]+","+pixelPosition[1][6][1]+" Color: "+imageArray[1][6][pixelPosition[1][6][0]][pixelPosition[1][6][1]]+"\n";
		s+= "B SEVEN: "+pixelPosition[1][7][0]+","+pixelPosition[1][7][1]+" Color: "+imageArray[1][7][pixelPosition[1][7][0]][pixelPosition[1][7][1]]+"\n";
		s+= "B SIX: "+pixelPosition[1][8][0]+","+pixelPosition[1][8][1]+" Color: "+imageArray[1][8][pixelPosition[1][8][0]][pixelPosition[1][8][1]]+"\n";
		s+= "B FIVE: "+pixelPosition[1][9][0]+","+pixelPosition[1][9][1]+" Color: "+imageArray[1][9][pixelPosition[1][9][0]][pixelPosition[1][9][1]]+"\n";
		s+= "B FOUR: "+pixelPosition[1][10][0]+","+pixelPosition[1][10][1]+" Color: "+imageArray[1][10][pixelPosition[1][10][0]][pixelPosition[1][10][1]]+"\n";
		s+= "B THREE: "+pixelPosition[1][11][0]+","+pixelPosition[1][11][1]+" Color: "+imageArray[1][11][pixelPosition[1][11][0]][pixelPosition[1][11][1]]+"\n";
		s+= "B TWO: "+pixelPosition[1][12][0]+","+pixelPosition[1][12][1]+" Color: "+imageArray[1][12][pixelPosition[1][12][0]][pixelPosition[1][12][1]]+"\n";
		return s;
	}
	
	private void loadImages(){
		imageArray[0][0] = getSel(RED_PATH+ACE);
		imageArray[0][1] = getSel(RED_PATH+KING);
		imageArray[0][2] = getSel(RED_PATH+QUEEN);
		imageArray[0][3] = getSel(RED_PATH+JACK);
		imageArray[0][4] = getSel(RED_PATH+TEN);
		imageArray[0][5] = getSel(RED_PATH+NINE);
		imageArray[0][6] = getSel(RED_PATH+EIGHT);
		imageArray[0][7] = getSel(RED_PATH+SEVEN);
		imageArray[0][8] = getSel(RED_PATH+SIX);
		imageArray[0][9] = getSel(RED_PATH+FIVE);
		imageArray[0][10] = getSel(RED_PATH+FOUR);
		imageArray[0][11] = getSel(RED_PATH+THREE);
		imageArray[0][12] = getSel(RED_PATH+TWO);
		imageArray[1][0] = getSel(BLACK_PATH+ACE);
		imageArray[1][1] = getSel(BLACK_PATH+KING);
		imageArray[1][2] = getSel(BLACK_PATH+QUEEN);
		imageArray[1][3] = getSel(BLACK_PATH+JACK);
		imageArray[1][4] = getSel(BLACK_PATH+TEN);
		imageArray[1][5] = getSel(BLACK_PATH+NINE);
		imageArray[1][6] = getSel(BLACK_PATH+EIGHT);
		imageArray[1][7] = getSel(BLACK_PATH+SEVEN);
		imageArray[1][8] = getSel(BLACK_PATH+SIX);
		imageArray[1][9] = getSel(BLACK_PATH+FIVE);
		imageArray[1][10] = getSel(BLACK_PATH+FOUR);
		imageArray[1][11] = getSel(BLACK_PATH+THREE);
		imageArray[1][12] = getSel(BLACK_PATH+TWO);
	}
	
	public int[][] getSel(String fileName){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(IMAGE_PATH+fileName));
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		int[][] sel = new int[img.getWidth()][img.getHeight()];
		for(int i=0; i<img.getWidth(); i++){
			for(int j=0; j<img.getHeight(); j++){
				sel[i][j] = img.getRGB(i, j);
			}
		}
		return sel;
	}
	
	private void findPixelPositions(){
		for(int x=0; x<2; x++){
			boolean found = false;
			for(int i=0; i<13; i++){
				found = false;
				for(int j=0; j<imageArray[x][i].length; j++){
					if(found){
						break;
					}
					for(int k=0; k<imageArray[x][i][j].length; k++){
						if(found){
							break;
						}
						found = true;
						for(int l=0; l<13; l++){
							if(i != l){
								if(imageArray[x][i][j][k] == imageArray[x][l][j][k]){
									found = false;
								}
							}
						}
						if(found){
							pixelPosition[x][i][0] = j;
							pixelPosition[x][i][1] = k;
							for(int p=0; p<i; p++){
								if(pixelPosition[x][p][0] == j && pixelPosition[x][p][1] == k || j < 3 || k < 3){
									found = false;
								}
							}
						}
					}
				}
			}
		}
	}
	
}