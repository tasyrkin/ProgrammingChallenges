import java.util.Arrays;

/**
 * @author  tasyrkin
 * @since   2014/01/08
 */
public class RemoveDups {

    public static void main(final String[] args) {
        int[] arr = {1};

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        int back = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                arr[++back] = arr[i];
            }
        }

        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 0, back + 1)));
    }
}
