import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class suncanvas extends Canvas
{
    String str;
    final int y90 = 124;
    final int y0 = 336;
    final int xS = 300;
    final int xL = 30;
    final int xOben = 60;
    final int xLL = 30;
    final int unten = 390;
    final int Radius = 10;
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
    public compute comp;
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
    String versStr;
    String gmtStr;
    String declinStr;
    boolean myConsole;
    String[] hoeheStrArray;
    String[] azStrArray;
    String[] dirStrArr;
    String dirStr;
    String[] dirStrArray;
    String riseStr;
    String setStr;
    
    public suncanvas(final Image myImage, final Date dat, final double latitude, final double longitude, final String locStr, final int locOffset, final String versStr, final boolean myConsole) {
        this.s = "";
        this.hArray = new int[25];
        this.xArray = new int[25];
        this.hoeheStrArray = new String[25];
        this.azStrArray = new String[25];
        this.dirStrArr = new String[25];
        this.dirStrArray = new String[16];
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
        this.year = this.dat.getYear();
        this.browserOffset = this.dat.getTimezoneOffset();
        this.browserOffset = -this.browserOffset / 60;
        this.locOffset = locOffset;
        this.timeString = String.valueOf(this.browserOffset) + " h";
        if (this.browserOffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.comp = new compute();
        this.dec = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, this.STD);
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.STD);
        this.str = String.valueOf(Math.round(10.0 * this.GHA) / 10.0);
        this.s = this.str.substring(0, this.str.indexOf(".") + 2);
        this.ghaStr = "GHA      = " + this.s + " deg.";
        this.GHA12 = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, 12.0);
        if (this.GHA12 > 5.0) {
            this.GHA12 -= 360.0;
        }
        this.equation = this.GHA12 * 4.0;
        this.diff = Math.abs(this.equation - (int)this.equation);
        this.min = (int)Math.round(this.diff * 60.0);
        if (this.min > 9) {
            this.str = ":";
        }
        else {
            this.str = ":0";
        }
        this.eqtStr = "Equation of Time = " + (int)this.equation + this.str + this.min + " min";
        if (this.equation < 0.0 && (int)this.equation == 0) {
            this.eqtStr = "Equation of Time = -" + (int)this.equation + this.str + this.min + " min";
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
        this.transStr = "Culmin.    " + this.transInt + this.str + this.min + "  Hrs";
        this.hoehe = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
        this.str = String.valueOf(Math.round(10.0 * this.hoehe) / 10.0);
        this.s = this.str.substring(0, this.str.indexOf(".") + 2);
        this.heightStr = "Altitude = " + this.s + " deg.";
        this.azimut = this.comp.computeAzimut(this.dec, this.latitude, this.longitude, this.GHA, this.hoehe);
        this.str = String.valueOf(Math.round(10.0 * this.azimut) / 10.0);
        this.s = this.str.substring(0, this.str.indexOf(".") + 2);
        this.azStr = "Azimuth  = " + this.s + " deg.";
        this.dirStr = this.comp.azDirection(this.azimut);
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
        if (this.latitude >= this.dec) {
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
        for (int i = 0; i < 25; ++i) {
            if (i == 0) {
                this.GHA = this.comp.computeGHA(this.date - 1, this.month + 1, this.year + 1900, 23.0);
            }
            else {
                this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, i - 1);
            }
            this.hoehe1 = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
            this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, i);
            this.hoehe = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
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
            this.azimut = this.comp.computeAzimut(this.dec, this.latitude, this.longitude, this.GHA, this.hoehe);
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
            this.hArray[i] = this.hBildInt;
            this.xArray[i] = this.xInt;
            this.hoeheStrArray[i] = "";
            this.str = String.valueOf(this.hoehe);
            this.hoeheStrArray[i] = this.str.substring(0, this.str.indexOf(".") + 3);
            this.azStrArray[i] = "";
            this.str = String.valueOf(this.azimut);
            this.azStrArray[i] = this.str.substring(0, this.str.indexOf(".") + 2);
            this.dirStrArr[i] = "";
            this.dirStrArr[i] = this.comp.azDirection(this.azimut);
            if (this.azimut < 10.0) {
                this.dirStrArr[i] = " " + this.dirStrArr[i];
            }
            if (this.azimut < 100.0) {
                this.dirStrArr[i] = " " + this.dirStrArr[i];
            }
        }
        final Rise_Set set = new Rise_Set(this.dat, this.latitude, this.longitude, this.locOffset, -0.8333);
        this.riseStr = set.rise_String();
        this.setStr = set.set_String();
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final Font font = graphics.getFont();
        graphics.setFont(new Font("Helvetica", 0, 9));
        graphics.setColor(Color.red);
        graphics.drawString("© 1998-2003  J. Giesen" + this.versStr, 450, 90);
        graphics.setFont(font);
        graphics.drawImage(this.myImage, 30, 100, this);
        graphics.setFont(new Font("Chicago", 0, 12));
        if (this.latitude >= this.dec) {
            graphics.drawString("S", 297, 118);
            this.n = (int)Math.round(135.0);
            graphics.drawString("E", 300 - this.n - 3, 118);
            graphics.drawString("W", 300 + this.n, 118);
        }
        else {
            graphics.drawString("N", 297, 118);
            this.n = (int)Math.round(135.0);
            graphics.drawString("W", 300 - this.n, 118);
            graphics.drawString("E", 300 + this.n - 3, 118);
        }
        graphics.setFont(font);
        if (this.browserOffset > 0) {
            graphics.drawString("Browser's Time Zone Offset is +" + this.browserOffset + " h", 15, 90);
        }
        else {
            graphics.drawString("Browser's Time Zone Offset is " + this.browserOffset + " h", 15, 90);
        }
        graphics.setColor(Color.green);
        graphics.drawLine(297, 336, 303, 336);
        graphics.drawLine(300, 333, 300, 339);
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
        graphics.drawString(String.valueOf(this.str) + " GMT", 205, 90);
        this.str = String.valueOf(Math.abs(this.latitude));
        if (this.latitude > 0.0) {
            this.str = String.valueOf(this.str) + " N";
        }
        else {
            this.str = String.valueOf(this.str) + " S";
        }
        graphics.drawString(this.str, 300, 90);
        this.str = String.valueOf(Math.abs(this.longitude));
        if (this.longitude > 0.0) {
            this.str = String.valueOf(this.str) + " E";
        }
        else {
            this.str = String.valueOf(this.str) + " W";
        }
        graphics.drawString(this.str, 370, 90);
        graphics.setColor(Color.black);
        graphics.drawString(this.dat.toString(), 30, 430);
        final String value = String.valueOf(this.dec);
        this.s = value.substring(0, value.indexOf(".") + 2);
        this.declinStr = "Declin.  = " + this.s + " deg.";
        graphics.drawString("Declin. = " + this.s + " deg.", 30, 445);
        graphics.drawString(this.ghaStr, 30, 460);
        graphics.drawString(this.eqtStr, 30, 475);
        graphics.setColor(Color.red);
        graphics.drawString(this.locStr, 270, 430);
        graphics.setColor(Color.black);
        graphics.drawString("Sunrise  " + this.riseStr, 270, 445);
        graphics.drawString("Sunset   " + this.setStr, 270, 475);
        graphics.drawString(this.transStr, 270, 460);
        graphics.setColor(Color.black);
        graphics.drawString(this.heightStr, 400, 430);
        graphics.drawString(String.valueOf(this.azStr) + " (" + this.dirStr + ")", 400, 445);
        if (336 - this.hSun < 410) {
            graphics.setColor(Color.yellow);
            graphics.fillOval(this.xSun - 10, 336 - this.hSun - 10, 20, 20);
            graphics.setColor(Color.red);
            graphics.drawOval(this.xSun - 10 - 1, 336 - this.hSun - 10 - 1, 22, 22);
        }
        else {
            graphics.setColor(Color.white);
            graphics.drawString("SUN", this.xSun - 10, 400);
            graphics.drawString("V", this.xSun - 4, 410);
        }
        final Font font2 = graphics.getFont();
        graphics.setFont(new Font("Helvetica", 0, 9));
        graphics.setColor(Color.black);
        if (this.myConsole) {
            this.writeConsole();
        }
        for (int i = 0; i < 25; ++i) {
            this.n = i + this.locOffset;
            this.n %= 24;
            if (this.n < 0) {
                this.n += 24;
            }
            String s;
            if (this.n < 10) {
                s = " ";
            }
            else {
                s = "";
            }
            if (this.hoeheStrArray[i].length() < 5) {
                this.hoeheStrArray[i] = " " + this.hoeheStrArray[i];
            }
            if (this.hoeheStrArray[i].length() < 6) {
                this.hoeheStrArray[i] = " " + this.hoeheStrArray[i];
            }
            if (this.myConsole) {
                System.out.println(String.valueOf(s) + "    " + this.n + ":00" + "       " + this.hoeheStrArray[i] + "       " + this.azStrArray[i] + "    " + this.dirStrArr[i]);
            }
            if (336 - this.hArray[i] < 390) {
                graphics.setColor(Color.red);
                graphics.drawOval(this.xArray[i] - 3, 336 - this.hArray[i] - 3, 6, 6);
                graphics.setColor(Color.black);
                if (i % 2 == 0) {
                    graphics.drawString(String.valueOf(this.n), this.xArray[i] + 7, 336 - this.hArray[i] + 3);
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
        if (this.myConsole) {
            System.out.println(" Sun View Applet" + this.versStr + " --  © 1998-2003 Juergen Giesen");
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
            System.out.println(" ");
            System.out.println(" Local Time    Altitude    Azimuth   Direction");
        }
    }
}
