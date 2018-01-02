import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Button;
import java.awt.Choice;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class moon20 extends Applet
{
    String versStr;
    Date dat;
    int date;
    int month;
    int year;
    Choice yearChoice;
    Choice monthChoice;
    Choice dateChoice;
    Choice hourChoice;
    Button nowButton;
    Button dataButton;
    String yearStr;
    String myStr;
    boolean demo;
    boolean online;
    public String email;
    public String param;
    public String wwwStr;
    public String usrStr;
    int currentDate;
    int currentMonth;
    int currentYear;
    Image sky;
    String[] monthArray;
    String[] mArray;
    double UT;
    String[] dataStr;
    int dayFullMoon;
    int dayNewMoon;
    double jd2;
    double jd3;
    double currentJD;
    String hourStr;
    String[] dataString;
    String[] regent;
    String[] dayRegent;
    int links;
    int links1;
    int links2;
    int ep;
    int golden;
    String easterStr;
    String ageStr;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 8136;
    }
    
    public void init() {
        final Color background = new Color(150, 150, 150);
        this.setBackground(background);
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.mArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.sky = this.getImage(this.getDocumentBase(), "applet/fullmoon1.gif");
        final URL url = this.getDocumentBase();
        this.myStr = url.toString();
        this.myStr = String.valueOf(this.myStr) + "1234567890123456789012345";
        this.wwwStr = this.myStr.substring(0, 27);
        this.dat = new Date();
        this.year = this.dat.getYear() + 1900;
        this.month = this.dat.getMonth();
        this.date = this.dat.getDate();
        this.currentYear = this.year;
        this.currentMonth = this.month;
        this.currentDate = this.date;
        this.UT = this.dat.getHours() + this.dat.getTimezoneOffset() / 60.0;
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 10, 0, 0);
        this.monthChoice = new Choice();
        for (int i = 0; i < 12; ++i) {
            this.monthChoice.addItem(this.monthArray[i]);
        }
        this.monthChoice.select(this.month);
        gbl.setConstraints(this.monthChoice, gbc);
        this.add(this.monthChoice);
        gbc.insets = new Insets(15, 0, 0, 0);
        this.dateChoice = new Choice();
        for (int j = 1; j < 32; ++j) {
            this.dateChoice.addItem(String.valueOf(j));
        }
        this.dateChoice.select(this.date - 1);
        gbl.setConstraints(this.dateChoice, gbc);
        this.add(this.dateChoice);
        if (this.UT < 0.0) {
            this.UT += 24.0;
            --this.date;
            if (this.date < 1) {
                --this.month;
                if (this.month < 0) {
                    this.month = 11;
                    --this.year;
                    this.dat.setYear(this.year);
                    this.yearChoice.select(String.valueOf(this.year));
                }
                this.dat.setMonth(this.month);
                this.date = this.daysInMonth(this.month, this.year);
                this.dat.setDate(this.date);
                this.monthChoice.select(this.month);
            }
            this.dat.setDate(this.date);
            this.dateChoice.select(this.date - 1);
        }
        this.yearChoice = new Choice();
        for (int k = 0; k < 300; ++k) {
            this.yearStr = String.valueOf(this.year - 31 + k);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(this.year);
        this.yearChoice.select(this.yearStr);
        gbl.setConstraints(this.yearChoice, gbc);
        this.add(this.yearChoice);
        this.hourChoice = new Choice();
        for (int l = 0; l < 24; ++l) {
            this.hourChoice.addItem(String.valueOf(l));
        }
        this.hourChoice.select((int)this.UT);
        gbl.setConstraints(this.hourChoice, gbc);
        this.add(this.hourChoice);
        gbc.insets = new Insets(10, 0, 0, 0);
        gbl.setConstraints(this.nowButton = new Button("Now"), gbc);
        this.add(this.nowButton);
        gbc.insets = new Insets(10, 0, 0, 10);
        gbl.setConstraints(this.dataButton = new Button("Data"), gbc);
        this.add(this.dataButton);
        final Canvas can = new Canvas();
        gbc.gridy = 2;
        gbc.weighty = 160.0;
        gbl.setConstraints(can, gbc);
        this.add(can);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
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
    }
    
    public void dataNew() {
        final double[] jD = new double[15];
        int minNewMoon = 0;
        int hourNM = 0;
        final double[] delta = new double[122];
        int N = 0;
        String strD = "";
        String strM = "";
        double jd = this.JD(1, 1, this.year, 0.0);
        for (int n = -350; n < 3500; ++n) {
            if (Math.abs(2449128.59 + 29.53058867 * n - 0.3 - jd) < 30.0) {
                N = n;
                break;
            }
        }
        for (int n2 = N; n2 < N + 14; ++n2) {
            jd = 2449128.59 + 29.53058867 * n2 - 1.0;
            for (int i = 0; i <= 48; ++i) {
                final PlanetRiseSet planet = new PlanetRiseSet(jd + i / 24.0, 6);
                final double moonL = planet.heliocentricLambda();
                final double sunL = planet.lambda();
                double SunMoonExcess = 360.0 - (sunL - moonL);
                if (SunMoonExcess > 360.0) {
                    SunMoonExcess -= 360.0;
                }
                delta[i] = SunMoonExcess;
            }
            hourNM = 0;
            for (int j = 0; j < 48; ++j) {
                if (delta[j] > 359.0 && delta[j + 1] < 1.0) {
                    hourNM = j;
                    break;
                }
            }
            if (Math.abs(delta[hourNM + 1]) < Math.abs(delta[hourNM] - 360.0)) {
                ++hourNM;
            }
            --hourNM;
            for (int k = 0; k <= 120; ++k) {
                final PlanetRiseSet planet = new PlanetRiseSet(jd + hourNM / 24.0 + k / 1440.0, 6);
                final double moonL = planet.heliocentricLambda();
                final double sunL = planet.lambda();
                double SunMoonExcess = 360.0 - (sunL - moonL);
                if (SunMoonExcess > 360.0) {
                    SunMoonExcess -= 360.0;
                }
                delta[k] = SunMoonExcess;
            }
            minNewMoon = 0;
            for (int l = 0; l < 120; ++l) {
                if (delta[l] > 359.0 && delta[l + 1] < 1.0) {
                    minNewMoon = l;
                    break;
                }
            }
            if (Math.abs(360.0 - delta[minNewMoon]) < Math.abs(360.0 - delta[minNewMoon + 1])) {
                --minNewMoon;
            }
            strM = this.mArray[this.calDat(2, jd + hourNM / 24.0 + minNewMoon / 1440.0)];
            final int D = this.calDat(1, jd + hourNM / 24.0 + minNewMoon / 1440.0);
            if (D < 10) {
                strD = "0" + D;
            }
            else {
                strD = String.valueOf(D);
            }
            this.dataString[n2 - N] = String.valueOf(this.calDat(3, jd + hourNM / 24.0 + minNewMoon / 1440.0)) + " " + strM + " " + strD + " " + this.hourStr;
            jD[n2 - N] = jd + hourNM / 24.0 + minNewMoon / 1440.0;
        }
        this.dataString[0] = "© J. Giesen - www.GeoAstro.de \n" + this.dataString[0] + "\n";
        for (int n3 = 1; n3 < 14; ++n3) {
            final double lun = jD[n3] - jD[n3 - 1];
            final double T = (this.currentJD - 2451545.0) / 365.25;
            final double tLun = 29.5305888531 + 2.1621E-7 * T - 3.6400000000000003E-9 * T * T;
            final double d = Math.abs(tLun - lun);
            String pm;
            if (tLun < lun) {
                pm = " + ";
            }
            else {
                pm = " - ";
            }
            String str1 = String.valueOf(Math.round(1000.0 * lun) / 1000.0);
            if (str1.length() == 5) {
                str1 = String.valueOf(str1) + "0";
            }
            String str2 = String.valueOf((int)Math.floor(lun)) + " d " + this.HMS(this.frac(lun) * 24.0) + " = " + (int)Math.floor(tLun) + " d " + this.HMS(this.frac(tLun) * 24.0) + pm + this.HMS(this.frac(d) * 24.0);
            final String[] dataString = this.dataString;
            final int n4 = n3;
            dataString[n4] = String.valueOf(dataString[n4]) + "   Lun = " + str1 + " d = " + str2;
            final MoonDistance moonDist = new MoonDistance(jD[n3]);
            final EarthCompute earthComp = new EarthCompute(jD[n3]);
            str2 = "  R = " + Math.round(moonDist.computeR()) + " km";
            str2 = String.valueOf(str2) + "   " + Math.round(100000.0 * earthComp.earthR()) / 100000.0 + " AU";
            final String[] dataString2 = this.dataString;
            final int n5 = n3;
            dataString2[n5] = String.valueOf(dataString2[n5]) + str2 + "\n";
        }
        final scrollFrame sf = new scrollFrame("New Moon " + this.year, 14, this.dataString, this.demo, this.online);
        sf.resize(720, 220);
        sf.show();
        sf.setLocation(250, 250);
    }
    
    public void dataFull() {
        final double[] jD = new double[15];
        int minFullMoon = 0;
        int hourFM = 0;
        final double[] delta = new double[122];
        int N = 0;
        String strD = "";
        String strM = "";
        double jd = this.JD(1, 1, this.year, 0.0);
        for (int n = -350; n < 3500; ++n) {
            final double nHalf = 0.5 + n;
            if (Math.abs(2449128.59 + 29.53058867 * nHalf - 0.3 - jd) < 30.0) {
                N = n;
                break;
            }
        }
        double nHalf = 0.5 + N;
        for (int n2 = N; n2 < N + 14; ++n2) {
            nHalf = 0.5 + n2;
            jd = 2449128.59 + 29.53058867 * nHalf - 1.0;
            for (int i = 0; i <= 48; ++i) {
                final PlanetRiseSet planet = new PlanetRiseSet(jd + i / 24.0, 6);
                final double moonL = planet.heliocentricLambda();
                final double sunL = planet.lambda();
                double SunMoonExcess = 360.0 - (sunL - moonL);
                if (SunMoonExcess > 360.0) {
                    SunMoonExcess -= 360.0;
                }
                delta[i] = SunMoonExcess;
            }
            hourFM = 0;
            for (int j = 0; j < 48; ++j) {
                if (delta[j] < 180.0 && delta[j + 1] > 180.0) {
                    hourFM = j;
                    break;
                }
            }
            if (Math.abs(180.0 - delta[hourFM]) > Math.abs(180.0 - delta[hourFM + 1])) {
                ++hourFM;
            }
            --hourFM;
            for (int k = 0; k <= 120; ++k) {
                final PlanetRiseSet planet = new PlanetRiseSet(jd + hourFM / 24.0 + k / 1440.0, 6);
                final double moonL = planet.heliocentricLambda();
                final double sunL = planet.lambda();
                double SunMoonExcess = 360.0 - (sunL - moonL);
                if (SunMoonExcess > 360.0) {
                    SunMoonExcess -= 360.0;
                }
                delta[k] = SunMoonExcess;
            }
            minFullMoon = 0;
            for (int l = 0; l < 120; ++l) {
                if (delta[l] < 180.0 && delta[l + 1] > 180.0) {
                    minFullMoon = l;
                    break;
                }
            }
            if (Math.abs(180.0 - delta[minFullMoon]) > Math.abs(180.0 - delta[minFullMoon + 1])) {
                ++minFullMoon;
            }
            strM = this.mArray[this.calDat(2, jd + hourFM / 24.0 + minFullMoon / 1440.0)];
            final int D = this.calDat(1, jd + hourFM / 24.0 + minFullMoon / 1440.0);
            if (D < 10) {
                strD = "0" + D;
            }
            else {
                strD = String.valueOf(D);
            }
            this.dataString[n2 - N] = String.valueOf(this.calDat(3, jd + hourFM / 24.0 + minFullMoon / 1440.0)) + " " + strM + " " + strD + " " + this.hourStr;
            jD[n2 - N] = jd + hourFM / 24.0 + minFullMoon / 1440.0;
        }
        this.dataString[0] = "© J. Giesen - www.GeoAstro.de \n" + this.dataString[0] + "\n";
        final double[] moonD = new double[15];
        double minMoonD = 450000.0;
        double maxMoonD = 0.0;
        int minMoon = 0;
        int maxMoon = 0;
        for (int n3 = 1; n3 < 14; ++n3) {
            final MoonDistance moonDist = new MoonDistance(jD[n3]);
            moonD[n3] = moonDist.computeR();
        }
        for (int n4 = 1; n4 < 14; ++n4) {
            if (moonD[n4] < minMoonD) {
                minMoon = n4;
                minMoonD = moonD[n4];
            }
        }
        for (int n5 = 1; n5 < 14; ++n5) {
            if (moonD[n5] > maxMoonD) {
                maxMoon = n5;
                maxMoonD = moonD[n5];
            }
        }
        for (int n6 = 1; n6 < 14; ++n6) {
            final double lun = jD[n6] - jD[n6 - 1];
            final double T = (this.currentJD - 2451545.0) / 365.25;
            final double tLun = 29.5305888531 + 2.1621E-7 * T - 3.6400000000000003E-9 * T * T;
            final double d = Math.abs(tLun - lun);
            String pm;
            if (tLun < lun) {
                pm = " + ";
            }
            else {
                pm = " - ";
            }
            String str1 = String.valueOf(Math.round(1000.0 * lun) / 1000.0);
            if (str1.length() == 5) {
                str1 = String.valueOf(str1) + "0";
            }
            String str2 = String.valueOf((int)Math.floor(lun)) + " d " + this.HMS(this.frac(lun) * 24.0) + " = " + (int)Math.floor(tLun) + " d " + this.HMS(this.frac(tLun) * 24.0) + pm + this.HMS(this.frac(d) * 24.0);
            final String[] dataString = this.dataString;
            final int n7 = n6;
            dataString[n7] = String.valueOf(dataString[n7]) + "   Lun = " + str1 + " d = " + str2;
            final MoonDistance moonDist = new MoonDistance(jD[n6]);
            final EarthCompute earthComp = new EarthCompute(jD[n6]);
            str2 = "  R = " + Math.round(moonDist.computeR()) + " km";
            if (n6 == minMoon || n6 == maxMoon) {
                if (n6 == minMoon) {
                    str2 = String.valueOf(str2) + " min";
                }
                if (n6 == maxMoon) {
                    str2 = String.valueOf(str2) + " max";
                }
            }
            else {
                str2 = String.valueOf(str2) + "    ";
            }
            str2 = String.valueOf(str2) + "   " + Math.round(100000.0 * earthComp.earthR()) / 100000.0 + " AU";
            final String[] dataString2 = this.dataString;
            final int n8 = n6;
            dataString2[n8] = String.valueOf(dataString2[n8]) + str2 + "\n";
        }
        final scrollFrame sf = new scrollFrame("Full Moon " + this.year, 14, this.dataString, this.demo, this.online);
        sf.resize(750, 220);
        sf.show();
        sf.setLocation(350, 350);
    }
    
    public void easter(final int golden, final int ep) {
        int month = 0;
        int date = 0;
        if (ep < 13) {
            month = 4;
            date = 12 - ep;
        }
        if (ep > 12 && ep < 24) {
            month = 3;
            date = 31 - (ep - 13);
        }
        if (ep == 24) {
            month = 4;
            date = 18;
        }
        if (ep == 25) {
            month = 4;
            if (ep > 11) {
                date = 17;
            }
            else {
                date = 18;
            }
        }
        if (ep > 25) {
            month = 4;
            date = 17 - (ep - 26);
        }
        double jd = this.JD(date, month, this.year, 12.0);
        final String day = this.dayString(jd);
        System.out.println("Official Full Moon " + this.monthArray[month - 1] + " " + date + "  " + day);
        final int num = this.dayNum(jd);
        if (num == 6) {
            jd += 7.0;
        }
        else {
            jd += 6 - num;
        }
        date = this.calDat(1, jd);
        month = this.calDat(2, jd);
        this.easterStr = String.valueOf(this.monthArray[month]) + " " + date;
        System.out.println("Date of Easter " + this.easterStr);
    }
    
    public String dayString(final double jd) {
        final String[] dayArray = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        long num = (long)(jd + 0.5);
        num -= num / 7L * 7L;
        return dayArray[(int)num];
    }
    
    public boolean keyDown(final Event event, final int code) {
        if (code == 109 || code == 77) {
            if (code == 77) {
                --this.month;
            }
            else {
                ++this.month;
            }
            this.dat.setMonth(this.month);
            this.date = this.dat.getDate();
            final int n = this.daysInMonth(this.month, this.year);
            if (this.date > n) {
                this.date = 1;
                this.dat.setDate(this.date);
                this.dat.setMonth(this.month - 1);
            }
            this.month = this.dat.getMonth();
            this.monthChoice.select(this.month);
            this.dateChoice.select(this.date - 1);
            this.year = this.dat.getYear();
            this.year += 1900;
            this.yearStr = String.valueOf(this.year);
            this.yearChoice.select(this.yearStr);
            this.repaint();
            return true;
        }
        if (code == 100 || code == 68) {
            if (code == 68) {
                --this.date;
                if (this.date == 0) {
                    --this.month;
                    if (this.month == -1) {
                        this.month = 11;
                        --this.year;
                        this.dat.setYear(this.year);
                    }
                    this.date = this.daysInMonth(this.month, this.year);
                }
            }
            else {
                ++this.date;
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = 1;
                    ++this.month;
                    if (this.month == 12) {
                        this.month = 0;
                        ++this.year;
                        this.dat.setYear(this.year - 1900);
                    }
                }
            }
            this.dateChoice.select(this.date - 1);
            this.dat.setMonth(this.month);
            this.monthChoice.select(this.month);
            this.dat.setSeconds(0);
            this.dat.setDate(this.date);
            this.yearStr = String.valueOf(this.year);
            this.yearChoice.select(this.yearStr);
            this.repaint();
            return true;
        }
        if (code == 121 || code == 89) {
            if (code == 121) {
                ++this.year;
                if (this.date == 0) {
                    --this.month;
                    if (this.month == -1) {
                        this.month = 11;
                        --this.year;
                        this.dat.setYear(this.year);
                    }
                    this.date = this.daysInMonth(this.month, this.year);
                }
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(n);
                }
            }
            else {
                --this.year;
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(n);
                }
            }
            this.dateChoice.select(this.date - 1);
            this.dat.setMonth(this.month);
            this.monthChoice.select(this.month);
            this.dat.setDate(this.date);
            this.yearStr = String.valueOf(this.year);
            this.yearChoice.select(this.yearStr);
            this.repaint();
            return true;
        }
        return true;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Button) {
            if (event.target == this.nowButton) {
                this.dat = new Date();
                this.date = this.dat.getDate();
                this.UT = this.dat.getHours() + this.dat.getTimezoneOffset() / 60.0;
                if (this.UT < 0.0) {
                    this.UT += 24.0;
                    --this.date;
                    this.dat.setDate(this.date);
                    final int hours = (int)(this.UT - this.dat.getTimezoneOffset() / 60.0);
                    this.dat.setHours(hours);
                }
                this.dateChoice.select(this.date - 1);
                this.hourChoice.select((int)this.UT);
                this.month = this.dat.getMonth();
                this.monthChoice.select(this.dat.getMonth());
                this.year = this.dat.getYear() + 1900;
                this.yearStr = String.valueOf(this.year);
                this.yearChoice.select(this.yearStr);
                this.repaint();
                return true;
            }
            if (event.target == this.dataButton) {
                this.dataFull();
                this.dataNew();
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Choice) {
            if (event.target == this.hourChoice) {
                final String hourStr = this.hourChoice.getSelectedItem();
                int hours2 = Integer.parseInt(hourStr);
                hours2 -= (int)(this.dat.getTimezoneOffset() / 60.0);
                this.dat.setHours(hours2);
                this.UT = hours2 + this.dat.getTimezoneOffset() / 60.0;
                this.repaint();
                return true;
            }
            if (event.target == this.dateChoice) {
                final String dateStr = this.dateChoice.getSelectedItem();
                this.date = Integer.parseInt(dateStr);
                this.dat.setDate(this.date);
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.UT = this.dat.getHours() + this.dat.getTimezoneOffset() / 60.0;
                this.repaint();
                return true;
            }
            if (event.target == this.monthChoice) {
                final String monthStr = this.monthChoice.getSelectedItem();
                this.month = this.MonthInteger(monthStr);
                this.monthChoice.select(this.month);
                this.dat.setMonth(this.month);
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.UT = this.dat.getHours() + this.dat.getTimezoneOffset() / 60.0;
                this.repaint();
                return true;
            }
            if (event.target == this.yearChoice) {
                final String yearStr = this.yearChoice.getSelectedItem();
                this.year = Integer.parseInt(yearStr);
                this.yearChoice.select(yearStr);
                this.dat.setYear(this.year - 1900);
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.dat.setMonth(this.MonthInteger(this.monthChoice.getSelectedItem()));
                this.UT = this.dat.getHours() + this.dat.getTimezoneOffset() / 60.0;
                this.repaint();
                return true;
            }
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
        double hour = 24.0 * (jd + 0.5 - JD0);
        final double diff = Math.abs(hour) - (int)Math.abs(hour);
        int min = (int)Math.round(diff * 60.0);
        if (min == 60) {
            min = 0;
            ++hour;
        }
        String str;
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        this.hourStr = String.valueOf((int)hour) + str + min;
        if ((int)hour < 10) {
            this.hourStr = "0" + this.hourStr;
        }
        this.hourStr = String.valueOf(this.hourStr) + " UT";
        if (what == 2) {
            return month - 1;
        }
        if (what == 3) {
            return year;
        }
        return 0;
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
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public String HMS(double x) {
        String str = "";
        if (x < 0.0) {
            x = Math.abs(x);
        }
        int HOUR = (int)x;
        int MIN = (int)Math.round(this.frac(x) * 60.0);
        if (MIN == 60) {
            MIN = 0;
            ++HOUR;
        }
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
    
    public int epact(final int year) {
        final int century = year / 100 + 1;
        final int S = 3 * century / 4;
        final int L = (8 * century + 5) / 25;
        final int golden = year % 19 + 1;
        int epact = 11 * (golden - 1) % 30;
        epact = epact - S + L + 8;
        if (epact <= 0) {
            epact += 30;
        }
        if (epact > 30) {
            epact -= 30;
        }
        return epact;
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("Helvetica", 0, 9));
        g.setColor(Color.black);
        g.drawString(this.versStr + " - © 2005-2010 J. Giesen  --  www.GeoAstro.de", 90, this.size().height - 7);
        g.setFont(new Font("Helvetica", 1, 12));
        g.setColor(Color.white);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.drawRect(2, 2, this.size().width - 4, this.size().height - 4);
        final Color c = new Color(25, 25, 25);
        g.setColor(c);
        g.drawImage(this.sky, this.size().width / 2 - 60, 80, this);
        String s = String.valueOf(this.dat.getMinutes());
        if (this.dat.getMinutes() < 10) {
            s = "0" + s;
        }
        s = ":00 UT";
        if (this.date == this.currentDate && this.month == this.currentMonth && this.year == this.currentYear) {
            g.drawString("The Moon now:  " + this.year + " " + this.dayString(this.currentJD) + "  " + this.monthArray[this.month] + " " + this.date + "  at   " + (int)this.UT + s + "  (" + this.dayRegent[this.dayNum(this.currentJD)] + ")", 80, 65);
        }
        else {
            g.drawString("The Moon on  " + this.year + " " + this.dayString(this.currentJD) + "  " + this.monthArray[this.month] + " " + this.date + "  at   " + (int)this.UT + s + "  (" + this.dayRegent[this.dayNum(this.currentJD)] + ")", 80, 65);
        }
        g.setFont(new Font("Helvetica", 0, 12));
        this.currentJD = this.JD(this.date, this.month + 1, this.year, this.UT);
        final Moon moon1 = new Moon(this.JD(this.date, this.month + 1, this.year, this.UT));
        final double lambda = moon1.lambda();
        final double beta = moon1.beta();
        final PlanetRiseSet planetRS = new PlanetRiseSet(this.currentJD, 0);
        final double earthGeoLambda = planetRS.lambda();
        double waxwan = lambda - earthGeoLambda;
        if (waxwan < 0.0) {
            waxwan += 360.0;
        }
        final int moonRadius = 59;
        final int xMoon = this.size().width / 2;
        final int yMoon = 140;
        this.fullMoon(this.JD(this.date, this.month + 1, this.year, 0.0));
        this.newMoon(this.JD(this.date, this.month + 1, this.year, 0.0));
        this.nextNewMoon(this.JD(this.date, this.month + 1, this.year, 0.0));
        if (this.jd2 > this.currentJD) {
            this.newMoon(this.JD(this.date, this.month + 1, this.year, 0.0) - 10.0);
            this.nextNewMoon(this.JD(this.date, this.month + 1, this.year, 0.0) - 10.0);
        }
        final double lun = this.jd3 - this.jd2;
        final double T = (this.currentJD - 2451545.0) / 365.25;
        final double tLun = 29.5305888531 + 2.1621E-7 * T - 3.6400000000000003E-9 * T * T;
        final double d = Math.abs(tLun - lun);
        String pm = "";
        if (tLun < lun) {
            pm = " + ";
        }
        else {
            pm = " - ";
        }
        final double age = this.currentJD - this.jd2;
        this.ageStr = (int)Math.floor(age) + " d " + this.HMS(this.frac(age) * 24.0);
        final double pminus = this.phase(this.currentJD - 0.041666666666666664);
        final double pplus = this.phase(this.currentJD + 0.041666666666666664);
        String iStr;
        if (pplus > pminus) {
            iStr = "(+)";
        }
        else {
            iStr = "(-)";
        }
        final String illumStr = Math.round(1000.0 * this.phase(this.currentJD)) / 10.0 + " %  " + iStr;
        g.drawString("Illuminated: " + illumStr, this.links, 215);
        g.drawString("Age: " + this.ageStr, this.links1, 215);
        g.drawString("Previous New Moon:", this.links, 235);
        g.drawString(this.dataStr[3], this.links1, 235);
        g.drawString(this.dataStr[4], this.links2, 235);
        g.drawString("Previous Full Moon:", this.links, 250);
        g.drawString(this.dataStr[1], this.links1, 250);
        g.drawString(this.dataStr[2], this.links2, 250);
        g.drawString("Next New Moon:", this.links, 265);
        g.drawString(this.dataStr[5], this.links1, 265);
        g.drawString(this.dataStr[6], this.links2, 265);
        this.golden = this.year % 19 + 1;
        this.ep = this.epact(this.year);
        this.easter(this.golden, this.ep);
        g.drawString("Golden Number, Epact:", this.links, 280);
        g.drawString(this.golden + ",  " + this.ep, this.links1, 280);
        g.drawString("Easter Date:", this.links, 295);
        g.drawString(this.easterStr, this.links1, 295);
        String str = (int)Math.floor(lun) + " d " + this.HMS(this.frac(lun) * 24.0) + " = " + Math.round(10000.0 * lun) / 10000.0 + " d";
        g.drawString("Synod. Period:", this.links, 310);
        g.drawString(str, this.links1, 310);
        str = (int)Math.floor(tLun) + " d " + this.HMS(this.frac(tLun) * 24.0) + pm + this.HMS(this.frac(d) * 24.0);
        g.drawString(str, this.links1, 325);
        final MoonCompute mc = new MoonCompute(this.currentJD);
        final double L = mc.lambda();
        g.drawString("Position:", this.links, 340);
        g.drawString(String.valueOf(this.tierkreisStr(L)) + "  " + (int)(L % 30.0) + "° " + (int)Math.round(60.0 * this.frac(L)) + "'", this.links1, 340);
        final double w = waxwan / 90.0;
        if (waxwan <= 90.0) {
            for (int i = -moonRadius; i <= moonRadius; ++i) {
                final double x1 = Math.sqrt(moonRadius * moonRadius - i * i);
                final int X1 = xMoon - (int)Math.round(x1);
                final double x2 = (1.0 - w) * moonRadius * Math.sqrt(1.0 - i * i / (moonRadius * moonRadius));
                final int X2 = xMoon + (int)Math.round(x2);
                g.drawLine(X1, yMoon - i, X2, yMoon - i);
            }
        }
        if (waxwan > 90.0 && waxwan <= 180.0) {
            for (int i = -moonRadius; i <= moonRadius; ++i) {
                final double x1 = Math.sqrt(moonRadius * moonRadius - i * i);
                final int X1 = xMoon - (int)Math.round(x1);
                final double x2 = (w - 1.0) * moonRadius * Math.sqrt(1.0 - i * i / (moonRadius * moonRadius));
                final int X2 = xMoon - (int)Math.round(x2);
                g.drawLine(X1, yMoon - i, X2, yMoon - i);
            }
        }
        if (waxwan > 180.0 && waxwan <= 270.0) {
            for (int i = -moonRadius; i <= moonRadius; ++i) {
                final double x1 = Math.sqrt(moonRadius * moonRadius - i * i);
                final int X1 = xMoon + (int)Math.round(x1);
                final double x2 = x1 - (w - 2.0) * moonRadius * Math.sqrt(1.0 - i * i / (moonRadius * moonRadius));
                final int X2 = xMoon + (int)Math.round(x2);
                g.drawLine(X1, yMoon - i, X2, yMoon - i);
            }
        }
        if (waxwan > 270.0) {
            for (int i = -moonRadius; i <= moonRadius; ++i) {
                final double x1 = Math.sqrt(moonRadius * moonRadius - i * i);
                final int X1 = xMoon + (int)Math.round(x1);
                final double x2 = (w - 3.0) * moonRadius * Math.sqrt(1.0 - i * i / (moonRadius * moonRadius));
                final int X2 = xMoon - (int)Math.round(x2);
                g.drawLine(X1, yMoon - i, X2, yMoon - i);
            }
        }
        if (this.demo) {
            final Font f = g.getFont();
            g.setFont(new Font("Chicago", 0, 72));
            g.setColor(Color.red);
            g.drawString("D E M O", 20, 140);
            g.drawString("D E M O", 20, 230);
            g.setFont(f);
        }
    }
    
    public void fullMoon(final double jd) {
        final double[] delta = new double[122];
        int hourFullMoon = 0;
        int monthFullMoon = 12;
        int minFullMoon = 100;
        int dayFM = 0;
        this.dayFullMoon = 0;
        for (int i = 0; i <= 31; ++i) {
            final PlanetRiseSet planet = new PlanetRiseSet(jd + 1.0 - i, 6);
            final double moonL = planet.heliocentricLambda();
            final double sunL = planet.lambda();
            double SunMoonExcess = 360.0 - (sunL - moonL);
            if (SunMoonExcess > 360.0) {
                SunMoonExcess -= 360.0;
            }
            delta[i] = SunMoonExcess;
        }
        for (int j = 0; j < 31; ++j) {
            if (delta[j] > 180.0 && delta[j + 1] < 180.0) {
                dayFM = j;
                break;
            }
        }
        this.dayFullMoon = this.calDat(1, jd - dayFM);
        for (int k = 0; k <= 24; ++k) {
            final PlanetRiseSet planet = new PlanetRiseSet(jd - dayFM + k / 24.0, 6);
            final double moonL = planet.heliocentricLambda();
            final double sunL = planet.lambda();
            double SunMoonExcess = 360.0 - (sunL - moonL);
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
        if (Math.abs(delta[hourFullMoon] - 180.0) < Math.abs(delta[hourFullMoon + 1] - 180.0)) {
            ++hourFullMoon;
        }
        --hourFullMoon;
        for (int m = 0; m <= 120; ++m) {
            final PlanetRiseSet planet = new PlanetRiseSet(jd - dayFM + hourFullMoon / 24.0 + m / 1440.0, 6);
            final double moonL = planet.heliocentricLambda();
            final double sunL = planet.lambda();
            double SunMoonExcess = 360.0 - (sunL - moonL);
            if (SunMoonExcess > 360.0) {
                SunMoonExcess -= 360.0;
            }
            delta[m] = SunMoonExcess;
        }
        minFullMoon = 0;
        for (int i2 = 0; i2 < 120; ++i2) {
            if (delta[i2] < 180.0 && delta[i2 + 1] > 180.0) {
                minFullMoon = i2;
                break;
            }
        }
        if (Math.abs(180.0 - delta[minFullMoon]) < Math.abs(180.0 - delta[minFullMoon + 1])) {
            --minFullMoon;
        }
        this.dayFullMoon = this.calDat(1, jd - dayFM + hourFullMoon / 24.0 + minFullMoon / 1440.0);
        monthFullMoon = this.calDat(2, jd - dayFM + hourFullMoon / 24.0 + minFullMoon / 1440.0);
        this.dataStr[1] = this.monthArray[monthFullMoon] + " ";
        if (this.dayFullMoon < 10) {
            final String[] dataStr = this.dataStr;
            final int n = 1;
            dataStr[n] = String.valueOf(dataStr[n]) + "0" + this.dayFullMoon;
        }
        else {
            final String[] dataStr2 = this.dataStr;
            final int n2 = 1;
            dataStr2[n2] = String.valueOf(dataStr2[n2]) + this.dayFullMoon;
        }
        this.dataStr[2] = "at  " + this.hourStr;
    }
    
    public void newMoon(final double jd) {
        final double[] delta = new double[122];
        int hourNewMoon = 0;
        int dayNM = 0;
        for (int i = 0; i <= 31; ++i) {
            final PlanetRiseSet planet = new PlanetRiseSet(jd + 1.0 - i, 6);
            final double moonL = planet.heliocentricLambda();
            final double sunL = planet.lambda();
            double SunMoonExcess = 360.0 - (sunL - moonL);
            if (SunMoonExcess > 360.0) {
                SunMoonExcess -= 360.0;
            }
            delta[i] = SunMoonExcess;
        }
        for (int j = 0; j <= 31; ++j) {
            if (delta[j] < 15.0 && delta[j + 1] > 345.0) {
                dayNM = j;
                break;
            }
        }
        this.dayNewMoon = this.calDat(1, jd - dayNM);
        for (int k = 0; k <= 24; ++k) {
            final PlanetRiseSet planet = new PlanetRiseSet(jd - dayNM + k / 24.0, 6);
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
        for (int m = 0; m <= 120; ++m) {
            final PlanetRiseSet planet = new PlanetRiseSet(jd - dayNM + hourNewMoon / 24.0 + m / 1440.0, 6);
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
        if (Math.abs(360.0 - delta[minNewMoon]) < Math.abs(360.0 - delta[minNewMoon + 1])) {
            --minNewMoon;
        }
        this.dayNewMoon = this.calDat(1, jd - dayNM + hourNewMoon / 24.0 + minNewMoon / 1440.0);
        final int monthNewMoon = this.calDat(2, jd - dayNM + hourNewMoon / 24.0 + minNewMoon / 1440.0);
        this.dataStr[3] = this.monthArray[monthNewMoon] + " ";
        if (this.dayNewMoon < 10) {
            final String[] dataStr = this.dataStr;
            final int n = 3;
            dataStr[n] = String.valueOf(dataStr[n]) + "0" + this.dayNewMoon;
        }
        else {
            final String[] dataStr2 = this.dataStr;
            final int n2 = 3;
            dataStr2[n2] = String.valueOf(dataStr2[n2]) + this.dayNewMoon;
        }
        this.dataStr[4] = "at  " + this.hourStr;
        this.jd2 = jd - dayNM + hourNewMoon / 24.0 + minNewMoon / 1440.0;
    }
    
    public void nextNewMoon(final double jd) {
        final double[] delta = new double[122];
        int hourNewMoon = 0;
        for (int i = 1; i <= 31; ++i) {
            final PlanetRiseSet planet = new PlanetRiseSet(jd + i, 6);
            final double moonL = planet.heliocentricLambda();
            final double sunL = planet.lambda();
            double SunMoonExcess = 360.0 - (sunL - moonL);
            if (SunMoonExcess > 360.0) {
                SunMoonExcess -= 360.0;
            }
            delta[i] = SunMoonExcess;
        }
        int dayNM = 0;
        for (int j = 0; j <= 31; ++j) {
            if (delta[j] > 345.0 && delta[j + 1] < 15.0) {
                dayNM = j;
                break;
            }
        }
        this.dayNewMoon = this.calDat(1, jd + dayNM);
        for (int k = 0; k <= 24; ++k) {
            final PlanetRiseSet planet = new PlanetRiseSet(jd + dayNM + k / 24.0, 6);
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
        for (int m = 0; m <= 120; ++m) {
            final PlanetRiseSet planet = new PlanetRiseSet(jd + dayNM + hourNewMoon / 24.0 + m / 1440.0, 6);
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
        if (Math.abs(360.0 - delta[minNewMoon]) < Math.abs(360.0 - delta[minNewMoon + 1])) {
            --minNewMoon;
        }
        this.dayNewMoon = this.calDat(1, jd + dayNM + hourNewMoon / 24.0 + minNewMoon / 1440.0);
        final int monthNewMoon = this.calDat(2, jd + dayNM + hourNewMoon / 24.0 + minNewMoon / 1440.0);
        this.dataStr[5] = this.monthArray[monthNewMoon] + " ";
        if (this.dayNewMoon < 10) {
            final String[] dataStr = this.dataStr;
            final int n = 5;
            dataStr[n] = String.valueOf(dataStr[n]) + "0" + this.dayNewMoon;
        }
        else {
            final String[] dataStr2 = this.dataStr;
            final int n2 = 5;
            dataStr2[n2] = String.valueOf(dataStr2[n2]) + this.dayNewMoon;
        }
        this.dataStr[6] = "at  " + this.hourStr;
        this.jd3 = jd + dayNM + hourNewMoon / 24.0 + minNewMoon / 1440.0;
    }
    
    double phase(final double jd) {
        final double T = (jd - 2451545.0) / 36525.0;
        final double K = 0.017453292519943295;
        final double D = 297.8502042 + 445267.1115168 * T - 0.00163 * T * T + T * T * T / 545868.0 - T * T * T * T / 1.13065E8;
        final double M = 357.5291092 + 35999.0502909 * T - 1.536E-4 * T * T + T * T * T / 2.449E7;
        final double Ms = 134.9634114 + 477198.8676313 * T + 0.008997 * T * T + T * T * T / 69699.0 - T * T * T * T / 1.4712E7;
        final double i = 180.0 - D - 6.289 * Math.sin(K * Ms) + 2.1 * Math.sin(K * M) - 1.274 * Math.sin(K * (2.0 * D - Ms)) - 0.658 * Math.sin(K * 2.0 * D) - 0.214 * Math.sin(K * 2.0 * Ms) - 0.11 * Math.sin(K * D);
        return 0.5 * (1.0 + Math.cos(K * i));
    }
    
    double phase1() {
        final double T = (this.currentJD - 2451545.0) / 36525.0;
        final double K = 0.017453292519943295;
        final double D = 297.8502042 + 445267.1115168 * T - 0.00163 * T * T + T * T * T / 545868.0 - T * T * T * T / 1.13065E8;
        final double M = 357.5291092 + 35999.0502909 * T - 1.536E-4 * T * T + T * T * T / 2.449E7;
        final double Ms = 134.9634114 + 477198.8676313 * T + 0.008997 * T * T + T * T * T / 69699.0 - T * T * T * T / 1.4712E7;
        final double i = 180.0 - D - 6.289 * Math.sin(K * Ms) + 2.1 * Math.sin(K * M) - 1.274 * Math.sin(K * (2.0 * D - Ms)) - 0.658 * Math.sin(K * 2.0 * D) - 0.214 * Math.sin(K * 2.0 * Ms) - 0.11 * Math.sin(K * D);
        double x = Math.abs(i % 360.0) - 180.0;
        if (x < 0.0) {
            x = Math.abs(x);
        }
        return x;
    }
    
    public String tierkreisStr(double longitudo) {
        if (longitudo < 0.0) {
            longitudo += 360.0;
        }
        longitudo %= 360.0;
        final String[] TK = { "Ari", "Tau", "Gem", "Can", "Leo", "Vir", "Lib", "Sco", "Sag", "Cap", "Aqu", "Pis" };
        return TK[(int)(longitudo / 30.0)];
    }
    
    public String dayName(final double jd) {
        final String[] dayArray = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        long num = (long)(jd + 0.5);
        num -= num / 7L * 7L;
        return dayArray[(int)num];
    }
    
    public int dayNum(final double jd) {
        long num = (long)(jd + 0.5);
        num -= num / 7L * 7L;
        return (int)num;
    }
    
    public moon20() {
        this.versStr = "Moon 2.0";
        this.demo = true;
        this.online = false;
        this.monthArray = new String[12];
        this.mArray = new String[12];
        this.dataStr = new String[7];
        this.hourStr = "";
        this.dataString = new String[20];
        this.regent = new String[] { "Saturn", "Jupiter", "Mars", "Sun", "Venus", "Mercury", "Moon" };
        this.dayRegent = new String[] { "Moon", "Mars", "Mercury", "Jupiter", "Venus", "Saturn", "Sun" };
        this.links = 90;
        this.links1 = 240;
        this.links2 = 300;
    }
}
