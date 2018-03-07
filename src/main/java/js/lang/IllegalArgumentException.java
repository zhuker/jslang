package js.lang;

import org.stjs.javascript.annotation.Native;

public class IllegalArgumentException extends java.lang.RuntimeException {
	public IllegalArgumentException(String msg) {
		super(msg);
	}

	@Native
    public IllegalArgumentException() {
	    this(null);
    }
	

}
