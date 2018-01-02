// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

import com.tn.components.ComponentStatus;

public class Interface1Status extends ComponentStatus
{
    boolean ivRomActive;
    
    public boolean getRomActive() {
        return this.ivRomActive;
    }
    
    public void setRomActive(final boolean pRomActive) {
        this.ivRomActive = pRomActive;
    }
}
