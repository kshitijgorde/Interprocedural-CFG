import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class al extends Component
{
    public d a;
    public final boolean b;
    public int c;
    
    public al(final d a, final boolean b) {
        this.a = a;
        this.b = b;
        this.setFont(this.a.i().o);
        this.setBackground(this.a.i().c);
        this.a.d();
        this.setForeground(f.a(this.getBackground()));
    }
    
    public abstract Dimension c();
    
    public abstract void paint(final Graphics p0);
}
