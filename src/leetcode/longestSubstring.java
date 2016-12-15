package leetcode;

/**
 * Created by hmaduri on 8/9/16.
 */
import java.util.Map;
import java.util.HashMap;

public class longestSubstring {
    public static void main(String[] args) {
//        String str = "abcabcabc";
        String str = "ababbbabcdef";
        System.out.println("longest substring is :" + longestsubstringwithoutrepeatingcharacters(str));
    }

    static int longestsubstringwithoutrepeatingcharacters(String str) {
        int maxlength=1, length=0, index=0;
        Map<Character, Integer> charmap = new HashMap<Character, Integer>();

        for(char a : str.toCharArray()) {
            if (charmap.get(a) == null) {
                length++;
                charmap.put(a, index);
            }
            else {
                int prevpos = charmap.get(a);
                length = index - prevpos;
                charmap.put(a,index);
            }

            index++;

            if (length > maxlength)
                maxlength = length;
        }
        return maxlength;
    }
}
