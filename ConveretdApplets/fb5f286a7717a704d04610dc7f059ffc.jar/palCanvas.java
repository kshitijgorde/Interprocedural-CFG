import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class palCanvas extends Canvas
{
    public void paint(final Graphics graphics) {
        graphics.drawImage(ColoringBook.palPic, 0, 0, 100, 60, this);
    }
}
