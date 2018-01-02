// 
// Decompiled by Procyon v0.5.30
// 

package anon;

import java.io.OutputStream;
import java.io.InputStream;

public interface AnonChannel
{
    public static final int HTTP = 0;
    public static final int SOCKS = 1;
    public static final int SMTP = 2;
    
    InputStream getInputStream();
    
    OutputStream getOutputStream();
    
    int getOutputBlockSize();
    
    void close();
    
    boolean isClosed();
}
