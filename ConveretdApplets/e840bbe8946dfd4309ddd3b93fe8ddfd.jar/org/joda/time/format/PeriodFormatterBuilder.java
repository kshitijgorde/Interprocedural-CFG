// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.format;

import java.util.Collections;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;
import org.joda.time.PeriodType;
import org.joda.time.DurationFieldType;
import org.joda.time.ReadWritablePeriod;
import java.util.Locale;
import org.joda.time.ReadablePeriod;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PeriodFormatterBuilder
{
    private static final int PRINT_ZERO_RARELY_FIRST = 1;
    private static final int PRINT_ZERO_RARELY_LAST = 2;
    private static final int PRINT_ZERO_IF_SUPPORTED = 3;
    private static final int PRINT_ZERO_ALWAYS = 4;
    private static final int PRINT_ZERO_NEVER = 5;
    private static final int YEARS = 0;
    private static final int MONTHS = 1;
    private static final int WEEKS = 2;
    private static final int DAYS = 3;
    private static final int HOURS = 4;
    private static final int MINUTES = 5;
    private static final int SECONDS = 6;
    private static final int MILLIS = 7;
    private static final int SECONDS_MILLIS = 8;
    private static final int SECONDS_OPTIONAL_MILLIS = 9;
    private static final int MAX_FIELD = 9;
    private int iMinPrintedDigits;
    private int iPrintZeroSetting;
    private int iMaxParsedDigits;
    private boolean iRejectSignedValues;
    private PeriodFieldAffix iPrefix;
    private List iElementPairs;
    private boolean iNotPrinter;
    private boolean iNotParser;
    private FieldFormatter[] iFieldFormatters;
    
    public PeriodFormatterBuilder() {
        this.clear();
    }
    
    public PeriodFormatter toFormatter() {
        final PeriodFormatter formatter = toFormatter(this.iElementPairs, this.iNotPrinter, this.iNotParser);
        this.iFieldFormatters = this.iFieldFormatters.clone();
        return formatter;
    }
    
    public PeriodPrinter toPrinter() {
        if (this.iNotPrinter) {
            return null;
        }
        return this.toFormatter().getPrinter();
    }
    
    public PeriodParser toParser() {
        if (this.iNotParser) {
            return null;
        }
        return this.toFormatter().getParser();
    }
    
    public void clear() {
        this.iMinPrintedDigits = 1;
        this.iPrintZeroSetting = 2;
        this.iMaxParsedDigits = 10;
        this.iRejectSignedValues = false;
        this.iPrefix = null;
        if (this.iElementPairs == null) {
            this.iElementPairs = new ArrayList();
        }
        else {
            this.iElementPairs.clear();
        }
        this.iNotPrinter = false;
        this.iNotParser = false;
        this.iFieldFormatters = new FieldFormatter[10];
    }
    
    public PeriodFormatterBuilder append(final PeriodFormatter periodFormatter) {
        if (periodFormatter == null) {
            throw new IllegalArgumentException("No formatter supplied");
        }
        this.clearPrefix();
        this.append0(periodFormatter.getPrinter(), periodFormatter.getParser());
        return this;
    }
    
    public PeriodFormatterBuilder append(final PeriodPrinter periodPrinter, final PeriodParser periodParser) {
        if (periodPrinter == null && periodParser == null) {
            throw new IllegalArgumentException("No printer or parser supplied");
        }
        this.clearPrefix();
        this.append0(periodPrinter, periodParser);
        return this;
    }
    
    public PeriodFormatterBuilder appendLiteral(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("Literal must not be null");
        }
        this.clearPrefix();
        final Literal literal = new Literal(s);
        this.append0(literal, literal);
        return this;
    }
    
    public PeriodFormatterBuilder minimumPrintedDigits(final int iMinPrintedDigits) {
        this.iMinPrintedDigits = iMinPrintedDigits;
        return this;
    }
    
    public PeriodFormatterBuilder maximumParsedDigits(final int iMaxParsedDigits) {
        this.iMaxParsedDigits = iMaxParsedDigits;
        return this;
    }
    
    public PeriodFormatterBuilder rejectSignedValues(final boolean iRejectSignedValues) {
        this.iRejectSignedValues = iRejectSignedValues;
        return this;
    }
    
    public PeriodFormatterBuilder printZeroRarelyLast() {
        this.iPrintZeroSetting = 2;
        return this;
    }
    
    public PeriodFormatterBuilder printZeroRarelyFirst() {
        this.iPrintZeroSetting = 1;
        return this;
    }
    
    public PeriodFormatterBuilder printZeroIfSupported() {
        this.iPrintZeroSetting = 3;
        return this;
    }
    
    public PeriodFormatterBuilder printZeroAlways() {
        this.iPrintZeroSetting = 4;
        return this;
    }
    
    public PeriodFormatterBuilder printZeroNever() {
        this.iPrintZeroSetting = 5;
        return this;
    }
    
    public PeriodFormatterBuilder appendPrefix(final String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        return this.appendPrefix(new SimpleAffix(s));
    }
    
    public PeriodFormatterBuilder appendPrefix(final String s, final String s2) {
        if (s == null || s2 == null) {
            throw new IllegalArgumentException();
        }
        return this.appendPrefix(new PluralAffix(s, s2));
    }
    
    private PeriodFormatterBuilder appendPrefix(PeriodFieldAffix iPrefix) {
        if (iPrefix == null) {
            throw new IllegalArgumentException();
        }
        if (this.iPrefix != null) {
            iPrefix = new CompositeAffix(this.iPrefix, iPrefix);
        }
        this.iPrefix = iPrefix;
        return this;
    }
    
    public PeriodFormatterBuilder appendYears() {
        this.appendField(0);
        return this;
    }
    
    public PeriodFormatterBuilder appendMonths() {
        this.appendField(1);
        return this;
    }
    
    public PeriodFormatterBuilder appendWeeks() {
        this.appendField(2);
        return this;
    }
    
    public PeriodFormatterBuilder appendDays() {
        this.appendField(3);
        return this;
    }
    
    public PeriodFormatterBuilder appendHours() {
        this.appendField(4);
        return this;
    }
    
    public PeriodFormatterBuilder appendMinutes() {
        this.appendField(5);
        return this;
    }
    
    public PeriodFormatterBuilder appendSeconds() {
        this.appendField(6);
        return this;
    }
    
    public PeriodFormatterBuilder appendSecondsWithMillis() {
        this.appendField(8);
        return this;
    }
    
    public PeriodFormatterBuilder appendSecondsWithOptionalMillis() {
        this.appendField(9);
        return this;
    }
    
    public PeriodFormatterBuilder appendMillis() {
        this.appendField(7);
        return this;
    }
    
    public PeriodFormatterBuilder appendMillis3Digit() {
        this.appendField(7, 3);
        return this;
    }
    
    private void appendField(final int n) {
        this.appendField(n, this.iMinPrintedDigits);
    }
    
    private void appendField(final int n, final int n2) {
        final FieldFormatter fieldFormatter = new FieldFormatter(n2, this.iPrintZeroSetting, this.iMaxParsedDigits, this.iRejectSignedValues, n, this.iFieldFormatters, this.iPrefix, null);
        this.append0(fieldFormatter, fieldFormatter);
        this.iFieldFormatters[n] = fieldFormatter;
        this.iPrefix = null;
    }
    
    public PeriodFormatterBuilder appendSuffix(final String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        return this.appendSuffix(new SimpleAffix(s));
    }
    
    public PeriodFormatterBuilder appendSuffix(final String s, final String s2) {
        if (s == null || s2 == null) {
            throw new IllegalArgumentException();
        }
        return this.appendSuffix(new PluralAffix(s, s2));
    }
    
    private PeriodFormatterBuilder appendSuffix(final PeriodFieldAffix periodFieldAffix) {
        FieldFormatter value;
        FieldFormatter value2;
        if (this.iElementPairs.size() > 0) {
            value = this.iElementPairs.get(this.iElementPairs.size() - 2);
            value2 = this.iElementPairs.get(this.iElementPairs.size() - 1);
        }
        else {
            value = null;
            value2 = null;
        }
        if (value == null || value2 == null || value != value2 || !(value instanceof FieldFormatter)) {
            throw new IllegalStateException("No field to apply suffix to");
        }
        this.clearPrefix();
        final FieldFormatter fieldFormatter = new FieldFormatter(value, periodFieldAffix);
        this.iElementPairs.set(this.iElementPairs.size() - 2, fieldFormatter);
        this.iElementPairs.set(this.iElementPairs.size() - 1, fieldFormatter);
        this.iFieldFormatters[fieldFormatter.getFieldType()] = fieldFormatter;
        return this;
    }
    
    public PeriodFormatterBuilder appendSeparator(final String s) {
        return this.appendSeparator(s, s, null, true, true);
    }
    
    public PeriodFormatterBuilder appendSeparatorIfFieldsAfter(final String s) {
        return this.appendSeparator(s, s, null, false, true);
    }
    
    public PeriodFormatterBuilder appendSeparatorIfFieldsBefore(final String s) {
        return this.appendSeparator(s, s, null, true, false);
    }
    
    public PeriodFormatterBuilder appendSeparator(final String s, final String s2) {
        return this.appendSeparator(s, s2, null, true, true);
    }
    
    public PeriodFormatterBuilder appendSeparator(final String s, final String s2, final String[] array) {
        return this.appendSeparator(s, s2, array, true, true);
    }
    
    private PeriodFormatterBuilder appendSeparator(final String s, final String s2, final String[] array, final boolean b, final boolean b2) {
        if (s == null || s2 == null) {
            throw new IllegalArgumentException();
        }
        this.clearPrefix();
        List<Separator> list = (List<Separator>)this.iElementPairs;
        if (list.size() == 0) {
            if (b2 && !b) {
                final Separator separator = new Separator(s, s2, array, Literal.EMPTY, Literal.EMPTY, b, b2);
                this.append0(separator, separator);
            }
            return this;
        }
        Separator separator2 = null;
        for (int size = list.size(); --size >= 0; --size) {
            if (list.get(size) instanceof Separator) {
                separator2 = list.get(size);
                list = list.subList(size + 1, list.size());
                break;
            }
        }
        if (separator2 != null && list.size() == 0) {
            throw new IllegalStateException("Cannot have two adjacent separators");
        }
        final Object[] composite = createComposite(list);
        list.clear();
        final Separator separator3 = new Separator(s, s2, array, (PeriodPrinter)composite[0], (PeriodParser)composite[1], b, b2);
        list.add(separator3);
        list.add(separator3);
        return this;
    }
    
    private void clearPrefix() throws IllegalStateException {
        if (this.iPrefix != null) {
            throw new IllegalStateException("Prefix not followed by field");
        }
        this.iPrefix = null;
    }
    
    private PeriodFormatterBuilder append0(final PeriodPrinter periodPrinter, final PeriodParser periodParser) {
        this.iElementPairs.add(periodPrinter);
        this.iElementPairs.add(periodParser);
        this.iNotPrinter |= (periodPrinter == null);
        this.iNotParser |= (periodParser == null);
        return this;
    }
    
    private static PeriodFormatter toFormatter(final List list, final boolean b, final boolean b2) {
        if (b && b2) {
            throw new IllegalStateException("Builder has created neither a printer nor a parser");
        }
        final int size = list.size();
        if (size >= 2 && list.get(0) instanceof Separator) {
            final Separator separator = list.get(0);
            final PeriodFormatter formatter = toFormatter(list.subList(2, size), b, b2);
            final Separator finish = separator.finish(formatter.getPrinter(), formatter.getParser());
            return new PeriodFormatter(finish, finish);
        }
        final Object[] composite = createComposite(list);
        if (b) {
            return new PeriodFormatter(null, (PeriodParser)composite[1]);
        }
        if (b2) {
            return new PeriodFormatter((PeriodPrinter)composite[0], null);
        }
        return new PeriodFormatter((PeriodPrinter)composite[0], (PeriodParser)composite[1]);
    }
    
    private static Object[] createComposite(final List list) {
        switch (list.size()) {
            case 0: {
                return new Object[] { Literal.EMPTY, Literal.EMPTY };
            }
            case 1: {
                return new Object[] { list.get(0), list.get(1) };
            }
            default: {
                final Composite composite = new Composite(list);
                return new Object[] { composite, composite };
            }
        }
    }
    
    static class SimpleAffix implements PeriodFieldAffix
    {
        private final String iText;
        
        SimpleAffix(final String iText) {
            this.iText = iText;
        }
        
        public int calculatePrintedLength(final int n) {
            return this.iText.length();
        }
        
        public void printTo(final StringBuffer sb, final int n) {
            sb.append(this.iText);
        }
        
        public void printTo(final Writer writer, final int n) throws IOException {
            writer.write(this.iText);
        }
        
        public int parse(final String s, final int n) {
            final String iText = this.iText;
            final int length = iText.length();
            if (s.regionMatches(true, n, iText, 0, length)) {
                return n + length;
            }
            return ~n;
        }
        
        public int scan(final String s, final int n) {
            final String iText = this.iText;
            final int length = iText.length();
            final int length2 = s.length();
            int i = n;
        Label_0136:
            while (i < length2) {
                if (s.regionMatches(true, i, iText, 0, length)) {
                    return i;
                }
                switch (s.charAt(i)) {
                    case '+':
                    case ',':
                    case '-':
                    case '.':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9': {
                        ++i;
                        continue;
                    }
                    default: {
                        break Label_0136;
                    }
                }
            }
            return ~n;
        }
    }
    
    static class PluralAffix implements PeriodFieldAffix
    {
        private final String iSingularText;
        private final String iPluralText;
        
        PluralAffix(final String iSingularText, final String iPluralText) {
            this.iSingularText = iSingularText;
            this.iPluralText = iPluralText;
        }
        
        public int calculatePrintedLength(final int n) {
            return ((n == 1) ? this.iSingularText : this.iPluralText).length();
        }
        
        public void printTo(final StringBuffer sb, final int n) {
            sb.append((n == 1) ? this.iSingularText : this.iPluralText);
        }
        
        public void printTo(final Writer writer, final int n) throws IOException {
            writer.write((n == 1) ? this.iSingularText : this.iPluralText);
        }
        
        public int parse(final String s, final int n) {
            String iPluralText = this.iPluralText;
            String iSingularText = this.iSingularText;
            if (iPluralText.length() < iSingularText.length()) {
                final String s2 = iPluralText;
                iPluralText = iSingularText;
                iSingularText = s2;
            }
            if (s.regionMatches(true, n, iPluralText, 0, iPluralText.length())) {
                return n + iPluralText.length();
            }
            if (s.regionMatches(true, n, iSingularText, 0, iSingularText.length())) {
                return n + iSingularText.length();
            }
            return ~n;
        }
        
        public int scan(final String s, final int n) {
            String iPluralText = this.iPluralText;
            String iSingularText = this.iSingularText;
            if (iPluralText.length() < iSingularText.length()) {
                final String s2 = iPluralText;
                iPluralText = iSingularText;
                iSingularText = s2;
            }
            final int length = iPluralText.length();
            final int length2 = iSingularText.length();
            for (int length3 = s.length(), i = n; i < length3; ++i) {
                if (s.regionMatches(true, i, iPluralText, 0, length)) {
                    return i;
                }
                if (s.regionMatches(true, i, iSingularText, 0, length2)) {
                    return i;
                }
            }
            return ~n;
        }
    }
    
    static class CompositeAffix implements PeriodFieldAffix
    {
        private final PeriodFieldAffix iLeft;
        private final PeriodFieldAffix iRight;
        
        CompositeAffix(final PeriodFieldAffix iLeft, final PeriodFieldAffix iRight) {
            this.iLeft = iLeft;
            this.iRight = iRight;
        }
        
        public int calculatePrintedLength(final int n) {
            return this.iLeft.calculatePrintedLength(n) + this.iRight.calculatePrintedLength(n);
        }
        
        public void printTo(final StringBuffer sb, final int n) {
            this.iLeft.printTo(sb, n);
            this.iRight.printTo(sb, n);
        }
        
        public void printTo(final Writer writer, final int n) throws IOException {
            this.iLeft.printTo(writer, n);
            this.iRight.printTo(writer, n);
        }
        
        public int parse(final String s, int n) {
            n = this.iLeft.parse(s, n);
            if (n >= 0) {
                n = this.iRight.parse(s, n);
            }
            return n;
        }
        
        public int scan(final String s, final int n) {
            final int scan = this.iLeft.scan(s, n);
            if (scan >= 0) {
                return this.iRight.scan(s, scan);
            }
            return ~n;
        }
    }
    
    static class FieldFormatter implements PeriodPrinter, PeriodParser
    {
        private final int iMinPrintedDigits;
        private final int iPrintZeroSetting;
        private final int iMaxParsedDigits;
        private final boolean iRejectSignedValues;
        private final int iFieldType;
        private final FieldFormatter[] iFieldFormatters;
        private final PeriodFieldAffix iPrefix;
        private final PeriodFieldAffix iSuffix;
        
        FieldFormatter(final int iMinPrintedDigits, final int iPrintZeroSetting, final int iMaxParsedDigits, final boolean iRejectSignedValues, final int iFieldType, final FieldFormatter[] iFieldFormatters, final PeriodFieldAffix iPrefix, final PeriodFieldAffix iSuffix) {
            this.iMinPrintedDigits = iMinPrintedDigits;
            this.iPrintZeroSetting = iPrintZeroSetting;
            this.iMaxParsedDigits = iMaxParsedDigits;
            this.iRejectSignedValues = iRejectSignedValues;
            this.iFieldType = iFieldType;
            this.iFieldFormatters = iFieldFormatters;
            this.iPrefix = iPrefix;
            this.iSuffix = iSuffix;
        }
        
        FieldFormatter(final FieldFormatter fieldFormatter, PeriodFieldAffix iSuffix) {
            this.iMinPrintedDigits = fieldFormatter.iMinPrintedDigits;
            this.iPrintZeroSetting = fieldFormatter.iPrintZeroSetting;
            this.iMaxParsedDigits = fieldFormatter.iMaxParsedDigits;
            this.iRejectSignedValues = fieldFormatter.iRejectSignedValues;
            this.iFieldType = fieldFormatter.iFieldType;
            this.iFieldFormatters = fieldFormatter.iFieldFormatters;
            this.iPrefix = fieldFormatter.iPrefix;
            if (fieldFormatter.iSuffix != null) {
                iSuffix = new CompositeAffix(fieldFormatter.iSuffix, iSuffix);
            }
            this.iSuffix = iSuffix;
        }
        
        public int countFieldsToPrint(final ReadablePeriod readablePeriod, final int n, final Locale locale) {
            if (n <= 0) {
                return 0;
            }
            if (this.iPrintZeroSetting == 4 || this.getFieldValue(readablePeriod) != Long.MAX_VALUE) {
                return 1;
            }
            return 0;
        }
        
        public int calculatePrintedLength(final ReadablePeriod readablePeriod, final Locale locale) {
            long fieldValue = this.getFieldValue(readablePeriod);
            if (fieldValue == Long.MAX_VALUE) {
                return 0;
            }
            int n = Math.max(FormatUtils.calculateDigitCount(fieldValue), this.iMinPrintedDigits);
            if (this.iFieldType >= 8) {
                n = Math.max(n, 4);
                ++n;
                if (this.iFieldType == 9 && Math.abs(fieldValue) % 1000L == 0L) {
                    n -= 4;
                }
                fieldValue /= 1000L;
            }
            final int n2 = (int)fieldValue;
            if (this.iPrefix != null) {
                n += this.iPrefix.calculatePrintedLength(n2);
            }
            if (this.iSuffix != null) {
                n += this.iSuffix.calculatePrintedLength(n2);
            }
            return n;
        }
        
        public void printTo(final StringBuffer sb, final ReadablePeriod readablePeriod, final Locale locale) {
            final long fieldValue = this.getFieldValue(readablePeriod);
            if (fieldValue == Long.MAX_VALUE) {
                return;
            }
            int n = (int)fieldValue;
            if (this.iFieldType >= 8) {
                n = (int)(fieldValue / 1000L);
            }
            if (this.iPrefix != null) {
                this.iPrefix.printTo(sb, n);
            }
            final int iMinPrintedDigits = this.iMinPrintedDigits;
            if (iMinPrintedDigits <= 1) {
                FormatUtils.appendUnpaddedInteger(sb, n);
            }
            else {
                FormatUtils.appendPaddedInteger(sb, n, iMinPrintedDigits);
            }
            if (this.iFieldType >= 8) {
                final int n2 = (int)(Math.abs(fieldValue) % 1000L);
                if (this.iFieldType == 8 || n2 > 0) {
                    sb.append('.');
                    FormatUtils.appendPaddedInteger(sb, n2, 3);
                }
            }
            if (this.iSuffix != null) {
                this.iSuffix.printTo(sb, n);
            }
        }
        
        public void printTo(final Writer writer, final ReadablePeriod readablePeriod, final Locale locale) throws IOException {
            final long fieldValue = this.getFieldValue(readablePeriod);
            if (fieldValue == Long.MAX_VALUE) {
                return;
            }
            int n = (int)fieldValue;
            if (this.iFieldType >= 8) {
                n = (int)(fieldValue / 1000L);
            }
            if (this.iPrefix != null) {
                this.iPrefix.printTo(writer, n);
            }
            final int iMinPrintedDigits = this.iMinPrintedDigits;
            if (iMinPrintedDigits <= 1) {
                FormatUtils.writeUnpaddedInteger(writer, n);
            }
            else {
                FormatUtils.writePaddedInteger(writer, n, iMinPrintedDigits);
            }
            if (this.iFieldType >= 8) {
                final int n2 = (int)(Math.abs(fieldValue) % 1000L);
                if (this.iFieldType == 8 || n2 > 0) {
                    writer.write(46);
                    FormatUtils.writePaddedInteger(writer, n2, 3);
                }
            }
            if (this.iSuffix != null) {
                this.iSuffix.printTo(writer, n);
            }
        }
        
        public int parseInto(final ReadWritablePeriod readWritablePeriod, final String s, int n, final Locale locale) {
            int n2 = (this.iPrintZeroSetting == 4) ? 1 : 0;
            if (n >= s.length()) {
                return (n2 != 0) ? (~n) : n;
            }
            if (this.iPrefix != null) {
                n = this.iPrefix.parse(s, n);
                if (n >= 0) {
                    n2 = 1;
                }
                else {
                    if (n2 == 0) {
                        return ~n;
                    }
                    return n;
                }
            }
            int scan = -1;
            if (this.iSuffix != null && n2 == 0) {
                scan = this.iSuffix.scan(s, n);
                if (scan >= 0) {
                    n2 = 1;
                }
                else {
                    if (n2 == 0) {
                        return ~scan;
                    }
                    return scan;
                }
            }
            if (n2 == 0 && !this.isSupported(readWritablePeriod.getPeriodType(), this.iFieldType)) {
                return n;
            }
            int n3;
            if (scan > 0) {
                n3 = Math.min(this.iMaxParsedDigits, scan - n);
            }
            else {
                n3 = Math.min(this.iMaxParsedDigits, s.length() - n);
            }
            int i = 0;
            int n4 = -1;
            boolean b = false;
            while (i < n3) {
                final char char1 = s.charAt(n + i);
                if (i == 0 && (char1 == '-' || char1 == '+') && !this.iRejectSignedValues) {
                    final boolean b2 = char1 == '-';
                    final char char2;
                    if (i + 1 >= n3 || (char2 = s.charAt(n + i + 1)) < '0') {
                        break;
                    }
                    if (char2 > '9') {
                        break;
                    }
                    if (b2) {
                        ++i;
                    }
                    else {
                        ++n;
                    }
                    n3 = Math.min(n3 + 1, s.length() - n);
                }
                else {
                    if (char1 >= '0' && char1 <= '9') {
                        b = true;
                    }
                    else {
                        if ((char1 != '.' && char1 != ',') || (this.iFieldType != 8 && this.iFieldType != 9)) {
                            break;
                        }
                        if (n4 >= 0) {
                            break;
                        }
                        n4 = n + i + 1;
                        n3 = Math.min(n3 + 1, s.length() - n);
                    }
                    ++i;
                }
            }
            if (!b) {
                return ~n;
            }
            if (scan >= 0 && n + i != scan) {
                return n;
            }
            if (this.iFieldType != 8 && this.iFieldType != 9) {
                this.setFieldValue(readWritablePeriod, this.iFieldType, this.parseInt(s, n, i));
            }
            else if (n4 < 0) {
                this.setFieldValue(readWritablePeriod, 6, this.parseInt(s, n, i));
                this.setFieldValue(readWritablePeriod, 7, 0);
            }
            else {
                final int int1 = this.parseInt(s, n, n4 - n - 1);
                this.setFieldValue(readWritablePeriod, 6, int1);
                final int n5 = n + i - n4;
                int int2;
                if (n5 <= 0) {
                    int2 = 0;
                }
                else {
                    if (n5 >= 3) {
                        int2 = this.parseInt(s, n4, 3);
                    }
                    else {
                        final int int3 = this.parseInt(s, n4, n5);
                        if (n5 == 1) {
                            int2 = int3 * 100;
                        }
                        else {
                            int2 = int3 * 10;
                        }
                    }
                    if (int1 < 0) {
                        int2 = -int2;
                    }
                }
                this.setFieldValue(readWritablePeriod, 7, int2);
            }
            n += i;
            if (n >= 0 && this.iSuffix != null) {
                n = this.iSuffix.parse(s, n);
            }
            return n;
        }
        
        private int parseInt(final String s, int n, int n2) {
            if (n2 >= 10) {
                return Integer.parseInt(s.substring(n, n + n2));
            }
            if (n2 <= 0) {
                return 0;
            }
            int n3 = s.charAt(n++);
            --n2;
            boolean b;
            if (n3 == 45) {
                if (--n2 < 0) {
                    return 0;
                }
                b = true;
                n3 = s.charAt(n++);
            }
            else {
                b = false;
            }
            n3 -= 48;
            while (n2-- > 0) {
                n3 = (n3 << 3) + (n3 << 1) + s.charAt(n++) - 48;
            }
            return b ? (-n3) : n3;
        }
        
        long getFieldValue(final ReadablePeriod readablePeriod) {
            PeriodType periodType;
            if (this.iPrintZeroSetting == 4) {
                periodType = null;
            }
            else {
                periodType = readablePeriod.getPeriodType();
            }
            if (periodType != null && !this.isSupported(periodType, this.iFieldType)) {
                return Long.MAX_VALUE;
            }
            long n = 0L;
            switch (this.iFieldType) {
                default: {
                    return Long.MAX_VALUE;
                }
                case 0: {
                    n = readablePeriod.get(DurationFieldType.years());
                    break;
                }
                case 1: {
                    n = readablePeriod.get(DurationFieldType.months());
                    break;
                }
                case 2: {
                    n = readablePeriod.get(DurationFieldType.weeks());
                    break;
                }
                case 3: {
                    n = readablePeriod.get(DurationFieldType.days());
                    break;
                }
                case 4: {
                    n = readablePeriod.get(DurationFieldType.hours());
                    break;
                }
                case 5: {
                    n = readablePeriod.get(DurationFieldType.minutes());
                    break;
                }
                case 6: {
                    n = readablePeriod.get(DurationFieldType.seconds());
                    break;
                }
                case 7: {
                    n = readablePeriod.get(DurationFieldType.millis());
                    break;
                }
                case 8:
                case 9: {
                    n = readablePeriod.get(DurationFieldType.seconds()) * 1000L + readablePeriod.get(DurationFieldType.millis());
                    break;
                }
            }
            if (n == 0L) {
                switch (this.iPrintZeroSetting) {
                    case 5: {
                        return Long.MAX_VALUE;
                    }
                    case 2: {
                        if (this.isZero(readablePeriod) && this.iFieldFormatters[this.iFieldType] == this) {
                            for (int i = this.iFieldType + 1; i <= 9; ++i) {
                                if (this.isSupported(periodType, i) && this.iFieldFormatters[i] != null) {
                                    return Long.MAX_VALUE;
                                }
                            }
                            break;
                        }
                        return Long.MAX_VALUE;
                    }
                    case 1: {
                        if (this.isZero(readablePeriod) && this.iFieldFormatters[this.iFieldType] == this) {
                            int min = Math.min(this.iFieldType, 8);
                            --min;
                            while (min >= 0 && min <= 9) {
                                if (this.isSupported(periodType, min) && this.iFieldFormatters[min] != null) {
                                    return Long.MAX_VALUE;
                                }
                                --min;
                            }
                            break;
                        }
                        return Long.MAX_VALUE;
                    }
                }
            }
            return n;
        }
        
        boolean isZero(final ReadablePeriod readablePeriod) {
            for (int i = 0; i < readablePeriod.size(); ++i) {
                if (readablePeriod.getValue(i) != 0) {
                    return false;
                }
            }
            return true;
        }
        
        boolean isSupported(final PeriodType periodType, final int n) {
            switch (n) {
                default: {
                    return false;
                }
                case 0: {
                    return periodType.isSupported(DurationFieldType.years());
                }
                case 1: {
                    return periodType.isSupported(DurationFieldType.months());
                }
                case 2: {
                    return periodType.isSupported(DurationFieldType.weeks());
                }
                case 3: {
                    return periodType.isSupported(DurationFieldType.days());
                }
                case 4: {
                    return periodType.isSupported(DurationFieldType.hours());
                }
                case 5: {
                    return periodType.isSupported(DurationFieldType.minutes());
                }
                case 6: {
                    return periodType.isSupported(DurationFieldType.seconds());
                }
                case 7: {
                    return periodType.isSupported(DurationFieldType.millis());
                }
                case 8:
                case 9: {
                    return periodType.isSupported(DurationFieldType.seconds()) || periodType.isSupported(DurationFieldType.millis());
                }
            }
        }
        
        void setFieldValue(final ReadWritablePeriod readWritablePeriod, final int n, final int n2) {
            switch (n) {
                case 0: {
                    readWritablePeriod.setYears(n2);
                    break;
                }
                case 1: {
                    readWritablePeriod.setMonths(n2);
                    break;
                }
                case 2: {
                    readWritablePeriod.setWeeks(n2);
                    break;
                }
                case 3: {
                    readWritablePeriod.setDays(n2);
                    break;
                }
                case 4: {
                    readWritablePeriod.setHours(n2);
                    break;
                }
                case 5: {
                    readWritablePeriod.setMinutes(n2);
                    break;
                }
                case 6: {
                    readWritablePeriod.setSeconds(n2);
                    break;
                }
                case 7: {
                    readWritablePeriod.setMillis(n2);
                    break;
                }
            }
        }
        
        int getFieldType() {
            return this.iFieldType;
        }
    }
    
    static class Literal implements PeriodPrinter, PeriodParser
    {
        static final Literal EMPTY;
        private final String iText;
        
        Literal(final String iText) {
            this.iText = iText;
        }
        
        public int countFieldsToPrint(final ReadablePeriod readablePeriod, final int n, final Locale locale) {
            return 0;
        }
        
        public int calculatePrintedLength(final ReadablePeriod readablePeriod, final Locale locale) {
            return this.iText.length();
        }
        
        public void printTo(final StringBuffer sb, final ReadablePeriod readablePeriod, final Locale locale) {
            sb.append(this.iText);
        }
        
        public void printTo(final Writer writer, final ReadablePeriod readablePeriod, final Locale locale) throws IOException {
            writer.write(this.iText);
        }
        
        public int parseInto(final ReadWritablePeriod readWritablePeriod, final String s, final int n, final Locale locale) {
            if (s.regionMatches(true, n, this.iText, 0, this.iText.length())) {
                return n + this.iText.length();
            }
            return ~n;
        }
        
        static {
            EMPTY = new Literal("");
        }
    }
    
    static class Separator implements PeriodPrinter, PeriodParser
    {
        private final String iText;
        private final String iFinalText;
        private final String[] iParsedForms;
        private final boolean iUseBefore;
        private final boolean iUseAfter;
        private final PeriodPrinter iBeforePrinter;
        private volatile PeriodPrinter iAfterPrinter;
        private final PeriodParser iBeforeParser;
        private volatile PeriodParser iAfterParser;
        
        Separator(final String iText, final String iFinalText, final String[] array, final PeriodPrinter iBeforePrinter, final PeriodParser iBeforeParser, final boolean iUseBefore, final boolean iUseAfter) {
            this.iText = iText;
            this.iFinalText = iFinalText;
            if ((iFinalText == null || iText.equals(iFinalText)) && (array == null || array.length == 0)) {
                this.iParsedForms = new String[] { iText };
            }
            else {
                final TreeSet<String> set = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
                set.add(iText);
                set.add(iFinalText);
                if (array != null) {
                    int length = array.length;
                    while (--length >= 0) {
                        set.add(array[length]);
                    }
                }
                final ArrayList list = new ArrayList<Object>(set);
                Collections.reverse(list);
                this.iParsedForms = list.toArray(new String[list.size()]);
            }
            this.iBeforePrinter = iBeforePrinter;
            this.iBeforeParser = iBeforeParser;
            this.iUseBefore = iUseBefore;
            this.iUseAfter = iUseAfter;
        }
        
        public int countFieldsToPrint(final ReadablePeriod readablePeriod, final int n, final Locale locale) {
            int countFieldsToPrint = this.iBeforePrinter.countFieldsToPrint(readablePeriod, n, locale);
            if (countFieldsToPrint < n) {
                countFieldsToPrint += this.iAfterPrinter.countFieldsToPrint(readablePeriod, n, locale);
            }
            return countFieldsToPrint;
        }
        
        public int calculatePrintedLength(final ReadablePeriod readablePeriod, final Locale locale) {
            final PeriodPrinter iBeforePrinter = this.iBeforePrinter;
            final PeriodPrinter iAfterPrinter = this.iAfterPrinter;
            int n = iBeforePrinter.calculatePrintedLength(readablePeriod, locale) + iAfterPrinter.calculatePrintedLength(readablePeriod, locale);
            if (this.iUseBefore) {
                if (iBeforePrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                    if (this.iUseAfter) {
                        final int countFieldsToPrint = iAfterPrinter.countFieldsToPrint(readablePeriod, 2, locale);
                        if (countFieldsToPrint > 0) {
                            n += ((countFieldsToPrint > 1) ? this.iText : this.iFinalText).length();
                        }
                    }
                    else {
                        n += this.iText.length();
                    }
                }
            }
            else if (this.iUseAfter && iAfterPrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                n += this.iText.length();
            }
            return n;
        }
        
        public void printTo(final StringBuffer sb, final ReadablePeriod readablePeriod, final Locale locale) {
            final PeriodPrinter iBeforePrinter = this.iBeforePrinter;
            final PeriodPrinter iAfterPrinter = this.iAfterPrinter;
            iBeforePrinter.printTo(sb, readablePeriod, locale);
            if (this.iUseBefore) {
                if (iBeforePrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                    if (this.iUseAfter) {
                        final int countFieldsToPrint = iAfterPrinter.countFieldsToPrint(readablePeriod, 2, locale);
                        if (countFieldsToPrint > 0) {
                            sb.append((countFieldsToPrint > 1) ? this.iText : this.iFinalText);
                        }
                    }
                    else {
                        sb.append(this.iText);
                    }
                }
            }
            else if (this.iUseAfter && iAfterPrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                sb.append(this.iText);
            }
            iAfterPrinter.printTo(sb, readablePeriod, locale);
        }
        
        public void printTo(final Writer writer, final ReadablePeriod readablePeriod, final Locale locale) throws IOException {
            final PeriodPrinter iBeforePrinter = this.iBeforePrinter;
            final PeriodPrinter iAfterPrinter = this.iAfterPrinter;
            iBeforePrinter.printTo(writer, readablePeriod, locale);
            if (this.iUseBefore) {
                if (iBeforePrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                    if (this.iUseAfter) {
                        final int countFieldsToPrint = iAfterPrinter.countFieldsToPrint(readablePeriod, 2, locale);
                        if (countFieldsToPrint > 0) {
                            writer.write((countFieldsToPrint > 1) ? this.iText : this.iFinalText);
                        }
                    }
                    else {
                        writer.write(this.iText);
                    }
                }
            }
            else if (this.iUseAfter && iAfterPrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                writer.write(this.iText);
            }
            iAfterPrinter.printTo(writer, readablePeriod, locale);
        }
        
        public int parseInto(final ReadWritablePeriod readWritablePeriod, final String s, int n, final Locale locale) {
            final int n2 = n;
            n = this.iBeforeParser.parseInto(readWritablePeriod, s, n, locale);
            if (n < 0) {
                return n;
            }
            boolean b = false;
            if (n > n2) {
                for (final String s2 : this.iParsedForms) {
                    if (s2 == null || s2.length() == 0 || s.regionMatches(true, n, s2, 0, s2.length())) {
                        n += s2.length();
                        b = true;
                        break;
                    }
                }
            }
            final int n3 = n;
            n = this.iAfterParser.parseInto(readWritablePeriod, s, n, locale);
            if (n < 0) {
                return n;
            }
            if (b && n == n3) {
                return ~n3;
            }
            if (n > n3 && !b && !this.iUseBefore) {
                return ~n3;
            }
            return n;
        }
        
        Separator finish(final PeriodPrinter iAfterPrinter, final PeriodParser iAfterParser) {
            this.iAfterPrinter = iAfterPrinter;
            this.iAfterParser = iAfterParser;
            return this;
        }
    }
    
    static class Composite implements PeriodPrinter, PeriodParser
    {
        private final PeriodPrinter[] iPrinters;
        private final PeriodParser[] iParsers;
        
        Composite(final List list) {
            final ArrayList list2 = new ArrayList();
            final ArrayList list3 = new ArrayList();
            this.decompose(list, list2, list3);
            if (list2.size() <= 0) {
                this.iPrinters = null;
            }
            else {
                this.iPrinters = (PeriodPrinter[])list2.toArray(new PeriodPrinter[list2.size()]);
            }
            if (list3.size() <= 0) {
                this.iParsers = null;
            }
            else {
                this.iParsers = (PeriodParser[])list3.toArray(new PeriodParser[list3.size()]);
            }
        }
        
        public int countFieldsToPrint(final ReadablePeriod readablePeriod, final int n, final Locale locale) {
            int n2 = 0;
            final PeriodPrinter[] iPrinters = this.iPrinters;
            for (int length = iPrinters.length; n2 < n && --length >= 0; n2 += iPrinters[length].countFieldsToPrint(readablePeriod, Integer.MAX_VALUE, locale)) {}
            return n2;
        }
        
        public int calculatePrintedLength(final ReadablePeriod readablePeriod, final Locale locale) {
            int n = 0;
            final PeriodPrinter[] iPrinters = this.iPrinters;
            int length = iPrinters.length;
            while (--length >= 0) {
                n += iPrinters[length].calculatePrintedLength(readablePeriod, locale);
            }
            return n;
        }
        
        public void printTo(final StringBuffer sb, final ReadablePeriod readablePeriod, final Locale locale) {
            final PeriodPrinter[] iPrinters = this.iPrinters;
            for (int length = iPrinters.length, i = 0; i < length; ++i) {
                iPrinters[i].printTo(sb, readablePeriod, locale);
            }
        }
        
        public void printTo(final Writer writer, final ReadablePeriod readablePeriod, final Locale locale) throws IOException {
            final PeriodPrinter[] iPrinters = this.iPrinters;
            for (int length = iPrinters.length, i = 0; i < length; ++i) {
                iPrinters[i].printTo(writer, readablePeriod, locale);
            }
        }
        
        public int parseInto(final ReadWritablePeriod readWritablePeriod, final String s, int into, final Locale locale) {
            final PeriodParser[] iParsers = this.iParsers;
            if (iParsers == null) {
                throw new UnsupportedOperationException();
            }
            for (int length = iParsers.length, n = 0; n < length && into >= 0; into = iParsers[n].parseInto(readWritablePeriod, s, into, locale), ++n) {}
            return into;
        }
        
        private void decompose(final List list, final List list2, final List list3) {
            for (int size = list.size(), i = 0; i < size; i += 2) {
                final Composite value = list.get(i);
                if (value instanceof PeriodPrinter) {
                    if (value instanceof Composite) {
                        this.addArrayToList(list2, value.iPrinters);
                    }
                    else {
                        list2.add(value);
                    }
                }
                final Composite value2 = list.get(i + 1);
                if (value2 instanceof PeriodParser) {
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
    
    interface PeriodFieldAffix
    {
        int calculatePrintedLength(final int p0);
        
        void printTo(final StringBuffer p0, final int p1);
        
        void printTo(final Writer p0, final int p1) throws IOException;
        
        int parse(final String p0, final int p1);
        
        int scan(final String p0, final int p1);
    }
}
