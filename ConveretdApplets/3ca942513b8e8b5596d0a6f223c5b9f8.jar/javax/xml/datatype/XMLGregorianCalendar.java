// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.datatype;

import java.util.Locale;
import java.util.TimeZone;
import java.util.GregorianCalendar;
import javax.xml.namespace.QName;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class XMLGregorianCalendar implements Cloneable
{
    public abstract void clear();
    
    public abstract void reset();
    
    public abstract void setYear(final BigInteger p0);
    
    public abstract void setYear(final int p0);
    
    public abstract void setMonth(final int p0);
    
    public abstract void setDay(final int p0);
    
    public abstract void setTimezone(final int p0);
    
    public void setTime(final int hour, final int minute, final int second) {
        this.setHour(hour);
        this.setMinute(minute);
        this.setSecond(second);
    }
    
    public abstract void setHour(final int p0);
    
    public abstract void setMinute(final int p0);
    
    public abstract void setSecond(final int p0);
    
    public abstract void setMillisecond(final int p0);
    
    public abstract void setFractionalSecond(final BigDecimal p0);
    
    public void setTime(final int n, final int n2, final int n3, final BigDecimal fractionalSecond) {
        this.setTime(n, n2, n3);
        this.setFractionalSecond(fractionalSecond);
    }
    
    public void setTime(final int n, final int n2, final int n3, final int millisecond) {
        this.setTime(n, n2, n3);
        this.setMillisecond(millisecond);
    }
    
    public abstract BigInteger getEon();
    
    public abstract int getYear();
    
    public abstract BigInteger getEonAndYear();
    
    public abstract int getMonth();
    
    public abstract int getDay();
    
    public abstract int getTimezone();
    
    public abstract int getHour();
    
    public abstract int getMinute();
    
    public abstract int getSecond();
    
    public int getMillisecond() {
        final BigDecimal fractionalSecond = this.getFractionalSecond();
        if (fractionalSecond != null) {
            return (int)(fractionalSecond.doubleValue() * 1000.0);
        }
        return Integer.MIN_VALUE;
    }
    
    public abstract BigDecimal getFractionalSecond();
    
    public abstract int compare(final XMLGregorianCalendar p0);
    
    public abstract XMLGregorianCalendar normalize();
    
    public boolean equals(final Object o) {
        return o instanceof XMLGregorianCalendar && this.compare((XMLGregorianCalendar)o) == 0;
    }
    
    public int hashCode() {
        XMLGregorianCalendar normalize = this;
        if (this.getTimezone() != 0) {
            normalize = this.normalize();
        }
        return normalize.getYear() + normalize.getMonth() + normalize.getDay() + normalize.getHour() + normalize.getMinute() + normalize.getSecond();
    }
    
    public abstract String toXMLFormat();
    
    public abstract QName getXMLSchemaType();
    
    public String toString() {
        return this.toXMLFormat();
    }
    
    public abstract boolean isValid();
    
    public abstract void add(final Duration p0);
    
    public abstract GregorianCalendar toGregorianCalendar();
    
    public abstract GregorianCalendar toGregorianCalendar(final TimeZone p0, final Locale p1, final XMLGregorianCalendar p2);
    
    public abstract TimeZone getTimeZone(final int p0);
    
    public abstract Object clone();
}
