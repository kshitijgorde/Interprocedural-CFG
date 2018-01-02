// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.lang.time;

import org.apache.commons.lang.Validate;
import java.util.HashMap;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParsePosition;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import java.text.FieldPosition;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Map;
import java.text.Format;

public class FastDateFormat extends Format
{
    private static final long serialVersionUID = 1L;
    public static final int FULL = 0;
    public static final int LONG = 1;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;
    private static String cDefaultPattern;
    private static final Map cInstanceCache;
    private static final Map cDateInstanceCache;
    private static final Map cTimeInstanceCache;
    private static final Map cDateTimeInstanceCache;
    private static final Map cTimeZoneDisplayCache;
    private final String mPattern;
    private final TimeZone mTimeZone;
    private final boolean mTimeZoneForced;
    private final Locale mLocale;
    private final boolean mLocaleForced;
    private transient Rule[] mRules;
    private transient int mMaxLengthEstimate;
    
    public static FastDateFormat getInstance() {
        return getInstance(getDefaultPattern(), null, null);
    }
    
    public static FastDateFormat getInstance(final String pattern) {
        return getInstance(pattern, null, null);
    }
    
    public static FastDateFormat getInstance(final String pattern, final TimeZone timeZone) {
        return getInstance(pattern, timeZone, null);
    }
    
    public static FastDateFormat getInstance(final String pattern, final Locale locale) {
        return getInstance(pattern, null, locale);
    }
    
    public static synchronized FastDateFormat getInstance(final String pattern, final TimeZone timeZone, final Locale locale) {
        final FastDateFormat emptyFormat = new FastDateFormat(pattern, timeZone, locale);
        FastDateFormat format = FastDateFormat.cInstanceCache.get(emptyFormat);
        if (format == null) {
            format = emptyFormat;
            format.init();
            FastDateFormat.cInstanceCache.put(format, format);
        }
        return format;
    }
    
    public static FastDateFormat getDateInstance(final int style) {
        return getDateInstance(style, null, null);
    }
    
    public static FastDateFormat getDateInstance(final int style, final Locale locale) {
        return getDateInstance(style, null, locale);
    }
    
    public static FastDateFormat getDateInstance(final int style, final TimeZone timeZone) {
        return getDateInstance(style, timeZone, null);
    }
    
    public static synchronized FastDateFormat getDateInstance(final int style, final TimeZone timeZone, Locale locale) {
        Object key = new Integer(style);
        if (timeZone != null) {
            key = new Pair(key, timeZone);
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        key = new Pair(key, locale);
        FastDateFormat format = FastDateFormat.cDateInstanceCache.get(key);
        if (format == null) {
            try {
                final SimpleDateFormat formatter = (SimpleDateFormat)DateFormat.getDateInstance(style, locale);
                final String pattern = formatter.toPattern();
                format = getInstance(pattern, timeZone, locale);
                FastDateFormat.cDateInstanceCache.put(key, format);
            }
            catch (ClassCastException ex) {
                throw new IllegalArgumentException("No date pattern for locale: " + locale);
            }
        }
        return format;
    }
    
    public static FastDateFormat getTimeInstance(final int style) {
        return getTimeInstance(style, null, null);
    }
    
    public static FastDateFormat getTimeInstance(final int style, final Locale locale) {
        return getTimeInstance(style, null, locale);
    }
    
    public static FastDateFormat getTimeInstance(final int style, final TimeZone timeZone) {
        return getTimeInstance(style, timeZone, null);
    }
    
    public static synchronized FastDateFormat getTimeInstance(final int style, final TimeZone timeZone, Locale locale) {
        Object key = new Integer(style);
        if (timeZone != null) {
            key = new Pair(key, timeZone);
        }
        if (locale != null) {
            key = new Pair(key, locale);
        }
        FastDateFormat format = FastDateFormat.cTimeInstanceCache.get(key);
        if (format == null) {
            if (locale == null) {
                locale = Locale.getDefault();
            }
            try {
                final SimpleDateFormat formatter = (SimpleDateFormat)DateFormat.getTimeInstance(style, locale);
                final String pattern = formatter.toPattern();
                format = getInstance(pattern, timeZone, locale);
                FastDateFormat.cTimeInstanceCache.put(key, format);
            }
            catch (ClassCastException ex) {
                throw new IllegalArgumentException("No date pattern for locale: " + locale);
            }
        }
        return format;
    }
    
    public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle) {
        return getDateTimeInstance(dateStyle, timeStyle, null, null);
    }
    
    public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle, final Locale locale) {
        return getDateTimeInstance(dateStyle, timeStyle, null, locale);
    }
    
    public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle, final TimeZone timeZone) {
        return getDateTimeInstance(dateStyle, timeStyle, timeZone, null);
    }
    
    public static synchronized FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle, final TimeZone timeZone, Locale locale) {
        Object key = new Pair(new Integer(dateStyle), new Integer(timeStyle));
        if (timeZone != null) {
            key = new Pair(key, timeZone);
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        key = new Pair(key, locale);
        FastDateFormat format = FastDateFormat.cDateTimeInstanceCache.get(key);
        if (format == null) {
            try {
                final SimpleDateFormat formatter = (SimpleDateFormat)DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
                final String pattern = formatter.toPattern();
                format = getInstance(pattern, timeZone, locale);
                FastDateFormat.cDateTimeInstanceCache.put(key, format);
            }
            catch (ClassCastException ex) {
                throw new IllegalArgumentException("No date time pattern for locale: " + locale);
            }
        }
        return format;
    }
    
    static synchronized String getTimeZoneDisplay(final TimeZone tz, final boolean daylight, final int style, final Locale locale) {
        final Object key = new TimeZoneDisplayKey(tz, daylight, style, locale);
        String value = FastDateFormat.cTimeZoneDisplayCache.get(key);
        if (value == null) {
            value = tz.getDisplayName(daylight, style, locale);
            FastDateFormat.cTimeZoneDisplayCache.put(key, value);
        }
        return value;
    }
    
    private static synchronized String getDefaultPattern() {
        if (FastDateFormat.cDefaultPattern == null) {
            FastDateFormat.cDefaultPattern = new SimpleDateFormat().toPattern();
        }
        return FastDateFormat.cDefaultPattern;
    }
    
    protected FastDateFormat(final String pattern, TimeZone timeZone, Locale locale) {
        if (pattern == null) {
            throw new IllegalArgumentException("The pattern must not be null");
        }
        this.mPattern = pattern;
        this.mTimeZoneForced = (timeZone != null);
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        this.mTimeZone = timeZone;
        this.mLocaleForced = (locale != null);
        if (locale == null) {
            locale = Locale.getDefault();
        }
        this.mLocale = locale;
    }
    
    protected void init() {
        final List rulesList = this.parsePattern();
        this.mRules = rulesList.toArray(new Rule[rulesList.size()]);
        int len = 0;
        int i = this.mRules.length;
        while (--i >= 0) {
            len += this.mRules[i].estimateLength();
        }
        this.mMaxLengthEstimate = len;
    }
    
    protected List parsePattern() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/text/DateFormatSymbols;
        //     3: dup            
        //     4: aload_0         /* this */
        //     5: getfield        org/apache/commons/lang/time/FastDateFormat.mLocale:Ljava/util/Locale;
        //     8: invokespecial   java/text/DateFormatSymbols.<init>:(Ljava/util/Locale;)V
        //    11: astore_1        /* symbols */
        //    12: new             Ljava/util/ArrayList;
        //    15: dup            
        //    16: invokespecial   java/util/ArrayList.<init>:()V
        //    19: astore_2        /* rules */
        //    20: aload_1         /* symbols */
        //    21: invokevirtual   java/text/DateFormatSymbols.getEras:()[Ljava/lang/String;
        //    24: astore_3        /* ERAs */
        //    25: aload_1         /* symbols */
        //    26: invokevirtual   java/text/DateFormatSymbols.getMonths:()[Ljava/lang/String;
        //    29: astore          months
        //    31: aload_1         /* symbols */
        //    32: invokevirtual   java/text/DateFormatSymbols.getShortMonths:()[Ljava/lang/String;
        //    35: astore          shortMonths
        //    37: aload_1         /* symbols */
        //    38: invokevirtual   java/text/DateFormatSymbols.getWeekdays:()[Ljava/lang/String;
        //    41: astore          weekdays
        //    43: aload_1         /* symbols */
        //    44: invokevirtual   java/text/DateFormatSymbols.getShortWeekdays:()[Ljava/lang/String;
        //    47: astore          shortWeekdays
        //    49: aload_1         /* symbols */
        //    50: invokevirtual   java/text/DateFormatSymbols.getAmPmStrings:()[Ljava/lang/String;
        //    53: astore          AmPmStrings
        //    55: aload_0         /* this */
        //    56: getfield        org/apache/commons/lang/time/FastDateFormat.mPattern:Ljava/lang/String;
        //    59: invokevirtual   java/lang/String.length:()I
        //    62: istore          length
        //    64: iconst_1       
        //    65: newarray        I
        //    67: astore          indexRef
        //    69: iconst_0       
        //    70: istore          i
        //    72: iload           i
        //    74: iload           length
        //    76: if_icmpge       964
        //    79: aload           indexRef
        //    81: iconst_0       
        //    82: iload           i
        //    84: iastore        
        //    85: aload_0         /* this */
        //    86: aload_0         /* this */
        //    87: getfield        org/apache/commons/lang/time/FastDateFormat.mPattern:Ljava/lang/String;
        //    90: aload           indexRef
        //    92: invokevirtual   org/apache/commons/lang/time/FastDateFormat.parseToken:(Ljava/lang/String;[I)Ljava/lang/String;
        //    95: astore          token
        //    97: aload           indexRef
        //    99: iconst_0       
        //   100: iaload         
        //   101: istore          i
        //   103: aload           token
        //   105: invokevirtual   java/lang/String.length:()I
        //   108: istore          tokenLen
        //   110: iload           tokenLen
        //   112: ifne            118
        //   115: goto            964
        //   118: aload           token
        //   120: iconst_0       
        //   121: invokevirtual   java/lang/String.charAt:(I)C
        //   124: istore          c
        //   126: iload           c
        //   128: tableswitch {
        //               78: 872
        //               79: 921
        //               80: 921
        //               81: 921
        //               82: 921
        //               83: 921
        //               84: 921
        //               85: 921
        //               86: 921
        //               87: 921
        //               88: 921
        //               89: 921
        //               90: 921
        //               91: 921
        //               92: 921
        //               93: 921
        //               94: 921
        //               95: 921
        //               96: 921
        //               97: 921
        //               98: 921
        //               99: 921
        //              100: 921
        //              101: 921
        //              102: 921
        //              103: 921
        //              104: 921
        //              105: 921
        //              106: 921
        //              107: 695
        //              108: 668
        //              109: 708
        //              110: 480
        //              111: 616
        //              112: 921
        //              113: 921
        //              114: 781
        //              115: 921
        //              116: 520
        //              117: 921
        //              118: 921
        //              119: 921
        //              120: 921
        //              121: 921
        //              122: 655
        //              123: 921
        //              124: 921
        //              125: 921
        //              126: 733
        //              127: 921
        //              128: 921
        //              129: 850
        //              130: 921
        //              131: 921
        //              132: 921
        //              133: 921
        //              134: 921
        //              135: 921
        //              136: 745
        //              137: 921
        //              138: 921
        //              139: 584
        //              140: 921
        //              141: 921
        //              142: 921
        //              143: 596
        //              144: 921
        //              145: 921
        //              146: 761
        //              147: 921
        //              148: 629
        //              149: 921
        //              150: 921
        //              151: 921
        //              152: 921
        //              153: 921
        //              154: 642
        //              155: 921
        //              156: 921
        //              157: 921
        //              158: 721
        //              159: 921
        //              160: 494
        //              161: 794
        //          default: 921
        //        }
        //   480: new             Lorg/apache/commons/lang/time/FastDateFormat$TextField;
        //   483: dup            
        //   484: iconst_0       
        //   485: aload_3         /* ERAs */
        //   486: invokespecial   org/apache/commons/lang/time/FastDateFormat$TextField.<init>:(I[Ljava/lang/String;)V
        //   489: astore          rule
        //   491: goto            949
        //   494: iload           tokenLen
        //   496: iconst_4       
        //   497: if_icmplt       512
        //   500: aload_0         /* this */
        //   501: iconst_1       
        //   502: iload           tokenLen
        //   504: invokevirtual   org/apache/commons/lang/time/FastDateFormat.selectNumberRule:(II)Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;
        //   507: astore          rule
        //   509: goto            949
        //   512: getstatic       org/apache/commons/lang/time/FastDateFormat$TwoDigitYearField.INSTANCE:Lorg/apache/commons/lang/time/FastDateFormat$TwoDigitYearField;
        //   515: astore          rule
        //   517: goto            949
        //   520: iload           tokenLen
        //   522: iconst_4       
        //   523: if_icmplt       541
        //   526: new             Lorg/apache/commons/lang/time/FastDateFormat$TextField;
        //   529: dup            
        //   530: iconst_2       
        //   531: aload           months
        //   533: invokespecial   org/apache/commons/lang/time/FastDateFormat$TextField.<init>:(I[Ljava/lang/String;)V
        //   536: astore          rule
        //   538: goto            949
        //   541: iload           tokenLen
        //   543: iconst_3       
        //   544: if_icmpne       562
        //   547: new             Lorg/apache/commons/lang/time/FastDateFormat$TextField;
        //   550: dup            
        //   551: iconst_2       
        //   552: aload           shortMonths
        //   554: invokespecial   org/apache/commons/lang/time/FastDateFormat$TextField.<init>:(I[Ljava/lang/String;)V
        //   557: astore          rule
        //   559: goto            949
        //   562: iload           tokenLen
        //   564: iconst_2       
        //   565: if_icmpne       576
        //   568: getstatic       org/apache/commons/lang/time/FastDateFormat$TwoDigitMonthField.INSTANCE:Lorg/apache/commons/lang/time/FastDateFormat$TwoDigitMonthField;
        //   571: astore          rule
        //   573: goto            949
        //   576: getstatic       org/apache/commons/lang/time/FastDateFormat$UnpaddedMonthField.INSTANCE:Lorg/apache/commons/lang/time/FastDateFormat$UnpaddedMonthField;
        //   579: astore          rule
        //   581: goto            949
        //   584: aload_0         /* this */
        //   585: iconst_5       
        //   586: iload           tokenLen
        //   588: invokevirtual   org/apache/commons/lang/time/FastDateFormat.selectNumberRule:(II)Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;
        //   591: astore          rule
        //   593: goto            949
        //   596: new             Lorg/apache/commons/lang/time/FastDateFormat$TwelveHourField;
        //   599: dup            
        //   600: aload_0         /* this */
        //   601: bipush          10
        //   603: iload           tokenLen
        //   605: invokevirtual   org/apache/commons/lang/time/FastDateFormat.selectNumberRule:(II)Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;
        //   608: invokespecial   org/apache/commons/lang/time/FastDateFormat$TwelveHourField.<init>:(Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;)V
        //   611: astore          rule
        //   613: goto            949
        //   616: aload_0         /* this */
        //   617: bipush          11
        //   619: iload           tokenLen
        //   621: invokevirtual   org/apache/commons/lang/time/FastDateFormat.selectNumberRule:(II)Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;
        //   624: astore          rule
        //   626: goto            949
        //   629: aload_0         /* this */
        //   630: bipush          12
        //   632: iload           tokenLen
        //   634: invokevirtual   org/apache/commons/lang/time/FastDateFormat.selectNumberRule:(II)Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;
        //   637: astore          rule
        //   639: goto            949
        //   642: aload_0         /* this */
        //   643: bipush          13
        //   645: iload           tokenLen
        //   647: invokevirtual   org/apache/commons/lang/time/FastDateFormat.selectNumberRule:(II)Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;
        //   650: astore          rule
        //   652: goto            949
        //   655: aload_0         /* this */
        //   656: bipush          14
        //   658: iload           tokenLen
        //   660: invokevirtual   org/apache/commons/lang/time/FastDateFormat.selectNumberRule:(II)Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;
        //   663: astore          rule
        //   665: goto            949
        //   668: new             Lorg/apache/commons/lang/time/FastDateFormat$TextField;
        //   671: dup            
        //   672: bipush          7
        //   674: iload           tokenLen
        //   676: iconst_4       
        //   677: if_icmpge       685
        //   680: aload           shortWeekdays
        //   682: goto            687
        //   685: aload           weekdays
        //   687: invokespecial   org/apache/commons/lang/time/FastDateFormat$TextField.<init>:(I[Ljava/lang/String;)V
        //   690: astore          rule
        //   692: goto            949
        //   695: aload_0         /* this */
        //   696: bipush          6
        //   698: iload           tokenLen
        //   700: invokevirtual   org/apache/commons/lang/time/FastDateFormat.selectNumberRule:(II)Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;
        //   703: astore          rule
        //   705: goto            949
        //   708: aload_0         /* this */
        //   709: bipush          8
        //   711: iload           tokenLen
        //   713: invokevirtual   org/apache/commons/lang/time/FastDateFormat.selectNumberRule:(II)Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;
        //   716: astore          rule
        //   718: goto            949
        //   721: aload_0         /* this */
        //   722: iconst_3       
        //   723: iload           tokenLen
        //   725: invokevirtual   org/apache/commons/lang/time/FastDateFormat.selectNumberRule:(II)Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;
        //   728: astore          rule
        //   730: goto            949
        //   733: aload_0         /* this */
        //   734: iconst_4       
        //   735: iload           tokenLen
        //   737: invokevirtual   org/apache/commons/lang/time/FastDateFormat.selectNumberRule:(II)Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;
        //   740: astore          rule
        //   742: goto            949
        //   745: new             Lorg/apache/commons/lang/time/FastDateFormat$TextField;
        //   748: dup            
        //   749: bipush          9
        //   751: aload           AmPmStrings
        //   753: invokespecial   org/apache/commons/lang/time/FastDateFormat$TextField.<init>:(I[Ljava/lang/String;)V
        //   756: astore          rule
        //   758: goto            949
        //   761: new             Lorg/apache/commons/lang/time/FastDateFormat$TwentyFourHourField;
        //   764: dup            
        //   765: aload_0         /* this */
        //   766: bipush          11
        //   768: iload           tokenLen
        //   770: invokevirtual   org/apache/commons/lang/time/FastDateFormat.selectNumberRule:(II)Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;
        //   773: invokespecial   org/apache/commons/lang/time/FastDateFormat$TwentyFourHourField.<init>:(Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;)V
        //   776: astore          rule
        //   778: goto            949
        //   781: aload_0         /* this */
        //   782: bipush          10
        //   784: iload           tokenLen
        //   786: invokevirtual   org/apache/commons/lang/time/FastDateFormat.selectNumberRule:(II)Lorg/apache/commons/lang/time/FastDateFormat$NumberRule;
        //   789: astore          rule
        //   791: goto            949
        //   794: iload           tokenLen
        //   796: iconst_4       
        //   797: if_icmplt       825
        //   800: new             Lorg/apache/commons/lang/time/FastDateFormat$TimeZoneNameRule;
        //   803: dup            
        //   804: aload_0         /* this */
        //   805: getfield        org/apache/commons/lang/time/FastDateFormat.mTimeZone:Ljava/util/TimeZone;
        //   808: aload_0         /* this */
        //   809: getfield        org/apache/commons/lang/time/FastDateFormat.mTimeZoneForced:Z
        //   812: aload_0         /* this */
        //   813: getfield        org/apache/commons/lang/time/FastDateFormat.mLocale:Ljava/util/Locale;
        //   816: iconst_1       
        //   817: invokespecial   org/apache/commons/lang/time/FastDateFormat$TimeZoneNameRule.<init>:(Ljava/util/TimeZone;ZLjava/util/Locale;I)V
        //   820: astore          rule
        //   822: goto            949
        //   825: new             Lorg/apache/commons/lang/time/FastDateFormat$TimeZoneNameRule;
        //   828: dup            
        //   829: aload_0         /* this */
        //   830: getfield        org/apache/commons/lang/time/FastDateFormat.mTimeZone:Ljava/util/TimeZone;
        //   833: aload_0         /* this */
        //   834: getfield        org/apache/commons/lang/time/FastDateFormat.mTimeZoneForced:Z
        //   837: aload_0         /* this */
        //   838: getfield        org/apache/commons/lang/time/FastDateFormat.mLocale:Ljava/util/Locale;
        //   841: iconst_0       
        //   842: invokespecial   org/apache/commons/lang/time/FastDateFormat$TimeZoneNameRule.<init>:(Ljava/util/TimeZone;ZLjava/util/Locale;I)V
        //   845: astore          rule
        //   847: goto            949
        //   850: iload           tokenLen
        //   852: iconst_1       
        //   853: if_icmpne       864
        //   856: getstatic       org/apache/commons/lang/time/FastDateFormat$TimeZoneNumberRule.INSTANCE_NO_COLON:Lorg/apache/commons/lang/time/FastDateFormat$TimeZoneNumberRule;
        //   859: astore          rule
        //   861: goto            949
        //   864: getstatic       org/apache/commons/lang/time/FastDateFormat$TimeZoneNumberRule.INSTANCE_COLON:Lorg/apache/commons/lang/time/FastDateFormat$TimeZoneNumberRule;
        //   867: astore          rule
        //   869: goto            949
        //   872: aload           token
        //   874: iconst_1       
        //   875: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   878: astore          sub
        //   880: aload           sub
        //   882: invokevirtual   java/lang/String.length:()I
        //   885: iconst_1       
        //   886: if_icmpne       907
        //   889: new             Lorg/apache/commons/lang/time/FastDateFormat$CharacterLiteral;
        //   892: dup            
        //   893: aload           sub
        //   895: iconst_0       
        //   896: invokevirtual   java/lang/String.charAt:(I)C
        //   899: invokespecial   org/apache/commons/lang/time/FastDateFormat$CharacterLiteral.<init>:(C)V
        //   902: astore          rule
        //   904: goto            949
        //   907: new             Lorg/apache/commons/lang/time/FastDateFormat$StringLiteral;
        //   910: dup            
        //   911: aload           sub
        //   913: invokespecial   org/apache/commons/lang/time/FastDateFormat$StringLiteral.<init>:(Ljava/lang/String;)V
        //   916: astore          rule
        //   918: goto            949
        //   921: new             Ljava/lang/IllegalArgumentException;
        //   924: dup            
        //   925: new             Ljava/lang/StringBuffer;
        //   928: dup            
        //   929: invokespecial   java/lang/StringBuffer.<init>:()V
        //   932: ldc             "Illegal pattern component: "
        //   934: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   937: aload           token
        //   939: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   942: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   945: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   948: athrow         
        //   949: aload_2         /* rules */
        //   950: aload           14
        //   952: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   957: pop            
        //   958: iinc            i, 1
        //   961: goto            72
        //   964: aload_2         /* rules */
        //   965: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name           Signature
        //  -----  ------  ----  -------------  --------------------------------------------------
        //  491    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  509    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  517    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  538    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  559    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  573    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  581    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  593    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  613    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  626    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  639    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  652    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  665    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  692    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  705    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  718    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  730    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  742    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  758    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  778    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  791    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  822    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  847    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  861    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  869    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  904    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  918    3       14    rule           Lorg/apache/commons/lang/time/FastDateFormat$Rule;
        //  880    41      16    sub            Ljava/lang/String;
        //  97     861     12    token          Ljava/lang/String;
        //  110    848     13    tokenLen       I
        //  126    832     15    c              C
        //  72     892     11    i              I
        //  0      966     0     this           Lorg/apache/commons/lang/time/FastDateFormat;
        //  12     954     1     symbols        Ljava/text/DateFormatSymbols;
        //  20     946     2     rules          Ljava/util/List;
        //  25     941     3     ERAs           [Ljava/lang/String;
        //  31     935     4     months         [Ljava/lang/String;
        //  37     929     5     shortMonths    [Ljava/lang/String;
        //  43     923     6     weekdays       [Ljava/lang/String;
        //  49     917     7     shortWeekdays  [Ljava/lang/String;
        //  55     911     8     AmPmStrings    [Ljava/lang/String;
        //  64     902     9     length         I
        //  69     897     10    indexRef       [I
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected String parseToken(final String pattern, final int[] indexRef) {
        final StringBuffer buf = new StringBuffer();
        int i = indexRef[0];
        final int length = pattern.length();
        char c = pattern.charAt(i);
        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
            buf.append(c);
            while (i + 1 < length) {
                final char peek = pattern.charAt(i + 1);
                if (peek != c) {
                    break;
                }
                buf.append(c);
                ++i;
            }
        }
        else {
            buf.append('\'');
            boolean inLiteral = false;
            while (i < length) {
                c = pattern.charAt(i);
                if (c == '\'') {
                    if (i + 1 < length && pattern.charAt(i + 1) == '\'') {
                        ++i;
                        buf.append(c);
                    }
                    else {
                        inLiteral = !inLiteral;
                    }
                }
                else {
                    if (!inLiteral && ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                        --i;
                        break;
                    }
                    buf.append(c);
                }
                ++i;
            }
        }
        indexRef[0] = i;
        return buf.toString();
    }
    
    protected NumberRule selectNumberRule(final int field, final int padding) {
        switch (padding) {
            case 1: {
                return new UnpaddedNumberField(field);
            }
            case 2: {
                return new TwoDigitNumberField(field);
            }
            default: {
                return new PaddedNumberField(field, padding);
            }
        }
    }
    
    public StringBuffer format(final Object obj, final StringBuffer toAppendTo, final FieldPosition pos) {
        if (obj instanceof Date) {
            return this.format((Date)obj, toAppendTo);
        }
        if (obj instanceof Calendar) {
            return this.format((Calendar)obj, toAppendTo);
        }
        if (obj instanceof Long) {
            return this.format((long)obj, toAppendTo);
        }
        throw new IllegalArgumentException("Unknown class: " + ((obj == null) ? "<null>" : obj.getClass().getName()));
    }
    
    public String format(final long millis) {
        return this.format(new Date(millis));
    }
    
    public String format(final Date date) {
        final Calendar c = new GregorianCalendar(this.mTimeZone);
        c.setTime(date);
        return this.applyRules(c, new StringBuffer(this.mMaxLengthEstimate)).toString();
    }
    
    public String format(final Calendar calendar) {
        return this.format(calendar, new StringBuffer(this.mMaxLengthEstimate)).toString();
    }
    
    public StringBuffer format(final long millis, final StringBuffer buf) {
        return this.format(new Date(millis), buf);
    }
    
    public StringBuffer format(final Date date, final StringBuffer buf) {
        final Calendar c = new GregorianCalendar(this.mTimeZone);
        c.setTime(date);
        return this.applyRules(c, buf);
    }
    
    public StringBuffer format(Calendar calendar, final StringBuffer buf) {
        if (this.mTimeZoneForced) {
            calendar = (Calendar)calendar.clone();
            calendar.setTimeZone(this.mTimeZone);
        }
        return this.applyRules(calendar, buf);
    }
    
    protected StringBuffer applyRules(final Calendar calendar, final StringBuffer buf) {
        final Rule[] rules = this.mRules;
        for (int len = this.mRules.length, i = 0; i < len; ++i) {
            rules[i].appendTo(buf, calendar);
        }
        return buf;
    }
    
    public Object parseObject(final String source, final ParsePosition pos) {
        pos.setIndex(0);
        pos.setErrorIndex(0);
        return null;
    }
    
    public String getPattern() {
        return this.mPattern;
    }
    
    public TimeZone getTimeZone() {
        return this.mTimeZone;
    }
    
    public boolean getTimeZoneOverridesCalendar() {
        return this.mTimeZoneForced;
    }
    
    public Locale getLocale() {
        return this.mLocale;
    }
    
    public int getMaxLengthEstimate() {
        return this.mMaxLengthEstimate;
    }
    
    public boolean equals(final Object obj) {
        if (!(obj instanceof FastDateFormat)) {
            return false;
        }
        final FastDateFormat other = (FastDateFormat)obj;
        return (this.mPattern == other.mPattern || this.mPattern.equals(other.mPattern)) && (this.mTimeZone == other.mTimeZone || this.mTimeZone.equals(other.mTimeZone)) && (this.mLocale == other.mLocale || this.mLocale.equals(other.mLocale)) && this.mTimeZoneForced == other.mTimeZoneForced && this.mLocaleForced == other.mLocaleForced;
    }
    
    public int hashCode() {
        int total = 0;
        total += this.mPattern.hashCode();
        total += this.mTimeZone.hashCode();
        total += (this.mTimeZoneForced ? 1 : 0);
        total += this.mLocale.hashCode();
        total += (this.mLocaleForced ? 1 : 0);
        return total;
    }
    
    public String toString() {
        return "FastDateFormat[" + this.mPattern + "]";
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.init();
    }
    
    static {
        cInstanceCache = new HashMap(7);
        cDateInstanceCache = new HashMap(7);
        cTimeInstanceCache = new HashMap(7);
        cDateTimeInstanceCache = new HashMap(7);
        cTimeZoneDisplayCache = new HashMap(7);
    }
    
    private static class CharacterLiteral implements Rule
    {
        private final char mValue;
        
        CharacterLiteral(final char value) {
            this.mValue = value;
        }
        
        public int estimateLength() {
            return 1;
        }
        
        public void appendTo(final StringBuffer buffer, final Calendar calendar) {
            buffer.append(this.mValue);
        }
    }
    
    private static class StringLiteral implements Rule
    {
        private final String mValue;
        
        StringLiteral(final String value) {
            this.mValue = value;
        }
        
        public int estimateLength() {
            return this.mValue.length();
        }
        
        public void appendTo(final StringBuffer buffer, final Calendar calendar) {
            buffer.append(this.mValue);
        }
    }
    
    private static class TextField implements Rule
    {
        private final int mField;
        private final String[] mValues;
        
        TextField(final int field, final String[] values) {
            this.mField = field;
            this.mValues = values;
        }
        
        public int estimateLength() {
            int max = 0;
            int i = this.mValues.length;
            while (--i >= 0) {
                final int len = this.mValues[i].length();
                if (len > max) {
                    max = len;
                }
            }
            return max;
        }
        
        public void appendTo(final StringBuffer buffer, final Calendar calendar) {
            buffer.append(this.mValues[calendar.get(this.mField)]);
        }
    }
    
    private static class UnpaddedNumberField implements NumberRule
    {
        static final UnpaddedNumberField INSTANCE_YEAR;
        private final int mField;
        
        UnpaddedNumberField(final int field) {
            this.mField = field;
        }
        
        public int estimateLength() {
            return 4;
        }
        
        public void appendTo(final StringBuffer buffer, final Calendar calendar) {
            this.appendTo(buffer, calendar.get(this.mField));
        }
        
        public final void appendTo(final StringBuffer buffer, final int value) {
            if (value < 10) {
                buffer.append((char)(value + 48));
            }
            else if (value < 100) {
                buffer.append((char)(value / 10 + 48));
                buffer.append((char)(value % 10 + 48));
            }
            else {
                buffer.append(Integer.toString(value));
            }
        }
        
        static {
            INSTANCE_YEAR = new UnpaddedNumberField(1);
        }
    }
    
    private static class UnpaddedMonthField implements NumberRule
    {
        static final UnpaddedMonthField INSTANCE;
        
        public int estimateLength() {
            return 2;
        }
        
        public void appendTo(final StringBuffer buffer, final Calendar calendar) {
            this.appendTo(buffer, calendar.get(2) + 1);
        }
        
        public final void appendTo(final StringBuffer buffer, final int value) {
            if (value < 10) {
                buffer.append((char)(value + 48));
            }
            else {
                buffer.append((char)(value / 10 + 48));
                buffer.append((char)(value % 10 + 48));
            }
        }
        
        static {
            INSTANCE = new UnpaddedMonthField();
        }
    }
    
    private static class PaddedNumberField implements NumberRule
    {
        private final int mField;
        private final int mSize;
        
        PaddedNumberField(final int field, final int size) {
            if (size < 3) {
                throw new IllegalArgumentException();
            }
            this.mField = field;
            this.mSize = size;
        }
        
        public int estimateLength() {
            return 4;
        }
        
        public void appendTo(final StringBuffer buffer, final Calendar calendar) {
            this.appendTo(buffer, calendar.get(this.mField));
        }
        
        public final void appendTo(final StringBuffer buffer, final int value) {
            if (value < 100) {
                int i = this.mSize;
                while (--i >= 2) {
                    buffer.append('0');
                }
                buffer.append((char)(value / 10 + 48));
                buffer.append((char)(value % 10 + 48));
            }
            else {
                int digits;
                if (value < 1000) {
                    digits = 3;
                }
                else {
                    Validate.isTrue(value > -1, "Negative values should not be possible", value);
                    digits = Integer.toString(value).length();
                }
                int j = this.mSize;
                while (--j >= digits) {
                    buffer.append('0');
                }
                buffer.append(Integer.toString(value));
            }
        }
    }
    
    private static class TwoDigitNumberField implements NumberRule
    {
        private final int mField;
        
        TwoDigitNumberField(final int field) {
            this.mField = field;
        }
        
        public int estimateLength() {
            return 2;
        }
        
        public void appendTo(final StringBuffer buffer, final Calendar calendar) {
            this.appendTo(buffer, calendar.get(this.mField));
        }
        
        public final void appendTo(final StringBuffer buffer, final int value) {
            if (value < 100) {
                buffer.append((char)(value / 10 + 48));
                buffer.append((char)(value % 10 + 48));
            }
            else {
                buffer.append(Integer.toString(value));
            }
        }
    }
    
    private static class TwoDigitYearField implements NumberRule
    {
        static final TwoDigitYearField INSTANCE;
        
        public int estimateLength() {
            return 2;
        }
        
        public void appendTo(final StringBuffer buffer, final Calendar calendar) {
            this.appendTo(buffer, calendar.get(1) % 100);
        }
        
        public final void appendTo(final StringBuffer buffer, final int value) {
            buffer.append((char)(value / 10 + 48));
            buffer.append((char)(value % 10 + 48));
        }
        
        static {
            INSTANCE = new TwoDigitYearField();
        }
    }
    
    private static class TwoDigitMonthField implements NumberRule
    {
        static final TwoDigitMonthField INSTANCE;
        
        public int estimateLength() {
            return 2;
        }
        
        public void appendTo(final StringBuffer buffer, final Calendar calendar) {
            this.appendTo(buffer, calendar.get(2) + 1);
        }
        
        public final void appendTo(final StringBuffer buffer, final int value) {
            buffer.append((char)(value / 10 + 48));
            buffer.append((char)(value % 10 + 48));
        }
        
        static {
            INSTANCE = new TwoDigitMonthField();
        }
    }
    
    private static class TwelveHourField implements NumberRule
    {
        private final NumberRule mRule;
        
        TwelveHourField(final NumberRule rule) {
            this.mRule = rule;
        }
        
        public int estimateLength() {
            return this.mRule.estimateLength();
        }
        
        public void appendTo(final StringBuffer buffer, final Calendar calendar) {
            int value = calendar.get(10);
            if (value == 0) {
                value = calendar.getLeastMaximum(10) + 1;
            }
            this.mRule.appendTo(buffer, value);
        }
        
        public void appendTo(final StringBuffer buffer, final int value) {
            this.mRule.appendTo(buffer, value);
        }
    }
    
    private static class TwentyFourHourField implements NumberRule
    {
        private final NumberRule mRule;
        
        TwentyFourHourField(final NumberRule rule) {
            this.mRule = rule;
        }
        
        public int estimateLength() {
            return this.mRule.estimateLength();
        }
        
        public void appendTo(final StringBuffer buffer, final Calendar calendar) {
            int value = calendar.get(11);
            if (value == 0) {
                value = calendar.getMaximum(11) + 1;
            }
            this.mRule.appendTo(buffer, value);
        }
        
        public void appendTo(final StringBuffer buffer, final int value) {
            this.mRule.appendTo(buffer, value);
        }
    }
    
    private static class TimeZoneNameRule implements Rule
    {
        private final TimeZone mTimeZone;
        private final boolean mTimeZoneForced;
        private final Locale mLocale;
        private final int mStyle;
        private final String mStandard;
        private final String mDaylight;
        
        TimeZoneNameRule(final TimeZone timeZone, final boolean timeZoneForced, final Locale locale, final int style) {
            this.mTimeZone = timeZone;
            this.mTimeZoneForced = timeZoneForced;
            this.mLocale = locale;
            this.mStyle = style;
            if (timeZoneForced) {
                this.mStandard = FastDateFormat.getTimeZoneDisplay(timeZone, false, style, locale);
                this.mDaylight = FastDateFormat.getTimeZoneDisplay(timeZone, true, style, locale);
            }
            else {
                this.mStandard = null;
                this.mDaylight = null;
            }
        }
        
        public int estimateLength() {
            if (this.mTimeZoneForced) {
                return Math.max(this.mStandard.length(), this.mDaylight.length());
            }
            if (this.mStyle == 0) {
                return 4;
            }
            return 40;
        }
        
        public void appendTo(final StringBuffer buffer, final Calendar calendar) {
            if (this.mTimeZoneForced) {
                if (this.mTimeZone.useDaylightTime() && calendar.get(16) != 0) {
                    buffer.append(this.mDaylight);
                }
                else {
                    buffer.append(this.mStandard);
                }
            }
            else {
                final TimeZone timeZone = calendar.getTimeZone();
                if (timeZone.useDaylightTime() && calendar.get(16) != 0) {
                    buffer.append(FastDateFormat.getTimeZoneDisplay(timeZone, true, this.mStyle, this.mLocale));
                }
                else {
                    buffer.append(FastDateFormat.getTimeZoneDisplay(timeZone, false, this.mStyle, this.mLocale));
                }
            }
        }
    }
    
    private static class TimeZoneNumberRule implements Rule
    {
        static final TimeZoneNumberRule INSTANCE_COLON;
        static final TimeZoneNumberRule INSTANCE_NO_COLON;
        final boolean mColon;
        
        TimeZoneNumberRule(final boolean colon) {
            this.mColon = colon;
        }
        
        public int estimateLength() {
            return 5;
        }
        
        public void appendTo(final StringBuffer buffer, final Calendar calendar) {
            int offset = calendar.get(15) + calendar.get(16);
            if (offset < 0) {
                buffer.append('-');
                offset = -offset;
            }
            else {
                buffer.append('+');
            }
            final int hours = offset / 3600000;
            buffer.append((char)(hours / 10 + 48));
            buffer.append((char)(hours % 10 + 48));
            if (this.mColon) {
                buffer.append(':');
            }
            final int minutes = offset / 60000 - 60 * hours;
            buffer.append((char)(minutes / 10 + 48));
            buffer.append((char)(minutes % 10 + 48));
        }
        
        static {
            INSTANCE_COLON = new TimeZoneNumberRule(true);
            INSTANCE_NO_COLON = new TimeZoneNumberRule(false);
        }
    }
    
    private static class TimeZoneDisplayKey
    {
        private final TimeZone mTimeZone;
        private final int mStyle;
        private final Locale mLocale;
        
        TimeZoneDisplayKey(final TimeZone timeZone, final boolean daylight, int style, final Locale locale) {
            this.mTimeZone = timeZone;
            if (daylight) {
                style |= Integer.MIN_VALUE;
            }
            this.mStyle = style;
            this.mLocale = locale;
        }
        
        public int hashCode() {
            return this.mStyle * 31 + this.mLocale.hashCode();
        }
        
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof TimeZoneDisplayKey) {
                final TimeZoneDisplayKey other = (TimeZoneDisplayKey)obj;
                return this.mTimeZone.equals(other.mTimeZone) && this.mStyle == other.mStyle && this.mLocale.equals(other.mLocale);
            }
            return false;
        }
    }
    
    private static class Pair
    {
        private final Object mObj1;
        private final Object mObj2;
        
        public Pair(final Object obj1, final Object obj2) {
            this.mObj1 = obj1;
            this.mObj2 = obj2;
        }
        
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Pair)) {
                return false;
            }
            final Pair key = (Pair)obj;
            if (this.mObj1 == null) {
                if (key.mObj1 != null) {
                    return false;
                }
            }
            else if (!this.mObj1.equals(key.mObj1)) {
                return false;
            }
            if ((this.mObj2 != null) ? this.mObj2.equals(key.mObj2) : (key.mObj2 == null)) {
                return true;
            }
            return false;
        }
        
        public int hashCode() {
            return ((this.mObj1 == null) ? 0 : this.mObj1.hashCode()) + ((this.mObj2 == null) ? 0 : this.mObj2.hashCode());
        }
        
        public String toString() {
            return "[" + this.mObj1 + ':' + this.mObj2 + ']';
        }
    }
    
    private interface Rule
    {
        int estimateLength();
        
        void appendTo(final StringBuffer p0, final Calendar p1);
    }
    
    private interface NumberRule extends Rule
    {
        void appendTo(final StringBuffer p0, final int p1);
    }
}
