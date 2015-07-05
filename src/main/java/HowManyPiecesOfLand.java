import java.math.*;
import java.util.*;
/**
 * Wrong solution
 */
public class HowManyPiecesOfLand {
	public static void main(String[] args){
		final Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i = 0; i < n; i++){
			long num = sc.nextLong()-1;
			BigInteger res = new BigInteger("1");
			BigInteger pow = new BigInteger("2");
			long step = 0;
			while(num > 0){
				if(num % 2 == 1){
					res = res.multiply(pow);
				}
				num /= 2;
				pow = pow.multiply(pow);
			}
			System.out.println(res.toString());
		}
	}
}
