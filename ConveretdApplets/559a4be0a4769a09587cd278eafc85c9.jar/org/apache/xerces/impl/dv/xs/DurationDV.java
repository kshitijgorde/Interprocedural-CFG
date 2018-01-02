// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class DurationDV extends AbstractDateTimeDV
{
    private static final int[][] DATETIMES;
    private int[][] fDuration;
    
    public DurationDV() {
        this.fDuration = null;
    }
    
    public Object getActualValue(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return new DateTimeData(this.parse(content), this);
        }
        catch (Exception ex) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "duration" });
        }
    }
    
    protected int[] parse(final String str) throws SchemaDateTimeException {
        final int len = str.length();
        final int[] date = new int[8];
        int start = 0;
        final char c = str.charAt(start++);
        if (c != 'P' && c != '-') {
            throw new SchemaDateTimeException();
        }
        date[7] = ((c == '-') ? 45 : 0);
        if (c == '-' && str.charAt(start++) != 'P') {
            throw new SchemaDateTimeException();
        }
        int negate = 1;
        if (date[7] == 45) {
            negate = -1;
        }
        boolean designator = false;
        int endDate = this.indexOf(str, start, len, 'T');
        if (endDate == -1) {
            endDate = len;
        }
        int end = this.indexOf(str, start, endDate, 'Y');
        if (end != -1) {
            date[0] = negate * this.parseInt(str, start, end);
            start = end + 1;
            designator = true;
        }
        end = this.indexOf(str, start, endDate, 'M');
        if (end != -1) {
            date[1] = negate * this.parseInt(str, start, end);
            start = end + 1;
            designator = true;
        }
        end = this.indexOf(str, start, endDate, 'D');
        if (end != -1) {
            date[2] = negate * this.parseInt(str, start, end);
            start = end + 1;
            designator = true;
        }
        if (len == endDate && start != len) {
            throw new SchemaDateTimeException();
        }
        if (len != endDate) {
            end = this.indexOf(str, ++start, len, 'H');
            if (end != -1) {
                date[3] = negate * this.parseInt(str, start, end);
                start = end + 1;
                designator = true;
            }
            end = this.indexOf(str, start, len, 'M');
            if (end != -1) {
                date[4] = negate * this.parseInt(str, start, end);
                start = end + 1;
                designator = true;
            }
            end = this.indexOf(str, start, len, 'S');
            if (end != -1) {
                final int mlsec = this.indexOf(str, start, end, '.');
                if (mlsec > 0) {
                    date[5] = negate * this.parseInt(str, start, mlsec);
                    date[6] = negate * this.parseInt(str, mlsec + 1, end);
                }
                else {
                    date[5] = negate * this.parseInt(str, start, end);
                }
                start = end + 1;
                designator = true;
            }
            if (start != len || str.charAt(--start) == 'T') {
                throw new SchemaDateTimeException();
            }
        }
        if (!designator) {
            throw new SchemaDateTimeException();
        }
        return date;
    }
    
    protected short compareDates(final int[] date1, final int[] date2, final boolean strict) {
        short resultB = 2;
        short resultA = this.compareOrder(date1, date2);
        if (resultA == 0) {
            return 0;
        }
        if (this.fDuration == null) {
            this.fDuration = new int[2][8];
        }
        int[] tempA = this.addDuration(date1, 0, this.fDuration[0]);
        int[] tempB = this.addDuration(date2, 0, this.fDuration[1]);
        resultA = this.compareOrder(tempA, tempB);
        if (resultA == 2) {
            return 2;
        }
        tempA = this.addDuration(date1, 1, this.fDuration[0]);
        tempB = this.addDuration(date2, 1, this.fDuration[1]);
        resultB = this.compareOrder(tempA, tempB);
        resultA = this.compareResults(resultA, resultB, strict);
        if (resultA == 2) {
            return 2;
        }
        tempA = this.addDuration(date1, 2, this.fDuration[0]);
        tempB = this.addDuration(date2, 2, this.fDuration[1]);
        resultB = this.compareOrder(tempA, tempB);
        resultA = this.compareResults(resultA, resultB, strict);
        if (resultA == 2) {
            return 2;
        }
        tempA = this.addDuration(date1, 3, this.fDuration[0]);
        tempB = this.addDuration(date2, 3, this.fDuration[1]);
        resultB = this.compareOrder(tempA, tempB);
        resultA = this.compareResults(resultA, resultB, strict);
        return resultA;
    }
    
    private short compareResults(final short resultA, final short resultB, final boolean strict) {
        if (resultB == 2) {
            return 2;
        }
        if (resultA != resultB && strict) {
            return 2;
        }
        if (resultA == resultB || strict) {
            return resultA;
        }
        if (resultA != 0 && resultB != 0) {
            return 2;
        }
        return (resultA != 0) ? resultA : resultB;
    }
    
    private int[] addDuration(final int[] date, final int index, final int[] duration) {
        this.resetDateObj(duration);
        int temp = DurationDV.DATETIMES[index][1] + date[1];
        duration[1] = this.modulo(temp, 1, 13);
        int carry = this.fQuotient(temp, 1, 13);
        duration[0] = DurationDV.DATETIMES[index][0] + date[0] + carry;
        temp = DurationDV.DATETIMES[index][5] + date[5];
        carry = this.fQuotient(temp, 60);
        duration[5] = this.mod(temp, 60, carry);
        temp = DurationDV.DATETIMES[index][4] + date[4] + carry;
        carry = this.fQuotient(temp, 60);
        duration[4] = this.mod(temp, 60, carry);
        temp = DurationDV.DATETIMES[index][3] + date[3] + carry;
        carry = this.fQuotient(temp, 24);
        duration[3] = this.mod(temp, 24, carry);
        duration[2] = DurationDV.DATETIMES[index][2] + date[2] + carry;
        while (true) {
            temp = this.maxDayInMonthFor(duration[0], duration[1]);
            if (duration[2] < 1) {
                duration[2] += this.maxDayInMonthFor(duration[0], duration[1] - 1);
                carry = -1;
            }
            else {
                if (duration[2] <= temp) {
                    break;
                }
                duration[2] -= temp;
                carry = 1;
            }
            temp = duration[1] + carry;
            duration[1] = this.modulo(temp, 1, 13);
            duration[0] += this.fQuotient(temp, 1, 13);
        }
        duration[7] = 90;
        return duration;
    }
    
    protected String dateToString(final int[] date) {
        final StringBuffer message = new StringBuffer(30);
        int negate = 1;
        if (date[0] < 0) {
            message.append('-');
            negate = -1;
        }
        message.append('P');
        message.append(negate * date[0]);
        message.append('Y');
        message.append(negate * date[1]);
        message.append('M');
        message.append(negate * date[2]);
        message.append('D');
        message.append('T');
        message.append(negate * date[3]);
        message.append('H');
        message.append(negate * date[4]);
        message.append('M');
        message.append(negate * date[5]);
        message.append('.');
        message.append(negate * date[6]);
        message.append('S');
        return message.toString();
    }
    
    static {
        DATETIMES = new int[][] { { 1696, 9, 1, 0, 0, 0, 0, 90 }, { 1697, 2, 1, 0, 0, 0, 0, 90 }, { 1903, 3, 1, 0, 0, 0, 0, 90 }, { 1903, 7, 1, 0, 0, 0, 0, 90 } };
    }
}
