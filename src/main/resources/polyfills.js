Integer.toHexString = function(i) {
    return i.toString(16);
};

Boolean.parseBoolean = function(s) {
    return ((s != null) && "true".equalsIgnoreCase(s));
};