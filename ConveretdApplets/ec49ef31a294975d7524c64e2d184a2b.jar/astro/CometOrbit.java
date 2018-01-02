// 
// Decompiled by Procyon v0.5.30
// 

package astro;

public class CometOrbit
{
    private Xyz[] orbit;
    private int nDivision;
    private static final double fMaxOrbit = 90.0;
    private static final double fTolerance = 1.0E-16;
    
    private void GetOrbitEllip(final Comet comet) {
        final double n = comet.getQ() / (1.0 - comet.getE());
        final double n2 = -2.0 * n * comet.getE();
        final double sqrt = Math.sqrt(1.0 - comet.getE() * comet.getE());
        if (n * (1.0 + comet.getE()) > 90.0) {
            final double n3 = Math.acos((1.0 - 90.0 / n) / comet.getE()) / (this.nDivision / 2 * (this.nDivision / 2));
            int n4 = this.nDivision / 2;
            for (int i = 0; i <= this.nDivision / 2; ++i) {
                final double n5 = n3 * i * i;
                final double n6 = n * (Math.cos(n5) - comet.getE());
                final double n7 = n * sqrt * Math.sin(n5);
                this.orbit[n4++] = new Xyz(n6, n7, 0.0);
                this.orbit[n4--] = new Xyz(n6, -n7, 0.0);
            }
        }
        else {
            int n8 = 0;
            int n9 = this.nDivision / 2;
            int nDivision = this.nDivision;
            double n10 = 0.0;
            for (int j = 0; j <= this.nDivision / 4; ++j, n10 += 6.283185307179586 / this.nDivision) {
                final double n11 = n * (Math.cos(n10) - comet.getE());
                final double n12 = n * sqrt * Math.sin(n10);
                this.orbit[n8++] = new Xyz(n11, n12, 0.0);
                this.orbit[n9--] = new Xyz(n2 - n11, n12, 0.0);
                this.orbit[n9++] = new Xyz(n2 - n11, -n12, 0.0);
                this.orbit[nDivision--] = new Xyz(n11, -n12, 0.0);
            }
        }
    }
    
    private void GetOrbitHyper(final Comet comet) {
        int n = this.nDivision / 2;
        final double sqrt = Math.sqrt(comet.getE() * comet.getE() - 1.0);
        final double n2 = comet.getQ() / (comet.getE() - 1.0);
        final double n3 = UdMath.arccosh((90.0 + n2) / (n2 * comet.getE())) / (this.nDivision / 2);
        double n4 = 0.0;
        for (int i = 0; i <= this.nDivision / 2; ++i, n4 += n3) {
            final double n5 = n2 * (comet.getE() - UdMath.cosh(n4));
            final double n6 = n2 * sqrt * UdMath.sinh(n4);
            this.orbit[n++] = new Xyz(n5, n6, 0.0);
            this.orbit[n--] = new Xyz(n5, -n6, 0.0);
        }
    }
    
    private void GetOrbitPara(final Comet comet) {
        int n = this.nDivision / 2;
        final double n2 = Math.atan(Math.sqrt(90.0 / comet.getQ() - 1.0)) * 2.0 / (this.nDivision / 2);
        double n3 = 0.0;
        for (int i = 0; i <= this.nDivision / 2; ++i, n3 += n2) {
            final double n4 = Math.sin(n3 / 2.0) / Math.cos(n3 / 2.0);
            final double n5 = comet.getQ() * (1.0 - n4 * n4);
            final double n6 = 2.0 * comet.getQ() * n4;
            this.orbit[n++] = new Xyz(n5, n6, 0.0);
            this.orbit[n--] = new Xyz(n5, -n6, 0.0);
        }
    }
    
    public CometOrbit(final Comet comet, final int nDivision) {
        this.nDivision = nDivision;
        this.orbit = new Xyz[nDivision + 1];
        if (comet.getE() < 0.9999999999999999) {
            this.GetOrbitEllip(comet);
        }
        else if (comet.getE() > 1.0) {
            this.GetOrbitHyper(comet);
        }
        else {
            this.GetOrbitPara(comet);
        }
        final Matrix vectorConstant = comet.getVectorConstant();
        final Matrix precMatrix = Matrix.PrecMatrix(comet.getEquinoxJd(), 2451545.0);
        for (int i = 0; i <= nDivision; ++i) {
            this.orbit[i] = this.orbit[i].Rotate(vectorConstant).Rotate(precMatrix);
        }
    }
    
    public int getDivision() {
        return this.nDivision;
    }
    
    public Xyz getAt(final int n) {
        return this.orbit[n];
    }
}
