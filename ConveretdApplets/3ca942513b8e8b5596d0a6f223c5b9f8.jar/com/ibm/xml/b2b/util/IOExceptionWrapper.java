// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

import java.io.IOException;

public class IOExceptionWrapper extends RuntimeException
{
    private IOException ex;
    
    public IOExceptionWrapper(final IOException ex) {
        this.ex = ex;
    }
    
    public IOException getIOException() {
        return this.ex;
    }
}
