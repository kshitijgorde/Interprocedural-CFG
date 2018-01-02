import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Mapcanvas extends Canvas
{
    public final int mapOben = 305;
    public final int mapRechts = 490;
    final int xL = 30;
    final double xFaktor = 1.2555555555555555;
    final double yFaktor = 1.2444444444444445;
    int x0;
    int y0;
    int left;
    final double K = 0.017453292519943295;
    Image myMap;
    Date dat;
    int day;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    int locOffset;
    int browserOffset;
    double dec;
    double GHA;
    double longitude;
    double latitude;
    int xMouse;
    int yMouse;
    boolean clicked;
    public Compute comp;
    String dayStr;
    String monthStr;
    String hourStr;
    double theTime;
    
    public Mapcanvas(final Image map, final Date myDate, final double myLat, final double myLong, final boolean myClick, final int myLocOffset) {
        this.x0 = (int)Math.round(226.0);
        this.y0 = (int)Math.round(112.0) + 305;
        this.left = 400;
        this.xMouse = 0;
        this.yMouse = 0;
        this.myMap = map;
        this.dat = myDate;
        this.longitude = myLong;
        this.latitude = myLat;
        this.clicked = myClick;
        this.locOffset = myLocOffset;
        this.repaint();
    }
    
    public String monthString(final int m) {
        final String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        if (m > 11 || m < 0) {
            return "?";
        }
        return monthArray[m];
    }
    
    public String dayString(final double jd) {
        final String[] dayArray = { null, "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        long num = (long)(jd + 0.5);
        num = num - num / 7L * 7L + 1L;
        final int n = (int)num;
        if (n < 1 || n > 7) {
            return "?";
        }
        return dayArray[(int)num];
    }
    
    public double JD(final int date, int month, int year, final double STD) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final double B = year / 400 - year / 100 + year / 4;
        final double A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + STD / 24.0;
        return MJD + 2400000.5;
    }
    
    public String makeTimeString(double time) {
        String str = "?";
        if (time < 0.0) {
            time += 24.0;
        }
        if (time > 24.0) {
            time -= 24.0;
        }
        final double diff = time - (int)time;
        int min = (int)Math.round(diff * 60.0);
        if (min == 60) {
            min = 0;
            ++time;
        }
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        str = String.valueOf(String.valueOf((int)time) + str + min);
        if (time < 10.0) {
            str = "0" + str;
        }
        return str;
    }
    
    public int daysInMonth(final int m, int y) {
        int n = 31;
        y = this.dat.getYear() + 1900;
        if (m == 0 | m == 2 | m == 4 | m == 6 | m == 7 | m == 9 | m == 11) {
            n = 31;
        }
        if (m == 3 | m == 5 | m == 8 | m == 10) {
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
        String str = "";
        g.drawImage(this.myMap, 30, 305, this);
        this.comp = new Compute();
        final int Radius = 8;
        this.date = this.dat.getDate();
        this.day = this.dat.getDay();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.hours = this.dat.getHours();
        final int hour = this.hours;
        this.minutes = this.dat.getMinutes();
        if (this.minutes > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        this.hourStr = String.valueOf(hour) + str + this.minutes;
        if (this.hours < 10) {
            this.hourStr = "0" + this.hourStr;
        }
        this.theTime = this.hours + this.minutes / 60.0;
        double jd = this.JD(this.date, this.month + 1, this.year + 1900, this.theTime);
        str = ":" + this.seconds;
        if (this.seconds < 10) {
            str = ":0" + this.seconds;
        }
        else {
            str = ":" + this.seconds;
        }
        String datum = String.valueOf(this.dayString(jd)) + "  " + this.monthString(this.month) + "  " + this.date + "   " + (this.year + 1900);
        g.setColor(Color.blue);
        g.drawString(datum, 490, 125);
        datum = "  at " + this.hourStr + str + " Hrs";
        g.drawString(datum, 580, 125);
        double gmtTime = this.hours - this.locOffset;
        int gmtDate = this.date;
        int gmtMonth = this.month;
        int gmtYear = this.year;
        if (gmtTime < 0.0) {
            gmtTime += 24.0;
            gmtDate = this.date - 1;
            if (gmtDate == 0) {
                gmtDate = this.daysInMonth(gmtMonth - 1, gmtYear);
                --gmtMonth;
                if (gmtMonth == -1) {
                    gmtMonth = 11;
                    --gmtYear;
                }
            }
        }
        if (gmtTime >= 24.0) {
            gmtTime -= 24.0;
            ++gmtDate;
            if (gmtDate > this.daysInMonth(gmtMonth, gmtYear)) {
                gmtDate = 1;
                ++gmtMonth;
                if (gmtMonth == 12) {
                    gmtMonth = 0;
                    ++gmtYear;
                }
            }
        }
        jd = this.JD(gmtDate, gmtMonth + 1, gmtYear + 1900, gmtTime);
        final double STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.dec = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, STD);
        datum = String.valueOf(this.dayString(jd)) + "  " + this.monthString(gmtMonth) + "  " + gmtDate + "   " + (gmtYear + 1900);
        g.drawString(datum, 490, 525);
        datum = String.valueOf(this.makeTimeString(STD)) + str + " UT";
        g.drawString("  at " + datum, 580, 525);
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, STD);
        g.setColor(Color.black);
        g.setColor(Color.red);
        int x = 180 - (int)Math.round(this.GHA);
        if (x < 0) {
            x += 360;
        }
        if (x > 360) {
            x -= 360;
        }
        int xx = 30;
        g.setColor(Color.gray);
        g.drawLine(30, this.y0, 30 + 2 * this.x0 - 2, this.y0);
        g.drawLine(30 + this.x0, 305, 30 + this.x0, 529);
        final int yy1 = (int)Math.round(this.y0 - 29.244444444444444);
        final int yy2 = (int)Math.round(this.y0 + 29.244444444444444);
        int y = (int)Math.round(499.75555555555553);
        int yy3 = (int)Math.round(334.24444444444447);
        for (int i = 0; i < 75; ++i) {
            g.drawLine(xx, yy1, xx + 2, yy1);
            g.drawLine(xx, yy2, xx + 2, yy2);
            g.drawLine(xx, y, xx + 2, y);
            g.drawLine(xx, yy3, xx + 2, yy3);
            xx += 6;
        }
        y = (int)Math.round(this.y0 - 1.2444444444444445 * this.dec);
        g.setColor(Color.yellow);
        g.fillOval(30 + (int)Math.round(1.2555555555555555 * x) - 8, y - 8, 16, 16);
        g.setColor(Color.red);
        g.drawOval(30 + (int)Math.round(1.2555555555555555 * x) - 8, y - 8, 16, 16);
        g.setColor(Color.black);
        int F;
        if (this.dec > 0.0) {
            F = 1;
        }
        else {
            F = -1;
        }
        int a = this.y0 + F * 111;
        final double b = -180.0 + this.GHA;
        g.setColor(Color.darkGray);
        for (int j = 0; j < 451; ++j) {
            if (j % 3 == 0) {
                if (this.dec > 0.0) {
                    a = this.y0 + 111;
                }
                else {
                    a = this.y0 - 111;
                }
                final double LL = -180.0 + this.GHA + j / 1.2555555555555555;
                yy3 = (int)Math.round(1.2444444444444445 * this.comp.computeLat(LL, this.dec));
                g.drawLine(30 + j, this.y0 - yy3, 30 + j, a);
            }
        }
        g.setColor(Color.red);
        x = 180 - (int)Math.round(this.longitude);
        if (x < 0) {
            x += 360;
        }
        if (x > 360) {
            x -= 360;
        }
        x = this.x0 + (int)Math.round(1.2555555555555555 * this.longitude);
        y = this.y0 - (int)Math.round(1.2444444444444445 * this.latitude);
        g.fillOval(30 + x - 4, y - 4, 8, 8);
        g.setColor(Color.yellow);
        g.drawLine(30 + x, y - 5, 30 + x, y + 5);
        g.drawLine(30 + x - 5, y, 30 + x + 5, y);
        final double hoehe = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
        double azimuth = this.comp.computeAzimut(this.dec, this.latitude, this.longitude, this.GHA, hoehe);
        String schatten = "";
        final int mapUnten = 550;
        if (hoehe > 0.0 && this.clicked) {
            g.setColor(Color.black);
            schatten = Math.round(1000.0 / Math.tan(0.017453292519943295 * hoehe)) / 1000.0 + " Meter";
            g.drawString("Shadow  length of a 1 m high gnomon", 490, 450);
            g.drawString(schatten, 490, 465);
            g.setColor(Color.red);
            azimuth -= 180.0;
            final double gnomon = 50.0 / Math.tan(0.017453292519943295 * hoehe);
            this.xMouse = 30 + this.x0 + (int)Math.round(this.longitude * 452.0 / 360.0);
            this.yMouse = this.y0 - (int)Math.round(this.latitude * 224.0 / 180.0);
            final int yGnomon = (int)Math.round(gnomon * Math.cos(0.017453292519943295 * azimuth));
            final int xGnomon = (int)Math.round(gnomon * Math.sin(0.017453292519943295 * azimuth));
            final double steig = yGnomon / xGnomon;
            if (this.yMouse - yGnomon > 305 && xGnomon + this.xMouse < 482) {
                g.drawLine(this.xMouse, this.yMouse, this.xMouse + xGnomon, this.yMouse - yGnomon);
            }
            if (this.yMouse - yGnomon < 305 && xGnomon + this.xMouse < 482) {
                final int xPart = (int)((this.yMouse - 305) / steig);
                g.drawLine(this.xMouse, this.yMouse, this.xMouse + xPart, 305);
            }
            if (this.yMouse - yGnomon > 305 && xGnomon + this.xMouse > 482) {
                final int yPart = (int)((482 - this.xMouse) * steig);
                g.drawLine(this.xMouse, this.yMouse, 482, this.yMouse - yPart);
            }
            if (this.yMouse - yGnomon < 305 && xGnomon + this.xMouse > 482) {
                final double steigEcke = (this.yMouse - 305) / (482 - this.xMouse);
                if (steig > steigEcke) {
                    final int xPart = (int)((this.yMouse - 305) / steig);
                    g.drawLine(this.xMouse, this.yMouse, this.xMouse + xPart, 305);
                }
                if (steig < steigEcke) {
                    final int yPart = (int)((482 - this.xMouse) * steig);
                    g.drawLine(this.xMouse, this.yMouse, 482, this.yMouse - yPart);
                }
            }
        }
        g.setColor(Color.black);
        if (hoehe < 0.0 && this.clicked) {
            g.drawString("Night", 490, 465);
        }
        g.setColor(Color.red);
        g.drawRect(30, 305, 2 * this.x0 - 2, 223);
        this.repaint();
    }
}
