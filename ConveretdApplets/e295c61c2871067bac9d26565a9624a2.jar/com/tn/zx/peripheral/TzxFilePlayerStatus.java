// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

import com.tn.components.ComponentStatus;

public class TzxFilePlayerStatus extends ComponentStatus
{
    private byte[] ivTzxFileBytes;
    
    public TzxFilePlayerStatus(final byte[] pTzxFileBytes) {
        this.ivTzxFileBytes = pTzxFileBytes;
    }
    
    public byte[] getTzxFileBytes() {
        return this.ivTzxFileBytes;
    }
}
