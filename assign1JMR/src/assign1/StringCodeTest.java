package assign1;
import org.junit.Test;
import static org.junit.Assert.*;


public class StringCodeTest {

	// StringCodeTest
	// Some test code is provided for the early HW1 problems,
	// and much is left for you to add.
	    //
	    // blowup
	    //
	
	@Test
	public void testBlowup1() {
		// basic cases
		assertEquals("xxaaaabb", StringCode.blowup("xx3abb"));
		assertEquals("xxxZZZZ", StringCode.blowup("2x3Z"));
		assertEquals("xddddyyy", StringCode.blowup("x3d2y"));
		assertEquals("fffddsss", StringCode.blowup("2f1d2s"));
		assertEquals("fqqqqqqq", StringCode.blowup("f3q2q"));
	}
	
	@Test
	public void testBlowup2() {
		// things with digits
		
		// digit at end
		assertEquals("axxx", StringCode.blowup("a2x3"));
		
		// digits next to each other
		assertEquals("a33111", StringCode.blowup("a231"));
		
		// try a 0
		assertEquals("aabb", StringCode.blowup("aa0bb"));
		assertEquals("", StringCode.blowup("01"));
		assertEquals("000", StringCode.blowup("30"));
	}
	
	@Test
	public void testBlowup3() {
		// weird chars, empty string, only symbols
		assertEquals("AB&&,- ab", StringCode.blowup("AB&&,- ab"));
		assertEquals("", StringCode.blowup(""));
		assertEquals("%",StringCode.blowup("%"));
		
		// string with only digits
		assertEquals("", StringCode.blowup("2"));
		assertEquals("33", StringCode.blowup("23"));
	}
		
	//
	// maxRun
	//
	@Test
	public void testRun1() {
		assertEquals(2, StringCode.maxRun("hoopla"));
		assertEquals(3, StringCode.maxRun("hoopllla"));
		assertEquals(6, StringCode.maxRun("------hhhhoop")); // This should be 6
		assertEquals(0,StringCode.maxRun(""));
	}
	
	@Test
	public void testRun2() {
		assertEquals(3, StringCode.maxRun("abbcccddbbbxx"));
		assertEquals(0, StringCode.maxRun(""));
		assertEquals(3, StringCode.maxRun("hhhooppoo"));
		assertEquals(3, StringCode.maxRun("xxyyyz"));
	    assertEquals(4, StringCode.maxRun("xxxppppqzmmmm"));

	}
	
	@Test
	public void testRun3() {
		// "evolve" technique -- make a series of test cases
		// where each is change from the one above.
		assertEquals(1, StringCode.maxRun("123"));
		assertEquals(2, StringCode.maxRun("1223"));
		assertEquals(2, StringCode.maxRun("112233"));
		assertEquals(3, StringCode.maxRun("1112233"));
		assertEquals(4, StringCode.maxRun("11112233"));
		
	}
	
}	   

