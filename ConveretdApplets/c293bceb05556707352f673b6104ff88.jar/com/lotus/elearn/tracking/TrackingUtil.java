// 
// Decompiled by Procyon v0.5.30
// 

package com.lotus.elearn.tracking;

import java.util.StringTokenizer;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Calendar;
import java.io.UnsupportedEncodingException;
import java.util.BitSet;
import java.util.ResourceBundle;

public final class TrackingUtil
{
    private static final String ISO_LATIN_1 = "8859_1";
    private static final int BYTE_MASK = 15;
    private static final char PERCENT = '%';
    private static final char PLUS = '+';
    private static final char SPACE = ' ';
    private static final char[] HEX_VALUES;
    private static ResourceBundle mErrors;
    private static BitSet mDontNeedEncoding;
    
    public static String decimalToText(final Double value) {
        String retVal = "";
        if (value != null) {
            final double doubleValue = value;
            final int intValue = (int)(Object)value;
            if (doubleValue == intValue) {
                retVal = String.valueOf(intValue);
            }
            else {
                retVal = String.valueOf(doubleValue);
            }
        }
        return retVal;
    }
    
    public static String decode(final String data, final String characterEncoding) {
        return data;
    }
    
    public static String encode(final String data, final String characterEncoding) {
        String retVal = null;
        if (data != null) {
            final int valueLength = data.length();
            final StringBuffer buffer = new StringBuffer(valueLength + 24);
            byte[] bytes = null;
            try {
                bytes = data.getBytes(characterEncoding);
            }
            catch (UnsupportedEncodingException ex) {
                bytes = data.getBytes();
            }
            for (int bytesLength = bytes.length, i = 0; i < bytesLength; ++i) {
                final byte byteValue = bytes[i];
                if (byteValue == 32) {
                    buffer.append('+');
                }
                else if (byteValue >= 0 && byteValue <= 255 && TrackingUtil.mDontNeedEncoding.get(byteValue)) {
                    final char charValue = (char)bytes[i];
                    buffer.append(charValue);
                }
                else {
                    buffer.append('%');
                    buffer.append(TrackingUtil.HEX_VALUES[byteValue >> 4 & 0xF]);
                    buffer.append(TrackingUtil.HEX_VALUES[byteValue & 0xF]);
                }
            }
            retVal = buffer.toString();
        }
        return retVal;
    }
    
    public static String getError(final int errorCode) {
        final String key = String.valueOf(errorCode);
        return getError(key);
    }
    
    public static String getError(final String errorKey) {
        if (TrackingUtil.mErrors == null) {
            TrackingUtil.mErrors = ResourceBundle.getBundle("com.lotus.elearn.tracking.TrackingErrors");
        }
        return TrackingUtil.mErrors.getString(errorKey);
    }
    
    public static String secondsToText(final double seconds, final String format) {
        final Calendar calendar = Calendar.getInstance();
        int decimalIndex = String.valueOf(seconds).indexOf(46);
        calendar.set(1900, 0, 1, 0, 0, 0);
        calendar.set(14, 0);
        calendar.add(13, (int)seconds);
        if (decimalIndex != -1) {
            final String value = String.valueOf(seconds).substring(decimalIndex);
            final double milliseconds = Double.valueOf(value) * 100.0;
            calendar.add(14, (int)milliseconds);
        }
        final SimpleDateFormat timeFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        final Date time = calendar.getTime();
        String retVal = timeFormat.format(time);
        if (calendar.get(14) == 0) {
            decimalIndex = retVal.indexOf(46);
            if (decimalIndex != -1) {
                retVal = retVal.substring(0, decimalIndex);
            }
        }
        if (format.startsWith("HH")) {
            final int days = calendar.get(6) - 1;
            if (days > 0) {
                int hours = calendar.get(10);
                hours += days * 24;
                retVal = String.valueOf(String.valueOf(hours)) + retVal.substring(2);
            }
        }
        return retVal;
    }
    
    public static String secondsToText(final Double seconds, final String format) {
        String retVal = "";
        if (seconds != null) {
            retVal = secondsToText((double)seconds, format);
        }
        return retVal;
    }
    
    public static String substitute(final String original, final String pattern, final String value) {
        return substitute(original, pattern, value, true);
    }
    
    public static String substitute(String original, String pattern, final String value, final boolean caseSensitive) {
        String retVal = original;
        if (original != null && original.length() > 0 && pattern != null && pattern.length() > 0 && value != null && !value.equals(pattern)) {
            if (!caseSensitive) {
                original = original.toUpperCase();
                pattern = pattern.toUpperCase();
            }
            final int originalLength = original.length();
            final StringBuffer buffer = new StringBuffer(originalLength);
            final int patternLength = pattern.length();
            int scanStart = 0;
            for (int patternPosition = original.indexOf(pattern, scanStart); patternPosition != -1; patternPosition = original.indexOf(pattern, scanStart)) {
                final String startString = String.valueOf(retVal.substring(scanStart, patternPosition)) + value;
                buffer.append(startString);
                scanStart = patternPosition + patternLength;
            }
            final String endString = retVal.substring(scanStart, originalLength);
            buffer.append(endString);
            retVal = buffer.toString();
        }
        return retVal;
    }
    
    public static Double textToDecimal(final String value) {
        Double retVal = null;
        if (value != null) {
            try {
                retVal = Double.valueOf(value.trim());
            }
            catch (NumberFormatException ex) {
                retVal = null;
            }
        }
        return retVal;
    }
    
    public static double textToSeconds(final String time) {
        double retVal = 0.0;
        final StringTokenizer tokenizer = new StringTokenizer(time, ":");
        if (tokenizer.countTokens() == 3) {
            retVal += Integer.parseInt(tokenizer.nextToken()) * 3600;
            retVal += Integer.parseInt(tokenizer.nextToken()) * 60;
            retVal += Double.valueOf(tokenizer.nextToken());
        }
        return retVal;
    }
    
    static {
        HEX_VALUES = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        TrackingUtil.mErrors = null;
        TrackingUtil.mDontNeedEncoding = new BitSet(256);
        for (int i = 97; i <= 122; ++i) {
            TrackingUtil.mDontNeedEncoding.set(i);
        }
        for (int i = 65; i <= 90; ++i) {
            TrackingUtil.mDontNeedEncoding.set(i);
        }
        for (int i = 48; i <= 57; ++i) {
            TrackingUtil.mDontNeedEncoding.set(i);
        }
        TrackingUtil.mDontNeedEncoding.set(45);
        TrackingUtil.mDontNeedEncoding.set(95);
        TrackingUtil.mDontNeedEncoding.set(46);
        TrackingUtil.mDontNeedEncoding.set(42);
    }
}
