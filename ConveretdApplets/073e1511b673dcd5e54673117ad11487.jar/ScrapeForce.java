// 
// Decompiled by Procyon v0.5.30
// 

public class ScrapeForce extends SonicForce
{
    public ScrapeForce(final double n, final double n2) {
        super(n, n2);
    }
    
    public void makeForce(final double n, final double n2) {
        int n3 = (int)(n2 * super.samplingRate);
        if (n3 < 1) {
            n3 = 1;
        }
        for (int i = 0; i < n3; ++i) {
            super.force[i] = 2.0 * (Math.random() - 0.5) * n;
        }
    }
    
    public void makeForce(final double n) {
        this.makeForce(n, super.durationOfForce);
    }
    
    public static void main(final String[] array) {
        final ScrapeForce scrapeForce = new ScrapeForce(8012.0, 0.02);
        scrapeForce.makeForce(1.0);
        for (int i = 0; i < scrapeForce.nSamples; ++i) {
            System.out.println(i / scrapeForce.samplingRate + " " + scrapeForce.force[i]);
        }
    }
}
