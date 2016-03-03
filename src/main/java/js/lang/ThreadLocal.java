package js.lang;

public class ThreadLocal<T> {

    T value;

    public T get() {
        return value;
    }

    public void set(T val) {
        this.value = val;
    }

}
