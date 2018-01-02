// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.io;

import java.io.IOException;

public class EnvelopeDamagedException extends IOException
{
    public static final String Ident = "$Id: EnvelopeDamagedException.java,v 1.2 2011/01/10 05:13:52 blm Exp $";
    
    public EnvelopeDamagedException(final String s, final String s2) {
        super(s + " - " + s2);
    }
}
