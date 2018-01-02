import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Event;
import java.util.Date;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class moonFrame extends Frame
{
    int[][] P;
    boolean isOnline;
    double latitude;
    double longitude;
    double locOffset;
    int links;
    int xMouse;
    int yMouse;
    int year;
    String datStr;
    String[] monthArray;
    int Monat;
    int Tag;
    int yoben;
    int yunten;
    int daysInYear;
    double RISE;
    double SET;
    String riseSTR;
    String setSTR;
    String latStr;
    String longStr;
    final char deg = '°';
    int currentDay;
    double currentTime;
    double currentELEV;
    String currentPhaseStr;
    String currentTimeStr;
    String versString;
    
    public moonFrame(final Date dat, final String versStr, final double LAT, final double LONG, final double offset, final boolean online) {
        this.P = new int[32][13];
        this.links = 20;
        this.xMouse = 1000;
        this.datStr = "";
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.yoben = 50;
        this.yunten = this.yoben + 360;
        this.daysInYear = 0;
        this.RISE = 0.0;
        this.SET = 0.0;
        this.riseSTR = "";
        this.setSTR = "";
        this.currentPhaseStr = "";
        this.currentTimeStr = "";
        this.latitude = LAT;
        this.longitude = LONG;
        this.locOffset = offset;
        this.year = dat.getYear();
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
        this.setTitle("Moon " + (this.year + 1900));
        this.isOnline = online;
        this.versString = versStr;
        this.setLocation(250, 250);
        for (int m = 1; m <= 12; ++m) {
            for (int d = 1; d <= this.daysInMonth(m - 1, this.year); ++d) {
                ++this.daysInYear;
            }
        }
        int s = 0;
        for (int i = 1; i <= 12; ++i) {
            for (int d2 = 1; d2 <= this.daysInMonth(i - 1, this.year); ++d2) {
                s += 2;
                if (i == dat.getMonth() + 1 && d2 == dat.getDate()) {
                    this.currentDay = this.links + s - 2;
                }
            }
        }
        final Moon j = new Moon(dat, this.latitude, this.longitude, this.locOffset);
        this.currentTime = dat.getHours() + dat.getMinutes() / 60.0 + dat.getSeconds() / 3600.0;
        this.currentELEV = j.elev();
        this.repaint();
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
    
    public String HM(double x) {
        String str = "";
        if (x < 0.0) {
            x = Math.abs(x);
        }
        final int HOUR = (int)x;
        final int MIN = (int)Math.round(this.frac(x) * 60.0);
        if (HOUR < 10) {
            str = "0";
        }
        str = String.valueOf(str) + HOUR + ":";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + MIN;
    }
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public boolean mouseMove(final Event event, final int x, final int y) {
        if (x >= this.links && y >= this.yoben && y <= this.yunten && x < this.links + 2 * this.daysInYear) {
            this.setCursor(new Cursor(1));
        }
        else {
            this.setCursor(new Cursor(0));
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        int sum = 0;
        String delta = "";
        this.currentPhaseStr = "";
        if (x >= this.links && y >= this.yoben && y <= this.yunten && x < this.links + 2 * this.daysInYear) {
            this.datStr = "";
            this.xMouse = x - this.links;
            this.yMouse = y - this.yoben;
            double time = 12.0 + 24.0 * this.yMouse / (this.yunten - this.yoben);
            if (time > 24.0) {
                time -= 24.0;
            }
            this.currentTimeStr = this.HM(time);
            for (int m = 1; m <= 12; ++m) {
                for (int d = 1; d <= this.daysInMonth(m - 1, this.year); ++d) {
                    if (sum >= this.xMouse) {
                        this.datStr = String.valueOf(this.monthArray[m - 1]) + " " + d;
                        this.Monat = m;
                        this.Tag = d;
                        break;
                    }
                    sum += 2;
                }
                if (this.datStr != "") {
                    break;
                }
            }
            final Date datum = new Date();
            datum.setMonth(this.Monat - 1);
            datum.setDate(this.Tag);
            datum.setYear(this.year);
            final Moon moon = new Moon(datum, this.latitude, this.longitude, this.locOffset);
            this.riseSTR = "Rise " + moon.riseString();
            this.RISE = moon.moon_hRise();
            this.setSTR = "Set " + moon.setString();
            this.SET = moon.moon_hSet();
            this.datStr = this.year + 1900 + " " + this.datStr;
            if (time < 12.0) {
                datum.setDate(this.Tag + 1);
            }
            datum.setHours((int)time);
            datum.setMinutes((int)Math.round(60.0 * this.frac(time)));
            datum.setSeconds(0);
            final Moon i = new Moon(datum, this.latitude, this.longitude, this.locOffset);
            final double ph1 = i.phase1();
            final double ph2 = i.phase();
            final double ph3 = i.phase2();
            if (ph2 > ph1 && ph2 < ph3) {
                delta = "(+)";
            }
            if (ph2 < ph1 && ph2 > ph3) {
                delta = "(-)";
            }
            this.currentPhaseStr = "Illum. Frac. " + Math.round(1000.0 * i.phase()) / 10.0 + "% " + delta;
        }
        else {
            this.xMouse = 1000;
            this.datStr = "";
            this.riseSTR = "";
            this.setSTR = "";
            this.currentPhaseStr = "";
            this.currentTimeStr = "";
        }
        this.repaint();
        return true;
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("Courier", 0, 10));
        final Date dat = new Date();
        dat.setYear(this.year);
        int x = this.links;
        final int faktor = 15;
        final String noRiseSet = "--:--";
        for (int n = 0; n <= 8; ++n) {
            g.setColor(Color.gray);
            g.drawLine(this.links, this.yoben + n * 3 * faktor, this.links + 2 * this.daysInYear, this.yoben + n * 3 * faktor);
            g.setColor(Color.black);
            g.drawString(String.valueOf((12 + n * 3) % 24), 5, this.yoben + n * 3 * faktor + 5);
        }
        int delta = 0;
        for (int m = 1; m <= 12; ++m) {
            g.setColor(Color.black);
            g.drawString(this.monthArray[m - 1], x + 20, 40);
            dat.setMonth(m - 1);
            for (int d = 1; d <= this.daysInMonth(m - 1, this.year); ++d) {
                dat.setDate(d);
                final Moon moon = new Moon(dat, this.latitude, this.longitude, this.locOffset);
                final String str1 = moon.riseString();
                double hRise = moon.moon_hRise();
                final String str2 = moon.setString();
                double hSet = moon.moon_hSet();
                if (hSet < hRise) {
                    delta = 2;
                }
                else {
                    delta = 0;
                }
                hRise -= 12.0;
                hSet -= 12.0;
                if (hRise < 0.0) {
                    hRise += 24.0;
                }
                if (hSet < 0.0) {
                    hSet += 24.0;
                }
                final int y1 = this.yoben + (int)Math.round(faktor * hRise);
                final int y2 = this.yoben + (int)Math.round(faktor * hSet);
                if (str1 != noRiseSet && str2 != noRiseSet) {
                    g.setColor(Color.red);
                    g.drawOval(x - 1, y1, 2, 1);
                    g.setColor(Color.blue);
                    g.drawOval(x - 1 + delta, y2, 2, 1);
                }
                if (str1 == noRiseSet) {
                    g.setColor(Color.red);
                    g.drawLine(x, this.yoben, x, this.yoben - 5);
                    g.drawLine(x, this.yunten, x, this.yunten + 5);
                }
                if (str2 == noRiseSet) {
                    g.setColor(Color.blue);
                    g.drawLine(x, this.yoben, x, this.yoben - 5);
                    g.drawLine(x, this.yunten, x, this.yunten + 5);
                }
                x += 2;
            }
            g.setColor(Color.gray);
            g.drawLine(x, this.yoben, x, this.yunten);
        }
        g.setColor(Color.black);
        g.drawRect(this.links, this.yoben, x - this.links, 360);
        g.setColor(Color.red);
        g.drawLine(this.currentDay, this.yoben, this.currentDay, this.yunten);
        int y1 = this.yoben + (int)Math.round(faktor * (this.currentTime - 12.0));
        g.setColor(Color.black);
        if (this.currentELEV > 0.0) {
            g.drawOval(this.currentDay - 2, y1 - 2, 4, 4);
        }
        else {
            g.fillOval(this.currentDay - 2, y1 - 2, 4, 4);
        }
        g.setColor(Color.green);
        g.drawLine(this.xMouse + this.links, this.yoben, this.xMouse + this.links, this.yunten);
        this.RISE -= 12.0;
        this.SET -= 12.0;
        if (this.RISE < 0.0) {
            this.RISE += 24.0;
        }
        if (this.SET < 0.0) {
            this.SET += 24.0;
        }
        g.setColor(Color.red);
        y1 = this.yoben + (int)Math.round(faktor * this.RISE);
        g.drawLine(this.xMouse + this.links - 5, y1, this.xMouse + this.links + 5, y1);
        g.setColor(Color.blue);
        final int y2 = this.yoben + (int)Math.round(faktor * this.SET);
        g.drawLine(this.xMouse + this.links - 5, y2, this.xMouse + this.links + 5, y2);
        g.setColor(Color.black);
        g.drawString(String.valueOf(this.latStr) + ", " + this.longStr, 50, 440);
        g.setColor(Color.red);
        g.fillOval(180, 435, 2, 2);
        g.drawString("Rise", 190, 440);
        g.setColor(Color.blue);
        g.fillOval(230, 435, 2, 2);
        g.drawString("Set", 240, 440);
        g.setColor(Color.black);
        g.drawString(String.valueOf(this.versString) + " (c) 2004-2009 J. Giesen - www.GeoAstro.de", 470, 440);
        g.drawString(this.datStr, 50, 460);
        g.setColor(Color.red);
        g.drawString(this.riseSTR, 150, 460);
        g.setColor(Color.blue);
        g.drawString(this.setSTR, 240, 460);
        g.setColor(Color.black);
        g.drawString(String.valueOf(this.currentTimeStr) + "    " + this.currentPhaseStr, 320, 460);
        g.drawOval(this.xMouse + this.links - 3, this.yMouse + this.yoben - 3, 6, 6);
        g.setFont(new Font("Chicago", 0, 12));
        g.setColor(Color.red);
        g.drawString("Click the diagram", 325, 440);
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 36));
            g.setColor(Color.red);
            g.drawString("D E M O", 320, 250);
        }
    }
}
