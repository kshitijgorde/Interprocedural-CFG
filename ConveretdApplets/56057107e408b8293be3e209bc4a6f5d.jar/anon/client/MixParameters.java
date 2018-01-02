// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import anon.client.replay.ReplayTimestamp;
import anon.client.crypto.IASymMixCipher;

public class MixParameters
{
    private String m_mixId;
    private IASymMixCipher m_mixCipher;
    private ReplayTimestamp m_replayTimestamp;
    private int m_replayOffset;
    private Object m_internalSynchronization;
    public static long m_referenceTime;
    
    public MixParameters(final String mixId, final IASymMixCipher mixCipher) {
        this.m_mixId = mixId;
        this.m_mixCipher = mixCipher;
        this.m_replayTimestamp = null;
        this.m_internalSynchronization = new Object();
        this.m_replayOffset = 0;
    }
    
    public String getMixId() {
        return this.m_mixId;
    }
    
    public IASymMixCipher getMixCipher() {
        return this.m_mixCipher;
    }
    
    public ReplayTimestamp getReplayTimestamp() {
        synchronized (this.m_internalSynchronization) {
            return this.m_replayTimestamp;
        }
    }
    
    public byte[] getReplayOffset() {
        final byte[] array = new byte[3];
        this.m_replayOffset &= 0xFFFFFF;
        array[0] = (byte)(this.m_replayOffset >> 16);
        array[1] = (byte)(this.m_replayOffset >> 8 & 0xFF);
        array[2] = (byte)(this.m_replayOffset & 0xFF);
        return array;
    }
    
    public byte[] getCurrentReplayOffset(final int n) {
        if (this.m_replayOffset == 0) {
            return null;
        }
        final byte[] array = new byte[3];
        final int n2 = this.m_replayOffset + n & 0xFFFFFF;
        array[0] = (byte)(n2 >> 16);
        array[1] = (byte)(n2 >> 8 & 0xFF);
        array[2] = (byte)(n2 & 0xFF);
        return array;
    }
    
    public void setReplayTimestamp(final ReplayTimestamp replayTimestamp) {
        synchronized (this.m_internalSynchronization) {
            this.m_replayTimestamp = replayTimestamp;
        }
    }
    
    public void setReplayOffset(final int n) {
        this.m_replayOffset = (n & 0xFFFFFF);
    }
}
