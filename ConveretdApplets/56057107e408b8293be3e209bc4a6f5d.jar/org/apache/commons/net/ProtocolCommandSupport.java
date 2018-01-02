// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net;

import java.util.EventListener;
import java.util.Enumeration;
import org.apache.commons.net.util.ListenerList;
import java.io.Serializable;

public class ProtocolCommandSupport implements Serializable
{
    private Object __source;
    private ListenerList __listeners;
    
    public ProtocolCommandSupport(final Object source) {
        this.__listeners = new ListenerList();
        this.__source = source;
    }
    
    public void fireCommandSent(final String command, final String message) {
        final Enumeration en = this.__listeners.getListeners();
        final ProtocolCommandEvent event = new ProtocolCommandEvent(this.__source, command, message);
        while (en.hasMoreElements()) {
            final ProtocolCommandListener listener = en.nextElement();
            listener.protocolCommandSent(event);
        }
    }
    
    public void fireReplyReceived(final int replyCode, final String message) {
        final Enumeration en = this.__listeners.getListeners();
        final ProtocolCommandEvent event = new ProtocolCommandEvent(this.__source, replyCode, message);
        while (en.hasMoreElements()) {
            final ProtocolCommandListener listener = en.nextElement();
            listener.protocolReplyReceived(event);
        }
    }
    
    public void addProtocolCommandListener(final ProtocolCommandListener listener) {
        this.__listeners.addListener(listener);
    }
    
    public void removeProtocolCommandListener(final ProtocolCommandListener listener) {
        this.__listeners.removeListener(listener);
    }
    
    public int getListenerCount() {
        return this.__listeners.getListenerCount();
    }
}
