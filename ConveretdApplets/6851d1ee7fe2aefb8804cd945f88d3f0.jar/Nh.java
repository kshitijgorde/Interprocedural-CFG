// 
// Decompiled by Procyon v0.5.30
// 

public class Nh
{
    private double[] ib;
    private int aka;
    
    public Nh(final int n) {
        this.aka = -1;
        if (n <= 0) {
            throw new IllegalArgumentException("initialCapacity must be greater than 0");
        }
        this.ib = new double[n];
    }
    
    public Nh() {
        this(100);
    }
    
    public boolean b(final double n) {
        if (this.aka >= 0 && n <= this.ib[this.aka]) {
            return false;
        }
        ++this.aka;
        if (this.aka > this.ib.length - 1) {
            this._a();
        }
        this.ib[this.aka] = n;
        return true;
    }
    
    private void _a() {
        final double[] ib = new double[Math.max((int)(this.ib.length * 1.1), 100)];
        System.arraycopy(this.ib, 0, ib, 0, this.ib.length);
        this.ib = ib;
    }
    
    public double[] e() {
        if (this.aka < 0) {
            return null;
        }
        final double[] array = new double[this.aka + 1];
        System.arraycopy(this.ib, 0, array, 0, this.aka + 1);
        return array;
    }
}
