// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.helpers;

import org.apache.log4j.spi.LocationInfo;
import java.util.Date;
import org.apache.log4j.spi.LoggingEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.log4j.Layout;

public class PatternParser
{
    private static final char ESCAPE_CHAR = '%';
    private static final int LITERAL_STATE = 0;
    private static final int CONVERTER_STATE = 1;
    private static final int MINUS_STATE = 2;
    private static final int DOT_STATE = 3;
    private static final int MIN_STATE = 4;
    private static final int MAX_STATE = 5;
    static final int FULL_LOCATION_CONVERTER = 1000;
    static final int METHOD_LOCATION_CONVERTER = 1001;
    static final int CLASS_LOCATION_CONVERTER = 1002;
    static final int LINE_LOCATION_CONVERTER = 1003;
    static final int FILE_LOCATION_CONVERTER = 1004;
    static final int RELATIVE_TIME_CONVERTER = 2000;
    static final int THREAD_CONVERTER = 2001;
    static final int LEVEL_CONVERTER = 2002;
    static final int NDC_CONVERTER = 2003;
    static final int MESSAGE_CONVERTER = 2004;
    int state;
    protected StringBuffer currentLiteral;
    protected int patternLength;
    protected int i;
    PatternConverter head;
    PatternConverter tail;
    protected FormattingInfo formattingInfo;
    protected String pattern;
    static /* synthetic */ Class class$java$text$DateFormat;
    
    public PatternParser(final String pattern) {
        this.currentLiteral = new StringBuffer(32);
        this.formattingInfo = new FormattingInfo();
        this.pattern = pattern;
        this.patternLength = pattern.length();
        this.state = 0;
    }
    
    private void addToList(final PatternConverter patternConverter) {
        if (this.head == null) {
            this.tail = patternConverter;
            this.head = patternConverter;
        }
        else {
            this.tail.next = patternConverter;
            this.tail = patternConverter;
        }
    }
    
    protected String extractOption() {
        if (this.i < this.patternLength && this.pattern.charAt(this.i) == '{') {
            final int index = this.pattern.indexOf(125, this.i);
            if (index > this.i) {
                final String substring = this.pattern.substring(this.i + 1, index);
                this.i = index + 1;
                return substring;
            }
        }
        return null;
    }
    
    protected int extractPrecisionOption() {
        final String option = this.extractOption();
        int int1 = 0;
        if (option != null) {
            try {
                int1 = Integer.parseInt(option);
                if (int1 <= 0) {
                    LogLog.error("Precision option (" + option + ") isn't a positive integer.");
                    int1 = 0;
                }
            }
            catch (NumberFormatException ex) {
                LogLog.error("Category option \"" + option + "\" not a decimal integer.", ex);
            }
        }
        return int1;
    }
    
    public PatternConverter parse() {
        this.i = 0;
        while (this.i < this.patternLength) {
            final char char1 = this.pattern.charAt(this.i++);
            switch (this.state) {
                case 0: {
                    if (this.i == this.patternLength) {
                        this.currentLiteral.append(char1);
                        continue;
                    }
                    if (char1 != '%') {
                        this.currentLiteral.append(char1);
                        continue;
                    }
                    switch (this.pattern.charAt(this.i)) {
                        case '%': {
                            this.currentLiteral.append(char1);
                            ++this.i;
                            continue;
                        }
                        case 'n': {
                            this.currentLiteral.append(Layout.LINE_SEP);
                            ++this.i;
                            continue;
                        }
                        default: {
                            if (this.currentLiteral.length() != 0) {
                                this.addToList(new LiteralPatternConverter(this.currentLiteral.toString()));
                            }
                            this.currentLiteral.setLength(0);
                            this.currentLiteral.append(char1);
                            this.state = 1;
                            this.formattingInfo.reset();
                            continue;
                        }
                    }
                    break;
                }
                case 1: {
                    this.currentLiteral.append(char1);
                    switch (char1) {
                        case '-': {
                            this.formattingInfo.leftAlign = true;
                            continue;
                        }
                        case '.': {
                            this.state = 3;
                            continue;
                        }
                        default: {
                            if (char1 >= '0' && char1 <= '9') {
                                this.formattingInfo.min = char1 - '0';
                                this.state = 4;
                                continue;
                            }
                            this.finalizeConverter(char1);
                            continue;
                        }
                    }
                    break;
                }
                case 4: {
                    this.currentLiteral.append(char1);
                    if (char1 >= '0' && char1 <= '9') {
                        this.formattingInfo.min = this.formattingInfo.min * 10 + (char1 - '0');
                        continue;
                    }
                    if (char1 == '.') {
                        this.state = 3;
                        continue;
                    }
                    this.finalizeConverter(char1);
                    continue;
                }
                case 3: {
                    this.currentLiteral.append(char1);
                    if (char1 >= '0' && char1 <= '9') {
                        this.formattingInfo.max = char1 - '0';
                        this.state = 5;
                        continue;
                    }
                    LogLog.error("Error occured in position " + this.i + ".\n Was expecting digit, instead got char \"" + char1 + "\".");
                    this.state = 0;
                    continue;
                }
                case 5: {
                    this.currentLiteral.append(char1);
                    if (char1 >= '0' && char1 <= '9') {
                        this.formattingInfo.max = this.formattingInfo.max * 10 + (char1 - '0');
                        continue;
                    }
                    this.finalizeConverter(char1);
                    this.state = 0;
                    continue;
                }
                default: {
                    continue;
                }
            }
        }
        if (this.currentLiteral.length() != 0) {
            this.addToList(new LiteralPatternConverter(this.currentLiteral.toString()));
        }
        return this.head;
    }
    
    protected void finalizeConverter(final char c) {
        PatternConverter patternConverter = null;
        switch (c) {
            case 'c': {
                patternConverter = new CategoryPatternConverter(this.formattingInfo, this.extractPrecisionOption());
                this.currentLiteral.setLength(0);
                break;
            }
            case 'C': {
                patternConverter = new ClassNamePatternConverter(this.formattingInfo, this.extractPrecisionOption());
                this.currentLiteral.setLength(0);
                break;
            }
            case 'd': {
                String s = "ISO8601";
                final String option = this.extractOption();
                if (option != null) {
                    s = option;
                }
                DateFormat dateFormat;
                if (s.equalsIgnoreCase("ISO8601")) {
                    dateFormat = new ISO8601DateFormat();
                }
                else if (s.equalsIgnoreCase("ABSOLUTE")) {
                    dateFormat = new AbsoluteTimeDateFormat();
                }
                else if (s.equalsIgnoreCase("DATE")) {
                    dateFormat = new DateTimeDateFormat();
                }
                else {
                    try {
                        dateFormat = new SimpleDateFormat(s);
                    }
                    catch (IllegalArgumentException ex) {
                        LogLog.error("Could not instantiate SimpleDateFormat with " + s, ex);
                        dateFormat = (DateFormat)OptionConverter.instantiateByClassName("org.apache.log4j.helpers.ISO8601DateFormat", (PatternParser.class$java$text$DateFormat == null) ? (PatternParser.class$java$text$DateFormat = class$("java.text.DateFormat")) : PatternParser.class$java$text$DateFormat, null);
                    }
                }
                patternConverter = new DatePatternConverter(this.formattingInfo, dateFormat);
                this.currentLiteral.setLength(0);
                break;
            }
            case 'F': {
                patternConverter = new LocationPatternConverter(this.formattingInfo, 1004);
                this.currentLiteral.setLength(0);
                break;
            }
            case 'l': {
                patternConverter = new LocationPatternConverter(this.formattingInfo, 1000);
                this.currentLiteral.setLength(0);
                break;
            }
            case 'L': {
                patternConverter = new LocationPatternConverter(this.formattingInfo, 1003);
                this.currentLiteral.setLength(0);
                break;
            }
            case 'm': {
                patternConverter = new BasicPatternConverter(this.formattingInfo, 2004);
                this.currentLiteral.setLength(0);
                break;
            }
            case 'M': {
                patternConverter = new LocationPatternConverter(this.formattingInfo, 1001);
                this.currentLiteral.setLength(0);
                break;
            }
            case 'p': {
                patternConverter = new BasicPatternConverter(this.formattingInfo, 2002);
                this.currentLiteral.setLength(0);
                break;
            }
            case 'r': {
                patternConverter = new BasicPatternConverter(this.formattingInfo, 2000);
                this.currentLiteral.setLength(0);
                break;
            }
            case 't': {
                patternConverter = new BasicPatternConverter(this.formattingInfo, 2001);
                this.currentLiteral.setLength(0);
                break;
            }
            case 'x': {
                patternConverter = new BasicPatternConverter(this.formattingInfo, 2003);
                this.currentLiteral.setLength(0);
                break;
            }
            case 'X': {
                patternConverter = new MDCPatternConverter(this.formattingInfo, this.extractOption());
                this.currentLiteral.setLength(0);
                break;
            }
            default: {
                LogLog.error("Unexpected char [" + c + "] at position " + this.i + " in conversion patterrn.");
                patternConverter = new LiteralPatternConverter(this.currentLiteral.toString());
                this.currentLiteral.setLength(0);
                break;
            }
        }
        this.addConverter(patternConverter);
    }
    
    protected void addConverter(final PatternConverter patternConverter) {
        this.currentLiteral.setLength(0);
        this.addToList(patternConverter);
        this.state = 0;
        this.formattingInfo.reset();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    private static class BasicPatternConverter extends PatternConverter
    {
        int type;
        
        BasicPatternConverter(final FormattingInfo formattingInfo, final int type) {
            super(formattingInfo);
            this.type = type;
        }
        
        public String convert(final LoggingEvent loggingEvent) {
            switch (this.type) {
                case 2000: {
                    return Long.toString(loggingEvent.timeStamp - LoggingEvent.getStartTime());
                }
                case 2001: {
                    return loggingEvent.getThreadName();
                }
                case 2002: {
                    return loggingEvent.getLevel().toString();
                }
                case 2003: {
                    return loggingEvent.getNDC();
                }
                case 2004: {
                    return loggingEvent.getRenderedMessage();
                }
                default: {
                    return null;
                }
            }
        }
    }
    
    private static class LiteralPatternConverter extends PatternConverter
    {
        private String literal;
        
        LiteralPatternConverter(final String literal) {
            this.literal = literal;
        }
        
        public final void format(final StringBuffer sb, final LoggingEvent loggingEvent) {
            sb.append(this.literal);
        }
        
        public String convert(final LoggingEvent loggingEvent) {
            return this.literal;
        }
    }
    
    private static class DatePatternConverter extends PatternConverter
    {
        private DateFormat df;
        private Date date;
        
        DatePatternConverter(final FormattingInfo formattingInfo, final DateFormat df) {
            super(formattingInfo);
            this.date = new Date();
            this.df = df;
        }
        
        public String convert(final LoggingEvent loggingEvent) {
            this.date.setTime(loggingEvent.timeStamp);
            String format = null;
            try {
                format = this.df.format(this.date);
            }
            catch (Exception ex) {
                LogLog.error("Error occured while converting date.", ex);
            }
            return format;
        }
    }
    
    private static class MDCPatternConverter extends PatternConverter
    {
        private String key;
        
        MDCPatternConverter(final FormattingInfo formattingInfo, final String key) {
            super(formattingInfo);
            this.key = key;
        }
        
        public String convert(final LoggingEvent loggingEvent) {
            final Object mdc = loggingEvent.getMDC(this.key);
            if (mdc == null) {
                return null;
            }
            return mdc.toString();
        }
    }
    
    private class LocationPatternConverter extends PatternConverter
    {
        int type;
        
        LocationPatternConverter(final FormattingInfo formattingInfo, final int type) {
            super(formattingInfo);
            this.type = type;
        }
        
        public String convert(final LoggingEvent loggingEvent) {
            final LocationInfo locationInformation = loggingEvent.getLocationInformation();
            switch (this.type) {
                case 1000: {
                    return locationInformation.fullInfo;
                }
                case 1001: {
                    return locationInformation.getMethodName();
                }
                case 1003: {
                    return locationInformation.getLineNumber();
                }
                case 1004: {
                    return locationInformation.getFileName();
                }
                default: {
                    return null;
                }
            }
        }
    }
    
    private abstract static class NamedPatternConverter extends PatternConverter
    {
        int precision;
        
        NamedPatternConverter(final FormattingInfo formattingInfo, final int precision) {
            super(formattingInfo);
            this.precision = precision;
        }
        
        abstract String getFullyQualifiedName(final LoggingEvent p0);
        
        public String convert(final LoggingEvent loggingEvent) {
            final String fullyQualifiedName = this.getFullyQualifiedName(loggingEvent);
            if (this.precision <= 0) {
                return fullyQualifiedName;
            }
            final int length = fullyQualifiedName.length();
            int lastIndex = length - 1;
            for (int i = this.precision; i > 0; --i) {
                lastIndex = fullyQualifiedName.lastIndexOf(46, lastIndex - 1);
                if (lastIndex == -1) {
                    return fullyQualifiedName;
                }
            }
            return fullyQualifiedName.substring(lastIndex + 1, length);
        }
    }
    
    private class ClassNamePatternConverter extends NamedPatternConverter
    {
        ClassNamePatternConverter(final FormattingInfo formattingInfo, final int n) {
            super(formattingInfo, n);
        }
        
        String getFullyQualifiedName(final LoggingEvent loggingEvent) {
            return loggingEvent.getLocationInformation().getClassName();
        }
    }
    
    private class CategoryPatternConverter extends NamedPatternConverter
    {
        CategoryPatternConverter(final FormattingInfo formattingInfo, final int n) {
            super(formattingInfo, n);
        }
        
        String getFullyQualifiedName(final LoggingEvent loggingEvent) {
            return loggingEvent.getLoggerName();
        }
    }
}
