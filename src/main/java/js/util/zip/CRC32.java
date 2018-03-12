package js.util.zip;
import js.lang.NullPointerException;

import static com.jcraft.jzlib.CRC32.crc_table;

import js.lang.ArrayIndexOutOfBoundsException;

public class CRC32 {

    private int crc;

    /**
     * Creates a new CRC32 object.
     */
    public CRC32() {
    }


    /**
     * Updates the CRC-32 checksum with the specified byte (the low
     * eight bits of the argument b).
     *
     * @param b the byte to update the checksum with
     */
    public void update(int b) {
        crc = _update(crc, b);
    }

    /**
     * Updates the CRC-32 checksum with the specified array of bytes.
     *
     * @throws  ArrayIndexOutOfBoundsException
     *          if {@code off} is negative, or {@code len} is negative,
     *          or {@code off+len} is greater than the length of the
     *          array {@code b}
     */
    public void update3(byte[] b, int off, int len) {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new ArrayIndexOutOfBoundsException();
        }
        crc = updateBytes(crc, b, off, len);
    }

    /**
     * Updates the CRC-32 checksum with the specified array of bytes.
     *
     * @param b the array of bytes to update the checksum with
     */
    public void updateB(byte[] b) {
        crc = updateBytes(crc, b, 0, b.length);
    }


    /**
     * Resets CRC-32 to initial value.
     */
    public void reset() {
        crc = 0;
    }

    /**
     * Returns CRC-32 value.
     */
    public long getValue() {
        return unsignedInt(crc);
    }

    static long unsignedInt(int signed) {
        return (signed & 0x7fffffff) + ((signed >>> 31) * 0x80000000);
    }

    private static int _update(int v, int b) {
        int c = ~v;
        c = crc_table[(c^b)&0xff]^(c >>> 8);
        v = ~c;
        return v;
    }

    private static int updateBytes(int v, byte[] buf, int index, int len) {
        int c = ~v;
        while (--len >= 0)
          c = crc_table[(c^buf[index++])&0xff]^(c >>> 8);
        v = ~c;
        return v;
    }

}
