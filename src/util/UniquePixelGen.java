package util;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UniquePixelGen {
	
	char[] code;
	int[][][] imageArray; 
	int[][] pixelPosition;
	
	private String FILE_NAME = "";
	private String IMAGE_PATH = "";
	
	public UniquePixelGen(String IMAGE_PATH, String FILE_NAME){
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
			
			for(int i=0; i<code.length; i++){
				bout.write(code[i]+":"
				+pixelPosition[i][0]+":"
				+pixelPosition[i][1]+":Colour:"
				+imageArray[pixelPosition[i][0]][pixelPosition[i][1]]);
			}
			
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadImages(){
		File dir = new File(IMAGE_PATH);
		String[] names  = dir.list();
		code = new char[names.length];
		for(int i=0; i<code.length; i++){
			//Set the codes.
			code[i] = names[i].charAt(0);
			imageArray[i] = getSel(IMAGE_PATH+names[i]);
		}
	}
	
	private int[][] getSel(String fileName){
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
		for(int i=0; i<13; i++){
			found = false;
			for(int j=0; j<imageArray[i].length; j++){
				if(found){
					break;
				}
				for(int k=0; k<imageArray[i][j].length; k++){
					if(found){
						break;
					}
					found = true;
					for(int l=0; l<13; l++){
						if(i != l){
							if(imageArray[i][j][k] == imageArray[l][j][k]){
								found = false;
							}
						}
					}
					if(found){
						pixelPosition[i][0] = j;
						pixelPosition[i][1] = k;
						for(int p=0; p<i; p++){
							if(pixelPosition[p][0] == j && pixelPosition[p][1] == k || j < 3 || k < 3){
								found = false;
							}
						}
					}
				}
			}
		}
	}
	
}