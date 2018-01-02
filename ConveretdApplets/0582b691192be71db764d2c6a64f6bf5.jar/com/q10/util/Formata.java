// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.util;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.DateFormatSymbols;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Formata
{
    public static DecimalFormat formataDecimal(final int n, final String s) {
        String s2 = "####0.00";
        switch (n) {
            case 0: {
                s2 = "#####0";
                break;
            }
            case 1: {
                s2 = "####0.0";
                break;
            }
            case 2: {
                s2 = "###0.00";
                break;
            }
            case 3: {
                s2 = "##0.000";
                break;
            }
            case 4: {
                s2 = "#0.0000";
                break;
            }
            case 5: {
                s2 = "0.00000";
                break;
            }
        }
        final DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(getLocale(s));
        decimalFormat.applyPattern(s2);
        return decimalFormat;
    }
    
    public static DecimalFormat formataDecimal(final int n) {
        return formataDecimal(n, "br");
    }
    
    public static DateFormat formataData(final char c, final String s) {
        final boolean b = s.equals("br") || s.equals("pt");
        final String s2 = b ? "dd/MM/yyyy" : "MM/dd/yyyy";
        final String s3 = b ? "dd/MM H:mm" : "MM/dd H:mm";
        final String s4 = b ? "dd/MM" : "MM/dd";
        final String s5 = "MM/yy";
        final String s6 = "ddMMyyyy";
        final String s7 = "H:mm";
        final String s8 = "MM/yyyy";
        final Locale locale = getLocale(s);
        DateFormat dateFormat = null;
        switch (c) {
            case 'D': {
                dateFormat = new SimpleDateFormat(s2, new DateFormatSymbols(locale));
                break;
            }
            case 'I': {
                dateFormat = new SimpleDateFormat(s3, new DateFormatSymbols(locale));
                break;
            }
            case 'G': {
                dateFormat = new SimpleDateFormat(s4, new DateFormatSymbols(locale));
                break;
            }
            case 'A': {
                dateFormat = new SimpleDateFormat(s5, new DateFormatSymbols(locale));
                break;
            }
            case 'E': {
                dateFormat = new SimpleDateFormat(s6, new DateFormatSymbols(locale));
                break;
            }
            case 'H': {
                dateFormat = new SimpleDateFormat(s7, new DateFormatSymbols(locale));
                break;
            }
            case 'M': {
                dateFormat = new SimpleDateFormat(s8, new DateFormatSymbols(locale));
                break;
            }
        }
        return dateFormat;
    }
    
    public static DateFormat formataData(final char c) {
        return formataData(c, "br");
    }
    
    public static String dataFormatada(final GregorianCalendar gregorianCalendar) {
        final StringBuffer sb = new StringBuffer();
        sb.append((gregorianCalendar.get(5) < 10) ? ("0" + String.valueOf(gregorianCalendar.get(5))) : String.valueOf(gregorianCalendar.get(5)));
        final int n = gregorianCalendar.get(2) + 1;
        sb.append((n < 10) ? ("0" + String.valueOf(n)) : String.valueOf(n));
        sb.append(String.valueOf(gregorianCalendar.get(1)));
        return sb.toString();
    }
    
    public static Locale getLocale(final String s) {
        if (s.equalsIgnoreCase("en")) {
            return Locale.US;
        }
        return new Locale("pt", "BR");
    }
}
