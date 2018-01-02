// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

public interface HttpConnectionManager
{
    HttpConnection getConnection(final HostConfiguration p0);
    
    HttpConnection getConnection(final HostConfiguration p0, final long p1) throws HttpException;
    
    HttpConnection getConnectionWithTimeout(final HostConfiguration p0, final long p1) throws ConnectTimeoutException;
    
    void releaseConnection(final HttpConnection p0);
}
