// 
// Decompiled by Procyon v0.5.30
// 

package jstella.cart;

import jstella.core.JSSystem;

public class CartridgeF4SC extends Cartridge
{
    private static final long serialVersionUID = 9208964210037667554L;
    private static final int CART_SIZE = 32768;
    private static final String CART_NAME = "CartridgeF4SC";
    private int[] myRAM;
    private int myCurrentBank;
    private int[] myImage;
    
    public CartridgeF4SC(final int[] image) {
        this.myRAM = new int[128];
        this.myCurrentBank = 0;
        this.myImage = new int[32768];
        this.myImage = Cartridge.copyImage(image);
        Cartridge.randomizeRAM(this.myRAM);
    }
    
    public String name() {
        return "CartridgeF4SC";
    }
    
    public void reset() {
        this.setCurrentBank(0);
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        this.addIndirectAccess(8180, 8192);
        this.addDirectPokeAccess(4096, 4224, this.myRAM, 127);
        this.addDirectPeekAccess(4224, 4352, this.myRAM, 127);
        this.setCurrentBank(0);
    }
    
    public int peek(int address) {
        address &= 0xFFF;
        if (address >= 4084 && address <= 4091) {
            this.setCurrentBank(address - 4084);
        }
        return this.myImage[this.myCurrentBank * 4096 + address];
    }
    
    public void poke(int address, final int aByteValue) {
        address &= 0xFFF;
        if (address >= 4084 && address <= 4091) {
            this.setCurrentBank(address - 4084);
        }
    }
    
    protected void setCurrentBank(final int bank) {
        if (this.myBankLocked) {
            return;
        }
        this.myCurrentBank = bank;
        this.addDirectPeekAccess(4352, 8180, this.myImage, 4095, this.myCurrentBank * 4096);
    }
    
    protected int getCurrentBank() {
        return this.myCurrentBank;
    }
    
    protected int bankCount() {
        return 8;
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
