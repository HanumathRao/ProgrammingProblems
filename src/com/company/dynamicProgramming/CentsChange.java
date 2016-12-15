package com.company.dynamicProgramming;

/**
 * Created by hmaduri on 6/25/16.
 */
public class CentsChange {

    public static void main(String[] args) {
        Integer cents[] = {25,10,5,1};
//        Integer cents[] = {1,5,10,25};
//        Integer cents[] = {1,5,10,25};
//        Integer cents[] = {1,25};
        int amount = 100;
        long cache[] = new long[amount+1];
        cache[0] = 1;

        for (int j=0; j<=cents.length-1;j++)
            for(int i=1;i<=amount;i++)
//            for(int j=0;j<=cents.length-1;j++)
                if((i-cents[j])>=0)
                    cache[i] += cache[i-cents[j]];

        for (int i=0;i<=amount;i++)
            System.out.print(cache[i]+":");

        System.out.println();

        System.out.println("cents:"+cache[amount-1]);

        System.out.println("cents through makechange:"+makeChange(amount));
    }

    public static int makeChange(int amount, int[] denoms, int index) {
        if (index >= denoms.length-1) return 1;

        int denomAmount = denoms[index];
        int ways=0;

        for (int i=0; i*denomAmount < amount; i++) {
            int amountRemaining = amount - i * denomAmount;
            ways += makeChange(amountRemaining, denoms, index+1);
        }
        return ways;
    }

    public static int makeChange(int n) {
        int denoms[] = {25,10,5,1};
//        int denoms[] = {25,1};
        return makeChange(n, denoms, 0);
    }
}
