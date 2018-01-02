// 
// Decompiled by Procyon v0.5.30
// 

class PlanetRiseSet
{
    double K;
    double lambda;
    double JD;
    int Date;
    int Month;
    double jd;
    double L;
    double B;
    
    public PlanetRiseSet(final double jd, final int myPlanet) {
        this.K = 0.017453292519943295;
        switch (myPlanet) {
            case 0: {
                final EarthCompute earthComp = new EarthCompute(jd);
                double BE = earthComp.earthB();
                double LE = earthComp.earthL();
                double RE = earthComp.earthR();
                double x = -RE * Math.cos(BE) * Math.cos(LE);
                double y = -RE * Math.cos(BE) * Math.sin(LE);
                double z = -RE * Math.sin(BE);
                this.lambda = Math.atan2(y, x) / this.K;
                if (this.lambda < 0.0) {
                    this.lambda += 360.0;
                }
                final EarthCompute earth = new EarthCompute(jd);
                this.L = earth.heliocentricLambda();
                BE = earth.earthB();
                LE = earth.earthL();
                RE = earth.earthR();
                x = -RE * Math.cos(BE) * Math.cos(LE);
                y = -RE * Math.cos(BE) * Math.sin(LE);
                z = -RE * Math.sin(BE);
                this.lambda = Math.atan2(y, x) / this.K;
                if (this.lambda < 0.0) {
                    this.lambda += 360.0;
                    break;
                }
                break;
            }
            case 6: {
                final Moon moon = new Moon(jd);
                this.lambda = moon.lambda();
                this.L = this.lambda;
                final EarthCompute earthComp = new EarthCompute(jd);
                final double BE = earthComp.earthB();
                final double LE = earthComp.earthL();
                final double RE = earthComp.earthR();
                final double x = -RE * Math.cos(BE) * Math.cos(LE);
                final double y = -RE * Math.cos(BE) * Math.sin(LE);
                final double z = -RE * Math.sin(BE);
                double Lambda = Math.atan2(y, x) / this.K;
                if (Lambda < 0.0) {
                    Lambda += 360.0;
                }
                this.lambda = Lambda;
                break;
            }
        }
    }
    
    double lambda() {
        if (this.lambda < 0.0) {
            this.lambda += 360.0;
        }
        return this.lambda;
    }
    
    double heliocentricLambda() {
        if (this.L < 0.0) {
            this.L += 360.0;
        }
        return this.L;
    }
}
