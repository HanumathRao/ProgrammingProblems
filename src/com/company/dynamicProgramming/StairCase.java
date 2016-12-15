package com.company.dynamicProgramming;

/**
 * Created by hmaduri on 6/25/16.
 */
public class StairCase {
    public static void main(String[] args) {
        Integer ways[] = {1,2,3};

        int N = 35;

        long matrix[] = new long[N+1];
        matrix[0] = 1;

        for(int i=1;i<=N;i++)
            for (int j=1;j<=3;j++)
                if (i-j >= 0)
                    matrix[i] += matrix[i-j];// + matrix[i-2] + matrix[i-3];

        System.out.println("output:" + matrix[N]);

        long l = countWays(N);
        System.out.println("countways:"+l);
    }


    public static long countWays(int n) {
        if (n<0) {
            return 0;
        } else if (n==0)
            return 1;
        else
            return countWays(n-1)+countWays(n-2)+countWays(n-3);
    }
}

