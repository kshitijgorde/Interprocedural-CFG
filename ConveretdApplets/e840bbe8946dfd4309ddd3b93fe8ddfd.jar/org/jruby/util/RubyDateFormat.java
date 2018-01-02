// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Iterator;
import java.text.FieldPosition;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import org.joda.time.DateTime;
import java.text.DateFormatSymbols;
import java.util.List;
import java.text.DateFormat;

public class RubyDateFormat extends DateFormat
{
    private static final long serialVersionUID = -250429218019023997L;
    private boolean ruby_1_9;
    private List<Token> compiledPattern;
    private final DateFormatSymbols formatSymbols;
    private static final int FORMAT_STRING = 0;
    private static final int FORMAT_WEEK_LONG = 1;
    private static final int FORMAT_WEEK_SHORT = 2;
    private static final int FORMAT_MONTH_LONG = 3;
    private static final int FORMAT_MONTH_SHORT = 4;
    private static final int FORMAT_DAY = 5;
    private static final int FORMAT_DAY_S = 6;
    private static final int FORMAT_HOUR = 7;
    private static final int FORMAT_HOUR_M = 8;
    private static final int FORMAT_HOUR_S = 9;
    private static final int FORMAT_DAY_YEAR = 10;
    private static final int FORMAT_MINUTES = 11;
    private static final int FORMAT_MONTH = 12;
    private static final int FORMAT_MERIDIAN = 13;
    private static final int FORMAT_MERIDIAN_LOWER_CASE = 14;
    private static final int FORMAT_SECONDS = 15;
    private static final int FORMAT_WEEK_YEAR_S = 16;
    private static final int FORMAT_WEEK_YEAR_M = 17;
    private static final int FORMAT_DAY_WEEK = 18;
    private static final int FORMAT_YEAR_LONG = 19;
    private static final int FORMAT_YEAR_SHORT = 20;
    private static final int FORMAT_ZONE_OFF = 21;
    private static final int FORMAT_ZONE_ID = 22;
    private static final int FORMAT_CENTURY = 23;
    private static final int FORMAT_HOUR_BLANK = 24;
    private static final int FORMAT_MILLISEC = 25;
    private static final int FORMAT_EPOCH = 26;
    private static final int FORMAT_DAY_WEEK2 = 27;
    private static final int FORMAT_WEEK_WEEKYEAR = 28;
    private static final int FORMAT_NANOSEC = 29;
    private static final int FORMAT_PRECISION = 30;
    private static final int FORMAT_WEEKYEAR = 31;
    private static final int FORMAT_OUTPUT = 32;
    private DateTime dt;
    
    public RubyDateFormat() {
        this("", new DateFormatSymbols());
    }
    
    public RubyDateFormat(final String pattern, final Locale aLocale) {
        this(pattern, new DateFormatSymbols(aLocale));
    }
    
    public RubyDateFormat(final String pattern, final Locale aLocale, final boolean ruby_1_9) {
        this(pattern, aLocale);
        this.ruby_1_9 = ruby_1_9;
    }
    
    public RubyDateFormat(final String pattern, final DateFormatSymbols formatSymbols) {
        this.formatSymbols = formatSymbols;
        this.applyPattern(pattern);
    }
    
    public void applyPattern(final String pattern) {
        this.compilePattern(pattern);
    }
    
    private void compilePattern(final String pattern) {
        this.compiledPattern = new LinkedList<Token>();
        final int len = pattern.length();
        int i = 0;
        while (i < len) {
            if (pattern.charAt(i) == '%') {
                if (++i == len) {
                    this.compiledPattern.add(new Token(0, "%"));
                }
                else {
                    i = this.addOutputFormatter(pattern, i);
                    switch (pattern.charAt(i)) {
                        case 'A': {
                            this.compiledPattern.add(new Token(1));
                            break;
                        }
                        case 'a': {
                            this.compiledPattern.add(new Token(2));
                            break;
                        }
                        case 'B': {
                            this.compiledPattern.add(new Token(3));
                            break;
                        }
                        case 'b':
                        case 'h': {
                            this.compiledPattern.add(new Token(4));
                            break;
                        }
                        case 'C': {
                            this.compiledPattern.add(new Token(23));
                            break;
                        }
                        case 'c': {
                            this.compiledPattern.add(new Token(2));
                            this.compiledPattern.add(new Token(0, " "));
                            this.compiledPattern.add(new Token(4));
                            this.compiledPattern.add(new Token(0, " "));
                            this.compiledPattern.add(new Token(5));
                            this.compiledPattern.add(new Token(0, " "));
                            this.compiledPattern.add(new Token(7));
                            this.compiledPattern.add(new Token(0, ":"));
                            this.compiledPattern.add(new Token(11));
                            this.compiledPattern.add(new Token(0, ":"));
                            this.compiledPattern.add(new Token(15));
                            this.compiledPattern.add(new Token(0, " "));
                            this.compiledPattern.add(new Token(19));
                            break;
                        }
                        case 'D': {
                            this.compiledPattern.add(new Token(12));
                            this.compiledPattern.add(new Token(0, "/"));
                            this.compiledPattern.add(new Token(5));
                            this.compiledPattern.add(new Token(0, "/"));
                            this.compiledPattern.add(new Token(20));
                            break;
                        }
                        case 'd': {
                            this.compiledPattern.add(new Token(5));
                            break;
                        }
                        case 'e': {
                            this.compiledPattern.add(new Token(6));
                            break;
                        }
                        case 'F': {
                            this.compiledPattern.add(new Token(19));
                            this.compiledPattern.add(new Token(0, "-"));
                            this.compiledPattern.add(new Token(12));
                            this.compiledPattern.add(new Token(0, "-"));
                            this.compiledPattern.add(new Token(5));
                            break;
                        }
                        case 'G': {
                            this.compiledPattern.add(new Token(31));
                            break;
                        }
                        case 'H': {
                            this.compiledPattern.add(new Token(7));
                            break;
                        }
                        case 'I': {
                            this.compiledPattern.add(new Token(8));
                            break;
                        }
                        case 'j': {
                            this.compiledPattern.add(new Token(10));
                            break;
                        }
                        case 'k': {
                            this.compiledPattern.add(new Token(24));
                            break;
                        }
                        case 'L': {
                            this.compiledPattern.add(new Token(25));
                            break;
                        }
                        case 'l': {
                            this.compiledPattern.add(new Token(9));
                            break;
                        }
                        case 'M': {
                            this.compiledPattern.add(new Token(11));
                            break;
                        }
                        case 'm': {
                            this.compiledPattern.add(new Token(12));
                            break;
                        }
                        case 'N': {
                            this.compiledPattern.add(new Token(29));
                            break;
                        }
                        case 'n': {
                            this.compiledPattern.add(new Token(0, "\n"));
                            break;
                        }
                        case 'p': {
                            this.compiledPattern.add(new Token(13));
                            break;
                        }
                        case 'P': {
                            this.compiledPattern.add(new Token(14));
                            break;
                        }
                        case 'R': {
                            this.compiledPattern.add(new Token(7));
                            this.compiledPattern.add(new Token(0, ":"));
                            this.compiledPattern.add(new Token(11));
                            break;
                        }
                        case 'r': {
                            this.compiledPattern.add(new Token(8));
                            this.compiledPattern.add(new Token(0, ":"));
                            this.compiledPattern.add(new Token(11));
                            this.compiledPattern.add(new Token(0, ":"));
                            this.compiledPattern.add(new Token(15));
                            this.compiledPattern.add(new Token(0, " "));
                            this.compiledPattern.add(new Token(13));
                            break;
                        }
                        case 's': {
                            this.compiledPattern.add(new Token(26));
                            break;
                        }
                        case 'S': {
                            this.compiledPattern.add(new Token(15));
                            break;
                        }
                        case 'T': {
                            this.compiledPattern.add(new Token(7));
                            this.compiledPattern.add(new Token(0, ":"));
                            this.compiledPattern.add(new Token(11));
                            this.compiledPattern.add(new Token(0, ":"));
                            this.compiledPattern.add(new Token(15));
                            break;
                        }
                        case 't': {
                            this.compiledPattern.add(new Token(0, "\t"));
                            break;
                        }
                        case 'u': {
                            this.compiledPattern.add(new Token(27));
                            break;
                        }
                        case 'U': {
                            this.compiledPattern.add(new Token(16));
                            break;
                        }
                        case 'v': {
                            this.compiledPattern.add(new Token(6));
                            this.compiledPattern.add(new Token(0, "-"));
                            this.compiledPattern.add(new Token(4));
                            this.compiledPattern.add(new Token(0, "-"));
                            this.compiledPattern.add(new Token(19));
                            break;
                        }
                        case 'V': {
                            this.compiledPattern.add(new Token(28));
                            break;
                        }
                        case 'W': {
                            this.compiledPattern.add(new Token(17));
                            break;
                        }
                        case 'w': {
                            this.compiledPattern.add(new Token(18));
                            break;
                        }
                        case 'X': {
                            this.compiledPattern.add(new Token(7));
                            this.compiledPattern.add(new Token(0, ":"));
                            this.compiledPattern.add(new Token(11));
                            this.compiledPattern.add(new Token(0, ":"));
                            this.compiledPattern.add(new Token(15));
                            break;
                        }
                        case 'x': {
                            this.compiledPattern.add(new Token(12));
                            this.compiledPattern.add(new Token(0, "/"));
                            this.compiledPattern.add(new Token(5));
                            this.compiledPattern.add(new Token(0, "/"));
                            this.compiledPattern.add(new Token(20));
                            break;
                        }
                        case 'Y': {
                            this.compiledPattern.add(new Token(19));
                            break;
                        }
                        case 'y': {
                            this.compiledPattern.add(new Token(20));
                            break;
                        }
                        case 'Z': {
                            this.compiledPattern.add(new Token(22));
                            break;
                        }
                        case 'z': {
                            this.compiledPattern.add(new Token(21));
                            break;
                        }
                        case '%': {
                            this.compiledPattern.add(new Token(0, "%"));
                            break;
                        }
                        default: {
                            this.compiledPattern.add(new Token(0, "%" + pattern.charAt(i)));
                            break;
                        }
                    }
                    ++i;
                }
            }
            else {
                final StringBuilder sb = new StringBuilder();
                while (i < len && pattern.charAt(i) != '%') {
                    sb.append(pattern.charAt(i));
                    ++i;
                }
                this.compiledPattern.add(new Token(0, sb.toString()));
            }
        }
    }
    
    private int addOutputFormatter(final String pattern, int index) {
        if (this.ruby_1_9) {
            final TimeOutputFormatter outputFormatter = TimeOutputFormatter.getFormatter(pattern.substring(index - 1));
            if (outputFormatter != null) {
                index += outputFormatter.getFormatter().length();
                this.compiledPattern.add(new Token(32, outputFormatter));
            }
        }
        return index;
    }
    
    private String formatOutput(TimeOutputFormatter formatter, String output) {
        if (formatter == null) {
            return output;
        }
        output = formatter.format(output);
        formatter = null;
        return output;
    }
    
    public void setDateTime(final DateTime dt) {
        this.dt = dt;
    }
    
    public StringBuffer format(final Date ignored, final StringBuffer toAppendTo, final FieldPosition fieldPosition) {
        TimeOutputFormatter formatter = null;
        for (final Token token : this.compiledPattern) {
            String output = null;
            boolean format = true;
            switch (token.getFormat()) {
                case 32: {
                    formatter = (TimeOutputFormatter)token.getData();
                    break;
                }
                case 0: {
                    output = token.getData().toString();
                    format = false;
                    break;
                }
                case 1: {
                    int v = (this.dt.getDayOfWeek() + 1) % 8;
                    if (v == 0) {
                        ++v;
                    }
                    output = this.formatSymbols.getWeekdays()[v];
                    break;
                }
                case 2: {
                    int v = (this.dt.getDayOfWeek() + 1) % 8;
                    if (v == 0) {
                        ++v;
                    }
                    output = this.formatSymbols.getShortWeekdays()[v];
                    break;
                }
                case 3: {
                    output = this.formatSymbols.getMonths()[this.dt.getMonthOfYear() - 1];
                    break;
                }
                case 4: {
                    output = this.formatSymbols.getShortMonths()[this.dt.getMonthOfYear() - 1];
                    break;
                }
                case 5: {
                    final int value = this.dt.getDayOfMonth();
                    output = String.format("%02d", value);
                    break;
                }
                case 6: {
                    final int value = this.dt.getDayOfMonth();
                    output = ((value < 10) ? " " : "") + Integer.toString(value);
                    break;
                }
                case 7:
                case 24: {
                    final int value = this.dt.getHourOfDay();
                    output = "";
                    if (value < 10) {
                        output += ((token.getFormat() == 7) ? "0" : " ");
                    }
                    output += value;
                    break;
                }
                case 8:
                case 9: {
                    int value = this.dt.getHourOfDay();
                    if (value > 12) {
                        value -= 12;
                    }
                    if (value == 0) {
                        output = "12";
                        break;
                    }
                    output = "";
                    if (value < 10) {
                        output += ((token.getFormat() == 8) ? "0" : " ");
                    }
                    output += value;
                    break;
                }
                case 10: {
                    final int value = this.dt.getDayOfYear();
                    output = String.format("%03d", value);
                    break;
                }
                case 11: {
                    final int value = this.dt.getMinuteOfHour();
                    output = String.format("%02d", value);
                    break;
                }
                case 12: {
                    final int value = this.dt.getMonthOfYear();
                    output = String.format("%02d", value);
                    break;
                }
                case 13:
                case 14: {
                    if (this.dt.getHourOfDay() < 12) {
                        output = ((token.getFormat() == 13) ? "AM" : "am");
                        break;
                    }
                    output = ((token.getFormat() == 13) ? "PM" : "pm");
                    break;
                }
                case 15: {
                    final int value = this.dt.getSecondOfMinute();
                    output = ((value < 10) ? "0" : "") + Integer.toString(value);
                    break;
                }
                case 17: {
                    output = this.formatWeekYear(2);
                    break;
                }
                case 16: {
                    output = this.formatWeekYear(1);
                    break;
                }
                case 18:
                case 27: {
                    int value = this.dt.getDayOfWeek();
                    if (token.getFormat() == 18) {
                        value %= 7;
                    }
                    output = Integer.toString(value);
                    break;
                }
                case 19: {
                    final int value = this.dt.getYear();
                    output = String.format("%04d", value);
                    break;
                }
                case 20: {
                    final int value = this.dt.getYear() % 100;
                    output = String.format("%02d", value);
                    break;
                }
                case 21: {
                    int value = this.dt.getZone().getOffset(this.dt.getMillis());
                    output = ((value < 0) ? "-" : "+");
                    value = Math.abs(value);
                    if (value / 3600000 < 10) {
                        output += "0";
                    }
                    output += value / 3600000;
                    value = value % 3600000 / 60000;
                    if (value < 10) {
                        output += "0";
                    }
                    output += value;
                    break;
                }
                case 22: {
                    toAppendTo.append(this.dt.getZone().getShortName(this.dt.getMillis()));
                    break;
                }
                case 23: {
                    toAppendTo.append(this.dt.getCenturyOfEra());
                    break;
                }
                case 25: {
                    final int value = this.dt.getMillisOfSecond();
                    output = String.format("%03d", value);
                    break;
                }
                case 26: {
                    output = Long.toString(this.dt.getMillis() / 1000L);
                    break;
                }
                case 28: {
                    final int value = this.dt.getWeekOfWeekyear();
                    output = String.format("%02d", value);
                    break;
                }
                case 29: {
                    final int value = this.dt.getMillisOfSecond() * 1000000;
                    String width = "3";
                    if (formatter != null) {
                        width = formatter.getFormatter();
                    }
                    output = this.formatTruncate(String.valueOf(value), Integer.valueOf(width), "0");
                    break;
                }
                case 31: {
                    output = Integer.toString(this.dt.getWeekyear());
                    break;
                }
            }
            if (output != null) {
                toAppendTo.append(format ? this.formatOutput(formatter, output) : output);
            }
        }
        return toAppendTo;
    }
    
    private String formatWeekYear(final int firstDayOfWeek) {
        final Calendar dtCalendar = this.dt.toGregorianCalendar();
        dtCalendar.setFirstDayOfWeek(firstDayOfWeek);
        dtCalendar.setMinimalDaysInFirstWeek(7);
        int value = dtCalendar.get(3);
        if ((value == 52 || value == 53) && dtCalendar.get(2) == 0) {
            value = 0;
        }
        return String.format("%02d", value);
    }
    
    public Date parse(final String source, final ParsePosition pos) {
        throw new UnsupportedOperationException();
    }
    
    private String formatTruncate(final String orig, final int len, final String pad) {
        if (len == 0) {
            return "";
        }
        if (orig.length() > len) {
            return orig.substring(0, len);
        }
        StringBuilder sb = new StringBuilder(len);
        sb.append(orig);
        while (sb.length() < len) {
            sb = sb.append(pad);
        }
        return sb.toString().substring(0, len);
    }
    
    private static class Token
    {
        private int format;
        private Object data;
        private TimeOutputFormatter outputFormatter;
        
        public Token(final int format) {
            this(format, null);
        }
        
        public Token(final int format, final Object data) {
            this.format = format;
            this.data = data;
        }
        
        public Object getData() {
            return this.data;
        }
        
        public int getFormat() {
            return this.format;
        }
    }
}
