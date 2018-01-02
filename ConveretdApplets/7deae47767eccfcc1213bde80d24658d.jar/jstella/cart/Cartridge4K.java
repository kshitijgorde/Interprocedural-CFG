// 
// Decompiled by Procyon v0.5.30
// 

package jstella.cart;

import jstella.core.JSSystem;

public class Cartridge4K extends Cartridge
{
    private static final long serialVersionUID = -3240308689643904765L;
    private static final int CARTRIDGE_MASK_VALUE = 4095;
    private static final int CARTRIDGE_SIZE = 4096;
    private static final int CARTRIDGE_BANK_COUNT = 1;
    int[] myImage;
    
    public Cartridge4K(final int[] image) {
        this.myImage = new int[4096];
        this.myImage = Cartridge.copyImage(image);
    }
    
    public String name() {
        return "Cartridge4K";
    }
    
    public void reset() {
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        this.addDirectPeekAccess(4096, 8192, this.myImage, 4095);
    }
    
    public int peek(final int address) {
        return this.myImage[address & 0xFFF];
    }
    
    public void poke(final int aC, final int aB) {
    }
    
    protected void setCurrentBank(final int bank) {
    }
    
    protected int getCurrentBank() {
        return 0;
    }
    
    protected int bankCount() {
        return 1;
    }
    
    public boolean patch(final int address, final int value) {
        this.myImage[address & 0xFFF] = value;
        return true;
    }
    
    public int[] getImage() {
        return this.myImage;
    }
}
