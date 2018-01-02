// 
// Decompiled by Procyon v0.5.30
// 

package forward.server;

public class TransferVolume
{
    private int m_transferedBytes;
    private long m_timeStamp;
    
    public TransferVolume(final int transferedBytes) {
        this.m_transferedBytes = transferedBytes;
        this.m_timeStamp = System.currentTimeMillis();
    }
    
    public int getVolume() {
        return this.m_transferedBytes;
    }
    
    public long getTimeStamp() {
        return this.m_timeStamp;
    }
}
