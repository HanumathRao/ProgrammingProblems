package com.company;

/**
 * Created by hmaduri on 6/5/16.
 */
public class EditDistanceBtwTwoStrings {
    public static void main(String[] args) {
        String str1 = "sunday", str2 = "saturday";

        int editDistance = getEditDistance(str1,str2);
        System.out.println("Editdistance:" + editDistance);
    }

    private static int getEditDistance(String str1, String str2) {
        if (str1.toCharArray().length == 0)
            return str2.length();
        if (str2.toCharArray().length == 0)
            return str1.length();

        return getEditDistanceInternal(str1,str2,0,0);
    }

    private static int getEditDistanceInternal(String str1, String str2, int m, int k) {
        if (m >= str1.length())
            return str2.length() - k;

        if (k>= str2.length())
            return str1.length() - m;

        if (str1.toCharArray()[m] == str2.toCharArray()[k])
            return getEditDistanceInternal(str1,str2,m+1,k+1);
        else {
            return 1 + Math.min(getEditDistanceInternal(str1,str2,m+1,k) ,
                                Math.min(getEditDistanceInternal(str1,str2,m,k+1),
                                         getEditDistanceInternal(str1,str2,m+1,k+1)));
        }
    }
}
