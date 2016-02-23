package js.io;

import js.nio.channels.FileChannel;

public class FileOutputStream extends OutputStream {

	public FileOutputStream(File f) throws FileNotFoundException {
		if (!f.exists()) {
			throw new FileNotFoundException(f);
		}
		throw new RuntimeException("TODO");
	}

	public FileOutputStream(String string) {
		// TODO Auto-generated constructor stub
		throw new RuntimeException("TODO");
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		throw new RuntimeException("TODO");
		
	}

	public FileChannel getChannel() {
		throw new RuntimeException("TODO");
	}

}
