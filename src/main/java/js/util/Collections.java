package js.util;

import js.util.Arrays;
import js.util.Comparator;
import js.util.ListIterator;

import js.util.List;

import js.lang.NeedsSpeedOptimization;
import js.util.ArrayList;

public class Collections {
    @NeedsSpeedOptimization
    public static <T> List<T> singletonList(T o) {
        return Arrays.asList(o);
    }

    public static <T> List<T> synchronizedList(List<T> list) {
        return list;
    }

    public static <T> void sort(List<T> list, Comparator<T> comparator) {
        Object[] a = list.toArray(new Object[0]);
        Arrays.sort(a, comparator);
        ListIterator<T> it = list.listIterator();
        for (int j = 0; j < a.length; j++) {
            Object e = a[j];
            it.next();
            it.set((T) e);
        }
    }

    @NeedsSpeedOptimization
    public static <T> List<T> emptyList() {
        return new ArrayList<T>();
    }

}
