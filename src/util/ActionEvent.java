package util;

public class ActionEvent {
	private Action action;
	private int raiseAmount = 0;
	
	public ActionEvent(Action action, int raiseAmount){
		this.action = action;
		this.raiseAmount = raiseAmount;
	}
	
	public ActionEvent(Action action){
		this.action = action;
	}
	
	public Action getAction(){
		return action;
	}
	
	public int getRaiseAmount(){
		return raiseAmount;
	}
	
}
