package assign1;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharGridTest {

	// Test cases for CharGrid -- a few basic tests are provided.
		
		@Test
		public void testCharArea1() {
			char[][] grid = new char[][] {
					{'a', 'y', ' '},
					{'x', 'a', 'z'},
				};
			
			CharGrid charGrid = new CharGrid(grid);
					
			assertEquals(4, charGrid.charArea('a'));
			assertEquals(1, charGrid.charArea('z'));
			assertEquals(1, charGrid.charArea('y'));
			assertEquals(1, charGrid.charArea('x'));
			assertEquals(0, charGrid.charArea('p'));
		}
		
		
		@Test
		public void testCharArea2() {
			char[][] grid = new char[][] {
					{'c', 'a', ' '},
					{'b', ' ', 'b'},
					{' ', ' ', 'a'}
				};
			
			CharGrid charGrid = new CharGrid(grid);
			
			assertEquals(6, charGrid.charArea('a'));
			assertEquals(3, charGrid.charArea('b'));
			assertEquals(1, charGrid.charArea('c'));
			assertEquals(0, charGrid.charArea('p'));
			assertEquals(0, charGrid.charArea('q'));
		}
		
		// TODO Add more tests
		@Test
	    public void testCountPlus1() { // Add more grids
	        char[][] grid = new char[][]{
	                {' ', ' ', ' ', ' ', ' ', 'a'},
	                {' ', ' ', 'q', 'q', ' ', 'i'},
	                {' ', 'p', 'q', 'q', ' ', 'i'},
	                {' ', 'q', 'q', 'q', 'q', 'q'},
	                {'p', 'p', 'p', 'q', 'i', 'i'},
	                {' ', 'q', 'q', 'q', ' ', 'i'},
	                {' ', 'p', ' ', ' ', ' ', ' '},
	                {' ', ' ', ' ', ' ', ' ', ' '},
	                {' ', ' ', 'r', ' ', ' ', ' '},



	        };

	        CharGrid charGrid = new CharGrid(grid);

	        assertEquals(1, charGrid.countPlus());
	    }

		@Test
	    public void testCountPlus2() { // Add more grids
	        char[][] grid = new char[][]{
	                {' ', ' ', ' ', ' ', ' ', 'a', ' ', ' '},
	                {' ', ' ', 'q', 'a', ' ', 'i', ' ', ' '},
	                {' ', 'q', 'q', 'q', ' ', 'i', 'w', ' '},
	                {' ', ' ', 'q', 'i', 'i', 'i', 'i', 'i'},
	                {' ', 'a', ' ', 'i', 'i', 'i', ' ', 's'},
	                {' ', ' ', ' ', 'i', ' ', 'i', ' ', 's'},
	                {' ', 'y', ' ', ' ', ' ', ' ', ' ', 's'},
	                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	                {' ', 'y', ' ', ' ', ' ', ' ', ' ', ' '},



	        };

	        CharGrid charGrid = new CharGrid(grid);

	        assertEquals(2, charGrid.countPlus());
	    }
		

		
	}




