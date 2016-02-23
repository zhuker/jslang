package js.nio.channels;

import js.nio.ByteBuffer;
import js.nio.MappedByteBuffer;

public class FileChannel implements ReadableByteChannel {

	public static class MapMode {

        /**
         * Mode for a read-only mapping.
         */
        public static final MapMode READ_ONLY
            = new MapMode("READ_ONLY");

        /**
         * Mode for a read/write mapping.
         */
        public static final MapMode READ_WRITE
            = new MapMode("READ_WRITE");

        /**
         * Mode for a private (copy-on-write) mapping.
         */
        public static final MapMode PRIVATE
            = new MapMode("PRIVATE");

        private final String name;

        private MapMode(String name) {
            this.name = name;
        }

	}

	public boolean isOpen() {
		throw new RuntimeException("TODO");
	}

	public long position() {
		throw new RuntimeException("TODO");
	}

	public int read(ByteBuffer arg0) {
		throw new RuntimeException("TODO");
	}

	public void position(long savedPos) {
		throw new RuntimeException("TODO");
		
	}

	public void close() {
		throw new RuntimeException("TODO");
		
	}

	public long size() {
		throw new RuntimeException("TODO");
	}

	public void truncate(long size) {
		throw new RuntimeException("TODO");
	}

	public int write(ByteBuffer arg0) {
		throw new RuntimeException("TODO");
	}

	public MappedByteBuffer map(MapMode mapMode, long offset, long len) {
		throw new RuntimeException("TODO");
	}

	public long transferFrom(FileChannel input, long pos, long count) {
		throw new RuntimeException("TODO");
	}

}
