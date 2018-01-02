// 
// Decompiled by Procyon v0.5.30
// 

package forward;

import java.net.Socket;
import anon.transport.connection.ConnectionException;
import anon.transport.connector.SkypeConnector;
import anon.transport.address.SkypeAddress;
import anon.transport.address.TcpIpAddress;
import anon.transport.connection.IStreamConnection;
import anon.transport.address.IAddress;
import logging.LogHolder;
import logging.LogType;
import anon.infoservice.ListenerInterface;
import anon.infoservice.HTTPConnectionFactory;
import anon.shared.ProxyConnection;
import anon.infoservice.ImmutableProxyInterface;

public class ForwardUtils
{
    private static ForwardUtils ms_fuInstance;
    ImmutableProxyInterface m_proxyInterface;
    
    public static ForwardUtils getInstance() {
        if (ForwardUtils.ms_fuInstance == null) {
            ForwardUtils.ms_fuInstance = new ForwardUtils();
        }
        return ForwardUtils.ms_fuInstance;
    }
    
    public synchronized void setProxySettings(final ImmutableProxyInterface proxyInterface) {
        this.m_proxyInterface = proxyInterface;
    }
    
    public ProxyConnection createProxyConnection(final String s, final int n) {
        ProxyConnection proxyConnection = null;
        try {
            synchronized (this) {
                proxyConnection = new ProxyConnection(HTTPConnectionFactory.getInstance().createHTTPConnection(new ListenerInterface(s, n), this.m_proxyInterface).Connect());
            }
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.NET, ex);
        }
        return proxyConnection;
    }
    
    public IStreamConnection createForwardingConnection(final IAddress address) {
        if (address instanceof TcpIpAddress) {
            final TcpIpAddress tcpIpAddress = (TcpIpAddress)address;
            return this.createProxyConnection(tcpIpAddress.getHostname(), tcpIpAddress.getPort());
        }
        if (address instanceof SkypeAddress) {
            LogHolder.log(7, LogType.NET, "forwardUtils:createconnection() start connection to skype forwarder");
            final SkypeConnector skypeConnector = new SkypeConnector();
            if (skypeConnector != null) {
                LogHolder.log(7, LogType.NET, "forwardUtils:createconnection() skype conector object created");
            }
            else {
                LogHolder.log(7, LogType.NET, "forwardUtils:createconnection() skype conector object NOT created");
            }
            try {
                return skypeConnector.connect((SkypeAddress)address);
            }
            catch (ConnectionException ex) {
                LogHolder.log(3, LogType.TRANSPORT, "Unable to create Skype Forwarding Connection. Cause: " + ex.getMessage());
                return null;
            }
        }
        if (address instanceof LocalAddress) {
            try {
                return (IStreamConnection)LocalForwarder.getConnector().connect(address);
            }
            catch (ConnectionException ex2) {
                LogHolder.log(3, LogType.TRANSPORT, "unable to contact local forwarder. " + ex2.getMessage());
            }
        }
        return null;
    }
    
    public Socket createConnection(final String s, final int n) {
        final ProxyConnection proxyConnection = this.createProxyConnection(s, n);
        Socket socket = null;
        if (proxyConnection != null) {
            socket = proxyConnection.getSocket();
        }
        return socket;
    }
    
    static {
        ForwardUtils.ms_fuInstance = null;
    }
}
