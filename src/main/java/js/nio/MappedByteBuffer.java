package js.nio;

public class MappedByteBuffer extends ByteBuffer {

    protected MappedByteBuffer(byte[] int8array, int off, int pos, int lim, int cap) {
        super(int8array, -1, pos, lim, cap, off);
    }

}
