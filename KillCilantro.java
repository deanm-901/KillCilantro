import java.util.ArrayList;
import java.util.List;

public class KillCilantro {
    public static List<Integer> getIndexesOfKey(String str, String key) {
        List<Integer> indexes = new ArrayList<Integer>();

        // testcilantrotest
        //     cilantro

        final int str_amm = str.length();
        final char[] str_arr = str.toCharArray();

        final int key_amm = key.length();
        final char[] key_arr = key.toCharArray();

        int current_key_idx = 0;

        for (int i=0;i<str_amm;i++) {
            if (str_arr[i] != key_arr[current_key_idx]) {
                current_key_idx = 0;
                continue;
            }

            current_key_idx++;

            if (current_key_idx >= key_amm) {
                indexes.add(i-(key_amm-1));
                current_key_idx = 0;
            }
        }

        return indexes;
    }

    public static String replaceKeyInString(String str, String keyin, String keyout) {
        String l_keyout = keyout.toLowerCase();

        String cat0 = "";
        
        final int str_amm = str.length();
        final int keyin_amm = keyin.length();
        final int keyout_amm = keyout.length();
        final List<Integer> keyout_idx = getIndexesOfKey(str, keyout); 

        int last_i = 0;
        for (int i : keyout_idx) {
            cat0 += str.substring(last_i, i)+keyin;

            last_i = i+keyout_amm;
        }
        cat0 += str.substring(last_i, str_amm);

        return cat0;
    }

    public static String killCilantro(String str) {
        return replaceKeyInString(str, "parsley", "cilantro");
    }
    
    public static void main(String[] args) {
        System.out.println(killCilantro("testcilantrotest"));
        System.out.println(killCilantro("cilantrotest"));
        System.out.println(killCilantro("nothinghere"));
        System.out.println(killCilantro("testcilantro"));
        System.out.println(killCilantro("cilantrocilantrocilantro"));
    }
}