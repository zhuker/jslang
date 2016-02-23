package js.util.logging;

public class Level {
	private static final String defaultBundle = "js.util.logging.resources.logging";
    public static final Level ALL = new Level("ALL", Integer.MIN_VALUE, defaultBundle);
	public static final Level FINE = new Level("FINE", 500, defaultBundle);
    public static final Level WARNING = new Level("WARNING", 900, defaultBundle);
	private String name;
	private int value;
	private String resourceBundleName;
	private String localizedLevelName;
	private String cachedLocale;

	/**
	 * Create a named Level with a given integer value and a given localization resource name.
	 * <p>
	 * 
	 * @param name
	 *            the name of the Level, for example "SEVERE".
	 * @param value
	 *            an integer value for the level.
	 * @param resourceBundleName
	 *            name of a resource bundle to use _in localizing the given name. If the resourceBundleName is null or an
	 *            empty string, it is ignored.
	 * @throws NullPointerException
	 *             if the name is null
	 */
	private Level(String name, int value, String resourceBundleName) {
		if (name == null) {
			throw new js.lang.NullPointerException();
		}
		this.name = name;
		this.value = value;
		this.resourceBundleName = resourceBundleName;
		this.localizedLevelName = resourceBundleName == null ? name : null;
		this.cachedLocale = null;
	}
}
