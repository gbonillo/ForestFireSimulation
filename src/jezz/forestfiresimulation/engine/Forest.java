package jezz.forestfiresimulation.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Représentaiton de la forêt
 * Contient la grille de cellule de la forêt 2D, 
 * ainsi que les listes des cellules en feu et brulées.
 *  
 * @author jezz
 */
public class Forest {
	
    /**
     * directions de la grille pour rechercher les voisin d'une cellule
     */
	static private int[][] dirs = new int[][] {
		{-1,0}, {1,0}, {0,-1}, {0,1}
	};
	
	final public int width;
	final public int height;
	Cell[][] cells;
	List<Cell> onFireCells;
	List<Cell> burntCells;
	
	public Forest(int width, int height, Collection<Position> firePosition) {
		this.width = width;
		this.height = height;
		cells = new Cell[height][width];
		onFireCells = new ArrayList<Cell>();
		burntCells = new ArrayList<Cell>();
		for (int y=0; y<height; y++){
			for (int x=0; x<width; x++){
				Cell cell = new Cell(x, y);
				cells[y][x] = cell;
				if (firePosition.contains(cell)){
					cell.fireState = FireState.ON_FIRE;
					onFireCells.add(cell);
				}
			}
		}
	}
	
    /**
     * @return tableau 2D des cellules
     */
	public Cell[][] getCells(){
		return cells;
	}
    
	/**
     * @return nb de cellules en feu
     */
	public int getOnFireCellsCount() {
		return onFireCells.size();
	}
    
	/**
     * @return nb de cellules brûlées
     */
	public int getBurntCellsCount() {
		return burntCells.size();
	}
	
	/**
	 * @param cell
	 * @return les cellules voisines de la cellule ET qui peuvent s'enflammer
	 */
	List<Cell> getCellFlammableNeighbors(Cell cell){
		List<Cell> cs = new ArrayList<>();
		
		for (int[] dir : dirs) {
			int nx = cell.x + dir[0]; 
			int ny = cell.y + dir[1]; 
			
			if (nx>=0 && ny>=0 && nx<width && ny<height){
				Cell n = cells[ny][nx];
				if (n.fireState == FireState.NO_FIRE){
					cs.add(n);
				}
			}
		}
		
		return cs;
	}
	
}
