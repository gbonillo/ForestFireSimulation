package jezz.forestfiresimulation.conf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author jezz
 */
public class ConfFactory {
    
    /**
     * Charge le fichier de conf "conf.properties" au même niveau que le Jar lors de l'exécution.
     * @return La conf ou null si fichier non trouvé
     */
    static public Conf loadConf() {
        try {
            File jarDirectory = new File(ConfFactory.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
            //File f = new File("./conf.properties");
            File f = new File( jarDirectory.getPath() + "/conf.properties");
            if (f.exists()){
                return new PropertiesConf(f);
            }
            else {
                Logger.getLogger(ConfFactory.class.getName()).log(Level.WARNING, "Conf file:{0} not found!", f.getAbsolutePath());
            }
        }
        catch(IOException | URISyntaxException ex){
            Logger.getLogger(ConfFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Charge un fichier de conf par défaut (sample.properties dans ce répertoire)
     * @return 
     * @throws IOException 
     * @throws java.io.FileNotFoundException 
     */
    static public Conf getSampleConf() throws IOException, FileNotFoundException {
        Conf conf;
        try (InputStream resource = ConfFactory.class.getResourceAsStream("sample.properties")) {
            conf = new PropertiesConf(resource);
        }
        return conf;
    }
    
}
