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

public class sunclock110 extends Applet implements Runnable
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
    char c;
    public String email;
    public String param;
    public String wwwStr;
    boolean ok;
    boolean demo;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 3010;
    }
    
    public void init() {
        final URL url = this.getDocumentBase();
        String str = url.toString();
        str = String.valueOf(str) + "1234567890123456789012345";
        this.wwwStr = str.substring(0, 27);
        this.dat = new Date();
        this.latStr = this.getParameter("latitude");
        this.longStr = this.getParameter("longitude");
        final Double latDouble = Double.valueOf(this.latStr);
        this.latitude = latDouble;
        final Double longDouble = Double.valueOf(this.longStr);
        this.longitude = longDouble;
        this.setBackground(Color.white);
        this.comp = new compute(this.dat, this.latitude, this.longitude);
        this.add(this.latLabel = new Label("Lat. ="));
        this.add(this.latField = new TextField(this.latStr, 6));
        this.add(this.longLabel = new Label(" Long.="));
        this.add(this.longField = new TextField(this.longStr, 6));
        boolean online = false;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        if (this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22)) {
            this.ok = true;
            online = true;
            this.demo = false;
        }
        else {
            this.ok = false;
        }
        if (!this.ok) {
            this.ok = true;
            if (this.email.length() == 0 || Integer.parseInt(this.param) != this.formula(this.email, this.email.length())) {
                this.ok = false;
            }
            else {
                this.ok = true;
                this.demo = false;
            }
            if (this.wwwStr.substring(0, 7).equals("http://")) {
                this.ok = false;
                this.demo = true;
            }
        }
        if (this.demo) {
            this.versStr = String.valueOf(this.versStr) + " DEMO";
        }
        this.repaint();
    }
    
    public void start() {
        (this.myThread = new Thread(this)).start();
    }
    
    public void stop() {
        this.myThread.stop();
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
    
    public void run() {
        while (true) {
            this.dat = new Date();
            this.latStr = this.latField.getText();
            this.latitude = this.getLatitude(this.latStr);
            this.longStr = this.longField.getText();
            this.longitude = this.getLongitude(this.longStr);
            this.comp = new compute(this.dat, this.latitude, this.longitude);
            this.repaint();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void paint(final Graphics g) {
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 64));
            g.setColor(Color.red);
            g.drawString("D E M O", 40, 150);
        }
        g.setFont(new Font("Courier", 0, 12));
        g.setColor(Color.red);
        g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        g.drawString(this.versStr, 30, 50);
        g.setColor(Color.black);
        this.comp.update(g);
    }
    
    public sunclock110() {
        this.versStr = "SunClock Applet 1.10";
        this.ok = true;
        this.demo = true;
    }
}
