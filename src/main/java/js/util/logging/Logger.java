package js.util.logging;

import static js.node.NodeJS.console;

import js.lang.FixMe;
import js.util.Collections;
import js.util.List;

public class Logger {
    private final static Logger instance = new Logger("ctx");

    private Logger(String ctx) {
    }

    public static Logger getLogger(String string) {
        return instance;
    }

    public void log(Object... arguments) {
        console.log(arguments);
    }

    public void finest(String string) {
        console.log(string);
    }

    public void warning(String string) {
        console.log(string);
    }

    @FixMe
    public List<Handler> getHandlers() {
        return Collections.<Handler>emptyList();
    }

    @FixMe
    public void removeHandler(Handler h) {
    }

    @FixMe
    public void setLevel(Level all) {
    }

    @FixMe
    public void addHandler(Handler h) {
    }

}
