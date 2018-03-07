package js.util;

import org.stjs.javascript.annotation.Native;

import js.io.InputStream;
import js.io.Reader;

public class Properties {

    @Native
	public void load(Reader reader) {
		throw new RuntimeException("TODO");
	}

	public String getProperty(String string) {
		throw new RuntimeException("TODO");
	}

	@Native
    public void load(InputStream is) {
        throw new RuntimeException("TODO Properties.load");
    }

    public Object get(String keyGitRevision) {
        throw new RuntimeException("TODO Properties.get");
    }

}
