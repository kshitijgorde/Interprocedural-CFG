// 
// Decompiled by Procyon v0.5.30
// 

public class Bang2Force extends SonicForce
{
    public Bang2Force(final double n, final double n2) {
        super(n, n2);
    }
    
    public void makeForce(final double n, final double n2) {
        int n3 = (int)(n2 * super.samplingRate);
        if (n3 < 1) {
            n3 = 1;
        }
        for (int i = 0; i < n3; ++i) {
            super.force[i] = 0.5 * Math.sin(3.141592653589793 * (i + 1) / (1 + n3)) * n;
        }
    }
    
    public void makeForce(final double n) {
        this.makeForce(n, 0.0);
    }
}
