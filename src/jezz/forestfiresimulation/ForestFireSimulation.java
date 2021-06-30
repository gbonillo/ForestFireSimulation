package jezz.forestfiresimulation;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import jezz.forestfiresimulation.conf.Conf;
import jezz.forestfiresimulation.conf.ConfFactory;

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
        Logger.getLogger(ForestFireSimulation.class.getName()).log(Level.INFO, "args={0}", Arrays.toString(args));
        try {
            
            Conf conf = ConfFactory.loadConf();
            if (conf == null){
                Logger.getLogger(ForestFireSimulation.class.getName()).log(Level.INFO, "Fichier de conf introuvable! => Chargement d'une conf de test!");
                conf = ConfFactory.getSampleConf();
            }
            
            ConsoleForestFireSimulation sim = new ConsoleForestFireSimulation(conf);
            if ( args.length>0 && "step".equals(args[0]) ){
                sim.runByStep();
            }
            else {
                sim.runFull();
            }
        }
        catch (IOException ex) {
            Logger.getLogger(ForestFireSimulation.class.getName()).log(Level.SEVERE, null, ex);
        }
		  
    }

}
