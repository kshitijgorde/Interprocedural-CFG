// 
// Decompiled by Procyon v0.5.30
// 

class Planet
{
    public String name;
    double mass;
    double period;
    double a;
    double e;
    double i;
    double lan;
    double lp;
    double ml2000;
    public Cartesian u;
    public Cartesian v;
    public Cartesian w;
    double tPeri;
    double b;
    public double firstPerihelionAfterJ2000;
    double polarCoordFactor;
    double c;
    boolean mag;
    double mag1;
    double mag2;
    double mag3;
    double mag4;
    
    public Planet(final String name, final double mass, final double period, final double a, final double e, final double n, final double n2, final double n3, final double n4, final boolean mag, final double mag2, final double mag3, final double mag4, final double mag5) {
        this.period = period;
        this.name = name;
        this.mass = mass;
        this.a = a;
        this.e = e;
        this.i = n * 3.141592653589793 / 180.0;
        this.lan = this.make0to2Pi(n2 * 3.141592653589793 / 180.0);
        this.lp = this.make0to2Pi(n3 * 3.141592653589793 / 180.0);
        this.ml2000 = this.make0to2Pi(n4 * 3.141592653589793 / 180.0);
        this.b = Math.sqrt((1.0 - e) / (1.0 + e));
        this.mag = mag;
        this.mag1 = mag2;
        this.mag2 = mag3;
        this.mag3 = mag4;
        this.mag4 = mag5;
        this.v = Cartesian.zHat().scalarMult(Math.cos(this.i)).plus(Cartesian.latLongToUnitVector(0.0, this.lan).crossProduct(Cartesian.zHat()).makeUnitLength().scalarMult(Math.sin(this.i)));
        this.c = Math.atan2(this.v.x * Math.cos(this.lp) + this.v.y * Math.sin(this.lp), -this.v.z);
        if (this.c < 1.5707963267948966) {
            this.c += 3.141592653589793;
        }
        if (this.c > 1.5707963267948966) {
            this.c -= 3.141592653589793;
        }
        this.u = Cartesian.latLongToUnitVector(this.c, this.lp);
        this.w = this.u.crossProduct(this.v);
        this.firstPerihelionAfterJ2000 = this.make0to2Pi((n3 - n4) * 3.141592653589793 / 180.0) * period / 6.283185307179586;
        this.polarCoordFactor = a * (1.0 - e) * (1.0 + e);
    }
    
    private double make0to2Pi(final double n) {
        double n2;
        for (n2 = n - Math.round(n / 6.283185307179586) * 2.0 * 3.141592653589793; n2 < 0.0; n2 += 6.283185307179586) {}
        while (n2 > 6.283185307179586) {
            n2 -= 6.283185307179586;
        }
        return n2;
    }
    
    public double getLatOfPerihelion() {
        return this.c;
    }
    
    public double getLonOfPerihelion() {
        return this.lp;
    }
    
    public double getLat(final double n, final double n2, final boolean b) {
        final Cartesian position = this.position(n, n2, b);
        if (b) {
            return 0.0;
        }
        return Math.asin(position.makeUnitLength().z);
    }
    
    public double getLon(final double n, final double n2, final boolean b) {
        final Cartesian position = this.position(n, n2, b);
        if (b) {
            return 0.0;
        }
        return Math.atan2(position.y, position.x);
    }
    
    public Cartesian position(final double n, final double n2, final boolean b) {
        final double n3 = n - this.firstPerihelionAfterJ2000;
        double n4 = n3 - Math.round(n3 / this.period) * this.period;
        if (n4 > this.period) {
            n4 -= this.period;
        }
        if (n4 < 0.0) {
            n4 += this.period;
        }
        final double timeSincePerihelionToAngle = this.timeSincePerihelionToAngle(n4, n2, b);
        if (b) {
            return new Cartesian(0.0, 0.0, 0.0);
        }
        final double n5 = this.polarCoordFactor / (1.0 + this.e * Math.cos(timeSincePerihelionToAngle));
        return this.u.scalarMult(n5 * Math.cos(timeSincePerihelionToAngle)).plus(this.w.scalarMult(-n5 * Math.sin(timeSincePerihelionToAngle)));
    }
    
    private double angleToTimeSincePerihelion(final double n) {
        double n2 = n + 3.141592653589793;
        if (n2 > 6.283185307179586) {
            n2 -= 6.283185307179586;
        }
        double n3;
        try {
            n3 = 2.0 * Math.atan(Math.tan(n2 / 2.0) / this.b);
        }
        catch (Exception ex) {
            n3 = 3.141592653589793;
        }
        double n4 = this.period / 12.566370614359172 * (2.0 * n3 + this.e * Math.sin(n3)) + this.period / 2.0;
        if (n4 > this.period) {
            n4 -= this.period;
        }
        return n4;
    }
    
    private double timeSincePerihelionToAngle(final double n, final double n2, final boolean b) {
        final int n3 = 1000;
        boolean b2 = false;
        double n4 = 6.283185307179586 * n / this.period;
        int n5 = 0;
        double abs = this.period * 10.0;
        while (true) {
            final double angleToTimeSincePerihelion = this.angleToTimeSincePerihelion(n4);
            final double n6 = n4 + 3.141592653589793;
            final double cos = Math.cos(n6 / 2.0);
            double n8;
            try {
                final double n7 = Math.tan(n6 / 2.0) / this.b;
                n8 = 12.566370614359172 * this.b * (1.0 + n7 * n7) * cos * cos / (this.period * (2.0 + this.e * Math.cos(2.0 * Math.atan(n7))));
            }
            catch (Exception ex) {
                n8 = 12.566370614359172 * Math.sin(n6 / 2.0) * Math.sin(n6 / 2.0) / (this.b * this.period * (2.0 - this.e));
            }
            final double n9 = n - angleToTimeSincePerihelion;
            final double n10 = n9 * n8;
            n4 += n10;
            final boolean b3 = ++n5 > n3 || (n5 > 10 && Math.abs(n9) > abs);
            if (b2 || b3) {
                break;
            }
            abs = Math.abs(n9);
            b2 = (Math.abs(n10) < n2);
        }
        return n4;
    }
    
    public double magnitude(final double n, final double n2, final double n3) {
        if (this.mag) {
            final double n4 = n3 * 180.0 / 3.141592653589793;
            return this.mag1 + 5.0 * Math.log(n * n2) / Math.log(10.0) + this.mag2 * n4 + this.mag3 * Math.exp(this.mag4 * Math.log(n4));
        }
        return -999.0;
    }
}
