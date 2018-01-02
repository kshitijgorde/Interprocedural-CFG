// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.util;

import java.text.ParsePosition;
import java.text.FieldPosition;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.Date;
import java.text.NumberFormat;
import java.text.DateFormat;

public class RelativeDateFormat extends DateFormat
{
    private long baseMillis;
    private boolean showZeroDays;
    private NumberFormat dayFormatter;
    private String daySuffix;
    private String hourSuffix;
    private String minuteSuffix;
    private NumberFormat secondFormatter;
    private String secondSuffix;
    private static long MILLISECONDS_IN_ONE_HOUR;
    private static long MILLISECONDS_IN_ONE_DAY;
    
    public RelativeDateFormat() {
        this(0L);
    }
    
    public RelativeDateFormat(final Date time) {
        this(time.getTime());
    }
    
    public RelativeDateFormat(final long baseMillis) {
        this.baseMillis = baseMillis;
        this.showZeroDays = false;
        this.dayFormatter = NumberFormat.getInstance();
        this.daySuffix = "d";
        this.hourSuffix = "h";
        this.minuteSuffix = "m";
        (this.secondFormatter = NumberFormat.getNumberInstance()).setMaximumFractionDigits(3);
        this.secondFormatter.setMinimumFractionDigits(3);
        this.secondSuffix = "s";
        this.calendar = new GregorianCalendar();
        this.numberFormat = new DecimalFormat("0");
    }
    
    public long getBaseMillis() {
        return this.baseMillis;
    }
    
    public void setBaseMillis(final long baseMillis) {
        this.baseMillis = baseMillis;
    }
    
    public boolean getShowZeroDays() {
        return this.showZeroDays;
    }
    
    public void setShowZeroDays(final boolean show) {
        this.showZeroDays = show;
    }
    
    public String getDaySuffix() {
        return this.daySuffix;
    }
    
    public void setDaySuffix(final String suffix) {
        if (suffix == null) {
            throw new IllegalArgumentException("Null 'suffix' argument.");
        }
        this.daySuffix = suffix;
    }
    
    public String getHourSuffix() {
        return this.hourSuffix;
    }
    
    public void setHourSuffix(final String suffix) {
        if (suffix == null) {
            throw new IllegalArgumentException("Null 'suffix' argument.");
        }
        this.hourSuffix = suffix;
    }
    
    public String getMinuteSuffix() {
        return this.minuteSuffix;
    }
    
    public void setMinuteSuffix(final String suffix) {
        if (suffix == null) {
            throw new IllegalArgumentException("Null 'suffix' argument.");
        }
        this.minuteSuffix = suffix;
    }
    
    public String getSecondSuffix() {
        return this.secondSuffix;
    }
    
    public void setSecondSuffix(final String suffix) {
        if (suffix == null) {
            throw new IllegalArgumentException("Null 'suffix' argument.");
        }
        this.secondSuffix = suffix;
    }
    
    public void setSecondFormatter(final NumberFormat formatter) {
        if (formatter == null) {
            throw new IllegalArgumentException("Null 'formatter' argument.");
        }
        this.secondFormatter = formatter;
    }
    
    public StringBuffer format(final Date date, final StringBuffer toAppendTo, final FieldPosition fieldPosition) {
        final long currentMillis = date.getTime();
        long elapsed = currentMillis - this.baseMillis;
        final long days = elapsed / RelativeDateFormat.MILLISECONDS_IN_ONE_DAY;
        elapsed -= days * RelativeDateFormat.MILLISECONDS_IN_ONE_DAY;
        final long hours = elapsed / RelativeDateFormat.MILLISECONDS_IN_ONE_HOUR;
        elapsed -= hours * RelativeDateFormat.MILLISECONDS_IN_ONE_HOUR;
        final long minutes = elapsed / 60000L;
        elapsed -= minutes * 60000L;
        final double seconds = elapsed / 1000.0;
        if (days != 0L || this.showZeroDays) {
            toAppendTo.append(this.dayFormatter.format(days) + this.getDaySuffix());
        }
        toAppendTo.append(String.valueOf(hours) + this.getHourSuffix());
        toAppendTo.append(String.valueOf(minutes) + this.getMinuteSuffix());
        toAppendTo.append(this.secondFormatter.format(seconds) + this.getSecondSuffix());
        return toAppendTo;
    }
    
    public Date parse(final String source, final ParsePosition pos) {
        return null;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RelativeDateFormat)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final RelativeDateFormat that = (RelativeDateFormat)obj;
        return this.baseMillis == that.baseMillis && this.showZeroDays == that.showZeroDays && this.daySuffix.equals(that.daySuffix) && this.hourSuffix.equals(that.hourSuffix) && this.minuteSuffix.equals(that.minuteSuffix) && this.secondSuffix.equals(that.secondSuffix) && this.secondFormatter.equals(that.secondFormatter);
    }
    
    public int hashCode() {
        int result = 193;
        result = 37 * result + (int)(this.baseMillis ^ this.baseMillis >>> 32);
        result = 37 * result + this.daySuffix.hashCode();
        result = 37 * result + this.hourSuffix.hashCode();
        result = 37 * result + this.minuteSuffix.hashCode();
        result = 37 * result + this.secondSuffix.hashCode();
        result = 37 * result + this.secondFormatter.hashCode();
        return result;
    }
    
    public Object clone() {
        final RelativeDateFormat clone = (RelativeDateFormat)super.clone();
        clone.dayFormatter = (NumberFormat)this.dayFormatter.clone();
        clone.secondFormatter = (NumberFormat)this.secondFormatter.clone();
        return clone;
    }
    
    public static void main(final String[] args) {
        final GregorianCalendar c0 = new GregorianCalendar(2006, 10, 1, 0, 0, 0);
        final GregorianCalendar c2 = new GregorianCalendar(2006, 10, 1, 11, 37, 43);
        c2.set(14, 123);
        System.out.println("Default: ");
        final RelativeDateFormat rdf = new RelativeDateFormat(c0.getTimeInMillis());
        System.out.println(rdf.format(c2.getTime()));
        System.out.println();
        System.out.println("Hide milliseconds: ");
        rdf.setSecondFormatter(new DecimalFormat("0"));
        System.out.println(rdf.format(c2.getTime()));
        System.out.println();
        System.out.println("Show zero day output: ");
        rdf.setShowZeroDays(true);
        System.out.println(rdf.format(c2.getTime()));
        System.out.println();
        System.out.println("Alternative suffixes: ");
        rdf.setShowZeroDays(false);
        rdf.setDaySuffix(":");
        rdf.setHourSuffix(":");
        rdf.setMinuteSuffix(":");
        rdf.setSecondSuffix("");
        System.out.println(rdf.format(c2.getTime()));
        System.out.println();
    }
    
    static {
        RelativeDateFormat.MILLISECONDS_IN_ONE_HOUR = 3600000L;
        RelativeDateFormat.MILLISECONDS_IN_ONE_DAY = 24L * RelativeDateFormat.MILLISECONDS_IN_ONE_HOUR;
    }
}
