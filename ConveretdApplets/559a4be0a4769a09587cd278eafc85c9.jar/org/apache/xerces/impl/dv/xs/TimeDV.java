// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class TimeDV extends AbstractDateTimeDV
{
    public Object getActualValue(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return new DateTimeData(this.parse(content), this);
        }
        catch (Exception ex) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "time" });
        }
    }
    
    protected int[] parse(final String str) throws SchemaDateTimeException {
        final int len = str.length();
        final int[] date = new int[8];
        final int[] timeZone = new int[2];
        date[0] = 2000;
        date[1] = 1;
        date[2] = 15;
        this.getTime(str, 0, len, date, timeZone);
        this.validateDateTime(date, timeZone);
        if (date[7] != 0) {
            this.normalize(date, timeZone);
        }
        return date;
    }
    
    protected String dateToString(final int[] date) {
        final StringBuffer message = new StringBuffer(16);
        message.append(date[3]);
        message.append(':');
        message.append(date[4]);
        message.append(':');
        message.append(date[5]);
        message.append('.');
        message.append(date[6]);
        message.append((char)date[7]);
        return message.toString();
    }
}
