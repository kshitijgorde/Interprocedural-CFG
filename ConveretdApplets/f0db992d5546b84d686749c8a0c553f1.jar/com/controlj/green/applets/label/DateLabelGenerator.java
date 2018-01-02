// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.label;

import java.util.Calendar;
import java.util.GregorianCalendar;
import com.controlj.green.applets.Rounder;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class DateLabelGenerator extends LabelGenerator
{
    private static final String TIME_FORMAT_EMPTY = "";
    private static final String DAYS_FORMAT = "";
    private static final String HOURS_FORMAT_24 = "H:mm";
    private static final String MINUTES_FORMAT_24 = "H:mm";
    private static final String SECONDS_FORMAT_24 = "H:mm:ss";
    private static final String MILLIS_FORMAT_24 = "H:mm:ss:SS";
    private static final String HOURS_FORMAT_12 = "h:mm a";
    private static final String MINUTES_FORMAT_12 = "h:mm a";
    private static final String SECONDS_FORMAT_12 = "h:mm:ss a";
    private static final String MILLIS_FORMAT_12 = "h:mm:ss:SS a";
    private static final String MONTH_PATTERN = "MM";
    private static final String DAY_PATTERN = "dd";
    private static final String YEAR_PATTERN = "yyyy";
    private static final String DATE_FORMAT_EMPTY = " ";
    private static final String TIMEDATE_SEPARATOR = "\n";
    private SimpleDateFormat dateformater;
    private String timeFormat;
    private String dateFormat;
    private boolean showTransitions;
    private boolean standardTime;
    private int standardDate;
    private String separator;
    
    public DateLabelGenerator(final TimeZone timeZone, final int standardDate, final boolean standardTime, final String separator) {
        this.dateformater = new SimpleDateFormat();
        this.timeFormat = "H:mm:ss:SS";
        this.dateFormat = " ";
        this.showTransitions = false;
        this.standardTime = true;
        this.standardDate = 1;
        this.separator = "/";
        this.setupDateTime(timeZone, standardDate, standardTime, null, separator);
        this.refreshFormatter(false);
    }
    
    private void setupDateTime(final TimeZone timeZone, final int standardDate, final boolean standardTime, final Locale loc, final String separator) {
        this.setupTime(standardTime);
        this.standardDate = standardDate;
        this.separator = separator;
        if (loc != null) {
            this.setLocale(loc);
        }
        this.dateFormat = this.createDateFormat();
        this.setTimeZone(timeZone);
    }
    
    private void setupTime(final boolean standardTime) {
        this.standardTime = standardTime;
        if (standardTime) {
            this.timeFormat = "h:mm:ss:SS a";
        }
        else {
            this.timeFormat = "H:mm:ss:SS";
        }
    }
    
    public DateLabelGenerator(final Locale loc, final TimeZone timeZone, final int standardDate, final boolean standardTime, final String separator) {
        this.dateformater = new SimpleDateFormat();
        this.timeFormat = "H:mm:ss:SS";
        this.dateFormat = " ";
        this.showTransitions = false;
        this.standardTime = true;
        this.standardDate = 1;
        this.separator = "/";
        this.setupDateTime(timeZone, standardDate, standardTime, loc, separator);
    }
    
    public void setLocale(final Locale loc) {
        super.setLocale(loc);
        this.dateformater = new SimpleDateFormat("", loc);
        this.refreshFormatter(false);
    }
    
    public void setTimeZone(final TimeZone timezone) {
        if (timezone == null) {
            this.dateformater.setTimeZone(TimeZone.getDefault());
        }
        else {
            this.dateformater.setTimeZone(timezone);
        }
    }
    
    public TimeZone getTimeZone() {
        return this.dateformater.getTimeZone();
    }
    
    public void showTransitions(final boolean showTrans) {
        this.showTransitions = showTrans;
    }
    
    public String makeLabel(final double value) {
        if (this.showTransitions && value % 8.64E7 == 0.0) {
            this.refreshFormatter(true);
            final String temp = this.dateformater.format(new Date((long)value));
            this.refreshFormatter(false);
            return temp;
        }
        return this.dateformater.format(new Date((long)value));
    }
    
    private void refreshFormatter(final boolean forceDate) {
        final StringBuffer pattern = new StringBuffer();
        final String timePattern = this.timeFormat;
        pattern.append(timePattern);
        String datePattern = this.dateFormat;
        if (forceDate) {
            datePattern = this.createDateFormat();
        }
        final boolean isTimeEmpty = timePattern.equals("");
        final boolean isDateEmpty = datePattern.equals(" ");
        if (this.showTransitions || (!isTimeEmpty && !isDateEmpty)) {
            if (isTimeEmpty) {
                pattern.append(datePattern);
                if (this.showTransitions) {
                    pattern.append("\n");
                }
            }
            else {
                pattern.append("\n");
                pattern.append(datePattern);
            }
        }
        else {
            pattern.append(datePattern);
        }
        this.dateformater.applyPattern(pattern.toString());
    }
    
    protected double calculateIncrement(final double minValue, final double maxValue) {
        return Rounder.round((maxValue - minValue) / super.numberOfLabels, 2);
    }
    
    protected double calculateStartValue(final double minValue, final double maxValue) {
        double value = Rounder.roundDownToIncrement(minValue, super.increment);
        if (this.getIncrement() > 3600000.0) {
            final Calendar cal = new GregorianCalendar(this.dateformater.getTimeZone());
            cal.setTime(new Date((long)value));
            final long hour = cal.get(11);
            if (hour < 12L) {
                cal.set(11, 0);
            }
            else {
                cal.set(11, 12);
            }
            value = cal.getTime().getTime();
        }
        final double increment = this.getIncrement();
        this.dateFormat = " ";
        if (increment >= 8.64E7) {
            this.timeFormat = "";
            this.dateFormat = this.createDateFormat();
        }
        else if (increment >= 3600000.0) {
            if (this.standardTime) {
                this.timeFormat = "h:mm a";
            }
            else {
                this.timeFormat = "H:mm";
            }
        }
        else if (increment >= 60000.0) {
            if (this.standardTime) {
                this.timeFormat = "h:mm a";
            }
            else {
                this.timeFormat = "H:mm";
            }
        }
        else if (increment >= 1000.0) {
            if (this.standardTime) {
                this.timeFormat = "h:mm:ss a";
            }
            else {
                this.timeFormat = "H:mm:ss";
            }
        }
        else if (increment < 1000.0) {
            if (this.standardTime) {
                this.timeFormat = "h:mm:ss:SS a";
            }
            else {
                this.timeFormat = "H:mm:ss:SS";
            }
        }
        this.refreshFormatter(false);
        return value;
    }
    
    private String createDateFormat() {
        String retVal = " ";
        switch (this.standardDate) {
            case 1: {
                retVal = "MM" + this.separator + "dd" + this.separator + "yyyy";
                break;
            }
            case 2: {
                retVal = "dd" + this.separator + "MM" + this.separator + "yyyy";
                break;
            }
            case 3: {
                retVal = "yyyy" + this.separator + "MM" + this.separator + "dd";
                break;
            }
            default: {
                throw new RuntimeException("invalid date format: " + this.standardDate);
            }
        }
        return retVal;
    }
}
