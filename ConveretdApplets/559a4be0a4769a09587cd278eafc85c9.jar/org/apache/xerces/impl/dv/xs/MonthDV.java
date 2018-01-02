// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class MonthDV extends AbstractDateTimeDV
{
    public Object getActualValue(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return new DateTimeData(this.parse(content), this);
        }
        catch (Exception ex) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "gMonth" });
        }
    }
    
    protected int[] parse(final String str) throws SchemaDateTimeException {
        final int len = str.length();
        final int[] date = new int[8];
        final int[] timeZone = new int[2];
        date[0] = 2000;
        date[2] = 15;
        if (str.charAt(0) != '-' || str.charAt(1) != '-') {
            throw new SchemaDateTimeException("Invalid format for gMonth: " + str);
        }
        int stop = 4;
        date[1] = this.parseInt(str, 2, stop);
        if (str.length() >= stop + 2 && str.charAt(stop) == '-' && str.charAt(stop + 1) == '-') {
            stop += 2;
        }
        if (stop < len) {
            final int sign = this.findUTCSign(str, stop, len);
            if (sign < 0) {
                throw new SchemaDateTimeException("Error in month parsing: " + str);
            }
            this.getTimeZone(str, date, sign, len, timeZone);
        }
        this.validateDateTime(date, timeZone);
        if (date[7] != 0 && date[7] != 90) {
            this.normalize(date, timeZone);
        }
        return date;
    }
    
    protected short compareDates(final int[] date1, final int[] date2) {
        if (date1[7] == date2[7]) {
            return (short)((date1[1] >= date2[1]) ? ((date1[1] > date2[1]) ? 1 : 0) : -1);
        }
        if (date1[7] == 90 || date2[7] == 90) {
            if (date1[1] == date2[1]) {
                return 2;
            }
            if (date1[1] + 1 == date2[1] || date1[1] - 1 == date2[1]) {
                return 2;
            }
        }
        if (date1[1] < date2[1]) {
            return -1;
        }
        return 1;
    }
    
    protected String dateToString(final int[] date) {
        final StringBuffer message = new StringBuffer(5);
        message.append('-');
        message.append('-');
        message.append(date[1]);
        message.append((char)date[7]);
        return message.toString();
    }
}
