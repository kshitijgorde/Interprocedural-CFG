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

public class sunrise116 extends Applet implements Runnable
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
    public String email;
    public String param;
    public String wwwStr;
    public String myStr;
    public String userString;
    public String usrStr;
    boolean demo;
    boolean online;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 1012;
    }
    
    public void init() {
        this.dat = new Date();
        this.latStr = this.getParameter("latitude");
        this.longStr = this.getParameter("longitude");
        final Double latDouble = Double.valueOf(this.latStr);
        this.latitude = latDouble;
        final Double longDouble = Double.valueOf(this.longStr);
        this.longitude = longDouble;
        this.setBackground(Color.white);
        this.comp = new compute(this.dat, this.latitude, this.longitude, this.demo);
        this.add(this.latLabel = new Label("Lat. ="));
        this.add(this.latField = new TextField(this.latStr, 6));
        this.add(this.longLabel = new Label(" Long.="));
        this.add(this.longField = new TextField(this.longStr, 6));
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
        this.repaint();
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
            try {
                this.latStr = this.latField.getText();
                this.latDouble = Double.valueOf(this.latStr);
                this.latitude = this.latDouble;
                if (Math.abs(this.latitude) > 90.0) {
                    this.latField.setText("Error");
                }
                this.comp = new compute(this.dat, this.latitude, this.longitude, this.demo);
                this.repaint();
            }
            catch (NumberFormatException ex) {
                this.latField.setText("Error");
            }
            try {
                this.longStr = this.longField.getText();
                this.longDouble = Double.valueOf(this.longStr);
                this.longitude = this.longDouble;
                if (Math.abs(this.longitude) > 180.0) {
                    this.longField.setText("Error");
                }
                this.comp = new compute(this.dat, this.latitude, this.longitude, this.demo);
                this.repaint();
            }
            catch (NumberFormatException ex2) {
                this.longField.setText("Error");
            }
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex3) {}
        }
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("Courier", 0, 12));
        g.setColor(Color.red);
        g.drawString(this.versStr, 30, 50);
        g.setColor(Color.black);
        this.comp.update(g);
    }
    
    public sunrise116() {
        this.versStr = "Sunrise & Sunset 1.16";
        this.demo = true;
        this.online = false;
    }
}
