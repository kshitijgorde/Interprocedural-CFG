// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.helpers;

import java.io.IOException;
import java.io.Writer;
import org.apache.log4j.spi.ErrorHandler;
import java.io.FilterWriter;

public class QuietWriter extends FilterWriter
{
    protected ErrorHandler errorHandler;
    
    public QuietWriter(final Writer writer, final ErrorHandler errorHandler) {
        super(writer);
        this.setErrorHandler(errorHandler);
    }
    
    public void write(final String s) {
        try {
            super.out.write(s);
        }
        catch (IOException ex) {
            this.errorHandler.error("Failed to write [" + s + "].", ex, 1);
        }
    }
    
    public void flush() {
        try {
            super.out.flush();
        }
        catch (IOException ex) {
            this.errorHandler.error("Failed to flush writer,", ex, 2);
        }
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        if (errorHandler == null) {
            throw new IllegalArgumentException("Attempted to set null ErrorHandler.");
        }
        this.errorHandler = errorHandler;
    }
}
