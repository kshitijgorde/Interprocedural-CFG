// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

import com.tn.zx.ZxIoHandler;

public class JoystickKempston extends IOJoystick
{
    private int ivPort1F;
    
    @Override
    void connect() {
        this.reset();
        for (int i = 0; i <= 31; ++i) {
            this.ivConnectedSpectrum.addIOHandler(this, i);
        }
    }
    
    @Override
    public String getId() {
        return "KEMPSTON";
    }
    
    @Override
    public int readIO(final int pAddress, final int pValue) {
        return this.ivPort1F;
    }
    
    @Override
    public void reset() {
        this.ivPort1F = 0;
    }
    
    @Override
    void update(final int pControl, final boolean pStatus) {
        int mask = 0;
        switch (pControl) {
            case 2: {
                mask = 8;
                break;
            }
            case 3: {
                mask = 4;
                break;
            }
            case 0: {
                mask = 2;
                break;
            }
            case 1: {
                mask = 1;
                break;
            }
            case 4: {
                mask = 16;
                break;
            }
        }
        if (pStatus) {
            this.ivPort1F |= mask;
        }
        else {
            this.ivPort1F &= (mask ^ 0xFF);
        }
    }
}
