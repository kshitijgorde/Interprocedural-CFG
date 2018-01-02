// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.failover;

import org.slf4j.LoggerFactory;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStreamReader;
import java.net.InetAddress;
import org.apache.activemq.command.ConnectionId;
import java.util.Set;
import java.util.HashSet;
import org.apache.activemq.transport.TransportFactory;
import java.io.Reader;
import java.io.BufferedReader;
import java.util.Collection;
import org.apache.activemq.transport.FutureResponse;
import org.apache.activemq.transport.ResponseCallback;
import org.apache.activemq.util.IOExceptionSupport;
import org.apache.activemq.command.RemoveInfo;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.ArrayList;
import org.apache.activemq.Service;
import org.apache.activemq.util.ServiceSupport;
import java.io.IOException;
import org.apache.activemq.command.ConnectionControl;
import org.apache.activemq.state.Tracked;
import org.apache.activemq.command.Response;
import java.io.InterruptedIOException;
import org.apache.activemq.thread.Task;
import org.apache.activemq.thread.DefaultThreadPools;
import org.apache.activemq.transport.DefaultTransportListener;
import java.util.LinkedHashMap;
import org.apache.activemq.broker.SslContext;
import java.util.List;
import org.apache.activemq.thread.TaskRunner;
import org.apache.activemq.transport.Transport;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.activemq.command.Command;
import java.util.Map;
import org.apache.activemq.state.ConnectionStateTracker;
import java.net.URI;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.activemq.transport.TransportListener;
import org.slf4j.Logger;
import org.apache.activemq.transport.CompositeTransport;

public class FailoverTransport implements CompositeTransport
{
    private static final Logger LOG;
    private static final int DEFAULT_INITIAL_RECONNECT_DELAY = 10;
    private TransportListener transportListener;
    private boolean disposed;
    private boolean connected;
    private final CopyOnWriteArrayList<URI> uris;
    private final CopyOnWriteArrayList<URI> updated;
    private final Object reconnectMutex;
    private final Object backupMutex;
    private final Object sleepMutex;
    private final Object listenerMutex;
    private final ConnectionStateTracker stateTracker;
    private final Map<Integer, Command> requestMap;
    private URI connectedTransportURI;
    private URI failedConnectTransportURI;
    private final AtomicReference<Transport> connectedTransport;
    private final TaskRunner reconnectTask;
    private boolean started;
    private boolean initialized;
    private long initialReconnectDelay;
    private long maxReconnectDelay;
    private double backOffMultiplier;
    private long timeout;
    private boolean useExponentialBackOff;
    private boolean randomize;
    private int maxReconnectAttempts;
    private int startupMaxReconnectAttempts;
    private int connectFailures;
    private long reconnectDelay;
    private Exception connectionFailure;
    private boolean firstConnection;
    private boolean backup;
    private final List<BackupTransport> backups;
    private int backupPoolSize;
    private boolean trackMessages;
    private boolean trackTransactionProducers;
    private int maxCacheSize;
    private final TransportListener disposedListener;
    private final TransportListener myTransportListener;
    private boolean updateURIsSupported;
    private boolean reconnectSupported;
    private SslContext brokerSslContext;
    private String updateURIsURL;
    private boolean rebalanceUpdateURIs;
    private boolean doRebalance;
    
    public FailoverTransport() throws InterruptedIOException {
        this.uris = new CopyOnWriteArrayList<URI>();
        this.updated = new CopyOnWriteArrayList<URI>();
        this.reconnectMutex = new Object();
        this.backupMutex = new Object();
        this.sleepMutex = new Object();
        this.listenerMutex = new Object();
        this.stateTracker = new ConnectionStateTracker();
        this.requestMap = new LinkedHashMap<Integer, Command>();
        this.connectedTransport = new AtomicReference<Transport>();
        this.initialReconnectDelay = 10L;
        this.maxReconnectDelay = 30000L;
        this.backOffMultiplier = 2.0;
        this.timeout = -1L;
        this.useExponentialBackOff = true;
        this.randomize = true;
        this.reconnectDelay = 10L;
        this.firstConnection = true;
        this.backup = false;
        this.backups = new CopyOnWriteArrayList<BackupTransport>();
        this.backupPoolSize = 1;
        this.trackMessages = false;
        this.trackTransactionProducers = true;
        this.maxCacheSize = 131072;
        this.disposedListener = new DefaultTransportListener() {};
        this.myTransportListener = this.createTransportListener();
        this.updateURIsSupported = true;
        this.reconnectSupported = true;
        this.updateURIsURL = null;
        this.rebalanceUpdateURIs = true;
        this.doRebalance = false;
        this.brokerSslContext = SslContext.getCurrentSslContext();
        this.stateTracker.setTrackTransactions(true);
        this.reconnectTask = DefaultThreadPools.getDefaultTaskRunnerFactory().createTaskRunner(new Task() {
            @Override
            public boolean iterate() {
                boolean result = false;
                boolean buildBackup = true;
                final boolean doReconnect = !FailoverTransport.this.disposed;
                synchronized (FailoverTransport.this.backupMutex) {
                    if ((FailoverTransport.this.connectedTransport.get() == null || FailoverTransport.this.doRebalance) && !FailoverTransport.this.disposed) {
                        result = FailoverTransport.this.doReconnect();
                        buildBackup = false;
                    }
                }
                if (buildBackup) {
                    FailoverTransport.this.buildBackups();
                }
                else {
                    buildBackup = true;
                    try {
                        FailoverTransport.this.reconnectTask.wakeup();
                    }
                    catch (InterruptedException e) {
                        FailoverTransport.LOG.debug("Reconnect task has been interrupted.", e);
                    }
                }
                return result;
            }
        }, "ActiveMQ Failover Worker: " + System.identityHashCode(this));
    }
    
    TransportListener createTransportListener() {
        return new TransportListener() {
            @Override
            public void onCommand(final Object o) {
                final Command command = (Command)o;
                if (command == null) {
                    return;
                }
                if (command.isResponse()) {
                    Object object = null;
                    synchronized (FailoverTransport.this.requestMap) {
                        object = FailoverTransport.this.requestMap.remove(((Response)command).getCorrelationId());
                    }
                    if (object != null && object.getClass() == Tracked.class) {
                        ((Tracked)object).onResponses(command);
                    }
                }
                if (!FailoverTransport.this.initialized) {
                    FailoverTransport.this.initialized = true;
                }
                if (command.isConnectionControl()) {
                    FailoverTransport.this.handleConnectionControl((ConnectionControl)command);
                }
                if (FailoverTransport.this.transportListener != null) {
                    FailoverTransport.this.transportListener.onCommand(command);
                }
            }
            
            @Override
            public void onException(final IOException error) {
                try {
                    FailoverTransport.this.handleTransportFailure(error);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    FailoverTransport.this.transportListener.onException(new InterruptedIOException());
                }
            }
            
            @Override
            public void transportInterupted() {
                if (FailoverTransport.this.transportListener != null) {
                    FailoverTransport.this.transportListener.transportInterupted();
                }
            }
            
            @Override
            public void transportResumed() {
                if (FailoverTransport.this.transportListener != null) {
                    FailoverTransport.this.transportListener.transportResumed();
                }
            }
        };
    }
    
    public final void disposeTransport(final Transport transport) {
        transport.setTransportListener(this.disposedListener);
        ServiceSupport.dispose(transport);
    }
    
    public final void handleTransportFailure(final IOException e) throws InterruptedException {
        if (FailoverTransport.LOG.isTraceEnabled()) {
            FailoverTransport.LOG.trace(this + " handleTransportFailure: " + e);
        }
        Transport transport = this.connectedTransport.getAndSet(null);
        if (transport == null) {
            synchronized (this.reconnectMutex) {
                transport = this.connectedTransport.getAndSet(null);
            }
        }
        if (transport != null) {
            this.disposeTransport(transport);
            boolean reconnectOk = false;
            synchronized (this.reconnectMutex) {
                if (this.started) {
                    FailoverTransport.LOG.warn("Transport (" + transport.getRemoteAddress() + ") failed to " + this.connectedTransportURI + " , attempting to automatically reconnect due to: " + e);
                    FailoverTransport.LOG.debug("Transport failed with the following exception:", e);
                    reconnectOk = true;
                }
                this.initialized = false;
                this.failedConnectTransportURI = this.connectedTransportURI;
                this.connectedTransportURI = null;
                this.connected = false;
                if (this.transportListener != null) {
                    this.transportListener.transportInterupted();
                }
                if (reconnectOk) {
                    this.reconnectTask.wakeup();
                }
            }
        }
    }
    
    public final void handleConnectionControl(final ConnectionControl control) {
        String reconnectStr = control.getReconnectTo();
        if (reconnectStr != null) {
            reconnectStr = reconnectStr.trim();
            if (reconnectStr.length() > 0) {
                try {
                    final URI uri = new URI(reconnectStr);
                    if (this.isReconnectSupported()) {
                        this.reconnect(uri);
                        FailoverTransport.LOG.info("Reconnected to: " + uri);
                    }
                }
                catch (Exception e) {
                    FailoverTransport.LOG.error("Failed to handle ConnectionControl reconnect to " + reconnectStr, e);
                }
            }
        }
        this.processNewTransports(control.isRebalanceConnection(), control.getConnectedBrokers());
    }
    
    private final void processNewTransports(final boolean rebalance, String newTransports) {
        if (newTransports != null) {
            newTransports = newTransports.trim();
            if (newTransports.length() > 0 && this.isUpdateURIsSupported()) {
                final List<URI> list = new ArrayList<URI>();
                final StringTokenizer tokenizer = new StringTokenizer(newTransports, ",");
                while (tokenizer.hasMoreTokens()) {
                    final String str = tokenizer.nextToken();
                    try {
                        final URI uri = new URI(str);
                        list.add(uri);
                    }
                    catch (Exception e) {
                        FailoverTransport.LOG.error("Failed to parse broker address: " + str, e);
                    }
                }
                if (!list.isEmpty()) {
                    try {
                        this.updateURIs(rebalance, list.toArray(new URI[list.size()]));
                    }
                    catch (IOException e2) {
                        FailoverTransport.LOG.error("Failed to update transport URI's from: " + newTransports, e2);
                    }
                }
            }
        }
    }
    
    @Override
    public void start() throws Exception {
        synchronized (this.reconnectMutex) {
            FailoverTransport.LOG.debug("Started.");
            if (this.started) {
                return;
            }
            this.started = true;
            this.stateTracker.setMaxCacheSize(this.getMaxCacheSize());
            this.stateTracker.setTrackMessages(this.isTrackMessages());
            this.stateTracker.setTrackTransactionProducers(this.isTrackTransactionProducers());
            if (this.connectedTransport.get() != null) {
                this.stateTracker.restore(this.connectedTransport.get());
            }
            else {
                this.reconnect(false);
            }
        }
    }
    
    @Override
    public void stop() throws Exception {
        Transport transportToStop = null;
        synchronized (this.reconnectMutex) {
            FailoverTransport.LOG.debug("Stopped.");
            if (!this.started) {
                return;
            }
            this.started = false;
            this.disposed = true;
            this.connected = false;
            for (final BackupTransport t : this.backups) {
                t.setDisposed(true);
            }
            this.backups.clear();
            if (this.connectedTransport.get() != null) {
                transportToStop = this.connectedTransport.getAndSet(null);
            }
            this.reconnectMutex.notifyAll();
        }
        synchronized (this.sleepMutex) {
            this.sleepMutex.notifyAll();
        }
        this.reconnectTask.shutdown();
        if (transportToStop != null) {
            transportToStop.stop();
        }
    }
    
    public long getInitialReconnectDelay() {
        return this.initialReconnectDelay;
    }
    
    public void setInitialReconnectDelay(final long initialReconnectDelay) {
        this.initialReconnectDelay = initialReconnectDelay;
    }
    
    public long getMaxReconnectDelay() {
        return this.maxReconnectDelay;
    }
    
    public void setMaxReconnectDelay(final long maxReconnectDelay) {
        this.maxReconnectDelay = maxReconnectDelay;
    }
    
    public long getReconnectDelay() {
        return this.reconnectDelay;
    }
    
    public void setReconnectDelay(final long reconnectDelay) {
        this.reconnectDelay = reconnectDelay;
    }
    
    public double getReconnectDelayExponent() {
        return this.backOffMultiplier;
    }
    
    public void setReconnectDelayExponent(final double reconnectDelayExponent) {
        this.backOffMultiplier = reconnectDelayExponent;
    }
    
    public Transport getConnectedTransport() {
        return this.connectedTransport.get();
    }
    
    public URI getConnectedTransportURI() {
        return this.connectedTransportURI;
    }
    
    public int getMaxReconnectAttempts() {
        return this.maxReconnectAttempts;
    }
    
    public void setMaxReconnectAttempts(final int maxReconnectAttempts) {
        this.maxReconnectAttempts = maxReconnectAttempts;
    }
    
    public int getStartupMaxReconnectAttempts() {
        return this.startupMaxReconnectAttempts;
    }
    
    public void setStartupMaxReconnectAttempts(final int startupMaxReconnectAttempts) {
        this.startupMaxReconnectAttempts = startupMaxReconnectAttempts;
    }
    
    public long getTimeout() {
        return this.timeout;
    }
    
    public void setTimeout(final long timeout) {
        this.timeout = timeout;
    }
    
    public boolean isRandomize() {
        return this.randomize;
    }
    
    public void setRandomize(final boolean randomize) {
        this.randomize = randomize;
    }
    
    public boolean isBackup() {
        return this.backup;
    }
    
    public void setBackup(final boolean backup) {
        this.backup = backup;
    }
    
    public int getBackupPoolSize() {
        return this.backupPoolSize;
    }
    
    public void setBackupPoolSize(final int backupPoolSize) {
        this.backupPoolSize = backupPoolSize;
    }
    
    public boolean isTrackMessages() {
        return this.trackMessages;
    }
    
    public void setTrackMessages(final boolean trackMessages) {
        this.trackMessages = trackMessages;
    }
    
    public boolean isTrackTransactionProducers() {
        return this.trackTransactionProducers;
    }
    
    public void setTrackTransactionProducers(final boolean trackTransactionProducers) {
        this.trackTransactionProducers = trackTransactionProducers;
    }
    
    public int getMaxCacheSize() {
        return this.maxCacheSize;
    }
    
    public void setMaxCacheSize(final int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }
    
    private boolean isShutdownCommand(final Command command) {
        return command != null && (command.isShutdownInfo() || command instanceof RemoveInfo);
    }
    
    @Override
    public void oneway(final Object o) throws IOException {
        final Command command = (Command)o;
        Exception error = null;
        try {
            synchronized (this.reconnectMutex) {
                if (this.isShutdownCommand(command) && this.connectedTransport.get() == null) {
                    if (command.isShutdownInfo()) {
                        return;
                    }
                    if (command instanceof RemoveInfo || command.isMessageAck()) {
                        this.stateTracker.track(command);
                        final Response response = new Response();
                        response.setCorrelationId(command.getCommandId());
                        this.myTransportListener.onCommand(response);
                        return;
                    }
                }
                int i = 0;
                while (!this.disposed) {
                    try {
                        Transport transport = this.connectedTransport.get();
                        final long start = System.currentTimeMillis();
                        boolean timedout = false;
                        while (transport == null && !this.disposed && this.connectionFailure == null && !Thread.currentThread().isInterrupted()) {
                            FailoverTransport.LOG.trace("Waiting for transport to reconnect..: " + command);
                            final long end = System.currentTimeMillis();
                            if (this.timeout > 0L && end - start > this.timeout) {
                                timedout = true;
                                FailoverTransport.LOG.info("Failover timed out after " + (end - start) + "ms");
                                break;
                            }
                            try {
                                this.reconnectMutex.wait(100L);
                            }
                            catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                FailoverTransport.LOG.debug("Interupted: " + e, e);
                            }
                            transport = this.connectedTransport.get();
                        }
                        if (transport != null) {
                            final Tracked tracked = this.stateTracker.track(command);
                            synchronized (this.requestMap) {
                                if (tracked != null && tracked.isWaitingForResponse()) {
                                    this.requestMap.put(command.getCommandId(), tracked);
                                }
                                else if (tracked == null && command.isResponseRequired()) {
                                    this.requestMap.put(command.getCommandId(), command);
                                }
                            }
                            try {
                                transport.oneway(command);
                                this.stateTracker.trackBack(command);
                            }
                            catch (IOException e2) {
                                if (tracked == null) {
                                    if (command.isResponseRequired()) {
                                        this.requestMap.remove(command.getCommandId());
                                    }
                                    throw e2;
                                }
                            }
                            return;
                        }
                        if (this.disposed) {
                            error = new IOException("Transport disposed.");
                        }
                        else if (this.connectionFailure != null) {
                            error = this.connectionFailure;
                        }
                        else if (timedout) {
                            error = new IOException("Failover timeout of " + this.timeout + " ms reached.");
                        }
                        else {
                            error = new IOException("Unexpected failure.");
                        }
                    }
                    catch (IOException e3) {
                        if (FailoverTransport.LOG.isDebugEnabled()) {
                            FailoverTransport.LOG.debug("Send oneway attempt: " + i + " failed for command:" + command);
                        }
                        this.handleTransportFailure(e3);
                        ++i;
                        continue;
                    }
                    break;
                }
            }
        }
        catch (InterruptedException e4) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
        if (this.disposed || error == null) {
            return;
        }
        if (error instanceof IOException) {
            throw (IOException)error;
        }
        throw IOExceptionSupport.create(error);
    }
    
    @Override
    public FutureResponse asyncRequest(final Object command, final ResponseCallback responseCallback) throws IOException {
        throw new AssertionError((Object)"Unsupported Method");
    }
    
    @Override
    public Object request(final Object command) throws IOException {
        throw new AssertionError((Object)"Unsupported Method");
    }
    
    @Override
    public Object request(final Object command, final int timeout) throws IOException {
        throw new AssertionError((Object)"Unsupported Method");
    }
    
    @Override
    public void add(final boolean rebalance, final URI[] u) {
        boolean newURI = false;
        for (int i = 0; i < u.length; ++i) {
            if (!this.contains(u[i])) {
                this.uris.add(u[i]);
                newURI = true;
            }
        }
        if (newURI) {
            this.reconnect(rebalance);
        }
    }
    
    @Override
    public void remove(final boolean rebalance, final URI[] u) {
        for (int i = 0; i < u.length; ++i) {
            this.uris.remove(u[i]);
        }
    }
    
    public void add(final boolean rebalance, final String u) {
        try {
            final URI newURI = new URI(u);
            if (!this.contains(newURI)) {
                this.uris.add(newURI);
                this.reconnect(rebalance);
            }
        }
        catch (Exception e) {
            FailoverTransport.LOG.error("Failed to parse URI: " + u);
        }
    }
    
    public void reconnect(final boolean rebalance) {
        synchronized (this.reconnectMutex) {
            if (this.started) {
                if (rebalance) {
                    this.doRebalance = true;
                }
                FailoverTransport.LOG.debug("Waking up reconnect task");
                try {
                    this.reconnectTask.wakeup();
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            else {
                FailoverTransport.LOG.debug("Reconnect was triggered but transport is not started yet. Wait for start to connect the transport.");
            }
        }
    }
    
    private List<URI> getConnectList() {
        final ArrayList<URI> l = new ArrayList<URI>(this.uris);
        boolean removed = false;
        if (this.failedConnectTransportURI != null) {
            removed = l.remove(this.failedConnectTransportURI);
        }
        if (this.randomize) {
            for (int i = 0; i < l.size(); ++i) {
                final int p = (int)(Math.random() * 100.0 % l.size());
                final URI t = l.get(p);
                l.set(p, l.get(i));
                l.set(i, t);
            }
        }
        if (removed) {
            l.add(this.failedConnectTransportURI);
        }
        FailoverTransport.LOG.debug("urlList connectionList:" + l + ", from: " + this.uris);
        return l;
    }
    
    @Override
    public TransportListener getTransportListener() {
        return this.transportListener;
    }
    
    @Override
    public void setTransportListener(final TransportListener commandListener) {
        synchronized (this.listenerMutex) {
            this.transportListener = commandListener;
            this.listenerMutex.notifyAll();
        }
    }
    
    @Override
    public <T> T narrow(final Class<T> target) {
        if (target.isAssignableFrom(this.getClass())) {
            return target.cast(this);
        }
        final Transport transport = this.connectedTransport.get();
        if (transport != null) {
            return transport.narrow(target);
        }
        return null;
    }
    
    protected void restoreTransport(final Transport t) throws Exception, IOException {
        t.start();
        final ConnectionControl cc = new ConnectionControl();
        cc.setFaultTolerant(true);
        t.oneway(cc);
        this.stateTracker.restore(t);
        Map tmpMap = null;
        synchronized (this.requestMap) {
            tmpMap = new LinkedHashMap(this.requestMap);
        }
        for (final Command command : tmpMap.values()) {
            if (FailoverTransport.LOG.isTraceEnabled()) {
                FailoverTransport.LOG.trace("restore requestMap, replay: " + command);
            }
            t.oneway(command);
        }
    }
    
    public boolean isUseExponentialBackOff() {
        return this.useExponentialBackOff;
    }
    
    public void setUseExponentialBackOff(final boolean useExponentialBackOff) {
        this.useExponentialBackOff = useExponentialBackOff;
    }
    
    @Override
    public String toString() {
        return (this.connectedTransportURI == null) ? "unconnected" : this.connectedTransportURI.toString();
    }
    
    @Override
    public String getRemoteAddress() {
        final Transport transport = this.connectedTransport.get();
        if (transport != null) {
            return transport.getRemoteAddress();
        }
        return null;
    }
    
    @Override
    public boolean isFaultTolerant() {
        return true;
    }
    
    final boolean doReconnect() {
        Exception failure = null;
        synchronized (this.reconnectMutex) {
            final String fileURL = this.getUpdateURIsURL();
            if (fileURL != null) {
                BufferedReader in = null;
                String newUris = null;
                final StringBuffer buffer = new StringBuffer();
                try {
                    in = new BufferedReader(this.getURLStream(fileURL));
                    while (true) {
                        final String line = in.readLine();
                        if (line == null) {
                            break;
                        }
                        buffer.append(line);
                    }
                    newUris = buffer.toString();
                }
                catch (IOException ioe) {
                    FailoverTransport.LOG.error("Failed to read updateURIsURL: " + fileURL, ioe);
                }
                finally {
                    if (in != null) {
                        try {
                            in.close();
                        }
                        catch (IOException ex) {}
                    }
                }
                this.processNewTransports(this.isRebalanceUpdateURIs(), newUris);
            }
            if (this.disposed || this.connectionFailure != null) {
                this.reconnectMutex.notifyAll();
            }
            if ((this.connectedTransport.get() != null && !this.doRebalance) || this.disposed || this.connectionFailure != null) {
                return false;
            }
            final List<URI> connectList = this.getConnectList();
            if (connectList.isEmpty()) {
                failure = new IOException("No uris available to connect to.");
            }
            else {
                if (this.doRebalance) {
                    if (connectList.get(0).equals(this.connectedTransportURI)) {
                        return this.doRebalance = false;
                    }
                    FailoverTransport.LOG.debug("Doing rebalance from: " + this.connectedTransportURI + " to " + connectList);
                    try {
                        final Transport transport = this.connectedTransport.getAndSet(null);
                        if (transport != null) {
                            this.disposeTransport(transport);
                        }
                    }
                    catch (Exception e) {
                        FailoverTransport.LOG.debug("Caught an exception stopping existing transport for rebalance", e);
                    }
                    this.doRebalance = false;
                }
                if (!this.useExponentialBackOff || this.reconnectDelay == 10L) {
                    this.reconnectDelay = this.initialReconnectDelay;
                }
                synchronized (this.backupMutex) {
                    if (this.backup && !this.backups.isEmpty()) {
                        final BackupTransport bt = this.backups.remove(0);
                        final Transport t = bt.getTransport();
                        final URI uri = bt.getUri();
                        t.setTransportListener(this.myTransportListener);
                        try {
                            if (this.started) {
                                this.restoreTransport(t);
                            }
                            this.reconnectDelay = this.initialReconnectDelay;
                            this.failedConnectTransportURI = null;
                            this.connectedTransportURI = uri;
                            this.connectedTransport.set(t);
                            this.reconnectMutex.notifyAll();
                            this.connectFailures = 0;
                            FailoverTransport.LOG.info("Successfully reconnected to backup " + uri);
                            return false;
                        }
                        catch (Exception e2) {
                            FailoverTransport.LOG.debug("Backup transport failed", e2);
                        }
                    }
                }
                final Iterator<URI> iter = connectList.iterator();
                while (iter.hasNext() && this.connectedTransport.get() == null && !this.disposed) {
                    final URI uri2 = iter.next();
                    Transport t = null;
                    try {
                        FailoverTransport.LOG.debug("Attempting connect to: " + uri2);
                        SslContext.setCurrentSslContext(this.brokerSslContext);
                        t = TransportFactory.compositeConnect(uri2);
                        t.setTransportListener(this.myTransportListener);
                        t.start();
                        if (this.started) {
                            this.restoreTransport(t);
                        }
                        FailoverTransport.LOG.debug("Connection established");
                        this.reconnectDelay = this.initialReconnectDelay;
                        this.connectedTransportURI = uri2;
                        this.connectedTransport.set(t);
                        this.reconnectMutex.notifyAll();
                        this.connectFailures = 0;
                        synchronized (this.listenerMutex) {
                            if (this.transportListener == null) {
                                try {
                                    this.listenerMutex.wait(2000L);
                                }
                                catch (InterruptedException ex2) {}
                            }
                        }
                        if (this.transportListener != null) {
                            this.transportListener.transportResumed();
                        }
                        else {
                            FailoverTransport.LOG.debug("transport resumed by transport listener not set");
                        }
                        if (this.firstConnection) {
                            this.firstConnection = false;
                            FailoverTransport.LOG.info("Successfully connected to " + uri2);
                        }
                        else {
                            FailoverTransport.LOG.info("Successfully reconnected to " + uri2);
                        }
                        this.connected = true;
                        return false;
                    }
                    catch (Exception e3) {
                        failure = e3;
                        FailoverTransport.LOG.debug("Connect fail to: " + uri2 + ", reason: " + e3);
                        if (t == null) {
                            continue;
                        }
                        try {
                            t.stop();
                        }
                        catch (Exception ee) {
                            FailoverTransport.LOG.debug("Stop of failed transport: " + t + " failed with reason: " + ee);
                        }
                    }
                    finally {
                        SslContext.setCurrentSslContext(null);
                    }
                }
            }
            int reconnectAttempts = 0;
            if (this.firstConnection && this.startupMaxReconnectAttempts != 0) {
                reconnectAttempts = this.startupMaxReconnectAttempts;
            }
            if (reconnectAttempts == 0) {
                reconnectAttempts = this.maxReconnectAttempts;
            }
            if (reconnectAttempts > 0 && ++this.connectFailures >= reconnectAttempts) {
                FailoverTransport.LOG.error("Failed to connect to transport after: " + this.connectFailures + " attempt(s)");
                this.connectionFailure = failure;
                synchronized (this.listenerMutex) {
                    if (this.transportListener == null) {
                        try {
                            this.listenerMutex.wait(2000L);
                        }
                        catch (InterruptedException ex3) {}
                    }
                }
                if (this.transportListener != null) {
                    if (this.connectionFailure instanceof IOException) {
                        this.transportListener.onException((IOException)this.connectionFailure);
                    }
                    else {
                        this.transportListener.onException(IOExceptionSupport.create(this.connectionFailure));
                    }
                }
                this.reconnectMutex.notifyAll();
                return false;
            }
        }
        if (!this.disposed) {
            FailoverTransport.LOG.debug("Waiting " + this.reconnectDelay + " ms before attempting connection. ");
            synchronized (this.sleepMutex) {
                try {
                    this.sleepMutex.wait(this.reconnectDelay);
                }
                catch (InterruptedException e4) {
                    Thread.currentThread().interrupt();
                }
            }
            if (this.useExponentialBackOff) {
                this.reconnectDelay *= (long)this.backOffMultiplier;
                if (this.reconnectDelay > this.maxReconnectDelay) {
                    this.reconnectDelay = this.maxReconnectDelay;
                }
            }
        }
        return !this.disposed;
    }
    
    final boolean buildBackups() {
        synchronized (this.backupMutex) {
            if (!this.disposed && this.backup && this.backups.size() < this.backupPoolSize) {
                final List<URI> connectList = this.getConnectList();
                final List<BackupTransport> disposedList = new ArrayList<BackupTransport>();
                for (final BackupTransport bt : this.backups) {
                    if (bt.isDisposed()) {
                        disposedList.add(bt);
                    }
                }
                this.backups.removeAll(disposedList);
                disposedList.clear();
                final Iterator<URI> iter = connectList.iterator();
                while (iter.hasNext() && this.backups.size() < this.backupPoolSize) {
                    final URI uri = iter.next();
                    if (this.connectedTransportURI != null && !this.connectedTransportURI.equals(uri)) {
                        try {
                            SslContext.setCurrentSslContext(this.brokerSslContext);
                            final BackupTransport bt2 = new BackupTransport(this);
                            bt2.setUri(uri);
                            if (this.backups.contains(bt2)) {
                                continue;
                            }
                            final Transport t = TransportFactory.compositeConnect(uri);
                            t.setTransportListener(bt2);
                            t.start();
                            bt2.setTransport(t);
                            this.backups.add(bt2);
                        }
                        catch (Exception e) {
                            FailoverTransport.LOG.debug("Failed to build backup ", e);
                        }
                        finally {
                            SslContext.setCurrentSslContext(null);
                        }
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean isDisposed() {
        return this.disposed;
    }
    
    @Override
    public boolean isConnected() {
        return this.connected;
    }
    
    @Override
    public void reconnect(final URI uri) throws IOException {
        this.add(true, new URI[] { uri });
    }
    
    @Override
    public boolean isReconnectSupported() {
        return this.reconnectSupported;
    }
    
    public void setReconnectSupported(final boolean value) {
        this.reconnectSupported = value;
    }
    
    @Override
    public boolean isUpdateURIsSupported() {
        return this.updateURIsSupported;
    }
    
    public void setUpdateURIsSupported(final boolean value) {
        this.updateURIsSupported = value;
    }
    
    @Override
    public void updateURIs(final boolean rebalance, final URI[] updatedURIs) throws IOException {
        if (this.isUpdateURIsSupported()) {
            final List<URI> copy = new ArrayList<URI>(this.updated);
            final List<URI> add = new ArrayList<URI>();
            if (updatedURIs != null && updatedURIs.length > 0) {
                final Set<URI> set = new HashSet<URI>();
                for (int i = 0; i < updatedURIs.length; ++i) {
                    final URI uri = updatedURIs[i];
                    if (uri != null) {
                        set.add(uri);
                    }
                }
                final Iterator i$ = set.iterator();
                while (i$.hasNext()) {
                    final URI uri = i$.next();
                    if (!copy.remove(uri)) {
                        add.add(uri);
                    }
                }
                synchronized (this.reconnectMutex) {
                    this.updated.clear();
                    this.updated.addAll(add);
                    for (final URI uri2 : copy) {
                        this.uris.remove(uri2);
                    }
                    this.add(rebalance, add.toArray(new URI[add.size()]));
                }
            }
        }
    }
    
    public String getUpdateURIsURL() {
        return this.updateURIsURL;
    }
    
    public void setUpdateURIsURL(final String updateURIsURL) {
        this.updateURIsURL = updateURIsURL;
    }
    
    public boolean isRebalanceUpdateURIs() {
        return this.rebalanceUpdateURIs;
    }
    
    public void setRebalanceUpdateURIs(final boolean rebalanceUpdateURIs) {
        this.rebalanceUpdateURIs = rebalanceUpdateURIs;
    }
    
    @Override
    public int getReceiveCounter() {
        final Transport transport = this.connectedTransport.get();
        if (transport == null) {
            return 0;
        }
        return transport.getReceiveCounter();
    }
    
    public void connectionInterruptProcessingComplete(final ConnectionId connectionId) {
        synchronized (this.reconnectMutex) {
            this.stateTracker.connectionInterruptProcessingComplete(this, connectionId);
        }
    }
    
    public ConnectionStateTracker getStateTracker() {
        return this.stateTracker;
    }
    
    private boolean contains(final URI newURI) {
        boolean result = false;
        try {
            for (final URI uri : this.uris) {
                if (newURI.getPort() == uri.getPort()) {
                    final InetAddress newAddr = InetAddress.getByName(newURI.getHost());
                    final InetAddress addr = InetAddress.getByName(uri.getHost());
                    if (addr.equals(newAddr)) {
                        result = true;
                        break;
                    }
                    continue;
                }
            }
        }
        catch (IOException e) {
            result = true;
            FailoverTransport.LOG.error("Failed to verify URI " + newURI + " already known: " + e);
        }
        return result;
    }
    
    private InputStreamReader getURLStream(final String path) throws IOException {
        InputStreamReader result = null;
        URL url = null;
        try {
            url = new URL(path);
            result = new InputStreamReader(url.openStream());
        }
        catch (MalformedURLException ex) {}
        if (result == null) {
            result = new FileReader(path);
        }
        return result;
    }
    
    static {
        LOG = LoggerFactory.getLogger(FailoverTransport.class);
    }
}
