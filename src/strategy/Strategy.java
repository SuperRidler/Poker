package strategy;

abstract public class Strategy {
	
	abstract public void update(int[] cardsOnTable, int playerCardOne, int playerCardTwo, int currentChips,
								int dealerPos, int[] opponentMove, int[] opponentPostion);
	
}
