package js.util;

import static org.stjs.javascript.Global.console;
import static org.stjs.javascript.JSObjectAdapter.$constructor;
import static org.stjs.javascript.JSObjectAdapter.$get;

import js.util.DualPivotQuicksort;

import org.stjs.javascript.Array;
import org.stjs.javascript.Global;
import org.stjs.javascript.JSObjectAdapter;
import org.stjs.javascript.SortFunction;
import org.stjs.javascript.typed.Int32Array;

public class Arrays {

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

    public static void fill(Object... arguments) {
        if (arguments.length == 2) {
            Array a = (Array) arguments[0];
            Object val = (Object) arguments[1];
            for (int i = 0, len = a.$length(); i < len; i++)
                a.$set(i, val);
        } else if (arguments.length == 4) {
            Array a = (Array) arguments[0];
            int fromIndex = (int) arguments[1];
            int toIndex = (int) arguments[2];
            Object val = arguments[3];
            rangeCheck(a.$length(), fromIndex, toIndex);
            for (int i = fromIndex; i < toIndex; i++)
                a.$set(i, val);
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

    public static int hashCode(int[] a) {
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
}
