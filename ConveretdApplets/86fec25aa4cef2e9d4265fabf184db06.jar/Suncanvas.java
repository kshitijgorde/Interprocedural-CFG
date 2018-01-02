import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Font;
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
    final int y90 = 114;
    final int y0 = 326;
    final int xS = 300;
    final int xL = 30;
    final int xOben = 60;
    final int xLL = 30;
    final int unten = 380;
    final int Radius = 8;
    String s;
    int hInt;
    int azInt;
    int xInt;
    int hBildInt;
    int transInt;
    int min;
    int iRise;
    int iSet;
    double hBild;
    double xx;
    double STD;
    double GHA12;
    double equation;
    double trans;
    double diff;
    double hRise;
    double hSet;
    double hoehe1;
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
    int[] hArray;
    int[] xArray;
    int xSun;
    int hSun;
    int n;
    boolean visible;
    boolean notVisible;
    final int mapOben = 450;
    final int mapRechts = 490;
    String versStr;
    String[] hoeheStrArray;
    String[] azStrArray;
    boolean myConsole;
    String gmtStr;
    String declinStr;
    double currentDec;
    
    public Suncanvas(final Image myImage, final Date dat, final double latitude, final double longitude, final String locStr, final int locOffset, final String versStr, final boolean myConsole) {
        this.s = "";
        this.hArray = new int[25];
        this.xArray = new int[25];
        this.hoeheStrArray = new String[25];
        this.azStrArray = new String[25];
        this.myImage = myImage;
        this.versStr = versStr;
        this.myConsole = myConsole;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locStr = locStr;
        this.dat = dat;
        this.hours = this.dat.getHours();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.year = this.dat.getYear();
        this.browserOffset = this.dat.getTimezoneOffset();
        this.browserOffset = -this.browserOffset / 60;
        this.locOffset = locOffset;
        this.timeString = String.valueOf(this.browserOffset) + " h";
        if (this.browserOffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.comp = new Compute();
        this.dec = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, this.STD);
        this.currentDec = this.dec;
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.STD);
        this.str = String.valueOf(Math.round(10.0 * this.GHA) / 10.0);
        this.s = this.str.substring(0, this.str.indexOf(".") + 2);
        this.ghaStr = "GHA      = " + this.s + " deg.";
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
            this.str = ":";
        }
        else {
            this.str = ":0";
        }
        this.eqtStr = (int)this.equation + this.str + this.min + " min";
        if (this.equation < 0.0 && (int)this.equation == 0) {
            this.eqtStr = "-" + (int)this.equation + this.str + this.min + " min";
        }
        this.trans = 12.0 - this.GHA12 / 15.0 - this.longitude / 15.0;
        this.trans += this.locOffset;
        this.transInt = (int)this.trans;
        this.diff = this.trans - this.transInt;
        this.min = (int)Math.round(this.diff * 60.0);
        if (this.min > 9) {
            this.str = ":";
        }
        else {
            this.str = ":0";
        }
        this.transStr = "Culm.     " + this.transInt + this.str + this.min;
        this.hoehe = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
        this.str = String.valueOf(Math.round(10.0 * this.hoehe) / 10.0);
        this.s = this.str.substring(0, this.str.indexOf(".") + 2);
        this.heightStr = "Elev.  = " + this.s + " deg.";
        this.azimut = this.comp.computeAzimut(this.dec, this.latitude, this.longitude, this.GHA, this.hoehe);
        this.str = String.valueOf(Math.round(10.0 * this.azimut) / 10.0);
        this.s = this.str.substring(0, this.str.indexOf(".") + 2);
        this.azStr = "Azimuth = " + this.s + " deg.";
        this.hBild = this.hoehe * 212.0 / 90.0;
        this.hBildInt = (int)Math.round(this.hBild);
        this.hSun = this.hBildInt;
        if (this.latitude >= this.dec) {
            this.xx = (180.0 - this.azimut) * 270.0 / 180.0;
        }
        else if (this.azimut < 180.0) {
            this.xx = this.azimut * 270.0 / 180.0;
        }
        else {
            this.xx = -(360.0 - this.azimut) * 270.0 / 180.0;
        }
        if (this.latitude >= 0.0) {
            this.xx = 300.0 - this.xx;
        }
        else {
            this.xx += 300.0;
        }
        this.xInt = (int)Math.round(this.xx);
        this.xSun = this.xInt;
        this.iRise = 100;
        this.iSet = 100;
        this.visible = true;
        this.notVisible = true;
        for (int i = -this.locOffset; i < -this.locOffset + 25; ++i) {
            this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, i - 1);
            final double computeDeclination = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, i);
            this.hoehe1 = this.comp.computeHeight(computeDeclination, this.latitude, this.longitude, this.GHA);
            this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, i);
            this.hoehe = this.comp.computeHeight(computeDeclination, this.latitude, this.longitude, this.GHA);
            if (this.hoehe > 0.0) {
                this.notVisible = false;
            }
            if (this.hoehe < 0.0) {
                this.visible = false;
            }
            if (this.hoehe > 0.0 && this.hoehe1 < 0.0) {
                this.iRise = i;
            }
            if (this.hoehe < 0.0 && this.hoehe1 > 0.0) {
                this.iSet = i;
            }
            this.hBild = this.hoehe * 212.0 / 90.0;
            this.hBildInt = (int)Math.round(this.hBild);
            this.azimut = this.comp.computeAzimut(computeDeclination, this.latitude, this.longitude, this.GHA, this.hoehe);
            if (this.latitude >= this.dec) {
                this.xx = (180.0 - this.azimut) * 270.0 / 180.0;
            }
            else if (this.azimut < 180.0) {
                this.xx = this.azimut * 270.0 / 180.0;
            }
            else {
                this.xx = -(360.0 - this.azimut) * 270.0 / 180.0;
            }
            if (this.latitude >= this.dec) {
                this.xx = 300.0 - this.xx;
            }
            else {
                this.xx += 300.0;
            }
            this.xInt = (int)Math.round(this.xx);
            this.hArray[i + this.locOffset] = this.hBildInt;
            this.xArray[i + this.locOffset] = this.xInt;
            this.str = String.valueOf(Math.round(10.0 * this.hoehe) / 10.0);
            this.hoeheStrArray[i + this.locOffset] = this.str.substring(0, this.str.indexOf(".") + 2);
            this.str = String.valueOf(Math.round(10.0 * this.azimut) / 10.0);
            this.azStrArray[i + this.locOffset] = this.str.substring(0, this.str.indexOf(".") + 2);
        }
        if (this.iRise == 100) {
            this.riseStr = "no Sunrise";
            if (this.visible) {
                this.riseStr = "Sun visible all day";
            }
        }
        else {
            this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.iRise);
            this.dec = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, this.iRise - 0.5);
            this.hoehe = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
            this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.iRise - 1);
            this.hoehe1 = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
            this.hRise = this.iRise - (this.hoehe + 0.8) / (this.hoehe - this.hoehe1);
            this.hRise += this.locOffset;
            this.diff = this.hRise - (int)this.hRise;
            this.min = (int)Math.round(this.diff * 60.0);
            if (this.min > 9) {
                this.str = ":";
            }
            else {
                this.str = ":0";
            }
            this.str = String.valueOf(String.valueOf((int)this.hRise) + this.str + this.min);
            this.riseStr = "Sunrise    " + this.str;
        }
        if (this.iSet == 100) {
            this.setStr = "no Sunset";
            if (this.notVisible) {
                this.setStr = "Sun unvisible all day";
            }
        }
        else {
            this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.iSet);
            this.dec = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, this.iSet + 0.5);
            this.hoehe = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
            this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.iSet - 1);
            this.hoehe1 = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
            this.hSet = this.iSet - 1.0 + (this.hoehe1 + 0.8) / (this.hoehe1 - this.hoehe);
            this.hSet += this.locOffset;
            if (this.hSet < 0.0) {
                this.hSet += 24.0;
            }
            this.diff = this.hSet - (int)this.hSet;
            this.min = (int)Math.round(this.diff * 60.0);
            if (this.min > 9) {
                this.str = ":";
            }
            else {
                this.str = ":0";
            }
            final String value = String.valueOf(String.valueOf((int)this.hSet) + this.str + this.min);
            this.str = value;
            this.str = value;
            this.setStr = "Sunset    " + this.str;
        }
        this.repaint();
    }
    
    public double sunL(final double n) {
        final double n2 = n / 10.0;
        double n3 = (280.4664567 + 360007.6982779 * n2 + 0.03032028 * n2 * n2 + n2 * n2 * n2 / 49931.0 - n2 * n2 * n2 * n2 / 15299.0 + n2 * n2 * n2 * n2 * n2 / 1988000.0) % 360.0;
        if (n3 < 0.0) {
            n3 += 360.0;
        }
        return n3;
    }
    
    public double deltaPSI(final double n) {
        final double sunL = this.sunL(n);
        double n2 = (218.3165 + 481267.8813 * n) % 360.0;
        if (n2 < 0.0) {
            n2 += 360.0;
        }
        final double n3 = 125.04452 - 1934.136261 * n + 0.0020708 * n * n + n * n * n / 450000.0;
        return (-17.2 * Math.sin(0.017453292519943295 * n3) - 1.32 * Math.sin(0.03490658503988659 * sunL) - 0.23 * Math.sin(0.03490658503988659 * n2) + 0.21 * Math.sin(0.03490658503988659 * n3)) / 3600.0;
    }
    
    public double EPS(final double n) {
        final double sunL = this.sunL(n);
        final double n2 = 218.3165 + 481267.8813 * n;
        final double n3 = 23.43929111111111 - (46.815 * n + 5.9E-4 * n * n - 0.001813 * n * n * n) / 3600.0;
        final double n4 = 125.04452 - 1934.136261 * n + 0.0020708 * n * n + n * n * n / 450000.0;
        return n3 + (9.2 * Math.cos(0.017453292519943295 * n4) + 0.57 * Math.cos(0.03490658503988659 * sunL) + 0.1 * Math.cos(0.03490658503988659 * n2) - 0.09 * Math.cos(0.03490658503988659 * n4)) / 3600.0;
    }
    
    public double eot(final int n, final int n2, final int n3, final double n4) {
        final double n5 = (this.JD(n, n2, n3, n4) - 2451545.0) / 36525.0;
        double n6 = this.sunL(n5) - 0.0057183 - this.RightAscension(n5) + this.deltaPSI(n5) * Math.cos(0.017453292519943295 * this.EPS(n5));
        if (n6 > 5.0) {
            n6 -= 360.0;
        }
        return n6 * 4.0;
    }
    
    public double RightAscension(final double n) {
        final double sunL = this.sunL(n);
        double n2 = (357.5291 + 35999.0503 * n - 1.559E-4 * n * n - 4.8E-7 * n * n * n) % 360.0;
        if (n2 < 0.0) {
            n2 += 360.0;
        }
        final double n3 = sunL + ((1.9146 - 0.004817 * n - 1.4E-5 * n * n) * Math.sin(0.017453292519943295 * n2) + (0.019993 - 1.01E-4 * n) * Math.sin(0.03490658503988659 * n2) + 2.9E-4 * Math.sin(0.05235987755982989 * n2));
        final double n4 = this.EPS(n) + 0.00256 * Math.cos(0.017453292519943295 * (125.04 - 1934.136 * n));
        final double n5 = n3 - 0.00569 - 0.00478 * Math.sin(0.017453292519943295 * (125.04 - 1934.136 * n));
        double n6 = Math.atan2(Math.cos(0.017453292519943295 * n4) * Math.sin(0.017453292519943295 * n5), Math.cos(0.017453292519943295 * n5)) / 0.017453292519943295;
        if (n6 < 0.0) {
            n6 += 360.0;
        }
        final double n7 = Math.asin(Math.sin(0.017453292519943295 * n4) * Math.sin(0.017453292519943295 * n5)) / 0.017453292519943295;
        return n6;
    }
    
    public double JD(final int n, int n2, int n3, final double n4) {
        if (n2 <= 2) {
            n2 += 12;
            --n3;
        }
        return 365.0 * n3 - 679004.0 + (n3 / 400 - n3 / 100 + n3 / 4) + (int)(30.6001 * (n2 + 1)) + n + n4 / 24.0 + 2400000.5;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawString("please wait...", 200, 200);
        final Font font = graphics.getFont();
        graphics.setFont(new Font("Helvetica", 0, 9));
        graphics.drawString("Sun & Earth Applet" + this.versStr + "       © 1998-2001 Juergen Giesen", 360, 82);
        graphics.setFont(font);
        graphics.drawImage(this.myImage, 30, 90, this);
        graphics.setFont(new Font("Chicago", 0, 12));
        graphics.setColor(Color.red);
        if (this.latitude >= this.dec) {
            graphics.drawString("S", 297, 108);
            this.n = (int)Math.round(135.0);
            graphics.drawString("E", 300 - this.n - 3, 108);
            graphics.drawString("W", 300 + this.n, 108);
        }
        else {
            graphics.drawString("N", 297, 108);
            this.n = (int)Math.round(135.0);
            graphics.drawString("W", 300 - this.n, 108);
            graphics.drawString("E", 300 + this.n - 3, 108);
        }
        graphics.setFont(font);
        this.str = "Browser's time zone offset is ";
        if (this.browserOffset > 0) {
            this.str = String.valueOf(this.str) + "+";
        }
        graphics.drawString(String.valueOf(this.str) + this.browserOffset + " h", 30, 82);
        graphics.setColor(Color.green);
        graphics.drawLine(297, 326, 303, 326);
        graphics.drawLine(300, 323, 300, 329);
        graphics.drawLine(300, 111, 300, 117);
        graphics.drawLine(303, 114, 303, 114);
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
        graphics.setColor(Color.blue);
        this.gmtStr = this.str;
        graphics.drawString(String.valueOf(this.str) + " GMT", 250, 82);
        graphics.drawString(this.dat.toString(), 30, 420);
        final String value = String.valueOf(this.currentDec);
        this.s = value.substring(0, value.indexOf(".") + 3);
        graphics.drawString(this.declinStr = "Declin. = " + this.s + " deg.", 490, 460);
        graphics.drawString(this.ghaStr, 490, 480);
        graphics.drawString("Equation of Time =", 490, 500);
        graphics.drawString(this.eqtStr, 490, 515);
        graphics.setColor(Color.red);
        graphics.drawString(this.locStr, 30, 435);
        graphics.setColor(Color.black);
        graphics.drawString(this.riseStr, 130, 435);
        graphics.drawString(this.transStr, 230, 435);
        graphics.drawString(this.setStr, 330, 435);
        graphics.setColor(Color.black);
        graphics.drawString(this.heightStr, 450, 420);
        graphics.drawString(this.azStr, 450, 435);
        if (326 - this.hSun < 400) {
            graphics.setColor(Color.yellow);
            graphics.fillOval(this.xSun - 8, 326 - this.hSun - 8, 16, 16);
            graphics.setColor(Color.red);
            graphics.drawOval(this.xSun - 8 - 1, 326 - this.hSun - 8 - 1, 18, 18);
        }
        else {
            graphics.setColor(Color.white);
            graphics.drawString("SUN", this.xSun - 10, 390);
            graphics.drawString("V", this.xSun - 4, 400);
        }
        final Font font2 = graphics.getFont();
        graphics.setFont(new Font("Helvetica", 0, 9));
        graphics.setColor(Color.black);
        if (this.myConsole) {
            this.writeConsole();
        }
        for (int i = -this.locOffset; i < -this.locOffset + 25; ++i) {
            this.n = i + this.locOffset;
            String s;
            if (this.n < 10) {
                s = " ";
            }
            else {
                s = "";
            }
            if (this.hoeheStrArray[i + this.locOffset].length() < 4) {
                this.hoeheStrArray[i + this.locOffset] = " " + this.hoeheStrArray[i + this.locOffset];
            }
            if (this.hoeheStrArray[i + this.locOffset].length() < 5) {
                this.hoeheStrArray[i + this.locOffset] = " " + this.hoeheStrArray[i + this.locOffset];
            }
            if (this.myConsole) {
                System.out.println(String.valueOf(s) + "    " + this.n + ":00           " + this.hoeheStrArray[i + this.locOffset] + "         " + this.azStrArray[i + this.locOffset]);
            }
            if (326 - this.hArray[i + this.locOffset] < 380) {
                graphics.setColor(Color.red);
                graphics.drawOval(this.xArray[i + this.locOffset] - 3, 326 - this.hArray[i + this.locOffset] - 3, 6, 6);
                graphics.setColor(Color.black);
                if (i % 2 == 0) {
                    graphics.drawString(String.valueOf(this.n), this.xArray[i + this.locOffset] + 7, 326 - this.hArray[i + this.locOffset] + 3);
                }
            }
        }
        if (this.myConsole) {
            System.out.println("------------------------------------------");
        }
        this.myConsole = false;
        graphics.setFont(font2);
    }
    
    void writeConsole() {
        System.out.println(" Sun & Earth Applet" + this.versStr + " - © 1998-2001 J. Giesen");
        System.out.println(" Location:  " + this.locStr);
        this.str = String.valueOf(Math.abs(this.latitude));
        try {
            this.str = this.str.substring(0, this.str.indexOf(".") + 3);
        }
        catch (StringIndexOutOfBoundsException ex) {
            this.str = String.valueOf(Math.abs(this.latitude));
        }
        if (this.latitude >= 0.0) {
            this.str = String.valueOf(this.str) + " N";
        }
        else {
            this.str = String.valueOf(this.str) + " S";
        }
        System.out.println(" Latitude:  " + this.str);
        this.str = String.valueOf(Math.abs(this.longitude));
        try {
            this.str = this.str.substring(0, this.str.indexOf(".") + 3);
        }
        catch (StringIndexOutOfBoundsException ex2) {
            this.str = String.valueOf(Math.abs(this.longitude));
        }
        if (this.longitude >= 0.0) {
            this.str = String.valueOf(this.str) + " E";
        }
        else {
            this.str = String.valueOf(this.str) + " W";
        }
        System.out.println(" Longitude: " + this.str);
        this.str = this.dat.toString();
        System.out.println(" Date/Time: " + this.str);
        System.out.println(" GMT:       " + this.gmtStr);
        System.out.println(" " + this.declinStr);
        System.out.println(" " + this.ghaStr);
        System.out.println(" Equ. of Time = " + this.eqtStr);
        System.out.println(" " + this.heightStr);
        System.out.println(" " + this.azStr);
        System.out.println(" " + this.riseStr);
        System.out.println(" " + this.transStr);
        System.out.println(" " + this.setStr);
        System.out.println(" Local Time          Elev.       Azimuth");
    }
}
