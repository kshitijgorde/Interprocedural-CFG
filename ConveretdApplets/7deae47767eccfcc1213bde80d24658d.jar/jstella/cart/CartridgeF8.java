// 
// Decompiled by Procyon v0.5.30
// 

package jstella.cart;

import jstella.core.JSSystem;

public class CartridgeF8 extends Cartridge
{
    private static final long serialVersionUID = 3629215127350071982L;
    private int myCurrentBank;
    private int myResetBank;
    private int[] myImage;
    
    public CartridgeF8(final int[] image, final boolean swapbanks) {
        this.myCurrentBank = 0;
        this.myResetBank = 0;
        this.myImage = new int[8192];
        this.myImage = Cartridge.copyImage(image);
        this.myResetBank = (swapbanks ? 0 : 1);
    }
    
    public String name() {
        return "CartridgeF8";
    }
    
    public void reset() {
        this.setCurrentBank((char)this.myResetBank);
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        this.addIndirectAccess(8184, 8192);
        this.setCurrentBank(1);
    }
    
    public int peek(final int address) {
        final int zNewAddress = address & 0xFFF;
        switch (zNewAddress) {
            case 4088: {
                this.setCurrentBank(0);
                break;
            }
            case 4089: {
                this.setCurrentBank(1);
                break;
            }
        }
        return this.myImage[this.myCurrentBank * 4096 + zNewAddress];
    }
    
    public void poke(final int address, final int aByteValue) {
        final int zNewAddress = address & 0xFFF;
        switch (zNewAddress) {
            case 4088: {
                this.setCurrentBank(0);
                break;
            }
            case 4089: {
                this.setCurrentBank(1);
                break;
            }
        }
    }
    
    protected void setCurrentBank(final int bank) {
        if (this.myBankLocked) {
            return;
        }
        this.myCurrentBank = bank;
        this.addDirectPeekAccess(4096, 8184, this.myImage, 4095, this.myCurrentBank * 4096);
    }
    
    protected int getCurrentBank() {
        return this.myCurrentBank;
    }
    
    protected int bankCount() {
        return 2;
    }
    
    public boolean patch(int address, final int aValue) {
        address &= 0xFFF;
        this.myImage[this.myCurrentBank * 4096 + address] = aValue;
        this.setCurrentBank(this.myCurrentBank);
        return true;
    }
    
    public int[] getImage() {
        return this.myImage;
    }
}
