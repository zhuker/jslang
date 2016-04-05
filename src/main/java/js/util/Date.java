package js.util;

import org.stjs.javascript.annotation.Namespace;

@Namespace("jsutil")
public class Date {

    private long time;

    public Date(Object... arguments) {
        switch (arguments.length) {
        case 0:
            this.time = (long) new org.stjs.javascript.Date().getTime();
            break;
        default:
            throw new RuntimeException("Date.init " + arguments.length);

        }
    }

    public long getTime() {
        return time;
    }

}
