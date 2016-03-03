package js.lang;

import org.stjs.javascript.annotation.GlobalScope;
import org.stjs.javascript.annotation.Native;
import org.stjs.javascript.annotation.STJSBridge;

@STJSBridge
@GlobalScope
public class StJs {
    public static StJs stjs;

    @Native
    public boolean isInstanceOf(Class child, Class parent) {
        return false;
    }

}
