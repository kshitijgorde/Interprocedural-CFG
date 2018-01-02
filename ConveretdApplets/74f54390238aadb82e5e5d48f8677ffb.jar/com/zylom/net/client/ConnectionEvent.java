// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.net.client;

import java.util.EventObject;

public class ConnectionEvent extends EventObject
{
    private PDU pdu;
    private Exception exception;
    
    public ConnectionEvent(final Object o) {
        super(o);
    }
    
    public ConnectionEvent(final Object o, final PDU pdu) {
        super(o);
        this.pdu = pdu;
    }
    
    public ConnectionEvent(final Object o, final Exception exception) {
        super(o);
        this.exception = exception;
    }
    
    public PDU getPDU() {
        return this.pdu;
    }
    
    public Exception getException() {
        return this.exception;
    }
}
