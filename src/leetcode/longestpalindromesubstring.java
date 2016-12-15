package leetcode;

/**
 * Created by hmaduri on 8/9/16.
 */
public class longestpalindromesubstring {

    public static void main(String [] args) {
        String str = "321012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210123210012321001232100123210123";
//        String reversestr = reverse(str);

        System.out.println("longest palindromic substring " + longestPalindromicSubstring(str));
    }

    static int longestPalindromicSubstring(String str) {
        int matrix[][] = new int[str.length()][str.length()];

        for(int i=0;i<str.length();i++) {
            matrix[i][i] = 1;
        }

        for (int i=1;i<str.length();i++) {
            for (int j=0;j<str.length() && j+i < str.length();j++) {
                if (isPalindrome(str,j,j+i))
                    matrix[j][i] = i+1;
                matrix[j][i] = matrix[j][i] > matrix[j][i-1] ? matrix[j][i] : matrix[j][i-1];
                matrix[j][i] = matrix[j][i] > matrix[j+1][i-1] ? matrix[j][i] : matrix[j+1][i-1];
            }
        }
        return matrix[0][str.length()-1];
    }

    static boolean isPalindrome(String str, int i, int j) {
        while(i<=j && str.charAt(i) == str.charAt(j)) {
            i++;
            j--;
        }
        if (i>j)
            return true;
        else return false;
    }

    static String reverse(String str) {
        String result = "";
        for (char a: str.toCharArray())
            result = a+result;
        return result;
    }
}
