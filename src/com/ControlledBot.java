//Set up in a way that allows easy control with a controller class.

package com;

import strategy.Strategy;
import site.Site;

public class ControlledBot {

	Strategy strat;
	Site site;
	
	public ControlledBot(Site site, Strategy strat){
		this.site = site;
		this.strat = strat;
		
		//Now with a site and a strategy to play, do something.
		//winAllTheMoney();
	}
	
}
