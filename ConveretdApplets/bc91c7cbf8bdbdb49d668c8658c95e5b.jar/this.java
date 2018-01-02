import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class this extends Canvas
{
    private Image Aa;
    private int width;
    private int height;
    
    public this(final Image aa, final int width, final int height) {
        this.Aa = aa;
        this.width = width;
        this.height = height;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.Aa, 0, 0, this.width, this.height, this);
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
}
