// 
// Decompiled by Procyon v0.5.30
// 

public class bw
{
    public String a;
    public String b;
    public double c;
    public boolean d;
    public boolean e;
    public by f;
    public bw[] g;
    
    public bw(final String a, final String s, final String s2, final String b, final boolean e) {
        this.a = a;
        this.a(s);
        this.b(s2);
        this.b = b;
        this.e = e;
    }
    
    public final void a(final String s) {
        if (s != null && s.indexOf("DEFAULT") == -1) {
            this.c = new Double(s);
        }
    }
    
    public final void b(final String s) {
        if (s != null) {
            if (s.equals("1") || s.equals("true")) {
                this.d = true;
            }
            else {
                this.d = false;
            }
        }
    }
    
    public String toString() {
        return "Name: " + this.a + "; Pattern: " + this.b + "; MaxValue: " + this.c + "; IncludeMaxValue: " + this.d + " isNumber: " + this.e + " subdefs: " + ((this.g != null) ? new Integer(this.g.length).toString() : "0");
    }
    
    public final void a(final bw bw) {
        if (this.g == null) {
            (this.g = new bw[1])[0] = bw;
        }
        else {
            final bw[] g = new bw[this.g.length + 1];
            for (int i = 0; i < this.g.length; ++i) {
                g[i] = this.g[i];
            }
            g[g.length - 1] = bw;
            this.g = g;
        }
    }
    
    public final boolean a() {
        return this.g == null;
    }
    
    public final by a(final double n) {
        if (this.g == null) {
            return this.f;
        }
        for (int i = 0; i < this.g.length; ++i) {
            for (int j = 1; j < this.g.length; ++j) {
                if (this.g[i].c < this.g[j].c) {
                    final bw bw = this.g[i];
                    this.g[i] = this.g[j];
                    this.g[j] = bw;
                }
            }
        }
        int n2 = -1;
        for (int k = 0; k < this.g.length; ++k) {
            if (n <= this.g[k].c) {
                n2 = k;
            }
        }
        if (n2 > -1) {
            if (n < this.g[n2].c) {
                return this.g[n2].f;
            }
            if (n == this.g[n2].c) {
                if (this.g[n2].d) {
                    return this.g[n2].f;
                }
                if (n2 > 0) {
                    return this.g[n2 - 1].f;
                }
            }
        }
        return this.f;
    }
}
