// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.util;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.text.ParseException;
import java.util.Locale;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DateFormat
{
    private static final java.text.DateFormat timeZoneFormat;
    private int formatIndex;
    private int parseIndex;
    
    static {
        timeZoneFormat = new SimpleDateFormat("z");
    }
    
    public String format(final String s, final long timeInMillis) {
        final Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(timeInMillis);
        this.formatIndex = 0;
        final StringBuilder sb = new StringBuilder();
        for (Field field = this.nextField(s, sb); field != Field.NONE; field = this.nextField(s, sb)) {
            switch (field) {
                case YY: {
                    this.twoDigitPadding(instance.get(1) % 1000, sb);
                    break;
                }
                case YEAR: {
                    sb.append(instance.get(1));
                    break;
                }
                case M: {
                    sb.append(instance.get(2) + 1);
                    break;
                }
                case MM: {
                    this.twoDigitPadding(instance.get(2) + 1, sb);
                    break;
                }
                case MON: {
                    sb.append(instance.getDisplayName(2, 1, Locale.getDefault()));
                    break;
                }
                case MONTH: {
                    sb.append(instance.getDisplayName(2, 2, Locale.getDefault()));
                    break;
                }
                case YW: {
                    sb.append(instance.get(3));
                    break;
                }
                case D: {
                    sb.append(instance.get(5));
                    break;
                }
                case DD: {
                    this.twoDigitPadding(instance.get(5), sb);
                    break;
                }
                case DAY: {
                    sb.append(instance.getDisplayName(7, 1, Locale.getDefault()));
                    break;
                }
                case DAYFULL: {
                    sb.append(instance.getDisplayName(7, 2, Locale.getDefault()));
                    break;
                }
                case DDD: {
                    sb.append(instance.get(6));
                    break;
                }
                case h: {
                    sb.append(instance.get(10) + 1);
                    break;
                }
                case hh: {
                    this.twoDigitPadding(instance.get(11), sb);
                    break;
                }
                case m: {
                    sb.append(instance.get(12));
                    break;
                }
                case mm: {
                    this.twoDigitPadding(instance.get(12), sb);
                    break;
                }
                case s: {
                    sb.append(instance.get(13));
                    break;
                }
                case ss: {
                    this.twoDigitPadding(instance.get(13), sb);
                    break;
                }
                case S: {
                    sb.append(instance.get(14));
                    break;
                }
                case SSS: {
                    this.threeDigitPadding(instance.get(14), sb);
                    break;
                }
                case Z: {
                    sb.append(instance.getTimeZone().getDisplayName(true, 0));
                    break;
                }
                case AM: {
                    sb.append(instance.getDisplayName(9, 1, Locale.getDefault()));
                    break;
                }
                case AD: {
                    sb.append((instance.get(0) == 1) ? "AD" : "BC");
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    public long parse(final String s, final String s2) throws ParseException {
        final Calendar instance = Calendar.getInstance();
        instance.clear();
        this.formatIndex = 0;
        this.parseIndex = 0;
        final StringBuilder sb = new StringBuilder();
        for (Field field = this.nextField(s, sb); field != Field.NONE; field = this.nextField(s, sb)) {
            if (!s2.substring(this.parseIndex, this.parseIndex + sb.length()).equals(sb.toString())) {
                throw new ParseException(s2, this.parseIndex);
            }
            this.parseIndex += sb.length();
            switch (field) {
                case YY: {
                    this.parseYY(s2, instance);
                    break;
                }
                case YEAR: {
                    this.parseNumber(s2, instance, 1, 0);
                    break;
                }
                case M:
                case MM: {
                    this.parseNumber(s2, instance, 2, -1);
                    break;
                }
                case MON: {
                    this.parseString(s2, instance, 2, 1, 3);
                    break;
                }
                case MONTH: {
                    this.parseString(s2, instance, 2, 2);
                    break;
                }
                case YW: {
                    this.parseNumber(s2, instance, 4, 0);
                    break;
                }
                case D:
                case DD: {
                    this.parseNumber(s2, instance, 5, 0);
                    break;
                }
                case DAY: {
                    this.parseString(s2, instance, 7, 1, 3);
                    break;
                }
                case DAYFULL: {
                    this.parseString(s2, instance, 7, 2);
                    break;
                }
                case DDD: {
                    this.parseNumber(s2, instance, 6, 0);
                    break;
                }
                case h: {
                    this.parseNumber(s2, instance, 10, -1);
                    break;
                }
                case hh: {
                    this.parseNumber(s2, instance, 10, 0);
                    break;
                }
                case m:
                case mm: {
                    this.parseNumber(s2, instance, 12, 0);
                    break;
                }
                case s:
                case ss: {
                    this.parseNumber(s2, instance, 13, 0);
                    break;
                }
                case S:
                case SSS: {
                    this.parseNumber(s2, instance, 14, 0);
                    break;
                }
                case Z: {
                    this.parseIndex += 3;
                    if (this.parseIndex > s2.length()) {
                        throw new ParseException(s2, this.parseIndex);
                    }
                    final Calendar instance2 = Calendar.getInstance();
                    try {
                        instance2.setTime(DateFormat.timeZoneFormat.parse(s2.substring(this.parseIndex - 3, this.parseIndex)));
                        break;
                    }
                    catch (Exception ex) {
                        return Long.MIN_VALUE;
                    }
                }
                case AM: {
                    this.parseIndex += 2;
                    if (this.parseIndex > s2.length()) {
                        throw new ParseException(s2, this.parseIndex);
                    }
                    instance.set(9, s2.substring(this.parseIndex - 2, this.parseIndex).equals("AM") ? 0 : 1);
                    break;
                }
                case AD: {
                    this.parseIndex += 2;
                    if (this.parseIndex > s2.length()) {
                        throw new ParseException(s2, this.parseIndex);
                    }
                    instance.set(0, s2.substring(this.parseIndex - 2, this.parseIndex).equals("AD") ? 1 : 0);
                    break;
                }
            }
            sb.setLength(0);
        }
        return instance.getTimeInMillis();
    }
    
    private Field nextField(final String s, final StringBuilder sb) {
        final int index = s.indexOf(123, this.formatIndex);
        if (index < 0) {
            sb.append(s.substring(this.formatIndex));
            return Field.NONE;
        }
        final int index2 = s.indexOf(125, index + 1);
        if (index2 < 0) {
            sb.append(s.substring(this.formatIndex));
            return Field.NONE;
        }
        try {
            final Field value = Field.valueOf(s.substring(index + 1, index2));
            if (index > this.formatIndex) {
                sb.append(s.substring(this.formatIndex, index));
            }
            this.formatIndex = index2 + 1;
            return value;
        }
        catch (IllegalArgumentException ex) {
            sb.append(s.substring(this.formatIndex));
            this.formatIndex = index2 + 1;
            return Field.NONE;
        }
    }
    
    private void twoDigitPadding(final int n, final StringBuilder sb) {
        if (n < 10) {
            sb.append('0');
            sb.append(n);
        }
        else {
            sb.append(n);
        }
    }
    
    private void threeDigitPadding(final int n, final StringBuilder sb) {
        if (n < 10) {
            sb.append("00");
            sb.append(n);
        }
        else if (n < 100) {
            sb.append('0');
            sb.append(n);
        }
        else {
            sb.append(n);
        }
    }
    
    private void parseNumber(final String s, final Calendar calendar, final int n, final int n2) throws ParseException {
        final int parseIndex = this.parseIndex;
        while (this.parseIndex < s.length()) {
            if (!Character.isDigit(s.charAt(this.parseIndex))) {
                break;
            }
            ++this.parseIndex;
        }
        try {
            calendar.set(n, Integer.parseInt(s.substring(parseIndex, this.parseIndex)) + n2);
        }
        catch (NumberFormatException ex) {
            throw new ParseException(s, this.parseIndex);
        }
    }
    
    private void parseString(final String s, final Calendar calendar, final int n, final int n2, final int n3) throws ParseException {
        final Integer n4 = calendar.getDisplayNames(n, n2, Locale.getDefault()).get(s.substring(this.parseIndex, this.parseIndex + n3));
        if (n4 != null) {
            calendar.set(n, n4);
            return;
        }
        throw new ParseException(s, this.parseIndex);
    }
    
    private void parseString(final String s, final Calendar calendar, final int n, final int n2) throws ParseException {
        final Map<String, Integer> displayNames = calendar.getDisplayNames(n, n2, Locale.getDefault());
        final int parseIndex = this.parseIndex;
        while (this.parseIndex < s.length()) {
            final Integer n3 = displayNames.get(s.substring(parseIndex, ++this.parseIndex));
            if (n3 != null) {
                calendar.set(n, n3);
                return;
            }
        }
        throw new ParseException(s, this.parseIndex);
    }
    
    private void parseYY(final String s, final Calendar calendar) throws ParseException {
        final int parseIndex = this.parseIndex;
        for (char c = s.charAt(this.parseIndex); Character.isDigit(c); c = s.charAt(++this.parseIndex)) {}
        try {
            final int int1 = Integer.parseInt(s.substring(parseIndex, this.parseIndex));
            calendar.set(1, (int1 < 71) ? (int1 + 2000) : (int1 + 1900));
        }
        catch (NumberFormatException ex) {
            throw new ParseException(s, this.parseIndex);
        }
    }
    
    public static void main(final String[] array) throws Exception {
        final DateFormat dateFormat = new DateFormat();
        final String s = "{YY}/{M}/{DAYFULL}";
        final String format = dateFormat.format(s, System.currentTimeMillis());
        System.out.println(format);
        System.out.println(dateFormat.format(s, dateFormat.parse(s, format)));
    }
    
    public enum Field
    {
        NONE("NONE", 0), 
        YY("YY", 1), 
        YEAR("YEAR", 2), 
        M("M", 3), 
        MM("MM", 4), 
        MON("MON", 5), 
        MONTH("MONTH", 6), 
        YW("YW", 7), 
        D("D", 8), 
        DD("DD", 9), 
        DAY("DAY", 10), 
        DAYFULL("DAYFULL", 11), 
        DDD("DDD", 12), 
        h("h", 13), 
        hh("hh", 14), 
        m("m", 15), 
        mm("mm", 16), 
        s("s", 17), 
        ss("ss", 18), 
        S("S", 19), 
        SSS("SSS", 20), 
        Z("Z", 21), 
        AM("AM", 22), 
        AD("AD", 23);
        
        private Field(final String s, final int n) {
        }
    }
}
