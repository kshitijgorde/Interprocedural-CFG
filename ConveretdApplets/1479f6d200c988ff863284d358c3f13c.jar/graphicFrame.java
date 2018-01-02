import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class graphicFrame extends Frame
{
    int[][] P;
    boolean isOnline;
    
    public graphicFrame(final String titleStr, final int[][] p, final boolean online) {
        this.P = new int[32][13];
        for (int i = 1; i <= 31; ++i) {
            for (int m = 1; m <= 12; ++m) {
                this.P[i][m] = p[i][m];
            }
        }
        this.setTitle(titleStr);
        this.isOnline = online;
        this.setLocation(200, 400);
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
        final int r = 12;
        final int a = 25;
        String[] monthArray = new String[12];
        monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        g.setFont(new Font("Courier", 0, 10));
        for (int i = 1; i <= 31; ++i) {
            g.setColor(Color.red);
            g.drawString(String.valueOf(i), 17 + i * a, 45);
            for (int m = 1; m <= 12; ++m) {
                g.setColor(Color.red);
                g.drawString(monthArray[m - 1], 10, 42 + m * a);
                final int c = this.P[i][m];
                final int x = 20 + i * a;
                final int y = 40 + m * a;
                if (c < 300) {
                    g.setColor(new Color(c, c, c));
                    g.fillRect(x - r, y - r, 2 * r, 2 * r);
                }
            }
        }
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 36));
            g.setColor(Color.red);
            g.drawString("D E M O", 320, 200);
        }
    }
}
