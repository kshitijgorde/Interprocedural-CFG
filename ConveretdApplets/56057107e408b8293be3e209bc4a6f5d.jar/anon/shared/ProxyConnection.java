// 
// Decompiled by Procyon v0.5.30
// 

package anon.shared;

import anon.transport.address.TcpIpAddress;
import anon.transport.address.IAddress;
import anon.transport.connection.ConnectionException;
import java.net.SocketException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import anon.transport.connection.IStreamConnection;

public final class ProxyConnection implements IStreamConnection
{
    private Socket m_ioSocket;
    private InputStream m_In;
    private OutputStream m_Out;
    private int m_State;
    
    public ProxyConnection(final Socket ioSocket) throws Exception {
        this.m_ioSocket = ioSocket;
        this.m_State = 1;
        try {
            this.m_ioSocket.setSoTimeout(0);
        }
        catch (Exception ex2) {}
        try {
            this.m_In = this.m_ioSocket.getInputStream();
            this.m_Out = this.m_ioSocket.getOutputStream();
        }
        catch (Exception ex) {
            this.close();
            throw ex;
        }
    }
    
    public Socket getSocket() {
        return this.m_ioSocket;
    }
    
    public InputStream getInputStream() {
        return this.m_In;
    }
    
    public OutputStream getOutputStream() {
        return this.m_Out;
    }
    
    public void setSoTimeout(final int soTimeout) throws SocketException {
        this.m_ioSocket.setSoTimeout(soTimeout);
    }
    
    public void close() {
        try {
            this.m_In.close();
        }
        catch (Exception ex) {}
        try {
            this.m_Out.close();
        }
        catch (Exception ex2) {}
        try {
            this.m_ioSocket.close();
        }
        catch (Exception ex3) {}
        this.m_State = 2;
    }
    
    public int getCurrentState() {
        return this.m_State;
    }
    
    public int getTimeout() throws ConnectionException {
        try {
            return this.m_ioSocket.getSoTimeout();
        }
        catch (SocketException ex) {
            throw new ConnectionException(ex);
        }
    }
    
    public void setTimeout(final int soTimeout) throws ConnectionException {
        try {
            this.m_ioSocket.setSoTimeout(soTimeout);
        }
        catch (SocketException ex) {
            throw new ConnectionException(ex);
        }
    }
    
    public IAddress getLocalAddress() {
        return new TcpIpAddress(this.m_ioSocket.getLocalAddress(), this.m_ioSocket.getLocalPort());
    }
    
    public IAddress getRemoteAddress() {
        return new TcpIpAddress(this.m_ioSocket.getInetAddress(), this.m_ioSocket.getPort());
    }
}
