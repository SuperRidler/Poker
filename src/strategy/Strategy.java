package strategy;

import util.Info;

abstract public class Strategy {
	
	abstract public void update(Info info);
	
	abstract public int whatToDo();
	
}
