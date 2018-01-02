// 
// Decompiled by Procyon v0.5.30
// 

package jstella.cart;

import jstella.core.JSSystem;

class Cartridge2K extends Cartridge
{
    private static final long serialVersionUID = 6519396049383803731L;
    private int[] myImage;
    
    public Cartridge2K(final int[] image) {
        this.myImage = new int[2048];
        this.myImage = Cartridge.copyImage(image);
    }
    
    public String name() {
        return "Cartridge2K";
    }
    
    public void reset() {
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        this.addDirectPeekAccess(4096, 8192, this.myImage, 2047);
    }
    
    public int peek(final int address) {
        return this.myImage[address & 0x7FF];
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
        this.myImage[address & 0x7FF] = value;
        return true;
    }
    
    public int[] getImage() {
        return this.myImage;
    }
}
