// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms;

import java.util.Hashtable;
import javax.jms.ConnectionFactory;
import javax.naming.NamingException;
import javax.jms.JMSException;
import java.util.concurrent.ConcurrentHashMap;
import org.xmodel.log.Log;
import javax.jms.Destination;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import javax.jms.Connection;
import javax.naming.InitialContext;

abstract class JmsClient implements JmsProvider
{
    protected static final String serverType;
    private static final int defaultThreadPriority = 7;
    protected String host;
    protected String port;
    protected String sslport;
    protected String username;
    protected String password;
    protected String router;
    protected String timeout;
    protected String retries;
    protected String timing;
    protected int threadPriority;
    protected static InitialContext initialContext;
    protected Connection cachedConnection;
    private final ReentrantLock lock;
    private final Map<String, Destination> destinations;
    static final Log log;
    
    static {
        serverType = System.getProperty("cornerstone.server.type", "none");
        JmsClient.initialContext = null;
        log = Log.getLog(JmsClient.class);
    }
    
    protected JmsClient() {
        this.lock = new ReentrantLock();
        this.destinations = new ConcurrentHashMap<String, Destination>();
        this.cachedConnection = null;
        this.threadPriority = 7;
    }
    
    @Override
    public void setThreadPriority(final int priority) {
        this.threadPriority = priority;
    }
    
    @Override
    public Connection createConnection() throws JMSException {
        return new JmsConnection(this, this.createRawConnection());
    }
    
    @Override
    public Connection getConnection() throws JMSException {
        this.lock.lock();
        try {
            if (this.cachedConnection == null) {
                this.cachedConnection = this.createConnection();
            }
            return this.cachedConnection;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void close() {
        this.lock.lock();
        try {
            this.cachedConnection.close();
        }
        catch (Exception ex) {
            return;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public Destination getDestination(final String name) throws JMSException {
        Destination result = this.destinations.get(name);
        if (result != null) {
            return result;
        }
        try {
            result = (Destination)this.getInitialContext().lookup(this.getDestinationIdentifier(name));
            this.destinations.put(name, result);
        }
        catch (NamingException e) {
            assert false;
            throw new JMSException(e.toString());
        }
        return result;
    }
    
    @Override
    public int[] getPorts() {
        final int[] result = { Integer.parseInt(this.port), Integer.parseInt(this.sslport) };
        return result;
    }
    
    protected abstract ConnectionFactory getConnectionFactory();
    
    protected abstract String getNamingURL();
    
    protected abstract String getNamingFactoryClass();
    
    protected abstract String getDestinationIdentifier(final String p0);
    
    protected InitialContext getInitialContext() throws NamingException {
        if (JmsClient.initialContext == null) {
            final Hashtable<String, String> env = new Hashtable<String, String>();
            env.put("java.naming.factory.initial", this.getNamingFactoryClass());
            env.put("java.naming.provider.url", this.getNamingURL());
            JmsClient.initialContext = new InitialContext(env);
        }
        return JmsClient.initialContext;
    }
    
    void readConfiguration() {
        this.router = System.getProperty("cornerstone.jms.router");
        this.host = System.getProperty("cornerstone.jms.hostname");
        this.port = System.getProperty("cornerstone.jms.port");
        this.sslport = System.getProperty("cornerstone.jms.sslport");
        this.username = System.getProperty("cornerstone.jms.username");
        this.password = System.getProperty("cornerstone.jms.password");
        this.timeout = System.getProperty("cornerstone.jms.timeout");
        this.retries = System.getProperty("cornerstone.jms.retries");
        this.timing = System.getProperty("cornerstone.jms.timing");
    }
    
    public Connection createRawConnection() throws JMSException {
        final Thread thread = Thread.currentThread();
        final int prevPty = thread.getPriority();
        try {
            this.readConfiguration();
            thread.setPriority(this.threadPriority);
            final ConnectionFactory factory = this.getConnectionFactory();
            JmsClient.log.debug("Create JMS connection");
            final Connection result = factory.createConnection(this.username, this.password);
            return result;
        }
        finally {
            thread.setPriority(prevPty);
        }
    }
    
    void closed(final JmsConnection connection) {
        if (connection == this.cachedConnection) {
            this.cachedConnection = null;
        }
        JmsClient.initialContext = null;
    }
}
