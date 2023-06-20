package org.sreejit.timeseriessimple.models;

public enum BucketType {
    DAY(86400000l), MONTH(2592000000l);

    private long milliseconds;

    BucketType(final long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public long getMilliseconds() {
        return milliseconds;
    }
}
