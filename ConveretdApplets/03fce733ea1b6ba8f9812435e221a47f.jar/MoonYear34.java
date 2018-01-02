import java.awt.Color;
import java.awt.Font;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Component;
import java.awt.Button;
import java.awt.Choice;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MoonYear34 extends Applet
{
    String versStr;
    moonPhase myMoonPhase;
    double[] jde;
    public int locOffset;
    Date dat;
    int year;
    Choice yearChoice;
    String yearStr;
    double currentJD;
    double currentTime;
    double phase;
    double lunation;
    double[] newMoonT;
    double[] fullMoonT;
    double[] firstQuarterT;
    double[] lastQuarterT;
    final double K = 0.017453292519943295;
    double T0;
    int myDay;
    int myMonth;
    int myYear;
    double myHour;
    int currentDay;
    int currentMonth;
    int currentYear;
    boolean demo;
    public String email;
    public String param;
    public String wwwStr;
    public String myStr;
    public String userString;
    public String usrStr;
    String lang;
    boolean online;
    String timeString;
    String phaseString;
    Button writeButton;
    String[] dataStr;
    int kData;
    String[] monthArray;
    String illumStr;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 69856;
    }
    
    public void init() {
        this.dat = new Date();
        this.timeString = this.dat.toString();
        this.locOffset = -this.dat.getTimezoneOffset() / 60;
        this.year = this.dat.getYear() + 1900;
        this.currentTime = this.dat.getHours() + this.dat.getMinutes() / 60.0 + this.dat.getSeconds() / 3600.0;
        this.currentJD = this.JD(this.dat.getDate(), this.dat.getMonth() + 1, this.year, this.currentTime);
        this.currentYear = this.year;
        this.caldat(this.currentJD);
        this.currentDay = this.myDay;
        this.currentMonth = this.myMonth;
        this.currentYear = this.year;
        this.yearChoice = new Choice();
        for (int i = 0; i < 135; ++i) {
            this.yearStr = String.valueOf(this.year - 31 + i);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(this.year);
        this.yearChoice.select(this.yearStr);
        this.add(this.yearChoice);
        this.add(this.writeButton = new Button("Write..."));
        this.getMoonPhases();
        boolean ok = true;
        this.online = false;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.lang = this.getParameter("language");
        if (this.lang.equals("0")) {
            this.phaseString = "Click the Moon to get the age !";
        }
        else {
            this.phaseString = "Ein Mausklick zeigt das Mondalter";
        }
        final URL url = this.getDocumentBase();
        this.myStr = url.toString();
        this.myStr = String.valueOf(this.myStr) + "1234567890123456789012345";
        this.wwwStr = this.myStr.substring(0, 27);
        if (this.formula(this.wwwStr, 18) == this.formula("http://www.tyge.de", 18) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21)) {
            ok = true;
            this.online = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (!ok) {
            ok = true;
            this.usrStr = this.email;
            this.userString = this.email;
            if (this.email.length() == 0 || Integer.parseInt(this.param) != this.formula(this.email, this.email.length())) {
                ok = false;
            }
            else {
                ok = true;
                this.usrStr = this.email;
                this.userString = this.email;
                this.demo = false;
            }
            if (this.wwwStr.substring(0, 7).equals("http://")) {
                ok = false;
                this.demo = true;
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        String s = "";
        String str = "";
        int m = -1;
        int tag = 0;
        if (y > 41 && y < 543 && x > 20 && x < 850) {
            final double yy = (y - 10.0) / 41.0;
            final int yM = (int)Math.round(yy);
            if (y > 10 + yM * 41 - 14 && y < 10 + yM * 41 + 14) {
                m = yM - 1;
            }
            final double xx = (x - 20.0) / 25.0;
            final int xM = (int)Math.round(xx);
            if (xM <= this.daysInMonth(m, this.year) && m >= 0 && m < 12) {
                tag = xM;
                final Date theDate = new Date();
                final double UT = 12.0 - this.locOffset;
                final double jd = this.JD(tag, m + 1, this.year, 0.0);
                double NM = 0.0;
                int nm = 0;
                if (m == 0) {
                    if (this.T0 < jd) {
                        this.phase = jd - this.T0;
                        this.lunation = this.newMoonT[0] - this.T0;
                        NM = this.T0;
                    }
                    if (this.newMoonT[0] < jd) {
                        this.phase = jd - this.newMoonT[0];
                        this.lunation = this.newMoonT[1] - this.newMoonT[0];
                        NM = this.newMoonT[0];
                    }
                    if (this.newMoonT[1] < jd) {
                        this.phase = jd - this.newMoonT[1];
                        this.lunation = this.newMoonT[2] - this.newMoonT[1];
                        NM = this.newMoonT[1];
                    }
                }
                else {
                    if (this.newMoonT[m - 1] - this.locOffset / 24.0 < jd) {
                        this.phase = jd - this.newMoonT[m - 1] + this.locOffset / 24.0;
                        NM = this.newMoonT[m - 1];
                        nm = 1;
                    }
                    if (this.newMoonT[m] - this.locOffset / 24.0 < jd) {
                        this.phase = jd - this.newMoonT[m] + this.locOffset / 24.0;
                        this.lunation = this.newMoonT[m + 1] - this.newMoonT[m];
                        NM = this.newMoonT[m];
                        nm = 2;
                    }
                    if (this.newMoonT[m + 1] - this.locOffset / 24.0 < jd) {
                        this.phase = jd - this.newMoonT[m + 1] + this.locOffset / 24.0;
                        this.lunation = this.newMoonT[m + 2] - this.newMoonT[m + 1];
                        NM = this.newMoonT[m + 1];
                        nm = 3;
                    }
                    if (this.newMoonT[m + 2] - this.locOffset / 24.0 < jd) {
                        this.phase = jd - this.newMoonT[m + 2] + this.locOffset / 24.0;
                        this.lunation = this.newMoonT[m + 3] - this.newMoonT[m + 2];
                        NM = this.newMoonT[m + 1];
                        nm = 4;
                    }
                }
                final double localTime = UT + this.locOffset;
                str = (int)localTime + ":";
                if (Math.round((localTime - (int)localTime) * 60.0) < 10L) {
                    str = String.valueOf(str) + "0" + Math.round((localTime - (int)localTime) * 60.0);
                }
                else {
                    str = String.valueOf(str) + Math.round((localTime - (int)localTime) * 60.0);
                }
                str = "0";
                s = String.valueOf(Math.round(100.0 * this.phase) / 100.0);
                if (this.lang.equals("0")) {
                    this.phaseString = String.valueOf(this.year) + " " + this.myMoonPhase.monthString(m + 1) + " " + tag + " at " + str + " UT: age " + s + " d  Lunation: " + Math.round(100.0 * this.lunation) / 100.0 + " d";
                }
                else {
                    this.phaseString = String.valueOf(this.year) + ", " + this.myMoonPhase.monthString(m + 1) + " " + tag + " at " + str + " UT: age " + s + " d   Lunation: " + Math.round(100.0 * this.lunation) / 100.0 + " d";
                }
                final double ph1 = this.phase((this.JD(tag, m + 1, this.year, 0.0) - 2451545.0 - 0.5) / 36525.0);
                final double ph2 = this.phase((this.JD(tag, m + 1, this.year, 0.0) - 2451545.0) / 36525.0);
                final double ph3 = this.phase((this.JD(tag, m + 1, this.year, 0.0) - 2451545.0 + 0.5) / 36525.0);
                String delta = "";
                if (ph2 > ph1 && ph2 < ph3) {
                    delta = "(+)";
                }
                if (ph2 < ph1 && ph2 > ph3) {
                    delta = "(-)";
                }
                this.illumStr = "Illum. Frac. " + Math.round(1000.0 * ph2) / 10.0 + "% " + delta;
                this.repaint();
            }
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int code) {
        if (code == 121) {
            this.dat.setYear(this.year + 1);
            ++this.year;
            this.yearStr = String.valueOf(this.year);
            this.yearChoice.select(this.yearStr);
            this.currentTime = this.dat.getHours() + this.dat.getMinutes() / 60.0 + this.dat.getSeconds() / 3600.0;
            this.currentJD = this.JD(this.dat.getDate(), this.dat.getMonth() + 1, this.dat.getYear() + 1900, this.currentTime);
            this.getMoonPhases();
            if (this.lang.equals("0")) {
                this.phaseString = "Click the Moon to get the age !";
            }
            else {
                this.phaseString = "Ein Mausklick zeigt das Mondalter";
            }
            this.repaint();
            return true;
        }
        if (code == 89) {
            this.dat.setYear(this.year - 1);
            --this.year;
            this.yearStr = String.valueOf(this.year);
            this.yearChoice.select(this.yearStr);
            this.currentTime = this.dat.getHours() + this.dat.getMinutes() / 60.0 + this.dat.getSeconds() / 3600.0;
            this.currentJD = this.JD(this.dat.getDate(), this.dat.getMonth() + 1, this.dat.getYear() + 1900, this.currentTime);
            this.getMoonPhases();
            if (this.lang.equals("0")) {
                this.phaseString = "Click the Moon to get the age !";
            }
            else {
                this.phaseString = "Ein Mausklick zeigt das Mondalter";
            }
            this.repaint();
            return true;
        }
        return true;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (!(event.target instanceof Choice)) {
            if (event.target instanceof Button) {
                final scrollFrame sf = new scrollFrame("Moon " + this.year, this.kData, this.dataStr, this.online);
                sf.resize(280, 400);
                sf.show();
            }
            return true;
        }
        if (this.demo) {
            return true;
        }
        this.illumStr = "";
        this.yearStr = this.yearChoice.getSelectedItem();
        this.year = Integer.parseInt(this.yearStr);
        this.yearChoice.select(this.yearStr);
        this.currentTime = this.dat.getHours() + this.dat.getMinutes() / 60.0 + this.dat.getSeconds() / 3600.0;
        this.currentJD = this.JD(this.dat.getDate(), this.dat.getMonth() + 1, this.dat.getYear() + 1900, this.currentTime);
        this.getMoonPhases();
        if (this.lang.equals("0")) {
            this.phaseString = "Click the Moon to get the age !";
        }
        else {
            this.phaseString = "Ein Mausklick zeigt das Mondalter";
        }
        this.repaint();
        return true;
    }
    
    public double JD(final int date, int month, int year, final double UT) {
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
    
    public int daysInMonth(final int m, final int y) {
        int n = 31;
        if (m == 0 || m == 2 || m == 4 || m == 6 || m == 7 || m == 9 || m == 11) {
            n = 31;
        }
        if (m == 3 || m == 5 || m == 8 || m == 10) {
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
    
    public void caldat(final double JD) {
        final double JD2 = (int)(JD + 0.5);
        final int B = (int)((JD2 - 1867216.25) / 36524.25);
        final double C = JD2 + B - B / 4 + 1525.0;
        final int D = (int)((C - 122.1) / 365.25);
        final double E = 365.0 * D + D / 4;
        final int F = (int)((C - E) / 30.6001);
        final int day = (int)(C - E + 0.5) - (int)(30.6001 * F);
        final int month = F - 1 - 12 * (F / 14);
        final int year = D - 4715 - (7 + month) / 10;
        final double hour = 24.0 * (JD + 0.5 - JD2);
        this.myDay = day;
        this.myMonth = month;
        this.myYear = year;
        this.myHour = hour;
    }
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public String caldatStr(final double JD) {
        String str = "";
        final double JD2 = (int)(JD + 0.5);
        final int B = (int)((JD2 - 1867216.25) / 36524.25);
        final double C = JD2 + B - B / 4 + 1525.0;
        final int D = (int)((C - 122.1) / 365.25);
        final double E = 365.0 * D + D / 4;
        final int F = (int)((C - E) / 30.6001);
        final int day = (int)(C - E + 0.5) - (int)(30.6001 * F);
        final int month = F - 1 - 12 * (F / 14);
        final int year = D - 4715 - (7 + month) / 10;
        final double hour = 24.0 * (JD + 0.5 - JD2);
        if (day > 9) {
            str = String.valueOf(year) + "  " + this.monthArray[month - 1] + " " + day + " " + "  at " + this.HM(hour);
        }
        else {
            str = String.valueOf(year) + "  " + this.monthArray[month - 1] + " 0" + day + " " + "  at " + this.HM(hour);
        }
        return str;
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
        str = String.valueOf(str) + HOUR + " h ";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + MIN + " m ";
        return str;
    }
    
    public void getMoonPhases() {
        this.myMoonPhase = new moonPhase(this.locOffset, this.year - 0.0833);
        this.T0 = this.myMoonPhase.newMoonJD();
        this.dataStr[0] = "MoonYear" + this.versStr + "\n" + "© 2000-2010 J.Giesen - www.GeoAstro.de" + "\n" + "\n";
        this.kData = 1;
        for (int i = 0; i < 15; ++i) {
            this.myMoonPhase = new moonPhase(this.locOffset, this.year - 0.016666666666666666 + i * 0.075);
            this.newMoonT[i] = this.myMoonPhase.newMoonJD() + this.locOffset / 24.0;
            this.fullMoonT[i] = this.myMoonPhase.fullMoonJD() + this.locOffset / 24.0;
            this.firstQuarterT[i] = this.myMoonPhase.firstQuarterJD() + this.locOffset / 24.0;
            this.lastQuarterT[i] = this.myMoonPhase.lastQuarterJD() + this.locOffset / 24.0;
            this.dataStr[this.kData] = "New   " + this.caldatStr(this.newMoonT[i]) + "\n";
            ++this.kData;
            this.dataStr[this.kData] = "First " + this.caldatStr(this.firstQuarterT[i]) + "\n";
            ++this.kData;
            this.dataStr[this.kData] = "Full  " + this.caldatStr(this.fullMoonT[i]) + "\n";
            ++this.kData;
            this.dataStr[this.kData] = "Last  " + this.caldatStr(this.lastQuarterT[i]) + "\n";
            ++this.kData;
        }
    }
    
    public int weekday(final double JD) {
        int num = (int)JD;
        num = num - num / 7 * 7 + 1;
        return num;
    }
    
    double phase(final double T) {
        final double D = 297.8502042 + 445267.1115168 * T - 0.00163 * T * T + T * T * T / 545868.0 - T * T * T * T / 1.13065E8;
        final double M = 357.5291092 + 35999.0502909 * T - 1.536E-4 * T * T + T * T * T / 2.449E7;
        final double Ms = 134.9634114 + 477198.8676313 * T + 0.008997 * T * T + T * T * T / 69699.0 - T * T * T * T / 1.4712E7;
        final double i = 180.0 - D - 6.289 * Math.sin(0.017453292519943295 * Ms) + 2.1 * Math.sin(0.017453292519943295 * M) - 1.274 * Math.sin(0.017453292519943295 * (2.0 * D - Ms)) - 0.658 * Math.sin(0.03490658503988659 * D) - 0.214 * Math.sin(0.03490658503988659 * Ms) - 0.11 * Math.sin(0.017453292519943295 * D);
        return 0.5 * (1.0 + Math.cos(0.017453292519943295 * i));
    }
    
    public void paint(final Graphics g) {
        final RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHints(renderHints);
        final int left = 20;
        g.setFont(new Font("Courier", 0, 10));
        String str = "";
        String delta = "";
        final double ph1 = this.phase((this.currentJD - 2451545.0 - 0.5) / 36525.0);
        final double ph2 = this.phase((this.currentJD - 2451545.0) / 36525.0);
        final double ph3 = this.phase((this.currentJD - 2451545.0 + 0.5) / 36525.0);
        if (ph2 > ph1 && ph2 < ph3) {
            delta = "(+)";
        }
        if (ph2 < ph1 && ph2 > ph3) {
            delta = "(-)";
        }
        str = ", Illum. Frac. " + Math.round(1000.0 * ph2) / 10.0 + "% " + delta;
        this.lunation = 29.53;
        double jd = 0.0;
        final int moonRadius = 12;
        final int deltaX = 25;
        int xMoon = 0;
        int yMoon = 20;
        final int dLun = 0;
        g.drawString(String.valueOf(this.timeString) + str, 20, 20);
        g.drawString(this.phaseString, 520, 20);
        g.drawString(this.illumStr, 640, 35);
        for (int m = 0; m < 12; ++m) {
            yMoon += 41;
            xMoon = 20;
            if (m + 1 == this.currentMonth && this.year == this.currentYear) {
                g.setColor(Color.red);
            }
            else {
                g.setColor(Color.black);
            }
            g.setFont(new Font("Courier", 0, 10));
            g.drawString(this.myMoonPhase.monthString(m + 1), 8, yMoon + 4);
            g.setFont(new Font("Courier", 0, 9));
            for (int d = 1; d < this.daysInMonth(m, this.year) + 1; ++d) {
                xMoon += deltaX;
                jd = this.JD(d, m + 1, this.year, 12.0 - this.locOffset);
                if (m == 0) {
                    if (this.T0 < jd) {
                        this.phase = jd - this.T0;
                        this.lunation = this.newMoonT[0] - this.T0;
                    }
                    if (this.newMoonT[0] < jd) {
                        this.phase = jd - this.newMoonT[0];
                        this.lunation = this.newMoonT[1] - this.newMoonT[0];
                    }
                    if (this.newMoonT[1] < jd) {
                        this.phase = jd - this.newMoonT[1];
                        this.lunation = this.newMoonT[2] - this.newMoonT[1];
                    }
                }
                else {
                    if (this.newMoonT[m - 1] < jd) {
                        this.phase = jd - this.newMoonT[m - 1];
                    }
                    if (this.newMoonT[m] < jd) {
                        this.phase = jd - this.newMoonT[m];
                        this.lunation = this.newMoonT[m + 1] - this.newMoonT[m];
                    }
                    if (this.newMoonT[m + 1] < jd) {
                        this.phase = jd - this.newMoonT[m + 1];
                        this.lunation = this.newMoonT[m + 2] - this.newMoonT[m + 1];
                    }
                    if (this.newMoonT[m + 2] < jd) {
                        this.phase = jd - this.newMoonT[m + 2];
                        this.lunation = this.newMoonT[m + 3] - this.newMoonT[m + 2];
                    }
                }
                if (d == this.currentDay && m + 1 == this.currentMonth && this.year == this.currentYear) {
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.gray);
                }
                g.fillRect(xMoon - 13, yMoon - 14, 28, 28);
                if (this.weekday(jd) == 6) {
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.black);
                }
                g.drawString(String.valueOf(d), xMoon - 4, yMoon + 24);
                this.phase /= 29.53;
                final double w = this.phase * 4.0;
                g.setColor(Color.white);
                g.fillOval(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius);
                g.setColor(Color.black);
                if (this.phase <= 0.25) {
                    for (int i = -moonRadius; i <= moonRadius; ++i) {
                        final double x1 = Math.sqrt(moonRadius * moonRadius - i * i);
                        final int X1 = xMoon - (int)Math.round(x1);
                        final double x2 = (1.0 - w) * moonRadius * Math.sqrt(1.0 - i * i / (moonRadius * moonRadius));
                        final int X2 = xMoon + (int)Math.round(x2);
                        g.drawLine(X1, yMoon - i, X2, yMoon - i);
                    }
                }
                if (this.phase > 0.25 && this.phase <= 0.5) {
                    for (int i = -moonRadius; i <= moonRadius; ++i) {
                        final double x1 = Math.sqrt(moonRadius * moonRadius - i * i);
                        final int X1 = xMoon - (int)Math.round(x1);
                        final double x2 = (w - 1.0) * moonRadius * Math.sqrt(1.0 - i * i / (moonRadius * moonRadius));
                        final int X2 = xMoon - (int)Math.round(x2);
                        g.drawLine(X1, yMoon - i, X2, yMoon - i);
                    }
                }
                if (this.phase > 0.5 && this.phase <= 0.75) {
                    for (int i = -moonRadius; i <= moonRadius; ++i) {
                        final double x1 = Math.sqrt(moonRadius * moonRadius - i * i);
                        final int X1 = xMoon + (int)Math.floor(x1);
                        final double x2 = x1 - (w - 2.0) * moonRadius * Math.sqrt(1.0 - i * i / (moonRadius * moonRadius));
                        final int X2 = xMoon + (int)Math.floor(x2);
                        g.drawLine(X1, yMoon - i, X2, yMoon - i);
                    }
                }
                if (this.phase > 0.75) {
                    for (int i = -moonRadius; i <= moonRadius; ++i) {
                        final double x1 = Math.sqrt(moonRadius * moonRadius - i * i);
                        final int X1 = xMoon + (int)Math.floor(x1);
                        final double x2 = (w - 3.0) * moonRadius * Math.sqrt(1.0 - i * i / (moonRadius * moonRadius));
                        final int X2 = xMoon - (int)Math.floor(x2);
                        g.drawLine(X1, yMoon - i, X2, yMoon - i);
                    }
                }
                g.setColor(Color.black);
                g.drawOval(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius);
            }
        }
        g.setColor(Color.black);
        g.setFont(new Font("Courier", 0, 10));
        g.drawString("MoonYear" + this.versStr + " - © 2000-2010 J.Giesen - www.GeoAstro.de", 40, yMoon + 40);
        String text;
        if (this.lang.equals("0")) {
            text = "New Moon:";
        }
        else {
            text = "Neumond:";
        }
        System.out.println("MoonYear" + this.versStr + " - © 2000-2010 J.Giesen - www.GeoAstro.de");
        System.out.println(text);
        int mm = 0;
        for (int j = 0; j < 14; ++j) {
            this.caldat(this.newMoonT[j]);
            String dStr;
            if (this.myDay < 10) {
                dStr = "0" + this.myDay;
            }
            else {
                dStr = String.valueOf(this.myDay);
            }
            if (this.myHour < 10.0) {
                str = "0" + (int)this.myHour + ":";
            }
            else {
                str = String.valueOf((int)this.myHour) + ":";
            }
            if ((this.myHour - (int)this.myHour) * 60.0 < 10.0) {
                str = String.valueOf(str) + "0" + Math.round((this.myHour - (int)this.myHour) * 60.0);
            }
            else {
                str = String.valueOf(str) + Math.round((this.myHour - (int)this.myHour) * 60.0);
            }
            jd = this.JD(this.myDay, this.myMonth, this.myYear, 12.0 - this.locOffset);
            mm = this.myMonth - 1;
            if (mm == 0) {
                if (this.T0 < jd) {
                    this.lunation = this.newMoonT[0] - this.T0;
                }
                if (this.newMoonT[0] < jd) {
                    this.lunation = this.newMoonT[1] - this.newMoonT[0];
                }
                if (this.newMoonT[1] < jd) {
                    this.lunation = this.newMoonT[2] - this.newMoonT[1];
                }
            }
            else {
                if (this.newMoonT[mm] - this.locOffset / 24.0 < jd) {
                    this.lunation = this.newMoonT[mm + 1] - this.newMoonT[mm];
                }
                if (this.newMoonT[mm + 1] - this.locOffset / 24.0 < jd) {
                    this.lunation = this.newMoonT[mm + 2] - this.newMoonT[mm + 1];
                }
                if (this.newMoonT[mm + 2] - this.locOffset / 24.0 < jd) {
                    this.lunation = this.newMoonT[mm + 3] - this.newMoonT[mm + 2];
                }
            }
            System.out.println(String.valueOf(this.myYear) + "  " + this.myMoonPhase.monthString(this.myMonth) + "  " + dStr + "   " + str + "   Lunation " + Math.round(100.0 * this.lunation) / 100.0);
            xMoon = 0;
            yMoon = 20;
            for (int k = 0; k < 12; ++k) {
                yMoon += 41;
                xMoon = 20;
                for (int d2 = 1; d2 < this.daysInMonth(k, this.year) + 1; ++d2) {
                    xMoon += deltaX;
                    if (k + 1 == this.myMonth && this.myDay == d2 && this.myYear == this.year) {
                        g.setColor(Color.blue);
                        g.fillOval(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius);
                        jd = this.JD(this.myDay, this.myMonth, this.year, this.myHour) + 0.5;
                        if (this.weekday(jd) == 6) {
                            g.setColor(Color.red);
                        }
                        else {
                            g.setColor(Color.black);
                        }
                        g.clearRect(xMoon - 11, yMoon + 14, 20, 12);
                        if (this.myHour < 10.0) {
                            str = "0" + (int)this.myHour + ":";
                        }
                        else {
                            str = String.valueOf((int)this.myHour) + ":";
                        }
                        if ((this.myHour - (int)this.myHour) * 60.0 < 10.0) {
                            str = String.valueOf(str) + "0" + Math.round((this.myHour - (int)this.myHour) * 60.0);
                        }
                        else {
                            str = String.valueOf(str) + Math.round((this.myHour - (int)this.myHour) * 60.0);
                        }
                        g.drawString(str, xMoon - 15, yMoon + 24);
                    }
                    g.setColor(Color.black);
                    g.drawOval(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius);
                }
            }
        }
        for (int l = 0; l < 14; ++l) {
            this.caldat(this.fullMoonT[l]);
            xMoon = 0;
            yMoon = 20;
            for (int m2 = 0; m2 < 12; ++m2) {
                yMoon += 41;
                xMoon = 20;
                for (int d3 = 1; d3 < this.daysInMonth(m2, this.year) + 1; ++d3) {
                    xMoon += deltaX;
                    if (m2 + 1 == this.myMonth && this.myDay == d3 && this.myYear == this.year) {
                        g.setColor(Color.red);
                        g.fillOval(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius);
                        final double fjd = this.JD(this.myDay, this.myMonth, this.myYear, this.myHour) + 0.5;
                        if (this.weekday(fjd) == 6) {
                            g.setColor(Color.red);
                        }
                        else {
                            g.setColor(Color.black);
                        }
                        g.clearRect(xMoon - 11, yMoon + 14, 20, 12);
                        if (this.myHour < 10.0) {
                            str = "0" + (int)this.myHour + ":";
                        }
                        else {
                            str = String.valueOf((int)this.myHour) + ":";
                        }
                        if ((this.myHour - (int)this.myHour) * 60.0 < 10.0) {
                            str = String.valueOf(str) + "0" + Math.round((this.myHour - (int)this.myHour) * 60.0);
                        }
                        else {
                            str = String.valueOf(str) + Math.round((this.myHour - (int)this.myHour) * 60.0);
                        }
                        g.drawString(str, xMoon - 15, yMoon + 24);
                    }
                    g.setColor(Color.black);
                    g.drawOval(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius);
                }
            }
        }
        for (int i2 = 0; i2 < 14; ++i2) {
            this.caldat(this.firstQuarterT[i2]);
            xMoon = 0;
            yMoon = 20;
            for (int m3 = 0; m3 < 12; ++m3) {
                yMoon += 41;
                xMoon = 20;
                for (int d4 = 1; d4 < this.daysInMonth(m3, this.year) + 1; ++d4) {
                    xMoon += deltaX;
                    if (m3 + 1 == this.myMonth && this.myDay == d4 && this.myYear == this.year) {
                        g.setColor(Color.red);
                        g.drawLine(xMoon, yMoon - moonRadius + 1, xMoon, yMoon + moonRadius - 1);
                        g.drawLine(xMoon - 1, yMoon - moonRadius + 1, xMoon - 1, yMoon + moonRadius - 1);
                        g.drawLine(xMoon + 1, yMoon - moonRadius + 1, xMoon + 1, yMoon + moonRadius - 1);
                    }
                }
            }
        }
        for (int i3 = 0; i3 < 14; ++i3) {
            this.caldat(this.lastQuarterT[i3]);
            xMoon = 0;
            yMoon = 20;
            for (int m4 = 0; m4 < 12; ++m4) {
                yMoon += 41;
                xMoon = 20;
                for (int d5 = 1; d5 < this.daysInMonth(m4, this.year) + 1; ++d5) {
                    xMoon += deltaX;
                    if (m4 + 1 == this.myMonth && this.myDay == d5 && this.myYear == this.year) {
                        g.setColor(Color.blue);
                        g.drawLine(xMoon, yMoon - moonRadius + 1, xMoon, yMoon + moonRadius - 1);
                        g.drawLine(xMoon - 1, yMoon - moonRadius + 1, xMoon - 1, yMoon + moonRadius - 1);
                        g.drawLine(xMoon + 1, yMoon - moonRadius + 1, xMoon + 1, yMoon + moonRadius - 1);
                    }
                }
            }
        }
        if (this.online) {
            this.userString = "running online";
        }
        if (this.demo) {
            this.userString = "demo running";
        }
        g.setColor(Color.black);
        g.drawString(this.userString, 400, yMoon + 40);
        if (this.demo) {
            final Font f = g.getFont();
            g.setFont(new Font("Chicago", 0, 192));
            g.setColor(Color.red);
            g.drawString("D E M O", 50, 300);
            g.setFont(new Font("Chicago", 0, 32));
            g.drawString("year menu disabled", 250, 400);
            g.setFont(f);
        }
        if (this.online) {
            final Font f = g.getFont();
            g.setFont(new Font("Chicago", 0, 60));
            g.setColor(Color.red);
            g.drawString("O N L I N E", 300, 300);
            g.setFont(f);
        }
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
    }
    
    public MoonYear34() {
        this.versStr = " 3.4 ";
        this.jde = new double[50];
        this.newMoonT = new double[15];
        this.fullMoonT = new double[15];
        this.firstQuarterT = new double[15];
        this.lastQuarterT = new double[15];
        this.myDay = 0;
        this.myMonth = 0;
        this.myYear = 0;
        this.currentDay = 0;
        this.currentMonth = 0;
        this.demo = true;
        this.online = false;
        this.dataStr = new String[77];
        this.kData = 0;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.illumStr = "";
    }
}
