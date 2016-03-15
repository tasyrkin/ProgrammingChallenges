import java.util.Scanner;

public class Expressions {
    public static final int NUM = 151;

    public static void main(String[] args) {

        long[][]dp = new long[NUM][NUM];
        for (int i = 0; i < dp.length; i++) {
            dp[i][1] = 1;
            dp[i][i] = 1;
        }

        for (int n = 2; n < dp.length; n++) {
            for (int depth = 1; depth < n; depth++) {
                {
                    int leftDepth = depth - 1;
                    for (int k = leftDepth; k <= n - 1; k++) {
                        for (int rightDepth = 0; rightDepth <= n - k - 1; rightDepth++) {
                            dp[n][depth] += dp[k][leftDepth] * dp[n - k - 1][rightDepth];
                        }
                    }
                }
                {
                    int rightDepth = depth;
                    for (int k = 0; k <= n - 1 - rightDepth; k++) {
                        for (int leftDepth = 1; leftDepth <= k; leftDepth++) {
                            dp[n][depth] += dp[k][leftDepth] * dp[n-k-1][rightDepth];
                        }
                    }
                }
            }
        }


        final Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextInt()){
            int length = scanner.nextInt();
            int depth = scanner.nextInt();
            long result = length % 2 == 0 ? dp[length/2][depth] : 0;
            System.out.println(result);
        }
    }
}
