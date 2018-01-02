// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index;

import java.io.IOException;

public class BadMagicException extends IOException
{
    private static final long serialVersionUID = -570930196733067056L;
    
    public BadMagicException() {
    }
    
    public BadMagicException(final String s) {
        super(s);
    }
}
