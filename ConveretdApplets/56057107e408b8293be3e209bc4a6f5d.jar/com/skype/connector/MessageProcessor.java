// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector;

public abstract class MessageProcessor
{
    private Object lock;
    private ConnectorListener parent;
    
    final void init(final Object lock, final ConnectorListener parent) {
        this.lock = lock;
    }
    
    protected abstract void messageReceived(final String p0);
    
    protected final void releaseLock() {
        synchronized (this.lock) {
            this.lock.notify();
        }
        // monitorexit(this.lock)
    }
    
    protected final void processedAllMessages() {
        Connector.getInstance().removeConnectorListener(this.parent);
    }
}
