import java.io.IOException;
import java.net.Socket;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public interface SocketFactory
{
    Socket createSocket(final String p0, final int p1, final Applet p2) throws IOException;
    
    Socket createSocket(final String p0, final int p1, final String[] p2) throws IOException;
}
