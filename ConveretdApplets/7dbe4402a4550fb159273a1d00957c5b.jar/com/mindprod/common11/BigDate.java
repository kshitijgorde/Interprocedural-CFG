// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common11;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.TimeZone;
import java.io.Serializable;

public final class BigDate implements Cloneable, Serializable, Comparable
{
    public static final boolean isBritish = true;
    private static final int AD_epochAdjustment;
    public static final int APR = 4;
    public static final int APRIL = 4;
    public static final int AUG = 8;
    public static final int AUGUST = 8;
    private static final int BC_epochAdjustment;
    public static final int BYPASSCHECK = 1;
    public static final int CHECK = 0;
    public static final int DEC = 12;
    public static final int DECEMBER = 12;
    public static final int FEB = 2;
    public static final int FEBRUARY = 2;
    public static final int FRI = 5;
    public static final int FRIDAY = 5;
    private static final int GC_firstDD;
    private static final int GC_firstDec_31;
    private static final int GC_firstMM;
    private static final int GC_firstOrdinal;
    private static final int GC_firstYYYY;
    public static final int JAN = 1;
    private static final int Jan_01_0001;
    private static final int Jan_01_0001BC;
    private static final int Jan_01_0004;
    private static final int Jan_01_Leap100RuleYear;
    private static final int Jan_01_Leap400RuleYear;
    public static final int JANUARY = 1;
    public static final int JUL = 7;
    public static final int JULY = 7;
    public static final int JUN = 6;
    public static final int JUNE = 6;
    private static final int Leap100RuleYYYY;
    private static final int Leap400RuleYYYY;
    public static final int MAR = 3;
    public static final int MARCH = 3;
    public static final int MAX_ORDINAL;
    public static final int MAX_YEAR = 999999;
    public static final int MAY = 5;
    public static final int MIN_ORDINAL;
    public static final int MIN_YEAR = -999999;
    private static final int missingDays;
    public static final int MON = 1;
    public static final int MONDAY = 1;
    private static final int MondayIsZeroAdjustment = 3;
    public static final int NORMALISE = 2;
    public static final int NORMALIZE;
    public static final int NOV = 11;
    public static final int NOVEMBER = 11;
    public static final int NULL_ORDINAL = Integer.MIN_VALUE;
    public static final int OCT = 10;
    public static final int OCTOBER = 10;
    private static final int OJC_lastDD;
    private static final int OJC_lastMM;
    private static final int OJC_lastYYYY;
    public static final int SAT = 6;
    public static final int SATURDAY = 6;
    public static final int SEP = 9;
    public static final int SEPTEMBER = 9;
    public static final int SUN = 0;
    public static final int SUNDAY = 0;
    private static final int SundayIsZeroAdjustment = 4;
    public static final int THU = 4;
    public static final int THURSDAY = 4;
    public static final int TUE = 2;
    public static final int TUESDAY = 2;
    public static final int WED = 3;
    public static final int WEDNESDAY = 3;
    public static final long NULL_TIMESTAMP = Long.MIN_VALUE;
    static final long serialVersionUID = 34L;
    private static final String[] dayAbbr;
    private static final String[] dayName;
    private static final String[] monthAbbr;
    private static final String[] monthName;
    private static final int[] leap_daysInYearPriorToMonthTable;
    private static final int[] usual_daysInYearPriorToMonthTable;
    private static final int[] usual_DaysPerMonthTable;
    private static int[] leap_dddToMMTable;
    private static int[] usual_dddToMMTable;
    protected transient int dd;
    private int how;
    protected transient int mm;
    protected int ordinal;
    protected transient int yyyy;
    
    public static BigDate UTCToday() {
        return new BigDate((int)(System.currentTimeMillis() / 86400000L));
    }
    
    public static int[] age(BigDate birthDate, BigDate asOf) {
        if (birthDate.ordinal >= asOf.ordinal) {
            if (birthDate.ordinal == asOf.ordinal) {
                return new int[] { 0, 0, 0 };
            }
            final BigDate temp = asOf;
            asOf = birthDate;
            birthDate = temp;
        }
        final int birthYYYY = birthDate.yyyy;
        final int birthMM = birthDate.mm;
        final int birthDD = birthDate.dd;
        final int asOfYYYY = asOf.yyyy;
        final int asOfMM = asOf.mm;
        final int asOfDD = asOf.dd;
        int ageInYears = asOfYYYY - birthYYYY;
        int ageInMonths = asOfMM - birthMM;
        int ageInDays = asOfDD - birthDD;
        if (ageInDays < 0) {
            ageInDays += daysInMonth(birthMM, birthYYYY);
            --ageInMonths;
        }
        if (ageInMonths < 0) {
            ageInMonths += 12;
            --ageInYears;
        }
        if (birthYYYY < 0 && asOfYYYY > 0) {
            --ageInYears;
        }
        if (ageInYears < 0) {
            ageInYears = 0;
            ageInMonths = 0;
            ageInDays = 0;
        }
        final int[] result = { ageInYears, ageInMonths, ageInDays };
        return result;
    }
    
    public static int calendarDayOfWeek(final int ordinal) {
        return dayOfWeek(ordinal) + 1;
    }
    
    public static final int compare(final int yyyy1, final int mm1, final int dd1, final int yyyy2, final int mm2, final int dd2) {
        int diff = yyyy1 - yyyy2;
        if (diff != 0) {
            return diff;
        }
        diff = mm1 - mm2;
        if (diff != 0) {
            return diff;
        }
        return dd1 - dd2;
    }
    
    public static String dayAbbr(final int dayOfWeek) {
        return BigDate.dayAbbr[dayOfWeek];
    }
    
    public static String dayName(final int dayOfWeek) {
        return BigDate.dayName[dayOfWeek];
    }
    
    public static int dayOfWeek(final int ordinal) {
        return (ordinal == Integer.MIN_VALUE) ? 0 : ((ordinal + 4 - BigDate.MIN_ORDINAL) % 7);
    }
    
    public static int daysInMonth(final int mm, final boolean leap) {
        if (mm != 2) {
            return BigDate.usual_DaysPerMonthTable[mm - 1];
        }
        return leap ? 29 : 28;
    }
    
    public static int daysInMonth(final int mm, final int yyyy) {
        if (mm != 2) {
            return BigDate.usual_DaysPerMonthTable[mm - 1];
        }
        return isLeap(yyyy) ? 29 : 28;
    }
    
    public static int flooredMulDiv(final int multiplicand, final int multiplier, final int divisor) {
        final long result = multiplicand * multiplier;
        if (result >= 0L) {
            return (int)(result / divisor);
        }
        return (int)((result - divisor + 1L) / divisor);
    }
    
    public static String getCopyright() {
        return "BigDate 5.1 freeware Copyright: (c) 1997-2011 Roedy Green, Canadian Mind Products, http://mindprod.com roedyg@mindprod.com";
    }
    
    public static boolean isAnniversary(final BigDate birthDate, final BigDate asOf) {
        if (asOf.compareTo(birthDate) < 0) {
            return false;
        }
        final int asOfMM = asOf.mm;
        final int birthMM = birthDate.mm;
        if (birthMM != asOfMM) {
            return false;
        }
        final int birthDD = birthDate.dd;
        final int asOfDD = asOf.dd;
        return birthDD == asOfDD || (birthDD == 29 && asOfDD == 28 && birthMM == 2 && !isLeap(asOf.yyyy));
    }
    
    public static boolean isLeap(final int yyyy) {
        if (yyyy >= BigDate.Leap100RuleYYYY) {
            return (yyyy & 0x3) == 0x0 && (yyyy % 100 != 0 || (yyyy >= BigDate.Leap400RuleYYYY && yyyy % 400 == 0));
        }
        if (yyyy < 0) {
            return (yyyy + 1 & 0x3) == 0x0;
        }
        return (yyyy & 0x3) == 0x0;
    }
    
    public static boolean isValid(final String yyyy_mm_dd) {
        final int[] ymd = relaxedParse(yyyy_mm_dd);
        return ymd != null && isValid(ymd[0], ymd[1], ymd[2]);
    }
    
    public static boolean isValid(final int yyyy, final int mm, final int dd) {
        if (yyyy == 0) {
            return mm == 0 && dd == 0;
        }
        return yyyy >= -999999 && yyyy <= 999999 && mm >= 1 && mm <= 12 && dd >= 1 && dd <= 31 && (yyyy != BigDate.OJC_lastYYYY || mm != BigDate.OJC_lastMM || BigDate.OJC_lastDD >= dd || dd >= BigDate.GC_firstDD) && dd <= daysInMonth(mm, yyyy);
    }
    
    public static int isoDayOfWeek(final int ordinal) {
        return (ordinal == Integer.MIN_VALUE) ? 0 : ((ordinal + 3 - BigDate.MIN_ORDINAL) % 7 + 1);
    }
    
    public static BigDate localToday() {
        return today(TimeZone.getDefault());
    }
    
    public static String monthAbbr(final int mm) {
        return BigDate.monthAbbr[mm];
    }
    
    public static String monthName(final int mm) {
        return BigDate.monthName[mm];
    }
    
    public static int nthXXXDay(final int which, final int dayOfWeek, final int yyyy, final int mm) {
        final int dayOfWeekOf1st = dayOfWeek(toOrdinal(yyyy, mm, 1));
        final int dayOfMonthOfFirstDesiredDay = (dayOfWeek - dayOfWeekOf1st + 7) % 7 + 1;
        int dayOfMonthOfNthDesiredDay = dayOfMonthOfFirstDesiredDay + (which - 1) * 7;
        if (which >= 5 && dayOfMonthOfNthDesiredDay > daysInMonth(mm, yyyy)) {
            dayOfMonthOfNthDesiredDay -= 7;
        }
        return dayOfMonthOfNthDesiredDay;
    }
    
    public static int ordinalOfnthXXXDay(final int which, final int dayOfWeek, final int yyyy, final int mm) {
        final int dayOfMonthOfNthDesiredDay = nthXXXDay(which, dayOfWeek, yyyy, mm);
        return toOrdinal(yyyy, mm, dayOfMonthOfNthDesiredDay);
    }
    
    public static int parseYYYY(final String yyyyString) throws NumberFormatException {
        int ye = yyyyString.length();
        boolean bc = false;
        if (yyyyString.endsWith("BC")) {
            ye -= 2;
            bc = true;
        }
        else if (yyyyString.endsWith("AD")) {
            ye -= 2;
        }
        int yyyy = Integer.parseInt(yyyyString.substring(0, ye));
        if (bc) {
            yyyy = -yyyy;
        }
        return yyyy;
    }
    
    public static BigDate parseYYYYmmdd(final String incomplete) {
        String complete = null;
        switch (ST.countInstances(incomplete, '-')) {
            case 2: {
                complete = incomplete;
                break;
            }
            case 1: {
                if (incomplete.endsWith("BC") || incomplete.endsWith("AD")) {
                    complete = incomplete.substring(0, incomplete.length() - 2) + "-01" + incomplete.substring(incomplete.length() - 2);
                    break;
                }
                complete = incomplete + "-01";
                break;
            }
            case 0: {
                if (incomplete.endsWith("BC") || incomplete.endsWith("AD")) {
                    complete = incomplete.substring(0, incomplete.length() - 2) + "-01-01" + incomplete.substring(incomplete.length() - 2);
                    break;
                }
                complete = incomplete + "-01-01";
                break;
            }
            default: {
                throw new NumberFormatException("BigDate.parseYYYYmmdd expects YYYY-MM-DD or YYYY-MM or YYYY.");
            }
        }
        return new BigDate(complete);
    }
    
    public static int toOrdinal(final String yyyy_mm_dd) {
        final int[] ymd = relaxedParse(yyyy_mm_dd);
        if (ymd == null) {
            throw new NumberFormatException("unparseable date: " + yyyy_mm_dd);
        }
        return toOrdinal(ymd[0], ymd[1], ymd[2]);
    }
    
    public static int toOrdinal(final int yyyy, final int mm, final int dd) {
        if (yyyy == 0 && mm == 0 && dd == 0) {
            return Integer.MIN_VALUE;
        }
        final int missingDayAdjust = (yyyy == BigDate.OJC_lastYYYY && ((mm == BigDate.OJC_lastMM && dd > BigDate.OJC_lastDD) || mm > BigDate.OJC_lastMM)) ? BigDate.missingDays : 0;
        return jan01OfYear(yyyy) + daysInYearPriorToMonth(mm, isLeap(yyyy)) - missingDayAdjust + dd - 1;
    }
    
    public static BigDate today(final TimeZone timeZone) {
        final BigDate d = new BigDate();
        d.setDateAtTime(System.currentTimeMillis(), timeZone);
        return d;
    }
    
    public BigDate() {
        this.dd = 0;
        this.mm = 0;
        this.ordinal = Integer.MIN_VALUE;
        this.yyyy = 0;
    }
    
    public BigDate(final double prolepticJulianDay) {
        this.dd = 0;
        this.mm = 0;
        this.ordinal = Integer.MIN_VALUE;
        this.yyyy = 0;
        this.setOrdinal((int)((long)(prolepticJulianDay + 0.5) - 2440588L));
    }
    
    public BigDate(final int ordinal) {
        this.dd = 0;
        this.mm = 0;
        this.ordinal = Integer.MIN_VALUE;
        this.yyyy = 0;
        this.set(ordinal);
    }
    
    public BigDate(final String yyyy_mm_dd) {
        this.dd = 0;
        this.mm = 0;
        this.ordinal = Integer.MIN_VALUE;
        this.yyyy = 0;
        final int[] ymd = relaxedParse(yyyy_mm_dd);
        if (ymd == null) {
            throw new NumberFormatException("unparseable date: " + yyyy_mm_dd);
        }
        this.set(ymd[0], ymd[1], ymd[2], 0);
    }
    
    public BigDate(final BigDate b) {
        this.dd = 0;
        this.mm = 0;
        this.ordinal = Integer.MIN_VALUE;
        this.yyyy = 0;
        this.ordinal = b.ordinal;
        this.yyyy = b.yyyy;
        this.mm = b.mm;
        this.dd = b.dd;
    }
    
    public BigDate(final Date utc, final TimeZone timeZone) {
        this.dd = 0;
        this.mm = 0;
        this.ordinal = Integer.MIN_VALUE;
        this.yyyy = 0;
        this.setDateAtTime(utc.getTime(), timeZone);
    }
    
    public BigDate(final int yyyy, final int mm, final int dd) {
        this.dd = 0;
        this.mm = 0;
        this.ordinal = Integer.MIN_VALUE;
        this.set(yyyy, mm, dd, this.yyyy = 0);
    }
    
    public BigDate(final int yyyy, final int mm, final int dd, final int how) {
        this.dd = 0;
        this.mm = 0;
        this.ordinal = Integer.MIN_VALUE;
        this.yyyy = 0;
        this.set(yyyy, mm, dd, how);
    }
    
    public final void addDays(final int days) {
        if (days == 0) {
            return;
        }
        this.ordinal += days;
        this.toGregorian();
    }
    
    public int calendarDayOfWeek() {
        return calendarDayOfWeek(this.ordinal);
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }
    
    public final int compareTo(final Object other) {
        return this.ordinal - ((BigDate)other).ordinal;
    }
    
    public int dayOfWeek() {
        return dayOfWeek(this.ordinal);
    }
    
    public final boolean equals(final Object d) {
        return d == this || (d instanceof BigDate && this.ordinal == ((BigDate)d).ordinal);
    }
    
    public final int getCalendarDayOfWeek() {
        return calendarDayOfWeek(this.ordinal);
    }
    
    public final int getDD() {
        return this.dd;
    }
    
    public final int getDDD() {
        return (this.ordinal == Integer.MIN_VALUE) ? 0 : (this.ordinal - jan01OfYear(this.yyyy) + 1);
    }
    
    public final Date getDate(final TimeZone timeZone) {
        return (this.ordinal == Integer.MIN_VALUE) ? null : new Date(this.getTimeStamp(timeZone));
    }
    
    public final int getDayOfWeek() {
        return dayOfWeek(this.ordinal);
    }
    
    public final int getISODayOfWeek() {
        return isoDayOfWeek(this.ordinal);
    }
    
    public final int getISOWeekNumber() {
        if (this.ordinal < BigDate.Jan_01_Leap100RuleYear) {
            return 0;
        }
        int jan04Ordinal = jan01OfYear(this.yyyy) + 3;
        int jan04DayOfWeek = (jan04Ordinal + 3 - BigDate.MIN_ORDINAL) % 7;
        int week1StartOrdinal = jan04Ordinal - jan04DayOfWeek;
        if (this.ordinal < week1StartOrdinal) {
            jan04Ordinal = jan01OfYear(this.yyyy - 1) + 3;
            jan04DayOfWeek = (jan04Ordinal + 3 - BigDate.MIN_ORDINAL) % 7;
            week1StartOrdinal = jan04Ordinal - jan04DayOfWeek;
        }
        else if (this.mm == 12) {
            jan04Ordinal = jan01OfYear(this.yyyy + 1) + 3;
            jan04DayOfWeek = (jan04Ordinal + 3 - BigDate.MIN_ORDINAL) % 7;
            final int week1StartNextOrdinal = jan04Ordinal - jan04DayOfWeek;
            if (this.ordinal >= week1StartNextOrdinal) {
                week1StartOrdinal = week1StartNextOrdinal;
            }
        }
        return (this.ordinal - week1StartOrdinal) / 7 + 1;
    }
    
    public final Date getLocalDate() {
        return (this.ordinal == Integer.MIN_VALUE) ? null : new Date(this.getLocalTimeStamp());
    }
    
    public final long getLocalTimeStamp() {
        return this.getTimeStamp(TimeZone.getDefault());
    }
    
    public final int getMM() {
        return this.mm;
    }
    
    public final int getOrdinal() {
        return this.ordinal;
    }
    
    public final double getProlepticJulianDay() {
        if (this.ordinal == Integer.MIN_VALUE) {
            return Double.MIN_VALUE;
        }
        return this.ordinal + 2440588L;
    }
    
    public final int getSeason() {
        return (this.mm + 9) % 12 / 3;
    }
    
    public final long getTimeStamp(final TimeZone timeZone) {
        if (this.ordinal == Integer.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        final int offsetInMillis = timeZone.getOffset((this.ordinal >= BigDate.Jan_01_0001) ? 1 : 0, this.yyyy, this.mm - 1, this.dd, this.getCalendarDayOfWeek(), 0);
        return this.ordinal * 86400000L - offsetInMillis;
    }
    
    public final Date getUTCDate() {
        return (this.ordinal == Integer.MIN_VALUE) ? null : new Date(this.getUTCTimeStamp());
    }
    
    public final long getUTCTimeStamp() {
        return (this.ordinal == Integer.MIN_VALUE) ? Long.MIN_VALUE : (this.ordinal * 86400000L);
    }
    
    public final int getWeekNumber() {
        if (this.ordinal < BigDate.Jan_01_Leap100RuleYear) {
            return 0;
        }
        final int jan01 = jan01OfYear(this.yyyy);
        final int jan01DayOfWeek = dayOfWeek(jan01);
        final int sundayOnOrBeforeJan01Ordinal = jan01 - jan01DayOfWeek;
        return (this.ordinal - sundayOnOrBeforeJan01Ordinal) / 7 + 1;
    }
    
    public final int getYYYY() {
        return this.yyyy;
    }
    
    public final int hashCode() {
        return this.ordinal;
    }
    
    public BigDate nearestXXXDay(final int dayOfWeek) {
        int diff = dayOfWeek - this.dayOfWeek();
        if (diff >= 4) {
            diff -= 7;
        }
        else if (diff <= -4) {
            diff += 7;
        }
        return new BigDate(this.ordinal + diff);
    }
    
    public final int ordinal() {
        return this.ordinal;
    }
    
    public final void set(final int ordinal) {
        if (this.ordinal == ordinal) {
            return;
        }
        this.ordinal = ordinal;
        this.toGregorian();
    }
    
    public final void set(final int yyyy, final int mm, final int dd) {
        this.set(yyyy, mm, dd, 0);
    }
    
    public final void set(final int yyyy, final int mm, final int dd, final int how) {
        if (this.yyyy == yyyy && this.mm == mm && this.dd == dd && this.how == how) {
            return;
        }
        this.yyyy = yyyy;
        this.mm = mm;
        this.dd = dd;
        switch (this.how = how) {
            case 0: {
                if (!isValid(yyyy, mm, dd)) {
                    throw new NumberFormatException("invalid date: " + yyyy + "/" + mm + "/" + dd);
                }
                break;
            }
            case 2: {
                this.normalise();
                break;
            }
        }
        this.calcOrdinal();
    }
    
    public void setDateAtTime(final long utcTimestamp, final TimeZone timeZone) {
        this.setOrdinal((int)(utcTimestamp / 86400000L));
        final int offsetInMillis = timeZone.getOffset((this.ordinal >= BigDate.Jan_01_0001) ? 1 : 0, this.yyyy, this.mm - 1, this.dd, this.getCalendarDayOfWeek(), (int)(utcTimestamp % 86400000L));
        this.setOrdinal((int)((utcTimestamp + offsetInMillis) / 86400000L));
    }
    
    public final void setOrdinal(final int ordinal) {
        if (this.ordinal == ordinal) {
            return;
        }
        this.ordinal = ordinal;
        this.toGregorian();
    }
    
    public String toDowMMDDYY() {
        if (this.ordinal == Integer.MIN_VALUE) {
            return "";
        }
        return BigDate.dayAbbr[this.getDayOfWeek()] + " " + ST.toLZ(this.mm, 2) + "/" + ST.toLZ(this.dd, 2) + "/" + ST.toLZ(this.yyyy % 100, 2);
    }
    
    public String toString() {
        if (this.ordinal == Integer.MIN_VALUE) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(13);
        sb.append(Integer.toString(Math.abs(this.yyyy)));
        sb.append("-");
        sb.append(ST.toLZ(this.mm, 2));
        sb.append("-");
        sb.append(ST.toLZ(this.dd, 2));
        if (this.yyyy < 1000) {
            if (this.yyyy < 0) {
                sb.append(" BC");
            }
            else {
                sb.append(" AD");
            }
        }
        return sb.toString();
    }
    
    public String toYYYYString() {
        if (this.ordinal == Integer.MIN_VALUE) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(7);
        sb.append(Integer.toString(Math.abs(this.yyyy)));
        if (this.yyyy < 1000) {
            if (this.yyyy < 0) {
                sb.append(" BC");
            }
            else {
                sb.append(" AD");
            }
        }
        return sb.toString();
    }
    
    protected static int daysInYearPriorToMonth(final int mm, final boolean leap) {
        return leap ? BigDate.leap_daysInYearPriorToMonthTable[mm - 1] : BigDate.usual_daysInYearPriorToMonthTable[mm - 1];
    }
    
    protected static int dddToMM(final int ddd, final boolean leap) {
        return leap ? BigDate.leap_dddToMMTable[ddd - 1] : BigDate.usual_dddToMMTable[ddd - 1];
    }
    
    protected static int jan01OfYear(final int yyyy) {
        if (yyyy < 0) {
            final int leapAdjustment = (3 - yyyy) / 4;
            return yyyy * 365 - leapAdjustment + BigDate.BC_epochAdjustment;
        }
        int leapAdjustment = (yyyy - 1) / 4;
        final int missingDayAdjust = (yyyy > BigDate.GC_firstYYYY) ? BigDate.missingDays : 0;
        if (yyyy > BigDate.Leap100RuleYYYY) {
            leapAdjustment -= (yyyy - BigDate.Leap100RuleYYYY + 99) / 100;
        }
        if (yyyy > BigDate.Leap400RuleYYYY) {
            leapAdjustment += (yyyy - BigDate.Leap400RuleYYYY + 399) / 400;
        }
        return yyyy * 365 + leapAdjustment - missingDayAdjust + BigDate.AD_epochAdjustment;
    }
    
    private static int[] relaxedParse(final String yyyy_mm_dd) {
        try {
            final StringBuffer yearSB = new StringBuffer(6);
            final int yyyy_mm_dd_length = yyyy_mm_dd.length();
            String mm_dd = "";
        Label_0341:
            for (int i = 0; i < yyyy_mm_dd_length; ++i) {
                final char c = yyyy_mm_dd.charAt(i);
                switch (c) {
                    case '-':
                    case '.':
                    case '/':
                    case '_': {
                        mm_dd = yyyy_mm_dd.substring(i + 1);
                        break Label_0341;
                    }
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9': {
                        yearSB.append(c);
                        break;
                    }
                    case ' ':
                    case ',': {
                        break;
                    }
                    default: {
                        return null;
                    }
                }
            }
            if (1 > yearSB.length() || yearSB.length() > 6) {
                return null;
            }
            final int yyyy = Integer.parseInt(yearSB.toString());
            final StringBuffer monthSB = new StringBuffer(2);
            final int mm_dd_length = mm_dd.length();
            String dd_ad = "";
        Label_0715:
            for (int j = 0; j < mm_dd_length; ++j) {
                final char c2 = mm_dd.charAt(j);
                switch (c2) {
                    case '-':
                    case '.':
                    case '/':
                    case '_': {
                        dd_ad = mm_dd.substring(j + 1);
                        break Label_0715;
                    }
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9': {
                        monthSB.append(c2);
                        break;
                    }
                    case ' ':
                    case ',': {
                        break;
                    }
                    default: {
                        return null;
                    }
                }
            }
            if (1 > monthSB.length() || monthSB.length() > 2) {
                return null;
            }
            final int mm = Integer.parseInt(monthSB.toString());
            final StringBuffer daySB = new StringBuffer(2);
            final int dd_ad_length = dd_ad.length();
            String ad = "";
        Label_1102:
            for (int k = 0; k < dd_ad_length; ++k) {
                final char c3 = dd_ad.charAt(k);
                switch (c3) {
                    case 'A':
                    case 'B':
                    case 'a':
                    case 'b': {
                        ad = dd_ad.substring(k);
                        break Label_1102;
                    }
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9': {
                        daySB.append(c3);
                        break;
                    }
                    case ' ':
                    case ',': {
                        break;
                    }
                    default: {
                        return null;
                    }
                }
            }
            if (1 > daySB.length() || daySB.length() > 2) {
                return null;
            }
            final int dd = Integer.parseInt(daySB.toString());
            if (1 > dd || dd > 31) {
                return null;
            }
            final StringBuffer adSB = new StringBuffer(2);
            for (int ad_length = ad.length(), l = 0; l < ad_length; ++l) {
                final char c4 = ad.charAt(l);
                switch (c4) {
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D': {
                        adSB.append(c4);
                        break;
                    }
                    case 'a':
                    case 'b':
                    case 'c':
                    case 'd': {
                        adSB.append(Character.toUpperCase(c4));
                        break;
                    }
                    case ' ':
                    case ',': {
                        break;
                    }
                    default: {
                        return null;
                    }
                }
            }
            final String adString = adSB.toString();
            if (adString.length() == 0 || adString.equals("AD")) {
                return new int[] { yyyy, mm, dd };
            }
            if (adString.equals("BC")) {
                return new int[] { -yyyy, mm, dd };
            }
            return null;
        }
        catch (NumberFormatException e) {
            return null;
        }
    }
    
    protected final void calcOrdinal() {
        this.ordinal = toOrdinal(this.yyyy, this.mm, this.dd);
    }
    
    protected final void normalise() {
        if (isValid(this.yyyy, this.mm, this.dd)) {
            return;
        }
        if (this.mm > 12) {
            this.yyyy += (this.mm - 1) / 12;
            this.mm = (this.mm - 1) % 12 + 1;
            if (isValid(this.yyyy, this.mm, this.dd)) {
                return;
            }
        }
        else if (this.mm <= 0) {
            this.yyyy -= -this.mm / 12 + 1;
            this.mm = 12 - -this.mm % 12;
            if (isValid(this.yyyy, this.mm, this.dd)) {
                return;
            }
        }
        if (isValid(this.yyyy, this.mm, 1)) {
            final int oldDD = this.dd;
            this.dd = 1;
            this.calcOrdinal();
            this.ordinal += oldDD - 1;
            this.toGregorian();
            if (isValid(this.yyyy, this.mm, this.dd)) {
                return;
            }
        }
        throw new NumberFormatException("date cannot be normalised: " + this.yyyy + "/" + this.mm + "/" + this.dd);
    }
    
    private void readObject(final ObjectInputStream s) throws ClassNotFoundException, IOException {
        s.defaultReadObject();
        try {
            this.toGregorian();
        }
        catch (NumberFormatException e) {
            throw new IOException("bad serialized BigDate");
        }
    }
    
    protected final void toGregorian() {
        if (this.ordinal == Integer.MIN_VALUE) {
            this.yyyy = 0;
            this.mm = 0;
            this.dd = 0;
            return;
        }
        if (this.ordinal > BigDate.MAX_ORDINAL) {
            throw new NumberFormatException("invalid ordinal date: " + this.ordinal);
        }
        if (this.ordinal >= BigDate.GC_firstOrdinal) {
            this.yyyy = BigDate.Leap400RuleYYYY + flooredMulDiv(this.ordinal - BigDate.Jan_01_Leap400RuleYear, 10000, 3652425);
        }
        else if (this.ordinal >= BigDate.Jan_01_0001) {
            this.yyyy = 4 + flooredMulDiv(this.ordinal - BigDate.Jan_01_0004, 100, 36525);
        }
        else {
            if (this.ordinal < BigDate.MIN_ORDINAL) {
                throw new NumberFormatException("invalid ordinal date: " + this.ordinal);
            }
            this.yyyy = -1 + flooredMulDiv(this.ordinal - BigDate.Jan_01_0001BC, 100, 36525);
        }
        int aim = this.ordinal + 1;
        if (BigDate.GC_firstOrdinal <= this.ordinal && this.ordinal <= BigDate.GC_firstDec_31) {
            aim += BigDate.missingDays;
        }
        int ddd;
        for (ddd = aim - jan01OfYear(this.yyyy); ddd <= 0; ddd = aim - jan01OfYear(this.yyyy)) {
            --this.yyyy;
        }
        boolean leap;
        for (leap = isLeap(this.yyyy); ddd > (leap ? 366 : 365); ddd = aim - jan01OfYear(this.yyyy), leap = isLeap(this.yyyy)) {
            ++this.yyyy;
        }
        this.mm = dddToMM(ddd, leap);
        this.dd = ddd - daysInYearPriorToMonth(this.mm, leap);
    }
    
    static {
        dayAbbr = new String[] { "sun", "mon", "tue", "wed", "thu", "fri", "sat" };
        dayName = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        monthAbbr = new String[] { "???", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        monthName = new String[] { "unknown", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        leap_daysInYearPriorToMonthTable = new int[] { 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335 };
        usual_daysInYearPriorToMonthTable = new int[] { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
        usual_DaysPerMonthTable = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        NORMALIZE = 2;
        OJC_lastYYYY = 1752;
        OJC_lastMM = 9;
        OJC_lastDD = 2;
        GC_firstYYYY = 1752;
        GC_firstMM = 9;
        GC_firstDD = 14;
        Leap100RuleYYYY = (BigDate.GC_firstYYYY + 99) / 100 * 100;
        Leap400RuleYYYY = (BigDate.GC_firstYYYY + 399) / 400 * 400;
        missingDays = BigDate.GC_firstDD - BigDate.OJC_lastDD - 1;
        AD_epochAdjustment = -719529;
        BC_epochAdjustment = BigDate.AD_epochAdjustment + 365;
        MIN_ORDINAL = toOrdinal(-999999, 1, 1);
        Jan_01_0001BC = toOrdinal(-1, 1, 1);
        Jan_01_0001 = toOrdinal(1, 1, 1);
        Jan_01_0004 = toOrdinal(4, 1, 1);
        GC_firstOrdinal = toOrdinal(BigDate.GC_firstYYYY, BigDate.GC_firstMM, BigDate.GC_firstDD);
        GC_firstDec_31 = toOrdinal(BigDate.GC_firstYYYY, 12, 31);
        Jan_01_Leap100RuleYear = toOrdinal(BigDate.Leap100RuleYYYY, 1, 1);
        Jan_01_Leap400RuleYear = toOrdinal(BigDate.Leap400RuleYYYY, 1, 1);
        MAX_ORDINAL = toOrdinal(999999, 12, 31);
        BigDate.usual_dddToMMTable = new int[365];
        int dddi = 0;
        for (int mmi = 1; mmi <= 12; ++mmi) {
            for (int last = daysInMonth(mmi, false), ddi = 0; ddi < last; ++ddi) {
                BigDate.usual_dddToMMTable[dddi++] = mmi;
            }
        }
        BigDate.leap_dddToMMTable = new int[366];
        dddi = 0;
        for (int mmi = 1; mmi <= 12; ++mmi) {
            for (int last = daysInMonth(mmi, true), ddi = 0; ddi < last; ++ddi) {
                BigDate.leap_dddToMMTable[dddi++] = mmi;
            }
        }
    }
}
