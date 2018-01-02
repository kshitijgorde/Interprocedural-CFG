import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class graphicFrame extends Frame
{
    int[][] P;
    String[][] D;
    int[][] elev;
    boolean isOnline;
    String[] dayArray;
    boolean day;
    boolean visibility;
    boolean oval;
    
    public graphicFrame(final String titleStr, final int[][][] p, final boolean online, final boolean weekday, final boolean vis, final boolean ov) {
        this.P = new int[32][13];
        this.D = new String[32][13];
        this.elev = new int[32][13];
        this.dayArray = new String[] { "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su" };
        this.day = weekday;
        this.oval = ov;
        for (int i = 1; i <= 31; ++i) {
            for (int m = 1; m <= 12; ++m) {
                this.P[i][m] = p[i][m][0];
                this.D[i][m] = this.dayArray[p[i][m][1]];
                this.elev[i][m] = p[i][m][2];
            }
        }
        this.setTitle(titleStr);
        this.isOnline = online;
        this.visibility = vis;
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
        final int a = 29;
        final int b = 40;
        this.setBackground(Color.white);
        final String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        g.setFont(new Font("Helvetica", 0, 12));
        g.setColor(Color.red);
        for (int m = 1; m <= 12; ++m) {
            g.drawString(monthArray[m - 1], 30, 44 + m * b);
        }
        for (int i = 1; i <= 31; ++i) {
            g.setColor(Color.red);
            String str = String.valueOf(i);
            if (i < 10) {
                str = " " + str;
            }
            g.drawString(str, 38 + i * a, 55);
            g.drawString(str, 38 + i * a, 553);
            for (int j = 1; j <= 12; ++j) {
                final int c = this.P[i][j];
                final int x = 45 + i * a;
                final int y = 40 + j * b;
                if (c < 300) {
                    g.setColor(new Color(c, c, c));
                    if (!this.oval) {
                        g.fillRect(x - r, y - r, 2 * r, 2 * r);
                    }
                    else {
                        g.fillOval(x - r, y - r, 2 * r, 2 * r);
                    }
                    if (this.day) {
                        g.setFont(new Font("Helvetica", 0, 10));
                        int C = 255 - c;
                        if (C > 100 && C < 150) {
                            C = 255 - c - 50;
                        }
                        g.setColor(new Color(C, C, C));
                        g.drawString(this.D[i][j], x - 6, y + 5);
                    }
                    if (this.visibility) {
                        if (this.elev[i][j] > 0) {
                            g.setColor(Color.blue);
                        }
                        else {
                            g.setColor(Color.black);
                        }
                        if (!this.oval) {
                            g.drawRect(x - r, y - r, 2 * r, 2 * r);
                        }
                        else {
                            g.drawOval(x - r, y - r, 2 * r, 2 * r);
                        }
                    }
                    else {
                        g.setColor(Color.black);
                        if (!this.oval) {
                            g.drawRect(x - r, y - r, 2 * r, 2 * r);
                        }
                        else {
                            g.drawOval(x - r, y - r, 2 * r, 2 * r);
                        }
                    }
                }
            }
        }
        g.drawRect(15, 30, 955, 535);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString("MoonLight  (c) 2006-2008 J. Giesen  -  www. GeoAstro.de", 350, 580);
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 72));
            g.setColor(Color.red);
            g.drawString("D  E  M  O", 320, 300);
        }
    }
}
