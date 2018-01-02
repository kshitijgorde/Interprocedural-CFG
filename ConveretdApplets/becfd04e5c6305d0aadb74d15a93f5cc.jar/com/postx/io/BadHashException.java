// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.io;

import java.io.IOException;

public class BadHashException extends IOException
{
    public static final String Ident = "$Id: BadHashException.java,v 1.2 2011/01/10 05:13:52 blm Exp $";
    
    public BadHashException(final String s) {
        super(s);
    }
}
