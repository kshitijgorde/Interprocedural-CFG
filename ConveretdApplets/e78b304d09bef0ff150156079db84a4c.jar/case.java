import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class case implements const
{
    private class Va;
    private Rectangle Ha;
    
    public case(final int n, final int n2, final Dimension dimension) {
        this.Ha = new Rectangle(n, n2, dimension.width, dimension.height);
    }
    
    public void b() {
        this.Va = null;
    }
    
    public boolean b(final class va) {
        if (this._(va)) {
            this.Va = va;
            return true;
        }
        return false;
    }
    
    public boolean _(final class class1) {
        if (class1 != null) {
            if (this.Va == null && class1.getID() == 0) {
                return true;
            }
            if (this.Va != null && class1._() == this.Va._() && class1.getID() == this.Va.getID() + 1) {
                return true;
            }
        }
        return false;
    }
    
    public void a(final Graphics graphics) {
        if (this.Va != null) {
            graphics.drawImage(this.Va.b(), this.Ha.x, this.Ha.y, null);
        }
    }
    
    public boolean contains(final int n, final int n2) {
        return this.Ha.contains(n, n2);
    }
    
    public class b() {
        return null;
    }
    
    public boolean h() {
        return this.Va != null && this.Va.getID() == 12;
    }
}
