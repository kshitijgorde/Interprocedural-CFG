// 
// Decompiled by Procyon v0.5.30
// 

public class native
{
    public static final long Mpa = 10000000000000L;
    public static final long Npa = 100000000000L;
    public static final long Opa = 1000000000L;
    public static final long Ppa = 10000000L;
    public static final long Qpa = 100000L;
    public static final long Rpa = 1000L;
    long zpa;
    long ypa;
    long xpa;
    long Spa;
    long Tpa;
    long Upa;
    long Vpa;
    
    public void set(final int n, final int n2, final int n3) {
        this.zpa = n;
        this.ypa = n2;
        this.xpa = n3;
        this.Spa = 0L;
        this.Tpa = 0L;
        this.Upa = 0L;
        this.Vpa = 0L;
    }
    
    public void b(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this.zpa = n;
        this.ypa = n2;
        this.xpa = n3;
        this.Spa = n4;
        this.Tpa = n5;
        this.Upa = n6;
        this.Vpa = n7;
    }
    
    public void b(long vpa) {
        if (vpa <= 0L) {
            vpa = 0L;
        }
        this.zpa = vpa / 10000000000000L;
        vpa -= this.zpa * 10000000000000L;
        this.ypa = vpa / 100000000000L;
        vpa -= this.ypa * 100000000000L;
        this.xpa = vpa / 1000000000L;
        vpa -= this.xpa * 1000000000L;
        this.Spa = vpa / 10000000L;
        vpa -= this.Spa * 10000000L;
        this.Tpa = vpa / 100000L;
        vpa -= this.Tpa * 100000L;
        this.Upa = vpa / 1000L;
        vpa -= this.Upa * 1000L;
        this.Vpa = vpa;
    }
    
    public long _() {
        return this.zpa * 10000000000000L + this.ypa * 100000000000L + this.xpa * 1000000000L + this.Spa * 10000000L + this.Tpa * 100000L + this.Upa * 1000L + this.Vpa;
    }
    
    public int getDay() {
        return (int)this.xpa;
    }
    
    public int M() {
        return (int)this.Spa;
    }
    
    public int N() {
        return (int)this.Vpa;
    }
    
    public int O() {
        return (int)this.Tpa;
    }
    
    public int getMonth() {
        return (int)this.ypa;
    }
    
    public int P() {
        return (int)this.Upa;
    }
    
    public int getYear() {
        return (int)this.zpa;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(24);
        if (this.zpa > 0L || this.ypa > 0L || this.xpa > 0L) {
            sb.append(this.zpa);
            sb.append('-');
            if (this.ypa < 10L) {
                sb.append('0');
            }
            sb.append(this.ypa);
            sb.append('-');
            if (this.xpa < 10L) {
                sb.append('0');
            }
            sb.append(this.xpa);
        }
        if (this.Spa > 0L || this.Tpa > 0L || this.Upa > 0L || this.Vpa > 0L) {
            if (this.zpa > 0L || this.ypa > 0L || this.xpa > 0L) {
                sb.append('_');
            }
            if (this.Spa < 10L) {
                sb.append('0');
            }
            sb.append(this.Spa);
            if (this.Tpa < 10L) {
                sb.append('0');
            }
            sb.append(this.Tpa);
            if (this.Upa < 10L) {
                sb.append('0');
            }
            sb.append(this.Upa);
            sb.append('.');
            if (this.Vpa < 100L) {
                sb.append('0');
            }
            if (this.Vpa < 10L) {
                sb.append('0');
            }
            sb.append(this.Vpa);
        }
        return sb.toString();
    }
}
