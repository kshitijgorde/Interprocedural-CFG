import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
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

public class MoonPhase33 extends Applet
{
    String versStr;
    moonPhase myMoonPhase;
    String[] Str;
    double[] jde;
    int locOffset;
    Date dat;
    int year;
    Choice yearChoice;
    String yearStr;
    double currentJD;
    double currentTime;
    int currentYear;
    int count;
    double phase;
    double lunation;
    int n;
    String myStr;
    boolean demo;
    boolean online;
    public String email;
    public String param;
    public String wwwStr;
    public String usrStr;
    public String userString;
    String[] monthArray;
    double[] T;
    boolean isDST;
    String[] dataStr;
    Button writeButton;
    scrollFrame sf;
    int kStart;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 6521;
    }
    
    public void init() {
        System.out.println("MoonPhase " + this.versStr + "  -  (c) 1999-2007 J.Giesen  -  www.GeoAstro.de");
        final URL url = this.getDocumentBase();
        this.myStr = url.toString();
        this.myStr = String.valueOf(this.myStr) + "1234567890123456789012345";
        this.wwwStr = this.myStr.substring(0, 27);
        this.dat = new Date();
        this.year = this.dat.getYear() + 1900;
        this.currentTime = this.dat.getHours() + this.dat.getMinutes() / 60.0 + this.dat.getSeconds() / 3600.0;
        this.currentJD = this.JD(this.dat.getDate(), this.dat.getMonth() + 1, this.year, this.currentTime);
        this.currentYear = this.year;
        this.yearChoice = new Choice();
        for (int i = 0; i < 82; ++i) {
            this.yearStr = String.valueOf(this.year - 31 + i);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(this.year);
        this.yearChoice.select(this.yearStr);
        this.add(this.yearChoice);
        this.add(this.writeButton = new Button("write"));
        final Date theDate = new Date();
        theDate.setDate(1);
        theDate.setMonth(0);
        final int offset1 = -theDate.getTimezoneOffset() / 60;
        theDate.setDate(1);
        theDate.setMonth(7);
        final int offset2 = -theDate.getTimezoneOffset() / 60;
        if (offset2 > offset1) {
            this.locOffset = offset1;
        }
        else {
            this.locOffset = offset2;
        }
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        this.userString = String.valueOf(this.email) + "  " + this.dat.toString();
        if (this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21)) {
            ok = true;
            this.online = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (!ok) {
            ok = true;
            if (this.email.length() == 0 || Integer.parseInt(this.param) != this.formula(this.email, this.email.length())) {
                ok = false;
            }
            else {
                ok = true;
                this.demo = false;
            }
            if (this.wwwStr.substring(0, 7).equals("http://")) {
                ok = false;
                this.demo = true;
            }
        }
        if (this.demo) {
            this.versStr = String.valueOf(this.versStr) + " DEMO";
        }
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Choice && event.target == this.yearChoice) {
            this.dat = new Date();
            this.yearStr = this.yearChoice.getSelectedItem();
            this.year = Integer.parseInt(this.yearStr);
            this.yearChoice.select(this.yearStr);
            this.dat.setYear(this.year - 1900);
            this.currentTime = this.dat.getHours() + this.dat.getMinutes() / 60.0 + this.dat.getSeconds() / 3600.0;
            this.currentJD = this.JD(this.dat.getDate(), this.dat.getMonth() + 1, this.year, this.currentTime);
            this.repaint();
        }
        if (event.target instanceof Button && event.target == this.writeButton) {
            (this.sf = new scrollFrame("Moon Phases " + this.year, this.count + 2, this.dataStr, this.demo, this.online)).resize(400, 690);
            this.sf.show();
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int code) {
        if (code == 121) {
            ++this.year;
            this.yearStr = String.valueOf(this.year);
            this.yearChoice.select(this.yearStr);
            this.repaint();
        }
        if (code == 89) {
            --this.year;
            this.yearStr = String.valueOf(this.year);
            this.yearChoice.select(this.yearStr);
            this.repaint();
        }
        return true;
    }
    
    public double JD(final int date, int month, int year, final double STD) {
        double A = 10000.0 * year + 100.0 * month + date;
        if (month <= 2) {
            month += 12;
            --year;
        }
        final double B = year / 400 - year / 100 + year / 4;
        A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + STD / 24.0;
        return MJD + 2400000.5;
    }
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public int caldat(final int what, final double JD) {
        final int Z = (int)(JD + 0.5);
        final double F = this.frac(JD + 0.5);
        int A;
        if (Z < 2299161) {
            A = Z;
        }
        else {
            final int a = (int)((Z - 1867216.25) / 36524.25);
            A = Z + 1 + a - (int)(a / 4.0);
        }
        final int B = A + 1524;
        final int C = (int)((B - 122.1) / 365.25);
        final int D = (int)(365.25 * C);
        final int E = (int)((B - D) / 30.6001);
        final double DAY = B - D - (int)(30.6001 * E) + F;
        final int day = (int)DAY;
        int month;
        if (E < 14) {
            month = E - 1;
        }
        else {
            month = E - 13;
        }
        if (what == 1) {
            return day;
        }
        return month;
    }
    
    public int calYear(final double JD) {
        final double JD2 = (int)(JD + 0.5);
        final int B = (int)((JD2 - 1867216.25) / 36524.25);
        final double C = JD2 + B - B / 4 + 1525.0;
        final int D = (int)((C - 122.1) / 365.25);
        final double E = 365.0 * D + D / 4;
        final int F = (int)((C - E) / 30.6001);
        final int month = F - 1 - 12 * (F / 14);
        return D - 4715 - (7 + month) / 10;
    }
    
    public Color myColor(final String str) {
        if (str.indexOf("date") > 0) {
            final Color myColor;
            return myColor = Color.red;
        }
        if (str.indexOf("Jan") > 0) {
            return Color.gray;
        }
        if (str.indexOf("Feb") > 0) {
            return Color.blue;
        }
        if (str.indexOf("Mar") > 0) {
            return Color.cyan;
        }
        if (str.indexOf("Apr") > 0) {
            return Color.green;
        }
        if (str.indexOf("May") > 0) {
            return Color.magenta;
        }
        if (str.indexOf("Jun") > 0) {
            return Color.orange;
        }
        if (str.indexOf("Jul") > 0) {
            return Color.pink;
        }
        if (str.indexOf("Aug") > 0) {
            return Color.red;
        }
        if (str.indexOf("Sep") > 0) {
            return Color.yellow;
        }
        if (str.indexOf("Oct") > 0) {
            return Color.gray;
        }
        if (str.indexOf("Nov") > 0) {
            return Color.blue;
        }
        if (str.indexOf("Dec") > 0) {
            return Color.cyan;
        }
        return Color.red;
    }
    
    public void getMoonPhases() {
        String s = "";
        int j = 0;
        int nNewMoon = 0;
        final double YEAR = this.year - 0.1;
        this.kStart = (int)Math.round((YEAR - 2000.0) * 12.3685);
        for (int i = 0; i < 15; ++i) {
            this.myMoonPhase = new moonPhase(this.locOffset, this.demo, this.kStart + i);
            this.Str[j] = this.myMoonPhase.newMoonStr();
            this.jde[j] = this.myMoonPhase.jdeTime();
            this.T[i] = this.myMoonPhase.newMoonJD();
            if (i > 0) {
                s = "  " + Math.round(1000.0 * (this.T[i] - this.T[i - 1])) / 1000.0 + "d  (";
                double Day = this.T[i] - this.T[i - 1];
                Day -= 29.530589;
                final double Hour = (Day - (int)Day) * 24.0;
                final double Min = (Hour - (int)Hour) * 60.0;
                if (Day >= 0.0) {
                    s = String.valueOf(s) + "+" + (int)Hour + "h ";
                }
                else {
                    s = String.valueOf(s) + "-" + Math.abs((int)Hour) + "h ";
                }
                s = String.valueOf(s) + Math.abs(Math.round(Min)) + "min)";
            }
            if (i > 0) {
                this.Str[j] = String.valueOf(this.Str[j]) + s;
            }
            if (this.T[i] < this.currentJD) {
                this.phase = this.currentJD - this.myMoonPhase.newMoonJD();
                nNewMoon = i;
            }
            this.Str[j + 1] = this.myMoonPhase.firstQuarterStr();
            this.jde[j + 1] = this.myMoonPhase.jdeTime();
            this.Str[j + 2] = this.myMoonPhase.fullMoonStr();
            this.jde[j + 2] = this.myMoonPhase.jdeTime();
            this.Str[j + 3] = this.myMoonPhase.lastQuarterStr();
            this.jde[j + 3] = this.myMoonPhase.jdeTime();
            j += 4;
        }
        this.count = j;
    }
    
    public void paint(final Graphics g) {
        int oben = 70;
        final int left = 20;
        g.setFont(new Font("Courier", 0, 10));
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        this.getMoonPhases();
        this.dataStr[0] = String.valueOf(this.versStr) + "  -  © 1999-2007 J. Giesen  -  www.GeoAstro.de" + "\n";
        String s;
        if (this.locOffset > 0) {
            s = "Time Zone: UT +" + this.locOffset + " h";
        }
        else {
            s = "Time Zone: UT " + this.locOffset + " h";
        }
        g.drawString(s, 15, 18);
        g.drawString("DST discarded", 15, 33);
        final String[] dataStr = this.dataStr;
        final int n2 = 0;
        dataStr[n2] = String.valueOf(dataStr[n2]) + s + ", DST discarded" + "\n";
        int n = 0;
        final double jdeStart = 0.0;
        double jdeStop = 0.0;
        g.setColor(Color.black);
        double minR = 500000.0;
        double maxR = 0.0;
        double jdMin = 0.0;
        double jdMax = 0.0;
        for (int i = 0; i < this.count; ++i) {
            if (this.jde[i] < this.JD(1, 1, this.year, 0.0)) {
                n = i;
            }
            if (this.calYear(this.jde[i]) == this.year) {
                g.setColor(Color.darkGray);
                if (this.Str[i].indexOf("Jan") > 0 && this.Str[i + 1].indexOf("Feb") > 0) {
                    g.drawLine(left, oben + 2, left + 220, oben + 2);
                }
                if (this.Str[i].indexOf("Feb") > 0 && this.Str[i + 1].indexOf("Mar") > 0) {
                    g.drawLine(left, oben + 2, left + 220, oben + 2);
                }
                if (this.Str[i].indexOf("Mar") > 0 && this.Str[i + 1].indexOf("Apr") > 0) {
                    g.drawLine(left, oben + 2, left + 220, oben + 2);
                }
                if (this.Str[i].indexOf("Apr") > 0 && this.Str[i + 1].indexOf("May") > 0) {
                    g.drawLine(left, oben + 2, left + 220, oben + 2);
                }
                if (this.Str[i].indexOf("May") > 0 && this.Str[i + 1].indexOf("Jun") > 0) {
                    g.drawLine(left, oben + 2, left + 220, oben + 2);
                }
                if (this.Str[i].indexOf("Jun") > 0 && this.Str[i + 1].indexOf("Jul") > 0) {
                    g.drawLine(left, oben + 2, left + 220, oben + 2);
                }
                if (this.Str[i].indexOf("Jul") > 0 && this.Str[i + 1].indexOf("Aug") > 0) {
                    g.drawLine(left, oben + 2, left + 220, oben + 2);
                }
                if (this.Str[i].indexOf("Aug") > 0 && this.Str[i + 1].indexOf("Sep") > 0) {
                    g.drawLine(left, oben + 2, left + 220, oben + 2);
                }
                if (this.Str[i].indexOf("Sep") > 0 && this.Str[i + 1].indexOf("Oct") > 0) {
                    g.drawLine(left, oben + 2, left + 220, oben + 2);
                }
                if (this.Str[i].indexOf("Oct") > 0 && this.Str[i + 1].indexOf("Nov") > 0) {
                    g.drawLine(left, oben + 2, left + 220, oben + 2);
                }
                if (this.Str[i].indexOf("Nov") > 0 && this.Str[i + 1].indexOf("Dec") > 0) {
                    g.drawLine(left, oben + 2, left + 220, oben + 2);
                }
                g.setColor(Color.black);
                g.drawString(this.Str[i], left, oben);
                oben += 12;
                this.dataStr[i] = String.valueOf(this.Str[i]) + "\n";
                if (this.dataStr[i].substring(0, 4).equals("Full")) {
                    final MoonDistance MD = new MoonDistance(this.jde[i]);
                    if (MD.computeR() > maxR) {
                        maxR = MD.computeR();
                        jdMax = this.jde[i];
                    }
                    if (MD.computeR() < minR) {
                        minR = MD.computeR();
                        jdMin = this.jde[i];
                    }
                }
                jdeStop = this.jde[i];
            }
        }
        this.dataStr[this.count] = "\nMin. Full Moon Distance  " + (int)Math.round(minR) + " km " + this.monthArray[this.caldat(2, jdMin) - 1] + " " + this.caldat(1, jdMin) + " (Biggest Full Moon)" + "\n";
        final double dR = 100.0 * (maxR - minR) / minR;
        this.dataStr[this.count + 1] = "Max. Full Moon Distance  " + (int)Math.round(maxR) + " km " + this.monthArray[this.caldat(2, jdMax) - 1] + " " + this.caldat(1, jdMax) + " (+ " + Math.round(10.0 * dR) / 10.0 + " %)";
        int diff = oben - 12 - 70;
        diff = (int)Math.round(diff * (this.currentJD - this.jde[n]) / (jdeStop - this.jde[n]));
        final Date currentDate = new Date();
        g.drawString(currentDate.toString(), left, 50);
        g.setColor(Color.red);
        for (int j = 1; j < this.count; ++j) {
            if (this.currentYear == this.year) {
                g.fillOval(5, 70 + diff - 9, 6, 6);
            }
        }
        g.drawString(this.versStr + " - © 1999-2007 J.Giesen - www.GeoAstro.de", 20, this.size().height - 10);
        if (this.demo) {
            final Font f = g.getFont();
            g.setFont(new Font("Chicago", 0, 98));
            g.setColor(Color.red);
            g.drawString("D", 150, 120);
            g.drawString("E", 150, 270);
            g.drawString("M", 150, 420);
            g.drawString("O", 150, 570);
            g.setFont(f);
        }
        if (this.currentYear == this.year) {
            this.lunation = 29.53;
            final int moonRadius = 20;
            final int xMoon = 340;
            final int yMoon = 30;
            int dLun = 0;
            g.setColor(Color.black);
            g.fillRect(xMoon - 25, yMoon - 25, 50, 50);
            if (this.phase <= this.lunation / 4.0) {
                dLun = moonRadius - (int)Math.round(moonRadius * this.phase * 4.0 / this.lunation);
                g.setColor(Color.white);
                g.fillOval(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius);
                g.setColor(Color.black);
                g.fillOval(xMoon - dLun, yMoon - moonRadius, 2 * dLun, 2 * moonRadius);
                g.fillArc(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius, 90, 180);
            }
            if (this.phase > this.lunation / 4.0 && this.phase <= this.lunation / 2.0) {
                dLun = (int)Math.round(moonRadius * (4.0 * this.phase / this.lunation - 1.0));
                g.setColor(Color.white);
                g.fillOval(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius);
                g.setColor(Color.black);
                g.fillArc(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius, 90, 180);
                g.setColor(Color.white);
                g.fillOval(xMoon - dLun, yMoon - moonRadius, 2 * dLun, 2 * moonRadius);
            }
            if (this.phase > this.lunation / 2.0 && this.phase <= 3.0 * this.lunation / 4.0) {
                dLun = moonRadius - (int)Math.round(moonRadius * (4.0 * this.phase / this.lunation - 2.0));
                g.setColor(Color.black);
                g.fillArc(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius, -90, 180);
                g.setColor(Color.white);
                g.fillOval(xMoon - dLun, yMoon - moonRadius, 2 * dLun, 2 * moonRadius);
                g.fillArc(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius, 90, 180);
            }
            if (this.phase > 3.0 * this.lunation / 4.0 && this.phase <= this.lunation) {
                dLun = (int)Math.round(moonRadius * (4.0 * this.phase / this.lunation - 3.0));
                g.setColor(Color.white);
                g.fillOval(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius);
                g.setColor(Color.black);
                g.fillOval(xMoon - dLun, yMoon - moonRadius, 2 * dLun, 2 * moonRadius);
                g.fillArc(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius, -90, 180);
            }
            g.setColor(Color.yellow);
            g.drawOval(xMoon - moonRadius, yMoon - moonRadius, 2 * moonRadius, 2 * moonRadius);
        }
    }
    
    public MoonPhase33() {
        this.versStr = "MoonPhases 3.3";
        this.Str = new String[70];
        this.jde = new double[70];
        this.locOffset = 0;
        this.n = 0;
        this.demo = true;
        this.online = false;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.T = new double[70];
        this.dataStr = new String[70];
    }
}
