package com.company;

import com.sun.tools.javac.util.Pair;


/**
 * Created by hmaduri on 6/4/16.
 */
public class SmallestRangeFromGivenLists {
    public static void main(String[] args) {
        int[] arr1 = {4, 7, 30};
        int[] arr2 = {1, 2};
        int[] arr3 = {20, 40};

        Pair<Integer,Integer> range = getSmallestRange(arr1,arr2,arr3);
        System.out.println("smallest range:" + range.fst +"," + range.snd);
    }

    public static Pair<Integer, Integer> getSmallestRange(int[] arr1, int[] arr2, int[] arr3) {
        int i=0,j=0,k=0;
        Pair<Integer,Integer> smallestRange = Pair.of(Integer.MAX_VALUE,0);
        do {
            if (i>=arr1.length || j>=arr2.length || k>= arr3.length)
                break;

            Pair<Integer,Integer> range = Pair.of(Math.max(Math.max(arr1[i], arr2[j]),arr3[k]),
                                                  Math.min(Math.min(arr1[i], arr2[j]), arr3[k]));
            if (smallestRange == null ||
                (smallestRange.fst - smallestRange.snd) > (range.fst- range.snd))
                smallestRange = range;
            int whoToIncrement = arr1[i] < arr2[j] ? arr1[i] < arr3[k] ? i++ : k++ : arr2[j] < arr3[k] ? j++ : k++;
        } while(true);
        return smallestRange;
    }
}
