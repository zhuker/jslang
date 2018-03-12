package js.nio.channels;

import static org.stjs.javascript.Global.console;

import org.stjs.javascript.Global;
import org.stjs.javascript.typed.ArrayBuffer;
import org.stjs.javascript.typed.Int8Array;

import js.lang.IntentionalHack;
import js.nio.ByteBuffer;
import js.nio.MappedByteBuffer;
import js.node.Buffer;
import js.node.FS;
import js.node.NodeJS;

public class FileChannel implements ReadableByteChannel {
    private final static FS fs = NodeJS.require("fs");

    private int fd;

    long fpos = 0;

    private boolean closed = false;

    public static class MapMode {

        /**
         * Mode for a read-only mapping.
         */
        public static final MapMode READ_ONLY = new MapMode("READ_ONLY");

        /**
         * Mode for a read/write mapping.
         */
        public static final MapMode READ_WRITE = new MapMode("READ_WRITE");

        /**
         * Mode for a private (copy-on-write) mapping.
         */
        public static final MapMode PRIVATE = new MapMode("PRIVATE");

        private final String name;

        private MapMode(String name) {
            this.name = name;
        }

    }

    public FileChannel(int fd) {
        this.fd = fd;
    }

    @Override
    public boolean isOpen() {
        return !closed;
    }

    public long position(Object... arguments) {
        switch (arguments.length) {
        case 0:
            return fpos;
        case 1:
            fpos = (long) arguments[0];
            return illegalCast(this);
        default:
            throw new RuntimeException("TODO FileChannel.position " + arguments);
        }
    }

    @IntentionalHack
    protected static long illegalCast(FileChannel x) {
        Object o = x;
        return (long) o;
    }

    @Override
    public int read(ByteBuffer dst) {
        Buffer buffer = (Buffer) (Object) dst.array();
        int start = dst.arrayOffset() + dst.position();
        int remaining = dst.remaining();
        int readSync = fs.readSync(fd, buffer, start, remaining, fpos);
        if (readSync > 0) {
            fpos += readSync;
            dst.setPosition(dst.position() + readSync);
        }
        return readSync == 0 ? -1 : readSync;
    }

    private static final ByteBuffer _read(ByteBuffer buffer, int count) {
        ByteBuffer slice = buffer.duplicate();
        int limit = buffer.position() + count;
        slice.setLimit(limit);
        buffer.setPosition(limit);
        return slice;
    }

    @Override
    public void close() {
        console.log("FileChannel close", fd);
        fs.closeSync(fd);
        this.closed = true;
    }

    public long size() {
        return fs.fstatSync(fd).size;
    }

    public void truncate(long size) {
        throw new RuntimeException("FileChannel.truncate");
    }

    public int write(ByteBuffer buf) {
        int position = buf.position();
        int len = buf.remaining();
        Object array = buf.array();
        Int8Array arr = (Int8Array) array;
        int writen = fs.writeSync(fd, new Buffer(arr.buffer), position, len, fpos);
        if (writen >= 0) {
            fpos += writen;
            buf.setPosition(position + writen);
        }
        //        console.log("write", nodebuf, position, len, fpos);
        //        console.log("writen", writen);
        return writen;
    }

    public MappedByteBuffer map(MapMode mapMode, long offset, long len) {
        throw new RuntimeException("TODO");
    }

    public long transferFrom(FileChannel input, long pos, long count) {
        throw new RuntimeException("TODO");
    }

}
