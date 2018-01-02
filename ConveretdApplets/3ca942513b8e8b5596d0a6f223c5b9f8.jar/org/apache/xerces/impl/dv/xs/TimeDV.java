// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import java.math.BigInteger;
import java.math.BigDecimal;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class TimeDV extends AbstractDateTimeDV
{
    public Object getActualValue(final String s, final ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return this.parse(s);
        }
        catch (Exception ex) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { s, "time" });
        }
    }
    
    protected DateTimeData parse(final String s) throws SchemaDateTimeException {
        final DateTimeData dateTimeData = new DateTimeData(s, this);
        final int length = s.length();
        dateTimeData.year = 2000;
        dateTimeData.month = 1;
        dateTimeData.day = 15;
        this.getTime(s, 0, length, dateTimeData);
        this.validateDateTime(dateTimeData);
        this.saveUnnormalized(dateTimeData);
        if (dateTimeData.utc != 0 && dateTimeData.utc != 90) {
            this.normalize(dateTimeData);
        }
        dateTimeData.position = 2;
        return dateTimeData;
    }
    
    protected String dateToString(final DateTimeData dateTimeData) {
        final StringBuffer sb = new StringBuffer(16);
        this.append(sb, dateTimeData.hour, 2);
        sb.append(':');
        this.append(sb, dateTimeData.minute, 2);
        sb.append(':');
        this.append(sb, dateTimeData.second);
        this.append(sb, (char)dateTimeData.utc, 0);
        return sb.toString();
    }
    
    protected XMLGregorianCalendar getXMLGregorianCalendar(final DateTimeData dateTimeData) {
        return super.factory.newXMLGregorianCalendar(null, Integer.MIN_VALUE, Integer.MIN_VALUE, dateTimeData.unNormHour, dateTimeData.unNormMinute, (int)dateTimeData.unNormSecond, (dateTimeData.unNormSecond != 0.0) ? new BigDecimal(dateTimeData.unNormSecond - (int)dateTimeData.unNormSecond) : null, dateTimeData.timezoneHr * 60 + dateTimeData.timezoneMin);
    }
}
