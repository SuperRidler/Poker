package com;

import site.*;
import strategy.*;

public class Controller {
	
	public static void main(String args[]){
		new ControlledBot(new DemoSite(), new DemoStrategy());
	}
	
}
