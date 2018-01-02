// 
// Decompiled by Procyon v0.5.30
// 

package jstella.cart;

import jstella.core.JSSystem;

public class Cartridge3F extends Cartridge
{
    private static final long serialVersionUID = 4078181695753196546L;
    private int myCurrentBank;
    private int[] myImage;
    
    Cartridge3F(final int[] image) {
        this.myImage = Cartridge.copyImage(image);
    }
    
    public String name() {
        return "Cartridge3F";
    }
    
    public void reset() {
        this.setCurrentBank(0);
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        this.addIndirectAccess(0, 64);
        this.addDirectPeekAccess(6144, 8192, this.myImage, 2047, this.myImage.length - 2048);
        this.setCurrentBank(0);
    }
    
    public int peek(int address) {
        address &= 0xFFF;
        if (address < 2048) {
            return this.myImage[(address & 0x7FF) + this.myCurrentBank * 2048];
        }
        return this.myImage[(address & 0x7FF) + this.myImage.length - 2048];
    }
    
    public void poke(int address, final int value) {
        address &= 0xFFF;
        if (address <= 63) {
            this.setCurrentBank(value);
        }
        this.myConsole.getTIA().poke(address, value);
    }
    
    protected void setCurrentBank(final int bank) {
        if (this.myBankLocked) {
            return;
        }
        if (bank * 2048 < this.myImage.length) {
            this.myCurrentBank = bank;
        }
        else {
            this.myCurrentBank = bank % (this.myImage.length / 2048);
        }
        this.addDirectPeekAccess(4096, 6144, this.myImage, 2047, this.myCurrentBank * 2048);
    }
    
    protected int getCurrentBank() {
        return this.myCurrentBank;
    }
    
    protected int bankCount() {
        return this.myImage.length / 2048;
    }
    
    public boolean patch(int address, final int value) {
        address &= 0xFFF;
        if (address < 2048) {
            this.myImage[(address & 0x7FF) + this.myCurrentBank * 2048] = value;
        }
        else {
            this.myImage[(address & 0x7FF) + this.myImage.length - 2048] = value;
        }
        return true;
    }
    
    protected int[] getImage() {
        return this.myImage;
    }
}
