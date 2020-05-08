package assign1;

public class CharGrid {
	public static int x = 10;
	private static char[][] grid;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//grid = new char[][] {
		//	{'a', 'b', 'c', 'd'},
		//	{'a', 'c', 'c', 'b'},
		//	};
			
		grid = new char[][] {
			{'a', 'p', 'c', 'd'},
			{'p', 'p', 'p', 'b'},
			{'x', 'p', 'c', 'a'}
			};
		
		System.out.println(charArea('a')); //12
		System.out.println(charArea('b')); //9
		System.out.println(charArea('c')); //6
		System.out.println(charArea('x')); //1
		System.out.println(charArea('v')); //0
		System.out.println(countPlus());
		//System.out.println(grid.length); // grid.length returns how many rows the grid has not columns
		//System.out.println(grid[0].length); // grid[0].length returns how many columns the grid has not rows
	}
	
	public static void example() {
        // int x = 10;
        System.out.println("x equals " + x);

	}
    /**
     * Constructs a new CharGrid with the given grid.
     * Does not make a copy.
     *
     * @param grid to set class variable
     */
    public CharGrid(char[][] grid) { // This is a constructor for the class
        this.grid = grid;
    }

    /**
     * Returns the area for the given char in the grid. (see handout).
     *
     * @param ch char to look for
     * @return area for given char
     */
    public static int charArea(char ch) {
    	int rowLength;
    	int colLength;
    	
    	// Step 1: Do the rows
    	int minCol = 9999; 
        int maxCol = -9999; // Integer can't be a null. -9999 is a stand in for a null.
    	for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == ch) {
                    if (col < minCol) minCol = col; // Find the lowest low
                    if (col > maxCol) maxCol = col; // Find the highest high
                }
            }
    	}
    	if ((minCol != 9999) && (maxCol != -9999)) {
    		rowLength = maxCol - minCol + 1;
    	} else {
    		rowLength = 0;
    	}
    	// Step 2: Do the columns
    	int minRow = 9999; 
        int maxRow = -9999; // Integer can't be a null. -9999 is a stand in for a null.
    	for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] == ch) {
                    if (row < minRow) minRow = row;
                    if (row > maxRow) maxRow = row;
                }
            }
    	}
    	if ((minRow != 9999) && (maxRow != -9999)) {
    		colLength = maxRow - minRow + 1;		
    	} else {
    		colLength = 0;
    	}
    	return rowLength*colLength;
    }	


    /**
     * Returns the count of '+' figures in the grid (see handout).
     *
     * @return number of + in grid
     */
    public static int countPlus() {
        int numberOfCrosses = 0;

        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[0].length; col++)
                if (isValidCross(row, col))
                    numberOfCrosses++;

        return numberOfCrosses;
    }

    private static boolean isValidCross(int row, int col) {
        int left = countCharsInDir(row, col, 0, -1);
        int right = countCharsInDir(row, col, 0, 1);
        int down = countCharsInDir(row, col, -1, 0);
        int up = countCharsInDir(row, col, 1, 0);

        return left != 0 && left == right && left == up && left == down;
    }

    private static int countCharsInDir(int row, int col, int shiftRow, int shiftCol) { // This is a recursion.
        int nextRow = row + shiftRow;
        int nextCol = col + shiftCol;

        if (isValidCell(nextRow, nextCol) && grid[row][col] == grid[nextRow][nextCol])
            return 1 + countCharsInDir(nextRow, nextCol, shiftRow, shiftCol);
        else return 0;
    }

    private static boolean isValidCell(int row, int col) {
        return row < grid.length && grid.length > 0 && col < grid[0].length && col >= 0 && row >= 0;
    }

}
