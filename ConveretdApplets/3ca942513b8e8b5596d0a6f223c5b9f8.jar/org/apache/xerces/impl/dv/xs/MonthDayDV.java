// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class MonthDayDV extends AbstractDateTimeDV
{
    private static final int MONTHDAY_SIZE = 7;
    
    public Object getActualValue(final String s, final ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return this.parse(s);
        }
        catch (Exception ex) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { s, "gMonthDay" });
        }
    }
    
    protected DateTimeData parse(final String s) throws SchemaDateTimeException {
        final DateTimeData dateTimeData = new DateTimeData(s, this);
        final int length = s.length();
        dateTimeData.year = 2000;
        if (s.charAt(0) != '-' || s.charAt(1) != '-') {
            throw new SchemaDateTimeException("Invalid format for gMonthDay: " + s);
        }
        dateTimeData.month = this.parseInt(s, 2, 4);
        int n = 4;
        if (s.charAt(n++) != '-') {
            throw new SchemaDateTimeException("Invalid format for gMonthDay: " + s);
        }
        dateTimeData.day = this.parseInt(s, n, n + 2);
        if (7 < length) {
            if (!this.isNextCharUTCSign(s, 7, length)) {
                throw new SchemaDateTimeException("Error in month parsing:" + s);
            }
            this.getTimeZone(s, dateTimeData, 7, length);
        }
        this.validateDateTime(dateTimeData);
        this.saveUnnormalized(dateTimeData);
        if (dateTimeData.utc != 0 && dateTimeData.utc != 90) {
            this.normalize(dateTimeData);
        }
        dateTimeData.position = 1;
        return dateTimeData;
    }
    
    protected String dateToString(final DateTimeData dateTimeData) {
        final StringBuffer sb = new StringBuffer(8);
        sb.append('-');
        sb.append('-');
        this.append(sb, dateTimeData.month, 2);
        sb.append('-');
        this.append(sb, dateTimeData.day, 2);
        this.append(sb, (char)dateTimeData.utc, 0);
        return sb.toString();
    }
    
    protected XMLGregorianCalendar getXMLGregorianCalendar(final DateTimeData dateTimeData) {
        return super.factory.newXMLGregorianCalendar(Integer.MIN_VALUE, dateTimeData.unNormMonth, dateTimeData.unNormDay, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, dateTimeData.timezoneHr * 60 + dateTimeData.timezoneMin);
    }
}
