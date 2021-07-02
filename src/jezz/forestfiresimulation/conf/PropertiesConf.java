package jezz.forestfiresimulation.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import jezz.forestfiresimulation.engine.Position;

/**
 * Implemente le chargement de la configuration avec des fichiers de conf de
 * type "properties"
 *
 * @author jezz
 */
public class PropertiesConf extends DefaultConf {

    public PropertiesConf(File f) throws FileNotFoundException, IOException {
        load(f);
    }

    public PropertiesConf(InputStream i) throws IOException {
        load(i);
    }

    private void load(File f) throws FileNotFoundException, IOException {
        try (FileInputStream fi = new FileInputStream(f)) {
            load(fi);
        }
    }

    private void load(InputStream f) throws IOException {
        Properties p = new Properties();
        p.load(f);
        this.height = Integer.parseInt(p.getProperty("height"));
        this.width = Integer.parseInt(p.getProperty("width"));
        this.proba = Double.parseDouble(p.getProperty("proba"));
        this.randomSeed = Long.parseLong(p.getProperty("randomSeed"));

        this.firePositions = new ArrayList<>();
        String posStr = p.getProperty("firePos");
        String[] posStrArr = posStr.trim().split(";");
        for (String s : posStrArr) {
            String[] xyStr = s.split(",");
            firePositions.add(new Position(Integer.parseInt(xyStr[0]), Integer.parseInt(xyStr[1])));
        }
    }
}
