// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.SwingUtilities;

final class bv extends bP implements e
{
    private int b;
    private final cJ c;
    
    public bv(final cJ c, final String s) {
        this.c = c;
        super(s);
        this.a(this);
    }
    
    public final void b() {
        cJ.l(this.c);
        super.b();
        this.b = cJ.m(this.c);
    }
    
    public final void c() {
        if (this.a != null) {
            this.a.d();
        }
        super.c();
        cJ.l(this.c);
    }
    
    public final void a(final am am) {
        SwingUtilities.invokeLater(new m(this.c, am, this.b));
    }
}
