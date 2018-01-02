// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import ji.awt.c;

public class k
{
    protected c a;
    
    public k(final int n) {
        this.a = null;
        this.a = new c("jiSort1", n);
    }
    
    public void a(final String s) {
        int n;
        for (n = 0; n < this.a.b() && this.a.b(n).toString().compareTo(s) <= 0; ++n) {}
        this.a.b(s, n);
    }
    
    public int a() {
        return this.a.b();
    }
    
    public String b() {
        String string = null;
        if (this.a.b() > 0) {
            string = this.a.b(0).toString();
            this.a.d(0);
        }
        return string;
    }
    
    public final void c() {
        this.a.c();
    }
}
