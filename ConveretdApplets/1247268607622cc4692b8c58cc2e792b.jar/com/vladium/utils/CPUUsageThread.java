// 
// Decompiled by Procyon v0.5.30
// 

package com.vladium.utils;

import java.util.ArrayList;

public class CPUUsageThread extends Thread
{
    public static final int DEFAULT_SAMPLING_INTERVAL = 2000;
    private long m_samplingInterval;
    private final ArrayList m_listeners;
    private static CPUUsageThread s_singleton;
    
    public static synchronized CPUUsageThread getCPUThreadUsageThread() {
        if (CPUUsageThread.s_singleton == null) {
            CPUUsageThread.s_singleton = new CPUUsageThread(2000L);
        }
        return CPUUsageThread.s_singleton;
    }
    
    public synchronized long setSamplingInterval(final long samplingInterval) {
        if (samplingInterval <= 0L) {
            throw new IllegalArgumentException("must be positive: samplingInterval");
        }
        final long samplingInterval2 = this.m_samplingInterval;
        this.m_samplingInterval = samplingInterval;
        return samplingInterval2;
    }
    
    public synchronized void addUsageEventListener(final IUsageEventListener usageEventListener) {
        if (usageEventListener != null) {
            this.m_listeners.add(usageEventListener);
        }
    }
    
    public synchronized void removeUsageEventListener(final IUsageEventListener usageEventListener) {
        if (usageEventListener != null) {
            this.m_listeners.remove(usageEventListener);
        }
    }
    
    @Override
    public void run() {
        while (!this.isInterrupted()) {
            this.notifyListeners(SystemInformation.makeCPUUsageSnapshot());
            final long samplingInterval;
            synchronized (this) {
                samplingInterval = this.m_samplingInterval;
            }
            try {
                Thread.sleep(samplingInterval);
            }
            catch (InterruptedException ex) {
                return;
            }
        }
        synchronized (CPUUsageThread.class) {
            CPUUsageThread.s_singleton = null;
        }
    }
    
    protected CPUUsageThread(final long samplingInterval) {
        this.setName(this.getClass().getName() + " [interval: " + samplingInterval + " ms]");
        this.setDaemon(true);
        this.setSamplingInterval(samplingInterval);
        this.m_listeners = new ArrayList();
    }
    
    private void notifyListeners(final SystemInformation.CPUUsageSnapshot cpuUsageSnapshot) {
        final ArrayList list;
        synchronized (this) {
            list = (ArrayList)this.m_listeners.clone();
        }
        for (int i = 0; i < list.size(); ++i) {
            list.get(i).accept(cpuUsageSnapshot);
        }
    }
    
    public interface IUsageEventListener
    {
        void accept(final SystemInformation.CPUUsageSnapshot p0);
    }
}
