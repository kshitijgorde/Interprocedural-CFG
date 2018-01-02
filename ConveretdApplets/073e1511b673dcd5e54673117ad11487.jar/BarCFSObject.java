// 
// Decompiled by Procyon v0.5.30
// 

public class BarCFSObject extends BarCCSObject
{
    private static final double[] BETA;
    
    public BarCFSObject(final double n, final double n2, final double n3, final int n4) {
        super(n, n2, n3, n4);
    }
    
    public BarCFSObject(final double n, final double n2, final double n3, final int n4, final double n5) {
        super(n, n2, n3, n4, n5);
    }
    
    public BarCFSObject(final double n, final double n2, final double n3, final int n4, final double n5, final double n6) {
        super(n, n2, n3, n4, n5, n6);
    }
    
    public double freq(final int n) {
        return super.f0 * ((n < 3) ? BarCFSObject.BETA[n] : (n - 0.5)) * ((n < 3) ? BarCFSObject.BETA[n] : (n - 0.5)) / (BarCFSObject.BETA[1] * BarCFSObject.BETA[1]);
    }
    
    public int maxFreq() {
        return (int)(Math.sqrt(super.samplingRate / (2.0 * super.f0)) * BarCFSObject.BETA[1] - 0.5);
    }
    
    private double beta(final int n) {
        if (n < 3) {
            return BarCFSObject.BETA[n];
        }
        return n - 0.5;
    }
    
    public double psi(final int n, final double n2) {
        double n3 = 1.0;
        final double n4 = 6.283185307179586 * ((n < 3) ? BarCFSObject.BETA[n] : (n - 0.5)) / 2.0;
        try {
            final double n5 = (Math.sin(n4) - (Math.exp(n4) - Math.exp(-n4)) / 2.0) / (Math.cos(n4) + (Math.exp(n4) + Math.exp(-n4)) / 2.0);
            final double n6 = n4 * n2;
            final double n7 = (Math.exp(n6) + Math.exp(-n6)) / 2.0 - Math.cos(n4 * n2);
            final double n8 = n5;
            final double n9 = n4 * n2;
            n3 = n7 + n8 * ((Math.exp(n9) - Math.exp(-n9)) / 2.0 - Math.sin(n4 * n2));
        }
        catch (Exception ex) {
            System.out.println("BARCF threw exception " + ex);
        }
        return n3;
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
    
    static {
        BETA = new double[] { 0.0, 0.597, 1.494 };
    }
}
