package js.io;

public class ByteArrayInputStream extends InputStream {

    protected byte buf[];

    protected int pos;

    protected int _mark = 0;

    protected int count;

    public ByteArrayInputStream(Object... arguments) {
        if (arguments.length == 1) {
            byte[] buf = (byte[]) arguments[0];
            this.buf = buf;
            this.pos = 0;
            this.count = buf.length;
        } else if (arguments.length == 3) {
            byte[] buf = (byte[]) arguments[0];
            int offset = (int) arguments[1];
            int length = (int) arguments[2];
            this.buf = buf;
            this.pos = offset;
            this.count = Math.min(offset + length, buf.length);
            this._mark = offset;
        }
    }

    @Override
    protected int read0() {
        return (pos < count) ? (buf[pos++] & 0xff) : -1;
    }

    @Override
    protected int read3(byte b[], int off, int len) {
        if (b == null) {
            throw new js.lang.NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new js.lang.IndexOutOfBoundsException();
        }

        if (pos >= count) {
            return -1;
        }

        int avail = count - pos;
        if (len > avail) {
            len = avail;
        }
        if (len <= 0) {
            return 0;
        }
        js.lang.System.arraycopy(buf, pos, b, off, len);
        pos += len;
        return len;
    }

    @Override
    public long skip(long n) {
        long k = count - pos;
        if (n < k) {
            k = n < 0 ? 0 : n;
        }

        pos += k;
        return k;
    }

    public int available() {
        return count - pos;
    }

    public boolean markSupported() {
        return true;
    }

    public void mark(int readAheadLimit) {
        _mark = pos;
    }

    public void reset() {
        pos = _mark;
    }

    @Override
    public void close() throws IOException {
    }

}
