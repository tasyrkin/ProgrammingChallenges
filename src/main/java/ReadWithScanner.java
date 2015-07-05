import java.util.Scanner;

/**
 * @author: tasyrkin
 * @since: 2014/01/04
 */
public class ReadWithScanner {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = Integer.parseInt(sc.nextLine());
        System.out.println(n);
        
        while(n-- > 0){
            System.out.println(sc.nextLine());
        }
        
        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
    } 
}
