package js.io;

import js.io.File;

import static org.stjs.javascript.Global.console;

import org.stjs.javascript.JSGlobal;

import js.nio.channels.FileChannel;
import js.node.FS;
import js.node.NodeJS;

public class RandomAccessFile implements Closeable {
    private final static FS fs = NodeJS.require("fs");
    private int fd;
    private FileChannel channel;

    public RandomAccessFile(Object... arguments) {
        String path = null;
        String mode = (String) arguments[1];
        if (JSGlobal.typeof(arguments[0]) == "string") {
            path = (String) arguments[0];
        } else {
            //RandomAccessFile(File file, String mode)
            path = ((File) arguments[0]).getPath();
        }
        if ("rw".equals(mode)) {
            mode = "a+";
        }
        js.lang.System.err.println("RandomAccessFile open " + path + " " + mode);
        this.fd = fs.openSync(path, mode);
    }

    public int read(byte[] dst) {
        throw new RuntimeException("TODO");
    }

    public int getFilePointer() {
        throw new RuntimeException("TODO");
    }

    public void seek(long i) {
        throw new RuntimeException("TODO");
    }

    public void write(byte[] tsPkt) {
        throw new RuntimeException("TODO");
    }

    @Override
    public void close() throws IOException {
        console.log("RandomAccessFile close", fd);
        fs.closeSync(fd);
    }

    public FileChannel getChannel() {
        if (channel == null) {
            channel = new FileChannel(fd);
        }
        return channel;
    }

}
