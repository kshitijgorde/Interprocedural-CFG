// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

import com.tn.z80.Z80A;
import com.tn.util.Array;
import com.tn.zx.ZxExpansionPort;
import com.tn.zx.ZxIoHandler;

public class ZXSpectrumTapFilePlayer implements ZxIoHandler
{
    private ZxExpansionPort ivConnectedSpectrum;
    private byte[] ivTapFile;
    private int ivPosNextBlock;
    private int ivPosRead;
    private boolean ivLoop;
    
    public ZXSpectrumTapFilePlayer() {
        this.ivConnectedSpectrum = null;
        this.setTapFileBuffer(null);
    }
    
    public void connectToZXSpectrum(final ZxExpansionPort pZXSpectrum) {
        (this.ivConnectedSpectrum = pZXSpectrum).addBreakpointHandler(this, 1388);
        pZXSpectrum.addBreakpointHandler(this, 1482);
    }
    
    public void disconnectFromZXSpectrum() {
        if (this.ivConnectedSpectrum != null) {
            this.ivConnectedSpectrum.removeBreakpointHandler(this);
            this.ivConnectedSpectrum = null;
        }
    }
    
    private int getNextByteFromBlock() {
        if (this.ivPosRead < this.ivPosNextBlock) {
            return this.ivTapFile[this.ivPosRead++] & 0xFF;
        }
        return -1;
    }
    
    public TapFilePlayerStatus getStatus() {
        return new TapFilePlayerStatus(this.ivTapFile, this.ivPosRead, true);
    }
    
    private void gotoNextBlock() {
        if (this.ivPosNextBlock + 2 <= this.ivTapFile.length) {
            this.ivPosRead = this.ivPosNextBlock + 2;
            this.ivPosNextBlock += 2 + Array.toIntLsbf(this.ivTapFile, this.ivPosNextBlock, 2);
        }
        else if (this.ivLoop) {
            this.ivPosRead = 2;
            this.ivPosNextBlock = 2 + Array.toIntLsbf(this.ivTapFile, 0, 2);
        }
        else {
            this.ivPosRead = this.ivPosNextBlock;
        }
    }
    
    public static boolean isTapFile(final byte[] pBytes) {
        int i;
        for (i = 0; i + 2 <= pBytes.length; i += 2 + Array.toIntLsbf(pBytes, i, 2)) {}
        return i == pBytes.length && i != 0;
    }
    
    @Override
    public int readIO(final int pAddress, final int pValue) {
        return pValue;
    }
    
    @Override
    public void readOpcode1(final int pAddress) {
    }
    
    @Override
    public int readOpcode2(final int pAddress, final int pOpcode) {
        final Z80A z80 = this.ivConnectedSpectrum.getZ80();
        if (pAddress == 1388 && pOpcode == 205) {
            z80.setPC(1482);
            z80.setH(0);
            this.gotoNextBlock();
            return 0;
        }
        if (pAddress == 1482 && pOpcode == 205) {
            final int b = this.getNextByteFromBlock();
            if (b >= 0) {
                z80.setL(b);
                z80.setPC(1496);
            }
            else {
                z80.setA(0);
                z80.setB(0);
                z80.setF_C(false);
                z80.setF_Z(true);
                z80.setPC(1485);
            }
            return 0;
        }
        return pOpcode;
    }
    
    public void setStatus(final TapFilePlayerStatus pStatus) {
        this.ivTapFile = pStatus.getTapFileBytes();
        this.ivPosNextBlock = 0;
        this.ivPosRead = 0;
    }
    
    public void setTapFileBuffer(final byte[] pTapFile) {
        this.ivTapFile = pTapFile;
        this.ivPosNextBlock = 0;
        this.ivPosRead = 0;
    }
    
    public void terminate() {
    }
    
    @Override
    public void writeIO(final int pAddress, final int pValue) {
    }
}
