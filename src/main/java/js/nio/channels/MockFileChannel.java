package js.nio.channels;

import org.stjs.javascript.typed.Int8Array;

import js.lang.IntentionalHack;
import js.nio.ByteBuffer;
import js.nio.MappedByteBuffer;

public class MockFileChannel extends FileChannel {

    private ByteBuffer backing;
    private int contentLength;

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

    public MockFileChannel(Int8Array arr) {
        super(-1);
        this.backing = ByteBuffer.wrap(arr);
        this.contentLength = backing.remaining();
    }

    public boolean isOpen() {
        return true;
    }

    public long position(Object... arguments) {
        if (arguments.length == 0) {
            return backing.position();
        }
        int newPosition = (int) arguments[0];
        backing.setPosition((int) newPosition);
        return illegalCast(this);
    }

    public int read(ByteBuffer dst) {
        if (!backing.hasRemaining()) {
            return -1;
        }
        int toRead = Math.min(backing.remaining(), dst.remaining());
        dst.putBuf(__read(backing, toRead));
        contentLength = Math.max(contentLength, backing.position());
        return toRead;
    }

    private static final ByteBuffer __read(ByteBuffer buffer, int count) {
        ByteBuffer slice = buffer.duplicate();
        int limit = buffer.position() + count;
        slice.setLimit(limit);
        buffer.setPosition(limit);
        return slice;
    }

    public void close() {
    }

    public long size() {
        return contentLength;
    }

    public void truncate(long size) {
        throw new RuntimeException("TODO");
    }

    public int write(ByteBuffer arg0) {
        throw new RuntimeException("FileChannel.write");
    }

}
