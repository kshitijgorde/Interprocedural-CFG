import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class break implements const
{
    private class Wa;
    private Rectangle Ha;
    private Color Ra;
    
    public break(final int n, final int n2, final Dimension dimension) {
        this.Ra = Color.blue;
        this.Ha = new Rectangle(n, n2, dimension.width, dimension.height);
    }
    
    public void b() {
        this.Wa = null;
    }
    
    public boolean b(final class wa) {
        if (this.Wa == null) {
            this.Wa = wa;
            return true;
        }
        return false;
    }
    
    public void a(final Graphics graphics) {
        if (this.Wa != null) {
            graphics.drawImage(this.Wa.b(), this.Ha.x, this.Ha.y, null);
            if (this.Wa.g()) {
                graphics.setColor(this.Ra);
                for (int i = 0; i < 2; ++i) {
                    graphics.drawRect(this.Ha.x + i, this.Ha.y + i, this.Ha.width - i * 2, this.Ha.height - i * 2);
                }
            }
        }
    }
    
    public boolean contains(final int n, final int n2) {
        return this.Ha.contains(n, n2);
    }
    
    public class b() {
        return this.Wa;
    }
    
    public boolean i() {
        return this.Wa == null;
    }
}
