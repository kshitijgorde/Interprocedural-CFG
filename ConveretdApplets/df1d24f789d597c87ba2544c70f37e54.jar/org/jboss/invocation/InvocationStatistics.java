// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.util.HashMap;
import java.util.Iterator;
import java.lang.reflect.Method;
import EDU.oswego.cs.dl.util.concurrent.ConcurrentReaderHashMap;
import java.util.Map;
import java.io.Serializable;

public class InvocationStatistics implements Serializable
{
    private static final long serialVersionUID = -8031193044335393420L;
    private Map methodStats;
    public long concurrentCalls;
    public long maxConcurrentCalls;
    public long lastResetTime;
    
    public InvocationStatistics() {
        this.concurrentCalls = 0L;
        this.maxConcurrentCalls = 0L;
        this.lastResetTime = System.currentTimeMillis();
        this.methodStats = (Map)new ConcurrentReaderHashMap();
    }
    
    public void updateStats(final Method m, final long elapsed) {
        TimeStatistic stat = this.methodStats.get(m);
        if (stat == null) {
            stat = new TimeStatistic();
            this.methodStats.put(m, stat);
        }
        final TimeStatistic timeStatistic = stat;
        ++timeStatistic.count;
        final TimeStatistic timeStatistic2 = stat;
        timeStatistic2.totalTime += elapsed;
        if (stat.minTime > elapsed) {
            stat.minTime = elapsed;
        }
        if (stat.maxTime < elapsed) {
            stat.maxTime = elapsed;
        }
    }
    
    public synchronized void callIn() {
        ++this.concurrentCalls;
        if (this.concurrentCalls > this.maxConcurrentCalls) {
            this.maxConcurrentCalls = this.concurrentCalls;
        }
    }
    
    public synchronized void callOut() {
        --this.concurrentCalls;
    }
    
    public void resetStats() {
        synchronized (this.methodStats) {
            for (final TimeStatistic stat : this.methodStats.values()) {
                stat.reset();
            }
        }
        this.maxConcurrentCalls = 0L;
        this.lastResetTime = System.currentTimeMillis();
    }
    
    public Map getStats() {
        return this.methodStats;
    }
    
    public String toString() {
        final StringBuffer tmp = new StringBuffer("<InvocationStatistics concurrentCalls='");
        tmp.append(this.concurrentCalls);
        tmp.append("' >\n");
        final HashMap copy = new HashMap(this.methodStats);
        for (final Map.Entry entry : copy.entrySet()) {
            final TimeStatistic stat = entry.getValue();
            if (stat != null) {
                tmp.append("<method name='");
                tmp.append(entry.getKey());
                tmp.append("' count='");
                tmp.append(stat.count);
                tmp.append("' minTime='");
                tmp.append(stat.minTime);
                tmp.append("' maxTime='");
                tmp.append(stat.maxTime);
                tmp.append("' totalTime='");
                tmp.append(stat.totalTime);
                tmp.append("' />\n");
            }
        }
        tmp.append("</InvocationStatistics>");
        return tmp.toString();
    }
    
    public class TimeStatistic implements Serializable
    {
        private static final long serialVersionUID = -8689933338506854386L;
        public volatile long count;
        public volatile long minTime;
        public volatile long maxTime;
        public volatile long totalTime;
        
        public TimeStatistic() {
            this.minTime = Long.MAX_VALUE;
        }
        
        public void reset() {
            this.count = 0L;
            this.minTime = Long.MAX_VALUE;
            this.maxTime = 0L;
            this.totalTime = 0L;
        }
    }
}
