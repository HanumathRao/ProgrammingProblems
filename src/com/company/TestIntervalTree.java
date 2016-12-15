package com.company;

import com.sun.tools.javac.util.Pair;

import java.util.List;

/**
 * Created by hmaduri on 6/24/16.
 */
public class TestIntervalTree {
    public static void main(String[] args) {

        IntervalTree<String> intervalTree = new IntervalTree<>();
        intervalTree.addRange("AaA", "BbB");
        intervalTree.addRange("CcC", "EeE");
        List<Pair<String,String>> intervals = intervalTree.query("AaaaA","DdD");
        intervalTree.deleteRange("AaaaA","DdD");
        for (Pair<String,String> intrvl : intervals) {
            System.out.println(intrvl.fst + ":" + intrvl.snd);
        }
        intervals = intervalTree.query("AaaA");
        for (Pair<String,String> intrvl : intervals) {
            System.out.println(intrvl.fst + ":" + intrvl.snd);
        }
        if (intervals.size() == 0)
            System.out.println("no interval");
    }
}
