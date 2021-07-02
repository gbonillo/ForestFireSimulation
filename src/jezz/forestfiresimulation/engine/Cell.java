package jezz.forestfiresimulation.engine;

/**
 * Cellule de la forêt
 *
 * @author jezz
 */
public class Cell extends Position {

    FireState fireState = FireState.NO_FIRE;

    public Cell(int x, int y) {
        super(x, y);
    }

    public FireState getFireState() {
        return fireState;
    }

}
