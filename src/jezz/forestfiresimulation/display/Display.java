package jezz.forestfiresimulation.display;

import jezz.forestfiresimulation.engine.Engine;

/**
 * Abstraction pour les objets affichant des informations sur la simulation
 * 
 * @author jezz
 */
public abstract class Display {
	
	protected Engine engine;

	public Display(Engine engine) {
		this.engine = engine;
	}
	
	abstract public void run(String[] args);
	abstract public void displayStates();
	abstract public void displayForest();
	
}
