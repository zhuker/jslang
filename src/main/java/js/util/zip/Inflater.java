package js.util.zip;

import com.jcraft.jzlib.ZStream;

import js.lang.NullPointerException;
import js.lang.ArrayIndexOutOfBoundsException;
import js.util.Arrays;
import js.util.zip.DataFormatException;
import js.lang.IllegalArgumentException;
import js.lang.OutOfMemoryError;

import org.stjs.javascript.annotation.Native;

import static com.jcraft.jzlib.JZlib.Z_BUF_ERROR;
import static com.jcraft.jzlib.JZlib.Z_DATA_ERROR;
import static com.jcraft.jzlib.JZlib.Z_MEM_ERROR;
import static com.jcraft.jzlib.JZlib.Z_NEED_DICT;
import static com.jcraft.jzlib.JZlib.Z_OK;
import static com.jcraft.jzlib.JZlib.Z_PARTIAL_FLUSH;
import static com.jcraft.jzlib.JZlib.Z_STREAM_END;
import static com.jcraft.jzlib.JZlib.Z_STREAM_ERROR;

/**
 * This class provides support for general purpose decompression using the
 * popular ZLIB compression library. The ZLIB compression library was
 * initially developed as part of the PNG graphics standard and is not
 * protected by patents. It is fully described in the specifications at
 * the <a href="package-summary.html#package_description">java.util.zip
 * package description</a>.
 * <p>
 * <p>The following code fragment demonstrates a trivial compression
 * and decompression of a string using <tt>Deflater</tt> and
 * <tt>Inflater</tt>.
 * <p>
 * <blockquote><pre>
 * try {
 *     // Encode a String into bytes
 *     String inputString = "blahblahblah\u20AC\u20AC";
 *     byte[] input = inputString.getBytes("UTF-8");
 * <p>
 *     // Compress the bytes
 *     byte[] output = new byte[100];
 *     Deflater compresser = new Deflater();
 *     compresser.setInput(input);
 *     compresser.finish();
 *     int compressedDataLength = compresser.deflate(output);
 * <p>
 *     // Decompress the bytes
 *     Inflater decompresser = new Inflater();
 *     decompresser.setInput(output, 0, compressedDataLength);
 *     byte[] result = new byte[100];
 *     int resultLength = decompresser.inflate(result);
 *     decompresser.end();
 * <p>
 *     // Decode the bytes into a String
 *     String outputString = new String(result, 0, resultLength, "UTF-8");
 * } catch(java.io.UnsupportedEncodingException ex) {
 *     // handle
 * } catch (java.util.zip.DataFormatException ex) {
 *     // handle
 * }
 * </pre></blockquote>
 *
 * @author David Connelly
 */
public class Inflater {

    private final ZStream[] zsRef;
    private byte[] buf;
    private int off, len;
    private boolean _finished;
    private boolean needDict;
    private long bytesRead;
    private long bytesWritten;

    private static final byte[] defaultBuf = new byte[0];

    static {
        /* Zip library is loaded from System.initializeSystemClass */
        initIDs();
    }

    /**
     * Creates a new decompressor. If the parameter 'nowrap' is true then
     * the ZLIB header and checksum fields will not be used. This provides
     * compatibility with the compression format used by both GZIP and PKZIP.
     * <p>
     * Note: When using the 'nowrap' option it is also necessary to provide
     * an extra "dummy" byte as input. This is required by the ZLIB native
     * library in order to support certain optimizations.
     *
     * @param nowrap if true then support GZIP compatible compression
     */
    public Inflater(boolean nowrap) {
        this.zsRef = new ZStream[1];
        this.buf = defaultBuf;

        zsRef[0] = init(nowrap);
    }

    /**
     * Creates a new decompressor.
     */
    @Native
    public Inflater() {
        this(false);
    }

    /**
     * Sets input data for decompression. Should be called whenever
     * needsInput() returns true indicating that more input data is
     * required.
     *
     * @param b   the input data bytes
     * @param off the start offset of the input data
     * @param len the length of the input data
     * @see Inflater#needsInput
     */
    public void setInput3(byte[] b, int off, int len) {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new ArrayIndexOutOfBoundsException();
        }
        {
            this.buf = b;
            this.off = off;
            this.len = len;
        }
    }

    /**
     * Sets input data for decompression. Should be called whenever
     * needsInput() returns true indicating that more input data is
     * required.
     *
     * @param b the input data bytes
     * @see Inflater#needsInput
     */
    public void setInput(byte[] b) {
        setInput3(b, 0, b.length);
    }

    /**
     * Sets the preset dictionary to the given array of bytes. Should be
     * called when inflate() returns 0 and needsDictionary() returns true
     * indicating that a preset dictionary is required. The method getAdler()
     * can be used to get the Adler-32 value of the dictionary needed.
     *
     * @param b   the dictionary data bytes
     * @param off the start offset of the data
     * @param len the length of the data
     * @see Inflater#needsDictionary
     * @see Inflater#getAdler
     */
    public void setDictionary3(byte[] b, int off, int len) {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new ArrayIndexOutOfBoundsException();
        }
        {
            ensureOpen();
            _setDictionary(zsRef[0], b, off, len);
            needDict = false;
        }
    }

    /**
     * Sets the preset dictionary to the given array of bytes. Should be
     * called when inflate() returns 0 and needsDictionary() returns true
     * indicating that a preset dictionary is required. The method getAdler()
     * can be used to get the Adler-32 value of the dictionary needed.
     *
     * @param b the dictionary data bytes
     * @see Inflater#needsDictionary
     * @see Inflater#getAdler
     */
    public void setDictionary(byte[] b) {
        setDictionary3(b, 0, b.length);
    }

    /**
     * Returns the total number of bytes remaining in the input buffer.
     * This can be used to find out what bytes still remain in the input
     * buffer after decompression has finished.
     *
     * @return the total number of bytes remaining in the input buffer
     */
    public int getRemaining() {
        {
            return len;
        }
    }

    /**
     * Returns true if no data remains in the input buffer. This can
     * be used to determine if #setInput should be called in order
     * to provide more input.
     *
     * @return true if no data remains in the input buffer
     */
    public boolean needsInput() {
        {
            return len <= 0;
        }
    }

    /**
     * Returns true if a preset dictionary is needed for decompression.
     *
     * @return true if a preset dictionary is needed for decompression
     * @see Inflater#setDictionary
     */
    public boolean needsDictionary() {
        {
            return needDict;
        }
    }

    /**
     * Returns true if the end of the compressed data stream has been
     * reached.
     *
     * @return true if the end of the compressed data stream has been
     * reached
     */
    public boolean finished() {
        {
            return _finished;
        }
    }

    /**
     * Uncompresses bytes into specified buffer. Returns actual number
     * of bytes uncompressed. A return value of 0 indicates that
     * needsInput() or needsDictionary() should be called in order to
     * determine if more input data or a preset dictionary is required.
     * In the latter case, getAdler() can be used to get the Adler-32
     * value of the dictionary required.
     *
     * @param b   the buffer for the uncompressed data
     * @param off the start offset of the data
     * @param len the maximum number of uncompressed bytes
     * @return the actual number of uncompressed bytes
     * @throws DataFormatException if the compressed data format is invalid
     * @see Inflater#needsInput
     * @see Inflater#needsDictionary
     */
    public int inflate3(byte[] b, int off, int len)
            throws DataFormatException {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new ArrayIndexOutOfBoundsException();
        }
        {
            ensureOpen();
            int thisLen = this.len;
            int n = inflateBytes(zsRef[0], b, off, len);
            bytesWritten += n;
            bytesRead += (thisLen - this.len);
            return n;
        }
    }

    /**
     * Uncompresses bytes into specified buffer. Returns actual number
     * of bytes uncompressed. A return value of 0 indicates that
     * needsInput() or needsDictionary() should be called in order to
     * determine if more input data or a preset dictionary is required.
     * In the latter case, getAdler() can be used to get the Adler-32
     * value of the dictionary required.
     *
     * @param b the buffer for the uncompressed data
     * @return the actual number of uncompressed bytes
     * @throws DataFormatException if the compressed data format is invalid
     * @see Inflater#needsInput
     * @see Inflater#needsDictionary
     */
    public int inflate(byte[] b) throws DataFormatException {
        return inflate3(b, 0, b.length);
    }

    /**
     * Returns the ADLER-32 value of the uncompressed data.
     *
     * @return the ADLER-32 value of the uncompressed data
     */
    public int getAdler() {
        {
            ensureOpen();
            return _getAdler(zsRef[0]);
        }
    }

    /**
     * Returns the total number of compressed bytes input so far.
     * <p>
     * <p>Since the number of bytes may be greater than
     * Integer.MAX_VALUE, the {@link #getBytesRead()} method is now
     * the preferred means of obtaining this information.</p>
     *
     * @return the total number of compressed bytes input so far
     */
    public int getTotalIn() {
        return (int) getBytesRead();
    }

    /**
     * Returns the total number of compressed bytes input so far.
     *
     * @return the total (non-negative) number of compressed bytes input so far
     * @since 1.5
     */
    public long getBytesRead() {
        {
            ensureOpen();
            return bytesRead;
        }
    }

    /**
     * Returns the total number of uncompressed bytes output so far.
     * <p>
     * <p>Since the number of bytes may be greater than
     * Integer.MAX_VALUE, the {@link #getBytesWritten()} method is now
     * the preferred means of obtaining this information.</p>
     *
     * @return the total number of uncompressed bytes output so far
     */
    public int getTotalOut() {
        return (int) getBytesWritten();
    }

    /**
     * Returns the total number of uncompressed bytes output so far.
     *
     * @return the total (non-negative) number of uncompressed bytes output so far
     * @since 1.5
     */
    public long getBytesWritten() {
        {
            ensureOpen();
            return bytesWritten;
        }
    }

    /**
     * Resets inflater so that a new set of input data can be processed.
     */
    public void reset() {
        {
            ensureOpen();
            _reset(zsRef[0]);
            buf = defaultBuf;
            _finished = false;
            needDict = false;
            off = len = 0;
            bytesRead = bytesWritten = 0;
        }
    }

    /**
     * Closes the decompressor and discards any unprocessed input.
     * This method should be called when the decompressor is no longer
     * being used, but will also be called automatically by the finalize()
     * method. Once this method is called, the behavior of the Inflater
     * object is undefined.
     */
    public void end() {
        {
            ZStream addr = zsRef[0];
            zsRef[0] = null;
            if (addr != null) {
                _end(addr);
                buf = null;
            }
        }
    }

    /**
     * Closes the decompressor when garbage is collected.
     */
    protected void finalize() {
        end();
    }

    private void ensureOpen() {
//        assert Thread.holdsLock(zsRef);
        if (zsRef[0] == null)
            throw new NullPointerException("Inflater has been closed");
    }

    boolean ended() {
        {
            return zsRef[0] == null;
        }
    }

    private static void initIDs() {
    }

    private static ZStream init(boolean nowrap) {
        ZStream zs = new ZStream();
        zs.inflateInitB(nowrap);
        return zs;
    }

    private static void _setDictionary(ZStream addr, byte[] b, int off,
                                      int len) {
        byte[] dict = off == 0 ? b : Arrays.copyOfRange(b, off, off + len);
        int ret = addr.inflateSetDictionary(dict, len);
        switch (ret) {
            case Z_OK:
                break;
            case Z_STREAM_ERROR:
            case Z_DATA_ERROR:
                throw new IllegalArgumentException(addr.msg);
            default:
                throw  new RuntimeException("unhandled return value");
        }
    }

    private int inflateBytes(ZStream strm, byte[] b, int off, int len)
            throws DataFormatException {
        strm.next_in = this.buf;
        strm.next_in_index = this.off;
        strm.next_out = b;
        strm.next_out_index = off;
        strm.avail_in = this.len;
        strm.avail_out = len;
        int ret = strm.inflate(Z_PARTIAL_FLUSH);
        switch (ret) {
            case Z_STREAM_END:
                _finished = true;
                /* fall through */
            case Z_OK:
                this.off += this.len - strm.avail_in;
                this.len = strm.avail_in;
                return len - strm.avail_out;
            case Z_NEED_DICT:
                this.needDict = true;
                this.off += this.len - strm.avail_in;
                this.len = strm.avail_in;
                return 0;
            case Z_BUF_ERROR:
                return 0;
            case Z_DATA_ERROR:
                throw new DataFormatException(strm.msg);
            case Z_MEM_ERROR:
                throw new OutOfMemoryError();
            default:
                throw new RuntimeException("unhandled ret val " + ret);
        }
    }

    private static int _getAdler(ZStream zs) {
        return (int) zs.getAdler();
    }

    private static void _reset(ZStream addr) {
        addr.inflateReset();
    }

    private static void _end(ZStream addr) {
        int i = addr.inflateEnd();
    }
}
