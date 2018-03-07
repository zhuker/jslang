package js.lang;

import static org.stjs.javascript.JSCollections.$map;

import org.stjs.javascript.Array;
import org.stjs.javascript.Date;
import org.stjs.javascript.Map;
import org.stjs.javascript.annotation.STJSBridge;
import org.stjs.javascript.annotation.ServerSide;
import org.stjs.javascript.typed.TypedArray;

import js.io.InputStream;
import js.io.PrintStream;

public class System {

    public final static PrintStream out = new JSConsolePrintStream();
    public final static PrintStream err = new JSConsolePrintStream();
//    public final static InputStream in = null;

//    public static void arraycopy(Object src, int srcPos, Object dst, int dstPos, int len) {
//        TypedArray _src = (TypedArray) src;
//        TypedArray _dst = (TypedArray) dst;
//        _dst.set(_src.subarray(srcPos, srcPos+len), dstPos);
//    }
    
    @NeedsSpeedOptimization
    public static void arraycopy(Object src, int srcPos, Object dst, int dstPos, int len) {
        Array _src = (Array) src;
        Array _dst = (Array) dst;
        int j = dstPos;
        int _lim = srcPos + len;
        for (int i = srcPos; i < _lim; i++) {
            _dst.$set(j++, _src.$get(i));
        }
    }


    public static void exit(int i) {
        throw new RuntimeException("TODO");
    }

    private final static Map<String, String> SystemProperties = $map();

    public static String getProperty(String string) {
        return SystemProperties.$get(string);
    }

    @NeedsSpeedOptimization
    public static long currentTimeMillis() {
        return (long) Date.now();
    }

    public static int identityHashCode(Object obj) {
        throw new RuntimeException("TODO");
    }

    public static Object console() {
        //TODO: 
        return null;
    }

}
