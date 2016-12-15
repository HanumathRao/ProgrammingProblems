package com.company;

public class TopDownStockProfit {

    public static void main(String[] args) {
//        int [] price = {10, 22, 5, 75, 65, 80};
//        int[] price = {12, 14, 17, 10, 14, 13, 12, 15};
//        int[] price = {100, 30, 15, 10, 8, 25, 80};
        int[] price = {90, 80, 70, 60, 50};
        int numOfElems = price.length;
        int numOfTransactions = 1;

        int maxProfitEarned = calculateProfit(numOfTransactions,price, numOfElems-1);
        System.out.println(" Profit earned =" + maxProfitEarned);
    }

    private static int calculateProfit(int numOfTransactions, int[] price, int numOfElems) {
        if (numOfTransactions <= 0 || numOfElems < 0)
            return 0;

        int maxEarning = -1;
        for (int index= numOfElems-1; index>=0;index--) {
            int priceEarned = (price[numOfElems] - price[index]);
            priceEarned += calculateProfit(numOfTransactions-1, price, index-1);
            if (maxEarning < priceEarned)
                maxEarning = priceEarned;
        }
        int noTransactionCost = calculateProfit(numOfTransactions, price, numOfElems-1);
        if (maxEarning < noTransactionCost)
            maxEarning = noTransactionCost;
        return maxEarning;
    }
}
