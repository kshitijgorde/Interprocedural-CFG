// 
// Decompiled by Procyon v0.5.30
// 

class PlanetRiseSet
{
    double K;
    double AU;
    double sinh0;
    double h0;
    double m0;
    double m1;
    double m2;
    double RA;
    double RA1;
    double RA2;
    double RA3;
    double dec;
    double dec1;
    double dec2;
    double dec3;
    double lat;
    double longit;
    double lambda;
    double beta;
    double JD;
    double deltaM;
    double eps;
    int planet;
    int Date;
    int Month;
    int Year;
    double D;
    double RP;
    double jd;
    double T;
    double azim;
    double elev;
    double distance;
    double L;
    double B;
    double declin;
    compute comp;
    double deltaT;
    double ut;
    
    public PlanetRiseSet(final int date, final int month, final int year, final double UT, final double latitude, final double longitude, final int myPlanet, final double dT) {
        this.K = 0.017453292519943295;
        this.AU = 1.49597871E8;
        this.comp = new compute();
        this.deltaT = dT;
        this.planet = myPlanet;
        this.lat = latitude;
        this.longit = longitude;
        this.jd = this.comp.JD(date, month, year, 0.0);
        final double theta0 = this.THETA0(this.jd);
        this.ut = UT;
        this.Date = date;
        this.Month = month;
        this.Year = year;
        switch (myPlanet) {
            case 0: {
                this.JD = this.comp.JD(date, month, year, UT);
                this.T = (this.JD - 2451545.0) / 36525.0;
                this.eps = this.K * (23.433333333333334 + (21.448 - 46.815 * this.T - 5.9E-4 * this.T * this.T + 0.001813 * this.T * this.T * this.T) / 3600.0);
                this.eps += this.K * this.comp.deltaEps(this.T);
                this.h0 = -0.8333;
                this.sinh0 = Math.sin(this.K * this.h0);
                final EarthCompute earth = new EarthCompute(this.JD, this.deltaT);
                this.distance = earth.R();
                this.L = earth.heliocentricLambda();
                this.lambda = this.L + 180.0;
                this.lambda = this.lambda + this.comp.deltaPsi(this.T) - 20.4898 / (this.distance * 3600.0);
                if (this.lambda < 0.0) {
                    this.lambda += 360.0;
                }
                this.beta = -earth.BETA();
                this.declin = Math.sin(this.K * this.beta) * Math.cos(this.eps) + Math.cos(this.K * this.beta) * Math.sin(this.eps) * Math.sin(this.K * this.lambda);
                this.declin = Math.asin(this.declin) / this.K % 360.0;
                this.RA = this.alpha();
                this.dec = this.delta();
                break;
            }
            case 4: {
                final JupiterCompute juc2 = new JupiterCompute(this.jd, this.deltaT);
                this.RA2 = juc2.alpha();
                this.dec2 = juc2.delta();
                final JupiterCompute juc3 = new JupiterCompute(this.jd - 1.0, this.deltaT);
                this.RA1 = juc3.alpha();
                this.dec1 = juc3.delta();
                final JupiterCompute juc4 = new JupiterCompute(this.jd + 1.0, this.deltaT);
                this.RA3 = juc4.alpha();
                this.dec3 = juc4.delta();
                this.JD = this.comp.JD(date, month, year, UT);
                this.h0 = -0.5667;
                this.sinh0 = Math.sin(this.K * this.h0);
                final JupiterCompute jup = new JupiterCompute(this.JD, this.deltaT);
                final double alph = jup.alpha();
                this.declin = jup.delta();
                final double ra = jup.alpha();
                final double h = this.THETA0(this.JD) + this.longit - ra;
                this.azim = Math.atan2(Math.sin(this.K * h), Math.cos(this.K * h) * Math.sin(this.K * this.lat) - Math.tan(this.K * this.declin) * Math.cos(this.K * this.lat)) / this.K;
                this.elev = Math.sin(this.K * this.lat) * Math.sin(this.K * this.declin) + Math.cos(this.K * this.lat) * Math.cos(this.K * this.declin) * Math.cos(this.K * h);
                this.elev = Math.asin(this.elev) / this.K;
                this.elev += this.comp.refract(this.elev) / 60.0;
                this.B = jup.BETA();
                this.beta = jup.beta();
                this.lambda = jup.lambda();
                this.distance = jup.R();
                this.L = jup.heliocentricLambda();
                this.D = jup.earthDist();
                this.RP = jup.R();
                this.T = (this.JD - 2451545.0) / 36525.0;
                this.eps = this.K * (23.433333333333334 + (21.448 - 46.815 * this.T - 5.9E-4 * this.T * this.T + 0.001813 * this.T * this.T * this.T) / 3600.0);
                this.declin = Math.sin(this.K * this.beta) * Math.cos(this.eps) + Math.cos(this.K * this.beta) * Math.sin(this.eps) * Math.sin(this.K * this.lambda);
                this.declin = Math.asin(this.declin) / this.K % 360.0;
                break;
            }
        }
        final double cosH0 = (this.sinh0 - Math.sin(this.K * latitude) * Math.sin(this.K * this.dec2)) / (Math.cos(this.K * latitude) * Math.cos(this.K * this.dec2));
        if (Math.abs(cosH0) <= 1.0) {
            final double H0 = Math.acos(cosH0) / this.K;
            this.m0 = (this.RA2 - longitude - theta0) / 360.0;
            while (this.m0 < 0.0) {
                ++this.m0;
            }
            while (this.m0 > 1.0) {
                --this.m0;
            }
            this.m1 = this.m0 - H0 / 360.0;
            while (this.m1 < 0.0) {
                ++this.m1;
            }
            while (this.m1 > 1.0) {
                --this.m1;
            }
            this.m2 = this.m0 + H0 / 360.0;
            while (this.m2 < 0.0) {
                ++this.m2;
            }
            while (this.m2 > 1.0) {
                --this.m2;
            }
            final double theta_0 = theta0 + 360.985647 * this.m0;
            this.RA = this.interpol(this.m0, this.RA1, this.RA2, this.RA3);
            this.dec = this.interpol(this.m0, this.dec1, this.dec2, this.dec3);
            double HH = theta_0 + longitude - this.RA;
            if (HH < 0.0) {
                HH += 360.0;
            }
            double delta = 0.01;
            if (myPlanet > 2) {
                delta = 0.01;
            }
            this.deltaM = -HH / 360.0;
            if (Math.abs(this.deltaM) < delta) {
                this.m0 += this.deltaM;
            }
            final double theta2 = theta0 + 360.985647 * this.m1;
            this.RA = this.interpol(this.m1, this.RA1, this.RA2, this.RA3);
            this.dec = this.interpol(this.m1, this.dec1, this.dec2, this.dec3);
            HH = theta2 + longitude - this.RA;
            if (HH < 0.0) {
                HH += 360.0;
            }
            double sinh = Math.sin(this.K * latitude) * Math.sin(this.K * this.dec) + Math.cos(this.K * latitude) * Math.cos(this.K * this.dec) * Math.cos(this.K * HH);
            double h2 = Math.asin(sinh) / this.K;
            this.deltaM = (h2 - this.h0) / (360.0 * Math.cos(this.K * this.dec) * Math.cos(this.K * latitude) * Math.sin(this.K * HH));
            if (Math.abs(this.deltaM) < delta) {
                this.m1 += this.deltaM;
            }
            final double theta3 = theta0 + 360.985647 * this.m2;
            this.RA = this.interpol(this.m2, this.RA1, this.RA2, this.RA3);
            this.dec = this.interpol(this.m2, this.dec1, this.dec2, this.dec3);
            HH = theta3 + longitude - this.RA;
            if (HH < 0.0) {
                HH += 360.0;
            }
            sinh = Math.sin(this.K * latitude) * Math.sin(this.K * this.dec) + Math.cos(this.K * latitude) * Math.cos(this.K * this.dec) * Math.cos(this.K * HH);
            h2 = Math.asin(sinh) / this.K;
            this.deltaM = (h2 - this.h0) / (360.0 * Math.cos(this.K * this.dec) * Math.cos(this.K * latitude) * Math.sin(this.K * HH));
            if (Math.abs(this.deltaM) < delta) {
                this.m2 += this.deltaM;
            }
            while (this.m0 < 0.0) {
                ++this.m0;
            }
            while (this.m0 > 1.0) {
                --this.m0;
            }
            while (this.m1 < 0.0) {
                ++this.m1;
            }
            while (this.m1 > 1.0) {
                --this.m1;
            }
            while (this.m2 < 0.0) {
                ++this.m2;
            }
            while (this.m2 > 1.0) {
                --this.m2;
            }
            this.m1 *= 24.0;
            this.m2 *= 24.0;
            this.m0 *= 24.0;
        }
        else {
            this.m0 = (this.RA2 - longitude - theta0) / 360.0;
            if (this.m0 > 1.0) {
                --this.m0;
            }
            if (this.m0 < 0.0) {
                ++this.m0;
            }
            this.m0 *= 24.0;
            this.m1 = 0.0;
            this.m2 = 0.0;
        }
    }
    
    double mag() {
        final EarthCompute earthComp = new EarthCompute(this.jd, this.deltaT);
        final double RE = earthComp.earthR();
        final double i = (this.RP * this.RP + this.D * this.D - RE * RE) / (2.0 * this.RP * this.D);
        final double Phi = Math.acos(i) / this.K;
        final double absPhi = Math.abs(Phi);
        double MAG = 0.0;
        final double term = 5.0 * Math.log(this.RP * this.D) * 0.43429448;
        MAG = -9.4 + term + 0.005 * absPhi;
        return MAG;
    }
    
    double illumFrac() {
        final EarthCompute earthComp = new EarthCompute(this.jd, this.deltaT);
        final double RE = earthComp.earthR();
        final double i = (this.RP * this.RP + this.D * this.D - RE * RE) / (2.0 * this.RP * this.D);
        final double Phi = Math.acos(i) / this.K;
        return 0.5 * (1.0 + Math.cos(this.K * Phi));
    }
    
    double phaseAngle() {
        final EarthCompute earthComp = new EarthCompute(this.jd, this.deltaT);
        final double RE = earthComp.earthR();
        final double i = (this.RP * this.RP + this.D * this.D - RE * RE) / (2.0 * this.RP * this.D);
        return Math.acos(i) / this.K;
    }
    
    double distance() {
        return this.distance;
    }
    
    double earthLambda() {
        return this.lambda;
    }
    
    double delta() {
        final double sinDelta = Math.sin(this.K * this.beta) * Math.cos(this.eps) + Math.cos(this.K * this.beta) * Math.sin(this.eps) * Math.sin(this.K * this.lambda);
        return Math.asin(sinDelta) / this.K % 360.0;
    }
    
    double declination() {
        return this.declin;
    }
    
    double alpha() {
        double a = Math.atan2(Math.sin(this.K * this.lambda) * Math.cos(this.eps) - Math.tan(this.K * this.beta) * Math.sin(this.eps), Math.cos(this.K * this.lambda)) / this.K;
        if (a < 0.0) {
            a += 360.0;
        }
        return a;
    }
    
    double interpol(final double m, final double x1, final double x2, final double x3) {
        return x2 + m * 0.5 * (x3 - x1 + m * (x1 + x3 - 2.0 * x2));
    }
    
    double elev() {
        return this.elev;
    }
    
    double azim() {
        if (this.azim < 0.0) {
            this.azim += 360.0;
        }
        return this.azim;
    }
    
    double utRise() {
        return this.m1;
    }
    
    double utSet() {
        return this.m2;
    }
    
    double utTrans() {
        return this.m0;
    }
    
    double THETA0(final double JD) {
        final double T = (JD - 2451545.0) / 36525.0;
        double x = 280.46061837 + 360.98564736629 * (JD - 2451545.0) + 3.87933E-4 * T * T - T * T * T / 3.871E7;
        x %= 360.0;
        if (x < 0.0) {
            x += 360.0;
        }
        return x;
    }
    
    double lambda() {
        if (this.lambda >= 360.0) {
            this.lambda -= 360.0;
        }
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
    
    double earthDistance() {
        return this.D;
    }
    
    double BETA() {
        return this.B;
    }
    
    double geoBeta() {
        return this.beta;
    }
}
