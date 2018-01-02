// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.datatype;

import java.util.GregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class DatatypeFactory
{
    public static final String DATATYPEFACTORY_PROPERTY = "javax.xml.datatype.DatatypeFactory";
    public static final String DATATYPEFACTORY_IMPLEMENTATION_CLASS = "org.apache.xerces.jaxp.datatype.DatatypeFactoryImpl";
    
    public static DatatypeFactory newInstance() throws DatatypeConfigurationException {
        try {
            return (DatatypeFactory)FactoryFinder.find("javax.xml.datatype.DatatypeFactory", "org.apache.xerces.jaxp.datatype.DatatypeFactoryImpl");
        }
        catch (FactoryFinder.ConfigurationError configurationError) {
            throw new DatatypeConfigurationException(configurationError.getMessage(), configurationError.getException());
        }
    }
    
    public abstract Duration newDuration(final String p0);
    
    public abstract Duration newDuration(final long p0);
    
    public abstract Duration newDuration(final boolean p0, final BigInteger p1, final BigInteger p2, final BigInteger p3, final BigInteger p4, final BigInteger p5, final BigDecimal p6);
    
    public Duration newDuration(final boolean b, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return this.newDuration(b, (n != Integer.MIN_VALUE) ? BigInteger.valueOf(n) : null, (n2 != Integer.MIN_VALUE) ? BigInteger.valueOf(n2) : null, (n3 != Integer.MIN_VALUE) ? BigInteger.valueOf(n3) : null, (n4 != Integer.MIN_VALUE) ? BigInteger.valueOf(n4) : null, (n5 != Integer.MIN_VALUE) ? BigInteger.valueOf(n5) : null, (n6 != Integer.MIN_VALUE) ? BigDecimal.valueOf(n6) : null);
    }
    
    public Duration newDurationDayTime(final String s) {
        return this.newDuration(s);
    }
    
    public Duration newDurationDayTime(final long n) {
        return this.newDuration(n);
    }
    
    public Duration newDurationDayTime(final boolean b, final BigInteger bigInteger, final BigInteger bigInteger2, final BigInteger bigInteger3, final BigInteger bigInteger4) {
        return this.newDuration(b, null, null, bigInteger, bigInteger2, bigInteger3, (bigInteger4 != null) ? new BigDecimal(bigInteger4) : null);
    }
    
    public Duration newDurationDayTime(final boolean b, final int n, final int n2, final int n3, final int n4) {
        return this.newDuration(b, Integer.MIN_VALUE, Integer.MIN_VALUE, n, n2, n3, n4);
    }
    
    public Duration newDurationYearMonth(final String s) {
        return this.newDuration(s);
    }
    
    public Duration newDurationYearMonth(final long n) {
        return this.newDuration(n);
    }
    
    public Duration newDurationYearMonth(final boolean b, final BigInteger bigInteger, final BigInteger bigInteger2) {
        return this.newDuration(b, bigInteger, bigInteger2, null, null, null, null);
    }
    
    public Duration newDurationYearMonth(final boolean b, final int n, final int n2) {
        return this.newDuration(b, (n != Integer.MIN_VALUE) ? BigInteger.valueOf(n) : null, (n2 != Integer.MIN_VALUE) ? BigInteger.valueOf(n2) : null, null, null, null, null);
    }
    
    public abstract XMLGregorianCalendar newXMLGregorianCalendar();
    
    public abstract XMLGregorianCalendar newXMLGregorianCalendar(final String p0);
    
    public abstract XMLGregorianCalendar newXMLGregorianCalendar(final GregorianCalendar p0);
    
    public abstract XMLGregorianCalendar newXMLGregorianCalendar(final BigInteger p0, final int p1, final int p2, final int p3, final int p4, final int p5, final BigDecimal p6, final int p7);
    
    public XMLGregorianCalendar newXMLGregorianCalendar(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        return this.newXMLGregorianCalendar((n != Integer.MIN_VALUE) ? BigInteger.valueOf(n) : null, n2, n3, n4, n5, n6, (n7 != Integer.MIN_VALUE) ? new BigDecimal(String.valueOf(n7 / 1000.0)) : null, n8);
    }
    
    public XMLGregorianCalendar newXMLGregorianCalendarDate(final int n, final int n2, final int n3, final int n4) {
        return this.newXMLGregorianCalendar((n != Integer.MIN_VALUE) ? BigInteger.valueOf(n) : null, n2, n3, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, null, n4);
    }
    
    public XMLGregorianCalendar newXMLGregorianCalendarTime(final int n, final int n2, final int n3, final int n4) {
        return this.newXMLGregorianCalendar(null, Integer.MIN_VALUE, Integer.MIN_VALUE, n, n2, n3, null, n4);
    }
    
    public XMLGregorianCalendar newXMLGregorianCalendarTime(final int n, final int n2, final int n3, final BigDecimal bigDecimal, final int n4) {
        return this.newXMLGregorianCalendar(null, Integer.MIN_VALUE, Integer.MIN_VALUE, n, n2, n3, bigDecimal, n4);
    }
    
    public XMLGregorianCalendar newXMLGregorianCalendarTime(final int n, final int n2, final int n3, final int n4, final int n5) {
        return this.newXMLGregorianCalendar(null, Integer.MIN_VALUE, Integer.MIN_VALUE, n, n2, n3, (n4 != Integer.MIN_VALUE) ? new BigDecimal(n4 / 1000.0) : null, n5);
    }
}
