// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.util.logging;

public class LogRecord
{
    public static final String Ident = "$Id: LogRecord.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    private static long globalSeq;
    private Level level;
    private String msg;
    private long time;
    private long seq;
    private Throwable thrown;
    
    public LogRecord(final Level level, final String msg) {
        this.time = System.currentTimeMillis();
        this.seq = LogRecord.globalSeq++;
        this.thrown = null;
        this.level = level;
        this.msg = msg;
    }
    
    public long getMillis() {
        return this.time;
    }
    
    public String getMessage() {
        return this.msg;
    }
    
    static {
        LogRecord.globalSeq = 0L;
    }
    
    public long getSequenceNumber() {
        return this.seq;
    }
    
    public Throwable getThrown() {
        return this.thrown;
    }
    
    public void setThrown(final Throwable thrown) {
        this.thrown = thrown;
    }
    
    public Level getLevel() {
        return this.level;
    }
}
