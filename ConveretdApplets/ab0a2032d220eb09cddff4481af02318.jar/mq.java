// 
// Decompiled by Procyon v0.5.30
// 

public final class mq implements Cloneable
{
    private double[] fb;
    private int gb;
    private int hb;
    
    public mq(final int gb, final int hb) {
        if (gb < 1) {
            throw new IllegalArgumentException("iSize must be greater than 0");
        }
        if (hb < 1) {
            throw new IllegalArgumentException("jSize must be greater than 0");
        }
        final long n = 2147483647L;
        if (gb * hb > n) {
            throw new IllegalArgumentException("Requested table is too big. iSize*jSize must be <= " + n);
        }
        this.gb = gb;
        this.hb = hb;
        this.fb = new double[gb * hb];
    }
    
    public final double getValue(final int n, final int n2) {
        if (n < 0 || n >= this.gb) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (n2 < 0 || n2 >= this.hb) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.fb[n * this.hb + n2];
    }
    
    public final void a(final int n, final int n2, final double n3) {
        if (n < 0 || n >= this.gb) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (n2 < 0 || n2 >= this.hb) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.fb[n * this.hb + n2] = n3;
    }
    
    public final int n() {
        return this.gb;
    }
    
    public final int c() {
        return this.hb;
    }
    
    public Object clone() {
        mq mq = null;
        try {
            mq = (mq)super.clone();
        }
        catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        mq.fb = new double[this.gb * this.hb];
        System.arraycopy(this.fb, 0, mq.fb, 0, this.fb.length);
        return mq;
    }
}
