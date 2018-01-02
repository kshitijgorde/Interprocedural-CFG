// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging.layout;

import org.apache.log4j.MDC;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.helpers.FormattingInfo;
import org.apache.log4j.helpers.PatternConverter;

public class ThreadMDCConverter extends PatternConverter
{
    private String key;
    
    public ThreadMDCConverter(final FormattingInfo formattingInfo, final String key) {
        super(formattingInfo);
        this.key = key;
    }
    
    protected String convert(final LoggingEvent loggingEvent) {
        final Object val = MDC.get(this.key);
        String strVal = null;
        if (val != null) {
            strVal = val.toString();
        }
        return strVal;
    }
}
