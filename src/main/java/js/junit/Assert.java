package js.junit;

public class Assert {
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
        throw new RuntimeException("TODO jsunit Assert.assertEquals");
    }

}
