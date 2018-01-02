// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.util.Vector;
import java.util.Observable;

public class InternalChannelMessageQueue extends Observable
{
    private Vector m_messageQueue;
    
    public InternalChannelMessageQueue() {
        this.m_messageQueue = new Vector();
    }
    
    public void addChannelMessage(final InternalChannelMessage internalChannelMessage) {
        synchronized (this.m_messageQueue) {
            this.m_messageQueue.addElement(internalChannelMessage);
            this.m_messageQueue.notify();
        }
        this.setChanged();
        this.notifyObservers(internalChannelMessage);
    }
    
    public InternalChannelMessage getFirstMessage() {
        InternalChannelMessage internalChannelMessage = null;
        synchronized (this.m_messageQueue) {
            if (this.m_messageQueue.size() > 0) {
                internalChannelMessage = this.m_messageQueue.firstElement();
            }
        }
        return internalChannelMessage;
    }
    
    public void removeFirstMessage() {
        synchronized (this.m_messageQueue) {
            if (this.m_messageQueue.size() > 0) {
                this.m_messageQueue.removeElementAt(0);
            }
        }
    }
    
    public InternalChannelMessage waitForNextMessage() throws InterruptedException {
        final InternalChannelMessage internalChannelMessage;
        synchronized (this.m_messageQueue) {
            while (this.m_messageQueue.size() == 0) {
                this.m_messageQueue.wait();
            }
            internalChannelMessage = this.m_messageQueue.firstElement();
        }
        return internalChannelMessage;
    }
}
