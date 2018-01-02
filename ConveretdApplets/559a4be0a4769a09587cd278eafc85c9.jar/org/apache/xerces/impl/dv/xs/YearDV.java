// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class YearDV extends AbstractDateTimeDV
{
    public Object getActualValue(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return new DateTimeData(this.parse(content), this);
        }
        catch (Exception ex) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "gYear" });
        }
    }
    
    protected int[] parse(final String str) throws SchemaDateTimeException {
        final int len = str.length();
        final int[] date = new int[8];
        final int[] timeZone = new int[2];
        int start = 0;
        if (str.charAt(0) == '-') {
            start = 1;
        }
        final int sign = this.findUTCSign(str, start, len);
        if (sign == -1) {
            date[0] = this.parseIntYear(str, len);
        }
        else {
            date[0] = this.parseIntYear(str, sign);
            this.getTimeZone(str, date, sign, len, timeZone);
        }
        date[2] = (date[1] = 1);
        this.validateDateTime(date, timeZone);
        if (date[7] != 0 && date[7] != 90) {
            this.normalize(date, timeZone);
        }
        return date;
    }
    
    protected String dateToString(final int[] date) {
        final StringBuffer message = new StringBuffer(5);
        message.append(date[0]);
        message.append((char)date[7]);
        return message.toString();
    }
}
