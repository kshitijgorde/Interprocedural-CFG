// 
// Decompiled by Procyon v0.5.30
// 

package jstella.cart;

import jstella.core.JSSystem;

public class CartridgeF6SC extends Cartridge
{
    private static final long serialVersionUID = -1055099564873018150L;
    private static final int CART_SIZE = 16384;
    private static final String CART_NAME = "CartridgeF6SC";
    private char myCurrentBank;
    private int myResetBank;
    private int[] myImage;
    private int[] myRAM;
    
    public CartridgeF6SC(final int[] image) {
        this.myImage = new int[16384];
        this.myRAM = new int[128];
        this.myImage = Cartridge.copyImage(image);
        Cartridge.randomizeRAM(this.myRAM);
    }
    
    public String name() {
        return "CartridgeF6SC";
    }
    
    public void reset() {
        this.setCurrentBank(0);
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        this.addIndirectAccess(8182, 8192);
        this.addDirectPokeAccess(4096, 4224, this.myRAM, 127);
        this.addDirectPeekAccess(4224, 4352, this.myRAM, 127);
        this.setCurrentBank(0);
    }
    
    public int peek(final int address) {
        final int zNewAddress = address & 0xFFF;
        switch (zNewAddress) {
            case 4086: {
                this.setCurrentBank(0);
                break;
            }
            case 4087: {
                this.setCurrentBank(1);
                break;
            }
            case 4088: {
                this.setCurrentBank(2);
                break;
            }
            case 4089: {
                this.setCurrentBank(3);
                break;
            }
        }
        return this.myImage[this.myCurrentBank * '\u1000' + zNewAddress];
    }
    
    public void poke(final int address, final int aByteValue) {
        final int zNewAddress = address & 0xFFF;
        switch (zNewAddress) {
            case 4086: {
                this.setCurrentBank(0);
                break;
            }
            case 4087: {
                this.setCurrentBank(1);
                break;
            }
            case 4088: {
                this.setCurrentBank(2);
                break;
            }
            case 4089: {
                this.setCurrentBank(3);
                break;
            }
        }
    }
    
    protected void setCurrentBank(final int bank) {
        if (this.myBankLocked) {
            return;
        }
        this.myCurrentBank = (char)bank;
        this.addDirectPeekAccess(4352, 8182, this.myImage, 4095, this.myCurrentBank * '\u1000');
    }
    
    protected int getCurrentBank() {
        return this.myCurrentBank;
    }
    
    protected int bankCount() {
        return 4;
    }
    
    public boolean patch(int address, final int aValue) {
        address &= 0xFFF;
        this.myImage[this.myCurrentBank * '\u1000' + address] = aValue;
        this.setCurrentBank(this.myCurrentBank);
        return true;
    }
    
    public int[] getImage() {
        return this.myImage;
    }
}
