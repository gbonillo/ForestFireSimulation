package jezz.forestfiresimulation.display;

import jezz.forestfiresimulation.engine.Engine;

/**
 * Abstraction pour les objets affichant des informations sur la simulation
 * 
 * @author jezz
 */
public abstract class Display {
	
	Engine engine;

	public Display(Engine engine) {
		this.engine = engine;
	}
	
	abstract public void displayStates();
	abstract public void displayForest();
	
}
