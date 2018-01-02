// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import HTTPClient.HTTPConnection;

public class HTTPConnectionDescriptor
{
    private HTTPConnection connection;
    private ListenerInterface targetInterface;
    
    public HTTPConnectionDescriptor(final HTTPConnection connection, final ListenerInterface targetInterface) {
        this.connection = connection;
        this.targetInterface = targetInterface;
    }
    
    public HTTPConnection getConnection() {
        return this.connection;
    }
    
    public ListenerInterface getTargetInterface() {
        return this.targetInterface;
    }
}
