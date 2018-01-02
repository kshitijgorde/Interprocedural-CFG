// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

import com.tn.zx.ZxIoHandler;

public class JoystickSinclair1 extends IOJoystick
{
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
        return "SINCLAIR1";
    }
    
    @Override
    public int readIO(final int pAddress, final int pValue) {
        if ((pAddress & 0x1000) == 0x0) {
            return pValue & this.ivPortEFFE;
        }
        return pValue;
    }
    
    @Override
    public void reset() {
        this.ivPortEFFE = 255;
    }
    
    @Override
    void update(final int pControl, final boolean pStatus) {
        int mask = 0;
        switch (pControl) {
            case 2: {
                mask = 2;
                break;
            }
            case 3: {
                mask = 4;
                break;
            }
            case 0: {
                mask = 16;
                break;
            }
            case 1: {
                mask = 8;
                break;
            }
            case 4: {
                mask = 1;
                break;
            }
        }
        if (pStatus) {
            this.ivPortEFFE &= (mask ^ 0xFF);
        }
        else {
            this.ivPortEFFE |= mask;
        }
    }
}
