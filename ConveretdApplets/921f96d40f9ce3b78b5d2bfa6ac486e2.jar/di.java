import java.awt.Dimension;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class di extends Panel
{
    public int p;
    public int d;
    
    public di(final int p2, final int d) {
        this.p = p2;
        this.d = d;
    }
    
    public di(final int p3, final int d, final Color background) {
        this.p = p3;
        this.d = d;
        this.setBackground(background);
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(this.p, this.d);
    }
}
