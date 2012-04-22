package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UniquePixelGenIn {
	
	private String FILE_NAME = "";
	char[] letters;
	int[][] locations;
	int[] colours;
	
	public UniquePixelGenIn(String FILE_NAME){
		this.FILE_NAME = FILE_NAME;
		loadArrays();
		System.out.println("Values Loaded.");
	}
	
	private void loadArrays(){
		try {
			BufferedReader in = new BufferedReader(new FileReader(FILE_NAME));
			int lines = 0;
			String line = "";
			while((line = in.readLine()) != null){
				lines++;
			}
			letters = new char[lines];
			locations = new int[lines][2];
			colours = new int[lines];
			int i = 0;
			in = new BufferedReader(new FileReader(FILE_NAME));
			while((line = in.readLine()) != null){
				String[] splitString = line.split(":");
				letters[i] = splitString[0].charAt(0);
				locations[i][0] = Integer.parseInt(splitString[1]);
				locations[i][1] = Integer.parseInt(splitString[2]);
				colours[i] = Integer.parseInt(splitString[4]);
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public char thisIs(int[][] imageArray){
		for(int i=0; i<letters.length; i++){
			int x = locations[i][0];
			int y = locations[i][1];
			if(imageArray[x][y] == colours[i]){
				return letters[i];
			}
		}
		return 'X';
	}
	
}
