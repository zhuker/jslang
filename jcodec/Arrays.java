package js.util;

import static org.stjs.javascript.Global.console;
import static org.stjs.javascript.JSObjectAdapter.$constructor;
import static org.stjs.javascript.JSObjectAdapter.$get;

import js.util.DualPivotQuicksort;

import org.stjs.javascript.Array;
import org.stjs.javascript.Global;
import org.stjs.javascript.JSObjectAdapter;
import org.stjs.javascript.SortFunction;
import org.stjs.javascript.annotation.Native;
import org.stjs.javascript.typed.Int32Array;

public class Arrays {

    //inline this method
    public static void fill(Object arr, int fromIndex, int toIndex, Object val) {
        fillRange(arr, fromIndex, toIndex, val);
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of ints are
     * <i>equal</i> to one another. Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal. In other words, two arrays are
     * equal if they contain the same elements in the same order. Also, two
     * array references are considered equal if both are <tt>null</tt>.
     * <p>
     *
     * @param a
     *            one array to be tested for equality
     * @param a2
     *            the other array to be tested for equality
     * @return <tt>true</tt> if the two arrays are equal
     */
    public static boolean arrayEquals(Object ar1, Object ar2) {
        int[] a = (int[]) ar1;
        int[] a2 = (int[]) ar2;
        if (a == a2)
            return true;
        if (a == null || a2 == null)
            return false;

        int length = a.length;
        if (a2.length != length)
            return false;

        for (int i = 0; i < length; i++)
            if (a[i] != a2[i])
                return false;

        return true;
    }

    public static <T> List<T> asList(T... arguments) {
        if (arguments.length == 1 && $get($constructor(arguments[0]), "name") == "Array") {
            Object o = arguments[0];
            return ArrayList.fromArray((Array<T>) o);
        } else if (arguments.length > 0) {
            Object o = arguments;
            return ArrayList.fromArray((Array<T>) o);
        } else {
            return new ArrayList<>();
        }
    }

    public static void fill(Object arr, Object val) {
        JSObjectAdapter.$js("arr.fill(val)");
    }
    
    public static void fillRange(Object arr, int fromIndex, int toIndex, Object val) {
        Array a = (Array) arr;
        rangeCheck(a.$length(), fromIndex, toIndex);
        JSObjectAdapter.$js("a.fill(val, fromIndex, toIndex);");
    }

    public static void _fill(Object... arguments) {
        if (arguments.length == 2) {
            Array a = (Array) arguments[0];
            Object val = (Object) arguments[1];
            JSObjectAdapter.$js("a.fill(val);");
        } else if (arguments.length == 4) {
            Array a = (Array) arguments[0];
            int fromIndex = (int) arguments[1];
            int toIndex = (int) arguments[2];
            rangeCheck(a.$length(), fromIndex, toIndex);
            Object val = arguments[3];
            JSObjectAdapter.$js("a.fill(val, fromIndex, toIndex);");
        } else {
            throw new RuntimeException("TODO fill " + arguments.length);
        }
    }

    public static void sort(Object... arguments) {
        if (arguments.length == 1 && Int32Array.class == $constructor(arguments[0])) {
            int[] a = (int[]) arguments[0];
            DualPivotQuicksort.sortInt6(a, 0, a.length - 1, null, 0, 0);
        } else if (arguments.length == 2) {
            Array a = (Array) arguments[0];
            Comparator cmp = (Comparator) arguments[1];
            a.sort((_a, _b) -> cmp.compare(_a, _b));
        } else {
            Global.console.log("arguments", arguments);
            throw new RuntimeException("TODO sort " + arguments);
        }
    }

    public static int hashCode(Object array) {
        throw new RuntimeException("TODO");
    }

    public static int binarySearch(Object array, Object key) {
        throw new RuntimeException("TODO");
    }

    /**
     * Checks that {@code fromIndex} and {@code toIndex} are in the range and
     * throws an exception if they aren't.
     */
    private static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new js.lang.IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {
            throw new js.lang.ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > arrayLength) {
            throw new js.lang.ArrayIndexOutOfBoundsException(toIndex);
        }
    }

    public static <T> T copyOf(T original, int newLength) {
        throw new RuntimeException("TODO Arrays.copyOf");
    }

    public static <T> T copyOfRange(T original, int from, int to) {
        throw new RuntimeException("TODO Arrays.copyOfRange");
    }

    public static String arrayToString(Object arr) {
        throw new RuntimeException("TODO Arrays.toString");
    }


}
