// 
// Decompiled by Procyon v0.5.30
// 

package anon.proxy;

public interface ProxyCallback
{
    public static final int STATUS_FINISHED = 0;
    public static final int STATUS_DELAY = 1;
    public static final int STATUS_PROCESSABLE = 2;
    
    int handleUpstreamChunk(final AnonProxyRequest p0, final ProxyCallbackBuffer p1) throws ProxyCallbackNotProcessableException;
    
    int handleDownstreamChunk(final AnonProxyRequest p0, final ProxyCallbackBuffer p1) throws ProxyCallbackNotProcessableException;
    
    void closeRequest(final AnonProxyRequest p0);
}
