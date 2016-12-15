package com.company;

import com.company.BinarySearchTreeWithMinimalHeight.BinarySearchTree;
import com.sun.tools.javac.util.Pair;

/**
 * Created by hmaduri on 6/25/16.
 */
public class BinarySearchTreeOrNot {

    public static void main(String[] args) {
        Integer elements[] = {1,2,3,4,5,6,7,8,9,10,11};
        BinarySearchTree<Integer> tree =
            BinarySearchTreeWithMinimalHeight.buildBinarySearchTree(elements,0,elements.length-1);

        boolean isBST = isBinarySearchTree(tree).snd;
        System.out.println("isBST:"+isBST);
    }

    public static Pair<Pair<Integer,Integer>,Boolean> isBinarySearchTree(BinarySearchTree<Integer> tree){
        Pair<Pair<Integer,Integer>,Boolean> lresult = Pair.of(Pair.of(tree.getKey(),tree.getKey()),true);
        Pair<Pair<Integer,Integer>,Boolean> rresult = Pair.of(Pair.of(tree.getKey(),tree.getKey()),true);

        if (tree.getLeftChild() != null) {
            lresult = isBinarySearchTree(tree.getLeftChild());
            if (lresult.snd == false) return lresult;
        }

        if (tree.getRightChild() != null) {
            rresult = isBinarySearchTree(tree.getRightChild());
            if (rresult.snd == false) return rresult;
        }

        if (tree.getKey() <= rresult.fst.fst &&
            tree.getKey() >= lresult.fst.snd)
                return Pair.of(Pair.of(lresult.fst.fst,rresult.fst.snd),true);
        else
                return Pair.of(Pair.of(lresult.fst.fst,rresult.fst.snd),false);
    }
}
