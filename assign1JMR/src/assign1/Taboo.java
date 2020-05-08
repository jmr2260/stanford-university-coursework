package assign1;
import java.util.*;

public class Taboo<T> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = Arrays.asList("a", "c", "a", "b"); // These are the rules
        Taboo<String> taboo = new Taboo<String>(list);

        List<String> newList01 = new ArrayList<String>(Arrays.asList("a", "c", "b", "x", "c", "a")); // This is the string to be reduced according to the rules
        taboo.reduce(newList01);
        //List<String> resultList = new ArrayList<String>(Arrays.asList("a", "x", "c"));
        System.out.println(taboo.noFollow("a"));
        System.out.println(newList01);	
        
        List<String> newList02 = new ArrayList<String>(Arrays.asList("c", "a", "c", "b", "c", "x", "c", "a", "b")); // This is the string to be reduced according to the rules
        taboo.reduce(newList02);
        System.out.println(newList02);
    }
	
	HashMap<T, HashSet<T>> database;

    /**
     * Constructs a new Taboo using the given rules (see handout.)
     *
     * @param rules rules for new Taboo
     */

    public Taboo(List<T> rules) {
        database = new HashMap<T, HashSet<T>>();
        T key = null;
        for (T val : rules) {
            if (key != null && val != null) {

                if (!database.containsKey(key))
                    database.put(key, new HashSet<T>());

                database.get(key).add(val);
            }

            key = val;

        }
    }

    /**
     * Returns the set of elements which should not follow
     * the given element.
     *
     * @param elem given elem
     * @return elements which should not follow the given element
     */
    public Set<T> noFollow(T elem) {
        if (database.containsKey(elem))
            return database.get(elem);
        else
            return Collections.emptySet();
    }

    /**
     * Removes elements from the given list that
     * violate the rules (see handout).
     *
     * @param list collection to reduce
     */
    @SuppressWarnings("unchecked")
    public void reduce(List<T> list) {

        T currVal = null;
        Iterator itr =list.iterator();

        while(itr.hasNext()){
            T nextVal = (T)itr.next();

            if (currVal != null && nextVal != null
                    && database.containsKey(currVal)
                    && database.get(currVal).contains(nextVal))
                itr.remove();
            else
                currVal = nextVal;
        }
    }

}
