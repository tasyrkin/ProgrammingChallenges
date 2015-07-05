import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Minesweeper {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;

        int field = 0;
        while(null != (str = br.readLine())){
            if(str.trim().length() == 0)continue;
            String [] ps = str.trim().split("\\s+");
            field++;
            
            int n = Integer.parseInt(ps[0]);
            int m = Integer.parseInt(ps[1]);
            
            if(n == 0 || m == 0)break;
            
            char[][]a = new char[n][m];
            
            for(int i = 0; i < n; i++){
                while(null != (str = br.readLine())){
                    if(str.trim().length() == 0)continue;
                    a[i] = str.trim().toCharArray();    
                    break;
                }
                
            }
            
            if(field > 1)System.out.println();

            System.out.println("Field #" + field + ":");
            for(int i = 0; i < n; i++){
                StringBuffer sb = new StringBuffer();
                for(int j = 0; j < m; j++){
                    if(a[i][j] == '.'){
                        int cnt = 0;
                        for(int i1 = -1; i1 <= 1; i1++){
                            for(int j1 = -1; j1 <= 1; j1++){
                                if(isValid(i+i1, n) && isValid(j+j1, m)){
                                    char ch = a[i+i1][j+j1];
                                    if(ch == '*'){
                                        cnt++;
                                    }
                                }
                            }
                        }
                        sb.append(cnt);
                    } else{
                        sb.append(a[i][j]);
                    }
                }
                System.out.println(sb.toString());
            }
        }
    }

    private static boolean isValid(int i, int n) {
        return i >= 0 && i < n;
    }
}
