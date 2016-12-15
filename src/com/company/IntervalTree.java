package com.company;

import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hmaduri on 6/23/16.
 */
public class IntervalTree<K extends Comparable<K>> {
    private IntervalTreeNode<K> root;

    private static class IntervalTreeNode<K> {
        public Pair<K,K> range;
        public IntervalTreeNode<K> leftChild;
        public IntervalTreeNode<K> rightChild;

        public IntervalTreeNode(Pair<K,K> range) {
            this.range = range;
        }
    }

    IntervalTree() {
    }

    private int compare(Pair<K,K> leftRange, Pair<K,K> rightRange) {
        if (leftRange.snd.compareTo(rightRange.fst) < 0 )
            return -1;
        else if ( rightRange.snd.compareTo(leftRange.fst) < 0 )
            return 1;
        else
            return 0;
    }

    private IntervalTreeNode<K> insert(IntervalTreeNode<K> root, Pair<K,K> range) {
        if (root == null) {
            IntervalTreeNode<K> node = new IntervalTreeNode<>(range);
            return node;
        }
        int compValue = compare(root.range, range);
        if (compValue > 0)
            root.leftChild = insert(root.leftChild, range);
        else if (compValue < 0)
            root.rightChild = insert(root.rightChild, range);
        return root;
    }


    public void addRange(K l, K r) {
        Pair<K,K> range = Pair.of(l,r);
        List<Pair<K,K>> overlappedPairs = query(l,r);
        Pair<K,K> mergedRange = merge(overlappedPairs, range);
        root = insert(root, mergedRange);
        for (Pair<K,K> p : overlappedPairs)
            root = remove(root, p);
    }

    private Pair<K,K> merge(List<Pair<K,K>> overlappedRanges, Pair<K,K> range) {
        K min = range.fst;
        K max = range.snd;

        for (Pair<K,K> r : overlappedRanges) {
            if (min.compareTo(r.fst) > 0) min = r.fst;
            if (max.compareTo(r.snd) < 0) max = r.snd;
        }
        return Pair.of(min,max);
    }

    private IntervalTreeNode<K> remove(IntervalTreeNode<K> node, Pair<K,K> range) {
        int compValue = compare(node.range, range);
        if (compValue == 0) {
            IntervalTreeNode<K> leftnode = getFirstGreater(node.rightChild);
            if (leftnode != null)
                return leftnode;
            return node.leftChild;
        }
        else if (compValue < 0)
            node.leftChild = remove(node.leftChild, range);
        else
            node.rightChild = remove(node.rightChild, range);
        return node;
    }

    private IntervalTreeNode<K> getFirstGreater(IntervalTreeNode<K> node) {
        if (node == null)
            return node;
        if (node.leftChild != null)
            return getFirstGreater(node.leftChild);
        return node;
    }

    public void deleteRange(K l, K r) {
        Pair<K,K> range = Pair.of(l,r);
        List<Pair<K,K>> overlappedPairs = query(l,r);
        List<Pair<K,K>> deletedRanges = delete(overlappedPairs, range);
        for (Pair<K,K> p : overlappedPairs)
            root = remove(root, p);

        for (Pair<K,K> p : deletedRanges)
            root = insert(root, p);
    }

    private List<Pair<K,K>> delete(List<Pair<K,K>> overlappedPairs, Pair<K,K> range) {
        List<Pair<K,K>> output = new ArrayList<>();
        for (Pair<K,K> r : overlappedPairs) {
            assert (compare(r,range) ==0);
            //check for complete overlap
            if (range.fst.compareTo(r.fst) < 0 &&
                range.snd.compareTo(r.snd) > 0)
                continue;
            else if (range.fst.compareTo(r.fst) > 0)
                output.add(Pair.of(r.fst, range.fst));
            else
                output.add(Pair.of(range.snd, r.snd));
        }
        return output;
    }

    public List<Pair<K,K>> query(K l, K r) {
        Pair<K,K> range = Pair.of(l,r);
        List<Pair<K,K>> result = new ArrayList<>();
        query(root,range, result);
        return result;
    }

    private void query(IntervalTreeNode<K> node, Pair<K,K> range, List<Pair<K,K>> output) {
        if (node == null)
            return;
        int compValue = compare(node.range, range);
        if (compValue == 0) {
            output.add(node.range);
            if (node.range.fst.compareTo(range.fst) > 0)
                query(node.leftChild,range, output);
            if (node.range.snd.compareTo(range.snd) < 0)
                query(node.rightChild,range,output);
        } else if (compValue > 0)
            query(node.leftChild, range, output);
        else
            query(node.rightChild, range, output);
    }

    public List<Pair<K,K>> query(K l) {
        List<Pair<K,K>> result = new ArrayList<>();
        query(root, Pair.of(l,l), result);
        return result;
    }
}
