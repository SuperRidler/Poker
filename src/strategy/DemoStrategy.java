package strategy;

import util.Info;

public class DemoStrategy extends Strategy {

	int[] cardsOnTable;
	
	@Override
	public void update(Info info) {
		//Example on info extraction from object.
		cardsOnTable = info.getCardsOnTable();
	}

	@Override
	public int whatToDo() {
		if(numberOfCardsOnTable() == 0){
			//Pre-Flop
		}else{
			//Post-Flop
		}
		return 0;
	}
	
	//Example of private method.
	private int numberOfCardsOnTable(){
		return cardsOnTable.length;
	}

}
