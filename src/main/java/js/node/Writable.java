package js.node;

import org.stjs.javascript.annotation.STJSBridge;

@STJSBridge
public interface Writable {
    public void write(Object o);
}
