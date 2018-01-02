// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.datatype;

import java.util.Date;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.Duration;
import javax.xml.namespace.QName;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.apache.xerces.util.DatatypeMessageFormatter;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.datatype.XMLGregorianCalendar;

class XMLGregorianCalendarImpl extends XMLGregorianCalendar
{
    private static final BigInteger MAX_INT;
    private static final BigDecimal MILLI_MULT;
    private BigInteger eon;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private BigDecimal fracSeconds;
    private int timezone;
    private BigInteger orig_eon;
    private int orig_year;
    private int orig_month;
    private int orig_day;
    private int orig_hour;
    private int orig_minute;
    private int orig_second;
    private BigDecimal orig_fracSeconds;
    private int orig_timezone;
    private static final int YEAR = 2000;
    private static final int MONTH = 1;
    private static final int DAY = 1;
    private static final BigDecimal ZERO;
    private static final BigInteger I_ZERO;
    private static final BigDecimal ONE;
    private static final BigInteger I_ONE;
    private static final BigInteger THIRTEEN;
    
    public XMLGregorianCalendarImpl() {
        this.year = Integer.MIN_VALUE;
        this.month = Integer.MIN_VALUE;
        this.day = Integer.MIN_VALUE;
        this.hour = Integer.MIN_VALUE;
        this.minute = Integer.MIN_VALUE;
        this.second = Integer.MIN_VALUE;
        this.timezone = Integer.MIN_VALUE;
        this.orig_year = Integer.MIN_VALUE;
        this.orig_month = Integer.MIN_VALUE;
        this.orig_day = Integer.MIN_VALUE;
        this.orig_hour = Integer.MIN_VALUE;
        this.orig_minute = Integer.MIN_VALUE;
        this.orig_second = Integer.MIN_VALUE;
        this.orig_timezone = Integer.MIN_VALUE;
    }
    
    public XMLGregorianCalendarImpl(final String s) {
        this.year = Integer.MIN_VALUE;
        this.month = Integer.MIN_VALUE;
        this.day = Integer.MIN_VALUE;
        this.hour = Integer.MIN_VALUE;
        this.minute = Integer.MIN_VALUE;
        this.second = Integer.MIN_VALUE;
        this.timezone = Integer.MIN_VALUE;
        this.orig_year = Integer.MIN_VALUE;
        this.orig_month = Integer.MIN_VALUE;
        this.orig_day = Integer.MIN_VALUE;
        this.orig_hour = Integer.MIN_VALUE;
        this.orig_minute = Integer.MIN_VALUE;
        this.orig_second = Integer.MIN_VALUE;
        this.orig_timezone = Integer.MIN_VALUE;
        if (s == null) {
            throw new NullPointerException(DatatypeMessageFormatter.formatMessage(null, "CannotBeNull", new Object[] { "String" }));
        }
        final int length = s.length();
        if (length <= 3) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidLexicalValue", new Object[] { s }));
        }
        if (s.charAt(0) == '-' && s.charAt(1) == '-') {
            if (s.charAt(2) == '-') {
                if (length < 5) {
                    throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gDay" }));
                }
                this.setDay(DateTimeUtil.parseInt(s, 3, 5));
                if (length > 5) {
                    this.parseTimeZone(s, 5, length);
                }
                this.save();
                if (!this.isValid()) {
                    throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gDay" }));
                }
            }
            else {
                this.setMonth(DateTimeUtil.parseInt(s, 2, 4));
                if (length == 4) {
                    this.save();
                    if (!this.isValid()) {
                        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gMonth" }));
                    }
                }
                else if (s.charAt(4) == '+' || s.charAt(4) == 'Z') {
                    this.parseTimeZone(s, 4, length);
                    this.save();
                    if (!this.isValid()) {
                        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gMonth" }));
                    }
                }
                else {
                    if (s.charAt(4) != '-' || length < 6) {
                        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gMonth/gMonthDay" }));
                    }
                    int n = 5;
                    if (s.charAt(n) == '-') {
                        ++n;
                        if (length == 6) {
                            this.save();
                            if (!this.isValid()) {
                                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gMonth" }));
                            }
                            return;
                        }
                        else if (s.charAt(n) == '-' || s.charAt(n) == '+' || s.charAt(n) == 'Z') {
                            this.parseTimeZone(s, n, length);
                            if (!this.isValid()) {
                                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gMonth" }));
                            }
                            return;
                        }
                    }
                    if (n + 2 > length) {
                        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gMonth/gMonthDay" }));
                    }
                    final int int1 = DateTimeUtil.parseInt(s, n, n + 2);
                    int n2 = n - 1;
                    if (n + 2 == length || s.charAt(n + 2) != ':') {
                        this.setDay(int1);
                        if (n + 2 == length) {
                            this.save();
                            if (!this.isValid()) {
                                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gMonthDay" }));
                            }
                            return;
                        }
                        else {
                            n2 = n + 2;
                        }
                    }
                    this.parseTimeZone(s, n2, length);
                    this.save();
                    if (!this.isValid()) {
                        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gMonth" }));
                    }
                }
            }
        }
        else if (s.charAt(2) == ':') {
            this.getTime(s, 0, length);
            this.save();
            if (!this.isValid()) {
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "time" }));
            }
        }
        else {
            int index = DateTimeUtil.indexOf(s, 1, length, '-');
            if (index != -1) {
                this.setYearAfterCheck(s, index);
                if (index + 2 >= length) {
                    throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gYear" }));
                }
                ++index;
                final int int2 = DateTimeUtil.parseInt(s, index, index + 2);
                if (index + 2 == length) {
                    this.setMonth(int2);
                    this.save();
                    if (!this.isValid()) {
                        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gYearMonth" }));
                    }
                }
                else if (s.charAt(index + 2) == ':') {
                    this.parseTimeZone(s, index - 1, length);
                    this.save();
                    if (!this.isValid()) {
                        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gYearMonth" }));
                    }
                }
                else {
                    if (s.charAt(index + 2) != '-' && s.charAt(index + 2) != '+' && s.charAt(index + 2) != 'Z') {
                        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gYear/gYearMonth/date/datetime" }));
                    }
                    this.setMonth(int2);
                    index += 2;
                    if (index >= length) {
                        return;
                    }
                    if (s.charAt(index) == '-' && index + 2 < length) {
                        ++index;
                        final int int3 = DateTimeUtil.parseInt(s, index, index + 2);
                        if (index + 2 < length) {
                            if (s.charAt(index + 2) == ':') {
                                this.parseTimeZone(s, index - 1, length);
                                this.save();
                                if (!this.isValid()) {
                                    throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidLexicalValue", new Object[] { s }));
                                }
                            }
                            else if (s.charAt(index + 2) == 'T') {
                                this.setDay(int3);
                                this.getTime(s, index + 3, length);
                                this.save();
                                if (!this.isValid()) {
                                    throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "datetime" }));
                                }
                            }
                            else {
                                if (s.charAt(index + 2) != '-' && s.charAt(index + 2) != '+' && s.charAt(index + 2) != 'Z') {
                                    throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidLexicalValue", new Object[] { s }));
                                }
                                this.setDay(int3);
                                this.parseTimeZone(s, index + 2, length);
                                this.save();
                                if (!this.isValid()) {
                                    throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "datetime" }));
                                }
                            }
                        }
                        else {
                            this.setDay(int3);
                            this.save();
                            if (!this.isValid()) {
                                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "date" }));
                            }
                        }
                    }
                    else {
                        if ((s.charAt(index) != '+' || index + 6 != length) && (s.charAt(index) != 'Z' || index + 1 != length)) {
                            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidLexicalValue", new Object[] { s }));
                        }
                        this.parseTimeZone(s, index, length);
                        this.save();
                        if (!this.isValid()) {
                            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidLexicalValue", new Object[] { s }));
                        }
                    }
                }
            }
            else {
                int n3 = DateTimeUtil.indexOf(s, 1, length, '+');
                if (n3 == -1) {
                    n3 = DateTimeUtil.indexOf(s, 1, length, 'Z');
                }
                this.setYearAfterCheck(s, (n3 != -1) ? n3 : length);
                if (n3 != -1) {
                    this.parseTimeZone(s, n3, length);
                }
                this.save();
                if (!this.isValid()) {
                    throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDateTimeValue", new Object[] { s, "gYear" }));
                }
            }
        }
    }
    
    private void setYearAfterCheck(final String s, final int n) {
        int n2 = 0;
        if (s.charAt(0) == '-') {
            ++n2;
        }
        if (s.charAt(n2) == '0' && n - n2 > 4) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "NoLeadingZeros", new Object[] { s }));
        }
        if (n - n2 < 4) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidYearSyntax", new Object[] { s }));
        }
        try {
            this.setYear(new BigInteger(s.substring(0, n)));
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public XMLGregorianCalendarImpl(final GregorianCalendar gregorianCalendar) {
        this.year = Integer.MIN_VALUE;
        this.month = Integer.MIN_VALUE;
        this.day = Integer.MIN_VALUE;
        this.hour = Integer.MIN_VALUE;
        this.minute = Integer.MIN_VALUE;
        this.second = Integer.MIN_VALUE;
        this.timezone = Integer.MIN_VALUE;
        this.orig_year = Integer.MIN_VALUE;
        this.orig_month = Integer.MIN_VALUE;
        this.orig_day = Integer.MIN_VALUE;
        this.orig_hour = Integer.MIN_VALUE;
        this.orig_minute = Integer.MIN_VALUE;
        this.orig_second = Integer.MIN_VALUE;
        this.orig_timezone = Integer.MIN_VALUE;
        this.setYear((gregorianCalendar.get(0) == 0) ? (-gregorianCalendar.get(1)) : gregorianCalendar.get(1));
        this.setMonth(gregorianCalendar.get(2) + 1);
        this.setDay(gregorianCalendar.get(5));
        this.setTime((gregorianCalendar.get(9) == 0) ? gregorianCalendar.get(10) : (12 + gregorianCalendar.get(10)), gregorianCalendar.get(12), gregorianCalendar.get(13), gregorianCalendar.get(14));
        this.setTimezone((gregorianCalendar.get(15) + gregorianCalendar.get(16)) / 60000);
        this.save();
    }
    
    public XMLGregorianCalendarImpl(final BigInteger year, final int month, final int day, final int hour, final int minute, final int second, final BigDecimal fractionalSecond, final int timezone) {
        this.year = Integer.MIN_VALUE;
        this.month = Integer.MIN_VALUE;
        this.day = Integer.MIN_VALUE;
        this.hour = Integer.MIN_VALUE;
        this.minute = Integer.MIN_VALUE;
        this.second = Integer.MIN_VALUE;
        this.timezone = Integer.MIN_VALUE;
        this.orig_year = Integer.MIN_VALUE;
        this.orig_month = Integer.MIN_VALUE;
        this.orig_day = Integer.MIN_VALUE;
        this.orig_hour = Integer.MIN_VALUE;
        this.orig_minute = Integer.MIN_VALUE;
        this.orig_second = Integer.MIN_VALUE;
        this.orig_timezone = Integer.MIN_VALUE;
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
        this.setHour(hour);
        this.setMinute(minute);
        this.setSecond(second);
        this.setFractionalSecond(fractionalSecond);
        this.setTimezone(timezone);
        this.save();
    }
    
    public XMLGregorianCalendarImpl(final int year, final int month, final int day, final int hour, final int minute, final int second, final BigDecimal fractionalSecond, final int n) {
        this.year = Integer.MIN_VALUE;
        this.month = Integer.MIN_VALUE;
        this.day = Integer.MIN_VALUE;
        this.hour = Integer.MIN_VALUE;
        this.minute = Integer.MIN_VALUE;
        this.second = Integer.MIN_VALUE;
        this.timezone = Integer.MIN_VALUE;
        this.orig_year = Integer.MIN_VALUE;
        this.orig_month = Integer.MIN_VALUE;
        this.orig_day = Integer.MIN_VALUE;
        this.orig_hour = Integer.MIN_VALUE;
        this.orig_minute = Integer.MIN_VALUE;
        this.orig_second = Integer.MIN_VALUE;
        this.orig_timezone = Integer.MIN_VALUE;
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
        this.setHour(hour);
        this.setMinute(minute);
        this.setSecond(second);
        this.setFractionalSecond(fractionalSecond);
        this.setTimezone(this.timezone);
        this.save();
    }
    
    private void save() {
        this.orig_eon = this.eon;
        this.orig_year = this.year;
        this.orig_month = this.month;
        this.orig_day = this.day;
        this.orig_hour = this.hour;
        this.orig_minute = this.minute;
        this.orig_second = this.second;
        this.orig_fracSeconds = this.fracSeconds;
        this.orig_timezone = this.timezone;
    }
    
    private void getTime(final String s, int n, final int n2) throws RuntimeException {
        int n3 = n + 2;
        this.setHour(DateTimeUtil.parseInt(s, n, n3));
        if (n3 >= n2) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidTimeValue", new Object[] { s }));
        }
        if (s.charAt(n3++) != ':') {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidTimeZoneValue", new Object[] { s }));
        }
        n = n3;
        int n4 = n3 + 2;
        this.setMinute(DateTimeUtil.parseInt(s, n, n4));
        if (n4 >= n2) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidTimeValue", new Object[] { s }));
        }
        if (s.charAt(n4++) != ':') {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidTimeZoneValue", new Object[] { s }));
        }
        final int utcSign = this.findUTCSign(s, n, n2);
        n = n4;
        final int n5 = (utcSign < 0) ? n2 : utcSign;
        final int index = DateTimeUtil.indexOf(s, n, n5, '.');
        this.setSecond(DateTimeUtil.parseInt(s, n, (index != -1) ? index : n5));
        if (index != -1) {
            try {
                this.setFractionalSecond(new BigDecimal(s.substring(index, n5)));
            }
            catch (NumberFormatException ex) {
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidTimeValue", new Object[] { s }));
            }
        }
        if (utcSign > 0) {
            this.getTimeZone(s, utcSign, n2);
        }
    }
    
    private void parseTimeZone(final String s, final int n, final int n2) throws RuntimeException {
        if (n < n2) {
            final int utcSign = this.findUTCSign(s, n, n2);
            if (utcSign < 0) {
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidTimeZoneValue", new Object[] { s }));
            }
            this.getTimeZone(s, utcSign, n2);
        }
    }
    
    private int findUTCSign(final String s, final int n, final int n2) {
        for (int i = n; i < n2; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == 'Z' || char1 == '+' || char1 == '-') {
                return i;
            }
        }
        return -1;
    }
    
    private void getTimeZone(final String s, int n, final int n2) throws RuntimeException {
        if (s.charAt(n) == 'Z') {
            if (n2 > ++n) {
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidTimeZoneValue", new Object[] { s }));
            }
            this.setTimezone(0);
        }
        else {
            if (n > n2 - 6) {
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidTimeZoneValue", new Object[] { s }));
            }
            final int n3 = (s.charAt(n) == '-') ? -1 : 1;
            int n4 = ++n + 2;
            final int n5 = n3 * DateTimeUtil.parseInt(s, n, n4);
            if (s.charAt(n4++) != ':') {
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidTimeZoneValue", new Object[] { s }));
            }
            final int n6 = n3 * DateTimeUtil.parseInt(s, n4, n4 + 2);
            if (n4 + 2 != n2) {
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidTimeZoneValue", new Object[] { s }));
            }
            this.setTimezone(n5 * 60 + n6);
        }
    }
    
    public void clear() {
        this.eon = null;
        this.year = Integer.MIN_VALUE;
        this.month = Integer.MIN_VALUE;
        this.day = Integer.MIN_VALUE;
        this.hour = Integer.MIN_VALUE;
        this.minute = Integer.MIN_VALUE;
        this.second = Integer.MIN_VALUE;
        this.fracSeconds = null;
        this.timezone = Integer.MIN_VALUE;
    }
    
    public void reset() {
        this.eon = this.orig_eon;
        this.year = this.orig_year;
        this.month = this.orig_month;
        this.day = this.orig_day;
        this.hour = this.orig_hour;
        this.minute = this.orig_minute;
        this.second = this.orig_second;
        this.fracSeconds = this.orig_fracSeconds;
        this.timezone = this.orig_timezone;
    }
    
    public void setYear(final BigInteger bigInteger) {
        if (bigInteger == null) {
            this.eon = null;
            this.year = Integer.MIN_VALUE;
            return;
        }
        if (bigInteger.compareTo(XMLGregorianCalendarImpl.MAX_INT) >= 0 || bigInteger.compareTo(XMLGregorianCalendarImpl.MAX_INT.negate()) <= 0) {
            final BigInteger remainder = bigInteger.remainder(XMLGregorianCalendarImpl.MAX_INT);
            this.eon = bigInteger.subtract(remainder);
            this.year = remainder.intValue();
        }
        else {
            this.eon = null;
            this.year = bigInteger.intValue();
        }
    }
    
    public void setYear(final int n) {
        if (n == Integer.MIN_VALUE) {
            this.year = n;
            this.eon = null;
            return;
        }
        if (n < XMLGregorianCalendarImpl.MAX_INT.intValue() && n > XMLGregorianCalendarImpl.MAX_INT.negate().intValue()) {
            this.eon = null;
            this.year = n;
        }
        else {
            this.setYear(BigInteger.valueOf(n));
        }
    }
    
    private int maxDaysInMonthFor(final BigInteger bigInteger, final int n) {
        if (n == 4 || n == 6 || n == 9 || n == 11) {
            return 30;
        }
        if (n != 2) {
            return 31;
        }
        if (isLeapYear(bigInteger)) {
            return 29;
        }
        return 28;
    }
    
    private static final boolean isLeapYear(final BigInteger bigInteger) {
        return bigInteger.mod(BigInteger.valueOf(4L)).equals(BigInteger.ZERO) && (!bigInteger.mod(BigInteger.valueOf(100L)).equals(BigInteger.ZERO) || bigInteger.mod(BigInteger.valueOf(400L)).equals(BigInteger.ZERO));
    }
    
    public void setMonth(final int n) {
        if (n == Integer.MIN_VALUE) {
            this.month = n;
            return;
        }
        if (n <= 0 || n > 12) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidValueSet", new Object[] { "month", "1", "12", String.valueOf(n) }));
        }
        this.month = n;
    }
    
    public void setDay(final int n) {
        if (n == Integer.MIN_VALUE) {
            this.day = n;
            return;
        }
        if (n < 1 || n > 31) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidValueSet", new Object[] { "day", "1", "31", String.valueOf(n) }));
        }
        this.day = n;
    }
    
    public void setTimezone(final int timezone) {
        if ((timezone < -840 && timezone != Integer.MIN_VALUE) || timezone > 840) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidValueSet", new Object[] { "offset", "-840", "840", String.valueOf(timezone) }));
        }
        this.timezone = timezone;
    }
    
    public void setHour(final int n) {
        if (n == Integer.MIN_VALUE) {
            this.hour = n;
            return;
        }
        if (n < 0 || n >= 24) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidValueSet", new Object[] { "hour", "0", "24", String.valueOf(n) }));
        }
        this.hour = n;
    }
    
    public void setMinute(final int n) {
        if (n == Integer.MIN_VALUE) {
            this.minute = n;
            return;
        }
        if (n < 0 || n > 59) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidValueSet", new Object[] { "minute", "0", "59", String.valueOf(n) }));
        }
        this.minute = n;
    }
    
    public void setSecond(final int n) {
        if (n == Integer.MIN_VALUE) {
            this.second = n;
            return;
        }
        if (n < 0 || n > 60) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidValueSet", new Object[] { "second", "0", "60", String.valueOf(n) }));
        }
        this.second = n;
    }
    
    public void setMillisecond(final int n) {
        if (n == Integer.MIN_VALUE) {
            this.fracSeconds = null;
            return;
        }
        if (n < 0 || n > 999) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidValueSet", new Object[] { "millisecond", "0", "1000", String.valueOf(n) }));
        }
        this.fracSeconds = new BigDecimal(String.valueOf(n / 1000.0));
    }
    
    public void setFractionalSecond(final BigDecimal fracSeconds) {
        if (fracSeconds == null) {
            this.fracSeconds = null;
            return;
        }
        if (fracSeconds.compareTo(XMLGregorianCalendarImpl.ZERO) < 0 || fracSeconds.compareTo(XMLGregorianCalendarImpl.ONE) > 0) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidValueSet", new Object[] { "fractional", "0.0", "1.0", fracSeconds }));
        }
        this.fracSeconds = fracSeconds;
    }
    
    public BigInteger getEon() {
        return this.eon;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public BigInteger getEonAndYear() {
        if (this.eon != null || this.year != Integer.MIN_VALUE) {
            return ((this.eon != null) ? this.eon : BigInteger.ZERO).add((this.year != Integer.MIN_VALUE) ? BigInteger.valueOf(this.year) : BigInteger.ZERO);
        }
        return null;
    }
    
    public int getMonth() {
        return this.month;
    }
    
    public int getDay() {
        return this.day;
    }
    
    public int getTimezone() {
        return this.timezone;
    }
    
    public int getHour() {
        return this.hour;
    }
    
    public int getMinute() {
        return this.minute;
    }
    
    public int getSecond() {
        return this.second;
    }
    
    public BigDecimal getFractionalSecond() {
        return this.fracSeconds;
    }
    
    public int compare(final XMLGregorianCalendar xmlGregorianCalendar) {
        QName xmlSchemaType = null;
        QName xmlSchemaType2 = null;
        try {
            xmlSchemaType = xmlGregorianCalendar.getXMLSchemaType();
            xmlSchemaType2 = this.getXMLSchemaType();
        }
        catch (IllegalStateException ex) {}
        if (xmlSchemaType != xmlSchemaType2 && (xmlSchemaType == null || xmlSchemaType2 == null || !xmlSchemaType.equals(xmlSchemaType2))) {
            return 2;
        }
        final XMLGregorianCalendar normalize = this.normalize();
        final XMLGregorianCalendar normalize2 = xmlGregorianCalendar.normalize();
        if (normalize.getTimezone() == normalize2.getTimezone()) {
            return this.compareOrder(normalize, normalize2);
        }
        final XMLGregorianCalendarImpl xmlGregorianCalendarImpl = new XMLGregorianCalendarImpl();
        if (normalize.getTimezone() == 0) {
            final XMLGregorianCalendar xmlGregorianCalendar2 = (XMLGregorianCalendar)normalize2.clone();
            xmlGregorianCalendar2.setTimezone(840);
            final int compareOrder = this.compareOrder(normalize, xmlGregorianCalendar2.normalize());
            if (compareOrder == -1) {
                return compareOrder;
            }
            final XMLGregorianCalendar xmlGregorianCalendar3 = (XMLGregorianCalendar)normalize2.clone();
            xmlGregorianCalendar3.setTimezone(-840);
            final int compareOrder2 = this.compareOrder(normalize, xmlGregorianCalendar3.normalize());
            if (compareOrder2 == 1) {
                return compareOrder2;
            }
            return 2;
        }
        else {
            if (normalize2.getTimezone() != 0) {
                return 2;
            }
            final XMLGregorianCalendar xmlGregorianCalendar4 = (XMLGregorianCalendar)normalize.clone();
            xmlGregorianCalendar4.setTimezone(-840);
            final int compareOrder3 = this.compareOrder(xmlGregorianCalendar4.normalize(), normalize2);
            if (compareOrder3 == -1) {
                return compareOrder3;
            }
            final XMLGregorianCalendar xmlGregorianCalendar5 = (XMLGregorianCalendar)normalize.clone();
            xmlGregorianCalendar5.setTimezone(840);
            final int compareOrder4 = this.compareOrder(xmlGregorianCalendar5.normalize(), normalize2);
            if (compareOrder4 == 1) {
                return compareOrder4;
            }
            return 2;
        }
    }
    
    private int compareOrder(final XMLGregorianCalendar xmlGregorianCalendar, final XMLGregorianCalendar xmlGregorianCalendar2) {
        final BigInteger eonAndYear = xmlGregorianCalendar.getEonAndYear();
        final BigInteger eonAndYear2 = xmlGregorianCalendar2.getEonAndYear();
        if (eonAndYear != eonAndYear2) {
            if (eonAndYear == null || eonAndYear2 == null) {
                return 2;
            }
            final int compareTo = eonAndYear.compareTo(eonAndYear2);
            if (compareTo != 0) {
                if (compareTo == 1) {
                    return 1;
                }
                return -1;
            }
        }
        final int checkValue = this.checkValue(xmlGregorianCalendar.getMonth(), xmlGregorianCalendar2.getMonth());
        if (checkValue != 0) {
            return checkValue;
        }
        final int checkValue2 = this.checkValue(xmlGregorianCalendar.getDay(), xmlGregorianCalendar2.getDay());
        if (checkValue2 != 0) {
            return checkValue2;
        }
        final int checkValue3 = this.checkValue(xmlGregorianCalendar.getHour(), xmlGregorianCalendar2.getHour());
        if (checkValue3 != 0) {
            return checkValue3;
        }
        final int checkValue4 = this.checkValue(xmlGregorianCalendar.getMinute(), xmlGregorianCalendar2.getMinute());
        if (checkValue4 != 0) {
            return checkValue4;
        }
        final int checkValue5 = this.checkValue(xmlGregorianCalendar.getSecond(), xmlGregorianCalendar2.getSecond());
        if (checkValue5 != 0) {
            return checkValue5;
        }
        final BigDecimal fractionalSecond = xmlGregorianCalendar.getFractionalSecond();
        final BigDecimal fractionalSecond2 = xmlGregorianCalendar2.getFractionalSecond();
        if (fractionalSecond == fractionalSecond2) {
            return 0;
        }
        final int compareTo2 = ((fractionalSecond == null) ? XMLGregorianCalendarImpl.ZERO : fractionalSecond).compareTo((fractionalSecond2 == null) ? XMLGregorianCalendarImpl.ZERO : fractionalSecond2);
        if (compareTo2 == 0) {
            return 0;
        }
        if (compareTo2 == 1) {
            return 1;
        }
        return -1;
    }
    
    private int checkValue(final int n, final int n2) {
        if (n == n2) {
            return 0;
        }
        if (n == Integer.MIN_VALUE || n2 == Integer.MIN_VALUE) {
            return 2;
        }
        if (n < n2) {
            return -1;
        }
        return 1;
    }
    
    public XMLGregorianCalendar normalize() {
        XMLGregorianCalendarImpl xmlGregorianCalendarImpl = this;
        if (this.timezone != Integer.MIN_VALUE && this.timezone != 0) {
            xmlGregorianCalendarImpl = (XMLGregorianCalendarImpl)this.clone();
            final BigInteger bigInteger = XMLGregorianCalendarImpl.ZERO.toBigInteger();
            xmlGregorianCalendarImpl.add(new DurationImpl((this.timezone > 0) ? -1 : 1, bigInteger, bigInteger, bigInteger, bigInteger, (this.timezone > 0) ? BigInteger.valueOf(this.timezone) : BigInteger.valueOf(-this.timezone), XMLGregorianCalendarImpl.ZERO));
            xmlGregorianCalendarImpl.timezone = 0;
        }
        return xmlGregorianCalendarImpl;
    }
    
    public String toXMLFormat() {
        final QName xmlSchemaType = this.getXMLSchemaType();
        final StringBuffer sb = new StringBuffer();
        if (xmlSchemaType == null) {
            sb.append("0001-01-01T00:00:00Z");
        }
        else if (xmlSchemaType == DatatypeConstants.GYEAR) {
            sb.append((this.eon == null) ? (((this.year < 0) ? "-" : "") + ((this.year > -10 && this.year < 10) ? "000" : ((this.year > -100 && this.year < 100) ? "00" : ((this.year > -1000 && this.year < 1000) ? "0" : "")))) : "");
            sb.append(this.getEonAndYear().abs().toString());
        }
        else if (xmlSchemaType == DatatypeConstants.GMONTH) {
            sb.append("--");
            sb.append((this.month < 10) ? "0" : "");
            sb.append(String.valueOf(this.month));
            sb.append("--");
        }
        else if (xmlSchemaType == DatatypeConstants.GDAY) {
            sb.append("---");
            sb.append((this.day < 10) ? "0" : "");
            sb.append(String.valueOf(this.day));
        }
        else if (xmlSchemaType == DatatypeConstants.GYEARMONTH) {
            sb.append((this.eon == null) ? (((this.year < 0) ? "-" : "") + ((this.year > -10 && this.year < 10) ? "000" : ((this.year > -100 && this.year < 100) ? "00" : ((this.year > -1000 && this.year < 1000) ? "0" : "")))) : "");
            sb.append(this.getEonAndYear().abs().toString());
            sb.append("-");
            sb.append((this.month < 10) ? "0" : "");
            sb.append(String.valueOf(this.month));
        }
        else if (xmlSchemaType == DatatypeConstants.GMONTHDAY) {
            sb.append("--");
            sb.append((this.month < 10) ? "0" : "");
            sb.append(String.valueOf(this.month));
            sb.append("-");
            sb.append((this.day < 10) ? "0" : "");
            sb.append(String.valueOf(this.day));
        }
        else if (xmlSchemaType == DatatypeConstants.DATE) {
            sb.append((this.eon == null) ? (((this.year < 0) ? "-" : "") + ((this.year > -10 && this.year < 10) ? "000" : ((this.year > -100 && this.year < 100) ? "00" : ((this.year > -1000 && this.year < 1000) ? "0" : "")))) : "");
            sb.append(this.getEonAndYear().abs().toString());
            sb.append("-");
            sb.append((this.month < 10) ? "0" : "");
            sb.append(String.valueOf(this.month));
            sb.append("-");
            sb.append((this.day < 10) ? "0" : "");
            sb.append(String.valueOf(this.day));
        }
        else if (xmlSchemaType == DatatypeConstants.TIME) {
            sb.append((this.hour < 10) ? "0" : "");
            sb.append(String.valueOf(this.hour));
            sb.append(":");
            sb.append((this.minute < 10) ? "0" : "");
            sb.append(String.valueOf(this.minute));
            sb.append(":");
            sb.append((this.second < 10) ? "0" : "");
            sb.append(String.valueOf(this.second));
            sb.append((this.fracSeconds != null) ? this.toString(this.fracSeconds) : "");
        }
        else if (xmlSchemaType == DatatypeConstants.DATETIME) {
            sb.append((this.eon == null) ? (((this.year < 0) ? "-" : "") + ((this.year > -10 && this.year < 10) ? "000" : ((this.year > -100 && this.year < 100) ? "00" : ((this.year > -1000 && this.year < 1000) ? "0" : "")))) : "");
            sb.append(this.getEonAndYear().abs().toString());
            sb.append("-");
            sb.append((this.month < 10) ? "0" : "");
            sb.append(String.valueOf(this.month));
            sb.append("-");
            sb.append((this.day < 10) ? "0" : "");
            sb.append(String.valueOf(this.day));
            sb.append("T");
            sb.append((this.hour < 10) ? "0" : "");
            sb.append(String.valueOf(this.hour));
            sb.append(":");
            sb.append((this.minute < 10) ? "0" : "");
            sb.append(String.valueOf(this.minute));
            sb.append(":");
            sb.append((this.second < 10) ? "0" : "");
            sb.append(String.valueOf(this.second));
            sb.append((this.fracSeconds != null) ? this.toString(this.fracSeconds).substring(1) : "");
        }
        return this.getTimezoneString(sb);
    }
    
    private String getTimezoneString(final StringBuffer sb) {
        if (this.timezone == Integer.MIN_VALUE) {
            return sb.toString();
        }
        if (this.timezone != 0) {
            final int n = (this.timezone < 0) ? -1 : 1;
            final int n2 = this.timezone / 60 * n;
            final int n3 = this.timezone % 60 * n;
            sb.append((char)((n == -1) ? 45 : 43));
            sb.append((n2 < 10) ? "0" : "");
            sb.append(n2);
            sb.append(":");
            sb.append((n3 < 10) ? "0" : "");
            sb.append(n3);
            return sb.toString();
        }
        sb.append("Z");
        return sb.toString();
    }
    
    public QName getXMLSchemaType() {
        final boolean b = this.hour != Integer.MIN_VALUE;
        final boolean b2 = this.minute != Integer.MIN_VALUE;
        final boolean b3 = this.second != Integer.MIN_VALUE;
        if (b && b2 && b3) {
            if (this.year != Integer.MIN_VALUE && this.month != Integer.MIN_VALUE && this.day != Integer.MIN_VALUE) {
                return DatatypeConstants.DATETIME;
            }
            if (this.year == Integer.MIN_VALUE && this.month == Integer.MIN_VALUE && this.day == Integer.MIN_VALUE) {
                return DatatypeConstants.TIME;
            }
        }
        else if (!b && !b2 && !b3) {
            final boolean b4 = this.year != Integer.MIN_VALUE;
            final boolean b5 = this.month != Integer.MIN_VALUE;
            final boolean b6 = this.day != Integer.MIN_VALUE;
            if (b4) {
                if (b5 && b6) {
                    return DatatypeConstants.DATE;
                }
                if (b5 && !b6) {
                    return DatatypeConstants.GYEARMONTH;
                }
                if (!b5 && !b6) {
                    return DatatypeConstants.GYEAR;
                }
            }
            else if (b5) {
                if (b6) {
                    return DatatypeConstants.GMONTHDAY;
                }
                return DatatypeConstants.GMONTH;
            }
            else if (b6) {
                return DatatypeConstants.GDAY;
            }
        }
        throw new IllegalStateException(DatatypeMessageFormatter.formatMessage(null, "InvalidFieldsSet", new Object[] { (this.year != Integer.MIN_VALUE) ? String.valueOf(this.year) : "FIELD_UNDEFINED", (this.month != Integer.MIN_VALUE) ? String.valueOf(this.month) : "FIELD_UNDEFINED", (this.day != Integer.MIN_VALUE) ? String.valueOf(this.day) : "FIELD_UNDEFINED", (this.hour != Integer.MIN_VALUE) ? String.valueOf(this.hour) : "FIELD_UNDEFINED", (this.minute != Integer.MIN_VALUE) ? String.valueOf(this.minute) : "FIELD_UNDEFINED", (this.second != Integer.MIN_VALUE) ? String.valueOf(this.second) : "FIELD_UNDEFINED", (this.fracSeconds != null) ? this.fracSeconds.toString() : "FIELD_UNDEFINED", (this.timezone != Integer.MIN_VALUE) ? String.valueOf(this.timezone) : "FIELD_UNDEFINED" }));
    }
    
    public boolean isValid() {
        final BigInteger eonAndYear = this.getEonAndYear();
        if (this.month != Integer.MIN_VALUE && this.day != Integer.MIN_VALUE) {
            if (this.day > this.maxDaysInMonthFor((eonAndYear != null) ? eonAndYear : BigInteger.valueOf(2000L), this.month)) {
                return false;
            }
        }
        else if (this.day != Integer.MIN_VALUE && this.day > this.maxDaysInMonthFor((eonAndYear != null) ? eonAndYear : BigInteger.valueOf(2000L), 1)) {
            return false;
        }
        return (this.hour != 24 || (this.minute == 0 && this.second == 0)) && (this.eon != null || this.year != 0) && (eonAndYear == null || eonAndYear.compareTo(BigInteger.ZERO) != 0) && (this.fracSeconds == null || this.second != Integer.MIN_VALUE);
    }
    
    public void add(final Duration duration) {
        final boolean[] array = { this.eon == null, this.year == Integer.MIN_VALUE, this.month == Integer.MIN_VALUE, this.day == Integer.MIN_VALUE, this.hour == Integer.MIN_VALUE, this.minute == Integer.MIN_VALUE, this.second == Integer.MIN_VALUE, this.fracSeconds == null };
        final int sign = duration.getSign();
        final BigInteger bigInteger = (BigInteger)duration.getField(DatatypeConstants.MONTHS);
        final BigInteger add = BigInteger.valueOf((this.month != Integer.MIN_VALUE) ? this.month : 1L).add((bigInteger != null) ? ((sign == -1) ? bigInteger.negate() : bigInteger) : XMLGregorianCalendarImpl.I_ZERO);
        this.month = DateTimeUtil.modulo(add, XMLGregorianCalendarImpl.I_ONE, XMLGregorianCalendarImpl.THIRTEEN).intValue();
        final BigInteger fQuotient = DateTimeUtil.fQuotient(add, XMLGregorianCalendarImpl.I_ONE, XMLGregorianCalendarImpl.THIRTEEN);
        final BigInteger eonAndYear = this.getEonAndYear();
        final BigInteger bigInteger2 = (BigInteger)duration.getField(DatatypeConstants.YEARS);
        this.setYear(((eonAndYear != null) ? eonAndYear : XMLGregorianCalendarImpl.I_ZERO).add((bigInteger2 != null) ? ((sign == -1) ? bigInteger2.negate() : bigInteger2) : XMLGregorianCalendarImpl.I_ZERO).add(fQuotient));
        final BigDecimal bigDecimal = (BigDecimal)(duration.isSet(DatatypeConstants.SECONDS) ? ((sign == -1) ? ((BigDecimal)duration.getField(DatatypeConstants.SECONDS)).negate() : duration.getField(DatatypeConstants.SECONDS)) : XMLGregorianCalendarImpl.ZERO);
        final BigDecimal setScale = bigDecimal.setScale(0, 2);
        final int n = ((this.second != Integer.MIN_VALUE) ? this.second : 0) + setScale.intValue();
        this.fracSeconds = ((this.fracSeconds != null) ? ((sign > 0) ? this.fracSeconds.subtract(bigDecimal.subtract(setScale)) : this.fracSeconds.add(bigDecimal.subtract(setScale))) : bigDecimal.subtract(setScale));
        final int fQuotient2 = DateTimeUtil.fQuotient(n, 60);
        this.second = DateTimeUtil.mod(n, 60, fQuotient2);
        final int n2 = ((this.minute != Integer.MIN_VALUE) ? this.minute : 0) + sign * duration.getMinutes() + fQuotient2;
        final int fQuotient3 = DateTimeUtil.fQuotient(n2, 60);
        this.minute = DateTimeUtil.mod(n2, 60, fQuotient3);
        final int n3 = ((this.hour != Integer.MIN_VALUE) ? this.hour : 0) + sign * duration.getHours() + fQuotient3;
        final int fQuotient4 = DateTimeUtil.fQuotient(n3, 24);
        this.hour = DateTimeUtil.mod(n3, 24, fQuotient4);
        this.day = ((this.day != Integer.MIN_VALUE) ? this.day : 1) + sign * duration.getDays() + fQuotient4;
        while (true) {
            final int n4 = (this.eon != null) ? this.maxDaysInMonthFor(this.eon.add((this.year != Integer.MIN_VALUE) ? BigInteger.valueOf(this.year) : BigInteger.valueOf(2000L)), (this.month != Integer.MIN_VALUE) ? this.month : 1) : DateTimeUtil.maxDayInMonthFor((this.year != Integer.MIN_VALUE) ? this.year : 2000, (this.month != Integer.MIN_VALUE) ? this.month : 1);
            if (this.month < 1) {
                this.month += 12;
                final BigInteger eonAndYear2 = this.getEonAndYear();
                if (eonAndYear2 != null) {
                    this.setYear(eonAndYear2.subtract(XMLGregorianCalendarImpl.I_ONE));
                }
                else {
                    this.setYear(-1);
                }
            }
            else if (this.month > 12) {
                this.month -= 12;
                final BigInteger eonAndYear3 = this.getEonAndYear();
                if (eonAndYear3 != null) {
                    this.setYear(eonAndYear3.add(XMLGregorianCalendarImpl.I_ONE));
                }
                else {
                    this.setYear(1);
                }
            }
            else {
                int n5;
                if (this.day < 1) {
                    this.day += ((this.eon != null) ? this.maxDaysInMonthFor(this.eon.add((this.year != Integer.MIN_VALUE) ? BigInteger.valueOf(this.year) : BigInteger.valueOf(2000L)), ((this.month != Integer.MIN_VALUE) ? this.month : 1) - 1) : DateTimeUtil.maxDayInMonthFor((this.year != Integer.MIN_VALUE) ? this.year : 2000, ((this.month != Integer.MIN_VALUE) ? this.month : 1) - 1));
                    n5 = -1;
                }
                else if (this.day > n4) {
                    this.day -= n4;
                    n5 = 1;
                }
                else {
                    if (this.hour < 0) {
                        this.hour += 24;
                        --this.day;
                        continue;
                    }
                    if (this.hour > 24) {
                        this.hour -= 24;
                        ++this.day;
                        continue;
                    }
                    if (this.minute < 0) {
                        this.minute += 60;
                        --this.hour;
                        continue;
                    }
                    if (this.minute > 60) {
                        this.minute -= 60;
                        ++this.hour;
                        continue;
                    }
                    if (this.second < 0) {
                        this.second += 60;
                        --this.minute;
                        continue;
                    }
                    if (this.second > 60) {
                        this.second -= 60;
                        ++this.minute;
                        continue;
                    }
                    if (this.fracSeconds.compareTo(XMLGregorianCalendarImpl.ZERO) == -1) {
                        this.fracSeconds = this.fracSeconds.add(XMLGregorianCalendarImpl.ONE);
                        --this.second;
                        continue;
                    }
                    if (this.fracSeconds.compareTo(XMLGregorianCalendarImpl.ONE) == 1) {
                        this.fracSeconds = this.fracSeconds.subtract(XMLGregorianCalendarImpl.ONE);
                        ++this.second;
                        continue;
                    }
                    break;
                }
                final int n6 = this.month + n5;
                this.month = DateTimeUtil.modulo(n6, 1, 13);
                this.setYear(this.year + DateTimeUtil.fQuotient(n6, 1, 13));
            }
        }
        if (array[0] && array[1]) {
            this.eon = null;
            this.year = Integer.MIN_VALUE;
        }
        if (array[2]) {
            this.month = Integer.MIN_VALUE;
        }
        if (array[3]) {
            this.day = Integer.MIN_VALUE;
        }
        if (array[4]) {
            this.hour = Integer.MIN_VALUE;
        }
        if (array[5]) {
            this.minute = Integer.MIN_VALUE;
        }
        if (array[6] && array[7]) {
            this.second = Integer.MIN_VALUE;
            this.fracSeconds = null;
        }
    }
    
    public GregorianCalendar toGregorianCalendar() {
        return this.toGregorianCalendar(null, null, null);
    }
    
    public GregorianCalendar toGregorianCalendar(final TimeZone timeZone, final Locale locale, final XMLGregorianCalendar xmlGregorianCalendar) {
        final boolean b = xmlGregorianCalendar != null;
        final GregorianCalendar gregorianCalendar = new GregorianCalendar((timeZone != null) ? timeZone : this.getTimeZone(b ? xmlGregorianCalendar.getTimezone() : Integer.MIN_VALUE), (locale != null) ? locale : Locale.getDefault());
        gregorianCalendar.clear();
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        final BigInteger eonAndYear = this.getEonAndYear();
        if (eonAndYear != null) {
            gregorianCalendar.set(0, (eonAndYear.signum() != -1) ? 1 : 0);
            gregorianCalendar.set(1, eonAndYear.abs().intValue());
        }
        else if (b) {
            final BigInteger eonAndYear2 = xmlGregorianCalendar.getEonAndYear();
            if (eonAndYear2 != null) {
                gregorianCalendar.set(0, (eonAndYear2.signum() != -1) ? 1 : 0);
                gregorianCalendar.set(1, eonAndYear2.abs().intValue());
            }
        }
        if (this.month != Integer.MIN_VALUE) {
            gregorianCalendar.set(2, this.month - 1);
        }
        else if (b) {
            gregorianCalendar.set(2, xmlGregorianCalendar.getMonth() - 1);
        }
        if (this.day != Integer.MIN_VALUE) {
            gregorianCalendar.set(5, this.day);
        }
        else if (b) {
            gregorianCalendar.set(5, xmlGregorianCalendar.getDay());
        }
        if (this.hour != Integer.MIN_VALUE) {
            gregorianCalendar.set(11, this.hour);
        }
        else if (b) {
            gregorianCalendar.set(11, xmlGregorianCalendar.getHour());
        }
        if (this.minute != Integer.MIN_VALUE) {
            gregorianCalendar.set(12, this.minute);
        }
        else if (b) {
            gregorianCalendar.set(12, xmlGregorianCalendar.getMinute());
        }
        if (this.second != Integer.MIN_VALUE) {
            gregorianCalendar.set(13, this.second);
        }
        else if (b) {
            gregorianCalendar.set(13, xmlGregorianCalendar.getSecond());
        }
        if (this.fracSeconds != null) {
            gregorianCalendar.set(14, this.getMillisecond());
        }
        else if (b) {
            gregorianCalendar.set(14, xmlGregorianCalendar.getMillisecond());
        }
        return gregorianCalendar;
    }
    
    public TimeZone getTimeZone(final int n) {
        final int n2 = (this.timezone != Integer.MIN_VALUE) ? this.timezone : n;
        if (n2 == Integer.MIN_VALUE) {
            return TimeZone.getDefault();
        }
        final int n3 = n2 / 60;
        final int n4 = n2 % 60;
        final StringBuffer sb = new StringBuffer(9);
        sb.append("GMT");
        sb.append((n2 > 0) ? '+' : '-');
        sb.append((n3 < 10) ? "0" : "");
        sb.append(n3);
        sb.append(':');
        sb.append((n4 < 10) ? "0" : "");
        sb.append(n4);
        return TimeZone.getTimeZone(sb.toString());
    }
    
    public Object clone() {
        final XMLGregorianCalendarImpl xmlGregorianCalendarImpl = new XMLGregorianCalendarImpl();
        xmlGregorianCalendarImpl.eon = ((this.eon != null) ? new BigInteger(this.eon.toString()) : null);
        xmlGregorianCalendarImpl.year = this.year;
        xmlGregorianCalendarImpl.month = this.month;
        xmlGregorianCalendarImpl.day = this.day;
        xmlGregorianCalendarImpl.hour = this.hour;
        xmlGregorianCalendarImpl.minute = this.minute;
        xmlGregorianCalendarImpl.second = this.second;
        xmlGregorianCalendarImpl.fracSeconds = ((this.fracSeconds != null) ? new BigDecimal(this.fracSeconds.toString()) : null);
        xmlGregorianCalendarImpl.timezone = this.timezone;
        xmlGregorianCalendarImpl.orig_eon = ((this.orig_eon != null) ? new BigInteger(this.orig_eon.toString()) : null);
        xmlGregorianCalendarImpl.orig_year = this.orig_year;
        xmlGregorianCalendarImpl.orig_month = this.orig_month;
        xmlGregorianCalendarImpl.orig_day = this.orig_day;
        xmlGregorianCalendarImpl.orig_hour = this.orig_hour;
        xmlGregorianCalendarImpl.orig_minute = this.orig_minute;
        xmlGregorianCalendarImpl.orig_second = this.orig_second;
        xmlGregorianCalendarImpl.orig_fracSeconds = ((this.orig_fracSeconds != null) ? new BigDecimal(this.orig_fracSeconds.toString()) : null);
        xmlGregorianCalendarImpl.orig_timezone = this.orig_timezone;
        return xmlGregorianCalendarImpl;
    }
    
    private String toString(final BigDecimal bigDecimal) {
        final String string = bigDecimal.unscaledValue().toString();
        final int scale = bigDecimal.scale();
        if (scale == 0) {
            return string;
        }
        final int n = string.length() - scale;
        if (n == 0) {
            return "0." + string;
        }
        StringBuffer sb;
        if (n > 0) {
            sb = new StringBuffer(string);
            sb.insert(n, '.');
        }
        else {
            sb = new StringBuffer(3 - n + string.length());
            sb.append("0.");
            for (int i = 0; i < -n; ++i) {
                sb.append('0');
            }
            sb.append(string);
        }
        return sb.toString();
    }
    
    static {
        MAX_INT = new BigInteger("1000000000");
        MILLI_MULT = new BigDecimal("1000");
        ZERO = new BigDecimal(0.0);
        I_ZERO = XMLGregorianCalendarImpl.ZERO.toBigInteger();
        ONE = new BigDecimal(1.0);
        I_ONE = XMLGregorianCalendarImpl.ONE.toBigInteger();
        THIRTEEN = BigInteger.valueOf(13L);
    }
}
