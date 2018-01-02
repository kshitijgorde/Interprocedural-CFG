// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.datatype;

import java.math.BigDecimal;
import javax.xml.namespace.QName;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.Calendar;

public abstract class Duration
{
    public abstract Duration add(final Duration p0);
    
    public abstract void addTo(final Calendar p0);
    
    public void addTo(final Date time) {
        if (time == null) {
            throw new NullPointerException("date is null");
        }
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        this.addTo(gregorianCalendar);
        time.setTime(gregorianCalendar.getTime().getTime());
    }
    
    public abstract int compare(final Duration p0);
    
    public boolean equals(final Object o) {
        if (o == null) {
            throw new NullPointerException("duration is null");
        }
        if (!(o instanceof Duration)) {
            throw new UnsupportedOperationException("Not a valid duration");
        }
        return this.compare((Duration)o) == 0;
    }
    
    public abstract Number getField(final DatatypeConstants.Field p0);
    
    public abstract boolean isSet(final DatatypeConstants.Field p0);
    
    public int getDays() {
        if (this.isSet(DatatypeConstants.DAYS)) {
            return this.getField(DatatypeConstants.DAYS).intValue();
        }
        return 0;
    }
    
    public int getHours() {
        if (this.isSet(DatatypeConstants.HOURS)) {
            return this.getField(DatatypeConstants.HOURS).intValue();
        }
        return 0;
    }
    
    public int getMonths() {
        if (this.isSet(DatatypeConstants.MONTHS)) {
            return this.getField(DatatypeConstants.MONTHS).intValue();
        }
        return 0;
    }
    
    public int getMinutes() {
        if (this.isSet(DatatypeConstants.MINUTES)) {
            return this.getField(DatatypeConstants.MINUTES).intValue();
        }
        return 0;
    }
    
    public int getSeconds() {
        if (this.isSet(DatatypeConstants.SECONDS)) {
            return this.getField(DatatypeConstants.SECONDS).intValue();
        }
        return 0;
    }
    
    public int getYears() {
        if (this.isSet(DatatypeConstants.YEARS)) {
            return this.getField(DatatypeConstants.YEARS).intValue();
        }
        return 0;
    }
    
    public abstract int getSign();
    
    public long getTimeInMillis(final Calendar calendar) {
        final Calendar calendar2 = (Calendar)calendar.clone();
        this.addTo(calendar);
        return calendar.getTime().getTime() - calendar2.getTime().getTime();
    }
    
    public long getTimeInMillis(final Date date) {
        final Date date2 = (Date)date.clone();
        this.addTo(date);
        return date.getTime() - date2.getTime();
    }
    
    public QName getXMLSchemaType() {
        final boolean set = this.isSet(DatatypeConstants.DAYS);
        final boolean set2 = this.isSet(DatatypeConstants.HOURS);
        final boolean set3 = this.isSet(DatatypeConstants.MINUTES);
        final boolean set4 = this.isSet(DatatypeConstants.SECONDS);
        final boolean set5 = this.isSet(DatatypeConstants.YEARS);
        final boolean set6 = this.isSet(DatatypeConstants.MONTHS);
        if (set5 && set6) {
            if (set && set2 && set3 && set4) {
                return DatatypeConstants.DURATION;
            }
            if (!set && !set2 && !set3 && !set4) {
                return DatatypeConstants.DURATION_YEARMONTH;
            }
        }
        else if (!set5 && !set6 && set && set2 && set3 && set4) {
            return DatatypeConstants.DURATION_DAYTIME;
        }
        throw new IllegalStateException("invalid duration");
    }
    
    public abstract int hashCode();
    
    public boolean isLongerThan(final Duration duration) {
        return this.compare(duration) == 1;
    }
    
    public boolean isShorterThan(final Duration duration) {
        return this.compare(duration) == -1;
    }
    
    public abstract Duration multiply(final BigDecimal p0);
    
    public Duration multiply(final int n) {
        return this.multiply(BigDecimal.valueOf(n));
    }
    
    public abstract Duration negate();
    
    public abstract Duration normalizeWith(final Calendar p0);
    
    public Duration subtract(final Duration duration) {
        return this.add(duration.negate());
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        if (this.getSign() < 0) {
            sb.append('-');
        }
        sb.append('P');
        final Number field = this.getField(DatatypeConstants.YEARS);
        if (field != null) {
            sb.append(field);
            sb.append('Y');
        }
        final Number field2 = this.getField(DatatypeConstants.MONTHS);
        if (field2 != null) {
            sb.append(field2);
            sb.append('M');
        }
        final Number field3 = this.getField(DatatypeConstants.DAYS);
        if (field3 != null) {
            sb.append(field3);
            sb.append('D');
        }
        final Number field4 = this.getField(DatatypeConstants.HOURS);
        final Number field5 = this.getField(DatatypeConstants.MINUTES);
        final Number field6 = this.getField(DatatypeConstants.SECONDS);
        if (field4 != null || field5 != null || field6 != null) {
            sb.append('T');
            if (field4 != null) {
                sb.append(field5);
                sb.append('H');
            }
            if (field5 != null) {
                sb.append(field5);
                sb.append('M');
            }
            if (field6 != null) {
                sb.append(field6);
                sb.append('S');
            }
        }
        return sb.toString();
    }
}
