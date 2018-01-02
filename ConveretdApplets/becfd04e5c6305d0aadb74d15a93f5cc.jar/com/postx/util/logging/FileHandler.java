// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.util.logging;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;

public class FileHandler extends StreamHandler
{
    public static final String Ident = "$Id: FileHandler.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    
    public FileHandler(final String s, final boolean b) throws IOException, SecurityException {
        super(new FileOutputStream(s, b), new SimpleFormatter());
    }
}
