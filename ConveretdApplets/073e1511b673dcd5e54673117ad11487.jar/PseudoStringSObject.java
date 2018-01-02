import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class PseudoStringSObject extends LineSObject
{
    private Random random;
    private double[] f;
    private boolean freqIsInitted;
    
    public PseudoStringSObject(final double n, final double n2, final double n3, final int n4) {
        super(n, n2, n3, n4);
        this.freqIsInitted = false;
    }
    
    public PseudoStringSObject(final double n, final double n2, final double n3, final int n4, final double n5) {
        super(n, n2, n3, n4, n5);
        this.freqIsInitted = false;
    }
    
    public PseudoStringSObject(final double n, final double n2, final double n3, final int n4, final double n5, final double n6) {
        super(n, n2, n3, n4, n5, n6);
        this.freqIsInitted = false;
    }
    
    private void initFreq() {
        try {
            this.random = new Random();
            final int maxFreq = this.maxFreq();
            (this.f = new double[maxFreq + 1])[0] = 0.0;
            this.f[1] = super.f0;
            for (int i = 2; i <= maxFreq; ++i) {
                this.f[i] = super.f0 * this.random.nextDouble() + (i - 1) * super.f0;
            }
            this.freqIsInitted = true;
        }
        catch (Exception ex) {
            System.out.println("Exception " + ex + " in initFreq() in PseudoStringSObject");
        }
    }
    
    public double freq(final int n) {
        if (!this.freqIsInitted) {
            this.initFreq();
        }
        return this.f[n];
    }
}
