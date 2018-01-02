// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.helpers;

import java.io.IOException;
import org.apache.log4j.spi.ErrorHandler;
import java.io.Writer;

public class CountingQuietWriter extends QuietWriter
{
    protected long count;
    
    public CountingQuietWriter(final Writer writer, final ErrorHandler errorHandler) {
        super(writer, errorHandler);
    }
    
    public void write(final String s) {
        try {
            super.out.write(s);
            this.count += s.length();
        }
        catch (IOException ex) {
            super.errorHandler.error("Write failure.", ex, 1);
        }
    }
    
    public long getCount() {
        return this.count;
    }
    
    public void setCount(final long count) {
        this.count = count;
    }
}
