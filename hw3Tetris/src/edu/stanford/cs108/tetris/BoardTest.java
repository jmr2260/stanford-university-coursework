package edu.stanford.cs108.tetris;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;

public class BoardTest {
	
	Board b;
	Piece pyr1, pyr2, pyr3, pyr4, l, s, sRotated;

	@Before
	public void setUp() throws Exception {
		b = new Board(3, 8); // 3 columns and 6 rows
		
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		l = new Piece(Piece.L1_STR);
		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();
		b.place(pyr3, 0, 0);
	}
		
	@Test
	public void test1() {
		assertEquals(1, b.getRowWidth(0));
		assertEquals(3, b.getRowWidth(1));
		assertEquals(0, b.getRowWidth(2));
		assertEquals(2, b.getColumnHeight(0));
		assertEquals(2, b.getColumnHeight(1));
		assertEquals(2, b.getColumnHeight(2));
		assertEquals(2, b.getMaxHeight());
	}
	
	@Test
	public void test2() {
		b.commit();
		int result = b.place(l, 1, 2);
		assertEquals(Board.PLACE_OK, result);
		assertEquals(2, b.getColumnHeight(0));
		assertEquals(5, b.getColumnHeight(1));
		assertEquals(3, b.getColumnHeight(2));
		assertEquals(5, b.getMaxHeight());
	}
	
	
	@Test
	public void test3() {
		b.commit();
		int result;
		result = b.dropHeight(s, 0);
		assertEquals(2, result);	
	}
	
	@Test
	public void test4() {
		b.commit();
		assertEquals(2,b.dropHeight(pyr1, 0));

	}
		
	@Test
	public void test6() {
		b.commit();
		assertEquals(2,b.dropHeight(pyr3, 0));

	}
	
	@Test
	public void test7() {
		b.commit();
		int result = b.place(l, 1, 2);
		assertEquals(Board.PLACE_OK, result);
		b.clearRows();
		assertEquals(0, b.getColumnHeight(0));
		assertEquals(4, b.getColumnHeight(1));
		assertEquals(2, b.getColumnHeight(2));
		assertEquals(4, b.getMaxHeight());
	}
	
	@Test
	public void test8() {
		b.commit();
		int result;
		String current = b.toString();
		result = b.place(pyr3, 0, 0);
		assertEquals(Board.PLACE_BAD, result);
		if (result == Board.PLACE_BAD) 
			b.undo();
		assertEquals(b.toString(), current);	
	}
	
	@Test
	public void test9() {
		b.commit();
		int result;
		String current = b.toString();
		result = b.place(l, 0, 0);
		assertEquals(Board.PLACE_BAD, result);
		if (result == Board.PLACE_BAD) 
			b.undo();
		assertEquals(b.toString(), current);	
	}
	
	//@Test
	//public void test5() {
		//b.commit();
		//int result = b.place(pyr2, 1, 2);
		//assertEquals(Board.PLACE_BAD, result);
		//if (result == Board.PLACE_BAD) 
			//b.undo();
		//assertEquals(b.toString(), ori);
		//assertEquals(2, b.getColumnHeight(0));
		//assertEquals(5, b.getColumnHeight(1));
		//assertEquals(3, b.getColumnHeight(2));
		//assertEquals(2, b.getMaxHeight());
	//}


	
}
