// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.format;

import org.joda.time.MutableDateTime;
import org.joda.time.DateTime;
import org.joda.time.ReadWritableInstant;
import org.joda.time.ReadablePartial;
import java.io.IOException;
import java.io.Writer;
import org.joda.time.DateTimeUtils;
import org.joda.time.ReadableInstant;
import org.joda.time.DateTimeZone;
import org.joda.time.Chronology;
import java.util.Locale;

public class DateTimeFormatter
{
    private final DateTimePrinter iPrinter;
    private final DateTimeParser iParser;
    private final Locale iLocale;
    private final boolean iOffsetParsed;
    private final Chronology iChrono;
    private final DateTimeZone iZone;
    private final Integer iPivotYear;
    
    public DateTimeFormatter(final DateTimePrinter iPrinter, final DateTimeParser iParser) {
        this.iPrinter = iPrinter;
        this.iParser = iParser;
        this.iLocale = null;
        this.iOffsetParsed = false;
        this.iChrono = null;
        this.iZone = null;
        this.iPivotYear = null;
    }
    
    private DateTimeFormatter(final DateTimePrinter iPrinter, final DateTimeParser iParser, final Locale iLocale, final boolean iOffsetParsed, final Chronology iChrono, final DateTimeZone iZone, final Integer iPivotYear) {
        this.iPrinter = iPrinter;
        this.iParser = iParser;
        this.iLocale = iLocale;
        this.iOffsetParsed = iOffsetParsed;
        this.iChrono = iChrono;
        this.iZone = iZone;
        this.iPivotYear = iPivotYear;
    }
    
    public boolean isPrinter() {
        return this.iPrinter != null;
    }
    
    public DateTimePrinter getPrinter() {
        return this.iPrinter;
    }
    
    public boolean isParser() {
        return this.iParser != null;
    }
    
    public DateTimeParser getParser() {
        return this.iParser;
    }
    
    public DateTimeFormatter withLocale(final Locale locale) {
        if (locale == this.getLocale() || (locale != null && locale.equals(this.getLocale()))) {
            return this;
        }
        return new DateTimeFormatter(this.iPrinter, this.iParser, locale, this.iOffsetParsed, this.iChrono, this.iZone, this.iPivotYear);
    }
    
    public Locale getLocale() {
        return this.iLocale;
    }
    
    public DateTimeFormatter withOffsetParsed() {
        if (this.iOffsetParsed) {
            return this;
        }
        return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, true, this.iChrono, null, this.iPivotYear);
    }
    
    public boolean isOffsetParsed() {
        return this.iOffsetParsed;
    }
    
    public DateTimeFormatter withChronology(final Chronology chronology) {
        if (this.iChrono == chronology) {
            return this;
        }
        return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, chronology, this.iZone, this.iPivotYear);
    }
    
    public Chronology getChronology() {
        return this.iChrono;
    }
    
    public Chronology getChronolgy() {
        return this.iChrono;
    }
    
    public DateTimeFormatter withZone(final DateTimeZone dateTimeZone) {
        if (this.iZone == dateTimeZone) {
            return this;
        }
        return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, false, this.iChrono, dateTimeZone, this.iPivotYear);
    }
    
    public DateTimeZone getZone() {
        return this.iZone;
    }
    
    public DateTimeFormatter withPivotYear(final Integer n) {
        if (this.iPivotYear == n || (this.iPivotYear != null && this.iPivotYear.equals(n))) {
            return this;
        }
        return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, this.iChrono, this.iZone, n);
    }
    
    public DateTimeFormatter withPivotYear(final int n) {
        return this.withPivotYear(new Integer(n));
    }
    
    public Integer getPivotYear() {
        return this.iPivotYear;
    }
    
    public void printTo(final StringBuffer sb, final ReadableInstant readableInstant) {
        this.printTo(sb, DateTimeUtils.getInstantMillis(readableInstant), DateTimeUtils.getInstantChronology(readableInstant));
    }
    
    public void printTo(final Writer writer, final ReadableInstant readableInstant) throws IOException {
        this.printTo(writer, DateTimeUtils.getInstantMillis(readableInstant), DateTimeUtils.getInstantChronology(readableInstant));
    }
    
    public void printTo(final StringBuffer sb, final long n) {
        this.printTo(sb, n, null);
    }
    
    public void printTo(final Writer writer, final long n) throws IOException {
        this.printTo(writer, n, null);
    }
    
    public void printTo(final StringBuffer sb, final ReadablePartial readablePartial) {
        final DateTimePrinter requirePrinter = this.requirePrinter();
        if (readablePartial == null) {
            throw new IllegalArgumentException("The partial must not be null");
        }
        requirePrinter.printTo(sb, readablePartial, this.iLocale);
    }
    
    public void printTo(final Writer writer, final ReadablePartial readablePartial) throws IOException {
        final DateTimePrinter requirePrinter = this.requirePrinter();
        if (readablePartial == null) {
            throw new IllegalArgumentException("The partial must not be null");
        }
        requirePrinter.printTo(writer, readablePartial, this.iLocale);
    }
    
    public String print(final ReadableInstant readableInstant) {
        final StringBuffer sb = new StringBuffer(this.requirePrinter().estimatePrintedLength());
        this.printTo(sb, readableInstant);
        return sb.toString();
    }
    
    public String print(final long n) {
        final StringBuffer sb = new StringBuffer(this.requirePrinter().estimatePrintedLength());
        this.printTo(sb, n);
        return sb.toString();
    }
    
    public String print(final ReadablePartial readablePartial) {
        final StringBuffer sb = new StringBuffer(this.requirePrinter().estimatePrintedLength());
        this.printTo(sb, readablePartial);
        return sb.toString();
    }
    
    private void printTo(final StringBuffer sb, final long n, Chronology selectChronology) {
        final DateTimePrinter requirePrinter = this.requirePrinter();
        selectChronology = this.selectChronology(selectChronology);
        DateTimeZone dateTimeZone = selectChronology.getZone();
        int offset = dateTimeZone.getOffset(n);
        long n2 = n + offset;
        if ((n ^ n2) < 0L && (n ^ offset) >= 0L) {
            dateTimeZone = DateTimeZone.UTC;
            offset = 0;
            n2 = n;
        }
        requirePrinter.printTo(sb, n2, selectChronology.withUTC(), offset, dateTimeZone, this.iLocale);
    }
    
    private void printTo(final Writer writer, final long n, Chronology selectChronology) throws IOException {
        final DateTimePrinter requirePrinter = this.requirePrinter();
        selectChronology = this.selectChronology(selectChronology);
        DateTimeZone dateTimeZone = selectChronology.getZone();
        int offset = dateTimeZone.getOffset(n);
        long n2 = n + offset;
        if ((n ^ n2) < 0L && (n ^ offset) >= 0L) {
            dateTimeZone = DateTimeZone.UTC;
            offset = 0;
            n2 = n;
        }
        requirePrinter.printTo(writer, n2, selectChronology.withUTC(), offset, dateTimeZone, this.iLocale);
    }
    
    private DateTimePrinter requirePrinter() {
        final DateTimePrinter iPrinter = this.iPrinter;
        if (iPrinter == null) {
            throw new UnsupportedOperationException("Printing not supported");
        }
        return iPrinter;
    }
    
    public int parseInto(final ReadWritableInstant readWritableInstant, final String s, final int n) {
        final DateTimeParser requireParser = this.requireParser();
        if (readWritableInstant == null) {
            throw new IllegalArgumentException("Instant must not be null");
        }
        final long millis = readWritableInstant.getMillis();
        final Chronology chronology = readWritableInstant.getChronology();
        final long n2 = millis + chronology.getZone().getOffset(millis);
        Chronology chronology2 = this.selectChronology(chronology);
        final DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(n2, chronology2, this.iLocale, this.iPivotYear);
        final int into = requireParser.parseInto(dateTimeParserBucket, s, n);
        readWritableInstant.setMillis(dateTimeParserBucket.computeMillis(false, s));
        if (this.iOffsetParsed && dateTimeParserBucket.getZone() == null) {
            chronology2 = chronology2.withZone(DateTimeZone.forOffsetMillis(dateTimeParserBucket.getOffset()));
        }
        readWritableInstant.setChronology(chronology2);
        return into;
    }
    
    public long parseMillis(final String s) {
        final DateTimeParser requireParser = this.requireParser();
        final DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(0L, this.selectChronology(this.iChrono), this.iLocale, this.iPivotYear);
        int into = requireParser.parseInto(dateTimeParserBucket, s, 0);
        if (into >= 0) {
            if (into >= s.length()) {
                return dateTimeParserBucket.computeMillis(true, s);
            }
        }
        else {
            into ^= -1;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(s, into));
    }
    
    public DateTime parseDateTime(final String s) {
        final DateTimeParser requireParser = this.requireParser();
        Chronology chronology = this.selectChronology(null);
        final DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(0L, chronology, this.iLocale, this.iPivotYear);
        int into = requireParser.parseInto(dateTimeParserBucket, s, 0);
        if (into >= 0) {
            if (into >= s.length()) {
                final long computeMillis = dateTimeParserBucket.computeMillis(true, s);
                if (this.iOffsetParsed && dateTimeParserBucket.getZone() == null) {
                    chronology = chronology.withZone(DateTimeZone.forOffsetMillis(dateTimeParserBucket.getOffset()));
                }
                return new DateTime(computeMillis, chronology);
            }
        }
        else {
            into ^= -1;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(s, into));
    }
    
    public MutableDateTime parseMutableDateTime(final String s) {
        final DateTimeParser requireParser = this.requireParser();
        Chronology chronology = this.selectChronology(null);
        final DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(0L, chronology, this.iLocale, this.iPivotYear);
        int into = requireParser.parseInto(dateTimeParserBucket, s, 0);
        if (into >= 0) {
            if (into >= s.length()) {
                final long computeMillis = dateTimeParserBucket.computeMillis(true, s);
                if (this.iOffsetParsed && dateTimeParserBucket.getZone() == null) {
                    chronology = chronology.withZone(DateTimeZone.forOffsetMillis(dateTimeParserBucket.getOffset()));
                }
                return new MutableDateTime(computeMillis, chronology);
            }
        }
        else {
            into ^= -1;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(s, into));
    }
    
    private DateTimeParser requireParser() {
        final DateTimeParser iParser = this.iParser;
        if (iParser == null) {
            throw new UnsupportedOperationException("Parsing not supported");
        }
        return iParser;
    }
    
    private Chronology selectChronology(Chronology chronology) {
        chronology = DateTimeUtils.getChronology(chronology);
        if (this.iChrono != null) {
            chronology = this.iChrono;
        }
        if (this.iZone != null) {
            chronology = chronology.withZone(this.iZone);
        }
        return chronology;
    }
}
