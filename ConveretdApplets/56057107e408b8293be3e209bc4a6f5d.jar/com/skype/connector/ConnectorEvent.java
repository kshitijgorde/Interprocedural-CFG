// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector;

import java.util.Date;
import java.util.EventObject;

class ConnectorEvent extends EventObject
{
    private static final long serialVersionUID = -4743437008394579910L;
    private final long time;
    
    ConnectorEvent(final Object source) {
        super(source);
        this.time = System.currentTimeMillis();
    }
    
    public final Connector getConnector() {
        return (Connector)this.getSource();
    }
    
    public final Date getTime() {
        return new Date(this.time);
    }
}
