// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal;

public class CalUtil
{
    public static int jdFromDate(final int dd, final int mm, final int yy) {
        if (yy >= 0) {
            return jdFromDatePos(dd, mm, yy);
        }
        return jdFromDateNeg(dd, mm, yy);
    }
    
    private static int jdFromDatePos(final int dd, final int mm, final int yy) {
        final int a = (14 - mm) / 12;
        final int y = yy + 4800 - a;
        final int m = mm + 12 * a - 3;
        int jd = dd + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
        if (jd < 2299161) {
            jd = dd + (153 * m + 2) / 5 + 365 * y + y / 4 - 32083;
        }
        return jd;
    }
    
    private static int jdFromDateNeg(final int dd, final int mm, final int yy) {
        final int cent = yy / 100;
        final int ret = jdFromDatePos(dd, mm, yy - 100 * cent);
        return ret + 36525 * cent;
    }
    
    public static int[] jdToDate(final int jd) {
        if (jd >= 0) {
            return jdToDatePos(jd);
        }
        return jdToDateNeg(jd);
    }
    
    private static int[] jdToDatePos(final int jd) {
        int b;
        int c;
        if (jd > 2299160) {
            final int a = jd + 32044;
            b = (4 * a + 3) / 146097;
            c = a - b * 146097 / 4;
        }
        else {
            b = 0;
            c = jd + 32082;
        }
        final int d = (4 * c + 3) / 1461;
        final int e = c - 1461 * d / 4;
        final int m = (5 * e + 2) / 153;
        final int day = e - (153 * m + 2) / 5 + 1;
        final int month = m + 3 - 12 * (m / 10);
        final int year = b * 100 + d - 4800 + m / 10;
        return new int[] { day, month, year };
    }
    
    private static int[] jdToDateNeg(final int jd) {
        int julianCenturies = jd / 36525;
        int rest = jd % 36525;
        if (rest < 0) {
            --julianCenturies;
            rest += 36525;
        }
        final int[] ret = jdToDatePos(rest);
        ret[2] += 100 * julianCenturies;
        return ret;
    }
    
    public static void printDate(final int dd, final int mm, final int yy, final StringBuffer sb, final boolean year) {
        if (dd < 10) {
            sb.append('0');
        }
        sb.append(dd).append("/");
        if (mm < 10) {
            sb.append('0');
        }
        sb.append(mm);
        if (year) {
            if (yy > 0) {
                sb.append("/").append(yy);
            }
            else {
                sb.append("/").append(-yy + 1).append(" BC");
            }
        }
    }
    
    public static void printTime(final int hour, final int min, final int sec, final StringBuffer sb, final boolean printSecond) {
        if (hour < 10) {
            sb.append('0');
        }
        sb.append(hour).append(":");
        if (min < 10) {
            sb.append('0');
        }
        sb.append(min);
        if (printSecond) {
            sb.append(':');
            if (sec < 10) {
                sb.append('0');
            }
            sb.append(sec);
        }
    }
    
    public static int[] parseTime(final int seconds, final boolean roundToMin) {
        int n = seconds;
        int ss = n % 60;
        if (roundToMin && ss > 30) {
            n += 60 - ss;
            ss = 0;
        }
        n -= 86400 * (n / 86400);
        final int hh = n / 3600;
        final int mm = (n - 3600 * hh) / 60;
        return new int[] { hh, mm, ss };
    }
}
