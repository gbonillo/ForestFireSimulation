package jezz.forestfiresimulation.conf;

import java.util.Collection;
import java.util.List;
import jezz.forestfiresimulation.engine.Position;

/**
 *
 * @author jezz
 */
public abstract class DefaultConf implements Conf{
	
	int height;
	int width;
	double proba;
	long randomSeed = 0; // default
	List<Position> firePositions;
	
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public double getProba() {
		return proba;
	}
    
    @Override
	public long getRandomSeed() {
		return randomSeed;
	}

	@Override
	public Collection<Position> getFirePositions() {
		return firePositions;
	}
	
}
