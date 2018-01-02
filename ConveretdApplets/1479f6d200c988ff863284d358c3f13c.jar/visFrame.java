import java.awt.Color;
import java.awt.Font;
import java.util.Date;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class visFrame extends Frame
{
    boolean isOnline;
    double latitude;
    double longitude;
    int year;
    double locOffset;
    int daysInYear;
    final char deg = '°';
    String latStr;
    String longStr;
    String versStr;
    
    public visFrame(final String vStr, final int YEAR, final double LAT, final double LONG, final double offset, final boolean online) {
        this.latitude = LAT;
        this.longitude = LONG;
        this.locOffset = offset;
        this.year = YEAR;
        this.versStr = vStr;
        this.setTitle("Moon " + this.year);
        this.isOnline = online;
        this.latStr = new StringBuffer().append(Math.abs(LAT)).append('°').toString();
        if (LAT >= 0.0) {
            this.latStr = String.valueOf(this.latStr) + " N";
        }
        else {
            this.latStr = String.valueOf(this.latStr) + " S";
        }
        this.longStr = new StringBuffer().append(Math.abs(LONG)).append('°').toString();
        if (LONG >= 0.0) {
            this.longStr = String.valueOf(this.longStr) + " E";
        }
        else {
            this.longStr = String.valueOf(this.longStr) + " W";
        }
        for (int m = 1; m <= 12; ++m) {
            for (int d = 1; d <= this.daysInMonth(m - 1, this.year - 1900); ++d) {
                ++this.daysInYear;
            }
        }
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
    
    public void paint(final Graphics g) {
        final Date calcDat = new Date();
        final int links = 50;
        final int y0 = 410;
        final int faktor = 15;
        final int faktor2 = 10;
        int x = links;
        String[] monthArray = new String[12];
        final String noRS = "--:--";
        monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        g.setFont(new Font("Courier", 0, 10));
        g.setColor(Color.blue);
        g.drawString("Illum.", 10, 240);
        g.setColor(Color.red);
        g.drawString("Hours", links + 2 * this.daysInYear + 5, 50);
        g.setColor(Color.gray);
        g.drawLine(links, y0, links, y0 - 24 * faktor);
        for (int i = 0; i <= 12; ++i) {
            g.setColor(Color.gray);
            g.drawLine(links, y0 - i * 2 * faktor, 2 * this.daysInYear + links, y0 - i * 2 * faktor);
            g.setColor(Color.blue);
            if (i <= 5) {
                g.drawString(String.valueOf(Math.round(i * 20) / 100.0), 15, y0 - i * 2 * faktor + 3);
            }
            g.setColor(Color.red);
            if (i < 12) {
                g.drawString(String.valueOf(2 * i), links + 2 * this.daysInYear + 10, y0 - i * 2 * faktor + 3);
            }
        }
        int n = 0;
        final int[] Y1 = new int[366];
        final int[] Y2 = new int[366];
        calcDat.setYear(this.year - 1900);
        for (int m = 1; m <= 12; ++m) {
            g.setColor(Color.black);
            g.drawString(monthArray[m - 1], x + 20, 40);
            calcDat.setMonth(m - 1);
            for (int d = 1; d <= this.daysInMonth(m - 1, this.year - 1900); ++d) {
                calcDat.setDate(d);
                calcDat.setHours((int)this.locOffset);
                calcDat.setMinutes(0);
                final Moon moon = new Moon(calcDat, this.latitude, this.longitude, this.locOffset);
                Y1[n] = y0 - (int)Math.round(150.0 * moon.phase());
                final String str1 = moon.riseString();
                final String str2 = moon.setString();
                double diff = moon.moon_hSet() - moon.moon_hRise();
                if (diff < 0.0) {
                    diff += 24.0;
                }
                if (str1 != noRS && str2 != noRS) {
                    Y2[n] = y0 - (int)Math.round(faktor * diff);
                }
                else {
                    Y2[n] = 0;
                }
                x += 2;
                ++n;
            }
            g.setColor(Color.gray);
            g.drawLine(x, y0, x, y0 - faktor * 24);
        }
        int xx = links;
        for (int j = 0; j < n - 1; ++j) {
            g.setColor(Color.blue);
            g.drawLine(xx, Y1[j], xx + 2, Y1[j + 1]);
            g.setColor(Color.red);
            if (Y2[j] != 0 && Y2[j + 1] != 0) {
                g.drawLine(xx, Y2[j], xx + 2, Y2[j + 1]);
            }
            xx += 2;
        }
        g.setColor(Color.black);
        g.drawString(String.valueOf(this.latStr) + ", " + this.longStr + "        " + this.versStr + "  -  (c) 2004-2009 J. Giesen  -  www.GeoAstro.de", 50, 435);
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 36));
            g.setColor(Color.red);
            g.drawString("D E M O", 320, 200);
        }
    }
}
