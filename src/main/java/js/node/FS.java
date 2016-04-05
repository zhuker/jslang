package js.node;

import org.stjs.javascript.Array;
import org.stjs.javascript.Map;
import org.stjs.javascript.annotation.Native;
import org.stjs.javascript.annotation.STJSBridge;
import org.stjs.javascript.functions.Callback2;
import org.stjs.javascript.functions.Callback3;
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

        @Native
        public boolean isFile() {
            throw new RuntimeException("TODO Stats.isFile");
        }

        @Native
        public boolean isDirectory() {
            throw new RuntimeException("TODO Stats.isDirectory");
        }
    }

    @Native
    public int openSync(String path, String flags) {
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
    public String readFileSync(String path, Map<String, Object> options) {
        throw new RuntimeException("TODO FS.readFileSync");
    }

    @Native
    public Stats statSync(String path) {
        throw new RuntimeException("TODO FS.statSync");
    }

    @Native
    public void mkdirSync(String path) {
        throw new RuntimeException("TODO FS.mkdirSync");
    }

    @Native
    public void writeFileSync(String file, Object data) {
        throw new RuntimeException("TODO FS.writeFileSync");
    }

    @Native
    public void readdir(String path, Callback2<?, ?> callback) {
        throw new RuntimeException("TODO FS.readdir");
    }

    /**
     * @throws Error
     *             This throws if any accessibility checks fail, and does
     *             nothing otherwise.
     * 
     * @param path
     */
    @Native
    public void accessSync(String path) {
        throw new RuntimeException("TODO FS.existsSync");
    }

    @Native
    public Array<String> readdirSync(String path) {
        throw new RuntimeException("TODO FS.readdirSync");
    }

    @Native
    public Stats lstatSync(String path) {
        throw new RuntimeException("TODO FS.lstatSync");
    }

    @Native
    public void unlinkSync(String curPath) {
        throw new RuntimeException("TODO FS.unlinkSync");
    }

    @Native
    public void rmdirSync(String path) {
        throw new RuntimeException("TODO FS.rmdirSync");
    }

    @Native
    public void read(int fd, Buffer buffer, int offset, int length, Long position,
            Callback3<Error, Integer, Buffer> callback) {
        throw new RuntimeException("TODO FS.read");
    }

    @Native
    public int readSync(int fd, Buffer buffer, int start, int remaining, Long position) {
        throw new RuntimeException("TODO FS.readSync");
    }

    @Native
    public Writable createWriteStream(String path, Map<String, String> flags) {
        throw new RuntimeException("TODO FS.createWriteStream");
    }

    @Native
    public int writeSync(int fd, Object data, Long position, String encoding) {
        throw new RuntimeException("TODO FS.writeSync");
    }

    @Native
    public int writeSync(int fd, Buffer chunk, int offset, int length) {
        throw new RuntimeException("TODO FS.writeSync");
    }
    @Native
    public int writeSync(int fd, Buffer chunk, int offset, int length, Long position) {
        throw new RuntimeException("TODO FS.writeSync");
    }

    @Native
    public Stats fstatSync(int fd) {
        throw new RuntimeException("TODO FS.fstatSync");
    }

}
