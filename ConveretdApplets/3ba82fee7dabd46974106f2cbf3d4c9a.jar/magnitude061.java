import java.awt.Font;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Dialog;
import java.net.URL;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Scrollbar;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class magnitude061 extends Applet
{
    Scrollbar scrollSunR;
    Scrollbar scrollMoonR;
    Scrollbar scrollMoonY;
    Scrollbar scrollRatio;
    Label sunLabel;
    Label moonLabel;
    Label moonYLabel;
    Label ratioLabel;
    Label magLabel;
    Label areaLabel;
    Label v1Label;
    Label iterLabel;
    Choice detailsChoice;
    int valueMoonR;
    int valueSunR;
    int valueMoonY;
    int valueRatio;
    double ratio;
    String[] data;
    int nData;
    String titleStr;
    String versStr;
    String str;
    public String email;
    public String param;
    public String wwwStr;
    boolean online;
    boolean demo;
    
    public void init() {
        this.versStr = "Eclipse Magnitude 0.61";
        this.setBackground(new Color(255, 255, 255));
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        this.setLayout(new BorderLayout());
        final Panel p = new Panel();
        p.setLayout(new GridLayout(0, 2));
        p.add(this.sunLabel = new Label(" Sun Radius = " + this.valueSunR));
        (this.scrollSunR = new Scrollbar(0)).setMaximum(210);
        this.scrollSunR.setValue(this.valueSunR);
        p.add(this.scrollSunR);
        p.add(this.moonLabel = new Label(" Moon Radius = " + this.valueMoonR));
        (this.scrollMoonR = new Scrollbar(0)).setMaximum(210);
        this.scrollMoonR.setValue(this.valueMoonR);
        p.add(this.scrollMoonR);
        p.add(this.moonYLabel = new Label(" Moon Center Y = " + this.valueMoonY));
        (this.scrollMoonY = new Scrollbar(0)).setBackground(Color.blue);
        this.scrollMoonY.setMaximum(210);
        this.scrollMoonY.setValue(this.valueMoonY);
        p.add(this.scrollMoonY);
        p.add(this.ratioLabel = new Label(" Ratio rMoon/RSun=" + Math.round(100 * this.valueMoonR / this.valueSunR) / 100.0));
        (this.scrollRatio = new Scrollbar(0)).setMaximum(210);
        this.scrollRatio.setValue(100);
        this.scrollRatio.setBackground(Color.red);
        this.scrollRatio.setForeground(Color.red);
        p.add(this.scrollRatio);
        p.add(this.magLabel = new Label("Magnitude"));
        (this.detailsChoice = new Choice()).addItem("Reset");
        this.detailsChoice.addItem("Data");
        this.detailsChoice.addItem("About");
        p.add(this.detailsChoice);
        p.add(this.areaLabel = new Label(" Obscuration"));
        this.add("North", p);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        if (this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 20) == this.formula("http://www.SciAm.com", 20)) {
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
            if (!ok) {
                boolean dem = false;
                if (this.online || this.demo) {
                    dem = true;
                }
                if (dem) {
                    this.versStr = String.valueOf(this.versStr) + " DEMO";
                }
                final Dialog AboutDialog = new Aboutdialog(this, this.versStr, dem);
                AboutDialog.resize(350, 230);
                AboutDialog.show();
            }
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
        return (int)num + 4101;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Choice && event.target == this.detailsChoice) {
            if (this.detailsChoice.getSelectedItem().equals("Reset")) {
                this.valueMoonR = 100;
                this.valueSunR = 100;
                this.valueMoonY = 50;
                this.valueRatio = 100;
                this.scrollSunR.setValue(this.valueSunR);
                this.sunLabel.setText(" Sun Radius = " + this.valueSunR);
                this.scrollMoonR.setValue(this.valueMoonR);
                this.moonLabel.setText(" Moon Radius = " + this.valueMoonR);
                this.scrollMoonY.setValue(this.valueMoonY);
                this.moonYLabel.setText(" Moon Center Y = " + this.valueMoonY);
                this.scrollRatio.setValue(this.valueRatio);
                this.scrollRatio.setValue((int)Math.round(100.0 * this.valueMoonR / this.valueSunR));
                this.ratioLabel.setText(" Ratio rMoon/RSun=" + Math.round(100 * this.valueMoonR / this.valueSunR) / 100.0);
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Data")) {
                this.doData();
                boolean dem = false;
                if (this.online || this.demo) {
                    dem = true;
                }
                final Frame df = new dataFrame(this.titleStr, this.versStr, "rMoon/rsun = " + Math.round(100.0 * this.ratio) / 100.0, this.data, this.nData, dem);
                df.resize(250, 350);
                df.show();
            }
            if (this.detailsChoice.getSelectedItem().equals("About")) {
                boolean dem = false;
                if (this.online || this.demo) {
                    dem = true;
                }
                final Dialog AboutDialog = new Aboutdialog(this, this.versStr, dem);
                AboutDialog.resize(350, 230);
                AboutDialog.show();
            }
        }
        return true;
    }
    
    public boolean handleEvent(final Event evt) {
        if (evt.target instanceof Scrollbar) {
            if (evt.target == this.scrollSunR) {
                this.valueSunR = ((Scrollbar)evt.target).getValue();
                if (this.valueSunR == 0) {
                    this.valueSunR = 1;
                }
                this.sunLabel.setText(" Sun Radius = " + this.valueSunR);
                this.ratio = this.valueMoonR / this.valueSunR;
                this.ratioLabel.setText(" Ratio rMoon/RSun=" + Math.round(1000.0 * this.ratio) / 1000.0);
                this.scrollRatio.setValue((int)Math.round(100.0 * this.ratio));
                this.repaint();
                return true;
            }
            if (evt.target == this.scrollMoonR) {
                this.valueMoonR = ((Scrollbar)evt.target).getValue();
                this.moonLabel.setText(" Moon Radius = " + this.valueMoonR);
                this.ratio = this.valueMoonR / this.valueSunR;
                this.ratioLabel.setText(" Ratio rMoon/RSun=" + Math.round(1000.0 * this.ratio) / 1000.0);
                this.scrollRatio.setValue((int)Math.round(100.0 * this.ratio));
                this.repaint();
                return true;
            }
            if (evt.target == this.scrollMoonY) {
                this.valueMoonY = ((Scrollbar)evt.target).getValue();
                this.moonYLabel.setText(" Moon Center Y =" + this.valueMoonY);
                this.repaint();
                return true;
            }
            if (evt.target == this.scrollRatio) {
                this.valueRatio = ((Scrollbar)evt.target).getValue();
                this.ratio = this.valueRatio / 100.0;
                this.valueMoonR = (int)Math.round(this.ratio * 100.0);
                this.scrollMoonR.setValue(this.valueMoonR);
                this.moonLabel.setText(" Moon Radius = " + this.valueMoonR);
                this.ratioLabel.setText(" Ratio rMoon/RSun=" + Math.round(1000.0 * this.valueMoonR / this.valueSunR) / 1000.0);
                this.repaint();
                return true;
            }
        }
        return super.handleEvent(evt);
    }
    
    public void doData() {
        this.nData = 1;
        double yS = 0.0;
        double mag = 0.0;
        double area = 0.0;
        final double areaSun = 3.141592653589793 * this.valueSunR * this.valueSunR;
        String str1 = "";
        for (int i = 0; i <= 20; ++i) {
            final double moonY = i * 10.0;
            yS = (moonY * moonY - this.valueMoonR * this.valueMoonR + this.valueSunR * this.valueSunR) / (2.0 * moonY);
            if (Math.abs(yS) > 100.0) {
                yS = 0.0;
            }
            double alphaSun = yS / this.valueSunR;
            alphaSun = Math.acos(alphaSun);
            double alphaMoon = (moonY - yS) / this.valueMoonR;
            alphaMoon = Math.acos(alphaMoon);
            boolean mondInnen = false;
            boolean mondAussen = false;
            boolean sonneInnen = false;
            if (moonY + this.valueMoonR <= this.valueSunR) {
                mag = this.valueMoonR / this.valueSunR;
                mondInnen = true;
            }
            else if (this.valueSunR < this.valueMoonR + moonY && Math.abs(moonY - this.valueMoonR) > this.valueSunR) {
                mag = this.valueMoonR / this.valueSunR;
                sonneInnen = true;
            }
            else if (yS >= 0.0) {
                if (yS <= this.valueSunR) {
                    final double hMoon = yS - (moonY - this.valueMoonR);
                    final double hSun = this.valueSunR - yS;
                    mag = (hMoon + hSun) / (2.0 * this.valueSunR);
                }
            }
            else if (yS < 0.0) {
                final double hMoon = Math.abs(moonY - this.valueMoonR) - Math.abs(yS);
                final double hSun = this.valueSunR + Math.abs(yS);
                mag = (hMoon + hSun) / (2.0 * this.valueSunR);
            }
            else if (this.valueMoonR > this.valueSunR && this.valueSunR < moonY + this.valueMoonR && this.valueSunR < this.valueMoonR - moonY) {
                mag = this.valueMoonR / this.valueSunR;
            }
            if (moonY - this.valueMoonR > this.valueSunR) {
                mag = 0.0;
                area = 1.0;
                mondAussen = true;
                sonneInnen = false;
            }
            else if (this.valueSunR == this.valueMoonR && moonY == 0.0) {
                mag = 1.0;
                area = 1.0;
            }
            if (alphaSun != 0.0 && alphaMoon != 0.0) {
                area = (this.valueSunR * this.valueSunR * (2.0 * alphaSun - Math.sin(2.0 * alphaSun)) / 2.0 + this.valueMoonR * this.valueMoonR * (2.0 * alphaMoon - Math.sin(2.0 * alphaMoon)) / 2.0) / areaSun;
            }
            if (sonneInnen) {
                area = 1.0;
            }
            if (mondInnen) {
                area = mag * mag;
            }
            str1 = String.valueOf(mag);
            if (str1.length() < 4) {
                str1 = String.valueOf(str1) + " ";
            }
            this.data[i] = str1 + "         " + Math.round(1000.0 * area) / 10.0 + "\n";
        }
        this.nData = 21;
    }
    
    public void paint(final Graphics g) {
        final int y0 = 390;
        final int x0 = this.size().width / 2;
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString(String.valueOf(this.versStr) + " -  (c) 2006-2008 J. Giesen - www.GeoAstro.de", 90, this.size().height - 5);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.setColor(Color.yellow);
        g.fillOval(x0 - this.valueSunR, y0 - this.valueSunR, 2 * this.valueSunR, 2 * this.valueSunR);
        g.setColor(Color.red);
        g.drawOval(x0 - this.valueSunR, y0 - this.valueSunR, 2 * this.valueSunR, 2 * this.valueSunR);
        g.setColor(Color.black);
        g.fillOval(x0 - this.valueMoonR, y0 - this.valueMoonY - this.valueMoonR, 2 * this.valueMoonR, 2 * this.valueMoonR);
        g.setColor(Color.red);
        double yS = 0.0;
        yS = (this.valueMoonY * this.valueMoonY - this.valueMoonR * this.valueMoonR + this.valueSunR * this.valueSunR) / (2.0 * this.valueMoonY);
        if (Math.abs(yS) > 100.0) {
            yS = 0.0;
        }
        double alphaSun = yS / this.valueSunR;
        alphaSun = Math.acos(alphaSun);
        double alphaMoon = (this.valueMoonY - yS) / this.valueMoonR;
        alphaMoon = Math.acos(alphaMoon);
        g.setColor(Color.red);
        g.drawLine(x0, y0 + this.valueSunR, x0, y0 - this.valueSunR);
        g.drawOval(x0 - this.valueSunR, y0 - this.valueSunR, 2 * this.valueSunR, 2 * this.valueSunR);
        double mag = 0.0;
        final double ratioArea = 0.0;
        double area = 0.0;
        double hMoon = 0.0;
        double hSun = 0.0;
        final double areaSun = 3.141592653589793 * this.valueSunR * this.valueSunR;
        final double sunR = this.valueSunR;
        final double moonR = this.valueMoonR;
        final double moonY = this.valueMoonY;
        boolean mondInnen = false;
        boolean mondAussen = false;
        boolean sonneInnen = false;
        if (this.valueMoonY + this.valueMoonR <= this.valueSunR) {
            mag = moonR / sunR;
            mondInnen = true;
        }
        else if (this.valueSunR < this.valueMoonR + this.valueMoonY && Math.abs(this.valueMoonY - this.valueMoonR) > this.valueSunR) {
            mag = moonR / sunR;
            sonneInnen = true;
        }
        else if (yS >= 0.0) {
            if (yS <= sunR) {
                hMoon = yS - (moonY - moonR);
                hSun = sunR - yS;
                mag = (hMoon + hSun) / (2.0 * sunR);
            }
        }
        else if (yS < 0.0) {
            hMoon = Math.abs(moonY - moonR) - Math.abs(yS);
            hSun = sunR + Math.abs(yS);
            mag = (hMoon + hSun) / (2.0 * sunR);
        }
        else if (moonR > sunR && sunR < moonY + moonR && sunR < moonR - moonY) {
            mag = moonR / sunR;
        }
        if (moonY - moonR > sunR) {
            mag = 0.0;
            area = 1.0;
            mondAussen = true;
            sonneInnen = false;
        }
        else if (this.valueSunR == this.valueMoonR && this.valueMoonY == 0) {
            mag = 1.0;
            area = 1.0;
        }
        for (int i = -9; i <= 9; ++i) {
            int dx;
            if (i % 2 == 0) {
                dx = 4;
            }
            else {
                dx = 2;
            }
            g.drawLine(x0 - dx, y0 - (int)Math.round(i * this.valueSunR / 10.0), x0 + dx, y0 - (int)Math.round(i * this.valueSunR / 10.0));
        }
        if (alphaSun != 0.0 && alphaMoon != 0.0) {
            area = (sunR * sunR * (2.0 * alphaSun - Math.sin(2.0 * alphaSun)) / 2.0 + moonR * moonR * (2.0 * alphaMoon - Math.sin(2.0 * alphaMoon)) / 2.0) / areaSun;
        }
        if (sonneInnen) {
            area = 1.0;
        }
        if (mondInnen) {
            area = mag * mag;
        }
        if (mondAussen) {
            this.magLabel.setText(" No Eclipse");
        }
        else {
            this.magLabel.setText(" Magnitude " + Math.round(1000.0 * mag) / 1000.0);
        }
        this.areaLabel.setText(" Obscuration " + Math.round(1000.0 * area) / 10.0 + " %");
        g.setColor(Color.white);
        g.drawLine(x0 - 5, y0 - this.valueMoonY, x0 + 5, y0 - this.valueMoonY);
        g.drawLine(x0, y0 - this.valueMoonY - 5, x0, y0 - this.valueMoonY + 5);
        g.setColor(Color.gray);
        int max = 0;
        if (this.valueSunR > this.valueMoonR) {
            max = this.valueSunR;
        }
        else {
            max = this.valueMoonR;
        }
        max += 20;
        g.drawLine(x0 - max, y0, x0 + max, y0);
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 96));
            g.setColor(Color.red);
            g.drawString("D E M O", 30, 300);
        }
    }
    
    public magnitude061() {
        this.valueMoonR = 100;
        this.valueSunR = 100;
        this.valueMoonY = 50;
        this.valueRatio = 100;
        this.ratio = this.valueMoonR / this.valueSunR;
        this.data = new String[370];
        this.nData = 0;
        this.demo = true;
    }
}
