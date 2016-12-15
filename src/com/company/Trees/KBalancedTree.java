package com.company.Trees;

import com.sun.tools.javac.util.Pair;

/**
 * Created by hmaduri on 7/2/16.
 */
public class KBalancedTree {
    public static void main(String[ ] args) {

        Tree tree = new Tree();

        tree.setKey(10);
        tree.setlChild(new Tree());
        tree.setrChild(new Tree());
        tree.getlChild().setKey(11);
        tree.getlChild().setlChild(new Tree());
        tree.getlChild().getlChild().setKey(12);
        tree.getlChild().getlChild().setlChild(new Tree());
        tree.getlChild().getlChild().getlChild().setKey(13);

        tree.getrChild().setKey(15);
        int k = 2;
        System.out.println("is this tree "+k+" balanced:"+ isKBalanced(tree, k));

    }

    public static boolean isKBalanced(Tree tree, int k) {
        if (isKBalancedTreeInternal(tree, k).fst)
            return true;
        else return false;
    }


    public static Pair<Boolean,Integer> isKBalancedTreeInternal(Tree tree, int k) {

        if (tree == null)
            return Pair.of(true,0);

        Pair<Boolean,Integer> leftResult = isKBalancedTreeInternal(tree.left, k);

        if (leftResult.fst == false)
            return Pair.of(false,-1);

        Pair<Boolean,Integer> rightResult = isKBalancedTreeInternal(tree.right, k);

        if (rightResult.fst == false)
            return Pair.of(false,-1);

        if (Math.abs(leftResult.snd - rightResult.snd) > k)
            return Pair.of(false, -1);

        return Pair.of(true, Math.max(leftResult.snd , rightResult.snd) + 1);
    }


    private static class Tree {
        private int data;
        private Tree left;
        private Tree right;

        Tree() {
        }

        public void setKey(int data) {
            this.data = data;
        }

        public void setlChild(Tree tree) {
            this.left = tree;
        }

        public void setrChild(Tree tree){
            this.right = tree;
        }

        public Tree getrChild(){
            return this.right;
        }

        public Tree getlChild() {
            return this.left;
        }
    }
}
