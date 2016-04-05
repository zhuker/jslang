package js.lang;

import static org.stjs.javascript.JSStringAdapterBase.fromCharCode;

public class Character {
    public final char value;

    public Character(char value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return fromCharCode(String.class, value);
    }

}
