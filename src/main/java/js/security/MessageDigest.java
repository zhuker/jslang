package js.security;

public class MessageDigest {

	public void update(Object... arguments) {
		throw new RuntimeException("TODO MessageDigest.update");
	}

	public byte[] digest() {
		throw new RuntimeException("TODO MessageDigest.digest");
	}

	public static MessageDigest getInstance(String string) throws NoSuchAlgorithmException {
		throw new RuntimeException("TODO MessageDigest.getInstance");
	}

}
