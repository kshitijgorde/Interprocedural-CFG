import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

public class CPlateCSObject extends LineSObject
{
    private static final double[][] BETA;
    
    public CPlateCSObject(final double n, final double n2, final double n3, final int n4) {
        super(n, n2, n3, n4);
    }
    
    public CPlateCSObject(final double n, final double n2, final double n3, final int n4, final double n5) {
        super(n, n2, n3, n4, n5);
    }
    
    public CPlateCSObject(final double n, final double n2, final double n3, final int n4, final double n5, final double n6) {
        super(n, n2, n3, n4, n5, n6);
    }
    
    public int soundMapOf(final double n) {
        int n2 = (int)(n * super.np);
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 >= super.np) {
            n2 = super.np - 1;
        }
        return n2;
    }
    
    public double centerOfSound(final int n) {
        return (n + 0.5) / super.np;
    }
    
    public double freq(final int n) {
        final int n2 = (n - 1) / this.maxFreqSqrt();
        final int n3 = 1 + (n - 1) % this.maxFreqSqrt();
        final double n4 = ((n2 > 2 || n3 > 3) ? (n3 + n2 / 2.0) : CPlateCSObject.BETA[n2][n3]) / CPlateCSObject.BETA[0][1];
        return n4 * n4 * super.f0;
    }
    
    private int mnToNf(final int n, final int n2) {
        return this.maxFreqSqrt() * n + (n2 - 1);
    }
    
    private int nfToM(final int n) {
        return (n - 1) / this.maxFreqSqrt();
    }
    
    private int nfToN(final int n) {
        return 1 + (n - 1) % this.maxFreqSqrt();
    }
    
    private int maxFreqSqrt() {
        return (int)(0.6666666666666666 * Math.sqrt(super.samplingRate / 2.0 / super.f0) + 0.3333333333333333);
    }
    
    public int maxFreq() {
        final int maxFreqSqrt = this.maxFreqSqrt();
        return maxFreqSqrt * maxFreqSqrt;
    }
    
    private double beta(final int n, final int n2) {
        if (n > 2 || n2 > 3) {
            return n2 + n / 2.0;
        }
        return CPlateCSObject.BETA[n][n2];
    }
    
    public double psi(final int n, final double n2) {
        final int n3 = (n - 1) / this.maxFreqSqrt();
        final int n4 = 1 + (n - 1) % this.maxFreqSqrt();
        final double n5 = 3.141592653589793 * ((n3 > 2 || n4 > 3) ? (n4 + n3 / 2.0) : CPlateCSObject.BETA[n3][n4]);
        return Bessel.bessj(n3, n5 * n2) - Bessel.bessi(n3, n5 * n2) * (Bessel.bessj(n3, n5) / Bessel.bessi(n3, n5));
    }
    
    public static void main(final String[] array) throws IOException {
    }
    
    static {
        BETA = new double[][] { { 0.0, 1.0150000000000001, 2.007, 3.0 }, { 0.0, 1.468, 2.4830000000000005, 3.49 }, { 0.0, 1.879, 2.9920000000000004, 4.0 } };
    }
}
