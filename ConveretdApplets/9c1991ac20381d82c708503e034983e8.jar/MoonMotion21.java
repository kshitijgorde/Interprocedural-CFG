import java.awt.Font;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Event;
import java.net.URL;
import java.awt.Component;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Button;
import java.awt.Choice;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MoonMotion21 extends Applet
{
    final double K = 0.017453292519943295;
    final char deg = '°';
    String versStr;
    Date dat;
    int date;
    int month;
    int year;
    Choice yearChoice;
    Choice monthChoice;
    Choice dateChoice;
    Choice termChoice;
    Choice frameChoice;
    Choice viewChoice;
    String yearStr;
    Button frameButton;
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
    double UT;
    int dayFullMoon;
    int dayNewMoon;
    double currentJD;
    double T;
    String[] dataStr;
    int nData;
    String hourStr;
    double[] moonL0;
    double[] moonL;
    double[] majorInequality;
    double[] annualInequality;
    double[] reduction;
    double[] parallacticInequality;
    double[] evection;
    double[] variation;
    double[] term212;
    double[] term206;
    double[] term192;
    double[] term165;
    double[] term148;
    double[] term110;
    double[] term55;
    double[] term;
    double[] jD;
    double[] SUM;
    int k;
    boolean showMajor;
    boolean showEvection;
    boolean showVariation;
    boolean showAnnual;
    boolean showReduction;
    boolean showParall;
    boolean showMoreTerms;
    int xMouse;
    int yMouse;
    int xMouseMod;
    int yMouseMod;
    boolean isModifiersX;
    boolean isModifiersY;
    boolean mouseClick;
    int viewItem;
    boolean mouseOrbitClick;
    String fullMoonStr;
    String newMoonStr;
    boolean meanMoon;
    boolean meanOrbit;
    boolean grayOK;
    Rectangle rect;
    Point pt;
    int hours;
    Choice hourChoice;
    int locOffset;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 13312;
    }
    
    public void init() {
        this.setBackground(new Color(150, 150, 150));
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        final URL url = this.getDocumentBase();
        this.myStr = url.toString();
        this.myStr = String.valueOf(this.myStr) + "1234567890123456789012345";
        this.wwwStr = this.myStr.substring(0, 27);
        this.dat = new Date();
        this.year = this.dat.getYear() + 1900;
        this.month = this.dat.getMonth();
        this.date = this.dat.getDate();
        this.hours = this.dat.getHours();
        this.locOffset = -this.dat.getTimezoneOffset() / 60;
        this.UT = this.hours - this.locOffset;
        this.currentJD = this.JD(this.date, this.month + 1, this.year, this.UT);
        this.yearChoice = new Choice();
        for (int i = 0; i < 82; ++i) {
            this.yearStr = String.valueOf(this.year - 31 + i);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(this.year);
        this.yearChoice.select(this.yearStr);
        this.add(this.yearChoice);
        this.monthChoice = new Choice();
        for (int j = 0; j < 12; ++j) {
            this.monthChoice.addItem(this.monthArray[j]);
        }
        this.monthChoice.select(this.month);
        this.add(this.monthChoice);
        this.dateChoice = new Choice();
        for (int k = 1; k < 32; ++k) {
            this.dateChoice.addItem(String.valueOf(k));
        }
        this.dateChoice.select(this.date - 1);
        this.add(this.dateChoice);
        this.hourChoice = new Choice();
        for (int l = 0; l < 24; ++l) {
            this.hourChoice.addItem(l + " UT");
        }
        this.hourStr = (int)this.UT + " UT";
        this.hourChoice.select(this.hourStr);
        this.add(this.hourChoice);
        (this.termChoice = new Choice()).addItem("Choose Terms...");
        this.termChoice.addItem("All Terms");
        this.termChoice.addItem("Hide Major Inequ.");
        this.termChoice.addItem("Hide Evection");
        this.termChoice.addItem("Hide Variation");
        this.termChoice.addItem("Hide Annual Inequ.");
        this.termChoice.addItem("Hide Reduction");
        this.termChoice.addItem("Hide Parall. Inequ.");
        this.termChoice.addItem("Hide More Terms");
        this.termChoice.addItem("Show None");
        this.add(this.termChoice);
        this.add(this.frameButton = new Button("Write Table"));
        (this.viewChoice = new Choice()).addItem("Diagram");
        this.viewChoice.addItem("Orbit");
        this.viewChoice.addItem("Mean Moon on/off");
        this.viewChoice.addItem("Mean Orbit on/off");
        this.viewChoice.addItem("Invert Background");
        this.add(this.viewChoice);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        this.userString = String.valueOf(this.email) + "  " + this.dat.toString();
        if (this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 27) == this.formula("http://www.jgiesen.business", 27)) {
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
        this.repaint();
    }
    
    public void termMenu(final int n) {
        for (int i = 9; i >= 0; --i) {
            this.termChoice.remove(i);
        }
        this.termChoice.addItem("Choose Terms");
        this.termChoice.addItem("All Terms");
        if (this.showMajor) {
            this.termChoice.addItem("Hide Major Inequ.");
        }
        else {
            this.termChoice.addItem("Show Major Inequ.");
        }
        if (this.showEvection) {
            this.termChoice.addItem("Hide Evection");
        }
        else {
            this.termChoice.addItem("Show Evection");
        }
        if (this.showVariation) {
            this.termChoice.addItem("Hide Variation");
        }
        else {
            this.termChoice.addItem("Show Variation");
        }
        if (this.showAnnual) {
            this.termChoice.addItem("Hide Annual Inequ.");
        }
        else {
            this.termChoice.addItem("Show Annual Inequ.");
        }
        if (this.showReduction) {
            this.termChoice.addItem("Hide Reduction");
        }
        else {
            this.termChoice.addItem("Show Reduction");
        }
        if (this.showParall) {
            this.termChoice.addItem("Hide Parall. Inequ.");
        }
        else {
            this.termChoice.addItem("Show Parall. Inequ.");
        }
        if (this.showMoreTerms) {
            this.termChoice.addItem("Hide More Terms");
        }
        else {
            this.termChoice.addItem("Show More Terms");
        }
        this.termChoice.addItem("Show None");
        this.termChoice.select(n);
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        if (this.viewItem == 1) {
            final int xL = 24;
            final int xR = 744;
            final int y2 = 38;
            final int y3 = 502;
            if (event.modifiers != 1 && event.modifiers != 8) {
                this.xMouse = x;
                this.yMouse = y;
            }
            if (event.modifiers == 8) {
                this.xMouseMod = x;
                this.isModifiersX = true;
                this.isModifiersY = false;
            }
            if (event.modifiers == 1) {
                this.yMouseMod = y;
                this.isModifiersY = true;
                this.isModifiersX = false;
            }
            if (this.xMouse >= 24 && this.xMouse <= 744 && this.yMouse >= 38 && this.yMouse <= 502) {
                this.mouseClick = true;
                this.repaint();
            }
            return true;
        }
        if (this.viewItem == 2) {
            this.pt = new Point(x, y);
            if (this.rect.contains(this.pt)) {
                this.xMouse = x;
                this.yMouse = y;
                this.mouseOrbitClick = true;
            }
            else {
                this.xMouse = 0;
                this.yMouse = 0;
            }
            this.repaint();
            return true;
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int x, final int y) {
        switch (this.viewItem) {
            case 1: {
                final int xL = 24;
                final int xR = 744;
                final int y2 = 38;
                final int y3 = 502;
                if (x >= xL && x <= xR && y >= y2 && y <= y3) {
                    this.setCursor(new Cursor(1));
                    break;
                }
                this.setCursor(new Cursor(0));
                break;
            }
            case 2: {
                final int xL = 130;
                final int xR = 670;
                final int y2 = 40;
                final int y3 = 580;
                if (x >= xL && x <= xR && y >= y2 && y <= y3) {
                    this.setCursor(new Cursor(1));
                    break;
                }
                this.setCursor(new Cursor(0));
                break;
            }
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        if (code == 72 || code == 104) {
            if (code == 72) {
                this.currentJD -= 0.041666666666666664;
            }
            else {
                this.currentJD += 0.041666666666666664;
            }
            this.date = this.calDat(1, this.currentJD);
            this.dateChoice.select(this.date - 1);
            this.month = this.calDat(2, this.currentJD);
            this.monthChoice.select(this.month);
            this.year = this.calDat(3, this.currentJD);
            this.yearChoice.select(String.valueOf(this.year));
            this.hours = this.calDat(4, this.currentJD);
            this.UT = this.hours;
            this.currentJD = this.JD(this.date, this.month + 1, this.year, this.UT);
            this.hourChoice.select(this.hours + " UT");
            this.repaint();
            return true;
        }
        if (code == 100 || code == 68) {
            if (code == 68) {
                --this.currentJD;
            }
            else {
                ++this.currentJD;
            }
            this.date = this.calDat(1, this.currentJD);
            this.dateChoice.select(this.date - 1);
            this.month = this.calDat(2, this.currentJD);
            this.monthChoice.select(this.month);
            this.hours = this.calDat(4, this.currentJD);
            this.year = this.calDat(3, this.currentJD);
            this.yearChoice.select(String.valueOf(this.year));
            this.repaint();
            return true;
        }
        if (code == 109 || code == 77) {
            if (code == 77) {
                --this.month;
            }
            else {
                ++this.month;
            }
            if (this.month > 11) {
                this.month = 0;
                ++this.year;
            }
            if (this.month < 0) {
                this.month = 11;
                --this.year;
            }
            n = this.daysInMonth(this.month, this.year);
            if (this.date > n) {
                this.date = n;
            }
            this.monthChoice.select(this.month);
            this.dateChoice.select(this.date - 1);
            this.yearChoice.select(String.valueOf(this.year));
            this.currentJD = this.JD(this.date, this.month + 1, this.year, this.UT);
            this.repaint();
            return true;
        }
        return true;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Button && event.target == this.frameButton) {
            final boolean dem = this.demo || this.online;
            final scrollFrame sf = new scrollFrame("Moon " + this.year + " " + this.monthArray[this.month], this.nData, this.dataStr, dem);
            sf.resize(880, 440);
            sf.show();
            return true;
        }
        if (!(event.target instanceof Choice)) {
            return true;
        }
        this.dat = new Date();
        this.yearStr = this.yearChoice.getSelectedItem();
        this.year = Integer.parseInt(this.yearStr);
        this.yearChoice.select(this.yearStr);
        this.dat.setYear(this.year - 1900);
        if (event.target == this.viewChoice) {
            if (this.viewChoice.getSelectedItem().equals("Diagram")) {
                this.viewItem = 1;
            }
            if (this.viewChoice.getSelectedItem().equals("Orbit")) {
                this.viewItem = 2;
            }
            if (this.viewChoice.getSelectedItem().equals("Mean Moon on/off")) {
                this.viewItem = 2;
                this.meanMoon ^= true;
            }
            if (this.viewChoice.getSelectedItem().equals("Mean Orbit on/off")) {
                this.viewItem = 2;
                this.meanOrbit ^= true;
            }
            if (this.viewChoice.getSelectedItem().equals("Invert Background")) {
                this.grayOK ^= true;
            }
            if (this.grayOK) {
                this.setBackground(new Color(150, 150, 150));
            }
            else {
                this.setBackground(Color.white);
            }
            if (this.viewItem == 2) {
                this.termChoice.disable();
            }
            if (this.viewItem == 1) {
                this.termChoice.enable();
            }
            this.repaint();
            return true;
        }
        if (event.target == this.termChoice) {
            if (this.termChoice.getSelectedItem().equals("All Terms")) {
                this.showMajor = true;
                this.showEvection = true;
                this.showVariation = true;
                this.showAnnual = true;
                this.showReduction = true;
                this.showParall = true;
                this.showMoreTerms = true;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Show Major Inequ.")) {
                this.showMajor = true;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Show Evection")) {
                this.showEvection = true;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Show Variation")) {
                this.showVariation = true;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Show Annual Inequ.")) {
                this.showAnnual = true;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Show Reduction")) {
                this.showReduction = true;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Show Parall. Inequ.")) {
                this.showParall = true;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Show More Terms")) {
                this.showMoreTerms = true;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Show None")) {
                this.showMajor = false;
                this.showEvection = false;
                this.showVariation = false;
                this.showAnnual = false;
                this.showReduction = false;
                this.showParall = false;
                this.showMoreTerms = false;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Hide Major Inequ.")) {
                this.showMajor = false;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Hide Evection")) {
                this.showEvection = false;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Hide Variation")) {
                this.showVariation = false;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Hide Annual Inequ.")) {
                this.showAnnual = false;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Hide Reduction")) {
                this.showReduction = false;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Hide Parall. Inequ.")) {
                this.showParall = false;
                this.repaint();
                this.termMenu(0);
                return true;
            }
            if (this.termChoice.getSelectedItem().equals("Hide More Terms")) {
                this.showMoreTerms = false;
                this.repaint();
                this.termMenu(0);
                return true;
            }
        }
        if (event.target == this.frameChoice) {
            if (this.frameChoice.getSelectedItem().equals("Mean Longitude")) {
                final scrollFrame sf2 = new scrollFrame("Moon " + this.year + " " + this.monthArray[this.month], this.nData, this.dataStr, this.online);
                sf2.resize(850, 460);
                sf2.show();
                return true;
            }
            if (this.frameChoice.getSelectedItem().equals("Major Inequality")) {
                final scrollFrame sf2 = new scrollFrame("Moon " + this.year + " " + this.monthArray[this.month], this.nData, this.dataStr, this.online);
                sf2.resize(850, 400);
                sf2.show();
                return true;
            }
        }
        if (event.target == this.hourChoice) {
            String str = this.hourChoice.getSelectedItem();
            final int L = str.length();
            str = str.substring(0, L - 3);
            this.hours = Integer.parseInt(str);
            this.UT = this.hours;
            this.currentJD = this.JD(this.date, this.month + 1, this.year, this.UT);
            this.repaint();
            return true;
        }
        if (event.target == this.dateChoice) {
            this.date = Integer.parseInt(this.dateChoice.getSelectedItem());
            final int n = this.daysInMonth(this.month, this.year);
            if (this.date > n) {
                this.date = n;
                this.dateChoice.select(this.date - 1);
            }
            this.currentJD = this.JD(this.date, this.month + 1, this.year, 0.0);
            this.repaint();
            return true;
        }
        if (event.target == this.monthChoice) {
            final String monthStr = this.monthChoice.getSelectedItem();
            this.month = this.MonthInteger(monthStr);
            this.monthChoice.select(this.month);
            this.dat.setMonth(this.month);
            this.dat.setSeconds(0);
            this.n = this.daysInMonth(this.month, this.year);
            if (this.date > this.n) {
                this.date = this.n;
                this.dat.setDate(this.date);
                this.dateChoice.select(this.date - 1);
            }
            this.repaint();
            return true;
        }
        if (event.target == this.yearChoice) {
            final String yearStr = this.yearChoice.getSelectedItem();
            this.year = Integer.parseInt(yearStr);
            this.yearChoice.select(yearStr);
            this.dat.setYear(this.year);
            this.n = this.daysInMonth(this.month, this.year);
            if (this.date > this.n) {
                this.date = this.n;
                this.dat.setDate(this.date);
                this.dateChoice.select(this.date - 1);
            }
            this.repaint();
            return true;
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
    
    public String trim(final double x, final int t) {
        String str = "";
        switch (t) {
            case 5: {
                str = String.valueOf(str) + Math.round(100000.0 * x) / 100000.0;
                break;
            }
            case 4: {
                str = String.valueOf(str) + Math.round(10000.0 * x) / 10000.0;
                break;
            }
            case 3: {
                str = String.valueOf(str) + Math.round(1000.0 * x) / 1000.0;
                break;
            }
        }
        for (int n = str.lastIndexOf("."); n > str.length() - (t + 1); str = String.valueOf(str) + "0") {}
        return str;
    }
    
    public String makeString(String str, final int L) {
        while (str.length() < L) {
            str = " " + str;
        }
        return str;
    }
    
    public int calDat(final int what, final double jd) {
        final double JD0 = (int)(jd + 0.5);
        final int B = (int)((JD0 - 1867216.25) / 36524.25);
        final double C = JD0 + B - B / 4 + 1525.0;
        final int D = (int)((C - 122.1) / 365.25);
        final double E = 365.0 * D + D / 4;
        final int F = (int)((C - E) / 30.6001);
        final int day = (int)(C - E + 0.5) - (int)(30.6001 * F);
        if (what == 1) {
            return day;
        }
        final int month = F - 1 - 12 * (F / 14);
        final int year = D - 4715 - (7 + month) / 10;
        final double hour = 24.0 * (jd + 0.5 - JD0);
        if (what == 2) {
            return month - 1;
        }
        if (what == 3) {
            return year;
        }
        if (what == 4) {
            return (int)Math.round(hour);
        }
        return 0;
    }
    
    public int daysInMonth(final int m, final int y) {
        int n = 31;
        if (m == 0 || m == 2 || m == 4 || m == 6 || m == 7 || m == 9 || m == 11) {
            n = 31;
        }
        else if (m == 3 || m == 5 || m == 8 || m == 10) {
            n = 30;
        }
        else if (m == 1) {
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
    
    public int MonthInteger(final String mStr) {
        int m = 100;
        for (int i = 0; i < 12; ++i) {
            if (mStr == this.monthArray[i]) {
                m = i;
                break;
            }
        }
        return m;
    }
    
    double check24(double x) {
        if (x > 24.0) {
            x -= 24.0;
        }
        if (x < 0.0) {
            x += 24.0;
        }
        return x;
    }
    
    double sunRA(final double JD) {
        final double PI2 = 6.283185307179586;
        final double T = (JD - 2451545.0) / 36525.0;
        final double M = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
        final double DL = 6893.0 * Math.sin(M) + 72.0 * Math.sin(2.0 * M);
        final double L = 6.283185307179586 * this.frac(0.7859453 + M / 6.283185307179586 + (6191.2 * T + DL) / 1296000.0);
        final double SL = Math.sin(L);
        final double X = Math.cos(L);
        final double eps = 23.43929111111111 - (46.815 * T + 5.9E-4 * T * T - 0.001813 * T * T * T) / 3600.0;
        final double Y = Math.cos(0.017453292519943295 * eps) * SL;
        final double Z = Math.sin(0.017453292519943295 * eps) * SL;
        final double R = Math.sqrt(1.0 - Z * Z);
        double RA = 7.639437268410976 * Math.atan(Y / (X + R));
        if (RA < 0.0) {
            RA += 24.0;
        }
        return RA;
    }
    
    double GM_Sidereal_Time(final double JD) {
        final double MJD = JD - 2400000.5;
        final long MJD2 = (long)MJD;
        final double ut = (MJD - MJD2) * 24.0;
        final double t_eph = (MJD2 - 51544.5) / 36525.0;
        return 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
    }
    
    double LM_Sidereal_Time(final double JD, final double LONG) {
        final double GMST = this.GM_Sidereal_Time(JD);
        return 24.0 * this.frac((GMST - LONG / 15.0) / 24.0);
    }
    
    double HourAngle(final double jd) {
        final double RA = this.sunRA(jd);
        double tau = 15.0 * (this.LM_Sidereal_Time(jd, 0.0) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        return tau;
    }
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
    }
    
    public String makeTimeString(final String whatStr, double time) {
        String str = "?";
        time = this.check24(time);
        final double diff = time - (int)time;
        int min = (int)Math.round(diff * 60.0);
        if (min == 60) {
            min = 0;
            ++time;
        }
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        str = String.valueOf(String.valueOf((int)time) + str + min);
        if (time < 10.0) {
            str = "0" + str;
        }
        return String.valueOf(whatStr) + " " + str;
    }
    
    public void windowData() {
        this.dataStr[0] = String.valueOf(this.versStr) + "  © 2004-2011  J. Giesen  --  www.GeoAstro.de" + "\n";
        final String[] dataStr = this.dataStr;
        final int n = 0;
        dataStr[n] = String.valueOf(dataStr[n]) + "Values for 0:00 UT\n";
        final String[] dataStr2 = this.dataStr;
        final int n2 = 0;
        dataStr2[n2] = String.valueOf(dataStr2[n2]) + "         \tMean M   Major             Evec               Var               Ann              Reduc             Parall            Terms    Long.\n";
        this.k = 1;
        final double jd = this.JD(1, this.month + 1, this.year, 0.0);
        for (int i = 0; i <= 60; ++i) {
            this.jD[i] = jd + i * 0.5;
            this.T = (this.jD[i] - 2451545.0) / 36525.0;
            this.moonL0[i] = this.L0(this.T) % 360.0;
            this.moonL[i] = this.L(this.T) % 360.0;
            this.majorInequality[i] = 22640.0 * Math.sin(0.017453292519943295 * this.moonL[i]) + 769.0 * Math.sin(0.03490658503988659 * this.moonL[i]);
            this.majorInequality[i] = this.majorInequality[i] / 3600.0 % 360.0;
            this.evection[i] = -4586.0 * Math.sin(0.017453292519943295 * (this.L(this.T) - 2.0 * this.D(this.T))) / 3600.0 % 360.0;
            this.variation[i] = 2370.0 * Math.sin(0.03490658503988659 * this.D(this.T)) / 3600.0 % 360.0;
            this.annualInequality[i] = -668.0 * Math.sin(0.017453292519943295 * this.LS(this.T)) / 3600.0 % 360.0;
            this.reduction[i] = -412.0 * Math.sin(0.03490658503988659 * this.F(this.T)) / 3600.0 % 360.0;
            this.parallacticInequality[i] = -125.0 * Math.sin(0.017453292519943295 * this.D(this.T)) / 3600.0 % 360.0;
            this.term212[i] = -212.0 * Math.sin(0.017453292519943295 * (2.0 * this.L(this.T) - 2.0 * this.D(this.T)));
            this.term206[i] = -206.0 * Math.sin(0.017453292519943295 * (this.L(this.T) + this.LS(this.T) - 2.0 * this.D(this.T)));
            this.term192[i] = 192.0 * Math.sin(0.017453292519943295 * (this.L(this.T) + 2.0 * this.D(this.T)));
            this.term165[i] = -165.0 * Math.sin(0.017453292519943295 * (this.LS(this.T) - 2.0 * this.D(this.T)));
            this.term148[i] = 148.0 * Math.sin(0.017453292519943295 * (this.L(this.T) - this.LS(this.T)));
            this.term110[i] = -110.0 * Math.sin(0.017453292519943295 * (this.L(this.T) + this.LS(this.T)));
            this.term55[i] = -55.0 * Math.sin(0.017453292519943295 * (2.0 * this.F(this.T) - 2.0 * this.D(this.T)));
            this.term[i] = this.term212[i] + this.term206[i] + this.term192[i] + this.term165[i] + this.term148[i] + this.term110[i] + this.term55[i];
            this.term[i] = this.term[i] / 3600.0 % 360.0;
            String dStr = "";
            if (this.calDat(1, this.jD[i]) < 10) {
                dStr = "0";
            }
            String str = String.valueOf(this.monthArray[this.calDat(2, this.jD[i])]) + " " + dStr + this.calDat(1, this.jD[i]);
            str = String.valueOf(str) + this.makeString(this.trim(this.moonL0[i], 3), 10);
            str = String.valueOf(str) + this.makeString(this.trim(this.majorInequality[i], 3), 8);
            str = String.valueOf(str) + this.makeString(this.trim(this.moonL0[i] + this.majorInequality[i], 3), 10);
            str = String.valueOf(str) + this.makeString(this.trim(this.evection[i], 3), 8);
            str = String.valueOf(str) + this.makeString(this.trim(this.moonL0[i] + this.majorInequality[i] + this.evection[i], 3), 10);
            str = String.valueOf(str) + this.makeString(this.trim(this.variation[i], 3), 8);
            str = String.valueOf(str) + this.makeString(this.trim(this.moonL0[i] + this.majorInequality[i] + this.evection[i] + this.variation[i], 3), 10);
            str = String.valueOf(str) + this.makeString(this.trim(this.annualInequality[i], 3), 8);
            str = String.valueOf(str) + this.makeString(this.trim(this.moonL0[i] + this.majorInequality[i] + this.evection[i] + this.variation[i] + this.annualInequality[i], 3), 10);
            str = String.valueOf(str) + this.makeString(this.trim(this.reduction[i], 3), 8);
            str = String.valueOf(str) + this.makeString(this.trim(this.moonL0[i] + this.majorInequality[i] + this.evection[i] + this.variation[i] + this.annualInequality[i] + this.reduction[i], 3), 10);
            str = String.valueOf(str) + this.makeString(this.trim(this.parallacticInequality[i], 3), 8);
            str = String.valueOf(str) + this.makeString(this.trim(this.moonL0[i] + this.majorInequality[i] + this.evection[i] + this.variation[i] + this.annualInequality[i] + this.reduction[i] + this.parallacticInequality[i], 3), 10);
            str = String.valueOf(str) + this.makeString(this.trim(this.term[i], 3), 8);
            str = String.valueOf(str) + this.makeString(this.trim(this.moonL0[i] + this.majorInequality[i] + this.evection[i] + this.variation[i] + this.annualInequality[i] + this.reduction[i] + this.parallacticInequality[i] + this.term[i], 3), 10);
            str = String.valueOf(str) + "\n";
            if (i % 2 == 0) {
                this.dataStr[this.k] = str;
                ++this.k;
            }
        }
    }
    
    public double libration(final double jd) {
        final double inclination = 1.54242;
        final Moon m = new Moon(this.currentJD);
        final double lambda = m.lambda();
        final double beta = m.moonB();
        final double T = (jd - 2451545.0) / 36525.0;
        final double omega = 125.044555 - 1934.1361849 * T + 0.0020762 * T * T + T * T * T / 467410.0 - T * T * T * T / 6.0616E7;
        final double W = lambda - omega;
        double F = 93.2720993 + 483202.0175273 * T - 0.0034029 * T * T - T * T * T / 3526000.0 + T * T * T * T / 8.6331E8;
        F %= 360.0;
        if (F < 0.0) {
            F += 360.0;
        }
        double A = Math.atan2(Math.sin(0.017453292519943295 * W) * Math.cos(0.017453292519943295 * beta) * Math.cos(0.017453292519943295 * inclination) - Math.sin(0.017453292519943295 * beta) * Math.sin(0.017453292519943295 * inclination), Math.cos(0.017453292519943295 * W) * Math.cos(0.017453292519943295 * beta)) / 0.017453292519943295;
        if (A < 0.0) {
            A += 360.0;
        }
        double Ls = A - F;
        if (Ls < -10.0) {
            Ls += 360.0;
        }
        if (Ls > 10.0) {
            Ls -= 360.0;
        }
        return Ls;
    }
    
    public void paint(final Graphics g) {
        final int x0 = 24;
        final int y0 = this.size().height - 50;
        final int ym = this.size().height / 2 - 30;
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.drawRect(2, 2, this.size().width - 4, this.size().height - 4);
        int currentIndex = 0;
        final int YM = ym + 25;
        if (this.viewItem == 1) {
            this.windowData();
            final int delta = 29;
            g.setColor(Color.lightGray);
            for (int i = -8; i <= 8; ++i) {
                if (i != 0) {
                    g.drawLine(24, ym + i * delta, this.size().width - 35, ym + i * delta);
                }
                if (i % 5 == 0) {
                    g.setColor(Color.black);
                    g.drawString(new StringBuffer().append(-i).append('°').toString(), 5, ym + i * delta + 5);
                    g.setColor(Color.lightGray);
                }
            }
            for (int j = 1; j <= 31; ++j) {
                g.drawLine(24 * j, ym - 8 * delta, 24 * j, ym + 8 * delta);
            }
            String s = "";
            g.setColor(Color.black);
            for (int k = 0; k <= 60; ++k) {
                if (this.calDat(1, this.jD[k]) < 10) {
                    s = "0";
                }
                else {
                    s = "";
                }
                if (k % 2 == 0) {
                    g.drawString(s + this.calDat(1, this.jD[k]), x0 + 12 * k - 5, ym + 8 * delta + 20);
                }
            }
            g.setColor(Color.red);
            currentIndex = 0;
            for (int l = 0; l <= this.daysInMonth(this.month, this.year); ++l) {
                final double jd = this.JD(1, this.month + 1, this.year, 0.0) + l;
                if (this.JD(this.date, this.month + 1, this.year, 0.0) == jd) {
                    currentIndex = l;
                    g.setColor(Color.red);
                    g.drawLine(x0 + l * 24, ym - 8 * delta, x0 + l * 24, ym + 8 * delta);
                    break;
                }
            }
            final double jd = this.JD(1, this.month + 1, this.year, 0.0) + currentIndex;
            this.T = (jd - 2451545.0) / 36525.0;
            g.setColor(Color.black);
            g.drawLine(20, YM + 245, 50, YM + 245);
            g.drawString("(1) Major Inequality", 60, YM + 250);
            double majIn = 22640.0 * Math.sin(0.017453292519943295 * this.L(this.T)) + 769.0 * Math.sin(0.03490658503988659 * this.L(this.T));
            majIn = majIn / 3600.0 % 360.0;
            if (this.showMajor) {
                g.setColor(Color.red);
            }
            else {
                g.setColor(Color.black);
            }
            g.drawString(new StringBuffer().append(Math.round(1000.0 * majIn) / 1000.0).append('°').toString(), 170, YM + 250);
            g.setColor(Color.green);
            g.drawLine(20, YM + 260, 50, YM + 260);
            g.drawString("(2) Evection", 60, YM + 265);
            final double ev = -4586.0 * Math.sin(0.017453292519943295 * (this.L(this.T) - 2.0 * this.D(this.T))) / 3600.0 % 360.0;
            if (this.showEvection) {
                g.setColor(Color.red);
            }
            else {
                g.setColor(Color.black);
            }
            g.drawString(new StringBuffer().append(Math.round(1000.0 * ev) / 1000.0).append('°').toString(), 170, YM + 265);
            g.setColor(Color.magenta);
            g.drawLine(20, YM + 275, 50, YM + 275);
            g.drawString("(3) Variation", 60, YM + 280);
            final double vari = 2370.0 * Math.sin(0.03490658503988659 * this.D(this.T)) / 3600.0 % 360.0;
            if (this.showVariation) {
                g.setColor(Color.red);
            }
            else {
                g.setColor(Color.black);
            }
            g.drawString(new StringBuffer().append(Math.round(1000.0 * vari) / 1000.0).append('°').toString(), 170, YM + 280);
            g.setColor(Color.cyan);
            g.drawLine(20, YM + 290, 50, YM + 290);
            g.drawString("(4) Annual Inequality", 60, YM + 295);
            final double ann = -668.0 * Math.sin(0.017453292519943295 * this.LS(this.T)) / 3600.0 % 360.0;
            if (this.showAnnual) {
                g.setColor(Color.red);
            }
            else {
                g.setColor(Color.black);
            }
            g.drawString(new StringBuffer().append(Math.round(1000.0 * ann) / 1000.0).append('°').toString(), 170, YM + 295);
            g.setColor(Color.yellow);
            g.drawLine(250, YM + 245, 280, YM + 245);
            g.drawString("(5) Reduc. to Ecliptic", 290, YM + 250);
            final double redu = -412.0 * Math.sin(0.03490658503988659 * this.F(this.T)) / 3600.0 % 360.0;
            if (this.showReduction) {
                g.setColor(Color.red);
            }
            else {
                g.setColor(Color.black);
            }
            g.drawString(new StringBuffer().append(Math.round(1000.0 * redu) / 1000.0).append('°').toString(), 420, YM + 250);
            g.setColor(Color.orange);
            g.drawLine(250, YM + 260, 280, YM + 260);
            g.drawString("(6) Parallactic Inequality", 290, YM + 265);
            final double para = -125.0 * Math.sin(0.017453292519943295 * this.D(this.T)) / 3600.0 % 360.0;
            if (this.showParall) {
                g.setColor(Color.red);
            }
            else {
                g.setColor(Color.black);
            }
            g.drawString(new StringBuffer().append(Math.round(1000.0 * para) / 1000.0).append('°').toString(), 420, YM + 265);
            g.setColor(Color.darkGray);
            g.drawLine(250, YM + 275, 280, YM + 275);
            g.drawString("(7) More Terms", 290, YM + 280);
            double terms = -212.0 * Math.sin(0.017453292519943295 * (2.0 * this.L(this.T) - 2.0 * this.D(this.T))) - 206.0 * Math.sin(0.017453292519943295 * (this.L(this.T) + this.LS(this.T) - 2.0 * this.D(this.T))) + 192.0 * Math.sin(0.017453292519943295 * (this.L(this.T) + 2.0 * this.D(this.T))) - 165.0 * Math.sin(0.017453292519943295 * (this.LS(this.T) - 2.0 * this.D(this.T))) + 148.0 * Math.sin(0.017453292519943295 * (this.L(this.T) - this.LS(this.T))) - 110.0 * Math.sin(0.017453292519943295 * (this.L(this.T) + this.LS(this.T))) - 55.0 * Math.sin(0.017453292519943295 * (2.0 * this.F(this.T) - 2.0 * this.D(this.T)));
            terms = terms / 3600.0 % 360.0;
            if (this.showMoreTerms) {
                g.setColor(Color.red);
            }
            else {
                g.setColor(Color.black);
            }
            g.drawString(new StringBuffer().append(Math.round(1000.0 * terms) / 1000.0).append('°').toString(), 420, YM + 280);
            g.setColor(Color.red);
            g.drawLine(250, YM + 290, 280, YM + 290);
            g.drawString("Sum", 290, YM + 295);
            double summe = 0.0;
            if (this.showMajor) {
                summe += majIn;
            }
            if (this.showEvection) {
                summe += ev;
            }
            if (this.showVariation) {
                summe += vari;
            }
            if (this.showAnnual) {
                summe += ann;
            }
            if (this.showReduction) {
                summe += redu;
            }
            if (this.showParall) {
                summe += para;
            }
            if (this.showMoreTerms) {
                summe += terms;
            }
            g.setColor(Color.black);
            g.drawString(new StringBuffer().append(Math.round(1000.0 * summe) / 1000.0).append('°').toString(), 420, YM + 295);
            g.setColor(Color.red);
            g.drawString("Mean Longitude", 490, YM + 250);
            g.setColor(Color.black);
            g.drawString(new StringBuffer().append(Math.round(1000.0 * (this.L0(this.T) % 360.0)) / 1000.0).append('°').toString(), 600, YM + 250);
            g.setColor(Color.black);
            g.drawString("Longitude", 490, YM + 265);
            summe += this.L0(this.T) % 360.0;
            g.drawString(new StringBuffer().append(Math.round(1000.0 * summe) / 1000.0).append('°').toString(), 600, YM + 265);
            g.drawString("Longitude (All Terms)", 490, YM + 280);
            summe = majIn + ev + vari + ann + redu + para + terms;
            summe += this.L0(this.T) % 360.0;
            g.drawString(new StringBuffer().append(Math.round(1000.0 * summe) / 1000.0).append('°').toString(), 600, YM + 280);
            double max = 0.0;
            double min = 0.0;
            int F = delta;
            int X1 = 0;
            int Y1 = 0;
            if (this.showMajor) {
                g.setColor(Color.black);
                for (int m = 0; m < 60; ++m) {
                    X1 = x0 + 12 * m;
                    Y1 = ym - (int)Math.round(F * this.majorInequality[m]);
                    final int X2 = x0 + 12 * (m + 1);
                    final int Y2 = ym - (int)Math.round(F * this.majorInequality[m + 1]);
                    g.drawLine(X1, Y1, X2, Y2);
                }
                for (int i2 = 0; i2 <= 30; ++i2) {
                    if (this.majorInequality[2 * i2] > max) {
                        max = this.majorInequality[2 * i2];
                    }
                    if (this.majorInequality[2 * i2] < min) {
                        min = this.majorInequality[2 * i2];
                    }
                }
                for (int i3 = 0; i3 <= 30; ++i3) {
                    if (this.majorInequality[2 * i3] == max) {
                        Y1 = ym - (int)Math.round(F * max);
                        g.drawString(String.valueOf(Math.round(1000.0 * max) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                    if (this.majorInequality[2 * i3] == min) {
                        Y1 = ym - (int)Math.round(F * min);
                        g.drawString(String.valueOf(Math.round(1000.0 * min) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                }
                this.dataStr[this.k] = "Maj. Inequ. Max.: " + Math.round(1000.0 * max) / 1000.0 + "  Min. " + Math.round(1000.0 * min) / 1000.0 + "\n";
                ++this.k;
                this.nData = this.k;
            }
            if (this.showEvection) {
                g.setColor(Color.green);
                for (int m = 0; m < 60; ++m) {
                    X1 = x0 + 12 * m;
                    Y1 = ym - (int)Math.round(F * this.evection[m]);
                    final int X2 = x0 + 12 * (m + 1);
                    final int Y2 = ym - (int)Math.round(F * this.evection[m + 1]);
                    g.drawLine(X1, Y1, X2, Y2);
                }
                max = 0.0;
                min = 0.0;
                for (int i2 = 0; i2 <= 30; ++i2) {
                    if (this.evection[2 * i2] > max) {
                        max = this.evection[2 * i2];
                    }
                    if (this.evection[2 * i2] < min) {
                        min = this.evection[2 * i2];
                    }
                }
                for (int i3 = 0; i3 <= 30; ++i3) {
                    if (this.evection[2 * i3] == max) {
                        Y1 = ym - (int)Math.round(F * max);
                        g.drawString(String.valueOf(Math.round(1000.0 * max) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                    if (this.evection[2 * i3] == min) {
                        Y1 = ym - (int)Math.round(F * min);
                        g.drawString(String.valueOf(Math.round(1000.0 * min) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                }
            }
            if (this.showVariation) {
                g.setColor(Color.magenta);
                for (int m = 0; m < 60; ++m) {
                    X1 = x0 + 12 * m;
                    Y1 = ym - (int)Math.round(F * this.variation[m]);
                    final int X2 = x0 + 12 * (m + 1);
                    final int Y2 = ym - (int)Math.round(F * this.variation[m + 1]);
                    g.drawLine(X1, Y1, X2, Y2);
                }
                max = 0.0;
                min = 0.0;
                for (int i2 = 0; i2 <= 30; ++i2) {
                    if (this.variation[2 * i2] > max) {
                        max = this.variation[2 * i2];
                    }
                    if (this.variation[2 * i2] < min) {
                        min = this.variation[2 * i2];
                    }
                }
                for (int i3 = 0; i3 <= 30; ++i3) {
                    if (this.variation[2 * i3] == max) {
                        Y1 = ym - (int)Math.round(F * max);
                        g.drawString(String.valueOf(Math.round(1000.0 * max) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                    if (this.variation[2 * i3] == min) {
                        Y1 = ym - (int)Math.round(F * min);
                        g.drawString(String.valueOf(Math.round(1000.0 * min) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                }
            }
            F = 10 * delta;
            if (this.showAnnual) {
                g.setColor(Color.cyan);
                for (int m = 0; m < 60; ++m) {
                    X1 = x0 + 12 * m;
                    Y1 = ym - (int)Math.round(F * this.annualInequality[m]);
                    final int X2 = x0 + 12 * (m + 1);
                    final int Y2 = ym - (int)Math.round(F * this.annualInequality[m + 1]);
                    g.drawLine(X1, Y1, X2, Y2);
                }
                max = -1.0;
                min = 1.0;
                for (int i2 = 0; i2 <= 30; ++i2) {
                    if (this.annualInequality[2 * i2] > max) {
                        max = this.annualInequality[2 * i2];
                    }
                    if (this.annualInequality[2 * i2] < min) {
                        min = this.annualInequality[2 * i2];
                    }
                }
                for (int i3 = 0; i3 <= 30; ++i3) {
                    if (this.annualInequality[2 * i3] == max) {
                        Y1 = ym - (int)Math.round(F * max);
                        g.drawString(String.valueOf(Math.round(1000.0 * max) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                    if (this.annualInequality[2 * i3] == min) {
                        Y1 = ym - (int)Math.round(F * min);
                        g.drawString(String.valueOf(Math.round(1000.0 * min) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                }
            }
            if (this.showReduction) {
                g.setColor(Color.yellow);
                for (int m = 0; m < 60; ++m) {
                    X1 = x0 + 12 * m;
                    Y1 = ym - (int)Math.round(F * this.reduction[m]);
                    final int X2 = x0 + 12 * (m + 1);
                    final int Y2 = ym - (int)Math.round(F * this.reduction[m + 1]);
                    g.drawLine(X1, Y1, X2, Y2);
                }
                max = -1.0;
                min = 1.0;
                for (int i2 = 0; i2 <= 30; ++i2) {
                    if (this.reduction[2 * i2] > max) {
                        max = this.reduction[2 * i2];
                    }
                    if (this.reduction[2 * i2] < min) {
                        min = this.reduction[2 * i2];
                    }
                }
                for (int i3 = 0; i3 <= 30; ++i3) {
                    if (this.reduction[2 * i3] == max) {
                        Y1 = ym - (int)Math.round(F * max);
                        g.drawString(String.valueOf(Math.round(1000.0 * max) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                    if (this.reduction[2 * i3] == min) {
                        Y1 = ym - (int)Math.round(F * min);
                        g.drawString(String.valueOf(Math.round(1000.0 * min) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                }
            }
            if (this.showParall) {
                g.setColor(Color.orange);
                for (int m = 0; m < 60; ++m) {
                    X1 = x0 + 12 * m;
                    Y1 = ym - (int)Math.round(F * this.parallacticInequality[m]);
                    final int X2 = x0 + 12 * (m + 1);
                    final int Y2 = ym - (int)Math.round(F * this.parallacticInequality[m + 1]);
                    g.drawLine(X1, Y1, X2, Y2);
                }
                max = -1.0;
                min = 1.0;
                for (int i2 = 0; i2 <= 30; ++i2) {
                    if (this.parallacticInequality[2 * i2] > max) {
                        max = this.parallacticInequality[2 * i2];
                    }
                    if (this.parallacticInequality[2 * i2] < min) {
                        min = this.parallacticInequality[2 * i2];
                    }
                }
                for (int i3 = 0; i3 <= 30; ++i3) {
                    if (this.parallacticInequality[2 * i3] == max) {
                        Y1 = ym - (int)Math.round(F * max);
                        g.drawString(String.valueOf(Math.round(1000.0 * max) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                    if (this.parallacticInequality[2 * i3] == min) {
                        Y1 = ym - (int)Math.round(F * min);
                        g.drawString(String.valueOf(Math.round(1000.0 * min) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                }
            }
            if (this.showMoreTerms) {
                g.setColor(Color.darkGray);
                for (int m = 0; m < 60; ++m) {
                    X1 = x0 + 12 * m;
                    Y1 = ym - (int)Math.round(F * this.term[m]);
                    final int X2 = x0 + 12 * (m + 1);
                    final int Y2 = ym - (int)Math.round(F * this.term[m + 1]);
                    g.drawLine(X1, Y1, X2, Y2);
                }
                max = -1.0;
                min = 1.0;
                for (int i2 = 0; i2 <= 30; ++i2) {
                    if (this.term[2 * i2] > max) {
                        max = this.term[2 * i2];
                    }
                    if (this.term[2 * i2] < min) {
                        min = this.term[2 * i2];
                    }
                }
                for (int i3 = 0; i3 <= 30; ++i3) {
                    if (this.term[2 * i3] == max) {
                        Y1 = ym - (int)Math.round(F * max);
                        g.drawString(String.valueOf(Math.round(1000.0 * max) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                    if (this.term[2 * i3] == min) {
                        Y1 = ym - (int)Math.round(F * min);
                        g.drawString(String.valueOf(Math.round(1000.0 * min) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                }
            }
            F = delta;
            g.setColor(Color.red);
            for (int m = 0; m < 60; ++m) {
                double sum = 0.0;
                double sum2 = 0.0;
                X1 = x0 + 12 * m;
                if (this.showMajor) {
                    sum += this.majorInequality[m];
                    sum2 += this.majorInequality[m + 1];
                }
                if (this.showEvection) {
                    sum += this.evection[m];
                    sum2 += this.evection[m + 1];
                }
                if (this.showVariation) {
                    sum += this.variation[m];
                    sum2 += this.variation[m + 1];
                }
                if (this.showAnnual) {
                    sum += this.annualInequality[m];
                    sum2 += this.annualInequality[m + 1];
                }
                if (this.showReduction) {
                    sum += this.reduction[m];
                    sum2 += this.reduction[m + 1];
                }
                if (this.showParall) {
                    sum += this.parallacticInequality[m];
                    sum2 += this.parallacticInequality[m + 1];
                }
                if (this.showMoreTerms) {
                    sum += this.term[m];
                    sum2 += this.term[m + 1];
                }
                Y1 = ym - (int)Math.round(F * sum);
                final int X2 = x0 + 12 * (m + 1);
                final int Y2 = ym - (int)Math.round(F * sum2);
                this.SUM[m] = sum;
                g.drawLine(X1, Y1, X2, Y2);
            }
            if (this.showMajor || this.showEvection || this.showVariation || this.showAnnual || this.showReduction || this.showMoreTerms) {
                max = -1.0;
                min = 1.0;
                for (int i2 = 0; i2 <= 30; ++i2) {
                    if (this.SUM[2 * i2] > max) {
                        max = this.SUM[2 * i2];
                    }
                    if (this.SUM[2 * i2] < min) {
                        min = this.SUM[2 * i2];
                    }
                }
                for (int i3 = 0; i3 <= 30; ++i3) {
                    if (this.SUM[2 * i3] == max) {
                        Y1 = ym - (int)Math.round(F * max);
                        g.drawString(String.valueOf(Math.round(1000.0 * max) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                    if (this.SUM[2 * i3] == min) {
                        Y1 = ym - (int)Math.round(F * min);
                        g.drawString(String.valueOf(Math.round(1000.0 * min) / 1000.0), X1 = x0 + 24 * i3, Y1);
                    }
                }
            }
            g.setColor(Color.darkGray);
            g.drawLine(20, ym, this.size().width - 20, ym);
            g.setFont(new Font("Helvetica", 0, 10));
            g.setColor(Color.black);
            if (this.mouseClick) {
                g.drawLine(this.xMouse - 3, this.yMouse, this.xMouse + 3, this.yMouse);
                g.drawLine(this.xMouse, this.yMouse - 3, this.xMouse, this.yMouse + 3);
                final double time = (this.xMouse - 24) / 24.0 + 1.0;
                final double degrees = (ym - this.yMouse) / delta;
                g.drawString("Date " + Math.round(100.0 * time) / 100.0, 675, YM + 250);
                if (degrees >= 0.0) {
                    g.drawString("L=M +" + Math.round(100.0 * degrees) / 100.0 + '°', 675, YM + 265);
                }
                else {
                    g.drawString("L=M " + Math.round(100.0 * degrees) / 100.0 + '°', 675, YM + 265);
                }
                this.mouseClick = false;
            }
            if (this.isModifiersX) {
                g.drawLine(this.xMouse, this.yMouse - 30, this.xMouseMod, this.yMouse - 30);
                g.drawLine(this.xMouse, this.yMouse, this.xMouse, this.yMouse - 30);
                g.drawLine(this.xMouseMod, this.yMouse, this.xMouseMod, this.yMouse - 30);
                final double d = (this.xMouseMod - this.xMouse) / 24.0;
                g.drawString(Math.round(100.0 * d) / 100.0 + " d", (this.xMouse + this.xMouseMod) / 2 - 20, this.yMouse - 40);
                this.isModifiersX = false;
            }
            if (this.isModifiersY) {
                g.drawLine(this.xMouse, this.yMouse, this.xMouse + 30, this.yMouse);
                g.drawLine(this.xMouse, this.yMouseMod, this.xMouse + 30, this.yMouseMod);
                g.drawLine(this.xMouse + 30, this.yMouse, this.xMouse + 30, this.yMouseMod);
                final double d = (this.yMouseMod - this.yMouse) / 29.0;
                g.drawString(new StringBuffer().append(Math.round(100.0 * d) / 100.0).append('°').toString(), this.xMouse + 40, (this.yMouse + this.yMouseMod) / 2);
                this.isModifiersY = false;
            }
        }
        if (this.viewItem == 2) {
            final int XM = 410;
            final int dxy = 30;
            final int xM = 390;
            final int yM = 310;
            final int Rad = 270;
            final int MF = 1667;
            g.setColor(Color.lightGray);
            for (int i4 = -9; i4 < 10; ++i4) {
                g.drawLine(XM - i4 * dxy, 40, XM - i4 * dxy, 580);
            }
            for (int i5 = -9; i5 < 10; ++i5) {
                g.drawLine(XM - 270, 310 - i5 * dxy, XM + 270, 310 - i5 * dxy);
            }
            if (this.meanOrbit) {
                final int radius = Math.round(384401 / MF);
                g.setColor(Color.blue);
                g.drawOval(XM - radius, 310 - radius, 2 * radius, 2 * radius);
                g.setColor(Color.black);
            }
            g.setColor(Color.black);
            g.drawLine(XM + 10 * dxy, 310, XM + 10 * dxy, 310 - 2 * dxy);
            g.drawString("100,000 km", XM + 10 * dxy + 10, 310 - dxy);
            if (this.mouseOrbitClick) {
                g.drawLine(this.xMouse - 3, this.yMouse, this.xMouse + 3, this.yMouse);
                g.drawLine(this.xMouse, this.yMouse - 3, this.xMouse, this.yMouse + 3);
                final double dx = this.xMouse - XM;
                final double dy = 310 - this.yMouse;
                double angle = Math.atan2(dy / dxy, dx / dxy) / 0.017453292519943295;
                if (angle < 0.0) {
                    angle += 360.0;
                }
                g.drawString("L = " + Math.round(100.0 * angle) / 100.0 + '°', 20, 280);
                g.drawString("R = " + (int)Math.round(50000.0 * Math.sqrt(dx * dx + dy * dy) / dxy) + " km", 20, 295);
                this.mouseOrbitClick = false;
            }
            this.T = (this.currentJD - 2451545.0) / 36525.0;
            this.UT = this.calDat(4, this.currentJD);
            g.setFont(new Font("helvetica", 1, 10));
            g.drawString((int)this.UT + ":00 UT", 20, 70);
            g.setFont(new Font("Helvetica", 0, 10));
            g.setColor(Color.blue);
            g.drawOval(XM - 5, 305, 10, 10);
            int count = 0;
            final int[] xMoon = new int[70];
            final int[] yMoon = new int[70];
            g.setColor(Color.darkGray);
            double MoonLambda = this.L0(this.T) % 360.0;
            MoonDistance MoonDist = new MoonDistance(this.currentJD);
            double MoonEarthDist = MoonDist.computeR() / MF;
            int xMOON = XM + (int)Math.round(MoonEarthDist * Math.cos(0.017453292519943295 * MoonLambda));
            int yMOON = 310 - (int)Math.round(MoonEarthDist * Math.sin(0.017453292519943295 * MoonLambda));
            if (this.meanMoon) {
                g.fillOval(xMOON - 6, yMOON - 6, 12, 12);
            }
            g.fillOval(20, 160, 12, 12);
            g.drawString("Mean Moon", 45, 170);
            g.setColor(Color.black);
            MoonLambda = (this.L0(this.T) + this.SUMME(this.T)) % 360.0;
            xMOON = XM + (int)Math.round(MoonEarthDist * Math.cos(0.017453292519943295 * MoonLambda));
            yMOON = 310 - (int)Math.round(MoonEarthDist * Math.sin(0.017453292519943295 * MoonLambda));
            g.fillOval(xMOON - 6, yMOON - 6, 12, 12);
            g.drawString("R = " + (int)Math.round(MoonEarthDist * MF) + " km", 20, 100);
            g.drawString("L = " + Math.round(1000.0 * MoonLambda) / 1000.0 + '°', 20, 115);
            g.drawString("L0= " + Math.round(1000.0 * (this.L0(this.T) % 360.0)) / 1000.0 + '°', 20, 130);
            g.drawString("dL= " + Math.round(1000.0 * (MoonLambda % 360.0 - this.L0(this.T) % 360.0)) / 1000.0 + '°', 20, 145);
            g.fillOval(20, 180, 12, 12);
            g.drawString("True Moon", 45, 190);
            this.newMoon(this.month, this.year);
            g.drawString("New Moon:", 20, 210);
            g.drawString(this.newMoonStr, 20, 225);
            this.fullMoon(this.month, this.year);
            g.drawString("Full Moon:", 20, 240);
            g.drawString(this.fullMoonStr, 20, 255);
            g.setColor(Color.yellow);
            final PlanetRiseSet planet = new PlanetRiseSet(this.date, this.month + 1, this.year, this.UT, 6);
            double MoonGeoLambda = planet.lambda();
            if (MoonGeoLambda < 0.0) {
                MoonGeoLambda += 360.0;
            }
            int dx2 = (int)Math.round(250.0 * Math.cos(0.017453292519943295 * MoonGeoLambda));
            int dy2 = (int)Math.round(250.0 * Math.sin(0.017453292519943295 * MoonGeoLambda));
            g.drawLine(XM, 310, XM + dx2, 310 - dy2);
            if (dy2 > 0) {
                dy2 += 20;
            }
            else {
                dy2 -= 20;
            }
            if (dx2 > 0) {
                dx2 += 20;
            }
            else {
                dx2 -= 20;
            }
            g.setFont(new Font("Helvetica", 1, 12));
            g.drawString("Sun", XM + dx2, 310 - dy2);
            g.setFont(new Font("Helvetica", 0, 10));
            g.setColor(Color.white);
            dx2 = (int)Math.round(80.0 * Math.cos(0.017453292519943295 * MoonGeoLambda));
            dy2 = (int)Math.round(80.0 * Math.sin(0.017453292519943295 * MoonGeoLambda));
            final int moonRadius = 15;
            g.fillArc(xMOON - moonRadius, yMOON - moonRadius, 2 * moonRadius, 2 * moonRadius, (int)(270.0 + MoonGeoLambda), 180);
            g.setColor(Color.black);
            g.fillArc(xMOON - moonRadius, yMOON - moonRadius, 2 * moonRadius, 2 * moonRadius, (int)(90.0 + MoonGeoLambda), 180);
            g.drawOval(xMOON - moonRadius, yMOON - moonRadius, 2 * moonRadius, 2 * moonRadius);
            g.setColor(Color.yellow);
            dx2 = (int)Math.round(250.0 * Math.cos(0.017453292519943295 * MoonGeoLambda));
            dy2 = (int)Math.round(250.0 * Math.sin(0.017453292519943295 * MoonGeoLambda));
            g.drawLine(xMOON, yMOON, xMOON + dx2, yMOON - dy2);
            final int earthradius = 25;
            g.fillArc(XM - earthradius, 310 - earthradius, 2 * earthradius, 2 * earthradius, (int)(270.0 + MoonGeoLambda), 180);
            g.setColor(Color.black);
            g.fillArc(XM - earthradius, 310 - earthradius, 2 * earthradius, 2 * earthradius, (int)(90.0 + MoonGeoLambda), 180);
            g.drawOval(XM - earthradius, 310 - earthradius, 2 * earthradius, 2 * earthradius);
            g.setColor(Color.red);
            final double GHA = this.HourAngle(this.currentJD);
            dx2 = (int)Math.round(27.0 * Math.cos(0.017453292519943295 * (GHA + MoonGeoLambda)));
            dy2 = (int)Math.round(27.0 * Math.sin(0.017453292519943295 * (GHA + MoonGeoLambda)));
            g.drawLine(XM + dx2, 310 - dy2, XM - dx2, 310 + dy2);
            g.fillOval(XM + dx2 - 2, 310 - dy2 - 2, 4, 4);
            g.setColor(Color.black);
            g.drawString("GHA " + Math.round(10.0 * GHA) / 10.0 + '°', 20, 85);
            g.drawString("Illum. Fraction:", 20, 320);
            g.drawString(Math.round(1000.0 * planet.moonIllumFrac()) / 10.0 + " %", 20, 335);
            final double libL = this.libration(this.currentJD);
            g.drawString("Libration Long. L = " + Math.round(10.0 * libL) / 10.0 + '°', 20, 355);
            double peri = 500000.0;
            double apo = 0.0;
            int dApo = 0;
            int dPeri = 0;
            g.setColor(Color.darkGray);
            for (int d2 = 1; d2 <= this.daysInMonth(this.month, this.year); ++d2) {
                this.T = (this.JD(d2, this.month + 1, this.year, 0.0) - 2451545.0) / 36525.0;
                MoonLambda = (this.L0(this.T) + this.SUMME(this.T)) % 360.0;
                MoonDist = new MoonDistance(this.JD(d2, this.month + 1, this.year, 0.0));
                MoonEarthDist = MoonDist.computeR() / MF;
                if (MoonEarthDist > apo) {
                    apo = MoonEarthDist;
                    dApo = count;
                }
                if (MoonEarthDist < peri) {
                    peri = MoonEarthDist;
                    dPeri = count;
                }
                xMoon[count] = XM + (int)Math.round(MoonEarthDist * Math.cos(0.017453292519943295 * MoonLambda));
                yMoon[count] = 310 - (int)Math.round(MoonEarthDist * Math.sin(0.017453292519943295 * MoonLambda));
                g.drawOval(xMoon[count] - 2, yMoon[count] - 2, 4, 4);
                g.drawString(String.valueOf(d2), xMoon[count], yMoon[count]);
                ++count;
                this.T = (this.JD(d2, this.month + 1, this.year, 12.0) - 2451545.0) / 36525.0;
                MoonLambda = (this.L0(this.T) + this.SUMME(this.T)) % 360.0;
                MoonDist = new MoonDistance(this.JD(d2, this.month + 1, this.year, 12.0));
                MoonEarthDist = MoonDist.computeR() / MF;
                if (MoonEarthDist > apo) {
                    apo = MoonEarthDist;
                    dApo = count;
                }
                if (MoonEarthDist < peri) {
                    peri = MoonEarthDist;
                    dPeri = count;
                }
                xMoon[count] = XM + (int)Math.round(MoonEarthDist * Math.cos(0.017453292519943295 * MoonLambda));
                yMoon[count] = 310 - (int)Math.round(MoonEarthDist * Math.sin(0.017453292519943295 * MoonLambda));
                ++count;
            }
            g.setColor(Color.red);
            g.fillOval(xMoon[dApo] - 2, yMoon[dApo] - 2, 4, 4);
            if (xMoon[dApo] > XM) {
                g.drawString("Apo", xMoon[dApo] + 5, yMoon[dApo] + 3);
            }
            else {
                g.drawString("Apo", xMoon[dApo] - 25, yMoon[dApo] + 3);
            }
            g.fillOval(xMoon[dPeri] - 2, yMoon[dPeri] - 2, 4, 4);
            if (xMoon[dPeri] > XM) {
                g.drawString("Peri", xMoon[dPeri] + 10, yMoon[dPeri] + 3);
            }
            else {
                g.drawString("Peri", xMoon[dPeri] - 40, yMoon[dPeri] + 3);
            }
            g.setColor(Color.black);
            for (int i6 = 0; i6 < count - 1; ++i6) {
                g.drawLine(xMoon[i6], yMoon[i6], xMoon[i6 + 1], yMoon[i6 + 1]);
            }
        }
        g.setColor(Color.black);
        int links;
        if (this.viewItem == 1) {
            links = 660;
        }
        else {
            links = 20;
        }
        g.drawString(this.versStr, links, 545);
        g.drawString("© 2004-2011  J. Giesen", links, 560);
        g.drawString("www.GeoAstro.de", links, 575);
        if (this.demo) {
            final Font f = g.getFont();
            g.setFont(new Font("Chicago", 0, 144));
            g.setColor(Color.red);
            g.drawString("D E M O", 100, 340);
            g.setFont(f);
        }
    }
    
    double SUMME(final double T) {
        final double majIn = (22640.0 * Math.sin(0.017453292519943295 * this.L(T)) + 769.0 * Math.sin(0.03490658503988659 * this.L(T))) / 3600.0;
        final double ev = -4586.0 * Math.sin(0.017453292519943295 * (this.L(T) - 2.0 * this.D(T))) / 3600.0;
        final double vari = 2370.0 * Math.sin(0.03490658503988659 * this.D(T)) / 3600.0;
        final double ann = -668.0 * Math.sin(0.017453292519943295 * this.LS(T)) / 3600.0;
        final double redu = -412.0 * Math.sin(0.03490658503988659 * this.F(T)) / 3600.0;
        final double para = -125.0 * Math.sin(0.017453292519943295 * this.D(T)) / 3600.0;
        double terms = -212.0 * Math.sin(0.017453292519943295 * (2.0 * this.L(T) - 2.0 * this.D(T))) - 206.0 * Math.sin(0.017453292519943295 * (this.L(T) + this.LS(T) - 2.0 * this.D(T))) + 192.0 * Math.sin(0.017453292519943295 * (this.L(T) + 2.0 * this.D(T))) - 165.0 * Math.sin(0.017453292519943295 * (this.LS(T) - 2.0 * this.D(T))) + 148.0 * Math.sin(0.017453292519943295 * (this.L(T) - this.LS(T))) - 110.0 * Math.sin(0.017453292519943295 * (this.L(T) + this.LS(T))) - 55.0 * Math.sin(0.017453292519943295 * (2.0 * this.F(T) - 2.0 * this.D(T)));
        terms /= 3600.0;
        return majIn + ev + vari + ann + redu + para + terms;
    }
    
    public double L0(final double T) {
        return 218.31617 + 481267.88088 * T - 4.06 * T * T / 3600.0;
    }
    
    public double L(final double T) {
        return 134.96292 + 477198.86753 * T + 33.25 * T * T / 3600.0;
    }
    
    public double LS(final double T) {
        return 357.52543 + 35999.04944 * T - 0.58 * T * T / 3600.0;
    }
    
    public double D(final double T) {
        return 297.85027 + 445267.11135 * T - 5.15 * T * T / 3600.0;
    }
    
    public double F(final double T) {
        return 93.27283 + 483202.01873 * T - 11.56 * T * T / 3600.0;
    }
    
    public void fullMoon(final int month, final int year) {
        final double[] delta = new double[122];
        final int n = this.daysInMonth(month, year);
        int hourFullMoon = 0;
        for (int i = 1; i <= n; ++i) {
            final PlanetRiseSet planet = new PlanetRiseSet(i, month + 1, year, 0.0, 6);
            final double moonL = planet.heliocentricLambda();
            final double sunL = planet.lambda();
            double SunMoonExcess = 360.0 - (sunL - moonL);
            if (SunMoonExcess > 360.0) {
                SunMoonExcess -= 360.0;
            }
            delta[i] = SunMoonExcess;
        }
        PlanetRiseSet planet = new PlanetRiseSet(1, month + 2, year, 0.0, 6);
        double moonL = planet.heliocentricLambda();
        double sunL = planet.lambda();
        double SunMoonExcess = 360.0 - (sunL - moonL);
        if (SunMoonExcess > 360.0) {
            SunMoonExcess -= 360.0;
        }
        delta[n + 1] = SunMoonExcess;
        for (int j = 1; j <= n; ++j) {
            if (delta[j] < 180.0 && delta[j + 1] > 180.0) {
                this.dayFullMoon = j;
                break;
            }
        }
        for (int k = 0; k <= 24; ++k) {
            planet = new PlanetRiseSet(this.dayFullMoon, month + 1, year, k, 6);
            moonL = planet.heliocentricLambda();
            sunL = planet.lambda();
            SunMoonExcess = 360.0 - (sunL - moonL);
            if (SunMoonExcess > 360.0) {
                SunMoonExcess -= 360.0;
            }
            delta[k] = SunMoonExcess;
        }
        for (int l = 0; l < 24; ++l) {
            if (delta[l] < 180.0 && delta[l + 1] > 180.0) {
                hourFullMoon = l;
                break;
            }
        }
        if (Math.abs(delta[hourFullMoon + 1] - 180.0) < Math.abs(delta[hourFullMoon] - 180.0)) {
            ++hourFullMoon;
        }
        --hourFullMoon;
        for (int m = 0; m <= 120; ++m) {
            planet = new PlanetRiseSet(this.dayFullMoon, month + 1, year, hourFullMoon + m / 60.0, 6);
            moonL = planet.heliocentricLambda();
            sunL = planet.lambda();
            SunMoonExcess = 360.0 - (sunL - moonL);
            if (SunMoonExcess > 360.0) {
                SunMoonExcess -= 360.0;
            }
            delta[m] = SunMoonExcess;
        }
        int minFullMoon = 0;
        for (int i2 = 0; i2 < 120; ++i2) {
            if (delta[i2] < 180.0 && delta[i2 + 1] > 180.0) {
                minFullMoon = i2;
                break;
            }
        }
        if (minFullMoon >= 60) {
            minFullMoon -= 60;
            ++hourFullMoon;
        }
        int monthFullMoon = month;
        if (hourFullMoon >= 24) {
            hourFullMoon -= 24;
            ++this.dayFullMoon;
        }
        if (hourFullMoon < 0) {
            hourFullMoon += 24;
            --this.dayFullMoon;
        }
        if (monthFullMoon < 11 && this.dayFullMoon > this.daysInMonth(monthFullMoon, year)) {
            this.dayFullMoon = 1;
            ++monthFullMoon;
        }
        if (monthFullMoon > 0 && this.dayFullMoon < 1) {
            this.dayFullMoon = this.daysInMonth(monthFullMoon, year);
            --monthFullMoon;
        }
        String str;
        if (this.dayFullMoon < 10) {
            str = "0" + this.dayFullMoon;
        }
        else {
            str = String.valueOf(this.dayFullMoon);
        }
        this.fullMoonStr = String.valueOf(this.monthArray[monthFullMoon]) + " " + str + " at " + this.makeTimeString("", hourFullMoon + minFullMoon / 60.0) + " UT" + "\n";
    }
    
    public void newMoon(final int month, final int year) {
        final double[] delta = new double[122];
        final int n = this.daysInMonth(month, year);
        int hourNewMoon = 0;
        for (int i = 1; i <= n; ++i) {
            final PlanetRiseSet planet = new PlanetRiseSet(i, month + 1, year, 0.0, 6);
            final double moonL = planet.heliocentricLambda();
            final double sunL = planet.lambda();
            double SunMoonExcess = 360.0 - (sunL - moonL);
            if (SunMoonExcess > 360.0) {
                SunMoonExcess -= 360.0;
            }
            delta[i] = SunMoonExcess;
        }
        for (int j = 1; j <= n; ++j) {
            if (delta[j] > 345.0 && delta[j + 1] < 15.0) {
                this.dayNewMoon = j;
                break;
            }
        }
        for (int k = 0; k <= 24; ++k) {
            final PlanetRiseSet planet = new PlanetRiseSet(this.dayNewMoon, month + 1, year, k, 6);
            final double moonL = planet.heliocentricLambda();
            final double sunL = planet.lambda();
            double SunMoonExcess = 360.0 - (sunL - moonL);
            if (SunMoonExcess > 360.0) {
                SunMoonExcess -= 360.0;
            }
            delta[k] = SunMoonExcess;
        }
        for (int l = 0; l < 24; ++l) {
            if (delta[l] > 359.0 && delta[l + 1] < 1.0) {
                hourNewMoon = l;
                break;
            }
        }
        if (Math.abs(delta[hourNewMoon + 1]) < Math.abs(delta[hourNewMoon] - 360.0)) {
            ++hourNewMoon;
        }
        --hourNewMoon;
        for (int m = 0; m < 120; ++m) {
            final PlanetRiseSet planet = new PlanetRiseSet(this.dayNewMoon, month + 1, year, hourNewMoon + m / 60.0, 6);
            final double moonL = planet.heliocentricLambda();
            final double sunL = planet.lambda();
            double SunMoonExcess = 360.0 - (sunL - moonL);
            if (SunMoonExcess > 360.0) {
                SunMoonExcess -= 360.0;
            }
            delta[m] = SunMoonExcess;
        }
        int minNewMoon = 0;
        for (int i2 = 0; i2 < 120; ++i2) {
            if (delta[i2] > 359.0 && delta[i2 + 1] < 1.0) {
                minNewMoon = i2;
                break;
            }
        }
        if (minNewMoon >= 60) {
            minNewMoon -= 60;
            ++hourNewMoon;
        }
        hourNewMoon = hourNewMoon;
        int monthNewMoon = month;
        if (hourNewMoon >= 24) {
            hourNewMoon -= 24;
            ++this.dayNewMoon;
        }
        if (hourNewMoon < 0) {
            hourNewMoon += 24;
            --this.dayNewMoon;
        }
        if (monthNewMoon < 11 && this.dayNewMoon > this.daysInMonth(monthNewMoon, year)) {
            this.dayNewMoon = 1;
            ++monthNewMoon;
        }
        if (monthNewMoon > 0 && this.dayNewMoon < 1) {
            this.dayNewMoon = this.daysInMonth(monthNewMoon, year);
            --monthNewMoon;
        }
        String str;
        if (this.dayNewMoon < 10) {
            str = "0" + this.dayNewMoon;
        }
        else {
            str = String.valueOf(this.dayNewMoon);
        }
        this.newMoonStr = String.valueOf(this.monthArray[monthNewMoon]) + " " + str + " at " + this.makeTimeString("", hourNewMoon + minNewMoon / 60.0) + " UT" + "\n";
    }
    
    public MoonMotion21() {
        this.versStr = "MoonMotion 2.1";
        this.n = 0;
        this.demo = true;
        this.online = false;
        this.monthArray = new String[12];
        this.dataStr = new String[35];
        this.hourStr = "";
        this.moonL0 = new double[70];
        this.moonL = new double[70];
        this.majorInequality = new double[70];
        this.annualInequality = new double[70];
        this.reduction = new double[70];
        this.parallacticInequality = new double[70];
        this.evection = new double[70];
        this.variation = new double[70];
        this.term212 = new double[70];
        this.term206 = new double[70];
        this.term192 = new double[70];
        this.term165 = new double[70];
        this.term148 = new double[70];
        this.term110 = new double[70];
        this.term55 = new double[70];
        this.term = new double[70];
        this.jD = new double[70];
        this.SUM = new double[70];
        this.showMajor = true;
        this.showEvection = true;
        this.showVariation = true;
        this.showAnnual = true;
        this.showReduction = true;
        this.showParall = true;
        this.showMoreTerms = true;
        this.isModifiersX = false;
        this.isModifiersY = false;
        this.mouseClick = false;
        this.viewItem = 1;
        this.mouseOrbitClick = false;
        this.fullMoonStr = "";
        this.newMoonStr = "";
        this.meanMoon = true;
        this.meanOrbit = false;
        this.grayOK = true;
        this.rect = new Rectangle(130, 40, 540, 540);
    }
    
    class PlanetRiseSet
    {
        double K;
        double lambda;
        double JD;
        int Date;
        int Month;
        double jd;
        double L;
        double B;
        double illFrac;
        
        public PlanetRiseSet(final int date, final int month, final int year, final double UT, final int myPlanet) {
            this.K = 0.017453292519943295;
            this.jd = this.JD(date, month, year, 0.0);
            switch (myPlanet) {
                case 0: {
                    final EarthCompute earthComp = new EarthCompute(this.jd);
                    double BE = earthComp.earthB();
                    double LE = earthComp.earthL();
                    double RE = earthComp.earthR();
                    double x = -RE * Math.cos(BE) * Math.cos(LE);
                    double y = -RE * Math.cos(BE) * Math.sin(LE);
                    double z = -RE * Math.sin(BE);
                    this.lambda = Math.atan2(y, x) / this.K;
                    if (this.lambda < 0.0) {
                        this.lambda += 360.0;
                    }
                    this.JD = this.JD(date, month, year, UT);
                    final EarthCompute earth = new EarthCompute(this.JD);
                    this.L = earth.heliocentricLambda();
                    BE = earth.earthB();
                    LE = earth.earthL();
                    RE = earth.earthR();
                    x = -RE * Math.cos(BE) * Math.cos(LE);
                    y = -RE * Math.cos(BE) * Math.sin(LE);
                    z = -RE * Math.sin(BE);
                    this.lambda = Math.atan2(y, x) / this.K;
                    if (this.lambda < 0.0) {
                        this.lambda += 360.0;
                        break;
                    }
                    break;
                }
                case 6: {
                    this.JD = this.JD(date, month, year, UT);
                    final Moon moon = new Moon(this.JD);
                    this.lambda = moon.lambda();
                    this.L = this.lambda;
                    final EarthCompute earthComp = new EarthCompute(this.JD(date, month, year, UT));
                    final double BE = earthComp.earthB();
                    final double LE = earthComp.earthL();
                    final double RE = earthComp.earthR();
                    final double x = -RE * Math.cos(BE) * Math.cos(LE);
                    final double y = -RE * Math.cos(BE) * Math.sin(LE);
                    final double z = -RE * Math.sin(BE);
                    double Lambda = Math.atan2(y, x) / this.K;
                    if (Lambda < 0.0) {
                        Lambda += 360.0;
                    }
                    this.lambda = Lambda;
                    this.illFrac = moon.phase();
                    break;
                }
            }
        }
        
        double JD(final int date, int month, int year, final double UT) {
            if (month <= 2) {
                month += 12;
                --year;
            }
            final int A = (int)(year / 100.0);
            final int B = 2 - A + (int)(A / 4.0);
            return (int)(365.25 * (year + 4716)) + (int)(30.6001 * (month + 1)) + date + B - 1524.5 + UT / 24.0;
        }
        
        double lambda() {
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
        
        double moonIllumFrac() {
            return this.illFrac;
        }
    }
    
    class Moon
    {
        static final double P2 = 6.283185307179586;
        static final double ARC = 206264.8062;
        static final double coseps = 0.917482062;
        static final double sineps = 0.397777156;
        static final double K = 0.017453292519943295;
        double moonDEC;
        double moon_RA;
        double B_Moon;
        double L_Moon;
        double T;
        double RA;
        
        public Moon(final double JD) {
            this.computeMoon(this.T = (JD - 2451545.0) / 36525.0);
        }
        
        public double frac(double X) {
            X -= (int)X;
            if (X < 0.0) {
                ++X;
            }
            return X;
        }
        
        public void computeMoon(final double T) {
            final double L0 = this.frac(0.606433 + 1336.855225 * T);
            final double L2 = 6.283185307179586 * this.frac(0.374897 + 1325.55241 * T);
            final double LS = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
            final double D = 6.283185307179586 * this.frac(0.827361 + 1236.853086 * T);
            final double F = 6.283185307179586 * this.frac(0.259086 + 1342.227825 * T);
            final double DL = 22640.0 * Math.sin(L2) - 4586.0 * Math.sin(L2 - 2.0 * D) + 2370.0 * Math.sin(2.0 * D) + 769.0 * Math.sin(2.0 * L2) - 668.0 * Math.sin(LS) - 412.0 * Math.sin(2.0 * F) - 212.0 * Math.sin(2.0 * L2 - 2.0 * D) - 206.0 * Math.sin(L2 + LS - 2.0 * D) + 192.0 * Math.sin(L2 + 2.0 * D) - 165.0 * Math.sin(LS - 2.0 * D) - 125.0 * Math.sin(D) - 110.0 * Math.sin(L2 + LS) + 148.0 * Math.sin(L2 - LS) - 55.0 * Math.sin(2.0 * F - 2.0 * D);
            final double S = F + (DL + 412.0 * Math.sin(2.0 * F) + 541.0 * Math.sin(LS)) / 206264.8062;
            final double H = F - 2.0 * D;
            final double N = -526.0 * Math.sin(H) + 44.0 * Math.sin(L2 + H) - 31.0 * Math.sin(-L2 + H) - 23.0 * Math.sin(LS + H) + 11.0 * Math.sin(-LS + H) - 25.0 * Math.sin(-2.0 * L2 + F) + 21.0 * Math.sin(-L2 + F);
            this.L_Moon = 6.283185307179586 * this.frac(L0 + DL / 1296000.0);
            this.B_Moon = (18520.0 * Math.sin(S) + N) / 206264.8062;
            final double CB = Math.cos(this.B_Moon);
            final double X = CB * Math.cos(this.L_Moon);
            final double V = CB * Math.sin(this.L_Moon);
            final double W = Math.sin(this.B_Moon);
            final double Y = 0.917482062 * V - 0.397777156 * W;
            final double Z = 0.397777156 * V + 0.917482062 * W;
            final double rho = Math.sqrt(1.0 - Z * Z);
            final double DEC = 57.29577951308232 * Math.atan2(Z, rho);
            this.RA = 7.639437268410976 * Math.atan2(Y, X + rho);
            this.moonDEC = DEC;
            this.moon_RA = this.RA;
        }
        
        double lambda() {
            double L = this.L_Moon / 0.017453292519943295;
            if (L < 0.0) {
                L += 360.0;
            }
            return L;
        }
        
        double phase() {
            final double D = 297.8502042 + 445267.1115168 * this.T - 0.00163 * this.T * this.T + this.T * this.T * this.T / 545868.0 - this.T * this.T * this.T * this.T / 1.13065E8;
            final double M = 357.5291092 + 35999.0502909 * this.T - 1.536E-4 * this.T * this.T + this.T * this.T * this.T / 2.449E7;
            final double Ms = 134.9634114 + 477198.8676313 * this.T + 0.008997 * this.T * this.T + this.T * this.T * this.T / 69699.0 - this.T * this.T * this.T * this.T / 1.4712E7;
            final double i = 180.0 - D - 6.289 * Math.sin(0.017453292519943295 * Ms) + 2.1 * Math.sin(0.017453292519943295 * M) - 1.274 * Math.sin(0.017453292519943295 * (2.0 * D - Ms)) - 0.658 * Math.sin(0.03490658503988659 * D) - 0.214 * Math.sin(0.03490658503988659 * Ms) - 0.11 * Math.sin(0.017453292519943295 * D);
            return 0.5 * (1.0 + Math.cos(0.017453292519943295 * i));
        }
        
        double moonB() {
            return this.B_Moon;
        }
    }
    
    class EarthCompute
    {
        final double K = 0.017453292519943295;
        double UT;
        double[][] LArray;
        double R;
        double L;
        double B;
        
        double earthL() {
            return this.L;
        }
        
        double earthB() {
            return this.B;
        }
        
        double earthR() {
            return this.R;
        }
        
        public EarthCompute(final double JD) {
            this.LArray = new double[65][3];
            final double T = (JD - 2451545.0) / 365250.0;
            this.B = (this.B0(T) + this.B1(T) * T) / 1.0E8;
            this.L = (this.L0(T) + this.L1(T) * T + this.L2(T) * T * T + this.L3(T) * T * T * T + this.L4(T) * T * T * T * T + this.L5(T) * T * T * T * T * T) / 1.0E8;
            this.R = (this.R0(T) + this.R1(T) * T + this.R2(T) * T * T + this.R3(T) * T * T * T + this.R4(T) * T * T * T * T) / 1.0E8;
        }
        
        double R() {
            return this.R;
        }
        
        double heliocentricLambda() {
            return this.L / 0.017453292519943295 % 360.0;
        }
        
        double L0(final double t) {
            this.LArray[1][0] = 1.75347046E8;
            this.LArray[1][1] = 0.0;
            this.LArray[1][2] = 0.0;
            this.LArray[2][0] = 3341656.0;
            this.LArray[2][1] = 4.6692568;
            this.LArray[2][2] = 6283.07585;
            this.LArray[3][0] = 34894.0;
            this.LArray[3][1] = 4.6261;
            this.LArray[3][2] = 12566.1517;
            this.LArray[4][0] = 3497.0;
            this.LArray[4][1] = 2.7441;
            this.LArray[4][2] = 5753.3849;
            this.LArray[5][0] = 3418.0;
            this.LArray[5][1] = 2.8289;
            this.LArray[5][2] = 3.5231;
            this.LArray[6][0] = 3136.0;
            this.LArray[6][1] = 3.6277;
            this.LArray[6][2] = 77713.7715;
            this.LArray[7][0] = 2676.0;
            this.LArray[7][1] = 4.4181;
            this.LArray[7][2] = 7860.4194;
            this.LArray[8][0] = 2343.0;
            this.LArray[8][1] = 6.1352;
            this.LArray[8][2] = 3930.2097;
            this.LArray[9][0] = 1324.0;
            this.LArray[9][1] = 0.7425;
            this.LArray[9][2] = 11506.7698;
            this.LArray[10][0] = 1273.0;
            this.LArray[10][1] = 2.0371;
            this.LArray[10][2] = 529.691;
            this.LArray[11][0] = 1199.0;
            this.LArray[11][1] = 1.1096;
            this.LArray[11][2] = 1577.3435;
            this.LArray[12][0] = 990.0;
            this.LArray[12][1] = 5.233;
            this.LArray[12][2] = 5884.927;
            this.LArray[13][0] = 902.0;
            this.LArray[13][1] = 2.045;
            this.LArray[13][2] = 26.298;
            this.LArray[14][0] = 857.0;
            this.LArray[14][1] = 3.508;
            this.LArray[14][2] = 398.149;
            this.LArray[15][0] = 780.0;
            this.LArray[15][1] = 1.179;
            this.LArray[15][2] = 5223.694;
            this.LArray[16][0] = 753.0;
            this.LArray[16][1] = 2.533;
            this.LArray[16][2] = 5507.553;
            this.LArray[17][0] = 505.0;
            this.LArray[17][1] = 4.583;
            this.LArray[17][2] = 18849.228;
            this.LArray[18][0] = 492.0;
            this.LArray[18][1] = 4.205;
            this.LArray[18][2] = 775.523;
            this.LArray[19][0] = 357.0;
            this.LArray[19][1] = 2.92;
            this.LArray[19][2] = 0.067;
            this.LArray[20][0] = 317.0;
            this.LArray[20][1] = 5.849;
            this.LArray[20][2] = 11790.629;
            this.LArray[21][0] = 284.0;
            this.LArray[21][1] = 1.899;
            this.LArray[21][2] = 796.298;
            this.LArray[22][0] = 271.0;
            this.LArray[22][1] = 0.315;
            this.LArray[22][2] = 10977.079;
            this.LArray[23][0] = 243.0;
            this.LArray[23][1] = 0.345;
            this.LArray[23][2] = 5486.778;
            this.LArray[24][0] = 206.0;
            this.LArray[24][1] = 4.806;
            this.LArray[24][2] = 2544.314;
            this.LArray[25][0] = 205.0;
            this.LArray[25][1] = 1.869;
            this.LArray[25][2] = 5573.143;
            this.LArray[26][0] = 202.0;
            this.LArray[26][1] = 2.458;
            this.LArray[26][2] = 6069.777;
            this.LArray[27][0] = 156.0;
            this.LArray[27][1] = 0.833;
            this.LArray[27][2] = 213.299;
            this.LArray[28][0] = 132.0;
            this.LArray[28][1] = 3.411;
            this.LArray[28][2] = 2942.463;
            this.LArray[29][0] = 126.0;
            this.LArray[29][1] = 1.083;
            this.LArray[29][2] = 20.775;
            this.LArray[30][0] = 115.0;
            this.LArray[30][1] = 0.645;
            this.LArray[30][2] = 0.98;
            this.LArray[31][0] = 103.0;
            this.LArray[31][1] = 0.636;
            this.LArray[31][2] = 4694.003;
            this.LArray[32][0] = 102.0;
            this.LArray[32][1] = 0.976;
            this.LArray[32][2] = 15720.839;
            this.LArray[33][0] = 102.0;
            this.LArray[33][1] = 4.267;
            this.LArray[33][2] = 7.114;
            this.LArray[34][0] = 99.0;
            this.LArray[34][1] = 6.21;
            this.LArray[34][2] = 2146.17;
            this.LArray[35][0] = 98.0;
            this.LArray[35][1] = 0.68;
            this.LArray[35][2] = 155.42;
            this.LArray[36][0] = 86.0;
            this.LArray[36][1] = 5.98;
            this.LArray[36][2] = 161000.69;
            this.LArray[37][0] = 85.0;
            this.LArray[37][1] = 1.3;
            this.LArray[37][2] = 6275.96;
            this.LArray[38][0] = 85.0;
            this.LArray[38][1] = 3.67;
            this.LArray[38][2] = 71430.7;
            this.LArray[39][0] = 80.0;
            this.LArray[39][1] = 1.81;
            this.LArray[39][2] = 17260.15;
            this.LArray[40][0] = 79.0;
            this.LArray[40][1] = 3.04;
            this.LArray[40][2] = 12036.46;
            this.LArray[41][0] = 75.0;
            this.LArray[41][1] = 1.76;
            this.LArray[41][2] = 5088.63;
            this.LArray[42][0] = 74.0;
            this.LArray[42][1] = 3.5;
            this.LArray[42][2] = 3154.69;
            this.LArray[43][0] = 74.0;
            this.LArray[43][1] = 4.68;
            this.LArray[43][2] = 801.82;
            this.LArray[44][0] = 70.0;
            this.LArray[44][1] = 0.83;
            this.LArray[44][2] = 9437.76;
            this.LArray[45][0] = 62.0;
            this.LArray[45][1] = 3.98;
            this.LArray[45][2] = 8827.39;
            this.LArray[46][0] = 61.0;
            this.LArray[46][1] = 1.82;
            this.LArray[46][2] = 7084.9;
            this.LArray[47][0] = 57.0;
            this.LArray[47][1] = 2.78;
            this.LArray[47][2] = 6286.6;
            this.LArray[48][0] = 56.0;
            this.LArray[48][1] = 4.39;
            this.LArray[48][2] = 14143.5;
            this.LArray[49][0] = 56.0;
            this.LArray[49][1] = 3.47;
            this.LArray[49][2] = 6279.55;
            this.LArray[50][0] = 52.0;
            this.LArray[50][1] = 0.19;
            this.LArray[50][2] = 12139.55;
            this.LArray[51][0] = 52.0;
            this.LArray[51][1] = 1.33;
            this.LArray[51][2] = 1748.02;
            this.LArray[52][0] = 51.0;
            this.LArray[52][1] = 0.28;
            this.LArray[52][2] = 5856.48;
            this.LArray[53][0] = 49.0;
            this.LArray[53][1] = 0.49;
            this.LArray[53][2] = 1194.45;
            this.LArray[54][0] = 41.0;
            this.LArray[54][1] = 5.37;
            this.LArray[54][2] = 8429.24;
            this.LArray[55][0] = 41.0;
            this.LArray[55][1] = 2.4;
            this.LArray[55][2] = 19651.05;
            this.LArray[56][0] = 39.0;
            this.LArray[56][1] = 6.17;
            this.LArray[56][2] = 10447.39;
            this.LArray[57][0] = 37.0;
            this.LArray[57][1] = 6.04;
            this.LArray[57][2] = 10213.29;
            this.LArray[58][0] = 37.0;
            this.LArray[58][1] = 2.57;
            this.LArray[58][2] = 1059.38;
            this.LArray[59][0] = 36.0;
            this.LArray[59][1] = 1.71;
            this.LArray[59][2] = 2352.87;
            this.LArray[60][0] = 36.0;
            this.LArray[60][1] = 1.78;
            this.LArray[60][2] = 6812.77;
            this.LArray[61][0] = 33.0;
            this.LArray[61][1] = 0.59;
            this.LArray[61][2] = 17789.85;
            this.LArray[62][0] = 30.0;
            this.LArray[62][1] = 0.44;
            this.LArray[62][2] = 83996.85;
            this.LArray[63][0] = 30.0;
            this.LArray[63][1] = 2.74;
            this.LArray[63][2] = 1349.87;
            this.LArray[64][0] = 25.0;
            this.LArray[64][1] = 3.16;
            this.LArray[64][2] = 4690.48;
            double l0 = 0.0;
            for (int i = 1; i < 65; ++i) {
                l0 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
            }
            return l0;
        }
        
        double L1(final double t) {
            this.LArray[1][0] = 6.28331966747E11;
            this.LArray[1][1] = 0.0;
            this.LArray[1][2] = 0.0;
            this.LArray[2][0] = 206059.0;
            this.LArray[2][1] = 2.678235;
            this.LArray[2][2] = 6283.07585;
            this.LArray[3][0] = 4303.0;
            this.LArray[3][1] = 2.6351;
            this.LArray[3][2] = 12566.1517;
            this.LArray[4][0] = 425.0;
            this.LArray[4][1] = 1.59;
            this.LArray[4][2] = 3.523;
            this.LArray[5][0] = 119.0;
            this.LArray[5][1] = 5.796;
            this.LArray[5][2] = 26.298;
            this.LArray[6][0] = 109.0;
            this.LArray[6][1] = 2.966;
            this.LArray[6][2] = 1577.344;
            this.LArray[7][0] = 93.0;
            this.LArray[7][1] = 2.59;
            this.LArray[7][2] = 18849.23;
            this.LArray[8][0] = 72.0;
            this.LArray[8][1] = 1.14;
            this.LArray[8][2] = 529.69;
            this.LArray[9][0] = 68.0;
            this.LArray[9][1] = 1.87;
            this.LArray[9][2] = 398.15;
            this.LArray[10][0] = 67.0;
            this.LArray[10][1] = 4.41;
            this.LArray[10][2] = 5507.55;
            this.LArray[11][0] = 59.0;
            this.LArray[11][1] = 2.89;
            this.LArray[11][2] = 5223.69;
            this.LArray[12][0] = 56.0;
            this.LArray[12][1] = 2.17;
            this.LArray[12][2] = 155.42;
            this.LArray[13][0] = 45.0;
            this.LArray[13][1] = 0.4;
            this.LArray[13][2] = 796.3;
            this.LArray[14][0] = 36.0;
            this.LArray[14][1] = 0.47;
            this.LArray[14][2] = 775.52;
            this.LArray[15][0] = 29.0;
            this.LArray[15][1] = 2.65;
            this.LArray[15][2] = 7.11;
            this.LArray[16][0] = 21.0;
            this.LArray[16][1] = 5.34;
            this.LArray[16][2] = 0.98;
            this.LArray[17][0] = 19.0;
            this.LArray[17][1] = 1.85;
            this.LArray[17][2] = 5486.78;
            this.LArray[18][0] = 19.0;
            this.LArray[18][1] = 4.97;
            this.LArray[18][2] = 213.3;
            this.LArray[19][0] = 17.0;
            this.LArray[19][1] = 2.99;
            this.LArray[19][2] = 6275.96;
            this.LArray[20][0] = 16.0;
            this.LArray[20][1] = 0.03;
            this.LArray[20][2] = 2544.31;
            this.LArray[21][0] = 16.0;
            this.LArray[21][1] = 1.43;
            this.LArray[21][2] = 2146.17;
            this.LArray[22][0] = 15.0;
            this.LArray[22][1] = 1.21;
            this.LArray[22][2] = 10977.08;
            this.LArray[23][0] = 12.0;
            this.LArray[23][1] = 2.83;
            this.LArray[23][2] = 1748.02;
            this.LArray[24][0] = 12.0;
            this.LArray[24][1] = 3.26;
            this.LArray[24][2] = 5088.63;
            this.LArray[25][0] = 12.0;
            this.LArray[25][1] = 5.27;
            this.LArray[25][2] = 1194.45;
            this.LArray[26][0] = 12.0;
            this.LArray[26][1] = 2.08;
            this.LArray[26][2] = 4694.0;
            this.LArray[27][0] = 11.0;
            this.LArray[27][1] = 0.77;
            this.LArray[27][2] = 553.57;
            this.LArray[28][0] = 10.0;
            this.LArray[28][1] = 1.3;
            this.LArray[28][2] = 6286.6;
            this.LArray[29][0] = 10.0;
            this.LArray[29][1] = 4.24;
            this.LArray[29][2] = 1349.87;
            this.LArray[30][0] = 9.0;
            this.LArray[30][1] = 2.7;
            this.LArray[30][2] = 242.73;
            this.LArray[31][0] = 9.0;
            this.LArray[31][1] = 5.64;
            this.LArray[31][2] = 951.72;
            this.LArray[32][0] = 8.0;
            this.LArray[32][1] = 5.3;
            this.LArray[32][2] = 2352.87;
            this.LArray[33][0] = 6.0;
            this.LArray[33][1] = 2.65;
            this.LArray[33][2] = 9437.76;
            this.LArray[34][0] = 6.0;
            this.LArray[34][1] = 4.67;
            this.LArray[34][2] = 4690.48;
            double l1 = 0.0;
            for (int i = 1; i < 35; ++i) {
                l1 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
            }
            return l1;
        }
        
        double L2(final double t) {
            this.LArray[1][0] = 52919.0;
            this.LArray[1][1] = 0.0;
            this.LArray[1][2] = 0.0;
            this.LArray[2][0] = 8720.0;
            this.LArray[2][1] = 1.0721;
            this.LArray[2][2] = 6283.0758;
            this.LArray[3][0] = 309.0;
            this.LArray[3][1] = 0.867;
            this.LArray[3][2] = 12566.152;
            this.LArray[4][0] = 27.0;
            this.LArray[4][1] = 0.05;
            this.LArray[4][2] = 3.52;
            this.LArray[5][0] = 16.0;
            this.LArray[5][1] = 5.19;
            this.LArray[5][2] = 26.3;
            this.LArray[6][0] = 16.0;
            this.LArray[6][1] = 3.68;
            this.LArray[6][2] = 155.42;
            this.LArray[7][0] = 10.0;
            this.LArray[7][1] = 0.76;
            this.LArray[7][2] = 18849.23;
            this.LArray[8][0] = 9.0;
            this.LArray[8][1] = 2.06;
            this.LArray[8][2] = 77713.77;
            this.LArray[9][0] = 7.0;
            this.LArray[9][1] = 0.83;
            this.LArray[9][2] = 775.52;
            this.LArray[10][0] = 5.0;
            this.LArray[10][1] = 4.66;
            this.LArray[10][2] = 1577.34;
            this.LArray[11][0] = 4.0;
            this.LArray[11][1] = 1.03;
            this.LArray[11][2] = 7.11;
            this.LArray[12][0] = 4.0;
            this.LArray[12][1] = 3.44;
            this.LArray[12][2] = 5573.14;
            this.LArray[13][0] = 3.0;
            this.LArray[13][1] = 5.14;
            this.LArray[13][2] = 796.3;
            this.LArray[14][0] = 3.0;
            this.LArray[14][1] = 6.05;
            this.LArray[14][2] = 5507.55;
            this.LArray[15][0] = 3.0;
            this.LArray[15][1] = 1.19;
            this.LArray[15][2] = 242.73;
            this.LArray[16][0] = 3.0;
            this.LArray[16][1] = 6.12;
            this.LArray[16][2] = 529.69;
            this.LArray[17][0] = 3.0;
            this.LArray[17][1] = 0.31;
            this.LArray[17][2] = 398.15;
            this.LArray[18][0] = 3.0;
            this.LArray[18][1] = 2.28;
            this.LArray[18][2] = 553.57;
            this.LArray[19][0] = 2.0;
            this.LArray[19][1] = 4.38;
            this.LArray[19][2] = 5223.69;
            this.LArray[20][0] = 2.0;
            this.LArray[20][1] = 3.75;
            this.LArray[20][2] = 0.98;
            double l2 = 0.0;
            for (int i = 1; i < 21; ++i) {
                l2 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
            }
            return l2;
        }
        
        double L3(final double t) {
            this.LArray[1][0] = 289.0;
            this.LArray[1][1] = 5.844;
            this.LArray[1][2] = 6283.076;
            this.LArray[2][0] = 35.0;
            this.LArray[2][1] = 0.0;
            this.LArray[2][2] = 0.0;
            this.LArray[3][0] = 17.0;
            this.LArray[3][1] = 5.49;
            this.LArray[3][2] = 12566.15;
            this.LArray[4][0] = 3.0;
            this.LArray[4][1] = 5.2;
            this.LArray[4][2] = 155.42;
            this.LArray[5][0] = 1.0;
            this.LArray[5][1] = 4.72;
            this.LArray[5][2] = 3.52;
            this.LArray[6][0] = 1.0;
            this.LArray[6][1] = 5.3;
            this.LArray[6][2] = 18849.23;
            this.LArray[7][0] = 1.0;
            this.LArray[7][1] = 5.97;
            this.LArray[7][2] = 242.73;
            double l3 = 0.0;
            for (int i = 1; i < 8; ++i) {
                l3 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
            }
            return l3;
        }
        
        double L4(final double t) {
            this.LArray[1][0] = 114.0;
            this.LArray[1][1] = 3.142;
            this.LArray[1][2] = 0.0;
            this.LArray[2][0] = 8.0;
            this.LArray[2][1] = 4.13;
            this.LArray[2][2] = 6283.08;
            this.LArray[3][0] = 1.0;
            this.LArray[3][1] = 3.84;
            this.LArray[3][2] = 12566.15;
            double l4 = 0.0;
            for (int i = 1; i < 4; ++i) {
                l4 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
            }
            return l4;
        }
        
        double L5(final double t) {
            this.LArray[1][0] = 1.0;
            this.LArray[1][1] = 3.14;
            this.LArray[1][2] = 0.0;
            double l5 = 0.0;
            for (int i = 1; i < 2; ++i) {
                l5 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
            }
            return l5;
        }
        
        double B0(final double t) {
            this.LArray[1][0] = 280.0;
            this.LArray[1][1] = 3.199;
            this.LArray[1][2] = 84334.662;
            this.LArray[2][0] = 102.0;
            this.LArray[2][1] = 5.422;
            this.LArray[2][2] = 5507.553;
            this.LArray[3][0] = 80.0;
            this.LArray[3][1] = 3.88;
            this.LArray[3][2] = 5223.69;
            this.LArray[4][0] = 44.0;
            this.LArray[4][1] = 3.7;
            this.LArray[4][2] = 2352.87;
            this.LArray[5][0] = 32.0;
            this.LArray[5][1] = 4.0;
            this.LArray[5][2] = 1577.34;
            double b0 = 0.0;
            for (int i = 1; i < 6; ++i) {
                b0 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
            }
            return b0;
        }
        
        double B1(final double t) {
            this.LArray[1][0] = 9.0;
            this.LArray[1][1] = 3.9;
            this.LArray[1][2] = 5507.55;
            this.LArray[2][0] = 6.0;
            this.LArray[2][1] = 1.73;
            this.LArray[2][2] = 5223.69;
            double b1 = 0.0;
            for (int i = 1; i < 3; ++i) {
                b1 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
            }
            return b1;
        }
        
        double R0(final double t) {
            this.LArray[1][0] = 1.00013989E8;
            this.LArray[1][1] = 0.0;
            this.LArray[1][2] = 0.0;
            this.LArray[2][0] = 1670700.0;
            this.LArray[2][1] = 3.0984635;
            this.LArray[2][2] = 6283.07585;
            this.LArray[3][0] = 13956.0;
            this.LArray[3][1] = 3.05525;
            this.LArray[3][2] = 12566.1517;
            this.LArray[4][0] = 3084.0;
            this.LArray[4][1] = 5.1985;
            this.LArray[4][2] = 77713.7715;
            this.LArray[5][0] = 1628.0;
            this.LArray[5][1] = 1.1739;
            this.LArray[5][2] = 5753.3849;
            this.LArray[6][0] = 1576.0;
            this.LArray[6][1] = 2.8469;
            this.LArray[6][2] = 7860.4194;
            this.LArray[7][0] = 925.0;
            this.LArray[7][1] = 5.453;
            this.LArray[7][2] = 11506.77;
            this.LArray[8][0] = 542.0;
            this.LArray[8][1] = 4.564;
            this.LArray[8][2] = 3930.21;
            this.LArray[9][0] = 472.0;
            this.LArray[9][1] = 3.661;
            this.LArray[9][2] = 5884.927;
            this.LArray[10][0] = 346.0;
            this.LArray[10][1] = 0.964;
            this.LArray[10][2] = 5507.553;
            this.LArray[11][0] = 329.0;
            this.LArray[11][1] = 5.9;
            this.LArray[11][2] = 5223.694;
            this.LArray[12][0] = 307.0;
            this.LArray[12][1] = 0.299;
            this.LArray[12][2] = 5573.143;
            this.LArray[13][0] = 243.0;
            this.LArray[13][1] = 4.273;
            this.LArray[13][2] = 11790.629;
            this.LArray[14][0] = 212.0;
            this.LArray[14][1] = 5.847;
            this.LArray[14][2] = 1577.344;
            this.LArray[15][0] = 186.0;
            this.LArray[15][1] = 5.022;
            this.LArray[15][2] = 10977.079;
            this.LArray[16][0] = 175.0;
            this.LArray[16][1] = 3.012;
            this.LArray[16][2] = 18849.228;
            this.LArray[17][0] = 110.0;
            this.LArray[17][1] = 5.055;
            this.LArray[17][2] = 5486.778;
            this.LArray[18][0] = 98.0;
            this.LArray[18][1] = 0.89;
            this.LArray[18][2] = 6069.78;
            this.LArray[19][0] = 86.0;
            this.LArray[19][1] = 5.69;
            this.LArray[19][2] = 15720.84;
            this.LArray[20][0] = 86.0;
            this.LArray[20][1] = 1.27;
            this.LArray[20][2] = 161000.69;
            this.LArray[21][0] = 65.0;
            this.LArray[21][1] = 0.27;
            this.LArray[21][2] = 17260.15;
            this.LArray[22][0] = 63.0;
            this.LArray[22][1] = 0.92;
            this.LArray[22][2] = 529.69;
            this.LArray[23][0] = 57.0;
            this.LArray[23][1] = 2.01;
            this.LArray[23][2] = 83996.85;
            this.LArray[24][0] = 56.0;
            this.LArray[24][1] = 5.24;
            this.LArray[24][2] = 71430.7;
            this.LArray[25][0] = 49.0;
            this.LArray[25][1] = 3.25;
            this.LArray[25][2] = 2544.31;
            this.LArray[26][0] = 47.0;
            this.LArray[26][1] = 2.58;
            this.LArray[26][2] = 775.52;
            this.LArray[27][0] = 45.0;
            this.LArray[27][1] = 5.54;
            this.LArray[27][2] = 9437.76;
            this.LArray[28][0] = 43.0;
            this.LArray[28][1] = 6.01;
            this.LArray[28][2] = 6275.96;
            this.LArray[29][0] = 39.0;
            this.LArray[29][1] = 5.36;
            this.LArray[29][2] = 4694.0;
            this.LArray[30][0] = 38.0;
            this.LArray[30][1] = 2.39;
            this.LArray[30][2] = 8827.39;
            this.LArray[31][0] = 37.0;
            this.LArray[31][1] = 0.83;
            this.LArray[31][2] = 19651.05;
            this.LArray[32][0] = 37.0;
            this.LArray[32][1] = 4.9;
            this.LArray[32][2] = 12139.55;
            this.LArray[33][0] = 36.0;
            this.LArray[33][1] = 1.67;
            this.LArray[33][2] = 12036.46;
            this.LArray[34][0] = 35.0;
            this.LArray[34][1] = 1.84;
            this.LArray[34][2] = 2942.46;
            this.LArray[35][0] = 33.0;
            this.LArray[35][1] = 0.24;
            this.LArray[35][2] = 7084.9;
            this.LArray[36][0] = 32.0;
            this.LArray[36][1] = 0.18;
            this.LArray[36][2] = 5088.63;
            this.LArray[37][0] = 32.0;
            this.LArray[37][1] = 1.78;
            this.LArray[37][2] = 398.15;
            this.LArray[38][0] = 28.0;
            this.LArray[38][1] = 1.21;
            this.LArray[38][2] = 6286.6;
            this.LArray[39][0] = 28.0;
            this.LArray[39][1] = 1.9;
            this.LArray[39][2] = 6279.55;
            this.LArray[40][0] = 26.0;
            this.LArray[40][1] = 4.59;
            this.LArray[40][2] = 10447.39;
            double r0 = 0.0;
            for (int i = 1; i < 41; ++i) {
                r0 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
            }
            return r0;
        }
        
        double R1(final double t) {
            this.LArray[1][0] = 103019.0;
            this.LArray[1][1] = 1.10749;
            this.LArray[1][2] = 6283.07585;
            this.LArray[2][0] = 1721.0;
            this.LArray[2][1] = 1.0644;
            this.LArray[2][2] = 12566.1517;
            this.LArray[3][0] = 702.0;
            this.LArray[3][1] = 3.142;
            this.LArray[3][2] = 0.0;
            this.LArray[4][0] = 32.0;
            this.LArray[4][1] = 1.02;
            this.LArray[4][2] = 18849.23;
            this.LArray[5][0] = 31.0;
            this.LArray[5][1] = 2.84;
            this.LArray[5][2] = 5507.55;
            this.LArray[6][0] = 25.0;
            this.LArray[6][1] = 1.32;
            this.LArray[6][2] = 5223.69;
            this.LArray[7][0] = 18.0;
            this.LArray[7][1] = 1.42;
            this.LArray[7][2] = 1577.34;
            this.LArray[8][0] = 10.0;
            this.LArray[8][1] = 5.91;
            this.LArray[8][2] = 10977.08;
            this.LArray[9][0] = 9.0;
            this.LArray[9][1] = 1.42;
            this.LArray[9][2] = 6275.96;
            this.LArray[10][0] = 9.0;
            this.LArray[10][1] = 0.27;
            this.LArray[10][2] = 5486.78;
            double r1 = 0.0;
            for (int i = 1; i < 11; ++i) {
                r1 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
            }
            return r1;
        }
        
        double R2(final double t) {
            this.LArray[1][0] = 4359.0;
            this.LArray[1][1] = 5.7846;
            this.LArray[1][2] = 6283.0758;
            this.LArray[2][0] = 124.0;
            this.LArray[2][1] = 5.579;
            this.LArray[2][2] = 12566.152;
            this.LArray[3][0] = 12.0;
            this.LArray[3][1] = 3.14;
            this.LArray[3][2] = 0.0;
            this.LArray[4][0] = 9.0;
            this.LArray[4][1] = 3.63;
            this.LArray[4][2] = 77713.77;
            this.LArray[5][0] = 6.0;
            this.LArray[5][1] = 1.87;
            this.LArray[5][2] = 5573.14;
            this.LArray[6][0] = 3.0;
            this.LArray[6][1] = 5.47;
            this.LArray[6][2] = 18849.23;
            double r2 = 0.0;
            for (int i = 1; i < 7; ++i) {
                r2 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
            }
            return r2;
        }
        
        double R3(final double t) {
            this.LArray[1][0] = 145.0;
            this.LArray[1][1] = 4.273;
            this.LArray[1][2] = 6283.076;
            this.LArray[2][0] = 7.0;
            this.LArray[2][1] = 3.92;
            this.LArray[2][2] = 12566.15;
            double r3 = 0.0;
            for (int i = 1; i < 3; ++i) {
                r3 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
            }
            return r3;
        }
        
        double R4(final double t) {
            this.LArray[1][0] = 4.0;
            this.LArray[1][1] = 2.56;
            this.LArray[1][2] = 6283.08;
            double r4 = 0.0;
            for (int i = 1; i < 2; ++i) {
                r4 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
            }
            return r4;
        }
    }
}
