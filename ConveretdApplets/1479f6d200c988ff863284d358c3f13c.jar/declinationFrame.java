import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class declinationFrame extends Frame
{
    boolean isOnline;
    double[][] d;
    int nData;
    
    public declinationFrame(final String vStr, final int n, final double[][] D, final boolean online) {
        this.d = new double[32][2];
        this.setTitle(vStr);
        this.isOnline = online;
        this.nData = n;
        for (int i = 1; i <= n; ++i) {
            this.d[i][0] = D[i][0];
            this.d[i][1] = D[i][1];
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
        final int y0 = 160;
        final int dx = 14;
        final int dy = 20;
        g.setFont(new Font("Courier", 0, 10));
        g.drawRect(x0, y0 - 120, 450, 240);
        for (int i = -5; i <= 5; ++i) {
            if (i % 2 == 0) {
                g.setColor(Color.black);
            }
            else {
                g.setColor(Color.gray);
            }
            g.drawLine(x0, y0 + i * dy, x0 + 450, y0 + i * dy);
        }
        g.setColor(Color.black);
        for (int j = -3; j <= 3; ++j) {
            g.drawString(String.valueOf(-j * 10), 10, y0 + j * 2 * dy + 5);
        }
        for (int k = 1; k <= this.nData; ++k) {
            if (k % 5 == 0) {
                g.setColor(Color.black);
            }
            else {
                g.setColor(Color.gray);
            }
            g.drawLine(x0 + k * dx, y0 - 120, x0 + k * dx, y0 + 120);
        }
        g.setColor(Color.black);
        g.drawLine(x0, y0, x0 + 450, y0);
        for (int l = 1; l <= this.nData; ++l) {
            final int Y = (int)Math.round(this.d[l][0] * 4.0);
            final int c = (int)Math.round(255.0 * this.d[l][1]);
            g.setColor(new Color(c, c, c));
            g.fillOval(x0 + l * dx - 5, y0 - Y - 5, 10, 10);
            if (this.d[l][1] > 0.995) {
                g.setColor(Color.red);
            }
            else {
                g.setColor(Color.black);
            }
            g.drawOval(x0 + l * dx - 5, y0 - Y - 5, 10, 10);
            if (l % 5 == 0) {
                g.setColor(Color.black);
                g.drawString(String.valueOf(l), x0 + l * dx - 3, y0 + 135);
            }
        }
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 48));
            g.setColor(Color.red);
            g.drawString("D E M O", 150, 150);
        }
    }
}
