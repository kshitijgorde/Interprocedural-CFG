// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Panel;

public abstract class bg extends Panel
{
    public static final Color c;
    public static final Color i;
    public static final Color a;
    public static final Color b;
    public static final Color l;
    protected u c;
    protected as f;
    
    public boolean a(final a a) {
        return this.c.a((az)a);
    }
    
    public void show() {
        super.show();
        this.c.requestFocus();
    }
    
    protected void a(final a a) {
        final Color[] a2 = F.a(a);
        this.c.a(a, a2[0], a2[1]);
        if (a.j) {
            this.c.a(a, Color.red, Color.pink);
        }
        if (a.h) {
            this.c.a(a, true);
        }
        else {
            this.c.a(a, false);
        }
    }
    
    public void a(final a a, final boolean b) {
        synchronized (this.c) {
            if (this.f.a(24) || !a.a(23)) {
                final int a2 = this.c.a((az)a);
                if (a2 == -1) {
                    if (b) {
                        this.c.a((az)a);
                    }
                }
                else {
                    this.c.b(a, a2);
                }
                this.a(a);
            }
        }
    }
    
    public bg() {
        this.c = new u();
    }
    
    static {
        c = new Color(153);
        i = new Color(10079487);
        a = new Color(16711680);
        b = new Color(10079487);
        l = new Color(39168);
    }
}
