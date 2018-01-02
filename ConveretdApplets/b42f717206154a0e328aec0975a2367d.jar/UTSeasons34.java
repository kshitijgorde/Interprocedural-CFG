import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Component;
import java.awt.Color;
import java.awt.Button;
import java.awt.Choice;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UTSeasons34 extends Applet
{
    String versStr;
    computeSeasons mySeasons;
    computeSeasons myNextSeasons;
    Date dat;
    int year;
    Choice yearChoice;
    Button writeButton;
    String yearStr;
    public String email;
    public String param;
    public String wwwStr;
    public String myStr;
    public String userString;
    public String usrStr;
    boolean demo;
    boolean online;
    int counter;
    int spring19;
    int spring20;
    int spring21;
    int summer20;
    int summer21;
    int summer22;
    int autumn21;
    int autumn22;
    int autumn23;
    int winter20;
    int winter21;
    int winter22;
    int daySpring;
    int daySummer;
    int dayAutumn;
    int dayWinter;
    double D;
    String perihelStr;
    String aphelStr;
    int copyrightYear;
    String[] dataStr;
    scrollFrame sf;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 1262;
    }
    
    public void init() {
        this.counter = 0;
        this.setBackground(Color.white);
        this.dat = new Date();
        this.year = this.dat.getYear() + 1900;
        this.copyrightYear = this.year;
        if (this.copyrightYear < 2003) {
            this.copyrightYear = 2003;
        }
        this.yearChoice = new Choice();
        for (int i = 0; i < 201; ++i) {
            this.yearStr = String.valueOf(this.year - 102 + i);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(this.year);
        this.yearChoice.select(this.yearStr);
        this.add(this.yearChoice);
        this.add(this.writeButton = new Button("Write"));
        this.mySeasons = new computeSeasons(this.year);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        final URL url = this.getDocumentBase();
        this.myStr = url.toString();
        this.myStr = String.valueOf(this.myStr) + "1234567890123456789012345";
        this.wwwStr = this.myStr.substring(0, 27);
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
    
    public boolean keyDown(final Event event, final int code) {
        if (this.demo) {
            return true;
        }
        if (code == 121) {
            ++this.counter;
            if (this.daySpring == 19) {
                ++this.spring19;
            }
            if (this.daySpring == 20) {
                ++this.spring20;
            }
            if (this.daySpring == 21) {
                ++this.spring21;
            }
            if (this.daySummer == 20) {
                ++this.summer20;
            }
            if (this.daySummer == 21) {
                ++this.summer21;
            }
            if (this.daySummer == 22) {
                ++this.summer22;
            }
            if (this.dayAutumn == 21) {
                ++this.autumn21;
            }
            if (this.dayAutumn == 22) {
                ++this.autumn22;
            }
            if (this.dayAutumn == 23) {
                ++this.autumn23;
            }
            if (this.dayWinter == 20) {
                ++this.winter20;
            }
            if (this.dayWinter == 21) {
                ++this.winter21;
            }
            if (this.dayWinter == 22) {
                ++this.winter22;
            }
            System.out.println("count=" + this.counter + "  " + this.year);
            System.out.println("count=" + this.counter + "  " + this.D);
            System.out.println("march 19=" + this.spring19 + "     march 20=" + this.spring20 + "   march 21=" + this.spring21);
            System.out.println("june  20=" + this.summer20 + "     june  21=" + this.summer21 + "   june  22=" + this.summer22);
            System.out.println("sept  21=" + this.autumn21 + "     sept  22=" + this.autumn22 + "   sept  23=" + this.autumn23);
            System.out.println("dec   20=" + this.winter20 + "     dec   21=" + this.winter21 + "   dec   22=" + this.winter22);
            ++this.year;
            this.mySeasons = new computeSeasons(this.year);
            this.yearStr = String.valueOf(this.year);
            this.yearChoice.select(this.yearStr);
            this.repaint();
        }
        if (code == 89) {
            ++this.counter;
            --this.year;
            this.mySeasons = new computeSeasons(this.year);
            this.yearStr = String.valueOf(this.year);
            this.yearChoice.select(this.yearStr);
            this.repaint();
        }
        return true;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Choice && event.target == this.yearChoice) {
            if (this.demo) {
                this.yearChoice.select(this.yearStr);
                return true;
            }
            this.yearStr = this.yearChoice.getSelectedItem();
            this.year = Integer.parseInt(this.yearStr);
            System.out.println("YEAR  " + this.year);
            this.yearChoice.select(this.yearStr);
            this.mySeasons = new computeSeasons(this.year);
            this.repaint();
            if (this.sf != null) {
                this.sf.schreiben(this.dataStr, 8, this.online);
            }
            return true;
        }
        else {
            if (event.target instanceof Button) {
                boolean demoVersion = false;
                if (this.online || this.demo) {
                    demoVersion = true;
                }
                (this.sf = new scrollFrame("Seasons " + this.year, 8, this.dataStr, demoVersion)).resize(450, 200);
                this.sf.show();
                return true;
            }
            return true;
        }
    }
    
    public void Perihelion() {
        double minR = 10.0;
        int d = 0;
        double h = 100.0;
        for (int i = 1; i < 6; ++i) {
            final EarthCompute earthComp = new EarthCompute(i, 1, this.year, 12.0);
            final double R = earthComp.earthR();
            if (R < minR) {
                minR = R;
                d = i;
            }
        }
        minR = 10.0;
        --d;
        for (int j = 0; j < 49; ++j) {
            final EarthCompute earthComp = new EarthCompute(d, 1, this.year, j);
            final double R = earthComp.earthR();
            if (R < minR) {
                minR = R;
                h = j;
            }
        }
        if (h >= 24.0) {
            ++d;
            h -= 24.0;
        }
        --h;
        double R = 10.0;
        int min = 0;
        for (int k = 0; k < 121; ++k) {
            final EarthCompute earthComp = new EarthCompute(d, 1, this.year, h + k / 60.0);
            R = earthComp.earthR();
            if (R < minR) {
                minR = R;
                min = k;
            }
        }
        h += min / 60.0;
        if (h >= 24.0) {
            h -= 24.0;
            ++d;
        }
        EarthCompute earthComp = new EarthCompute(d, 1, this.year, h);
        double R2 = earthComp.earthR();
        earthComp = new EarthCompute(d, 1, this.year, h - 1.0);
        final double RM = earthComp.earthR();
        earthComp = new EarthCompute(d, 1, this.year, h + 1.0);
        final double RP = earthComp.earthR();
        final double minFrac = this.Quad(R2, RM, RP, 1.0);
        h += minFrac;
        final int hour = (int)Math.round(h);
        earthComp = new EarthCompute(d, 1, this.year, h);
        R2 = earthComp.earthR();
        if (h > 9.0) {
            this.perihelStr = "Perihelion:  Jan 0" + d + " at " + hour + " UT    R= " + Math.round(1000000.0 * R2) / 1000000.0 + " AU";
            this.dataStr[7] = "Perihelion:  Jan 0" + d + " at " + hour + " UT    R= " + Math.round(1000000.0 * R2) / 1000000.0 + " AU" + "\n";
        }
        else {
            this.perihelStr = "Perihelion:  Jan 0" + d + " at 0" + hour + " UT    R= " + Math.round(1000000.0 * R2) / 1000000.0 + " AU";
            this.dataStr[7] = "Perihelion:  Jan 0" + d + " at 0" + hour + " UT    R= " + Math.round(1000000.0 * R2) / 1000000.0 + " AU" + "\n";
        }
        System.out.println("Perihelion:  Jan 0" + d + " at " + this.makeHMString(h) + "   R=" + Math.round(1000000.0 * R2) / 1000000.0 + " AU");
    }
    
    public void Aphelion() {
        double minR = 0.0;
        int d = 0;
        double h = 100.0;
        for (int i = 3; i < 7; ++i) {
            final EarthCompute earthComp = new EarthCompute(i, 7, this.year, 12.0);
            final double R = earthComp.earthR();
            if (R > minR) {
                minR = R;
                d = i;
            }
        }
        minR = 0.0;
        --d;
        for (int j = 0; j < 49; ++j) {
            final EarthCompute earthComp = new EarthCompute(d, 7, this.year, j);
            final double R = earthComp.earthR();
            if (R > minR) {
                minR = R;
                h = j;
            }
        }
        if (h >= 24.0) {
            ++d;
            h -= 24.0;
        }
        --h;
        double R = 0.0;
        int min = 0;
        for (int k = 0; k < 121; ++k) {
            final EarthCompute earthComp = new EarthCompute(d, 7, this.year, h + k / 60.0);
            R = earthComp.earthR();
            if (R > minR) {
                minR = R;
                min = k;
            }
        }
        h += min / 60.0;
        if (h >= 24.0) {
            h -= 24.0;
            ++d;
        }
        EarthCompute earthComp = new EarthCompute(d, 7, this.year, h);
        double R2 = earthComp.earthR();
        earthComp = new EarthCompute(d, 7, this.year, h - 1.0);
        final double RM = earthComp.earthR();
        earthComp = new EarthCompute(d, 7, this.year, h + 1.0);
        final double RP = earthComp.earthR();
        final double minFrac = this.Quad(R2, RM, RP, 1.0);
        h += minFrac;
        final int hour = (int)Math.round(h);
        earthComp = new EarthCompute(d, 7, this.year, h);
        R2 = earthComp.earthR();
        if (h > 9.0) {
            this.aphelStr = "Aphelion:    Jul 0" + d + " at " + hour + " UT    R= " + Math.round(1000000.0 * R2) / 1000000.0 + " AU";
            this.dataStr[8] = "Aphelion:    Jul 0" + d + " at " + hour + " UT    R= " + Math.round(1000000.0 * R2) / 1000000.0 + " AU" + "\n";
        }
        else {
            this.aphelStr = "Aphelion:    Jul 0" + d + " at 0" + hour + " UT    R= " + Math.round(1000000.0 * R2) / 1000000.0 + " AU";
            this.dataStr[8] = "Aphelion:    Jul 0" + d + " at 0" + hour + " UT    R= " + Math.round(1000000.0 * R2) / 1000000.0 + " AU" + "\n";
        }
        System.out.println("Aphelion:    Jul 0" + d + " at " + this.makeHMString(h) + "   R=" + Math.round(1000000.0 * R2) / 1000000.0 + " AU");
    }
    
    public double Quad(final double y0, final double yM, final double yP, final double dX) {
        final double x1 = -dX;
        final double a = (yM * dX - yP * x1 - y0 * (dX - x1)) / (x1 * x1 * dX - dX * dX * x1);
        final double b = (yM - y0 - a * x1 * x1) / x1;
        return -b / (2.0 * a);
    }
    
    public String makeDMSString(final double time) {
        final String str = "?";
        final int d = (int)time;
        final int h = (int)(24.0 * (time - d));
        final int m = (int)Math.round(1440.0 * (time - d) - 60 * h);
        String minStr;
        if (m >= 10) {
            minStr = String.valueOf(m) + " min";
        }
        else {
            minStr = "0" + m + " min";
        }
        return d + " d  " + h + " h  " + minStr;
    }
    
    public String makeHMString(final double time) {
        final int h = (int)time;
        final int m = (int)Math.round(60.0 * (time - h));
        String hStr;
        if (h < 9) {
            hStr = "0" + h;
        }
        else {
            hStr = String.valueOf(h);
        }
        String minStr;
        if (m < 9) {
            minStr = ":0" + m;
        }
        else {
            minStr = ":" + m;
        }
        return String.valueOf(hStr) + minStr + " UT";
    }
    
    public void paint(final Graphics g) {
        System.out.println("Seasons" + this.versStr + " -  (C) 2002-" + this.copyrightYear + " J. Giesen  -  www.GeoAstro.de");
        this.dataStr[0] = "Seasons" + this.versStr + " -  © 2002-" + this.copyrightYear + " J. Giesen  -  www.GeoAstro.de" + "\n";
        final int oben = 50;
        final int left = 20;
        g.setFont(new Font("Courier", 0, 12));
        g.setColor(Color.red);
        g.drawString("Solstices and Equinoxes", left + 43, oben);
        g.drawString("Duration", 250, oben);
        final String[] dataStr = this.dataStr;
        final int n = 0;
        dataStr[n] = String.valueOf(dataStr[n]) + "Solstices and Equinoxes " + this.year + "          Duration" + "\n";
        g.setColor(Color.black);
        g.drawString("Spring:  " + this.mySeasons.spring(), left, oben + 20);
        this.daySpring = this.mySeasons.intDay();
        this.D = this.mySeasons.fracDay();
        System.out.println(String.valueOf(this.year));
        System.out.println("Spring: March " + this.D + " (at " + this.makeHMString(24.0 * (this.D - (int)this.D)) + ")");
        g.drawString("Summer:  " + this.mySeasons.summer(), left, oben + 40);
        this.daySummer = this.mySeasons.intDay();
        this.D = this.mySeasons.fracDay();
        System.out.println("Summer: June  " + this.D + " (at " + this.makeHMString(24.0 * (this.D - (int)this.D)) + ")");
        g.drawString("Autumn:  " + this.mySeasons.autumn(), left, oben + 60);
        this.dayAutumn = this.mySeasons.intDay();
        this.D = this.mySeasons.fracDay();
        System.out.println("Autumn: Sept  " + this.D + " (at " + this.makeHMString(24.0 * (this.D - (int)this.D)) + ")");
        g.drawString("Winter:  " + this.mySeasons.winter(), left, oben + 80);
        this.dayWinter = this.mySeasons.intDay();
        this.D = this.mySeasons.fracDay();
        System.out.println("Winter: Dec   " + this.D + " (at " + this.makeHMString(24.0 * (this.D - (int)this.D)) + ")");
        g.drawString(String.valueOf(this.makeDMSString(this.mySeasons.springDuration())), 250, oben + 20);
        g.drawString(String.valueOf(this.makeDMSString(this.mySeasons.summerDuration())), 250, oben + 40);
        g.drawString(String.valueOf(this.makeDMSString(this.mySeasons.autumnDuration())), 250, oben + 60);
        this.myNextSeasons = new computeSeasons(this.year + 1);
        final String dummy = this.myNextSeasons.spring();
        final double winterDuration = this.myNextSeasons.marchJD() - this.mySeasons.decemberJD();
        g.drawString(String.valueOf(this.makeDMSString(winterDuration)), 250, oben + 80);
        this.dataStr[1] = "Spring:  " + this.year + " " + this.mySeasons.spring() + "    " + this.makeDMSString(this.mySeasons.springDuration()) + "  = " + Math.round(1000.0 * this.mySeasons.springDuration()) / 1000.0 + " d" + "\n";
        this.dataStr[2] = "Summer:  " + this.year + " " + this.mySeasons.summer() + "    " + this.makeDMSString(this.mySeasons.summerDuration()) + "  = " + Math.round(1000.0 * this.mySeasons.summerDuration()) / 1000.0 + " d" + "\n";
        this.dataStr[3] = "Autumn:  " + this.year + " " + this.mySeasons.autumn() + "    " + this.makeDMSString(this.mySeasons.autumnDuration()) + "  = " + Math.round(1000.0 * this.mySeasons.autumnDuration()) / 1000.0 + " d" + "\n";
        this.dataStr[4] = "Winter:  " + this.year + " " + this.mySeasons.winter() + "    " + this.makeDMSString(winterDuration) + "  = " + Math.round(1000.0 * winterDuration) / 1000.0 + " d" + "\n";
        this.dataStr[5] = "Spring:  " + (this.year + 1) + " " + this.myNextSeasons.spring() + "\n";
        final double summe = this.mySeasons.springDuration() + this.mySeasons.summerDuration() + this.mySeasons.autumnDuration() + winterDuration;
        System.out.println(String.valueOf(Math.round(1000.0 * summe) / 1000.0) + " d");
        this.dataStr[6] = "Year:                                " + this.makeDMSString(summe) + "  = " + Math.round(1000.0 * summe) / 1000.0 + " d" + "\n";
        this.Perihelion();
        g.drawString(this.perihelStr, left, oben + 100);
        this.Aphelion();
        g.drawString(this.aphelStr, left, oben + 120);
        g.setFont(new Font("Courier", 0, 10));
        g.setColor(Color.blue);
        g.drawString("Seasons" + this.versStr + " -  © 2002-" + this.copyrightYear + " J. Giesen  -  www.GeoAstro.de", left, oben + 140);
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        if (this.demo) {
            final Font f = g.getFont();
            g.setFont(new Font("Chicago", 0, 18));
            g.setColor(Color.red);
            g.drawString("D E M O", 120, 100);
            g.drawString("year menu disabled", 90, 120);
            g.setFont(f);
        }
    }
    
    public UTSeasons34() {
        this.versStr = " 3.4 ";
        this.demo = true;
        this.online = false;
        this.spring19 = 0;
        this.spring20 = 0;
        this.spring21 = 0;
        this.summer20 = 0;
        this.summer21 = 0;
        this.summer22 = 0;
        this.autumn21 = 0;
        this.autumn22 = 0;
        this.autumn23 = 0;
        this.winter20 = 0;
        this.winter21 = 0;
        this.winter22 = 0;
        this.dataStr = new String[10];
    }
}
