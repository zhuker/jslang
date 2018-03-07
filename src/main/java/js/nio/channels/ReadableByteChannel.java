package js.nio.channels;

import js.io.Closeable;
import js.io.IOException;
import js.nio.ByteBuffer;

public interface ReadableByteChannel extends Closeable {
    
    boolean isOpen();
	int read(ByteBuffer buf) throws IOException;

}
