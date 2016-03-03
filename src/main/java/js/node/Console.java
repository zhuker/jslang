package js.node;

import org.stjs.javascript.annotation.Native;
import org.stjs.javascript.annotation.STJSBridge;

@STJSBridge
public class Console {
    @Native
    public void log(Object... arguments) {
        System.out.println(arguments);
    }

}
