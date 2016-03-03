package js.node;

import org.stjs.javascript.annotation.Native;
import org.stjs.javascript.annotation.STJSBridge;
import org.stjs.javascript.typed.ArrayBuffer;

@STJSBridge
public class FS {

    @Native
    public int openSync(String path, String string) {
        throw new RuntimeException("TODO FS.openSync");
    }

    @Native
    public void closeSync(int fd) {
        throw new RuntimeException("TODO FS.closeSync");
    }

    @Native
    public ArrayBuffer readFileSync(String path) {
        throw new RuntimeException("TODO FS.readFileSync");
    }

}
