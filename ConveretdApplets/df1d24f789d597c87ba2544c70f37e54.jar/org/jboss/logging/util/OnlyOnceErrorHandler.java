// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging.util;

import org.apache.log4j.Appender;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.Logger;
import java.io.PrintStream;
import org.apache.log4j.spi.ErrorHandler;

public class OnlyOnceErrorHandler implements ErrorHandler
{
    final String WARN_PREFIX = "log4j warning: ";
    final String ERROR_PREFIX = "log4j error: ";
    boolean firstTime;
    static PrintStream output;
    
    public OnlyOnceErrorHandler() {
        this.firstTime = true;
    }
    
    public static void setOutput(final PrintStream out) {
        OnlyOnceErrorHandler.output = out;
    }
    
    public void setLogger(final Logger logger) {
    }
    
    public void activateOptions() {
    }
    
    public void error(final String message, final Exception e, final int errorCode) {
        this.error(message, e, errorCode, null);
    }
    
    public void error(final String message, final Exception e, final int errorCode, final LoggingEvent event) {
        if (this.firstTime) {
            OnlyOnceErrorHandler.output.println("log4j error: " + message);
            e.printStackTrace(OnlyOnceErrorHandler.output);
            this.firstTime = false;
        }
    }
    
    public void error(final String message) {
        if (this.firstTime) {
            OnlyOnceErrorHandler.output.println("log4j error: " + message);
            this.firstTime = false;
        }
    }
    
    public void setAppender(final Appender appender) {
    }
    
    public void setBackupAppender(final Appender appender) {
    }
    
    static {
        OnlyOnceErrorHandler.output = System.err;
    }
}
