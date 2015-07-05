import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class P100 {

    private static long[]dp = new long[1000002];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        
        dp[1] = 1;
        for(int i = 2; i < dp.length; i++){
            dp[i] = solve(i);
        }
         
        while(null != (str = br.readLine())){
            if(str.trim().length() == 0)continue;
            String [] ps = str.trim().split("\\s+");
            int a = Integer.parseInt(ps[0]);
            int b = Integer.parseInt(ps[1]);

            int a1 = Math.min(a, b);
            int b1 = Math.max(a, b);

            long max = -1;
            for(int i = a1; i <= b1; i++){
                if(dp[i] == 0){
                    dp[i] = solve(i);
                }
                if(dp[i] > max){
                    max = dp[i];
                }
            }
            System.out.println(a + " " + b + " " + max);
        }
    }

    private static long solve(long n) {
        if(n < dp.length && dp[(int)n] != 0){
            return dp[(int)n];
        }
        long res;
        if(n % 2 == 0){
            res = solve(n/2) + 1;
        } else {
            res = solve(3*n + 1) + 1;
        }
        if(n < dp.length && dp[(int)n] == 0){
            dp[(int)n] = res;
        }
        return res;
    }
}
