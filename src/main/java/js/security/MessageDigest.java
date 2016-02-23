package js.security;

import js.nio.ByteBuffer;

public class MessageDigest {

	public void update(byte[] bytes) {
		throw new RuntimeException("TODO MessageDigest.update");
	}

	public byte[] digest() {
		throw new RuntimeException("TODO MessageDigest.digest");
	}

	public void update(ByteBuffer bytes) {
		throw new RuntimeException("TODO MessageDigest.update");
	}

	public static MessageDigest getInstance(String string) throws NoSuchAlgorithmException {
		throw new RuntimeException("TODO MessageDigest.getInstance");
	}

}
