// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.helpers.PatternConverter;

public class PatternLayout extends Layout
{
    public static final String DEFAULT_CONVERSION_PATTERN = "%m%n";
    public static final String TTCC_CONVERSION_PATTERN = "%r [%t] %p %c %x - %m%n";
    protected final int BUF_SIZE = 256;
    protected final int MAX_CAPACITY = 1024;
    private StringBuffer sbuf;
    private String pattern;
    private PatternConverter head;
    private String timezone;
    
    public PatternLayout() {
        this("%m%n");
    }
    
    public PatternLayout(final String pattern) {
        this.sbuf = new StringBuffer(256);
        this.pattern = pattern;
        this.head = this.createPatternParser((pattern == null) ? "%m%n" : pattern).parse();
    }
    
    public void setConversionPattern(final String pattern) {
        this.pattern = pattern;
        this.head = this.createPatternParser(pattern).parse();
    }
    
    public String getConversionPattern() {
        return this.pattern;
    }
    
    public void activateOptions() {
    }
    
    public boolean ignoresThrowable() {
        return true;
    }
    
    protected PatternParser createPatternParser(final String s) {
        return new PatternParser(s);
    }
    
    public String format(final LoggingEvent loggingEvent) {
        if (this.sbuf.capacity() > 1024) {
            this.sbuf = new StringBuffer(256);
        }
        else {
            this.sbuf.setLength(0);
        }
        for (PatternConverter patternConverter = this.head; patternConverter != null; patternConverter = patternConverter.next) {
            patternConverter.format(this.sbuf, loggingEvent);
        }
        return this.sbuf.toString();
    }
}
