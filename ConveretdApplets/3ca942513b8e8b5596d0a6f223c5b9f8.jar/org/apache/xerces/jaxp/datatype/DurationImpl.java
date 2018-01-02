// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.datatype;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Locale;
import org.apache.xerces.util.DatatypeMessageFormatter;
import javax.xml.namespace.QName;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.datatype.Duration;

class DurationImpl extends Duration
{
    private int sign;
    private BigInteger years;
    private BigInteger months;
    private BigInteger days;
    private BigInteger hours;
    private BigInteger minutes;
    private BigDecimal seconds;
    private static final int PROPERTY_COUNT = 6;
    private static final XMLGregorianCalendar[] SAVED_CAL;
    private static final BigDecimal ZERO;
    private static final BigDecimal ONE;
    private static final BigInteger I_ONE;
    private static final BigInteger I_ZERO;
    private static final BigInteger TWELVE;
    private static final BigDecimal SIXTY;
    private static final BigInteger I_SIXTY;
    private static final BigInteger TWENTY_FOUR;
    private static final BigInteger THIRTEEN;
    private static final BigDecimal[] FIELD_MAXVALUES;
    private static final DatatypeConstants.Field[] FIELDS;
    
    private DurationImpl() {
        this.sign = 0;
        this.years = null;
        this.months = null;
        this.days = null;
        this.hours = null;
        this.minutes = null;
        this.seconds = null;
    }
    
    public DurationImpl(final String s, final QName qName) throws NullPointerException, IllegalArgumentException, UnsupportedOperationException {
        this.sign = 0;
        this.years = null;
        this.months = null;
        this.days = null;
        this.hours = null;
        this.minutes = null;
        this.seconds = null;
        this.parse(s, qName);
    }
    
    public DurationImpl(final int sign, final BigInteger years, final BigInteger months, final BigInteger days, final BigInteger hours, final BigInteger minutes, final BigDecimal seconds) {
        this.sign = 0;
        this.years = null;
        this.months = null;
        this.days = null;
        this.hours = null;
        this.minutes = null;
        this.seconds = null;
        this.sign = sign;
        if (years != null && years.signum() == -1) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "YearsCannotBeNegative", new Object[] { years }));
        }
        this.years = years;
        if (months != null && months.signum() == -1) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "MonthsCannotBeNegative", new Object[] { months }));
        }
        this.months = months;
        if (days != null && days.signum() == -1) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "DaysCannotBeNegative", new Object[] { days }));
        }
        this.days = days;
        if (hours != null && hours.signum() == -1) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "HoursCannotBeNegative", new Object[] { hours }));
        }
        this.hours = hours;
        if (minutes != null && minutes.signum() == -1) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "MinutesCannotBeNegative", new Object[] { minutes }));
        }
        this.minutes = minutes;
        if (seconds != null && seconds.signum() == -1) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "SecondsCannotBeNegative", new Object[] { seconds }));
        }
        this.seconds = seconds;
    }
    
    public DurationImpl(long n) {
        this.sign = 0;
        this.years = null;
        this.months = null;
        this.days = null;
        this.hours = null;
        this.minutes = null;
        this.seconds = null;
        boolean b = false;
        if (n == 0L) {
            this.sign = 0;
            return;
        }
        if (n < 0L) {
            this.sign = -1;
            if (n == Long.MIN_VALUE) {
                ++n;
                b = true;
            }
            n *= -1L;
        }
        else {
            this.sign = 1;
        }
        this.years = null;
        this.months = null;
        final long n2 = n / 86400000L;
        this.days = BigInteger.valueOf(n2);
        n -= n2 * 86400000L;
        final long n3 = n / 3600000L;
        this.hours = BigInteger.valueOf(n3);
        n -= n3 * 3600000L;
        final long n4 = n / 60000L;
        this.minutes = BigInteger.valueOf(n4);
        n -= n4 * 60000L;
        this.seconds = new BigDecimal(String.valueOf(n / 1000.0)).add(b ? DurationImpl.ONE : DurationImpl.ZERO);
    }
    
    public DurationImpl(final boolean b, final BigInteger years, final BigInteger months, final BigInteger days, final BigInteger hours, final BigInteger minutes, final BigDecimal seconds) throws UnsupportedOperationException, IllegalArgumentException {
        this.sign = 0;
        this.years = null;
        this.months = null;
        this.days = null;
        this.hours = null;
        this.minutes = null;
        this.seconds = null;
        if (years != null && years.signum() == -1) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "YearsCannotBeNegative", new Object[] { years }));
        }
        this.years = years;
        if (months != null && months.signum() == -1) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "MonthsCannotBeNegative", new Object[] { months }));
        }
        this.months = months;
        if (days != null && days.signum() == -1) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "DaysCannotBeNegative", new Object[] { days }));
        }
        this.days = days;
        if (hours != null && hours.signum() == -1) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "HoursCannotBeNegative", new Object[] { hours }));
        }
        this.hours = hours;
        if (minutes != null && minutes.signum() == -1) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "MinutesCannotBeNegative", new Object[] { minutes }));
        }
        this.minutes = minutes;
        if (seconds != null && seconds.signum() == -1) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "SecondsCannotBeNegative", new Object[] { seconds }));
        }
        this.seconds = seconds;
        if ((years == null || years.signum() == 0) && (months == null || months.signum() == 0) && (days == null || days.signum() == 0) && (hours == null || hours.signum() == 0) && (minutes == null || minutes.signum() == 0) && (seconds == null || seconds.signum() == 0)) {
            this.sign = 0;
        }
        else {
            this.sign = (b ? 1 : -1);
        }
    }
    
    public int getSign() {
        return this.sign;
    }
    
    public Number getField(final DatatypeConstants.Field field) throws NullPointerException, IllegalArgumentException {
        if (field == null) {
            throw new NullPointerException(DatatypeMessageFormatter.formatMessage(null, "CannotBeNull", new Object[] { "Field" }));
        }
        if (field == DatatypeConstants.YEARS) {
            return this.years;
        }
        if (field == DatatypeConstants.MONTHS) {
            return this.months;
        }
        if (field == DatatypeConstants.DAYS) {
            return this.days;
        }
        if (field == DatatypeConstants.HOURS) {
            return this.hours;
        }
        if (field == DatatypeConstants.MINUTES) {
            return this.minutes;
        }
        if (field == DatatypeConstants.SECONDS) {
            return this.seconds;
        }
        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidValue", new Object[] { "field", field }));
    }
    
    public boolean isSet(final DatatypeConstants.Field field) throws NullPointerException, IllegalArgumentException {
        if (field == null) {
            throw new NullPointerException(DatatypeMessageFormatter.formatMessage(null, "CannotBeNull", new Object[] { "Field" }));
        }
        if (field == DatatypeConstants.YEARS) {
            return this.years != null;
        }
        if (field == DatatypeConstants.MONTHS) {
            return this.months != null;
        }
        if (field == DatatypeConstants.DAYS) {
            return this.days != null;
        }
        if (field == DatatypeConstants.HOURS) {
            return this.hours != null;
        }
        if (field == DatatypeConstants.MINUTES) {
            return this.minutes != null;
        }
        if (field == DatatypeConstants.SECONDS) {
            return this.seconds != null;
        }
        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidValue", new Object[] { "field", field }));
    }
    
    public Duration add(final Duration duration) {
        return this.addDuration(this, duration, new DurationImpl());
    }
    
    public void addTo(final Calendar calendar) {
        calendar.add(1, this.sign * this.getYears());
        calendar.add(2, this.sign * this.getMonths());
        calendar.add(5, this.sign * this.getDays());
        calendar.add(10, this.sign * this.getHours());
        calendar.add(12, this.sign * this.getMinutes());
        calendar.add(13, this.sign * this.getSeconds());
        calendar.add(14, (this.seconds != null) ? ((int)(this.sign * this.seconds.subtract(this.seconds.setScale(0, 1)).floatValue() * 1000.0f)) : 0);
    }
    
    public Duration multiply(final BigDecimal bigDecimal) {
        final int signum = bigDecimal.signum();
        final BigDecimal abs = bigDecimal.abs();
        final BigDecimal[] bigDecimalArray = this.createBigDecimalArray();
        final BigDecimal[] array = new BigDecimal[6];
        BigDecimal bigDecimal2 = DurationImpl.ZERO;
        for (int i = 0; i < 6; ++i) {
            final BigDecimal add = bigDecimalArray[i].multiply(abs).add(bigDecimal2.multiply(DurationImpl.FIELD_MAXVALUES[i]));
            array[i] = add.setScale(0, 1);
            bigDecimal2 = add.subtract(array[i]);
            if (bigDecimal2.signum() != 0 && DurationImpl.FIELDS[i] == DatatypeConstants.MONTHS) {
                throw new IllegalStateException();
            }
        }
        return new DurationImpl(this.sign * signum >= 0, (this.years != null) ? array[0].toBigInteger() : ((array[0].signum() != 0) ? array[0].toBigInteger() : null), (this.months != null) ? array[1].toBigInteger() : ((array[1].signum() != 0) ? array[1].toBigInteger() : null), (this.days != null) ? array[2].toBigInteger() : ((array[2].signum() != 0) ? array[2].toBigInteger() : null), (this.hours != null) ? array[3].toBigInteger() : ((array[3].signum() != 0) ? array[3].toBigInteger() : null), (this.minutes != null) ? array[4].toBigInteger() : ((array[4].signum() != 0) ? array[4].toBigInteger() : null), (this.seconds != null) ? array[5] : ((array[5].signum() != 0) ? array[5] : null));
    }
    
    private BigDecimal[] createBigDecimalArray() {
        return new BigDecimal[] { (this.years != null) ? new BigDecimal(this.years) : DurationImpl.ZERO, (this.months != null) ? new BigDecimal(this.months) : DurationImpl.ZERO, (this.days != null) ? new BigDecimal(this.days) : DurationImpl.ZERO, (this.hours != null) ? new BigDecimal(this.hours) : DurationImpl.ZERO, (this.minutes != null) ? new BigDecimal(this.minutes) : DurationImpl.ZERO, (this.seconds != null) ? this.seconds : DurationImpl.ZERO };
    }
    
    public Duration negate() {
        return new DurationImpl(-1 * this.sign, this.years, this.months, this.days, this.hours, this.minutes, this.seconds);
    }
    
    public Duration normalizeWith(final Calendar calendar) {
        final Calendar calendar2 = (Calendar)calendar.clone();
        calendar2.add(1, this.sign * this.getYears());
        calendar2.add(2, this.sign * this.getMonths());
        calendar2.add(5, this.sign * this.getDays());
        final int n = (int)((calendar2.getTime().getTime() - calendar.getTime().getTime()) / 86400000L);
        int n2 = 1;
        if (n == 0 && this.hours.signum() == 0 && this.minutes.signum() == 0 && this.seconds.signum() == 0) {
            n2 = 0;
        }
        else if (n < 0) {
            n2 = -1;
        }
        return new DurationImpl(n2, null, null, BigInteger.valueOf(n).abs(), this.hours, this.minutes, this.seconds);
    }
    
    public int compare(final Duration duration) {
        return this.compareDates(this, duration);
    }
    
    public int hashCode() {
        final GregorianCalendar gregorianCalendar = DurationImpl.SAVED_CAL[0].toGregorianCalendar();
        this.addTo(gregorianCalendar);
        return (int)gregorianCalendar.getTime().getTime();
    }
    
    private void parse(final String s, final QName qName) throws IllegalArgumentException {
        final int length = s.length();
        int n = 0;
        final char char1 = s.charAt(n++);
        if (char1 != 'P' && char1 != '-') {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "ParseDuration1", new Object[] { s }));
        }
        this.sign = ((char1 == '-') ? -1 : 1);
        if (char1 == '-' && s.charAt(n++) != 'P') {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "ParseDuration2", new Object[] { s }));
        }
        boolean b = false;
        int index = DateTimeUtil.indexOf(s, n, length, 'T');
        if (index == -1) {
            index = length;
        }
        else if (qName == DatatypeConstants.DURATION_YEARMONTH) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidYearMonthDuration", new Object[] { s }));
        }
        final int index2 = DateTimeUtil.indexOf(s, n, index, 'Y');
        if (index2 != -1) {
            if (qName == DatatypeConstants.DURATION_DAYTIME) {
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDayTimeDuration", new Object[] { s }));
            }
            try {
                this.years = new BigInteger(s.substring(n, index2));
            }
            catch (NumberFormatException ex) {
                throw new IllegalArgumentException(ex.getMessage());
            }
            if (this.years.signum() == -1) {
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "YearsCannotBeNegative", new Object[] { s }));
            }
            n = index2 + 1;
            b = true;
        }
        final int index3 = DateTimeUtil.indexOf(s, n, index, 'M');
        if (index3 != -1) {
            if (qName == DatatypeConstants.DURATION_DAYTIME) {
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidDayTimeDuration", new Object[] { s }));
            }
            try {
                this.months = new BigInteger(s.substring(n, index3));
            }
            catch (NumberFormatException ex2) {
                throw new IllegalArgumentException(ex2.getMessage());
            }
            if (this.months.signum() == -1) {
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "MonthsCannotBeNegative", new Object[] { s }));
            }
            n = index3 + 1;
            b = true;
        }
        final int index4 = DateTimeUtil.indexOf(s, n, index, 'D');
        if (index4 != -1) {
            if (qName == DatatypeConstants.DURATION_YEARMONTH) {
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidYearMonthDuration", new Object[] { s }));
            }
            try {
                this.days = new BigInteger(s.substring(n, index4));
            }
            catch (NumberFormatException ex3) {
                throw new IllegalArgumentException(ex3.getMessage());
            }
            if (this.days.signum() == -1) {
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "DaysCannotBeNegative", new Object[] { s }));
            }
            n = index4 + 1;
            b = true;
        }
        if (length == index && n != length) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "IllegalLexicalValue", new Object[] { s }));
        }
        if (length != index) {
            final int index5 = DateTimeUtil.indexOf(s, ++n, length, 'H');
            if (index5 != -1) {
                try {
                    this.hours = new BigInteger(s.substring(n, index5));
                }
                catch (NumberFormatException ex4) {
                    throw new IllegalArgumentException(ex4.getMessage());
                }
                if (this.hours.signum() == -1) {
                    throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "HoursCannotBeNegative", new Object[] { s }));
                }
                n = index5 + 1;
                b = true;
            }
            final int index6 = DateTimeUtil.indexOf(s, n, length, 'M');
            if (index6 != -1) {
                try {
                    this.minutes = new BigInteger(s.substring(n, index6));
                }
                catch (NumberFormatException ex5) {
                    throw new IllegalArgumentException(ex5.getMessage());
                }
                if (this.minutes.signum() == -1) {
                    throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "MinutesCannotBeNegative", new Object[] { s }));
                }
                n = index6 + 1;
                b = true;
            }
            final int index7 = DateTimeUtil.indexOf(s, n, length, 'S');
            if (index7 != -1) {
                try {
                    this.seconds = new BigDecimal(s.substring(n, index7));
                }
                catch (NumberFormatException ex6) {
                    throw new IllegalArgumentException(ex6.getMessage());
                }
                if (this.seconds.signum() == -1) {
                    throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "SecondsCannotBeNegative", new Object[] { s }));
                }
                n = index7 + 1;
                b = true;
            }
            if (n != length || s.charAt(--n) == 'T') {
                throw new IllegalArgumentException();
            }
        }
        if (!b) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "IllegalLexicalValue", new Object[] { s }));
        }
        if ((this.years == null || this.years.signum() == 0) && (this.months == null || this.months.signum() == 0) && (this.days == null || this.days.signum() == 0) && (this.hours == null || this.hours.signum() == 0) && (this.minutes == null || this.minutes.signum() == 0) && (this.seconds == null || this.seconds.signum() == 0)) {
            this.sign = 0;
        }
    }
    
    private DurationImpl addDuration(final DurationImpl durationImpl, final Duration duration, final DurationImpl durationImpl2) throws IllegalStateException {
        this.reset(durationImpl2);
        int sign = 0;
        final int sign2 = durationImpl.sign;
        final int sign3 = duration.getSign();
        BigInteger bigInteger = null;
        if (durationImpl.months != null || duration.isSet(DatatypeConstants.MONTHS)) {
            final BigInteger bigInteger2 = (BigInteger)duration.getField(DatatypeConstants.MONTHS);
            bigInteger = ((durationImpl.months != null) ? ((sign2 == -1) ? durationImpl.months.negate() : durationImpl.months) : DurationImpl.I_ZERO).add((bigInteger2 != null) ? ((sign3 == -1) ? bigInteger2.negate() : bigInteger2) : DurationImpl.I_ZERO);
        }
        BigInteger add = null;
        if (durationImpl.years != null || duration.isSet(DatatypeConstants.YEARS)) {
            final BigInteger bigInteger3 = (BigInteger)duration.getField(DatatypeConstants.YEARS);
            add = ((durationImpl.years != null) ? ((sign2 == -1) ? durationImpl.years.negate() : durationImpl.years) : DurationImpl.I_ZERO).add((bigInteger3 != null) ? ((sign3 == -1) ? bigInteger3.negate() : bigInteger3) : DurationImpl.I_ZERO);
        }
        BigDecimal bigDecimal = null;
        if (durationImpl.seconds != null || duration.isSet(DatatypeConstants.SECONDS)) {
            final BigDecimal bigDecimal2 = (BigDecimal)duration.getField(DatatypeConstants.SECONDS);
            bigDecimal = ((durationImpl.seconds != null) ? ((sign2 == -1) ? durationImpl.seconds.negate() : durationImpl.seconds) : DurationImpl.ZERO).add((bigDecimal2 != null) ? ((sign3 == -1) ? bigDecimal2.negate() : bigDecimal2) : DurationImpl.ZERO);
        }
        BigInteger bigInteger4 = null;
        if (durationImpl.minutes != null || duration.isSet(DatatypeConstants.MINUTES)) {
            final BigInteger bigInteger5 = (BigInteger)duration.getField(DatatypeConstants.MINUTES);
            bigInteger4 = ((durationImpl.minutes != null) ? ((sign2 == -1) ? durationImpl.minutes.negate() : durationImpl.minutes) : DurationImpl.I_ZERO).add((bigInteger5 != null) ? ((sign3 == -1) ? bigInteger5.negate() : bigInteger5) : DurationImpl.I_ZERO);
        }
        BigInteger bigInteger6 = null;
        if (durationImpl.hours != null || duration.isSet(DatatypeConstants.HOURS)) {
            final BigInteger bigInteger7 = (BigInteger)duration.getField(DatatypeConstants.HOURS);
            bigInteger6 = ((durationImpl.hours != null) ? ((sign2 == -1) ? durationImpl.hours.negate() : durationImpl.hours) : DurationImpl.I_ZERO).add((bigInteger7 != null) ? ((sign3 == -1) ? bigInteger7.negate() : bigInteger7) : DurationImpl.I_ZERO);
        }
        BigInteger add2 = null;
        if (durationImpl.days != null || duration.isSet(DatatypeConstants.DAYS)) {
            final BigInteger bigInteger8 = (BigInteger)duration.getField(DatatypeConstants.DAYS);
            add2 = ((durationImpl.days != null) ? ((sign2 == -1) ? durationImpl.days.negate() : durationImpl.days) : DurationImpl.I_ZERO).add((bigInteger8 != null) ? ((sign3 == -1) ? bigInteger8.negate() : bigInteger8) : DurationImpl.I_ZERO);
        }
        boolean b;
        do {
            b = false;
            int n = (add != null && add.signum() != 0) ? add2.signum() : 0;
            if (bigInteger != null) {
                if (n > 0 && bigInteger.signum() == -1) {
                    b = true;
                    while (bigInteger.signum() == -1) {
                        add = ((add != null) ? add.subtract(DurationImpl.I_ONE) : DurationImpl.I_ONE.negate());
                        bigInteger = bigInteger.add(DurationImpl.TWELVE);
                    }
                }
                else if (n < 0 && bigInteger.compareTo(DurationImpl.I_ZERO) == 1) {
                    b = true;
                    while (bigInteger.compareTo(DurationImpl.I_ZERO) == 1) {
                        add = ((add != null) ? add.add(DurationImpl.I_ONE) : DurationImpl.I_ONE);
                        bigInteger = bigInteger.subtract(DurationImpl.TWELVE);
                    }
                }
                if (bigInteger.signum() != 0) {
                    n = bigInteger.signum();
                }
            }
            if (add2 != null && add2.signum() != 0) {
                n = add2.signum();
            }
            if (bigInteger6 != null) {
                if (n > 0 && bigInteger6.signum() == -1) {
                    b = true;
                    while (bigInteger6.signum() == -1) {
                        add2 = ((add2 != null) ? add2.subtract(DurationImpl.I_ONE) : DurationImpl.I_ONE.negate());
                        bigInteger6 = bigInteger6.add(DurationImpl.TWENTY_FOUR);
                    }
                }
                else if (n < 0 && bigInteger6.compareTo(DurationImpl.I_ZERO) == 1) {
                    b = true;
                    while (bigInteger6.compareTo(DurationImpl.I_ZERO) == 1) {
                        add2 = ((add2 != null) ? add2.add(DurationImpl.I_ONE) : DurationImpl.I_ONE);
                        bigInteger6 = bigInteger6.subtract(DurationImpl.TWENTY_FOUR);
                    }
                }
                if (bigInteger6.signum() != 0) {
                    n = bigInteger6.signum();
                }
            }
            if (bigInteger4 != null) {
                if (n > 0 && bigInteger4.signum() == -1) {
                    b = true;
                    while (bigInteger4.signum() == -1) {
                        bigInteger6 = ((bigInteger6 != null) ? bigInteger6.subtract(DurationImpl.I_ONE) : DurationImpl.I_ONE.negate());
                        bigInteger4 = bigInteger4.add(DurationImpl.I_SIXTY);
                    }
                }
                else if (n < 0 && bigInteger4.compareTo(DurationImpl.I_ZERO) == 1) {
                    b = true;
                    while (bigInteger4.compareTo(DurationImpl.I_ZERO) == 1) {
                        bigInteger6 = bigInteger6.add(DurationImpl.I_ONE);
                        bigInteger4 = bigInteger4.subtract(DurationImpl.I_SIXTY);
                    }
                }
                if (bigInteger4.signum() != 0) {
                    n = bigInteger4.signum();
                }
            }
            if (bigDecimal != null) {
                if (n > 0 && bigDecimal.compareTo(DurationImpl.ZERO) == -1) {
                    b = true;
                    while (bigDecimal.compareTo(DurationImpl.ZERO) == -1) {
                        bigInteger4 = ((bigInteger4 != null) ? bigInteger4.subtract(DurationImpl.I_ONE) : DurationImpl.I_ONE.negate());
                        bigDecimal = bigDecimal.add(DurationImpl.SIXTY);
                    }
                }
                else if (n < 0 && bigDecimal.compareTo(DurationImpl.ZERO) == 1) {
                    b = true;
                    while (bigDecimal.compareTo(DurationImpl.ZERO) == 1) {
                        bigInteger4 = bigInteger4.add(DurationImpl.I_ONE);
                        bigDecimal = bigDecimal.subtract(DurationImpl.SIXTY);
                    }
                }
                if (bigDecimal.signum() == 0) {
                    continue;
                }
                bigDecimal.signum();
            }
        } while (b);
        if ((add != null && add.signum() == 1) || (bigInteger != null && bigInteger.signum() == 1) || (add2 != null && add2.signum() == 1) || (bigInteger6 != null && bigInteger6.signum() == 1) || (bigInteger4 != null && bigInteger4.signum() == 1) || (bigDecimal != null && bigDecimal.signum() == 1)) {
            if ((add != null && add.signum() == -1) || (bigInteger != null && bigInteger.signum() == -1) || (add2 != null && add2.signum() == -1) || (bigInteger6 != null && bigInteger6.signum() == -1) || (bigInteger4 != null && bigInteger4.signum() == -1) || (bigDecimal != null && bigDecimal.signum() == -1)) {
                throw new IllegalStateException();
            }
            sign = 1;
        }
        else if ((add != null && add.signum() == -1) || (bigInteger != null && bigInteger.signum() == -1) || (add2 != null && add2.signum() == -1) || (bigInteger6 != null && bigInteger6.signum() == -1) || (bigInteger4 != null && bigInteger4.signum() == -1) || (bigDecimal != null && bigDecimal.signum() == -1)) {
            sign = -1;
        }
        durationImpl2.sign = sign;
        durationImpl2.years = ((add != null) ? add.abs() : null);
        durationImpl2.months = ((bigInteger != null) ? bigInteger.abs() : null);
        durationImpl2.days = ((add2 != null) ? add2.abs() : null);
        durationImpl2.hours = ((this.hours != null) ? bigInteger6.abs() : null);
        durationImpl2.minutes = ((this.minutes != null) ? bigInteger4.abs() : null);
        durationImpl2.seconds = ((bigDecimal != null) ? bigDecimal.abs() : null);
        return durationImpl2;
    }
    
    private void reset(final DurationImpl durationImpl) {
        durationImpl.years = null;
        durationImpl.months = null;
        durationImpl.days = null;
        durationImpl.hours = null;
        durationImpl.minutes = null;
        durationImpl.seconds = null;
        durationImpl.sign = 0;
    }
    
    private int compareDates(final DurationImpl durationImpl, final Duration duration) {
        if (this.compareOrder(durationImpl, duration) == 0) {
            return 0;
        }
        final XMLGregorianCalendar xmlGregorianCalendar = (XMLGregorianCalendar)DurationImpl.SAVED_CAL[0].clone();
        final XMLGregorianCalendar xmlGregorianCalendar2 = (XMLGregorianCalendar)DurationImpl.SAVED_CAL[0].clone();
        xmlGregorianCalendar.add(durationImpl);
        xmlGregorianCalendar2.add(duration);
        final int compare = xmlGregorianCalendar.compare(xmlGregorianCalendar2);
        if (compare == 2) {
            return 2;
        }
        final XMLGregorianCalendar xmlGregorianCalendar3 = (XMLGregorianCalendar)DurationImpl.SAVED_CAL[1].clone();
        final XMLGregorianCalendar xmlGregorianCalendar4 = (XMLGregorianCalendar)DurationImpl.SAVED_CAL[1].clone();
        xmlGregorianCalendar3.add(durationImpl);
        xmlGregorianCalendar4.add(duration);
        final int compareResults = this.compareResults(compare, xmlGregorianCalendar3.compare(xmlGregorianCalendar4));
        if (compareResults == 2) {
            return 2;
        }
        final XMLGregorianCalendar xmlGregorianCalendar5 = (XMLGregorianCalendar)DurationImpl.SAVED_CAL[2].clone();
        final XMLGregorianCalendar xmlGregorianCalendar6 = (XMLGregorianCalendar)DurationImpl.SAVED_CAL[2].clone();
        xmlGregorianCalendar5.add(durationImpl);
        xmlGregorianCalendar6.add(duration);
        final int compareResults2 = this.compareResults(compareResults, xmlGregorianCalendar5.compare(xmlGregorianCalendar6));
        if (compareResults2 == 2) {
            return 2;
        }
        final XMLGregorianCalendar xmlGregorianCalendar7 = (XMLGregorianCalendar)DurationImpl.SAVED_CAL[3].clone();
        final XMLGregorianCalendar xmlGregorianCalendar8 = (XMLGregorianCalendar)DurationImpl.SAVED_CAL[3].clone();
        xmlGregorianCalendar7.add(durationImpl);
        xmlGregorianCalendar8.add(duration);
        return this.compareResults(compareResults2, xmlGregorianCalendar7.compare(xmlGregorianCalendar8));
    }
    
    private int compareResults(final int n, final int n2) {
        if (n2 == 2) {
            return 2;
        }
        if (n != n2) {
            return 2;
        }
        return n;
    }
    
    private int compareOrder(final Duration duration, final Duration duration2) {
        boolean b = true;
        final int sign = duration.getSign();
        final int sign2 = duration2.getSign();
        if (sign == 0 && sign2 == 0) {
            return 0;
        }
        if (sign == -1 && sign2 == -1) {
            b = false;
        }
        else {
            if (sign == 1 && sign2 == -1) {
                return 1;
            }
            if (sign == -1 && sign2 == 1) {
                return -1;
            }
        }
        final int compareTo = ((BigInteger)(duration.isSet(DatatypeConstants.YEARS) ? duration.getField(DatatypeConstants.YEARS) : DurationImpl.I_ZERO)).compareTo(duration2.isSet(DatatypeConstants.YEARS) ? ((BigInteger)duration2.getField(DatatypeConstants.YEARS)) : DurationImpl.I_ZERO);
        if (compareTo == -1) {
            return b ? -1 : 1;
        }
        if (compareTo == 1) {
            return b ? 1 : -1;
        }
        final int compareTo2 = ((BigInteger)(duration.isSet(DatatypeConstants.MONTHS) ? duration.getField(DatatypeConstants.MONTHS) : DurationImpl.I_ZERO)).compareTo(duration2.isSet(DatatypeConstants.MONTHS) ? ((BigInteger)duration2.getField(DatatypeConstants.MONTHS)) : DurationImpl.I_ZERO);
        if (compareTo2 == -1) {
            return b ? -1 : 1;
        }
        if (compareTo2 == 1) {
            return b ? 1 : -1;
        }
        final int compareTo3 = ((BigInteger)(duration.isSet(DatatypeConstants.DAYS) ? duration.getField(DatatypeConstants.DAYS) : DurationImpl.I_ZERO)).compareTo(duration2.isSet(DatatypeConstants.DAYS) ? ((BigInteger)duration2.getField(DatatypeConstants.DAYS)) : DurationImpl.I_ZERO);
        if (compareTo3 == -1) {
            return b ? -1 : 1;
        }
        if (compareTo3 == 1) {
            return b ? 1 : -1;
        }
        final int compareTo4 = ((BigInteger)(duration.isSet(DatatypeConstants.HOURS) ? duration.getField(DatatypeConstants.HOURS) : DurationImpl.I_ZERO)).compareTo(duration2.isSet(DatatypeConstants.HOURS) ? ((BigInteger)duration2.getField(DatatypeConstants.HOURS)) : DurationImpl.I_ZERO);
        if (compareTo4 == -1) {
            return b ? -1 : 1;
        }
        if (compareTo4 == 1) {
            return b ? 1 : -1;
        }
        final int compareTo5 = ((BigInteger)(duration.isSet(DatatypeConstants.MINUTES) ? duration.getField(DatatypeConstants.MINUTES) : DurationImpl.I_ZERO)).compareTo(duration2.isSet(DatatypeConstants.MINUTES) ? ((BigInteger)duration2.getField(DatatypeConstants.MINUTES)) : DurationImpl.I_ZERO);
        if (compareTo5 == -1) {
            return b ? -1 : 1;
        }
        if (compareTo5 == 1) {
            return b ? 1 : -1;
        }
        final int compareTo6 = ((BigDecimal)(duration.isSet(DatatypeConstants.SECONDS) ? duration.getField(DatatypeConstants.SECONDS) : DurationImpl.ZERO)).compareTo(duration2.isSet(DatatypeConstants.SECONDS) ? ((BigDecimal)duration2.getField(DatatypeConstants.SECONDS)) : DurationImpl.ZERO);
        if (compareTo6 == -1) {
            return b ? -1 : 1;
        }
        if (compareTo6 == 1) {
            return b ? 1 : -1;
        }
        return 0;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(30);
        if (this.sign == -1) {
            sb.append('-');
        }
        sb.append('P');
        if (this.years != null) {
            sb.append(this.years);
            sb.append('Y');
        }
        if (this.months != null) {
            sb.append(this.months);
            sb.append('M');
        }
        if (this.days != null) {
            sb.append(this.days);
            sb.append('D');
        }
        if (this.hours != null || this.minutes != null || this.seconds != null) {
            sb.append('T');
            if (this.hours != null) {
                sb.append(this.hours);
                sb.append('H');
            }
            if (this.minutes != null) {
                sb.append(this.minutes);
                sb.append('M');
            }
            if (this.seconds != null) {
                sb.append(this.toString(this.seconds));
                sb.append('S');
            }
        }
        return sb.toString();
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
        SAVED_CAL = new XMLGregorianCalendar[] { new XMLGregorianCalendarImpl("1696-09-01T00:00:00Z"), new XMLGregorianCalendarImpl("1697-02-01T00:00:00Z"), new XMLGregorianCalendarImpl("1903-03-01T00:00:00Z"), new XMLGregorianCalendarImpl("1903-07-01T00:00:00Z") };
        ZERO = new BigDecimal(0.0);
        ONE = new BigDecimal(1.0);
        I_ONE = DurationImpl.ONE.toBigInteger();
        I_ZERO = DurationImpl.ZERO.toBigInteger();
        TWELVE = BigInteger.valueOf(12L);
        SIXTY = BigDecimal.valueOf(60L);
        I_SIXTY = DurationImpl.SIXTY.toBigInteger();
        TWENTY_FOUR = BigInteger.valueOf(24L);
        THIRTEEN = BigInteger.valueOf(13L);
        FIELD_MAXVALUES = new BigDecimal[] { DurationImpl.ONE, new BigDecimal(12.0), DurationImpl.ZERO, new BigDecimal(24.0), new BigDecimal(60.0), new BigDecimal(60.0) };
        FIELDS = new DatatypeConstants.Field[] { DatatypeConstants.YEARS, DatatypeConstants.MONTHS, DatatypeConstants.DAYS, DatatypeConstants.HOURS, DatatypeConstants.MINUTES, DatatypeConstants.SECONDS };
    }
}
