// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector;

public final class ConnectorStatusEvent extends ConnectorEvent
{
    private static final long serialVersionUID = -7285732323922562464L;
    private final int status;
    
    ConnectorStatusEvent(final Object source, final int newStatus) {
        super(source);
        this.status = newStatus;
    }
    
    public int getStatus() {
        return this.status;
    }
}
