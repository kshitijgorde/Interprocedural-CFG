// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.comm;

import com.controlj.green.applets.TrendPlot;
import com.controlj.green.applets.GraphHandler;

public class PollTrends implements Runnable
{
    private long waitInterval;
    private GraphHandler handler;
    private volatile boolean go;
    
    public PollTrends(final GraphHandler handler) {
        this.waitInterval = 10000L;
        this.go = true;
        this.handler = handler;
    }
    
    public long getPollInterval() {
        return this.waitInterval;
    }
    
    public void setPollInterval(final long waitInterval) {
        if (TrendPlot.traceLevel >= 2) {
            TrendPlot.trace(this.getClass(), "Poll interval set to: " + waitInterval);
        }
        this.waitInterval = waitInterval;
    }
    
    public void run() {
        this.go = true;
        if (TrendPlot.traceLevel >= 1) {
            TrendPlot.trace(this.getClass(), "Polling for current data every: " + this.waitInterval);
        }
        while (this.go) {
            try {
                this.handler.requestCurrentData();
                Thread.currentThread();
                Thread.sleep(this.waitInterval);
            }
            catch (InterruptedException e) {}
        }
        if (TrendPlot.traceLevel >= 1) {
            TrendPlot.trace(this.getClass(), "No longer polling trends ");
        }
    }
    
    public void stop() {
        this.go = false;
    }
}
