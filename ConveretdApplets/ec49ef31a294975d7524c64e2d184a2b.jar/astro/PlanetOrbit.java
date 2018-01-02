// 
// Decompiled by Procyon v0.5.30
// 

package astro;

public class PlanetOrbit
{
    private int planetNo;
    private double jd;
    private int nDivision;
    private Xyz[] orbit;
    
    private void doGetPlanetOrbit(final PlanetElm planetElm) {
        final double n = -2.0 * planetElm.axis * planetElm.e;
        final double sqrt = Math.sqrt(1.0 - planetElm.e * planetElm.e);
        int n2 = 0;
        int n3 = this.nDivision / 2;
        int n4 = this.nDivision / 2;
        int nDivision = this.nDivision;
        double n5 = 0.0;
        for (int i = 0; i <= this.nDivision / 4; ++i, n5 += 360.0 / this.nDivision) {
            final double n6 = planetElm.axis * (UdMath.udcos(n5) - planetElm.e);
            final double n7 = planetElm.axis * sqrt * UdMath.udsin(n5);
            this.orbit[n2++] = new Xyz(n6, n7, 0.0);
            this.orbit[n3--] = new Xyz(n - n6, n7, 0.0);
            this.orbit[n4++] = new Xyz(n - n6, -n7, 0.0);
            this.orbit[nDivision--] = new Xyz(n6, -n7, 0.0);
        }
    }
    
    public PlanetOrbit(final int planetNo, final ATime aTime, final int nDivision) {
        this.planetNo = planetNo;
        this.jd = aTime.getJd();
        this.nDivision = nDivision;
        final PlanetElm planetElm = new PlanetElm(planetNo, aTime);
        this.orbit = new Xyz[nDivision + 1];
        this.doGetPlanetOrbit(planetElm);
        final Matrix vectorConstant = Matrix.VectorConstant(planetElm.peri * 3.141592653589793 / 180.0, planetElm.node * 3.141592653589793 / 180.0, planetElm.incl * 3.141592653589793 / 180.0, aTime);
        final Matrix precMatrix = Matrix.PrecMatrix(aTime.getJd(), 2451512.5);
        for (int i = 0; i <= nDivision; ++i) {
            this.orbit[i] = this.orbit[i].Rotate(vectorConstant).Rotate(precMatrix);
        }
    }
    
    public double getEpoch() {
        return this.jd;
    }
    
    public int getDivision() {
        return this.nDivision;
    }
    
    public Xyz getAt(final int n) {
        return this.orbit[n];
    }
}
