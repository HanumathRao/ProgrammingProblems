package com.company;


import java.util.*;
/**
 * Created by hmaduri on 5/31/16.
 */
public class numberSetCombinations {
    public static void main(String[] args) {
        int number = 5;
        List<Integer> intList = new ArrayList<>();

        printNumSetCombinations(number, intList);
    }

    private static void printNumSetCombinations(int num, List<Integer> integerList) {
        if (num<=0)
            System.out.println(integerList.toString());
        for(int index=1;index<=num;index++) {
            if (integerList.size() == 0 ||
                    integerList.get(integerList.size()-1) <= index) {
                integerList.add(integerList.size(),index);
                printNumSetCombinations(num - index, integerList);
                integerList.remove(integerList.size()-1);
            }
        }
    }
}
