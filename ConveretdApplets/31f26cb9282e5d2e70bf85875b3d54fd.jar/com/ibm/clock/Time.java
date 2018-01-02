// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.clock;

public class Time
{
    public static final int HOURS_IN_DAY = 24;
    public static final int MINUTES_IN_HOUR = 60;
    public static final int SECONDS_IN_MINUTE = 60;
    protected static final long MILLISECONDS_IN_DAY = 86400000L;
    protected static final long MILLISECONDS_IN_HOUR = 3600000L;
    protected static final long MILLISECONDS_IN_MINUTE = 60000L;
    protected static final long MILLISECONDS_IN_SECOND = 1000L;
    private long time;
    
    public Time() {
        this(0L);
    }
    
    protected Time(final long inMillis) throws IllegalArgumentException {
        this.setInMillis(inMillis);
    }
    
    public Time(final int n, final int n2, final int n3) {
        if (!this.isProperHour(n) || !this.isProperMinute(n2) || !this.isProperSecond(n3)) {
            throw new IllegalArgumentException();
        }
        this.setInMillis(this.computeTime(n, n2, n3));
    }
    
    protected synchronized void setInMillis(final long time) throws IllegalArgumentException {
        if (!this.isProperTime(time)) {
            throw new IllegalArgumentException();
        }
        this.time = time;
    }
    
    public long getInMillis() {
        return this.time;
    }
    
    public int getHour() {
        return (int)(this.getInMillis() / 3600000L);
    }
    
    public synchronized void setHour(final int n) throws IllegalArgumentException {
        if (!this.isProperHour(n)) {
            throw new IllegalArgumentException();
        }
        this.setInMillis(this.computeTime(n, this.getMinute(), this.getSecond()));
    }
    
    public int getMinute() {
        return (int)(this.getInMillis() % 3600000L / 60000L);
    }
    
    public void setMinute(final int n) {
        if (!this.isProperMinute(n)) {
            throw new IllegalArgumentException();
        }
        this.setInMillis(this.computeTime(this.getHour(), n, this.getSecond()));
    }
    
    public int getSecond() {
        return (int)(this.getInMillis() % 60000L / 1000L);
    }
    
    public void setSecond(final int n) {
        if (!this.isProperSecond(n)) {
            throw new IllegalArgumentException();
        }
        this.setInMillis(this.computeTime(this.getHour(), this.getMinute(), n));
    }
    
    private boolean isProperTime(final long n) {
        return n >= 0L && n < 86400000L;
    }
    
    private boolean isProperHour(final int n) {
        return n >= 0 && n < 24;
    }
    
    private boolean isProperMinute(final int n) {
        return n >= 0 && n < 60;
    }
    
    private boolean isProperSecond(final int n) {
        return n >= 0 && n < 60;
    }
    
    private long computeTime(final int n, final int n2, final int n3) {
        return n * 3600000L + n2 * 60000L + n3 * 1000L;
    }
}
