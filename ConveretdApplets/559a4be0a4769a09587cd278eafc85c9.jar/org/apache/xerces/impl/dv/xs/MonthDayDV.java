// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class MonthDayDV extends AbstractDateTimeDV
{
    private static final int MONTHDAY_SIZE = 7;
    
    public Object getActualValue(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return new DateTimeData(this.parse(content), this);
        }
        catch (Exception ex) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "gMonthDay" });
        }
    }
    
    protected int[] parse(final String str) throws SchemaDateTimeException {
        final int len = str.length();
        final int[] date = new int[8];
        final int[] timeZone = new int[2];
        date[0] = 2000;
        if (str.charAt(0) != '-' || str.charAt(1) != '-') {
            throw new SchemaDateTimeException("Invalid format for gMonthDay: " + str);
        }
        date[1] = this.parseInt(str, 2, 4);
        int start = 4;
        if (str.charAt(start++) != '-') {
            throw new SchemaDateTimeException("Invalid format for gMonthDay: " + str);
        }
        date[2] = this.parseInt(str, start, start + 2);
        if (7 < len) {
            final int sign = this.findUTCSign(str, 7, len);
            if (sign < 0) {
                throw new SchemaDateTimeException("Error in month parsing:" + str);
            }
            this.getTimeZone(str, date, sign, len, timeZone);
        }
        this.validateDateTime(date, timeZone);
        if (date[7] != 0 && date[7] != 90) {
            this.normalize(date, timeZone);
        }
        return date;
    }
    
    protected String dateToString(final int[] date) {
        final StringBuffer message = new StringBuffer(8);
        message.append('-');
        message.append('-');
        message.append(date[1]);
        message.append('-');
        message.append(date[2]);
        message.append((char)date[7]);
        return message.toString();
    }
}
