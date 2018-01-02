// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

public class NFImageChunk
{
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected int[][] pixels;
    
    public NFImageChunk(final int x, final int y, final int w, final int h, final int[][] pixels) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.pixels = pixels;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.w;
    }
    
    public int getHeight() {
        return this.h;
    }
    
    public int[][] getPixels() {
        return this.pixels;
    }
}
