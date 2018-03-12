package js.nio.channels;

import js.io.FileInputStream;
import js.io.IOException;
import js.nio.ByteBuffer;
import js.nio.channels.Channels.ReadableByteChannelImpl;

import js.io.ByteArrayInputStream;
import js.io.InputStream;

public class Channels {

    public static class ReadableByteChannelImpl implements ReadableByteChannel {
        private static final int TRANSFER_SIZE = 8192;
        InputStream _in;
        private byte buf[];
        private boolean open = true;

        ReadableByteChannelImpl(InputStream _in) {
            this._in = _in;
            buf = new byte[0];
        }

        @Override
        public void close() throws IOException {
            _in.close();
            open = false;
        }

        @Override
        public boolean isOpen() {
            return open;
        }

        @Override
        public int read(ByteBuffer dst) throws IOException {
            int len = dst.remaining();
            int totalRead = 0;
            int bytesRead = 0;
            {
                while (totalRead < len) {
                    int bytesToRead = Math.min((len - totalRead), TRANSFER_SIZE);
                    if (buf.length < bytesToRead)
                        buf = new byte[bytesToRead];
                    if ((totalRead > 0) && !(_in.available() > 0))
                        break; // block at most once
                    try {
                        begin();
                        bytesRead = _in.read(buf, 0, bytesToRead);
                    } finally {
                        end(bytesRead > 0);
                    }
                    if (bytesRead < 0)
                        break;
                    else
                        totalRead += bytesRead;
                    dst.put3(buf, 0, bytesRead);
                }
                if ((bytesRead < 0) && (totalRead == 0))
                    return -1;

                return totalRead;
            }
        }

        private void end(boolean b) {
        }

        private void begin() {
        }

    }

    public static InputStream newInputStream(ReadableByteChannel _in) {
        throw new RuntimeException("TODO");
    }

    public static ReadableByteChannel newChannel(InputStream _in) {
        if (_in instanceof FileInputStream && FileInputStream.class == _in.getClass()) {
            return ((FileInputStream) _in).getChannel();
        }

        return new ReadableByteChannelImpl(_in);
    }

}
