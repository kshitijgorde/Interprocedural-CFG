// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.address;

import java.net.UnknownHostException;
import java.net.InetAddress;

public class TcpIpAddress implements IAddress
{
    public static final String TRANSPORT_IDENTIFIER = "tcpip";
    private static final String IP_PARAMETER = "ip-address";
    private static final String PORT_PARAMETER = "port";
    protected int m_port;
    protected InetAddress m_ipAddress;
    
    public TcpIpAddress(final String s, final int port) {
        try {
            this.m_ipAddress = InetAddress.getByName(s);
        }
        catch (UnknownHostException ex) {
            this.m_ipAddress = null;
        }
        this.m_port = port;
    }
    
    public TcpIpAddress(final InetAddress ipAddress, final int port) {
        this.m_ipAddress = ipAddress;
        this.m_port = port;
    }
    
    public TcpIpAddress(final Endpoint endpoint) throws AddressMappingException {
        final String parameter = endpoint.getParameter("ip-address");
        if (parameter == null) {
            throw new AddressMappingException("IP Parameter is missing");
        }
        try {
            this.m_ipAddress = InetAddress.getByName(parameter);
        }
        catch (UnknownHostException ex) {
            throw new AddressMappingException("IP-Address could not be parsed.");
        }
        final String parameter2 = endpoint.getParameter("port");
        if (parameter2 == null) {
            throw new AddressMappingException("Port Parameter is missing");
        }
        try {
            this.m_port = Integer.parseInt(parameter2);
        }
        catch (NumberFormatException ex2) {
            throw new AddressMappingException("Port could not be parsed.");
        }
    }
    
    public InetAddress getIPAddress() {
        return this.m_ipAddress;
    }
    
    public int getPort() {
        return this.m_port;
    }
    
    public String getHostname() {
        return this.m_ipAddress.getHostName();
    }
    
    public AddressParameter[] getAllParameters() {
        return new AddressParameter[] { new AddressParameter("ip-address", this.m_ipAddress.getHostAddress()), new AddressParameter("port", String.valueOf(this.m_port)) };
    }
    
    public String getTransportIdentifier() {
        return "tcpip";
    }
}
