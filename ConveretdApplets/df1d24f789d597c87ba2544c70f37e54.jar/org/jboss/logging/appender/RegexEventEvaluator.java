// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging.appender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.spi.LoggingEvent;
import java.util.HashMap;
import org.apache.log4j.spi.TriggeringEventEvaluator;

public class RegexEventEvaluator implements TriggeringEventEvaluator
{
    private HashMap regexMap;
    
    public RegexEventEvaluator() {
        this.regexMap = new HashMap();
    }
    
    public boolean isTriggeringEvent(final LoggingEvent event) {
        final String regex = (String)event.getMDC("RegexEventEvaluator");
        boolean isTriggeringEvent = false;
        if (regex != null) {
            Pattern re = this.regexMap.get(regex);
            if (re == null) {
                re = Pattern.compile(regex);
                this.regexMap.put(regex, re);
            }
            if (re != null) {
                final String msg = event.getRenderedMessage();
                if (msg != null) {
                    final Matcher m = re.matcher(msg);
                    isTriggeringEvent = m.matches();
                }
            }
        }
        return isTriggeringEvent;
    }
}
