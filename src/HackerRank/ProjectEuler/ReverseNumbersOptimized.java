package HackerRank.ProjectEuler;

import java.util.Scanner;

/**
 * Created by hmaduri on 6/12/16.
 */
public class ReverseNumbersOptimized {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long testcases = sc.nextLong();

        while(testcases-- > 0) {
            long count=0;
            for (int i = 1; i < 5; i++) {

                switch (i % 4) {
                    case 0:
                    case 2:
                        count += 20 * (int) Math.pow(30, (i / 2 - 1));
                        break;
                    case 1:
                        count += 100 * (int)Math.pow(500, i / 4 -1);
                        break;
                    case 3:
                        break;
                }
            }
            System.out.println(count);
        }
    }
}
