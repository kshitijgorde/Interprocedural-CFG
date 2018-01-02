// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.awt.Insets;
import java.awt.Color;

public class edge
{
    public Color a;
    public int b;
    public Color c;
    public Color d;
    public Color e;
    public Color f;
    public Color g;
    public Color h;
    public Color i;
    public int j;
    public Insets k;
    
    public void a(final Insets insets) {
        this.k = ((insets == null) ? null : ((Insets)insets.clone()));
    }
    
    public Insets a() {
        return (this.k == null) ? null : ((Insets)this.k.clone());
    }
    
    public void a(final int j, final pen pen, final pen pen2) {
        Color b = null;
        Color c = null;
        Color b2 = null;
        Color c2 = null;
        if (pen != null) {
            b = pen.b;
            c = pen.c;
        }
        if (pen2 != null) {
            b2 = pen2.b;
            c2 = pen2.c;
        }
        if (c != null) {
            this.f = c;
        }
        if (b != null) {
            this.g = b;
        }
        if (c2 != null) {
            this.h = c2;
        }
        if (b2 != null) {
            this.i = b2;
        }
        this.j = j;
    }
    
    public void b() {
        final Color f = this.f;
        this.f = this.i;
        this.i = f;
        final Color g = this.g;
        this.g = this.h;
        this.h = g;
    }
    
    public void c() {
        this.j = 1;
        this.f = new Color(132, 132, 132);
        this.g = new Color(66, 66, 66);
        this.h = Color.white;
        this.i = new Color(214, 214, 206);
    }
    
    public void a(final pen pen) {
        if (pen == null) {
            return;
        }
        this.c = pen.b;
        this.d = pen.c;
    }
    
    public edge() {
        this.c = Color.gray;
        this.d = Color.white;
        this.e = Color.lightGray;
        this.f = Color.gray;
        this.g = Color.black;
        this.h = Color.white;
        this.i = Color.lightGray;
        this.k = new Insets(0, 0, 0, 0);
    }
}
