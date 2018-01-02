// 
// Decompiled by Procyon v0.5.30
// 

package anon.proxy;

public abstract class AbstractHTTPConnectionListener
{
    private int m_priority;
    
    public AbstractHTTPConnectionListener(final int priority) {
        this.m_priority = priority;
    }
    
    public final int getPriority() {
        return this.m_priority;
    }
    
    public abstract void requestHeadersReceived(final HTTPConnectionEvent p0);
    
    public abstract void responseHeadersReceived(final HTTPConnectionEvent p0);
    
    public abstract void upstreamContentBytesReceived(final HTTPConnectionEvent p0);
    
    public abstract void downstreamContentBytesReceived(final HTTPConnectionEvent p0);
    
    public final boolean equals(final Object o) {
        return o != null && o.getClass() == this.getClass();
    }
    
    public final int hashCode() {
        return this.getClass().getName().hashCode();
    }
}
