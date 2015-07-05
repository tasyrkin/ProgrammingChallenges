import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Hartals {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        
        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());
            int p = Integer.parseInt(br.readLine());
            Set<Integer> hartals = new HashSet<Integer>();
            for(int i = 0; i < p; i++){
                int dist = Integer.parseInt(br.readLine());
                int day = dist;
                while(day <= n){
                    if((day+1)%7 != 0 && day % 7 != 0){
                        hartals.add(day);
                    }
                    day += dist;
                }
            }
            System.out.println(hartals.size());
            
        }
    }

}
