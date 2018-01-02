// 
// Decompiled by Procyon v0.5.30
// 

public class cp
{
    public static final long hHb = 10000000000000L;
    public static final long iHb = 100000000000L;
    public static final long jHb = 1000000000L;
    public static final long kHb = 10000000L;
    public static final long lHb = 100000L;
    public static final long mHb = 1000L;
    long PGb;
    long OGb;
    long NGb;
    long nHb;
    long Ua;
    long oHb;
    long pHb;
    
    public void set(final int n, final int n2, final int n3) {
        this.PGb = n;
        this.OGb = n2;
        this.NGb = n3;
        this.nHb = 0L;
        this.Ua = 0L;
        this.oHb = 0L;
        this.pHb = 0L;
    }
    
    public void _(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this.PGb = n;
        this.OGb = n2;
        this.NGb = n3;
        this.nHb = n4;
        this.Ua = n5;
        this.oHb = n6;
        this.pHb = n7;
    }
    
    public void _(long pHb) {
        if (pHb <= 0L) {
            pHb = 0L;
        }
        this.PGb = pHb / 10000000000000L;
        pHb -= this.PGb * 10000000000000L;
        this.OGb = pHb / 100000000000L;
        pHb -= this.OGb * 100000000000L;
        this.NGb = pHb / 1000000000L;
        pHb -= this.NGb * 1000000000L;
        this.nHb = pHb / 10000000L;
        pHb -= this.nHb * 10000000L;
        this.Ua = pHb / 100000L;
        pHb -= this.Ua * 100000L;
        this.oHb = pHb / 1000L;
        pHb -= this.oHb * 1000L;
        this.pHb = pHb;
    }
    
    public long _() {
        return this.PGb * 10000000000000L + this.OGb * 100000000000L + this.NGb * 1000000000L + this.nHb * 10000000L + this.Ua * 100000L + this.oHb * 1000L + this.pHb;
    }
    
    public int getDay() {
        return (int)this.NGb;
    }
    
    public int M() {
        return (int)this.nHb;
    }
    
    public int N() {
        return (int)this.pHb;
    }
    
    public int O() {
        return (int)this.Ua;
    }
    
    public int getMonth() {
        return (int)this.OGb;
    }
    
    public int P() {
        return (int)this.oHb;
    }
    
    public int getYear() {
        return (int)this.PGb;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(24);
        if (this.PGb > 0L || this.OGb > 0L || this.NGb > 0L) {
            sb.append(this.PGb);
            sb.append('-');
            if (this.OGb < 10L) {
                sb.append('0');
            }
            sb.append(this.OGb);
            sb.append('-');
            if (this.NGb < 10L) {
                sb.append('0');
            }
            sb.append(this.NGb);
        }
        if (this.nHb > 0L || this.Ua > 0L || this.oHb > 0L || this.pHb > 0L) {
            if (this.PGb > 0L || this.OGb > 0L || this.NGb > 0L) {
                sb.append('_');
            }
            if (this.nHb < 10L) {
                sb.append('0');
            }
            sb.append(this.nHb);
            if (this.Ua < 10L) {
                sb.append('0');
            }
            sb.append(this.Ua);
            if (this.oHb < 10L) {
                sb.append('0');
            }
            sb.append(this.oHb);
            sb.append('.');
            if (this.pHb < 100L) {
                sb.append('0');
            }
            if (this.pHb < 10L) {
                sb.append('0');
            }
            sb.append(this.pHb);
        }
        return sb.toString();
    }
}
