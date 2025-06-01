import java.util.*;
public class ArrayOperations {
    /**
     * Delete the value at the given position in the argument array, shifting
     * all the subsequent elements down, and storing a 0 as the last element of
     * the array.
     */
    public static void delete(int[] values, int pos) {
        if (pos < 0 || pos >= values.length) {
            return;
        } else {
            for (int i = pos; i < values.length - 1; i++){
                values[i] = values[i+1];
            }
            values[values.length - 1] = 0;
        }

    }

    /**
     * Insert newInt at the given position in the argument array, shifting all
     * the subsequent elements up to make room for it. The last element in the
     * argument array is lost.
     */
    public static void insert(int[] values, int pos, int newInt) {
        if (pos < 0 || pos >= values.length) {
            return;
        }
//        int[] r = Arrays.copyOf(values, values.length);
//        for (int i = pos + 1; i < values.length; i++){
//            values[i] = r[i - 1];
//        }
//        values[pos] = newInt;
        for (int i = values.length -1; i > pos; i--) {
            values[i] = values[i-1];
        }
        values[pos] = newInt;
    }

    public static int[] catenate(int[] A, int[] B) {
        int[] array = new int[A.length + B.length];
        for (int i = 0; i < A.length; i++) {
            array[i] = A[i];
        }
        for (int i = A.length; i < B.length + A.length; i++) {
            array[i] = B[i - A.length];
        }
        return array;
    }
}
