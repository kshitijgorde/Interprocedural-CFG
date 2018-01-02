import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class semidurationFrame extends Frame
{
    final double K = 0.017453292519943295;
    final char deg = '°';
    compute comp;
    double[][] Lat;
    int N;
    int YEAR;
    int dL;
    int Dx;
    int dx;
    int startMonth;
    int stopMonth;
    String[] monthArray;
    double startLat;
    int DY;
    boolean ONLINE;
    
    public semidurationFrame(final String titleStr, final int year, final boolean online) {
        this.Lat = new double[15][370];
        this.dL = 12;
        this.Dx = 0;
        this.dx = 3;
        this.startMonth = 2;
        this.stopMonth = 8;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.startLat = 45.0;
        this.DY = (int)(90.0 - this.startLat) * this.dL;
        this.comp = new compute();
        this.ONLINE = online;
        this.setTitle(titleStr);
        final double cosZ = Math.cos(1.5853401554517652);
        final double dT = 0.05;
        for (int t = 7; t <= 11; ++t) {
            this.N = 0;
            for (int m = this.startMonth; m <= this.stopMonth; ++m) {
                for (int d = 1; d <= this.comp.daysInMonth(m, year); ++d) {
                    ++this.N;
                    final double delta = this.comp.sunDecRA(1, this.comp.JD(d, m + 1, year + 1900, 12.0));
                    final double sinDelta = Math.sin(0.017453292519943295 * delta);
                    final double cosDelta = Math.cos(0.017453292519943295 * delta);
                    for (int i = 0; i < 45000; ++i) {
                        final double L = this.startLat + 0.001 * i;
                        final double T = 0.13333333333333333 * Math.acos((cosZ - Math.sin(0.017453292519943295 * L) * sinDelta) / (Math.cos(0.017453292519943295 * L) * cosDelta)) / 0.017453292519943295;
                        if (Math.abs(T - 2 * t) < dT) {
                            this.Lat[t][this.N] = this.dL * (L - this.startLat);
                            break;
                        }
                    }
                }
            }
        }
        this.N = 0;
        for (int m = this.startMonth; m <= this.stopMonth; ++m) {
            for (int d = 1; d <= this.comp.daysInMonth(m, year); ++d) {
                ++this.N;
                final double delta = this.comp.sunDecRA(1, this.comp.JD(d, m + 1, year + 1900, 12.0));
                final double sinDelta = Math.sin(0.017453292519943295 * delta);
                final double cosDelta = Math.cos(0.017453292519943295 * delta);
                for (int i = 0; i < 450000; ++i) {
                    final double L = this.startLat + 1.0E-4 * i;
                    final double T = 0.13333333333333333 * Math.acos((cosZ - Math.sin(0.017453292519943295 * L) * sinDelta) / (Math.cos(0.017453292519943295 * L) * cosDelta)) / 0.017453292519943295;
                    if (T > 24.0 - dT) {
                        this.Lat[12][this.N] = this.dL * (L - this.startLat);
                        break;
                    }
                }
            }
        }
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public void paint(final Graphics g) {
        final int y0 = 580;
        final int x0 = 50;
        g.setColor(Color.white);
        g.fillRect(0, 0, 1500, 800);
        this.Dx = this.N * this.dx;
        g.setFont(new Font("Helvetica", 0, 12));
        g.setColor(Color.lightGray);
        for (int i = 0; i <= 45; ++i) {
            g.drawLine(x0, y0 - i * this.dL, x0 + this.Dx, y0 - i * this.dL);
        }
        g.setColor(Color.black);
        for (int j = 0; j <= 9; ++j) {
            g.drawLine(x0, y0 - j * 5 * this.dL, x0 + this.Dx, y0 - j * 5 * this.dL);
            g.drawString(new StringBuffer().append((int)(this.startLat + j * 5)).append('°').toString(), 20, y0 - j * 5 * this.dL + 5);
        }
        g.setColor(Color.gray);
        int D = 0;
        for (int m = this.startMonth; m <= this.stopMonth; ++m) {
            g.setColor(Color.black);
            g.drawLine(x0 + this.dx * D, y0, x0 + this.dx * D, y0 - this.DY);
            g.setColor(Color.lightGray);
            g.drawLine(x0 + this.dx * (D + 5), y0, x0 + this.dx * (D + 5), y0 - this.DY);
            g.setColor(Color.gray);
            g.drawLine(x0 + this.dx * (D + 10), y0, x0 + this.dx * (D + 10), y0 - this.DY);
            g.setColor(Color.black);
            g.drawString(this.monthArray[m], x0 + this.dx * (D + 10) + 7, y0 + 20);
            g.setColor(Color.lightGray);
            g.drawLine(x0 + this.dx * (D + 15), y0, x0 + this.dx * (D + 15), y0 - this.DY);
            g.setColor(Color.gray);
            g.drawLine(x0 + this.dx * (D + 20), y0, x0 + this.dx * (D + 20), y0 - this.DY);
            g.setColor(Color.lightGray);
            g.drawLine(x0 + this.dx * (D + 25), y0, x0 + this.dx * (D + 25), y0 - this.DY);
            D += this.comp.daysInMonth(m, this.YEAR + 1900);
            g.setColor(Color.black);
            g.drawLine(x0 + this.dx * D, y0, x0 + this.dx * D, y0 - this.DY);
        }
        g.setColor(Color.blue);
        for (int k = 18; k < this.N - 8; ++k) {
            g.drawLine(x0 + this.dx * k, y0 - (int)Math.round(this.Lat[7][k]), x0 + this.dx * (k + 1), y0 - (int)Math.round(this.Lat[7][k + 1]));
            if (k == 60) {
                g.drawString("14h", x0 + this.dx * k - 14, y0 - 10);
            }
        }
        g.setColor(Color.red);
        for (int l = 18; l < this.N - 8; ++l) {
            g.drawLine(x0 + this.dx * l, y0 - (int)Math.round(this.Lat[8][l]), x0 + this.dx * (l + 1), y0 - (int)Math.round(this.Lat[8][l + 1]));
            if (l == 104) {
                g.drawString("16h", x0 + this.dx * l, y0 - 10);
            }
        }
        g.drawString("SUN ABOVE HORIZON", x0 + 85 * this.dx, y0 - 380);
        g.setColor(Color.magenta);
        for (int i2 = 18; i2 < this.N - 8; ++i2) {
            g.drawLine(x0 + this.dx * i2, y0 - (int)Math.round(this.Lat[9][i2]), x0 + this.dx * (i2 + 1), y0 - (int)Math.round(this.Lat[9][i2 + 1]));
            if (i2 == 104) {
                g.drawString("18h", x0 + this.dx * i2, y0 - 125);
            }
        }
        g.setColor(Color.green);
        for (int i3 = 18; i3 < this.N - 8; ++i3) {
            g.drawLine(x0 + this.dx * i3, y0 - (int)Math.round(this.Lat[10][i3]), x0 + this.dx * (i3 + 1), y0 - (int)Math.round(this.Lat[10][i3 + 1]));
            if (i3 == 104) {
                g.drawString("20h", x0 + this.dx * i3, y0 - 190);
            }
        }
        g.setColor(Color.blue);
        for (int i4 = 19; i4 < this.N - 8; ++i4) {
            g.drawLine(x0 + this.dx * i4, y0 - (int)Math.round(this.Lat[11][i4]), x0 + this.dx * (i4 + 1), y0 - (int)Math.round(this.Lat[11][i4 + 1]));
            if (i4 == 104) {
                g.drawString("22h", x0 + this.dx * i4, y0 - 225);
            }
        }
        g.setColor(Color.red);
        for (int i5 = 24; i5 < this.N - 15; ++i5) {
            g.drawLine(x0 + this.dx * i5, y0 - (int)Math.round(this.Lat[12][i5]), x0 + this.dx * (i5 + 1), y0 - (int)Math.round(this.Lat[12][i5 + 1]));
            if (i5 == 104) {
                g.drawString("24h", x0 + this.dx * i5, y0 - 260);
            }
        }
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString("www.GeoAstro.de", x0 + this.dx * D - 85, y0 - 10);
        g.setColor(Color.black);
        g.drawLine(x0, y0, x0 + this.Dx, y0);
        if (this.ONLINE) {
            final Font f = g.getFont();
            g.setColor(Color.red);
            g.setFont(new Font("Chicago", 0, 48));
            g.drawString("D E M O", 100, 280);
            g.setFont(f);
        }
    }
}
