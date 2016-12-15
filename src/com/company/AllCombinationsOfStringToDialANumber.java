package com.company;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * Created by hmaduri on 6/4/16.
 */
public class AllCombinationsOfStringToDialANumber {
    public static void main(String[] args) {
        Map<Character,List<Character>> table = new HashMap<>();
        Init(table);
        Character[] arr = {'2','3','4'};
        printListOfAllStrings(table,arr, 0, "");

    }

    public static void printListOfAllStrings(Map<Character, List<Character>> map, Character[] arr, int num, String str) {
        if (num == arr.length) {
            System.out.println(str);
            return;
        }
        for (Character ch: map.get(arr[num]))
            printListOfAllStrings(map,arr,num+1, str + "," + ch);
    }


    public static void Init(Map<Character,List<Character>> map) {
        map.put('1',add(createList(),'1'));
        map.put('2',add(add(add(createList(),'A'),'B'),'C'));
        map.put('3',add(add(add(createList(),'D'),'E'),'F'));
        map.put('4',add(add(add(createList(),'G'),'H'),'I'));
        map.put('5',add(add(add(createList(),'J'),'K'),'L'));
        map.put('6',add(add(add(createList(),'M'),'N'),'O'));
        map.put('7',add(add(add(add(createList(),'P'),'Q'),'R'),'S'));
        map.put('8',add(add(add(createList(),'T'),'U'),'V'));
        map.put('9',add(add(add(add(createList(),'W'),'X'),'Y'),'Z'));
        map.put('0',add(createList(),'0'));
    }

    private static List<Character> createList() {
        return new ArrayList<Character>();
    }

    private static List<Character> add(List<Character> arrList,Character ch) {
        if (arrList==null)
            return new ArrayList<Character>();
        else {
            arrList.add(ch);
            return arrList;
        }

    }
}
