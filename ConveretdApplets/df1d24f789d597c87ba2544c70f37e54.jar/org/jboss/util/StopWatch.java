// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.io.Serializable;

public class StopWatch implements Serializable, Cloneable
{
    static final long serialVersionUID = 4628094303187435707L;
    protected long total;
    protected long start;
    protected long stop;
    protected int count;
    protected boolean running;
    
    public StopWatch() {
        this.total = 0L;
        this.start = -1L;
        this.stop = -1L;
        this.count = 0;
        this.running = false;
    }
    
    public StopWatch(final boolean running) {
        this.total = 0L;
        this.start = -1L;
        this.stop = -1L;
        this.count = 0;
        this.running = false;
        if (running) {
            this.start();
        }
    }
    
    public void start(final boolean reset) {
        if (!this.running) {
            if (reset) {
                this.reset();
            }
            this.start = System.currentTimeMillis();
            this.running = true;
        }
    }
    
    public void start() {
        this.start(false);
    }
    
    public long stop() {
        long lap = 0L;
        if (this.running) {
            ++this.count;
            this.stop = System.currentTimeMillis();
            lap = this.stop - this.start;
            this.total += lap;
            this.running = false;
        }
        return lap;
    }
    
    public void reset() {
        this.start = -1L;
        this.stop = -1L;
        this.total = 0L;
        this.count = 0;
        this.running = false;
    }
    
    public int getLapCount() {
        return this.count;
    }
    
    public long getLapTime() {
        if (this.start == -1L) {
            return 0L;
        }
        if (this.running) {
            return System.currentTimeMillis() - this.start;
        }
        return this.stop - this.start;
    }
    
    public long getAverageLapTime() {
        return (this.count == 0) ? 0L : (this.getLapTime() / this.getLapCount());
    }
    
    public long getTime() {
        if (this.start == -1L) {
            return 0L;
        }
        if (this.running) {
            return this.total + System.currentTimeMillis() - this.start;
        }
        return this.total;
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public String toString() {
        final StringBuffer buff = new StringBuffer();
        if (this.running) {
            this.formatElapsedTime(buff, this.getTime());
            if (this.count >= 1) {
                buff.append(", count=").append(this.count);
                buff.append(", current=");
                this.formatElapsedTime(buff, this.getLapTime());
            }
        }
        else {
            this.formatElapsedTime(buff, this.getTime());
            if (this.count > 1) {
                buff.append(", count=").append(this.count);
                buff.append(", average=");
                this.formatElapsedTime(buff, this.getAverageLapTime());
            }
        }
        return buff.toString();
    }
    
    private void formatElapsedTime(final StringBuffer buff, final long lapsed) {
        final long m = lapsed / 60000L;
        if (m != 0L) {
            buff.append(m).append("m:");
        }
        final long s = (lapsed - 60000L * m) / 1000L;
        if (s != 0L) {
            buff.append(s).append("s:");
        }
        final long ms = lapsed - 60000L * m - 1000L * s;
        buff.append(ms).append("ms");
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
    
    public static StopWatch makeSynchronized(final StopWatch watch) {
        return new Wrapper(watch) {
            public synchronized void start(final boolean reset) {
                this.watch.start(reset);
            }
            
            public synchronized void start() {
                this.watch.start();
            }
            
            public synchronized long stop() {
                return this.watch.stop();
            }
            
            public synchronized void reset() {
                this.watch.reset();
            }
            
            public synchronized long getLapTime() {
                return this.watch.getLapTime();
            }
            
            public synchronized long getAverageLapTime() {
                return this.watch.getAverageLapTime();
            }
            
            public synchronized int getLapCount() {
                return this.watch.getLapCount();
            }
            
            public synchronized long getTime() {
                return this.watch.getTime();
            }
            
            public synchronized boolean isRunning() {
                return this.watch.isRunning();
            }
            
            public synchronized String toString() {
                return this.watch.toString();
            }
        };
    }
    
    private static class Wrapper extends StopWatch
    {
        protected StopWatch watch;
        
        public Wrapper(final StopWatch watch) {
            this.watch = watch;
        }
        
        public void start(final boolean reset) {
            this.watch.start(reset);
        }
        
        public void start() {
            this.watch.start();
        }
        
        public long stop() {
            return this.watch.stop();
        }
        
        public void reset() {
            this.watch.reset();
        }
        
        public long getLapTime() {
            return this.watch.getLapTime();
        }
        
        public long getAverageLapTime() {
            return this.watch.getAverageLapTime();
        }
        
        public int getLapCount() {
            return this.watch.getLapCount();
        }
        
        public long getTime() {
            return this.watch.getTime();
        }
        
        public boolean isRunning() {
            return this.watch.isRunning();
        }
        
        public String toString() {
            return this.watch.toString();
        }
    }
}
