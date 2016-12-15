package leetcode;

/**
 * Created by hmaduri on 8/9/16.
 */

///This program doesn't work. it works for only some cases.
public class longestPalindromicSubstring {
    public static void main(String[] args) {
//        String str = "abcdefaba";
//        String str2 = "abafedcba";

//        String str = "ababa";
//        String str2 = "ababa";
        String str = "abacdfgdcaba";
        String reversestr = reverse(str);
        System.out.println("same string   : "+ str);
        System.out.println("reverse string: " + reversestr);

        System.out.println("length of longest palindromic substring " + longestCommonSubstring(str,reversestr));
    }

    static String reverse(String str) {
        String result = "";
        for (char a: str.toCharArray())
            result = a+result;
        return result;
    }

    static int longestCommonSubstring(String str1, String str2) {
        int l = str1.length(),m = str2.length();
        int length[][] = new int[l+1][m+1];
        int maxlength[][] = new int[l+1][m+1];

        for(int i=0;i<=str2.length();i++) {
            length[0][i] = 0;
            maxlength[0][i] = 0;
        }

        for(int i=0;i<=str1.length();i++) {
            length[i][0] = 0;
            maxlength[i][0] = 0;
        }

        for (int i=1;i<=str1.length();i++) {
            for(int j=1;j<=str2.length();j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1))
                    length[i][j] = length[i-1][j-1] + 1;
                else
                    length[i][j] = 0;

                maxlength[i][j] = maxlength[i-1][j] > maxlength[i][j-1] ? maxlength[i-1][j] : maxlength[i][j-1];
                maxlength[i][j] = maxlength[i][j] > length[i][j] ? maxlength[i][j] :length[i][j];
            }
        }

        String result = "";
        int lmax = l;
        int mmax = m;
        int maxsize = maxlength[l][m];
        while(maxsize>0) {
            if (maxlength[lmax][mmax] == length[lmax][mmax]) {
                result = str1.charAt(lmax-1) + result;
                lmax--;
                mmax--;
                maxsize--;
            }
            else if (maxlength[lmax][mmax] == maxlength[lmax-1][mmax])
                lmax = lmax -1 ;
            else if (maxlength[lmax][mmax] == maxlength[lmax][mmax-1])
                mmax = mmax - 1;
        }

        System.out.println("palindromic string " + result);

        return maxlength[l][m];

    }

}
