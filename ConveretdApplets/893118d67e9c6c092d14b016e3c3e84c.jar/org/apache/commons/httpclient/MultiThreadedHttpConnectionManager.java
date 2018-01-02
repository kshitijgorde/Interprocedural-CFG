// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.lang.ref.Reference;
import java.net.SocketException;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.httpclient.protocol.Protocol;
import java.net.InetAddress;
import java.io.InputStream;
import java.util.Iterator;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.Collections;
import java.util.HashMap;
import java.lang.ref.ReferenceQueue;
import java.util.Map;

public class MultiThreadedHttpConnectionManager implements HttpConnectionManager
{
    public static final int DEFAULT_MAX_HOST_CONNECTIONS = 2;
    public static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 20;
    private ConnectionPool connectionPool;
    private Map referenceToHostConfig;
    private ReferenceQueue referenceQueue;
    private boolean connectionStaleCheckingEnabled;
    private int maxHostConnections;
    private int maxTotalConnections;
    
    public MultiThreadedHttpConnectionManager() {
        this.connectionStaleCheckingEnabled = true;
        this.maxHostConnections = 2;
        this.maxTotalConnections = 20;
        this.referenceToHostConfig = Collections.synchronizedMap(new HashMap<Object, Object>());
        this.connectionPool = new ConnectionPool();
        this.referenceQueue = new ReferenceQueue();
        new ReferenceQueueThread().start();
    }
    
    public HttpConnection getConnection(final HostConfiguration hostConfiguration) {
        try {
            return this.getConnectionWithTimeout(hostConfiguration, 0L);
        }
        catch (ConnectTimeoutException e) {
            LOG.debug("Unexpected exception while waiting for connection", e);
            return this.getConnectionWithTimeout(hostConfiguration, 0L);
        }
    }
    
    public HttpConnection getConnection(final HostConfiguration hostConfiguration, final long timeout) throws HttpException {
        LOG.trace("enter HttpConnectionManager.getConnection(HostConfiguration, long)");
        try {
            return this.getConnectionWithTimeout(hostConfiguration, timeout);
        }
        catch (ConnectTimeoutException e) {
            throw new HttpException(e.getMessage());
        }
    }
    
    public void setConnectionStaleCheckingEnabled(final boolean connectionStaleCheckingEnabled) {
        this.connectionStaleCheckingEnabled = connectionStaleCheckingEnabled;
    }
    
    public boolean isConnectionStaleCheckingEnabled() {
        return this.connectionStaleCheckingEnabled;
    }
    
    public HttpConnection getConnectionWithTimeout(final HostConfiguration hostConfiguration, final long timeout) throws ConnectTimeoutException {
        LOG.trace("enter HttpConnectionManager.getConnectionWithTimeout(HostConfiguration, long)");
        if (hostConfiguration == null) {
            throw new IllegalArgumentException("hostConfiguration is null");
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("HttpConnectionManager.getConnection:  config = " + hostConfiguration + ", timeout = " + timeout);
        }
        final HttpConnection conn = this.doGetConnection(hostConfiguration, timeout);
        return new HttpConnectionAdapter(conn);
    }
    
    public int getConnectionsInUse(final HostConfiguration hostConfiguration) {
        synchronized (this.connectionPool) {
            final HostConnectionPool hostPool = this.connectionPool.getHostPool(hostConfiguration);
            // monitorexit(this.connectionPool)
            return hostPool.numConnections;
        }
    }
    
    public int getConnectionsInUse() {
        synchronized (this.connectionPool) {
            // monitorexit(this.connectionPool)
            return this.connectionPool.numConnections;
        }
    }
    
    public void setMaxConnectionsPerHost(final int maxHostConnections) {
        this.maxHostConnections = maxHostConnections;
    }
    
    public int getMaxConnectionsPerHost() {
        return this.maxHostConnections;
    }
    
    public void setMaxTotalConnections(final int maxTotalConnections) {
        this.maxTotalConnections = maxTotalConnections;
    }
    
    public int getMaxTotalConnections() {
        return this.maxTotalConnections;
    }
    
    public void releaseConnection(HttpConnection conn) {
        LOG.trace("enter HttpConnectionManager.releaseConnection(HttpConnection)");
        if (conn instanceof HttpConnectionAdapter) {
            conn = ((HttpConnectionAdapter)conn).getWrappedConnection();
        }
        SimpleHttpConnectionManager.finishLastResponse(conn);
        this.connectionPool.freeConnection(conn);
    }
    
    private HostConfiguration configurationForConnection(final HttpConnection conn) {
        final HostConfiguration connectionConfiguration = new HostConfiguration();
        connectionConfiguration.setHost(conn.getHost(), conn.getVirtualHost(), conn.getPort(), conn.getProtocol());
        if (conn.getLocalAddress() != null) {
            connectionConfiguration.setLocalAddress(conn.getLocalAddress());
        }
        if (conn.getProxyHost() != null) {
            connectionConfiguration.setProxy(conn.getProxyHost(), conn.getProxyPort());
        }
        return connectionConfiguration;
    }
    
    private HttpConnection doGetConnection(HostConfiguration hostConfiguration, final long timeout) throws ConnectTimeoutException {
        HttpConnection connection = null;
        synchronized (this.connectionPool) {
            hostConfiguration = new HostConfiguration(hostConfiguration);
            final HostConnectionPool hostPool = this.connectionPool.getHostPool(hostConfiguration);
            WaitingThread waitingThread = null;
            final boolean useTimeout = timeout > 0L;
            long timeToWait = timeout;
            long startWait = 0L;
            long endWait = 0L;
            while (connection == null) {
                if (hostPool.freeConnections.size() > 0) {
                    connection = this.connectionPool.getFreeConnection(hostConfiguration);
                }
                else if (hostPool.numConnections < this.maxHostConnections && this.connectionPool.numConnections < this.maxTotalConnections) {
                    connection = this.connectionPool.createConnection(hostConfiguration);
                }
                else if (hostPool.numConnections < this.maxHostConnections && this.connectionPool.freeConnections.size() > 0) {
                    this.connectionPool.deleteLeastUsedConnection();
                    connection = this.connectionPool.createConnection(hostConfiguration);
                }
                else {
                    try {
                        if (useTimeout && timeToWait <= 0L) {
                            throw new ConnectTimeoutException("Timeout waiting for connection");
                        }
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("Unable to get a connection, waiting..., hostConfig=" + hostConfiguration);
                        }
                        if (waitingThread == null) {
                            waitingThread = new WaitingThread();
                            waitingThread.hostConnectionPool = hostPool;
                            waitingThread.thread = Thread.currentThread();
                        }
                        if (useTimeout) {
                            startWait = System.currentTimeMillis();
                        }
                        hostPool.waitingThreads.addLast(waitingThread);
                        this.connectionPool.waitingThreads.addLast(waitingThread);
                        this.connectionPool.wait(timeToWait);
                        hostPool.waitingThreads.remove(waitingThread);
                        this.connectionPool.waitingThreads.remove(waitingThread);
                    }
                    catch (InterruptedException ex) {}
                    finally {
                        if (useTimeout) {
                            endWait = System.currentTimeMillis();
                            timeToWait -= endWait - startWait;
                        }
                    }
                    if (!useTimeout) {
                        continue;
                    }
                    endWait = System.currentTimeMillis();
                    timeToWait -= endWait - startWait;
                }
            }
        }
        // monitorexit(this.connectionPool)
        return connection;
    }
    
    private class ConnectionPool
    {
        private LinkedList freeConnections;
        private LinkedList waitingThreads;
        private final Map mapHosts;
        private int numConnections;
        
        ConnectionPool() {
            this.freeConnections = new LinkedList();
            this.waitingThreads = new LinkedList();
            this.mapHosts = new HashMap();
            this.numConnections = 0;
        }
        
        public synchronized HttpConnection getFreeConnection(final HostConfiguration hostConfiguration) {
            HttpConnection connection = null;
            final HostConnectionPool hostPool = this.getHostPool(hostConfiguration);
            if (hostPool.freeConnections.size() > 0) {
                connection = hostPool.freeConnections.removeFirst();
                this.freeConnections.remove(connection);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Getting free connection, hostConfig=" + hostConfiguration);
                }
            }
            else if (LOG.isDebugEnabled()) {
                LOG.debug("There were no free connections to get, hostConfig=" + hostConfiguration);
            }
            return connection;
        }
        
        public synchronized HostConnectionPool getHostPool(final HostConfiguration hostConfiguration) {
            LOG.trace("enter HttpConnectionManager.ConnectionPool.getHostPool(HostConfiguration)");
            HostConnectionPool listConnections = this.mapHosts.get(hostConfiguration);
            if (listConnections == null) {
                listConnections = new HostConnectionPool();
                listConnections.hostConfiguration = hostConfiguration;
                this.mapHosts.put(hostConfiguration, listConnections);
            }
            return listConnections;
        }
        
        public synchronized HttpConnection createConnection(final HostConfiguration hostConfiguration) {
            HttpConnection connection = null;
            final HostConnectionPool hostPool = this.getHostPool(hostConfiguration);
            if (hostPool.numConnections < MultiThreadedHttpConnectionManager.this.getMaxConnectionsPerHost() && this.numConnections < MultiThreadedHttpConnectionManager.this.getMaxTotalConnections()) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Allocating new connection, hostConfig=" + hostConfiguration);
                }
                connection = new HttpConnection(hostConfiguration);
                connection.setStaleCheckingEnabled(MultiThreadedHttpConnectionManager.this.connectionStaleCheckingEnabled);
                connection.setHttpConnectionManager(MultiThreadedHttpConnectionManager.this);
                ++this.numConnections;
                final HostConnectionPool hostConnectionPool = hostPool;
                ++hostConnectionPool.numConnections;
                MultiThreadedHttpConnectionManager.this.referenceToHostConfig.put(new WeakReference<HttpConnection>(connection, MultiThreadedHttpConnectionManager.this.referenceQueue), hostConfiguration);
            }
            else if (LOG.isDebugEnabled()) {
                if (hostPool.numConnections >= MultiThreadedHttpConnectionManager.this.getMaxConnectionsPerHost()) {
                    LOG.debug("No connection allocated, host pool has already reached maxConnectionsPerHost, hostConfig=" + hostConfiguration + ", maxConnectionsPerhost=" + MultiThreadedHttpConnectionManager.this.getMaxConnectionsPerHost());
                }
                else {
                    LOG.debug("No connection allocated, maxTotalConnections reached, maxTotalConnections=" + MultiThreadedHttpConnectionManager.this.getMaxTotalConnections());
                }
            }
            return connection;
        }
        
        public synchronized void deleteLeastUsedConnection() {
            final HttpConnection connection = this.freeConnections.removeFirst();
            if (connection != null) {
                final HostConfiguration connectionConfiguration = MultiThreadedHttpConnectionManager.this.configurationForConnection(connection);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Reclaiming unused connection, hostConfig=" + connectionConfiguration);
                }
                connection.close();
                final Iterator iter = MultiThreadedHttpConnectionManager.this.referenceToHostConfig.keySet().iterator();
                while (iter.hasNext()) {
                    final WeakReference connectionRef = iter.next();
                    if (connectionRef.get() == connection) {
                        iter.remove();
                        connectionRef.enqueue();
                        break;
                    }
                }
                final HostConnectionPool hostPool = this.getHostPool(connectionConfiguration);
                hostPool.freeConnections.remove(connection);
                final HostConnectionPool hostConnectionPool = hostPool;
                --hostConnectionPool.numConnections;
                --this.numConnections;
            }
            else if (LOG.isDebugEnabled()) {
                LOG.debug("Attempted to reclaim an unused connection but there were none.");
            }
        }
        
        public void freeConnection(final HttpConnection conn) {
            final HostConfiguration connectionConfiguration = MultiThreadedHttpConnectionManager.this.configurationForConnection(conn);
            if (LOG.isDebugEnabled()) {
                LOG.debug("Freeing connection, hostConfig=" + connectionConfiguration);
            }
            synchronized (this) {
                final HostConnectionPool hostPool = this.getHostPool(connectionConfiguration);
                hostPool.freeConnections.add(conn);
                if (hostPool.numConnections == 0) {
                    LOG.error("Host connection pool not found, hostConfig=" + connectionConfiguration);
                    hostPool.numConnections = 1;
                }
                this.freeConnections.add(conn);
                if (this.numConnections == 0) {
                    LOG.error("Host connection pool not found, hostConfig=" + connectionConfiguration);
                    this.numConnections = 1;
                }
                this.notifyWaitingThread(hostPool);
            }
        }
        
        public synchronized void notifyWaitingThread(final HostConfiguration configuration) {
            this.notifyWaitingThread(this.getHostPool(configuration));
        }
        
        public synchronized void notifyWaitingThread(final HostConnectionPool hostPool) {
            WaitingThread waitingThread = null;
            if (hostPool.waitingThreads.size() > 0) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Notifying thread waiting on host pool, hostConfig=" + hostPool.hostConfiguration);
                }
                waitingThread = hostPool.waitingThreads.removeFirst();
                this.waitingThreads.remove(waitingThread);
            }
            else if (this.waitingThreads.size() > 0) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("No-one waiting on host pool, notifying next waiting thread.");
                }
                waitingThread = this.waitingThreads.removeFirst();
                waitingThread.hostConnectionPool.waitingThreads.remove(waitingThread);
            }
            else if (LOG.isDebugEnabled()) {
                LOG.debug("Notifying no-one, there are no waiting threads");
            }
            if (waitingThread != null) {
                waitingThread.thread.interrupt();
            }
        }
        
        static /* synthetic */ void access$1(final ConnectionPool connectionPool, final int numConnections) {
            connectionPool.numConnections = numConnections;
        }
    }
    
    private class HostConnectionPool
    {
        public HostConfiguration hostConfiguration;
        public LinkedList freeConnections;
        public LinkedList waitingThreads;
        public int numConnections;
        
        HostConnectionPool() {
            this.freeConnections = new LinkedList();
            this.waitingThreads = new LinkedList();
            this.numConnections = 0;
        }
    }
    
    private static class HttpConnectionAdapter extends HttpConnection
    {
        private HttpConnection wrappedConnection;
        
        public HttpConnectionAdapter(final HttpConnection connection) {
            super(connection.getHost(), connection.getPort(), connection.getProtocol());
            this.wrappedConnection = connection;
        }
        
        public void setConnectionTimeout(final int timeout) {
            if (this.hasConnection()) {
                this.wrappedConnection.setConnectionTimeout(timeout);
            }
        }
        
        public void setHost(final String host) throws IllegalStateException {
            if (this.hasConnection()) {
                this.wrappedConnection.setHost(host);
            }
        }
        
        public String getHost() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getHost();
            }
            return null;
        }
        
        public void setHttpConnectionManager(final HttpConnectionManager httpConnectionManager) {
            if (this.hasConnection()) {
                this.wrappedConnection.setHttpConnectionManager(httpConnectionManager);
            }
        }
        
        public HttpConnectionManager getHttpConnectionManager() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getHttpConnectionManager();
            }
            return null;
        }
        
        public void setLastResponseInputStream(final InputStream inStream) {
            if (this.hasConnection()) {
                this.wrappedConnection.setLastResponseInputStream(inStream);
            }
        }
        
        public InputStream getLastResponseInputStream() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getLastResponseInputStream();
            }
            return null;
        }
        
        public void setLocalAddress(final InetAddress localAddress) {
            if (this.hasConnection()) {
                this.wrappedConnection.setLocalAddress(localAddress);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public InetAddress getLocalAddress() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getLocalAddress();
            }
            return null;
        }
        
        public boolean isOpen() {
            return this.hasConnection() && this.wrappedConnection.isOpen();
        }
        
        public void setPort(final int port) throws IllegalStateException {
            if (this.hasConnection()) {
                this.wrappedConnection.setPort(port);
            }
        }
        
        public int getPort() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getPort();
            }
            return -1;
        }
        
        public void setProtocol(final Protocol protocol) {
            if (this.hasConnection()) {
                this.wrappedConnection.setProtocol(protocol);
            }
        }
        
        public Protocol getProtocol() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getProtocol();
            }
            return null;
        }
        
        public boolean isProxied() {
            return this.hasConnection() && this.wrappedConnection.isProxied();
        }
        
        public void setProxyHost(final String host) throws IllegalStateException {
            if (this.hasConnection()) {
                this.wrappedConnection.setProxyHost(host);
            }
        }
        
        public String getProxyHost() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getProxyHost();
            }
            return null;
        }
        
        public void setProxyPort(final int port) throws IllegalStateException {
            if (this.hasConnection()) {
                this.wrappedConnection.setProxyPort(port);
            }
        }
        
        public int getProxyPort() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getProxyPort();
            }
            return -1;
        }
        
        public OutputStream getRequestOutputStream() throws IOException, IllegalStateException {
            if (this.hasConnection()) {
                return this.wrappedConnection.getRequestOutputStream();
            }
            return null;
        }
        
        public boolean isResponseAvailable() throws IOException {
            return this.hasConnection() && this.wrappedConnection.isResponseAvailable();
        }
        
        public boolean isResponseAvailable(final int timeout) throws IOException {
            return this.hasConnection() && this.wrappedConnection.isResponseAvailable(timeout);
        }
        
        public InputStream getResponseInputStream() throws IOException, IllegalStateException {
            if (this.hasConnection()) {
                return this.wrappedConnection.getResponseInputStream();
            }
            return null;
        }
        
        public boolean isSecure() {
            return this.hasConnection() && this.wrappedConnection.isSecure();
        }
        
        public void setSendBufferSize(final int sendBufferSize) throws SocketException {
            if (this.hasConnection()) {
                this.wrappedConnection.setSendBufferSize(sendBufferSize);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public int getSendBufferSize() throws SocketException {
            if (this.hasConnection()) {
                return this.wrappedConnection.getSendBufferSize();
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void setSoTimeout(final int timeout) throws SocketException, IllegalStateException {
            if (this.hasConnection()) {
                this.wrappedConnection.setSoTimeout(timeout);
            }
        }
        
        public int getSoTimeout() throws SocketException {
            if (this.hasConnection()) {
                return this.wrappedConnection.getSoTimeout();
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void setStaleCheckingEnabled(final boolean staleCheckEnabled) {
            if (this.hasConnection()) {
                this.wrappedConnection.setStaleCheckingEnabled(staleCheckEnabled);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public boolean isStaleCheckingEnabled() {
            return this.hasConnection() && this.wrappedConnection.isStaleCheckingEnabled();
        }
        
        public boolean isTransparent() {
            return this.hasConnection() && this.wrappedConnection.isTransparent();
        }
        
        public void setVirtualHost(final String host) throws IllegalStateException {
            if (this.hasConnection()) {
                this.wrappedConnection.setVirtualHost(host);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public String getVirtualHost() {
            if (this.hasConnection()) {
                return this.wrappedConnection.getVirtualHost();
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void close() {
            if (this.hasConnection()) {
                this.wrappedConnection.close();
            }
        }
        
        public void flushRequestOutputStream() throws IOException {
            if (this.hasConnection()) {
                this.wrappedConnection.flushRequestOutputStream();
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void open() throws IOException {
            if (this.hasConnection()) {
                this.wrappedConnection.open();
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void print(final String data) throws IOException, IllegalStateException, HttpRecoverableException {
            if (this.hasConnection()) {
                this.wrappedConnection.print(data);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void printLine() throws IOException, IllegalStateException, HttpRecoverableException {
            if (this.hasConnection()) {
                this.wrappedConnection.printLine();
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void printLine(final String data) throws IOException, IllegalStateException, HttpRecoverableException {
            if (this.hasConnection()) {
                this.wrappedConnection.printLine(data);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public String readLine() throws IOException, IllegalStateException {
            if (this.hasConnection()) {
                return this.wrappedConnection.readLine();
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void releaseConnection() {
            if (this.hasConnection()) {
                final HttpConnection wrappedConnection = this.wrappedConnection;
                this.wrappedConnection = null;
                wrappedConnection.releaseConnection();
            }
        }
        
        public void shutdownOutput() {
            if (this.hasConnection()) {
                this.wrappedConnection.shutdownOutput();
            }
        }
        
        public void tunnelCreated() throws IllegalStateException, IOException {
            if (this.hasConnection()) {
                this.wrappedConnection.tunnelCreated();
            }
        }
        
        public void write(final byte[] data, final int offset, final int length) throws IOException, IllegalStateException, HttpRecoverableException {
            if (this.hasConnection()) {
                this.wrappedConnection.write(data, offset, length);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void write(final byte[] data) throws IOException, IllegalStateException, HttpRecoverableException {
            if (this.hasConnection()) {
                this.wrappedConnection.write(data);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void writeLine() throws IOException, IllegalStateException, HttpRecoverableException {
            if (this.hasConnection()) {
                this.wrappedConnection.writeLine();
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        public void writeLine(final byte[] data) throws IOException, IllegalStateException, HttpRecoverableException {
            if (this.hasConnection()) {
                this.wrappedConnection.writeLine(data);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
        
        protected boolean hasConnection() {
            return this.wrappedConnection != null;
        }
        
        HttpConnection getWrappedConnection() {
            return this.wrappedConnection;
        }
    }
    
    private class ReferenceQueueThread extends Thread
    {
        public ReferenceQueueThread() {
            this.setDaemon(true);
        }
        
        public void run() {
            while (true) {
                try {
                    final Reference ref = MultiThreadedHttpConnectionManager.this.referenceQueue.remove();
                    if (ref == null) {
                        continue;
                    }
                    this.handleReference(ref);
                }
                catch (InterruptedException e) {
                    LOG.debug("ReferenceQueueThread interrupted", e);
                }
            }
        }
        
        private void handleReference(final Reference ref) {
            synchronized (MultiThreadedHttpConnectionManager.this.connectionPool) {
                if (MultiThreadedHttpConnectionManager.this.referenceToHostConfig.containsKey(ref)) {
                    final HostConfiguration config = MultiThreadedHttpConnectionManager.this.referenceToHostConfig.get(ref);
                    MultiThreadedHttpConnectionManager.this.referenceToHostConfig.remove(ref);
                    final HostConnectionPool hostPool2;
                    final HostConnectionPool hostPool = hostPool2 = MultiThreadedHttpConnectionManager.this.connectionPool.getHostPool(config);
                    --hostPool2.numConnections;
                    final ConnectionPool access$4 = MultiThreadedHttpConnectionManager.this.connectionPool;
                    ConnectionPool.access$1(access$4, access$4.numConnections - 1);
                    MultiThreadedHttpConnectionManager.this.connectionPool.notifyWaitingThread(config);
                }
            }
            // monitorexit(MultiThreadedHttpConnectionManager.access$4(this.this$0))
        }
    }
    
    private class WaitingThread
    {
        public HostConnectionPool hostConnectionPool;
        public Thread thread;
    }
}
