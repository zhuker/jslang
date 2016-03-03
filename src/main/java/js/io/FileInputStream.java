package js.io;

import org.stjs.javascript.typed.ArrayBuffer;
import org.stjs.javascript.typed.Int8Array;

import js.nio.channels.FileChannel;
import js.node.FS;
import js.node.NodeJS;

public class FileInputStream extends InputStream {
    private final static FS fs = NodeJS.require("fs");
    private Int8Array arr;

    public FileInputStream(Object... arguments) throws FileNotFoundException {
        if (arguments.length == 1) {
            File file = (File) arguments[0];
            ArrayBuffer buffer = fs.readFileSync(file.getPath());
            arr = new Int8Array(buffer);
        } else {
            throw new RuntimeException("TODO FileInputStream.init");
        }
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
