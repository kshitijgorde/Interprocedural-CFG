// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal;

import java.util.Vector;

public class LunarYear
{
    private LunarDate[] months;
    private int jdNextLunarNY;
    private int year;
    public static final LunarYear DUMMY;
    
    static {
        DUMMY = new LunarYear(0, 0);
    }
    
    public static void getDescription() {
        System.out.println("We use 23 bits of a 32-bits integer to encode a lunar year:\n");
        System.out.println("1 2 3 4  5 6 7 8  8 10 11 12 13 14 15|16|17 18 19 20 21 22 23 24 25 26 27 28|29 30 31 32");
        System.out.println("0 0 0 0  0 0 0 0  0 x  x  x  x  x  x |x |x  x  x  x  x  x  x  x  x  x  x  x |x  x  x  x");
        System.out.println("                    Index of NewYear  Ln 1  2  3  4  5  6  7  8  9  10 11 12 LeapMonth");
        System.out.println("- Last 4 bits (29 to 32): name of the leap month if present (0 means no leap month)");
        System.out.println("- Bits 17 to 28: number of days in lunar months 1, 2, 3... (Month has 30 days if bit set)");
        System.out.println("- Bit 16: set if there is a leap month and that month has 30 days");
        System.out.println("- The remaining bits (NY): number of days between lunar New Year and solar New Year");
        System.out.println("  (i.e., index of lunar New Year in solar year. Index of 1/1 is 0)");
    }
    
    public LunarYear(final int yy, final int code) {
        this.months = new LunarDate[0];
        this.decode(this.year = yy, code);
    }
    
    public LunarYear(final int yy, final LunarDate[] mms, final int jdNext) {
        this.months = new LunarDate[0];
        this.year = yy;
        this.months = mms;
        this.jdNextLunarNY = jdNext;
    }
    
    public int encode() {
        int ret = 0;
        int leapMonth = 0;
        int leapMonthLength = 0;
        final int solarNY = CalUtil.jdFromDate(1, 1, this.getYear());
        final int offsetOfTet = this.getMonths()[0].getJd() - solarNY;
        final int[] regularMonths = new int[12];
        for (int i = 0; i < this.getMonths().length; ++i) {
            final LunarDate ld = this.getMonths()[i];
            final int jdNextMonth = (i < this.getMonths().length - 1) ? this.getMonths()[i + 1].getJd() : this.jdNextLunarNY;
            if (ld.isLeap()) {
                leapMonth = ld.getMonth();
                leapMonthLength = jdNextMonth - ld.getJd();
            }
            else {
                regularMonths[ld.getMonth() - 1] = jdNextMonth - ld.getJd();
            }
        }
        ret |= leapMonth;
        for (int i = 0; i < regularMonths.length; ++i) {
            final int k = (regularMonths[11 - i] != 29) ? 1 : 0;
            ret |= k << i + 4;
        }
        final int j = (leapMonthLength == 30) ? 1 : 0;
        ret |= j << 16;
        ret |= offsetOfTet << 17;
        return ret;
    }
    
    private void decode(final int yy, final int k) {
        final Vector ly = new Vector();
        final int[] possibleMonthLengths = { 29, 30 };
        final int[] regularMonths = new int[12];
        final int offsetOfTet = k >> 17;
        final int leapMonth = k & 0xF;
        final int leapMonthLength = possibleMonthLengths[k >> 16 & 0x1];
        final int solarNY = CalUtil.jdFromDate(1, 1, yy);
        int currentJD = solarNY + offsetOfTet;
        int j = k >> 4;
        for (int i = 0; i < 12; ++i) {
            regularMonths[12 - i - 1] = possibleMonthLengths[j & 0x1];
            j >>= 1;
        }
        if (leapMonth == 0) {
            for (int mm = 1; mm <= 12; ++mm) {
                ly.addElement(new LunarDate(1, mm, yy, false, currentJD));
                currentJD += regularMonths[mm - 1];
            }
        }
        else {
            for (int mm = 1; mm <= leapMonth; ++mm) {
                ly.addElement(new LunarDate(1, mm, yy, false, currentJD));
                currentJD += regularMonths[mm - 1];
            }
            ly.addElement(new LunarDate(1, leapMonth, yy, true, currentJD));
            currentJD += leapMonthLength;
            for (int mm = leapMonth + 1; mm <= 12; ++mm) {
                ly.addElement(new LunarDate(1, mm, yy, false, currentJD));
                currentJD += regularMonths[mm - 1];
            }
        }
        final LunarDate[] a = new LunarDate[ly.size()];
        ly.copyInto(a);
        this.setMonths(a);
        this.jdNextLunarNY = currentJD;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public boolean isLeapYear() {
        return this.getMonths().length == 13;
    }
    
    public LunarDate[] getMonths() {
        return this.months;
    }
    
    public void setMonths(final LunarDate[] dates) {
        this.months = dates;
    }
    
    public int getJdNextLunarNY() {
        return this.jdNextLunarNY;
    }
    
    public void setJdNextLunarNY(final int i) {
        this.jdNextLunarNY = i;
    }
}
