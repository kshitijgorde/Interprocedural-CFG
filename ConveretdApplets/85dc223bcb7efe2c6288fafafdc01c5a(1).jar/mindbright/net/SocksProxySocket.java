// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.net;

import java.io.OutputStream;
import java.io.InputStream;
import java.net.SocketException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.Socket;

public class SocksProxySocket extends Socket
{
    private static final String[] replyErrorV5;
    private static final String[] replyErrorV4;
    private String proxyHost;
    private int proxyPort;
    private String targetHost;
    private int targetPort;
    String serverDesc;
    
    public String getServerDesc() {
        return this.serverDesc;
    }
    
    private SocksProxySocket(final String targetHost, final int targetPort, final String proxyHost, final int proxyPort) throws IOException, UnknownHostException {
        super(proxyHost, proxyPort);
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.targetHost = targetHost;
        this.targetPort = targetPort;
    }
    
    public static SocksProxySocket getSocks4Proxy(final String targetHost, final int targetPort, final String proxyHost, final int proxyPort, final String userId) throws IOException, UnknownHostException {
        final SocksProxySocket proxySocket = new SocksProxySocket(targetHost, targetPort, proxyHost, proxyPort);
        try {
            final InputStream proxyIn = proxySocket.getInputStream();
            final OutputStream proxyOut = proxySocket.getOutputStream();
            final InetAddress hostAddr = InetAddress.getByName(targetHost);
            proxyOut.write(4);
            proxyOut.write(1);
            proxyOut.write(targetPort >>> 8 & 0xFF);
            proxyOut.write(targetPort & 0xFF);
            proxyOut.write(hostAddr.getAddress());
            proxyOut.write(userId.getBytes());
            proxyOut.write(0);
            proxyOut.flush();
            final int res = proxyIn.read();
            if (res == -1) {
                throw new IOException("SOCKS4 server " + proxyHost + ":" + proxyPort + " disconnected");
            }
            if (res != 0) {
                throw new IOException("Invalid response from SOCKS4 server (" + res + ") " + proxyHost + ":" + proxyPort);
            }
            final int code = proxyIn.read();
            if (code != 90) {
                if (code > 90 && code < 93) {
                    throw new IOException("SOCKS4 server unable to connect, reason: " + SocksProxySocket.replyErrorV4[code - 91]);
                }
                throw new IOException("SOCKS4 server unable to connect, reason: " + code);
            }
            else {
                final byte[] data = new byte[6];
                if (proxyIn.read(data, 0, 6) != 6) {
                    throw new IOException("SOCKS4 error reading destination address/port");
                }
                proxySocket.serverDesc = data[2] + "." + data[3] + "." + data[4] + "." + data[5] + ":" + (data[0] << 8 | data[1]);
            }
        }
        catch (SocketException e) {
            throw new SocketException("Error communicating with SOCKS4 server " + proxyHost + ":" + proxyPort + ", " + e.getMessage());
        }
        return proxySocket;
    }
    
    public static SocksProxySocket getSocks5Proxy(final String targetHost, final int targetPort, final String proxyHost, final int proxyPort, final boolean localLookup, final ProxyAuthenticator authenticator) throws IOException, UnknownHostException {
        final SocksProxySocket proxySocket = new SocksProxySocket(targetHost, targetPort, proxyHost, proxyPort);
        try {
            final InputStream proxyIn = proxySocket.getInputStream();
            final OutputStream proxyOut = proxySocket.getOutputStream();
            byte[] request = { 5, 2, 0, 2 };
            final byte[] reply = new byte[2];
            proxyOut.write(request);
            proxyOut.flush();
            int res = proxyIn.read();
            if (res == -1) {
                throw new IOException("SOCKS5 server " + proxyHost + ":" + proxyPort + " disconnected");
            }
            if (res != 5) {
                throw new IOException("Invalid response from SOCKS5 server (" + res + ") " + proxyHost + ":" + proxyPort);
            }
            final int method = proxyIn.read();
            switch (method) {
                case 0: {
                    break;
                }
                case 2: {
                    doAuthentication(proxyIn, proxyOut, authenticator, proxyHost, proxyPort);
                    break;
                }
                default: {
                    throw new IOException("SOCKS5 server does not support our authentication methods");
                }
            }
            if (localLookup) {
                InetAddress hostAddr;
                try {
                    hostAddr = InetAddress.getByName(targetHost);
                }
                catch (UnknownHostException e2) {
                    throw new IOException("Can't do local lookup on: " + targetHost + ", try socks5 without local lookup");
                }
                request = new byte[] { 5, 1, 0, 1 };
                proxyOut.write(request);
                proxyOut.write(hostAddr.getAddress());
            }
            else {
                request = new byte[] { 5, 1, 0, 3 };
                proxyOut.write(request);
                proxyOut.write(targetHost.length());
                proxyOut.write(targetHost.getBytes());
            }
            proxyOut.write(targetPort >>> 8 & 0xFF);
            proxyOut.write(targetPort & 0xFF);
            proxyOut.flush();
            res = proxyIn.read();
            if (res != 5) {
                throw new IOException("Invalid response from SOCKS5 server (" + res + ") " + proxyHost + ":" + proxyPort);
            }
            final int status = proxyIn.read();
            if (status != 0) {
                if (status > 0 && status < 9) {
                    throw new IOException("SOCKS5 server unable to connect, reason: " + SocksProxySocket.replyErrorV5[status]);
                }
                throw new IOException("SOCKS5 server unable to connect, reason: " + status);
            }
            else {
                proxyIn.read();
                final int aType = proxyIn.read();
                final byte[] data = new byte[255];
                switch (aType) {
                    case 1: {
                        if (proxyIn.read(data, 0, 4) != 4) {
                            throw new IOException("SOCKS5 error reading address");
                        }
                        proxySocket.serverDesc = data[0] + "." + data[1] + "." + data[2] + "." + data[3];
                        break;
                    }
                    case 3: {
                        final int n = proxyIn.read();
                        if (proxyIn.read(data, 0, n) != n) {
                            throw new IOException("SOCKS5 error reading address");
                        }
                        proxySocket.serverDesc = new String(data);
                        break;
                    }
                    default: {
                        throw new IOException("SOCKS5 gave unsupported address type: " + aType);
                    }
                }
                if (proxyIn.read(data, 0, 2) != 2) {
                    throw new IOException("SOCKS5 error reading port");
                }
                final StringBuffer sb = new StringBuffer();
                final SocksProxySocket socksProxySocket = proxySocket;
                socksProxySocket.serverDesc = sb.append(socksProxySocket.serverDesc).append(":").append(data[0] << 8 | data[1]).toString();
            }
        }
        catch (SocketException e) {
            throw new SocketException("Error communicating with SOCKS5 server " + proxyHost + ":" + proxyPort + ", " + e.getMessage());
        }
        return proxySocket;
    }
    
    static void doAuthentication(final InputStream proxyIn, final OutputStream proxyOut, final ProxyAuthenticator authenticator, final String proxyHost, final int proxyPort) throws IOException {
        final String username = authenticator.getProxyUsername("SOCKS5", null);
        final String password = authenticator.getProxyPassword("SOCKS5", null);
        proxyOut.write(1);
        proxyOut.write(username.length());
        proxyOut.write(username.getBytes());
        proxyOut.write(password.length());
        proxyOut.write(password.getBytes());
        final int res = proxyIn.read();
        if (res != 5) {
            throw new IOException("Invalid response from SOCKS5 server (" + res + ") " + proxyHost + ":" + proxyPort);
        }
        if (proxyIn.read() != 0) {
            throw new IOException("Invalid username/password for SOCKS5 server");
        }
    }
    
    public String toString() {
        return "SocksProxySocket[addr=" + this.getInetAddress() + ",port=" + this.getPort() + ",localport=" + this.getLocalPort() + "]";
    }
    
    static {
        replyErrorV5 = new String[] { "Success", "General SOCKS server failure", "Connection not allowed by ruleset", "Network unreachable", "Host unreachable", "Connection refused", "TTL expired", "Command not supported", "Address type not supported" };
        replyErrorV4 = new String[] { "Request rejected or failed", "SOCKS server cannot connect to identd on the client", "The client program and identd report different user-ids" };
    }
}
