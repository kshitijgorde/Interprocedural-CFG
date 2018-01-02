// 
// Decompiled by Procyon v0.5.30
// 

public class Zh
{
    public static final long na = 10000000000000L;
    public static final long Xia = 100000000000L;
    public static final long Yia = 1000000000L;
    public static final long Zia = 10000000L;
    public static final long _ja = 100000L;
    public static final long aja = 1000L;
    long _a;
    long Z;
    long Y;
    long bja;
    long cja;
    long dja;
    long eja;
    
    public void set(final int n, final int n2, final int n3) {
        this._a = n;
        this.Z = n2;
        this.Y = n3;
        this.bja = 0L;
        this.cja = 0L;
        this.dja = 0L;
        this.eja = 0L;
    }
    
    public void b(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this._a = n;
        this.Z = n2;
        this.Y = n3;
        this.bja = n4;
        this.cja = n5;
        this.dja = n6;
        this.eja = n7;
    }
    
    public void b(long eja) {
        if (eja <= 0L) {
            eja = 0L;
        }
        this._a = eja / 10000000000000L;
        eja -= this._a * 10000000000000L;
        this.Z = eja / 100000000000L;
        eja -= this.Z * 100000000000L;
        this.Y = eja / 1000000000L;
        eja -= this.Y * 1000000000L;
        this.bja = eja / 10000000L;
        eja -= this.bja * 10000000L;
        this.cja = eja / 100000L;
        eja -= this.cja * 100000L;
        this.dja = eja / 1000L;
        eja -= this.dja * 1000L;
        this.eja = eja;
    }
    
    public long a() {
        return this._a * 10000000000000L + this.Z * 100000000000L + this.Y * 1000000000L + this.bja * 10000000L + this.cja * 100000L + this.dja * 1000L + this.eja;
    }
    
    public int getDay() {
        return (int)this.Y;
    }
    
    public int c() {
        return (int)this.bja;
    }
    
    public int d() {
        return (int)this.eja;
    }
    
    public int e() {
        return (int)this.cja;
    }
    
    public int getMonth() {
        return (int)this.Z;
    }
    
    public int f() {
        return (int)this.dja;
    }
    
    public int getYear() {
        return (int)this._a;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(24);
        if (this._a > 0L || this.Z > 0L || this.Y > 0L) {
            sb.append(this._a);
            sb.append('-');
            if (this.Z < 10L) {
                sb.append('0');
            }
            sb.append(this.Z);
            sb.append('-');
            if (this.Y < 10L) {
                sb.append('0');
            }
            sb.append(this.Y);
        }
        if (this.bja > 0L || this.cja > 0L || this.dja > 0L || this.eja > 0L) {
            if (this._a > 0L || this.Z > 0L || this.Y > 0L) {
                sb.append('_');
            }
            if (this.bja < 10L) {
                sb.append('0');
            }
            sb.append(this.bja);
            if (this.cja < 10L) {
                sb.append('0');
            }
            sb.append(this.cja);
            if (this.dja < 10L) {
                sb.append('0');
            }
            sb.append(this.dja);
            sb.append('.');
            if (this.eja < 100L) {
                sb.append('0');
            }
            if (this.eja < 10L) {
                sb.append('0');
            }
            sb.append(this.eja);
        }
        return sb.toString();
    }
}
