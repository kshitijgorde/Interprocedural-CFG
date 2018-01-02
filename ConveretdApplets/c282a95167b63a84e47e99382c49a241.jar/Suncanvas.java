import java.util.Date;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Suncanvas extends Canvas
{
    String str;
    final double K = 0.017453292519943295;
    String s;
    double STD;
    double hRise;
    double hSet;
    Date dat;
    int locOffset;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    double latitude;
    double longitude;
    String riseStr;
    String setStr;
    boolean visible;
    boolean unvisible;
    double YE;
    double zero1;
    double zero2;
    double UTRISE;
    double UTSET;
    int NZ;
    boolean ABOVE;
    boolean RISE;
    boolean SETT;
    double Y0;
    double yPlus;
    double yMinus;
    double DX;
    double sunRA;
    char deg;
    Compute comp;
    double rsDec;
    double JD;
    
    public Suncanvas(final Date myDate, final double myLat, final double myLong, final int myLocOffset) {
        this.s = "";
        this.visible = false;
        this.unvisible = false;
        this.deg = '°';
        this.latitude = myLat;
        this.longitude = myLong;
        this.dat = myDate;
        this.hours = this.dat.getHours();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.year = this.dat.getYear();
        this.locOffset = myLocOffset;
        this.STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.RISE = false;
        this.SETT = false;
        for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
            this.riseset(this.date, this.month + 1, this.year + 1900, i);
            if (this.RISE && this.SETT) {
                break;
            }
        }
        if (this.RISE || this.SETT) {
            if (this.RISE) {
                this.UTRISE += this.locOffset;
                if (this.UTRISE > 24.0) {
                    this.UTRISE -= 24.0;
                }
                if (this.UTRISE < 0.0) {
                    this.UTRISE += 24.0;
                }
            }
            if (this.SETT) {
                this.UTSET += this.locOffset;
                if (this.UTSET > 24.0) {
                    this.UTSET -= 24.0;
                }
                if (this.UTSET < 0.0) {
                    this.UTSET += 24.0;
                }
            }
            if (this.RISE) {
                this.hRise = this.UTRISE;
            }
            if (this.SETT) {
                this.hSet = this.UTSET;
            }
        }
        else if (this.ABOVE) {
            this.riseStr = "Visible all day";
            this.setStr = "Visible all day";
            this.visible = true;
            this.hRise = 100.0;
            this.hSet = 100.0;
        }
        else {
            this.riseStr = "Invisible all day";
            this.setStr = "Invisible all day";
            this.unvisible = true;
            this.hRise = 100.0;
            this.hSet = 100.0;
        }
    }
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public double Rise() {
        return this.hRise;
    }
    
    public double Set() {
        return this.hSet;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public boolean notVisible() {
        return this.unvisible;
    }
    
    public void riseset(final int DATE, final int MONTH, final int YEAR, final double HOUR) {
        this.comp = new Compute();
        final double K = 0.017453292519943295;
        final double sh = Math.sin(-0.014543828656868749);
        final double JD = this.comp.JD(DATE, MONTH, YEAR, HOUR);
        double dec = this.comp.sunDecRA(1, JD);
        double ra = this.comp.sunDecRA(2, JD);
        this.Y0 = this.comp.sin_elev(JD, this.latitude, -this.longitude, dec, ra) - sh;
        final double jdPlus = this.comp.JD(DATE, MONTH, YEAR, HOUR + 1.0);
        dec = this.comp.sunDecRA(1, jdPlus);
        ra = this.comp.sunDecRA(2, jdPlus);
        this.yPlus = this.comp.sin_elev(jdPlus, this.latitude, -this.longitude, dec, ra) - sh;
        final double jdMinus = this.comp.JD(DATE, MONTH, YEAR, HOUR - 1.0);
        dec = this.comp.sunDecRA(1, jdMinus);
        ra = this.comp.sunDecRA(2, jdMinus);
        this.yMinus = this.comp.sin_elev(jdMinus, this.latitude, -this.longitude, dec, ra) - sh;
        this.ABOVE = (this.yMinus > 0.0);
        this.QUAD();
        switch (this.NZ) {
            case 1: {
                if (this.yMinus < 0.0) {
                    this.UTRISE = HOUR + this.zero1;
                    this.RISE = true;
                    break;
                }
                this.UTSET = HOUR + this.zero1;
                this.SETT = true;
                break;
            }
            case 2: {
                if (this.YE < 0.0) {
                    this.UTRISE = HOUR + this.zero2;
                    this.UTSET = HOUR + this.zero1;
                }
                else {
                    this.UTRISE = HOUR + this.zero1;
                    this.UTSET = HOUR + this.zero2;
                }
                this.RISE = true;
                this.SETT = true;
                break;
            }
        }
    }
    
    public void QUAD() {
        this.NZ = 0;
        final double A = 0.5 * (this.yMinus + this.yPlus) - this.Y0;
        final double B = 0.5 * (this.yPlus - this.yMinus);
        final double C = this.Y0;
        final double XE = -B / (2.0 * A);
        this.YE = (A * XE + B) * XE + C;
        final double DIS = B * B - 4.0 * A * C;
        if (DIS >= 0.0) {
            this.DX = 0.5 * Math.sqrt(DIS) / Math.abs(A);
            this.zero1 = XE - this.DX;
            this.zero2 = XE + this.DX;
            if (Math.abs(this.zero1) <= 1.0) {
                ++this.NZ;
            }
            if (Math.abs(this.zero2) <= 1.0) {
                ++this.NZ;
            }
            if (this.zero1 < -1.0) {
                this.zero1 = this.zero2;
            }
        }
    }
}
