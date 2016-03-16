import java.util.Scanner;

public class Expressions {
    public static final int NUM = 151;

    public static void main(String[] args) {

        long[][]dp = new long[NUM][NUM];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }

        for (int n = 2; n < dp.length; n++) {
            for (int depth = 1; depth < n; depth++) {
                //StringBuffer sb = new StringBuffer(String.format("[%d, %d] ->\n", n, depth));
                for (int k = 0; k <= n - 1; k++) {
                    for (int l = 0; l <= depth; l++) {
                        //sb.append(String.format("\t(%d, %d=%d)*(%d, %d=%d)\n", k, depth-1, dp[k][depth-1], n-k-1, l, dp[n-k-1][l]));
                        dp[n][depth] += dp[k][depth-1]*dp[n-k-1][l];
                    }
                }
                for (int k = 0; k <= n - 1; k++) {
                    for (int l = 0; l <= depth-2; l++) {
                        //sb.append(String.format("\t(%d, %d=%d)*(%d, %d=%d)\n", k, l, dp[k][l], n-k-1, depth, dp[n-k-1][depth]));
                        dp[n][depth] += dp[k][l]*dp[n-k-1][depth];
                    }
                }
                //System.out.println(sb.toString() + " => " + dp[n][depth]);
            }
        }


        final Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextInt()){
            int length = scanner.nextInt();
            int depth = scanner.nextInt();
            long result = length % 2 == 0 ? dp[length/2][depth] : 0;
            //long result = dp[length][depth];
            System.out.println(result);
        }
    }
}
