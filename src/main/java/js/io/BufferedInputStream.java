package js.io;

public class BufferedInputStream extends InputStream {

    private InputStream is;

    public BufferedInputStream(InputStream is) {
        this.is = is;
    }

    public int read(Object... arguments) throws IOException {
        return is.read(arguments);
    }

    public long skip(long l) {
        return is.skip(l);
    }

    public void close() throws IOException {
        is.close();
    }
}
