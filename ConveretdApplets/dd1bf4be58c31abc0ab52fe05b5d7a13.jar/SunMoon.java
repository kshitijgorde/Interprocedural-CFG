// 
// Decompiled by Procyon v0.5.30
// 

class SunMoon
{
    double K;
    double lambda;
    double L;
    double B;
    
    public SunMoon(final double jd, final int myPlanet) {
        this.K = 0.017453292519943295;
        switch (myPlanet) {
            case 0: {
                final EarthCompute earthComp = new EarthCompute(jd);
                final double BE = earthComp.earthB();
                final double LE = earthComp.earthL();
                final double RE = earthComp.earthR();
                final double x = -RE * Math.cos(BE) * Math.cos(LE);
                final double y = -RE * Math.cos(BE) * Math.sin(LE);
                final double z = -RE * Math.sin(BE);
                this.L = earthComp.heliocentricLambda();
                this.lambda = Math.atan2(y, x) / this.K;
                if (this.lambda < 0.0) {
                    this.lambda += 360.0;
                    break;
                }
                break;
            }
            case 6: {
                final PlanetMoon moon = new PlanetMoon(jd);
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
                final PlanetMoon moonA = new PlanetMoon(jd);
                this.B = moonA.beta();
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
    
    double BETA() {
        return this.B;
    }
}
