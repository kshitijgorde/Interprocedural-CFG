// 
// Decompiled by Procyon v0.5.30
// 

package socket;

import java.io.IOException;

public class TimedOutException extends IOException
{
    public TimedOutException() {
    }
    
    public TimedOutException(final String message) {
        super(message);
    }
}
