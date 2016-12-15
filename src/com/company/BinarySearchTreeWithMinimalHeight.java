package com.company;

/**
 * Created by hmaduri on 6/25/16.
 */
public class BinarySearchTreeWithMinimalHeight {
    public static void main(String[] args) {
//        Integer integers[] = {1,2,3,4,5,6,7,8,9,10};
        Integer integers[] = {1,2,3,4,5,6,7,8,9,10,11,12};
        BinarySearchTree<Integer> tree = buildBinarySearchTree(integers,0,integers.length-1);
        print("",tree);
    }

    public static <K extends Comparable<K>> void print(String indent, BinarySearchTree<K> tree) {
        if(tree== null)
            return;
        System.out.println(indent + tree.key.toString());
        print("****"+indent,tree.getLeftChild());
        print("****"+indent,tree.getRightChild());
    }

    public static class BinarySearchTree<K extends Comparable<K>> {
        private K key;
        private BinarySearchTree<K> leftChild;
        private BinarySearchTree<K> rightChild;

        public BinarySearchTree() {

        }

        public K getKey(){
            return this.key;
        }

        public BinarySearchTree<K> getLeftChild() {
            return this.leftChild;
        }

        public BinarySearchTree<K> getRightChild() {
            return this.rightChild;
        }

        public void addKey(K key) {
            this.key =key;
        }
    }

    public static <K extends Comparable<K>> BinarySearchTree<K> buildBinarySearchTree(K[] elements, int start, int end) {
        if (start>end)
            return null;
        if (start == end) {
            BinarySearchTree<K> root = new BinarySearchTree<K>();
            root.key = elements[start];
            root.leftChild = null;
            root.rightChild = null;
            return root;
        }
        int mid = start + (end - start)/2;
        BinarySearchTree<K> leftChild = buildBinarySearchTree(elements, start, mid-1);
        BinarySearchTree<K> rightChild = buildBinarySearchTree(elements, mid+1, end);
        BinarySearchTree<K> root = new BinarySearchTree<>();
        root.leftChild = leftChild;
        root.rightChild = rightChild;
        root.key = elements[mid];
        return root;
    }
}
