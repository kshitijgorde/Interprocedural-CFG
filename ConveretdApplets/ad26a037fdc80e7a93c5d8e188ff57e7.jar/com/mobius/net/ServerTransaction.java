// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.net;

public abstract class ServerTransaction
{
    protected ServerConnection connection;
    
    protected ServerTransaction(final ServerConnection serverConnection) {
        this.setServerConnection(serverConnection);
    }
    
    protected void setServerConnection(final ServerConnection connection) {
        if (connection == null) {
            throw new IllegalArgumentException("ServerConnection can't be null");
        }
        this.connection = connection;
    }
}
