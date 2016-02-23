package js.nio.channels;

import js.io.IOException;

import js.nio.ByteBuffer;

public interface WritableByteChannel extends Channel {

    public int write(ByteBuffer src) throws IOException;
}
