import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Checkbox;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class GraphFrame extends Frame
{
    boolean isOnline;
    final char deg = '°';
    double[] libL;
    double[] libB;
    int breite;
    int year;
    int nTage;
    int xL;
    int hoehe;
    int oben;
    int numMouse;
    String[] monthArray;
    int monthMouse;
    int dateMouse;
    double xFaktor;
    Checkbox boxL;
    Checkbox boxB;
    Checkbox boxLB;
    boolean LOK;
    boolean BOK;
    boolean LBOK;
    
    public GraphFrame(final String vStr, final int YEAR, final double[] L, final double[] B, final boolean online) {
        this.xL = 40;
        this.hoehe = 400;
        this.oben = 50;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.xFaktor = 1.4;
        this.LOK = true;
        this.BOK = false;
        this.LBOK = false;
        final Panel p = new Panel();
        p.setLayout(new GridLayout(0, 3));
        (this.boxL = new Checkbox("Libration longitude")).setState(this.LOK);
        (this.boxB = new Checkbox("Libration latitude")).setState(this.BOK);
        (this.boxLB = new Checkbox("Latit. and long.")).setState(this.LBOK);
        p.add(this.boxL);
        p.add(this.boxB);
        p.add(this.boxLB);
        this.add("North", p);
        this.repaint();
        this.isOnline = online;
        this.libL = L;
        this.libB = B;
        this.year = YEAR;
        this.setTitle("Moon Libration " + (this.year + 1900));
        this.nTage = 0;
        for (int m = 0; m < 12; ++m) {
            this.nTage += this.daysInMonth(m, this.year);
        }
        this.setLocation(200, 400);
    }
    
    public int date(final double JD) {
        final double JD2 = (int)(JD + 0.5);
        final int B = (int)((JD2 - 1867216.25) / 36524.25);
        final double C = JD2 + B - B / 4 + 1525.0;
        final int D = (int)((C - 122.1) / 365.25);
        final double E = 365.0 * D + D / 4;
        final int F = (int)((C - E) / 30.6001);
        return (int)(C - E + 0.5) - (int)(30.6001 * F);
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        this.numMouse = 0;
        final Rectangle rect = new Rectangle(this.xL, this.oben, this.breite, this.hoehe);
        final Point pt = new Point(x, y);
        if (rect.contains(pt)) {
            this.numMouse = (int)Math.round((x - this.xL) / this.xFaktor);
            int ind = 0;
            for (int m = 0; m < 12; ++m) {
                for (int d = 1; d <= this.daysInMonth(m, this.year); ++d) {
                    if (++ind == this.numMouse) {
                        this.monthMouse = m + 1;
                        this.dateMouse = d;
                    }
                }
            }
        }
        else {
            this.numMouse = 0;
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int x, final int y) {
        final Rectangle rect = new Rectangle(this.xL, this.oben, this.breite, this.hoehe);
        final Point pt = new Point(x, y);
        if (rect.contains(pt)) {
            this.setCursor(new Cursor(1));
        }
        else {
            this.setCursor(new Cursor(0));
        }
        return true;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Checkbox) {
            if (event.target == this.boxL) {
                this.LOK ^= true;
                this.repaint();
            }
            if (event.target == this.boxB) {
                this.BOK ^= true;
                this.repaint();
            }
            if (event.target == this.boxLB) {
                this.LBOK ^= true;
                this.repaint();
            }
        }
        return true;
    }
    
    public int daysInMonth(final int m, int y) {
        int n = 31;
        y += 1900;
        if (m == 0 || m == 2 || m == 4 || m == 6 || m == 7 || m == 9 || m == 11) {
            n = 31;
        }
        if (m == 3 || m == 5 || m == 8 || m == 10) {
            n = 30;
        }
        if (m == 1) {
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
    
    public void paint(final Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 800);
        g.setColor(Color.black);
        final int y0 = this.oben + 220;
        final double f = this.hoehe / 20.0;
        final int dx = 43;
        final int dy = 20;
        this.breite = (int)Math.round(this.xFaktor * this.nTage);
        g.setFont(new Font("Courier", 0, 10));
        for (int i = 0; i < 12; ++i) {
            g.drawString(this.monthArray[i], this.xL + 10 + i * dx, this.oben + this.hoehe + 20);
        }
        g.setColor(Color.gray);
        int s = 0;
        for (int m = 0; m < 12; ++m) {
            s += this.daysInMonth(m, this.year);
            g.drawLine(this.xL + (int)Math.round(this.xFaktor * s), this.oben, this.xL + (int)Math.round(this.xFaktor * s), this.oben + this.hoehe);
        }
        for (int j = -8; j < 11; ++j) {
            g.drawLine(this.xL, y0 - j * dy, this.xL + this.breite, y0 - j * dy);
        }
        g.setColor(Color.red);
        g.drawRect(this.xL, this.oben, this.breite, 20 * dy);
        g.drawLine(this.xL, y0, this.xL + this.breite, y0);
        g.setColor(Color.black);
        for (int k = -9; k < 12; ++k) {
            g.drawString(new StringBuffer().append(k).append('°').toString(), 15, y0 - k * dy + 5);
        }
        if (this.LOK) {
            for (int l = 1; l < this.nTage; ++l) {
                g.drawLine(this.xL + (int)Math.round(this.xFaktor * (l - 1)), y0 - (int)Math.round(f * this.libL[l - 1]), this.xL + (int)Math.round(this.xFaktor * l), y0 - (int)Math.round(f * this.libL[l]));
            }
        }
        if (this.BOK) {
            for (int l = 1; l < this.nTage; ++l) {
                g.drawLine(this.xL + (int)Math.round(this.xFaktor * (l - 1)), y0 - (int)Math.round(f * this.libB[l - 1]), this.xL + (int)Math.round(this.xFaktor * l), y0 - (int)Math.round(f * this.libB[l]));
            }
        }
        if (this.LBOK) {
            for (int i2 = 1; i2 < this.nTage; ++i2) {
                final double LB1 = Math.sqrt(this.libL[i2 - 1] * this.libL[i2 - 1] + this.libB[i2 - 1] * this.libB[i2 - 1]);
                final double LB2 = Math.sqrt(this.libL[i2] * this.libL[i2] + this.libB[i2] * this.libB[i2]);
                g.drawLine(this.xL + (int)Math.round(this.xFaktor * (i2 - 1)), y0 - (int)Math.round(f * LB1), this.xL + (int)Math.round(this.xFaktor * i2), y0 - (int)Math.round(f * LB2));
            }
        }
        if (this.numMouse > 0) {
            g.drawLine(this.xL + (int)Math.round(this.xFaktor * this.numMouse), y0, this.xL + (int)Math.round(this.xFaktor * this.numMouse), y0 - (int)Math.round(f * this.libL[this.numMouse]));
            g.drawString(Math.round(1000.0 * this.libL[this.numMouse]) / 1000.0 + "h", this.xL + (int)Math.round(this.xFaktor * this.numMouse) - 15, y0 + 35);
            g.drawString(this.monthMouse + "/" + this.dateMouse, this.xL + (int)Math.round(this.xFaktor * this.numMouse) - 15, y0 + 45);
        }
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 36));
            g.setColor(Color.red);
            g.drawString("D E M O", 200, 200);
        }
    }
}
