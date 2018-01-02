// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

public class Microdrive
{
    private int ivOldE7write;
    private int ivOldEFread;
    private int ivOldEFwrite;
    private MicrodriveCartridge ivCartridge;
    private int ivCartridgeIndex;
    private int ivBlockIndex;
    private int ivBlockLen;
    private static final int SECTOR_LENGTH = 543;
    private static final int HEADERBLOCK_LENGTH = 15;
    private static final int DATABLOCK_LENGTH = 528;
    private static final int GAP_LENGTH = 8;
    private static final int PREAMPLE_LENGTH = 12;
    
    public Microdrive() {
        this.ivOldE7write = 0;
        this.ivOldEFread = 1;
        this.ivOldEFwrite = 0;
        this.ivCartridge = null;
        this.ivCartridgeIndex = -1;
        this.ivBlockIndex = -1;
        this.ivBlockLen = -1;
        this.ivCartridge = new MicrodriveCartridgeImpl();
    }
    
    private void cartridgeForward() {
        if (this.ivBlockIndex >= 0 && this.ivBlockIndex < this.ivBlockLen) {
            ++this.ivCartridgeIndex;
            if (this.ivCartridgeIndex >= this.ivCartridge.getLength()) {
                this.ivCartridgeIndex = 0;
                this.cartridgeTruncateIndex();
            }
        }
        ++this.ivBlockIndex;
        if (this.ivBlockIndex > this.ivBlockLen + 8) {
            this.cartridgeTruncateIndex();
        }
    }
    
    private void cartridgeTruncateIndex() {
        final int sectorNumber = this.ivCartridgeIndex / 543;
        int sectorIndex = this.ivCartridgeIndex % 543;
        if (sectorIndex < 15) {
            sectorIndex = 0;
            this.ivBlockLen = 15;
        }
        else {
            sectorIndex = 15;
            this.ivBlockLen = 528;
        }
        this.ivCartridgeIndex = sectorNumber * 543 + sectorIndex;
        this.ivBlockIndex = -12;
    }
    
    private int getLowered(final int pOldValue, final int pNewValue) {
        return ~pNewValue & pOldValue;
    }
    
    public MicrodriveStatus getstatus() {
        return new MicrodriveStatus();
    }
    
    public void insertCartridge(final MicrodriveCartridge pCartridge) {
        this.ivCartridge = pCartridge;
    }
    
    public int readPortE7() {
        int result = 252;
        if (this.ivCartridge != null && this.ivBlockIndex < this.ivBlockLen) {
            if (this.ivBlockIndex >= 0) {
                result = (this.ivCartridge.getByte(this.ivCartridgeIndex) & 0xFF);
            }
            this.cartridgeForward();
        }
        return result;
    }
    
    public int readPortEF() {
        this.ivOldEFread |= 0x7;
        if (this.ivCartridge != null) {
            this.cartridgeForward();
            if (this.ivCartridge.isWriteProtected()) {
                this.ivOldEFread &= 0xFE;
            }
            if (this.ivBlockIndex == 0) {
                this.ivOldEFread &= 0xFD;
            }
            if (this.ivBlockIndex < this.ivBlockLen) {
                this.ivOldEFread &= 0xFB;
            }
        }
        return this.ivOldEFread;
    }
    
    public void removeCartridge() {
        this.ivCartridge = null;
    }
    
    public void setStatus(final MicrodriveStatus pMicrodriveStatus) {
    }
    
    public void writePortE7(final int pValue) {
        if (this.ivCartridge != null && this.ivBlockIndex < this.ivBlockLen) {
            if (this.ivBlockIndex >= 0 && (this.ivOldEFwrite & 0x4) == 0x0 && !this.ivCartridge.isWriteProtected()) {
                this.ivCartridge.setByte(this.ivCartridgeIndex, (byte)pValue);
            }
            this.cartridgeForward();
        }
    }
    
    public void writePortEF(final int pValue) {
        final int lowered = this.getLowered(this.ivOldEFwrite, pValue);
        this.ivOldEFwrite = pValue;
        if ((lowered & 0x4) != 0x0 && this.ivCartridge != null) {
            this.cartridgeTruncateIndex();
        }
    }
}
