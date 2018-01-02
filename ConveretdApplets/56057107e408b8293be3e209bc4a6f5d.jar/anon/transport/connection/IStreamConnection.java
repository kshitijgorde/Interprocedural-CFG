// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection;

import java.io.OutputStream;
import java.io.InputStream;

public interface IStreamConnection extends IConnection
{
    InputStream getInputStream();
    
    OutputStream getOutputStream();
}
