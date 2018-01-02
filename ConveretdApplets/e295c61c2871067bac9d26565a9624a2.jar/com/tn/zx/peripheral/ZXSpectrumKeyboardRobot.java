// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

import com.tn.z80.Z80A;
import com.tn.zx.ZxExpansionPort;
import com.tn.zx.ZxIoHandler;

public class ZXSpectrumKeyboardRobot implements ZxIoHandler
{
    private ZxExpansionPort ivConnectedSpectrum;
    private int[] ivKeyCodeBuffer;
    private int ivKeyCodeBufferIndex;
    
    public ZXSpectrumKeyboardRobot() {
        this.ivConnectedSpectrum = null;
        this.ivKeyCodeBuffer = null;
    }
    
    public void connectToZXSpectrum(final ZxExpansionPort pZXSpectrum) {
        (this.ivConnectedSpectrum = pZXSpectrum).addBreakpointHandler(this, 4271);
        pZXSpectrum.addBreakpointHandler(this, 13955);
        pZXSpectrum.addBreakpointHandler(this, 6261);
    }
    
    public void disconnectFromZXSpectrum() {
        if (this.ivConnectedSpectrum != null) {
            this.ivConnectedSpectrum.removeBreakpointHandler(this);
            this.ivConnectedSpectrum = null;
        }
    }
    
    public KeyboardRobotStatus getStatus() {
        return new KeyboardRobotStatus();
    }
    
    @Override
    public int readIO(final int pAddress, final int pValue) {
        return pValue;
    }
    
    @Override
    public void readOpcode1(final int pAddress) {
    }
    
    @Override
    public synchronized int readOpcode2(final int pAddress, final int pOpcode) {
        if (this.ivKeyCodeBuffer == null) {
            return pOpcode;
        }
        if (this.ivKeyCodeBufferIndex >= this.ivKeyCodeBuffer.length) {
            return pOpcode;
        }
        final Z80A z80 = this.ivConnectedSpectrum.getZ80();
        if (pAddress == 4271 && pOpcode == 167) {
            z80.setPC(4280);
            z80.setA(this.ivKeyCodeBuffer[this.ivKeyCodeBufferIndex++]);
            return 0;
        }
        if (pAddress == 13955 && pOpcode == 203) {
            z80.setPC(13964);
            z80.setA(this.ivKeyCodeBuffer[this.ivKeyCodeBufferIndex++]);
            return 0;
        }
        if (pAddress == 6261 && pOpcode == 203) {
            z80.setPC(6270);
            z80.setA(this.ivKeyCodeBuffer[this.ivKeyCodeBufferIndex++]);
            return 0;
        }
        return pOpcode;
    }
    
    public synchronized void setKeyCodeBuffer(final int[] pKeyCodes) {
        this.ivKeyCodeBuffer = pKeyCodes;
        this.ivKeyCodeBufferIndex = 0;
    }
    
    public void setKeyCodeBuffer(final String pKeyCodes) {
        throw new RuntimeException("Not implemented");
    }
    
    public void setStatus(final KeyboardRobotStatus pKeyboardRobotStatus) {
    }
    
    public void terminate() {
    }
    
    @Override
    public void writeIO(final int pAddress, final int pValue) {
    }
}
