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
    double DEC;
    double RA;
    int offset;
    String[] str;
    int utHours;
    Rise_Set rs;
    
    compute(final Date dat, final double LAT, final double LONG, final boolean demo) {
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
        this.utHours = hours - this.offset;
        if (this.utHours < 0) {
            this.utHours += 24;
        }
        if (this.utHours > 24) {
            this.utHours -= 24;
        }
        this.rs = new Rise_Set(dat, LAT, -LONG, 0);
        this.str[4] = "Latitude   " + Math.abs(LAT);
        if (LAT > 0.0) {
            this.str[4] = String.valueOf(this.str[4]) + " N";
        }
        else {
            this.str[4] = String.valueOf(this.str[4]) + " S";
        }
        this.str[5] = "Longitude  " + Math.abs(LONG);
        if (LONG > 0.0) {
            this.str[5] = String.valueOf(this.str[5]) + " W";
        }
        else {
            this.str[5] = String.valueOf(this.str[5]) + " E";
        }
        this.str[7] = dat.toString();
        if (dat.getTimezoneOffset() <= 0) {
            this.str[6] = "Time Zone  UT + " + -dat.getTimezoneOffset() / 60 + " h";
        }
        else {
            this.str[6] = "Time Zone   UT " + dat.getTimezoneOffset() / 60 + " h";
        }
        String s;
        if (this.utHours > 9) {
            s = "";
        }
        else {
            s = "0";
        }
        this.str[8] = "UT         " + s + this.utHours;
        if (minutes > 9) {
            s = ":";
        }
        else {
            s = ":0";
        }
        this.str[8] = String.valueOf(this.str[8]) + s + minutes;
        if (seconds > 9) {
            s = ":";
        }
        else {
            s = ":0";
        }
        this.str[8] = String.valueOf(this.str[8]) + s + seconds;
        this.str[9] = this.rs.rise_String();
        this.str[10] = this.rs.set_String();
        this.rs = new Rise_Set(dat, LAT, -LONG, 1);
        this.str[9] = String.valueOf(this.str[9]) + "  " + this.rs.civilRise();
        this.str[10] = String.valueOf(this.str[10]) + "  " + this.rs.civilSet();
        if (demo) {
            this.str[10] = "Set    DEMO";
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
        g.drawString("1.16 - © 1998-2007 J. Giesen - www.GeoAstro.de", 30, 205);
        g.setColor(Color.red);
        g.drawRect(1, 1, 317, 222);
    }
}
