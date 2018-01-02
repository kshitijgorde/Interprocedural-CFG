// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.InterruptedIOException;

public class ThreadInterruptedIOException extends InterruptedIOException
{
    public ThreadInterruptedIOException(final String s) {
        super(s);
    }
}
