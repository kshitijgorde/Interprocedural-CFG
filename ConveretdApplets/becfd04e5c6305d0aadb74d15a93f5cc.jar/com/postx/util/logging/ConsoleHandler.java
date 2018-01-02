// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.util.logging;

import java.io.OutputStream;

public class ConsoleHandler extends StreamHandler
{
    public static final String Ident = "$Id: ConsoleHandler.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    
    public ConsoleHandler() {
        super(System.out, new SimpleFormatter());
    }
}
