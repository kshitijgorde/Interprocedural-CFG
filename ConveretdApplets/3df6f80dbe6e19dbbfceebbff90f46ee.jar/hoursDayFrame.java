import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Checkbox;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class hoursDayFrame extends Frame
{
    boolean isOnline;
    double latitude;
    double longitude;
    final char deg = '°';
    String latStr;
    String longStr;
    String versStr;
    double[] hours;
    int sum;
    int breite;
    int year;
    int nTage;
    double mittel;
    double minDay;
    double maxDay;
    double minHours;
    double maxHours;
    int monthMin;
    int dateMin;
    int monthMax;
    int dateMax;
    int dayNumberSpring;
    int dayNumberAutumn;
    int xL;
    int hoehe;
    int numMouse;
    String[] monthArray;
    int monthMouse;
    int dateMouse;
    boolean drawOK;
    boolean diffOK;
    Checkbox box1;
    Checkbox box2;
    Rectangle rect;
    Point pt;
    
    public hoursDayFrame(final String vStr, final int YEAR, final double LAT, final double LONG, final double[] h, final boolean online) {
        this.minHours = 100.0;
        this.maxHours = 0.0;
        this.xL = 40;
        this.hoehe = 320;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.drawOK = false;
        this.diffOK = false;
        this.rect = new Rectangle(this.xL, 60, (int)Math.round(475.8), this.hoehe);
        final Panel p = new Panel();
        p.setLayout(new GridLayout(0, 2));
        (this.box1 = new Checkbox("Show mean/min/max")).setState(this.drawOK);
        (this.box2 = new Checkbox("Show difference per day")).setState(this.diffOK);
        p.add(this.box1);
        p.add(this.box2);
        this.add("North", p);
        this.repaint();
        this.latStr = String.valueOf(Math.abs(LAT));
        if (LAT >= 0.0) {
            this.latStr = String.valueOf(this.latStr) + "°N";
        }
        else {
            this.latStr = String.valueOf(this.latStr) + "°S";
        }
        this.longStr = String.valueOf(Math.abs(LONG));
        if (LONG >= 0.0) {
            this.longStr = String.valueOf(this.longStr) + "°E";
        }
        else {
            this.longStr = String.valueOf(this.longStr) + "°W";
        }
        this.setTitle(String.valueOf(vStr) + " at " + this.latStr + ", " + this.longStr);
        this.isOnline = online;
        this.latitude = LAT;
        this.longitude = LONG;
        this.hours = h;
        this.year = YEAR;
        this.nTage = 0;
        for (int m = 0; m < 12; ++m) {
            this.nTage += this.daysInMonth(m, this.year);
        }
        this.breite = (int)Math.round(1.3 * this.nTage);
        this.mittel = 0.0;
        for (int i = 1; i <= this.nTage; ++i) {
            if (this.hours[i] < 0.0) {
                this.hours[i] += 24.0;
            }
            this.mittel += this.hours[i];
            if (this.hours[i] < this.minHours) {
                this.minHours = this.hours[i];
                this.minDay = i;
            }
            if (this.hours[i] > this.maxHours) {
                this.maxHours = this.hours[i];
                this.maxDay = i;
            }
        }
        this.mittel /= this.nTage;
        int ind = 0;
        for (int j = 0; j < 12; ++j) {
            for (int d = 1; d <= this.daysInMonth(j, this.year); ++d) {
                if (++ind == this.minDay) {
                    this.monthMin = j + 1;
                    this.dateMin = d;
                }
                if (ind == this.maxDay) {
                    this.monthMax = j + 1;
                    this.dateMax = d;
                }
            }
        }
        this.setLocation(200, 400);
        final compute comp = new compute();
        final computeSeasons mySeasons = new computeSeasons(this.year + 1900);
        this.dayNumberSpring = 31 + this.daysInMonth(1, this.year) + this.date(mySeasons.march());
        this.dayNumberAutumn = 31 + this.daysInMonth(1, this.year) + 31 + 30 + 31 + 30 + 31 + 31 + this.date(mySeasons.september());
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
        if (x >= this.xL && x <= this.xL + this.breite && y > 60 && y <= 60 + this.hoehe) {
            this.numMouse = (int)Math.round((x - this.xL) / 1.3);
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
        this.pt = new Point(x, y);
        if (this.rect.contains(this.pt)) {
            this.setCursor(new Cursor(1));
        }
        else {
            this.setCursor(new Cursor(0));
        }
        return true;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Checkbox) {
            if (event.target == this.box1) {
                this.drawOK ^= true;
                this.repaint();
            }
            if (event.target == this.box2) {
                this.diffOK ^= true;
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
        final int y0 = 390;
        final double f = this.hoehe / 24.0;
        final int dx = 40;
        final int dy = 40;
        g.setFont(new Font("Courier", 0, 10));
        for (int i = 0; i < 12; ++i) {
            g.drawString(this.monthArray[i], this.xL + 10 + i * dx, y0 + 20);
        }
        g.setColor(Color.red);
        for (int j = 1; j < 365; ++j) {
            final double diff1 = Math.abs(this.hours[j + 1] - this.hours[j]);
            if (diff1 < 10.0) {
                g.drawLine(this.xL + (int)Math.round(1.3 * j), y0 - (int)Math.round(f * this.hours[j]), this.xL + (int)Math.round(1.3 * (j + 1)), y0 - (int)Math.round(f * this.hours[j + 1]));
            }
        }
        if (this.diffOK) {
            final double F = this.hoehe * 60.0 / 24.0;
            g.setColor(Color.blue);
            final int Y0 = y0 - 4 * dy;
            for (int k = 1; k < 365; ++k) {
                final double diff1 = this.hours[k] - this.hours[k - 1];
                final double diff2 = this.hours[k + 1] - this.hours[k];
                final double diff3 = Math.abs(diff1 - diff2);
                if (Math.abs(diff1) < 0.4 && Math.abs(diff2) < 0.4 && Math.abs(diff3) < 0.01) {
                    g.drawLine(this.xL + (int)Math.round(1.3 * k), Y0 - (int)Math.round(F * diff1), this.xL + (int)Math.round(1.3 * (k + 1)), Y0 - (int)Math.round(F * diff2));
                }
            }
            for (int l = -4; l <= 4; ++l) {
                g.drawString(l * 3 + " min", this.breite + 50, Y0 - l * dx + 5);
            }
        }
        g.setColor(Color.gray);
        int s = 0;
        int index = 0;
        for (int m = 0; m < 12; ++m) {
            s += this.daysInMonth(m, this.year);
            g.setColor(Color.gray);
            g.drawLine(this.xL + (int)Math.round(1.3 * s), 70, this.xL + (int)Math.round(1.3 * s), y0);
            double hoursMonth = 0.0;
            for (int d = 1; d <= this.daysInMonth(m, this.year); ++d) {
                ++index;
                hoursMonth += this.hours[index];
            }
            hoursMonth /= this.daysInMonth(m, this.year);
            g.setColor(Color.black);
            g.drawString(String.valueOf(Math.round(100.0 * hoursMonth) / 100.0), this.xL + 5 + m * dx, 60);
        }
        g.setColor(Color.gray);
        for (int i2 = 1; i2 < 8; ++i2) {
            g.drawLine(this.xL, y0 - i2 * dy, this.xL + this.breite, y0 - i2 * dy);
        }
        if (this.diffOK) {
            g.setColor(Color.blue);
            g.drawLine(this.xL, y0 - 4 * dy, this.xL + this.breite, y0 - 4 * dy);
        }
        g.setColor(Color.red);
        for (int i3 = 0; i3 <= 8; ++i3) {
            g.drawString(i3 * 3 + " h", 10, y0 - i3 * dx + 5);
        }
        g.drawRect(this.xL, 70, this.breite, this.hoehe);
        g.setColor(Color.blue);
        if (this.drawOK) {
            g.drawLine(this.xL, y0 - (int)Math.round(f * this.mittel), this.xL + this.breite, y0 - (int)Math.round(f * this.mittel));
            g.drawString(Math.round(1000.0 * this.mittel) / 1000.0 + "h", this.xL + 10, y0 - (int)Math.round(f * this.mittel) - 5);
            if (this.minHours > 0.0) {
                g.drawLine(this.xL + (int)Math.round(1.3 * this.minDay), y0, this.xL + (int)Math.round(1.3 * this.minDay), y0 - (int)Math.round(f * this.minHours));
                g.drawString(Math.round(1000.0 * this.minHours) / 1000.0 + "h", this.xL + (int)Math.round(1.3 * this.minDay) - 15, y0 - (int)Math.round(f * this.minHours) - 10);
                g.drawString(String.valueOf(this.monthArray[this.monthMin - 1]) + " " + this.dateMin, this.xL + (int)Math.round(1.3 * this.minDay) - 15, y0 - (int)Math.round(f * this.minHours) - 20);
            }
            if (this.maxHours < 24.0) {
                g.drawLine(this.xL + (int)Math.round(1.3 * this.maxDay), y0, this.xL + (int)Math.round(1.3 * this.maxDay), y0 - (int)Math.round(f * this.maxHours));
                g.drawString(Math.round(1000.0 * this.maxHours) / 1000.0 + "h", this.xL + (int)Math.round(1.3 * this.maxDay) - 15, y0 - (int)Math.round(f * this.maxHours) - 10);
                g.drawString(String.valueOf(this.monthArray[this.monthMax - 1]) + " " + this.dateMax, this.xL + (int)Math.round(1.3 * this.maxDay) - 15, y0 - (int)Math.round(f * this.maxHours) - 20);
            }
            g.drawOval(this.xL + (int)Math.round(1.3 * this.dayNumberSpring) - 1, y0 - (int)Math.round(f * this.hours[this.dayNumberSpring]) - 1, 2, 2);
            g.drawOval(this.xL + (int)Math.round(1.3 * this.dayNumberAutumn) - 1, y0 - (int)Math.round(f * this.hours[this.dayNumberAutumn]) - 1, 2, 2);
        }
        if (this.numMouse > 0) {
            g.drawLine(this.xL + (int)Math.round(1.3 * this.numMouse), y0, this.xL + (int)Math.round(1.3 * this.numMouse), y0 - (int)Math.round(f * this.hours[this.numMouse]));
            g.drawString(Math.round(1000.0 * this.hours[this.numMouse]) / 1000.0 + "h", this.xL + (int)Math.round(1.3 * this.numMouse) - 15, y0 + 35);
            g.drawString(this.monthMouse + "/" + this.dateMouse, this.xL + (int)Math.round(1.3 * this.numMouse) - 15, y0 + 45);
        }
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 36));
            g.setColor(Color.red);
            g.drawString("D E M O", 200, 200);
        }
    }
}
