package asterdata;

import com.sun.tools.javac.util.Pair;

import java.util.List;


public class TestRangeTree {
    public static void main(String[] args) {

        testCase1();
        testCase2();
        testCase3();
    }

    public static void testCase3() {
        System.out.println("testcase3");
        RangeTree<String> intervalTree = new RangeTree<>();
        intervalTree.addRange("A", true, "F", true);
        intervalTree.addRange("H", true, "M", true);
        List<RangePair<String>> intervals = intervalTree.query("A",false, "Z", false);
        System.out.println("before results");
        for (RangePair<String> intrvl : intervals) {
            System.out.println((intrvl.isFirstInclusive()? "[" : "(") + intrvl.first() + ":" + intrvl.second() + (intrvl.isSecondInclusive()?"]":")"));
        }

        intervalTree.deleteRange("A",true,"Z",true);
        intervals = intervalTree.query("A",false, "Z", false);
        System.out.println("after results");
        for (RangePair<String> intrvl : intervals) {
            System.out.println((intrvl.isFirstInclusive()? "[" : "(" )+ intrvl.first() + ":" + intrvl.second() + (intrvl.isSecondInclusive()?"]":")"));
        }
    }

    public static void testCase2() {
        System.out.println("testcase2");
        RangeTree<String> intervalTree = new RangeTree<>();
        intervalTree.addRange("AaA", true, "BbB", true);
        intervalTree.addRange("CcC", true, "EeE", true);
        List<RangePair<String>> intervals = intervalTree.query("AaaaA",false, "DdD", false);
        System.out.println("before results");
        for (RangePair<String> intrvl : intervals) {
            System.out.println((intrvl.isFirstInclusive()? "[" : "(") + intrvl.first() + ":" + intrvl.second() + (intrvl.isSecondInclusive()?"]":")"));
        }

        intervalTree.deleteRange("BbB",true,"CcC",true);
        intervals = intervalTree.query("AaaaA",false, "DdD", false);
        System.out.println("after results");
        for (RangePair<String> intrvl : intervals) {
            System.out.println((intrvl.isFirstInclusive()? "[" : "(" )+ intrvl.first() + ":" + intrvl.second() + (intrvl.isSecondInclusive()?"]":")"));
        }

    }


    public static void testCase1(){
        System.out.println("testcase1");
        RangeTree<String> intervalTree = new RangeTree<>();
        intervalTree.addRange("AaA", false, "BbB", false);
        intervalTree.addRange("CcC", false, "EeE", false);
        List<RangePair<String>> intervals = intervalTree.query("AaaaA",false, "DdD", false);
        intervalTree.deleteRange("AaaaA",true, "DdD", false);
        for (RangePair<String> intrvl : intervals) {
            System.out.println((intrvl.isFirstInclusive()? "[" : "(" )+ intrvl.first() + ":" + intrvl.second() + (intrvl.isSecondInclusive()?"]":")"));
        }
        intervals = intervalTree.query("A", false, "Z", false);
        for (RangePair<String> intrvl : intervals) {
            System.out.println((intrvl.isFirstInclusive()? "[" :"(") + intrvl.first() + ":" + intrvl.second() + (intrvl.isSecondInclusive()?"]":")"));
        }
        if (intervals.size() == 0)
            System.out.println("no interval");
        System.out.println("****************");
    }
}
