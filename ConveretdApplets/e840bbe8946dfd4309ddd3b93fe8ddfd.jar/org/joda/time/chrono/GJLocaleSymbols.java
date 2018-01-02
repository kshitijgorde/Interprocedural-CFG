// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.chrono;

import org.joda.time.IllegalFieldValueException;
import org.joda.time.DateTimeFieldType;
import java.util.Comparator;
import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.TreeMap;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

class GJLocaleSymbols
{
    private static final int FAST_CACHE_SIZE = 64;
    private static final GJLocaleSymbols[] cFastCache;
    private static WeakHashMap cCache;
    private final WeakReference iLocale;
    private final String[] iEras;
    private final String[] iDaysOfWeek;
    private final String[] iShortDaysOfWeek;
    private final String[] iMonths;
    private final String[] iShortMonths;
    private final String[] iHalfday;
    private final TreeMap iParseEras;
    private final TreeMap iParseDaysOfWeek;
    private final TreeMap iParseMonths;
    private final int iMaxEraLength;
    private final int iMaxDayOfWeekLength;
    private final int iMaxShortDayOfWeekLength;
    private final int iMaxMonthLength;
    private final int iMaxShortMonthLength;
    private final int iMaxHalfdayLength;
    
    public static GJLocaleSymbols forLocale(Locale default1) {
        if (default1 == null) {
            default1 = Locale.getDefault();
        }
        final int n = System.identityHashCode(default1) & 0x3F;
        GJLocaleSymbols gjLocaleSymbols = GJLocaleSymbols.cFastCache[n];
        if (gjLocaleSymbols != null && gjLocaleSymbols.iLocale.get() == default1) {
            return gjLocaleSymbols;
        }
        synchronized (GJLocaleSymbols.cCache) {
            gjLocaleSymbols = GJLocaleSymbols.cCache.get(default1);
            if (gjLocaleSymbols == null) {
                gjLocaleSymbols = new GJLocaleSymbols(default1);
                GJLocaleSymbols.cCache.put(default1, gjLocaleSymbols);
            }
        }
        return GJLocaleSymbols.cFastCache[n] = gjLocaleSymbols;
    }
    
    private static String[] realignMonths(final String[] array) {
        final String[] array2 = new String[13];
        for (int i = 1; i < 13; ++i) {
            array2[i] = array[i - 1];
        }
        return array2;
    }
    
    private static String[] realignDaysOfWeek(final String[] array) {
        final String[] array2 = new String[8];
        for (int i = 1; i < 8; ++i) {
            array2[i] = array[(i < 7) ? (i + 1) : 1];
        }
        return array2;
    }
    
    private static void addSymbols(final TreeMap treeMap, final String[] array, final Integer[] array2) {
        int length = array.length;
        while (--length >= 0) {
            final String s = array[length];
            if (s != null) {
                treeMap.put(s, array2[length]);
            }
        }
    }
    
    private static void addNumerals(final TreeMap treeMap, final int n, final int n2, final Integer[] array) {
        for (int i = n; i <= n2; ++i) {
            treeMap.put(String.valueOf(i).intern(), array[i]);
        }
    }
    
    private static int maxLength(final String[] array) {
        int n = 0;
        int length = array.length;
        while (--length >= 0) {
            final String s = array[length];
            if (s != null) {
                final int length2 = s.length();
                if (length2 <= n) {
                    continue;
                }
                n = length2;
            }
        }
        return n;
    }
    
    private GJLocaleSymbols(final Locale locale) {
        this.iLocale = new WeakReference((T)locale);
        final DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
        this.iEras = dateFormatSymbols.getEras();
        this.iDaysOfWeek = realignDaysOfWeek(dateFormatSymbols.getWeekdays());
        this.iShortDaysOfWeek = realignDaysOfWeek(dateFormatSymbols.getShortWeekdays());
        this.iMonths = realignMonths(dateFormatSymbols.getMonths());
        this.iShortMonths = realignMonths(dateFormatSymbols.getShortMonths());
        this.iHalfday = dateFormatSymbols.getAmPmStrings();
        final Integer[] array = new Integer[13];
        for (int i = 0; i < 13; ++i) {
            array[i] = new Integer(i);
        }
        addSymbols(this.iParseEras = new TreeMap((Comparator<? super K>)String.CASE_INSENSITIVE_ORDER), this.iEras, array);
        if ("en".equals(locale.getLanguage())) {
            this.iParseEras.put("BCE", array[0]);
            this.iParseEras.put("CE", array[1]);
        }
        addSymbols(this.iParseDaysOfWeek = new TreeMap((Comparator<? super K>)String.CASE_INSENSITIVE_ORDER), this.iDaysOfWeek, array);
        addSymbols(this.iParseDaysOfWeek, this.iShortDaysOfWeek, array);
        addNumerals(this.iParseDaysOfWeek, 1, 7, array);
        addSymbols(this.iParseMonths = new TreeMap((Comparator<? super K>)String.CASE_INSENSITIVE_ORDER), this.iMonths, array);
        addSymbols(this.iParseMonths, this.iShortMonths, array);
        addNumerals(this.iParseMonths, 1, 12, array);
        this.iMaxEraLength = maxLength(this.iEras);
        this.iMaxDayOfWeekLength = maxLength(this.iDaysOfWeek);
        this.iMaxShortDayOfWeekLength = maxLength(this.iShortDaysOfWeek);
        this.iMaxMonthLength = maxLength(this.iMonths);
        this.iMaxShortMonthLength = maxLength(this.iShortMonths);
        this.iMaxHalfdayLength = maxLength(this.iHalfday);
    }
    
    public String eraValueToText(final int n) {
        return this.iEras[n];
    }
    
    public int eraTextToValue(final String s) {
        final Integer n = this.iParseEras.get(s);
        if (n != null) {
            return n;
        }
        throw new IllegalFieldValueException(DateTimeFieldType.era(), s);
    }
    
    public int getEraMaxTextLength() {
        return this.iMaxEraLength;
    }
    
    public String monthOfYearValueToText(final int n) {
        return this.iMonths[n];
    }
    
    public String monthOfYearValueToShortText(final int n) {
        return this.iShortMonths[n];
    }
    
    public int monthOfYearTextToValue(final String s) {
        final Integer n = this.iParseMonths.get(s);
        if (n != null) {
            return n;
        }
        throw new IllegalFieldValueException(DateTimeFieldType.monthOfYear(), s);
    }
    
    public int getMonthMaxTextLength() {
        return this.iMaxMonthLength;
    }
    
    public int getMonthMaxShortTextLength() {
        return this.iMaxShortMonthLength;
    }
    
    public String dayOfWeekValueToText(final int n) {
        return this.iDaysOfWeek[n];
    }
    
    public String dayOfWeekValueToShortText(final int n) {
        return this.iShortDaysOfWeek[n];
    }
    
    public int dayOfWeekTextToValue(final String s) {
        final Integer n = this.iParseDaysOfWeek.get(s);
        if (n != null) {
            return n;
        }
        throw new IllegalFieldValueException(DateTimeFieldType.dayOfWeek(), s);
    }
    
    public int getDayOfWeekMaxTextLength() {
        return this.iMaxDayOfWeekLength;
    }
    
    public int getDayOfWeekMaxShortTextLength() {
        return this.iMaxShortDayOfWeekLength;
    }
    
    public String halfdayValueToText(final int n) {
        return this.iHalfday[n];
    }
    
    public int halfdayTextToValue(final String s) {
        final String[] iHalfday = this.iHalfday;
        int length = iHalfday.length;
        while (--length >= 0) {
            if (iHalfday[length].equalsIgnoreCase(s)) {
                return length;
            }
        }
        throw new IllegalFieldValueException(DateTimeFieldType.halfdayOfDay(), s);
    }
    
    public int getHalfdayMaxTextLength() {
        return this.iMaxHalfdayLength;
    }
    
    static {
        cFastCache = new GJLocaleSymbols[64];
        GJLocaleSymbols.cCache = new WeakHashMap();
    }
}
