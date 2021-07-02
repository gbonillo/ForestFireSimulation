package jezz.forestfiresimulation.conf;

import java.util.Collection;
import jezz.forestfiresimulation.engine.Position;

/**
 * Interface pour les données de configuration
 *
 * @author jezz
 */
public interface Conf {

    public int getHeight();
    public int getWidth();
    public double getProba();
    public long getRandomSeed();
    public Collection<Position> getFirePositions();

}
