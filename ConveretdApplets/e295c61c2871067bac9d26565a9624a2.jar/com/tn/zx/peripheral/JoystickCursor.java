// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

import com.tn.zx.ZxIoHandler;

public class JoystickCursor extends IOJoystick
{
    private int ivPortF7FE;
    private int ivPortEFFE;
    
    @Override
    void connect() {
        this.reset();
        for (int i = 0; i < 256; i += 2) {
            this.ivConnectedSpectrum.addIOHandler(this, i);
        }
    }
    
    @Override
    public String getId() {
        return "CURSOR";
    }
    
    @Override
    public int readIO(final int pAddress, int pValue) {
        if ((pAddress & 0x800) == 0x0) {
            pValue &= this.ivPortF7FE;
        }
        if ((pAddress & 0x1000) == 0x0) {
            pValue &= this.ivPortEFFE;
        }
        return pValue;
    }
    
    @Override
    public void reset() {
        this.ivPortEFFE = 255;
        this.ivPortF7FE = 255;
    }
    
    @Override
    void update(final int pControl, final boolean pStatus) {
        int mask1 = 0;
        int mask2 = 0;
        switch (pControl) {
            case 2: {
                mask2 = 8;
                break;
            }
            case 3: {
                mask2 = 16;
                break;
            }
            case 0: {
                mask1 = 16;
                break;
            }
            case 1: {
                mask2 = 4;
                break;
            }
            case 4: {
                mask2 = 1;
                break;
            }
        }
        if (pStatus) {
            this.ivPortF7FE &= (mask1 ^ 0xFF);
            this.ivPortEFFE &= (mask2 ^ 0xFF);
        }
        else {
            this.ivPortF7FE |= mask1;
            this.ivPortEFFE |= mask2;
        }
    }
}
