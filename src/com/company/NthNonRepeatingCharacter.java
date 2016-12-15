package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hmaduri on 6/5/16.
 */
public class NthNonRepeatingCharacter {

    public static void main(String[] args) {

        String s = "geeksforgeeks";
        int k = 3;

        char ch = getNthNonRepeatingCharacter(s,k);
        System.out.println("k th character is :" + ch);
    }

    private static char getNthNonRepeatingCharacter(String s, int k) {
        Map<Character,Character> map = new HashMap<>();
        List<Character> charlist = new ArrayList<>();
        Character NULL = new Character(':');
        for (Character ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                if (map.get(ch) != NULL) {
                    charlist.remove(map.get(ch));
                    map.put(ch, NULL);
                }
            } else {
                map.put(ch, ch);
                charlist.add(ch);
            }
        }
        return charlist.get(k-1);
    }
}
