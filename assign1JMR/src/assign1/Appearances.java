package assign1;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Appearances {

	public static void main(String[] args) {
		List<String> a = convertStringToList("abbccc");
		List<String> b = convertStringToList("cccbba");	
		System.out.println(sameCount(a,b));
		
		List<Integer> x = Arrays.asList(1, 2, 3, 1, 2, 3, 5);
		List<Integer> y = Arrays.asList(1, 1, 1, 2, 2, 3, 5);
		System.out.println(sameCount(x,y));
		
		List<Double> i = Arrays.asList(1.0, 1.2, 1.6, 1.6);
		List<Double> j = Arrays.asList(1.1, 1.2, 1.6, 1.6);
		System.out.println(sameCount(i,j));

	}
	
	private static List<String> convertStringToList(String s) {
		List<String> list = new ArrayList<String>();
		for (int i=0; i<s.length(); i++) {
			list.add(String.valueOf(s.charAt(i)));
		}
		return list;
	}
	
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
        HashMap<T, Integer> Hash1 = new HashMap<T, Integer>();
        HashMap<T, Integer> Hash2 = new HashMap<T, Integer>();

        countNumberOfAppearances(a, Hash1);
        countNumberOfAppearances(b, Hash2);

        int result = 0;

        for (T value : Hash1.keySet())
            if (Hash2.containsKey(value)
                    && Hash1.get(value).equals(Hash2.get(value)))
                result++;


        return result;
    }

    private static <T> void countNumberOfAppearances(Collection<T> c, HashMap<T, Integer> Hash1) {
        for (T nextVal : c) {
            if (Hash1.containsKey(nextVal))
                Hash1.put(nextVal, 1 + Hash1.get(nextVal));
            else
                Hash1.put(nextVal, 1);
        }
    }

}
