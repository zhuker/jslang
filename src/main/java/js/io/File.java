package js.io;

import static org.stjs.javascript.Global.console;

import org.stjs.javascript.Array;

import js.node.FS;
import js.node.FS.Stats;
import js.node.NodeJS;

public class File {
    private final static FS fs = NodeJS.require("fs");

    private String path;

    public File(Object... arguments) {
        switch (arguments.length) {
        case 1:
            this.path = (String) arguments[0];
            break;
        case 2:
            File dir = (File) arguments[0];
            this.path = dir.path + "/" + arguments[1];
            break;
        default:
            throw new RuntimeException("TODO File.init");
        }
    }

    public long length() {
        Stats statSync = fs.statSync(path);
        if (statSync != null) {
            return statSync.size;
        }
        return 0;
    }

    @Override
    public String toString() {
        return path;
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
        Array<String> readdirSync = fs.readdirSync(path);
        console.log("readdirSync", readdirSync);
        if (readdirSync != null) {
            File[] res = new File[readdirSync.$length()];
            for (int i = 0; i < readdirSync.$length(); i++) {
                res[i] = new File(path + "/" + readdirSync.$get(i));
            }
            return res;
        }
        return null;
    }

    public String getCanonicalPath() {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");
    }

    public void $delete() {
        fs.unlinkSync(path);
    }

    public String getParent() {
        throw new RuntimeException("TODO");
    }

    public boolean mkdirs() {
        _mkdirs(path);
        return isDir(path);
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

    static boolean isDir(String path) {
        try {
            Stats statSync = fs.statSync(path);
            return statSync.isDirectory();
        } catch (Exception e) {
            return false;
        }
    }

    static void _mkdirs(String outDir) {
        int idx = 0;
        if (isDir(outDir)) {
            return;
        }
        while ((idx = outDir.indexOf("/", idx + 1)) >= 0) {
            String path = outDir.substring(0, idx);
            if (!isDir(path)) {
                _mkdir(path);
            }
        }
        if (!isDir(outDir)) {
            _mkdir(outDir);
        }
    }

    private static void _mkdir(String path) {
        console.log("mkdir", path);
        try {
            fs.mkdirSync(path);
        } catch (Exception e) {
            if (isDir(path)) {
                //this happens when two processes are trying to create same dir simultaneously
            } else {
                throw (RuntimeException) e;
            }
        }
    }
}
