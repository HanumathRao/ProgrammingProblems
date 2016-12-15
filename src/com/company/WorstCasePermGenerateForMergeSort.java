package com.company;

import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hmaduri on 6/5/16.
 */
public class WorstCasePermGenerateForMergeSort {
    public static void main(String[] args) {
        int k = 15;
        List<Integer> intlist = new ArrayList<>();
        for (int index=1;index<=k;index++)
            intlist.add(index);

        List<Integer> b = generateWorstCasePermutationForMergeSort(intlist,0,intlist.size()-1);
        System.out.println("permuted array for worst case performance of merge sort:" + b);
    }


    private static List<Integer> generateWorstCasePermutationForMergeSort(List<Integer> a, int l, int r) {
        int m;

        if(l>=r || a.size() == 0)
            return new ArrayList<>(a);
        else {
            m = l+(r-l)/2;
            Pair<List<Integer>, List<Integer>> split = alterSplit(a,l,r,m);
            List<Integer> leftList = generateWorstCasePermutationForMergeSort(split.fst,l,m);
            List<Integer> rightList = generateWorstCasePermutationForMergeSort(split.snd,m+1,r);

            return join(leftList,rightList);
        }
    }

    private static Pair<List<Integer>, List<Integer>> alterSplit(List<Integer > a, int l, int r, int m) {
        if (a.size()==0)
            return Pair.of(new ArrayList<>(), new ArrayList<>());
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
//      13; 6; 0,2,4,6,8,10,12
        //14; 7; 0,2,4,6,8,10,12,14
        for(int index=0;index<=(a.size()-1)/2;index++)
            left.add(a.get(2*index));
        //13; 6; 1,3,5,7,9,11,13
        //14; 7; 1,3,5,7,9,11,13
        int length = a.size()%2 == 0 ? (a.size()-1)/2 : (a.size()-1)/2-1;
        for(int index=0;index<=length;index++)
            right.add(a.get(2*index + 1));

        return Pair.of(left,right);
    }

    private static List<Integer> join(List<Integer> left, List<Integer> right) {
        List<Integer> arrlist = new ArrayList<>();

        for (int index=0;index<=left.size()-1;index++)
            arrlist.add(left.get(index));

        for(int index=0;index<=right.size()-1;index++)
            arrlist.add(right.get(index));
        return arrlist;
    }
}
