package util;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UniquePixelSuit {
	
	private static String IMAGE_PATH = "Numbers/Suits/";
	private static String SPADE = "S.png";
	private static String CLUB = "C.png";
	private static String HEART = "H.png";
	private static String DIAMOND = "D.png";
	
	int[][][] suitArray = new int[4][][];
	int[][] pixelPosition = new int[4][2];
	
	private String FILE_NAME = "";
	
	public UniquePixelSuit(String IMAGE_PATH, String FILE_NAME){
		this.IMAGE_PATH = IMAGE_PATH;
		this.FILE_NAME = FILE_NAME;
		loadImages();
		findPixelPositions();
	}
	
	public void writeToFile(){
		try{
			FileWriter fstream = new FileWriter(FILE_NAME);
			BufferedWriter bout = new BufferedWriter(fstream);
			
			bout.write("S:"+pixelPosition[0][0]+":"+pixelPosition[0][1]+":"+pixelPosition[0][0]+":"+pixelPosition[0][1]+":Colour:"+suitArray[0][pixelPosition[0][0]][pixelPosition[0][1]]+":"+suitArray[0][pixelPosition[0][0]][pixelPosition[0][1]]+"\n");
			bout.write("C:"+pixelPosition[1][0]+":"+pixelPosition[1][1]+":"+pixelPosition[1][0]+":"+pixelPosition[1][1]+":Colour:"+suitArray[1][pixelPosition[1][0]][pixelPosition[1][1]]+":"+suitArray[1][pixelPosition[1][0]][pixelPosition[1][1]]+"\n");
			bout.write("H:"+pixelPosition[2][0]+":"+pixelPosition[2][1]+":"+pixelPosition[2][0]+":"+pixelPosition[2][1]+":Colour:"+suitArray[2][pixelPosition[2][0]][pixelPosition[2][1]]+":"+suitArray[2][pixelPosition[2][0]][pixelPosition[2][1]]+"\n");
			bout.write("D:"+pixelPosition[3][0]+":"+pixelPosition[3][1]+":"+pixelPosition[3][0]+":"+pixelPosition[3][1]+":Colour:"+suitArray[3][pixelPosition[3][0]][pixelPosition[3][1]]+":"+suitArray[3][pixelPosition[3][0]][pixelPosition[3][1]]);

			
			bout.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void loadImages(){
		suitArray[0] = getSel(SPADE);
		suitArray[1] = getSel(CLUB);
		suitArray[2] = getSel(HEART);
		suitArray[3] = getSel(DIAMOND);
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
		boolean found = false;
		for(int i=0; i<4; i++){
			found = false;
			for(int j=0; j<suitArray[i].length; j++){
				if(found){
					break;
				}
				for(int k=0; k<suitArray[i][j].length; k++){
					if(found){
						break;
					}
					found = true;
					for(int l=0; l<4; l++){
						if(i != l){
							if(suitArray[i][j][k] == suitArray[l][j][k]){
								found = false;
							}
						}
					}
					if(found){
						pixelPosition[i][0] = j;
						pixelPosition[i][1] = k;
						if(j < 5 || k < 5){
							found = false;
						}
						/*for(int p=0; p<i; p++){
							if(pixelPosition[p][0] == j && pixelPosition[p][1] == k || j < 2 || k < 2){
								found = false;
							}
						}*/
					}
				}
			}
		}
	}

}
