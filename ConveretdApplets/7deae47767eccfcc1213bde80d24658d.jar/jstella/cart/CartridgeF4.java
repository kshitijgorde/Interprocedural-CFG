// 
// Decompiled by Procyon v0.5.30
// 

package jstella.cart;

import jstella.core.JSSystem;

public class CartridgeF4 extends Cartridge
{
    private static final long serialVersionUID = 9208964210037667554L;
    private static final int CART_SIZE = 32768;
    private static final String CART_NAME = "CartridgeF4";
    private int myCurrentBank;
    private int[] myImage;
    
    public CartridgeF4(final int[] image) {
        this.myCurrentBank = 0;
        this.myImage = new int[32768];
        this.myImage = Cartridge.copyImage(image);
    }
    
    public String name() {
        return "CartridgeF4";
    }
    
    public void reset() {
        this.setCurrentBank(0);
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        this.addIndirectAccess(8180, 8192);
        this.setCurrentBank(0);
    }
    
    public int peek(final int address) {
        final int zNewAddress = address & 0xFFF;
        switch (zNewAddress) {
            case 4084: {
                this.setCurrentBank(0);
                break;
            }
            case 4085: {
                this.setCurrentBank(1);
                break;
            }
            case 4086: {
                this.setCurrentBank(2);
                break;
            }
            case 4087: {
                this.setCurrentBank(3);
                break;
            }
            case 4088: {
                this.setCurrentBank(4);
                break;
            }
            case 4089: {
                this.setCurrentBank(5);
                break;
            }
            case 4090: {
                this.setCurrentBank(6);
                break;
            }
            case 4091: {
                this.setCurrentBank(7);
                break;
            }
        }
        return this.myImage[this.myCurrentBank * 4096 + zNewAddress];
    }
    
    public void poke(final int address, final int aByteValue) {
        final int zNewAddress = address & 0xFFF;
        switch (zNewAddress) {
            case 4084: {
                this.setCurrentBank(0);
                break;
            }
            case 4085: {
                this.setCurrentBank(1);
                break;
            }
            case 4086: {
                this.setCurrentBank(2);
                break;
            }
            case 4087: {
                this.setCurrentBank(3);
                break;
            }
            case 4088: {
                this.setCurrentBank(4);
                break;
            }
            case 4089: {
                this.setCurrentBank(5);
                break;
            }
            case 4090: {
                this.setCurrentBank(6);
                break;
            }
            case 4091: {
                this.setCurrentBank(7);
                break;
            }
        }
    }
    
    protected void setCurrentBank(final int bank) {
        if (this.myBankLocked) {
            return;
        }
        this.myCurrentBank = bank;
        this.addDirectPeekAccess(4096, 8180, this.myImage, 4095, this.myCurrentBank * 4096);
    }
    
    protected int getCurrentBank() {
        return this.myCurrentBank;
    }
    
    protected int bankCount() {
        return 4;
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
