// 
// Decompiled by Procyon v0.5.30
// 

public class j
{
    private int bja;
    private int cja;
    private int dja;
    private int eja;
    
    public j(final int n, final int n2, final int n3) {
        this.a(n, n2, n3, 0);
    }
    
    public j(final int n, final int n2, final int n3, final int n4) {
        this.a(n, n2, n3, n4);
    }
    
    public j(final double n) {
        this._(n);
    }
    
    public j() {
        this.set(0, 0, 0);
    }
    
    public int c() {
        return this.bja;
    }
    
    public int e() {
        return this.cja;
    }
    
    public int f() {
        return this.dja;
    }
    
    public int d() {
        return this.eja;
    }
    
    public boolean a(final int n, final int n2, final int n3) {
        return this._(n, n2, n3, 0);
    }
    
    public boolean _(final int n, final int n2, final int n3, final int n4) {
        return n >= 0 && n <= 23 && n2 >= 0 && n2 <= 59 && n3 >= 0 && n3 <= 59 && n4 >= 0 && n4 <= 999;
    }
    
    public void k(final int n) {
        final int n2 = (int)this._() + n * 1000;
        if (n2 >= 86400000) {
            this.a(23, 59, 59, 999);
        }
        else {
            this._(n2);
        }
    }
    
    public void l(final int n) {
        final int n2 = (int)this._() + n * 60 * 1000;
        if (n2 >= 86400000) {
            this.a(23, 59, 59, 999);
        }
        else {
            this._(n2);
        }
    }
    
    public void p(final int n) {
        final int n2 = (int)this._() + n * 60 * 60 * 1000;
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
    
    public void a(final int bja, final int cja, final int dja, final int eja) {
        if (this._(bja, cja, dja, eja)) {
            this.bja = bja;
            this.cja = cja;
            this.dja = dja;
            this.eja = eja;
        }
        else {
            this.bja = 0;
            this.cja = 0;
            this.dja = 0;
            this.eja = 0;
        }
    }
    
    public double _() {
        return a(this.bja, this.cja, this.dja, this.eja);
    }
    
    public static double a(final int n, final int n2, final int n3) {
        return a(n, n2, n3, 0);
    }
    
    public static double a(final int n, final int n2, final int n3, final int n4) {
        return n * 60 * 60 * 1000 + n2 * 60 * 1000 + n3 * 1000 + n4;
    }
    
    public int _(final double n, final double n2) {
        return Math.round(Math.round(n) - Math.round(n2));
    }
    
    public void _(final double n) {
        final int n2 = (int)Math.round(n);
        int n3 = n2 / 3600000;
        int n4 = (n2 - n3 * 60 * 60 * 1000) / 60000;
        int n5 = (n2 - n3 * 60 * 60 * 1000 - n4 * 60 * 1000) / 1000;
        int n6 = (n2 - n3 * 60 * 60 * 1000 - n4 * 60 * 1000 - n5 * 1000) % 1000;
        if (!this._(n3, n4, n5, n6)) {
            n4 = (n3 = (n5 = (n6 = 0)));
        }
        this.a(n3, n4, n5, n6);
    }
    
    public String toString() {
        return this.a(this.bja, this.cja, this.dja, this.eja);
    }
    
    public String a(final int n, final int n2, final int n3, final int n4) {
        final StringBuffer sb = new StringBuffer(12);
        if (this._(n, n2, n3, n4)) {
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
    
    public String b(final double n) {
        final int n2 = (int)Math.round(n);
        int n3 = n2 / 3600000;
        int n4 = (n2 - n3 * 60 * 60 * 1000) / 60000;
        int n5 = (n2 - n3 * 60 * 60 * 1000 - n4 * 60 * 1000) / 1000;
        int n6 = (n2 - n3 * 60 * 60 * 1000 - n4 * 60 * 1000 - n5 * 1000) % 1000;
        if (!this._(n3, n4, n5, n6)) {
            n4 = (n3 = (n5 = (n6 = 0)));
        }
        return this.a(n3, n4, n5, n6);
    }
    
    public void aa() {
        this.eja = 0;
        if (this.cja != 0 || this.dja != 0) {
            if (this.bja < 23) {
                ++this.bja;
                this.cja = 0;
                this.dja = 0;
                this.eja = 0;
            }
            else {
                this.cja = 59;
                this.dja = 59;
                this.eja = 999;
            }
        }
    }
    
    public void q(final int n) {
        this.ba();
        while (this.cja % n != 0) {
            this.l(1);
            if (this.bja == 23 && this.cja == 59 && this.dja == 59 && this.eja == 999) {
                break;
            }
        }
    }
    
    public void ca() {
        this.ba();
        while (this.cja % 5 != 0) {
            this.l(1);
            if (this.bja == 23 && this.cja == 59 && this.dja == 59 && this.eja == 999) {
                break;
            }
        }
    }
    
    public void da() {
        this.eja = 0;
    }
    
    public void ea() {
        this.da();
        while (this.dja % 5 != 0) {
            this.k(1);
            if (this.bja == 23 && this.cja == 59 && this.dja == 59 && this.eja == 999) {
                break;
            }
        }
    }
    
    public void ba() {
        this.eja = 0;
        if (this.dja != 0) {
            if (this.cja < 59) {
                ++this.cja;
                this.dja = 0;
                this.eja = 0;
            }
            else if (this.bja < 23) {
                ++this.bja;
                this.cja = 0;
                this.dja = 0;
                this.eja = 0;
            }
            else {
                this.bja = 23;
                this.cja = 59;
                this.dja = 59;
                this.eja = 999;
            }
        }
    }
    
    public int ha() {
        return this.dja + this.cja * 60 + this.bja * 60 * 60;
    }
}
