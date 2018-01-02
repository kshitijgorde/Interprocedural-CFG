// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import logging.LogHolder;
import logging.LogType;
import anon.IServiceContainer;
import java.util.Observable;
import java.util.Observer;

public class DummyTrafficControlChannel extends AbstractControlChannel implements Runnable, Observer
{
    public static final int DT_MIN_INTERVAL_MS = 500;
    public static final int DT_MAX_INTERVAL_MS = 30000;
    public static final int DT_DISABLE = Integer.MAX_VALUE;
    private Observable m_observedMultiplexer;
    private volatile boolean m_bRun;
    private Thread m_threadRunLoop;
    private long m_interval;
    private Object m_internalSynchronization;
    
    public DummyTrafficControlChannel(final Multiplexer observedMultiplexer, final IServiceContainer serviceContainer) {
        super(4, observedMultiplexer, serviceContainer);
        this.m_interval = 30000L;
        this.m_internalSynchronization = new Object();
        this.m_bRun = false;
        this.m_threadRunLoop = null;
        this.m_interval = -1L;
        this.m_observedMultiplexer = observedMultiplexer;
    }
    
    public void run() {
        LogHolder.log(5, LogType.NET, "Dummy traffic interval: " + this.m_interval + "ms");
        while (this.m_bRun) {
            try {
                Thread.sleep(this.m_interval);
                if (!this.m_bRun) {
                    continue;
                }
                LogHolder.log(6, LogType.NET, "Sending Dummy!");
                this.sendRawMessage(new byte[0]);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void stop() {
        synchronized (this.m_internalSynchronization) {
            this.m_bRun = false;
            this.m_observedMultiplexer.deleteObserver(this);
            if (this.m_threadRunLoop != null) {
                while (this.m_threadRunLoop.isAlive()) {
                    LogHolder.log(5, LogType.NET, "Shutting down dummy traffic channel...");
                    this.m_threadRunLoop.interrupt();
                    try {
                        this.m_threadRunLoop.join();
                    }
                    catch (InterruptedException ex) {}
                }
                LogHolder.log(5, LogType.NET, "Dummy traffic channel closed!");
                this.m_threadRunLoop = null;
            }
        }
    }
    
    public void update(final Observable observable, final Object o) {
        synchronized (this.m_internalSynchronization) {
            if (this.m_threadRunLoop != null) {
                this.m_threadRunLoop.interrupt();
            }
        }
    }
    
    public void setDummyTrafficInterval(int n) {
        int n2 = 0;
        synchronized (this.m_internalSynchronization) {
            this.stop();
            if (n == Integer.MAX_VALUE) {
                LogHolder.log(4, LogType.NET, "Dummy traffic disabled!");
                return;
            }
            if (n < 500) {
                n = 500;
            }
            else if (n > 30000) {
                n = 30000;
            }
            this.m_interval = n;
            if (n > 0) {
                this.start();
                n2 = 1;
            }
        }
        if (n2 == 1) {
            LogHolder.log(7, LogType.NET, "Sending Dummy!");
            this.sendRawMessage(new byte[0]);
        }
    }
    
    protected void processPacketData(final byte[] array) {
        LogHolder.log(7, LogType.NET, "DummyTrafficControlChannel: processPacketData(): Received a dummy-packet.");
    }
    
    private void start() {
        synchronized (this.m_internalSynchronization) {
            if (!this.m_bRun) {
                this.m_bRun = true;
                (this.m_threadRunLoop = new Thread(this, "JAP - Dummy Traffic")).setDaemon(true);
                this.m_observedMultiplexer.addObserver(this);
                this.m_threadRunLoop.start();
            }
        }
    }
}
