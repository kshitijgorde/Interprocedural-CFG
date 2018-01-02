// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;

public final class co extends u
{
    String[] a;
    private int a;
    private int b;
    private boolean a;
    private Color h;
    private Font b;
    private FontMetrics a;
    
    co(int i) {
        super(true);
        this.h = Color.red;
        this.a = 80;
        this.b = ap.a();
        this.b = 1;
        this.a = new String[1];
        for (i = 0; i < 1; ++i) {
            this.a[0] = "";
        }
    }
    
    public final void a(final ei ei) {
        this.b(ei);
        ei.a(this.b);
        ei.a(this.a ? this.h : this.b());
        for (int i = 0; i < this.b; ++i) {
            ei.a(this.a[i], 0, this.a.getHeight() * i + this.a.getAscent());
        }
    }
    
    public final void b() {
        super.b();
        this.a = this.a(this.b);
    }
    
    public final int a() {
        return this.a;
    }
    
    public final int b() {
        return this.b * this.a.getHeight();
    }
    
    private boolean b(final int n, final int n2) {
        return this.a != null && n >= 0 && n2 >= 0 && n < this.c() && n2 < this.d();
    }
    
    public final boolean a(final Event event, final int n, final int n2) {
        if (this.b(n, n2)) {
            this.a = true;
            this.h();
        }
        return true;
    }
    
    public final boolean c(final Event event, final int n, final int n2) {
        if (this.a && this.b(n, n2)) {
            this.a(new Event(this, 1001, null));
        }
        this.a = false;
        this.h();
        return true;
    }
}
