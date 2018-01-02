// 
// Decompiled by Procyon v0.5.30
// 

package anon.proxy;

public interface IProxyListener
{
    public static final int PROTOCOL_OTHER = 0;
    public static final int PROTOCOL_WWW = 1;
    
    void channelsChanged(final int p0);
    
    void transferedBytes(final long p0, final int p1);
}
