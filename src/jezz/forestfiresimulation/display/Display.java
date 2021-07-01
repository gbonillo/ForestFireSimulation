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
    
    protected boolean runOneStep(){
        boolean end = engine.step();
        displayStates();
        displayForest();
        return end;
    }
    
    protected void runToEnd(){
        engine.stepToEnd();
		displayStates();
		displayForest();
    }
    
	abstract public void displayStates();
	abstract public void displayForest();
	
}
