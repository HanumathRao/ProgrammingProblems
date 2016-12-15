package com.company.BTree;

import com.sun.tools.javac.util.Pair;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by hmaduri on 6/1/16.
 */
public abstract class BTreeNode<K extends Comparable<K>,V> {
    private static final int SIZE = 10;
    private final ConcurrentSkipListMap<K,V> keysMap = new ConcurrentSkipListMap<>();

    public BTreeNode(BTreeNodeBuilder<K,V> bldr) {
        for(Pair<K,V> kv: bldr.getKeyValuePairs()) {
            keysMap.put(kv.fst,kv.snd);
        }
    }

    public abstract BTreeNodeBuilder<K,V> getBuilder();

    public static abstract class BTreeNodeBuilder<K extends Comparable<K>,V> {
        abstract public BTreeNode<K,V> build();
        abstract public BTreeNodeBuilder<K,V> add(K key, V value);
        abstract public boolean isFull();
        abstract public K lastKey();
        abstract public List<Pair<K,V>> getKeyValuePairs();
        abstract public List<BTreeNodeBuilder<K,V>> getChildren();
    }

    public static class BTreeInternalNodeBuilder<K extends Comparable<K>, V extends BTreeNode<K,V>>
                                           extends BTreeNodeBuilder<K,V> {
        public List<K> keys = new ArrayList<K>();
        public List<V> children = new ArrayList<>();

        public BTreeInternalNodeBuilder() { }

        @Override
        public BTreeInternalNode<K,V> build() {
            return new BTreeInternalNode<K,V>(this);
        }

        @Override
        public BTreeNodeBuilder<K, V> add(K key, V value) {
            keys.add(key);
            children.add(value);
            return this;
        }

        @Override
        public boolean isFull() {
            assert(keys.size()<=BTreeNode.SIZE);
            return keys.size() == BTreeNode.SIZE;
        }

        @Override
        public List<Pair<K,V>> getKeyValuePairs() {
            List<Pair<K,V>> keyValues = new ArrayList<>();
            for (int index=0;index<this.keys.size();index++)
                keyValues.add(Pair.of(this.keys.get(index), this.children.get(index)));
            return keyValues;
        }

        @Override
        public K lastKey() {
            return this.keys.get(this.keys.size()-1);
        }

        @Override
        public List<BTreeNodeBuilder<K,V>> getChildren(){
            return null;
        }
    }

    public static class BTreeLeafNodeBuilder<K extends Comparable<K>,V>
                                               extends BTreeNodeBuilder<K,V> {
        public List<K> keys = new ArrayList<K>();
        public List<V> values = new ArrayList<V>();

        public BTreeLeafNodeBuilder() { }

        @Override
        public BTreeNode.BTreeLeafNode<K,V> build() {
            return new BTreeLeafNode<K,V>(this);
        }

        @Override
        public BTreeNodeBuilder<K, V> add(K key, V value) {
            keys.add(key);
            values.add(value);
            return this;
        }

        @Override
        public boolean isFull() {
            assert(keys.size() <= BTreeNode.SIZE);
            return keys.size() == BTreeNode.SIZE;
        }

        @Override
        public K lastKey() {
            return this.keys.get(this.keys.size()-1);
        }

        @Override
        public List<Pair<K, V>> getKeyValuePairs() {
            List<Pair<K,V>> keyValues = new ArrayList<>();
            for (int index=0;index<this.keys.size();index++)
                keyValues.add(Pair.of(this.keys.get(index), this.values.get(index)));
            return keyValues;
        }

        @Override
        public List<BTreeNodeBuilder<K,V>> getChildren() {
            return new ArrayList<>();
        }
    }

    public static class BTreeInternalNode<K extends Comparable<K>,V extends BTreeNode<K,V>> extends BTreeNode<K,V> {

        public BTreeInternalNode(BTreeInternalNodeBuilder<K,V> bldr) {
            super(bldr);
        }

        @Override
        public BTreeNodeBuilder<K, V> getBuilder() {
            return new BTreeInternalNodeBuilder<K,V>();
        }
    }

    public static class BTreeLeafNode<K extends Comparable<K>,V> extends BTreeNode<K,V> {

        public BTreeLeafNode(BTreeLeafNodeBuilder<K,V> bldr) {
            super(bldr);
        }

        @Override
        public BTreeNodeBuilder<K,V> getBuilder() {
            return new BTreeLeafNodeBuilder<K,V>();
        }
    }
}
