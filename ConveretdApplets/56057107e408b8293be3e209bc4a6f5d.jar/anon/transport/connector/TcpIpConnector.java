// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connector;

import anon.transport.connection.IConnection;
import anon.transport.address.IAddress;
import anon.transport.connection.ConnectionException;
import java.io.IOException;
import anon.transport.connection.CommunicationException;
import anon.transport.connection.SocketConnection;
import java.net.Socket;
import anon.transport.connection.IStreamConnection;
import anon.transport.address.TcpIpAddress;

public class TcpIpConnector implements IConnector
{
    public IStreamConnection connect(final TcpIpAddress tcpIpAddress) throws ConnectionException {
        try {
            return new SocketConnection(new Socket(tcpIpAddress.getIPAddress(), tcpIpAddress.getPort()));
        }
        catch (IOException ex) {
            throw new CommunicationException(ex);
        }
    }
    
    public IConnection connect(final IAddress address) throws ConnectionException {
        if (!(address instanceof TcpIpAddress)) {
            throw new IllegalArgumentException("Connector can only handel Address of type TcpIpAddress");
        }
        return null;
    }
}
