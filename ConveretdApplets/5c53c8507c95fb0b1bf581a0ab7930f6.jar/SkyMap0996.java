import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Event;
import java.net.URL;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Choice;
import java.util.Date;
import java.awt.Button;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SkyMap0996 extends Applet
{
    final double K = 0.017453292519943295;
    char deg;
    Panel p;
    Rise_Set rs;
    String versStr;
    String vers;
    Button now;
    Button aboutButton;
    boolean demo;
    double mag;
    double ra;
    double dec;
    Date dat;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    int locOffset;
    double UT;
    Choice hoursChoice;
    Choice minChoice;
    Choice monthChoice;
    Choice dateChoice;
    Choice yearChoice;
    Choice viewChoice;
    Choice magChoice;
    String hoursStr;
    String minStr;
    String dateStr;
    String monthStr;
    String yearStr;
    String[] monthArray;
    int directionItem;
    boolean nameOK;
    boolean eclipticOK;
    boolean equatorOK;
    boolean constellOK;
    boolean circumpolarOK;
    boolean kolurOK;
    double latitude;
    double longitude;
    Constellation constell;
    String[] dataStr;
    String[] dataStr1;
    String[] constellStr;
    String[] dataStr2;
    int nData;
    int nData1;
    int nData2;
    String nsStr;
    String ewStr;
    Choice offsetChoice;
    String latStr;
    String longStr;
    String locStr;
    String timezoneStr;
    int xMouse;
    int yMouse;
    int x0;
    int y0;
    String clickStr;
    String planetStr;
    int starNum;
    int xMars;
    int yMars;
    int xVenus;
    int yVenus;
    int xMercury;
    int yMercury;
    int xJup;
    int yJup;
    int xSat;
    int ySat;
    double currentJD;
    MarsCompute mar;
    VenusCompute ven;
    MercuryCompute mer;
    JupiterCompute jup;
    SaturnCompute sat;
    boolean starsOK;
    boolean planetsOK;
    boolean printOK;
    int Radius;
    double[] azPlanet;
    double[] altPlanet;
    String[] planetArray;
    String[] dayArray;
    double planetRA;
    double planetDec;
    double planetH;
    double planetElev;
    double planetAzim;
    double homeLat;
    double homeLong;
    double phase;
    boolean planetRiseOK;
    String[] planetRiseArray;
    String[] planetSetArray;
    double utSunSet;
    double utSunRise;
    String[] visArray;
    double starRise;
    int key;
    TextField fieldLatDeg;
    TextField fieldLongDeg;
    String homeLatStr;
    String homeLongStr;
    boolean RaDecOK;
    boolean AltAzOK;
    String AltAzStr;
    String RaDecStr;
    boolean analemmaOK;
    int[] analemmaX;
    int[] analemmaY;
    
    public void init() {
        this.setFont(new Font("Helvetica", 0, 12));
        this.locStr = this.getParameter("location");
        this.latStr = this.getParameter("latitude");
        this.homeLatStr = this.latStr;
        this.longStr = this.getParameter("longitude");
        this.homeLongStr = this.longStr;
        this.timezoneStr = this.getParameter("timezone");
        Double myDouble = Double.valueOf(this.latStr);
        this.latitude = myDouble;
        this.homeLat = this.latitude;
        if (this.latitude >= 0.0) {
            this.nsStr = " N";
        }
        else {
            this.nsStr = " S";
        }
        myDouble = Double.valueOf(this.longStr);
        this.longitude = myDouble;
        this.homeLong = this.longitude;
        if (this.longitude >= 0.0) {
            this.ewStr = " E";
        }
        else {
            this.ewStr = " W";
        }
        this.dat = new Date();
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.seconds = this.dat.getSeconds();
        if (this.timezoneStr.equals("auto")) {
            this.locOffset = -this.dat.getTimezoneOffset() / 60;
        }
        else {
            myDouble = Double.valueOf(this.timezoneStr);
            this.locOffset = (int)(double)myDouble;
        }
        this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        final URL url = this.getDocumentBase();
        String myStr = url.toString();
        myStr = String.valueOf(myStr) + "1234567890123456789012345";
        final String wwwStr = myStr.substring(0, 27);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        (this.p = new Panel()).setLayout(new GridLayout(2, 1));
        this.latStr = String.valueOf(this.latitude);
        this.fieldLatDeg = new TextField(this.latStr, 4);
        this.p.add(this.fieldLatDeg);
        this.longStr = String.valueOf(this.longitude);
        this.fieldLongDeg = new TextField(this.longStr, 5);
        this.p.add(this.fieldLongDeg);
        (this.offsetChoice = new Choice()).addItem("UT +0 h");
        this.offsetChoice.addItem("UT +1 h");
        this.offsetChoice.addItem("UT -1 h");
        this.offsetChoice.addItem("UT +2 h");
        this.offsetChoice.addItem("UT -2 h");
        this.offsetChoice.addItem("UT +3 h");
        this.offsetChoice.addItem("UT -3 h");
        this.offsetChoice.addItem("UT +4 h");
        this.offsetChoice.addItem("UT -4 h");
        this.offsetChoice.addItem("UT +5 h");
        this.offsetChoice.addItem("UT -5 h");
        this.offsetChoice.addItem("UT +6 h");
        this.offsetChoice.addItem("UT -6 h");
        this.offsetChoice.addItem("UT +7 h");
        this.offsetChoice.addItem("UT -7 h");
        this.offsetChoice.addItem("UT +8 h");
        this.offsetChoice.addItem("UT -8 h");
        this.offsetChoice.addItem("UT +9 h");
        this.offsetChoice.addItem("UT -9 h");
        this.offsetChoice.addItem("UT +10 h");
        this.offsetChoice.addItem("UT -10 h");
        this.offsetChoice.addItem("UT +11 h");
        this.offsetChoice.addItem("UT -11 h");
        this.offsetChoice.addItem("UT +12 h");
        this.offsetChoice.addItem("UT -12 h");
        this.offsetChoice.addItem("UT +13 h");
        if (this.locOffset >= 0) {
            this.offsetChoice.select("UT +" + this.locOffset + " h");
        }
        else {
            this.offsetChoice.select("UT " + this.locOffset + " h");
        }
        this.p.add(this.offsetChoice);
        this.yearChoice = new Choice();
        for (int i = 0; i < 150; ++i) {
            this.yearStr = String.valueOf(this.year + 1900 - 75 + i);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(this.year + 1900);
        this.yearChoice.select(this.yearStr);
        this.p.add(this.yearChoice);
        this.monthChoice = new Choice();
        for (int j = 0; j < 12; ++j) {
            this.monthChoice.addItem(this.monthArray[j]);
        }
        this.monthChoice.select(this.month);
        this.p.add(this.monthChoice);
        this.dateChoice = new Choice();
        for (int k = 1; k < 32; ++k) {
            this.dateStr = String.valueOf(k);
            this.dateChoice.addItem(this.dateStr);
        }
        this.dateChoice.select(this.date - 1);
        this.p.add(this.dateChoice);
        this.hoursChoice = new Choice();
        for (int l = 0; l < 10; ++l) {
            this.hoursStr = "0" + l;
            this.hoursChoice.addItem(this.hoursStr);
        }
        for (int m = 10; m < 24; ++m) {
            this.hoursStr = String.valueOf(m);
            this.hoursChoice.addItem(this.hoursStr);
        }
        this.hoursChoice.select(this.hours);
        this.p.add(this.hoursChoice);
        this.minChoice = new Choice();
        for (int i2 = 0; i2 < 10; ++i2) {
            this.minStr = "0" + i2;
            this.minChoice.addItem(this.minStr);
        }
        for (int i3 = 10; i3 < 60; ++i3) {
            this.minStr = String.valueOf(i3);
            this.minChoice.addItem(this.minStr);
        }
        this.minChoice.select(this.minutes);
        this.p.add(this.minChoice);
        this.now = new Button("Now");
        this.p.add(this.now);
        (this.magChoice = new Choice()).addItem("mag<1");
        this.magChoice.addItem("mag<2");
        this.magChoice.addItem("mag<2.5");
        this.magChoice.addItem("mag<3");
        this.magChoice.addItem("mag<3.5");
        this.magChoice.select("mag<2");
        this.p.add(this.magChoice);
        (this.viewChoice = new Choice()).addItem("Details...");
        this.viewChoice.addItem("Home");
        this.viewChoice.addItem("North Down");
        this.viewChoice.addItem("East Down");
        this.viewChoice.addItem("South Down");
        this.viewChoice.addItem("West Down");
        this.viewChoice.addItem("Stars on/off");
        this.viewChoice.addItem("Planets on/off");
        this.viewChoice.addItem("Analemma on/off");
        this.viewChoice.addItem("Names on/off");
        this.viewChoice.addItem("Constell. on/off");
        this.viewChoice.addItem("Ecliptic on/off");
        this.viewChoice.addItem("Equator on/off");
        this.viewChoice.addItem("Equ. Colure on/off");
        this.viewChoice.addItem("Circumpol on/off");
        this.viewChoice.addItem("Alt/Az on/off");
        this.viewChoice.addItem("Star List");
        this.viewChoice.addItem("Constell. List");
        this.viewChoice.addItem("Planet List");
        this.viewChoice.addItem("Print Vers. on/off");
        this.viewChoice.select(0);
        this.p.add(this.viewChoice);
        (this.aboutButton = new Button()).setLabel("About...");
        this.p.add(this.aboutButton);
        this.add("North", this.p);
        boolean ok = true;
        final String email = this.getParameter("email");
        final String param = this.getParameter("password");
        if (this.formula(wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(wwwStr, 20) == this.formula("http://www.SciAm.com", 20)) {
            ok = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (!ok) {
            ok = true;
            if (email.length() == 0 || Integer.parseInt(param) != this.formula(email, email.length())) {
                ok = false;
            }
            else {
                ok = true;
                this.demo = false;
            }
            if (wwwStr.substring(0, 7).equals("http://")) {
                ok = false;
                this.demo = true;
            }
        }
        System.out.println(this.vers);
    }
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 18048;
    }
    
    public boolean action(final Event evt, final Object eventobject) {
        if (evt.target == this.aboutButton) {
            final Dialog Aboutdialog = new Aboutdialog(this, this.versStr, this.demo);
            Aboutdialog.resize(300, 200);
            Aboutdialog.show();
            return true;
        }
        this.planetStr = "";
        this.clickStr = "";
        final double oldLat = this.latitude;
        if (evt.target instanceof TextField) {
            String str = "";
            if (evt.target == this.fieldLatDeg) {
                str = this.fieldLatDeg.getText();
                if (str.length() == 0 || str.charAt(0) == '.' || str.charAt(0) == ',') {
                    this.fieldLatDeg.setText(String.valueOf(Math.abs(Math.round(100.0 * oldLat) / 100.0)));
                    return true;
                }
                for (int i = 0; i < str.length(); ++i) {
                    final char c = str.charAt(i);
                    if (c == ',') {
                        str = String.valueOf(str.substring(0, i)) + '.' + str.substring(i + 1, str.length());
                    }
                }
                for (int j = 0; j < str.length(); ++j) {
                    final char c = str.charAt(j);
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.' && c != '-') {
                        this.fieldLatDeg.setText(String.valueOf(oldLat));
                        return true;
                    }
                }
                this.latStr = str;
                final Double latDouble = Double.valueOf(this.latStr);
                this.latitude = latDouble;
                this.fieldLatDeg.setText(str);
                if (Math.abs(this.latitude) >= 90.0) {
                    this.latitude = oldLat;
                    this.latStr = String.valueOf(Math.round(100.0 * oldLat) / 100.0);
                    this.fieldLatDeg.setText(this.latStr);
                }
                if (this.latitude != this.homeLat) {
                    this.locStr = "User Input";
                }
                if (this.longitude == this.homeLong && this.latitude == this.homeLat) {
                    this.locStr = this.getParameter("location");
                }
                this.repaint();
                return true;
            }
            else if (evt.target == this.fieldLongDeg) {
                final double oldLong = this.longitude;
                str = this.fieldLongDeg.getText();
                if (str.length() == 0 || str.charAt(0) == '.' || str.charAt(0) == ',') {
                    this.fieldLongDeg.setText(String.valueOf(Math.abs(Math.round(100.0 * oldLong) / 100.0)));
                    return true;
                }
                for (int k = 0; k < str.length(); ++k) {
                    final char c = str.charAt(k);
                    if (c == ',') {
                        str = String.valueOf(str.substring(0, k)) + '.' + str.substring(k + 1, str.length());
                    }
                }
                for (int l = 0; l < str.length(); ++l) {
                    final char c = str.charAt(l);
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.' && c != '-') {
                        this.fieldLongDeg.setText(String.valueOf(oldLong));
                        return true;
                    }
                }
                this.longStr = str;
                final Double longDouble = Double.valueOf(str);
                this.longitude = longDouble;
                this.fieldLongDeg.setText(this.longStr);
                if (Math.abs(this.longitude) >= 180.0) {
                    this.longitude = oldLong;
                    this.longStr = String.valueOf(Math.round(100.0 * oldLong) / 100.0);
                    this.fieldLongDeg.setText(this.longStr);
                }
                if (this.longitude != this.homeLong) {
                    this.locStr = "User Input";
                }
                if (this.longitude == this.homeLong && this.latitude == this.homeLat) {
                    this.locStr = this.getParameter("location");
                }
                this.repaint();
                return true;
            }
        }
        if (evt.target instanceof Button && evt.target == this.now) {
            this.dat = new Date();
            this.hours = this.dat.getHours();
            this.minutes = this.dat.getMinutes();
            this.date = this.dat.getDate();
            this.month = this.dat.getMonth();
            this.year = this.dat.getYear();
            this.seconds = this.dat.getSeconds();
            if (this.timezoneStr.equals("auto")) {
                this.locOffset = -this.dat.getTimezoneOffset() / 60;
            }
            else {
                final Double myDouble = Double.valueOf(this.timezoneStr);
                this.locOffset = (int)(double)myDouble;
            }
            this.hoursChoice.select(this.hours);
            this.minChoice.select(this.minutes);
            this.dateChoice.select(this.date - 1);
            this.monthChoice.select(this.month);
            this.yearChoice.select(String.valueOf(this.year + 1900));
            this.repaint();
            return true;
        }
        if (evt.target instanceof Choice) {
            if (evt.target == this.magChoice) {
                if (this.magChoice.getSelectedItem().equals("mag<1")) {
                    this.mag = 1.0;
                }
                if (this.magChoice.getSelectedItem().equals("mag<2")) {
                    this.mag = 2.0;
                }
                if (this.magChoice.getSelectedItem().equals("mag<2.5")) {
                    this.mag = 2.5;
                }
                if (this.magChoice.getSelectedItem().equals("mag<3")) {
                    this.mag = 3.0;
                }
                if (this.magChoice.getSelectedItem().equals("mag<3.5")) {
                    this.mag = 3.5;
                }
                this.repaint();
                return true;
            }
            if (evt.target == this.offsetChoice) {
                final String str = this.offsetChoice.getSelectedItem();
                this.locOffset = this.getTimeZone(str);
                this.repaint();
                return true;
            }
            if (evt.target == this.viewChoice) {
                final String mark0 = "";
                final String mark2 = "a";
                if (this.viewChoice.getSelectedItem().equals("Home")) {
                    this.latitude = this.homeLat;
                    this.longitude = this.homeLong;
                    this.fieldLatDeg.setText(this.homeLatStr);
                    this.fieldLongDeg.setText(this.homeLongStr);
                }
                if (this.viewChoice.getSelectedItem().equals("North Down")) {
                    this.directionItem = 1;
                }
                if (this.viewChoice.getSelectedItem().equals("East Down")) {
                    this.directionItem = 2;
                }
                if (this.viewChoice.getSelectedItem().equals("South Down")) {
                    this.directionItem = 3;
                }
                if (this.viewChoice.getSelectedItem().equals("West Down")) {
                    this.directionItem = 4;
                }
                if (this.viewChoice.getSelectedItem().equals("Constell. on/off")) {
                    this.constellOK ^= true;
                }
                if (this.viewChoice.getSelectedItem().equals("Stars on/off")) {
                    this.starsOK ^= true;
                }
                if (this.viewChoice.getSelectedItem().equals("Planets on/off")) {
                    this.planetsOK ^= true;
                }
                if (this.viewChoice.getSelectedItem().equals("Analemma on/off")) {
                    this.analemmaOK ^= true;
                }
                if (this.viewChoice.getSelectedItem().equals("Names on/off")) {
                    this.nameOK ^= true;
                }
                if (this.viewChoice.getSelectedItem().equals("Ecliptic on/off")) {
                    this.eclipticOK ^= true;
                }
                if (this.viewChoice.getSelectedItem().equals("Equator on/off")) {
                    this.equatorOK ^= true;
                }
                if (this.viewChoice.getSelectedItem().equals("Circumpol on/off")) {
                    this.circumpolarOK ^= true;
                }
                if (this.viewChoice.getSelectedItem().equals("Equ. Colure on/off")) {
                    this.kolurOK ^= true;
                }
                if (this.viewChoice.getSelectedItem().equals("Print Vers. on/off")) {
                    this.printOK ^= true;
                }
                if (this.viewChoice.getSelectedItem().equals("Alt/Az on/off")) {
                    this.AltAzOK ^= true;
                }
                if (this.viewChoice.getSelectedItem().equals("Star List")) {
                    this.starList();
                    final scrollFrame sf = new scrollFrame(this.demo, "Bright Stars", this.nData, this.dataStr);
                    sf.resize(370, 480);
                    sf.show();
                    this.viewChoice.select(0);
                    return true;
                }
                if (this.viewChoice.getSelectedItem().equals("Constell. List")) {
                    this.constellList();
                    final scrollFrame sf2 = new scrollFrame(this.demo, "Main Constellations", this.nData1, this.dataStr1);
                    sf2.resize(400, 480);
                    sf2.show();
                    this.viewChoice.select(0);
                    return true;
                }
                if (this.viewChoice.getSelectedItem().equals("Planet List")) {
                    this.planetRiseOK = true;
                    this.planetList();
                    final scrollFrame sf3 = new scrollFrame(this.demo, "The Bright Planets", this.nData2, this.dataStr2);
                    sf3.resize(450, 190);
                    sf3.show();
                    this.viewChoice.select(0);
                    this.planetRiseOK = false;
                    return true;
                }
                this.viewChoice.select(0);
                this.repaint();
                return true;
            }
            else {
                if (evt.target == this.hoursChoice) {
                    this.hoursStr = this.hoursChoice.getSelectedItem();
                    this.hours = Integer.parseInt(this.hoursStr);
                    this.hoursChoice.select(this.hoursStr);
                    this.dat.setHours(this.hours);
                    this.dat.setSeconds(0);
                    this.repaint();
                    return true;
                }
                if (evt.target == this.minChoice) {
                    this.minStr = this.minChoice.getSelectedItem();
                    this.minutes = Integer.parseInt(this.minStr);
                    this.minChoice.select(this.minStr);
                    this.dat.setMinutes(this.minutes);
                    this.dat.setSeconds(0);
                    this.repaint();
                    return true;
                }
                if (evt.target == this.dateChoice) {
                    this.dateStr = this.dateChoice.getSelectedItem();
                    this.date = Integer.parseInt(this.dateStr);
                    this.dat.setDate(this.date);
                    this.dat.setSeconds(0);
                    final int n = this.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = n;
                        this.dat.setDate(this.date);
                        this.dateChoice.select(this.date - 1);
                    }
                    this.repaint();
                    return true;
                }
                if (evt.target == this.monthChoice) {
                    this.monthStr = this.monthChoice.getSelectedItem();
                    this.month = this.MonthInteger(this.monthStr);
                    this.monthChoice.select(this.month);
                    this.dat.setMonth(this.month);
                    this.dat.setSeconds(0);
                    final int n = this.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = n;
                        this.dat.setDate(this.date);
                        this.dateChoice.select(this.date - 1);
                    }
                    this.repaint();
                    return true;
                }
                if (evt.target == this.yearChoice) {
                    this.yearStr = this.yearChoice.getSelectedItem();
                    this.year = Integer.parseInt(this.yearStr);
                    this.yearChoice.select(this.yearStr);
                    this.year -= 1900;
                    this.dat.setYear(this.year);
                    final int n = this.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = n;
                        this.dat.setDate(this.date);
                        this.dateChoice.select(this.date - 1);
                    }
                    this.repaint();
                    return true;
                }
            }
        }
        return super.handleEvent(evt);
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        this.key = code;
        if (code != 78 && code != 110 && code != 87 && code != 119 && code != 108 && code != 76 && code != 104 && code != 100 && code != 109 && code != 72 && code != 68 && code != 77) {
            return false;
        }
        if (code == 110) {
            ++this.minutes;
            if (this.minutes == 60) {
                this.minutes = 0;
                ++this.hours;
                if (this.hours == 24) {
                    this.hours = 0;
                    ++this.date;
                }
                this.dat.setHours(this.hours);
                this.hoursChoice.select(this.hours);
                if (this.date > this.daysInMonth(this.month, this.year)) {
                    this.date = 1;
                    this.dat.setDate(this.date);
                    ++this.month;
                    this.dat.setMonth(this.month);
                    this.monthChoice.select(this.month);
                }
                this.dateChoice.select(this.date - 1);
            }
            this.dat.setMinutes(this.minutes);
            this.minChoice.select(this.minutes);
            this.repaint();
            return true;
        }
        if (code == 78) {
            --this.minutes;
            if (this.minutes == -1) {
                this.minutes = 59;
                --this.hours;
                if (this.hours == -1) {
                    this.hours = 23;
                    --this.date;
                }
                this.dat.setHours(this.hours);
                this.hoursChoice.select(this.hours);
                if (this.date == 0) {
                    --this.month;
                    this.date = this.daysInMonth(this.month, this.year);
                    this.dat.setDate(this.date);
                    this.dat.setMonth(this.month);
                    this.monthChoice.select(this.month);
                }
                this.dateChoice.select(this.date - 1);
            }
            this.dat.setMinutes(this.minutes);
            this.minChoice.select(this.minutes);
            this.repaint();
            return true;
        }
        if (code == 119 || code == 87) {
            double Jd = this.JD(this.date, this.month + 1, this.year + 1900, this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0);
            if (code == 119) {
                Jd += 7.0;
            }
            else {
                Jd -= 7.0;
            }
            this.date = (int)this.caldat(1, Jd);
            this.month = (int)this.caldat(2, Jd) - 1;
            this.year = (int)this.caldat(4, Jd) - 1900;
            this.dat.setDate(this.date);
            this.dateChoice.select(this.date - 1);
            this.dat.setMonth(this.month);
            this.monthChoice.select(this.month);
            this.dat.setYear(this.year);
            this.yearStr = String.valueOf(this.year + 1900);
            this.yearChoice.select(this.yearStr);
            this.repaint();
            return true;
        }
        if (code == 108 || code == 76) {
            if (code == 108 && Math.abs(this.latitude) <= 89.0) {
                ++this.latitude;
                this.latStr = String.valueOf(Math.round(100.0 * this.latitude) / 100.0);
                this.fieldLatDeg.setText(this.latStr);
                this.repaint();
                return true;
            }
            if (code == 76 && Math.abs(this.latitude) <= 90.0) {
                --this.latitude;
                this.latStr = String.valueOf(Math.round(100.0 * this.latitude) / 100.0);
                this.fieldLatDeg.setText(this.latStr);
                this.repaint();
                return true;
            }
        }
        if (code == 72 || code == 104) {
            if (code == 72) {
                --this.hours;
                if (this.hours < 0) {
                    this.hours += 24;
                    --this.date;
                }
                if (this.date == 0) {
                    --this.month;
                    this.dat.setMonth(this.month);
                    this.date = this.daysInMonth(this.month, this.year);
                    if (this.month == -1) {
                        this.month = 11;
                        --this.year;
                        this.dat.setYear(this.year);
                        this.yearStr = String.valueOf(this.year + 1900);
                        this.yearChoice.select(this.yearStr);
                    }
                }
            }
            else {
                ++this.hours;
            }
            if (this.hours >= 24) {
                this.hours -= 24;
                ++this.date;
                this.month = this.dat.getMonth();
                this.year = this.dat.getYear();
                n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = 1;
                    ++this.month;
                    if (this.month == 12) {
                        this.month = 0;
                        ++this.year;
                        this.yearStr = String.valueOf(this.year + 1900);
                        this.yearChoice.select(this.yearStr);
                    }
                    this.dat.setMonth(this.month);
                    this.monthChoice.select(this.month);
                }
                this.dateChoice.select(this.date - 1);
            }
            this.dat.setHours(this.hours);
            this.dat.setSeconds(0);
            this.dat.setDate(this.date);
            this.hoursStr = String.valueOf(this.hours);
            if (this.hours < 10) {
                this.hoursStr = "0" + this.hoursStr;
            }
            this.hoursChoice.select(this.hours);
            this.date = this.dat.getDate();
            this.dateChoice.select(this.date - 1);
            this.month = this.dat.getMonth();
            this.monthChoice.select(this.month);
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
                        this.yearStr = String.valueOf(this.year + 1900);
                    }
                    this.date = this.daysInMonth(this.month, this.year);
                }
            }
            else {
                ++this.date;
                this.month = this.dat.getMonth();
                n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = 1;
                    ++this.month;
                    if (this.month == 12) {
                        this.month = 0;
                        ++this.year;
                        this.dat.setYear(this.year);
                    }
                }
            }
            this.dateChoice.select(this.date - 1);
            this.dat.setMonth(this.month);
            this.monthChoice.select(this.month);
            this.dat.setSeconds(0);
            this.dat.setDate(this.date);
            this.yearStr = String.valueOf(this.year + 1900);
            this.yearChoice.select(this.yearStr);
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
            this.dat.setMonth(this.month);
            this.date = this.dat.getDate();
            n = this.daysInMonth(this.month, this.year);
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
            this.dat.setSeconds(0);
            this.repaint();
            return true;
        }
        return false;
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        this.findStar(x, y);
        this.repaint();
        return true;
    }
    
    public double caldat(final int what, final double JD) {
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
        if (what == 1) {
            return day;
        }
        if (what == 2) {
            return month;
        }
        if (what == 3) {
            return hour;
        }
        return year;
    }
    
    public boolean mouseMove(final Event event, final int x, final int y) {
        final int Radius = this.x0 - 20;
        final double r2 = (x - this.x0) * (x - this.x0) + (y - this.y0) * (y - this.y0);
        if (r2 < Radius * Radius) {
            this.setCursor(new Cursor(1));
            if (this.AltAzOK) {
                double azMouse = 0.0;
                switch (this.directionItem) {
                    case 3: {
                        azMouse = -90.0 - Math.atan2(y - this.y0, x - this.x0) / 0.017453292519943295;
                        break;
                    }
                    case 1: {
                        azMouse = 90.0 - Math.atan2(y - this.y0, x - this.x0) / 0.017453292519943295;
                        break;
                    }
                    case 4: {
                        azMouse = -Math.atan2(y - this.y0, x - this.x0) / 0.017453292519943295;
                        break;
                    }
                    case 2: {
                        azMouse = 180.0 - Math.atan2(y - this.y0, x - this.x0) / 0.017453292519943295;
                        break;
                    }
                }
                if (azMouse < 0.0) {
                    azMouse += 360.0;
                }
                if (azMouse > 360.0) {
                    azMouse -= 360.0;
                }
                this.AltAzStr = "Alt=" + Math.round(900.0 * (1.0 - Math.sqrt(r2) / Radius)) / 10.0 + this.deg + " Az=" + Math.round(10.0 * azMouse) / 10.0 + this.deg;
            }
        }
        else {
            this.setCursor(new Cursor(0));
            this.AltAzStr = "";
            this.RaDecStr = "";
        }
        this.repaint();
        return true;
    }
    
    public int getTimeZone(final String timeString) {
        int timeOffset = 0;
        if (timeString.equals("UT +0 h")) {
            timeOffset = 0;
        }
        else if (timeString.equals("UT -1 h")) {
            timeOffset = -1;
        }
        else if (timeString.equals("UT +1 h")) {
            timeOffset = 1;
        }
        else if (timeString.equals("UT -2 h")) {
            timeOffset = -2;
        }
        else if (timeString.equals("UT +2 h")) {
            timeOffset = 2;
        }
        else if (timeString.equals("UT -3 h")) {
            timeOffset = -3;
        }
        else if (timeString.equals("UT +3 h")) {
            timeOffset = 3;
        }
        else if (timeString.equals("UT -4 h")) {
            timeOffset = -4;
        }
        else if (timeString.equals("UT +4 h")) {
            timeOffset = 4;
        }
        else if (timeString.equals("UT -5 h")) {
            timeOffset = -5;
        }
        else if (timeString.equals("UT +5 h")) {
            timeOffset = 5;
        }
        else if (timeString.equals("UT -6 h")) {
            timeOffset = -6;
        }
        else if (timeString.equals("UT +6 h")) {
            timeOffset = 6;
        }
        else if (timeString.equals("UT -7 h")) {
            timeOffset = -7;
        }
        else if (timeString.equals("UT +7 h")) {
            timeOffset = 7;
        }
        else if (timeString.equals("UT -8 h")) {
            timeOffset = -8;
        }
        else if (timeString.equals("UT +8 h")) {
            timeOffset = 8;
        }
        else if (timeString.equals("UT -9 h")) {
            timeOffset = -9;
        }
        else if (timeString.equals("UT +9 h")) {
            timeOffset = 9;
        }
        else if (timeString.equals("UT -10 h")) {
            timeOffset = -10;
        }
        else if (timeString.equals("UT +10 h")) {
            timeOffset = 10;
        }
        else if (timeString.equals("UT -11 h")) {
            timeOffset = -11;
        }
        else if (timeString.equals("UT +11 h")) {
            timeOffset = 11;
        }
        else if (timeString.equals("UT -12 h")) {
            timeOffset = -12;
        }
        else if (timeString.equals("UT +12 h")) {
            timeOffset = 12;
        }
        else if (timeString.equals("UT +13 h")) {
            timeOffset = 13;
        }
        return timeOffset;
    }
    
    public void findStar(final int x, final int y) {
        final int Radius = this.x0 - 20;
        this.xMouse = x;
        this.yMouse = y;
        final StarData myValue = new StarData();
        if ((this.xMouse - this.xMars) * (this.xMouse - this.xMars) + (this.yMouse - this.yMars) * (this.yMouse - this.yMars) < 25) {
            this.planetStr = "Mars " + this.mag(this.currentJD, 3);
        }
        if ((this.xMouse - this.xVenus) * (this.xMouse - this.xVenus) + (this.yMouse - this.yVenus) * (this.yMouse - this.yVenus) < 25) {
            this.planetStr = "Venus " + this.mag(this.currentJD, 2);
        }
        if ((this.xMouse - this.xMercury) * (this.xMouse - this.xMercury) + (this.yMouse - this.yMercury) * (this.yMouse - this.yMercury) < 25) {
            this.planetStr = "Mercury " + this.mag(this.currentJD, 1);
        }
        if ((this.xMouse - this.xJup) * (this.xMouse - this.xJup) + (this.yMouse - this.yJup) * (this.yMouse - this.yJup) < 25) {
            this.planetStr = "Jupiter " + this.mag(this.currentJD, 4);
        }
        if ((this.xMouse - this.xSat) * (this.xMouse - this.xSat) + (this.yMouse - this.ySat) * (this.yMouse - this.ySat) < 25) {
            this.planetStr = "Saturn " + this.mag(this.currentJD, 5);
        }
        final int nStar = myValue.nStarData();
        boolean found = false;
        for (int i = 0; i <= nStar; ++i) {
            this.ra = 15.0 * myValue.getRA(i);
            this.dec = myValue.getDec(i);
            final double magnitudo = myValue.getMag(i);
            final String name = myValue.star(i, 3);
            final String bild = myValue.star(i, 4);
            final double h = this.THETA0(this.JD(this.date, this.month + 1, this.year + 1900, this.UT)) + this.longitude - 15.0 * this.ra;
            double elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * this.dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * h);
            elev = Math.asin(elev) / 0.017453292519943295;
            double azim = Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
            switch (this.directionItem) {
                case 1: {
                    azim += 180.0;
                    break;
                }
                case 2: {
                    azim += 90.0;
                    break;
                }
                case 4: {
                    azim += 270.0;
                    break;
                }
            }
            final int X = this.x0 + (int)Math.round((90.0 - elev) * Radius / 90.0 * Math.sin(azim * 0.017453292519943295));
            final int Y = this.y0 + (int)Math.round((90.0 - elev) * Radius / 90.0 * Math.cos(azim * 0.017453292519943295));
            if ((this.xMouse - X) * (this.xMouse - X) + (this.yMouse - Y) * (this.yMouse - Y) < 25) {
                this.clickStr = String.valueOf(name) + " " + bild + " " + magnitudo + " " + Math.round(10.0 * this.ra / 15.0) / 10.0 + "h " + Math.round(10.0 * this.dec) / 10.0 + this.deg;
                this.starNum = i;
                found = true;
                if (this.mag < 3.0 && magnitudo > this.mag) {
                    this.clickStr = "";
                }
            }
        }
        if (!found) {
            this.clickStr = "";
        }
        if ((this.xMouse - this.x0) * (this.xMouse - this.x0) + (this.yMouse - this.y0) * (this.yMouse - this.y0) > Radius * Radius) {
            this.clickStr = "";
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
    
    double sunDecRA(final int what, final double JD) {
        final double PI2 = 6.283185307179586;
        final double cos_eps = 0.917482;
        final double sin_eps = 0.397778;
        final double T = (JD - 2451545.0) / 36525.0;
        final double M = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
        final double DL = 6893.0 * Math.sin(M) + 72.0 * Math.sin(2.0 * M);
        final double L = 6.283185307179586 * this.frac(0.7859453 + M / 6.283185307179586 + (6191.2 * T + DL) / 1296000.0);
        final double SL = Math.sin(L);
        final double X = Math.cos(L);
        final double Y = 0.917482 * SL;
        final double Z = 0.397778 * SL;
        final double R = Math.sqrt(1.0 - Z * Z);
        final double DEC = 57.29577951308232 * Math.atan(Z / R);
        double RA = 7.639437268410976 * Math.atan(Y / (X + R));
        if (RA < 0.0) {
            RA += 24.0;
        }
        if (what == 1) {
            return DEC;
        }
        return RA;
    }
    
    public double planetAlt(final double j, final double ra, final double dec) {
        final double H = this.THETA0(j) + this.longitude - 15.0 * ra;
        final double alt = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * H);
        return Math.asin(alt) / 0.017453292519943295;
    }
    
    double THETA0(final double JD) {
        final double T = (JD - 2451545.0) / 36525.0;
        double x = 280.46061837 + 360.98564736629 * (JD - 2451545.0) + 3.87933E-4 * T * T - T * T * T / 3.871E7;
        x %= 360.0;
        if (x < 0.0) {
            x += 360.0;
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
        final int HOUR = (int)x;
        final int MIN = (int)(this.frac(x) * 60.0);
        final double SEC = 60.0 * (this.frac(x) * 60.0 - MIN);
        if (HOUR < 10) {
            str = "0";
        }
        str = String.valueOf(str) + HOUR + ":";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + MIN + ":";
        if ((int)Math.round(SEC) < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + Math.round(SEC);
    }
    
    double LM_Sidereal_Time(final double JD, final double LONG) {
        final double GMST = this.GM_Sidereal_Time(JD);
        return 24.0 * this.frac((GMST - LONG / 15.0) / 24.0);
    }
    
    double GM_Sidereal_Time(final double JD) {
        final double T = (JD - 2451545.0) / 36525.0;
        double x = 280.46061837 + 360.98564736629 * (JD - 2451545.0) + 3.87933E-4 * T * T - T * T * T / 3.871E7;
        x %= 360.0;
        if (x < 0.0) {
            x += 360.0;
        }
        x /= 15.0;
        return x;
    }
    
    public String makeTimeString(double time) {
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
        return str;
    }
    
    public int daysInMonth(final int m, int y) {
        int n = 31;
        y += 1900;
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
    
    public double computeAzimut(final double dec, final double latitude, final double longitude, final double hoehe) {
        final double cosAz = (Math.sin(dec * 0.017453292519943295) - Math.sin(0.017453292519943295 * latitude) * Math.sin(0.017453292519943295 * hoehe)) / (Math.cos(0.017453292519943295 * hoehe) * Math.cos(0.017453292519943295 * latitude));
        return Math.acos(cosAz) / 0.017453292519943295;
    }
    
    public void mercury(double jd) {
        this.mer = new MercuryCompute(jd);
        this.planetRA = this.mer.alpha() / 15.0;
        this.planetDec = this.mer.delta();
        this.planetH = this.THETA0(jd) + this.longitude - 15.0 * this.planetRA;
        this.planetAzim = Math.atan2(Math.sin(0.017453292519943295 * this.planetH), Math.cos(0.017453292519943295 * this.planetH) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.planetDec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        switch (this.directionItem) {
            case 1: {
                this.planetAzim += 180.0;
                break;
            }
            case 2: {
                this.planetAzim += 90.0;
                break;
            }
            case 4: {
                this.planetAzim += 270.0;
                break;
            }
        }
        this.planetElev = this.planetAlt(jd, this.planetRA, this.planetDec);
        this.xMercury = this.planetX(this.planetElev, this.planetAzim);
        this.yMercury = this.planetY(this.planetElev, this.planetAzim);
        this.altPlanet[1] = this.planetElev;
        this.azPlanet[1] = this.planetAzim + 180.0;
        if (this.planetRiseOK) {
            jd = (int)this.currentJD - 0.5;
            MercuryCompute merc = new MercuryCompute(jd);
            double j0 = jd;
            double i = 0.0;
            for (int k = 0; k < 25; ++k) {
                i = j0 + k / 24.0;
                merc = new MercuryCompute(i);
                this.planetRA = merc.alpha() / 15.0;
                this.planetDec = merc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev > 0.0) {
                    i -= 0.041666666666666664;
                    break;
                }
            }
            for (int l = -1; l < 62; ++l) {
                i += 6.944444444444445E-4;
                merc = new MercuryCompute(i);
                this.planetRA = merc.alpha() / 15.0;
                this.planetDec = merc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev > this.starRise) {
                    break;
                }
            }
            i -= 3.4722222222222224E-4;
            String day = String.valueOf((int)this.caldat(1, i));
            if ((int)this.caldat(1, i) < 10) {
                day = "0" + day;
            }
            this.planetRiseArray[1] = String.valueOf(day) + "  " + this.makeTimeString(this.caldat(3, i));
            final double utRise = this.caldat(3, i);
            i = 0.0;
            for (int m = 0; m < 25; ++m) {
                i = j0 + m / 24.0;
                merc = new MercuryCompute(i);
                this.planetRA = merc.alpha() / 15.0;
                this.planetDec = merc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev < 0.0 && m == 0) {
                    j0 += 0.5;
                }
                if (this.planetElev < 0.0 && m > 0) {
                    i -= 0.041666666666666664;
                    break;
                }
            }
            for (int i2 = -1; i2 < 62; ++i2) {
                i += 6.944444444444445E-4;
                merc = new MercuryCompute(i);
                this.planetRA = merc.alpha() / 15.0;
                this.planetDec = merc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev < this.starRise) {
                    break;
                }
            }
            i -= 3.4722222222222224E-4;
            day = String.valueOf((int)this.caldat(1, i));
            if ((int)this.caldat(1, i) < 10) {
                day = "0" + day;
            }
            this.planetSetArray[1] = String.valueOf(day) + "  " + this.makeTimeString(this.caldat(3, i));
            if (this.caldat(3, i) > this.utSunSet) {
                final String[] planetSetArray = this.planetSetArray;
                final int n = 1;
                planetSetArray[n] = String.valueOf(planetSetArray[n]) + "  EVE";
            }
            else if (utRise < this.utSunRise) {
                final String[] planetSetArray2 = this.planetSetArray;
                final int n2 = 1;
                planetSetArray2[n2] = String.valueOf(planetSetArray2[n2]) + "  MOR";
            }
            if (this.altPlanet[1] > 0.0 && this.currentJD > this.utSunSet) {
                this.visArray[1] = "(+)";
            }
            else {
                this.visArray[1] = "(-)";
            }
        }
    }
    
    public void venus(double jd) {
        this.ven = new VenusCompute(jd);
        this.planetRA = this.ven.alpha() / 15.0;
        this.planetDec = this.ven.delta();
        this.planetH = this.THETA0(jd) + this.longitude - 15.0 * this.planetRA;
        this.planetAzim = Math.atan2(Math.sin(0.017453292519943295 * this.planetH), Math.cos(0.017453292519943295 * this.planetH) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.planetDec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        switch (this.directionItem) {
            case 1: {
                this.planetAzim += 180.0;
                break;
            }
            case 2: {
                this.planetAzim += 90.0;
                break;
            }
            case 4: {
                this.planetAzim += 270.0;
                break;
            }
        }
        this.planetElev = this.planetAlt(jd, this.planetRA, this.planetDec);
        this.xVenus = this.planetX(this.planetElev, this.planetAzim);
        this.yVenus = this.planetY(this.planetElev, this.planetAzim);
        this.altPlanet[2] = this.planetElev;
        this.azPlanet[2] = this.planetAzim + 180.0;
        if (this.planetRiseOK) {
            jd = (int)this.currentJD - 0.5;
            VenusCompute venc = new VenusCompute(jd);
            double j0 = jd;
            double i = 0.0;
            for (int k = 0; k < 25; ++k) {
                i = j0 + k / 24.0;
                venc = new VenusCompute(i);
                this.planetRA = venc.alpha() / 15.0;
                this.planetDec = venc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev > 0.0 && k > 0) {
                    i -= 0.041666666666666664;
                    break;
                }
            }
            for (int l = -1; l < 62; ++l) {
                i += 6.944444444444445E-4;
                venc = new VenusCompute(i);
                this.planetRA = venc.alpha() / 15.0;
                this.planetDec = venc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev > this.starRise) {
                    break;
                }
            }
            i -= 3.4722222222222224E-4;
            final double utRise = this.caldat(3, i);
            String day = String.valueOf((int)this.caldat(1, i));
            if ((int)this.caldat(1, i) < 10) {
                day = "0" + day;
            }
            this.planetRiseArray[2] = String.valueOf(day) + "  " + this.makeTimeString(this.caldat(3, i));
            i = 0.0;
            for (int m = 0; m < 25; ++m) {
                i = j0 + m / 24.0;
                venc = new VenusCompute(i);
                this.planetRA = venc.alpha() / 15.0;
                this.planetDec = venc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev < 0.0 && m == 0) {
                    j0 += 0.5;
                }
                if (this.planetElev < 0.0 && m > 0) {
                    i -= 0.041666666666666664;
                    break;
                }
            }
            for (int i2 = -1; i2 < 62; ++i2) {
                i += 6.944444444444445E-4;
                venc = new VenusCompute(i);
                this.planetRA = venc.alpha() / 15.0;
                this.planetDec = venc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev < this.starRise) {
                    break;
                }
            }
            i -= 3.4722222222222224E-4;
            day = String.valueOf((int)this.caldat(1, i));
            if ((int)this.caldat(1, i) < 10) {
                day = "0" + day;
            }
            this.planetSetArray[2] = String.valueOf(day) + "  " + this.makeTimeString(this.caldat(3, i));
            if (this.caldat(3, i) > this.utSunSet) {
                final String[] planetSetArray = this.planetSetArray;
                final int n = 2;
                planetSetArray[n] = String.valueOf(planetSetArray[n]) + "  EVE";
            }
            else if (utRise < this.utSunRise) {
                final String[] planetSetArray2 = this.planetSetArray;
                final int n2 = 2;
                planetSetArray2[n2] = String.valueOf(planetSetArray2[n2]) + "  MOR";
            }
            if (this.altPlanet[2] > 0.0 && this.currentJD > this.utSunSet) {
                this.visArray[2] = "(+)";
            }
            else {
                this.visArray[2] = "(-)";
            }
        }
    }
    
    public void mars(double jd) {
        this.mar = new MarsCompute(jd);
        this.planetRA = this.mar.alpha() / 15.0;
        this.planetDec = this.mar.delta();
        this.planetH = this.THETA0(jd) + this.longitude - 15.0 * this.planetRA;
        this.planetAzim = Math.atan2(Math.sin(0.017453292519943295 * this.planetH), Math.cos(0.017453292519943295 * this.planetH) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.planetDec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        switch (this.directionItem) {
            case 1: {
                this.planetAzim += 180.0;
                break;
            }
            case 2: {
                this.planetAzim += 90.0;
                break;
            }
            case 4: {
                this.planetAzim += 270.0;
                break;
            }
        }
        this.planetElev = this.planetAlt(jd, this.planetRA, this.planetDec);
        this.xMars = this.planetX(this.planetElev, this.planetAzim);
        this.yMars = this.planetY(this.planetElev, this.planetAzim);
        this.altPlanet[3] = this.planetElev;
        this.azPlanet[3] = this.planetAzim + 180.0;
        if (this.planetRiseOK) {
            jd = (int)this.currentJD - 0.5;
            MarsCompute marc = new MarsCompute(jd);
            double j0 = jd;
            double i = 0.0;
            for (int k = 0; k < 25; ++k) {
                i = j0 + k / 24.0;
                marc = new MarsCompute(i);
                this.planetRA = marc.alpha() / 15.0;
                this.planetDec = marc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev > 0.0 && k == 0) {
                    j0 += 0.5;
                }
                if (this.planetElev > 0.0 && k > 0) {
                    i -= 0.041666666666666664;
                    break;
                }
            }
            for (int l = -2; l < 63; ++l) {
                i += 6.944444444444445E-4;
                marc = new MarsCompute(i);
                this.planetRA = marc.alpha() / 15.0;
                this.planetDec = marc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev > this.starRise) {
                    break;
                }
            }
            i -= 3.4722222222222224E-4;
            String day = String.valueOf((int)this.caldat(1, i));
            if ((int)this.caldat(1, i) < 10) {
                day = "0" + day;
            }
            this.planetRiseArray[3] = String.valueOf(day) + "  " + this.makeTimeString(this.caldat(3, i));
            j0 = i;
            for (int m = 0; m < 25; ++m) {
                i = j0 + m / 24.0;
                marc = new MarsCompute(i);
                this.planetRA = marc.alpha() / 15.0;
                this.planetDec = marc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev < 0.0 && m == 0) {
                    j0 += 0.5;
                }
                if (this.planetElev < 0.0 && m > 0) {
                    i -= 0.041666666666666664;
                    break;
                }
            }
            for (int i2 = -1; i2 < 62; ++i2) {
                i += 6.944444444444445E-4;
                marc = new MarsCompute(i);
                this.planetRA = marc.alpha() / 15.0;
                this.planetDec = marc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev < this.starRise) {
                    break;
                }
            }
            i -= 3.4722222222222224E-4;
            day = String.valueOf((int)this.caldat(1, i));
            if ((int)this.caldat(1, i) < 10) {
                day = "0" + day;
            }
            this.planetSetArray[3] = String.valueOf(day) + "  " + this.makeTimeString(this.caldat(3, i));
            if (this.altPlanet[3] > 0.0 && this.currentJD > this.utSunSet) {
                this.visArray[3] = "(+)";
            }
            else {
                this.visArray[3] = "(-)";
            }
        }
    }
    
    public void jupiter(double jd) {
        this.jup = new JupiterCompute(jd);
        this.planetRA = this.jup.alpha() / 15.0;
        this.planetDec = this.jup.delta();
        this.planetH = this.THETA0(jd) + this.longitude - 15.0 * this.planetRA;
        this.planetAzim = Math.atan2(Math.sin(0.017453292519943295 * this.planetH), Math.cos(0.017453292519943295 * this.planetH) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.planetDec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        switch (this.directionItem) {
            case 1: {
                this.planetAzim += 180.0;
                break;
            }
            case 2: {
                this.planetAzim += 90.0;
                break;
            }
            case 4: {
                this.planetAzim += 270.0;
                break;
            }
        }
        this.planetElev = this.planetAlt(jd, this.planetRA, this.planetDec);
        this.xJup = this.planetX(this.planetElev, this.planetAzim);
        this.yJup = this.planetY(this.planetElev, this.planetAzim);
        this.altPlanet[4] = this.planetElev;
        this.azPlanet[4] = this.planetAzim + 180.0;
        if (this.planetRiseOK) {
            jd = (int)this.currentJD - 0.5;
            JupiterCompute jupc = new JupiterCompute(jd);
            double j0 = jd;
            double i = 0.0;
            for (int k = 0; k < 25; ++k) {
                i = j0 + k / 24.0;
                jupc = new JupiterCompute(i);
                this.planetRA = jupc.alpha() / 15.0;
                this.planetDec = jupc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev > 0.0 && k == 0) {
                    j0 += 0.5;
                }
                if (this.planetElev > 0.0 && k > 0) {
                    i -= 0.041666666666666664;
                    break;
                }
            }
            for (int l = -2; l < 63; ++l) {
                i += 6.944444444444445E-4;
                jupc = new JupiterCompute(i);
                this.planetRA = jupc.alpha() / 15.0;
                this.planetDec = jupc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev > this.starRise) {
                    break;
                }
            }
            i -= 3.4722222222222224E-4;
            String day = String.valueOf((int)this.caldat(1, i));
            if ((int)this.caldat(1, i) < 10) {
                day = "0" + day;
            }
            this.planetRiseArray[4] = String.valueOf(day) + "  " + this.makeTimeString(this.caldat(3, i));
            j0 = i;
            for (int m = 0; m < 25; ++m) {
                i = j0 + m / 24.0;
                jupc = new JupiterCompute(i);
                this.planetRA = jupc.alpha() / 15.0;
                this.planetDec = jupc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev < 0.0 && m == 0) {
                    j0 += 0.5;
                }
                if (this.planetElev < 0.0 && m > 0) {
                    i -= 0.041666666666666664;
                    break;
                }
            }
            for (int i2 = -1; i2 < 62; ++i2) {
                i += 6.944444444444445E-4;
                jupc = new JupiterCompute(i);
                this.planetRA = jupc.alpha() / 15.0;
                this.planetDec = jupc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev < this.starRise) {
                    break;
                }
            }
            i -= 3.4722222222222224E-4;
            day = String.valueOf((int)this.caldat(1, i));
            if ((int)this.caldat(1, i) < 10) {
                day = "0" + day;
            }
            this.planetSetArray[4] = String.valueOf(day) + "  " + this.makeTimeString(this.caldat(3, i));
            if (this.altPlanet[4] > 0.0 && this.currentJD > this.utSunSet) {
                this.visArray[4] = "(+)";
            }
            else {
                this.visArray[4] = "(-)";
            }
        }
    }
    
    public void saturn(double jd) {
        this.sat = new SaturnCompute(jd);
        this.planetRA = this.sat.alpha() / 15.0;
        this.planetDec = this.sat.delta();
        this.planetH = this.THETA0(jd) + this.longitude - 15.0 * this.planetRA;
        this.planetElev = this.planetAlt(jd, this.planetRA, this.planetDec);
        this.planetAzim = Math.atan2(Math.sin(0.017453292519943295 * this.planetH), Math.cos(0.017453292519943295 * this.planetH) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.planetDec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        switch (this.directionItem) {
            case 1: {
                this.planetAzim += 180.0;
                break;
            }
            case 2: {
                this.planetAzim += 90.0;
                break;
            }
            case 4: {
                this.planetAzim += 270.0;
                break;
            }
        }
        this.xSat = this.planetX(this.planetElev, this.planetAzim);
        this.ySat = this.planetY(this.planetElev, this.planetAzim);
        this.altPlanet[5] = this.planetElev;
        this.azPlanet[5] = this.planetAzim + 180.0;
        if (this.planetRiseOK) {
            jd = (int)this.currentJD - 0.5;
            SaturnCompute satc = new SaturnCompute(jd);
            double j0 = jd;
            double i = 0.0;
            for (int k = 0; k < 25; ++k) {
                i = j0 + k / 24.0;
                satc = new SaturnCompute(i);
                this.planetRA = satc.alpha() / 15.0;
                this.planetDec = satc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev > 0.0 && k == 0) {
                    j0 += 0.5;
                }
                if (this.planetElev > 0.0 && k > 0) {
                    i -= 0.041666666666666664;
                    break;
                }
            }
            for (int l = -2; l < 63; ++l) {
                i += 6.944444444444445E-4;
                satc = new SaturnCompute(i);
                this.planetRA = satc.alpha() / 15.0;
                this.planetDec = satc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev > this.starRise) {
                    break;
                }
            }
            i -= 3.4722222222222224E-4;
            String day = String.valueOf((int)this.caldat(1, i));
            if ((int)this.caldat(1, i) < 10) {
                day = "0" + day;
            }
            this.planetRiseArray[5] = String.valueOf(day) + "  " + this.makeTimeString(this.caldat(3, i));
            j0 = i;
            for (int m = 0; m < 25; ++m) {
                i = j0 + m / 24.0;
                satc = new SaturnCompute(i);
                this.planetRA = satc.alpha() / 15.0;
                this.planetDec = satc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev < 0.0 && m == 0) {
                    j0 += 0.5;
                }
                if (this.planetElev < 0.0 && m > 0) {
                    i -= 0.041666666666666664;
                    break;
                }
            }
            for (int i2 = -1; i2 < 62; ++i2) {
                i += 6.944444444444445E-4;
                satc = new SaturnCompute(i);
                this.planetRA = satc.alpha() / 15.0;
                this.planetDec = satc.delta();
                this.planetElev = this.planetAlt(i, this.planetRA, this.planetDec);
                if (this.planetElev < this.starRise) {
                    break;
                }
            }
            i -= 3.4722222222222224E-4;
            day = String.valueOf((int)this.caldat(1, i));
            if ((int)this.caldat(1, i) < 10) {
                day = "0" + day;
            }
            this.planetSetArray[5] = String.valueOf(day) + "  " + this.makeTimeString(this.caldat(3, i));
            if (this.altPlanet[5] > 0.0 && this.currentJD > this.utSunSet) {
                this.visArray[5] = "(+)";
            }
            else {
                this.visArray[5] = "(-)";
            }
        }
    }
    
    int planetX(final double elev, final double az) {
        final double f = (90.0 - elev) * this.Radius / 90.0;
        return this.x0 + (int)Math.round(f * Math.sin(az * 0.017453292519943295));
    }
    
    int planetY(final double elev, final double az) {
        final double f = (90.0 - elev) * this.Radius / 90.0;
        return this.y0 + (int)Math.round(f * Math.cos(az * 0.017453292519943295));
    }
    
    double mag(final double jd, final int planet) {
        final EarthCompute earthComp = new EarthCompute(jd);
        final double RE = earthComp.earthR();
        double RP = 0.0;
        double Phi = 0.0;
        double D = 0.0;
        double absPhi = 0.0;
        double MAG = 0.0;
        final double T = (jd - 2451545.0) / 36525.0;
        switch (planet) {
            case 1: {
                RP = this.mer.R();
                D = this.mer.earthDist();
                final double i = (RP * RP + D * D - RE * RE) / (2.0 * RP * D);
                Phi = (absPhi = Math.acos(i) / 0.017453292519943295);
                MAG = -0.42 + 5.0 * Math.log(RP * D) * 0.43429448 + 0.038 * absPhi - 2.73E-4 * absPhi * absPhi + 2.0E-6 * absPhi * absPhi * absPhi;
                break;
            }
            case 2: {
                RP = this.ven.R();
                D = this.ven.earthDist();
                final double i = (RP * RP + D * D - RE * RE) / (2.0 * RP * D);
                Phi = (absPhi = Math.acos(i) / 0.017453292519943295);
                MAG = -4.4 + 5.0 * Math.log(RP * D) * 0.43429448 + 9.0E-4 * absPhi + 2.39E-4 * absPhi * absPhi - 6.5E-7 * absPhi * absPhi * absPhi;
                break;
            }
            case 3: {
                RP = this.mar.R();
                D = this.mar.earthDist();
                final double i = (RP * RP + D * D - RE * RE) / (2.0 * RP * D);
                Phi = (absPhi = Math.acos(i) / 0.017453292519943295);
                MAG = -1.52 + 5.0 * Math.log(RP * D) * 0.43429448 + 0.016 * absPhi;
                break;
            }
            case 4: {
                RP = this.jup.R();
                D = this.jup.earthDist();
                final double i = (RP * RP + D * D - RE * RE) / (2.0 * RP * D);
                Phi = (absPhi = Math.acos(i) / 0.017453292519943295);
                MAG = -9.4 + 5.0 * Math.log(RP * D) * 0.43429448 + 0.005 * absPhi;
                break;
            }
            case 5: {
                RP = this.sat.R();
                D = this.sat.earthDist();
                final double i = (RP * RP + D * D - RE * RE) / (2.0 * RP * D);
                Phi = Math.acos(i) / 0.017453292519943295;
                final double omega = 169.50847 + 1.394681 * T + 4.12E-4 * T * T;
                final double ii = 28.075216 - 0.012998 * T + 4.0E-6 * T * T;
                final double sinB = Math.sin(0.017453292519943295 * ii) * Math.cos(0.017453292519943295 * this.sat.beta()) * Math.sin(0.017453292519943295 * (this.sat.lambda() - omega)) - Math.cos(0.017453292519943295 * ii) * Math.sin(0.017453292519943295 * this.sat.beta());
                final double B = Math.asin(sinB) / 0.017453292519943295;
                MAG = -8.88 + 5.0 * Math.log(RP * D) * 0.43429448 + 0.044 * Math.abs(Phi) - 2.6 * Math.sin(0.017453292519943295 * Math.abs(B)) + 1.25 * Math.sin(0.017453292519943295 * B) * Math.sin(0.017453292519943295 * B);
                break;
            }
        }
        return Math.round(10.0 * MAG) / 10.0;
    }
    
    public void paint(final Graphics g) {
        final int w = this.size().width;
        final int H = this.size().height;
        this.x0 = w / 2;
        this.y0 = 45 + (H - 50) / 2;
        this.Radius = this.x0 - 20;
        final int planetR = 4;
        final String[] dirStr = new String[4];
        this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.currentJD = this.JD(this.date, this.month + 1, this.year + 1900, this.UT);
        g.drawRect(1, 1, w - 2, H - 2);
        this.rs = new Rise_Set(this.dat, this.latitude, this.longitude, this.locOffset, 1);
        final double curElev = this.rs.current_Elev();
        int ySun = (int)Math.round(4.166666666666667 * this.rs.current_Elev());
        final int ySun2 = this.size().height - 110;
        if (!this.printOK) {
            g.setColor(new Color(155, 205, 255));
            g.fillRect(w - 60, ySun2 - 25, 30, 25);
            g.drawLine(w - 60, ySun2, w - 30, ySun2);
            g.setColor(new Color(225, 225, 225));
            g.fillRect(w - 60, ySun2, 30, 25);
            g.setColor(Color.red);
            g.drawLine(w - 60, ySun2, w - 30, ySun2);
            g.setColor(new Color(200, 200, 200));
            g.fillRect(w - 60, ySun2 + 25, 30, 25);
            g.setColor(Color.black);
            g.drawLine(w - 60, ySun2 + 25, w - 30, ySun2 + 25);
            g.setColor(new Color(175, 175, 175));
            g.fillRect(w - 60, ySun2 + 50, 30, 25);
            g.setColor(Color.black);
            g.drawLine(w - 60, ySun2 + 50, w - 30, ySun2 + 50);
            g.setColor(new Color(140, 140, 140));
            g.fillRect(w - 60, ySun2 + 75, 30, 25);
            g.setColor(Color.black);
            g.drawLine(w - 60, ySun2 + 75, w - 30, ySun2 + 75);
            g.setColor(Color.black);
            g.setFont(new Font("Helvetica", 0, 10));
            g.drawString("0" + this.deg, w - 25, ySun2 + 5);
            g.drawString("-6" + this.deg, w - 25, ySun2 + 30);
            g.drawString("-12" + this.deg, w - 25, ySun2 + 55);
            g.drawString("-18" + this.deg, w - 25, ySun2 + 80);
            if (curElev < -18.0) {
                ySun = -85;
            }
            if (curElev > 5.0) {
                ySun = 15;
            }
            g.setColor(Color.yellow);
            g.fillOval(w - 45 - 3, ySun2 - ySun - 3, 6, 6);
            g.setColor(Color.red);
            g.drawOval(w - 45 - 4, ySun2 - ySun - 4, 8, 8);
        }
        g.setColor(Color.black);
        final String[] riseStr = new String[8];
        final String[] setStr = new String[8];
        riseStr[1] = "Sun Rise" + this.rs.rise_String();
        setStr[1] = "Sun Set " + this.rs.set_String();
        this.utSunRise = this.rs.h_rise() - this.locOffset;
        this.utSunSet = this.rs.h_set() - this.locOffset;
        this.planetRiseArray[6] = "    " + this.makeTimeString(this.utSunRise);
        this.planetSetArray[6] = "    " + this.makeTimeString(this.utSunSet);
        if (curElev > 0.0) {
            this.visArray[6] = "(+)";
        }
        else {
            this.visArray[6] = "(-)";
        }
        this.rs = new Rise_Set(this.dat, this.latitude, this.longitude, this.locOffset, 2);
        riseStr[2] = "Civ Tl  " + this.rs.rise_String();
        setStr[2] = "Civ Tl  " + this.rs.set_String();
        this.rs = new Rise_Set(this.dat, this.latitude, this.longitude, this.locOffset, 3);
        riseStr[3] = "Naut Tl " + this.rs.rise_String();
        setStr[3] = "Naut Tl " + this.rs.set_String();
        this.rs = new Rise_Set(this.dat, this.latitude, this.longitude, this.locOffset, 4);
        riseStr[4] = "Astr Tl " + this.rs.rise_String();
        setStr[4] = "Astr Tl " + this.rs.set_String();
        if (!this.printOK) {
            if (curElev > -0.833) {
                g.setColor(new Color(155, 205, 255));
            }
            if (curElev < -0.833 && curElev > -6.0) {
                g.setColor(new Color(225, 225, 225));
            }
            if (curElev < -6.0 && curElev > -12.0) {
                g.setColor(new Color(200, 200, 200));
            }
            if (curElev < -12.0 && curElev > -18.0) {
                g.setColor(new Color(175, 175, 175));
            }
            if (curElev < -18.0) {
                g.setColor(new Color(140, 140, 140));
            }
            g.fillOval(this.x0 - this.Radius, this.y0 - this.Radius, 2 * this.Radius, 2 * this.Radius);
        }
        double h = this.THETA0(this.currentJD) + this.longitude - 15.0 * this.rs.sunDecRA(2, this.currentJD);
        double elev = curElev;
        final double trueAzim;
        double azim = trueAzim = Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.rs.sunDecRA(1, this.currentJD)) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        switch (this.directionItem) {
            case 1: {
                azim += 180.0;
                break;
            }
            case 2: {
                azim += 90.0;
                break;
            }
            case 4: {
                azim += 270.0;
                break;
            }
        }
        int X = this.x0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.sin(azim * 0.017453292519943295));
        int Y = this.y0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.cos(azim * 0.017453292519943295));
        g.setColor(Color.yellow);
        if (elev > -0.8) {
            g.fillOval(X - 8, Y - 8, 16, 16);
            g.setColor(Color.red);
            g.drawOval(X - 8, Y - 8, 16, 16);
        }
        else {
            X = this.x0 + (int)Math.round(this.Radius * Math.sin(azim * 0.017453292519943295));
            Y = this.y0 + (int)Math.round(this.Radius * Math.cos(azim * 0.017453292519943295));
            final int X2 = this.x0 + (int)Math.round((this.Radius - 10) * Math.sin(azim * 0.017453292519943295));
            final int Y2 = this.y0 + (int)Math.round((this.Radius - 10) * Math.cos(azim * 0.017453292519943295));
            g.fillOval(X - 3, Y - 3, 6, 6);
            g.drawLine(X, Y, X2, Y2);
        }
        this.azPlanet[6] = trueAzim + 180.0;
        this.altPlanet[6] = curElev;
        if (this.locOffset >= 0) {
            riseStr[1] = String.valueOf(riseStr[1]) + "   UT + " + this.locOffset + "h";
        }
        else {
            riseStr[1] = String.valueOf(riseStr[1]) + "   UT - " + Math.abs(this.locOffset) + "h";
        }
        final Moon myMoon = new Moon(this.dat, this.latitude, this.longitude, this.locOffset);
        riseStr[5] = "Moon R.  " + myMoon.riseString();
        setStr[5] = "Moon S.  " + myMoon.setString();
        this.planetRiseArray[7] = "    " + this.makeTimeString(myMoon.h_rise() - this.locOffset);
        this.planetSetArray[7] = "    " + this.makeTimeString(myMoon.h_set() - this.locOffset);
        this.phase = myMoon.phase();
        final double phaseMinus = myMoon.phaseMinus(0.3);
        final double phasePlus = myMoon.phasePlus(0.3);
        String str = "( )";
        if (this.phase > phaseMinus && this.phase < phasePlus) {
            str = " (+)";
        }
        if (this.phase < phaseMinus && this.phase > phasePlus) {
            str = " (-)";
        }
        riseStr[6] = Math.round(1000.0 * this.phase) / 10.0 + "%" + str;
        setStr[6] = "";
        g.setColor(Color.black);
        g.setFont(new Font("Courier", 0, 10));
        for (int i = 1; i < 7; ++i) {
            g.drawString(riseStr[i], 20, 51 + i * 14);
            g.drawString(setStr[i], w - 100, 52 + i * 14);
        }
        g.drawString(this.locStr, 20, H - 90);
        if (this.latitude >= 0.0) {
            this.nsStr = " N";
        }
        else {
            this.nsStr = " S";
        }
        if (this.longitude >= 0.0) {
            this.ewStr = " E";
        }
        else {
            this.ewStr = " W";
        }
        g.drawString("Lat.  " + Math.round(100.0 * Math.abs(this.latitude)) / 100.0 + this.nsStr, 20, H - 78);
        g.drawString("Long. " + Math.abs(this.longitude) + this.ewStr, 20, H - 66);
        g.drawString("LST   " + this.HMS(this.LM_Sidereal_Time(this.currentJD, -this.longitude)), 20, H - 54);
        g.setColor(Color.red);
        if (this.clickStr.length() > 0) {
            g.drawString(this.clickStr, 20, H - 15);
        }
        g.drawString(this.planetStr, 20, H - 3);
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString(this.vers, w / 2 - 140, H - 5);
        int rStar = 2;
        int x = 0;
        int y = 0;
        this.dec = 89.264;
        h = this.THETA0(this.currentJD) + this.longitude - 37.949999999999996;
        elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * this.dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * h);
        elev = Math.asin(elev) / 0.017453292519943295;
        azim = Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        switch (this.directionItem) {
            case 1: {
                azim += 180.0;
                break;
            }
            case 2: {
                azim += 90.0;
                break;
            }
            case 4: {
                azim += 270.0;
                break;
            }
        }
        X = this.x0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.sin(azim * 0.017453292519943295));
        Y = this.y0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.cos(azim * 0.017453292519943295));
        g.setColor(Color.red);
        g.fillOval(X - rStar, Y - rStar, 2 * rStar, 2 * rStar);
        h = this.THETA0(this.currentJD) + this.longitude - 0.0;
        if (this.latitude >= 0.0) {
            elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(1.5707963267948966) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(1.5707963267948966) * Math.cos(0.017453292519943295 * h);
        }
        else {
            elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(-1.5707963267948966) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(1.5707963267948966) * Math.cos(0.017453292519943295 * h);
        }
        elev = Math.asin(elev) / 0.017453292519943295;
        azim = Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(1.5707963267948966) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        int r = Math.abs((int)Math.round(elev * this.Radius / 90.0 * Math.cos(azim * 0.017453292519943295)));
        if (Math.abs(this.latitude) == 90.0) {
            r = this.Radius;
        }
        switch (this.directionItem) {
            case 1: {
                azim += 180.0;
                break;
            }
            case 2: {
                azim += 90.0;
                break;
            }
            case 4: {
                azim += 270.0;
                break;
            }
        }
        X = this.x0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.sin(azim * 0.017453292519943295));
        if (this.latitude >= 0.0) {
            Y = this.y0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.cos(azim * 0.017453292519943295));
        }
        else {
            Y = this.y0 - (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.cos(azim * 0.017453292519943295));
        }
        g.setColor(Color.green);
        g.drawOval(X - 3, Y - 3, 6, 6);
        if (this.circumpolarOK) {
            g.drawOval(X - r, Y - r, 2 * r, 2 * r);
        }
        g.setColor(Color.darkGray);
        for (int j = 0; j < 12; ++j) {
            y = (int)Math.round(this.Radius * Math.cos(0.017453292519943295 * j * 30.0));
            x = (int)Math.round(this.Radius * Math.sin(0.017453292519943295 * j * 30.0));
            g.drawLine(this.x0, this.y0, this.x0 + x, this.y0 - y);
        }
        g.setColor(Color.red);
        switch (this.directionItem) {
            case 1:
            case 3: {
                g.drawLine(this.x0, this.y0 - this.Radius, this.x0, this.y0 + this.Radius);
                break;
            }
            case 2:
            case 4: {
                g.drawLine(this.x0 - this.Radius, this.y0, this.x0 + this.Radius, this.y0);
                break;
            }
        }
        g.setColor(Color.darkGray);
        for (int k = 0; k <= 9; ++k) {
            x = Math.round(k * this.Radius / 9);
            g.drawOval(this.x0 - x, this.y0 - x, 2 * x, 2 * x);
        }
        if (this.eclipticOK) {
            g.setColor(Color.red);
            g.setFont(new Font("Helvetica", 0, 9));
            for (int l = 0; l <= 360; ++l) {
                this.dec = 23.44 * Math.sin(0.017453292519943295 * l);
                h = this.THETA0(this.currentJD) + this.longitude - l;
                elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * this.dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * h);
                elev = Math.asin(elev) / 0.017453292519943295;
                azim = Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
                switch (this.directionItem) {
                    case 1: {
                        azim += 180.0;
                        break;
                    }
                    case 2: {
                        azim += 90.0;
                        break;
                    }
                    case 4: {
                        azim += 270.0;
                        break;
                    }
                }
                X = this.x0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.sin(azim * 0.017453292519943295));
                Y = this.y0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.cos(azim * 0.017453292519943295));
                if ((X - this.x0) * (X - this.x0) + (Y - this.y0) * (Y - this.y0) < this.Radius * this.Radius) {
                    g.drawLine(X, Y, X, Y);
                    if (l % 15 == 0) {
                        g.fillOval(X - 1, Y - 1, 2, 2);
                    }
                    if (l % 30 == 0) {
                        g.drawString(String.valueOf(l / 15), X + 2, Y);
                    }
                }
            }
        }
        if (this.equatorOK) {
            g.setColor(Color.blue);
            for (int l = 0; l <= 360; ++l) {
                this.dec = 0.0;
                h = this.THETA0(this.currentJD) + this.longitude - l;
                elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * this.dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * h);
                elev = Math.asin(elev) / 0.017453292519943295;
                azim = Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
                switch (this.directionItem) {
                    case 1: {
                        azim += 180.0;
                        break;
                    }
                    case 2: {
                        azim += 90.0;
                        break;
                    }
                    case 4: {
                        azim += 270.0;
                        break;
                    }
                }
                X = this.x0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.sin(azim * 0.017453292519943295));
                Y = this.y0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.cos(azim * 0.017453292519943295));
                if ((X - this.x0) * (X - this.x0) + (Y - this.y0) * (Y - this.y0) < this.Radius * this.Radius) {
                    g.drawLine(X, Y, X, Y);
                }
            }
        }
        if (this.kolurOK) {
            g.setColor(Color.magenta);
            h = this.THETA0(this.currentJD) + this.longitude;
            for (int l = -90; l <= 90; ++l) {
                this.dec = l;
                elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * this.dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * h);
                elev = Math.asin(elev) / 0.017453292519943295;
                azim = Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
                switch (this.directionItem) {
                    case 1: {
                        azim += 180.0;
                        break;
                    }
                    case 2: {
                        azim += 90.0;
                        break;
                    }
                    case 4: {
                        azim += 270.0;
                        break;
                    }
                }
                X = this.x0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.sin(azim * 0.017453292519943295));
                Y = this.y0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.cos(azim * 0.017453292519943295));
                if ((X - this.x0) * (X - this.x0) + (Y - this.y0) * (Y - this.y0) < this.Radius * this.Radius) {
                    g.drawLine(X, Y, X, Y);
                    if (l != 90 && l % 10 == 0) {
                        g.fillOval(X - 1, Y - 1, 2, 2);
                    }
                    if (l % 30 == 0) {
                        g.drawString(String.valueOf(l), X + 2, Y);
                    }
                }
            }
            h = this.THETA0(this.currentJD) + this.longitude - 180.0;
            for (int m = -90; m <= 90; ++m) {
                this.dec = m;
                elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * this.dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * h);
                elev = Math.asin(elev) / 0.017453292519943295;
                azim = Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
                switch (this.directionItem) {
                    case 1: {
                        azim += 180.0;
                        break;
                    }
                    case 2: {
                        azim += 90.0;
                        break;
                    }
                    case 4: {
                        azim += 270.0;
                        break;
                    }
                }
                X = this.x0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.sin(azim * 0.017453292519943295));
                Y = this.y0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.cos(azim * 0.017453292519943295));
                if ((X - this.x0) * (X - this.x0) + (Y - this.y0) * (Y - this.y0) < this.Radius * this.Radius) {
                    g.drawLine(X, Y, X, Y);
                    if (m != 90 && m % 10 == 0) {
                        g.fillOval(X - 1, Y - 1, 2, 2);
                    }
                    if (m % 30 == 0) {
                        g.drawString(String.valueOf(m), X + 2, Y);
                    }
                }
            }
        }
        g.setColor(Color.red);
        switch (this.directionItem) {
            case 1: {
                dirStr[0] = "S";
                dirStr[1] = "E";
                dirStr[2] = "N";
                dirStr[3] = "W";
                break;
            }
            case 2: {
                dirStr[0] = "W";
                dirStr[1] = "S";
                dirStr[2] = "E";
                dirStr[3] = "N";
                break;
            }
            case 3: {
                dirStr[0] = "N";
                dirStr[1] = "W";
                dirStr[2] = "S";
                dirStr[3] = "E";
                break;
            }
            case 4: {
                dirStr[0] = "E";
                dirStr[1] = "N";
                dirStr[2] = "W";
                dirStr[3] = "S";
                break;
            }
        }
        int dx = 0;
        int dy = 0;
        g.setFont(new Font("Chicago", 0, 12));
        for (int n = 0; n <= 3; ++n) {
            if (n == 0) {
                dx = -4;
                dy = 13;
            }
            if (n == 1) {
                dx = 4;
                dy = 5;
            }
            if (n == 2) {
                dx = -4;
                dy = -5;
            }
            if (n == 3) {
                dx = -12;
                dy = 5;
            }
            y = dy + this.y0 - (int)Math.round(this.Radius * Math.cos(0.017453292519943295 * n * 90.0));
            x = dx + this.x0 + (int)Math.round(this.Radius * Math.sin(0.017453292519943295 * n * 90.0));
            g.drawString(dirStr[n], x, y);
        }
        final Moon my_moon = new Moon(this.dat, this.latitude, this.longitude, this.locOffset);
        final double raMoon = my_moon.moonRA();
        final double decMoon = my_moon.moonDEC();
        final double hMoon = this.THETA0(this.currentJD) + this.longitude - 15.0 * raMoon;
        double elevMoon = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * decMoon) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * decMoon) * Math.cos(0.017453292519943295 * hMoon);
        elevMoon = Math.asin(elevMoon) / 0.017453292519943295;
        double azimMoon = Math.atan2(Math.sin(0.017453292519943295 * hMoon), Math.cos(0.017453292519943295 * hMoon) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * decMoon) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        this.azPlanet[7] = azimMoon + 180.0;
        switch (this.directionItem) {
            case 1: {
                azimMoon += 180.0;
                break;
            }
            case 2: {
                azimMoon += 90.0;
                break;
            }
            case 4: {
                azimMoon += 270.0;
                break;
            }
        }
        if (azimMoon < 0.0) {
            azimMoon += 360.0;
        }
        if (azimMoon > 360.0) {
            azimMoon -= 360.0;
        }
        this.altPlanet[7] = elevMoon;
        if (elevMoon > 0.0) {
            this.visArray[7] = "(+)";
        }
        else {
            this.visArray[7] = "(-)";
        }
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString("SUN   MOON", w - 130, this.size().height - 48);
        g.setFont(new Font("Courier", 0, 10));
        for (str = new StringBuffer().append(Math.round(10.0 * curElev) / 10.0).append(this.deg).toString(); str.length() < 6; str = " " + str) {}
        g.drawString("Alt. " + str, w - 170, this.size().height - 34);
        for (str = new StringBuffer().append(Math.round(10.0 * elevMoon) / 10.0).append(this.deg).toString(); str.length() < 6; str = " " + str) {}
        g.drawString(str, w - 100, this.size().height - 34);
        for (str = new StringBuffer().append(Math.round(10.0 * (trueAzim + 180.0)) / 10.0).append(this.deg).toString(); str.length() < 6; str = " " + str) {}
        g.drawString("Azim " + str, w - 170, this.size().height - 20);
        for (str = new StringBuffer().append(Math.round(10.0 * this.azPlanet[7]) / 10.0).append(this.deg).toString(); str.length() < 6; str = " " + str) {}
        g.drawString(str, w - 100, this.size().height - 20);
        if (elevMoon > 0.0) {
            final int gray = (int)(this.phase * 255.0);
            final Color moonColor = new Color(gray, gray, gray);
            X = this.x0 + (int)Math.round((90.0 - elevMoon) * this.Radius / 90.0 * Math.sin(azimMoon * 0.017453292519943295));
            Y = this.y0 + (int)Math.round((90.0 - elevMoon) * this.Radius / 90.0 * Math.cos(azimMoon * 0.017453292519943295));
            if ((X - this.x0) * (X - this.x0) + (Y - this.y0) * (Y - this.y0) < this.Radius * this.Radius) {
                g.setColor(moonColor);
                g.fillOval(X - 8, Y - 8, 16, 16);
                g.setColor(Color.black);
                g.drawOval(X - 8, Y - 8, 16, 16);
            }
        }
        else {
            X = this.x0 + (int)Math.round(this.Radius * Math.sin(azimMoon * 0.017453292519943295));
            Y = this.y0 + (int)Math.round(this.Radius * Math.cos(azimMoon * 0.017453292519943295));
            final int X3 = this.x0 + (int)Math.round((this.Radius - 10) * Math.sin(azimMoon * 0.017453292519943295));
            final int Y3 = this.y0 + (int)Math.round((this.Radius - 10) * Math.cos(azimMoon * 0.017453292519943295));
            g.drawOval(X - 3, Y - 3, 6, 6);
            g.drawLine(X, Y, X3, Y3);
        }
        if (this.starsOK) {
            final StarData myValue = new StarData();
            final int nStar = myValue.nStarData();
            g.setFont(new Font("Helvetica", 0, 10));
            for (int i2 = 0; i2 <= nStar; ++i2) {
                this.ra = 15.0 * myValue.getRA(i2);
                this.dec = myValue.getDec(i2);
                final double magnitudo = myValue.getMag(i2);
                final String name = myValue.star(i2, 3);
                if (magnitudo < 1.0) {
                    rStar = 3;
                }
                else if (magnitudo < 2.0) {
                    rStar = 2;
                }
                else if (magnitudo < 3.9) {
                    rStar = 1;
                }
                h = this.THETA0(this.currentJD) + this.longitude - 15.0 * this.ra;
                elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * this.dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * h);
                elev = Math.asin(elev) / 0.017453292519943295;
                String s;
                if (elev > 0.0) {
                    s = "(+)  ";
                }
                else {
                    s = "(-)  ";
                }
                this.constellStr[i2] = s;
                azim = Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
                switch (this.directionItem) {
                    case 1: {
                        azim += 180.0;
                        break;
                    }
                    case 2: {
                        azim += 90.0;
                        break;
                    }
                    case 4: {
                        azim += 270.0;
                        break;
                    }
                }
                X = this.x0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.sin(azim * 0.017453292519943295));
                Y = this.y0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.cos(azim * 0.017453292519943295));
                if (this.clickStr.length() > 0 && this.starNum == i2) {
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.black);
                }
                if ((X - this.x0) * (X - this.x0) + (Y - this.y0) * (Y - this.y0) < this.Radius * this.Radius) {
                    if (this.mag == 1.0 && magnitudo < 1.0) {
                        g.fillOval(X - rStar, Y - rStar, 2 * rStar, 2 * rStar);
                        if (this.nameOK) {
                            g.drawString(name, X + 3, Y);
                        }
                    }
                    if (this.mag == 2.0 && magnitudo < 2.0) {
                        g.fillOval(X - rStar, Y - rStar, 2 * rStar, 2 * rStar);
                        if (this.nameOK) {
                            g.drawString(name, X + 3, Y);
                        }
                    }
                    if (this.mag == 2.5 && magnitudo < 2.5) {
                        g.fillOval(X - rStar, Y - rStar, 2 * rStar, 2 * rStar);
                        if (this.nameOK) {
                            g.drawString(name, X + 3, Y);
                        }
                    }
                    if (this.mag == 3.0 && magnitudo < 3.0) {
                        g.fillOval(X - rStar, Y - rStar, 2 * rStar, 2 * rStar);
                        if (this.nameOK) {
                            g.drawString(name, X + 3, Y);
                        }
                    }
                    if (this.mag == 3.5 && magnitudo < 3.9) {
                        g.fillOval(X - rStar, Y - rStar, 2 * rStar, 2 * rStar);
                        if (this.nameOK) {
                            g.drawString(name, X + 3, Y);
                        }
                    }
                }
            }
        }
        this.starNum = 1000;
        if (this.constellOK) {
            this.constell = new Constellation();
            g.setColor(Color.red);
            g.setFont(new Font("Helvetica", 0, 11));
            for (int i3 = 0; i3 <= this.constell.number(); ++i3) {
                final String name = this.constell.value(i3)[2];
                Double myDouble = Double.valueOf(this.constell.value(i3)[0]);
                this.ra = myDouble;
                myDouble = Double.valueOf(this.constell.value(i3)[1]);
                this.dec = myDouble;
                h = this.THETA0(this.currentJD) + this.longitude - 15.0 * this.ra;
                elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * this.dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * h);
                elev = Math.asin(elev) / 0.017453292519943295;
                azim = Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
                switch (this.directionItem) {
                    case 1: {
                        azim += 180.0;
                        break;
                    }
                    case 2: {
                        azim += 90.0;
                        break;
                    }
                    case 4: {
                        azim += 270.0;
                        break;
                    }
                }
                X = this.x0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.sin(azim * 0.017453292519943295));
                Y = this.y0 + (int)Math.round((90.0 - elev) * this.Radius / 90.0 * Math.cos(azim * 0.017453292519943295));
                if ((X - this.x0) * (X - this.x0) + (Y - this.y0) * (Y - this.y0) < this.Radius * this.Radius) {
                    g.drawString(name, X + 3, Y);
                }
            }
        }
        if (this.planetsOK) {
            g.setFont(new Font("Helvetica", 0, 10));
            this.mars(this.currentJD);
            g.setColor(Color.red);
            if ((this.xMars - this.x0) * (this.xMars - this.x0) + (this.yMars - this.y0) * (this.yMars - this.y0) < this.Radius * this.Radius) {
                g.fillOval(this.xMars - planetR, this.yMars - planetR, 2 * planetR, 2 * planetR);
                g.setColor(Color.black);
                if (this.nameOK) {
                    g.drawString("Mars", this.xMars + 3, this.yMars);
                }
            }
            this.venus(this.currentJD);
            g.setColor(Color.green);
            if ((this.xVenus - this.x0) * (this.xVenus - this.x0) + (this.yVenus - this.y0) * (this.yVenus - this.y0) < this.Radius * this.Radius) {
                g.fillOval(this.xVenus - planetR, this.yVenus - planetR, 2 * planetR, 2 * planetR);
                g.setColor(Color.black);
                if (this.nameOK) {
                    g.drawString("Venus", this.xVenus + 3, this.yVenus);
                }
            }
            this.mercury(this.currentJD);
            g.setColor(new Color(200, 200, 0));
            if ((this.xMercury - this.x0) * (this.xMercury - this.x0) + (this.yMercury - this.y0) * (this.yMercury - this.y0) < this.Radius * this.Radius) {
                g.fillOval(this.xMercury - planetR, this.yMercury - planetR, 2 * planetR, 2 * planetR);
                g.setColor(Color.black);
                if (this.nameOK) {
                    g.drawString("Mercury", this.xMercury + 3, this.yMercury);
                }
            }
            this.jupiter(this.currentJD);
            g.setColor(Color.blue);
            if ((this.xJup - this.x0) * (this.xJup - this.x0) + (this.yJup - this.y0) * (this.yJup - this.y0) < this.Radius * this.Radius) {
                g.fillOval(this.xJup - planetR, this.yJup - planetR, 2 * planetR, 2 * planetR);
                g.setColor(Color.black);
                if (this.nameOK) {
                    g.drawString("Jupiter", this.xJup + 3, this.yJup);
                }
            }
            this.saturn(this.currentJD);
            g.setColor(Color.cyan);
            if ((this.xSat - this.x0) * (this.xSat - this.x0) + (this.ySat - this.y0) * (this.ySat - this.y0) < this.Radius * this.Radius) {
                g.fillOval(this.xSat - planetR, this.ySat - planetR, 2 * planetR, 2 * planetR);
                g.setColor(Color.black);
                if (this.nameOK) {
                    g.drawString("Saturn", this.xSat + 3, this.ySat);
                }
            }
        }
        if (this.demo) {
            final Font f = g.getFont();
            g.setFont(new Font("Chicago", 0, 96));
            g.setColor(Color.red);
            g.drawString("DEMO", this.size().width / 4, 300);
            g.setFont(f);
        }
        g.setColor(Color.red);
        if (this.AltAzOK) {
            g.setFont(new Font("Courier", 0, 10));
            g.drawString(this.AltAzStr, 20, H - 42);
        }
        if (this.RaDecOK) {
            g.setFont(new Font("Courier", 0, 10));
            g.drawString(this.RaDecStr, 20, H - 29);
        }
        if (this.analemmaOK) {
            g.setColor(Color.red);
            int k2 = 0;
            for (int m2 = 0; m2 < 12; ++m2) {
                for (int d = 1; d <= this.daysInMonth(m2, this.year); ++d) {
                    final double jd = this.JD(d, m2 + 1, this.year + 1900, this.UT);
                    final double decAna = this.sunDecRA(1, jd);
                    final double r_a = this.sunDecRA(2, jd);
                    final double hAna = this.planetAlt(jd, r_a, decAna);
                    final double azAna = this.computeAzimut(decAna, this.latitude, this.longitude, hAna);
                    this.analemmaX[k2] = this.x0 + (int)Math.round((90.0 - hAna) * this.Radius / 90.0 * Math.sin(azAna * 0.017453292519943295));
                    this.analemmaY[k2] = this.y0 - (int)Math.round((90.0 - hAna) * this.Radius / 90.0 * Math.cos(azAna * 0.017453292519943295));
                    ++k2;
                }
            }
            for (int i4 = 0; i4 < k2 - 1; ++i4) {
                if ((this.analemmaX[i4] - this.x0) * (this.analemmaX[i4] - this.x0) + (this.analemmaY[i4] - this.y0) * (this.analemmaY[i4] - this.y0) < this.Radius * this.Radius) {
                    g.drawLine(this.analemmaX[i4], this.analemmaY[i4], this.analemmaX[i4 + 1], this.analemmaY[i4 + 1]);
                }
            }
        }
    }
    
    public void constellList() {
        final String[] CON = { "And", "Ant", "Aps", "Aqr", "Aql", "Ara", "Ari", "Aur", "Boo", "Cae", "Cam", "Cnc", "CVn", "CMa", "CMi", "Cap", "Car", "Cas", "Cen", "Cep", "Cet", "Cha", "Cir", "Col", "Com", "CrA", "CrB", "Crv", "Crt", "Cru", "Cyg", "Del", "Dor", "Dra", "Equ", "Eri", "For", "Gem", "Gru", "Her", "Hor", "Hya", "Hyi", "Ind", "Lac", "Leo", "LMi", "Lep", "Lib", "Lup", "Lyn", "Lyr", "Men", "Mic", "Mon", "Mus", "Nor", "Oct", "Oph", "Ori", "Pav", "Peg", "Per", "Phe", "Pic", "Psc", "PsA", "Pup", "Pyx", "Ret", "Sge", "Sgr", "Sco", "Scl", "Sct", "Ser", "Sex", "Tau", "Tel", "Tri", "TrA", "Tuc", "UMa", "UMi", "Vel", "Vir", "Vol", "Vul" };
        final StarData myValue = new StarData();
        final int nStar = myValue.nStarData();
        final int constellNum = 0;
        this.nData1 = 0;
        this.dataStr1[0] = "(+) above horizon, (-) below horizon\n" + this.locStr + " " + this.latitude + this.nsStr + ", " + this.longitude + this.ewStr + "\n";
        final String[] dataStr1 = this.dataStr1;
        final int n = 0;
        dataStr1[n] = String.valueOf(dataStr1[n]) + this.dayArray[this.dat.getDay()] + " " + this.dat.toGMTString() + "\n";
        final String[] dataStr2 = this.dataStr1;
        final int n2 = 0;
        dataStr2[n2] = String.valueOf(dataStr2[n2]) + "                                 mag      RA (h)    Decl.(" + this.deg + ")" + "\n";
        for (int c = 0; c < 88; ++c) {
            for (int i = 0; i <= nStar; ++i) {
                if (CON[c] == myValue.star(i, 4)) {
                    ++this.nData1;
                    String str2 = String.valueOf(c) + "  " + myValue.star(i, 4) + "  " + myValue.star(i, 3);
                    if (c < 10) {
                        str2 = " " + str2;
                    }
                    while (str2.length() < 24) {
                        str2 = String.valueOf(str2) + " ";
                    }
                    for (str2 = String.valueOf(str2) + "  " + this.constellStr[i] + "  " + myValue.star(i, 2); str2.length() < 40; str2 = String.valueOf(str2) + " ") {}
                    for (str2 = String.valueOf(str2) + "  " + myValue.star(i, 0); str2.length() < 50; str2 = String.valueOf(str2) + " ") {}
                    str2 = String.valueOf(str2) + "  " + myValue.star(i, 1);
                    this.dataStr1[this.nData1] = String.valueOf(str2) + "\n";
                }
            }
        }
    }
    
    public void starList() {
        final StarData myValue = new StarData();
        this.nData = myValue.nStarData();
        this.dataStr[0] = String.valueOf(this.locStr) + " " + this.latitude + this.nsStr + ", " + this.longitude + this.ewStr + "\n";
        final String[] dataStr = this.dataStr;
        final int n = 0;
        dataStr[n] = String.valueOf(dataStr[n]) + this.dayArray[this.dat.getDay()] + " " + this.dat.toGMTString() + "\n";
        final String[] dataStr2 = this.dataStr;
        final int n2 = 0;
        dataStr2[n2] = String.valueOf(dataStr2[n2]) + "(+) Altitude>5°, (-) Altitude<5°\n";
        int ah = 0;
        for (int i = 0; i <= this.nData; ++i) {
            this.ra = myValue.getRA(i);
            this.dec = myValue.getDec(i);
            final double magnitudo = myValue.getMag(i);
            final String name = myValue.star(i, 3);
            final double h = this.THETA0(this.currentJD) + this.longitude - 15.0 * this.ra;
            double elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * this.dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * h);
            elev = Math.asin(elev) / 0.017453292519943295;
            String str1;
            for (str1 = name; str1.length() < 15; str1 = String.valueOf(str1) + " ") {}
            String s;
            if (elev > 5.0) {
                s = "(+)  ";
            }
            else {
                s = "(-)  ";
            }
            if (i > 1 && elev > 5.0) {
                ++ah;
            }
            str1 = String.valueOf(str1) + "  " + myValue.star(i, 4) + "  " + s;
            this.constellStr[i] = s;
            for (str1 = String.valueOf(str1) + magnitudo; str1.length() < 32; str1 = String.valueOf(str1) + " ") {}
            str1 = String.valueOf(i) + " " + str1;
            if (i < 100) {
                str1 = " " + str1;
            }
            if (i < 10) {
                str1 = " " + str1;
            }
            for (str1 = String.valueOf(str1) + "   " + this.ra; str1.length() < 49; str1 = String.valueOf(str1) + " ") {}
            this.dataStr[i + 1] = String.valueOf(str1) + this.dec + "\n";
        }
        final String[] dataStr3 = this.dataStr;
        final int n3 = 0;
        dataStr3[n3] = String.valueOf(dataStr3[n3]) + ah + " Stars with Altitude>5°" + "\n";
        final String[] dataStr4 = this.dataStr;
        final int n4 = 0;
        dataStr4[n4] = String.valueOf(dataStr4[n4]) + "                               mag     RA (h)    Decl.(" + this.deg + ")" + "\n";
        ++this.nData;
    }
    
    public void planetList() {
        if (this.planetRiseOK) {
            this.mercury(this.currentJD);
            this.venus(this.currentJD);
            this.mars(this.currentJD);
            this.jupiter(this.currentJD);
            this.saturn(this.currentJD);
        }
        this.nData2 = 7;
        this.dataStr2[0] = String.valueOf(this.locStr) + "  " + this.latitude + this.nsStr + ", " + this.longitude + this.ewStr + "\n" + this.dayArray[this.dat.getDay()] + " " + this.dat.toGMTString() + "\n";
        final String[] dataStr2 = this.dataStr2;
        final int n = 0;
        dataStr2[n] = String.valueOf(dataStr2[n]) + "Rise, Set, and Date are UT\n";
        final String[] dataStr3 = this.dataStr2;
        final int n2 = 0;
        dataStr3[n2] = String.valueOf(dataStr3[n2]) + "         Vis     Mag      Alt.     Azim.   Date  Rise  Date   Set  Vis.\n";
        for (int i = 1; i <= 7; ++i) {
            String str2 = "";
            if (i < 6) {
                str2 = String.valueOf(this.mag(this.currentJD, i));
            }
            if (i == 7) {
                str2 = String.valueOf((int)Math.round(100.0 * this.phase)) + "%";
            }
            while (str2.length() < 8) {
                str2 = " " + str2;
            }
            String str3;
            for (str3 = new StringBuffer().append(Math.round(10.0 * this.azPlanet[i]) / 10.0).append(this.deg).toString(); str3.length() < 10; str3 = " " + str3) {}
            String str4;
            for (str4 = new StringBuffer().append(Math.round(10.0 * this.altPlanet[i]) / 10.0).append(this.deg).toString(); str4.length() < 10; str4 = " " + str4) {}
            this.dataStr2[i] = String.valueOf(this.planetArray[i]) + "  " + this.visArray[i] + str2 + str4 + str3 + "    " + this.planetRiseArray[i] + "   " + this.planetSetArray[i] + "\n";
        }
    }
    
    public SkyMap0996() {
        this.deg = '°';
        this.versStr = "Sky Map 0.996";
        this.vers = String.valueOf(this.versStr) + "  -  (c) 2005-2008 J. Giesen - www.GeoAstro.de";
        this.demo = true;
        this.mag = 2.0;
        this.monthArray = new String[12];
        this.directionItem = 3;
        this.nameOK = true;
        this.eclipticOK = true;
        this.equatorOK = true;
        this.constellOK = false;
        this.circumpolarOK = true;
        this.kolurOK = true;
        this.dataStr = new String[150];
        this.dataStr1 = new String[150];
        this.constellStr = new String[150];
        this.dataStr2 = new String[15];
        this.clickStr = "";
        this.planetStr = "";
        this.starNum = 1000;
        this.starsOK = true;
        this.planetsOK = true;
        this.printOK = false;
        this.azPlanet = new double[8];
        this.altPlanet = new double[8];
        this.planetArray = new String[] { "", "Mercury", "Venus  ", "Mars   ", "Jupiter", "Saturn ", "Sun    ", "Moon   " };
        this.dayArray = new String[] { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri ", "Sat" };
        this.planetRiseOK = false;
        this.planetRiseArray = new String[8];
        this.planetSetArray = new String[8];
        this.visArray = new String[8];
        this.starRise = -0.567;
        this.key = 0;
        this.RaDecOK = true;
        this.AltAzOK = true;
        this.AltAzStr = "";
        this.RaDecStr = "";
        this.analemmaOK = false;
        this.analemmaX = new int[370];
        this.analemmaY = new int[370];
    }
}
