package js.util.zip;

import com.jcraft.jzlib.ZStream;

class ZStreamRef {
    ZStream zs;

    ZStreamRef(ZStream strm) {
        this.zs = strm;
    }

    public ZStream address() {
        return zs;
    }

    public void clear() {
        zs = null;
    }
}
