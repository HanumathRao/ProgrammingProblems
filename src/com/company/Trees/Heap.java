package com.company.Trees;

/**
 * Created by hmaduri on 7/2/16.
 */
public class Heap {

    public static void main(String[] args) {
        Integer elems[] = {1,2,3,23,1,4,12,4,53};

        minHeap minelems = new minHeap();
        for(int elem : elems)
            minelems.insert(elem);

        int k = 10;
        for (int index=0;index<k;index++)
            System.out.println("minimum elem: " + minelems.get());
    }

    private static class minHeap {
        int keys[];
        int length;

        public minHeap(int size) {
            this.keys = new int[size+1];
            this.length=0;
        }

        public minHeap() {
            this.keys = new int[26];
            this.length = 0;
        }

        public void insert(int key) {
            keys[++length] = key;
            trickleup();
        }

        public void trickleup() {
            int current = this.length;
            while(current/2 >= 1 && keys[current] < keys[current/2]) {
                swap(current, current / 2);
                current = current / 2;
            }
        }

        public void swap(int i, int j) {
            int k = keys[i];
            keys[i] = keys[j];
            keys[j] = k;
        }

        public int get() {
            if (length <=0)
                return -1;
            int top = keys[1];
            keys[1] = keys[length--];
            trickledown(1);
            return top;
        }

        public void trickledown(int index) {
            int rootindex = index;
            while(rootindex*2+1<=length) {
                int  minindex;
                if (keys[rootindex*2] < keys[rootindex*2+1])
                    minindex = rootindex*2;
                else
                    minindex = rootindex*2+1;
                if (keys[rootindex]>keys[minindex])
                    swap(minindex,rootindex);
                else break;
                rootindex = minindex;
            }
            if (rootindex*2 == length)
                if (keys[rootindex]>keys[rootindex*2])
                    swap(rootindex*2,rootindex);
        }
    }
}
