// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

public interface NetStreamListener
{
    public static final int ERR_ERR = 0;
    public static final int ERR_CER = 2;
    
    void notifyDisconnection(final int p0);
    
    void notifyIncoming(final byte[] p0);
}
