// 
// Decompiled by Procyon v0.5.30
// 

public class for
{
    private int Spa;
    private int Tpa;
    private int Upa;
    private int Vpa;
    
    public for(final int n, final int n2, final int n3) {
        this._(n, n2, n3, 0);
    }
    
    public for(final int n, final int n2, final int n3, final int n4) {
        this._(n, n2, n3, n4);
    }
    
    public for(final double n) {
        this._(n);
    }
    
    public for() {
        this.set(0, 0, 0);
    }
    
    public int M() {
        return this.Spa;
    }
    
    public int O() {
        return this.Tpa;
    }
    
    public int P() {
        return this.Upa;
    }
    
    public int N() {
        return this.Vpa;
    }
    
    public boolean a(final int n, final int n2, final int n3) {
        return this.b(n, n2, n3, 0);
    }
    
    public boolean b(final int n, final int n2, final int n3, final int n4) {
        return n >= 0 && n <= 23 && n2 >= 0 && n2 <= 59 && n3 >= 0 && n3 <= 59 && n4 >= 0 && n4 <= 999;
    }
    
    public void z(final int n) {
        final int n2 = (int)this.l() + n * 1000;
        if (n2 >= 86400000) {
            this._(23, 59, 59, 999);
        }
        else {
            this._(n2);
        }
    }
    
    public void A(final int n) {
        final int n2 = (int)this.l() + n * 60 * 1000;
        if (n2 >= 86400000) {
            this._(23, 59, 59, 999);
        }
        else {
            this._(n2);
        }
    }
    
    public void B(final int n) {
        final int n2 = (int)this.l() + n * 60 * 60 * 1000;
        if (n2 >= 86400000) {
            this._(23, 59, 59, 999);
        }
        else {
            this._(n2);
        }
    }
    
    public void set(final int n, final int n2, final int n3) {
        this._(n, n2, n3, 0);
    }
    
    public void _(final int spa, final int tpa, final int upa, final int vpa) {
        if (this.b(spa, tpa, upa, vpa)) {
            this.Spa = spa;
            this.Tpa = tpa;
            this.Upa = upa;
            this.Vpa = vpa;
        }
        else {
            this.Spa = 0;
            this.Tpa = 0;
            this.Upa = 0;
            this.Vpa = 0;
        }
    }
    
    public double l() {
        return a(this.Spa, this.Tpa, this.Upa, this.Vpa);
    }
    
    public static double b(final int n, final int n2, final int n3) {
        return a(n, n2, n3, 0);
    }
    
    public static double a(final int n, final int n2, final int n3, final int n4) {
        return n * 60 * 60 * 1000 + n2 * 60 * 1000 + n3 * 1000 + n4;
    }
    
    public int b(final double n, final double n2) {
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
        this._(n3, n4, n5, n6);
    }
    
    public String toString() {
        return this.a(this.Spa, this.Tpa, this.Upa, this.Vpa);
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
    
    public String b(final double n) {
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
    
    public void J() {
        this.Vpa = 0;
        if (this.Tpa != 0 || this.Upa != 0) {
            if (this.Spa < 23) {
                ++this.Spa;
                this.Tpa = 0;
                this.Upa = 0;
                this.Vpa = 0;
            }
            else {
                this.Tpa = 59;
                this.Upa = 59;
                this.Vpa = 999;
            }
        }
    }
    
    public void C(final int n) {
        this.K();
        while (this.Tpa % n != 0) {
            this.A(1);
            if (this.Spa == 23 && this.Tpa == 59 && this.Upa == 59 && this.Vpa == 999) {
                break;
            }
        }
    }
    
    public void L() {
        this.K();
        while (this.Tpa % 5 != 0) {
            this.A(1);
            if (this.Spa == 23 && this.Tpa == 59 && this.Upa == 59 && this.Vpa == 999) {
                break;
            }
        }
    }
    
    public void M() {
        this.Vpa = 0;
    }
    
    public void N() {
        this.M();
        while (this.Upa % 5 != 0) {
            this.z(1);
            if (this.Spa == 23 && this.Tpa == 59 && this.Upa == 59 && this.Vpa == 999) {
                break;
            }
        }
    }
    
    public void K() {
        this.Vpa = 0;
        if (this.Upa != 0) {
            if (this.Tpa < 59) {
                ++this.Tpa;
                this.Upa = 0;
                this.Vpa = 0;
            }
            else if (this.Spa < 23) {
                ++this.Spa;
                this.Tpa = 0;
                this.Upa = 0;
                this.Vpa = 0;
            }
            else {
                this.Spa = 23;
                this.Tpa = 59;
                this.Upa = 59;
                this.Vpa = 999;
            }
        }
    }
    
    public int Q() {
        return this.Upa + this.Tpa * 60 + this.Spa * 60 * 60;
    }
}
