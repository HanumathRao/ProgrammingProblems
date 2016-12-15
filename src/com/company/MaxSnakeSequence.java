package com.company;

import com.sun.tools.javac.util.Pair;

/**
 * Created by hmaduri on 6/4/16.
 */
public class MaxSnakeSequence {
    public static void main(String [] args) {
        int[][] mat = { {9,6,5,2},
                        {8,7,6,5},
                        {7,3,1,6},
                        {1,1,1,7}};

        String snakeSequence = findMaxSnakeSequence(mat, 4, 4);
        System.out.println("Maximum snake sequence:" + snakeSequence);
    }

    public static String findMaxSnakeSequence(int[][] mat, int x, int y) {
        int[][] snakeMat = new int[x+1][y+1];
        for(int indexi=0;indexi<x;indexi++)
            for(int indexj=0;indexj<y;indexj++){
                if (indexi==x-1)
                    snakeMat[indexi][indexj]=1;
                else if (indexj==y-1)
                    snakeMat[indexi][indexj]=1;
                else
                    snakeMat[indexi][indexj]=1;
            }
        int hithertoMaxSnake = -1;
        Pair<Integer,Integer> snakeCoordinates = Pair.of(-1,-1);

        for(int indexi=x-1;indexi>=0;indexi--)
            for(int indexj=y-1;indexj>=0;indexj--) {
                int length = snakeMat[indexi][indexj];
                if (indexi == x-1 || mat[indexi][indexj] == mat[indexi+1][indexj]-1 ||
                    mat[indexi][indexj] == mat[indexi+1][indexj]+1)
                    snakeMat[indexi][indexj] = Math.max(snakeMat[indexi][indexj], length + snakeMat[indexi+1][indexj]);
                if (indexj == y-1 || mat[indexi][indexj] == mat[indexi][indexj+1]-1 ||
                         mat[indexi][indexj] == mat[indexi][indexj+1]+1)
                    snakeMat[indexi][indexj] = Math.max(snakeMat[indexi][indexj], length + snakeMat[indexi][indexj + 1]);
                if (hithertoMaxSnake < snakeMat[indexi][indexj]) {
                    hithertoMaxSnake = snakeMat[indexi][indexj];
                    snakeCoordinates = Pair.of(indexi,indexj);
                }
            }
        String snake = getSnake(snakeCoordinates,snakeMat, hithertoMaxSnake, mat);
        return snake;
    }

    public static String getSnake(Pair<Integer,Integer> coord, int[][] snakeMat,int length, int[][] mat) {
        if (length == 0)
            return "";
        Pair<Integer,Integer> coordinates = snakeMat[coord.fst][coord.snd] - snakeMat[coord.fst][coord.snd+1] == 1 ?
                                            Pair.of(coord.fst,coord.snd+1) : Pair.of(coord.fst+1,coord.snd);
        return "," + mat[coord.fst][coord.snd] + getSnake(coordinates,snakeMat,--length, mat);
    }
}
