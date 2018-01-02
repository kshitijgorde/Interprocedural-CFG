import java.awt.Font;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Component;
import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class sundata148 extends Applet implements Runnable
{
    String versStr;
    Date dat;
    compute comp;
    Thread myThread;
    TextField latField;
    TextField longField;
    double latitude;
    double longitude;
    String latStr;
    String longStr;
    Double latDouble;
    Double longDouble;
    Label latLabel;
    Label longLabel;
    String bgCol;
    boolean demo;
    boolean online;
    public String myStr;
    public String email;
    public String param;
    public String wwwStr;
    public String usrStr;
    public String userString;
    char deg;
    char c;
    int fontGray;
    
    public void init() {
        final URL url = this.getDocumentBase();
        this.myStr = url.toString();
        this.myStr = String.valueOf(this.myStr) + "1234567890123456789012345";
        this.wwwStr = this.myStr.substring(0, 27);
        this.dat = new Date();
        this.latStr = this.getParameter("latitude");
        this.longStr = this.getParameter("longitude");
        this.latDouble = Double.valueOf(this.latStr);
        this.latitude = this.latDouble;
        this.longDouble = Double.valueOf(this.longStr);
        this.longitude = this.longDouble;
        final String bgColRed = this.getParameter("bgRed");
        final String bgColGreen = this.getParameter("bgGreen");
        final String bgColBlue = this.getParameter("bgBlue");
        final String fontCol = this.getParameter("fontGray");
        final int bgRed = Integer.parseInt(bgColRed);
        final int bgGreen = Integer.parseInt(bgColGreen);
        final int bgBlue = Integer.parseInt(bgColBlue);
        this.fontGray = Integer.parseInt(fontCol);
        final Color myBG = new Color(bgRed, bgGreen, bgBlue);
        this.setBackground(myBG);
        this.comp = new compute(this.dat, this.latitude, this.longitude, this.demo, this.fontGray);
        this.add(this.latLabel = new Label("Lat. ="));
        this.add(this.latField = new TextField(this.latStr, 6));
        this.add(this.longLabel = new Label(" Long.="));
        this.add(this.longField = new TextField(this.longStr, 6));
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        this.userString = String.valueOf(this.email) + "  " + this.dat.toString();
        if (this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 21) == this.formula("http://www.j-giesen.de", 21) || this.formula(this.wwwStr, 23) == this.formula("http://www.astropolaris", 23)) {
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
            this.versStr = String.valueOf(this.versStr) + "   D E M O";
        }
        this.repaint();
    }
    
    public double getLatitude(String str) {
        if (!str.equals("-")) {
            for (int i = 0; i < str.length(); ++i) {
                this.c = str.charAt(i);
                if (this.c == ',') {
                    str = String.valueOf(str.substring(0, i)) + '.' + str.substring(i + 1, str.length());
                }
            }
            for (int j = 0; j < str.length(); ++j) {
                this.c = str.charAt(j);
                if (this.c != '0' && this.c != '1' && this.c != '2' && this.c != '3' && this.c != '4' && this.c != '5' && this.c != '6' && this.c != '7' && this.c != '8' && this.c != '9' && this.c != '.') {
                    if (this.c != '-') {
                        continue;
                    }
                }
                try {
                    this.latDouble = Double.valueOf(str);
                    this.latitude = this.latDouble;
                    if (this.latitude > 90.0) {
                        this.latitude = 90.0;
                    }
                    if (this.latitude < -90.0) {
                        this.latitude = -90.0;
                    }
                }
                catch (NumberFormatException ex) {}
            }
        }
        return this.latitude;
    }
    
    public double getLongitude(String str) {
        if (!str.equals("-")) {
            for (int i = 0; i < str.length(); ++i) {
                this.c = str.charAt(i);
                if (this.c == ',') {
                    str = String.valueOf(str.substring(0, i)) + '.' + str.substring(i + 1, str.length());
                }
            }
            for (int j = 0; j < str.length(); ++j) {
                this.c = str.charAt(j);
                if (this.c != '0' && this.c != '1' && this.c != '2' && this.c != '3' && this.c != '4' && this.c != '5' && this.c != '6' && this.c != '7' && this.c != '8' && this.c != '9' && this.c != '.') {
                    if (this.c != '-') {
                        continue;
                    }
                }
                try {
                    this.longDouble = Double.valueOf(str);
                    this.longitude = this.longDouble;
                    if (this.longitude > 180.0) {
                        this.longitude = 180.0;
                    }
                    if (this.longitude < -180.0) {
                        this.longitude = -180.0;
                    }
                }
                catch (NumberFormatException ex) {}
            }
        }
        return this.longitude;
    }
    
    public void start() {
        (this.myThread = new Thread(this)).start();
    }
    
    public void stop() {
        this.myThread.stop();
    }
    
    public void run() {
        while (true) {
            this.dat = new Date();
            this.latStr = this.latField.getText();
            this.latitude = this.getLatitude(this.latStr);
            this.longStr = this.longField.getText();
            this.longitude = this.getLongitude(this.longStr);
            this.comp = new compute(this.dat, this.latitude, this.longitude, this.demo, this.fontGray);
            this.repaint();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return 801 + (int)num;
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("Courier", 0, 12));
        g.setColor(Color.red);
        g.drawString(this.versStr, 30, 50);
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.setColor(Color.black);
        this.comp.update(g);
    }
    
    public sundata148() {
        this.versStr = "Sun Data 1.48";
        this.demo = true;
        this.online = false;
        this.deg = '°';
    }
}
