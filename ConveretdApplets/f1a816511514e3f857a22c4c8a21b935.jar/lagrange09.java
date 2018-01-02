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

public class lagrange09 extends Applet
{
    Scrollbar scrollM1M2;
    Scrollbar scrollRadius;
    Label ratioM1M2Label;
    Label radiusLabel;
    Label magLabel;
    Label omegaLabel;
    Choice detailsChoice;
    int valueR;
    int valueM1M2;
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
    double r1;
    double r2;
    boolean showLagrange;
    boolean showOrbits;
    int[] y1;
    int[] y2;
    int[] y3;
    double[] Y;
    double[] y;
    double[] Y1;
    double[] Y2;
    double[] Y3;
    int y0;
    int x0;
    int oben;
    int unten;
    boolean fieldM1;
    boolean fieldM2;
    boolean fieldSum;
    boolean L4L5;
    boolean rotate;
    double K;
    int xr1;
    int yr1;
    int xr2;
    int yr2;
    int xL1;
    int yL1;
    int xL2;
    int yL2;
    int xL3;
    int yL3;
    int xL4;
    int yL4;
    int xL5;
    int yL5;
    int nWinkel;
    int radL1;
    int radL2;
    int radL3;
    int radL4L5;
    double rL1;
    double rL2;
    double rL3;
    double winkel;
    double gamma;
    int rho;
    
    public void init() {
        this.y0 = 320;
        this.x0 = this.size().width / 2;
        this.unten = this.size().height - 15;
        this.versStr = "Lagrange(1) 0.9";
        this.setBackground(new Color(255, 255, 255));
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        this.setLayout(new BorderLayout());
        final Panel p = new Panel();
        p.setLayout(new GridLayout(0, 2));
        p.add(this.ratioM1M2Label = new Label(" Ratio M1/M2 = " + this.valueM1M2 / 100));
        (this.scrollM1M2 = new Scrollbar(0)).setMaximum(5010);
        this.scrollM1M2.setValue(this.valueM1M2);
        p.add(this.scrollM1M2);
        p.add(this.radiusLabel = new Label(" Distance M1M2 = " + this.valueR));
        (this.scrollRadius = new Scrollbar(0)).setMaximum(310);
        this.scrollRadius.setValue(this.valueR);
        p.add(this.scrollRadius);
        p.add(this.omegaLabel = new Label(" Ang. Frequ. = "));
        (this.detailsChoice = new Choice()).addItem("Reset");
        this.detailsChoice.addItem("Lagrange Points on/off");
        this.detailsChoice.addItem("Points L4,L5 on/off");
        this.detailsChoice.addItem("Orbits on/off");
        this.detailsChoice.addItem("Field M1 on/off");
        this.detailsChoice.addItem("Field M2 on/off");
        this.detailsChoice.addItem("Field Sum on/off");
        this.detailsChoice.addItem("Data");
        this.detailsChoice.addItem("About");
        p.add(this.detailsChoice);
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
        return (int)num + 3401;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Choice && event.target == this.detailsChoice) {
            if (this.detailsChoice.getSelectedItem().equals("Lagrange Points on/off")) {
                this.showLagrange ^= true;
                this.L4L5 = this.showLagrange;
                this.repaint();
            }
            if (this.detailsChoice.getSelectedItem().equals("Orbits on/off")) {
                this.showOrbits ^= true;
                this.repaint();
            }
            if (this.detailsChoice.getSelectedItem().equals("Field M1 on/off")) {
                this.fieldM1 ^= true;
                this.repaint();
            }
            if (this.detailsChoice.getSelectedItem().equals("Field M2 on/off")) {
                this.fieldM2 ^= true;
                this.repaint();
            }
            if (this.detailsChoice.getSelectedItem().equals("Field Sum on/off")) {
                this.fieldSum ^= true;
                this.repaint();
            }
            if (this.detailsChoice.getSelectedItem().equals("Points L4,L5 on/off")) {
                this.L4L5 ^= true;
                this.repaint();
            }
            if (this.detailsChoice.getSelectedItem().equals("Reset")) {
                this.valueR = 150;
                this.valueM1M2 = 200;
                this.fieldM1 = true;
                this.fieldM2 = true;
                this.fieldSum = true;
                this.showLagrange = true;
                this.showOrbits = false;
                this.L4L5 = false;
                this.rotate = false;
                this.nWinkel = 0;
                this.scrollM1M2.setValue(this.valueM1M2);
                this.ratioM1M2Label.setText(" M1/M2 = " + this.valueM1M2 / 100);
                this.scrollRadius.setValue(this.valueR);
                this.radiusLabel.setText(" Radius = " + this.valueR);
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Data")) {
                this.doData();
                boolean dem = false;
                if (this.online || this.demo) {
                    dem = true;
                }
                final Frame df = new dataFrame("Lagrangian Points", this.versStr, this.data, this.nData, dem);
                df.resize(250, 250);
                df.show();
            }
            if (this.detailsChoice.getSelectedItem().equals("About")) {
                boolean dem = false;
                if (this.online || this.demo) {
                    dem = true;
                }
                final Dialog AboutDialog = new Aboutdialog(this, this.versStr, dem);
                AboutDialog.resize(350, 250);
                AboutDialog.show();
            }
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int code) {
        if (code == 114) {
            this.rotate = true;
            this.doRotate(1);
            this.repaint();
        }
        if (code == 82) {
            this.rotate = true;
            this.doRotate(3);
            this.repaint();
        }
        return true;
    }
    
    public boolean keyUp(final Event event, final int code) {
        if (code == 114) {
            this.rotate = false;
        }
        return true;
    }
    
    public boolean handleEvent(final Event evt) {
        if (evt.target instanceof Scrollbar) {
            if (evt.target == this.scrollM1M2) {
                this.valueM1M2 = ((Scrollbar)evt.target).getValue();
                if (this.valueM1M2 == 0) {
                    this.valueM1M2 = 1;
                }
                this.ratioM1M2Label.setText(" Ratio M1/M2 = " + this.valueM1M2 / 100.0);
                if (this.valueM1M2 > 1500 && this.valueM1M2 < 3000) {
                    this.gamma = 1500.0;
                }
                if (this.valueM1M2 >= 3000) {
                    this.gamma = 1000.0;
                }
                if (this.valueM1M2 <= 1500) {
                    this.gamma = 3000.0;
                }
                this.repaint();
                return true;
            }
            if (evt.target == this.scrollRadius) {
                this.valueR = ((Scrollbar)evt.target).getValue();
                this.radiusLabel.setText(" Distance M1M2 = " + this.valueR);
                this.repaint();
                return true;
            }
        }
        return super.handleEvent(evt);
    }
    
    public void doRotate(final int step) {
        this.rotate = true;
        this.fieldM1 = false;
        this.fieldM2 = false;
        this.fieldSum = false;
        this.showLagrange = false;
        this.L4L5 = false;
        this.nWinkel = (this.nWinkel + 1) % 360;
        this.xr1 = (int)Math.round(this.r2 * Math.cos(this.K * this.nWinkel * step));
        this.yr1 = (int)Math.round(this.r2 * Math.sin(this.K * this.nWinkel * step));
        this.xr2 = (int)Math.round(this.r1 * Math.cos(this.K * this.nWinkel * step));
        this.yr2 = (int)Math.round(this.r1 * Math.sin(this.K * this.nWinkel * step));
        this.xL1 = (int)Math.round(this.radL1 * Math.cos(this.K * this.nWinkel * step));
        this.yL1 = (int)Math.round(this.radL1 * Math.sin(this.K * this.nWinkel * step));
        this.xL2 = (int)Math.round(this.radL2 * Math.cos(this.K * this.nWinkel * step));
        this.yL2 = (int)Math.round(this.radL2 * Math.sin(this.K * this.nWinkel * step));
        this.xL3 = (int)Math.round(this.radL3 * Math.cos(this.K * this.nWinkel * step));
        this.yL3 = (int)Math.round(this.radL3 * Math.sin(this.K * this.nWinkel * step));
        this.xL4 = (int)Math.round(this.radL4L5 * Math.cos(this.K * (this.nWinkel * step + this.winkel + 180.0)));
        this.yL4 = (int)Math.round(this.radL4L5 * Math.sin(this.K * (this.nWinkel * step + this.winkel + 180.0)));
        this.xL5 = (int)Math.round(this.radL4L5 * Math.cos(this.K * (this.nWinkel * step - this.winkel)));
        this.yL5 = (int)Math.round(this.radL4L5 * Math.sin(this.K * (this.nWinkel * step - this.winkel)));
    }
    
    public double solve(final double start, final double r1, final double r2, final double c) {
        double x = start;
        for (int i = 1; i <= 10; ++i) {
            double y1 = this.gamma * this.valueM1M2 / ((x + r2) * (x + r2));
            if (r2 + x > 0.0) {
                y1 = -y1;
            }
            double y2 = this.gamma * 100.0 / ((x - r1) * (x - r1));
            if (-r1 + x > 0.0) {
                y2 = -y2;
            }
            final double f = y1 + y2 + c * x;
            y1 = -2.0 * y1 / (x + r2);
            y2 = -2.0 * y2 / (x - r1);
            final double fs = y1 + y2 + c;
            x -= f / fs;
        }
        return x;
    }
    
    public void doData() {
        final double r = this.r1 + this.r2;
        this.data[0] = "x1      = " + Math.round(-100.0 * this.r2) / 100.0 + "\n";
        this.data[1] = "x2      = " + Math.round(100.0 * this.r1) / 100.0 + "\n";
        this.data[2] = "r=x2-x1 = " + Math.round(100.0 * r) / 100.0 + "\n";
        this.data[3] = "M1/M2   = " + Math.round(this.valueM1M2) / 100.0 + "\n";
        this.data[4] = "x(L1)   = " + Math.round(1000.0 * this.rL1) / 1000.0 + "\n";
        this.data[5] = "x(L2)   = " + Math.round(1000.0 * this.rL2) / 1000.0 + "\n";
        this.data[6] = "x(L3)   = " + Math.round(1000.0 * this.rL3) / 1000.0 + "\n";
        this.data[7] = "x(L1)/r = " + Math.round(1000.0 * this.rL1 / r) / 1000.0 + "\n";
        this.data[8] = "x(L2)/r = " + Math.round(1000.0 * this.rL2 / r) / 1000.0 + "\n";
        this.data[9] = "x(L3)/r = " + Math.round(1000.0 * this.rL3 / r) / 1000.0 + "\n";
        this.data[10] = "(x2-x(L1))/r = " + Math.round(1000.0 * (this.r1 - this.rL1) / r) / 1000.0 + "\n";
        this.data[11] = "(x(L2)-x2)/r = " + Math.round(1000.0 * (this.rL2 - this.r1) / r) / 1000.0 + "\n";
        this.data[12] = "(x(L3)-x1)/r = " + Math.round(1000.0 * (this.rL3 - this.r2) / r) / 1000.0 + "\n";
        this.nData = 13;
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("Helvetica", 0, 11));
        this.r1 = 0.01 * this.valueR * this.valueM1M2 / (1.0 + 0.01 * this.valueM1M2);
        final double RR1 = this.r1;
        final int R1 = (int)Math.round(RR1);
        final int rad1 = (int)Math.round(this.rho * Math.pow(this.valueM1M2 / 100.0, 0.3333333333333333));
        this.r2 = this.valueR / (1.0 + 0.01 * this.valueM1M2);
        final double RR2 = this.r2;
        final int R2 = (int)Math.round(RR2);
        final int rad2 = (int)Math.round(this.rho * Math.pow(100.0 / this.valueM1M2, 0.3333333333333333));
        if (!this.rotate) {
            g.setColor(Color.red);
            g.fillOval(this.x0 - R2 - rad1, this.y0 - rad1, 2 * rad1, 2 * rad1);
            g.drawString("M1", this.x0 - R2 - 6, this.y0 - rad1 - 10);
            g.drawString("x1=-" + Math.round(10.0 * this.r2) / 10.0, this.x0 - R2 - 12, this.oben + 10);
            g.setColor(Color.blue);
            g.drawLine(this.x0 - R2, this.y0 + rad1, this.x0 - R2, this.y0 - rad1);
            g.setColor(new Color(255, 150, 150));
            g.drawLine(this.x0 - R2, this.oben + 20, this.x0 - R2, this.y0 - rad1 - 30);
            g.drawLine(this.x0 - R2, this.y0 + rad1 + 10, this.x0 - R2, this.unten);
            g.setColor(Color.blue);
            g.fillOval(this.x0 + R1 - rad2, this.y0 - rad2, 2 * rad2, 2 * rad2);
            g.drawString("M2", this.x0 + R1 - 6, this.y0 - rad2 - 10);
            g.drawString("x2=" + Math.round(10.0 * this.r1) / 10.0, this.x0 + R1 - 12, this.oben + 10);
            g.setColor(Color.red);
            g.drawLine(this.x0 + R1, this.y0 + rad2, this.x0 + R1, this.y0 - rad2);
            g.setColor(new Color(150, 150, 255));
            g.drawLine(this.x0 + R1, this.oben + 20, this.x0 + R1, this.y0 - rad2 - 30);
            g.drawLine(this.x0 + R1, this.y0 + rad2 + 10, this.x0 + R1, this.unten);
        }
        for (int i = -250; i <= 250; ++i) {
            final int k = i + 250;
            this.Y1[k] = this.gamma * this.valueM1M2 / ((RR2 + i) * (RR2 + i));
            if (RR2 + i > 0.0) {
                this.Y1[k] = -this.Y1[k];
            }
            this.y1[k] = (int)Math.round(this.Y1[k]);
            this.Y2[k] = this.gamma * 100.0 / ((-RR1 + i) * (-RR1 + i));
            if (-RR1 + i > 0.0) {
                this.Y2[k] = -this.Y2[k];
            }
            this.y2[k] = (int)Math.round(this.Y2[k]);
        }
        if (this.fieldM1) {
            g.setColor(Color.red);
            for (int j = -250; j < 250; ++j) {
                final int k = j + 250;
                if (Math.abs(this.y1[k]) < 500 && Math.abs(this.y1[k + 1]) < 500) {
                    g.drawLine(this.x0 + j, this.y0 - this.y1[k], this.x0 + j + 1, this.y0 - this.y1[k + 1]);
                }
            }
        }
        if (this.fieldM2) {
            g.setColor(Color.blue);
            for (int j = -250; j < 250; ++j) {
                final int k = j + 250;
                if (Math.abs(this.y2[k]) < 500 && Math.abs(this.y2[k + 1]) < 500) {
                    g.drawLine(this.x0 + j, this.y0 - this.y2[k], this.x0 + j + 1, this.y0 - this.y2[k + 1]);
                }
            }
        }
        for (int j = -250; j <= 250; ++j) {
            final int k = j + 250;
            this.Y3[k] = this.Y1[k] + this.Y2[k];
            this.y3[k] = (int)Math.round(this.Y3[k]);
        }
        if (this.fieldSum) {
            g.setColor(Color.green);
            for (int l = -250; l < 250; ++l) {
                final int k = l + 250;
                if (Math.abs(this.y3[k]) < 500 && Math.abs(this.y3[k + 1]) < 500) {
                    g.drawLine(this.x0 + l, this.y0 - this.y3[k], this.x0 + l + 1, this.y0 - this.y3[k + 1]);
                }
            }
        }
        double OMEGA = 0.0;
        g.setColor(Color.magenta);
        final double YY1 = this.gamma * this.valueM1M2 / (this.valueR * this.valueR);
        final double YY2 = this.gamma * 100.0 / (this.valueR * this.valueR);
        OMEGA = (YY1 + YY2) / this.valueR;
        this.omegaLabel.setText(" Ang. Frequ. = " + Math.round(1000.0 * Math.sqrt(OMEGA)) / 1000.0);
        final int yy1 = (int)Math.round(OMEGA * 250.0);
        final int yy2 = (int)Math.round(YY2);
        if (this.showLagrange) {
            g.drawLine(this.x0 - 250, this.y0 - yy1, this.x0 + 250, this.y0 + yy1);
            g.drawLine(this.x0, this.y0, this.x0 - R2, this.y0 - yy2);
            for (int m = -250; m <= 250; ++m) {
                final int k = m + 250;
                this.y[k] = m * OMEGA;
            }
            String strL = "";
            final int n = 0;
            for (int i2 = -250; i2 < 250; ++i2) {
                final int k = 250 + i2;
                if (-this.y[k] > this.Y3[k] && -this.y[k + 1] < this.Y3[k + 1]) {
                    final double L = this.solve(i2, this.r1, this.r2, OMEGA);
                    final int intL = (int)Math.round(L);
                    strL = String.valueOf(Math.round(10.0 * L) / 10.0);
                    g.setColor(Color.magenta);
                    final int yy3 = (int)Math.round(-this.y[k]);
                    g.drawLine(this.x0 + intL, this.y0, this.x0 + intL, this.y0 - yy3);
                    g.fillOval(this.x0 + intL - 2, this.y0 - 2, 4, 4);
                    g.fillOval(this.x0 + intL - 1, this.y0 - yy3 - 1, 2, 2);
                    if (i2 < 0) {
                        g.drawString("L3", this.x0 + intL - 6, this.y0 + 15);
                        g.drawString(strL, this.x0 + intL - 12, this.y0 + 25);
                        this.radL3 = Math.abs(intL);
                        this.rL3 = L;
                    }
                    if (i2 >= 0 && i2 < R1) {
                        g.drawString("L1", this.x0 + intL - 6, this.y0 - 5);
                        g.drawString(strL, this.x0 + intL - 12, this.y0 - 16);
                        this.radL1 = intL;
                        this.rL1 = L;
                    }
                    if (i2 > R1) {
                        g.drawString("L2", this.x0 + intL - 6, this.y0 - 5);
                        g.drawString(strL, this.x0 + intL - 15, this.y0 - 16);
                        this.radL2 = intL;
                        this.rL2 = L;
                    }
                }
            }
        }
        if (this.showOrbits) {
            g.setColor(Color.red);
            g.drawOval(this.x0 - R2, this.y0 - R2, 2 * R2, 2 * R2);
            g.setColor(Color.blue);
            g.drawOval(this.x0 - R1, this.y0 - R1, 2 * R1, 2 * R1);
            g.setColor(Color.magenta);
            g.drawOval(this.x0 - this.radL1, this.y0 - this.radL1, 2 * this.radL1, 2 * this.radL1);
            g.drawOval(this.x0 - this.radL2, this.y0 - this.radL2, 2 * this.radL2, 2 * this.radL2);
            g.drawOval(this.x0 - this.radL3, this.y0 - this.radL3, 2 * this.radL3, 2 * this.radL3);
            g.drawOval(this.x0 - this.radL4L5, this.y0 - this.radL4L5, 2 * this.radL4L5, 2 * this.radL4L5);
        }
        final double a = RR1 + RR2;
        final int m2 = (int)Math.round(this.x0 - RR2 + 0.5 * a);
        final double H = 0.5 * a * Math.pow(3.0, 0.3333333333333333);
        this.radL4L5 = (int)Math.round(Math.sqrt(H * H + (-RR2 + 0.5 * a) * (-RR2 + 0.5 * a)));
        this.winkel = Math.atan2(H, m2 - this.x0) / this.K;
        if (this.L4L5) {
            final int hoehe = (int)Math.round(H);
            g.setColor(Color.magenta);
            g.drawLine(m2, this.y0 - 3, m2, this.y0 + 3);
            g.fillOval(m2 - 5, this.y0 - hoehe - 5, 10, 10);
            g.fillOval(m2 - 5, this.y0 + hoehe - 5, 10, 10);
            g.drawString("L4", m2 - 5, this.y0 - hoehe - 7);
            g.drawString("L5", m2 - 5, this.y0 + hoehe + 17);
            if (this.showOrbits) {
                g.drawOval(this.x0 - this.radL4L5, this.y0 - this.radL4L5, 2 * this.radL4L5, 2 * this.radL4L5);
            }
        }
        if (this.rotate) {
            g.setColor(Color.red);
            g.fillOval(this.x0 - this.xr1 - rad1, this.y0 + this.yr1 - rad1, 2 * rad1, 2 * rad1);
            g.setColor(Color.blue);
            g.fillOval(this.x0 + this.xr2 - rad2, this.y0 - this.yr2 - rad2, 2 * rad2, 2 * rad2);
            g.setColor(Color.magenta);
            g.fillOval(this.x0 + this.xL1 - 5, this.y0 - this.yL1 - 5, 10, 10);
            g.fillOval(this.x0 + this.xL2 - 5, this.y0 - this.yL2 - 5, 10, 10);
            g.fillOval(this.x0 - this.xL3 - 5, this.y0 + this.yL3 - 5, 10, 10);
            g.fillOval(this.x0 - this.xL4 - 5, this.y0 + this.yL4 - 5, 10, 10);
            g.fillOval(this.x0 + this.xL5 - 5, this.y0 - this.yL5 - 5, 10, 10);
        }
        g.setColor(Color.black);
        g.drawLine(this.x0, this.oben, this.x0, this.unten);
        g.drawLine(1, this.y0, this.size().width - 1, this.y0);
        for (int i3 = -5; i3 <= 5; ++i3) {
            if (i3 % 2 == 0) {
                g.drawLine(this.x0 + i3 * 50, this.y0 - 3, this.x0 + i3 * 50, this.y0 + 3);
            }
            else {
                g.drawLine(this.x0 + i3 * 50, this.y0, this.x0 + i3 * 50, this.y0 + 3);
            }
        }
        g.setColor(Color.green);
        if (this.fieldSum) {
            g.drawString("F", this.x0 + 5, this.oben + 15);
        }
        g.setColor(Color.red);
        if (this.fieldM1) {
            g.drawString("F1", this.x0 + 5, this.oben + 27);
        }
        g.setColor(Color.blue);
        if (this.fieldM2) {
            g.drawString("F2", this.x0 + 5, this.oben + 39);
        }
        final int h = this.size().height - this.oben;
        g.clearRect(0, this.oben, 15, h);
        g.clearRect(this.size().width - 15, this.oben, 14, h);
        g.clearRect(2, this.size().height - 15, this.size().width - 2, 14);
        g.clearRect(2, 2, this.size().width - 2, this.oben);
        g.drawString("x", this.x0 + 250, this.y0 - 5);
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString(String.valueOf(this.versStr) + " -  (c) 2006 J. Giesen - www.GeoAstro.de", 150, this.size().height - 5);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 96));
            g.setColor(Color.red);
            g.drawString("D E M O", 100, 300);
        }
    }
    
    public lagrange09() {
        this.valueR = 150;
        this.valueM1M2 = 200;
        this.data = new String[20];
        this.nData = 0;
        this.demo = true;
        this.showLagrange = true;
        this.showOrbits = false;
        this.y1 = new int[502];
        this.y2 = new int[502];
        this.y3 = new int[502];
        this.Y = new double[502];
        this.y = new double[502];
        this.Y1 = new double[502];
        this.Y2 = new double[502];
        this.Y3 = new double[502];
        this.oben = 90;
        this.fieldM1 = true;
        this.fieldM2 = true;
        this.fieldSum = true;
        this.L4L5 = false;
        this.rotate = false;
        this.K = 0.017453292519943295;
        this.nWinkel = 0;
        this.radL1 = 0;
        this.radL2 = 0;
        this.radL3 = 0;
        this.radL4L5 = 0;
        this.gamma = 3000.0;
        this.rho = 15;
    }
}
