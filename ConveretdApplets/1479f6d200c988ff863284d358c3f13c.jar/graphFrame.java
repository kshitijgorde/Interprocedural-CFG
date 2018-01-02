import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class graphFrame extends Frame
{
    boolean isOnline;
    double[][] d;
    int nData;
    double locOffset;
    int noTransitDay;
    
    public graphFrame(final String vStr, final double offset, final int n, final double[][] D, final int noTrans, final boolean online) {
        this.d = new double[35][4];
        this.setTitle(vStr);
        this.isOnline = online;
        this.nData = n;
        this.locOffset = offset;
        this.setLocation(250, 200);
        this.noTransitDay = noTrans;
        for (int i = 0; i <= n; ++i) {
            this.d[i][0] = D[i][0];
            this.d[i][1] = D[i][1];
            this.d[i][2] = D[i][2];
        }
        this.repaint();
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public void paint(final Graphics g) {
        final int x0 = 30;
        final int y0 = 300;
        final int dx = 15;
        final int dy = 30;
        g.setFont(new Font("Courier", 0, 10));
        g.drawRect(x0, y0 - 270, 465, 270);
        g.setColor(Color.lightGray);
        if (this.noTransitDay > 1) {
            g.fillRect(x0 + this.noTransitDay * dx - dx + 1, y0 - 269, dx - 1, 269);
        }
        for (int i = 1; i <= 8; ++i) {
            if (i % 2 == 0) {
                g.setColor(Color.black);
            }
            else {
                g.setColor(Color.gray);
            }
            g.drawLine(x0, y0 - i * dy, x0 + 465, y0 - i * dy);
        }
        g.setColor(Color.black);
        for (int j = 0; j <= 9; ++j) {
            g.drawString(String.valueOf(j * 10), 10, y0 - j * dy + 5);
        }
        for (int k = 1; k <= 31; ++k) {
            if (k % 5 == 0) {
                g.setColor(Color.black);
            }
            else {
                g.setColor(Color.gray);
            }
            g.drawLine(x0 + k * dx, y0, x0 + k * dx, y0 - 270);
        }
        g.setColor(Color.black);
        g.drawLine(x0, y0, x0 + 450, y0);
        for (int l = 0; l <= this.nData; ++l) {
            final int Y = (int)Math.round(this.d[l][1] * 3.0);
            if (Y != 0) {
                final int X = (int)Math.round((this.locOffset / 24.0 + (this.d[l][0] - ((int)this.d[0][0] + 0.5))) * dx) + dx;
                final int c = (int)Math.round(255.0 * this.d[l][2]);
                g.setColor(new Color(c, c, c));
                g.fillOval(x0 + X - 5, y0 - Y - 5, 10, 10);
                if (this.d[l][2] > 0.997) {
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.black);
                }
                g.drawOval(x0 + X - 5, y0 - Y - 5, 10, 10);
            }
            if (l % 5 == 0) {
                g.setColor(Color.black);
                g.drawString(String.valueOf(l + 1), x0 + l * dx + 3, y0 + 15);
            }
        }
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 48));
            g.setColor(Color.red);
            g.drawString("D E M O", 150, 150);
        }
    }
}
