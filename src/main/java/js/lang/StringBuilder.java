package js.lang;

import static org.stjs.javascript.Global.console;

import org.stjs.javascript.JSGlobal;
import org.stjs.javascript.annotation.Native;

public class StringBuilder {

    private String str = "";

    public StringBuilder(Object... arguments) {
        switch (arguments.length) {
        case 0:
            break;
        case 1:
            if (JSGlobal.typeof(arguments[0]) == "string") {
                str = (String) arguments[0];
            }
            break;
        default:
            console.log("arguments", arguments);
            throw new RuntimeException("TODO StringBuilder " + arguments.length);
        }
    }

    public StringBuilder append(Object o, int start, int end) {
        str += o;
        return this;
    }
    
    @Native
    public StringBuilder append(Object o) {
        str += o;
        return this;
    }

    @NeedsSpeedOptimization
    public void setLength(int i) {
    }

    @Override
    public String toString() {
        return str;
    }

}
