import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class h extends Canvas
{
    public Image a9;
    
    public h(final Image a9) {
        this.a9 = a9;
    }
    
    public h() {
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.a9, 0, 0, this);
    }
}
