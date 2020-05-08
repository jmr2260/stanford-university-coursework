package assign1;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TabooTest {

	
	@Test
    public void testNoFollow() {
        List<String> list = Arrays.asList("a", "c", "a", "b");
        Taboo<String> taboo = new Taboo<String>(list);

        Set<String> testHashSet1 = new HashSet<String>(Arrays.asList("c", "b"));
        assertTrue(testHashSet1.equals(taboo.noFollow("a")));

        Set<String> testHashSet2 = new HashSet<String>(Arrays.asList("a"));
        assertTrue(testHashSet2.equals(taboo.noFollow("c")));

        Set<String> testHashSet3 = new HashSet<String>();
        assertTrue(testHashSet3.equals(taboo.noFollow("x")));
        
        Set<String> testHashSet4 = new HashSet<String>();
        assertTrue(testHashSet4.equals(taboo.noFollow("b")));
        
        Set<String> testHashSet5 = new HashSet<String>();
        assertTrue(testHashSet5.equals(taboo.noFollow("&")));
    }
	
	@Test
	public void testReduce1() {
        List<String> list = Arrays.asList("p", "q", "p", "r"); // These are the rules
        Taboo<String> taboo = new Taboo<String>(list);

        List<String> newList = new ArrayList<String>(Arrays.asList("p", "q", "r", "z", "q", "p"));
        taboo.reduce(newList);
        List<String> compareList = new ArrayList<String>(Arrays.asList("p", "z", "q"));
        assertTrue(compareList.equals(newList));
	}
	
	@Test
	public void testReduce2() {
        List<String> list = Arrays.asList("r" ,"p", "q", "r", "q"); // These are the rules
        Taboo<String> taboo = new Taboo<String>(list);

        List<String> newList = new ArrayList<String>(Arrays.asList("p", "q", "r", "q", "p", "z", "r"));
        taboo.reduce(newList);
        List<String> compareList = new ArrayList<String>(Arrays.asList("p", "r", "z","r"));
        assertTrue(compareList.equals(newList));
	}

    @Test
    public void testReduce3() {
        List<String> list = Arrays.asList("u", "z", "u", "v", "u");
        Taboo<String> taboo = new Taboo<String>(list);

        List<String> newList = new ArrayList<String>(Arrays.asList("u", "z", "u", "h", "z", "u"));
        taboo.reduce(newList);
        List<String> compareList = new ArrayList<String>(Arrays.asList("u", "u", "h", "z"));
        assertTrue(compareList.equals(newList));
    }

    @Test
     public void testReduce4() {
        List<String> list = Arrays.asList("e", "e", "e");
        Taboo<String> taboo = new Taboo<String>(list);

        List<String> newList = new ArrayList<String>(Arrays.asList("e", "e", "e", "e"));
        taboo.reduce(newList);

        List<String> compareList = new ArrayList<String>(Arrays.asList("e"));
        assertTrue(compareList.equals(newList));

    }
    @Test
    public void testReduce5() {
        List<String> list = Arrays.asList("e", "f");
        Taboo<String> taboo = new Taboo<String>(list);

        List<String> newList = new ArrayList<String>(Arrays.asList("e", "f", "f", "e"));
        taboo.reduce(newList);

        List<String> compareList = new ArrayList<String>(Arrays.asList("e", "e"));
        assertTrue(compareList.equals(newList));
    }


}
