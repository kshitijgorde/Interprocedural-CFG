// 
// Decompiled by Procyon v0.5.30
// 

public class BangForce extends SonicForce
{
    public BangForce(final double n, final double n2) {
        super(n, n2);
    }
    
    public void makeForce(final double n, final double n2) {
        int n3 = (int)(n2 * super.samplingRate);
        if (n3 < 1) {
            n3 = 1;
        }
        for (int i = 0; i < n3; ++i) {
            super.force[i] = 0.5 * (1.0 - Math.cos(6.283185307179586 * (i + 1) / (1 + n3))) * n;
        }
    }
    
    public void makeForce(final double n) {
        this.makeForce(n, 0.0);
    }
    
    public static void main(final String[] array) {
        final BangForce bangForce = new BangForce(8012.0, 0.01);
        bangForce.makeForce(1.0, 0.001);
        for (int i = 0; i < bangForce.nSamples; ++i) {
            System.out.println(i / bangForce.samplingRate + " " + bangForce.force[i]);
        }
    }
}
