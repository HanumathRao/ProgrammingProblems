package com.company;

import com.sun.tools.javac.util.Pair;

/**
 * Created by hmaduri on 6/25/16.
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();

        binaryTree.put(100);
        binaryTree.getNode().addrightChild(new BinaryTree<Integer>().put(120));
        binaryTree.getNode().addLeftChild(new BinaryTree<Integer>().put(150));
        binaryTree.getNode().getLeftChild().addLeftChild(new BinaryTree<Integer>().put(130));
//        binaryTree.getNode().getLeftChild().getLeftChild().addLeftChild(new BinaryTree<Integer>().put(111));

        boolean isBalanced = isBalancedBinary(binaryTree).snd;
        System.out.println("is balanced binary tree:"+isBalanced);
    }

    public static class BinaryTree<K extends Comparable<K>> {
        private K key;
        private BinaryTree<K> leftChild;
        private BinaryTree<K> rightChild;

        public BinaryTree() {

        }

        public BinaryTree<K> addLeftChild(BinaryTree<K> node) {
            this.leftChild = node;
            return node;
        }

        public BinaryTree<K> addrightChild(BinaryTree<K> node) {
            this.rightChild = node;
            return node;
        }

        public BinaryTree<K> getLeftChild() {
            return this.leftChild;
        }

        public BinaryTree<K> getRightChild() {
            return this.rightChild;
        }

        public BinaryTree<K> put(K key) {
            this.key = key;
            return this;
        }

        public BinaryTree<K> getNode() {
            return this;
        }
    }

    public static <K extends Comparable<K>> Pair<Integer,Boolean> isBalancedBinary(BinaryTree<K> root) {
        if (root == null)
            return Pair.of(0,true);
        Pair<Integer, Boolean> leftHeight = isBalancedBinary(root.leftChild);
        if (leftHeight.snd == false)
            return Pair.of(-1, false);
        Pair<Integer, Boolean> rightHeight = isBalancedBinary(root.rightChild);
        if (rightHeight.snd == false)
            return Pair.of(-1, false);
        if (Math.abs(leftHeight.fst - rightHeight.fst) > 1)
            return Pair.of(-1,false);
        return Pair.of(leftHeight.fst + 1 + rightHeight.fst,true);
    }


}
