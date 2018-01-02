// 
// Decompiled by Procyon v0.5.30
// 

class MoonDistance
{
    int date;
    int month;
    int year;
    double JD;
    double T;
    
    public double Jul_Date(final int date, int month, int year, final double UT) {
        double A = 10000.0 * year + 100.0 * month + date;
        if (month <= 2) {
            month += 12;
            --year;
        }
        final double B = year / 400 - year / 100 + year / 4;
        A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + UT / 24.0;
        return MJD + 2400000.5;
    }
    
    MoonDistance(final int date, final int month, final int year, final double UT) {
        this.date = date;
        this.month = month;
        this.year = year;
        this.JD = this.Jul_Date(date, month, year + 1900, UT);
        this.T = (this.JD - 2415020.0) / 36525.0;
        this.T = this.T;
    }
    
    double compute() {
        final double K = 0.017453292519943295;
        final double m = 296.104608 + 477000.0 * this.T + 198.849108 * this.T + 0.009192 * this.T * this.T;
        final double l = 270.434164 + 480960.0 * this.T + 307.883142 * this.T - 0.001133 * this.T * this.T;
        final double L = 279.696678 + 36000.0 * this.T + 0.768925 * this.T + 3.03E-4 * this.T * this.T;
        double P = 3423.0 + 187.0 * Math.cos(0.017453292519943295 * m) + 10.0 * Math.cos(0.03490658503988659 * m) + 34.0 * Math.cos(0.017453292519943295 * (2.0 * (l - L) - m)) + 28.0 * Math.cos(0.017453292519943295 * (2.0 * (l - L))) + 3.0 * Math.cos(0.017453292519943295 * (2.0 * (l - L) + m));
        P /= 3600.0;
        final double r = 6378.14 / Math.sin(0.017453292519943295 * P);
        return r;
    }
}
