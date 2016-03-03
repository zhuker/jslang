package js.io;

public abstract class InputStream implements Closeable {

    public int read(Object... arguments) throws IOException {
        if (arguments.length == 0) {
            return read0();
        } else if (arguments.length == 1) {
            byte[] dst = (byte[]) arguments[0];
            return read1(dst);
        } else if (arguments.length == 3) {
            byte[] dst = (byte[]) arguments[0];
            int off = (int) arguments[1];
            int len = (int) arguments[2];
            return read3(dst, off, len);
        } else {
            throw new RuntimeException("TODO InputStream read " + arguments.length);
        }
    }

    public long skip(long l) {
        throw new RuntimeException("TODO");
    }

    @Override
    public void close() throws IOException {
        throw new RuntimeException("TODO");
    }

    protected int read0() {
        throw new RuntimeException("TODO");
    }

    protected int read1(byte[] buf) {
        return read3(buf, 0, buf.length);
    }

    protected int read3(byte[] buf, int off, int len) {
        throw new RuntimeException("TODO");
    }

}
