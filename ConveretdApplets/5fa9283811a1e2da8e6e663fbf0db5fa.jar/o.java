import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class o
{
    public Graphics p;
    public int p;
    public int d;
    public int a;
    public int n;
    public String p;
    
    public final void p(final double n, final double n2) {
        this.p = (int)n;
        this.d = (int)n2;
        this.a = this.p;
    }
    
    public final void d() {
        this.a = this.p;
        this.n += 15;
    }
    
    public final void p(final String s) {
        this.p.drawString(s, this.a, this.n);
        if (this.a < this.d) {
            this.a += 7 * s.length();
        }
        else {
            this.d();
        }
        this.p = "";
    }
    
    public final void d(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '¬') {
                this.p(this.p);
                this.d();
                this.p = "";
            }
            else {
                this.p += s.charAt(i);
            }
        }
        this.p(this.p);
    }
    
    public final void a(final String s) {
        this.d(s);
        this.d();
    }
    
    public final void p() {
        this.p.setColor(Color.black);
    }
    
    public o(final Graphics p3, final double n, final double n2) {
        this.a = this.p;
        this.n = 20;
        this.p = new String();
        this.p = p3;
        this.p(n, n2);
    }
}
