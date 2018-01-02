import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Component;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.TextField;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RiseSet21 extends Applet
{
    Date dat;
    Thread myThread;
    TextField latField;
    TextField longField;
    double latitude;
    double longitude;
    double maximum;
    String latStr;
    String longStr;
    Double latDouble;
    Double longDouble;
    int date;
    int month;
    int year;
    int number;
    Checkbox box;
    BorderLayout bl;
    String homeString;
    String homeLatStr;
    String homeLongStr;
    String numberStr;
    String versStr;
    boolean console;
    boolean demo;
    boolean online;
    public String str;
    public String email;
    public String param;
    public String wwwStr;
    public String usrStr;
    public String userString;
    Rise_Set rs;
    boolean linesOK;
    
    public void init() {
        this.versStr = " 2.1";
        this.setBackground(Color.white);
        this.homeString = this.getParameter("location");
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
        this.numberStr = this.getParameter("numberOfDays");
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        this.latDouble = Double.valueOf(this.homeLatStr);
        this.latitude = this.latDouble;
        this.longDouble = Double.valueOf(this.homeLongStr);
        this.longitude = this.longDouble;
        this.number = Integer.parseInt(this.numberStr);
        this.add(this.box = new Checkbox("Day and Night"));
        this.box.setState(true);
        this.dat = new Date();
        this.rs = new Rise_Set(this.dat, this.latitude, -this.longitude);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        this.userString = String.valueOf(this.email) + "  " + this.dat.toString();
        if (this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22)) {
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
        }
        if (this.demo) {
            this.versStr = String.valueOf(this.versStr) + " DEMO";
        }
        this.repaint();
    }
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 111;
    }
    
    public int daysInMonth(final int m, int y) {
        int n = 31;
        y += 1900;
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
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Checkbox && event.target == this.box) {
            if (this.box.getState()) {
                this.console = true;
            }
            else {
                this.console = false;
            }
            if (this.console) {
                this.linesOK ^= true;
            }
            this.repaint();
        }
        return true;
    }
    
    public void paint(final Graphics g) {
        final int F = 9;
        final int xL = 16;
        int h = 12;
        g.setFont(new Font("Helvetica", 0, 14));
        g.setColor(Color.red);
        g.drawString(this.homeString, 20, 35);
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.setFont(new Font("Helvetica", 0, 12));
        g.setColor(Color.blue);
        g.drawString("Rise           Set", 135, 35);
        this.dat = new Date();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        final int dim = this.daysInMonth(this.month, this.year);
        g.setFont(new Font("Courier", 0, 12));
        for (int i = 0; i < this.number; ++i) {
            final int n = this.date + i;
            this.dat.setDate(n);
            if (n > dim) {
                this.dat.setDate(n - dim);
                this.dat.setMonth(this.month + 1);
                if (this.month + 1 == 12) {
                    this.dat.setMonth(0);
                    this.dat.setYear(this.year + 1);
                }
            }
            this.rs = new Rise_Set(this.dat, this.latitude, -this.longitude);
            if (this.console) {
                final int left = xL + (int)Math.round(F * this.rs.h_rise());
                final int right = xL + (int)Math.round(F * this.rs.h_set());
                final int right2 = xL + F * 24;
                final int y1 = 45 + i * 15;
                final int y2 = 42 + (i + 1) * 15;
                h = y2 - y1;
                g.setColor(Color.yellow);
                g.fillRect(left, y1, xL + right - left, h);
                final Color c = new Color(50, 225, 255);
                g.setColor(c);
                g.fillRect(xL, y1, left - xL, h);
                g.fillRect(right, y1, right2 - right, h);
            }
            g.setColor(Color.black);
            if (this.demo) {
                g.drawString(String.valueOf(this.dat.toString().substring(0, 10)) + "  " + this.rs.rise_String() + "   DEMO", 30, 55 + i * 15);
            }
            else {
                g.drawString(String.valueOf(this.dat.toString().substring(0, 10)) + "  " + this.rs.rise_String() + " " + this.rs.set_String(), 30, 55 + i * 15);
            }
        }
        final int top = 43 + this.number * 15;
        g.setFont(new Font("Helvetica", 0, 9));
        if (this.console) {
            if (this.linesOK) {
                g.setColor(Color.gray);
                g.drawLine(xL + 3 * F, top, xL + 3 * F, top - this.number * 15);
                g.drawLine(xL + 6 * F, top, xL + 6 * F, top - this.number * 15);
                g.drawLine(xL + 9 * F, top, xL + 9 * F, top - this.number * 15);
                g.drawLine(xL + 12 * F, top, xL + 12 * F, top - this.number * 15);
                g.drawLine(xL + 15 * F, top, xL + 15 * F, top - this.number * 15);
                g.drawLine(xL + 18 * F, top, xL + 18 * F, top - this.number * 15);
                g.drawLine(xL + 21 * F, top, xL + 21 * F, top - this.number * 15);
            }
            g.setColor(Color.black);
            g.drawString("6", xL - 2 + 6 * F, 58 + this.number * 15);
            g.drawString("12", xL - 4 + 12 * F, 58 + this.number * 15);
            g.drawString("18", xL - 5 + 18 * F, 58 + this.number * 15);
            g.drawLine(xL + 3 * F, top, xL + 3 * F, top + 5);
            g.drawLine(xL + 6 * F, top, xL + 6 * F, top + 5);
            g.drawLine(xL + 9 * F, top, xL + 9 * F, top + 5);
            g.drawLine(xL + 12 * F, top, xL + 12 * F, top + 5);
            g.drawLine(xL + 15 * F, top, xL + 15 * F, top + 5);
            g.drawLine(xL + 18 * F, top, xL + 18 * F, top + 5);
            g.drawLine(xL + 21 * F, top, xL + 21 * F, top + 5);
            g.drawLine(xL, top, xL, top + 5);
            g.drawLine(xL + 24 * F, top, xL + 24 * F, top + 5);
            final int time = (int)Math.round(F * this.rs.current_Time());
            g.setColor(Color.red);
            g.drawLine(xL + time, 45, xL + time, 45 + h);
            g.drawLine(xL + time - 1, 45, xL + time - 1, 45 + h);
            g.drawLine(xL + time + 1, 45, xL + time + 1, 45 + h);
        }
        g.drawString("RiseSet " + this.versStr + " --  © 1999-2008 Juergen Giesen", 20, 70 + this.number * 15);
        g.drawString("www.GeoAstro.de", 20, 80 + this.number * 15);
    }
    
    public RiseSet21() {
        this.console = true;
        this.demo = true;
        this.online = false;
        this.linesOK = true;
    }
}
