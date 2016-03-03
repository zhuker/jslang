package js.nio;

import org.stjs.javascript.Global;
import org.stjs.javascript.JSGlobal;
import org.stjs.javascript.typed.Int32Array;

import js.lang.IntentionalHack;

public class IntBuffer {

    private Int32Array hb;
    final int offset;
    private int pos;
    private int lim;
    private int cap;
    private int _mark;

    protected IntBuffer(Int32Array int32array, int off, int pos, int lim, int cap) {
        this.hb = int32array;
        this.offset = off;
        this._mark = -1;
        this.pos = pos;
        this.lim = lim;
        this.cap = cap;
    }

    public static IntBuffer wrap(int[] orig) {
        throw new RuntimeException("TODO");
    }

    public static IntBuffer allocate(int length) {
        return new IntBuffer(new Int32Array(length), 0, 0, length, length);
    }

    public int[] array() {
        throw new RuntimeException("TODO IntBuffer.array");
    }

    public int capacity() {
        return cap;
    }

    public void clear() {
        throw new RuntimeException("TODO IntBuffer.clear");
    }

    public IntBuffer duplicate() {
        throw new RuntimeException("TODO IntBuffer.duplicate");
    }

    public int get(Object... arguments) {
        if (arguments.length == 1 && "number" == JSGlobal.typeof(arguments[0])) {
            int i = (int) arguments[0];
            return hb.$get(ix(checkIndex(i)));
        }
        throw new RuntimeException("TODO IntBuffer.get");
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

    public Object put(Object... arguments) {
        if (arguments.length == 1 && "number" == JSGlobal.typeof(arguments[0])) {
            int x = (int) arguments[0];
            hb.$set(ix(nextPutIndex()), x);
            return this;
        }
        Global.console.error("arguments", arguments);
        throw new RuntimeException("TODO IntBuffer.put " + arguments.length);
    }

    protected int ix(int i) {
        return i + offset;
    }

    final int nextPutIndex() { // package-private
        if (pos >= lim)
            throw new js.nio.BufferOverflowException();
        return pos++;
    }

    public int position(int... arguments) {
        if (arguments.length == 0) {
            return pos;
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

    @IntentionalHack
    private int illegalCast(IntBuffer intBuffer) {
        Object o = intBuffer;
        return (int) o;
    }

    public boolean hasRemaining() {
        throw new RuntimeException("TODO IntBuffer.hasRemaining");
    }

    public IntBuffer slice() {
        throw new RuntimeException("TODO IntBuffer.slice");
    }

}
