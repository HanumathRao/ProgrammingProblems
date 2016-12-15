package com.company;


import com.company.BinarySearchTreeWithMinimalHeight.BinarySearchTree;
import com.sun.tools.javac.util.Pair;

import java.util.Comparator;

/**
 * Created by hmaduri on 6/25/16.
 */
public class Ancestor {
    public static void main(String[] args) {
        Integer elements[] = {1,2,3,4,5,6,7,8,9,10,11,12};

        BinarySearchTree<Integer> tree =
            BinarySearchTreeWithMinimalHeight.buildBinarySearchTree(elements,0,elements.length-1);

        BinarySearchTree<Integer> node1 = search(tree, 9);
        BinarySearchTree<Integer> node2 = search(tree, 3);

        BinarySearchTree<Integer> commonAncestor = getCommonAncestor(tree,node1,node2).snd;
        System.out.println(commonAncestor.getKey());
        print("",tree);
    }


    public static <K extends Comparable<K>> void print(String indent, BinarySearchTree<K> tree) {
        if(tree== null)
            return;
        System.out.println(indent + tree.getKey().toString());
        print("****"+indent,tree.getLeftChild());
        print("****"+indent,tree.getRightChild());
    }

    public static <K extends Comparable<K>> Pair<Pair<Boolean,Boolean>,BinarySearchTree<K>>
        getCommonAncestor(BinarySearchTree<K> tree , BinarySearchTree<K> node1, BinarySearchTree<K> node2) {
        Boolean node1Found=false;
        Boolean node2Found=false;
        if (tree == null)
            return Pair.of(Pair.of(false,false),null);
        if (tree == node1)
            node1Found = true;
        else if (tree == node2)
            node2Found = true;

        Pair<Pair<Boolean,Boolean>,BinarySearchTree<K>> lresult = getCommonAncestor(tree.getLeftChild(),node1,node2);
        Pair<Pair<Boolean,Boolean>,BinarySearchTree<K>> rresult = getCommonAncestor(tree.getRightChild(),node1,node2);

        if (lresult.snd !=null)
            return lresult;
        if (rresult.snd != null)
            return rresult;

        if (((lresult.fst.fst || lresult.fst.snd) && !(lresult.fst.fst && lresult.fst.snd)) &&
            ((rresult.fst.fst || rresult.fst.snd) && !(rresult.fst.fst && rresult.fst.snd)))
            return Pair.of(Pair.of(true,true),tree);

        if (node1Found && (lresult.fst.snd || rresult.fst.snd) ||
            node2Found && (lresult.fst.fst || rresult.fst.fst))
            return Pair.of(Pair.of(true,true),tree);

        return Pair.of(Pair.of(node1Found,node2Found),null);
    }

    public static <K extends Comparable<K>> BinarySearchTree<K> search(BinarySearchTree<K> tree, K element) {
        if (tree == null)
            return null;
        if (tree.getKey().compareTo(element) == 0)
            return tree;
        else if (tree.getKey().compareTo(element) > 0)
            return search(tree.getLeftChild(),element);
        else
            return search(tree.getRightChild(),element);
    }
}
