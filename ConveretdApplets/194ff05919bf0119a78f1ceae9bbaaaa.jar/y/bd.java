// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.Color;
import java.awt.FontMetrics;

final class bd extends u
{
    private c a;
    private int a;
    private int b;
    private FontMetrics a;
    
    bd(final c a, final int a2, final int b) {
        this.a = a;
        this.a = a2;
        this.b = b;
        this.b(a.c);
    }
    
    public final void a(final ei ei) {
        this.b(ei);
        ei.a(Color.black);
        ei.c(0, 0, this.a, this.b);
        ei.a(u.a);
        for (int i = 0, n = 0; i < this.a.a.size(); ++i, n += this.a.a.getHeight()) {
            ei.a(Color.black);
            if (i == this.a.b) {
                ei.a(u.a);
                ei.b(0, n, this.a, this.a.a.getHeight() + 2);
                ei.a(Color.white);
            }
            ei.a((String)this.a.a.elementAt(i), 2, i * this.a.a.getHeight() + this.a.a.getAscent() + 2);
        }
    }
    
    private void a(int b) {
        this.a.b = -1;
        if (b >= 0 && (b /= this.a.getHeight()) < this.a.a.size()) {
            this.a.b = b;
        }
    }
    
    public final boolean a(final int n) {
        this.a(n);
        this.h();
        return true;
    }
    
    public final boolean a(final Event event) {
        if (event.target == this && event.id == 1005) {
            if (this.a.a != null) {
                this.a.a();
            }
            return true;
        }
        return super.a(event);
    }
    
    public final boolean a(Event a, final int n, final int n2) {
        this.a(n2);
        if ((a = this.a.a()) != null) {
            this.a.a(a);
        }
        return true;
    }
    
    public final void b() {
        super.b();
        this.a = this.a(u.a);
    }
    
    public final int a() {
        return this.a;
    }
    
    public final int b() {
        return this.b;
    }
}
