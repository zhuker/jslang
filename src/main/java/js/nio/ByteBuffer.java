package js.nio;

import static org.stjs.javascript.Global.console;
import static org.stjs.javascript.JSGlobal.typeof;

import org.stjs.javascript.Global;
import org.stjs.javascript.JSGlobal;
import org.stjs.javascript.JSObjectAdapter;

import js.lang.IntentionalHack;
import js.lang.NeedsSpeedOptimization;
import js.lang.System;

@NeedsSpeedOptimization
public class ByteBuffer {
    private byte[] hb;
    final int offset;
    private int pos;
    private int lim;
    private int cap;
    private int _mark;
    boolean bigEndian = true;

    protected ByteBuffer(byte[] int8array, int mark, int pos, int lim, int cap, int off) {
        this.hb = int8array;
        this.offset = off;
        this._mark = -1;
        this.pos = pos;
        this.lim = lim;
        this.cap = cap;
    }

    public int position(int... arguments) {
        if (arguments.length == 0) {
            return getPosition();
        } else if (arguments.length == 1) {
            int newPosition = arguments[0];
            if ((newPosition > lim) || (newPosition < 0))
                throw new js.lang.IllegalArgumentException("wrong position");
            pos = newPosition;
            if (_mark > pos)
                _mark = -1;
            return illegalCast(this);
        }
        throw new RuntimeException("TODO IntBuffer.position");
    }

    private int getPosition() {
        return pos;
    }

    @IntentionalHack
    private int illegalCast(ByteBuffer intBuffer) {
        Object o = intBuffer;
        return (int) o;
    }

    @IntentionalHack
    private byte illegalCastByte(ByteBuffer intBuffer) {
        Object o = intBuffer;
        return (byte) o;
    }

    public ByteBuffer duplicate() {
        return new ByteBuffer(hb, this.markValue(), this.getPosition(), this.limit(), this.capacity(), offset);
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

    public byte get(Object... arguments) {
        if (arguments.length == 0) {
            return hb[ix(nextGetIndex())];
        } else if (arguments.length == 1 && "number" == JSGlobal.typeof(arguments[0])) {
            int i = (int) arguments[0];
            return hb[ix(checkIndex(i))];
        } else if (arguments.length == 1 && "object" == JSGlobal.typeof(arguments[0])) {
            byte[] dst = (byte[]) arguments[0];
            int length = dst.length;
            checkBounds(offset, length, dst.length);
            if (length > remaining())
                throw new BufferUnderflowException();
            int end = offset + length;
            for (int i = offset; i < end; i++)
                dst[i] = get();
            return illegalCastByte(this);
        }
        Global.console.error("arguments", arguments);
        Global.console.error("arguments0", arguments[0]);
        Global.console.error("arguments0", JSGlobal.typeof(arguments[0]));
        Global.console.error("arguments0", JSObjectAdapter.$constructor(arguments[0]));
        throw new RuntimeException("TODO ByteBuffer.get " + arguments.length);
    }

    /**
     * Checks the current position against the limit, throwing a
     * {@link BufferUnderflowException} if it is not smaller than the limit, and
     * then increments the position.
     *
     * @return The current position value, before it is incremented
     */
    final int nextGetIndex() { // package-private
        if (pos >= lim)
            throw new BufferUnderflowException();
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
    final int checkIndex(int i) { // package-private
        if ((i < 0) || (i >= lim))
            throw new js.lang.IndexOutOfBoundsException("" + i);
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
        return new ByteBuffer(hb,
                -1,
                0,
                this.remaining(),
                this.remaining(),
                this.getPosition() + offset);
    }

    public int limit(int... arguments) {
        if (arguments.length == 0) {
            return lim;
        } else {
            int newLimit = arguments[0];
            if ((newLimit > cap) || (newLimit < 0))
                throw new js.lang.IllegalArgumentException("" + arguments[0]);
            lim = newLimit;
            if (pos > lim)
                pos = lim;
            if (_mark > lim)
                _mark = -1;
            return illegalCast(this);
        }
    }

    public ByteBuffer put(Object... arguments) {
        if (arguments.length == 1 && "number" == typeof(arguments[0])) {
            byte x = (byte) arguments[0];
            hb[ix(nextPutIndex())] = x;
        } else if (arguments.length == 1 && arguments[0] instanceof ByteBuffer) {
            ByteBuffer src = (ByteBuffer) arguments[0];
            ByteBuffer sb = (ByteBuffer) src;
            int n = sb.remaining();
            if (n > remaining())
                throw new BufferOverflowException();
            System.arraycopy(sb.hb, sb.ix(sb.position()), hb, ix(position()), n);
            sb.position(sb.position() + n);
            position(position() + n);
        } else if (arguments.length == 1 && "object" == typeof(arguments[0])) {
            byte[] src = (byte[]) arguments[0];
            return put3(src, 0, src.length);
        } else if (arguments.length == 3) {
            byte[] src = (byte[]) arguments[0];
            int off = (int) arguments[1];
            int len = (int) arguments[2];
            return put3(src, off, len);
        } else {
            console.error("arguments", arguments);
            throw new RuntimeException("TODO ByteBuffer.put " + arguments.length);
        }
        return this;
    }

    private ByteBuffer put3(byte[] src, int off, int len) {
        checkBounds(off, len, src.length);
        if (len > remaining())
            throw new BufferOverflowException();
        int end = off + len;
        for (int i = off; i < end; i++)
            this.put(src[i]);
        return this;
    }

    final int nextPutIndex() { // package-private
        if (pos >= lim)
            throw new js.nio.BufferOverflowException();
        return pos++;
    }

    public void mark() {
        throw new RuntimeException("TODO mark");
    }

    public void reset() {
        throw new RuntimeException("TODO reset");
    }

    public ByteBuffer putLong(long l) {
        console.error("ByteBuffer putLong");
        throw new RuntimeException("TODO putLong");
    }

    public long getLong() {
        throw new RuntimeException("TODO getLong");
    }

    public void clear() {
        throw new RuntimeException("TODO clear");
    }

    public Object order(ByteOrder... arguments) {
        if (arguments.length == 0) {
            return bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        } else {
            ByteOrder bo = arguments[0];
            bigEndian = (bo == ByteOrder.BIG_ENDIAN);
            return this;
        }
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

    public int getInt(Object... arguments) {
        if (arguments.length == 0) {
            return Bits.getInt(this, ix(_nextGetIndex(4)), bigEndian);
        }
        throw new RuntimeException("TODO ByteBuffer.getInt");
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
        } else {
            console.error("TODO putShort", arguments);
            throw new RuntimeException("TODO putShort");
        }
    }

    public short getShort(Object... arguments) {
        if (arguments.length == 0) {
            return Bits.getShort(this, ix(_nextGetIndex(2)), bigEndian);
        }
        // TODO Auto-generated method stub
        console.error("arguments", arguments);
        throw new RuntimeException("TODO ByteBuffer.getShort " + arguments.length);
    }

    byte _get(int i) { // package-private
        return hb[i];
    }

    void _put(int i, byte b) {
        hb[i] = b;
    }
}
