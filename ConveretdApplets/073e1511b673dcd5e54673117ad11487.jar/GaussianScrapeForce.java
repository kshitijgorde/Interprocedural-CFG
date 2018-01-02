import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class GaussianScrapeForce extends SonicForce
{
    public GaussianScrapeForce(final double n, final double n2) {
        super(n, n2);
    }
    
    public void makeForce(final double n, final double n2) {
        final Random random = new Random();
        int n3 = (int)(n2 * super.samplingRate);
        if (n3 < 1) {
            n3 = 1;
        }
        for (int i = 0; i < n3; ++i) {
            super.force[i] = random.nextGaussian() * n;
        }
    }
    
    public void makeForce(final double n) {
        this.makeForce(n, super.durationOfForce);
    }
}
