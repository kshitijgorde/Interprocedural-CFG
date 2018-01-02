// 
// Decompiled by Procyon v0.5.30
// 

public final class case implements Cloneable
{
    private double[] hra;
    private int ira;
    private int jra;
    
    public case(final int ira, final int jra) {
        if (ira < 1) {
            throw new IllegalArgumentException("iSize must be greater than 0");
        }
        if (jra < 1) {
            throw new IllegalArgumentException("jSize must be greater than 0");
        }
        final long n = 2147483647L;
        if (ira * jra > n) {
            throw new IllegalArgumentException("Requested table is too big. iSize*jSize must be <= " + n);
        }
        this.ira = ira;
        this.jra = jra;
        this.hra = new double[ira * jra];
    }
    
    public final double getValue(final int n, final int n2) {
        if (n < 0 || n >= this.ira) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (n2 < 0 || n2 >= this.jra) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.hra[n * this.jra + n2];
    }
    
    public final void _(final int n, final int n2, final double n3) {
        if (n < 0 || n >= this.ira) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (n2 < 0 || n2 >= this.jra) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.hra[n * this.jra + n2] = n3;
    }
    
    public final int U() {
        return this.ira;
    }
    
    public final int V() {
        return this.jra;
    }
    
    public Object clone() {
        case case1 = null;
        try {
            case1 = (case)super.clone();
        }
        catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        case1.hra = new double[this.ira * this.jra];
        System.arraycopy(this.hra, 0, case1.hra, 0, this.hra.length);
        return case1;
    }
}
