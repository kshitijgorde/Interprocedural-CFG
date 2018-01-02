// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.util.logging;

public abstract class Formatter
{
    public static final String Ident = "$Id: Formatter.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    
    abstract String format(final LogRecord p0);
    
    public String formatMessage(final LogRecord logRecord) {
        return this.format(logRecord);
    }
}
