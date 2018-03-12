package js.util;

import org.stjs.javascript.annotation.Native;

public class Random {

    @Native
    public Random() {
    }

    public Random(int i) {
    }

    public long nextLong() {
        throw new RuntimeException("TODO Random.nextLong");
    }

    public int nextInt(int i) {
        throw new RuntimeException("TODO Random.nextInt");
    }

    public void nextBytes(byte[] dst) {
        for (int i = 0; i < dst.length; i++) {
            dst[i] = (byte) (Math.random() * 255);
        }
    }

}
