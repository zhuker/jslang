const fs = require('fs');

const javasrc = "src";
const stjssrc = "jcodec";

fs.copyFileSync(stjssrc + '/Arrays.java', javasrc + '/main/java/js/util/Arrays.java');
fs.copyFileSync(stjssrc + '/ByteBuffer.java', javasrc + '/main/java/js/nio/ByteBuffer.java');
