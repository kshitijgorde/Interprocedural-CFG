// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.astro;

import java.util.Vector;
import duc.cal.LunarYear;
import duc.cal.LunarDate;
import java.util.Hashtable;
import duc.cal.CalConverter;

public class CalConverterAstro extends CalConverter
{
    public static final double PI = 3.141592653589793;
    public static final double WINTERSOLSTICE = 4.71238898038469;
    private Hashtable cache;
    private IAstroCalculator calculator;
    
    public static int INT(final double d) {
        return (int)Math.floor(d);
    }
    
    public static int MONTH(final int x) {
        int z;
        for (z = x; z > 12; z -= 12) {}
        return z;
    }
    
    public CalConverterAstro() {
        this.cache = new Hashtable();
        this.calculator = new AstroCalculatorCC2K();
    }
    
    private LunarDate[] computeLunarMonths(final int yy) {
        int k = INT((yy - 1900) * 12.3685);
        if (yy < 1000) {
            k += (5000 - yy) / 4000;
        }
        for (double jd = this.newMoonTime(k), sunLong = this.sunLongitudeAtMidnight(this.localDayFromJD(jd)); sunLong > 4.71238898038469; sunLong = this.sunLongitudeAtMidnight(this.localDayFromJD(jd))) {
            --k;
            jd = this.newMoonTime(k);
        }
        int k2 = k + 13;
        final double jd2 = this.newMoonTime(k2);
        final double sunLong2 = this.sunLongitudeAtMidnight(this.localDayFromJD(jd2));
        if (sunLong2 > 4.71238898038469) {
            k2 = k + 12;
        }
        if (k2 == k + 12) {
            final LunarDate[] ret = new LunarDate[12];
            for (int i = 0; i < ret.length; ++i) {
                final int n = this.localDayFromJD(this.newMoonTime(k + i));
                final int year = (i < 2) ? (yy - 1) : yy;
                ret[i] = new LunarDate(1, MONTH(i + 11), year, false, n);
            }
            return ret;
        }
        final int[] moons = new int[14];
        final int[] suns = new int[14];
        int leapIndex = -1;
        for (int j = 0; j < suns.length; ++j) {
            moons[j] = this.localDayFromJD(this.newMoonTime(k + j));
            suns[j] = INT(this.sunLongitudeAtMidnight(moons[j]) * 6.0 / 3.141592653589793);
            if (j > 0 && leapIndex < 0 && suns[j] == suns[j - 1]) {
                leapIndex = j - 1;
            }
        }
        final LunarDate[] ret = new LunarDate[13];
        for (int j = 0; j < ret.length; ++j) {
            final int month = (j < leapIndex) ? MONTH(j + 11) : MONTH(j + 10);
            final boolean leap = j == leapIndex;
            final int year2 = (month >= 11 && j < 6) ? (yy - 1) : yy;
            ret[j] = new LunarDate(1, month, year2, leap, moons[j]);
        }
        return ret;
    }
    
    public LunarYear computeLunarYear(final int yy) {
        final LunarDate[] a1 = this.computeLunarMonths(yy);
        final LunarDate[] a2 = this.computeLunarMonths(yy + 1);
        final Vector v = new Vector(13);
        for (int i = 0; i < a1.length; ++i) {
            if (a1[i].getYear() == yy) {
                v.addElement(a1[i]);
            }
        }
        int jdNext = 0;
        for (int j = 0; j < a2.length; ++j) {
            if (a2[j].getYear() == yy) {
                v.addElement(a2[j]);
            }
            if (a2[j].getMonth() == 1 && !a2[j].isLeap()) {
                jdNext = a2[j].getJd();
            }
        }
        final LunarDate[] a3 = new LunarDate[v.size()];
        v.copyInto(a3);
        final LunarYear ret = new LunarYear(yy, a3, jdNext);
        return ret;
    }
    
    public IAstroCalculator getCalculator() {
        return this.calculator;
    }
    
    public LunarYear getLunarYear(final int yy) {
        return this.computeLunarYear(yy);
    }
    
    private int localDayFromJD(final double jd) {
        return INT(jd + 0.5 + this.getTimeZone() / 1440.0);
    }
    
    private double localMidnight(final int n) {
        return n - 0.5 - this.getTimeZone() / 1440.0;
    }
    
    private double newMoonTime(final int k) {
        final Integer key = new Integer(k);
        Double ret = this.cache.get(key);
        if (ret == null) {
            ret = new Double(this.getCalculator().newMoonTime(k));
            if (this.cache.size() > 100) {
                this.cache.clear();
            }
            this.cache.put(key, ret);
        }
        return ret;
    }
    
    public void setCalculator(final IAstroCalculator calculator) {
        this.calculator = calculator;
    }
    
    private double sunLongitudeAtMidnight(final int day) {
        return this.getCalculator().sunLongitude(this.localMidnight(day));
    }
}
