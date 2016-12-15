package com.company;

/**
 * Created by hmaduri on 5/30/16.
 */
public class TwoTransactionStockProfit {
    public static void main(String[] args ) {
//        int[] price = {12, 14, 17, 10, 14, 13, 12, 20};
        int[] price = {2, 30, 15, 10, 8, 25, 80};
        int size = price.length;

        int profitEarned = calculateProfit(size, price);
        System.out.println("profit earned: " + profitEarned);
    }

    private static int calculateProfit(int size, int[] price) {

        int[] profit = new int[size];

        //Traverse the array from the back and fillup the
        //one transaction profits
        int maxElem = price[size-1];
        for (int index=size-2;index>=0;index--) {
            if (maxElem < price[index])
                maxElem = price[index];

            profit[index] = Integer.max(profit[index+1], maxElem - price[index]);
        }

        //Traverse the array from the front and fill it up with
        //two transaction profits.
        int minElem = price[0];
        for (int index=1;index<size;index++) {
            if(minElem > price[index])
                minElem = price[index];
            profit[index] = Integer.max(profit[index-1], profit[index] + price[index] - minElem);
        }

        return profit[size-1];
    }
}
