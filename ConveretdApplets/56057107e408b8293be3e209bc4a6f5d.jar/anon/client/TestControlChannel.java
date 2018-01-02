// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import logging.LogHolder;
import logging.LogType;
import anon.IServiceContainer;

public class TestControlChannel extends XmlControlChannel implements Runnable
{
    public static final int DT_MIN_INTERVAL_MS = 500;
    public static final int DT_MAX_INTERVAL_MS = 30000;
    public static final int DT_DISABLE = Integer.MAX_VALUE;
    private volatile boolean m_bRun;
    private Thread m_threadRunLoop;
    private long m_interval;
    private Object m_internalSynchronization;
    
    public TestControlChannel(final Multiplexer multiplexer, final IServiceContainer serviceContainer) {
        super(255, multiplexer, serviceContainer, true);
        this.m_interval = 30000L;
        this.m_internalSynchronization = new Object();
        this.m_bRun = false;
        this.m_threadRunLoop = null;
        this.m_interval = -1L;
    }
    
    public void run() {
        LogHolder.log(5, LogType.NET, "Test control channel sent interval: " + this.m_interval + "ms");
        while (this.m_bRun) {
            try {
                Thread.sleep(this.m_interval);
                if (!this.m_bRun) {
                    continue;
                }
                LogHolder.log(6, LogType.NET, "Sending control channel test message!");
                final Document document = XMLUtil.createDocument();
                document.appendChild(document.createElement("TestControlChannel"));
                this.sendXmlMessage(document);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void stop() {
        synchronized (this.m_internalSynchronization) {
            this.m_bRun = false;
            if (this.m_threadRunLoop != null) {
                while (this.m_threadRunLoop.isAlive()) {
                    LogHolder.log(5, LogType.NET, "Shutting down test control channel...");
                    this.m_threadRunLoop.interrupt();
                    try {
                        this.m_threadRunLoop.join();
                    }
                    catch (InterruptedException ex) {}
                }
                LogHolder.log(5, LogType.NET, "Test control channel closed!");
                this.m_threadRunLoop = null;
            }
        }
    }
    
    public void setMessageInterval(int n) {
        synchronized (this.m_internalSynchronization) {
            this.stop();
            if (n == Integer.MAX_VALUE) {
                LogHolder.log(4, LogType.NET, "Test control channel sent disabled!");
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
            }
        }
    }
    
    protected void processXmlMessage(final Document document) {
        LogHolder.log(7, LogType.NET, "ControlChannelTest: Received control message:" + XMLUtil.toString(document));
    }
    
    private void start() {
        synchronized (this.m_internalSynchronization) {
            if (!this.m_bRun) {
                this.m_bRun = true;
                (this.m_threadRunLoop = new Thread(this, "JAP - Control Channel Test")).setDaemon(true);
                this.m_threadRunLoop.start();
            }
        }
    }
}
