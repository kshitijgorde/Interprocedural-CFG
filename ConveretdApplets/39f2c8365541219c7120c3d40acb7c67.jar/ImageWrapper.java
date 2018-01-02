import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class ImageWrapper
{
    public Image image;
    public int x;
    public int width;
    public String label;
    public String symbol;
    public String link;
    
    public String toString() {
        return new String("ImageWrapper: " + this.label + "(" + this.symbol + ") at [" + this.x + "," + (this.x + this.width) + "] (" + this.link + ")");
    }
}
