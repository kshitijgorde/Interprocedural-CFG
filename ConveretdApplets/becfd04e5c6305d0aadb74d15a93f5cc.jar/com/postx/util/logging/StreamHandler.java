// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.util.logging;

import java.io.OutputStream;
import java.io.PrintStream;

public class StreamHandler extends Handler
{
    public static final String Ident = "$Id: StreamHandler.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    private PrintStream out;
    private Formatter fmt;
    
    public void close() {
        this.out.close();
    }
    
    public StreamHandler() {
        this.out = null;
        this.fmt = null;
    }
    
    public StreamHandler(final OutputStream outputStream, final Formatter fmt) {
        this.out = null;
        this.fmt = null;
        this.setOutputStream(outputStream);
        this.fmt = fmt;
    }
    
    protected void setOutputStream(final OutputStream outputStream) {
        this.out = (PrintStream)((outputStream instanceof PrintStream) ? outputStream : new PrintStream(outputStream));
    }
    
    public void publish(final LogRecord logRecord) {
        if (logRecord != null && this.out != null && this.fmt != null) {
            this.out.print(this.fmt.formatMessage(logRecord));
        }
    }
}
