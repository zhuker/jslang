package js.io;

public class BufferedInputStream extends InputStream {

    private InputStream is;

    public BufferedInputStream(InputStream is) {
        this.is = is;
    }

    @Override
    protected int read0() {
        return is.read0();
    }

    @Override
    protected int read1(byte[] buf) {
        return is.read1(buf);
    }

    @Override
    protected int read3(byte[] buf, int off, int len) {
        return is.read3(buf, off, len);
    }

    public long skip(long l) {
        return is.skip(l);
    }

    public void close() throws IOException {
        is.close();
    }
}
