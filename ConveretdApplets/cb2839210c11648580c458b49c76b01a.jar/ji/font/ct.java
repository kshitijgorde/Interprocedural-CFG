// 
// Decompiled by Procyon v0.5.30
// 

package ji.font;

import java.awt.Font;

public class ct
{
    public int a;
    public int b;
    public String c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public Font i;
    public boolean j;
    
    public void a() {
        this.c = null;
        this.i = null;
    }
    
    public ct() {
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.d = false;
        this.e = false;
        this.f = true;
        this.g = false;
        this.h = false;
        this.i = null;
        this.j = false;
    }
    
    public ct(final String c, final int n, final boolean d, final boolean e, final boolean f) {
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.d = false;
        this.e = false;
        this.f = true;
        this.g = false;
        this.h = false;
        this.i = null;
        this.j = false;
        this.c = c;
        this.b = n;
        this.a = n;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public ct(final String c, final int n, final boolean d, final boolean e, final boolean f, final Font i) {
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.d = false;
        this.e = false;
        this.f = true;
        this.g = false;
        this.h = false;
        this.i = null;
        this.j = false;
        this.c = c;
        this.b = n;
        this.a = n;
        this.d = d;
        this.e = e;
        this.f = f;
        this.i = i;
    }
    
    public final void a(final boolean g, final boolean h) {
        this.g = g;
        this.h = h;
    }
    
    public final boolean b() {
        return this.i != null;
    }
    
    public final String toString() {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.c))).append(":").append(this.a).append(":").append(this.b).append(":").append(this.d).append(":").append(this.e).append(":").append(this.f).append(", font=<").append(this.i).append(">")));
    }
    
    public final ct c() {
        final ct ct = new ct();
        ct.a = this.a;
        ct.b = this.b;
        ct.c = this.c;
        ct.d = this.d;
        ct.e = this.e;
        ct.f = this.f;
        ct.i = this.i;
        ct.g = this.g;
        ct.h = this.h;
        ct.j = this.j;
        return ct;
    }
    
    public boolean a(final ct ct) {
        boolean b = false;
        if (ct != null) {
            boolean b2 = true;
            if (ct.c != null && this.c != null && !ct.c.toLowerCase().equals(this.c.toLowerCase())) {
                b2 = false;
            }
            if (ct.i != null) {
                if (this.i == null) {
                    b2 = false;
                }
                else if (!this.i.equals(ct.i)) {
                    b2 = false;
                }
            }
            if (b2 && ct.a == this.a && ct.b == this.b && ct.d == this.d && ct.e == this.e && ct.f == this.f && ct.g == this.g && ct.h == this.h && ct.b() == this.b()) {
                b = true;
            }
        }
        return b;
    }
}
