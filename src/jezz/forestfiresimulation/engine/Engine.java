package jezz.forestfiresimulation.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jezz.forestfiresimulation.conf.Conf;

/**
 * Moteur de la simulation Gère l'avancement du temps et la transformation de la
 * forêt à chaque étape.
 *
 * @author jezz
 */
public class Engine {

    Conf conf;
    Forest forest;
    double probaFire;

    /**
     * si != 0 permet d'utilisé toujours le même "seed" pour obtenir le même
     * tirage aléatoire et donc la même propagation du feu
     */
    long simulationRandomSeed = 0;

    Random randomGen;

    int time;

    public Engine(Conf conf) {
        this.conf = conf;
        init();
    }

    public void reset() {
        init();
    }

    private void init() {
        this.time = 0;
        this.forest = new Forest(conf.getWidth(), conf.getHeight(), conf.getFirePositions());
        this.probaFire = conf.getProba();
        this.simulationRandomSeed = conf.getRandomSeed();
        if (this.simulationRandomSeed == 0) {
            this.simulationRandomSeed = System.currentTimeMillis();
        }
        this.randomGen = new Random(simulationRandomSeed);
    }

    public Forest getForest() {
        return forest;
    }

    public double getProbaFire() {
        return probaFire;
    }

    public int getTime() {
        return time;
    }

    /**
     * Détermine de façon "pseudo" aléatoire si une cellule voisine doit prendre
     * feu
     *
     * @return true avec la probabilité "probaFire"
     */
    private boolean randomSetOnFire() {
        return randomGen.nextDouble() < probaFire;
    }

    /**
     * Avance la simulation d'une unité de temps
     *
     * @return true si la simulation est terminée;
     */
    public boolean step() {
        if (forest.onFireCells.isEmpty())
            return true; // si on a déjà fini, on fige le temps

        List<Cell> newFireCells = new ArrayList<>();
        for (Cell c : forest.onFireCells) {
            c.fireState = FireState.BURNT;
            forest.burntCells.add(c);
            List<Cell> neighbors = forest.getCellFlammableNeighbors(c);
            for (Cell n : neighbors) {
                if (randomSetOnFire()) {
                    n.fireState = FireState.ON_FIRE;
                    newFireCells.add(n);
                }
            }
        }
        forest.onFireCells = newFireCells;
        time++;
        return forest.onFireCells.isEmpty();
    }

    /**
     * Avance la simulation jusqu'à la fin
     */
    public void stepToEnd() {
        while (!step());
    }

}
