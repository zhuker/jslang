package js.lang;

import org.stjs.javascript.Array;
import org.stjs.javascript.annotation.Adapter;
import org.stjs.javascript.annotation.STJSBridge;
import org.stjs.javascript.annotation.Template;

@Adapter
@STJSBridge
public class VGObjectAdapter {

    @Template("adapter")
    public native static <T> T create(Class<Object> o, Object _prototype);

    @Template("adapter")
    public native static <T> T assign(Class<Object> o, T _instance, Object fromJson);

    @Template("adapter")
    public native static Array<String> keys(Class<Object> o, Object _instance);

}
