// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Panel;

public abstract class Q extends Panel
{
    public static final Color c;
    public static final Color h;
    public static final Color a;
    public static final Color b;
    public static final Color i;
    protected l b;
    public t d;
    
    public boolean a(final ab ab) {
        return this.b.a((aU)ab);
    }
    
    public void show() {
        super.show();
        this.b.requestFocus();
    }
    
    protected void a(final ab ab) {
        final Color[] a = cG.a(ab);
        this.b.a(ab, a[0], a[1]);
        if (ab.h) {
            this.b.b(ab, true);
        }
        else {
            this.b.b(ab, false);
        }
    }
    
    public void a(final ab ab, final boolean b) {
        synchronized (this.b) {
            if (this.d.d(24) || !ab.d(23)) {
                final int a = this.b.a((aU)ab);
                if (a == -1) {
                    if (b) {
                        this.b.c(ab);
                    }
                }
                else {
                    this.b.b(ab, a);
                }
                this.a(ab);
            }
        }
    }
    
    public Q() {
        this.b = new l();
    }
    
    static {
        c = new Color(153);
        h = new Color(10079487);
        a = new Color(16711680);
        b = new Color(10079487);
        i = new Color(39168);
    }
}
