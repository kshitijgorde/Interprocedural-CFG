// 
// Decompiled by Procyon v0.5.30
// 

public class CMembraneSObject extends CPlateCSObject
{
    private static final double[][] BETA;
    
    public CMembraneSObject(final double n, final double n2, final double n3, final int n4) {
        super(n, n2, n3, n4);
    }
    
    public CMembraneSObject(final double n, final double n2, final double n3, final int n4, final double n5) {
        super(n, n2, n3, n4, n5);
    }
    
    public CMembraneSObject(final double n, final double n2, final double n3, final int n4, final double n5, final double n6) {
        super(n, n2, n3, n4, n5, n6);
    }
    
    public double freq(final int n) {
        final int n2 = (n - 1) / this.maxFreqSqrt();
        final int n3 = 1 + (n - 1) % this.maxFreqSqrt();
        return super.f0 * ((n2 > 2 || n3 > 3) ? (n3 + n2 / 2.0 - 0.75) : CMembraneSObject.BETA[n2][n3]) / CMembraneSObject.BETA[0][1];
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
        return (int)(0.6666666666666666 * (super.samplingRate / 2.0 * CMembraneSObject.BETA[0][1] / super.f0 + 0.75));
    }
    
    public int maxFreq() {
        final int maxFreqSqrt = this.maxFreqSqrt();
        return maxFreqSqrt * maxFreqSqrt;
    }
    
    private double beta(final int n, final int n2) {
        if (n > 2 || n2 > 3) {
            return n2 + n / 2.0 - 0.75;
        }
        return CMembraneSObject.BETA[n][n2];
    }
    
    public double psi(final int n, final double n2) {
        final int n3 = (n - 1) / this.maxFreqSqrt();
        final int n4 = 1 + (n - 1) % this.maxFreqSqrt();
        return Bessel.bessj(n3, 3.141592653589793 * ((n3 > 2 || n4 > 3) ? (n4 + n3 / 2.0 - 0.75) : CMembraneSObject.BETA[n3][n4]) * n2);
    }
    
    static {
        BETA = new double[][] { { 0.0, 0.7655000000000001, 1.7571, 2.7546 }, { 0.0, 1.2197, 2.233, 3.2383 }, { 0.0, 1.6347, 2.6793, 3.6987 } };
    }
}
