package js.util;

public class TimeZone {

	public static TimeZone getTimeZone(String string) {
	    return instance;
	}
	
	private final static TimeZone instance = new TimeZone();

}
