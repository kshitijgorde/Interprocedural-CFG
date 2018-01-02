// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.lang.reflect.Method;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import java.io.BufferedOutputStream;
import org.apache.commons.httpclient.util.TimeoutController;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.DefaultProtocolSocketFactory;
import java.net.SocketException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import org.apache.commons.httpclient.protocol.Protocol;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.net.Socket;

public class HttpConnection
{
    private static final byte[] CRLF;
    private boolean used;
    private String hostName;
    private String virtualName;
    private int portNumber;
    private String proxyHostName;
    private int proxyPortNumber;
    private Socket socket;
    private PushbackInputStream inputStream;
    private OutputStream outputStream;
    private int sendBufferSize;
    private InputStream lastResponseInputStream;
    protected boolean isOpen;
    private Protocol protocolInUse;
    private int soTimeout;
    private boolean soNodelay;
    private boolean locked;
    private boolean usingSecureSocket;
    private boolean tunnelEstablished;
    private boolean staleCheckingEnabled;
    private int connectTimeout;
    private HttpConnectionManager httpConnectionManager;
    private InetAddress localAddress;
    
    static {
        CRLF = new byte[] { 13, 10 };
    }
    
    public HttpConnection(final String host, final int port) {
        this(null, -1, host, null, port, Protocol.getProtocol("http"));
    }
    
    public HttpConnection(final String host, final int port, final Protocol protocol) {
        this(null, -1, host, null, port, protocol);
    }
    
    public HttpConnection(final String host, final String virtualHost, final int port, final Protocol protocol) {
        this(null, -1, host, virtualHost, port, protocol);
    }
    
    public HttpConnection(final String proxyHost, final int proxyPort, final String host, final int port) {
        this(proxyHost, proxyPort, host, null, port, Protocol.getProtocol("http"));
    }
    
    public HttpConnection(final HostConfiguration hostConfiguration) {
        this(hostConfiguration.getProxyHost(), hostConfiguration.getProxyPort(), hostConfiguration.getHost(), hostConfiguration.getVirtualHost(), hostConfiguration.getPort(), hostConfiguration.getProtocol());
        this.localAddress = hostConfiguration.getLocalAddress();
    }
    
    public HttpConnection(final String proxyHost, final int proxyPort, final String host, final String virtualHost, final int port, final Protocol protocol) {
        this.used = false;
        this.hostName = null;
        this.virtualName = null;
        this.portNumber = -1;
        this.proxyHostName = null;
        this.proxyPortNumber = -1;
        this.socket = null;
        this.inputStream = null;
        this.outputStream = null;
        this.sendBufferSize = -1;
        this.lastResponseInputStream = null;
        this.isOpen = false;
        this.soTimeout = 0;
        this.soNodelay = true;
        this.locked = false;
        this.usingSecureSocket = false;
        this.tunnelEstablished = false;
        this.staleCheckingEnabled = true;
        this.connectTimeout = 0;
        if (host == null) {
            throw new IllegalArgumentException("host parameter is null");
        }
        if (protocol == null) {
            throw new IllegalArgumentException("protocol is null");
        }
        this.proxyHostName = proxyHost;
        this.proxyPortNumber = proxyPort;
        this.hostName = host;
        this.virtualName = virtualHost;
        this.portNumber = protocol.resolvePort(port);
        this.protocolInUse = protocol;
    }
    
    public String getHost() {
        return this.hostName;
    }
    
    public void setHost(final String host) throws IllegalStateException {
        if (host == null) {
            throw new IllegalArgumentException("host parameter is null");
        }
        this.assertNotOpen();
        this.hostName = host;
    }
    
    public String getVirtualHost() {
        return this.virtualName;
    }
    
    public void setVirtualHost(final String host) throws IllegalStateException {
        this.assertNotOpen();
        this.virtualName = host;
    }
    
    public int getPort() {
        if (this.portNumber < 0) {
            return this.isSecure() ? 443 : 80;
        }
        return this.portNumber;
    }
    
    public void setPort(final int port) throws IllegalStateException {
        this.assertNotOpen();
        this.portNumber = port;
    }
    
    public String getProxyHost() {
        return this.proxyHostName;
    }
    
    public void setProxyHost(final String host) throws IllegalStateException {
        this.assertNotOpen();
        this.proxyHostName = host;
    }
    
    public int getProxyPort() {
        return this.proxyPortNumber;
    }
    
    public void setProxyPort(final int port) throws IllegalStateException {
        this.assertNotOpen();
        this.proxyPortNumber = port;
    }
    
    public boolean isSecure() {
        return this.protocolInUse.isSecure();
    }
    
    public Protocol getProtocol() {
        return this.protocolInUse;
    }
    
    public void setProtocol(final Protocol protocol) {
        this.assertNotOpen();
        if (protocol == null) {
            throw new IllegalArgumentException("protocol is null");
        }
        this.protocolInUse = protocol;
    }
    
    public InetAddress getLocalAddress() {
        return this.localAddress;
    }
    
    public void setLocalAddress(final InetAddress localAddress) {
        this.assertNotOpen();
        this.localAddress = localAddress;
    }
    
    public boolean isOpen() {
        if (this.used && this.isStaleCheckingEnabled() && this.isStale()) {
            this.close();
        }
        return this.isOpen;
    }
    
    public boolean isStaleCheckingEnabled() {
        return this.staleCheckingEnabled;
    }
    
    public void setStaleCheckingEnabled(final boolean staleCheckEnabled) {
        this.staleCheckingEnabled = staleCheckEnabled;
    }
    
    protected boolean isStale() {
        boolean isStale = true;
        if (this.isOpen) {
            isStale = false;
            try {
                if (this.inputStream.available() == 0) {
                    try {
                        this.socket.setSoTimeout(1);
                        final int byteRead = this.inputStream.read();
                        if (byteRead == -1) {
                            isStale = true;
                        }
                        else {
                            this.inputStream.unread(byteRead);
                        }
                    }
                    finally {
                        this.socket.setSoTimeout(this.soTimeout);
                    }
                    this.socket.setSoTimeout(this.soTimeout);
                }
            }
            catch (InterruptedIOException ex) {}
            catch (IOException e) {
                isStale = true;
            }
        }
        return isStale;
    }
    
    public boolean isProxied() {
        return this.proxyHostName != null && this.proxyPortNumber > 0;
    }
    
    public void setLastResponseInputStream(final InputStream inStream) {
        this.lastResponseInputStream = inStream;
    }
    
    public InputStream getLastResponseInputStream() {
        return this.lastResponseInputStream;
    }
    
    public void setSoTimeout(final int timeout) throws SocketException, IllegalStateException {
        this.soTimeout = timeout;
        if (this.socket != null) {
            this.socket.setSoTimeout(timeout);
        }
    }
    
    public int getSoTimeout() throws SocketException {
        if (this.socket != null) {
            return this.socket.getSoTimeout();
        }
        return this.soTimeout;
    }
    
    public void setConnectionTimeout(final int timeout) {
        this.connectTimeout = timeout;
    }
    
    public void open() throws IOException {
        this.assertNotOpen();
        try {
            if (this.socket == null) {
                final String host = (this.proxyHostName == null) ? this.hostName : this.proxyHostName;
                final int port = (this.proxyHostName == null) ? this.portNumber : this.proxyPortNumber;
                this.usingSecureSocket = (this.isSecure() && !this.isProxied());
                final ProtocolSocketFactory socketFactory = (this.isSecure() && this.isProxied()) ? new DefaultProtocolSocketFactory() : this.protocolInUse.getSocketFactory();
                if (this.connectTimeout == 0) {
                    if (this.localAddress != null) {
                        this.socket = socketFactory.createSocket(host, port, this.localAddress, 0);
                    }
                    else {
                        this.socket = socketFactory.createSocket(host, port);
                    }
                }
                else {
                    final SocketTask task = (SocketTask)new SocketTask() {
                        public void doit() throws IOException {
                            if (HttpConnection.this.localAddress != null) {
                                ((SocketTask)this).setSocket(socketFactory.createSocket(host, port, HttpConnection.this.localAddress, 0));
                            }
                            else {
                                ((SocketTask)this).setSocket(socketFactory.createSocket(host, port));
                            }
                        }
                    };
                    TimeoutController.execute(task, this.connectTimeout);
                    this.socket = task.getSocket();
                    if (task.exception != null) {
                        throw task.exception;
                    }
                }
            }
            this.socket.setTcpNoDelay(this.soNodelay);
            this.socket.setSoTimeout(this.soTimeout);
            if (this.sendBufferSize != -1) {
                this.socket.setSendBufferSize(this.sendBufferSize);
            }
            this.inputStream = new PushbackInputStream(this.socket.getInputStream());
            this.outputStream = new BufferedOutputStream(new WrappedOutputStream(this.socket.getOutputStream()), this.socket.getSendBufferSize());
            this.isOpen = true;
            this.used = false;
        }
        catch (IOException e) {
            this.closeSocketAndStreams();
            throw e;
        }
        catch (TimeoutController.TimeoutException e2) {
            throw new ConnectionTimeoutException();
        }
    }
    
    public void tunnelCreated() throws IllegalStateException, IOException {
        if (!this.isSecure() || !this.isProxied()) {
            throw new IllegalStateException("Connection must be secure and proxied to use this feature");
        }
        if (this.usingSecureSocket) {
            throw new IllegalStateException("Already using a secure socket");
        }
        final SecureProtocolSocketFactory socketFactory = (SecureProtocolSocketFactory)this.protocolInUse.getSocketFactory();
        this.socket = socketFactory.createSocket(this.socket, this.hostName, this.portNumber, true);
        if (this.sendBufferSize != -1) {
            this.socket.setSendBufferSize(this.sendBufferSize);
        }
        this.inputStream = new PushbackInputStream(this.socket.getInputStream());
        this.outputStream = new BufferedOutputStream(new WrappedOutputStream(this.socket.getOutputStream()), this.socket.getSendBufferSize());
        this.usingSecureSocket = true;
        this.tunnelEstablished = true;
    }
    
    public boolean isTransparent() {
        return !this.isProxied() || this.tunnelEstablished;
    }
    
    public void flushRequestOutputStream() throws IOException {
        this.assertOpen();
        this.outputStream.flush();
    }
    
    public OutputStream getRequestOutputStream() throws IOException, IllegalStateException {
        this.assertOpen();
        OutputStream out = this.outputStream;
        if (Wire.enabled()) {
            out = new WireLogOutputStream(out);
        }
        return out;
    }
    
    public InputStream getResponseInputStream() throws IOException, IllegalStateException {
        this.assertOpen();
        return this.inputStream;
    }
    
    public boolean isResponseAvailable() throws IOException {
        this.assertOpen();
        return this.inputStream.available() > 0;
    }
    
    public boolean isResponseAvailable(final int timeout) throws IOException {
        this.assertOpen();
        boolean result = false;
        if (this.inputStream.available() > 0) {
            result = true;
        }
        else {
            try {
                this.socket.setSoTimeout(timeout);
                final int byteRead = this.inputStream.read();
                if (byteRead != -1) {
                    this.inputStream.unread(byteRead);
                    result = true;
                }
            }
            catch (InterruptedIOException ex) {}
            finally {
                this.socket.setSoTimeout(this.soTimeout);
            }
            this.socket.setSoTimeout(this.soTimeout);
        }
        return result;
    }
    
    public void write(final byte[] data) throws IOException, IllegalStateException, HttpRecoverableException {
        this.write(data, 0, data.length);
    }
    
    public void write(final byte[] data, final int offset, final int length) throws IOException, IllegalStateException, HttpRecoverableException {
        if (offset + length > data.length) {
            throw new HttpRecoverableException("Unable to write: offset=" + offset + " length=" + length + " data.length=" + data.length);
        }
        if (data.length <= 0) {
            throw new HttpRecoverableException("Unable to write: data.length=" + data.length);
        }
        this.assertOpen();
        try {
            this.outputStream.write(data, offset, length);
        }
        catch (HttpRecoverableException hre) {
            throw hre;
        }
        catch (SocketException se) {
            throw new HttpRecoverableException(se.toString());
        }
        catch (IOException ioe) {
            throw ioe;
        }
    }
    
    public void writeLine(final byte[] data) throws IOException, IllegalStateException, HttpRecoverableException {
        this.write(data);
        this.writeLine();
    }
    
    public void writeLine() throws IOException, IllegalStateException, HttpRecoverableException {
        this.write(HttpConnection.CRLF);
    }
    
    public void print(final String data) throws IOException, IllegalStateException, HttpRecoverableException {
        this.write(HttpConstants.getBytes(data));
    }
    
    public void printLine(final String data) throws IOException, IllegalStateException, HttpRecoverableException {
        this.writeLine(HttpConstants.getBytes(data));
    }
    
    public void printLine() throws IOException, IllegalStateException, HttpRecoverableException {
        this.writeLine();
    }
    
    public String readLine() throws IOException, IllegalStateException {
        this.assertOpen();
        return HttpParser.readLine(this.inputStream);
    }
    
    public void shutdownOutput() {
        try {
            final Class[] paramsClasses = new Class[0];
            final Method shutdownOutput = this.socket.getClass().getMethod("shutdownOutput", (Class<?>[])paramsClasses);
            final Object[] params = new Object[0];
            shutdownOutput.invoke(this.socket, params);
        }
        catch (Exception ex) {}
    }
    
    public void close() {
        this.closeSocketAndStreams();
    }
    
    public HttpConnectionManager getHttpConnectionManager() {
        return this.httpConnectionManager;
    }
    
    public void setHttpConnectionManager(final HttpConnectionManager httpConnectionManager) {
        this.httpConnectionManager = httpConnectionManager;
    }
    
    public void releaseConnection() {
        this.used = true;
        if (!this.locked && this.httpConnectionManager != null) {
            this.httpConnectionManager.releaseConnection(this);
        }
    }
    
    boolean isLocked() {
        return this.locked;
    }
    
    void setLocked(final boolean locked) {
        this.locked = locked;
    }
    
    protected void closeSocketAndStreams() {
        this.lastResponseInputStream = null;
        if (this.inputStream != null) {
            final InputStream temp = this.inputStream;
            this.inputStream = null;
            try {
                temp.close();
            }
            catch (Exception ex) {}
        }
        if (this.outputStream != null) {
            final OutputStream temp2 = this.outputStream;
            this.outputStream = null;
            try {
                temp2.close();
            }
            catch (Exception ex2) {}
        }
        if (this.socket != null) {
            final Socket temp3 = this.socket;
            this.socket = null;
            try {
                temp3.close();
            }
            catch (Exception ex3) {}
        }
        this.isOpen = false;
        this.used = false;
        this.tunnelEstablished = false;
        this.usingSecureSocket = false;
    }
    
    protected void assertNotOpen() throws IllegalStateException {
        if (this.isOpen) {
            throw new IllegalStateException("Connection is open");
        }
    }
    
    protected void assertOpen() throws IllegalStateException {
        if (!this.isOpen) {
            throw new IllegalStateException("Connection is not open");
        }
    }
    
    public int getSendBufferSize() throws SocketException {
        if (this.socket == null) {
            return -1;
        }
        return this.socket.getSendBufferSize();
    }
    
    public void setSendBufferSize(final int sendBufferSize) throws SocketException {
        this.sendBufferSize = sendBufferSize;
        if (this.socket != null) {
            this.socket.setSendBufferSize(sendBufferSize);
        }
    }
    
    public class ConnectionTimeoutException extends IOException
    {
    }
    
    private abstract class SocketTask implements Runnable
    {
        private Socket socket;
        private IOException exception;
        
        protected void setSocket(final Socket newSocket) {
            this.socket = newSocket;
        }
        
        protected Socket getSocket() {
            return this.socket;
        }
        
        public abstract void doit() throws IOException;
        
        public void run() {
            try {
                this.doit();
            }
            catch (IOException e) {
                this.exception = e;
            }
        }
    }
    
    private class WrappedOutputStream extends OutputStream
    {
        private OutputStream out;
        
        public WrappedOutputStream(final OutputStream out) {
            this.out = out;
        }
        
        private IOException handleException(final IOException ioe) {
            final boolean tempUsed = HttpConnection.this.used;
            HttpConnection.this.close();
            if (ioe instanceof InterruptedIOException) {
                return new IOTimeoutException(ioe.getMessage());
            }
            if (tempUsed) {
                return new HttpRecoverableException(ioe.getMessage(), ioe);
            }
            return ioe;
        }
        
        public void write(final int b) throws IOException {
            try {
                this.out.write(b);
            }
            catch (IOException ioe) {
                throw this.handleException(ioe);
            }
        }
        
        public void flush() throws IOException {
            try {
                this.out.flush();
            }
            catch (IOException ioe) {
                throw this.handleException(ioe);
            }
        }
        
        public void close() throws IOException {
            try {
                this.out.close();
            }
            catch (IOException ioe) {
                throw this.handleException(ioe);
            }
        }
        
        public void write(final byte[] b, final int off, final int len) throws IOException {
            try {
                this.out.write(b, off, len);
            }
            catch (IOException ioe) {
                throw this.handleException(ioe);
            }
        }
        
        public void write(final byte[] b) throws IOException {
            try {
                this.out.write(b);
            }
            catch (IOException ioe) {
                throw this.handleException(ioe);
            }
        }
    }
}
