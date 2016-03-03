package js.io;

import org.stjs.javascript.typed.ArrayBuffer;
import org.stjs.javascript.typed.Int8Array;

import js.nio.channels.FileChannel;
import js.node.FS;
import js.node.NodeJS;

public class FileInputStream extends InputStream {
    private final static FS fs = NodeJS.require("fs");
    private Int8Array arr;
    private ByteArrayInputStream bais;

    public FileInputStream(Object... arguments) throws FileNotFoundException {
        if (arguments.length == 1) {
            File file = (File) arguments[0];
            ArrayBuffer buffer = fs.readFileSync(file.getPath());
            arr = new Int8Array(buffer);
            bais = new ByteArrayInputStream(arr);
        } else {
            throw new RuntimeException("TODO FileInputStream.init");
        }
    }

    @Override
    protected int read0() {
        return bais.read0();
    }

    @Override
    protected int read1(byte[] buf) {
        return bais.read1(buf);
    }

    @Override
    protected int read3(byte[] buf, int off, int len) {
        return bais.read3(buf, off, len);
    }

    @Override
    public long skip(long n) {
        return bais.skip(n);
    }

    public boolean markSupported() {
        return bais.markSupported();
    }

    public void mark(int readAheadLimit) {
        bais.mark(readAheadLimit);
    }

    public void reset() {
        bais.reset();
    }

    public FileChannel getChannel() {
        return new FileChannel(arr);
    }

    @Override
    public void close() throws IOException {
    }

    public int available() {
        throw new RuntimeException("TODO");
    }

}
