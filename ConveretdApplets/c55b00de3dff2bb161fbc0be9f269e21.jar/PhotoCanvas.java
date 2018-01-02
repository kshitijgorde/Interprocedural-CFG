import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class PhotoCanvas extends Canvas
{
    private String text;
    private Image image;
    
    public PhotoCanvas() {
        this.text = new String();
    }
    
    public void setText(final String text) {
        this.text = text;
        this.image = null;
        this.repaint();
    }
    
    public void setImage(final Image image) {
        this.image = image;
        this.text = "";
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.text.length() > 0) {
            graphics.drawString(this.text, 20, 20);
        }
        if (this.image != null) {
            graphics.drawImage(this.image, 0, 0, this);
        }
    }
}
