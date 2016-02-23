package js.io;

public abstract class OutputStream implements Closeable {

	public void write(int cur) throws IOException {
		throw new RuntimeException("TODO");
	}
	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
		
	}

	public void write(byte[] bytes) {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
		
	}

	public void flush() {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
		
	}

	public void write(byte[] buffer, int i, int n) {
		throw new RuntimeException("TODO");
		
	}

}
