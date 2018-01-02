// 
// Decompiled by Procyon v0.5.30
// 

public class gq
{
    private double[] e;
    private int Aa;
    
    public gq(final int n) {
        this.Aa = -1;
        if (n <= 0) {
            throw new IllegalArgumentException("initialCapacity must be greater than 0");
        }
        this.e = new double[n];
    }
    
    public gq() {
        this(100);
    }
    
    public boolean a(final double n) {
        if (this.Aa >= 0 && n <= this.e[this.Aa]) {
            return false;
        }
        ++this.Aa;
        if (this.Aa > this.e.length - 1) {
            this.i();
        }
        this.e[this.Aa] = n;
        return true;
    }
    
    private void i() {
        final double[] e = new double[Math.max((int)(this.e.length * 1.1), 100)];
        System.arraycopy(this.e, 0, e, 0, this.e.length);
        this.e = e;
    }
    
    public double[] _() {
        if (this.Aa < 0) {
            return null;
        }
        final double[] array = new double[this.Aa + 1];
        System.arraycopy(this.e, 0, array, 0, this.Aa + 1);
        return array;
    }
}
