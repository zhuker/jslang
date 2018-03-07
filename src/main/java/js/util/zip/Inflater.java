package js.util.zip;

import org.stjs.javascript.annotation.Native;

public class Inflater {

    @Native
    public int inflate(byte[] b) throws DataFormatException {
        throw new RuntimeException("TODO Inflater.inflate");
    }

    public boolean needsInput() {
        return false;
    }

    public void setInput(byte[] b) {
        throw new RuntimeException("TODO Inflater.setInput");
    }

    public int inflate(byte[] b, int off, int len) throws DataFormatException {
        throw new RuntimeException("TODO Inflater.inflate");
    }

}
