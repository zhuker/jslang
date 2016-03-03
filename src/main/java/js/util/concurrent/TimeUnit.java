package js.util.concurrent;

public final class TimeUnit {
    public final static TimeUnit MILLISECONDS = new TimeUnit();

    public TimeUnit() {
    }

    public long toMillis(long period) {
        return period;
    }

}
