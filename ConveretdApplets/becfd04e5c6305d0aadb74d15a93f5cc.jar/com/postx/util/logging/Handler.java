// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.util.logging;

public abstract class Handler
{
    public static final String Ident = "$Id: Handler.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    
    abstract void close();
    
    abstract void publish(final LogRecord p0);
}
