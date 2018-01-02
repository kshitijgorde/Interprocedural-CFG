// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.util.Observer;
import java.util.Observable;

public class PacketCounter extends Observable implements Observer
{
    private volatile long m_processedDataPackets;
    private volatile long m_payPacketCounter;
    private Object m_internalSynchronization;
    
    public PacketCounter(final long processedDataPackets) {
        if (processedDataPackets > 0L) {
            this.m_processedDataPackets = processedDataPackets;
        }
        else {
            this.m_processedDataPackets = 0L;
        }
        this.m_payPacketCounter = 0L;
        this.m_internalSynchronization = new Object();
    }
    
    public PacketCounter() {
        this(0L);
    }
    
    public void update(final Observable observable, final Object o) {
        if (o instanceof PacketProcessedEvent) {
            final int code = ((PacketProcessedEvent)o).getCode();
            synchronized (this.m_internalSynchronization) {
                switch (code) {
                    case 1:
                    case 3:
                    case 5: {
                        ++this.m_processedDataPackets;
                        ++this.m_payPacketCounter;
                        this.setChanged();
                        break;
                    }
                }
                this.notifyObservers();
            }
        }
    }
    
    public long getProcessedPackets() {
        return this.m_processedDataPackets;
    }
    
    public long getAndResetBytesForPayment() {
        long n = 0L;
        synchronized (this.m_internalSynchronization) {
            n = this.m_payPacketCounter * MixPacket.getPacketSize();
            this.m_payPacketCounter = 0L;
        }
        return n;
    }
}
