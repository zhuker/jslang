package js.io;

public abstract class InputStream implements Closeable {

	public int read() throws IOException {
		throw new RuntimeException("TODO");
	}

	public int read(byte[] buf, int read, int i) throws IOException {
		throw new RuntimeException("TODO");
	}

	public long skip(long l) {
		throw new RuntimeException("TODO");
	}
	@Override
	public void close() throws IOException {
		throw new RuntimeException("TODO");
	}

	public int read(byte[] buf1) {
		throw new RuntimeException("TODO");
	}

}
