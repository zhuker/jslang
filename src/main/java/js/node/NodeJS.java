package js.node;

import org.stjs.javascript.annotation.GlobalScope;
import org.stjs.javascript.annotation.Native;
import org.stjs.javascript.annotation.STJSBridge;

@STJSBridge
@GlobalScope
public class NodeJS {
    public static Console console;

    @Native
    public static <T> T require(String str) {
        if (str == "fs") {
            return (T) new FS();
        }
        return null;
    }
}
