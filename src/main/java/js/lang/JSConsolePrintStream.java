package js.lang;

import static org.stjs.javascript.Global.console;

import js.io.PrintStream;

public class JSConsolePrintStream extends PrintStream {

    public JSConsolePrintStream() {
        super(null);
    }

    @Override
    public void println(Object... arguments) {
        if (arguments.length == 1) {
            console.log(arguments[0]);
        } else {
            console.log("println", arguments);
        }
    }

    @Override
    public void print(Object o) {
        console.log("print", o);
    }

    @Override
    public void printf(Object... arguments) {
        console.log("printf", arguments);
    }

}
