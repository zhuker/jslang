package js.util.regex;

import org.stjs.javascript.RegExp;

public class Pattern {
    private RegExp r;

    private Pattern(RegExp r) {
        this.r = r;
    }

	public static Pattern compile(String string) {
	    RegExp r = new RegExp(string);
	    return new Pattern(r);
	}

	public Matcher matcher(String string) {
		throw new RuntimeException("TODO");
	}

}
