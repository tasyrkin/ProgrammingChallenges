/**
 * @author  tasyrkin
 * @since   2014/01/08
 */
public class FindIndex {

    public static int findIndex(final int N) {
        long num = 10;
        int classes = 0;
        int res = 0;
        int calculated = 0;
        while (num <= N - 1) {
            ++classes;
            calculated += 9 * (num / 10);
            res += 9 * (num / 10) * classes;
            num *= 10;
        }

        res += (N - 1 - calculated) * (classes + 1);

        // System.out.println(res);
        return res;
    }

    public static void main(final String[] args) {

        System.out.println(findIndex(1));
        System.out.println(findIndex(5));
        System.out.println(findIndex(11));
        System.out.println(findIndex(20));
        System.out.println(findIndex(100));
        System.out.println(findIndex(999));
        System.out.println(findIndex(4780));
    }

}
