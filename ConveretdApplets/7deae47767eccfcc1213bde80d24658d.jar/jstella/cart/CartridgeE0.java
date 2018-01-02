// 
// Decompiled by Procyon v0.5.30
// 

package jstella.cart;

import jstella.core.JSSystem;

public class CartridgeE0 extends Cartridge
{
    private static final long serialVersionUID = 8410286352380616979L;
    private char myCurrentBank;
    private int myResetBank;
    private int[] myImage;
    private int[] myCurrentSlice;
    
    public CartridgeE0(final int[] image) {
        this.myImage = new int[8192];
        this.myCurrentSlice = new int[4];
        this.myImage = Cartridge.copyImage(image);
    }
    
    public String name() {
        return "CartridgeE0";
    }
    
    public void reset() {
        this.segmentZero(4);
        this.segmentOne(5);
        this.segmentTwo(6);
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        this.myCurrentSlice[3] = 7;
        this.addDirectPeekAccess(7168, 8160, this.myImage, 1023, 7168);
        this.addIndirectAccess(8160, 8192);
        this.segmentZero(4);
        this.segmentOne(5);
        this.segmentTwo(6);
    }
    
    public int peek(int address) {
        address = (char)(address & 0xFFF);
        if (!this.myBankLocked) {
            if (address >= 4064 && address <= 4071) {
                this.segmentZero(address & 0x7);
            }
            else if (address >= 4072 && address <= 4079) {
                this.segmentOne(address & 0x7);
            }
            else if (address >= 4080 && address <= 4087) {
                this.segmentTwo(address & 0x7);
            }
        }
        return this.myImage[(this.myCurrentSlice[address >> 10] << 10) + (address & 0x3FF)];
    }
    
    public void poke(int address, final int aByteValue) {
        address = (char)(address & 0xFFF);
        if (!this.myBankLocked) {
            if (address >= 4064 && address <= 4071) {
                this.segmentZero(address & 0x7);
            }
            else if (address >= 4072 && address <= 4079) {
                this.segmentOne(address & 0x7);
            }
            else if (address >= 4080 && address <= 4087) {
                this.segmentTwo(address & 0x7);
            }
        }
    }
    
    private void segmentZero(final int slice) {
        this.myCurrentSlice[0] = slice;
        this.addDirectPeekAccess(4096, 5120, this.myImage, 1023, slice << 10);
    }
    
    private void segmentOne(final int slice) {
        this.myCurrentSlice[1] = slice;
        this.addDirectPeekAccess(5120, 6144, this.myImage, 1023, slice << 10);
    }
    
    void segmentTwo(final int slice) {
        this.myCurrentSlice[2] = slice;
        this.addDirectPeekAccess(6144, 7168, this.myImage, 1023, slice << 10);
    }
    
    protected void setCurrentBank(final int bank) {
    }
    
    protected int getCurrentBank() {
        return 0;
    }
    
    protected int bankCount() {
        return 1;
    }
    
    public boolean patch(int address, final int aValue) {
        address = (char)(address & 0xFFF);
        this.myImage[(this.myCurrentSlice[address >> 10] << 10) + (address & 0x3FF)] = aValue;
        return true;
    }
    
    public int[] getImage() {
        return this.myImage;
    }
}
