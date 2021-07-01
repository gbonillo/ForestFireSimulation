package jezz.forestfiresimulation;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import jezz.forestfiresimulation.conf.Conf;
import jezz.forestfiresimulation.conf.ConfFactory;
import jezz.forestfiresimulation.display.Display;
import jezz.forestfiresimulation.display.awt.AwtDisplay;
import jezz.forestfiresimulation.display.console.ConsoleDisplay;
import jezz.forestfiresimulation.engine.Engine;
import jezz.forestfiresimulation.utils.Utils;

/**
 * Main class 
 * Gère les paramètres et l'instanciation de la Conf 
 * et de la Classe principale à exécuter.
 * 
 * @author jezz
 */
public class ForestFireSimulation {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger.getLogger(ForestFireSimulation.class.getName()).log(Level.FINE, "args={0}", Arrays.toString(args));
        try {
            Conf conf = ConfFactory.loadConf();
            if (conf == null){
                Logger.getLogger(ForestFireSimulation.class.getName()).log(Level.WARNING, "Fichier de conf introuvable! => Chargement d'une conf de test!");
                conf = ConfFactory.getSampleConf();
            }

            Engine engine = new Engine(conf);
            
            Display display;
            if (Utils.contains(args, "console"))
                display = new ConsoleDisplay(engine);
            else 
                display = new AwtDisplay(engine);

            display.run(args);
            
        }
        catch (IOException ex) {
            Logger.getLogger(ForestFireSimulation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
