package com.company.BTree;

import com.company.BTree.BTreeNode.BTreeNodeBuilder;
import com.sun.tools.javac.util.Pair;

import java.util.*;
/**
 * Created by hmaduri on 6/1/16.
 */

public class BTree<K extends Comparable<K>,V> {
    private final BTreeNode<K,V> root;

    public BTree(BTreeNode<K,V> root) {
        this.root = root;
    }

    public BTreeBuilder<K,V> getBuilder() {
        return new BTreeBuilder<K,V>();
    }

    public static class BTreeBuilder<K extends Comparable<K>,V> {

        private BTreeNodeBuilder<K,V> root;

        public BTreeBuilder() { }

        public BTreeNode.BTreeNodeBuilder<K,V> createLeafNode() {
            return new BTreeNode.BTreeLeafNodeBuilder<K,V>();
        }

        public <N extends BTreeNode<K,N>>
        BTreeNode.BTreeInternalNodeBuilder<K,N> createInternalNode() {
            return new BTreeNode.BTreeInternalNodeBuilder<K,N>();
        }

        public void insert(Pair<K,V>... keyValuePairs) {
            Arrays.sort(keyValuePairs, new Comparator<Pair<K, V>>() {
                @Override
                public int compare(Pair<K, V> o1, Pair<K, V> o2) {
                    return o1.fst.compareTo(o2.fst);
                }
            });

            List<BTreeNodeBuilder<K,V>> btreenodes = new ArrayList<>();
            BTreeNode.BTreeNodeBuilder datanode = null;
            for(Pair<K,V> pair : keyValuePairs) {
                if (datanode == null || datanode.isFull()) {
                    datanode = this.createLeafNode();
                    btreenodes.add(datanode);
                }
                datanode.add(pair.fst,pair.snd);
            }
            buildBTree(btreenodes);
        }

        private BTree.BTreeBuilder<K,V> buildBTree(List<BTreeNodeBuilder<K,V>> btreenodes) {
            if (btreenodes.size() == 1) {
                this.root = btreenodes.get(0);
                return this;
            }
            else {
                List<BTreeNode.BTreeNodeBuilder<K,V>> internalNodes = new ArrayList<>();
                BTreeNode.BTreeNodeBuilder internalNode = null;
                for(BTreeNode.BTreeNodeBuilder btreenode : btreenodes) {
                    if (internalNode == null || internalNode.isFull()) {
                        internalNode = this.createInternalNode();
                        internalNodes.add(internalNode);
                    }
                    internalNode.add(btreenode.lastKey(),btreenode.build());
                }
                buildBTree(internalNodes);
            }
            return this;
        }

        public BTree<K,V> build() {
            assert(this.root != null);
            return new BTree<K,V>(this.root.build());
        }
    }
}
