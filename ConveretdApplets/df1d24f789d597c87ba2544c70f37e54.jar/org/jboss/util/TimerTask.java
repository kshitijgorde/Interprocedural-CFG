// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

public abstract class TimerTask implements Executable, Comparable
{
    static final int NEW = 1;
    static final int SCHEDULED = 2;
    static final int EXECUTED = 3;
    static final int CANCELLED = 4;
    private final Object m_lock;
    private int m_state;
    private final long m_period;
    private long m_nextExecutionTime;
    
    protected TimerTask() {
        this.m_lock = new Object();
        this.m_state = 1;
        this.m_period = 0L;
    }
    
    protected TimerTask(final long period) {
        this.m_lock = new Object();
        this.m_state = 1;
        if (period < 0L) {
            throw new IllegalArgumentException("Period can't be negative");
        }
        this.m_period = period;
    }
    
    public boolean cancel() {
        synchronized (this.getLock()) {
            final boolean ret = this.m_state == 2;
            this.m_state = 4;
            return ret;
        }
    }
    
    public abstract void execute() throws Exception;
    
    public int compareTo(final Object other) {
        if (other == this) {
            return 0;
        }
        final TimerTask t = (TimerTask)other;
        final long diff = this.getNextExecutionTime() - t.getNextExecutionTime();
        return (int)diff;
    }
    
    Object getLock() {
        return this.m_lock;
    }
    
    void setState(final int state) {
        synchronized (this.getLock()) {
            this.m_state = state;
        }
    }
    
    int getState() {
        synchronized (this.getLock()) {
            return this.m_state;
        }
    }
    
    boolean isPeriodic() {
        return this.m_period > 0L;
    }
    
    long getNextExecutionTime() {
        synchronized (this.getLock()) {
            return this.m_nextExecutionTime;
        }
    }
    
    void setNextExecutionTime(final long time) {
        synchronized (this.getLock()) {
            this.m_nextExecutionTime = time;
        }
    }
    
    protected long getPeriod() {
        return this.m_period;
    }
}
