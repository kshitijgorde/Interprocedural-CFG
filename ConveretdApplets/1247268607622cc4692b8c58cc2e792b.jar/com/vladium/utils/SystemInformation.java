// 
// Decompiled by Procyon v0.5.30
// 

package com.vladium.utils;

public abstract class SystemInformation
{
    public static final int MIN_ELAPSED_TIME = 100;
    
    public static CPUUsageSnapshot makeCPUUsageSnapshot() {
        return new CPUUsageSnapshot(System.currentTimeMillis(), getProcessCPUTime());
    }
    
    public static double getProcessCPUUsage(final CPUUsageSnapshot cpuUsageSnapshot, final CPUUsageSnapshot cpuUsageSnapshot2) {
        if (cpuUsageSnapshot == null) {
            throw new IllegalArgumentException("null input: start");
        }
        if (cpuUsageSnapshot2 == null) {
            throw new IllegalArgumentException("null input: end");
        }
        if (cpuUsageSnapshot2.m_time < cpuUsageSnapshot.m_time + 100L) {
            throw new IllegalArgumentException("end time must be at least 100 ms later than start time");
        }
        return (cpuUsageSnapshot2.m_CPUTime - cpuUsageSnapshot.m_CPUTime) / (cpuUsageSnapshot2.m_time - cpuUsageSnapshot.m_time);
    }
    
    public static native int getProcessID();
    
    public static native long getProcessCPUTime();
    
    public static native double getProcessCPUUsage();
    
    public static final class CPUUsageSnapshot
    {
        public final long m_time;
        public final long m_CPUTime;
        
        private CPUUsageSnapshot(final long time, final long cpuTime) {
            this.m_time = time;
            this.m_CPUTime = cpuTime;
        }
    }
}
