import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Suncanvas extends Canvas
{
    String str;
    final double K = 0.017453292519943295;
    final char deg = '°';
    int top;
    final int xL = 30;
    final int xLL = 30;
    final int y90;
    final int y0;
    final int xS = 256;
    final int unten;
    final int Radius = 9;
    String s;
    int hInt;
    int azInt;
    int xInt;
    int hBildInt;
    int transInt;
    int min;
    double hBild;
    double xx;
    double STD;
    double equation;
    double trans;
    double diff;
    Image myImage;
    Date dat;
    public Compute comp;
    int browserOffset;
    int locOffset;
    String timeString;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    double latitude;
    double longitude;
    double dec;
    double GHA;
    double hoehe;
    double azimut;
    String ghaStr;
    String eqtStr;
    String riseStr;
    String setStr;
    String transStr;
    String locStr;
    String heightStr;
    String azStr;
    String rise45Str;
    String set45Str;
    int[] hArray;
    int[] xArray;
    int xSun;
    int hSun;
    final int mapOben = 320;
    final int mapRechts = 490;
    String versStr;
    String gmtStr;
    String declinStr;
    double currentDec;
    Rise_Set rs;
    double currentElevation;
    double rise45;
    double set45;
    double rise;
    double set;
    double winkel;
    double delta45;
    int xE;
    int yE;
    int xM;
    int yM;
    int x9;
    int r;
    String delta45Str;
    double sum;
    boolean UV;
    String culmHoeheStr;
    String[] dataStr;
    int nData;
    String[] monthArray;
    boolean online;
    
    public Suncanvas(final Image bild, final Date myDate, final double myLat, final double myLong, final String myLoc, final int myLocOffset, final String vers, final boolean ol) {
        this.top = 90;
        this.y90 = this.top;
        this.y0 = this.top + 162;
        this.unten = this.y0 + 40;
        this.s = "";
        this.hArray = new int[25];
        this.xArray = new int[25];
        this.currentElevation = 0.0;
        this.sum = 0.0;
        this.UV = true;
        this.dataStr = new String[385];
        this.nData = 0;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.myImage = bild;
        this.versStr = vers;
        this.online = ol;
        this.latitude = myLat;
        this.longitude = myLong;
        this.locStr = myLoc;
        this.dat = myDate;
        this.hours = this.dat.getHours();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.year = this.dat.getYear();
        this.browserOffset = this.dat.getTimezoneOffset();
        this.browserOffset = -this.browserOffset / 60;
        this.locOffset = myLocOffset;
        this.timeString = String.valueOf(this.browserOffset) + " Hrs";
        if (this.browserOffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.comp = new Compute();
        this.dec = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, this.STD);
        this.currentDec = this.dec;
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.STD);
        this.str = String.valueOf(Math.round(10.0 * this.GHA) / 10.0);
        this.ghaStr = String.valueOf(this.str) + " ° W";
        this.equation = this.eot(this.date, this.month + 1, this.year + 1900, this.STD);
        this.diff = Math.abs(this.equation) - (int)Math.abs(this.equation);
        this.min = (int)Math.round(this.diff * 60.0);
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
        this.eqtStr = (int)this.equation + this.str + this.min + " s";
        if (this.equation < 0.0 && (int)this.equation == 0) {
            this.eqtStr = "-" + (int)this.equation + this.str + this.min + " s";
        }
        this.rs = new Rise_Set(this.dat, this.latitude, this.longitude, this.locOffset, -0.833);
        this.riseStr = this.rs.rise_String();
        this.setStr = this.rs.set_String();
        this.rise = this.rs.h_rise();
        this.set = this.rs.h_set();
        this.rs = new Rise_Set(this.dat, this.latitude, this.longitude, this.locOffset, 45.0);
        this.rise45 = this.rs.h_rise();
        this.set45 = this.rs.h_set();
        this.delta45 = this.set45 - this.rise45;
        if (this.delta45 > 0.0) {
            this.delta45Str = String.valueOf(String.valueOf(Math.round(10.0 * this.delta45) / 10.0)) + " h";
        }
        else {
            this.delta45Str = "";
        }
        if (this.rise45 > 0.0) {
            this.rise45Str = this.HM(this.rise45);
        }
        else {
            this.rise45Str = "--:--";
        }
        if (this.set45 > 0.0) {
            this.set45Str = this.HM(this.set45);
        }
        else {
            this.set45Str = "--:--";
        }
        this.trans = 12.0 - this.equation / 60.0 - this.longitude / 15.0;
        this.trans += this.locOffset;
        this.transStr = this.rs.makeTimeString("", this.trans);
        this.hoehe = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
        this.currentElevation = this.hoehe;
        this.str = String.valueOf(Math.round(10.0 * this.hoehe) / 10.0);
        this.heightStr = String.valueOf(this.str) + "°";
        this.azimut = this.comp.computeAzimut(this.dec, this.latitude, this.longitude, this.GHA, this.hoehe);
        this.str = String.valueOf(Math.round(10.0 * this.azimut) / 10.0);
        this.azStr = String.valueOf(this.str) + "°";
        this.hBild = this.hoehe * (this.y0 - this.y90) / 90.0;
        this.hBildInt = (int)Math.round(this.hBild);
        this.hSun = this.hBildInt;
        if (this.latitude >= this.dec) {
            this.xx = (180.0 - this.azimut) * 226.0 / 180.0;
        }
        else if (this.azimut < 180.0) {
            this.xx = this.azimut * 226.0 / 180.0;
        }
        else {
            this.xx = -(360.0 - this.azimut) * 226.0 / 180.0;
        }
        if (this.latitude >= this.dec) {
            this.xx = 256.0 - this.xx;
        }
        else {
            this.xx += 256.0;
        }
        this.xInt = (int)Math.round(this.xx);
        this.xSun = this.xInt;
        for (int i = -this.locOffset; i < -this.locOffset + 25; ++i) {
            final double dec1 = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, i);
            this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, i);
            this.hoehe = this.comp.computeHeight(dec1, this.latitude, this.longitude, this.GHA);
            this.hBild = this.hoehe * (this.y0 - this.y90) / 90.0;
            this.hBildInt = (int)Math.round(this.hBild);
            this.azimut = this.comp.computeAzimut(dec1, this.latitude, this.longitude, this.GHA, this.hoehe);
            if (this.latitude >= this.dec) {
                this.xx = (180.0 - this.azimut) * 226.0 / 180.0;
            }
            else if (this.azimut <= 180.0) {
                this.xx = this.azimut * 226.0 / 180.0;
            }
            else {
                this.xx = -(360.0 - this.azimut) * 226.0 / 180.0;
            }
            if (this.latitude >= this.dec) {
                this.xx = 256.0 - this.xx;
            }
            else {
                this.xx += 256.0;
            }
            this.xInt = (int)Math.round(this.xx);
            this.hArray[i + this.locOffset] = this.hBildInt;
            this.xArray[i + this.locOffset] = this.xInt;
        }
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.trans - this.locOffset);
        final double culm_hoehe = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
        this.culmHoeheStr = "Elev. " + Math.round(10.0 * culm_hoehe) / 10.0 + "°";
        this.repaint();
    }
    
    public void write(final int what) {
        String str = "";
        String titleStr = "";
        this.dataStr[0] = "Sunshine" + this.versStr + " - © 2000-2009 J. Giesen - www.GeoAstro.de" + "\n" + "\n";
        final String[] dataStr = this.dataStr;
        final int n = 0;
        dataStr[n] = String.valueOf(dataStr[n]) + "Location: " + this.locStr + ", " + Math.abs(this.latitude);
        if (this.latitude >= 0.0) {
            final String[] dataStr2 = this.dataStr;
            final int n2 = 0;
            dataStr2[n2] = String.valueOf(dataStr2[n2]) + "° N, ";
        }
        else {
            final String[] dataStr3 = this.dataStr;
            final int n3 = 0;
            dataStr3[n3] = String.valueOf(dataStr3[n3]) + "° S, ";
        }
        final String[] dataStr4 = this.dataStr;
        final int n4 = 0;
        dataStr4[n4] = String.valueOf(dataStr4[n4]) + Math.abs(this.longitude);
        if (this.longitude >= 0.0) {
            final String[] dataStr5 = this.dataStr;
            final int n5 = 0;
            dataStr5[n5] = String.valueOf(dataStr5[n5]) + "° E";
        }
        else {
            final String[] dataStr6 = this.dataStr;
            final int n6 = 0;
            dataStr6[n6] = String.valueOf(dataStr6[n6]) + "° W";
        }
        final String[] dataStr7 = this.dataStr;
        final int n7 = 0;
        dataStr7[n7] = String.valueOf(dataStr7[n7]) + ", UT ";
        if (this.locOffset >= 0) {
            final String[] dataStr8 = this.dataStr;
            final int n8 = 0;
            dataStr8[n8] = String.valueOf(dataStr8[n8]) + "+" + this.locOffset + "h" + "\n";
        }
        else {
            final String[] dataStr9 = this.dataStr;
            final int n9 = 0;
            dataStr9[n9] = String.valueOf(dataStr9[n9]) + "" + this.locOffset + "h" + "\n";
        }
        final String[] dataStr10 = this.dataStr;
        final int n10 = 0;
        dataStr10[n10] = String.valueOf(dataStr10[n10]) + "   Date       Rise  Trans    Set    Alt  Intense sun light\n\n";
        this.nData = 1;
        if (what == 1) {
            for (int i = 1; i <= this.daysInMonth(this.month, this.year); ++i) {
                final Date writeDat = new Date();
                writeDat.setDate(i);
                writeDat.setMonth(this.month);
                writeDat.setYear(this.year);
                final double equation = this.eot(i, this.month + 1, this.year + 1900, 12.0);
                double trans = 12.0 - equation / 60.0 - this.longitude / 15.0;
                trans += this.locOffset;
                this.transStr = this.HM(trans);
                final double GHA = this.comp.computeGHA(i, this.month + 1, this.year + 1900, trans - this.locOffset);
                final double dec = this.comp.computeDeclination(i, this.month + 1, this.year + 1900, 12.0);
                final double culm_hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
                final String culmHoeheStr = Math.round(10.0 * culm_hoehe) / 10.0 + "°";
                final Rise_Set rsw = new Rise_Set(writeDat, this.latitude, this.longitude, this.locOffset, 45.0);
                final double rise45 = rsw.h_rise();
                final double set45 = rsw.h_set();
                final Rise_Set rsw2 = new Rise_Set(writeDat, this.latitude, this.longitude, this.locOffset, -0.833);
                final double rise46 = rsw2.h_rise();
                final double set46 = rsw2.h_set();
                final double delta45 = set45 - rise45;
                if (delta45 > 0.0) {
                    this.delta45Str = String.valueOf(String.valueOf(Math.round(10.0 * delta45) / 10.0)) + " h";
                }
                else {
                    this.delta45Str = "";
                }
                if (rise45 > 0.0) {
                    this.rise45Str = this.HM(rise45);
                }
                else {
                    this.rise45Str = "--:--";
                }
                if (set45 > 0.0) {
                    this.set45Str = this.HM(set45);
                }
                else {
                    this.set45Str = "--:--";
                }
                if (i < 10) {
                    str = "0" + i;
                }
                else {
                    str = String.valueOf(i);
                }
                this.dataStr[i] = String.valueOf(this.year + 1900) + " " + this.monthArray[writeDat.getMonth()] + " " + str + "  " + this.HM(rise46) + "  " + this.transStr + "  " + this.HM(set46) + "  " + culmHoeheStr + "  " + this.rise45Str + " until " + this.set45Str + "\n";
                ++this.nData;
                titleStr = "Sunshine " + this.locStr + ", " + (this.year + 1900) + " " + this.monthArray[writeDat.getMonth()];
            }
        }
        if (what == 12) {
            for (int m = 0; m <= 11; ++m) {
                for (int j = 1; j <= this.daysInMonth(m, this.year); ++j) {
                    final Date writeDat2 = new Date();
                    writeDat2.setDate(j);
                    writeDat2.setMonth(m);
                    writeDat2.setYear(this.year);
                    final double equation = this.eot(j, m + 1, this.year + 1900, 12.0);
                    double trans = 12.0 - equation / 60.0 - this.longitude / 15.0;
                    trans += this.locOffset;
                    this.transStr = this.HM(trans);
                    final double GHA = this.comp.computeGHA(j, m + 1, this.year + 1900, trans - this.locOffset);
                    final double dec = this.comp.computeDeclination(j, m + 1, this.year + 1900, 12.0);
                    final double culm_hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
                    final String culmHoeheStr2 = Math.round(10.0 * culm_hoehe) / 10.0 + "°";
                    final Rise_Set rsw = new Rise_Set(writeDat2, this.latitude, this.longitude, this.locOffset, 45.0);
                    final double rise45 = rsw.h_rise();
                    final double set45 = rsw.h_set();
                    final Rise_Set rsw2 = new Rise_Set(writeDat2, this.latitude, this.longitude, this.locOffset, -0.833);
                    final double rise46 = rsw2.h_rise();
                    final double set46 = rsw2.h_set();
                    final double delta45 = set45 - rise45;
                    if (delta45 > 0.0) {
                        this.delta45Str = String.valueOf(String.valueOf(Math.round(10.0 * delta45) / 10.0)) + " h";
                    }
                    else {
                        this.delta45Str = "";
                    }
                    if (rise45 > 0.0) {
                        this.rise45Str = this.HM(rise45);
                    }
                    else {
                        this.rise45Str = "--:--";
                    }
                    if (set45 > 0.0) {
                        this.set45Str = this.HM(set45);
                    }
                    else {
                        this.set45Str = "--:--";
                    }
                    if (j < 10) {
                        str = "0" + j;
                    }
                    else {
                        str = String.valueOf(j);
                    }
                    this.dataStr[this.nData] = String.valueOf(this.year + 1900) + " " + this.monthArray[writeDat2.getMonth()] + " " + str + "  " + this.HM(rise46) + "  " + this.transStr + "  " + this.HM(set46) + "  " + culmHoeheStr2 + "  " + this.rise45Str + " until " + this.set45Str + "\n";
                    ++this.nData;
                    titleStr = "Sunshine " + this.locStr + ", " + (this.year + 1900);
                }
                this.dataStr[this.nData] = "\n";
                ++this.nData;
            }
        }
        final scrollFrame sf = new scrollFrame(titleStr, this.nData, this.dataStr, this.online);
        sf.resize(400, 200);
        sf.show();
    }
    
    public int daysInMonth(final int m, int y) {
        int n = 31;
        y = this.dat.getYear() + 1900;
        if (m == 0 | m == 2 | m == 4 | m == 6 | m == 7 | m == 9 | m == 11) {
            n = 31;
        }
        if (m == 3 | m == 5 | m == 8 | m == 10) {
            n = 30;
        }
        if (m == 1) {
            n = 28;
            if (y % 4 == 0) {
                n = 29;
            }
            if (y % 100 == 0) {
                n = 28;
            }
            if (y % 400 == 0) {
                n = 29;
            }
        }
        return n;
    }
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public String HM(double x) {
        String str = "";
        if (x < 0.0) {
            x = Math.abs(x);
        }
        final int HOUR = (int)x;
        final int MIN = (int)Math.round(this.frac(x) * 60.0);
        if (HOUR < 10) {
            str = "0";
        }
        str = String.valueOf(str) + HOUR + ":";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + MIN;
        return str;
    }
    
    public String makeAMPMString(double time) {
        String str = "?";
        if (time < 0.0) {
            time += 24.0;
        }
        if (time > 24.0) {
            time -= 24.0;
        }
        final double diff = time - (int)time;
        int min = (int)Math.round(diff * 60.0);
        if (min == 60) {
            min = 0;
            ++time;
        }
        String minStr;
        if (min > 9) {
            minStr = ":";
        }
        else {
            minStr = ":0";
        }
        str = String.valueOf(String.valueOf((int)time) + minStr + min);
        if (time < 10.0) {
            str = "0" + str;
        }
        if (time < 12.0) {
            str = String.valueOf(str) + " AM";
        }
        if (time > 12.0) {
            str = String.valueOf(String.valueOf((int)(time - 12.0)) + minStr + min + " PM");
        }
        return str;
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
        final double T = (this.JD(date, month, year, UT) - 2451545.0) / 36525.0;
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
        return RA;
    }
    
    public double JD(final int date, int month, int year, final double STD) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final double B = year / 400 - year / 100 + year / 4;
        final double A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + STD / 24.0;
        return MJD + 2400000.5;
    }
    
    public void paint(final Graphics g) {
        Font f = g.getFont();
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 9));
        g.drawString("Sunshine" + this.versStr, 490, 480);
        g.drawString("© 2000-2009  J. Giesen", 490, 492);
        g.setFont(f);
        g.drawImage(this.myImage, 30, this.top, this);
        if (this.UV) {
            g.setColor(Color.red);
            g.drawLine(30, this.y0 - 80, 482, this.y0 - 80);
            g.drawLine(30, this.y0 - 82, 482, this.y0 - 82);
            g.drawLine(30, this.y0 - 81, 482, this.y0 - 81);
        }
        g.setFont(new Font("Chicago", 0, 12));
        g.setColor(Color.red);
        if (this.latitude >= this.dec) {
            g.drawString("S", 253, this.y90 + 13);
            g.drawString("E", 140, this.y90 + 13);
            g.drawString("W", 363, this.y90 + 13);
        }
        else {
            g.drawString("N", 253, this.y90 + 13);
            g.drawString("W", 138, this.y90 + 13);
            g.drawString("E", 366, this.y90 + 13);
        }
        g.setFont(new Font("Helvetica", 0, 12));
        g.setColor(Color.red);
        if (this.UV) {
            g.drawString("The most intense sun light and the highest risk for sun damage today is between ", 30, this.top - 5);
            g.setFont(new Font("Helvetica", 1, 12));
            g.drawString(String.valueOf(this.rise45Str) + " and " + this.set45Str, 475, this.top - 5);
        }
        g.setFont(f);
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.hours -= this.locOffset;
        if (this.hours < 0) {
            this.hours += 24;
        }
        if (this.hours >= 24) {
            this.hours -= 24;
        }
        if (this.minutes > 9) {
            this.str = ":";
        }
        else {
            this.str = ":0";
        }
        this.str = String.valueOf(this.hours) + this.str + this.minutes;
        if (this.seconds > 9) {
            this.str = String.valueOf(this.str) + ":";
        }
        else {
            this.str = String.valueOf(this.str) + ":0";
        }
        this.str = String.valueOf(this.str) + this.seconds;
        g.setColor(Color.blue);
        this.gmtStr = this.str;
        String str = this.dat.toString();
        g.drawString("Subsolar point", 490, 385);
        g.setColor(Color.black);
        str = String.valueOf(Math.abs(Math.round(100.0 * this.currentDec) / 100.0));
        if (this.currentDec >= 0.0) {
            this.declinStr = String.valueOf(str) + " ° N";
        }
        else {
            this.declinStr = String.valueOf(str) + " ° S";
        }
        g.drawString("Latitude", 490, 400);
        g.drawString(this.declinStr, 560, 400);
        g.drawString("Longitude", 490, 415);
        g.drawString(this.ghaStr, 560, 415);
        g.setColor(Color.red);
        g.drawString("Location:  " + this.locStr, 490, this.top + 15);
        g.setColor(Color.black);
        g.drawString("Sunrise", 490, this.top + 55);
        g.drawString(this.riseStr, 555, this.top + 55);
        g.drawString("Culmination", 490, this.top + 70);
        g.drawString(String.valueOf(this.transStr) + "  " + this.culmHoeheStr, 555, this.top + 70);
        g.drawString("Sunset", 490, this.top + 85);
        g.drawString(this.setStr, 555, this.top + 85);
        g.setColor(Color.red);
        g.drawString("Curr. Elevation", 490, this.top + 100);
        if (this.currentElevation < 45.0) {
            g.setColor(Color.black);
        }
        g.drawString("   " + this.heightStr, 555, this.top + 100);
        g.setColor(Color.black);
        if (this.y0 - this.hSun < this.unten) {
            if (this.currentElevation >= -0.83) {
                g.setColor(Color.yellow);
            }
            else {
                g.setColor(Color.red);
            }
            g.fillOval(this.xSun - 9, this.y0 - this.hSun - 9, 18, 18);
            g.setColor(Color.red);
            g.drawOval(this.xSun - 9, this.y0 - this.hSun - 9, 18, 18);
        }
        else {
            g.setColor(Color.white);
            g.drawString("Sun", this.xSun - 10, this.unten - 10);
            g.drawString("V", this.xSun - 4, this.unten);
        }
        f = g.getFont();
        g.setFont(new Font("Helvetica", 0, 9));
        g.setColor(Color.black);
        for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
            if (this.hArray[i + this.locOffset] > 0) {
                g.setColor(Color.red);
                g.drawOval(this.xArray[i + this.locOffset] - 2, this.y0 - this.hArray[i + this.locOffset] - 2, 4, 4);
                g.setColor(Color.black);
                if (i % 2 == 0) {
                    g.drawString(String.valueOf(i + this.locOffset), this.xArray[i + this.locOffset] + 7, this.y0 - this.hArray[i + this.locOffset] + 3);
                }
            }
        }
        if (this.UV) {
            this.xM = 580;
            this.yM = 260;
            this.r = 50;
            final Color background = new Color(235, 235, 255);
            if (this.riseStr.equals("Visible all day")) {
                g.setColor(Color.red);
                g.fillOval(this.xM - this.r, this.yM - this.r, 2 * this.r, 2 * this.r);
                g.setColor(Color.yellow);
                g.fillArc(this.xM - this.r, this.yM - this.r, 2 * this.r, 2 * this.r, 270 - (int)(this.rise45 * 15.0), (int)(360.0 - 15.0 * (this.set45 - this.rise45)));
            }
            else {
                g.setColor(Color.red);
                g.fillOval(this.xM - this.r, this.yM - this.r, 2 * this.r, 2 * this.r);
                g.setColor(Color.yellow);
                g.fillArc(this.xM - this.r, this.yM - this.r, 2 * this.r, 2 * this.r, 270 - (int)(this.rise45 * 15.0), (int)(360.0 - 15.0 * (this.set45 - this.rise45)));
                g.setColor(Color.black);
                g.fillArc(this.xM - this.r, this.yM - this.r, 2 * this.r, 2 * this.r, 270 - (int)(this.rise * 15.0), (int)(360.0 - 15.0 * (this.set - this.rise)));
            }
            g.setFont(new Font("Helvetica", 0, 11));
            g.setColor(Color.red);
            g.drawString("6", this.xM - this.r - 9, this.yM + 5);
            g.drawString("12", this.xM - 5, this.yM - this.r - 3);
            g.drawString("18", this.xM + this.r + 3, this.yM + 5);
            this.x9 = (int)Math.round(this.r * Math.sin(0.7853981633974483));
            g.drawString("9", this.xM - this.x9 - 10, this.yM - this.x9 - 5);
            g.drawString("15", this.xM + this.x9 + 5, this.yM - this.x9 - 5);
            g.drawString("3", this.xM - this.x9 - 10, this.yM + this.x9 + 10);
            g.drawString("21", this.xM + this.x9 + 5, this.yM + this.x9 + 10);
            g.drawString("0", this.xM - 5, this.yM + this.r + 13);
            g.setColor(Color.green);
            this.winkel = 270.0 - 15.0 * (this.STD + this.locOffset);
            this.xE = (int)Math.round(this.r * Math.cos(0.017453292519943295 * this.winkel));
            this.yE = (int)Math.round(this.r * Math.sin(0.017453292519943295 * this.winkel));
            g.drawLine(this.xM, this.yM, this.xM + this.xE, this.yM - this.yE);
            if (Math.abs(Math.cos(0.017453292519943295 * this.winkel)) < 0.71) {
                g.drawLine(this.xM - 1, this.yM, this.xM + this.xE - 1, this.yM - this.yE);
                g.drawLine(this.xM + 1, this.yM, this.xM + this.xE + 1, this.yM - this.yE);
            }
            else {
                g.drawLine(this.xM, this.yM + 1, this.xM + this.xE, this.yM - this.yE + 1);
                g.drawLine(this.xM, this.yM - 1, this.xM + this.xE, this.yM - this.yE - 1);
            }
            g.setColor(Color.black);
            g.drawOval(this.xM - this.r, this.yM - this.r, 2 * this.r, 2 * this.r);
            this.winkel = 270.0 - 15.0 * (this.rise45 + this.set45) / 2.0;
            this.xE = (int)Math.round(this.r * Math.cos(0.017453292519943295 * this.winkel) / 2.0);
            this.yE = (int)Math.round(this.r * Math.sin(0.017453292519943295 * this.winkel) / 2.0);
            g.setFont(new Font("Helvetica", 0, 11));
            g.drawString(this.delta45Str, this.xM + this.xE - 10, this.yM - this.yE - 5);
        }
        g.setColor(Color.red);
        g.setFont(f);
    }
}
