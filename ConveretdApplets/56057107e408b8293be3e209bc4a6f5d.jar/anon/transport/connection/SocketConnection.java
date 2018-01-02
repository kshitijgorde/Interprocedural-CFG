// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection;

import java.net.SocketException;
import anon.transport.connection.util.ClosedOutputStream;
import anon.transport.connection.util.ClosedInputStream;
import java.io.IOException;
import anon.transport.address.TcpIpAddress;
import anon.transport.address.IAddress;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketConnection implements IStreamConnection
{
    private Socket m_underlyingSocket;
    private OutputStream m_outputStream;
    private InputStream m_inputStream;
    private int m_internalState;
    private IAddress m_localAddress;
    private IAddress m_remoteAddress;
    
    public SocketConnection(final Socket underlyingSocket) {
        this.m_localAddress = new TcpIpAddress(underlyingSocket.getLocalAddress(), underlyingSocket.getLocalPort());
        this.m_remoteAddress = new TcpIpAddress(underlyingSocket.getInetAddress(), underlyingSocket.getPort());
        try {
            this.m_underlyingSocket = underlyingSocket;
            this.m_inputStream = this.m_underlyingSocket.getInputStream();
            this.m_outputStream = this.m_underlyingSocket.getOutputStream();
            this.m_internalState = 1;
        }
        catch (IOException ex) {
            this.setCLOSE();
        }
    }
    
    private void setCLOSE() {
        this.m_internalState = 2;
        this.m_underlyingSocket = null;
        this.m_inputStream = ClosedInputStream.getNotCloseable();
        this.m_outputStream = ClosedOutputStream.getNotCloseable();
    }
    
    public InputStream getInputStream() {
        return this.m_inputStream;
    }
    
    public OutputStream getOutputStream() {
        return this.m_outputStream;
    }
    
    public synchronized int getTimeout() throws ConnectionException {
        if (this.m_internalState == 2) {
            throw new ConnectionException("Connection is already closed");
        }
        try {
            return this.m_underlyingSocket.getSoTimeout();
        }
        catch (SocketException ex) {
            throw new ConnectionException(ex);
        }
    }
    
    public synchronized void setTimeout(final int soTimeout) throws ConnectionException {
        if (this.m_internalState == 2) {
            throw new ConnectionException("Connection is already closed");
        }
        try {
            this.m_underlyingSocket.setSoTimeout(soTimeout);
        }
        catch (SocketException ex) {
            throw new ConnectionException(ex);
        }
    }
    
    public synchronized void close() throws IOException {
        if (this.m_internalState == 1) {
            this.m_underlyingSocket.close();
            this.setCLOSE();
        }
    }
    
    public int getCurrentState() {
        return this.m_internalState;
    }
    
    public IAddress getLocalAddress() {
        return this.m_localAddress;
    }
    
    public IAddress getRemoteAddress() {
        return this.m_remoteAddress;
    }
}
