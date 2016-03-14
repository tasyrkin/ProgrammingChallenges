import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

/**
 * The state represents a count of numbers whose digits sum up to N.
 * If one thinks of the state as ALL numbers whose digits sum up to N,
 * then transitioning to another state means that we add allowed number
 * (1,4,2,3) to ALL those numbers and it results in N+1, N+2, N+3 sums
 * correspondingly.
 *
 * State 0 represents an empty set and its count is 1.
 *
 * Consider ALL numbers for a state N=4:
 * 1111, 112, 121, 13, 22, 211, 31 (total = 7)
 * It is possible to transition from
 * the state N=3: 111, 12, 21, 3 => by adding 1 to the left: *1*111, *1*12, *1*21, *1*3
 * the state N=2: 11, 2          => by adding 2 to the left: *2*11, *2*2
 * the state N=1: 1              => by adding 3 to the left: *3*1
 */
public class CountingDP {

    static final BigInteger TWO = new BigInteger("2");

    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        BigInteger dp[] = new BigInteger [1001];

        Arrays.fill(dp, ZERO);

        dp[0] = ONE;

        for ( int i = 1; i <= 1000; i++ ) {
            if(i - 1 >= 0)
                dp[i] = dp[i].add(dp[i - 1].multiply(TWO));

            if(i - 2 >= 0)
                dp[i] = dp[i].add(dp[i - 2]);

            if(i - 3 >= 0)
                dp[i] = dp[i].add(dp[i - 3]);
        }

        while (input.hasNextInt()) {
            final int n = input.nextInt();
            System.out.println (dp[n]);
        }
    }
}