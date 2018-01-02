// 
// Decompiled by Procyon v0.5.30
// 

public class BangBangForce extends SonicForce
{
    private double[] bangForce;
    
    public BangBangForce(final double n, final double n2) {
        super(n, n2);
    }
    
    public void makeForce(final double n, final double n2, final double n3) {
        int n4 = (int)(n2 * super.samplingRate);
        if (n4 < 1) {
            n4 = 1;
        }
        this.bangForce = new double[n4];
        for (int i = 0; i < n4; ++i) {
            this.bangForce[i] = 0.5 * (1.0 - Math.cos(6.283185307179586 * (i + 1) / (1 + n4))) * n;
        }
        try {
            for (int j = 0; j < super.nSamples; ++j) {
                if (Math.random() < n3) {
                    for (int n5 = 0; n5 < n4 && j + n5 < super.nSamples; ++n5) {
                        final double[] force = super.force;
                        final int n6 = j + n5;
                        force[n6] += this.bangForce[n5];
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("out of bounds in BangBangForce ");
        }
    }
    
    public void makeForce(final double n) {
        this.makeForce(n, 0.0, 0.001);
    }
}
