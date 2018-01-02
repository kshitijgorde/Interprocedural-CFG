// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.datatype;

import java.util.GregorianCalendar;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.Duration;
import javax.xml.datatype.DatatypeFactory;

public class DatatypeFactoryImpl extends DatatypeFactory
{
    public Duration newDuration(final String s) {
        return new DurationImpl(s, DatatypeConstants.DURATION);
    }
    
    public Duration newDuration(final long n) {
        return new DurationImpl(n);
    }
    
    public Duration newDuration(final boolean b, final BigInteger bigInteger, final BigInteger bigInteger2, final BigInteger bigInteger3, final BigInteger bigInteger4, final BigInteger bigInteger5, final BigDecimal bigDecimal) {
        return new DurationImpl(b, bigInteger, bigInteger2, bigInteger3, bigInteger4, bigInteger5, bigDecimal);
    }
    
    public Duration newDuration(final boolean b, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return new DurationImpl(((n == Integer.MIN_VALUE || n == 0) && (n2 == Integer.MIN_VALUE || n2 == 0) && (n3 == Integer.MIN_VALUE || n3 == 0) && (n4 == Integer.MIN_VALUE || n4 == 0) && (n5 == Integer.MIN_VALUE || n5 == 0) && (n6 == Integer.MIN_VALUE || n6 == 0)) ? 0 : (b ? 1 : -1), (n != Integer.MIN_VALUE) ? BigInteger.valueOf(n) : null, (n2 != Integer.MIN_VALUE) ? BigInteger.valueOf(n2) : null, (n3 != Integer.MIN_VALUE) ? BigInteger.valueOf(n3) : null, (n4 != Integer.MIN_VALUE) ? BigInteger.valueOf(n4) : null, (n5 != Integer.MIN_VALUE) ? BigInteger.valueOf(n5) : null, (n6 != Integer.MIN_VALUE) ? BigDecimal.valueOf(n6) : null);
    }
    
    public Duration newDurationDayTime(final long n) {
        return new DurationImpl(n);
    }
    
    public Duration newDurationDayTime(final String s) {
        return new DurationImpl(s, DatatypeConstants.DURATION_DAYTIME);
    }
    
    public Duration newDurationYearMonth(final long n) {
        return new DurationImpl(n);
    }
    
    public Duration newDurationYearMonth(final String s) {
        return new DurationImpl(s, DatatypeConstants.DURATION_YEARMONTH);
    }
    
    public XMLGregorianCalendar newXMLGregorianCalendar() {
        return new XMLGregorianCalendarImpl();
    }
    
    public XMLGregorianCalendar newXMLGregorianCalendar(final String s) {
        return new XMLGregorianCalendarImpl(s);
    }
    
    public XMLGregorianCalendar newXMLGregorianCalendar(final GregorianCalendar gregorianCalendar) {
        return new XMLGregorianCalendarImpl(gregorianCalendar);
    }
    
    public XMLGregorianCalendar newXMLGregorianCalendar(final BigInteger bigInteger, final int n, final int n2, final int n3, final int n4, final int n5, final BigDecimal bigDecimal, final int n6) {
        return new XMLGregorianCalendarImpl(bigInteger, n, n2, n3, n4, n5, bigDecimal, n6);
    }
}
