package js.node;

import org.stjs.javascript.annotation.Native;
import org.stjs.javascript.annotation.STJSBridge;
import org.stjs.javascript.typed.ArrayBuffer;

@STJSBridge
public class FS {

    public static class Stats {
        public int dev;
        public long ino;
        public int mode;
        public int nlink;
        public int uid;
        public int gid;
        public int rdev;
        public long size;
        public int blksize;
        public int blocks;
        public Object atime;
        public Object mtime;
        public Object ctime;
        public Object birthtime;
    }

    @Native
    public int openSync(String path, String string) {
        throw new RuntimeException("TODO FS.openSync");
    }

    @Native
    public void closeSync(int fd) {
        throw new RuntimeException("TODO FS.closeSync");
    }

    @Native
    public ArrayBuffer readFileSync(String path) {
        throw new RuntimeException("TODO FS.readFileSync");
    }

    @Native
    public Stats statSync(String path) {
        throw new RuntimeException("TODO FS.statSync");
    }

}
