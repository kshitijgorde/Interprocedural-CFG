// 
// Decompiled by Procyon v0.5.30
// 

package jstella.cart;

import jstella.core.JSSystem;

public class CartridgeE7 extends Cartridge
{
    private static final long serialVersionUID = 93485597991685138L;
    int[] myCurrentSlice;
    int myCurrentRAM;
    int[] myImage;
    int[] myRAM;
    
    CartridgeE7(final int[] image) {
        this.myCurrentSlice = new int[2];
        this.myCurrentRAM = 0;
        this.myImage = new int[16384];
        this.myRAM = new int[2048];
        this.myImage = Cartridge.copyImage(image);
        Cartridge.randomizeRAM(this.myRAM);
    }
    
    public String name() {
        return "CartridgeE7";
    }
    
    public void reset() {
        this.bankRAM(0);
        this.setCurrentBank(0);
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        this.addIndirectAccess(8160, 8192);
        this.addDirectPeekAccess(6656, 8160, this.myImage, 2047, 14336);
        this.myCurrentSlice[1] = 7;
        this.bankRAM(0);
        this.setCurrentBank(0);
    }
    
    public int peek(int address) {
        address = (char)(address & 0xFFF);
        if (address >= 4064 && address <= 4071) {
            this.setCurrentBank(address & 0x7);
        }
        else if (address >= 4072 && address <= 4075) {
            this.bankRAM(address & 0x3);
        }
        return this.myImage[(this.myCurrentSlice[address >> 11] << 11) + (address & 0x7FF)];
    }
    
    public void poke(int address, final int aByteValue) {
        address = (char)(address & 0xFFF);
        if (address >= 4064 && address <= 4071) {
            this.setCurrentBank(address & 0x7);
        }
        else if (address >= 4072 && address <= 4075) {
            this.bankRAM(address & 0x3);
        }
    }
    
    void bankRAM(final int bank) {
        this.myCurrentRAM = bank;
        final int offset = bank << 8;
        final int shift = 6;
        this.addDirectPokeAccess(6144, 6400, this.myRAM, 255, 1024 + (bank << 8));
        this.addDirectPeekAccess(6400, 6656, this.myRAM, 255, 1024 + (bank << 8));
    }
    
    public void setCurrentBank(final int slice) {
        if (this.myBankLocked) {
            return;
        }
        if ((this.myCurrentSlice[0] = slice) != 7) {
            this.addDirectPeekAccess(4096, 6144, this.myImage, 2047, slice << 11);
        }
        else {
            this.addDirectPokeAccess(4096, 5120, this.myRAM, 1023);
            this.addDirectPeekAccess(5120, 6144, this.myRAM, 1023);
        }
    }
    
    public int getCurrentBank() {
        return this.myCurrentSlice[0];
    }
    
    public int bankCount() {
        return 8;
    }
    
    public boolean patch(int address, final int value) {
        address = (char)(address & 0xFFF);
        this.myImage[(this.myCurrentSlice[address >> 11] << 11) + (address & 0x7FF)] = value;
        this.setCurrentBank(this.myCurrentSlice[0]);
        return true;
    }
    
    public int[] getImage() {
        return this.myImage;
    }
}
