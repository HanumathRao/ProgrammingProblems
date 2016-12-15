package leetcode;

/**
 * Created by hmaduri on 8/9/16.
 */

//output should be PAHNAPLSIIGYIR
public class zigzagprinting {
    public static void main(String [] args) {
        String str = "PAYPALISHIRING";
        int rowcount = 3;
        System.out.println("zig zag: " + zigzag(str, rowcount));
    }

    static String zigzag(String str, int rows) {
        String [] strings = new String[rows];

        for(int i=0;i<rows;i++)
            strings[i] = "";
        int r = 0; int incr=0;
        for(int i=0;i<str.length();i++) {
            if (r == 0) incr = 1;
            if (r == rows-1) incr = -1;

            strings[r] += str.charAt(i);
            r += incr;
        }
        String result = "";
        for (int i=0;i<rows;i++)
            result += strings[i];

        return result;
    }
}
