import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class compute extends Canvas
{
    double DEC;
    double RA;
    int offset;
    String[] str;
    double declin;
    Rise_Set rs;
    char deg;
    boolean demo;
    Color myFontColor;
    
    compute(final Date dat, final double LAT, final double LONG, final boolean myDemo, final int myFont) {
        this.str = new String[30];
        this.deg = '°';
        this.demo = myDemo;
        this.myFontColor = new Color(myFont, myFont, myFont);
        final int hours = dat.getHours();
        final int minutes = dat.getMinutes();
        final int seconds = dat.getSeconds();
        final int date = dat.getDate();
        int month = dat.getMonth();
        ++month;
        int year = dat.getYear();
        year += 1900;
        this.offset = -dat.getTimezoneOffset() / 60;
        this.rs = new Rise_Set(dat, LAT, -LONG);
        this.str[17] = "Sunrise    " + this.rs.rise_String();
        this.str[18] = "Sunset     " + this.rs.set_String();
        final double UT = hours - this.offset + minutes / 60.0 + seconds / 3600.0;
        int utHours = hours - this.offset;
        if (utHours < 0) {
            utHours += 24;
        }
        if (utHours > 24) {
            utHours -= 24;
        }
        final double JD = this.Jul_Date(date, month, year, UT);
        final double T = (JD - 2451545.0) / 36525.0;
        this.RA = this.RightAscension(T) / 15.0;
        double GHA = this.sun_GHA(date, month, year, UT);
        GHA %= 360.0;
        double eqt = this.eot(date, month, year, UT);
        final double H = this.sun_elev(JD, LAT, LONG, this.declin, this.RA);
        final double AZ = this.Azimut(this.declin, LAT, -LONG, GHA, H);
        final double distance = this.radius(JD);
        final double GMST = this.GM_sidereal_Time(JD);
        final double LMST = 24.0 * this.frac((GMST - LONG / 15.0) / 24.0);
        final int hour_LMST = (int)LMST;
        final double minutes_LMST = (LMST - hour_LMST) * 60.0;
        final int min_LMST = (int)minutes_LMST;
        final int seconds_LMST = (int)Math.round((minutes_LMST - min_LMST) * 60.0);
        String s;
        if (hour_LMST > 9) {
            s = "";
        }
        else {
            s = "0";
        }
        String LMST_Str = "Local Sidereal Time   " + s + hour_LMST;
        if (min_LMST > 9) {
            s = "";
        }
        else {
            s = "0";
        }
        LMST_Str = String.valueOf(LMST_Str) + ":" + s + min_LMST + ":";
        if (seconds_LMST > 9) {
            s = "";
        }
        else {
            s = "0";
        }
        LMST_Str = String.valueOf(LMST_Str) + s + seconds_LMST;
        s = String.valueOf(Math.abs(LAT));
        if (LAT > 0.0) {
            s = String.valueOf(s) + this.deg + " N";
        }
        else {
            s = String.valueOf(s) + this.deg + " S";
        }
        this.str[4] = "Latitude   " + s;
        s = String.valueOf(Math.abs(LONG));
        if (LONG > 0.0) {
            s = String.valueOf(s) + this.deg + " W";
        }
        else {
            s = String.valueOf(s) + this.deg + " E";
        }
        this.str[5] = "Longitude  " + s;
        this.str[7] = dat.toString();
        if (-dat.getTimezoneOffset() / 60 >= 0) {
            this.str[6] = "Time Zone  UT + " + Math.abs(dat.getTimezoneOffset() / 60) + " h";
        }
        else {
            this.str[6] = "Time Zone   UT - " + Math.abs(dat.getTimezoneOffset() / 60) + " h";
        }
        if (utHours > 9) {
            s = "";
        }
        else {
            s = "0";
        }
        this.str[8] = "UT         " + s + utHours;
        if (minutes > 9) {
            s = ":";
        }
        else {
            s = ":0";
        }
        this.str[8] = String.valueOf(this.str[8]) + s + minutes;
        if (seconds > 9) {
            s = ":";
        }
        else {
            s = ":0";
        }
        this.str[8] = String.valueOf(this.str[8]) + s + seconds;
        s = String.valueOf(Math.round(100000.0 * JD) / 100000.0);
        this.str[9] = "Jul. Day   " + s;
        this.str[10] = LMST_Str;
        s = String.valueOf(Math.round(10.0 * GHA) / 10.0);
        this.str[11] = "Greenwich Hour Angle  " + s + this.deg;
        final double diff = Math.abs(eqt) - (int)Math.abs(eqt);
        long min = (int)Math.round(diff * 60.0);
        if (min == 60L) {
            min = 0L;
            if (eqt >= 0.0) {
                ++eqt;
            }
            else {
                --eqt;
            }
        }
        if (min > 9L) {
            s = ":";
        }
        else {
            s = ":0";
        }
        String eqtStr = (int)eqt + s + min + " min";
        if (eqt < 0.0 && (int)eqt == 0) {
            eqtStr = "-" + (int)eqt + s + min + " min";
        }
        this.str[12] = "Equation of Time      " + eqtStr;
        s = String.valueOf(Math.round(10.0 * H) / 10.0);
        this.str[13] = "Elevation    " + s + this.deg;
        s = String.valueOf(Math.round(100.0 * Math.abs(this.declin)) / 100.0);
        if (this.declin > 0.0) {
            s = String.valueOf(s) + this.deg + " N";
        }
        else {
            s = String.valueOf(s) + this.deg + " S";
        }
        s = String.valueOf(s) + " = " + (int)Math.abs(this.declin) + this.deg + " " + (int)Math.round(60.0 * this.frac(Math.abs(this.declin))) + "'";
        if (this.declin > 0.0) {
            s = String.valueOf(s) + " N";
        }
        else {
            s = String.valueOf(s) + " S";
        }
        this.str[14] = "Declination  " + s;
        s = String.valueOf(Math.round(10.0 * AZ) / 10.0);
        this.str[15] = "Azimuth      " + s + this.deg;
        s = String.valueOf(Math.round(100000.0 * distance) / 100000.0);
        this.str[16] = "Distance     " + s + " AU";
        this.repaint();
    }
    
    public double sunL(final double T) {
        final double tau = T / 10.0;
        double L = 280.4664567 + 360007.6982779 * tau + 0.03032028 * tau * tau + tau * tau * tau / 49931.0 - tau * tau * tau * tau / 15299.0 + tau * tau * tau * tau * tau / 1988000.0;
        L %= 360.0;
        if (L < 0.0) {
            L += 360.0;
        }
        return L;
    }
    
    public double deltaPSI(final double T) {
        final double K = 0.017453292519943295;
        final double LS = this.sunL(T);
        double LM = 218.3165 + 481267.8813 * T;
        LM %= 360.0;
        if (LM < 0.0) {
            LM += 360.0;
        }
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        double deltaPsi = -17.2 * Math.sin(0.017453292519943295 * omega) - 1.32 * Math.sin(0.03490658503988659 * LS) - 0.23 * Math.sin(0.03490658503988659 * LM) + 0.21 * Math.sin(0.03490658503988659 * omega);
        deltaPsi /= 3600.0;
        return deltaPsi;
    }
    
    public double EPS(final double T) {
        final double K = 0.017453292519943295;
        final double LS = this.sunL(T);
        final double LM = 218.3165 + 481267.8813 * T;
        final double eps0 = 23.43929111111111 - (46.815 * T + 5.9E-4 * T * T - 0.001813 * T * T * T) / 3600.0;
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        final double deltaEps = (9.2 * Math.cos(0.017453292519943295 * omega) + 0.57 * Math.cos(0.03490658503988659 * LS) + 0.1 * Math.cos(0.03490658503988659 * LM) - 0.09 * Math.cos(0.03490658503988659 * omega)) / 3600.0;
        return eps0 + deltaEps;
    }
    
    public double eot(final int date, final int month, final int year, final double UT) {
        final double K = 0.017453292519943295;
        final double T = (this.Jul_Date(date, month, year, UT) - 2451545.0) / 36525.0;
        final double eps = this.EPS(T);
        final double RA = this.RightAscension(T);
        final double LS = this.sunL(T);
        final double deltaPsi = this.deltaPSI(T);
        double E = LS - 0.0057183 - RA + deltaPsi * Math.cos(0.017453292519943295 * eps);
        if (E > 5.0) {
            E -= 360.0;
        }
        E *= 4.0;
        return E;
    }
    
    public double RightAscension(final double T) {
        final double K = 0.017453292519943295;
        final double L = this.sunL(T);
        double M = 357.5291 + 35999.0503 * T - 1.559E-4 * T * T - 4.8E-7 * T * T * T;
        M %= 360.0;
        if (M < 0.0) {
            M += 360.0;
        }
        double C = (1.9146 - 0.004817 * T - 1.4E-5 * T * T) * Math.sin(0.017453292519943295 * M);
        C += (0.019993 - 1.01E-4 * T) * Math.sin(0.03490658503988659 * M);
        C += 2.9E-4 * Math.sin(0.05235987755982989 * M);
        final double theta = L + C;
        double eps = this.EPS(T);
        eps += 0.00256 * Math.cos(0.017453292519943295 * (125.04 - 1934.136 * T));
        final double lambda = theta - 0.00569 - 0.00478 * Math.sin(0.017453292519943295 * (125.04 - 1934.136 * T));
        double RA = Math.atan2(Math.cos(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda), Math.cos(0.017453292519943295 * lambda));
        RA /= 0.017453292519943295;
        if (RA < 0.0) {
            RA += 360.0;
        }
        double delta = Math.asin(Math.sin(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda));
        delta /= 0.017453292519943295;
        this.DEC = delta;
        this.declin = delta;
        return RA;
    }
    
    double Jul_Date(final int date, int month, int year, final double ut) {
        double A = 10000.0 * year + 100.0 * month + date;
        if (month <= 2) {
            month += 12;
            --year;
        }
        double B;
        if (A <= 1.58210041E7) {
            B = -2 + (year + 4716) / 4 - 1179;
        }
        else {
            B = year / 400 - year / 100 + year / 4;
        }
        A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + ut / 24.0;
        return MJD + 2400000.5;
    }
    
    double GM_sidereal_Time(final double JD) {
        final double MJD = JD - 2400000.5;
        final double MJD2 = (long)MJD;
        final double ut = (MJD - (long)MJD2) * 24.0;
        final double t_eph = (MJD2 - 51544.5) / 36525.0;
        return 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
    }
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
    }
    
    void sun(final double JD) {
        final double PI2 = 6.283185307179586;
        final double cos_eps = 0.917482062;
        final double sin_eps = 0.397777156;
        final double T = (JD - 2451545.0) / 36525.0;
        final double M = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
        final double DL = 6893.0 * Math.sin(M) + 72.0 * Math.sin(2.0 * M);
        final double L = 6.283185307179586 * this.frac(0.7859453 + M / 6.283185307179586 + (6191.2 * T + DL) / 1296000.0);
        final double SL = Math.sin(L);
        final double X = Math.cos(L);
        final double Y = 0.917482062 * SL;
        final double Z = 0.397777156 * SL;
        final double R = Math.sqrt(1.0 - Z * Z);
        this.DEC = 57.29577951308232 * Math.atan(Z / R);
        this.RA = 7.639437268410976 * Math.atan(Y / (X + R));
        if (this.RA < 0.0) {
            this.RA += 24.0;
        }
    }
    
    double LM_sidereal_Time(final double JD, final double LONG) {
        final double GMST = this.GM_sidereal_Time(JD);
        return 24.0 * this.frac((GMST - LONG / 15.0) / 24.0);
    }
    
    double sun_GHA(final int date, final int month, final int year, final double ut) {
        final double JD = this.Jul_Date(date, month, year, ut);
        final double GMST = this.GM_sidereal_Time(JD);
        double tau = 15.0 * (GMST - this.RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        if (tau < 0.0) {
            tau += 360.0;
        }
        return tau;
    }
    
    double sun_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        final double K = 0.017453292519943295;
        double tau = 15.0 * (this.LM_sidereal_Time(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        final double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
        return Math.asin(sinH) / 0.017453292519943295;
    }
    
    double radius(final double JD) {
        final double K = 0.017453292519943295;
        final double T = (JD - 2415020.0) / 36525.0;
        double G = 358.4758333 + 35999.0 * T + (179.1 * T - 0.54 * T * T) / 3600.0;
        G *= 0.017453292519943295;
        final double R = 3.057E-5 - 1.5E-7 * T + (-0.00727412 + 1.814E-5 * T) * Math.cos(G) + (-9.138E-5 + 4.6E-7 * T) * Math.cos(2.0 * G) - 1.45E-6 * Math.cos(3.0 * G);
        return Math.exp(R * Math.log(10.0));
    }
    
    public double Azimut(final double dec, final double latitude, final double longitude, final double GHA, final double hoehe) {
        final double K = 0.017453292519943295;
        final double lat_K = latitude * 0.017453292519943295;
        final double hoehe_K = hoehe * 0.017453292519943295;
        final double cosAz = (Math.sin(dec * 0.017453292519943295) - Math.sin(lat_K) * Math.sin(hoehe_K)) / (Math.cos(hoehe_K) * Math.cos(lat_K));
        double Az = 1.5707963267948966 - Math.asin(cosAz);
        Az /= 0.017453292519943295;
        if (Math.sin(0.017453292519943295 * (GHA + longitude)) <= 0.0) {
            Az = Az;
        }
        else {
            Az = 360.0 - Az;
        }
        return Az;
    }
    
    public void paint(final Graphics g) {
        final int r = (int)Math.floor(17.0 * Math.random());
        g.setColor(this.myFontColor);
        for (int i = 4; i < 17; ++i) {
            if (!this.demo) {
                g.drawString(this.str[i], 30, i * 18);
            }
            else if (i == r) {
                g.drawString("DEMO", 30, i * 18);
            }
            else {
                g.drawString(this.str[i], 30, i * 18);
            }
        }
        for (int j = 17; j < 19; ++j) {
            g.drawString(this.str[j], 30, j * 18);
        }
        g.setColor(Color.red);
        g.setFont(new Font("Courier", 0, 10));
        g.drawString("© Juergen Giesen 1998-2009", 30, 350);
        g.drawString("http://www.GeoAstro.de", 30, 366);
    }
}
