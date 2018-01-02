import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class X extends Canvas
{
    private boolean drawImage;
    private Image getHeight;
    private Image getWidth;
    private H paint;
    
    X(final Image getHeight, final Image getWidth, final H paint) {
        this.getHeight = getHeight;
        this.getWidth = getWidth;
        this.paint = paint;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        this.paint.I(graphics);
        if (this.drawImage) {
            graphics.drawImage(this.getHeight, 0, 0, null);
        }
        else {
            graphics.drawImage(this.getWidth, 0, 0, null);
        }
    }
    
    public final void setEnabled(final boolean drawImage) {
        this.drawImage = drawImage;
        this.repaint();
    }
    
    public final Dimension getSize() {
        return new Dimension(this.getHeight.getWidth(this), this.getHeight.getHeight(this));
    }
}
