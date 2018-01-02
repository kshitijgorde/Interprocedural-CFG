// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class XDate
{
    static final TimeZone timezone;
    static final String _dateFormat = "yyyy-MM-dd";
    static final SimpleDateFormat dateFormat;
    static final String _dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    static final SimpleDateFormat dateTimeFormat;
    
    static {
        timezone = TimeZone.getTimeZone("GMT");
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    }
    
    public static String date() {
        try {
            final GregorianCalendar gc = new GregorianCalendar(XDate.timezone);
            final DatatypeFactory dtf = DatatypeFactory.newInstance();
            final XMLGregorianCalendar calendar = dtf.newXMLGregorianCalendar(gc);
            calendar.setHour(Integer.MIN_VALUE);
            calendar.setMinute(Integer.MIN_VALUE);
            calendar.setSecond(Integer.MIN_VALUE);
            calendar.setTimezone(Integer.MIN_VALUE);
            return calendar.toXMLFormat();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static String time() {
        try {
            final GregorianCalendar gc = new GregorianCalendar(XDate.timezone);
            final DatatypeFactory dtf = DatatypeFactory.newInstance();
            final XMLGregorianCalendar calendar = dtf.newXMLGregorianCalendar(gc);
            calendar.setDay(Integer.MIN_VALUE);
            calendar.setMonth(Integer.MIN_VALUE);
            calendar.setYear(Integer.MIN_VALUE);
            return calendar.toXMLFormat();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static String dateTime() {
        try {
            final GregorianCalendar gc = new GregorianCalendar(XDate.timezone);
            final DatatypeFactory dtf = DatatypeFactory.newInstance();
            final XMLGregorianCalendar calendar = dtf.newXMLGregorianCalendar(gc);
            return calendar.toXMLFormat();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static String dateTime(final Date date) {
        if (date == null) {
            return null;
        }
        try {
            final GregorianCalendar gc = new GregorianCalendar(XDate.timezone);
            gc.setTime(date);
            final DatatypeFactory dtf = DatatypeFactory.newInstance();
            final XMLGregorianCalendar calendar = dtf.newXMLGregorianCalendar(gc);
            return calendar.toXMLFormat();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static Date parse(final String s) {
        Date result = parse(XDate.dateTimeFormat, s);
        if (result == null) {
            result = parse(XDate.dateFormat, s);
        }
        return result;
    }
    
    private static Date parse(final SimpleDateFormat f, final String s) {
        Date result = null;
        try {
            result = f.parse(convertTimezone(s));
        }
        catch (Exception ex) {}
        return result;
    }
    
    private static String convertTimezone(final String dt) {
        final StringBuffer sb = new StringBuffer(dt);
        sb.deleteCharAt(dt.lastIndexOf(90));
        return sb.toString();
    }
}
