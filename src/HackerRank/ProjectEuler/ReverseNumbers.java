package HackerRank.ProjectEuler;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by hmaduri on 6/12/16.
 */
public class ReverseNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        Map<Character,Boolean> charMap = new HashMap<>();
        charMap.put('0',false);
        charMap.put('1',true);
        charMap.put('2',false);
        charMap.put('3',true);
        charMap.put('4',false);
        charMap.put('5',true);
        charMap.put('6',false);
        charMap.put('7',true);
        charMap.put('8',false);
        charMap.put('9',true);
        while(testcases-- > 0) {
            long num = sc.nextLong();
            long i=0,count=0;
            while(i<num) {
                boolean isReverseNum = true;
                long num1 =i;
                String l = Long.toString(i++);
                String r = new StringBuffer(l).reverse().toString();
                if (r.charAt(0) == '0' || l.charAt(0) == '0')
                    continue;
                String output = new StringBuffer(Long.toString(Long.parseLong(l) + Long.parseLong(r))).toString();
                for(char c: output.toCharArray()) {
                    if(charMap.get(c)==false) {
                        isReverseNum = false;
                        break;
                    }
                }
                if(isReverseNum==true) {
                    //System.out.print(output+ " ");
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
