import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class compute extends Canvas
{
    int offset;
    String[] str;
    double riseTime;
    double setTime;
    double time;
    String riseStr;
    String setStr;
    
    compute(final Date dat, final double LAT, final double LONG) {
        this.str = new String[11];
        final int hours = dat.getHours();
        final int minutes = dat.getMinutes();
        final int seconds = dat.getSeconds();
        final int date = dat.getDate();
        int month = dat.getMonth();
        ++month;
        int year = dat.getYear();
        year += 1900;
        this.offset = -dat.getTimezoneOffset() / 60;
        final double UT = hours - this.offset + minutes / 60.0 + seconds / 3600.0;
        this.time = UT + this.offset;
        final Rise_Set rs = new Rise_Set(dat, LAT, -LONG, this.offset, -0.8333);
        this.riseTime = rs.h_rise();
        this.setTime = rs.h_set();
        this.str[4] = "Latitude   " + Math.abs(LAT) + '°';
        if (LAT >= 0.0) {
            this.str[4] = String.valueOf(this.str[4]) + " N";
        }
        else {
            this.str[4] = String.valueOf(this.str[4]) + " S";
        }
        this.str[5] = "Longitude  " + Math.abs(LONG) + '°';
        if (LONG <= 0.0) {
            this.str[5] = String.valueOf(this.str[5]) + " E";
        }
        else {
            this.str[5] = String.valueOf(this.str[5]) + " W";
        }
        this.str[6] = dat.toString();
        if (dat.getTimezoneOffset() <= 0) {
            this.str[8] = "Time Zone  UT + " + -dat.getTimezoneOffset() / 60 + " h";
        }
        else {
            this.str[8] = "Time Zone  UT  - " + Math.abs(dat.getTimezoneOffset() / 60) + " h";
        }
        String s;
        if (UT < 0.0) {
            if (Math.abs((int)(UT + 24.0)) > 9) {
                s = "";
            }
            else {
                s = "0";
            }
        }
        else if ((int)UT > 9) {
            s = "";
        }
        else {
            s = "0";
        }
        if (UT < 0.0) {
            this.str[7] = "UT         " + s + (int)(UT + 24.0);
        }
        else {
            this.str[7] = "UT         " + s + (int)UT;
        }
        if (minutes > 9) {
            s = ":";
        }
        else {
            s = ":0";
        }
        this.str[7] = String.valueOf(this.str[7]) + s + minutes;
        if (seconds > 9) {
            s = ":";
        }
        else {
            s = ":0";
        }
        this.str[7] = String.valueOf(this.str[7]) + s + seconds;
        this.riseStr = rs.rise_String();
        this.setStr = rs.rise_String();
        if (this.riseStr.equals("Sun visible all day") || this.riseStr.equals("Sun invisible all day")) {
            this.str[9] = rs.rise_String();
        }
        else {
            this.str[9] = "Sunrise  " + rs.rise_String();
        }
        if (this.setStr.equals("Sun visible all day") || this.setStr.equals("Sun invisible all day")) {
            this.str[10] = rs.set_String();
        }
        else {
            this.str[10] = "Sunset   " + rs.set_String();
        }
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        for (int i = 4; i < 9; ++i) {
            g.drawString(this.str[i], 30, i * 18);
        }
        g.setColor(Color.red);
        for (int j = 9; j < 11; ++j) {
            g.drawString(this.str[j], 30, j * 18);
        }
        g.setColor(Color.blue);
        g.setFont(new Font("Courier", 0, 10));
        g.drawString("© 1999-2008 J. Giesen", 10, 212);
        g.drawString("www.GeoAstro.de", 10, 230);
        g.setColor(Color.black);
        final int xM = 240;
        final int yM = 200;
        final int r = 50;
        final int xRise = (int)(xM - Math.round(r * Math.sin(3.141592653589793 * this.riseTime / 12.0)));
        final int yRise = (int)(yM + Math.round(r * Math.cos(3.141592653589793 * this.riseTime / 12.0)));
        final int xSet = (int)(xM - Math.round(r * Math.sin(3.141592653589793 * this.setTime / 12.0)));
        final int ySet = (int)(yM + Math.round(r * Math.cos(3.141592653589793 * this.setTime / 12.0)));
        g.setColor(Color.blue);
        g.drawString("6", xM - r - 10, yM + 5);
        g.drawString("12", xM - 5, yM - r - 5);
        g.drawString("18", xM + r + 5, yM + 5);
        final int x9 = (int)Math.round(r * Math.sin(0.7853981633974483));
        g.drawString("9", xM - x9 - 10, yM - x9 - 5);
        g.drawString("15", xM + x9 + 5, yM - x9 - 5);
        g.drawString("3", xM - x9 - 10, yM + x9 + 10);
        g.drawString("21", xM + x9 + 5, yM + x9 + 10);
        if (this.riseStr.equals("Sun visible all day")) {
            g.setColor(Color.yellow);
            g.fillOval(xM - r, yM - r, 2 * r, 2 * r);
        }
        else {
            g.fillArc(xM - r, yM - r, 2 * r, 2 * r, 270 - (int)(this.riseTime * 15.0), (int)(360.0 - 15.0 * (this.setTime - this.riseTime)));
            g.setColor(Color.yellow);
            g.fillArc(xM - r, yM - r, 2 * r, 2 * r, 270 - (int)(this.riseTime * 15.0), (int)(-15.0 * (this.setTime - this.riseTime)));
        }
        g.setColor(Color.black);
        g.drawOval(xM - r, yM - r, 2 * r, 2 * r);
        g.drawLine(xM, yM, xRise, yRise);
        g.drawLine(xM, yM, xSet, ySet);
        final int xUT = (int)(xM - Math.round(r * Math.sin(3.141592653589793 * this.time / 12.0)));
        final int yUT = (int)(yM + Math.round(r * Math.cos(3.141592653589793 * this.time / 12.0)));
        g.setColor(Color.red);
        g.drawLine(xM, yM, xUT, yUT);
        g.setColor(Color.lightGray);
        g.drawLine(xM - r, yM, xM + r, yM);
        g.drawLine(xM, yM - r, xM, yM + r);
        g.drawLine(xM, yM, xM - x9, yM - x9);
        g.drawLine(xM, yM, xM + x9, yM + x9);
        g.drawLine(xM, yM, xM + x9, yM - x9);
        g.drawLine(xM, yM, xM - x9, yM + x9);
        g.clipRect(0, 0, this.size().width, this.size().height);
    }
}
