import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class compute extends Canvas
{
    double DEC;
    double RA;
    double EOT;
    String str1;
    String str2;
    String str3;
    String str4;
    String hoursGMTStr;
    String minutesGMTStr;
    String secondsGMTStr;
    String dateGMTStr;
    String datGMTStr;
    String monthGMTStr;
    int monthGMT;
    String versStr;
    
    compute(final Date date, final String versStr) {
        this.versStr = versStr;
        date.getHours();
        final int minutes = date.getMinutes();
        final int seconds = date.getSeconds();
        date.getDate();
        int n = date.getMonth() + 1;
        final int n2 = date.getYear() + 1900;
        final String string = date.toString();
        this.str1 = String.valueOf(string.substring(0, 10)) + " " + string.substring(string.length() - 4, string.length());
        this.str2 = string.substring(11, 19);
        this.datGMTStr = date.toGMTString();
        final int length = this.datGMTStr.length();
        this.dateGMTStr = this.datGMTStr.substring(0, length - 22);
        this.monthGMTStr = this.datGMTStr.substring(3, length - 18);
        if (this.monthGMTStr.equals("Jan")) {
            n = 1;
        }
        if (this.monthGMTStr.equals("Feb")) {
            n = 2;
        }
        if (this.monthGMTStr.equals("Mar")) {
            n = 3;
        }
        if (this.monthGMTStr.equals("Apr")) {
            n = 4;
        }
        if (this.monthGMTStr.equals("May")) {
            n = 5;
        }
        if (this.monthGMTStr.equals("Jun")) {
            n = 6;
        }
        if (this.monthGMTStr.equals("Jul")) {
            n = 7;
        }
        if (this.monthGMTStr.equals("Aug")) {
            n = 8;
        }
        if (this.monthGMTStr.equals("Sep")) {
            n = 9;
        }
        if (this.monthGMTStr.equals("Oct")) {
            n = 10;
        }
        if (this.monthGMTStr.equals("Nov")) {
            n = 11;
        }
        if (this.monthGMTStr.equals("Dez")) {
            n = 12;
        }
        final int int1 = Integer.parseInt(this.dateGMTStr);
        this.hoursGMTStr = this.datGMTStr.substring(12, 14);
        this.hoursGMTStr = this.datGMTStr.substring(length - 12, length - 10);
        this.EOT = this.eot(int1, n, n2, Integer.parseInt(this.hoursGMTStr) + minutes / 60.0 + seconds / 3600.0);
        long round = Math.round(this.frac(Math.abs(this.EOT)) * 60.0);
        if (round == 60L) {
            round = 0L;
            if (this.EOT >= 0.0) {
                ++this.EOT;
            }
            else {
                --this.EOT;
            }
        }
        String s;
        if (round > 9L) {
            s = ":";
        }
        else {
            s = ":0";
        }
        this.str3 = "EoT:  " + (int)this.EOT + s + round + " min";
        String.valueOf(this.DEC);
        long round2 = Math.round(this.frac(Math.abs(this.DEC)) * 60.0);
        if (round2 == 60L) {
            round2 = 0L;
            if (this.DEC >= 0.0) {
                ++this.DEC;
            }
            else {
                --this.DEC;
            }
        }
        String s2 = String.valueOf(round2);
        if (round2 < 10L) {
            s2 = "0" + s2;
        }
        this.str4 = "Dec: " + Math.abs((int)this.DEC) + "° " + s2 + "'";
        if (this.DEC >= 0.0) {
            this.str4 = String.valueOf(this.str4) + " N";
        }
        else {
            this.str4 = String.valueOf(this.str4) + " S";
        }
        this.repaint();
    }
    
    double frac(double n) {
        n -= (int)n;
        if (n < 0.0) {
            ++n;
        }
        return n;
    }
    
    double JD(final int n, int n2, int n3, final double n4) {
        final double n5 = 10000.0 * n3 + 100.0 * n2 + n;
        if (n2 <= 2) {
            n2 += 12;
            --n3;
        }
        double n6;
        if (n5 <= 1.58210041E7) {
            n6 = -2 + (n3 + 4716) / 4 - 1179;
        }
        else {
            n6 = n3 / 400 - n3 / 100 + n3 / 4;
        }
        return 365.0 * n3 - 679004.0 + n6 + (int)(30.6001 * (n2 + 1)) + n + n4 / 24.0 + 2400000.5;
    }
    
    public double sunL(final double n) {
        double n2 = (280.46645 + 36000.76983 * n + 3.032E-4 * n * n) % 360.0;
        if (n2 < 0.0) {
            n2 += 360.0;
        }
        return n2;
    }
    
    public double deltaPSI(final double n) {
        final double sunL = this.sunL(n);
        double n2 = (218.3165 + 481267.8813 * n) % 360.0;
        if (n2 < 0.0) {
            n2 += 360.0;
        }
        final double n3 = 125.04452 - 1934.136261 * n + 0.0020708 * n * n + n * n * n / 450000.0;
        return (-17.2 * Math.sin(0.017453292519943295 * n3) - 1.32 * Math.sin(0.03490658503988659 * sunL) - 0.23 * Math.sin(0.03490658503988659 * n2) + 0.21 * Math.sin(0.03490658503988659 * n3)) / 3600.0;
    }
    
    public double EPS(final double n) {
        final double sunL = this.sunL(n);
        final double n2 = 218.3165 + 481267.8813 * n;
        final double n3 = 23.43929111111111 - (46.815 * n + 5.9E-4 * n * n - 0.001813 * n * n * n) / 3600.0;
        final double n4 = 125.04452 - 1934.136261 * n + 0.0020708 * n * n + n * n * n / 450000.0;
        return n3 + (9.2 * Math.cos(0.017453292519943295 * n4) + 0.57 * Math.cos(0.03490658503988659 * sunL) + 0.1 * Math.cos(0.03490658503988659 * n2) - 0.09 * Math.cos(0.03490658503988659 * n4)) / 3600.0;
    }
    
    public double eot(final int n, final int n2, final int n3, final double n4) {
        final double n5 = (this.JD(n, n2, n3, n4) - 2451545.0) / 36525.0;
        double n6 = this.sunL(n5) - 0.0057183 - this.RightAscension(n5) + this.deltaPSI(n5) * Math.cos(0.017453292519943295 * this.EPS(n5));
        if (n6 > 5.0) {
            n6 -= 360.0;
        }
        return n6 * 4.0;
    }
    
    public double RightAscension(final double n) {
        final double sunL = this.sunL(n);
        double n2 = (357.5291 + 35999.0503 * n - 1.559E-4 * n * n - 4.8E-7 * n * n * n) % 360.0;
        if (n2 < 0.0) {
            n2 += 360.0;
        }
        final double n3 = sunL + ((1.9146 - 0.004817 * n - 1.4E-5 * n * n) * Math.sin(0.017453292519943295 * n2) + (0.019993 - 1.01E-4 * n) * Math.sin(0.03490658503988659 * n2) + 2.9E-4 * Math.sin(0.05235987755982989 * n2));
        final double n4 = this.EPS(n) + 0.00256 * Math.cos(0.017453292519943295 * (125.04 - 1934.136 * n));
        final double n5 = n3 - 0.00569 - 0.00478 * Math.sin(0.017453292519943295 * (125.04 - 1934.136 * n));
        double n6 = Math.atan2(Math.cos(0.017453292519943295 * n4) * Math.sin(0.017453292519943295 * n5), Math.cos(0.017453292519943295 * n5)) / 0.017453292519943295;
        if (n6 < 0.0) {
            n6 += 360.0;
        }
        this.DEC = Math.asin(Math.sin(0.017453292519943295 * n4) * Math.sin(0.017453292519943295 * n5)) / 0.017453292519943295;
        return n6;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(new Color(255, 255, 0));
        graphics.setFont(new Font("Courier", 0, 14));
        graphics.drawString(this.str1, 18, 18);
        graphics.drawString(this.str2, 50, 36);
        graphics.drawString(this.str3, 18, 54);
        graphics.setFont(new Font("Courier", 0, 12));
        if (this.EOT > 0.0) {
            graphics.drawString("Dial is Fast", 35, 72);
        }
        else {
            graphics.drawString("Dial is Slow", 35, 72);
        }
        graphics.setFont(new Font("Courier", 0, 14));
        graphics.drawString(this.str4, 18, 90);
        graphics.setFont(new Font("Helvetica", 0, 9));
        graphics.drawString(this.versStr, 5, 108);
    }
}
