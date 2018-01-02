// 
// Decompiled by Procyon v0.5.30
// 

class RiseSet
{
    boolean RISE;
    boolean SETT;
    boolean ABOVE;
    int locOffset;
    int date;
    int month;
    int year;
    compute comp;
    double latitude;
    double longitude;
    double YE;
    double zero1;
    double zero2;
    double UTRISE;
    double UTSET;
    int NZ;
    double Y0;
    double yPlus;
    double yMinus;
    double DX;
    String riseStr;
    String setStr;
    double lenDay;
    
    RiseSet(final int DAT, final int Month, final int Year, final int offset, final double Lat, final double Long) {
        this.date = DAT;
        this.month = Month;
        this.year = Year;
        this.locOffset = offset;
        this.latitude = Lat;
        this.longitude = Long;
        this.comp = new compute();
    }
    
    public void currentRiseSet() {
        this.RISE = false;
        this.SETT = false;
        for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
            this.riseset(this.date, this.month + 1, this.year + 1900, i, 1);
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
                this.riseStr = this.comp.makeTimeString(this.UTRISE);
            }
            if (this.SETT) {
                this.setStr = this.comp.makeTimeString(this.UTSET);
            }
            if (this.RISE && this.SETT) {
                this.lenDay = this.UTSET - this.UTRISE;
            }
        }
        if (this.RISE) {
            final double hRise = this.UTRISE;
            this.riseStr = this.comp.makeTimeString(hRise);
        }
        else if (this.ABOVE) {
            this.riseStr = "Sun visible all day";
            this.setStr = "Sun visible all day";
            this.lenDay = 24.0;
        }
        else {
            this.riseStr = "Sun unvisible all day";
            this.setStr = "Sun unvisible all day";
            this.lenDay = 0.0;
        }
        if (this.SETT) {
            final double hSet = this.UTSET;
            this.setStr = this.comp.makeTimeString(hSet);
        }
        else if (this.ABOVE) {
            this.riseStr = "Sun visible all day";
            this.setStr = "Sun visible all day";
        }
        else {
            this.riseStr = "Sun unvisible all day";
            this.setStr = "Sun unvisible all day";
        }
    }
    
    public void riseset(final int DATE, final int MONTH, final int YEAR, final double HOUR, final int what) {
        final double K = 0.017453292519943295;
        double sh = 0.0;
        switch (what) {
            case 1: {
                sh = Math.sin(-0.014543828656868749);
                break;
            }
            case 2: {
                sh = Math.sin(-0.10471975511965978);
                break;
            }
            case 3: {
                sh = Math.sin(-0.20943951023931956);
                break;
            }
            case 4: {
                sh = Math.sin(-0.3141592653589793);
                break;
            }
        }
        final double JD = this.comp.JD(DATE, MONTH, YEAR, HOUR);
        double dec = this.comp.sunDecRA(JD, 1);
        double ra = this.comp.sunDecRA(JD, 2);
        this.Y0 = this.comp.sin_elev(JD, this.latitude, -this.longitude, dec, ra) - sh;
        final double jdPlus = this.comp.JD(DATE, MONTH, YEAR, HOUR + 1.0);
        dec = this.comp.sunDecRA(jdPlus, 1);
        ra = this.comp.sunDecRA(jdPlus, 2);
        this.yPlus = this.comp.sin_elev(jdPlus, this.latitude, -this.longitude, dec, ra) - sh;
        final double jdMinus = this.comp.JD(DATE, MONTH, YEAR, HOUR - 1.0);
        dec = this.comp.sunDecRA(jdMinus, 1);
        ra = this.comp.sunDecRA(jdMinus, 2);
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
    
    public String returnStr() {
        return String.valueOf(this.riseStr) + "   " + this.setStr + "   " + Math.round(100.0 * this.lenDay) / 100.0;
    }
}
