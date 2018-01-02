// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import org.apache.log4j.spi.ErrorHandler;
import java.io.IOException;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import org.apache.log4j.helpers.QuietWriter;

public class WriterAppender extends AppenderSkeleton
{
    protected boolean immediateFlush;
    protected String encoding;
    protected QuietWriter qw;
    
    public WriterAppender() {
        this.immediateFlush = true;
    }
    
    public WriterAppender(final Layout layout, final OutputStream outputStream) {
        this(layout, new OutputStreamWriter(outputStream));
    }
    
    public WriterAppender(final Layout layout, final Writer writer) {
        this.immediateFlush = true;
        super.layout = layout;
        this.setWriter(writer);
    }
    
    public void setImmediateFlush(final boolean immediateFlush) {
        this.immediateFlush = immediateFlush;
    }
    
    public boolean getImmediateFlush() {
        return this.immediateFlush;
    }
    
    public void activateOptions() {
    }
    
    public void append(final LoggingEvent loggingEvent) {
        if (!this.checkEntryConditions()) {
            return;
        }
        this.subAppend(loggingEvent);
    }
    
    protected boolean checkEntryConditions() {
        if (super.closed) {
            LogLog.warn("Not allowed to write to a closed appender.");
            return false;
        }
        if (this.qw == null) {
            super.errorHandler.error("No output stream or file set for the appender named [" + super.name + "].");
            return false;
        }
        if (super.layout == null) {
            super.errorHandler.error("No layout set for the appender named [" + super.name + "].");
            return false;
        }
        return true;
    }
    
    public synchronized void close() {
        if (super.closed) {
            return;
        }
        super.closed = true;
        this.writeFooter();
        this.reset();
    }
    
    protected void closeWriter() {
        if (this.qw != null) {
            try {
                this.qw.close();
            }
            catch (IOException ex) {
                LogLog.error("Could not close " + this.qw, ex);
            }
        }
    }
    
    protected OutputStreamWriter createWriter(final OutputStream outputStream) {
        OutputStreamWriter outputStreamWriter = null;
        final String encoding = this.getEncoding();
        if (encoding != null) {
            try {
                outputStreamWriter = new OutputStreamWriter(outputStream, encoding);
            }
            catch (IOException ex) {
                LogLog.warn("Error initializing output writer.");
                LogLog.warn("Unsupported encoding?");
            }
        }
        if (outputStreamWriter == null) {
            outputStreamWriter = new OutputStreamWriter(outputStream);
        }
        return outputStreamWriter;
    }
    
    public String getEncoding() {
        return this.encoding;
    }
    
    public void setEncoding(final String encoding) {
        this.encoding = encoding;
    }
    
    public synchronized void setErrorHandler(final ErrorHandler errorHandler) {
        if (errorHandler == null) {
            LogLog.warn("You have tried to set a null error-handler.");
        }
        else {
            super.errorHandler = errorHandler;
            if (this.qw != null) {
                this.qw.setErrorHandler(errorHandler);
            }
        }
    }
    
    public synchronized void setWriter(final Writer writer) {
        this.reset();
        this.qw = new QuietWriter(writer, super.errorHandler);
        this.writeHeader();
    }
    
    protected void subAppend(final LoggingEvent loggingEvent) {
        this.qw.write(super.layout.format(loggingEvent));
        if (super.layout.ignoresThrowable()) {
            final String[] throwableStrRep = loggingEvent.getThrowableStrRep();
            if (throwableStrRep != null) {
                for (int length = throwableStrRep.length, i = 0; i < length; ++i) {
                    this.qw.write(throwableStrRep[i]);
                    this.qw.write(Layout.LINE_SEP);
                }
            }
        }
        if (this.immediateFlush) {
            this.qw.flush();
        }
    }
    
    public boolean requiresLayout() {
        return true;
    }
    
    protected void reset() {
        this.closeWriter();
        this.qw = null;
    }
    
    protected void writeFooter() {
        if (super.layout != null) {
            final String footer = super.layout.getFooter();
            if (footer != null && this.qw != null) {
                this.qw.write(footer);
                this.qw.flush();
            }
        }
    }
    
    protected void writeHeader() {
        if (super.layout != null) {
            final String header = super.layout.getHeader();
            if (header != null && this.qw != null) {
                this.qw.write(header);
            }
        }
    }
}
