import java.awt.Rectangle;
import java.awt.Scrollbar;

// 
// Decompiled by Procyon v0.5.30
// 

public class g extends Scrollbar
{
    public boolean af;
    
    public g(final h h, final int n) {
        super(n);
        this.af = true;
    }
    
    public final void setBounds(final Rectangle rectangle) {
        this.reshape(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public final void setBounds(final int n, final int n2, final int n3, final int n4) {
        this.reshape(n, n2, n3, n4);
    }
    
    public final void reshape(int n, int n2, final int n3, int n4) {
        if (this.af) {
            ++n;
            ++n4;
        }
        else {
            --n;
        }
        --n2;
        ++n4;
        super.reshape(n, n2, n3, n4);
    }
    
    public final void l(final String s) {
        this.af = s.equals("right");
    }
}
