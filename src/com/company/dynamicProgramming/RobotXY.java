package com.company.dynamicProgramming;

/**
 * Created by hmaduri on 6/25/16.
 */
public class RobotXY {
    public static void main(String[] args) {
        int X = 3, Y=3;
        int matrix[][] = new int[X+1][Y+1];

        matrix[X][Y] = 0;
        for (int i=X-1;i>=1;i--)
            matrix[i][Y] += 1 + matrix[i+1][Y];

        for (int i=Y-1;i>=1;i--)
            matrix[X][i] += 1 + matrix[X][i+1];

        for(int i=X-1; i>=0;i--)
            for(int j=Y-1; j>=0;j--)
                matrix[i][j] += 2 + matrix[i+1][j] + matrix[i][j+1];

        System.out.println("number of ways:" + matrix[1][1]);

        System.out.println("ways:"+countWays(3,3));
    }

    public static int countWays(int m,int n) {
        if (m==1)
            return n-1;
        if (n==1)
            return m-1;
        return 1+ countWays(m,n-1) + 1 + countWays(m-1,n);
    }
}
