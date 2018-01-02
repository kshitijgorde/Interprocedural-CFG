// 
// Decompiled by Procyon v0.5.30
// 

package irc.security;

import java.net.InetAddress;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.net.ServerSocket;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.Socket;

public interface SecuredProvider
{
    Socket getSocket(final String p0, final Integer p1) throws UnknownHostException, IOException;
    
    ServerSocket getServerSocket(final Integer p0) throws IOException;
    
    FileInputStream getFileInputStream(final File p0) throws IOException;
    
    FileOutputStream getFileOutputStream(final File p0) throws IOException;
    
    Integer getFileSize(final File p0);
    
    File getLoadFile(final String p0);
    
    File getSaveFile(final String p0);
    
    File getSaveFile(final String p0, final String p1);
    
    InetAddress getLocalHost() throws UnknownHostException;
    
    String resolve(final InetAddress p0);
    
    boolean tryProvider();
    
    String getName();
}
