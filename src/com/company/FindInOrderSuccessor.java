package com.company;


/**
 * Created by hmaduri on 6/25/16.
 */
public class FindInOrderSuccessor {
    public static void main(String[] args) {
        Integer elements[] = {1,2,3,4,5,6,7,8,9,10,11};

        BinarySearchTree<Integer> tree = buildBST(elements,0,elements.length -1);
        BinarySearchTree<Integer> node = search(tree,6);

        printInOrderSuccessor(node);
    }

    public static <K extends Comparable<K>> void printInOrderSuccessor(BinarySearchTree<K> node) {
        BinarySearchTree<K> min = null;
        if (node.rchild != null)
            min = getMinimum(node.rchild);
        if(min == null) {
            min = node.parent;
            BinarySearchTree<K> temp = node;
            while(temp == min.rchild){
                min = min.parent;
                temp = temp.parent;
            }
        }

        if (min == null)
            System.out.println("no inorder successor");
        else
            System.out.println(min.key);
    }

    public static <K extends Comparable<K>> BinarySearchTree<K> getMinimum(BinarySearchTree<K> node) {
        if (node.lchild == null)
            return node;
        return getMinimum(node.lchild);
    }

    public static class BinarySearchTree<K extends Comparable<K>> {
        private K key;
        private BinarySearchTree<K> lchild;
        private BinarySearchTree<K> rchild;
        private BinarySearchTree<K> parent;

        public BinarySearchTree() {

        }
    }

    public static <K extends Comparable<K>> BinarySearchTree<K> search(BinarySearchTree<K> tree, K element) {
        if (tree == null)
            return null;
        if (tree.key == element)
            return tree;
        else if (tree.key.compareTo(element) > 0)
            return search(tree.lchild,element);
        else
            return search(tree.rchild,element);
    }

    public static <K extends Comparable<K>> BinarySearchTree<K> buildBST(K[] elements, int start, int end) {
        if (start>end)
            return null;
        if (start == end) {
            BinarySearchTree<K> root = new BinarySearchTree<>();
            root.key = elements[start];
            root.lchild = null;
            root.rchild = null;
            root.parent = null;
            return root;
        }
        int mid = start + (end - start)/2;
        BinarySearchTree<K> leftChild = buildBST(elements,start,mid-1);
        BinarySearchTree<K> rightChild = buildBST(elements,mid+1,end);
        BinarySearchTree<K> root = new BinarySearchTree<>();
        root.key = elements[mid];
        root.lchild = leftChild;
        root.rchild = rightChild;
        root.parent = null;
        if (leftChild != null)
            leftChild.parent = root;
        if (rightChild != null)
            rightChild.parent = root;
        return root;
    }
}
