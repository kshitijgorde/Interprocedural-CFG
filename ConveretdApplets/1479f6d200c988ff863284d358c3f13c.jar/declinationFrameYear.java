import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class declinationFrameYear extends Frame
{
    boolean isOnline;
    double[][] d;
    int nData;
    int year;
    String[] monthArray;
    char deg;
    Button button;
    String[] data;
    int n_Data;
    
    public declinationFrameYear(final String vStr, final int YEAR, final int n, final double[][] D, final boolean online) {
        this.d = new double[368][2];
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.deg = '°';
        this.data = new String[32];
        this.n_Data = 0;
        this.setTitle(vStr);
        this.isOnline = online;
        this.nData = n;
        this.year = YEAR;
        this.setBackground(Color.white);
        this.button = new Button("Table");
        this.setLayout(new FlowLayout());
        this.add(this.button);
        for (int i = 1; i <= n; ++i) {
            this.d[i][0] = D[i][0];
            this.d[i][1] = D[i][1];
        }
        this.repaint();
    }
    
    public boolean action(final Event evt, final Object whichAction) {
        if (evt.target instanceof Button && evt.target == this.button) {
            final scrollFrame sf = new scrollFrame("Extreme Declinations " + this.year, this.n_Data, this.data, false);
            sf.resize(300, 220);
            sf.show();
        }
        return true;
    }
    
    public int daysInMonth(final int m, int y) {
        int n = 31;
        y += 1900;
        if (m == 0 || m == 2 || m == 4 || m == 6 || m == 7 || m == 9 || m == 11) {
            n = 31;
        }
        else if (m == 3 || m == 5 || m == 8 || m == 10) {
            n = 30;
        }
        else if (m == 1) {
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
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public double scheitel(final double yMinus, final double Y0, final double yPlus) {
        final double A = 0.5 * (yMinus + yPlus) - Y0;
        final double B = 0.5 * (yPlus - yMinus);
        final double XE = -B / (2.0 * A);
        final double YE = (A * XE + B) * XE + Y0;
        return YE;
    }
    
    public void paint(final Graphics g) {
        final int x0 = 50;
        final int y0 = 190;
        final int dx = 2;
        final int dy = 20;
        g.setFont(new Font("Courier", 0, 10));
        g.drawRect(x0, y0 - 120, 366 * dx, 2 * dy * 6);
        for (int i = -5; i <= 5; ++i) {
            if (i % 2 == 0) {
                g.setColor(Color.black);
            }
            else {
                g.setColor(Color.gray);
            }
            g.drawLine(x0, y0 + i * dy, x0 + 366 * dx, y0 + i * dy);
        }
        g.setColor(Color.black);
        for (int j = -3; j <= 3; ++j) {
            g.drawString(new StringBuffer().append(-j * 10).append(this.deg).toString(), 20, y0 + j * 2 * dy + 5);
        }
        final int r = 2;
        double maxPos = 0.0;
        double minPos = 50.0;
        this.n_Data = 0;
        for (int k = 1; k <= this.nData; ++k) {
            if (k > 1 && k < this.nData && this.d[k - 1][0] < this.d[k][0] && this.d[k + 1][0] < this.d[k][0]) {
                final double dMaxPos = this.scheitel(this.d[k - 1][0], this.d[k][0], this.d[k + 1][0]);
                ++this.n_Data;
                this.data[this.n_Data] = Math.round(100.0 * dMaxPos) / 100.0 + "\n";
                if (dMaxPos > maxPos) {
                    maxPos = dMaxPos;
                }
                if (dMaxPos < minPos) {
                    minPos = dMaxPos;
                }
            }
        }
        double minNeg = 0.0;
        ++this.n_Data;
        this.data[this.n_Data] = "Max. " + Math.round(100.0 * maxPos) / 100.0 + "\n" + "\n";
        for (int l = 1; l <= this.nData; ++l) {
            if (l > 1 && l < this.nData && this.d[l - 1][0] > this.d[l][0] && this.d[l + 1][0] > this.d[l][0]) {
                final double dMin = this.scheitel(this.d[l - 1][0], this.d[l][0], this.d[l + 1][0]);
                ++this.n_Data;
                this.data[this.n_Data] = Math.round(100.0 * dMin) / 100.0 + "\n";
                if (dMin < minNeg) {
                    minNeg = dMin;
                }
            }
        }
        ++this.n_Data;
        this.data[this.n_Data] = "Min. " + Math.round(100.0 * minNeg) / 100.0;
        g.setColor(Color.red);
        final int Y0 = y0 + 320;
        g.drawLine(x0, Y0, x0 + dx * 366, Y0);
        g.drawString(new StringBuffer().append(Math.round(100.0 * minPos) / 100.0).append(this.deg).toString(), x0 - 40, Y0 + 5);
        int Y2 = (int)Math.round((maxPos - minPos) * 100.0);
        g.drawLine(x0, Y0 - Y2, x0 + dx * 366, Y0 - Y2);
        g.drawString(new StringBuffer().append(Math.round(100.0 * maxPos) / 100.0).append(this.deg).toString(), x0 - 40, Y0 - Y2 + 5);
        g.drawRect(x0, Y0 - Y2, 366 * dx, Y2);
        Y2 = (int)Math.round((maxPos - minPos) * 50.0);
        g.drawLine(x0, Y0 - Y2, x0 + dx * 366, Y0 - Y2);
        g.drawString(new StringBuffer().append(Math.round(50.0 * (maxPos + minPos)) / 100.0).append(this.deg).toString(), x0 - 40, Y0 - Y2 + 5);
        for (int m = 1; m <= this.nData; ++m) {
            if (m > 1 && m < this.nData && this.d[m][0] > 0.0 && this.d[m - 1][0] < this.d[m][0] && this.d[m + 1][0] < this.d[m][0]) {
                final double dMaxPos = this.scheitel(this.d[m - 1][0], this.d[m][0], this.d[m + 1][0]);
                Y2 = (int)Math.round((dMaxPos - minPos) * 100.0);
                final int c = (int)Math.round(255.0 * this.d[m][1]);
                g.setColor(new Color(c, c, c));
                g.fillOval(x0 + (m - 1) * dx - 2 * r, Y0 - Y2 - 2 * r, 4 * r, 4 * r);
                g.setColor(Color.black);
                g.drawOval(x0 + (m - 1) * dx - 2 * r, Y0 - Y2 - 2 * r, 4 * r, 4 * r);
            }
        }
        int N = 32;
        int M = 0;
        final int N2 = 1;
        for (int i2 = 1; i2 <= this.nData; ++i2) {
            Y2 = (int)Math.round(this.d[i2][0] * 4.0);
            final int c = (int)Math.round(255.0 * this.d[i2][1]);
            g.setColor(new Color(c, c, c));
            g.fillOval(x0 + (i2 - 1) * dx - r, y0 - Y2 - r, 2 * r, 2 * r);
            if (this.d[i2][1] > 0.995) {
                g.setColor(Color.red);
            }
            else {
                g.setColor(Color.black);
            }
            g.drawOval(x0 + (i2 - 1) * dx - r, y0 - Y2 - r, 2 * r, 2 * r);
            if (i2 <= 30) {
                for (int n = 1; n <= 30; ++n) {
                    if (i2 % 5 == 0) {
                        if (i2 % 10 == 0) {
                            g.setColor(Color.darkGray);
                        }
                        else {
                            g.setColor(Color.gray);
                        }
                        g.drawLine(x0 + (i2 - 1) * dx, y0 - 120, x0 + (i2 - 1) * dx, y0 + 120);
                        g.drawLine(x0 + (i2 - 1) * dx, Y0, x0 + (i2 - 1) * dx, Y0 - (int)Math.round(100.0 * (maxPos - minPos)));
                    }
                }
            }
            g.drawString(this.monthArray[0], x0 + 20, y0 + 140);
            if (i2 == N) {
                ++M;
                g.setColor(Color.black);
                g.drawString(this.monthArray[M], x0 + i2 * dx + 20, y0 + 140);
                N += this.daysInMonth(M, this.year - 1900);
                g.setColor(Color.red);
                g.drawLine(x0 + (i2 - 1) * dx, y0 - 120, x0 + (i2 - 1) * dx, y0 + 120);
                g.drawLine(x0 + (i2 - 1) * dx, Y0, x0 + (i2 - 1) * dx, Y0 - (int)Math.round(100.0 * (maxPos - minPos)));
                for (int n = 1; n <= this.daysInMonth(M, this.year - 1900); ++n) {
                    if (n % 5 == 0) {
                        g.setColor(Color.gray);
                        if (n % 10 == 0) {
                            g.setColor(Color.darkGray);
                        }
                        g.drawLine(x0 + (i2 - 2 + n) * dx, y0 - 120, x0 + (i2 - 2 + n) * dx, y0 + 120);
                        g.drawLine(x0 + (i2 - 2 + n) * dx, Y0, x0 + (i2 - 2 + n) * dx, Y0 - (int)Math.round(100.0 * (maxPos - minPos)));
                    }
                }
            }
        }
        g.drawString("© 2004-2009 J. Giesen  - www.GeoAstro.de", x0, 45);
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 48));
            g.setColor(Color.red);
            g.drawString("D E M O", 150, 150);
        }
    }
}
