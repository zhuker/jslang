package js.lang.reflect;

import static org.stjs.javascript.JSCollections.$array;

import org.stjs.javascript.annotation.Namespace;

@Namespace("jslang.reflect")
public class Array {

    public static Object[] newInstance(Class<?> componentType, int len) {
        Object o;
        if (len == 0) {
            o = $array();
        } else {
            o = new org.stjs.javascript.Array<>(len);
        }
        return (Object[]) o;
    }

    public static int getLength(Object obj) {
        throw new RuntimeException("TODO Array.getLength");
    }

    public static Object get(Object obj, int i) {
        throw new RuntimeException("TODO Array.get");
    }

}
