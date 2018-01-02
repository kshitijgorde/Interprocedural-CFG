// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.io.IOException;

public class ConnectMethod extends HttpMethodBase
{
    public static final String NAME = "CONNECT";
    
    public ConnectMethod() {
    }
    
    public ConnectMethod(final HttpMethod method) {
    }
    
    public String getName() {
        return "CONNECT";
    }
    
    public int execute(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        final int code = super.execute(state, conn);
        if (code >= 200 && code < 300) {
            conn.tunnelCreated();
        }
        return code;
    }
    
    protected void addAuthorizationRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
    }
    
    protected void addCookieRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
    }
    
    protected void addRequestHeaders(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        this.addUserAgentRequestHeader(state, conn);
        this.addHostRequestHeader(state, conn);
        this.addProxyAuthorizationRequestHeader(state, conn);
        this.addProxyConnectionHeader(state, conn);
    }
    
    protected boolean shouldCloseConnection(final HttpConnection conn) {
        if (this.getStatusCode() == 200) {
            Header connectionHeader = null;
            if (!conn.isTransparent()) {
                connectionHeader = this.getResponseHeader("proxy-connection");
            }
            if (connectionHeader == null) {
                connectionHeader = this.getResponseHeader("connection");
            }
            if (connectionHeader != null) {
                connectionHeader.getValue().equalsIgnoreCase("close");
            }
            return false;
        }
        return super.shouldCloseConnection(conn);
    }
    
    protected void writeRequestLine(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        int port = conn.getPort();
        if (port == -1) {
            port = conn.getProtocol().getDefaultPort();
        }
        final StringBuffer buffer = new StringBuffer();
        buffer.append(this.getName());
        buffer.append(' ');
        buffer.append(conn.getHost());
        if (port > -1) {
            buffer.append(':');
            buffer.append(port);
        }
        buffer.append(" HTTP/1.1");
        final String line = buffer.toString();
        conn.printLine(line);
        if (Wire.enabled()) {
            Wire.output(line);
        }
    }
}
