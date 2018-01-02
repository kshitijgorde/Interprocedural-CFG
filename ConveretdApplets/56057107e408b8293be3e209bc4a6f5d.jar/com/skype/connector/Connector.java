// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.io.PrintStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.io.PrintWriter;

public abstract class Connector
{
    private static Connector _instance;
    private final Object _debugListenerMutex;
    private ConnectorListener _debugListener;
    private volatile PrintWriter _debugOut;
    private volatile String _applicationName;
    private volatile int _status;
    private volatile int _connectTimeout;
    private volatile int _commandTimeout;
    private final Object _isInitializedMutex;
    private boolean _isInitialized;
    private ExecutorService _asyncSender;
    private ExecutorService _syncSender;
    private final List _asyncListeners;
    private final List _syncListeners;
    private final AtomicInteger _commandCount;
    private ExecutorService _commandExecutor;
    private final Map properties;
    
    public static synchronized Connector getInstance() {
        if (Connector._instance == null) {
            String connectorClassName = null;
            final String osName = System.getProperty("os.name");
            if (osName.startsWith("Windows")) {
                connectorClassName = "com.skype.connector.win32.Win32Connector";
            }
            else if (osName.startsWith("Linux") || osName.startsWith("LINUX")) {
                connectorClassName = "com.skype.connector.linux.LinuxConnector";
            }
            else if (osName.startsWith("Mac OS X")) {
                connectorClassName = "com.skype.connector.osx.OSXConnector";
            }
            if (connectorClassName == null) {
                throw new IllegalStateException("This platform is not supported by Skype4Java.");
            }
            try {
                final Class connectorClass = Class.forName(connectorClassName);
                final Method getInstance = connectorClass.getMethod("getInstance", (Class[])null);
                Connector._instance = (Connector)getInstance.invoke(null, (Object[])null);
            }
            catch (Exception e) {
                throw new IllegalStateException("The connector couldn't be initialized.", e);
            }
        }
        return Connector._instance;
    }
    
    protected static synchronized void setInstance(final Connector newInstance) throws ConnectorException {
        if (Connector._instance != null) {
            Connector._instance.dispose();
        }
        Connector._instance = newInstance;
    }
    
    protected Connector() {
        this._debugListenerMutex = new Object();
        this._debugOut = new PrintWriter(System.out, true);
        this._applicationName = "Skype4Java";
        this._status = 6;
        this._connectTimeout = 10000;
        this._commandTimeout = 10000;
        this._isInitializedMutex = new Object();
        this._asyncListeners = new ArrayList();
        this._syncListeners = new ArrayList();
        this._commandCount = new AtomicInteger();
        this.properties = new ConcurrentHashMap();
    }
    
    public String getInstalledPath() {
        return "skype";
    }
    
    public final void setDebug(final boolean on) throws ConnectorException {
        synchronized (this._debugListenerMutex) {
            if (on) {
                if (this._debugListener == null) {
                    this.addConnectorListener(this._debugListener = new AbstractConnectorListener() {
                        public void messageReceived(final ConnectorMessageEvent event) {
                            Connector.this.getDebugOut().println("<- " + event.getMessage());
                        }
                        
                        public void messageSent(final ConnectorMessageEvent event) {
                            Connector.this.getDebugOut().println("-> " + event.getMessage());
                        }
                    }, true, true);
                }
            }
            else if (this._debugListener != null) {
                this.removeConnectorListener(this._debugListener);
                this._debugListener = null;
            }
        }
        // monitorexit(this._debugListenerMutex)
    }
    
    public final void setDebugOut(final PrintWriter newDebugOut) {
        ConnectorUtils.checkNotNull("debugOut", newDebugOut);
        this._debugOut = newDebugOut;
    }
    
    public final void setDebugOut(final PrintStream newDebugOut) {
        ConnectorUtils.checkNotNull("debugOut", newDebugOut);
        this.setDebugOut(new PrintWriter(newDebugOut, true));
    }
    
    public final PrintWriter getDebugOut() {
        return this._debugOut;
    }
    
    public final void setApplicationName(final String newApplicationName) {
        ConnectorUtils.checkNotNull("applicationName", newApplicationName);
        this._applicationName = newApplicationName;
    }
    
    public final String getApplicationName() {
        return this._applicationName;
    }
    
    protected final void setStatus(final int newStatus) {
        this.fireStatusChanged(this._status = newStatus);
    }
    
    private void fireStatusChanged(final int newStatus) {
        this._syncSender.execute(new Runnable() {
            public void run() {
                Connector.this.fireStatusChanged(Connector.this.toConnectorListenerArray(Connector.this._syncListeners), newStatus);
            }
        });
        this._asyncSender.execute(new Runnable() {
            public void run() {
                Connector.this.fireStatusChanged(Connector.this.toConnectorListenerArray(Connector.this._asyncListeners), newStatus);
            }
        });
    }
    
    private ConnectorListener[] toConnectorListenerArray(final List listeners) {
        return listeners.toArray(new ConnectorListener[0]);
    }
    
    private void fireStatusChanged(final ConnectorListener[] listeners, final int newStatus) {
        final ConnectorStatusEvent event = new ConnectorStatusEvent(this, newStatus);
        for (int i = listeners.length - 1; i >= 0; --i) {
            listeners[i].statusChanged(event);
        }
    }
    
    public final int getStatus() {
        return this._status;
    }
    
    public final void setConnectTimeout(final int newConnectTimeout) {
        if (newConnectTimeout < 0) {
            throw new IllegalArgumentException("The connect timeout must be more than 0.");
        }
        this._connectTimeout = newConnectTimeout;
    }
    
    public final int getConnectTimeout() {
        return this._connectTimeout;
    }
    
    public final void setCommandTimeout(final int newCommandTimeout) {
        if (newCommandTimeout < 0) {
            throw new IllegalArgumentException("The connect timeout must be more than 0.");
        }
        this._commandTimeout = newCommandTimeout;
    }
    
    public final int getCommandTimeout() {
        return this._commandTimeout;
    }
    
    public final int connect() throws ConnectorException {
        this.initialize();
        final int status = this.connect(this.getConnectTimeout());
        if (status == 2) {
            this.sendApplicationName(this.getApplicationName());
            this.sendProtocol();
        }
        return status;
    }
    
    protected final void initialize() throws ConnectorException {
        synchronized (this._isInitializedMutex) {
            if (!this._isInitialized) {
                this._asyncSender = Executors.newCachedThreadPool(new ThreadFactory() {
                    private final AtomicInteger threadNumber = new AtomicInteger();
                    
                    public Thread newThread(final Runnable r) {
                        final Thread thread = new Thread(r, "AsyncSkypeMessageSender-" + this.threadNumber.getAndIncrement());
                        thread.setDaemon(true);
                        return thread;
                    }
                });
                this._syncSender = Executors.newSingleThreadExecutor(new ThreadFactory() {
                    public Thread newThread(final Runnable r) {
                        final Thread thread = new Thread(r, "SyncSkypeMessageSender");
                        thread.setDaemon(true);
                        return thread;
                    }
                });
                this._commandExecutor = Executors.newCachedThreadPool(new ThreadFactory() {
                    private final AtomicInteger threadNumber = new AtomicInteger();
                    
                    public Thread newThread(final Runnable r) {
                        final Thread thread = new Thread(r, "CommandExecutor-" + this.threadNumber.getAndIncrement());
                        thread.setDaemon(true);
                        return thread;
                    }
                });
                this.initializeImpl();
                this._isInitialized = true;
            }
        }
        // monitorexit(this._isInitializedMutex)
    }
    
    protected abstract void initializeImpl() throws ConnectorException;
    
    protected abstract int connect(final int p0) throws ConnectorException;
    
    protected void sendApplicationName(final String applicationName) throws ConnectorException {
    }
    
    protected void sendProtocol() throws ConnectorException {
        this.execute("PROTOCOL 9999", new String[] { "PROTOCOL " }, false);
    }
    
    public final void dispose() throws ConnectorException {
        synchronized (this._isInitializedMutex) {
            if (!this._isInitialized) {
                // monitorexit(this._isInitializedMutex)
                return;
            }
            this.disposeImpl();
            this.setStatus(6);
            this._commandExecutor.shutdown();
            this._syncSender.shutdown();
            this._asyncSender.shutdown();
            this._syncListeners.clear();
            this._asyncListeners.clear();
            // monitorenter(debugListenerMutex = this._debugListenerMutex)
            try {
                if (this._debugListener != null) {
                    this.addConnectorListener(this._debugListener, false, true);
                }
            }
            // monitorexit(debugListenerMutex)
            finally {}
            this._isInitialized = false;
        }
        // monitorexit(this._isInitializedMutex)
    }
    
    protected abstract void disposeImpl() throws ConnectorException;
    
    public boolean isRunning() throws ConnectorException {
        try {
            this.assureAttached();
            return true;
        }
        catch (ConnectorException e) {
            return false;
        }
    }
    
    public final String execute(final String command) throws ConnectorException {
        ConnectorUtils.checkNotNull("command", command);
        return this.execute(command, command);
    }
    
    public final String executeWithId(final String command, final String responseHeader) throws ConnectorException {
        ConnectorUtils.checkNotNull("command", command);
        ConnectorUtils.checkNotNull("responseHeader", responseHeader);
        final String header = "#" + this._commandCount.getAndIncrement() + " ";
        final String response = this.execute(String.valueOf(header) + command, new String[] { String.valueOf(header) + responseHeader, String.valueOf(header) + "ERROR " }, true);
        return response.substring(header.length());
    }
    
    public final Future waitForEndWithId(final String command, final String responseHeader, final NotificationChecker checker) throws ConnectorException {
        ConnectorUtils.checkNotNull("command", command);
        ConnectorUtils.checkNotNull("responseHeader", responseHeader);
        ConnectorUtils.checkNotNull("responseHeader", checker);
        final String header = "#" + this._commandCount.getAndIncrement() + " ";
        final NotificationChecker wrappedChecker = new NotificationChecker() {
            public boolean isTarget(final String message) {
                return checker.isTarget(message) || message.startsWith(String.valueOf(header) + "ERROR ");
            }
        };
        final Future future = this.execute(String.valueOf(header) + command, wrappedChecker, true, false);
        return new Future() {
            public boolean isDone() {
                return future.isDone();
            }
            
            public boolean isCancelled() {
                return future.isCancelled();
            }
            
            public Object get(final long timeout, final TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return this.removeId(future.get(timeout, unit));
            }
            
            public Object get() throws InterruptedException, ExecutionException {
                return this.removeId(future.get());
            }
            
            private String removeId(final String message) {
                if (message.startsWith(header)) {
                    return message.substring(header.length());
                }
                return message;
            }
            
            public boolean cancel(final boolean mayInterruptIfRunning) {
                return future.cancel(mayInterruptIfRunning);
            }
        };
    }
    
    public final String executeWithoutTimeout(final String command, final String responseHeader) throws ConnectorException {
        ConnectorUtils.checkNotNull("command", command);
        ConnectorUtils.checkNotNull("responseHeader", responseHeader);
        return this.execute(command, new String[] { responseHeader, "ERROR " }, true, true);
    }
    
    public final String execute(final String command, final String responseHeader) throws ConnectorException {
        ConnectorUtils.checkNotNull("command", command);
        ConnectorUtils.checkNotNull("responseHeader", responseHeader);
        return this.execute(command, new String[] { responseHeader, "ERROR " }, true);
    }
    
    public final String execute(final String command, final String[] responseHeaders) throws ConnectorException {
        ConnectorUtils.checkNotNull("command", command);
        ConnectorUtils.checkNotNull("responseHeaders", responseHeaders);
        return this.execute(command, responseHeaders, true);
    }
    
    protected final String execute(final String command, final String[] responseHeaders, final boolean checkAttached) throws ConnectorException {
        return this.execute(command, responseHeaders, checkAttached, false);
    }
    
    private String execute(final String command, final String[] responseHeaders, final boolean checkAttached, final boolean withoutTimeout) throws ConnectorException {
        final NotificationChecker checker = new NotificationChecker() {
            public boolean isTarget(final String message) {
                for (int i = 0; i < responseHeaders.length; ++i) {
                    final String responseHeader = responseHeaders[i];
                    if (message.startsWith(responseHeader)) {
                        return true;
                    }
                }
                return false;
            }
        };
        try {
            return this.execute(command, checker, checkAttached, withoutTimeout).get();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectorException("The '" + command + "' command was interrupted.", e);
        }
        catch (ExecutionException e2) {
            if (e2.getCause() instanceof NotAttachedException) {
                final NotAttachedException cause = (NotAttachedException)e2.getCause();
                throw new NotAttachedException(cause.getStatus(), cause);
            }
            if (e2.getCause() instanceof ConnectorException) {
                final ConnectorException cause2 = (ConnectorException)e2.getCause();
                throw new ConnectorException(cause2.getMessage(), cause2);
            }
            throw new ConnectorException("The '" + command + "' command execution failed.", e2);
        }
    }
    
    private Future execute(final String command, final NotificationChecker responseChecker, final boolean checkAttached, final boolean withoutTimeout) throws ConnectorException {
        ConnectorUtils.checkNotNull("command", command);
        ConnectorUtils.checkNotNull("responseChecker", responseChecker);
        if (checkAttached) {
            this.assureAttached();
        }
        return this._commandExecutor.submit((Callable<Object>)new Callable() {
            public Object call() throws Exception {
                final BlockingQueue responses = new LinkedBlockingQueue();
                final ConnectorListener listener = new AbstractConnectorListener() {
                    public void messageReceived(final ConnectorMessageEvent event) {
                        final String message = event.getMessage();
                        if (responseChecker.isTarget(message) || message.startsWith("PONG")) {
                            responses.add(message);
                        }
                    }
                };
                Connector.this.addConnectorListener(listener, false);
                Connector.this.fireMessageSent(command);
                Connector.this.sendCommand(command);
                try {
                    boolean pinged = false;
                    while (true) {
                        final String response = responses.poll(Connector.this.getCommandTimeout(), TimeUnit.MILLISECONDS);
                        if (response == null) {
                            if (pinged) {
                                Connector.this.setStatus(6);
                                throw new NotAttachedException(6);
                            }
                            Connector.this.fireMessageSent("PING");
                            Connector.this.sendCommand("PING");
                            pinged = true;
                        }
                        else {
                            if (!response.startsWith("PONG")) {
                                return response;
                            }
                            pinged = false;
                        }
                    }
                }
                finally {
                    Connector.this.removeConnectorListener(listener);
                }
            }
        });
    }
    
    private void fireMessageSent(final String message) {
        this.fireMessageEvent(message, false);
    }
    
    protected abstract void sendCommand(final String p0);
    
    private void assureAttached() throws ConnectorException {
        int attachedStatus = this.getStatus();
        if (attachedStatus != 2) {
            attachedStatus = this.connect();
            if (attachedStatus != 2) {
                throw new NotAttachedException(attachedStatus);
            }
        }
    }
    
    public final void addConnectorListener(final ConnectorListener listener) throws ConnectorException {
        this.addConnectorListener(listener, true);
    }
    
    public final void addConnectorListener(final ConnectorListener listener, final boolean checkAttached) throws ConnectorException {
        this.addConnectorListener(listener, checkAttached, false);
    }
    
    public final void addConnectorListener(final ConnectorListener listener, final boolean checkAttached, final boolean isSynchronous) throws ConnectorException {
        ConnectorUtils.checkNotNull("listener", listener);
        if (isSynchronous) {
            this._syncListeners.add(listener);
        }
        else {
            this._asyncListeners.add(listener);
        }
        if (checkAttached) {
            this.assureAttached();
        }
    }
    
    public final void removeConnectorListener(final ConnectorListener listener) {
        ConnectorUtils.checkNotNull("listener", listener);
        this._syncListeners.remove(listener);
        this._asyncListeners.remove(listener);
    }
    
    protected final void fireMessageReceived(final String message) {
        this.fireMessageEvent(message, true);
    }
    
    private void fireMessageEvent(final String message, final boolean isReceived) {
        ConnectorUtils.checkNotNull("message", message);
        this._syncSender.execute(new Runnable() {
            public void run() {
                Connector.this.fireMessageEvent(Connector.this.toConnectorListenerArray(Connector.this._syncListeners), message, isReceived);
            }
        });
        this._asyncSender.execute(new Runnable() {
            public void run() {
                Connector.this.fireMessageEvent(Connector.this.toConnectorListenerArray(Connector.this._asyncListeners), message, isReceived);
            }
        });
    }
    
    private void fireMessageEvent(final ConnectorListener[] listeners, final String message, final boolean isReceived) {
        final ConnectorMessageEvent event = new ConnectorMessageEvent(this, message);
        for (int i = listeners.length - 1; i >= 0; --i) {
            if (isReceived) {
                listeners[i].messageReceived(event);
            }
            else {
                listeners[i].messageSent(event);
            }
        }
    }
    
    public final void setStringProperty(final String name, final String value) {
        ConnectorUtils.checkNotNull("name", name);
        if (value != null) {
            this.properties.put(name, value);
        }
        else {
            this.properties.remove(name);
        }
    }
    
    public final String getStringProperty(final String name) {
        ConnectorUtils.checkNotNull("name", name);
        return this.properties.get(name);
    }
    
    public class Status
    {
        public static final int PENDING_AUTHORIZATION = 1;
        public static final int ATTACHED = 2;
        public static final int REFUSED = 3;
        public static final int NOT_AVAILABLE = 4;
        public static final int API_AVAILABLE = 5;
        public static final int NOT_RUNNING = 6;
    }
}
