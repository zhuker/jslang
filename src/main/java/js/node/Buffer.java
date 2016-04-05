package js.node;

import org.stjs.javascript.annotation.Native;
import org.stjs.javascript.annotation.STJSBridge;
import org.stjs.javascript.typed.ArrayBuffer;

@STJSBridge
public class Buffer {
    @Native
    public static boolean isBuffer(Object o) {
        return false;
    }

    public int length;

    @Native
    public Buffer(ArrayBuffer arrayBuffer) {
        throw new RuntimeException("TODO new.Buffer");
    }

    @Native
    public Buffer(int capacity) {
        throw new RuntimeException("TODO new.Buffer");
    }

    @Native
    public String toString(String encoding, int start, int end) {
        throw new RuntimeException("TODO Buffer.toString");
    }

    @Native
    public int readInt32BE(int offset) {
        throw new RuntimeException("TODO Buffer.readInt32BE");
    }

    @Native
    public long readUInt32LE(int pos) {
        throw new RuntimeException("TODO Buffer.readUInt32LE");
    }

    @Native
    public Buffer slice(int start, int end) {
        throw new RuntimeException("TODO Buffer.slice");
    }

    @Native
    public void fill(int i) {
        throw new RuntimeException("TODO Buffer.fill");
    }

    @Native
    public short readInt16LE(int offset) {
        throw new RuntimeException("TODO Buffer.readInt16LE");
    }

    @Native
    public void writeInt8(byte val, int offset) {
        throw new RuntimeException("TODO Buffer.writeInt8");
    }

    @Native
    public static Buffer from(ArrayBuffer buffer, int offset, int length) {
        throw new RuntimeException("TODO Buffer.from");
    }
}
