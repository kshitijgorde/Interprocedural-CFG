import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class s extends u
{
    private Image A2;
    
    public s(final Rectangle rectangle, final Image a2) {
        super(rectangle);
        this.A2 = a2;
    }
    
    public boolean f() {
        return abstract.e2 = !abstract.e2;
    }
    
    public void b(final Graphics graphics) {
        if (abstract.e2) {
            graphics.drawImage(this.A2, super.o2.x, super.o2.y, null);
        }
    }
}
