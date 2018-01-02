// 
// Decompiled by Procyon v0.5.30
// 

package forward.server;

import anon.transport.connection.IStreamConnection;
import anon.transport.connection.IChunkConnection;
import anon.transport.connection.ChunkConnectionAdapter;
import anon.transport.connection.SkypeConnection;
import com.skype.Stream;
import com.skype.Skype;
import com.skype.SkypeException;
import logging.LogHolder;
import logging.LogType;
import com.skype.ApplicationListener;
import com.skype.Application;

public class SkypeServerManager implements IServerManager
{
    private final String m_appName;
    private ForwardScheduler m_scheduler;
    private Application m_application;
    private RequestListener m_listner;
    private boolean m_isListning;
    
    public SkypeServerManager(final String appName) {
        this.m_appName = appName;
        this.m_isListning = false;
    }
    
    public Object getId() {
        return this.toString();
    }
    
    public synchronized void shutdown() {
        if (!this.m_isListning) {
            return;
        }
        this.m_application.removeApplicationListener(this.m_listner);
        try {
            this.m_application.finish();
        }
        catch (SkypeException ex) {
            LogHolder.log(2, LogType.TRANSPORT, ex);
        }
        this.m_scheduler = null;
        this.m_listner = null;
        this.m_isListning = false;
    }
    
    public synchronized void startServerManager(final ForwardScheduler scheduler) throws Exception {
        if (this.m_isListning) {
            return;
        }
        this.m_scheduler = scheduler;
        this.m_listner = new RequestListener();
        try {
            (this.m_application = Skype.addApplication(this.m_appName)).addApplicationListener(this.m_listner);
            this.m_isListning = true;
        }
        catch (SkypeException ex) {
            LogHolder.log(3, LogType.TRANSPORT, "Could not Start Skype forwarding Server.");
            this.shutdown();
            throw ex;
        }
    }
    
    public String toString() {
        return "skype:app(" + this.m_appName + ")";
    }
    
    private class RequestListener implements ApplicationListener
    {
        public void connected(final Stream stream) throws SkypeException {
            synchronized (SkypeServerManager.this) {
                if (!SkypeServerManager.this.m_isListning || SkypeServerManager.this.m_scheduler == null) {
                    stream.disconnect();
                    return;
                }
                SkypeServerManager.this.m_scheduler.handleNewConnection(new ChunkConnectionAdapter(new SkypeConnection(stream)));
            }
        }
        
        public void disconnected(final Stream stream) throws SkypeException {
        }
    }
}
