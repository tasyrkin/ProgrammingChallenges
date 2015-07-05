import java.util.*;
import java.math.BigInteger;

class HowManyFibs {
	public static void main(String[] args) {
		final Scanner sc = new Scanner(System.in);

		List<BigInteger> bis = new LinkedList<BigInteger>();

		BigInteger prev = new BigInteger("1");
		BigInteger curr = new BigInteger("2");

		bis.add(prev);
		bis.add(curr);

		while(curr.toString().length() < 102){
			BigInteger next = curr.add(prev);
			bis.add(next);
			prev = curr;
			curr = next;
		}

		BigInteger[] arr = bis.toArray(new BigInteger[0]);

		BigInteger a = new BigInteger(sc.next());
		BigInteger b = new BigInteger(sc.next());

		while (!(a.equals(BigInteger.ZERO) && b.equals(BigInteger.ZERO))) {


			int aidx = Arrays.binarySearch(arr, a);
			if(aidx < 0){
				aidx = (aidx + 1)*-1;
			}
			int bidx = Arrays.binarySearch(arr, b);
			boolean binclude = bidx >= 0;
			if(bidx < 0)bidx = (bidx + 1)*-1;


			long result = bidx - aidx + 1 - (binclude ? 0 : 1);

			System.out.println(result);
		
			a = new BigInteger(sc.next());
			b = new BigInteger(sc.next());
		}


	}

}
