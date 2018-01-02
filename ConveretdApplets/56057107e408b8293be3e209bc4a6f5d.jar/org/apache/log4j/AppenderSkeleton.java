// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.helpers.OnlyOnceErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.OptionHandler;

public abstract class AppenderSkeleton implements Appender, OptionHandler
{
    protected Layout layout;
    protected String name;
    protected Priority threshold;
    protected ErrorHandler errorHandler;
    protected Filter headFilter;
    protected Filter tailFilter;
    protected boolean closed;
    
    public AppenderSkeleton() {
        this.errorHandler = new OnlyOnceErrorHandler();
        this.closed = false;
    }
    
    public void activateOptions() {
    }
    
    public void addFilter(final Filter filter) {
        if (this.headFilter == null) {
            this.tailFilter = filter;
            this.headFilter = filter;
        }
        else {
            this.tailFilter.next = filter;
            this.tailFilter = filter;
        }
    }
    
    protected abstract void append(final LoggingEvent p0);
    
    public void clearFilters() {
        final Filter filter = null;
        this.tailFilter = filter;
        this.headFilter = filter;
    }
    
    public void finalize() {
        if (this.closed) {
            return;
        }
        LogLog.debug("Finalizing appender named [" + this.name + "].");
        this.close();
    }
    
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }
    
    public Filter getFilter() {
        return this.headFilter;
    }
    
    public final Filter getFirstFilter() {
        return this.headFilter;
    }
    
    public Layout getLayout() {
        return this.layout;
    }
    
    public final String getName() {
        return this.name;
    }
    
    public Priority getThreshold() {
        return this.threshold;
    }
    
    public boolean isAsSevereAsThreshold(final Priority priority) {
        return this.threshold == null || priority.isGreaterOrEqual(this.threshold);
    }
    
    public synchronized void doAppend(final LoggingEvent loggingEvent) {
        if (this.closed) {
            LogLog.error("Attempted to append to closed appender named [" + this.name + "].");
            return;
        }
        if (!this.isAsSevereAsThreshold(loggingEvent.getLevel())) {
            return;
        }
        Filter filter = this.headFilter;
    Label_0101:
        while (filter != null) {
            switch (filter.decide(loggingEvent)) {
                case -1: {
                    return;
                }
                case 1: {
                    break Label_0101;
                }
                case 0: {
                    filter = filter.next;
                }
                default: {
                    continue;
                }
            }
        }
        this.append(loggingEvent);
    }
    
    public synchronized void setErrorHandler(final ErrorHandler errorHandler) {
        if (errorHandler == null) {
            LogLog.warn("You have tried to set a null error-handler.");
        }
        else {
            this.errorHandler = errorHandler;
        }
    }
    
    public void setLayout(final Layout layout) {
        this.layout = layout;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setThreshold(final Priority threshold) {
        this.threshold = threshold;
    }
    
    public abstract boolean requiresLayout();
    
    public abstract void close();
}
