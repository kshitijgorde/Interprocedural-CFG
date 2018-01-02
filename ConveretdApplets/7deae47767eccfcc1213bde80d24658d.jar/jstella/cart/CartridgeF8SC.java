// 
// Decompiled by Procyon v0.5.30
// 

package jstella.cart;

import jstella.core.JSSystem;

public class CartridgeF8SC extends Cartridge
{
    private static final long serialVersionUID = 8192869953213524247L;
    private static final int CART_SIZE = 8192;
    private static final String CART_NAME = "CartridgeF8SC";
    private int myCurrentBank;
    private int myResetBank;
    private int[] myImage;
    private int[] myRAM;
    
    public CartridgeF8SC(final int[] image) {
        this.myCurrentBank = 0;
        this.myResetBank = 0;
        this.myImage = new int[8192];
        this.myRAM = new int[128];
        this.myImage = Cartridge.copyImage(image);
        Cartridge.randomizeRAM(this.myRAM);
    }
    
    public String name() {
        return "CartridgeF8SC";
    }
    
    public void reset() {
        this.setCurrentBank(1);
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        this.addIndirectAccess(8184, 8192);
        this.addDirectPokeAccess(4096, 4224, this.myRAM, 127);
        this.addDirectPeekAccess(4224, 4352, this.myRAM, 127);
        this.setCurrentBank(1);
    }
    
    public int peek(final int address) {
        final int zNewAddress = address & 0xFFF;
        if (!this.myBankLocked) {
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
        return this.myImage[this.myCurrentBank * 4096 + zNewAddress];
    }
    
    public void poke(final int address, final int aByteValue) {
        final int zNewAddress = address & 0xFFF;
        if (!this.myBankLocked) {
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
    }
    
    protected void setCurrentBank(final int bank) {
        if (this.myBankLocked) {
            return;
        }
        this.myCurrentBank = bank;
        this.addDirectPeekAccess(4352, 8184, this.myImage, 4095, this.myCurrentBank << 12);
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
