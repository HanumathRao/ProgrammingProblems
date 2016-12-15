package com.company.Trees;

/**
 * Created by hmaduri on 7/2/16.
 */
public class FenwickTree {
    public static void main(String [] args) {
        Integer elements[] = {2,3,5,6,7,8,9,1,34,322};

        int fenwickTree[] = new int[elements.length+1];

        buildFenwickTree(fenwickTree, elements);
        int start =0, end= 4;
        System.out.println("sum of elements from [" + start + ","+end+"] is "+ (getSum(end+1, fenwickTree ) - getSum(start, fenwickTree)));
    }

    public static void buildFenwickTree(int tree[], Integer elems[] ) {
        //insert 0 in the first element
        tree[0] = 0;

        for(int index=0;index<elems.length;index++) {
            updateTree(index,elems, tree, index+1);
        }
    }

    public static void updateTree(int index, Integer elems[], int tree[], int treeindex) {
        if (treeindex >= (tree.length+1)) return;
        tree[treeindex] += elems[index];
        updateTree(index,elems,tree,getnext(treeindex));
     }

    public static int getnext(int index) {
        return index + ((index & (~index+1)));
    }

    public static int getParent(int index){
        return index - ((index & (~index+1)));
    }

    public static int getSum(int index, int tree[]) {
        if (index<=0)
            return 0;
        return tree[index] + getSum(getParent(index), tree);
    }
}
