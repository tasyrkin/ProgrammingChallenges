import java.util.Arrays;
import java.util.Scanner;

public class Expressions {
    public static final int NUM = 151;

    public static void main(String[] args) {

//        long[]catalan = new long[10];
//
//        catalan[0] = 1;
//
//        for(int number = 1; number < catalan.length; number++){
//            for(int k = 0; k < number; k++){
//                catalan[number] += catalan[k] * catalan[number-k-1];
//            }
//        }
//
//        System.out.println(Arrays.toString(catalan));

        long[][]dp = new long[NUM][NUM];
        for (int i = 0; i < dp.length; i++) {
            dp[i][1] = 1;
            dp[i][i] = 1;
        }

        for (int n = 2; n < dp.length; n++) {
            for (int depth = 2; depth < n; depth++) {
                {
                    int leftDepth = depth - 1;
                    for (int k = Math.max(leftDepth, 1) + 1; k <= n - 1; k++) {
                        for (int rightDepth = 2; rightDepth < n - k - 1; rightDepth++) {
                            dp[n][depth] += dp[k][leftDepth] * dp[n - k - 1][rightDepth];
                        }
                    }
                }
                {
                    int rightDepth = depth;
                    for (int k = 0; k <= n - 1 - rightDepth; k++) {
                        for (int leftDepth = 2; leftDepth < k; leftDepth++) {
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
            //long result = length % 2 == 0 ? dp[length/2][depth] : 0;
            //System.out.println(result);
            System.out.println(dp[length][depth]);
        }
    }
}
