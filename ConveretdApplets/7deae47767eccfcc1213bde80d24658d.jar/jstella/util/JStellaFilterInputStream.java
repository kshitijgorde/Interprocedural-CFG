// 
// Decompiled by Procyon v0.5.30
// 

package jstella.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

public class JStellaFilterInputStream extends FilterInputStream
{
    public JStellaFilterInputStream(final InputStream aStream) {
        super(aStream);
    }
    
    public void close() throws IOException {
    }
    
    public void manuallyClose() throws IOException {
        super.close();
    }
}
