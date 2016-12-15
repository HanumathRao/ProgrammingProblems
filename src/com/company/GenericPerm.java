package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by hmaduri on 5/30/16.
 */
public class GenericPerm {
    public static void main(String[] args) {
        Character[] string = {'h','a','n','u'};
        PermGenerator<Character> gen = new PermGenerator<Character>(string);
        Character[] permString = gen.nextPermutation();
        do {
            for(int index=0;index<permString.length;index++)
                System.out.print(permString[index]);
            System.out.println();
            permString = gen.nextPermutation();
        } while(permString != null);
    }


    private static class PermGenerator<T extends Comparable<T>> {
        private final T[] value;
        Boolean isFirstTime = true;

        public PermGenerator(T[] value) {
            this.value = value.clone();
            Arrays.sort(this.value);
        }

        public T[] nextPermutation() {
            if (this.isFirstTime) {
                this.isFirstTime = false;
                return this.value;
            }
            T[] returnValue = nextPermutationInternal(value, new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    return o1.compareTo(o2);
                }
            });
            return returnValue;
        }

        private T[] nextPermutationInternal(T[] value, Comparator<T> comparator) {
            int end = value.length-1;
            int lastButOne = end - 1;

            while(end>0 && value[lastButOne].compareTo(value[end]) >= 0 ) {
                lastButOne--; end--;
            }

            if (end == 0)
                return null;

            int last = value.length - 1;
            while(value[lastButOne].compareTo(value[last]) > 0 && last>end)
                last--;

            PermGenerator.swap(value, lastButOne, last);
            PermGenerator.sort(value, end, value.length-1);
            return this.value;
        }

        private static <E> void swap(E[] value, int i, int j) {
            E tmp = value[i];
            value[i] = value[j];
            value[j] = tmp;
        }

        private static <E> void sort(E[] value, int i, int j) {
            for(int index=i;index<=j;index++,j--)
                swap(value,index,j);
        }
    }
}
