package js.io;

public class File {

    private String path;

    public File(Object... arguments) {
        if (arguments.length == 1) {
            this.path = (String) arguments[0];
        } else {
            throw new RuntimeException("TODO File.init");
        }
    }

    public long length() {
        throw new RuntimeException("TODO");
    }

    public static File createTempFile(String string, String string2) {
        throw new RuntimeException("TODO");
    }

    public String getAbsolutePath() {
        throw new RuntimeException("TODO");
    }

    public boolean isDirectory() {
        throw new RuntimeException("TODO");
    }

    public String getName() {
        throw new RuntimeException("TODO");
    }

    public boolean exists() {
        throw new RuntimeException("TODO");
    }

    public File getParentFile() {
        throw new RuntimeException("TODO");
    }

    public void renameTo(File src) {
        throw new RuntimeException("TODO");
    }

    public void deleteOnExit() {
        throw new RuntimeException("TODO");
    }

    public File[] listFiles(Object... arguments) {
        throw new RuntimeException("TODO");
    }

    public String getCanonicalPath() {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");
    }

    public void $delete() {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");

    }

    public String getParent() {
        throw new RuntimeException("TODO");
    }

    public boolean mkdirs() {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");
    }

    public long lastModified() {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");
    }

    public void setLastModified(long lastModified) {
        throw new RuntimeException("TODO");

    }
    
    public String getPath() {
        return path;
    }

}
