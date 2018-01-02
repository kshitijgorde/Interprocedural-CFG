// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.io.IOException;

public class DataChainSendOrderStructure
{
    private byte[] m_orderData;
    private Object m_additionalProtocolData;
    private int m_processedBytes;
    private IOException m_thrownException;
    private byte[] m_channelCell;
    private Object m_internalSynchronization;
    private boolean m_processingDone;
    
    public DataChainSendOrderStructure(final byte[] orderData) {
        this.m_orderData = orderData;
        this.m_processedBytes = 0;
        this.m_thrownException = null;
        this.m_channelCell = null;
        this.m_internalSynchronization = new Object();
        this.m_processingDone = false;
        this.m_additionalProtocolData = null;
    }
    
    public byte[] getOrderData() {
        return this.m_orderData;
    }
    
    public Object getAdditionalProtocolData() {
        return this.m_additionalProtocolData;
    }
    
    public void setAdditionalProtocolData(final Object additionalProtocolData) {
        this.m_additionalProtocolData = additionalProtocolData;
    }
    
    public void processingDone() {
        synchronized (this.m_internalSynchronization) {
            this.m_processingDone = true;
            this.m_internalSynchronization.notify();
        }
    }
    
    public boolean isProcessingDone() {
        return this.m_processingDone;
    }
    
    public Object getSynchronizationObject() {
        return this.m_internalSynchronization;
    }
    
    public void setThrownException(final IOException thrownException) {
        this.m_thrownException = thrownException;
    }
    
    public IOException getThrownException() {
        return this.m_thrownException;
    }
    
    public void setProcessedBytes(final int processedBytes) {
        this.m_processedBytes = processedBytes;
    }
    
    public int getProcessedBytes() {
        return this.m_processedBytes;
    }
    
    public void setChannelCell(final byte[] channelCell) {
        this.m_channelCell = channelCell;
    }
    
    public byte[] getChannelCell() {
        return this.m_channelCell;
    }
}
