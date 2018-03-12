package js.io;

import static org.stjs.javascript.Global.console;

import js.io.FileNotFoundException;

import org.stjs.javascript.JSGlobal;
import org.stjs.javascript.typed.ArrayBuffer;
import org.stjs.javascript.typed.Int8Array;

import js.nio.channels.FileChannel;
import js.node.FS;
import js.node.NodeJS;

public class FileInputStream extends InputStream {
    private final static FS fs = NodeJS.require("fs");
    private ByteArrayInputStream bais;
    private int fd;
    private FileChannel channel;

    public FileInputStream(Object... arguments) throws FileNotFoundException {
        if (arguments.length == 1) {
            String path = null;
            String mode = "r";
            if (JSGlobal.typeof(arguments[0]) == "string") {
                //    public FileInputStream(String name) throws FileNotFoundException {
                path = (String) arguments[0];
            } else {
                //    public FileInputStream(File file) throws FileNotFoundException {
                path = ((File) arguments[0]).getPath();
            }
            js.lang.System.err.println("FileInputStream open " + path + " " + mode);
            this.fd = fs.openSync(path, mode);
            ArrayBuffer buffer = fs.readFileSync(path);
            Int8Array arr = new Int8Array(buffer);
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
        if (channel == null) {
            channel = new FileChannel(fd);
        }
        return channel;
    }

    @Override
    public void close() throws IOException {
        console.log("FileInputStream close", fd);
        fs.closeSync(fd);
    }

    public int available() {
        throw new RuntimeException("TODO");
    }

}
