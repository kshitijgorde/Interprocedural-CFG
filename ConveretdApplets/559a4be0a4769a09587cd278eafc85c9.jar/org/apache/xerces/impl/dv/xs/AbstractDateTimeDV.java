// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

public abstract class AbstractDateTimeDV extends TypeValidator
{
    private static final boolean DEBUG = false;
    protected static final int CY = 0;
    protected static final int M = 1;
    protected static final int D = 2;
    protected static final int h = 3;
    protected static final int m = 4;
    protected static final int s = 5;
    protected static final int ms = 6;
    protected static final int utc = 7;
    protected static final int hh = 0;
    protected static final int mm = 1;
    protected static final int TOTAL_SIZE = 8;
    protected static final int YEAR = 2000;
    protected static final int MONTH = 1;
    protected static final int DAY = 15;
    
    public short getAllowedFacets() {
        return 2032;
    }
    
    public int compare(final Object value1, final Object value2) {
        return this.compareDates(((DateTimeData)value1).data, ((DateTimeData)value2).data, true);
    }
    
    protected short compareDates(final int[] date1, final int[] date2, final boolean strict) {
        if (date1[7] == date2[7]) {
            return this.compareOrder(date1, date2);
        }
        final int[] tempDate = new int[8];
        final int[] timeZone = new int[2];
        if (date1[7] == 90) {
            this.cloneDate(date2, tempDate);
            timeZone[0] = 14;
            timeZone[1] = 0;
            tempDate[7] = 43;
            this.normalize(tempDate, timeZone);
            final short c1 = this.compareOrder(date1, tempDate);
            this.cloneDate(date2, tempDate);
            timeZone[0] = 14;
            timeZone[1] = 0;
            tempDate[7] = 45;
            this.normalize(tempDate, timeZone);
            final short c2 = this.compareOrder(date1, tempDate);
            if ((c1 < 0 && c2 > 0) || (c1 == 0 && c2 == 0)) {
                return 2;
            }
            return (c1 != 2) ? c1 : c2;
        }
        else {
            if (date2[7] != 90) {
                return 2;
            }
            this.cloneDate(date1, tempDate);
            timeZone[0] = 14;
            timeZone[1] = 0;
            tempDate[7] = 45;
            this.normalize(tempDate, timeZone);
            final short c1 = this.compareOrder(tempDate, date2);
            this.cloneDate(date1, tempDate);
            timeZone[0] = 14;
            timeZone[1] = 0;
            tempDate[7] = 43;
            this.normalize(tempDate, timeZone);
            final short c2 = this.compareOrder(tempDate, date2);
            if ((c1 < 0 && c2 > 0) || (c1 == 0 && c2 == 0)) {
                return 2;
            }
            return (c1 != 2) ? c1 : c2;
        }
    }
    
    protected short compareOrder(final int[] date1, final int[] date2) {
        for (int i = 0; i < 8; ++i) {
            if (date1[i] < date2[i]) {
                return -1;
            }
            if (date1[i] > date2[i]) {
                return 1;
            }
        }
        return 0;
    }
    
    protected void getTime(final String buffer, int start, final int end, final int[] data, final int[] timeZone) throws RuntimeException {
        int stop = start + 2;
        data[3] = this.parseInt(buffer, start, stop);
        if (buffer.charAt(stop++) != ':') {
            throw new RuntimeException("Error in parsing time zone");
        }
        start = stop;
        stop += 2;
        data[4] = this.parseInt(buffer, start, stop);
        if (buffer.charAt(stop++) != ':') {
            throw new RuntimeException("Error in parsing time zone");
        }
        start = stop;
        stop += 2;
        data[5] = this.parseInt(buffer, start, stop);
        final int milisec = this.indexOf(buffer, start, end, '.');
        final int sign = this.findUTCSign(buffer, (milisec != -1) ? milisec : start, end);
        if (milisec != -1) {
            if (sign < 0) {
                data[6] = this.parseInt(buffer, milisec + 1, buffer.length());
            }
            else {
                data[6] = this.parseInt(buffer, milisec + 1, sign);
            }
        }
        if (sign > 0) {
            this.getTimeZone(buffer, data, sign, end, timeZone);
        }
    }
    
    protected int getDate(final String buffer, int start, final int end, final int[] date) throws RuntimeException {
        start = this.getYearMonth(buffer, start, end, date);
        if (buffer.charAt(start++) != '-') {
            throw new RuntimeException("CCYY-MM must be followed by '-' sign");
        }
        final int stop = start + 2;
        date[2] = this.parseInt(buffer, start, stop);
        return stop;
    }
    
    protected int getYearMonth(final String buffer, int start, final int end, final int[] date) throws RuntimeException {
        if (buffer.charAt(0) == '-') {
            ++start;
        }
        int i = this.indexOf(buffer, start, end, '-');
        if (i == -1) {
            throw new RuntimeException("Year separator is missing or misplaced");
        }
        final int length = i - start;
        if (length < 4) {
            throw new RuntimeException("Year must have 'CCYY' format");
        }
        if (length > 4 && buffer.charAt(start) == '0') {
            throw new RuntimeException("Leading zeros are required if the year value would otherwise have fewer than four digits; otherwise they are forbidden");
        }
        date[0] = this.parseIntYear(buffer, i);
        if (buffer.charAt(i) != '-') {
            throw new RuntimeException("CCYY must be followed by '-' sign");
        }
        start = ++i;
        i = start + 2;
        date[1] = this.parseInt(buffer, start, i);
        return i;
    }
    
    protected void parseTimeZone(final String buffer, final int start, final int end, final int[] date, final int[] timeZone) throws RuntimeException {
        if (start < end) {
            final int sign = this.findUTCSign(buffer, start, end);
            if (sign < 0) {
                throw new RuntimeException("Error in month parsing");
            }
            this.getTimeZone(buffer, date, sign, end, timeZone);
        }
    }
    
    protected void getTimeZone(final String buffer, final int[] data, int sign, final int end, final int[] timeZone) throws RuntimeException {
        data[7] = buffer.charAt(sign);
        if (buffer.charAt(sign) == 'Z') {
            if (end > ++sign) {
                throw new RuntimeException("Error in parsing time zone");
            }
        }
        else {
            if (sign > end - 6) {
                throw new RuntimeException("Error in parsing time zone");
            }
            int stop = ++sign + 2;
            timeZone[0] = this.parseInt(buffer, sign, stop);
            if (buffer.charAt(stop++) != ':') {
                throw new RuntimeException("Error in parsing time zone");
            }
            timeZone[1] = this.parseInt(buffer, stop, stop + 2);
            if (stop + 2 != end) {
                throw new RuntimeException("Error in parsing time zone");
            }
        }
    }
    
    protected int indexOf(final String buffer, final int start, final int end, final char ch) {
        for (int i = start; i < end; ++i) {
            if (buffer.charAt(i) == ch) {
                return i;
            }
        }
        return -1;
    }
    
    protected void validateDateTime(final int[] data, final int[] timeZone) {
        if (data[0] == 0) {
            throw new RuntimeException("The year \"0000\" is an illegal year value");
        }
        if (data[1] < 1 || data[1] > 12) {
            throw new RuntimeException("The month must have values 1 to 12");
        }
        if (data[2] > this.maxDayInMonthFor(data[0], data[1]) || data[2] < 1) {
            throw new RuntimeException("The day must have values 1 to 31");
        }
        if (data[3] > 23 || data[3] < 0) {
            throw new RuntimeException("Hour must have values 0-23");
        }
        if (data[4] > 59 || data[4] < 0) {
            throw new RuntimeException("Minute must have values 0-59");
        }
        if (data[5] > 60 || data[5] < 0) {
            throw new RuntimeException("Second must have values 0-60");
        }
        if (timeZone[0] > 14 || timeZone[0] < -14) {
            throw new RuntimeException("Time zone should have range -14..+14");
        }
        if (timeZone[1] > 59 || timeZone[1] < -59) {
            throw new RuntimeException("Minute must have values 0-59");
        }
    }
    
    protected int findUTCSign(final String buffer, final int start, final int end) {
        for (int i = start; i < end; ++i) {
            final int c = buffer.charAt(i);
            if (c == 90 || c == 43 || c == 45) {
                return i;
            }
        }
        return -1;
    }
    
    protected int parseInt(final String buffer, int start, final int end) throws NumberFormatException {
        final int radix = 10;
        int result = 0;
        int digit = 0;
        final int limit = -2147483647;
        final int multmin = limit / radix;
        do {
            digit = TypeValidator.getDigit(buffer.charAt(start));
            if (digit < 0) {
                throw new NumberFormatException("'" + buffer.toString() + "' has wrong format");
            }
            if (result < multmin) {
                throw new NumberFormatException("'" + buffer.toString() + "' has wrong format");
            }
            result *= radix;
            if (result < limit + digit) {
                throw new NumberFormatException("'" + buffer.toString() + "' has wrong format");
            }
            result -= digit;
        } while (++start < end);
        return -result;
    }
    
    protected int parseIntYear(final String buffer, final int end) {
        final int radix = 10;
        int result = 0;
        boolean negative = false;
        int i = 0;
        int digit = 0;
        int limit;
        if (buffer.charAt(0) == '-') {
            negative = true;
            limit = Integer.MIN_VALUE;
            ++i;
        }
        else {
            limit = -2147483647;
        }
        final int multmin = limit / radix;
        while (i < end) {
            digit = TypeValidator.getDigit(buffer.charAt(i++));
            if (digit < 0) {
                throw new NumberFormatException("'" + buffer.toString() + "' has wrong format");
            }
            if (result < multmin) {
                throw new NumberFormatException("'" + buffer.toString() + "' has wrong format");
            }
            result *= radix;
            if (result < limit + digit) {
                throw new NumberFormatException("'" + buffer.toString() + "' has wrong format");
            }
            result -= digit;
        }
        if (!negative) {
            return -result;
        }
        if (i > 1) {
            return result;
        }
        throw new NumberFormatException("'" + buffer.toString() + "' has wrong format");
    }
    
    protected void normalize(final int[] date, final int[] timeZone) {
        int negate = 1;
        if (date[7] == 43) {
            negate = -1;
        }
        int temp = date[4] + negate * timeZone[1];
        int carry = this.fQuotient(temp, 60);
        date[4] = this.mod(temp, 60, carry);
        temp = date[3] + negate * timeZone[0] + carry;
        carry = this.fQuotient(temp, 24);
        date[3] = this.mod(temp, 24, carry);
        date[2] += carry;
        while (true) {
            temp = this.maxDayInMonthFor(date[0], date[1]);
            if (date[2] < 1) {
                date[2] += this.maxDayInMonthFor(date[0], date[1] - 1);
                carry = -1;
            }
            else {
                if (date[2] <= temp) {
                    break;
                }
                date[2] -= temp;
                carry = 1;
            }
            temp = date[1] + carry;
            date[1] = this.modulo(temp, 1, 13);
            date[0] += this.fQuotient(temp, 1, 13);
        }
        date[7] = 90;
    }
    
    protected void resetDateObj(final int[] data) {
        for (int i = 0; i < 8; ++i) {
            data[i] = 0;
        }
    }
    
    protected int maxDayInMonthFor(final int year, final int month) {
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        if (month != 2) {
            return 31;
        }
        if (this.isLeapYear(year)) {
            return 29;
        }
        return 28;
    }
    
    private boolean isLeapYear(final int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }
    
    protected int mod(final int a, final int b, final int quotient) {
        return a - quotient * b;
    }
    
    protected int fQuotient(final int a, final int b) {
        return (int)Math.floor(a / b);
    }
    
    protected int modulo(final int temp, final int low, final int high) {
        final int a = temp - low;
        final int b = high - low;
        return this.mod(a, b, this.fQuotient(a, b)) + low;
    }
    
    protected int fQuotient(final int temp, final int low, final int high) {
        return this.fQuotient(temp - low, high - low);
    }
    
    protected String dateToString(final int[] date) {
        final StringBuffer message = new StringBuffer(25);
        message.append(date[0]);
        message.append('-');
        message.append(date[1]);
        message.append('-');
        message.append(date[2]);
        message.append('T');
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
    
    private void cloneDate(final int[] finalValue, final int[] tempDate) {
        System.arraycopy(finalValue, 0, tempDate, 0, 8);
    }
    
    static final class DateTimeData
    {
        final int[] data;
        final AbstractDateTimeDV type;
        private String canonical;
        
        public DateTimeData(final int[] data, final AbstractDateTimeDV type) {
            this.data = data;
            this.type = type;
        }
        
        public boolean equals(final Object obj) {
            if (!(obj instanceof DateTimeData)) {
                return false;
            }
            final int[] odata = ((DateTimeData)obj).data;
            return this.type.compareDates(this.data, odata, true) == 0;
        }
        
        public synchronized String toString() {
            if (this.canonical == null) {
                this.canonical = this.type.dateToString(this.data);
            }
            return this.canonical;
        }
    }
}
