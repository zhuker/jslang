package js.nio;

import static org.stjs.javascript.Global.console;

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

    protected IntBuffer(Int32Array int32array, int mark, int pos, int lim, int cap, int off) {
        this.hb = int32array;
        this.offset = off;
        this._mark = mark;
        this.pos = pos;
        this.lim = lim;
        this.cap = cap;
    }

    public static IntBuffer wrap(int[] orig) {
        return new IntBuffer((Int32Array) (Object) orig, -1, 0, orig.length, orig.length, 0);
    }

    public static IntBuffer allocate(int length) {
        return new IntBuffer(new Int32Array(length), -1, 0, length, length, 0);
    }

    public int[] array() {
        return (int[]) (Object) hb;
    }

    public int capacity() {
        return cap;
    }

    public void clear() {
        throw new RuntimeException("TODO IntBuffer.clear");
    }

    public IntBuffer duplicate() {
        return new IntBuffer(hb, this.markValue(), this.position(), this.limit(), this.capacity(), offset);
    }

    public int limit() {
        return lim;
    }

    final int markValue() { // package-private
        return _mark;
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
        switch (arguments.length) {
        case 1:
            if ("number" == JSGlobal.typeof(arguments[0])) {
                int x = (int) arguments[0];
                hb.$set(ix(nextPutIndex()), x);
                return this;
            }
            break;
        case 2:
            int index = (int) arguments[0];
            int val = (int) arguments[1];
            hb.$set(ix(checkIndex(index)), val);
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

    public final int remaining() {
        return lim - pos;
    }

    public IntBuffer slice() {
        return new IntBuffer(hb, -1, 0, this.remaining(), this.remaining(), this.position() + offset);
    }

}
