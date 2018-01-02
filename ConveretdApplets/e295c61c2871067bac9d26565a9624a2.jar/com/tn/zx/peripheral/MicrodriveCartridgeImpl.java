// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

import com.tn.util.Array;

public class MicrodriveCartridgeImpl implements MicrodriveCartridge
{
    private byte[] ivData;
    private boolean ivWriteProtected;
    
    public MicrodriveCartridgeImpl() {
        this.ivData = new byte[137922];
        this.ivWriteProtected = false;
    }
    
    public MicrodriveCartridgeImpl(final byte[] pMicrodriveFile) {
        this();
        this.ivData = Array.subArray(pMicrodriveFile, 0, 137922);
        this.ivWriteProtected = (pMicrodriveFile[137922] != 0);
    }
    
    @Override
    public byte getByte(final int pIndex) {
        return this.ivData[pIndex];
    }
    
    @Override
    public int getLength() {
        return this.ivData.length;
    }
    
    @Override
    public boolean isWriteProtected() {
        return this.ivWriteProtected;
    }
    
    @Override
    public void setByte(final int pIndex, final byte pValue) {
        this.ivData[pIndex] = pValue;
    }
    
    public void setWriteProtected(final boolean pWriteProtected) {
        this.ivWriteProtected = pWriteProtected;
    }
}
