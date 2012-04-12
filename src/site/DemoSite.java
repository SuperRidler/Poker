//Skeleton to show Site code design.

package site;

import java.awt.AWTException;
import java.awt.Robot;

import util.Info;

public class DemoSite extends Site {

	//The robot that emulates a human.
	Robot robocop;
	
	//Constructor call so we can set up a few things.
	public DemoSite(){
		//When site class is made lets create it a robot.
		try {
			robocop = new Robot();
		} catch (AWTException e) {
			System.out.println("Error making robot.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void action(int com1, int com2) {
		//This abstract method is inherited and must be call-able.
		//Action described by two codes.
		
		//Example
		switch(com1){
		 case 0 : clickCheck(); break;
		 case 1 : clickCall(); break;
		 default : System.out.println("Unknown Instruction"); break;
		}
	}

	private void clickCheck(){
		//Click check button code.
	}
	
	private void clickCall(){
		//Click call button code.
	}

	@Override
	public Info getInfo() {
		//Get all the info a strategy needs and return it as an info object.
		return null;
	}
	
}
