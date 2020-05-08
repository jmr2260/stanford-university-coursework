package assign1;

public class TetrisGrid {

	public static void main(String[] args) {
		
	}
	    private boolean[][] grid;

	    /**
	     * Constructs a new instance with the given grid.
	     * Does not make a copy.
	     *
	     * @param grid to set class variable
	     */
	    public TetrisGrid(boolean[][] grid) {
	        this.grid = grid;

	    }

	    /**
	     * Does row-clearing on the grid (see handout).
	     */
	    public void clearRows() {
	        for (int y = 0; y < grid[0].length; y++)
	            if (isRowCompleted(y))
	                deleteRow(y);
	    }

	    private void deleteRow(int y) {
	        for (int currY = y; currY < grid[0].length; currY++)
	            moveRowBelow(currY);
	    }

	    private void moveRowBelow(int y) {
	        for (int x = 0; x < grid.length; x++)
	            if (y < grid[0].length - 1)         
	                grid[x][y] = grid[x][y + 1];
	            else if (y == grid[0].length - 1)
	                grid[x][y] = false;
	    }

	    private boolean isRowCompleted(int y) {
	        for (int x = 0; x < grid.length; x++)
	            if (!grid[x][y])
	                return false;
	        return true;
	    }

	    /**
	     * Returns the internal 2d grid array.
	     *
	     * @return 2d grid array
	     */
	    boolean[][] getGrid() {
	        return grid;
	    }

}
