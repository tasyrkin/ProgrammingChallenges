import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

import static java.math.BigInteger.ONE;

public class Counting {

    public static final BigInteger TWO = new BigInteger("2");

    public static void main(String[] args) throws IOException {

        final BigInteger[][] combinations = countCombinations(1001, 1001);

        final Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextInt()){
            int n = scanner.nextInt();

            String result = calculateNumbersAmount(combinations, n).toString();

            System.out.println(result);
        }
    }

    private static void generateResults(BigInteger[][] combinations) {

        for(int n = 1; n <= 1000; n++) {

            BigInteger totalNumbersCount = calculateNumbersAmount(combinations, n);

            System.out.println(String.format("c.put(%d,\"%s\");", n, totalNumbersCount));
        }
    }

    private static BigInteger calculateNumbersAmount(BigInteger[][] combinations, int sum) {

        BigInteger totalNumbersCount = BigInteger.ZERO;

        for (int k1 = 0; k1 <= 1000 && sum >= k1; k1++) {
            for (int k2 = 0; k2 <= 500 && sum >= k1 + 2 * k2; k2++) {
                int k3 = (sum - k1 - 2 * k2) / 3;

                if (sum != k1 + 2 * k2 + 3 * k3) continue;

                int length = k1 + k2 + k3;

                final BigInteger k3Combinations = combinations[length][k3];
                final BigInteger k2Combinations = combinations[length - k3][k2];
                final BigInteger k1Combinations = pow(new BigInteger(String.valueOf(k1)), TWO);

                final BigInteger currentResult = k1Combinations.multiply(k2Combinations).multiply(k3Combinations);

                totalNumbersCount = totalNumbersCount.add(currentResult);
            }
        }
        return totalNumbersCount;
    }

    private static BigInteger[][] countCombinations(int n, int k) {

        final BigInteger[][] result = new BigInteger[n][k];
        for(int i = 0; i < n; i++){
            result[i][0] = ONE;
            result[i][i] = ONE;
        }

        for(int ni = 1; ni < n; ni++){
            for(int ki = 1; ki < ni; ki++){
                result[ni][ki] = result[ni-1][ki].add(result[ni-1][ki-1]);
            }
        }

        return result;
    }

    private static BigInteger pow(BigInteger num, BigInteger pow) {

        BigInteger res = ONE;
        while(num.compareTo(BigInteger.ZERO) > 0){
            if(num.mod(TWO).equals(ONE)){
                res = res.multiply(pow);
            }
            num = num.divide(TWO);
            pow = pow.multiply(pow);
        }
        return res;
    }

}
