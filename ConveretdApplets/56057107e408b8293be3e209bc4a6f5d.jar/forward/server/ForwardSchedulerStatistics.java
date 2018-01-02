// 
// Decompiled by Procyon v0.5.30
// 

package forward.server;

import java.util.Enumeration;
import java.util.Vector;

public class ForwardSchedulerStatistics
{
    private static final long BANDWIDTH_STATISTICS_INTERVAL = 1000L;
    private int m_rejectedConnections;
    private int m_acceptedConnections;
    private long m_transferedBytes;
    private Vector m_lastTransferVolumes;
    
    public ForwardSchedulerStatistics() {
        this.m_rejectedConnections = 0;
        this.m_acceptedConnections = 0;
        this.m_transferedBytes = 0L;
        this.m_lastTransferVolumes = new Vector();
    }
    
    public void incrementRejectedConnections() {
        synchronized (this) {
            ++this.m_rejectedConnections;
        }
    }
    
    public synchronized int getRejectedConnections() {
        return this.m_rejectedConnections;
    }
    
    public void incrementAcceptedConnections() {
        synchronized (this) {
            ++this.m_acceptedConnections;
        }
    }
    
    public int getAcceptedConnections() {
        int acceptedConnections = 0;
        synchronized (this) {
            acceptedConnections = this.m_acceptedConnections;
        }
        return acceptedConnections;
    }
    
    public void incrementTransferVolume(final int n) {
        synchronized (this.m_lastTransferVolumes) {
            this.removeOutdatedTransferVolumes();
            this.m_lastTransferVolumes.addElement(new TransferVolume(n));
        }
        synchronized (this) {
            this.m_transferedBytes += n;
        }
    }
    
    public int getCurrentBandwidthUsage() {
        int n = 0;
        synchronized (this.m_lastTransferVolumes) {
            this.removeOutdatedTransferVolumes();
            final Enumeration<TransferVolume> elements = this.m_lastTransferVolumes.elements();
            while (elements.hasMoreElements()) {
                n += elements.nextElement().getVolume();
            }
        }
        return Math.round(n * 1000.0f / 1000.0f);
    }
    
    public long getTransferedBytes() {
        long transferedBytes = 0L;
        synchronized (this) {
            transferedBytes = this.m_transferedBytes;
        }
        return transferedBytes;
    }
    
    private void removeOutdatedTransferVolumes() {
        synchronized (this.m_lastTransferVolumes) {
            final long currentTimeMillis = System.currentTimeMillis();
            int n = 1;
            while (this.m_lastTransferVolumes.size() > 0 && n == 1) {
                if (this.m_lastTransferVolumes.firstElement().getTimeStamp() + 1000L < currentTimeMillis) {
                    this.m_lastTransferVolumes.removeElementAt(0);
                }
                else {
                    n = 0;
                }
            }
        }
    }
}
