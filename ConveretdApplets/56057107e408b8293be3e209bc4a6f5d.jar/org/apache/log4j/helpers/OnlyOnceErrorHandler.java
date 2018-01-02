// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.helpers;

import org.apache.log4j.Appender;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.ErrorHandler;

public class OnlyOnceErrorHandler implements ErrorHandler
{
    final String WARN_PREFIX = "log4j warning: ";
    final String ERROR_PREFIX = "log4j error: ";
    boolean firstTime;
    
    public OnlyOnceErrorHandler() {
        this.firstTime = true;
    }
    
    public void setLogger(final Logger logger) {
    }
    
    public void activateOptions() {
    }
    
    public void error(final String s, final Exception ex, final int n) {
        this.error(s, ex, n, null);
    }
    
    public void error(final String s, final Exception ex, final int n, final LoggingEvent loggingEvent) {
        if (this.firstTime) {
            LogLog.error(s, ex);
            this.firstTime = false;
        }
    }
    
    public void error(final String s) {
        if (this.firstTime) {
            LogLog.error(s);
            this.firstTime = false;
        }
    }
    
    public void setAppender(final Appender appender) {
    }
    
    public void setBackupAppender(final Appender appender) {
    }
}
