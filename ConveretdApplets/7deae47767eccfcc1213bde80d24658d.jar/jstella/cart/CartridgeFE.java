// 
// Decompiled by Procyon v0.5.30
// 

package jstella.cart;

import jstella.core.JSSystem;

public class CartridgeFE extends Cartridge
{
    private static final long serialVersionUID = 3716862330037765895L;
    private int[] myImage;
    
    public CartridgeFE(final int[] image) {
        this.myImage = new int[8192];
        this.myImage = Cartridge.copyImage(image);
    }
    
    public String name() {
        return "CartridgeFE";
    }
    
    public void reset() {
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        this.addIndirectAccess(4096, 8192);
    }
    
    public int peek(final int address) {
        return this.myImage[(address & 0xFFF) + (((address & 0x2000) == 0x0) ? 4096 : 0)];
    }
    
    public void poke(final int address, final int aByteValue) {
    }
    
    protected void setCurrentBank(final int bank) {
    }
    
    protected int getCurrentBank() {
        return 0;
    }
    
    protected int bankCount() {
        return 1;
    }
    
    public boolean patch(final int address, final int aValue) {
        this.myImage[(address & 0xFFF) + (((address & 0x2000) == 0x0) ? 4096 : 0)] = aValue;
        return true;
    }
    
    public int[] getImage() {
        return this.myImage;
    }
}
