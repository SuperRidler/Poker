//Set up to allow quick single instances of the poker robot.

package com;

import site.Site;
import strategy.Strategy;

public class Bot {

	Strategy strat;
	Site site;
	
	public static void main(String[] args) {
		if(args.length == 0){
			System.out.println("No strategy or site given.");
		}else if(args.length != 2){
			System.out.println("Wrong number of parametres");
		}else{
			//Strategy and site given, extract them from the args array.
		}
	}

}
