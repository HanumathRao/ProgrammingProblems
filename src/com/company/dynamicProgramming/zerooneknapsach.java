package com.company.dynamicProgramming;

/**
 * Created by hmaduri on 6/27/16.
 */
public class zerooneknapsach {

    public static void main(String[] args) {
        Integer weights[ ] = {1,4,5,7};
        Integer values[] = {1,3,4,5};


        Integer matrix[][] = new Integer[values.length][weights[weights.length-1]+1];

        for(int i=0;i<=values.length-1;i++)
            matrix[i][0] = 0;

        for(int j=0;j<=weights[weights.length-1];j++)
            matrix[0][j] = j >= weights[0] ? values[0] :0;

        for(int i=1;i<=values.length-1;i++)
            for (int j=1;j<=weights[weights.length-1];j++) {
                int k = ((j-weights[i] >= 0) ? weights[j-weights[i]] : 0);
                matrix[i][j] = Math.max( values[i]+  matrix[i-1][k], matrix[i-1][j]);
            }


        System.out.println("Heighest value:"+matrix[values.length-1][weights[weights.length-1]]);

    }
}
