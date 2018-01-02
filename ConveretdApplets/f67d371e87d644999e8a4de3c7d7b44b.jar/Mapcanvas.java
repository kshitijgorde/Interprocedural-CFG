import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Mapcanvas extends Canvas
{
    public final int mapOben = 305;
    public final int mapRechts = 545;
    final int xL = 30;
    int x0;
    int y0;
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
    public Compute comp;
    String dayStr;
    String monthStr;
    String hourStr;
    double theTime;
    
    public Mapcanvas(final Date myDate, final double myLat, final double myLong, final int myLocOffset) {
        this.x0 = 226;
        this.y0 = 467;
        this.dat = myDate;
        this.longitude = myLong;
        this.latitude = myLat;
        this.locOffset = myLocOffset;
        this.repaint();
    }
    
    public String monthString(final int m) {
        String[] monthArray = new String[12];
        monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
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
        this.comp = new Compute();
        this.date = this.dat.getDate();
        this.day = this.dat.getDay();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.hours = this.dat.getHours();
        final int hour = this.hours;
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
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
        this.theTime = this.hours + this.minutes / 60.0 + this.seconds / 3600.0;
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
        g.drawString(datum, 545, 125);
        datum = "at " + this.hourStr + str + " Hrs";
        g.drawString(datum, 640, 125);
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
        jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, STD);
        this.dec = this.comp.DeclinationRightAscension(1, jd);
        datum = String.valueOf(this.dayString(jd)) + "  " + this.monthString(gmtMonth) + "  " + gmtDate + "   " + (gmtYear + 1900);
        g.drawString(datum, 545, 505);
        datum = String.valueOf(this.makeTimeString(STD)) + str + " UT";
        g.drawString("at " + datum, 640, 505);
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, STD);
        final double hoehe = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
        g.setColor(Color.black);
        if (hoehe < 0.0) {
            g.drawString("Night", 545, 480);
        }
        this.repaint();
    }
}
