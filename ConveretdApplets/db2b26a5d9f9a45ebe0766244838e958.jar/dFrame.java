import java.awt.Event;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class dFrame extends Frame
{
    double K;
    final char deg = '°';
    int[][][] ae_Data;
    String titleString;
    boolean demo;
    
    public dFrame(final String titleStr, final double[][][] aeData, final boolean isDemo) {
        this.K = 0.017453292519943295;
        this.ae_Data = new int[40][71][2];
        this.setTitle(titleStr);
        this.titleString = titleStr;
        this.demo = isDemo;
        for (int i = 1; i < 36; ++i) {
            for (int k = 0; k <= 66; ++k) {
                this.ae_Data[i][k][0] = (int)Math.round(2.0 * aeData[i][k][0]);
                this.ae_Data[i][k][1] = (int)Math.round(5.0 * aeData[i][k][1]);
            }
        }
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        final int y0 = 500;
        final int links = 40;
        final compute comp = new compute();
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 1000);
        final int dy = 50;
        final int X = 720;
        g.setColor(Color.darkGray);
        for (int a = 0; a <= 9; ++a) {
            g.drawLine(links, y0 - a * dy, links + X, y0 - a * dy);
        }
        final int dx = 60;
        final int Y = 450;
        for (int n = 0; n < 13; ++n) {
            g.drawLine(links + n * dx, y0, links + n * dx, y0 - Y);
        }
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 10));
        for (int i = 0; i <= 9; ++i) {
            g.drawString(new StringBuffer().append(i * 10).append('°').toString(), links - 25, y0 - i * dy + 2);
        }
        g.setFont(new Font("Helvetica", 0, 12));
        g.drawString("Elevation", links, 37);
        g.drawString("Azim.", links + X + 3, y0 + 4);
        g.setFont(new Font("Helvetica", 0, 10));
        for (int j = 0; j <= 12; ++j) {
            g.drawString(new StringBuffer().append(30 * j).append('°').toString(), links + j * 60 - 9, y0 + 15);
        }
        g.setColor(Color.red);
        g.drawLine(links + 360, y0, links + 360, y0 - 360);
        g.setColor(Color.black);
        for (int k = 1; k < 36; ++k) {
            for (int l = 0; l <= 66; ++l) {
                if (this.ae_Data[k][l][1] > 0) {
                    g.drawOval(links + this.ae_Data[k][l][0], y0 - this.ae_Data[k][l][1] - 1, 1, 1);
                }
            }
        }
        for (int m = 1; m < 36; ++m) {
            for (int k2 = 0; k2 <= 66; ++k2) {
                if (this.ae_Data[m][k2][1] > 0) {
                    g.drawOval(links + 720 - this.ae_Data[m][k2][0], y0 - this.ae_Data[m][k2][1] - 1, 1, 1);
                }
            }
        }
        g.setColor(Color.blue);
        int NS;
        int start;
        if (this.titleString.indexOf("N") != -1) {
            NS = 26;
            start = 0;
        }
        else {
            NS = 13;
            start = 1;
        }
        for (int i2 = start; i2 < 36; ++i2) {
            for (int k3 = 1; k3 <= 66; ++k3) {
                if (k3 == 33 && i2 % 2 == 0 && this.ae_Data[i2][k3][1] > 0) {
                    g.drawString(new StringBuffer().append(-90 + i2 * 5).append('°').toString(), links + 2 + this.ae_Data[i2][k3][0], y0 - this.ae_Data[i2][k3][1]);
                }
                if (i2 % 2 == 0 && Math.abs(this.ae_Data[i2][k3 - 1][0] - this.ae_Data[i2][k3][0]) < 200 && this.ae_Data[i2][k3 - 1][1] > 0) {
                    g.drawLine(links + this.ae_Data[i2][k3 - 1][0], y0 - this.ae_Data[i2][k3 - 1][1], links + this.ae_Data[i2][k3][0], y0 - this.ae_Data[i2][k3][1]);
                }
            }
        }
        for (int i3 = 1; i3 < 36; ++i3) {
            for (int k4 = 1; k4 <= 66; ++k4) {
                if (i3 % 2 == 0 && Math.abs(this.ae_Data[i3][k4 - 1][0] - this.ae_Data[i3][k4][0]) < 200 && this.ae_Data[i3][k4 - 1][1] > 0) {
                    g.drawLine(links + 720 - this.ae_Data[i3][k4 - 1][0], y0 - this.ae_Data[i3][k4 - 1][1], links + 720 - this.ae_Data[i3][k4][0], y0 - this.ae_Data[i3][k4][1]);
                }
            }
        }
        g.setColor(Color.red);
        for (int i4 = 2; i4 < 36; ++i4) {
            for (int k5 = 0; k5 <= 66; ++k5) {
                if (i4 == NS && k5 % 3 == 0 && k5 < 33 && this.ae_Data[i4][k5][1] > 0) {
                    g.drawString(-11 + k5 / 3 + "h", links + this.ae_Data[i4][k5][0] + 5, y0 - this.ae_Data[i4][k5][1]);
                }
                if (k5 % 3 == 0 && Math.abs(this.ae_Data[i4 - 1][k5][0] - this.ae_Data[i4][k5][0]) < 50 && this.ae_Data[i4 - 1][k5][1] > 0) {
                    g.drawLine(links + this.ae_Data[i4 - 1][k5][0], y0 - this.ae_Data[i4 - 1][k5][1], links + this.ae_Data[i4][k5][0], y0 - this.ae_Data[i4][k5][1]);
                }
            }
        }
        for (int i5 = 2; i5 < 36; ++i5) {
            for (int k6 = 0; k6 <= 66; ++k6) {
                if (i5 == NS && k6 % 3 == 0 && k6 < 33 && this.ae_Data[i5][k6][1] > 0) {
                    g.drawString(11 - k6 / 3 + "h", links + 720 - this.ae_Data[i5][k6][0] + 5, y0 - this.ae_Data[i5][k6][1]);
                }
                if (k6 % 3 == 0 && Math.abs(this.ae_Data[i5 - 1][k6][0] - this.ae_Data[i5][k6][0]) < 50 && this.ae_Data[i5 - 1][k6][1] > 0) {
                    g.drawLine(links + 720 - this.ae_Data[i5 - 1][k6][0], y0 - this.ae_Data[i5 - 1][k6][1], links + 720 - this.ae_Data[i5][k6][0], y0 - this.ae_Data[i5][k6][1]);
                }
            }
        }
        g.setColor(Color.black);
        g.drawRect(links, y0 - Y, X, Y);
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 100));
            g.setColor(Color.red);
            g.drawString("D E M O", 200, 300);
        }
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
}
