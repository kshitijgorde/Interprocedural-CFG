// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

import com.tn.components.ComponentStatus;

public class TapFilePlayerStatus extends ComponentStatus
{
    private byte[] ivTapFileBytes;
    private int ivTapFilePos;
    private boolean ivTapFileLoop;
    
    public TapFilePlayerStatus(final byte[] pTapFileBytes, final int pTapFilePos, final boolean pTapFileLoop) {
        this.ivTapFileBytes = pTapFileBytes;
        this.ivTapFilePos = pTapFilePos;
        this.ivTapFileLoop = pTapFileLoop;
    }
    
    public byte[] getTapFileBytes() {
        return this.ivTapFileBytes;
    }
    
    public boolean getTapFileLoop() {
        return this.ivTapFileLoop;
    }
    
    public int getTapFilePos() {
        return this.ivTapFilePos;
    }
}
