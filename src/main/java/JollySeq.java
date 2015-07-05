import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class JollySeq {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;

        main:
        while(null != (str = br.readLine())){
            if(str.trim().length() == 0)continue;
            String [] ps = str.trim().split("\\s+");

            Set<Integer> diff = new HashSet<Integer>();
            int n = Integer.MAX_VALUE;
            for(int i = 0; i < ps.length-1 && i-1 < n-1; i++){
                if(i == 0){
                    n = Integer.parseInt(ps[0]);
                    continue;
                }
                int a = Integer.parseInt(ps[i]);
                int b = Integer.parseInt(ps[i+1]);
                diff.add(Math.abs(a - b));
            }
            //System.out.println(Arrays.toString(diff.toArray(new Integer[0])));
            
            for(Integer i = 1; i < n; i++){
                if(!diff.contains(i)){
                    System.out.println("Not jolly");
                    continue main;
                } else {
                    diff.remove(i);
                }
            }
            //System.out.println(Arrays.toString(diff.toArray(new Integer[0])));
            if(diff.isEmpty()){
                System.out.println("Jolly");
            } else {
                System.out.println("Not jolly");
            }
            
        }
    }

}
