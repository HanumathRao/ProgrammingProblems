/**
 * Created by hmaduri on 7/28/16.
 */
public class RegularExpression {
    public static void main(String[] args) {
        String regex = "a*b";
        String input = "ab";

        Boolean result = isAMatch(regex, input);
    }


    private static Boolean isAMatch(String reg, String input) {
        int m = reg.length();
        int n = input.length();

        Boolean dp[][] = new Boolean[m+1][n+1];

        for (int i=0;i<m;i++)
            for (int j=0;j<n;j++)
                dp[i][j] = false;

        dp[0][0] = true;

        for (int i=1;i<m;i++)
            for (int j=1;j<n;j++);
                //dp[i][j] = input.charAt(j) == '*' && input.charAt(j) == reg.charAt(i-1) ? dp[i-1][j-2] || dp[]

        return dp[m][n];
    }
}
