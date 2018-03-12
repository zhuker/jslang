package js.util.zip;

import com.jcraft.jzlib.ZStream;

import js.lang.NullPointerException;
import js.lang.ArrayIndexOutOfBoundsException;
import js.util.Arrays;
import js.util.zip.DataFormatException;
import js.lang.IllegalArgumentException;
import js.lang.OutOfMemoryError;

import org.stjs.javascript.JSGlobal;
import org.stjs.javascript.annotation.Native;

import static com.jcraft.jzlib.JZlib.Z_BUF_ERROR;
import static com.jcraft.jzlib.JZlib.Z_DATA_ERROR;
import static com.jcraft.jzlib.JZlib.Z_FINISH;
import static com.jcraft.jzlib.JZlib.Z_MEM_ERROR;
import static com.jcraft.jzlib.JZlib.Z_OK;
import static com.jcraft.jzlib.JZlib.Z_STREAM_END;
import static com.jcraft.jzlib.JZlib.Z_STREAM_ERROR;

/**
 * This class provides support for general purpose compression using the
 * popular ZLIB compression library. The ZLIB compression library was
 * initially developed as part of the PNG graphics standard and is not
 * protected by patents. It is fully described in the specifications at
 * the <a href="package-summary.html#package_description">java.util.zip
 * package description</a>.
 *
 * <p>The following code fragment demonstrates a trivial compression
 * and decompression of a string using <tt>Deflater</tt> and
 * <tt>Inflater</tt>.
 *
 * <blockquote><pre>
 * try {
 *     // Encode a String into bytes
 *     String inputString = "blahblahblah";
 *     byte[] input = inputString.getBytes("UTF-8");
 *
 *     // Compress the bytes
 *     byte[] output = new byte[100];
 *     Deflater compresser = new Deflater();
 *     compresser.setInput(input);
 *     compresser.finish();
 *     int compressedDataLength = compresser.deflate(output);
 *     compresser.end();
 *
 *     // Decompress the bytes
 *     Inflater decompresser = new Inflater();
 *     decompresser.setInput(output, 0, compressedDataLength);
 *     byte[] result = new byte[100];
 *     int resultLength = decompresser.inflate(result);
 *     decompresser.end();
 *
 *     // Decode the bytes into a String
 *     String outputString = new String(result, 0, resultLength, "UTF-8");
 * } catch(java.io.UnsupportedEncodingException ex) {
 *     // handle
 * } catch (java.util.zip.DataFormatException ex) {
 *     // handle
 * }
 * </pre></blockquote>
 *
 * @see         Inflater
 * @author      David Connelly
 */
public
class Deflater {

    private final ZStreamRef zsRef;
    private byte[] buf;
    private int off, len;
    private int level, strategy;
    private boolean setParams;
    private boolean _finish, _finished;
    private long bytesRead;
    private long bytesWritten;

    /**
     * Compression method for the deflate algorithm (the only one currently
     * supported).
     */
    public static final int DEFLATED = 8;

    /**
     * Compression level for no compression.
     */
    public static final int NO_COMPRESSION = 0;

    /**
     * Compression level for fastest compression.
     */
    public static final int BEST_SPEED = 1;

    /**
     * Compression level for best compression.
     */
    public static final int BEST_COMPRESSION = 9;

    /**
     * Default compression level.
     */
    public static final int DEFAULT_COMPRESSION = -1;

    /**
     * Compression strategy best used for data consisting mostly of small
     * values with a somewhat random distribution. Forces more Huffman coding
     * and less string matching.
     */
    public static final int FILTERED = 1;

    /**
     * Compression strategy for Huffman coding only.
     */
    public static final int HUFFMAN_ONLY = 2;

    /**
     * Default compression strategy.
     */
    public static final int DEFAULT_STRATEGY = 0;

    /**
     * Compression flush mode used to achieve best compression result.
     *
     * @see Deflater#deflate4(byte[], int, int, int)
     * @since 1.7
     */
    public static final int NO_FLUSH = 0;

    /**
     * Compression flush mode used to flush out all pending output; may
     * degrade compression for some compression algorithms.
     *
     * @see Deflater#deflate4(byte[], int, int, int)
     * @since 1.7
     */
    public static final int SYNC_FLUSH = 2;

    /**
     * Compression flush mode used to flush out all pending output and
     * reset the deflater. Using this mode too often can seriously degrade
     * compression.
     *
     * @see Deflater#deflate4(byte[], int, int, int)
     * @since 1.7
     */
    public static final int FULL_FLUSH = 3;

    static {
        /* Zip library is loaded from System.initializeSystemClass */
        initIDs();
    }

    /**
     * Creates a new compressor using the specified compression level.
     * If 'nowrap' is true then the ZLIB header and checksum fields will
     * not be used in order to support the compression format used in
     * both GZIP and PKZIP.
     * @param level the compression level (0-9)
     * @param nowrap if true then use GZIP compatible compression
     */
    public Deflater(int level, boolean nowrap) {
        if ((Integer)level == null) {
            level = DEFAULT_COMPRESSION;
        }
        this.level = level;
        this.strategy = DEFAULT_STRATEGY;
        this.zsRef = init(level, DEFAULT_STRATEGY, nowrap);
        this.buf = new byte[0];
    }

    /**
     * Creates a new compressor using the specified compression level.
     * Compressed data will be generated in ZLIB format.
     * @param level the compression level (0-9)
     */
    @Native
    public Deflater(int level) {
        this(level, false);
    }

    /**
     * Creates a new compressor with the default compression level.
     * Compressed data will be generated in ZLIB format.
     */
    @Native
    public Deflater() {
        this(DEFAULT_COMPRESSION, false);
    }

    /**
     * Sets input data for compression. This should be called whenever
     * needsInput() returns true indicating that more input data is required.
     * @param b the input data bytes
     * @param off the start offset of the data
     * @param len the length of the data
     * @see Deflater#needsInput
     */
    public void setInput3(byte[] b, int off, int len) {
        if (b== null) {
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
     * Sets input data for compression. This should be called whenever
     * needsInput() returns true indicating that more input data is required.
     * @param b the input data bytes
     * @see Deflater#needsInput
     */
    public void setInput(byte[] b) {
        setInput3(b, 0, b.length);
    }

    /**
     * Sets preset dictionary for compression. A preset dictionary is used
     * when the history buffer can be predetermined. When the data is later
     * uncompressed with Inflater.inflate(), Inflater.getAdler() can be called
     * in order to get the Adler-32 value of the dictionary required for
     * decompression.
     * @param b the dictionary data bytes
     * @param off the start offset of the data
     * @param len the length of the data
     * @see Inflater#inflate
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
            _setDictionary(zsRef.address(), b, off, len);
        }
    }

    /**
     * Sets preset dictionary for compression. A preset dictionary is used
     * when the history buffer can be predetermined. When the data is later
     * uncompressed with Inflater.inflate(), Inflater.getAdler() can be called
     * in order to get the Adler-32 value of the dictionary required for
     * decompression.
     * @param b the dictionary data bytes
     * @see Inflater#inflate
     * @see Inflater#getAdler
     */
    public void setDictionary(byte[] b) {
        setDictionary3(b, 0, b.length);
    }

    /**
     * Sets the compression strategy to the specified value.
     *
     * <p> If the compression strategy is changed, the next invocation
     * of {@code deflate} will compress the input available so far with
     * the old strategy (and may be flushed); the new strategy will take
     * effect only after that invocation.
     *
     * @param strategy the new compression strategy
     * @exception IllegalArgumentException if the compression strategy is
     *                                     invalid
     */
    public void setStrategy(int strategy) {
        switch (strategy) {
          case DEFAULT_STRATEGY:
          case FILTERED:
          case HUFFMAN_ONLY:
            break;
          default:
            throw new IllegalArgumentException();
        }
        {
            if (this.strategy != strategy) {
                this.strategy = strategy;
                setParams = true;
            }
        }
    }

    /**
     * Sets the compression level to the specified value.
     *
     * <p> If the compression level is changed, the next invocation
     * of {@code deflate} will compress the input available so far
     * with the old level (and may be flushed); the new level will
     * take effect only after that invocation.
     *
     * @param level the new compression level (0-9)
     * @exception IllegalArgumentException if the compression level is invalid
     */
    public void setLevel(int level) {
        if ((level < 0 || level > 9) && level != DEFAULT_COMPRESSION) {
            throw new IllegalArgumentException("invalid compression level");
        }
        {
            if (this.level != level) {
                this.level = level;
                setParams = true;
            }
        }
    }

    /**
     * Returns true if the input data buffer is empty and setInput()
     * should be called in order to provide more input.
     * @return true if the input data buffer is empty and setInput()
     * should be called in order to provide more input
     */
    public boolean needsInput() {
        {
            return len <= 0;
        }
    }

    /**
     * When called, indicates that compression should end with the current
     * contents of the input buffer.
     */
    public void finish() {
        {
            _finish = true;
        }
    }

    /**
     * Returns true if the end of the compressed data output stream has
     * been reached.
     * @return true if the end of the compressed data output stream has
     * been reached
     */
    public boolean finished() {
        {
            return _finished;
        }
    }

    /**
     * Compresses the input data and fills specified buffer with compressed
     * data. Returns actual number of bytes of compressed data. A return value
     * of 0 indicates that {@link #needsInput() needsInput} should be called
     * in order to determine if more input data is required.
     *
     * <p>This method uses {@link #NO_FLUSH} as its compression flush mode.
     * An invocation of this method of the form {@code deflater.deflate(b, off, len)}
     * yields the same result as the invocation of
     * {@code deflater.deflate(b, off, len, Deflater.NO_FLUSH)}.
     *
     * @param b the buffer for the compressed data
     * @param off the start offset of the data
     * @param len the maximum number of bytes of compressed data
     * @return the actual number of bytes of compressed data written to the
     *         output buffer
     */
    public int deflate3(byte[] b, int off, int len) {
        return deflate4(b, off, len, NO_FLUSH);
    }

    /**
     * Compresses the input data and fills specified buffer with compressed
     * data. Returns actual number of bytes of compressed data. A return value
     * of 0 indicates that {@link #needsInput() needsInput} should be called
     * in order to determine if more input data is required.
     *
     * <p>This method uses {@link #NO_FLUSH} as its compression flush mode.
     * An invocation of this method of the form {@code deflater.deflate(b)}
     * yields the same result as the invocation of
     * {@code deflater.deflate(b, 0, b.length, Deflater.NO_FLUSH)}.
     *
     * @param b the buffer for the compressed data
     * @return the actual number of bytes of compressed data written to the
     *         output buffer
     */
    public int deflate(byte[] b) {
        return deflate4(b, 0, b.length, NO_FLUSH);
    }

    /**
     * Compresses the input data and fills the specified buffer with compressed
     * data. Returns actual number of bytes of data compressed.
     *
     * <p>Compression flush mode is one of the following three modes:
     *
     * <ul>
     * <li>{@link #NO_FLUSH}: allows the deflater to decide how much data
     * to accumulate, before producing output, in order to achieve the best
     * compression (should be used in normal use scenario). A return value
     * of 0 in this flush mode indicates that {@link #needsInput()} should
     * be called in order to determine if more input data is required.
     *
     * <li>{@link #SYNC_FLUSH}: all pending output in the deflater is flushed,
     * to the specified output buffer, so that an inflater that works on
     * compressed data can get all input data available so far (In particular
     * the {@link #needsInput()} returns {@code true} after this invocation
     * if enough output space is provided). Flushing with {@link #SYNC_FLUSH}
     * may degrade compression for some compression algorithms and so it
     * should be used only when necessary.
     *
     * <li>{@link #FULL_FLUSH}: all pending output is flushed out as with
     * {@link #SYNC_FLUSH}. The compression state is reset so that the inflater
     * that works on the compressed output data can restart from this point
     * if previous compressed data has been damaged or if random access is
     * desired. Using {@link #FULL_FLUSH} too often can seriously degrade
     * compression.
     * </ul>
     *
     * <p>In the case of {@link #FULL_FLUSH} or {@link #SYNC_FLUSH}, if
     * the return value is {@code len}, the space available in output
     * buffer {@code b}, this method should be invoked again with the same
     * {@code flush} parameter and more output space.
     *
     * @param b the buffer for the compressed data
     * @param off the start offset of the data
     * @param len the maximum number of bytes of compressed data
     * @param flush the compression flush mode
     * @return the actual number of bytes of compressed data written to
     *         the output buffer
     *
     * @throws IllegalArgumentException if the flush mode is invalid
     * @since 1.7
     */
    public int deflate4(byte[] b, int off, int len, int flush) {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new ArrayIndexOutOfBoundsException();
        }
        {
            ensureOpen();
            if (flush == NO_FLUSH || flush == SYNC_FLUSH ||
                flush == FULL_FLUSH) {
                int thisLen = this.len;
                int n = deflateBytes(zsRef.address(), b, off, len, flush);
                bytesWritten += n;
                bytesRead += (thisLen - this.len);
                return n;
            }
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the ADLER-32 value of the uncompressed data.
     * @return the ADLER-32 value of the uncompressed data
     */
    public int getAdler() {
        {
            ensureOpen();
            return _getAdler(zsRef.address());
        }
    }

    /**
     * Returns the total number of uncompressed bytes input so far.
     *
     * <p>Since the number of bytes may be greater than
     * Integer.MAX_VALUE, the {@link #getBytesRead()} method is now
     * the preferred means of obtaining this information.</p>
     *
     * @return the total number of uncompressed bytes input so far
     */
    public int getTotalIn() {
        return (int) getBytesRead();
    }

    /**
     * Returns the total number of uncompressed bytes input so far.
     *
     * @return the total (non-negative) number of uncompressed bytes input so far
     * @since 1.5
     */
    public long getBytesRead() {
        {
            ensureOpen();
            return bytesRead;
        }
    }

    /**
     * Returns the total number of compressed bytes output so far.
     *
     * <p>Since the number of bytes may be greater than
     * Integer.MAX_VALUE, the {@link #getBytesWritten()} method is now
     * the preferred means of obtaining this information.</p>
     *
     * @return the total number of compressed bytes output so far
     */
    public int getTotalOut() {
        return (int) getBytesWritten();
    }

    /**
     * Returns the total number of compressed bytes output so far.
     *
     * @return the total (non-negative) number of compressed bytes output so far
     * @since 1.5
     */
    public long getBytesWritten() {
        {
            ensureOpen();
            return bytesWritten;
        }
    }

    /**
     * Resets deflater so that a new set of input data can be processed.
     * Keeps current compression level and strategy settings.
     */
    public void reset() {
        {
            ensureOpen();
            _reset(zsRef.address());
            _finish = false;
            _finished = false;
            off = len = 0;
            bytesRead = bytesWritten = 0;
        }
    }

    /**
     * Closes the compressor and discards any unprocessed input.
     * This method should be called when the compressor is no longer
     * being used, but will also be called automatically by the
     * finalize() method. Once this method is called, the behavior
     * of the Deflater object is undefined.
     */
    public void end() {
        {
            ZStream addr = zsRef.address();
            zsRef.clear();
            if (addr != null) {
                _end(addr);
                buf = null;
            }
        }
    }

    /**
     * Closes the compressor when garbage is collected.
     */
    protected void finalize() {
        end();
    }

    private void ensureOpen() {
//        assert Thread.holdsLock(zsRef);
        if (zsRef.address() == null)
            throw new NullPointerException("Deflater has been closed");
    }

    private static void initIDs() {
    }

    private static ZStreamRef init(int level, int strategy, boolean nowrap) {
        ZStream strm = new ZStream();
        strm.deflateParams(level, strategy);
        int init = strm.deflateInitIB(level, nowrap);
        switch (init) {
            case Z_OK:
            return new ZStreamRef(strm);
            case Z_MEM_ERROR:
                throw new OutOfMemoryError();
            case Z_STREAM_ERROR:
                throw new IllegalArgumentException(strm.msg);
            default:
                throw new RuntimeException("InternalError");
        }
    }

    private static void _setDictionary(ZStream addr, byte[] b, int off, int len) {
        byte[] dict = off == 0 ? b : Arrays.copyOfRange(b, off, off + len);
        int ret = addr.deflateSetDictionary(dict, len);
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

    private int deflateBytes(ZStream addr, byte[] b, int off, int len,
                             int flush) {
        ZStream strm = addr;

        byte[] this_buf = this.buf;
        int this_off = this.off;
        int this_len = this.len;
        byte[] in_buf;
        byte[] out_buf;
        int res;
        if (this.setParams) {
            int level = this.level;
            int strategy = this.strategy;
            in_buf = this_buf;
            if (in_buf == null) {
                // Throw OOME only when length is not zero
                if (this_len != 0)
                    throw new OutOfMemoryError();
                return 0;
            }
            out_buf = b;
            if (out_buf == null) {
                if (len != 0)
                    throw  new OutOfMemoryError();
                return 0;
            }

            strm.next_in = in_buf;
            strm.next_in_index = this_off;
            strm.next_out = out_buf;
            strm.next_out_index = off;
            strm.avail_in = this_len;
            strm.avail_out = len;
            res = strm.deflateParams(level, strategy);

            switch (res) {
                case Z_OK:
                    this.setParams = false;
                    this_off += this_len - strm.avail_in;
                    this.off = this_off;
                    this.len = strm.avail_in;
                    return len - strm.avail_out;
                case Z_BUF_ERROR:
                    this.setParams = false;
                    return 0;
                default:
                    throw new RuntimeException(strm.msg);
            }
        } else {
            boolean finish = this._finish;
            in_buf = this_buf;
            if (in_buf == null) {
                if (this_len != 0)
                    throw new OutOfMemoryError();
                return 0;
            }
            out_buf = b;
            if (out_buf == null) {
                if (len != 0)
                    throw new OutOfMemoryError();

                return 0;
            }

            strm.next_in = in_buf;
            strm.next_in_index = this_off;
            strm.next_out = out_buf;
            strm.next_out_index = off;
            strm.avail_in = this_len;
            strm.avail_out = len;
            res = strm.deflate(finish ? Z_FINISH : flush);

            switch (res) {
                case Z_STREAM_END:
                    this._finished = true;
                    /* fall through */
                case Z_OK:
                    this_off += this_len - strm.avail_in;
                    this.off = this_off;
                    this.len = strm.avail_in;
                    return len - strm.avail_out;
                case Z_BUF_ERROR:
                    return 0;
                default:
                    throw new RuntimeException(strm.msg);
            }
        }
    }

    private static int _getAdler(ZStream addr) {
        return (int) addr.getAdler();
    }

    private static void _reset(ZStream addr) {
        if (addr.deflateReset() != Z_OK) {
            throw new RuntimeException();
        }
    }

    private static void _end(ZStream addr) {
        if (addr.deflateEnd() == Z_STREAM_ERROR) {
            throw new RuntimeException();
        }
    }
}
