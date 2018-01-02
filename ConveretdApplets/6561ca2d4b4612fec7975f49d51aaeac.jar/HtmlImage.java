import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class HtmlImage
{
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected Image img;
    
    protected HtmlImage(final int x, final int y, final int w, final int h, final Image img) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.img = img;
    }
}
