package assign1;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AppearancesTest {

	// utility -- converts a string to a list with one
    // elem for each char.
    private List<String> convertStringToList(String s) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) {
            list.add(String.valueOf(s.charAt(i)));
            // note: String.valueOf() converts lots of things to string form
        }
        return list;
    }

    @Test
    public void testSameCount1() {
        List<String> a = convertStringToList("abbccc");
        List<String> b = convertStringToList("cccbba");
        assertEquals(3, Appearances.sameCount(a, b));
    }

    @Test
    public void testSameCount2() {
        // basic List<Integer> cases
        List<Integer> a = Arrays.asList(1, 2, 3, 1, 2, 3, 5);
        assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 9, 9, 1)));
        assertEquals(2, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1)));
        assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1, 1)));
        assertEquals(4, Appearances.sameCount(a, Arrays.asList(1, 2, 3, 1, 2, 3, 5)));
        assertEquals(0, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 2, 3)));
    }
    
    @Test
    public void testSameCount3() {
		List<Double> p = Arrays.asList(1.0, 1.2, 1.6, 1.6);
		List<Double> q = Arrays.asList(1.1, 1.2, 1.6, 1.6);
        assertEquals(2, Appearances.sameCount(p, q));
    }
    
    @Test
    public void testSameCount4() {
		List<Float> p = Arrays.asList(-1.0f, 1.7f, 1.9f, 1.9f);
		List<Float> q = Arrays.asList(-1.0f, 1.2f, 1.9f, 1.9f);
        assertEquals(2, Appearances.sameCount(p, q));
    }
    
    @Test
    public void testSameCount5() {
        List<String> a = convertStringToList("xyzqqq666hwa");
        List<String> b = convertStringToList("z666yqqqxhaw");
        assertEquals(8, Appearances.sameCount(a, b));
    }
}
