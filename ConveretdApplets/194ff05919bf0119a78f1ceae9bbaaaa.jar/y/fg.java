// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.FontMetrics;

public abstract class fg extends av
{
    private by a;
    cg a;
    public int a;
    int b;
    int t;
    public int u;
    int v;
    
    public final int d() {
        return this.u;
    }
    
    public static String a(final StringBuffer sb, final FontMetrics fontMetrics, final int n) {
        if (sb == null) {
            throw new IllegalArgumentException("null list");
        }
        if (fontMetrics == null) {
            throw new IllegalArgumentException("null fm");
        }
        String s;
        for (s = new String(sb); fontMetrics.stringWidth(s) > n - 2 && sb.length() > 0; s = new String((Object)sb + "..")) {
            sb.setLength(sb.length() - 1);
        }
        return s;
    }
    
    public fg() {
        this(true, (byte)0);
    }
    
    public fg(final boolean b, final byte b2) {
        this(b);
    }
    
    public abstract void a(final ei p0, final int p1, final int p2, final int p3, final int p4);
    
    private fg(final boolean b) {
        super((byte)0);
        this.a = 0;
        this.b = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.a(this.a = new cg(this), 1, 1, 0, 1, true);
        this.a = new by();
        if (b) {
            this.a(this.a, 1, 1, 1, 1, false);
        }
    }
    
    final void l() {
        final by a = this.a;
        final int a2 = this.a;
        final int f = this.a.f;
        final int b = this.b;
        final int b2 = f;
        final int a3 = a2;
        final by by = a;
        a.a = a3;
        by.b = b2;
        by.t = b;
        by.h();
    }
    
    public void b(final int n, final int n2) {
    }
    
    public void a(final int n) {
    }
    
    public final void c(final int n, final int n2) {
        this.b += n2;
        if (n < this.a + this.u) {
            this.h();
        }
        this.l();
    }
    
    final boolean a() {
        if (this.b < this.a + this.u) {
            this.a = Math.max(0, this.b - this.u);
            return true;
        }
        return false;
    }
    
    public final void d(final int n, final int n2) {
        this.b -= n2;
        if (this.a() || n < this.a + this.u) {
            this.h();
        }
        this.l();
    }
    
    public final void b(final int u) {
        this.a.u = u;
    }
    
    public final void m() {
        this.d(0, this.b);
    }
    
    public final boolean a(final Event event) {
        if (event.target == this.a && event.id == 605) {
            this.a(this.a = (int)event.arg);
            this.h();
        }
        else {
            if (event.target != null || event.id != 605) {
                return false;
            }
            this.t = (int)event.arg;
            this.h();
        }
        return true;
    }
    
    public abstract int h();
    
    public abstract int g();
    
    public abstract void a(final Event p0, final int p1, final int p2);
    
    public abstract void b(final Event p0, final int p1, final int p2);
    
    public final void n() {
        this.a = Math.max(0, this.b - this.u);
        this.l();
        this.h();
    }
}
