// 
// Decompiled by Procyon v0.5.30
// 

public class ap
{
    private int nHb;
    private int Ua;
    private int oHb;
    private int pHb;
    
    public ap(final int n, final int n2, final int n3) {
        this.a(n, n2, n3, 0);
    }
    
    public ap(final int n, final int n2, final int n3, final int n4) {
        this.a(n, n2, n3, n4);
    }
    
    public ap(final double n) {
        this._(n);
    }
    
    public ap() {
        this.set(0, 0, 0);
    }
    
    public int M() {
        return this.nHb;
    }
    
    public int O() {
        return this.Ua;
    }
    
    public int P() {
        return this.oHb;
    }
    
    public int N() {
        return this.pHb;
    }
    
    public boolean b(final int n, final int n2, final int n3) {
        return this.b(n, n2, n3, 0);
    }
    
    public boolean b(final int n, final int n2, final int n3, final int n4) {
        return n >= 0 && n <= 23 && n2 >= 0 && n2 <= 59 && n3 >= 0 && n3 <= 59 && n4 >= 0 && n4 <= 999;
    }
    
    public void j(final int n) {
        final int n2 = (int)this.m() + n * 1000;
        if (n2 >= 86400000) {
            this.a(23, 59, 59, 999);
        }
        else {
            this._(n2);
        }
    }
    
    public void i(final int n) {
        final int n2 = (int)this.m() + n * 60 * 1000;
        if (n2 >= 86400000) {
            this.a(23, 59, 59, 999);
        }
        else {
            this._(n2);
        }
    }
    
    public void h(final int n) {
        final int n2 = (int)this.m() + n * 60 * 60 * 1000;
        if (n2 >= 86400000) {
            this.a(23, 59, 59, 999);
        }
        else {
            this._(n2);
        }
    }
    
    public void set(final int n, final int n2, final int n3) {
        this.a(n, n2, n3, 0);
    }
    
    public void a(final int nHb, final int ua, final int oHb, final int pHb) {
        if (this.b(nHb, ua, oHb, pHb)) {
            this.nHb = nHb;
            this.Ua = ua;
            this.oHb = oHb;
            this.pHb = pHb;
        }
        else {
            this.nHb = 0;
            this.Ua = 0;
            this.oHb = 0;
            this.pHb = 0;
        }
    }
    
    public double m() {
        return _(this.nHb, this.Ua, this.oHb, this.pHb);
    }
    
    public static double a(final int n, final int n2, final int n3) {
        return _(n, n2, n3, 0);
    }
    
    public static double _(final int n, final int n2, final int n3, final int n4) {
        return n * 60 * 60 * 1000 + n2 * 60 * 1000 + n3 * 1000 + n4;
    }
    
    public int a(final double n, final double n2) {
        return Math.round(Math.round(n) - Math.round(n2));
    }
    
    public void _(final double n) {
        final int n2 = (int)Math.round(n);
        int n3 = n2 / 3600000;
        int n4 = (n2 - n3 * 60 * 60 * 1000) / 60000;
        int n5 = (n2 - n3 * 60 * 60 * 1000 - n4 * 60 * 1000) / 1000;
        int n6 = (n2 - n3 * 60 * 60 * 1000 - n4 * 60 * 1000 - n5 * 1000) % 1000;
        if (!this.b(n3, n4, n5, n6)) {
            n4 = (n3 = (n5 = (n6 = 0)));
        }
        this.a(n3, n4, n5, n6);
    }
    
    public String toString() {
        return this.a(this.nHb, this.Ua, this.oHb, this.pHb);
    }
    
    public String a(final int n, final int n2, final int n3, final int n4) {
        final StringBuffer sb = new StringBuffer(12);
        if (this.b(n, n2, n3, n4)) {
            if (n < 10) {
                sb.append("0").append(n);
            }
            else {
                sb.append(n);
            }
            sb.append(":");
            if (n2 < 10) {
                sb.append("0").append(n2);
            }
            else {
                sb.append(n2);
            }
            sb.append(":");
            if (n3 < 10) {
                sb.append("0").append(n3);
            }
            else {
                sb.append(n3);
            }
        }
        else {
            sb.append("00:00:00");
        }
        return sb.toString();
    }
    
    public String f(final double n) {
        final int n2 = (int)Math.round(n);
        int n3 = n2 / 3600000;
        int n4 = (n2 - n3 * 60 * 60 * 1000) / 60000;
        int n5 = (n2 - n3 * 60 * 60 * 1000 - n4 * 60 * 1000) / 1000;
        int n6 = (n2 - n3 * 60 * 60 * 1000 - n4 * 60 * 1000 - n5 * 1000) % 1000;
        if (!this.b(n3, n4, n5, n6)) {
            n4 = (n3 = (n5 = (n6 = 0)));
        }
        return this.a(n3, n4, n5, n6);
    }
    
    public void l() {
        this.pHb = 0;
        if (this.Ua != 0 || this.oHb != 0) {
            if (this.nHb < 23) {
                ++this.nHb;
                this.Ua = 0;
                this.oHb = 0;
                this.pHb = 0;
            }
            else {
                this.Ua = 59;
                this.oHb = 59;
                this.pHb = 999;
            }
        }
    }
    
    public void _a(final int n) {
        this.n();
        while (this.Ua % n != 0) {
            this.i(1);
            if (this.nHb == 23 && this.Ua == 59 && this.oHb == 59 && this.pHb == 999) {
                break;
            }
        }
    }
    
    public void m() {
        this.n();
        while (this.Ua % 5 != 0) {
            this.i(1);
            if (this.nHb == 23 && this.Ua == 59 && this.oHb == 59 && this.pHb == 999) {
                break;
            }
        }
    }
    
    public void c() {
        this.pHb = 0;
    }
    
    public void d() {
        this.c();
        while (this.oHb % 5 != 0) {
            this.j(1);
            if (this.nHb == 23 && this.Ua == 59 && this.oHb == 59 && this.pHb == 999) {
                break;
            }
        }
    }
    
    public void n() {
        this.pHb = 0;
        if (this.oHb != 0) {
            if (this.Ua < 59) {
                ++this.Ua;
                this.oHb = 0;
                this.pHb = 0;
            }
            else if (this.nHb < 23) {
                ++this.nHb;
                this.Ua = 0;
                this.oHb = 0;
                this.pHb = 0;
            }
            else {
                this.nHb = 23;
                this.Ua = 59;
                this.oHb = 59;
                this.pHb = 999;
            }
        }
    }
    
    public int Q() {
        return this.oHb + this.Ua * 60 + this.nHb * 60 * 60;
    }
}
