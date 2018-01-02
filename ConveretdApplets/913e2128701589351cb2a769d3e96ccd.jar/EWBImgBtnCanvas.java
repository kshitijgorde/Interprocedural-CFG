import java.awt.Graphics;
import java.awt.Event;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class EWBImgBtnCanvas extends Canvas
{
    private Image image;
    private EWBLocTextPanel locText;
    private EWBMapCanvas mapC;
    
    EWBImgBtnCanvas(final Image image, final EWBLocTextPanel locText, final EWBMapCanvas mapC) {
        this.image = image;
        this.locText = locText;
        this.mapC = mapC;
        this.resize(this.image.getWidth(this), this.image.getHeight(this));
        this.setBackground(Color.white);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 501) {
            this.locText.setDefault();
            this.mapC.setDefault();
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.image, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
