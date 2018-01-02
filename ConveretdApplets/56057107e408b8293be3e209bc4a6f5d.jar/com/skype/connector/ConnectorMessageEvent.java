// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector;

public final class ConnectorMessageEvent extends ConnectorEvent
{
    private static final long serialVersionUID = -8610258526127376241L;
    private final String message;
    
    ConnectorMessageEvent(final Object source, final String newMessage) {
        super(source);
        this.message = newMessage;
    }
    
    public String getMessage() {
        return this.message;
    }
}
