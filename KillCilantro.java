import java.util.ArrayList;
import java.util.List;

class KeyPair {
    public String keyin;
    public String keyout;
    
    public KeyPair(String keyin, String keyout) {
        this.keyin = keyin;
        this.keyout = keyout;
    }
}

public class KillCilantro {
    public static List<Integer> getIndexesOfKey(String str, String key) {
        List<Integer> indexes = new ArrayList<Integer>();

        // define variables for length and char array for easier use
        final int str_amm = str.length();
        final char[] str_arr = str.toCharArray();

        final int key_amm = key.length();
        final char[] key_arr = key.toCharArray();

        // an index defining the highest eqivalent character between the string and key
        int current_key_idx = 0;

        // loops through string once
        for (int i=0;i<str_amm;i++) {
            // if the characters are not equal, set idx to zero and skip
            if (str_arr[i] != key_arr[current_key_idx]) {
                current_key_idx = 0;
                continue;
            }

            current_key_idx++;

            // if the idx is greater than or equal to the length of key, which means
            // that the key must be present inside of the string
            if (current_key_idx >= key_amm) {
                indexes.add(i-(key_amm-1));
                current_key_idx = 0;
            }
        }

        return indexes;
    }

    public static String replaceKeyInString(String str, KeyPair pair) {
        // return string
        String cat0 = "";
        
        // defining constants
        final int str_amm = str.length();
        final int keyin_amm = pair.keyin.length();
        final int keyout_amm = pair.keyout.length();
        
        // call to get idx
        final List<Integer> keyout_idx = getIndexesOfKey(str, pair.keyout); 

        int last_i = 0;
        for (int i : keyout_idx) {
            cat0 += str.substring(last_i, i)+pair.keyin;

            last_i = i+keyout_amm;
        }
        cat0 += str.substring(last_i, str_amm);

        return cat0;
    }

    public static String replaceKeysInString(String str, List<KeyPair> pairs) {
        String finalString = str;
        for (KeyPair pair : pairs) {
            finalString = replaceKeyInString(finalString, pair);
        }

        return finalString;
    }

    public static String killCilantro(String str) {
        return replaceKeyInString(str, new KeyPair("parsley", "cilantro"));
    }
    
    public static void main(String[] args) {
        List<KeyPair> pairs = new ArrayList<KeyPair>();
        pairs.add(new KeyPair("parsley", "cilantro"));
        pairs.add(new KeyPair("bye", "hi"));
        pairs.add(new KeyPair("foo", "bar"));
        pairs.add(new KeyPair("hello", "world"));
    
        System.out.println(killCilantro("testcilantrotest"));
        System.out.println(killCilantro("cilantrotest"));
        System.out.println(killCilantro("nothinghere"));
        System.out.println(killCilantro("testcilantro"));
        System.out.println(killCilantro("cilantrocilantrocilantro"));

        System.out.println(replaceKeysInString("oh world, i like cilantro. the bars that use it are good. hi!", pairs));
    }
}