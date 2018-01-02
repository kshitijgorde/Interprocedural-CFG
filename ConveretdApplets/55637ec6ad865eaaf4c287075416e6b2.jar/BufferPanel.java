import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class BufferPanel extends Panel
{
    private Image offscreenImage;
    private Graphics offscreen;
    
    protected void clearBuffer() {
        this.offscreen.setColor(this.getBackground());
        this.offscreen.fillRect(0, 0, this.size().width, this.size().height);
    }
    
    protected void drawOffscreen(final Graphics graphics) {
    }
    
    protected Graphics getBufferGraphics() {
        return this.offscreen;
    }
    
    protected Image getBufferImage() {
        return this.offscreenImage;
    }
    
    public void paint(final Graphics graphics) {
        if (this.offscreenImage != null) {
            graphics.drawImage(this.offscreenImage, 0, 0, null);
        }
    }
    
    protected void redraw() {
        if (this.offscreen != null) {
            this.drawOffscreen(this.offscreen);
            this.repaint();
        }
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
        this.offscreenImage = this.createImage(n3, n4);
        this.drawOffscreen(this.offscreen = this.offscreenImage.getGraphics());
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
        this.redraw();
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.redraw();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
