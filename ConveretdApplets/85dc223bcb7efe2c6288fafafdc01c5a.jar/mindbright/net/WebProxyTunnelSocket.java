// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.net;

import java.io.OutputStream;
import java.io.InputStream;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.Socket;

public class WebProxyTunnelSocket extends Socket
{
    private String proxyHost;
    private int proxyPort;
    private String targetHost;
    private int targetPort;
    HttpHeader responseHeader;
    String serverDesc;
    
    public HttpHeader getResponseHeader() {
        return this.responseHeader;
    }
    
    public String getServerDesc() {
        return this.serverDesc;
    }
    
    private WebProxyTunnelSocket(final String host, final int port, final String proxyHost, final int proxyPort) throws IOException, UnknownHostException {
        super(proxyHost, proxyPort);
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.targetHost = this.targetHost;
        this.targetPort = this.targetPort;
    }
    
    public static WebProxyTunnelSocket getProxy(final String host, final int port, final String proxyHost, final int proxyPort, String protoStr, final ProxyAuthenticator authenticator, final String userAgent) throws IOException, UnknownHostException {
        WebProxyTunnelSocket proxySocket = new WebProxyTunnelSocket(host, port, proxyHost, proxyPort);
        int status = -1;
        String serverDesc;
        try {
            InputStream proxyIn = proxySocket.getInputStream();
            OutputStream proxyOut = proxySocket.getOutputStream();
            final HttpHeader requestHeader = new HttpHeader();
            if (protoStr == null) {
                protoStr = "";
            }
            requestHeader.setStartLine("CONNECT " + protoStr + host + ":" + port + " HTTP/1.0");
            requestHeader.setHeaderField("User-Agent", userAgent);
            requestHeader.setHeaderField("Pragma", "No-Cache");
            requestHeader.setHeaderField("Proxy-Connection", "Keep-Alive");
            requestHeader.writeTo(proxyOut);
            proxySocket.responseHeader = new HttpHeader(proxyIn);
            serverDesc = proxySocket.responseHeader.getHeaderField("server");
            if (proxySocket.responseHeader.getStatus() == 407 && authenticator != null) {
                final String method = proxySocket.responseHeader.getProxyAuthMethod();
                String realm = proxySocket.responseHeader.getProxyAuthRealm();
                if (realm == null) {
                    realm = "";
                }
                if ("basic".equalsIgnoreCase(method)) {
                    proxySocket.close();
                    proxySocket = new WebProxyTunnelSocket(host, port, proxyHost, proxyPort);
                    proxyIn = proxySocket.getInputStream();
                    proxyOut = proxySocket.getOutputStream();
                    final String username = authenticator.getProxyUsername("HTTP Proxy", realm);
                    final String password = authenticator.getProxyPassword("HTTP Proxy", realm);
                    requestHeader.setBasicProxyAuth(username, password);
                    requestHeader.writeTo(proxyOut);
                    proxySocket.responseHeader = new HttpHeader(proxyIn);
                }
                else {
                    if ("digest".equalsIgnoreCase(method)) {
                        throw new IOException("We don't support 'Digest' HTTP Authentication");
                    }
                    throw new IOException("Unknown HTTP Authentication method '" + method + "'");
                }
            }
            status = proxySocket.responseHeader.getStatus();
        }
        catch (SocketException e) {
            throw new SocketException("Error communicating with proxy server " + proxyHost + ":" + proxyPort + " (" + e.getMessage() + ")");
        }
        if (status < 200 || status > 299) {
            throw new WebProxyException("Proxy tunnel setup failed: " + proxySocket.responseHeader.getStartLine());
        }
        proxySocket.serverDesc = serverDesc;
        return proxySocket;
    }
    
    public String toString() {
        return "WebProxyTunnelSocket[addr=" + this.getInetAddress() + ",port=" + this.getPort() + ",localport=" + this.getLocalPort() + "]";
    }
}
