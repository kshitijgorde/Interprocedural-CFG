// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Panel;

public abstract class K extends Panel
{
    public static final Color a;
    public static final Color b;
    public static final Color c;
    public static final Color d;
    public static final Color e;
    protected am b;
    protected be f;
    
    public boolean a(final aq aq) {
        return this.b.a((bk)aq);
    }
    
    public void show() {
        super.show();
        this.b.requestFocus();
    }
    
    protected void a(final aq aq) {
        final Color[] a = aI.a(aq);
        this.b.a(aq, a[0], a[1]);
        if (aq.u) {
            this.b.a(aq, Color.red, Color.pink);
        }
        if (aq.v) {
            this.b.a(aq, true);
        }
        else {
            this.b.a(aq, false);
        }
    }
    
    public void a(final aq aq, final boolean b) {
        synchronized (this.b) {
            if (this.f.c(24) || !aq.c(23)) {
                final int a = this.b.a((bk)aq);
                if (a == -1) {
                    if (b) {
                        this.b.a((bk)aq);
                    }
                }
                else {
                    this.b.b(aq, a);
                }
                this.a(aq);
            }
        }
    }
    
    public K() {
        this.b = new am();
    }
    
    static {
        a = new Color(153);
        b = new Color(10079487);
        c = new Color(16711680);
        d = new Color(10079487);
        e = new Color(39168);
    }
}
