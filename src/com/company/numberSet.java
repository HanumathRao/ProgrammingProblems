package com.company;

/**
 * Created by hmaduri on 5/31/16.
 */
public class numberSet {
    public static void main(String[] args) {
        int number = 5;

        printNumberSets(number, "");
    }

    private static void printNumberSets(int number,String str) {
        if (number<=0)
            System.out.println(str);
        for (int index=1;index<=number;index++)
            printNumberSets(number-index,str+","+index);
    }
}
