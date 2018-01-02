// 
// Decompiled by Procyon v0.5.30
// 

public class BarCCSObject extends LineSObject
{
    private static final double[] BETA;
    
    public BarCCSObject(final double n, final double n2, final double n3, final int n4) {
        super(n, n2, n3, n4);
    }
    
    public BarCCSObject(final double n, final double n2, final double n3, final int n4, final double n5) {
        super(n, n2, n3, n4, n5);
    }
    
    public BarCCSObject(final double n, final double n2, final double n3, final int n4, final double n5, final double n6) {
        super(n, n2, n3, n4, n5, n6);
    }
    
    public double freq(final int n) {
        return super.f0 * ((n < 3) ? BarCCSObject.BETA[n] : (n + 0.5)) * ((n < 3) ? BarCCSObject.BETA[n] : (n + 0.5)) / (BarCCSObject.BETA[1] * BarCCSObject.BETA[1]);
    }
    
    public int maxFreq() {
        return (int)(Math.sqrt(super.samplingRate / (2.0 * super.f0)) * BarCCSObject.BETA[1] - 0.5);
    }
    
    private double beta(final int n) {
        if (n < 3) {
            return BarCCSObject.BETA[n];
        }
        return n + 0.5;
    }
    
    private static final double sinh(final double n) {
        return (Math.exp(n) - Math.exp(-n)) / 2.0;
    }
    
    private static final double cosh(final double n) {
        return (Math.exp(n) + Math.exp(-n)) / 2.0;
    }
    
    public double psi(final int n, final double n2) {
        final double n3 = 6.283185307179586 * BarCCSObject.BETA[1] * 0.5 * Math.sqrt(this.freq(n) / super.f0);
        final double n4 = (Math.sin(n3) + (Math.exp(n3) - Math.exp(-n3)) / 2.0) / (Math.cos(n3) - (Math.exp(n3) + Math.exp(-n3)) / 2.0);
        final double n5 = n3 * n2;
        final double n6 = (Math.exp(n5) + Math.exp(-n5)) / 2.0 - Math.cos(n3 * n2);
        final double n7 = n4;
        final double n8 = n3 * n2;
        return n6 + n7 * ((Math.exp(n8) - Math.exp(-n8)) / 2.0 - Math.sin(n3 * n2));
    }
    
    static {
        BETA = new double[] { 0.0, 1.5056, 2.4997000000000003 };
    }
}
