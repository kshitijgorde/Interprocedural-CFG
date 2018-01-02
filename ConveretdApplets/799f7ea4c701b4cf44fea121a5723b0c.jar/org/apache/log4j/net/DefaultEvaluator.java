// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.net;

import org.apache.log4j.Priority;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.TriggeringEventEvaluator;

class DefaultEvaluator implements TriggeringEventEvaluator
{
    public boolean isTriggeringEvent(final LoggingEvent loggingEvent) {
        return loggingEvent.getLevel().isGreaterOrEqual(Level.ERROR);
    }
}
