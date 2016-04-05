package js.io;

import js.nio.channels.FileChannel;
import js.node.FS;
import js.node.NodeJS;

public class FileOutputStream extends OutputStream {
    private final static FS fs = NodeJS.require("fs");
    private int fd;

    public FileOutputStream(Object... arguments) throws FileNotFoundException {
        switch (arguments.length) {
        case 1:
            File file = (File) arguments[0];
            fd = fs.openSync(file.getPath(), "w+");
            break;

        default:
            throw new RuntimeException("FileOutputStream.init " + arguments.length);
        }
    }

    @Override
    public void close() throws IOException {
        fs.closeSync(fd);
    }

    public FileChannel getChannel() {
        return new FileChannel(fd);
    }

}
