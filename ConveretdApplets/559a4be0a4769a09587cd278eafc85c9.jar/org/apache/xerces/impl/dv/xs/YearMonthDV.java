// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class YearMonthDV extends AbstractDateTimeDV
{
    public Object getActualValue(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return new DateTimeData(this.parse(content), this);
        }
        catch (Exception ex) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "gYearMonth" });
        }
    }
    
    protected int[] parse(final String str) throws SchemaDateTimeException {
        final int len = str.length();
        final int[] date = new int[8];
        final int[] timeZone = new int[2];
        final int end = this.getYearMonth(str, 0, len, date);
        date[2] = 15;
        this.parseTimeZone(str, end, len, date, timeZone);
        this.validateDateTime(date, timeZone);
        if (date[7] != 0 && date[7] != 90) {
            this.normalize(date, timeZone);
        }
        return date;
    }
}
