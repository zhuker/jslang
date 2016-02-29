package js.io;

import js.io.IOException;

public abstract class OutputStream implements Closeable {

    public void write(Object... arguments) throws IOException {
        throw new RuntimeException("TODO");
    }

    @Override
    public void close() throws IOException {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");

    }

    public void flush() throws IOException {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");

    }

}
