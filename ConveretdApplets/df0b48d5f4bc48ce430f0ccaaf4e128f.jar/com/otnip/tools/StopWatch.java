// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools;

public class StopWatch
{
    private long startTime_ms;
    private long startTime_ns;
    private String label;
    
    public StopWatch() {
        this.label = "";
        this.start();
    }
    
    public StopWatch(final String label) {
        this.label = "";
        this.start(label);
    }
    
    public void start() {
        this.startTime_ms = System.currentTimeMillis();
        this.startTime_ns = System.nanoTime();
    }
    
    public void start(final String label) {
        this.label = label;
        this.start();
    }
    
    public String stop() {
        final long stopTime_ms = System.currentTimeMillis();
        final long stopTime_ns = System.nanoTime();
        final long dt = stopTime_ms - this.startTime_ms;
        String result = "";
        if (dt > 100L) {
            result = "Elapsed Time (" + this.label + "):  " + dt + " ms";
        }
        else {
            result = "Elapsed Time (" + this.label + "):  " + (stopTime_ns - this.startTime_ns) + " ns";
        }
        return result;
    }
    
    public String toString() {
        return this.stop();
    }
    
    @Deprecated
    public long getElapsedTime() {
        return System.currentTimeMillis() - this.startTime_ms;
    }
    
    public long getElapsedTimeMs() {
        return System.currentTimeMillis() - this.startTime_ms;
    }
    
    public long getElapsedTimeNs() {
        return System.nanoTime() - this.startTime_ns;
    }
}
