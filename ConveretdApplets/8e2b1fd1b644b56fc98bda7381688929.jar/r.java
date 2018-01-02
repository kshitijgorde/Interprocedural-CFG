// 
// Decompiled by Procyon v0.5.30
// 

public class r
{
    private double[] z;
    private int S;
    
    public r(final int n) {
        this.S = -1;
        if (n <= 0) {
            throw new IllegalArgumentException("initialCapacity must be greater than 0");
        }
        this.z = new double[n];
    }
    
    public r() {
        this(100);
    }
    
    public boolean a(final double n) {
        if (this.S >= 0 && n <= this.z[this.S]) {
            return false;
        }
        ++this.S;
        if (this.S > this.z.length - 1) {
            this.W();
        }
        this.z[this.S] = n;
        return true;
    }
    
    private void W() {
        final double[] z = new double[Math.max((int)(this.z.length * 1.1), 100)];
        System.arraycopy(this.z, 0, z, 0, this.z.length);
        this.z = z;
    }
    
    public double[] Y() {
        if (this.S < 0) {
            return null;
        }
        final double[] array = new double[this.S + 1];
        System.arraycopy(this.z, 0, array, 0, this.S + 1);
        return array;
    }
}
