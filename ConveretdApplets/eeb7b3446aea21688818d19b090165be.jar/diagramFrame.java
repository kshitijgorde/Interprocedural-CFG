import java.awt.Event;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class diagramFrame extends Frame
{
    boolean isOnline;
    int[] d;
    int nData;
    int year;
    String[] monthArray;
    String minDistStr;
    String maxDistStr;
    
    public diagramFrame(final String vStr, final int y, final String str1, final String str2, final int n, final int[] D, final boolean online) {
        this.d = new int[370];
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.setTitle(vStr);
        this.isOnline = online;
        this.nData = n;
        this.year = y;
        this.setLocation(250, 200);
        this.minDistStr = str1;
        this.maxDistStr = str2;
        for (int i = 0; i <= n; ++i) {
            this.d[i] = D[i];
        }
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        final int x0 = 60;
        final int y0 = 350;
        int X = 0;
        final double dx = 1.7;
        final int dy = 50;
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 500);
        g.setFont(new Font("Courier", 0, 10));
        g.setColor(Color.black);
        for (int i = 0; i <= 6; ++i) {
            g.drawString(350 + i * 10 + ",000", 10, y0 - i * dy + 5);
        }
        int sum = 0;
        for (int m = 0; m < 12; ++m) {
            g.setColor(Color.gray);
            for (int j = 1; j <= 2; ++j) {
                X += (int)Math.round(dx * 10.0);
                g.drawLine(x0 + X, y0, x0 + X, y0 - 300);
            }
            g.setColor(Color.black);
            sum += this.daysInMonth(m, this.year);
            X = (int)Math.round(dx * (sum - 1));
            g.drawLine(x0 + X, y0, x0 + X, y0 - 300);
            g.drawString(this.monthArray[m], X + 25, y0 + 15);
        }
        final int SUM = sum;
        X = (int)Math.round(dx * (SUM - 1));
        g.setColor(Color.black);
        g.drawRect(x0, y0 - 300, X, 300);
        for (int k = 1; k <= 12; ++k) {
            if (k % 2 == 0) {
                g.setColor(Color.black);
            }
            else {
                g.setColor(Color.gray);
            }
            g.drawLine(x0, y0 - k * dy / 2, x0 + X, y0 - k * dy / 2);
        }
        int min = 450000;
        int max = 0;
        int iMin = 0;
        int iMax = 0;
        g.setColor(Color.red);
        for (int l = 0; l < this.nData - 1; ++l) {
            final int Y = (int)Math.round(dy * (this.d[l] - 350000) / 10000.0);
            final int Y2 = (int)Math.round(dy * (this.d[l + 1] - 350000) / 10000.0);
            X = (int)Math.round(dx * l);
            final int X2 = (int)Math.round(dx * (l + 1));
            g.drawOval(x0 + X - 1, y0 - Y - 1, 2, 2);
            g.drawLine(x0 + X, y0 - Y, x0 + X2, y0 - Y2);
            if (this.d[l] < min) {
                min = this.d[l];
                iMin = l;
            }
            if (this.d[l] > max) {
                max = this.d[l];
                iMax = l;
            }
        }
        g.setColor(Color.blue);
        X = (int)Math.round(dx * iMin);
        int Y = (int)Math.round(dy * (this.d[iMin] - 350000) / 10000.0);
        g.fillOval(x0 + X - 2, y0 - Y - 2, 4, 4);
        X = (int)Math.round(dx * iMax);
        Y = (int)Math.round(dy * (this.d[iMax] - 350000) / 10000.0);
        g.fillOval(x0 + X - 2, y0 - Y - 2, 4, 4);
        g.drawString(String.valueOf(this.minDistStr) + " - " + this.maxDistStr, 20, 40);
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 48));
            g.setColor(Color.red);
            g.drawString("D E M O", 150, 150);
        }
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
}
