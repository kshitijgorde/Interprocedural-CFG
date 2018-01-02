// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.ByteArrayInputStream;
import java.awt.Image;
import java.awt.MediaTracker;

public class NF11Util
{
    public static void removeImage(final MediaTracker mediaTracker, final Image image, final int n, final int n2, final int n3) {
        mediaTracker.removeImage(image, n, n2, n3);
    }
    
    public static void removeImage(final MediaTracker mediaTracker, final Image image, final int n) {
        mediaTracker.removeImage(image, n);
    }
    
    public static void removeImage(final MediaTracker mediaTracker, final Image image) {
        mediaTracker.removeImage(image);
    }
    
    public static ByteArrayInputStream getInputStreamForString(final String s) {
        return new ByteArrayInputStream(s.getBytes());
    }
    
    public static String formatDate(final Date date, final String s) {
        String format = null;
        try {
            format = new SimpleDateFormat(s).format(date);
        }
        catch (Exception ex) {}
        return format;
    }
    
    public static Date parseDate(final String s, final String s2) {
        Date parse = null;
        try {
            parse = new SimpleDateFormat(s2).parse(s);
        }
        catch (Exception ex) {}
        return parse;
    }
    
    public static String formatDecimal(final Number n, final int groupingSize, final char groupingSeparator, final char decimalSeparator, final String s) {
        String format = null;
        try {
            final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
            decimalFormatSymbols.setGroupingSeparator(groupingSeparator);
            decimalFormatSymbols.setDecimalSeparator(decimalSeparator);
            final DecimalFormat decimalFormat = new DecimalFormat(s, decimalFormatSymbols);
            decimalFormat.setGroupingSize(groupingSize);
            format = decimalFormat.format(n);
        }
        catch (Exception ex) {}
        return format;
    }
    
    public static String formatDecimal(final Number n, final String s) {
        String format = null;
        try {
            format = new DecimalFormat(s).format(n);
        }
        catch (Exception ex) {}
        return format;
    }
}
