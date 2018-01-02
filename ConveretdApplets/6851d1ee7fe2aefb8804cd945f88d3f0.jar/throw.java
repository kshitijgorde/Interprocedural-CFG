// 
// Decompiled by Procyon v0.5.30
// 

public final class throw implements Cloneable
{
    private double[] sla;
    private int tla;
    private int ula;
    
    public throw(final int tla, final int ula) {
        if (tla < 1) {
            throw new IllegalArgumentException("iSize must be greater than 0");
        }
        if (ula < 1) {
            throw new IllegalArgumentException("jSize must be greater than 0");
        }
        final long n = 2147483647L;
        if (tla * ula > n) {
            throw new IllegalArgumentException("Requested table is too big. iSize*jSize must be <= " + n);
        }
        this.tla = tla;
        this.ula = ula;
        this.sla = new double[tla * ula];
    }
    
    public final double getValue(final int n, final int n2) {
        if (n < 0 || n >= this.tla) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (n2 < 0 || n2 >= this.ula) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.sla[n * this.ula + n2];
    }
    
    public final void _(final int n, final int n2, final double n3) {
        if (n < 0 || n >= this.tla) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (n2 < 0 || n2 >= this.ula) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.sla[n * this.ula + n2] = n3;
    }
    
    public final int s() {
        return this.tla;
    }
    
    public final int t() {
        return this.ula;
    }
    
    public Object clone() {
        throw throw1 = null;
        try {
            throw1 = (throw)super.clone();
        }
        catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        throw1.sla = new double[this.tla * this.ula];
        System.arraycopy(this.sla, 0, throw1.sla, 0, this.sla.length);
        return throw1;
    }
}
