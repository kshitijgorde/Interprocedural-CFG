// 
// Decompiled by Procyon v0.5.30
// 

package jstella.cart;

import jstella.core.JSSystem;

public class CartridgeFASC extends Cartridge
{
    private static final long serialVersionUID = -7642942968613484045L;
    private int myCurrentBank;
    private int[] myImage;
    private int[] myRAM;
    
    public CartridgeFASC(final int[] aImage) {
        this.myCurrentBank = 0;
        this.myImage = new int[12288];
        this.myRAM = new int[256];
        this.myImage = Cartridge.copyImage(aImage);
        Cartridge.randomizeRAM(this.myRAM);
    }
    
    public String name() {
        return "CartridgeFASC";
    }
    
    public void reset() {
        this.setCurrentBank(2);
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        this.addIndirectAccess(8184, 8192);
        this.addDirectPokeAccess(4096, 4352, this.myRAM, 255);
        this.addDirectPeekAccess(4352, 4608, this.myRAM, 255);
        this.setCurrentBank(2);
    }
    
    public int peek(final int address) {
        final int zMaskedAddress = address & 0xFFF;
        switch (zMaskedAddress) {
            case 4088: {
                this.setCurrentBank(0);
                break;
            }
            case 4089: {
                this.setCurrentBank(1);
                break;
            }
            case 4090: {
                this.setCurrentBank(2);
                break;
            }
        }
        return this.myImage[this.myCurrentBank * 4096 + zMaskedAddress];
    }
    
    public void poke(int address, final int aByte) {
        address &= 0xFFF;
        switch (address) {
            case 4088: {
                this.setCurrentBank(0);
                break;
            }
            case 4089: {
                this.setCurrentBank(1);
                break;
            }
            case 4090: {
                this.setCurrentBank(2);
                break;
            }
        }
    }
    
    public void setCurrentBank(final int bank) {
        if (this.myBankLocked) {
            return;
        }
        this.myCurrentBank = bank;
        this.addDirectPeekAccess(4608, 8184, this.myImage, 4095, this.myCurrentBank * 4096);
    }
    
    public int getCurrentBank() {
        return this.myCurrentBank;
    }
    
    public int bankCount() {
        return 3;
    }
    
    public boolean patch(int address, final int value) {
        address &= 0xFFF;
        this.myImage[this.myCurrentBank * 4096 + address] = value;
        return true;
    }
    
    public int[] getImage() {
        return this.myImage;
    }
}
