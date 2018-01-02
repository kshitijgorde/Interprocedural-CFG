// 
// Decompiled by Procyon v0.5.30
// 

public class PluckForce extends SonicForce
{
    public PluckForce(final double n, final double n2) {
        super(n, n2);
    }
    
    public void makeForce(final double n, final double n2) {
        int n3 = (int)(n2 * super.samplingRate);
        if (n3 < 1) {
            n3 = 1;
        }
        for (int i = 0; i < n3; ++i) {
            super.force[i] = (i + 1.0) * n / n3;
        }
    }
    
    public void makeForce(final double n) {
        this.makeForce(n, super.durationOfForce / 10.0);
    }
    
    public static void main(final String[] array) {
        final PluckForce pluckForce = new PluckForce(8012.0, 0.02);
        pluckForce.makeForce(1.0, 0.001);
        for (int i = 0; i < pluckForce.nSamples; ++i) {
            System.out.println(i / pluckForce.samplingRate + " " + pluckForce.force[i]);
        }
    }
}
