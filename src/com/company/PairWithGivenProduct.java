package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hmaduri on 6/4/16.
 */
public class PairWithGivenProduct {

    public static void main(String[] args ) {
        int arr[] = {10, 20, 9, 40};
        int prod = 200;

        boolean pairOfNums = areThereAnyPairsWithGivenProduct(arr,prod);
        System.out.println("Product found in given array:" + pairOfNums);

        boolean pairOfNums1 = fastFindAnyPairsWithGivenProduct(arr,prod);
        System.out.println("Product with fast version found in given array:" + pairOfNums1);
    }

    public static boolean areThereAnyPairsWithGivenProduct(int[] arr, int prod) {
        for(int index = 0; index< arr.length;index++)
            for(int indexj =index+1 ; indexj < arr.length; indexj++) {
                int prodvalue = arr[index]*arr[indexj];
                if (prodvalue == prod) return true;
            }
        return false;
    }

    public static boolean fastFindAnyPairsWithGivenProduct(int[] arr, int prod) {
        Set<Integer> hashtable = new HashSet<>();
        for(int index=0;index<arr.length;index++)
            if (prod == 0 && arr[index] == 0)
                return true;
            else if (prod%arr[index] == 0 && hashtable.contains(prod/arr[index]))
                return true;
            else hashtable.add(arr[index]);
        return false;
    }
}
