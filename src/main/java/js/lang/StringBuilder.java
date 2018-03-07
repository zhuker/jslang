package js.lang;

import org.stjs.javascript.annotation.Native;

public class StringBuilder {

    private String str = "";

    public StringBuilder(Object... arguments) {
        if (arguments.length == 0) {
        } else {
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
