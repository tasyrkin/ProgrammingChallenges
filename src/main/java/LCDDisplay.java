import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class LCDDisplay {

    
    private static final String BORDERS_WITH_COVER = " * ";
    private static final String BORDERS_WITHOUT_COVER = " ^ ";
    private static final String MID_WITH_LEFT = "|^ ";
    private static final String MID_WITH_RIGHT = " ^|";
    private static final String MID_WITH_BOTH = "|^|";

    public static void main(String[] args) throws IOException {
        
        

        Map<Integer, String>[]patterns = new HashMap[5];
        int cnt = 0;
        patterns[cnt] = new HashMap<Integer, String>();
        patterns[cnt].put(0, BORDERS_WITH_COVER);
        patterns[cnt].put(1, BORDERS_WITHOUT_COVER);
        patterns[cnt].put(2, BORDERS_WITH_COVER);
        patterns[cnt].put(3, BORDERS_WITH_COVER);
        patterns[cnt].put(4, BORDERS_WITHOUT_COVER);
        patterns[cnt].put(5, BORDERS_WITH_COVER);
        patterns[cnt].put(6, BORDERS_WITH_COVER);
        patterns[cnt].put(7, BORDERS_WITH_COVER);
        patterns[cnt].put(8, BORDERS_WITH_COVER);
        patterns[cnt].put(9, BORDERS_WITH_COVER);

        cnt++;
        patterns[cnt] = new HashMap<Integer, String>();
        patterns[cnt].put(0, MID_WITH_BOTH);
        patterns[cnt].put(1, MID_WITH_RIGHT);
        patterns[cnt].put(2, MID_WITH_RIGHT);
        patterns[cnt].put(3, MID_WITH_RIGHT);
        patterns[cnt].put(4, MID_WITH_BOTH);
        patterns[cnt].put(5, MID_WITH_LEFT);
        patterns[cnt].put(6, MID_WITH_LEFT);
        patterns[cnt].put(7, MID_WITH_RIGHT);
        patterns[cnt].put(8, MID_WITH_BOTH);
        patterns[cnt].put(9, MID_WITH_BOTH);

        cnt++;
        patterns[cnt] = new HashMap<Integer, String>();
        patterns[cnt].put(0, BORDERS_WITHOUT_COVER);
        patterns[cnt].put(1, BORDERS_WITHOUT_COVER);
        patterns[cnt].put(2, BORDERS_WITH_COVER);
        patterns[cnt].put(3, BORDERS_WITH_COVER);
        patterns[cnt].put(4, BORDERS_WITH_COVER);
        patterns[cnt].put(5, BORDERS_WITH_COVER);
        patterns[cnt].put(6, BORDERS_WITH_COVER);
        patterns[cnt].put(7, BORDERS_WITHOUT_COVER);
        patterns[cnt].put(8, BORDERS_WITH_COVER);
        patterns[cnt].put(9, BORDERS_WITH_COVER);

        cnt++;
        patterns[cnt] = new HashMap<Integer, String>();
        patterns[cnt].put(0, MID_WITH_BOTH);
        patterns[cnt].put(1, MID_WITH_RIGHT);
        patterns[cnt].put(2, MID_WITH_LEFT);
        patterns[cnt].put(3, MID_WITH_RIGHT);
        patterns[cnt].put(4, MID_WITH_RIGHT);
        patterns[cnt].put(5, MID_WITH_RIGHT);
        patterns[cnt].put(6, MID_WITH_BOTH);
        patterns[cnt].put(7, MID_WITH_RIGHT);
        patterns[cnt].put(8, MID_WITH_BOTH);
        patterns[cnt].put(9, MID_WITH_RIGHT);

        cnt++;
        patterns[cnt] = new HashMap<Integer, String>();
        patterns[cnt].put(0, BORDERS_WITH_COVER);
        patterns[cnt].put(1, BORDERS_WITHOUT_COVER);
        patterns[cnt].put(2, BORDERS_WITH_COVER);
        patterns[cnt].put(3, BORDERS_WITH_COVER);
        patterns[cnt].put(4, BORDERS_WITHOUT_COVER);
        patterns[cnt].put(5, BORDERS_WITH_COVER);
        patterns[cnt].put(6, BORDERS_WITH_COVER);
        patterns[cnt].put(7, BORDERS_WITHOUT_COVER);
        patterns[cnt].put(8, BORDERS_WITH_COVER);
        patterns[cnt].put(9, BORDERS_WITH_COVER);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;

        while(null != (str = br.readLine())){
            if(str.trim().length() == 0)continue;
            String [] ps = str.trim().split("\\s+");

            int s = Integer.parseInt(ps[0]);
            int n = Integer.parseInt(ps[1]);

            if(n == 0 && s == 0)break;

            StringBuffer spaces = new StringBuffer();
            for(int i = 0; i < s; i++){
                spaces.append(" ");
            }
            StringBuffer horiz = new StringBuffer();
            for(int i = 0; i < s; i++){
                horiz.append("-");
            }
            
            
            char[] nums = Integer.toString(n).toCharArray();
            
            for(int i = 0; i < 5; i++){
                if(i % 2 != 0){
                    for(int sj = 0; sj < s; sj++){
                        System.out.println(getString(patterns[i], spaces, horiz, nums));
                    }
                } else {
                    System.out.println(getString(patterns[i], spaces, horiz, nums));
                }
            }
            System.out.println();
        }
    }

    private static String getString(Map<Integer, String> pattern, StringBuffer spaces, StringBuffer horiz, char[] nums) {
        StringBuffer sb = new StringBuffer();
        for(int ni = 0; ni < nums.length; ni++){
            if(sb.length() > 0)sb.append(" ");
            sb.append(pattern.get(Integer.valueOf("" + nums[ni])).replace("^", spaces.toString()).replace("*", horiz.toString()));
        }
        return sb.toString();
    }

}
