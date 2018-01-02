import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Dialog;
import java.net.URL;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Scrollbar;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class refract086 extends Applet
{
    final char deg = '°';
    final String min = "'";
    Scrollbar scrollSunY;
    Label sunYLabel;
    Choice detailsChoice;
    double sunR;
    double Faktor;
    String[] Data;
    int nData;
    String titleStr;
    String versStr;
    String str;
    public String email;
    public String param;
    public String wwwStr;
    boolean online;
    boolean demo;
    int y0;
    int x0;
    boolean showTrueSun;
    double R;
    double hObs;
    double hTrue;
    double hObsOben;
    double hObsUnten;
    double hObsMitte;
    int oben;
    int unten;
    int max;
    int[] yOben;
    int[] yUnten;
    Scrollbar scrollP;
    Scrollbar scrollT;
    int valueP;
    int valueT;
    double ptFaktor;
    String ptStr;
    int maxScrollY;
    int valueSunY;
    int MAX;
    Choice zoomChoice;
    Canvas myCan;
    Label pLabel;
    Label tLabel;
    Label refractLabel;
    double hTrueMitte;
    int yTrueMitte;
    int yAppMitte;
    Color skyColor;
    Color darkColor;
    boolean gridOK;
    int radius;
    double[][] numData;
    
    public void init() {
        this.setFont(new Font("Helvetica", 0, 12));
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 0, 0, 0);
        this.x0 = this.size().width / 2;
        this.versStr = "Refraction 0.86";
        this.setBackground(new Color(255, 255, 255));
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        gbc.gridy = 0;
        gbc.gridx = 0;
        (this.zoomChoice = new Choice()).addItem("Altitude < 12°");
        this.zoomChoice.addItem("Altitude < 6°");
        this.zoomChoice.addItem("Altitude < 4°");
        this.zoomChoice.addItem("Altitude < 3°");
        this.zoomChoice.addItem("Altitude < 2°");
        gbl.setConstraints(this.zoomChoice, gbc);
        this.zoomChoice.select("Altitude < 3°");
        this.add(this.zoomChoice);
        gbc.gridx = 1;
        this.scrollSunY = new Scrollbar(0);
        gbc.fill = 2;
        this.scrollSunY.setBackground(Color.blue);
        this.scrollSunY.setMaximum(this.maxScrollY);
        this.scrollSunY.setValue(this.valueSunY);
        gbl.setConstraints(this.scrollSunY, gbc);
        this.add(this.scrollSunY);
        (this.sunYLabel = new Label()).setText(" True Alt. " + this.DMS(this.hTrueMitte));
        gbl.setConstraints(this.scrollSunY, gbc);
        this.add(this.sunYLabel);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = 0;
        gbc.gridx = 0;
        (this.detailsChoice = new Choice()).addItem("Details...");
        this.detailsChoice.addItem("Reset P,T");
        this.detailsChoice.addItem("Show/Hide True Sun");
        this.detailsChoice.addItem("Show/Hide Grid");
        this.detailsChoice.addItem("Data: True-App.");
        this.detailsChoice.addItem("Data: App.-True");
        this.detailsChoice.addItem("Graph: Refraction");
        this.detailsChoice.addItem("About");
        gbl.setConstraints(this.detailsChoice, gbc);
        this.add(this.detailsChoice);
        gbc.gridx = 1;
        (this.scrollP = new Scrollbar(0)).setMaximum(110);
        this.scrollP.setValue(this.valueP);
        gbc.fill = 2;
        gbl.setConstraints(this.scrollP, gbc);
        this.add(this.scrollP);
        gbc.fill = 0;
        gbc.gridx = 2;
        (this.pLabel = new Label()).setText("P = " + (960 + this.valueP) + " hPa");
        gbl.setConstraints(this.pLabel, gbc);
        this.add(this.pLabel);
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 60, 0, 0);
        gbc.fill = 2;
        (this.refractLabel = new Label()).setText("Refraction: " + Math.round(600.0 * (this.hObsMitte - this.hTrueMitte)) / 10.0 + "'" + "\n" + "Flattening: " + Math.round(60000.0 * (this.hObsOben - this.hObsUnten) / (2.0 * this.sunR)) / 1000.0 + "\n" + "   P,T: " + Math.round(1000.0 * this.ptFaktor) / 1000.0);
        gbl.setConstraints(this.refractLabel, gbc);
        this.add(this.refractLabel);
        gbc.gridx = 1;
        this.scrollT = new Scrollbar(0);
        gbc.insets = new Insets(0, 0, 0, 0);
        this.scrollT.setMaximum(90);
        this.scrollT.setValue(this.valueT);
        gbl.setConstraints(this.scrollT, gbc);
        this.add(this.scrollT);
        gbc.fill = 0;
        gbc.gridx = 2;
        (this.tLabel = new Label()).setText("T = " + (this.valueT - 40) + " " + '°' + "C");
        gbl.setConstraints(this.tLabel, gbc);
        this.add(this.tLabel);
        gbc.gridy = 3;
        gbc.weighty = 200.0;
        gbl.setConstraints(this.myCan = new Canvas(), gbc);
        this.add(this.myCan);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        if (this.formula(this.wwwStr, 18) == this.formula("http://www.tyge.de", 18) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 20) == this.formula("http://www.SciAm.com", 20)) {
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
        return (int)num + 11108 + 479;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Choice) {
            if (event.target == this.zoomChoice) {
                if (this.zoomChoice.getSelectedItem().equals("Altitude < 12°")) {
                    this.Faktor = 0.5;
                }
                if (this.zoomChoice.getSelectedItem().equals("Altitude < 6°")) {
                    this.Faktor = 1.0;
                }
                if (this.zoomChoice.getSelectedItem().equals("Altitude < 4°")) {
                    this.Faktor = 1.5;
                }
                if (this.zoomChoice.getSelectedItem().equals("Altitude < 3°")) {
                    this.Faktor = 2.0;
                }
                if (this.zoomChoice.getSelectedItem().equals("Altitude < 2°")) {
                    this.Faktor = 3.0;
                }
                this.yTrueMitte = (int)Math.round(490.0 - 360.0 * this.hTrueMitte * this.Faktor / 6.0);
                this.valueSunY = 610 - this.yTrueMitte;
                if (this.valueSunY > this.MAX) {
                    this.valueSunY = this.MAX;
                }
                if (this.valueSunY < 0) {
                    this.valueSunY = 0;
                }
                this.scrollSunY.setValue(this.valueSunY);
                this.repaint();
                return true;
            }
            if (event.target == this.detailsChoice) {
                if (this.detailsChoice.getSelectedItem().equals("Reset P,T")) {
                    this.valueP = 50;
                    this.valueT = 50;
                    this.scrollP.setValue(this.valueP);
                    this.scrollT.setValue(this.valueT);
                    this.detailsChoice.select(0);
                    this.repaint();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Show/Hide True Sun")) {
                    this.showTrueSun ^= true;
                    this.detailsChoice.select(0);
                    this.repaint();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Show/Hide Grid")) {
                    this.gridOK ^= true;
                    this.detailsChoice.select(0);
                    this.repaint();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Graph: Refraction")) {
                    this.doNumData();
                    final boolean dem = this.online || this.demo;
                    this.detailsChoice.select(0);
                    final Frame gf = new graphFrame("App. Altitude and Refraction", this.numData, this.nData, this.ptFaktor, dem);
                    gf.resize(470, 460);
                    gf.setLocation(20, 200);
                    gf.show();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Data: True-App.")) {
                    this.doData();
                    final boolean dem = this.online || this.demo;
                    this.detailsChoice.select(0);
                    final Frame df = new dataFrame(this.ptStr, "Refraction: True-App.", this.versStr, this.Data, this.nData, dem);
                    df.resize(300, 425);
                    df.setLocation(20, 200);
                    df.show();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Data: App.-True")) {
                    this.doData1();
                    final boolean dem = this.online || this.demo;
                    this.detailsChoice.select(0);
                    final Frame df = new dataFrame(this.ptStr, "Refraction: App.-True", this.versStr, this.Data, this.nData, dem);
                    df.resize(300, 425);
                    df.setLocation(350, 200);
                    df.show();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("About")) {
                    boolean dem = false;
                    if (this.online || this.demo) {
                        dem = true;
                    }
                    this.detailsChoice.select(0);
                    final Dialog AboutDialog = new Aboutdialog(this, this.versStr, dem);
                    AboutDialog.resize(350, 230);
                    AboutDialog.show();
                }
            }
        }
        return true;
    }
    
    public boolean handleEvent(final Event evt) {
        if (evt.target instanceof Scrollbar) {
            if (evt.target == this.scrollSunY) {
                this.valueSunY = ((Scrollbar)evt.target).getValue();
                this.yTrueMitte = 610 - this.valueSunY;
            }
            if (evt.target == this.scrollP) {
                this.valueP = ((Scrollbar)evt.target).getValue();
            }
            if (evt.target == this.scrollT) {
                this.valueT = ((Scrollbar)evt.target).getValue();
            }
        }
        this.repaint();
        return super.handleEvent(evt);
    }
    
    double refract(final double h) {
        final double K = 0.017453292519943295;
        return 1.02 / Math.tan(K * (h + 10.3 / (h + 5.11)));
    }
    
    double refract1(final double h) {
        final double K = 0.017453292519943295;
        return 1.0 / Math.tan(K * (h + 7.31 / (h + 4.4)));
    }
    
    public String DMS(final double x) {
        String str = "";
        double X;
        if (x < 0.0) {
            X = Math.abs(x);
        }
        else {
            X = x;
        }
        final int DEG = (int)X;
        int MIN = (int)(this.frac(X) * 60.0);
        double SEC = 60.0 * (this.frac(X) * 60.0 - MIN);
        if (Math.round(SEC) == 60L) {
            SEC = 0.0;
            ++MIN;
        }
        if (x < 0.0) {
            str = "-";
        }
        else {
            str = "+";
        }
        if (DEG < 10) {
            str = " " + str;
        }
        str = String.valueOf(str) + DEG + '°' + " ";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + MIN + "' ";
        if ((int)Math.round(SEC) < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + Math.round(SEC) + "''";
    }
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
    }
    
    public void doData() {
        double hTrue = -2.5;
        double dH = 1.0;
        String str = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        this.nData = 115;
        this.Data[0] = "True Alt.   App. Alt.   Refr.       Flatt.\n";
        final String[] data = this.Data;
        final int n = 0;
        data[n] = String.valueOf(data[n]) + "  °           °           '\n";
        for (int i = 1; i <= this.nData; ++i) {
            if (i < 44) {
                dH = 0.5;
            }
            else {
                dH = 1.0;
            }
            hTrue += dH;
            for (str = String.valueOf(hTrue); str.length() < 10; str = String.valueOf(str) + " ") {}
            double R = this.refract(hTrue) / 60.0;
            R *= this.ptFaktor;
            for (str3 = String.valueOf(Math.round(6000.0 * R) / 100.0); str3.length() < 10; str3 = String.valueOf(str3) + " ") {}
            final double hApp = hTrue + R;
            for (str2 = String.valueOf(Math.round(1000.0 * hApp) / 1000.0); str2.length() < 10; str2 = String.valueOf(str2) + " ") {}
            final double radius = 0.26666666666666666;
            double R2 = this.refract(hTrue + radius) / 60.0;
            R2 *= this.ptFaktor;
            R2 += hTrue;
            double R3 = this.refract(hTrue - radius) / 60.0;
            R3 *= this.ptFaktor;
            R3 += hTrue;
            final double flat = 1.0 + (R2 - R3) / (2.0 * radius);
            str4 = String.valueOf(Math.round(1000.0 * flat) / 1000.0);
            this.Data[i] = String.valueOf(str) + "  " + str2 + "  " + str3 + "  " + str4 + "\n";
        }
    }
    
    public void doNumData() {
        double hTrue = -2.5;
        double dH = 1.0;
        this.nData = 115;
        double hApp = 0.0;
        for (int i = 1; i <= this.nData; ++i) {
            if (i < 44) {
                dH = 0.5;
            }
            else {
                dH = 1.0;
            }
            hApp += dH;
            double R = this.refract1(hApp);
            R *= this.ptFaktor;
            hTrue = hApp - R / 60.0;
            this.numData[i][0] = hTrue;
            this.numData[i][1] = R;
        }
    }
    
    public void doData1() {
        String str = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        this.nData = 114;
        this.Data[0] = "App. Alt.   True Alt.   Refr.       Flatt.\n";
        final String[] data = this.Data;
        final int n = 0;
        data[n] = String.valueOf(data[n]) + "  °           °           '\n";
        double hApp = -1.5;
        for (int i = 1; i <= this.nData; ++i) {
            double dH;
            if (i < 44) {
                dH = 0.5;
            }
            else {
                dH = 1.0;
            }
            hApp += dH;
            for (str = String.valueOf(hApp); str.length() < 10; str = String.valueOf(str) + " ") {}
            double R = this.refract1(hApp) / 60.0;
            R *= this.ptFaktor;
            for (str3 = String.valueOf(Math.round(6000.0 * R) / 100.0); str3.length() < 10; str3 = String.valueOf(str3) + " ") {}
            final double hTrue = hApp - R;
            for (str2 = String.valueOf(Math.round(1000.0 * hTrue) / 1000.0); str2.length() < 10; str2 = String.valueOf(str2) + " ") {}
            final double radius = 0.26666666666666666;
            double R2 = this.refract1(hApp + radius) / 60.0;
            R2 *= this.ptFaktor;
            R2 += hApp;
            double R3 = this.refract1(hApp - radius) / 60.0;
            R3 *= this.ptFaktor;
            R3 += hApp;
            final double flat = 1.0 + (R2 - R3) / (2.0 * radius);
            str4 = String.valueOf(Math.round(1000.0 * flat) / 1000.0);
            this.Data[i] = String.valueOf(str) + "  " + str2 + "  " + str3 + "  " + str4 + "\n";
        }
    }
    
    public void paint(final Graphics g) {
        final double limitAppAlt = -1.75;
        this.ptFaktor = (960 + this.valueP) / 1010.0 * (283.0 / (273 + this.valueT - 40));
        this.ptStr = "P = " + (960 + this.valueP) + " hPA,  T = " + (this.valueT - 40) + " " + '°' + "C,  Factor = " + this.ptFaktor + "\n";
        g.setColor(Color.black);
        g.setFont(new Font("Courier", 0, 10));
        this.radius = (int)Math.round(this.Faktor * this.sunR);
        this.yTrueMitte = 610 - this.valueSunY;
        this.hTrueMitte = -2.0 / this.Faktor + (610 - this.yTrueMitte) / (this.Faktor * 60.0);
        this.R = this.refract(this.hTrueMitte);
        this.hObsMitte = this.hTrueMitte + this.R / 60.0;
        this.sunYLabel.setText(" True Alt. " + this.DMS(this.hTrueMitte));
        g.setColor(this.skyColor);
        g.fillRect(2, this.y0 - 370, this.size().width - 4, 370);
        g.setColor(this.darkColor);
        g.fillRect(2, this.y0, this.size().width - 4, this.size().height - this.y0);
        this.yTrueMitte = 610 - this.valueSunY;
        if (this.hTrueMitte > limitAppAlt) {
            g.setColor(Color.red);
            this.max = (int)Math.round(this.sunR * this.Faktor);
            for (int i = 0; i <= this.max; ++i) {
                this.hTrue = this.hTrueMitte * 60.0 + Math.sqrt(this.sunR * this.sunR - i * i / (this.Faktor * this.Faktor));
                this.R = this.refract(this.hTrue / 60.0);
                this.R *= this.ptFaktor;
                this.hObs = (this.hTrue + this.R) / 60.0;
                this.yOben[i] = this.y0 - (int)Math.round(this.hObs * this.Faktor * 60.0);
                if (i == 0) {
                    this.oben = this.yOben[i];
                    this.hObsOben = this.hObs;
                }
                if (i == this.max) {
                    this.hObsMitte = this.hObs;
                }
            }
            this.yAppMitte = this.y0 - (int)Math.round(this.hObsMitte * this.Faktor * 60.0);
            g.drawString("center " + Math.round(600.0 * this.hObsMitte) / 10.0 + "'", this.x0 + 80, this.yAppMitte + 5);
            g.drawString("= " + Math.round(1000.0 * this.hObsMitte) / 1000.0 + '°', this.x0 + 160, this.yAppMitte + 5);
            g.drawString("upper  " + Math.round(600.0 * this.hObsOben) / 10.0 + "'", this.x0 + 80, this.oben + 5);
            g.drawString("= " + Math.round(1000.0 * this.hObsOben) / 1000.0 + '°', this.x0 + 160, this.oben + 5);
            for (int j = 0; j <= this.max; ++j) {
                this.hTrue = this.hTrueMitte * 60.0 - Math.sqrt(this.sunR * this.sunR - j * j / (this.Faktor * this.Faktor));
                this.R = this.refract(this.hTrue / 60.0);
                this.R *= this.ptFaktor;
                this.hObs = (this.hTrue + this.R) / 60.0;
                this.yUnten[j] = this.y0 - (int)Math.round(this.hObs * this.Faktor * 60.0);
                if (j == 0) {
                    this.unten = this.yUnten[j];
                    this.hObsUnten = this.hObs;
                }
            }
            g.drawString("lower  " + Math.round(600.0 * this.hObsUnten) / 10.0 + "'", this.x0 + 80, this.unten + 5);
            g.drawString("= " + Math.round(1000.0 * this.hObsUnten) / 1000.0 + '°', this.x0 + 160, this.unten + 5);
            g.drawString("vert. " + Math.round(100.0 * (this.hObsOben - this.hObsUnten)) / 100.0 + "'", this.x0 + 220, this.yAppMitte + 5);
        }
        final int links = this.x0 - 220;
        if (this.showTrueSun) {
            g.setColor(Color.blue);
            g.drawString("TRUE SUN:", links, 115);
            g.drawString("center: " + Math.round(600.0 * this.hTrueMitte) / 10.0 + "'", links, this.y0 - (int)Math.round(this.hTrueMitte * 60.0 * this.Faktor) + 5);
            g.drawString("= " + Math.round(1000.0 * this.hTrueMitte) / 1000.0 + '°', links + 90, this.y0 - (int)Math.round(this.hTrueMitte * 60.0 * this.Faktor) + 5);
            g.drawString("upper:  " + Math.round(600.0 * this.hTrueMitte + 10.0 * this.sunR) / 10.0 + "'", links, this.y0 - (int)Math.round((this.hTrueMitte + this.sunR / 60.0) * 60.0 * this.Faktor) + 5);
            g.drawString("= " + Math.round(1000.0 * (this.hTrueMitte + this.sunR / 60.0)) / 1000.0 + '°', links + 90, this.y0 - (int)Math.round((this.hTrueMitte + this.sunR / 60.0) * 60.0 * this.Faktor) + 5);
            g.drawString("lower:  " + Math.round(600.0 * this.hTrueMitte - 10.0 * this.sunR) / 10.0 + "'", links, this.y0 - (int)Math.round((this.hTrueMitte - this.sunR / 60.0) * 60.0 * this.Faktor) + 5);
            g.drawString("= " + Math.round(1000.0 * (this.hTrueMitte - this.sunR / 60.0)) / 1000.0 + '°', links + 90, this.y0 - (int)Math.round((this.hTrueMitte - this.sunR / 60.0) * 60.0 * this.Faktor) + 5);
        }
        if (this.hTrueMitte > limitAppAlt) {
            g.setColor(Color.yellow);
            for (int j = 0; j <= this.max; ++j) {
                g.drawLine(this.x0 - j, this.yOben[j], this.x0 - j, this.yUnten[j]);
            }
            for (int k = 0; k <= this.max; ++k) {
                g.drawLine(this.x0 + k, this.yOben[k], this.x0 + k, this.yUnten[k]);
            }
            g.setColor(Color.red);
            for (int l = 0; l < this.max; ++l) {
                g.drawLine(this.x0 + l, this.yOben[l], this.x0 + l + 1, this.yOben[l + 1]);
            }
            for (int m = 0; m < this.max; ++m) {
                g.drawLine(this.x0 - m, this.yOben[m], this.x0 - m - 1, this.yOben[m + 1]);
            }
            for (int i2 = 0; i2 < this.max; ++i2) {
                g.drawLine(this.x0 + i2, this.yUnten[i2], this.x0 + i2 + 1, this.yUnten[i2 + 1]);
            }
            for (int i3 = 0; i3 < this.max; ++i3) {
                g.drawLine(this.x0 - i3, this.yUnten[i3], this.x0 - i3 - 1, this.yUnten[i3 + 1]);
            }
            this.pLabel.setText("P = " + (960 + this.valueP) + " hPa");
            this.tLabel.setText("T = " + (this.valueT - 40) + " " + '°' + "C");
        }
        g.setColor(Color.gray);
        double dy = 30.0;
        final int start = -6;
        int stop = 12;
        if (this.Faktor == 1.0) {
            dy = 30.0;
            stop = 12;
        }
        else if (this.Faktor == 0.5) {
            dy = 30.0;
            stop = 12;
        }
        else if (this.Faktor == 1.5) {
            dy = 22.5;
            stop = 16;
        }
        else if (this.Faktor == 2.0) {
            dy = 30.0;
            stop = 12;
        }
        else if (this.Faktor == 2.5) {
            dy = 18.0;
            stop = 20;
        }
        else if (this.Faktor == 3.0) {
            dy = 22.5;
            stop = 16;
        }
        int DX = 10;
        if (this.gridOK) {
            DX = (int)(this.Faktor * dy);
        }
        for (int i3 = start; i3 <= stop; ++i3) {
            if (i3 % 2 == 0) {
                g.setColor(Color.darkGray);
                g.drawLine(this.x0 - DX, this.y0 - (int)Math.round(i3 * dy), this.x0 + DX, this.y0 - (int)Math.round(i3 * dy));
                g.setColor(Color.black);
                if (this.Faktor != 2.5) {
                    g.drawString(new StringBuffer().append(Math.round(100 * i3 * dy / (60.0 * this.Faktor)) / 100.0).append('°').toString(), 20, this.y0 - (int)Math.round(i3 * dy));
                }
                else {
                    g.drawString(new StringBuffer().append(Math.round(100 * i3 * 0.125) / 100.0).append('°').toString(), 20, this.y0 - (int)Math.round(i3 * dy));
                }
                g.setColor(Color.gray);
            }
            else {
                g.setColor(Color.gray);
                if (!this.gridOK) {
                    g.drawLine(this.x0 - 7, this.y0 - (int)Math.round(i3 * dy), this.x0 + 7, this.y0 - (int)Math.round(i3 * dy));
                }
                else {
                    g.drawLine(this.x0 - DX, this.y0 - (int)Math.round(i3 * dy), this.x0 + DX, this.y0 - (int)Math.round(i3 * dy));
                }
            }
        }
        if (this.gridOK) {
            for (int i4 = -(int)this.Faktor; i4 <= (int)this.Faktor; ++i4) {
                if (i4 % 2 == 0) {
                    g.setColor(Color.darkGray);
                }
                else {
                    g.setColor(Color.gray);
                }
                g.drawLine(this.x0 - (int)Math.round(i4 * dy), this.y0 - 360, this.x0 - (int)Math.round(i4 * dy), this.y0 + 120);
            }
        }
        if (this.hTrueMitte > limitAppAlt) {
            g.setColor(Color.red);
            g.drawLine(this.x0 - 4, this.yAppMitte, this.x0 + 4, this.yAppMitte);
            g.drawLine(this.x0, this.yAppMitte + 4, this.x0, this.yAppMitte - 4);
        }
        if (this.showTrueSun) {
            g.setColor(Color.blue);
            g.drawString("TRUE SUN:", links, 115);
            g.drawOval(this.x0 - this.radius, this.yTrueMitte - this.radius, 2 * this.radius, 2 * this.radius);
            g.drawLine(this.x0 - 4, this.yTrueMitte, this.x0 + 4, this.yTrueMitte);
            g.drawLine(this.x0, this.yTrueMitte + 4, this.x0, this.yTrueMitte - 4);
        }
        g.clearRect(1, 1, 2 * this.x0, 120);
        if (this.hTrueMitte > limitAppAlt) {
            this.refractLabel.setText("Refraction: " + Math.round(600.0 * (this.hObsMitte - this.hTrueMitte)) / 10.0 + "'" + "\n" + "Flattening: " + Math.round(60000.0 * (this.hObsOben - this.hObsUnten) / (2.0 * this.sunR)) / 1000.0 + "\n" + "P, T: " + Math.round(1000.0 * this.ptFaktor) / 1000.0);
        }
        else {
            this.refractLabel.setText("Refraction: \nFlattening: \nP, T: " + Math.round(1000.0 * this.ptFaktor) / 1000.0);
        }
        g.setColor(Color.blue);
        g.drawString("TRUE SUN:", links, 115);
        g.setColor(Color.red);
        g.drawString("APPAR. SUN:", this.x0 + 80, 115);
        g.setColor(Color.black);
        g.drawLine(1, this.y0, this.size().width - 2, this.y0);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.setFont(new Font("Helvetica", 0, 9));
        g.drawString(String.valueOf(this.versStr) + " -  (c) 2006-2011 J. Giesen - www.GeoAstro.de", 175, this.size().height - 5);
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 96));
            g.setColor(Color.red);
            g.drawString("D E M O", 120, 400);
        }
    }
    
    public refract086() {
        this.sunR = 16.0;
        this.Faktor = 2.0;
        this.Data = new String[120];
        this.demo = true;
        this.y0 = 490;
        this.showTrueSun = true;
        this.yOben = new int[50];
        this.yUnten = new int[50];
        this.valueP = 50;
        this.valueT = 50;
        this.ptFaktor = 1.0;
        this.maxScrollY = 490;
        this.valueSunY = 120;
        this.MAX = 480;
        this.hTrueMitte = 0.0;
        this.yTrueMitte = 490;
        this.skyColor = new Color(150, 255, 255);
        this.darkColor = new Color(230, 230, 230);
        this.gridOK = false;
        this.numData = new double[150][2];
    }
}
