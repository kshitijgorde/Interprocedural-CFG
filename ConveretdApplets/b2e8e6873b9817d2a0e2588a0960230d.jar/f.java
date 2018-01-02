import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class f extends h
{
    private Image iKc;
    
    public f(final Rectangle rectangle, final Image iKc) {
        super(rectangle);
        this.iKc = iKc;
    }
    
    public boolean e() {
        return j.NJc = !j.NJc;
    }
    
    public void _(final Graphics graphics) {
        if (j.NJc) {
            graphics.drawImage(this.iKc, super.XJc.x, super.XJc.y, null);
        }
    }
}
