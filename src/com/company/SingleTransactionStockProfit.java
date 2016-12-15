package com.company;

/**
 * Created by hmaduri on 5/30/16.
 */
public class SingleTransactionStockProfit {

    public static void main(String[] args) {
//        int[] price = {10, 22, 5, 75, 65, 80};
        int[] price = {12, 14, 17, 10, 14, 13, 12, 20};
        int size = price.length;
//        int transactions = 2;
        int profitEarned = calculateProfit(size, price);

        System.out.println("profit earned = " + profitEarned);
    }

    private static int calculateProfit(int size, int[] price) {
        int minElem = Integer.MAX_VALUE;
        int maxDiff = Integer.MIN_VALUE;

        for (int index=0;index<size;index++) {
            if (minElem > price[index]) {
                minElem = price[index];
            }
            if (maxDiff < (price[index] - minElem))
                maxDiff = (price[index] - minElem);

        }
        return maxDiff;
    }

}
