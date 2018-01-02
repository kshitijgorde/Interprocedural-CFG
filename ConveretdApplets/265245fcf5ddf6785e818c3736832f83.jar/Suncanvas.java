import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Suncanvas extends Canvas
{
    String str;
    final double K = 0.017453292519943295;
    final char deg = '°';
    final int oben = 150;
    final int links = 590;
    compute comp;
    Rise_Set rs;
    Date dat;
    int min;
    double UT;
    double equation;
    double trans;
    int locOffset;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    double latitude;
    double longitude;
    String eqtStr;
    String riseStr;
    String setStr;
    String transStr;
    int minWOZ;
    int secWOZ;
    int hWOZ;
    final int yUT = 210;
    final int xUT = 160;
    final int xUT1 = 430;
    final int rSec = 50;
    final int rMin = 40;
    final int rh = 100;
    final int r = 120;
    double ST;
    double st;
    int minST;
    int secST;
    int MIN;
    int sec;
    double WOZ;
    double sunAzim;
    double moonAzim;
    double moonElev;
    double sunElev;
    double moonLHA;
    double sunLHA;
    double JD;
    String moonRiseStr;
    String moonSetStr;
    double moonIllFrac;
    String pmString;
    double h_SunRise;
    double h_SunSet;
    double moonCulmTime;
    double nextMoonCulm;
    double moonLHARise;
    double moonLHASet;
    boolean azimuthOK;
    String versStr;
    double sunDec;
    double moonDec;
    double deltaMoonTrans;
    double prevMoonCulm;
    String locString;
    double diffLHA;
    double diffDec;
    double hSunRise;
    double hSunSet;
    double hMoonRise;
    double hMoonSet;
    boolean zeroDown;
    
    public Suncanvas(final String vStr, final int _date, final int _month, final int _year, final int _hours, final int _minutes, final int _seconds, final double myLat, final double myLong, final String myLoc, final int myLocOffset, final boolean myAzim, final boolean zDown) {
        this.minWOZ = 0;
        this.secWOZ = 0;
        this.hWOZ = 0;
        this.ST = 0.0;
        this.MIN = 0;
        this.sec = 0;
        this.WOZ = 0.0;
        this.latitude = myLat;
        this.longitude = -myLong;
        this.locOffset = myLocOffset;
        this.versStr = vStr;
        this.locString = myLoc;
        (this.dat = new Date()).setDate(_date);
        this.dat.setMonth(_month);
        this.dat.setYear(_year);
        this.dat.setHours(_hours);
        this.dat.setMinutes(_minutes);
        this.dat.setSeconds(_seconds);
        this.year = _year;
        this.month = _month;
        this.date = _date;
        this.hours = _hours;
        this.minutes = _minutes;
        this.seconds = _seconds;
        this.azimuthOK = myAzim;
        this.zeroDown = zDown;
        this.comp = new compute();
        this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.equation = this.eot(this.date, this.month + 1, this.year + 1900, this.UT);
        this.min = (int)Math.round(60.0 * this.comp.frac(Math.abs(this.equation)));
        if (this.min == 60) {
            this.min = 0;
            if (this.equation >= 0.0) {
                ++this.equation;
            }
            else {
                --this.equation;
            }
        }
        if (this.min > 9) {
            this.str = " min ";
        }
        else {
            this.str = " min 0";
        }
        if (this.equation > 0.0) {
            this.eqtStr = "+ " + (int)this.equation;
        }
        if (this.equation < 0.0) {
            this.eqtStr = "- " + (int)Math.abs(this.equation);
        }
        if (this.equation < 0.0 && (int)this.equation == 0) {
            this.eqtStr = "- " + (int)this.equation;
        }
        this.eqtStr = String.valueOf(this.eqtStr) + this.str + this.min + " s";
        this.rs = new Rise_Set(this.dat, this.latitude, this.longitude, this.locOffset, -0.833);
        this.riseStr = this.rs.rise_String();
        this.hSunRise = this.rs.h_rise();
        this.setStr = this.rs.set_String();
        this.hSunSet = this.rs.h_set();
        this.trans = 12.0 - this.equation / 60.0 + this.longitude / 15.0;
        this.trans += this.locOffset;
        this.transStr = this.comp.makeTimeString(this.trans);
        this.JD = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, this.UT);
        final double RA = this.rs.sunDecRA(2, this.JD);
        final double sunGHA = this.sun_GHA(this.JD, RA);
        this.sunDec = this.rs.sunDecRA(1, this.JD);
        this.sunElev = this.sun_elev(this.JD, this.latitude, this.longitude, this.sunDec, RA);
        this.sunAzim = this.computeAzimut(this.sunDec, this.latitude, -this.longitude, sunGHA, this.sunElev);
        this.sunLHA = this.comp.sun_LHA(this.date, this.month, this.year, this.UT, this.locOffset, this.latitude, this.longitude);
        final double sunLHA1 = this.comp.sun_LHA(this.date, this.month, this.year, this.UT - 1.0, this.locOffset, this.latitude, this.longitude);
        final Rise_Set RS = new Rise_Set(this.dat, this.latitude, this.longitude, this.locOffset, -0.833);
        this.h_SunRise = RS.h_rise();
        this.h_SunSet = RS.h_set();
        final Moon moon = new Moon(this.versStr, this.dat, this.latitude, -this.longitude, this.locOffset, this.locString);
        final double moonRA = moon.alpha();
        this.moonDec = moon.delta();
        double h = this.comp.THETA0(this.JD) - this.longitude - moonRA;
        if (h < 0.0) {
            h += 360.0;
        }
        this.moonLHA = h;
        this.moonLHA %= 360.0;
        this.diffLHA = this.sunLHA - this.moonLHA;
        this.diffDec = this.sunDec - this.moonDec;
        this.moonAzim = Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.moonDec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        this.moonAzim += 180.0;
        if (this.moonAzim > 360.0) {
            this.moonAzim -= 360.0;
        }
        this.moonElev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * this.moonDec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * this.moonDec) * Math.cos(0.017453292519943295 * h);
        this.moonElev = Math.asin(this.moonElev) / 0.017453292519943295;
        final double horParal = 3288.90917195;
        double paral = Math.cos(0.017453292519943295 * this.moonElev) * Math.sin(0.017453292519943295 * horParal / 3600.0);
        paral = Math.asin(paral) / 0.017453292519943295;
        this.moonElev -= paral;
        final Moon moonRS = new Moon(this.versStr, this.dat, this.latitude, -this.longitude, this.locOffset, this.locString);
        this.moonRiseStr = moonRS.riseString(this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, 0.0), 24, -this.locOffset);
        this.hMoonRise = moonRS.h_MoonRise() + this.locOffset;
        this.moonSetStr = moonRS.setString(this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, 0.0), 24, -this.locOffset);
        this.hMoonSet = moonRS.h_MoonSet() + this.locOffset;
        final Moon MOON = new Moon(this.versStr, this.dat, this.latitude, -this.longitude, this.locOffset, this.locString);
        this.moonCulmTime = MOON.culmTime();
        this.nextMoonCulm = MOON.nextCulmTime();
        this.prevMoonCulm = MOON.prevCulmTime();
        this.moonLHARise = this.comp.moon_LHA(this.date, this.month, this.year, this.hMoonRise - this.locOffset, this.locOffset, this.latitude, this.longitude);
        this.moonLHASet = this.comp.moon_LHA(this.date, this.month, this.year, this.hMoonSet - this.locOffset, this.locOffset, this.latitude, this.longitude);
        final Moon moon2 = new Moon(this.versStr, this.dat, this.latitude, -this.longitude, this.locOffset, this.locString);
        this.moonIllFrac = moon2.phase();
        final double moonIllFrac1 = moon2.phase1();
        final double moonIllFrac2 = moon2.phase2();
        if (this.moonIllFrac < moonIllFrac1 && moonIllFrac1 > moonIllFrac2) {
            this.pmString = " (-)";
        }
        else {
            this.pmString = " (+)";
        }
    }
    
    double sun_GHA(final double JD, final double RA) {
        final double GMST = this.comp.GM_Sidereal_Time(JD);
        double tau = 15.0 * (GMST - RA);
        tau %= 360.0;
        if (tau < 0.0) {
            tau += 360.0;
        }
        return tau;
    }
    
    public double computeAzimut(final double dec, final double latitude, final double longitude, final double GHA, final double hoehe) {
        double cosAz = (Math.sin(dec * 0.017453292519943295) - Math.sin(latitude * 0.017453292519943295) * Math.sin(hoehe * 0.017453292519943295)) / (Math.cos(hoehe * 0.017453292519943295) * Math.cos(0.017453292519943295 * latitude));
        if (Math.abs(cosAz) > 1.0) {
            System.out.println("Error computeAzimut cosAz=" + cosAz);
            cosAz = -1.0;
        }
        final double Az = Math.acos(cosAz) / 0.017453292519943295;
        if (Math.sin(0.017453292519943295 * (GHA + longitude)) <= 0.0) {
            return Az;
        }
        return 360.0 - Az;
    }
    
    double sun_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        double tau = 15.0 * (this.comp.LMST(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        final double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
        return Math.asin(sinH) / 0.017453292519943295;
    }
    
    public double sunL(final double T) {
        double L = 280.46645 + 36000.76983 * T + 3.032E-4 * T * T;
        final double tau = T / 10.0;
        L = 280.4664567 + 360007.6982779 * tau + 0.03032028 * tau * tau + tau * tau * tau / 49931.0 - tau * tau * tau * tau / 15299.0 + tau * tau * tau * tau * tau / 1988000.0;
        L %= 360.0;
        if (L < 0.0) {
            L += 360.0;
        }
        return L;
    }
    
    public double deltaPSI(final double T) {
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
        final double LS = this.sunL(T);
        final double LM = 218.3165 + 481267.8813 * T;
        final double eps0 = 23.43929111111111 - (46.815 * T + 5.9E-4 * T * T - 0.001813 * T * T * T) / 3600.0;
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        final double deltaEps = (9.2 * Math.cos(0.017453292519943295 * omega) + 0.57 * Math.cos(0.03490658503988659 * LS) + 0.1 * Math.cos(0.03490658503988659 * LM) - 0.09 * Math.cos(0.03490658503988659 * omega)) / 3600.0;
        return eps0 + deltaEps;
    }
    
    public double eot(final int date, final int month, final int year, final double UT) {
        final double T = (this.comp.Jul_Date(date, month, year, UT) - 2451545.0) / 36525.0;
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
        return RA;
    }
    
    void sunTable() {
        final String[] dataStr = new String[370];
        final String[] DateMonth = new String[370];
        final String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        String eqtStr = "";
        final double[] EQT = new double[370];
        final double[] TRANS = new double[370];
        int n = 0;
        for (int m = 0; m < 12; ++m) {
            for (int d = 1; d <= this.comp.daysInMonth(m, this.year); ++d) {
                EQT[n] = this.eot(d, m + 1, this.year + 1900, 12 - this.locOffset);
                final double trans = 12.0 - EQT[n] / 60.0 + this.longitude / 15.0;
                TRANS[n] = trans + this.locOffset;
                String str = monthArray[m];
                if (d < 10) {
                    str = String.valueOf(str) + " ";
                }
                DateMonth[n] = String.valueOf(str) + " " + d;
                ++n;
            }
        }
        for (int i = 0; i < n; ++i) {
            dataStr[i] = String.valueOf(DateMonth[i]) + "  " + this.comp.makeTimeString(TRANS[i]) + "  ";
            double equation = EQT[i];
            int min = (int)Math.round(60.0 * this.comp.frac(Math.abs(equation)));
            if (min == 60) {
                min = 0;
                if (equation >= 0.0) {
                    ++equation;
                }
                else {
                    --equation;
                }
            }
            String str;
            if (min > 9) {
                str = " m ";
            }
            else {
                str = " m 0";
            }
            if (equation > 0.0) {
                eqtStr = "+";
            }
            if (equation < 0.0) {
                eqtStr = "-";
            }
            if (equation < 0.0 && (int)equation == 0) {
                eqtStr = "-";
            }
            if (Math.abs(equation) < 10.0) {
                eqtStr = String.valueOf(eqtStr) + " ";
            }
            eqtStr = String.valueOf(eqtStr) + (int)Math.abs(equation);
            eqtStr = String.valueOf(eqtStr) + str + min + " s";
            this.transStr = this.comp.makeTimeString(TRANS[i + 1]);
            final String[] array = dataStr;
            final int n2 = i;
            array[n2] = String.valueOf(array[n2]) + " " + eqtStr + "    " + DateMonth[i + 1] + "  " + this.transStr + "   " + (int)Math.round(3600.0 * (TRANS[i + 1] - TRANS[i])) + " s" + "\n";
        }
        String str = "SunMoon Clock " + this.versStr + "\n" + "© 2007-2010 J. Giesen  - www.GeoAstro.de" + "\n";
        str = String.valueOf(str) + this.locString + ",  " + Math.abs(this.latitude);
        if (this.latitude >= 0.0) {
            str = String.valueOf(str) + " N";
        }
        else {
            str = String.valueOf(str) + " S";
        }
        str = String.valueOf(str) + "  " + Math.abs(this.longitude);
        if (this.longitude >= 0.0) {
            str = String.valueOf(str) + " W";
        }
        else {
            str = String.valueOf(str) + " E";
        }
        if (this.locOffset >= 0) {
            str = String.valueOf(str) + ",  UT +" + this.locOffset + " h";
        }
        else {
            str = String.valueOf(str) + ",  UT " + this.locOffset + " h";
        }
        str = String.valueOf(str) + "\n\n";
        str = String.valueOf(str) + "        Trans      E. o. T            Trans   \u2206Trans\n";
        dataStr[0] = String.valueOf(str) + dataStr[0];
        final scrollFrame sf = new scrollFrame("Solar Days " + (this.year + 1900), n - 2, dataStr, false);
        sf.resize(380, 400);
        sf.show();
    }
    
    public void paint(final Graphics g) {
        g.setColor(Color.black);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        this.JD = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, this.UT);
        g.setFont(new Font("Courier", 0, 12));
        this.str = String.valueOf(Math.round(100000.0 * this.JD) / 100000.0);
        if (this.str.length() == 12) {
            this.str = String.valueOf(this.str) + "0";
        }
        g.drawString(String.valueOf(this.str), 690, 385);
        g.drawString(this.comp.HMS(15.0 * this.comp.LMST(this.JD, this.longitude)), 690, 400);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString("Local Sidereal Time", 590, 400);
        g.drawString("Julian Day", 590, 385);
        g.drawRect(20, 80, 550, 260);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString("Sunrise", 590, 150);
        g.drawString("Transit", 590, 165);
        g.drawString("Sunset", 590, 180);
        g.drawString("Sunlight", 590, 195);
        g.drawString("Equation of Time", 590, 210);
        g.setFont(new Font("Courier", 0, 10));
        g.drawString(this.riseStr, 680, 150);
        g.drawString(this.transStr, 680, 165);
        g.drawString(this.setStr, 680, 180);
        g.drawString(this.comp.HMS(15.0 * (this.hSunSet - this.hSunRise)), 680, 195);
        g.drawString(String.valueOf(this.eqtStr), 680, 210);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString("Moonrise", 590, 235);
        g.drawString("Transit", 590, 250);
        g.drawString("Moonset", 590, 265);
        g.drawString("Moonlight", 590, 280);
        g.drawString("Illum. Fraction", 590, 295);
        g.drawString("Prev. Transit", 590, 310);
        g.drawString("Next Transit", 590, 325);
        g.setFont(new Font("Courier", 0, 10));
        g.drawString(this.moonRiseStr, 680, 235);
        if (this.moonCulmTime >= 0.0) {
            g.drawString(this.comp.makeTimeString(this.moonCulmTime), 680, 250);
        }
        else {
            g.drawString("--:--", 680, 250);
        }
        g.drawString(this.moonSetStr, 680, 265);
        double hMoonLight = this.hMoonSet - this.hMoonRise;
        if (hMoonLight < 0.0) {
            hMoonLight += 24.0;
        }
        g.drawString(this.comp.HMS(15.0 * hMoonLight), 680, 280);
        g.drawString(Math.round(1000.0 * this.moonIllFrac) / 10.0 + " %" + this.pmString, 680, 295);
        this.deltaMoonTrans = 24.0 + this.moonCulmTime - this.prevMoonCulm;
        if (this.prevMoonCulm < 0.0) {
            this.prevMoonCulm += 24.0;
        }
        g.drawString(this.comp.makeTimeString(this.prevMoonCulm) + " (" + this.comp.HMS1(this.deltaMoonTrans) + ")", 680, 310);
        this.deltaMoonTrans = 24.0 + this.nextMoonCulm - this.moonCulmTime;
        g.drawString(this.comp.makeTimeString(this.nextMoonCulm) + " (" + this.comp.HMS1(this.deltaMoonTrans) + ")", 680, 325);
        g.setFont(new Font("Helvetica", 0, 10));
        this.WOZ = this.UT - this.longitude / 15.0 + this.equation / 60.0;
        this.WOZ %= 24.0;
        this.hWOZ = (int)this.WOZ;
        this.MIN = (int)(this.comp.frac(this.WOZ) * 60.0);
        this.minWOZ = this.MIN;
        this.sec = (int)Math.round(this.comp.frac(this.WOZ) * 3600.0 - this.MIN * 60);
        this.secWOZ = this.sec;
        final double sunLHARise = this.comp.sun_LHA(this.date, this.month, this.year, this.h_SunRise - this.locOffset, this.locOffset, this.latitude, this.longitude);
        final double sunLHASet = this.comp.sun_LHA(this.date, this.month, this.year, this.h_SunSet - this.locOffset, this.locOffset, this.latitude, this.longitude);
        double W1 = sunLHARise + 180.0;
        double W2 = sunLHASet + 180.0;
        if (this.zeroDown) {
            W1 += 180.0;
            W2 += 180.0;
        }
        final double sunTimeRise = (12.0 + sunLHARise / 15.0) % 24.0;
        final double sunTimeSet = (12.0 + sunLHASet / 15.0) % 24.0;
        g.setColor(Color.red);
        g.setFont(new Font("Courier", 0, 10));
        g.drawString(this.comp.makeTimeString(sunTimeRise), 730, 150);
        g.drawString("12:00", 730, 165);
        g.drawString(this.comp.makeTimeString(sunTimeSet), 730, 180);
        if (sunLHASet > sunLHARise) {
            g.setColor(Color.gray);
            g.fillOval(41, 91, 238, 238);
            g.setColor(Color.white);
            g.fillArc(40, 90, 240, 240, (int)(90.0 - W1), (int)(W1 - W2));
        }
        else {
            g.setColor(Color.white);
            g.fillOval(41, 91, 238, 238);
            g.setColor(Color.gray);
            g.fillArc(40, 90, 240, 240, (int)(90.0 - W1), (int)(W1 - W2));
        }
        g.setFont(new Font("Courier", 0, 10));
        g.setColor(Color.red);
        g.drawOval(40, 90, 240, 240);
        g.setColor(Color.black);
        if (!this.zeroDown) {
            g.drawString("0", 157, 104);
            g.drawString("12", 153, 325);
            g.drawString("18", 46, 213);
            g.drawString("6", 268, 213);
        }
        else {
            g.drawString("12", 154, 104);
            g.drawString("0", 157, 325);
            g.drawString("6", 46, 213);
            g.drawString("18", 265, 213);
        }
        for (int i = 0; i < 12; ++i) {
            final int xH = 160 + (int)Math.round(110.0 * Math.sin(i * 30 * 0.017453292519943295));
            final int yH = 210 + (int)Math.round(110.0 * Math.cos(i * 30 * 0.017453292519943295));
            if (i * 30 % 90 != 0) {
                g.fillOval(xH - 2, yH - 2, 4, 4);
            }
        }
        for (int j = 0; j < 12; ++j) {
            final double winkel = 0.017453292519943295 * (15 + j * 30);
            final int xH = 160 + (int)Math.round(110.0 * Math.sin(winkel));
            final int yH = 210 + (int)Math.round(110.0 * Math.cos(winkel));
            g.drawOval(xH - 1, yH - 1, 2, 2);
        }
        double h = this.WOZ * 15.0;
        if (this.zeroDown) {
            h += 180.0;
        }
        int xH = (int)Math.round(100.0 * Math.sin(h * 0.017453292519943295));
        int yH = (int)Math.round(100.0 * Math.cos(h * 0.017453292519943295));
        g.drawLine(160, 210, 160 + xH, 210 - yH);
        g.drawLine(161, 210, 160 + xH, 210 - yH);
        g.drawLine(162, 210, 160 + xH, 210 - yH);
        g.drawLine(158, 210, 160 + xH, 210 - yH);
        g.drawLine(159, 210, 160 + xH, 210 - yH);
        g.drawLine(160, 212, 160 + xH, 210 - yH);
        g.drawLine(160, 211, 160 + xH, 210 - yH);
        g.drawLine(160, 208, 160 + xH, 210 - yH);
        g.drawLine(160, 209, 160 + xH, 210 - yH);
        g.setColor(Color.red);
        g.fillOval(157, 207, 6, 6);
        g.setColor(Color.black);
        g.drawOval(157, 207, 6, 6);
        g.setFont(new Font("Helvetica", 0, 11));
        g.setColor(Color.red);
        g.drawString("SUN", 70, 360);
        g.setColor(Color.blue);
        g.drawString("MOON", 350, 360);
        g.setColor(Color.black);
        g.drawString("Difference", 255, 360);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString("Local Solar Time", 70, 375);
        g.drawString("Local Lunar Time", 350, 375);
        g.drawString("Local Hour Angle", 70, 390);
        g.drawString("Local Hour Angle", 350, 390);
        g.drawString("Declination", 350, 405);
        g.drawString("Declination", 70, 405);
        g.drawString("Azimuth Angle", 70, 420);
        g.drawString("Azimuth Angle", 350, 420);
        g.drawString("Altitude", 350, 435);
        g.drawString("Altitude", 70, 435);
        g.setFont(new Font("Courier", 0, 12));
        g.drawString(this.comp.makeTimeString1(this.hWOZ, this.minWOZ, this.secWOZ), 180, 375);
        g.drawString(new StringBuffer().append(Math.round(100.0 * this.sunLHA) / 100.0).append('°').toString(), 180, 390);
        g.drawString(new StringBuffer().append(Math.round(100.0 * this.sunDec) / 100.0).append('°').toString(), 180, 405);
        g.drawString(new StringBuffer().append(Math.round(100.0 * this.sunAzim) / 100.0).append('°').toString(), 180, 420);
        g.drawString(new StringBuffer().append(Math.round(10.0 * this.sunElev) / 10.0).append('°').toString(), 180, 435);
        if (this.sunLHA > this.moonLHA) {
            g.drawString(String.valueOf(this.comp.HMS(this.diffLHA)), 255, 375);
        }
        else {
            g.drawString("-" + this.comp.HMS(this.diffLHA), 255, 375);
        }
        g.drawString(new StringBuffer().append(Math.round(100.0 * this.diffLHA) / 100.0).append('°').toString(), 255, 390);
        g.drawString(new StringBuffer().append(Math.round(100.0 * this.diffDec) / 100.0).append('°').toString(), 255, 405);
        if (this.azimuthOK) {
            if (!this.zeroDown) {
                xH = (int)Math.round(120.0 * Math.sin(this.sunAzim * 0.017453292519943295));
                yH = (int)Math.round(120.0 * Math.cos(this.sunAzim * 0.017453292519943295));
            }
            else {
                xH = (int)Math.round(120.0 * Math.sin((this.sunAzim + 180.0) * 0.017453292519943295));
                yH = (int)Math.round(120.0 * Math.cos((this.sunAzim + 180.0) * 0.017453292519943295));
            }
            g.setColor(Color.red);
            g.fillOval(160 + xH - 4, 210 - yH - 4, 8, 8);
        }
        double w1 = this.moonLHARise + 180.0;
        double w2 = this.moonLHASet + 180.0;
        if (this.zeroDown) {
            w1 += 180.0;
            w2 += 180.0;
        }
        final double moonTimeRise = (12.0 + this.moonLHARise / 15.0) % 24.0;
        final double moonTimeSet = (12.0 + this.moonLHASet / 15.0) % 24.0;
        g.setColor(Color.blue);
        g.setFont(new Font("Courier", 0, 10));
        g.drawString(this.comp.makeTimeString(moonTimeRise), 730, 235);
        g.drawString("12:00", 730, 250);
        g.drawString(this.comp.makeTimeString(moonTimeSet), 730, 265);
        if (this.moonLHASet > this.moonLHARise) {
            g.setColor(Color.gray);
            g.fillOval(311, 91, 238, 238);
            g.setColor(Color.white);
            g.fillArc(310, 90, 240, 240, (int)(90.0 - w1), (int)(w1 - w2));
        }
        else {
            g.setColor(Color.white);
            g.fillOval(311, 91, 238, 238);
            g.setColor(Color.gray);
            g.fillArc(310, 90, 240, 240, (int)(90.0 - w1), (int)(w1 - w2));
        }
        g.setColor(Color.blue);
        g.drawOval(310, 90, 240, 240);
        g.setColor(Color.black);
        g.setFont(new Font("Courier", 0, 10));
        if (!this.zeroDown) {
            g.drawString("0", 427, 104);
            g.drawString("12", 423, 325);
            g.drawString("18", 316, 213);
            g.drawString("6", 538, 213);
        }
        else {
            g.drawString("12", 424, 104);
            g.drawString("0", 427, 325);
            g.drawString("6", 316, 213);
            g.drawString("18", 535, 213);
        }
        this.ST = this.moonLHA / 15.0;
        this.ST = this.ST;
        this.st = this.comp.frac(this.ST);
        this.MIN = (int)(this.st * 60.0);
        this.sec = (int)Math.round(this.st * 3600.0 - this.MIN * 60);
        this.ST += 12.0;
        h = this.ST % 24.0;
        if (this.zeroDown) {
            h += 12.0;
        }
        xH = (int)Math.round(100.0 * Math.sin(h * 15.0 * 0.017453292519943295));
        yH = (int)Math.round(100.0 * Math.cos(h * 15.0 * 0.017453292519943295));
        g.drawLine(430, 210, 430 + xH, 210 - yH);
        g.drawLine(431, 210, 430 + xH, 210 - yH);
        g.drawLine(432, 210, 430 + xH, 210 - yH);
        g.drawLine(428, 210, 430 + xH, 210 - yH);
        g.drawLine(429, 210, 430 + xH, 210 - yH);
        g.drawLine(430, 212, 430 + xH, 210 - yH);
        g.drawLine(430, 211, 430 + xH, 210 - yH);
        g.drawLine(430, 208, 430 + xH, 210 - yH);
        g.drawLine(430, 209, 430 + xH, 210 - yH);
        if (this.azimuthOK) {
            if (!this.zeroDown) {
                xH = (int)Math.round(120.0 * Math.sin(this.moonAzim * 0.017453292519943295));
                yH = (int)Math.round(120.0 * Math.cos(this.moonAzim * 0.017453292519943295));
            }
            else {
                xH = (int)Math.round(120.0 * Math.sin((this.moonAzim + 180.0) * 0.017453292519943295));
                yH = (int)Math.round(120.0 * Math.cos((this.moonAzim + 180.0) * 0.017453292519943295));
            }
            g.setColor(Color.blue);
            g.fillOval(430 + xH - 4, 210 - yH - 4, 8, 8);
        }
        g.setColor(Color.blue);
        g.fillOval(427, 207, 6, 6);
        g.setColor(Color.black);
        g.drawOval(427, 207, 6, 6);
        g.setFont(new Font("Courier", 0, 12));
        g.drawString(this.comp.makeTimeString1(this.ST, this.MIN, this.sec), 460, 375);
        g.drawString(new StringBuffer().append(Math.round(100.0 * this.moonLHA) / 100.0).append('°').toString(), 460, 390);
        g.drawString(new StringBuffer().append(Math.round(100.0 * this.moonDec) / 100.0).append('°').toString(), 460, 405);
        g.drawString(new StringBuffer().append(Math.round(100.0 * this.moonAzim) / 100.0).append('°').toString(), 460, 420);
        g.drawString(new StringBuffer().append(Math.round(10.0 * this.moonElev) / 10.0).append('°').toString(), 460, 435);
        for (int k = 0; k < 12; ++k) {
            xH = 430 + (int)Math.round(110.0 * Math.sin(k * 30 * 0.017453292519943295));
            yH = 210 + (int)Math.round(110.0 * Math.cos(k * 30 * 0.017453292519943295));
            if (k * 30 % 90 != 0) {
                g.fillOval(xH - 2, yH - 2, 4, 4);
            }
        }
        for (int l = 0; l < 12; ++l) {
            final double winkel = 0.017453292519943295 * (15 + l * 30);
            xH = 430 + (int)Math.round(110.0 * Math.sin(winkel));
            yH = 210 + (int)Math.round(110.0 * Math.cos(winkel));
            g.drawOval(xH - 1, yH - 1, 2, 2);
        }
    }
}
