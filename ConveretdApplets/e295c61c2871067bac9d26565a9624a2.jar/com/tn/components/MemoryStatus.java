// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.components;

import com.tn.util.Array;

public class MemoryStatus extends ComponentStatus
{
    private byte[] ivBytes;
    
    public MemoryStatus(final byte[] pBytes) {
        this.ivBytes = pBytes;
    }
    
    public MemoryStatus(final byte[] pSrc, final int pSrcPos, final int pLen) {
        this.ivBytes = Array.subArray(pSrc, pSrcPos, pLen);
    }
    
    public byte[] getBytes() {
        return this.ivBytes;
    }
    
    public void setBytes(final byte[] pBytes) {
        this.ivBytes = pBytes;
    }
}
