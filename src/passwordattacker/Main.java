package passwordattacker;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by User on 22/8/15.
 */
public class Main {
    private static BigInteger[][] memo;
    private static final int mod = 1000000007;

    public static void main(String[] args) throws IOException {
        // Parse input into array of I
        BufferedReader reader = new BufferedReader(new FileReader("input/A-large-practice.in"));
        int numCases = Integer.valueOf(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/output.txt"));

        for (int i = 0; i < numCases; i++) {
            System.out.println(String.format("Case #%s: ", i + 1));
            String inp = reader.readLine();
            int M = Integer.parseInt(inp.split("\\s")[0]);
            int N = Integer.parseInt(inp.split("\\s")[1]);
            memo = new BigInteger[M][N];
            for (int n = 0; n < N; n++) {
                memo[0][n] = BigInteger.ONE;
                if (n < M) {
                    memo[n][n] = n==0 ? BigInteger.ONE : memo[n - 1][n - 1].multiply(BigInteger.valueOf((long) n +1));
                }

            }
            BigInteger ans = dp(M - 1, N - 1).mod(BigInteger.valueOf((long) mod));
            writer.write(String.format("Case #%s: ", i + 1));
//            for (BigInteger[] m : memo) {
//                System.out.println(Arrays.toString(m));
//            }
            System.out.println("Answer: " + ans);
            writer.write(String.valueOf(ans));
            writer.newLine();
        }

        writer.close();
        reader.close();
    }

    private static BigInteger dp(int m, int n) {
//        System.out.println(String.format("dp[%s][%s]", m,n));
        try {
            if (memo[m][n] == null || memo[m][n].equals(BigInteger.ZERO)) {
                memo[m][n] = dp(m, n - 1).add(dp(m - 1, n - 1)).multiply(BigInteger.valueOf((long)m+1));
            }
        } catch (Exception e) {
            System.out.println(m+", "+n);
//            e.printStackTrace();
        }

        return memo[m][n];

    }
}
