// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

public class Query
{
    public static final int POSITION = 1;
    public static final int DURATION = 2;
    public static final int LATENCY = 3;
    public static final int JITTER = 4;
    public static final int RATE = 5;
    public static final int SEEKING = 6;
    public static final int SEGMENT = 7;
    public static final int CONVERT = 8;
    public static final int FORMATS = 9;
    private int type;
    private int format;
    private long value;
    
    private Query(final int type) {
        this.value = -1L;
        this.type = type;
    }
    
    public int getType() {
        return this.type;
    }
    
    public static Query newPosition(final int format) {
        final Query q = new Query(1);
        q.format = format;
        return q;
    }
    
    public void setPosition(final int format, final long position) {
        this.format = format;
        this.value = position;
    }
    
    public int parsePositionFormat() {
        return this.format;
    }
    
    public long parsePositionValue() {
        return this.value;
    }
    
    public static Query newDuration(final int format) {
        final Query q = new Query(2);
        q.format = format;
        return q;
    }
    
    public void setDuration(final int format, final long position) {
        this.format = format;
        this.value = position;
    }
    
    public int parseDurationFormat() {
        return this.format;
    }
    
    public long parseDurationValue() {
        return this.value;
    }
}
