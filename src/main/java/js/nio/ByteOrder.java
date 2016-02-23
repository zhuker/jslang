package js.nio;

public class ByteOrder {

	/**
	 * Constant denoting big-endian byte order. In this order, the bytes of a multibyte value are ordered from most
	 * significant to least significant.
	 */
	public static final ByteOrder BIG_ENDIAN = new ByteOrder("BIG_ENDIAN");

	/**
	 * Constant denoting little-endian byte order. In this order, the bytes of a multibyte value are ordered from least
	 * significant to most significant.
	 */
	public static final ByteOrder LITTLE_ENDIAN = new ByteOrder("LITTLE_ENDIAN");

	private String name;

	private ByteOrder(String name) {
		this.name = name;
	}
}
