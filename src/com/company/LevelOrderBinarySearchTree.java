package com.company;

import com.company.BinarySearchTreeWithMinimalHeight.BinarySearchTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hmaduri on 6/25/16.
 */
public class LevelOrderBinarySearchTree {
    public static void main(String[] args) {
        Map<Integer,List<Integer>> levelorderlists = new HashMap<>();
        Integer elements[] = {1,2,3,4,5,6,7,8,9,10,11};

        BinarySearchTree<Integer> tree =
            BinarySearchTreeWithMinimalHeight.buildBinarySearchTree(elements,0,elements.length-1);

        printLevelOrder(tree, levelorderlists, 0);

        for(Map.Entry<Integer,List<Integer>> e : levelorderlists.entrySet()) {
            System.out.println("level:" + e.getKey());
            for (Integer i : e.getValue())
                System.out.print(i+":");
            System.out.println();
        }
    }

    public static void printLevelOrder(BinarySearchTree<Integer> tree, Map<Integer, List<Integer>> lists, int level) {
        if(tree == null)
            return;
        if (lists.get(level) == null)
            lists.put(level,new ArrayList<>());
        lists.get(level).add(tree.getKey());
        printLevelOrder(tree.getLeftChild(),lists,level+1);
        printLevelOrder(tree.getRightChild(),lists,level+1);
    }

}
