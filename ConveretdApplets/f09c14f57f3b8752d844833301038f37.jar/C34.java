import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class C34 extends C28 implements C41
{
    String j;
    Font k;
    int l;
    int n;
    int o;
    int p;
    int q;
    public static String r;
    int s;
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        final int n8 = (int)((this.n - n) * n2 + n3);
        final int n9 = (int)(n7 - ((this.o - n4) * n5 + n6));
        final int abs = Math.abs((int)(n7 - ((this.l - n4) * n5 + n6)) - n9);
        if (abs > 1) {
            if (super.Q != null) {
                c20.SetTextColor(super.Q.getBlue() * 65536 + super.Q.getGreen() * 256 + super.Q.getRed());
            }
            String s = "Times New Roman";
            if (this.k != null) {
                if (this.k.getFamily() == "TimesRoman") {
                    s = "Times New Roman";
                }
                if (this.k.getFamily() == "Helvetica") {
                    s = "Arial Narrow";
                }
                if (this.k.getFamily() == "Courier") {
                    s = "Courier New";
                }
            }
            c20.LogFont(abs, s);
            c20.CreateFontIndirect();
            c20.SelectFontObject();
            c20.SetTextAlign(24);
            c20.TextOut(n8, n9, this.j, this.j.length());
            c20.DeleteFontObject();
        }
    }
    
    public Object clone() {
        return new C34(this.q, this.s, this.n, this.o, this.j);
    }
    
    public C34(final int n, final int n2, final int n3, final int n4, final String j) {
        this.j = j;
        this.n = n3;
        this.o = n4;
        this.l = n4 + n2;
        this.s = n2;
        super.P = n3;
        super.B = n4;
        this.p = (int)(j.length() * n2 * 0.7);
        super.J = n2;
    }
    
    static {
        C34.r = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
    
    public void f(final int n, final int n2) {
        super.f(n, n2);
        this.n += n;
        this.o += n2;
        this.l += n2;
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        if (super.Q != null) {
            c15.B(super.Q);
        }
        final int n8 = (int)((this.n - n) * n2 + n3);
        final int n9 = (int)(n7 - ((this.o - n4) * n5 + n6));
        final int abs = Math.abs((int)(n7 - ((this.l - n4) * n5 + n6)) - n9);
        if (this.k != null) {
            c15.A(this.k);
        }
        if (abs > 1) {
            c15.z(this.j, n8, n9, abs);
        }
    }
    
    public boolean h(final String s, final int n, final int n2) {
        return s.equals(this.j) && this.n == n && this.o == n2;
    }
    
    public void i(final Font k) {
        this.k = k;
    }
}
