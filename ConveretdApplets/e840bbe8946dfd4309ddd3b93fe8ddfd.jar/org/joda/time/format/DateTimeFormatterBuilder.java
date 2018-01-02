// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.format;

import org.joda.time.field.PreciseDateTimeField;
import org.joda.time.field.MillisDurationField;
import java.util.Set;
import org.joda.time.MutableDateTime;
import java.util.HashSet;
import java.util.HashMap;
import org.joda.time.DateTimeField;
import java.util.Map;
import org.joda.time.ReadablePartial;
import java.util.Locale;
import org.joda.time.DateTimeZone;
import org.joda.time.Chronology;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import org.joda.time.DateTimeFieldType;
import java.util.ArrayList;

public class DateTimeFormatterBuilder
{
    private ArrayList iElementPairs;
    private Object iFormatter;
    
    public DateTimeFormatterBuilder() {
        this.iElementPairs = new ArrayList();
    }
    
    public DateTimeFormatter toFormatter() {
        final Object formatter = this.getFormatter();
        DateTimePrinter dateTimePrinter = null;
        if (this.isPrinter(formatter)) {
            dateTimePrinter = (DateTimePrinter)formatter;
        }
        DateTimeParser dateTimeParser = null;
        if (this.isParser(formatter)) {
            dateTimeParser = (DateTimeParser)formatter;
        }
        if (dateTimePrinter != null || dateTimeParser != null) {
            return new DateTimeFormatter(dateTimePrinter, dateTimeParser);
        }
        throw new UnsupportedOperationException("Both printing and parsing not supported");
    }
    
    public DateTimePrinter toPrinter() {
        final Object formatter = this.getFormatter();
        if (this.isPrinter(formatter)) {
            return (DateTimePrinter)formatter;
        }
        throw new UnsupportedOperationException("Printing is not supported");
    }
    
    public DateTimeParser toParser() {
        final Object formatter = this.getFormatter();
        if (this.isParser(formatter)) {
            return (DateTimeParser)formatter;
        }
        throw new UnsupportedOperationException("Parsing is not supported");
    }
    
    public boolean canBuildFormatter() {
        return this.isFormatter(this.getFormatter());
    }
    
    public boolean canBuildPrinter() {
        return this.isPrinter(this.getFormatter());
    }
    
    public boolean canBuildParser() {
        return this.isParser(this.getFormatter());
    }
    
    public void clear() {
        this.iFormatter = null;
        this.iElementPairs.clear();
    }
    
    public DateTimeFormatterBuilder append(final DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter == null) {
            throw new IllegalArgumentException("No formatter supplied");
        }
        return this.append0(dateTimeFormatter.getPrinter(), dateTimeFormatter.getParser());
    }
    
    public DateTimeFormatterBuilder append(final DateTimePrinter dateTimePrinter) {
        this.checkPrinter(dateTimePrinter);
        return this.append0(dateTimePrinter, null);
    }
    
    public DateTimeFormatterBuilder append(final DateTimeParser dateTimeParser) {
        this.checkParser(dateTimeParser);
        return this.append0(null, dateTimeParser);
    }
    
    public DateTimeFormatterBuilder append(final DateTimePrinter dateTimePrinter, final DateTimeParser dateTimeParser) {
        this.checkPrinter(dateTimePrinter);
        this.checkParser(dateTimeParser);
        return this.append0(dateTimePrinter, dateTimeParser);
    }
    
    public DateTimeFormatterBuilder append(final DateTimePrinter dateTimePrinter, final DateTimeParser[] array) {
        if (dateTimePrinter != null) {
            this.checkPrinter(dateTimePrinter);
        }
        if (array == null) {
            throw new IllegalArgumentException("No parsers supplied");
        }
        final int length = array.length;
        if (length != 1) {
            final DateTimeParser[] array2 = new DateTimeParser[length];
            int i;
            for (i = 0; i < length - 1; ++i) {
                if ((array2[i] = array[i]) == null) {
                    throw new IllegalArgumentException("Incomplete parser array");
                }
            }
            array2[i] = array[i];
            return this.append0(dateTimePrinter, new MatchingParser(array2));
        }
        if (array[0] == null) {
            throw new IllegalArgumentException("No parser supplied");
        }
        return this.append0(dateTimePrinter, array[0]);
    }
    
    public DateTimeFormatterBuilder appendOptional(final DateTimeParser dateTimeParser) {
        this.checkParser(dateTimeParser);
        return this.append0(null, new MatchingParser(new DateTimeParser[] { dateTimeParser, null }));
    }
    
    private void checkParser(final DateTimeParser dateTimeParser) {
        if (dateTimeParser == null) {
            throw new IllegalArgumentException("No parser supplied");
        }
    }
    
    private void checkPrinter(final DateTimePrinter dateTimePrinter) {
        if (dateTimePrinter == null) {
            throw new IllegalArgumentException("No printer supplied");
        }
    }
    
    private DateTimeFormatterBuilder append0(final Object o) {
        this.iFormatter = null;
        this.iElementPairs.add(o);
        this.iElementPairs.add(o);
        return this;
    }
    
    private DateTimeFormatterBuilder append0(final DateTimePrinter dateTimePrinter, final DateTimeParser dateTimeParser) {
        this.iFormatter = null;
        this.iElementPairs.add(dateTimePrinter);
        this.iElementPairs.add(dateTimeParser);
        return this;
    }
    
    public DateTimeFormatterBuilder appendLiteral(final char c) {
        return this.append0(new CharacterLiteral(c));
    }
    
    public DateTimeFormatterBuilder appendLiteral(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("Literal must not be null");
        }
        switch (s.length()) {
            case 0: {
                return this;
            }
            case 1: {
                return this.append0(new CharacterLiteral(s.charAt(0)));
            }
            default: {
                return this.append0(new StringLiteral(s));
            }
        }
    }
    
    public DateTimeFormatterBuilder appendDecimal(final DateTimeFieldType dateTimeFieldType, final int n, int n2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (n2 < n) {
            n2 = n;
        }
        if (n < 0 || n2 <= 0) {
            throw new IllegalArgumentException();
        }
        if (n <= 1) {
            return this.append0(new UnpaddedNumber(dateTimeFieldType, n2, false));
        }
        return this.append0(new PaddedNumber(dateTimeFieldType, n2, false, n));
    }
    
    public DateTimeFormatterBuilder appendFixedDecimal(final DateTimeFieldType dateTimeFieldType, final int n) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal number of digits: " + n);
        }
        return this.append0(new FixedNumber(dateTimeFieldType, n, false));
    }
    
    public DateTimeFormatterBuilder appendSignedDecimal(final DateTimeFieldType dateTimeFieldType, final int n, int n2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (n2 < n) {
            n2 = n;
        }
        if (n < 0 || n2 <= 0) {
            throw new IllegalArgumentException();
        }
        if (n <= 1) {
            return this.append0(new UnpaddedNumber(dateTimeFieldType, n2, true));
        }
        return this.append0(new PaddedNumber(dateTimeFieldType, n2, true, n));
    }
    
    public DateTimeFormatterBuilder appendFixedSignedDecimal(final DateTimeFieldType dateTimeFieldType, final int n) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal number of digits: " + n);
        }
        return this.append0(new FixedNumber(dateTimeFieldType, n, true));
    }
    
    public DateTimeFormatterBuilder appendText(final DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        return this.append0(new TextField(dateTimeFieldType, false));
    }
    
    public DateTimeFormatterBuilder appendShortText(final DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        return this.append0(new TextField(dateTimeFieldType, true));
    }
    
    public DateTimeFormatterBuilder appendFraction(final DateTimeFieldType dateTimeFieldType, final int n, int n2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (n2 < n) {
            n2 = n;
        }
        if (n < 0 || n2 <= 0) {
            throw new IllegalArgumentException();
        }
        return this.append0(new Fraction(dateTimeFieldType, n, n2));
    }
    
    public DateTimeFormatterBuilder appendFractionOfSecond(final int n, final int n2) {
        return this.appendFraction(DateTimeFieldType.secondOfDay(), n, n2);
    }
    
    public DateTimeFormatterBuilder appendFractionOfMinute(final int n, final int n2) {
        return this.appendFraction(DateTimeFieldType.minuteOfDay(), n, n2);
    }
    
    public DateTimeFormatterBuilder appendFractionOfHour(final int n, final int n2) {
        return this.appendFraction(DateTimeFieldType.hourOfDay(), n, n2);
    }
    
    public DateTimeFormatterBuilder appendFractionOfDay(final int n, final int n2) {
        return this.appendFraction(DateTimeFieldType.dayOfYear(), n, n2);
    }
    
    public DateTimeFormatterBuilder appendMillisOfSecond(final int n) {
        return this.appendDecimal(DateTimeFieldType.millisOfSecond(), n, 3);
    }
    
    public DateTimeFormatterBuilder appendMillisOfDay(final int n) {
        return this.appendDecimal(DateTimeFieldType.millisOfDay(), n, 8);
    }
    
    public DateTimeFormatterBuilder appendSecondOfMinute(final int n) {
        return this.appendDecimal(DateTimeFieldType.secondOfMinute(), n, 2);
    }
    
    public DateTimeFormatterBuilder appendSecondOfDay(final int n) {
        return this.appendDecimal(DateTimeFieldType.secondOfDay(), n, 5);
    }
    
    public DateTimeFormatterBuilder appendMinuteOfHour(final int n) {
        return this.appendDecimal(DateTimeFieldType.minuteOfHour(), n, 2);
    }
    
    public DateTimeFormatterBuilder appendMinuteOfDay(final int n) {
        return this.appendDecimal(DateTimeFieldType.minuteOfDay(), n, 4);
    }
    
    public DateTimeFormatterBuilder appendHourOfDay(final int n) {
        return this.appendDecimal(DateTimeFieldType.hourOfDay(), n, 2);
    }
    
    public DateTimeFormatterBuilder appendClockhourOfDay(final int n) {
        return this.appendDecimal(DateTimeFieldType.clockhourOfDay(), n, 2);
    }
    
    public DateTimeFormatterBuilder appendHourOfHalfday(final int n) {
        return this.appendDecimal(DateTimeFieldType.hourOfHalfday(), n, 2);
    }
    
    public DateTimeFormatterBuilder appendClockhourOfHalfday(final int n) {
        return this.appendDecimal(DateTimeFieldType.clockhourOfHalfday(), n, 2);
    }
    
    public DateTimeFormatterBuilder appendDayOfWeek(final int n) {
        return this.appendDecimal(DateTimeFieldType.dayOfWeek(), n, 1);
    }
    
    public DateTimeFormatterBuilder appendDayOfMonth(final int n) {
        return this.appendDecimal(DateTimeFieldType.dayOfMonth(), n, 2);
    }
    
    public DateTimeFormatterBuilder appendDayOfYear(final int n) {
        return this.appendDecimal(DateTimeFieldType.dayOfYear(), n, 3);
    }
    
    public DateTimeFormatterBuilder appendWeekOfWeekyear(final int n) {
        return this.appendDecimal(DateTimeFieldType.weekOfWeekyear(), n, 2);
    }
    
    public DateTimeFormatterBuilder appendWeekyear(final int n, final int n2) {
        return this.appendSignedDecimal(DateTimeFieldType.weekyear(), n, n2);
    }
    
    public DateTimeFormatterBuilder appendMonthOfYear(final int n) {
        return this.appendDecimal(DateTimeFieldType.monthOfYear(), n, 2);
    }
    
    public DateTimeFormatterBuilder appendYear(final int n, final int n2) {
        return this.appendSignedDecimal(DateTimeFieldType.year(), n, n2);
    }
    
    public DateTimeFormatterBuilder appendTwoDigitYear(final int n) {
        return this.appendTwoDigitYear(n, false);
    }
    
    public DateTimeFormatterBuilder appendTwoDigitYear(final int n, final boolean b) {
        return this.append0(new TwoDigitYear(DateTimeFieldType.year(), n, b));
    }
    
    public DateTimeFormatterBuilder appendTwoDigitWeekyear(final int n) {
        return this.appendTwoDigitWeekyear(n, false);
    }
    
    public DateTimeFormatterBuilder appendTwoDigitWeekyear(final int n, final boolean b) {
        return this.append0(new TwoDigitYear(DateTimeFieldType.weekyear(), n, b));
    }
    
    public DateTimeFormatterBuilder appendYearOfEra(final int n, final int n2) {
        return this.appendDecimal(DateTimeFieldType.yearOfEra(), n, n2);
    }
    
    public DateTimeFormatterBuilder appendYearOfCentury(final int n, final int n2) {
        return this.appendDecimal(DateTimeFieldType.yearOfCentury(), n, n2);
    }
    
    public DateTimeFormatterBuilder appendCenturyOfEra(final int n, final int n2) {
        return this.appendSignedDecimal(DateTimeFieldType.centuryOfEra(), n, n2);
    }
    
    public DateTimeFormatterBuilder appendHalfdayOfDayText() {
        return this.appendText(DateTimeFieldType.halfdayOfDay());
    }
    
    public DateTimeFormatterBuilder appendDayOfWeekText() {
        return this.appendText(DateTimeFieldType.dayOfWeek());
    }
    
    public DateTimeFormatterBuilder appendDayOfWeekShortText() {
        return this.appendShortText(DateTimeFieldType.dayOfWeek());
    }
    
    public DateTimeFormatterBuilder appendMonthOfYearText() {
        return this.appendText(DateTimeFieldType.monthOfYear());
    }
    
    public DateTimeFormatterBuilder appendMonthOfYearShortText() {
        return this.appendShortText(DateTimeFieldType.monthOfYear());
    }
    
    public DateTimeFormatterBuilder appendEraText() {
        return this.appendText(DateTimeFieldType.era());
    }
    
    public DateTimeFormatterBuilder appendTimeZoneName() {
        return this.append0(new TimeZoneName(0), null);
    }
    
    public DateTimeFormatterBuilder appendTimeZoneShortName() {
        return this.append0(new TimeZoneName(1), null);
    }
    
    public DateTimeFormatterBuilder appendTimeZoneId() {
        return this.append0(new TimeZoneName(2), null);
    }
    
    public DateTimeFormatterBuilder appendTimeZoneOffset(final String s, final boolean b, final int n, final int n2) {
        return this.append0(new TimeZoneOffset(s, b, n, n2));
    }
    
    public DateTimeFormatterBuilder appendPattern(final String s) {
        DateTimeFormat.appendPatternTo(this, s);
        return this;
    }
    
    private Object getFormatter() {
        Object iFormatter = this.iFormatter;
        if (iFormatter == null) {
            if (this.iElementPairs.size() == 2) {
                final Object value = this.iElementPairs.get(0);
                final Object value2 = this.iElementPairs.get(1);
                if (value != null) {
                    if (value == value2 || value2 == null) {
                        iFormatter = value;
                    }
                }
                else {
                    iFormatter = value2;
                }
            }
            if (iFormatter == null) {
                iFormatter = new Composite(this.iElementPairs);
            }
            this.iFormatter = iFormatter;
        }
        return iFormatter;
    }
    
    private boolean isPrinter(final Object o) {
        return o instanceof DateTimePrinter && (!(o instanceof Composite) || ((Composite)o).isPrinter());
    }
    
    private boolean isParser(final Object o) {
        return o instanceof DateTimeParser && (!(o instanceof Composite) || ((Composite)o).isParser());
    }
    
    private boolean isFormatter(final Object o) {
        return this.isPrinter(o) || this.isParser(o);
    }
    
    static void appendUnknownString(final StringBuffer sb, int n) {
        while (--n >= 0) {
            sb.append('\ufffd');
        }
    }
    
    static void printUnknownString(final Writer writer, int n) throws IOException {
        while (--n >= 0) {
            writer.write(65533);
        }
    }
    
    static class CharacterLiteral implements DateTimePrinter, DateTimeParser
    {
        private final char iValue;
        
        CharacterLiteral(final char iValue) {
            this.iValue = iValue;
        }
        
        public int estimatePrintedLength() {
            return 1;
        }
        
        public void printTo(final StringBuffer sb, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) {
            sb.append(this.iValue);
        }
        
        public void printTo(final Writer writer, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) throws IOException {
            writer.write(this.iValue);
        }
        
        public void printTo(final StringBuffer sb, final ReadablePartial readablePartial, final Locale locale) {
            sb.append(this.iValue);
        }
        
        public void printTo(final Writer writer, final ReadablePartial readablePartial, final Locale locale) throws IOException {
            writer.write(this.iValue);
        }
        
        public int estimateParsedLength() {
            return 1;
        }
        
        public int parseInto(final DateTimeParserBucket dateTimeParserBucket, final String s, final int n) {
            if (n >= s.length()) {
                return ~n;
            }
            final char char1 = s.charAt(n);
            final char iValue = this.iValue;
            if (char1 != iValue) {
                final char upperCase = Character.toUpperCase(char1);
                final char upperCase2 = Character.toUpperCase(iValue);
                if (upperCase != upperCase2 && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                    return ~n;
                }
            }
            return n + 1;
        }
    }
    
    static class StringLiteral implements DateTimePrinter, DateTimeParser
    {
        private final String iValue;
        
        StringLiteral(final String iValue) {
            this.iValue = iValue;
        }
        
        public int estimatePrintedLength() {
            return this.iValue.length();
        }
        
        public void printTo(final StringBuffer sb, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) {
            sb.append(this.iValue);
        }
        
        public void printTo(final Writer writer, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) throws IOException {
            writer.write(this.iValue);
        }
        
        public void printTo(final StringBuffer sb, final ReadablePartial readablePartial, final Locale locale) {
            sb.append(this.iValue);
        }
        
        public void printTo(final Writer writer, final ReadablePartial readablePartial, final Locale locale) throws IOException {
            writer.write(this.iValue);
        }
        
        public int estimateParsedLength() {
            return this.iValue.length();
        }
        
        public int parseInto(final DateTimeParserBucket dateTimeParserBucket, final String s, final int n) {
            if (s.regionMatches(true, n, this.iValue, 0, this.iValue.length())) {
                return n + this.iValue.length();
            }
            return ~n;
        }
    }
    
    abstract static class NumberFormatter implements DateTimePrinter, DateTimeParser
    {
        protected final DateTimeFieldType iFieldType;
        protected final int iMaxParsedDigits;
        protected final boolean iSigned;
        
        NumberFormatter(final DateTimeFieldType iFieldType, final int iMaxParsedDigits, final boolean iSigned) {
            this.iFieldType = iFieldType;
            this.iMaxParsedDigits = iMaxParsedDigits;
            this.iSigned = iSigned;
        }
        
        public int estimateParsedLength() {
            return this.iMaxParsedDigits;
        }
        
        public int parseInto(final DateTimeParserBucket dateTimeParserBucket, final String s, int n) {
            int n2 = Math.min(this.iMaxParsedDigits, s.length() - n);
            boolean b = false;
            int i = 0;
            while (i < n2) {
                final char char1 = s.charAt(n + i);
                if (i == 0 && (char1 == '-' || char1 == '+') && this.iSigned) {
                    b = (char1 == '-');
                    final char char2;
                    if (i + 1 >= n2 || (char2 = s.charAt(n + i + 1)) < '0') {
                        break;
                    }
                    if (char2 > '9') {
                        break;
                    }
                    if (b) {
                        ++i;
                    }
                    else {
                        ++n;
                    }
                    n2 = Math.min(n2 + 1, s.length() - n);
                }
                else {
                    if (char1 < '0') {
                        break;
                    }
                    if (char1 > '9') {
                        break;
                    }
                    ++i;
                }
            }
            if (i == 0) {
                return ~n;
            }
            int int1;
            if (i >= 9) {
                int1 = Integer.parseInt(s.substring(n, n += i));
            }
            else {
                int j = n;
                if (b) {
                    ++j;
                }
                try {
                    int1 = s.charAt(j++) - '0';
                }
                catch (StringIndexOutOfBoundsException ex) {
                    return ~n;
                }
                for (n += i; j < n; int1 = (int1 << 3) + (int1 << 1) + s.charAt(j++) - 48) {}
                if (b) {
                    int1 = -int1;
                }
            }
            dateTimeParserBucket.saveField(this.iFieldType, int1);
            return n;
        }
    }
    
    static class UnpaddedNumber extends NumberFormatter
    {
        protected UnpaddedNumber(final DateTimeFieldType dateTimeFieldType, final int n, final boolean b) {
            super(dateTimeFieldType, n, b);
        }
        
        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }
        
        public void printTo(final StringBuffer sb, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) {
            try {
                FormatUtils.appendUnpaddedInteger(sb, this.iFieldType.getField(chronology).get(n));
            }
            catch (RuntimeException ex) {
                sb.append('\ufffd');
            }
        }
        
        public void printTo(final Writer writer, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) throws IOException {
            try {
                FormatUtils.writeUnpaddedInteger(writer, this.iFieldType.getField(chronology).get(n));
            }
            catch (RuntimeException ex) {
                writer.write(65533);
            }
        }
        
        public void printTo(final StringBuffer sb, final ReadablePartial readablePartial, final Locale locale) {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.appendUnpaddedInteger(sb, readablePartial.get(this.iFieldType));
                }
                catch (RuntimeException ex) {
                    sb.append('\ufffd');
                }
            }
            else {
                sb.append('\ufffd');
            }
        }
        
        public void printTo(final Writer writer, final ReadablePartial readablePartial, final Locale locale) throws IOException {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.writeUnpaddedInteger(writer, readablePartial.get(this.iFieldType));
                }
                catch (RuntimeException ex) {
                    writer.write(65533);
                }
            }
            else {
                writer.write(65533);
            }
        }
    }
    
    static class PaddedNumber extends NumberFormatter
    {
        protected final int iMinPrintedDigits;
        
        protected PaddedNumber(final DateTimeFieldType dateTimeFieldType, final int n, final boolean b, final int iMinPrintedDigits) {
            super(dateTimeFieldType, n, b);
            this.iMinPrintedDigits = iMinPrintedDigits;
        }
        
        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }
        
        public void printTo(final StringBuffer sb, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) {
            try {
                FormatUtils.appendPaddedInteger(sb, this.iFieldType.getField(chronology).get(n), this.iMinPrintedDigits);
            }
            catch (RuntimeException ex) {
                DateTimeFormatterBuilder.appendUnknownString(sb, this.iMinPrintedDigits);
            }
        }
        
        public void printTo(final Writer writer, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) throws IOException {
            try {
                FormatUtils.writePaddedInteger(writer, this.iFieldType.getField(chronology).get(n), this.iMinPrintedDigits);
            }
            catch (RuntimeException ex) {
                DateTimeFormatterBuilder.printUnknownString(writer, this.iMinPrintedDigits);
            }
        }
        
        public void printTo(final StringBuffer sb, final ReadablePartial readablePartial, final Locale locale) {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.appendPaddedInteger(sb, readablePartial.get(this.iFieldType), this.iMinPrintedDigits);
                }
                catch (RuntimeException ex) {
                    DateTimeFormatterBuilder.appendUnknownString(sb, this.iMinPrintedDigits);
                }
            }
            else {
                DateTimeFormatterBuilder.appendUnknownString(sb, this.iMinPrintedDigits);
            }
        }
        
        public void printTo(final Writer writer, final ReadablePartial readablePartial, final Locale locale) throws IOException {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.writePaddedInteger(writer, readablePartial.get(this.iFieldType), this.iMinPrintedDigits);
                }
                catch (RuntimeException ex) {
                    DateTimeFormatterBuilder.printUnknownString(writer, this.iMinPrintedDigits);
                }
            }
            else {
                DateTimeFormatterBuilder.printUnknownString(writer, this.iMinPrintedDigits);
            }
        }
    }
    
    static class FixedNumber extends PaddedNumber
    {
        protected FixedNumber(final DateTimeFieldType dateTimeFieldType, final int n, final boolean b) {
            super(dateTimeFieldType, n, b, n);
        }
        
        public int parseInto(final DateTimeParserBucket dateTimeParserBucket, final String s, final int n) {
            final int into = super.parseInto(dateTimeParserBucket, s, n);
            if (into < 0) {
                return into;
            }
            int n2 = n + this.iMaxParsedDigits;
            if (into != n2) {
                if (this.iSigned) {
                    final char char1 = s.charAt(n);
                    if (char1 == '-' || char1 == '+') {
                        ++n2;
                    }
                }
                if (into > n2) {
                    return ~(n2 + 1);
                }
                if (into < n2) {
                    return ~into;
                }
            }
            return into;
        }
    }
    
    static class TwoDigitYear implements DateTimePrinter, DateTimeParser
    {
        private final DateTimeFieldType iType;
        private final int iPivot;
        private final boolean iLenientParse;
        
        TwoDigitYear(final DateTimeFieldType iType, final int iPivot, final boolean iLenientParse) {
            this.iType = iType;
            this.iPivot = iPivot;
            this.iLenientParse = iLenientParse;
        }
        
        public int estimateParsedLength() {
            return this.iLenientParse ? 4 : 2;
        }
        
        public int parseInto(final DateTimeParserBucket dateTimeParserBucket, final String s, int n) {
            int n2 = s.length() - n;
            if (!this.iLenientParse) {
                if (Math.min(2, n2) < 2) {
                    return ~n;
                }
            }
            else {
                boolean b = false;
                boolean b2 = false;
                int i = 0;
                while (i < n2) {
                    final char char1 = s.charAt(n + i);
                    if (i == 0 && (char1 == '-' || char1 == '+')) {
                        b = true;
                        b2 = (char1 == '-');
                        if (b2) {
                            ++i;
                        }
                        else {
                            ++n;
                            --n2;
                        }
                    }
                    else {
                        if (char1 < '0') {
                            break;
                        }
                        if (char1 > '9') {
                            break;
                        }
                        ++i;
                    }
                }
                if (i == 0) {
                    return ~n;
                }
                if (b || i != 2) {
                    int int1;
                    if (i >= 9) {
                        int1 = Integer.parseInt(s.substring(n, n += i));
                    }
                    else {
                        int j = n;
                        if (b2) {
                            ++j;
                        }
                        try {
                            int1 = s.charAt(j++) - '0';
                        }
                        catch (StringIndexOutOfBoundsException ex) {
                            return ~n;
                        }
                        for (n += i; j < n; int1 = (int1 << 3) + (int1 << 1) + s.charAt(j++) - 48) {}
                        if (b2) {
                            int1 = -int1;
                        }
                    }
                    dateTimeParserBucket.saveField(this.iType, int1);
                    return n;
                }
            }
            final char char2 = s.charAt(n);
            if (char2 < '0' || char2 > '9') {
                return ~n;
            }
            final char c = (char)(char2 - '0');
            final char char3 = s.charAt(n + 1);
            if (char3 < '0' || char3 > '9') {
                return ~n;
            }
            final char c2 = (char)((c << 3) + (c << 1) + char3 - '0');
            int n3 = this.iPivot;
            if (dateTimeParserBucket.getPivotYear() != null) {
                n3 = dateTimeParserBucket.getPivotYear();
            }
            final int n4 = n3 - 50;
            int n5;
            if (n4 >= 0) {
                n5 = n4 % 100;
            }
            else {
                n5 = 99 + (n4 + 1) % 100;
            }
            dateTimeParserBucket.saveField(this.iType, c2 + (n4 + ((c2 < n5) ? 100 : 0) - n5));
            return n + 2;
        }
        
        public int estimatePrintedLength() {
            return 2;
        }
        
        public void printTo(final StringBuffer sb, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) {
            final int twoDigitYear = this.getTwoDigitYear(n, chronology);
            if (twoDigitYear < 0) {
                sb.append('\ufffd');
                sb.append('\ufffd');
            }
            else {
                FormatUtils.appendPaddedInteger(sb, twoDigitYear, 2);
            }
        }
        
        public void printTo(final Writer writer, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) throws IOException {
            final int twoDigitYear = this.getTwoDigitYear(n, chronology);
            if (twoDigitYear < 0) {
                writer.write(65533);
                writer.write(65533);
            }
            else {
                FormatUtils.writePaddedInteger(writer, twoDigitYear, 2);
            }
        }
        
        private int getTwoDigitYear(final long n, final Chronology chronology) {
            try {
                int value = this.iType.getField(chronology).get(n);
                if (value < 0) {
                    value = -value;
                }
                return value % 100;
            }
            catch (RuntimeException ex) {
                return -1;
            }
        }
        
        public void printTo(final StringBuffer sb, final ReadablePartial readablePartial, final Locale locale) {
            final int twoDigitYear = this.getTwoDigitYear(readablePartial);
            if (twoDigitYear < 0) {
                sb.append('\ufffd');
                sb.append('\ufffd');
            }
            else {
                FormatUtils.appendPaddedInteger(sb, twoDigitYear, 2);
            }
        }
        
        public void printTo(final Writer writer, final ReadablePartial readablePartial, final Locale locale) throws IOException {
            final int twoDigitYear = this.getTwoDigitYear(readablePartial);
            if (twoDigitYear < 0) {
                writer.write(65533);
                writer.write(65533);
            }
            else {
                FormatUtils.writePaddedInteger(writer, twoDigitYear, 2);
            }
        }
        
        private int getTwoDigitYear(final ReadablePartial readablePartial) {
            if (readablePartial.isSupported(this.iType)) {
                try {
                    int value = readablePartial.get(this.iType);
                    if (value < 0) {
                        value = -value;
                    }
                    return value % 100;
                }
                catch (RuntimeException ex) {}
            }
            return -1;
        }
    }
    
    static class TextField implements DateTimePrinter, DateTimeParser
    {
        private static Map cParseCache;
        private final DateTimeFieldType iFieldType;
        private final boolean iShort;
        
        TextField(final DateTimeFieldType iFieldType, final boolean iShort) {
            this.iFieldType = iFieldType;
            this.iShort = iShort;
        }
        
        public int estimatePrintedLength() {
            return this.iShort ? 6 : 20;
        }
        
        public void printTo(final StringBuffer sb, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) {
            try {
                sb.append(this.print(n, chronology, locale));
            }
            catch (RuntimeException ex) {
                sb.append('\ufffd');
            }
        }
        
        public void printTo(final Writer writer, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) throws IOException {
            try {
                writer.write(this.print(n, chronology, locale));
            }
            catch (RuntimeException ex) {
                writer.write(65533);
            }
        }
        
        public void printTo(final StringBuffer sb, final ReadablePartial readablePartial, final Locale locale) {
            try {
                sb.append(this.print(readablePartial, locale));
            }
            catch (RuntimeException ex) {
                sb.append('\ufffd');
            }
        }
        
        public void printTo(final Writer writer, final ReadablePartial readablePartial, final Locale locale) throws IOException {
            try {
                writer.write(this.print(readablePartial, locale));
            }
            catch (RuntimeException ex) {
                writer.write(65533);
            }
        }
        
        private String print(final long n, final Chronology chronology, final Locale locale) {
            final DateTimeField field = this.iFieldType.getField(chronology);
            if (this.iShort) {
                return field.getAsShortText(n, locale);
            }
            return field.getAsText(n, locale);
        }
        
        private String print(final ReadablePartial readablePartial, final Locale locale) {
            if (!readablePartial.isSupported(this.iFieldType)) {
                return "\ufffd";
            }
            final DateTimeField field = this.iFieldType.getField(readablePartial.getChronology());
            if (this.iShort) {
                return field.getAsShortText(readablePartial, locale);
            }
            return field.getAsText(readablePartial, locale);
        }
        
        public int estimateParsedLength() {
            return this.estimatePrintedLength();
        }
        
        public int parseInto(final DateTimeParserBucket dateTimeParserBucket, final String s, final int n) {
            final Locale locale = dateTimeParserBucket.getLocale();
            Set set = null;
            int n2 = 0;
            synchronized (TextField.cParseCache) {
                Object o = TextField.cParseCache.get(locale);
                if (o == null) {
                    o = new HashMap<Locale, Object[]>();
                    TextField.cParseCache.put(locale, o);
                }
                final Object[] array = ((Map<Locale, Object[]>)o).get(this.iFieldType);
                if (array == null) {
                    set = new HashSet<String>(32);
                    final MutableDateTime.Property property = new MutableDateTime(0L, DateTimeZone.UTC).property(this.iFieldType);
                    final int minimumValueOverall = property.getMinimumValueOverall();
                    final int maximumValueOverall = property.getMaximumValueOverall();
                    if (maximumValueOverall - minimumValueOverall > 32) {
                        return ~n;
                    }
                    n2 = property.getMaximumTextLength(locale);
                    for (int i = minimumValueOverall; i <= maximumValueOverall; ++i) {
                        property.set(i);
                        set.add(property.getAsShortText(locale));
                        set.add(property.getAsShortText(locale).toLowerCase(locale));
                        set.add(property.getAsShortText(locale).toUpperCase(locale));
                        set.add(property.getAsText(locale));
                        set.add(property.getAsText(locale).toLowerCase(locale));
                        set.add(property.getAsText(locale).toUpperCase(locale));
                    }
                    if ("en".equals(locale.getLanguage()) && this.iFieldType == DateTimeFieldType.era()) {
                        set.add("BCE");
                        set.add("bce");
                        set.add("CE");
                        set.add("ce");
                        n2 = 3;
                    }
                    ((Map<Locale, Object[]>)o).put((Locale)this.iFieldType, new Object[] { set, new Integer(n2) });
                }
                else {
                    set = (Set)array[0];
                    n2 = (int)array[1];
                }
            }
            for (int j = Math.min(s.length(), n + n2); j > n; --j) {
                final String substring = s.substring(n, j);
                if (set.contains(substring)) {
                    dateTimeParserBucket.saveField(this.iFieldType, substring, locale);
                    return j;
                }
            }
            return ~n;
        }
        
        static {
            TextField.cParseCache = new HashMap();
        }
    }
    
    static class Fraction implements DateTimePrinter, DateTimeParser
    {
        private final DateTimeFieldType iFieldType;
        protected int iMinDigits;
        protected int iMaxDigits;
        
        protected Fraction(final DateTimeFieldType iFieldType, final int iMinDigits, int iMaxDigits) {
            this.iFieldType = iFieldType;
            if (iMaxDigits > 18) {
                iMaxDigits = 18;
            }
            this.iMinDigits = iMinDigits;
            this.iMaxDigits = iMaxDigits;
        }
        
        public int estimatePrintedLength() {
            return this.iMaxDigits;
        }
        
        public void printTo(final StringBuffer sb, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) {
            try {
                this.printTo(sb, null, n, chronology);
            }
            catch (IOException ex) {}
        }
        
        public void printTo(final Writer writer, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) throws IOException {
            this.printTo(null, writer, n, chronology);
        }
        
        public void printTo(final StringBuffer sb, final ReadablePartial readablePartial, final Locale locale) {
            final long set = readablePartial.getChronology().set(readablePartial, 0L);
            try {
                this.printTo(sb, null, set, readablePartial.getChronology());
            }
            catch (IOException ex) {}
        }
        
        public void printTo(final Writer writer, final ReadablePartial readablePartial, final Locale locale) throws IOException {
            this.printTo(null, writer, readablePartial.getChronology().set(readablePartial, 0L), readablePartial.getChronology());
        }
        
        protected void printTo(final StringBuffer sb, final Writer writer, final long n, final Chronology chronology) throws IOException {
            final DateTimeField field = this.iFieldType.getField(chronology);
            int iMinDigits = this.iMinDigits;
            long remainder;
            try {
                remainder = field.remainder(n);
            }
            catch (RuntimeException ex) {
                if (sb != null) {
                    DateTimeFormatterBuilder.appendUnknownString(sb, iMinDigits);
                }
                else {
                    DateTimeFormatterBuilder.printUnknownString(writer, iMinDigits);
                }
                return;
            }
            if (remainder == 0L) {
                if (sb != null) {
                    while (--iMinDigits >= 0) {
                        sb.append('0');
                    }
                }
                else {
                    while (--iMinDigits >= 0) {
                        writer.write(48);
                    }
                }
                return;
            }
            final long[] fractionData = this.getFractionData(remainder, field);
            final long n2 = fractionData[0];
            final int n3 = (int)fractionData[1];
            String s;
            if ((n2 & 0x7FFFFFFFL) == n2) {
                s = Integer.toString((int)n2);
            }
            else {
                s = Long.toString(n2);
            }
            int i;
            int n4;
            for (i = s.length(), n4 = n3; i < n4; --n4) {
                if (sb != null) {
                    sb.append('0');
                }
                else {
                    writer.write(48);
                }
                --iMinDigits;
            }
            if (iMinDigits < n4) {
                while (iMinDigits < n4 && i > 1 && s.charAt(i - 1) == '0') {
                    --n4;
                    --i;
                }
                if (i < s.length()) {
                    if (sb != null) {
                        for (int j = 0; j < i; ++j) {
                            sb.append(s.charAt(j));
                        }
                    }
                    else {
                        for (int k = 0; k < i; ++k) {
                            writer.write(s.charAt(k));
                        }
                    }
                    return;
                }
            }
            if (sb != null) {
                sb.append(s);
            }
            else {
                writer.write(s);
            }
        }
        
        private long[] getFractionData(final long n, final DateTimeField dateTimeField) {
            final long unitMillis = dateTimeField.getDurationField().getUnitMillis();
            int iMaxDigits = this.iMaxDigits;
            long n2 = 0L;
            while (true) {
                switch (iMaxDigits) {
                    default: {
                        n2 = 1L;
                        break;
                    }
                    case 1: {
                        n2 = 10L;
                        break;
                    }
                    case 2: {
                        n2 = 100L;
                        break;
                    }
                    case 3: {
                        n2 = 1000L;
                        break;
                    }
                    case 4: {
                        n2 = 10000L;
                        break;
                    }
                    case 5: {
                        n2 = 100000L;
                        break;
                    }
                    case 6: {
                        n2 = 1000000L;
                        break;
                    }
                    case 7: {
                        n2 = 10000000L;
                        break;
                    }
                    case 8: {
                        n2 = 100000000L;
                        break;
                    }
                    case 9: {
                        n2 = 1000000000L;
                        break;
                    }
                    case 10: {
                        n2 = 10000000000L;
                        break;
                    }
                    case 11: {
                        n2 = 100000000000L;
                        break;
                    }
                    case 12: {
                        n2 = 1000000000000L;
                        break;
                    }
                    case 13: {
                        n2 = 10000000000000L;
                        break;
                    }
                    case 14: {
                        n2 = 100000000000000L;
                        break;
                    }
                    case 15: {
                        n2 = 1000000000000000L;
                        break;
                    }
                    case 16: {
                        n2 = 10000000000000000L;
                        break;
                    }
                    case 17: {
                        n2 = 100000000000000000L;
                        break;
                    }
                    case 18: {
                        n2 = 1000000000000000000L;
                        break;
                    }
                }
                if (unitMillis * n2 / n2 == unitMillis) {
                    break;
                }
                --iMaxDigits;
            }
            return new long[] { n * n2 / unitMillis, iMaxDigits };
        }
        
        public int estimateParsedLength() {
            return this.iMaxDigits;
        }
        
        public int parseInto(final DateTimeParserBucket dateTimeParserBucket, final String s, final int n) {
            final DateTimeField field = this.iFieldType.getField(dateTimeParserBucket.getChronology());
            int min;
            long n2;
            long n3;
            int i;
            char char1;
            long n4;
            for (min = Math.min(this.iMaxDigits, s.length() - n), n2 = 0L, n3 = field.getDurationField().getUnitMillis() * 10L, i = 0; i < min; ++i, n4 = n3 / 10L, n2 += (char1 - '0') * n4, n3 = n4) {
                char1 = s.charAt(n + i);
                if (char1 < '0') {
                    break;
                }
                if (char1 > '9') {
                    break;
                }
            }
            final long n5 = n2 / 10L;
            if (i == 0) {
                return ~n;
            }
            if (n5 > 2147483647L) {
                return ~n;
            }
            dateTimeParserBucket.saveField(new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), MillisDurationField.INSTANCE, field.getDurationField()), (int)n5);
            return n + i;
        }
    }
    
    static class TimeZoneOffset implements DateTimePrinter, DateTimeParser
    {
        private final String iZeroOffsetText;
        private final boolean iShowSeparators;
        private final int iMinFields;
        private final int iMaxFields;
        
        TimeZoneOffset(final String iZeroOffsetText, final boolean iShowSeparators, int iMinFields, int iMaxFields) {
            this.iZeroOffsetText = iZeroOffsetText;
            this.iShowSeparators = iShowSeparators;
            if (iMinFields <= 0 || iMaxFields < iMinFields) {
                throw new IllegalArgumentException();
            }
            if (iMinFields > 4) {
                iMinFields = 4;
                iMaxFields = 4;
            }
            this.iMinFields = iMinFields;
            this.iMaxFields = iMaxFields;
        }
        
        public int estimatePrintedLength() {
            int length = 1 + this.iMinFields << 1;
            if (this.iShowSeparators) {
                length += this.iMinFields - 1;
            }
            if (this.iZeroOffsetText != null && this.iZeroOffsetText.length() > length) {
                length = this.iZeroOffsetText.length();
            }
            return length;
        }
        
        public void printTo(final StringBuffer sb, final long n, final Chronology chronology, int n2, final DateTimeZone dateTimeZone, final Locale locale) {
            if (dateTimeZone == null) {
                return;
            }
            if (n2 == 0 && this.iZeroOffsetText != null) {
                sb.append(this.iZeroOffsetText);
                return;
            }
            if (n2 >= 0) {
                sb.append('+');
            }
            else {
                sb.append('-');
                n2 = -n2;
            }
            final int n3 = n2 / 3600000;
            FormatUtils.appendPaddedInteger(sb, n3, 2);
            if (this.iMaxFields == 1) {
                return;
            }
            n2 -= n3 * 3600000;
            if (n2 == 0 && this.iMinFields <= 1) {
                return;
            }
            final int n4 = n2 / 60000;
            if (this.iShowSeparators) {
                sb.append(':');
            }
            FormatUtils.appendPaddedInteger(sb, n4, 2);
            if (this.iMaxFields == 2) {
                return;
            }
            n2 -= n4 * 60000;
            if (n2 == 0 && this.iMinFields <= 2) {
                return;
            }
            final int n5 = n2 / 1000;
            if (this.iShowSeparators) {
                sb.append(':');
            }
            FormatUtils.appendPaddedInteger(sb, n5, 2);
            if (this.iMaxFields == 3) {
                return;
            }
            n2 -= n5 * 1000;
            if (n2 == 0 && this.iMinFields <= 3) {
                return;
            }
            if (this.iShowSeparators) {
                sb.append('.');
            }
            FormatUtils.appendPaddedInteger(sb, n2, 3);
        }
        
        public void printTo(final Writer writer, final long n, final Chronology chronology, int n2, final DateTimeZone dateTimeZone, final Locale locale) throws IOException {
            if (dateTimeZone == null) {
                return;
            }
            if (n2 == 0 && this.iZeroOffsetText != null) {
                writer.write(this.iZeroOffsetText);
                return;
            }
            if (n2 >= 0) {
                writer.write(43);
            }
            else {
                writer.write(45);
                n2 = -n2;
            }
            final int n3 = n2 / 3600000;
            FormatUtils.writePaddedInteger(writer, n3, 2);
            if (this.iMaxFields == 1) {
                return;
            }
            n2 -= n3 * 3600000;
            if (n2 == 0 && this.iMinFields == 1) {
                return;
            }
            final int n4 = n2 / 60000;
            if (this.iShowSeparators) {
                writer.write(58);
            }
            FormatUtils.writePaddedInteger(writer, n4, 2);
            if (this.iMaxFields == 2) {
                return;
            }
            n2 -= n4 * 60000;
            if (n2 == 0 && this.iMinFields == 2) {
                return;
            }
            final int n5 = n2 / 1000;
            if (this.iShowSeparators) {
                writer.write(58);
            }
            FormatUtils.writePaddedInteger(writer, n5, 2);
            if (this.iMaxFields == 3) {
                return;
            }
            n2 -= n5 * 1000;
            if (n2 == 0 && this.iMinFields == 3) {
                return;
            }
            if (this.iShowSeparators) {
                writer.write(46);
            }
            FormatUtils.writePaddedInteger(writer, n2, 3);
        }
        
        public void printTo(final StringBuffer sb, final ReadablePartial readablePartial, final Locale locale) {
        }
        
        public void printTo(final Writer writer, final ReadablePartial readablePartial, final Locale locale) throws IOException {
        }
        
        public int estimateParsedLength() {
            return this.estimatePrintedLength();
        }
        
        public int parseInto(final DateTimeParserBucket dateTimeParserBucket, final String s, int n) {
            int n2 = s.length() - n;
            Label_0097: {
                if (this.iZeroOffsetText != null) {
                    if (this.iZeroOffsetText.length() == 0) {
                        if (n2 > 0) {
                            final char char1 = s.charAt(n);
                            if (char1 == '-') {
                                break Label_0097;
                            }
                            if (char1 == '+') {
                                break Label_0097;
                            }
                        }
                        dateTimeParserBucket.setOffset(0);
                        return n;
                    }
                    if (s.regionMatches(true, n, this.iZeroOffsetText, 0, this.iZeroOffsetText.length())) {
                        dateTimeParserBucket.setOffset(0);
                        return n + this.iZeroOffsetText.length();
                    }
                }
            }
            if (n2 <= 1) {
                return ~n;
            }
            final char char2 = s.charAt(n);
            boolean b;
            if (char2 == '-') {
                b = true;
            }
            else {
                if (char2 != '+') {
                    return ~n;
                }
                b = false;
            }
            --n2;
            ++n;
            if (this.digitCount(s, n, 2) < 2) {
                return ~n;
            }
            final int twoDigits = FormatUtils.parseTwoDigits(s, n);
            if (twoDigits > 23) {
                return ~n;
            }
            int n3 = twoDigits * 3600000;
            n2 -= 2;
            n += 2;
            Label_0552: {
                if (n2 > 0) {
                    final char char3 = s.charAt(n);
                    boolean b2;
                    if (char3 == ':') {
                        b2 = true;
                        --n2;
                        ++n;
                    }
                    else {
                        if (char3 < '0' || char3 > '9') {
                            break Label_0552;
                        }
                        b2 = false;
                    }
                    final int digitCount = this.digitCount(s, n, 2);
                    if (digitCount != 0 || b2) {
                        if (digitCount < 2) {
                            return ~n;
                        }
                        final int twoDigits2 = FormatUtils.parseTwoDigits(s, n);
                        if (twoDigits2 > 59) {
                            return ~n;
                        }
                        n3 += twoDigits2 * 60000;
                        n2 -= 2;
                        n += 2;
                        if (n2 > 0) {
                            if (b2) {
                                if (s.charAt(n) != ':') {
                                    break Label_0552;
                                }
                                --n2;
                                ++n;
                            }
                            final int digitCount2 = this.digitCount(s, n, 2);
                            if (digitCount2 != 0 || b2) {
                                if (digitCount2 < 2) {
                                    return ~n;
                                }
                                final int twoDigits3 = FormatUtils.parseTwoDigits(s, n);
                                if (twoDigits3 > 59) {
                                    return ~n;
                                }
                                n3 += twoDigits3 * 1000;
                                n2 -= 2;
                                n += 2;
                                if (n2 > 0) {
                                    if (b2) {
                                        if (s.charAt(n) != '.' && s.charAt(n) != ',') {
                                            break Label_0552;
                                        }
                                        --n2;
                                        ++n;
                                    }
                                    final int digitCount3 = this.digitCount(s, n, 3);
                                    if (digitCount3 != 0 || b2) {
                                        if (digitCount3 < 1) {
                                            return ~n;
                                        }
                                        n3 += (s.charAt(n++) - '0') * 'd';
                                        if (digitCount3 > 1) {
                                            n3 += (s.charAt(n++) - '0') * '\n';
                                            if (digitCount3 > 2) {
                                                n3 += s.charAt(n++) - '0';
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            dateTimeParserBucket.setOffset(b ? (-n3) : n3);
            return n;
        }
        
        private int digitCount(final String s, final int n, int n2) {
            int i = Math.min(s.length() - n, n2);
            n2 = 0;
            while (i > 0) {
                final char char1 = s.charAt(n + n2);
                if (char1 < '0') {
                    break;
                }
                if (char1 > '9') {
                    break;
                }
                ++n2;
                --i;
            }
            return n2;
        }
    }
    
    static class TimeZoneName implements DateTimePrinter
    {
        static final int LONG_NAME = 0;
        static final int SHORT_NAME = 1;
        static final int ID = 2;
        private final int iType;
        
        TimeZoneName(final int iType) {
            this.iType = iType;
        }
        
        public int estimatePrintedLength() {
            return (this.iType == 1) ? 4 : 20;
        }
        
        public void printTo(final StringBuffer sb, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) {
            sb.append(this.print(n - n2, dateTimeZone, locale));
        }
        
        public void printTo(final Writer writer, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, final Locale locale) throws IOException {
            writer.write(this.print(n - n2, dateTimeZone, locale));
        }
        
        private String print(final long n, final DateTimeZone dateTimeZone, final Locale locale) {
            if (dateTimeZone == null) {
                return "";
            }
            switch (this.iType) {
                case 0: {
                    return dateTimeZone.getName(n, locale);
                }
                case 1: {
                    return dateTimeZone.getShortName(n, locale);
                }
                case 2: {
                    return dateTimeZone.getID();
                }
                default: {
                    return "";
                }
            }
        }
        
        public void printTo(final StringBuffer sb, final ReadablePartial readablePartial, final Locale locale) {
        }
        
        public void printTo(final Writer writer, final ReadablePartial readablePartial, final Locale locale) throws IOException {
        }
    }
    
    static class Composite implements DateTimePrinter, DateTimeParser
    {
        private final DateTimePrinter[] iPrinters;
        private final DateTimeParser[] iParsers;
        private final int iPrintedLengthEstimate;
        private final int iParsedLengthEstimate;
        
        Composite(final List list) {
            final ArrayList list2 = new ArrayList<Object>();
            final ArrayList list3 = new ArrayList<Object>();
            this.decompose(list, list2, list3);
            if (list2.size() <= 0) {
                this.iPrinters = null;
                this.iPrintedLengthEstimate = 0;
            }
            else {
                final int size = list2.size();
                this.iPrinters = new DateTimePrinter[size];
                int iPrintedLengthEstimate = 0;
                for (int i = 0; i < size; ++i) {
                    final DateTimePrinter dateTimePrinter = list2.get(i);
                    iPrintedLengthEstimate += dateTimePrinter.estimatePrintedLength();
                    this.iPrinters[i] = dateTimePrinter;
                }
                this.iPrintedLengthEstimate = iPrintedLengthEstimate;
            }
            if (list3.size() <= 0) {
                this.iParsers = null;
                this.iParsedLengthEstimate = 0;
            }
            else {
                final int size2 = list3.size();
                this.iParsers = new DateTimeParser[size2];
                int iParsedLengthEstimate = 0;
                for (int j = 0; j < size2; ++j) {
                    final DateTimeParser dateTimeParser = list3.get(j);
                    iParsedLengthEstimate += dateTimeParser.estimateParsedLength();
                    this.iParsers[j] = dateTimeParser;
                }
                this.iParsedLengthEstimate = iParsedLengthEstimate;
            }
        }
        
        public int estimatePrintedLength() {
            return this.iPrintedLengthEstimate;
        }
        
        public void printTo(final StringBuffer sb, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, Locale default1) {
            final DateTimePrinter[] iPrinters = this.iPrinters;
            if (iPrinters == null) {
                throw new UnsupportedOperationException();
            }
            if (default1 == null) {
                default1 = Locale.getDefault();
            }
            for (int length = iPrinters.length, i = 0; i < length; ++i) {
                iPrinters[i].printTo(sb, n, chronology, n2, dateTimeZone, default1);
            }
        }
        
        public void printTo(final Writer writer, final long n, final Chronology chronology, final int n2, final DateTimeZone dateTimeZone, Locale default1) throws IOException {
            final DateTimePrinter[] iPrinters = this.iPrinters;
            if (iPrinters == null) {
                throw new UnsupportedOperationException();
            }
            if (default1 == null) {
                default1 = Locale.getDefault();
            }
            for (int length = iPrinters.length, i = 0; i < length; ++i) {
                iPrinters[i].printTo(writer, n, chronology, n2, dateTimeZone, default1);
            }
        }
        
        public void printTo(final StringBuffer sb, final ReadablePartial readablePartial, Locale default1) {
            final DateTimePrinter[] iPrinters = this.iPrinters;
            if (iPrinters == null) {
                throw new UnsupportedOperationException();
            }
            if (default1 == null) {
                default1 = Locale.getDefault();
            }
            for (int length = iPrinters.length, i = 0; i < length; ++i) {
                iPrinters[i].printTo(sb, readablePartial, default1);
            }
        }
        
        public void printTo(final Writer writer, final ReadablePartial readablePartial, Locale default1) throws IOException {
            final DateTimePrinter[] iPrinters = this.iPrinters;
            if (iPrinters == null) {
                throw new UnsupportedOperationException();
            }
            if (default1 == null) {
                default1 = Locale.getDefault();
            }
            for (int length = iPrinters.length, i = 0; i < length; ++i) {
                iPrinters[i].printTo(writer, readablePartial, default1);
            }
        }
        
        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }
        
        public int parseInto(final DateTimeParserBucket dateTimeParserBucket, final String s, int into) {
            final DateTimeParser[] iParsers = this.iParsers;
            if (iParsers == null) {
                throw new UnsupportedOperationException();
            }
            for (int length = iParsers.length, n = 0; n < length && into >= 0; into = iParsers[n].parseInto(dateTimeParserBucket, s, into), ++n) {}
            return into;
        }
        
        boolean isPrinter() {
            return this.iPrinters != null;
        }
        
        boolean isParser() {
            return this.iParsers != null;
        }
        
        private void decompose(final List list, final List list2, final List list3) {
            for (int size = list.size(), i = 0; i < size; i += 2) {
                final Composite value = list.get(i);
                if (value instanceof DateTimePrinter) {
                    if (value instanceof Composite) {
                        this.addArrayToList(list2, value.iPrinters);
                    }
                    else {
                        list2.add(value);
                    }
                }
                final Composite value2 = list.get(i + 1);
                if (value2 instanceof DateTimeParser) {
                    if (value2 instanceof Composite) {
                        this.addArrayToList(list3, value2.iParsers);
                    }
                    else {
                        list3.add(value2);
                    }
                }
            }
        }
        
        private void addArrayToList(final List list, final Object[] array) {
            if (array != null) {
                for (int i = 0; i < array.length; ++i) {
                    list.add(array[i]);
                }
            }
        }
    }
    
    static class MatchingParser implements DateTimeParser
    {
        private final DateTimeParser[] iParsers;
        private final int iParsedLengthEstimate;
        
        MatchingParser(final DateTimeParser[] iParsers) {
            this.iParsers = iParsers;
            int iParsedLengthEstimate = 0;
            int length = iParsers.length;
            while (--length >= 0) {
                final DateTimeParser dateTimeParser = iParsers[length];
                if (dateTimeParser != null) {
                    final int estimateParsedLength = dateTimeParser.estimateParsedLength();
                    if (estimateParsedLength <= iParsedLengthEstimate) {
                        continue;
                    }
                    iParsedLengthEstimate = estimateParsedLength;
                }
            }
            this.iParsedLengthEstimate = iParsedLengthEstimate;
        }
        
        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }
        
        public int parseInto(final DateTimeParserBucket dateTimeParserBucket, final String s, final int n) {
            final DateTimeParser[] iParsers = this.iParsers;
            final int length = iParsers.length;
            final Object saveState = dateTimeParserBucket.saveState();
            boolean b = false;
            int n2 = n;
            Object saveState2 = null;
            int n3 = n;
            int i = 0;
            while (i < length) {
                final DateTimeParser dateTimeParser = iParsers[i];
                if (dateTimeParser == null) {
                    if (n2 <= n) {
                        return n;
                    }
                    b = true;
                    break;
                }
                else {
                    final int into = dateTimeParser.parseInto(dateTimeParserBucket, s, n);
                    if (into >= n) {
                        if (into > n2) {
                            if (into >= s.length() || i + 1 >= length || iParsers[i + 1] == null) {
                                return into;
                            }
                            n2 = into;
                            saveState2 = dateTimeParserBucket.saveState();
                        }
                    }
                    else if (into < 0) {
                        final int n4 = ~into;
                        if (n4 > n3) {
                            n3 = n4;
                        }
                    }
                    dateTimeParserBucket.restoreState(saveState);
                    ++i;
                }
            }
            if (n2 > n || (n2 == n && b)) {
                if (saveState2 != null) {
                    dateTimeParserBucket.restoreState(saveState2);
                }
                return n2;
            }
            return ~n3;
        }
    }
}
