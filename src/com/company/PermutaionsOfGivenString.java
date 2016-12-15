package com.company;

/**
 * Created by hmaduri on 5/30/16.
 */
public class PermutaionsOfGivenString {
    static int totalSize=0;
    public static void main(String[] args) {
        char[] string = {'a','b','c','d','e'};
        permute(string, 0);
        System.out.println("totalSize:" + totalSize);
    }

    private static void permute(char[] string, int index) {

        if (index == string.length - 1) {
            System.out.println(string);
            totalSize += 1;
        }
        for(int i=index;i<string.length;i++) {
            char tmp = string[i];
            string[i] = string[index];
            string[index] = tmp;
            permute(string, index + 1);
            tmp = string[i];
            string[i] = string[index];
            string[index] = tmp;
        }
    }
}
