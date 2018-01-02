// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging.layout;

import org.apache.log4j.NDC;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.helpers.FormattingInfo;
import org.apache.log4j.helpers.PatternConverter;

public class ThreadNDCConverter extends PatternConverter
{
    public ThreadNDCConverter(final FormattingInfo formattingInfo) {
        super(formattingInfo);
    }
    
    protected String convert(final LoggingEvent loggingEvent) {
        final Object val = NDC.get();
        String strVal = null;
        if (val != null) {
            strVal = val.toString();
        }
        return strVal;
    }
}
