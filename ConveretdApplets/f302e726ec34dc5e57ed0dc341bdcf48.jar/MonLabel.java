import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

public class MonLabel extends Label
{
    private Image image;
    
    public MonLabel(final Image image) {
        this.image = null;
        this.image = image;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        graphics.drawImage(this.image, 0, 0, this);
    }
}
