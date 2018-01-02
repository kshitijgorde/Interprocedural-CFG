// 
// Decompiled by Procyon v0.5.30
// 

package astro;

public class ATime
{
    private int nYear;
    private int nMonth;
    private int nDay;
    private int nHour;
    private int nMin;
    private double fSec;
    private double fJd;
    private double fTimezone;
    private double fT;
    private double fT2;
    private static final String[] strMonthAbbr;
    public static final int F_INCTIME = 1;
    public static final int F_DECTIME = -1;
    
    public static String getMonthAbbr(final int n) {
        return ATime.strMonthAbbr[n - 1];
    }
    
    private double makeJd() {
        int nYear = this.nYear;
        int nMonth = this.nMonth;
        final double n = this.nDay + this.nHour / 24.0 + this.nMin / 24.0 / 60.0 + this.fSec / 24.0 / 60.0 / 60.0;
        if (nMonth < 3) {
            nMonth += 12;
            --nYear;
        }
        double n2 = Math.floor(365.25 * nYear) + Math.floor(30.59 * (nMonth - 2)) + n + 1721086.5;
        if (n2 > 2299160.5) {
            n2 += Math.floor(nYear / 400.0) - Math.floor(nYear / 100.0) + 2.0;
        }
        return n2;
    }
    
    private double makeT() {
        final double n = (this.fJd - 2442412.5) / 365.25;
        return n + (0.0317 * n + 1.43) * 1.0E-6;
    }
    
    private double makeT2() {
        return (this.fJd - 2451545.0) / 36525.0;
    }
    
    private void getDate(double n) {
        n += 0.5;
        double floor = Math.floor(n);
        if (floor >= 2299160.5) {
            final double floor2 = Math.floor((floor - 1867216.25) / 36524.25);
            floor += floor2 - Math.floor(floor2 / 4.0) + 1.0;
        }
        final double n2 = Math.floor(floor) + 1524.0;
        final double floor3 = Math.floor((n2 - 122.1) / 365.25);
        final double floor4 = Math.floor(365.25 * floor3);
        final double floor5 = Math.floor((n2 - floor4) / 30.6001);
        final double n3 = n2 - floor4 - Math.floor(30.6001 * floor5) + (n - Math.floor(n));
        this.nMonth = (int)Math.floor(floor5 - ((floor5 >= 13.5) ? 13 : 1) + 0.5);
        this.nYear = (int)Math.floor(floor3 - ((this.nMonth > 2) ? 4716 : 4715) + 0.5);
        this.nDay = (int)Math.floor(n3);
        final double n4 = (n3 - this.nDay) * 24.0;
        this.nHour = (int)Math.floor(n4);
        final double n5 = (n4 - this.nHour) * 60.0;
        this.nMin = (int)Math.floor(n5);
        this.fSec = (n5 - this.nMin) * 60.0;
    }
    
    public void changeDate(final TimeSpan timeSpan, final int n) {
        final double n2 = this.nHour * 60.0 * 60.0 + this.nMin * 60.0 + this.fSec;
        final double n3 = timeSpan.nHour * 60.0 * 60.0 + timeSpan.nMin * 60.0 + timeSpan.fSec;
        double fmod = n2 + ((n == 1) ? n3 : (-n3));
        int n4;
        if (0.0 <= fmod && fmod < 86400.0) {
            n4 = 0;
        }
        else if (fmod >= 86400.0) {
            n4 = (int)Math.floor(fmod / 24.0 / 60.0 / 60.0);
            fmod = UdMath.fmod(fmod, 86400.0);
        }
        else {
            n4 = (int)Math.ceil(fmod / 24.0 / 60.0 / 60.0) - 1;
            fmod = UdMath.fmod(fmod, 86400.0) + 86400.0;
        }
        final int nHour = (int)Math.floor(fmod / 60.0 / 60.0);
        final int nMin = (int)Math.floor(fmod / 60.0) - nHour * 60;
        final double fSec = fmod - (nHour * 60.0 * 60.0 + nMin * 60.0);
        final ATime aTime = new ATime(new ATime(this.getYear(), this.getMonth(), this.getDay(), 12, 0, 0.0, 0.0).getJd() + ((n == 1) ? (n4 + timeSpan.nDay) : ((double)(n4 - timeSpan.nDay))), 0.0);
        int year = aTime.getYear();
        final int month = aTime.getMonth();
        int day = aTime.getDay();
        int n5 = month + ((n == 1) ? timeSpan.nMonth : (-timeSpan.nMonth));
        if (1 > n5) {
            year -= n5 / 12 + 1;
            n5 = 12 + n5 % 12;
        }
        else if (n5 > 12) {
            year += n5 / 12;
            n5 = 1 + (n5 - 1) % 12;
        }
        final int n6 = year + ((n == 1) ? timeSpan.nYear : (-timeSpan.nYear));
        if (n6 == 1582 && n5 == 10) {
            if (5 <= day && day < 10) {
                day = 4;
            }
            else if (10 <= day && day < 15) {
                day = 15;
            }
        }
        final ATime aTime2 = new ATime(n6, n5, day, 12, 0, 0.0, 0.0);
        final int year2 = aTime2.getYear();
        final int month2 = aTime2.getMonth();
        final int day2 = aTime2.getDay();
        this.nYear = year2;
        this.nMonth = month2;
        this.nDay = day2;
        this.nHour = nHour;
        this.nMin = nMin;
        this.fSec = fSec;
        this.fJd = this.makeJd() - this.fTimezone / 24.0;
        this.fT = this.makeT();
        this.fT2 = this.makeT2();
    }
    
    public ATime(final int nYear, final int nMonth, final int nDay, final int nHour, final int nMin, final double fSec, final double fTimezone) {
        this.nYear = nYear;
        this.nMonth = nMonth;
        this.nDay = nDay;
        this.nHour = nHour;
        this.nMin = nMin;
        this.fSec = fSec;
        this.fJd = this.makeJd() - fTimezone / 24.0;
        this.fTimezone = fTimezone;
        this.fT = this.makeT();
        this.fT2 = this.makeT2();
    }
    
    public ATime(final int nYear, final int nMonth, final double n, final double fTimezone) {
        this.nYear = nYear;
        this.nMonth = nMonth;
        this.nDay = (int)Math.floor(n);
        final double n2 = (n - this.nDay) * 24.0;
        this.nHour = (int)Math.floor(n2);
        final double n3 = (n2 - this.nHour) * 60.0;
        this.nMin = (int)Math.floor(n3);
        this.fSec = (n3 - this.nMin) * 60.0;
        this.fJd = this.makeJd() - fTimezone / 24.0;
        this.fTimezone = fTimezone;
        this.fT = this.makeT();
        this.fT2 = this.makeT2();
    }
    
    public ATime(final double fJd, final double fTimezone) {
        this.fJd = fJd;
        this.getDate(fJd + (this.fTimezone = fTimezone) / 24.0);
        this.fT = this.makeT();
        this.fT2 = this.makeT2();
    }
    
    public ATime(final ATime aTime) {
        this.nYear = aTime.nYear;
        this.nMonth = aTime.nMonth;
        this.nDay = aTime.nDay;
        this.nHour = aTime.nHour;
        this.nMin = aTime.nMin;
        this.fSec = aTime.fSec;
        this.fJd = aTime.fJd;
        this.fTimezone = aTime.fTimezone;
        this.fT = aTime.fT;
        this.fT2 = aTime.fT2;
    }
    
    public int getYear() {
        return this.nYear;
    }
    
    public int getMonth() {
        return this.nMonth;
    }
    
    public int getDay() {
        return this.nDay;
    }
    
    public int getHour() {
        return this.nHour;
    }
    
    public int getMinute() {
        return this.nMin;
    }
    
    public double getSecond() {
        return this.fSec;
    }
    
    public double getTimezone() {
        return this.fTimezone;
    }
    
    public double getJd() {
        return this.fJd;
    }
    
    public double getT() {
        return this.fT;
    }
    
    public double getT2() {
        return this.fT2;
    }
    
    public static double getEp(final double n) {
        double n2 = (n - 2451545.0) / 36525.0;
        if (n2 > 30.0) {
            n2 = 30.0;
        }
        else if (n2 < -30.0) {
            n2 = -30.0;
        }
        return (23.43929111 - 0.013004166666666667 * n2 - 1.638888888888889E-7 * n2 * n2 + 5.03611111111111E-7 * n2 * n2 * n2) * 3.141592653589793 / 180.0;
    }
    
    public String toString() {
        return new String(Integer.toString(this.nYear) + "/" + Integer.toString(this.nMonth) + "/" + Integer.toString(this.nDay) + " " + Integer.toString(this.nHour) + ":" + Integer.toString(this.nMin) + ":" + Double.toString(this.fSec) + " = " + Double.toString(this.fJd) + " (TZ:" + Double.toString(this.fTimezone) + ")");
    }
    
    static {
        strMonthAbbr = new String[] { "Jan.", "Feb.", "Mar.", "Apr.", "May ", "June", "July", "Aug.", "Sep.", "Oct.", "Nov.", "Dec." };
    }
}
