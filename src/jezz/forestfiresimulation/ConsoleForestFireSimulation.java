package jezz.forestfiresimulation;

import java.util.Scanner;
import jezz.forestfiresimulation.conf.Conf;
import jezz.forestfiresimulation.display.ConsoleDisplay;
import jezz.forestfiresimulation.display.Display;
import jezz.forestfiresimulation.engine.Engine;

/**
 * Classe principale de l'application pour 
 * l'affichage et le rendu dans la console
 * 
 * @author jezz
 */
public class ConsoleForestFireSimulation {
    Conf conf;
    Engine engine;
    Display display;
    
    public ConsoleForestFireSimulation(Conf conf) {
        conf = conf;
        engine = new Engine(conf);
        display = new ConsoleDisplay(engine);
    }
    
    public void runByStep(){
        display.displayStates();
		display.displayForest();
        try (Scanner sc = new Scanner(System.in)) {
            boolean end = false;
            while (!end && sc.nextLine() != null){
                end = engine.step();
                display.displayStates();
                display.displayForest();
            }
        }
    }
    
    public void runFull(){
        display.displayStates();
		display.displayForest();
		engine.stepToEnd();
		display.displayStates();
		display.displayForest();
    }
}
