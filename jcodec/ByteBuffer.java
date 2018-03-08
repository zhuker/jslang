package js.nio;

import static org.stjs.javascript.Global.console;

import org.stjs.javascript.annotation.Native;
import org.stjs.javascript.annotation.STJSBridge;
import org.stjs.javascript.typed.ArrayBuffer;
import org.stjs.javascript.typed.DataView;
import org.stjs.javascript.typed.Int8Array;

import js.lang.NeedsSpeedOptimization;

@NeedsSpeedOptimization
public class ByteBuffer {
    private byte[] hb;
    final int offset;
    private int pos;
    private int lim;
    private int cap;
    private int _mark;
    boolean bigEndian = true;
    boolean littleEndian = false;
    private DataView data;
    private Int8Array arr;

    protected ByteBuffer(byte[] int8array, int mark, int pos, int lim, int cap, int off) {
        this.hb = int8array;
        arr = (Int8Array) (Object) hb;
        ArrayBuffer buf = arr.buffer;
        this.data = new DataView(buf, off, cap);
        this.offset = off;
        this._mark = -1;
        this.pos = pos;
        this.lim = lim;
        this.cap = cap;
    }

    public int position() {
        return pos;
    }

    public ByteBuffer position(int newPosition) {
        return setPosition(newPosition);
    }

    public ByteBuffer setPosition(int newPosition) {
        if ((newPosition > lim) || (newPosition < 0))
            throw new js.lang.IllegalArgumentException("wrong position");
        pos = newPosition;
        if (_mark > pos)
            _mark = -1;
        return this;
    }

    public ByteBuffer duplicate() {
        return new ByteBuffer(hb, this.markValue(), this.position(), this.limit(), this.capacity(), offset);
    }

    final int markValue() { // package-private
        return _mark;
    }

    /**
     * Returns the number of elements between the current position and the
     * limit.
     *
     * @return The number of elements remaining in this buffer
     */
    public final int remaining() {
        return lim - pos;
    }

    public byte get(int idx) {
        return getAt(idx);
    }
    public byte get() {
        return hb[ix(nextGetIndex())];
    }

    /**
     * Checks the current position against the limit, throwing a
     * {@link BufferUnderflowException} if it is not smaller than the limit, and
     * then increments the position.
     *
     * @return The current position value, before it is incremented
     */
    final int nextGetIndex() { // package-private
        //        if (pos >= lim)
        //            throw new BufferUnderflowException();
        return pos++;
    }

    static void checkBounds(int off, int len, int size) { // package-private
        if ((off | len | (off + len) | (size - (off + len))) < 0)
            throw new js.lang.IndexOutOfBoundsException();
    }

    /**
     * Checks the given index against the limit, throwing an
     * {@link IndexOutOfBoundsException} if it is not smaller than the limit or
     * is smaller than zero.
     */
    final int checkIndex(int i) {
        if ((i < 0) || (i >= lim))
            throw new js.lang.IndexOutOfBoundsException("" + i);
        return i;
    }

    final int checkIndex2(int i, int nb) {
        if ((i < 0) || (nb > lim - i))
            throw new js.lang.IndexOutOfBoundsException();
        return i;
    }

    public boolean hasRemaining() {
        return pos < lim;
    }

    public ByteBuffer putInt(int x) {
        Bits.putInt(this, ix(_nextPutIndex(4)), x, bigEndian);
        return this;
    }

    protected int ix(int i) {
        return i + offset;
    }

    final int _nextPutIndex(int nb) { // package-private
        if (lim - pos < nb)
            throw new BufferOverflowException();
        int p = pos;
        pos += nb;
        return p;
    }

    public ByteBuffer flip() {
        lim = pos;
        pos = 0;
        _mark = -1;
        return this;
    }

    public static ByteBuffer allocate(int length) {
        return new ByteBuffer(new byte[length], -1, 0, length, length, 0);
    }

    public ByteBuffer slice() {
        return new ByteBuffer(hb, -1, 0, this.remaining(), this.remaining(), this.position() + offset);
    }

    public int limit() {
        return lim;
    }

    public ByteBuffer setLimit(int newLimit) {
        if ((newLimit > cap) || (newLimit < 0))
            throw new js.lang.IllegalArgumentException(newLimit + ">" + cap);
        lim = newLimit;
        if (pos > lim)
            pos = lim;
        if (_mark > lim)
            _mark = -1;
        return this;
    }

    public ByteBuffer put(byte b) {
        hb[ix(nextPutIndex())] = b;
        return this;
    }

    public ByteBuffer putAt(int index, byte b) {
        hb[ix(checkIndex(index))] = b;
        return this;
    }

    public final ByteBuffer putArr(byte[] src) {
        return put3(src, 0, src.length);
    }

    public ByteBuffer putBuf(ByteBuffer src) {
        int n = src.remaining();
        if (n > remaining())
            throw new BufferOverflowException();
        Int8Array _dst = arr;
        Int8Array _src = src.arr;
        int dstIdx = ix(position());
        int srcIdx = src.ix(src.position());

        _dst.subarray(dstIdx, dstIdx + n).set(_src.subarray(srcIdx, srcIdx + n), 0);

        src.setPosition(src.position() + n);
        setPosition(position() + n);
        return this;
    }

    public ByteBuffer put3(byte[] src, int off, int len) {
        checkBounds(off, len, src.length);
        if (len > remaining())
            throw new BufferOverflowException();
        int end = off + len;
        for (int i = off; i < end; i++)
            this.put(src[i]);
        return this;
    }

    final int nextPutIndex() {
        if (pos >= lim)
            throw new js.nio.BufferOverflowException();
        return pos++;
    }

    public ByteBuffer mark() {
        _mark = pos;
        return this;
    }

    public ByteBuffer reset() {
        int m = _mark;
        if (m < 0)
            throw new js.lang.IllegalStateException();
        pos = m;
        return this;
    }

    public ByteBuffer putLong(long x) {
        Bits.putLong(this, ix(_nextPutIndex(8)), x, bigEndian);
        return this;
    }

    public long getLong() {
        throw new RuntimeException("TODO getLong");
    }

    public ByteBuffer clear() {
        pos = 0;
        lim = cap;
        _mark = -1;
        return this;
    }

    public ByteOrder getOrder() {
        return bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
    }

    public ByteBuffer order(ByteOrder bo) {
        bigEndian = (bo == ByteOrder.BIG_ENDIAN);
        littleEndian = !bigEndian;
        return this;
    }

    public int capacity() {
        return cap;
    }

    public IntBuffer asIntBuffer() {
        throw new RuntimeException("TODO asIntBuffer");
    }

    public byte[] array() {
        return hb;
    }

    public static ByteBuffer wrap(Object... arguments) {
        if (arguments.length == 1) {
            byte[] buf = (byte[]) arguments[0];
            return new ByteBuffer(buf, -1, 0, buf.length, buf.length, 0);
        } else {
            console.error("arguments", arguments);
            throw new RuntimeException("TODO ByteBuffer.wrap " + arguments.length);
        }
    }

    public double getDouble() {
        throw new RuntimeException("TODO getDouble");
    }

    public float getFloat() {
        throw new RuntimeException("TODO getFloat");
    }

    public int arrayOffset() {
        return offset;
    }

    public boolean hasArray() {
        return true;
    }

    public int getInt() {
        return data.getInt32(_nextGetIndex(4), littleEndian);
    }

    public int getIntAt(int i) {
        return data.getInt32(checkIndex2(i, 4), littleEndian);
    }

    final int _nextGetIndex(int nb) { // package-private
        if (lim - pos < nb)
            throw new BufferUnderflowException();
        int p = pos;
        pos += nb;
        return p;
    }

    public char getChar() {
        throw new RuntimeException("TODO getChar");
    }

    public void putFloat(float f) {
        throw new RuntimeException("TODO putFloat");
    }

    public void putDouble(double value) {
        throw new RuntimeException("TODO putDouble");
    }

    public ByteBuffer putShort(Object... arguments) {
        if (arguments.length == 1) {
            short x = (short) arguments[0];
            Bits.putShort(this, ix(_nextPutIndex(2)), x, bigEndian);
            return this;
        }
        console.error("TODO putShort", arguments);
        throw new RuntimeException("TODO putShort");
    }

    public short getShort() {
        return Bits.getShort(this, ix(_nextGetIndex(2)), bigEndian);
    }

    public short getShortAt(int idx) {
        throw new RuntimeException("TODO ByteBuffer.getShortAt ");
    }

    byte _get(int i) { // package-private
        return hb[i];
    }

    void _put(int i, byte b) {
        hb[i] = b;
    }

    public byte getAt(int idx) {
        return hb[ix(checkIndex(idx))];
    }

    public ByteBuffer getBuf(byte[] dst) {
        int length = dst.length;
        checkBounds(offset, length, dst.length);
        if (length > remaining())
            throw new BufferUnderflowException();
        int end = offset + length;
        for (int i = offset; i < end; i++)
            dst[i] = get();
        return this;
    }

    public ByteBuffer getBuf3(byte[] b, int off, int toRead) {
        throw new RuntimeException("TODO ByteBuffer.getBuf3");
    }

    public static ByteBuffer allocateDirect(int capacity) {
        return new ByteBuffer(new byte[capacity], -1, 0, capacity, capacity, 0);
    }

    public boolean isDirect() {
        return false;
    }

    @Override
    public String toString() {
        return "ByteBuffer [pos=" + pos + ", lim=" + lim + ", cap=" + cap + "]";
    }

    public ByteBuffer put(byte[] src) {
        return putArr(src);
    }

    public ByteBuffer put(ByteBuffer src) {
        return putBuf(src);
    }

    public ByteBuffer get(byte[] b, int off, int toRead) {
        return getBuf3(b, off, toRead);
    }

    public ByteBuffer get(byte[] dst) {
        return getBuf(dst);
    }

    public int getInt(int i) {
        return getIntAt(i);
    }

    public ByteOrder order() {
        return getOrder();
    }

    public ByteBuffer put(int i, byte b) {
        return putAt(i, b);
    }

    public ByteBuffer put(byte[] src, int off, int len) {
        return put3(src, off, len);
    }
    
    public ByteBuffer limit(int newLimit) {
        return setLimit(newLimit);
    }
}
