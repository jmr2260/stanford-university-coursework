package assign1;
import java.util.HashSet;

public class StringCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("a3tx2z ==> " + blowup("a3tx2z"));
		System.out.println("12x ==> " + blowup("12x"));
		System.out.println("p31k ==> " + blowup("p31k"));
		System.out.println("p42z ==> " + blowup("p42z"));
		System.out.println("z2f43 ==> " + blowup("z2f43"));
		
		System.out.println("xxyyyz ==> " + maxRun("xxyyyz"));
		System.out.println("xxxppppqzmmmm ==> " + maxRun("xxxppppqzmmmm"));
		System.out.println("xxxppppqzmmmm ==> " + maxRun("xxxppppqzmmmm"));

	}
	/**
     * Given a string, for each digit in the original string,
     * replaces the digit with that many occurrences of the character
     * following. So the string "a3tx2z" yields "attttxzzz".
     *
     * @param str to replace digits
     * @return blown up string
     */
    public static String blowup(String str) {
        String result = "";

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)))
                result += createNewCharacters(str, i);
            else result += str.charAt(i);
        }
        return result;
    }

    private static String createNewCharacters(String str, int pos) {
        String result = "";
        if (pos < str.length() - 1) {
            char nextChar = str.charAt(pos + 1);

            int numToReplace = Character.getNumericValue(str.charAt(pos));
            for (int i = 0; i < numToReplace; i++)
                result += nextChar;
        }
        return result;
    }
    
    public static int maxRun(String str) {
        int maxRun = 0;
        int currRun;

        for (int i = 0; i < str.length(); i += currRun) {
            currRun = calculateMode(str, i);
            if (currRun > maxRun) maxRun = currRun;
        }

        return maxRun;
    }

    private static int calculateMode(String str, int pos) { // This is a non-recursive function.
    	int count = 1;
    	int index = 1;
    	boolean done = false;
    	do {
    		if (pos < str.length() - 1) {
    			if ((pos + index) < str.length()) {	
	    			if (str.charAt(pos) == str.charAt(pos+index)) {
	    				count += 1;
	    				index += 1;
	    				//System.out.println(str + ", character = " + str.charAt(pos) + ", count = " + count + ", index = " + index);
	    			} else {
	    				done = true;
	    				break;
	    			}
    			} else { 
    				done = true;
    			}
    		} else {
    			done = true;
    		}
    	} while (done == false);   			
    	return count;
    } 

}
