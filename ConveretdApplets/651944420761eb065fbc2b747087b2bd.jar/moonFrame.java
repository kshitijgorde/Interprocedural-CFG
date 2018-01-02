import java.awt.Event;
import java.awt.Font;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class moonFrame extends Frame
{
    static final double K = 0.017453292519943295;
    compute comp;
    boolean online;
    double u1;
    double u2;
    double u3;
    double u4;
    double psi;
    double r1;
    double r2;
    double r3;
    double r4;
    int RR;
    Button pButton;
    Button mButton;
    int[] x_1;
    int[] x_2;
    int[] x_3;
    int[] x_4;
    double[] Jd;
    int year;
    int month;
    int date;
    int R1;
    int R2;
    int R3;
    int R4;
    double UT;
    Label L;
    boolean demo;
    
    public moonFrame(final int Year, final int Month, final int Date, final double U_T, final boolean dem) {
        this.RR = 6;
        this.x_1 = new int[35];
        this.x_2 = new int[35];
        this.x_3 = new int[35];
        this.x_4 = new int[35];
        this.Jd = new double[35];
        this.R1 = 4;
        this.R2 = 3;
        this.R3 = 5;
        this.R4 = 5;
        this.setTitle(String.valueOf(Year));
        this.setBackground(Color.white);
        this.comp = new compute();
        this.year = Year;
        this.month = Month;
        this.date = Date;
        this.UT = (int)U_T;
        this.demo = dem;
        this.setBackground(Color.white);
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 0, 0);
        gbl.setConstraints(this.mButton = new Button("-1 h"), gbc);
        this.add(this.mButton);
        (this.L = new Label()).setText("  " + this.comp.makeTimeString(this.UT) + "  ");
        gbl.setConstraints(this.L, gbc);
        this.add(this.L);
        gbl.setConstraints(this.pButton = new Button("+1 h"), gbc);
        this.add(this.pButton);
        gbc.gridy = 1;
        gbc.weighty = 160.0;
        final Canvas mC = new Canvas();
        gbl.setConstraints(mC, gbc);
        this.add(mC);
        this.calculate(this.UT);
        this.repaint();
    }
    
    public void calculate(final double ut) {
        final double JDE;
        final double JD = JDE = this.comp.JD(this.date, this.month, this.year, ut);
        final double d0 = JDE - 2451545.0;
        for (int i = 0; i <= 31; ++i) {
            final double d2 = d0 - 15.0 + i;
            this.Jd[i] = JD - 15.0 + i;
            double V = 172.74 + 0.00111588 * d2;
            V = this.comp.check360(V);
            double M = 357.529 + 0.9856003 * d2;
            M = this.comp.check360(M);
            double N = 20.02 + 0.0830853 * d2 + 0.329 * Math.sin(0.017453292519943295 * V);
            N = this.comp.check360(N);
            double J = 66.115 + 0.9025179 * d2 - 0.329 * Math.sin(0.017453292519943295 * V);
            J = this.comp.check360(J);
            final double A = 1.915 * Math.sin(0.017453292519943295 * M) + 0.02 * Math.sin(0.03490658503988659 * M);
            final double B = 5.555 * Math.sin(0.017453292519943295 * N) + 0.168 * Math.sin(0.03490658503988659 * N);
            final double k = J + A - B;
            final double R = 1.00014 - 0.01671 * Math.cos(0.017453292519943295 * M) - 1.4E-4 * Math.cos(0.03490658503988659 * M);
            final double r = 5.20872 - 0.25208 * Math.cos(0.017453292519943295 * N) - 0.00611 * Math.cos(0.03490658503988659 * N);
            final double dEJ = Math.sqrt(r * r + R * R - 2.0 * r * R * Math.cos(0.017453292519943295 * k));
            this.psi = Math.asin(R * Math.sin(0.017453292519943295 * k) / dEJ) / 0.017453292519943295;
            double omega1 = 210.98 + 877.8169088 * (d2 - dEJ / 173.0) + this.psi - B;
            double omega2 = 187.23 + 870.1869088 * (d2 - dEJ / 173.0) + this.psi - B;
            omega1 = this.comp.check360(omega1);
            omega2 = this.comp.check360(omega2);
            double lambda = 34.35 + 0.083091 * d2 + 0.329 * Math.sin(0.017453292519943295 * V) + B;
            lambda = this.comp.check360(lambda);
            final double Ds = 3.12 * Math.sin(0.017453292519943295 * (lambda + 42.8));
            final double De = Ds - 2.22 * Math.sin(0.017453292519943295 * this.psi) * Math.cos(0.017453292519943295 * (lambda + 22.0)) - 1.3 * (r - dEJ) * Math.sin(0.017453292519943295 * (lambda - 100.5)) / dEJ;
            this.u1 = 163.8067 + 203.4058643 * (d2 - dEJ / 173.0) + this.psi - B;
            this.u2 = 358.4108 + 101.2916334 * (d2 - dEJ / 173.0) + this.psi - B;
            this.u3 = 5.7129 + 50.2345179 * (d2 - dEJ / 173.0) + this.psi - B;
            this.u4 = 224.8151 + 21.4879801 * (d2 - dEJ / 173.0) + this.psi - B;
            final double G = 331.18 + 50.310482 * (d2 - dEJ / 173.0);
            final double H = 87.4 + 21.569231 * (d2 - dEJ / 173.0);
            double U1 = this.u1 + 0.473 * Math.sin(0.03490658503988659 * (this.u1 - this.u2));
            double U2 = this.u2 + 1.065 * Math.sin(0.03490658503988659 * (this.u2 - this.u3));
            double U3 = this.u3 + 0.165 * Math.sin(0.017453292519943295 * G);
            double U4 = this.u4 + 0.841 * Math.sin(0.017453292519943295 * H);
            U1 = this.comp.check360(U1);
            U2 = this.comp.check360(U2);
            U3 = this.comp.check360(U3);
            U4 = this.comp.check360(U4);
            this.u1 = this.comp.check360(this.u1);
            this.u2 = this.comp.check360(this.u2);
            this.u3 = this.comp.check360(this.u3);
            this.u4 = this.comp.check360(this.u4);
            this.r1 = 5.9073 - 0.0244 * Math.cos(0.03490658503988659 * (this.u1 - this.u2));
            this.r2 = 9.3991 - 0.0882 * Math.cos(0.03490658503988659 * (this.u2 - this.u3));
            this.r3 = 14.9924 - 0.0216 * Math.cos(0.017453292519943295 * G);
            this.r4 = 26.3699 - 0.1935 * Math.cos(0.017453292519943295 * H);
            this.x_1[i] = (int)Math.round(this.RR * this.r1 * Math.sin(0.017453292519943295 * U1));
            this.x_2[i] = (int)Math.round(this.RR * this.r2 * Math.sin(0.017453292519943295 * U2));
            this.x_3[i] = (int)Math.round(this.RR * this.r3 * Math.sin(0.017453292519943295 * U3));
            this.x_4[i] = (int)Math.round(this.RR * this.r4 * Math.sin(0.017453292519943295 * U4));
        }
    }
    
    public void paint(final Graphics g) {
        final RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHints(renderHints);
        final int xM = 210;
        final int yM = 230;
        final int oben = 70;
        final int dy = 16;
        g.setColor(Color.white);
        g.fillRect(0, 0, 500, 550);
        g.setFont(new Font("Helvetica", 0, 11));
        g.setColor(Color.black);
        g.drawLine(xM, oben, xM, oben + dy * 31);
        g.setColor(Color.lightGray);
        for (int i = 0; i <= 31; ++i) {
            final int D = this.comp.calDat(1, this.Jd[i]);
            if (D == this.date) {
                g.setColor(Color.red);
                g.drawString(this.comp.calDat(2, this.Jd[i]) + 1 + "/" + D, 10, oben + dy * i + 5);
                g.drawLine(50, oben + dy * i, 380, oben + dy * i);
            }
            else {
                g.setColor(Color.black);
                g.drawString(this.comp.calDat(2, this.Jd[i]) + 1 + "/" + D, 10, oben + dy * i + 5);
                g.setColor(Color.lightGray);
                g.drawLine(50, oben + dy * i, 380, oben + dy * i);
            }
        }
        g.setColor(Color.red);
        for (int j = 0; j <= 31; ++j) {
            g.drawOval(xM + this.x_1[j] - this.R1, oben + dy * j - this.R1, 2 * this.R1, 2 * this.R1);
        }
        g.setColor(Color.green);
        for (int k = 0; k <= 31; ++k) {
            g.drawOval(xM + this.x_2[k] - this.R2, oben + dy * k - this.R2, 2 * this.R2, 2 * this.R2);
        }
        g.setColor(Color.blue);
        for (int l = 0; l <= 31; ++l) {
            g.drawOval(xM + this.x_3[l] - this.R3, oben + dy * l - this.R3, 2 * this.R3, 2 * this.R3);
        }
        g.setColor(Color.magenta);
        for (int m = 0; m <= 31; ++m) {
            g.drawOval(xM + this.x_4[m] - this.R4, oben + dy * m - this.R4, 2 * this.R4, 2 * this.R4);
        }
        if (this.online) {
            g.setFont(new Font("Helvetiva", 0, 36));
            g.drawString("online", xM - 50, yM);
        }
        g.setColor(Color.black);
        g.drawString("(c) 2010 J. Giesen  -  www.GeoAstro.de", 50, this.size().height - 10);
        if (this.demo) {
            g.setColor(Color.red);
            g.setFont(new Font("Helvetica", 0, 96));
            g.drawString("D E M O", 20, 280);
        }
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public boolean action(final Event evt, final Object whichAction) {
        if (evt.target instanceof Button) {
            if (evt.target == this.pButton) {
                ++this.UT;
                this.L.setText("  " + this.comp.makeTimeString(this.UT % 24.0) + "  ");
                this.calculate(this.UT);
                this.repaint();
            }
            if (evt.target == this.mButton) {
                --this.UT;
                double u = this.UT % 24.0;
                if (u < 0.0) {
                    u += 24.0;
                }
                this.L.setText("  " + this.comp.makeTimeString(u) + "  ");
                this.calculate(this.UT);
                this.repaint();
            }
        }
        return true;
    }
}
