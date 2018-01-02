// 
// Decompiled by Procyon v0.5.30
// 

package anon.proxy;

public class HTTPConnectionEvent
{
    private HTTPProxyCallback.HTTPConnectionHeader connectionHeader;
    private volatile long upStreamContentBytes;
    private volatile long downStreamContentBytes;
    private AnonProxyRequest anonRequest;
    
    public HTTPConnectionEvent() {
    }
    
    HTTPConnectionEvent(final HTTPProxyCallback.HTTPConnectionHeader connectionHeader, final long upStreamContentBytes, final long downStreamContentBytes, final AnonProxyRequest anonRequest) {
        this.connectionHeader = connectionHeader;
        this.upStreamContentBytes = upStreamContentBytes;
        this.downStreamContentBytes = downStreamContentBytes;
        this.anonRequest = anonRequest;
    }
    
    public HTTPProxyCallback.HTTPConnectionHeader getConnectionHeader() {
        return this.connectionHeader;
    }
    
    public void setConnectionHeader(final HTTPProxyCallback.HTTPConnectionHeader connectionHeader) {
        this.connectionHeader = connectionHeader;
    }
    
    public long getUpStreamContentBytes() {
        return this.upStreamContentBytes;
    }
    
    public void setUpStreamContentBytes(final long upStreamContentBytes) {
        this.upStreamContentBytes = upStreamContentBytes;
    }
    
    public long getDownStreamContentBytes() {
        return this.downStreamContentBytes;
    }
    
    public void setDownStreamContentBytes(final long downStreamContentBytes) {
        this.downStreamContentBytes = downStreamContentBytes;
    }
    
    public AnonProxyRequest getAnonRequest() {
        return this.anonRequest;
    }
    
    public void setAnonRequest(final AnonProxyRequest anonRequest) {
        this.anonRequest = anonRequest;
    }
}
