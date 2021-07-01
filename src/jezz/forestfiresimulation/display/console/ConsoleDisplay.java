package jezz.forestfiresimulation.display.console;

import java.io.PrintStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;
import jezz.forestfiresimulation.display.Display;
import jezz.forestfiresimulation.engine.Cell;
import jezz.forestfiresimulation.engine.Engine;
import jezz.forestfiresimulation.engine.FireState;
import jezz.forestfiresimulation.utils.Utils;

/**
 * Affichage de la simulation dans la console (System.out)
 * 
 * @author jezz
 */
public class ConsoleDisplay extends Display {

	public ConsoleDisplay(Engine engine) {
		super(engine);
	}
	
	private Map<FireState, String> fireStateSymbol = new EnumMap<FireState,String>(FireState.class);
	{
		fireStateSymbol.put(FireState.NO_FIRE, "_");
		fireStateSymbol.put(FireState.ON_FIRE, "#");
		fireStateSymbol.put(FireState.BURNT,   ".");
	}
	
	@Override
	public void displayStates() {
		PrintStream ps = System.out;
		ps.println("Forest Fire Simulation [ConsoleDisplay] :");
		ps.println("  temps : " + engine.getTime());
		ps.println("  nb cellules brûlées : " + engine.getForest().getBurntCellsCount());
		ps.println("  nb cellules en feu  : " + engine.getForest().getOnFireCellsCount());
	}

	@Override
	public void displayForest() {
		PrintStream ps = System.out;
		Cell[][] cells = engine.getForest().getCells();
		for (int y = 0; y<cells.length; y++){
			for (int x = 0; x<cells[y].length; x++){
                if (x>0) ps.print(" ");
				ps.print(fireStateSymbol.get(cells[y][x].getFireState()));
			}
			ps.println();
		}
	}
	
    @Override
    public void run(String[] args){
        displayStates();
		displayForest();
        if ( Utils.contains(args, "step") ){
            runByStep();
        }
        else {
            runToEnd();
        }
    }
    
	void runByStep(){
        try (Scanner sc = new Scanner(System.in)) {
            boolean end = false;
            while (!end && sc.nextLine() != null){
                end = runOneStep();
            }
        }
    }
	
}
