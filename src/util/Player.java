package util;

import strategy.Strategy;

public class Player {
	
	String name;
	int chips = 0;
	int[] currentHand = new int[2];
	PlayerState state = PlayerState.WAITING;
	Strategy strat;
	
	public Player(String name, Strategy strat, int chips){
		this.name = name;
		this.strat = strat;
		this.chips = chips;
	}
	
	public void setHand(int card1, int card2){
		currentHand[0] = card1;
		currentHand[2] = card2;
	}
	
	public void checkForBankruptcy(){
		if(chips < 1){
			//No chips left.
			state = PlayerState.BANKRUPT;
		}
	}
	
	public boolean isBankrupt(){
		return state==PlayerState.BANKRUPT;
	}
	
	public void removeChips(int chips){
		this.chips -= chips;
	}
	
	public void addChips(int chips){
		this.chips += chips;
	}
	
	public PlayerState getState(){
		return state;
	}
	
	@Override
	public String toString(){
		return name+": "+chips;
	}
	
}
