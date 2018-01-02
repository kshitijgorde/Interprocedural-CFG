// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.IOException;

public class ProtocolNotSuppException extends IOException
{
    public ProtocolNotSuppException() {
    }
    
    public ProtocolNotSuppException(final String s) {
        super(s);
    }
}
