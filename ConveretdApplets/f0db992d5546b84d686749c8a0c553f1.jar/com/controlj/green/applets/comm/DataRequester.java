// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.comm;

import com.controlj.green.applets.JumpPanel;
import com.controlj.green.applets.TrendPlot;
import com.controlj.green.applets.GraphHandler;

public class DataRequester implements Runnable
{
    public static final int NO_REQUEST = -1;
    public static final int REQUEST_LEFT = 0;
    public static final int REQUEST_RIGHT = 1;
    public static final int REQUEST_JUMP = 2;
    public static final int REQUEST_CURRENT = 3;
    public static final int REQUEST_LEFT_AND_RIGHT = 4;
    public static final int REQUEST_INITAL = 5;
    private GraphHandler handler;
    Thread myThread;
    int requestType;
    boolean isProcessingRequest;
    private volatile boolean go;
    
    public DataRequester(final GraphHandler handler) {
        this.requestType = -1;
        this.isProcessingRequest = false;
        this.go = true;
        this.handler = handler;
    }
    
    public void makeRequest(final int type) {
        this.requestType = type;
        if (this.myThread != null) {
            this.myThread.interrupt();
        }
    }
    
    public void run() {
        this.myThread = Thread.currentThread();
        this.go = true;
        while (this.go) {
            try {
                this.isProcessingRequest = true;
                int lastRequestMade = -1;
                while (lastRequestMade != this.requestType) {
                    if (TrendPlot.traceLevel >= 2) {
                        TrendPlot.trace(this.getClass(), "Making request: " + this.requestType);
                    }
                    switch (this.requestType) {
                        case -1: {
                            continue;
                        }
                        case 4: {
                            while (this.requestType == 4) {
                                if (TrendPlot.traceLevel >= 2) {
                                    TrendPlot.trace(this.getClass(), "Requesting: left and right");
                                }
                                final long originalTotal = this.handler.getTotalNumberOfDataPoints();
                                if (!this.handler.requestMoreData(false) && !this.handler.requestMoreData(true)) {
                                    break;
                                }
                                if (this.handler.getTotalNumberOfDataPoints() == originalTotal) {
                                    break;
                                }
                            }
                            lastRequestMade = 4;
                            continue;
                        }
                        case 0:
                        case 5: {
                            final int tempRequestType = this.requestType;
                            while (this.requestType == tempRequestType) {
                                if (TrendPlot.traceLevel >= 2) {
                                    TrendPlot.trace(this.getClass(), "Requesting: left or initial");
                                }
                                final long originalTotal2 = this.handler.getTotalNumberOfDataPoints();
                                if (!this.handler.requestMoreData(false)) {
                                    break;
                                }
                                if (this.handler.getTotalNumberOfDataPoints() == originalTotal2) {
                                    break;
                                }
                                if (this.requestType != 5) {
                                    continue;
                                }
                                this.handler.doResetGraphs();
                            }
                            lastRequestMade = tempRequestType;
                            continue;
                        }
                        case 1: {
                            if (TrendPlot.traceLevel >= 2) {
                                TrendPlot.trace(this.getClass(), "Requesting: right");
                            }
                            while (this.requestType == 1) {
                                final long originalTotal2 = this.handler.getTotalNumberOfDataPoints();
                                if (!this.handler.requestMoreData(true)) {
                                    break;
                                }
                                if (this.handler.getTotalNumberOfDataPoints() == originalTotal2) {
                                    break;
                                }
                            }
                            lastRequestMade = 1;
                            continue;
                        }
                        case 2: {
                            if (TrendPlot.traceLevel >= 2) {
                                TrendPlot.trace(this.getClass(), "Requesting: jump");
                            }
                            try {
                                this.handler.doJumpToTime(this.handler.getJumpPanel().getJumpTime(), this.handler.getJumpPanel().getPrecision());
                            }
                            catch (JumpPanel.BadDateException ex) {}
                            lastRequestMade = 2;
                            continue;
                        }
                        case 3: {
                            if (TrendPlot.traceLevel >= 2) {
                                TrendPlot.trace(this.getClass(), "Requesting: current");
                            }
                            this.handler.requestCurrentData();
                            lastRequestMade = 3;
                            continue;
                        }
                    }
                }
                if (TrendPlot.traceLevel >= 2) {
                    TrendPlot.trace(this.getClass(), "Request finished ");
                }
                this.isProcessingRequest = false;
                Thread.currentThread();
                Thread.sleep(2147483647L);
            }
            catch (InterruptedException e) {
                if (TrendPlot.traceLevel < 2) {
                    continue;
                }
                TrendPlot.trace(this.getClass(), "DataRequester interupted, making request now");
            }
        }
        if (TrendPlot.traceLevel >= 2) {
            TrendPlot.trace(this.getClass(), "DataRequester stopped");
        }
    }
    
    public void stop() {
        this.go = false;
    }
    
    public boolean isProcessingRequest() {
        return this.isProcessingRequest;
    }
}
