package js.junit;

import static org.stjs.javascript.Global.console;

import org.stjs.javascript.Array;
import org.stjs.javascript.JSGlobal;

public class Assert {
    public static void assertNull(Object... arguments) {
        switch (arguments.length) {
        case 1:
            if (arguments[0] != null) {
                throw new RuntimeException("assertion failed");
            }
            break;

        default:
            console.error("arguments", arguments);
            throw new RuntimeException("TODO assertNull " + arguments.length);
        }
    }
    
    public static void assertNotNull(Object... arguments) {
        switch (arguments.length) {
        case 1:
            if (arguments[0] == null) {
                throw new RuntimeException("assertion failed");
            }
            break;

        default:
            console.error("arguments", arguments);
            throw new RuntimeException("TODO assertNotNull " + arguments.length);
        }
    }
    public static void assertFalse(Object... arguments) {
        switch (arguments.length) {
        case 1:
            if ((Boolean) arguments[0] != false) {
                throw new RuntimeException("assertion failed");
            }
            break;

        default:
            console.error("arguments", arguments);
            throw new RuntimeException("TODO assertNull " + arguments.length);
        }
    }

    public static void assertArrayEquals(Object... arguments) {
        Array expected = (Array) (arguments.length == 2 ? arguments[0] : arguments[1]);
        Array actual = (Array) (arguments.length == 2 ? arguments[1] : arguments[2]);
        assertEquals(expected.$length(), actual.$length());
        for (int i = 0; i < expected.$length(); i++) {
            Object e = expected.$get(i);
            Object a = actual.$get(i);
            if (e != a) {
                throw new RuntimeException("assertion failed at " + i + " " + e + " != " + a);
            }
        }
    }

    public static void assertTrue(Object... arguments) {
        switch (arguments.length) {
        case 1:
            if (!(Boolean) arguments[0]) {
                throw new RuntimeException("assertion failed");
            }
            return;
        case 2:
            if (!(Boolean) arguments[1]) {
                throw new RuntimeException("assertion failed " + arguments[0]);
            }
            return;
        default:
            throw new RuntimeException("TODO assertTrue " + arguments);
        }
    }

    public static void assertEquals(Object... arguments) {
        switch (arguments.length) {
        case 2:
            boolean eq = arguments[0] == arguments[1] || arguments[0].equals(arguments[1]);
            if (!eq) {
                throw new RuntimeException("assertion failed " + arguments[0] + " != " + arguments[1]);
            }
            return;
        case 3:
            if (JSGlobal.typeof(arguments[0]) == "number") {
                //assertEquals(double expected, double actual, double delta)
                double expected = (double) arguments[0];
                double actual = (double) arguments[1];
                double delta = (double) arguments[2];
                if (delta < Math.abs(expected - actual)) {
                    throw new RuntimeException(
                            "assertion failed " + arguments[0] + " " + arguments[1] + " != " + arguments[2]);
                }
                return;
            } else if (JSGlobal.typeof(arguments[0]) == "string") {
                //    static public void assertEquals(String message, Object expected, Object actual) {
                String msg = (String) arguments[0];
                boolean ok = arguments[1] == arguments[2] || arguments[1].equals(arguments[2]);
                if (!ok) {
                    throw new RuntimeException(
                            "assertion failed " + arguments[0] + " " + arguments[1] + " != " + arguments[2]);
                }
                return;
            }
            break;
        default:
            break;
        }
        console.log("arguments", arguments);
        throw new RuntimeException("TODO jsunit Assert.assertEquals "+arguments);
    }

}
