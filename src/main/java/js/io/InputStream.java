package js.io;

public abstract class InputStream implements Closeable {

	public int read(Object... arguments) throws IOException {
		throw new RuntimeException("TODO");
	}

	public long skip(long l) {
		throw new RuntimeException("TODO");
	}
	@Override
	public void close() throws IOException {
		throw new RuntimeException("TODO");
	}

}
