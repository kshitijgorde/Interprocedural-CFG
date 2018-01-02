// 
// Decompiled by Procyon v0.5.30
// 

class SnowImage
{
    int w;
    int h;
    int halfX;
    int halfY;
    int[] pix;
    
    SnowImage(final int[] pix, final int w, final int h) {
        this.pix = pix;
        this.w = w;
        this.h = h;
        this.halfX = w / 2;
        this.halfY = h / 2;
    }
}
