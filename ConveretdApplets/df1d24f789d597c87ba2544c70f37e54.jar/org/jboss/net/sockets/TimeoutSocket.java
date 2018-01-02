// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.sockets;

import java.net.SocketException;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.SocketChannel;
import java.net.SocketAddress;
import java.net.InetAddress;
import java.net.Socket;

public class TimeoutSocket extends Socket
{
    private Socket s;
    
    public TimeoutSocket(final Socket s) {
        this.s = s;
    }
    
    public InetAddress getInetAddress() {
        return this.s.getInetAddress();
    }
    
    public InetAddress getLocalAddress() {
        return this.s.getLocalAddress();
    }
    
    public int getPort() {
        return this.s.getPort();
    }
    
    public int getLocalPort() {
        return this.s.getLocalPort();
    }
    
    public SocketAddress getRemoteSocketAddress() {
        return this.s.getRemoteSocketAddress();
    }
    
    public SocketAddress getLocalSocketAddress() {
        return this.s.getLocalSocketAddress();
    }
    
    public SocketChannel getChannel() {
        return this.s.getChannel();
    }
    
    public InputStream getInputStream() throws IOException {
        final InputStream is = this.s.getInputStream();
        final InterruptableInputStream iis = new InterruptableInputStream(is);
        return iis;
    }
    
    public OutputStream getOutputStream() throws IOException {
        return this.s.getOutputStream();
    }
    
    public void setTcpNoDelay(final boolean on) throws SocketException {
        this.s.setTcpNoDelay(on);
    }
    
    public boolean getTcpNoDelay() throws SocketException {
        return this.s.getTcpNoDelay();
    }
    
    public void setSoLinger(final boolean on, final int linger) throws SocketException {
        this.s.setSoLinger(on, linger);
    }
    
    public int getSoLinger() throws SocketException {
        return this.s.getSoLinger();
    }
    
    public void sendUrgentData(final int data) throws IOException {
        this.s.sendUrgentData(data);
    }
    
    public void setOOBInline(final boolean on) throws SocketException {
        this.s.setOOBInline(on);
    }
    
    public boolean getOOBInline() throws SocketException {
        return this.s.getOOBInline();
    }
    
    public synchronized void setSoTimeout(final int timeout) throws SocketException {
        this.s.setSoTimeout(1000);
    }
    
    public synchronized int getSoTimeout() throws SocketException {
        return this.s.getSoTimeout();
    }
    
    public synchronized void setSendBufferSize(final int size) throws SocketException {
        this.s.setSendBufferSize(size);
    }
    
    public synchronized int getSendBufferSize() throws SocketException {
        return this.s.getSendBufferSize();
    }
    
    public synchronized void setReceiveBufferSize(final int size) throws SocketException {
        this.s.setReceiveBufferSize(size);
    }
    
    public synchronized int getReceiveBufferSize() throws SocketException {
        return this.s.getReceiveBufferSize();
    }
    
    public void setKeepAlive(final boolean on) throws SocketException {
        this.s.setKeepAlive(on);
    }
    
    public boolean getKeepAlive() throws SocketException {
        return this.s.getKeepAlive();
    }
    
    public void setTrafficClass(final int tc) throws SocketException {
        this.s.setTrafficClass(tc);
    }
    
    public int getTrafficClass() throws SocketException {
        return this.s.getTrafficClass();
    }
    
    public void setReuseAddress(final boolean on) throws SocketException {
        this.s.setReuseAddress(on);
    }
    
    public boolean getReuseAddress() throws SocketException {
        return this.s.getReuseAddress();
    }
    
    public synchronized void close() throws IOException {
        this.s.close();
    }
    
    public void shutdownInput() throws IOException {
        this.s.shutdownInput();
    }
    
    public void shutdownOutput() throws IOException {
        this.s.shutdownOutput();
    }
    
    public String toString() {
        return this.s.toString();
    }
    
    public boolean isConnected() {
        return this.s.isConnected();
    }
    
    public boolean isBound() {
        return this.s.isBound();
    }
    
    public boolean isClosed() {
        return this.s.isClosed();
    }
    
    public boolean isInputShutdown() {
        return this.s.isInputShutdown();
    }
    
    public boolean isOutputShutdown() {
        return this.s.isOutputShutdown();
    }
}
