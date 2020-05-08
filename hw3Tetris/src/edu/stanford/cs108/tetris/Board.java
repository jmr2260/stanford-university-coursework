// Board.java
package edu.stanford.cs108.tetris;

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearing.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Instead,
 just represents the abstract 2-d board.
*/
public class Board	{
	// Some ivars are stubbed out for you:
	private int width;
	private int height;
	private boolean[][] grid;
	private boolean DEBUG = true;
	boolean committed;
	private int[] widths;
	private int[] heights;
	private int maxHeight;
	private boolean[][] copyGrid;
	private int[] copyWidths;
	private int[] copyHeights;
	private int copyMaxHeight;
	
	public static final int PLACE_OK = 0;
	public static final int PLACE_ROW_FILLED = 1;
	public static final int PLACE_OUT_BOUNDS = 2;
	public static final int PLACE_BAD = 3;

	// Here a few trivial methods are provided:
	
	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	*/
	public Board(int width, int height) { // This is a constructor
		this.width = width;
		this.height = height;
		grid = new boolean[width][height];
		committed = true;
		widths = new int[height];
		heights = new int[width];
		maxHeight = 0;
		copyWidths = new int[widths.length];
		copyHeights = new int[heights.length];
		copyGrid = new boolean[width][height];
	}
	
	
	/**
	 Returns the width of the board in blocks.
	*/
	public int getWidth() {
		return width;
	}
	
	
	/**
	 Returns the height of the board in blocks.
	*/
	public int getHeight() {
		return height;
	}
	
	
	/**
	 Returns the max column height present in the board.
	 For an empty board this is 0.
	*/
	public int getMaxHeight() {	 
		return maxHeight; // Return the maximum height of the Tetris pile achieved so far
	}
	
	
	/**
	 Checks the board for internal consistency -- used
	 for debugging.
	*/
	public void sanityCheck() {
		if (DEBUG) {
			int trueMaxHeight = 0; 
			for (int c = 0; c < heights.length; c++) {
				int trueHeight = 0;
				int d;
				for (d = height - 1; d >= 0; d--) {
					if (grid[c][d]) {
						break;
					}
				}
				trueHeight = d + 1;
				trueMaxHeight = Math.max(trueMaxHeight, trueHeight);
				if (trueHeight != heights[c]) {
					throw new RuntimeException(String.format("Error – heights[%d] = %d. The correct height is %d.", c, heights[c], trueHeight));
				}
			}
			if (maxHeight != trueMaxHeight) {
				throw new RuntimeException(String.format("Error – maxHeight = %d. The correct maxHeight is %d.", maxHeight, trueMaxHeight));
			}

			for (int c = 0; c < widths.length; c++) {
				int trueWidth = 0;
				for (int d = 0; d < width; d++) {
					if (grid[d][c] == true) {
						trueWidth += 1;
					}
				}
				if (trueWidth != widths[c]) {
					throw new RuntimeException(String.format("Error – widths[%d] = %d. The correct width is %d.", c, widths[c], trueWidth));
				}
			}

		}
	}
	
	/**
	 Given a piece and an x, returns the y
	 value where the piece would come to rest
	 if it were dropped straight down at that x.
	 
	 <p>
	 Implementation: use the skirt and the col heights
	 to compute this fast -- O(skirt length).
	*/
	public int dropHeight(Piece piece, int x) {
		int y = 0;
		for(int c =0 ; c < piece.getSkirt().length;c++)
		{
			if(y < heights[x+c]-piece.getSkirt()[c])
				y = heights[x+c]-piece.getSkirt()[c];
		}
		return y;
	}
	
	
	/**
	 Returns the height of the given column --
	 i.e. the y value of the highest block + 1.
	 The height is 0 if the column contains no blocks.
	*/
	public int getColumnHeight(int x) {
		return heights[x];
	}
	
	
	/**
	 Returns the number of filled blocks in
	 the given row.
	*/
	public int getRowWidth(int y) {
		 return widths[y];
	}
	
	
	/**
	 Returns true if the given block is filled in the board.
	 Blocks outside of the valid width/height area
	 always return true.
	*/
	public boolean getGrid(int x, int y) {
		if (y < 0 || y >= height || x < 0 || x >= width) {
			return true;
		} else {
			return grid[x][y];
		}
	}
	
	
	
	/**
	 Attempts to add the body of a piece to the board.
	 Copies the piece blocks into the board grid.
	 Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
	 for a regular placement that causes at least one row to be filled.
	 
	 <p>Error cases:
	 A placement may fail in two ways. First, if part of the piece may falls out
	 of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 Or the placement may collide with existing blocks in the grid
	 in which case PLACE_BAD is returned.
	 In both error cases, the board may be left in an invalid
	 state. The client can use undo(), to recover the valid, pre-place state.
	*/
	public int place(Piece piece, int x, int y) {
		// flag !committed problem
		int currentX, currentY;
		
		if (!committed) throw new RuntimeException("place commit problem");

		System.arraycopy(widths, 0, copyWidths, 0, widths.length);
		System.arraycopy(heights, 0, copyHeights, 0, heights.length);
		for (int c = 0; c < width; c++) {
			System.arraycopy(grid[c], 0, copyGrid[c], 0, height);
		}
		copyMaxHeight = maxHeight;

		committed = false;

		if (x < 0 || y < 0 || x >= width || y >= height || piece.getWidth() + x > width || piece.getHeight() + y > height) {
			return PLACE_OUT_BOUNDS;
		}

		int result = PLACE_OK;
		
		
		TPoint[] bodyOfPiece = piece.getBody();
		for (int c = 0; c < bodyOfPiece.length; c++) {
			currentX = x + bodyOfPiece[c].x;
			currentY = y + bodyOfPiece[c].y;
			if (grid[currentX][currentY]) {
				return PLACE_BAD;
			}

			grid[currentX][currentY] = true;
			widths[currentY] += 1;
			if (widths[currentY] == width) {
				result = PLACE_ROW_FILLED;
			}

			heights[currentX] = Math.max(heights[currentX], currentY  + 1);
			maxHeight = Math.max(maxHeight, heights[currentX]);
		}

		sanityCheck();

		return result;
	}
	
	
	/**
	 Deletes rows that are filled all the way across, moving
	 things above down. Returns the number of rows cleared.
	*/
	public int clearRows() {
	
		int rowsCleared = 0;
		if (committed) {
			System.arraycopy(widths, 0, copyWidths, 0, widths.length);
			System.arraycopy(heights, 0, copyHeights, 0, heights.length);
			for (int c = 0; c < width; c++) {
				System.arraycopy(grid[c], 0, copyGrid[c], 0, height);
			}
			copyMaxHeight = maxHeight;
			
			committed = false;
		}
		
		int maxHeightBefore = maxHeight;
		// Clear filled rows and push rows from above down
		int toRow = 0;
		for (int c = 0; c < maxHeight; c++) {
			if (widths[toRow] < width) {
				toRow++;
			}
		}
		int fromRow = toRow;
		for (int t = toRow; t < maxHeight; t++) {
			fromRow++;
			for (int f = fromRow; f < maxHeight; f++) {
				if (widths[f] == width) {
					fromRow++;
				}
			}				
			if (fromRow < maxHeight) {
				widths[t] = widths[fromRow];
				for (int c = 0; c < width; c++) {
					grid[c][t] = grid[c][fromRow]; // Push fromRow squares to the toRow
				}
			} else {
				widths[t] = 0;
				for (int c = 0; c < width; c++) {
					grid[c][t] = false; // Clear the row if it is filled
				}
			}
		}
		
		// Update maxHeight and heights array 
		maxHeight = 0;
		for (int c = 0; c < heights.length; c++) { 
			int d;
			for (d = heights[c] - 1; d >= 0 ; d--) {
				if (grid[c][d] == true) {
					break;
				}
			} 
			heights[c] = d + 1; // This is the new heights array
			maxHeight = Math.max(maxHeight, heights[c]);
		}
		
		sanityCheck();
		rowsCleared = maxHeightBefore - maxHeight;
		return rowsCleared; // This difference gives the number of rows cleared
	}

	/**
	 Reverts the board to its state before up to one place
	 and one clearRows();
	 If the conditions for undo() are not met, such as
	 calling undo() twice in a row, then the second undo() does nothing.
	 See the overview docs.
	*/
	public void undo() {
		if (!committed) {
			committed = true;
			
			maxHeight = copyMaxHeight; // Restoring to previously saved maxHeight
			
			int[] swapInt; 
			
			swapInt = heights; 
			heights = copyHeights; // Restoring to previously saved heights
			copyHeights = swapInt;
			
			swapInt = widths;
			widths = copyWidths; // Restoring to previously saved widths
			copyWidths = swapInt;
			
			boolean[][] swapBool;
			swapBool = grid;
			grid = copyGrid; // Restoring to previously saved grid
			copyGrid = swapBool;
		}
		
		sanityCheck();
	}
	
	
	/**
	 Puts the board in the committed state.
	*/
	public void commit() {
		committed = true;
	}


	
	/*
	 Renders the board state as a big String, suitable for printing.
	 This is the sort of print-obj-state utility that can help see complex
	 state change over time.
	 (provided debugging utility) 
	 */
	public String toString() {
		StringBuilder buff = new StringBuilder();
		for (int y = height-1; y>=0; y--) {
			buff.append('|');
			for (int x=0; x<width; x++) {
				if (getGrid(x,y)) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x=0; x<width+2; x++) buff.append('-');
		return(buff.toString());
	}
}


