package com.company;

/**
 * Created by hmaduri on 6/5/16.
 */
public class KPalindromicstring {
    public static void main(String[] args) {
        int k = 2;
        String s = "abcdeca";
        String revStr = reverse(s);

        System.out.println("isKPal:" + (getEditDistance(s,revStr)<= 2*k));

    }

    private static int getEditDistance(String str1, String str2) {
        if (str1.length() == 0)
            return str2.length();
        if (str2.length() == 0)
            return str1.length();

        return getEditDistanceInternal(str1,str2,0,0);
    }

    private static int getEditDistanceInternal(String str1, String str2, int m, int n) {
        if (m>= str1.length())
            return str2.length() - n;
        if (n>= str2.length())
            return str1.length() - m;
        if (str1.toCharArray()[m] == str2.toCharArray()[n])
            return getEditDistanceInternal(str1,str2,m+1,n+1);
        else {
            return 1 + Math.min(getEditDistanceInternal(str1,str2,m+1,n),
                                getEditDistanceInternal(str1,str2,m,n+1));
        }
    }

    private static String reverse(String str) {
        String s = new String();
        char[] chArray = str.toCharArray();
        for(int index=chArray.length - 1;index>=0;index--)
            s += chArray[index];
        return s;
    }
}
