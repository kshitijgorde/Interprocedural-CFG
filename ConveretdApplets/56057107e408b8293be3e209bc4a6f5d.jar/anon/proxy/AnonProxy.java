// 
// Decompiled by Procyon v0.5.30
// 

package anon.proxy;

import java.security.SignatureException;
import anon.client.TrustException;
import anon.util.ObjectQueue;
import java.util.Enumeration;
import anon.IServiceContainer;
import anon.AnonServerDescription;
import anon.NotConnectedToMixException;
import anon.AnonChannel;
import java.net.Socket;
import java.net.SocketException;
import java.io.InterruptedIOException;
import anon.infoservice.MixCascade;
import logging.LogHolder;
import logging.LogType;
import anon.client.AnonClient;
import anon.transport.connection.IStreamConnection;
import anon.AnonServiceFactory;
import anon.mixminion.MixminionServiceDescription;
import anon.tor.TorAnonServerDescription;
import anon.infoservice.AbstractMixCascadeContainer;
import anon.terms.TermsAndConditionConfirmation;
import anon.infoservice.IMutableProxyInterface;
import java.net.ServerSocket;
import java.util.Vector;
import anon.AnonService;
import anon.AnonServiceEventListener;

public final class AnonProxy implements Runnable, AnonServiceEventListener
{
    public static final int UNLIMITED_REQUESTS = Integer.MAX_VALUE;
    public static final int MIN_REQUESTS = 5;
    public static final int E_BIND = -2;
    public static final int E_MIX_PROTOCOL_NOT_SUPPORTED = -10;
    public static final int E_SIGNATURE_CHECK_FIRSTMIX_FAILED = -22;
    public static final int E_SIGNATURE_CHECK_OTHERMIX_FAILED = -23;
    private static final int RECONNECT_INTERVAL = 5000;
    private int m_maxRequests;
    private AnonService m_Anon;
    private AnonService m_Tor;
    private AnonService m_Mixminion;
    private Vector m_anonServiceListener;
    private Thread threadRun;
    private ServerSocket m_socketListener;
    private IMutableProxyInterface m_proxyInterface;
    private IProxyListener m_ProxyListener;
    private volatile int m_numChannels;
    private boolean m_bReconnecting;
    private final Object THREAD_SYNC;
    private final Object SHUTDOWN_SYNC;
    private boolean bShuttingDown;
    private final ProxyCallbackHandler m_callbackHandler;
    private HTTPProxyCallback m_httpProxyCallback;
    private DecompressionProxyCallback m_decompressionProxyCallback;
    private TermsAndConditionConfirmation termsConfirmation;
    private AbstractMixCascadeContainer m_currentMixCascade;
    private TorAnonServerDescription m_currentTorParams;
    private MixminionServiceDescription m_currentMixminionParams;
    private boolean m_forwardedConnection;
    private int m_maxDummyTrafficInterval;
    
    public AnonProxy(final ServerSocket serverSocket, final TermsAndConditionConfirmation termsAndConditionConfirmation) {
        this(serverSocket, null, termsAndConditionConfirmation);
    }
    
    public AnonProxy(final ServerSocket socketListener, final IMutableProxyInterface proxyInterface, final TermsAndConditionConfirmation termsConfirmation) {
        this.m_maxRequests = Integer.MAX_VALUE;
        this.m_proxyInterface = new IMutableProxyInterface.DummyMutableProxyInterface();
        this.m_numChannels = 0;
        this.m_bReconnecting = false;
        this.THREAD_SYNC = new Object();
        this.SHUTDOWN_SYNC = new Object();
        this.bShuttingDown = false;
        this.m_callbackHandler = new ProxyCallbackHandler();
        this.m_httpProxyCallback = null;
        this.m_decompressionProxyCallback = null;
        this.termsConfirmation = null;
        this.m_currentMixCascade = new DummyMixCascadeContainer();
        this.m_maxDummyTrafficInterval = 30000;
        if (socketListener == null) {
            throw new IllegalArgumentException("Socket listener is null!");
        }
        this.m_socketListener = socketListener;
        if (proxyInterface != null) {
            this.m_proxyInterface = proxyInterface;
        }
        (this.m_Anon = AnonServiceFactory.getAnonServiceInstance("AN.ON")).setProxy(this.m_proxyInterface);
        this.setDummyTraffic(Integer.MAX_VALUE);
        this.m_forwardedConnection = false;
        this.m_anonServiceListener = new Vector();
        this.m_Anon.removeEventListeners();
        this.m_Anon.addEventListener(this);
        this.termsConfirmation = termsConfirmation;
    }
    
    public AnonProxy(final ServerSocket socketListener, final IStreamConnection streamConnection, final int maxDummyTrafficInterval, final TermsAndConditionConfirmation termsConfirmation) {
        this.m_maxRequests = Integer.MAX_VALUE;
        this.m_proxyInterface = new IMutableProxyInterface.DummyMutableProxyInterface();
        this.m_numChannels = 0;
        this.m_bReconnecting = false;
        this.THREAD_SYNC = new Object();
        this.SHUTDOWN_SYNC = new Object();
        this.bShuttingDown = false;
        this.m_callbackHandler = new ProxyCallbackHandler();
        this.m_httpProxyCallback = null;
        this.m_decompressionProxyCallback = null;
        this.termsConfirmation = null;
        this.m_currentMixCascade = new DummyMixCascadeContainer();
        this.m_maxDummyTrafficInterval = 30000;
        if (socketListener == null) {
            throw new IllegalArgumentException("Socket listener is null!");
        }
        this.m_socketListener = socketListener;
        this.m_Anon = new AnonClient(streamConnection);
        this.m_forwardedConnection = true;
        this.setDummyTraffic(this.m_maxDummyTrafficInterval = maxDummyTrafficInterval);
        this.m_anonServiceListener = new Vector();
        this.m_Anon.removeEventListeners();
        this.m_Anon.addEventListener(this);
        this.termsConfirmation = termsConfirmation;
    }
    
    public void enableProxyCallback(final ProxyCallback proxyCallback) {
        if (this.m_callbackHandler == null) {
            LogHolder.log(4, LogType.NET, "No ProxyCallbackHandler activated: cannot process HTTP headers.");
            return;
        }
        this.m_callbackHandler.registerProxyCallback(proxyCallback);
    }
    
    public void disableProxyCallback(final ProxyCallback proxyCallback) {
        if (proxyCallback != null) {
            this.m_callbackHandler.removeCallback(proxyCallback);
        }
    }
    
    public void setHTTPHeaderProcessingEnabled(final boolean b) {
        synchronized (this.m_callbackHandler) {
            if (b) {
                if (this.m_httpProxyCallback == null) {
                    this.m_httpProxyCallback = new HTTPProxyCallback();
                }
                this.enableProxyCallback(this.m_httpProxyCallback);
            }
            else {
                this.disableProxyCallback(this.m_httpProxyCallback);
                this.m_httpProxyCallback = null;
            }
        }
    }
    
    public void setHTTPDecompressionEnabled(final boolean b) {
        if (b) {
            if (this.m_decompressionProxyCallback == null) {
                this.m_decompressionProxyCallback = new DecompressionProxyCallback();
            }
            this.enableProxyCallback(this.m_decompressionProxyCallback);
        }
        else {
            this.disableProxyCallback(this.m_decompressionProxyCallback);
            this.m_decompressionProxyCallback = null;
        }
    }
    
    public synchronized void removeHTTPConnectionListener(final AbstractHTTPConnectionListener abstractHTTPConnectionListener) {
        if (abstractHTTPConnectionListener == null) {
            return;
        }
        if (this.m_callbackHandler == null) {
            LogHolder.log(4, LogType.NET, "No Callbackhandler activated: cannot activate JonDoFox headers.");
            return;
        }
        synchronized (this.m_callbackHandler) {
            if (this.m_httpProxyCallback != null) {
                this.m_httpProxyCallback.removeHTTPConnectionListener(abstractHTTPConnectionListener);
            }
        }
    }
    
    public void addHTTPConnectionListener(final AbstractHTTPConnectionListener abstractHTTPConnectionListener) {
        if (this.m_callbackHandler == null) {
            LogHolder.log(4, LogType.NET, "No Callbackhandler activated: cannot activate JonDoFox headers.");
            return;
        }
        synchronized (this.m_callbackHandler) {
            if (this.m_httpProxyCallback == null) {
                LogHolder.log(4, LogType.NET, "No HTTPProxyCallback activated: cannot activate JonDoFox headers.");
                return;
            }
            this.m_httpProxyCallback.addHTTPConnectionListener(abstractHTTPConnectionListener);
            this.enableProxyCallback(this.m_httpProxyCallback);
        }
    }
    
    public MixCascade getMixCascade() {
        try {
            return this.m_currentMixCascade.getCurrentCascade();
        }
        catch (NullPointerException ex) {
            return null;
        }
    }
    
    public void setTorParams(final TorAnonServerDescription currentTorParams) {
        this.m_currentTorParams = currentTorParams;
    }
    
    public TorAnonServerDescription getTorParams() {
        return this.m_currentTorParams;
    }
    
    public void setMixminionParams(final MixminionServiceDescription currentMixminionParams) {
        this.m_currentMixminionParams = currentMixminionParams;
    }
    
    public MixminionServiceDescription getMixminionParams() {
        return this.m_currentMixminionParams;
    }
    
    public void setMaxConcurrentRequests(final int maxRequests) {
        if (maxRequests > 5) {
            this.m_maxRequests = maxRequests;
        }
    }
    
    public int getMaxConcurrentRequests() {
        return this.m_maxRequests;
    }
    
    public void setDummyTraffic(final int dummyTraffic) {
        try {
            if (!this.m_forwardedConnection || this.m_maxDummyTrafficInterval < 0 || dummyTraffic == Integer.MAX_VALUE) {
                ((AnonClient)this.m_Anon).setDummyTraffic(dummyTraffic);
            }
            else if (dummyTraffic >= 0) {
                ((AnonClient)this.m_Anon).setDummyTraffic(Math.min(dummyTraffic, this.m_maxDummyTrafficInterval));
            }
            else {
                ((AnonClient)this.m_Anon).setDummyTraffic(this.m_maxDummyTrafficInterval);
            }
        }
        catch (ClassCastException ex) {}
    }
    
    public void stop() {
        synchronized (this.SHUTDOWN_SYNC) {
            if (this.threadRun == null) {
                this.disconnected();
                return;
            }
            this.bShuttingDown = true;
            this.m_Anon.shutdown(true);
            if (this.m_Tor != null) {
                this.m_Tor.shutdown(true);
            }
            if (this.m_Mixminion != null) {
                this.m_Mixminion.shutdown(true);
            }
            int n = 0;
            while (this.threadRun.isAlive()) {
                try {
                    this.threadRun.interrupt();
                    this.threadRun.join(500L);
                    ++n;
                }
                catch (InterruptedException ex) {}
            }
            this.m_Tor = null;
            this.m_Mixminion = null;
            this.threadRun = null;
            this.packetMixed(0L);
            this.disconnected();
            this.bShuttingDown = false;
        }
    }
    
    public void run() {
        int soTimeout = 0;
        LogHolder.log(7, LogType.NET, "AnonProxy is running as Thread");
        try {
            soTimeout = this.m_socketListener.getSoTimeout();
        }
        catch (Exception ex4) {
            if (this.bShuttingDown) {
                return;
            }
        }
        try {
            this.m_socketListener.setSoTimeout(2000);
        }
        catch (Exception ex) {
            LogHolder.log(7, LogType.NET, "Could not set accept time out!", ex);
        }
        if (this.bShuttingDown) {
            try {
                this.m_socketListener.setSoTimeout(soTimeout);
            }
            catch (Exception ex5) {}
            return;
        }
        final OpenSocketRequester openSocketRequester = new OpenSocketRequester(this, this.THREAD_SYNC);
        final Thread thread = new Thread(openSocketRequester, openSocketRequester.getClass().getName());
        thread.start();
        try {
            while (!Thread.currentThread().isInterrupted() && !this.bShuttingDown) {
                Socket accept;
                try {
                    accept = this.m_socketListener.accept();
                }
                catch (InterruptedIOException ex6) {
                    continue;
                }
                try {
                    accept.setSoTimeout(0);
                    openSocketRequester.pushSocket(accept);
                }
                catch (SocketException ex2) {
                    LogHolder.log(3, LogType.NET, "Could not set non-Blocking mode for Channel-Socket!", ex2);
                }
            }
        }
        catch (Exception ex3) {
            LogHolder.log(3, LogType.NET, ex3);
        }
        try {
            this.m_socketListener.setSoTimeout(soTimeout);
        }
        catch (Exception ex7) {}
        thread.interrupt();
        openSocketRequester.close();
        try {
            thread.join();
        }
        catch (InterruptedException ex8) {}
        LogHolder.log(6, LogType.NET, "JAPAnonProxyServer stopped.");
    }
    
    AnonChannel createChannel(final int n) throws NotConnectedToMixException, Exception {
        if (n == 1) {
            if (this.m_Tor != null) {
                return this.m_Tor.createChannel(1);
            }
            if (this.getMixCascade().isSocks5Supported()) {
                return this.m_Anon.createChannel(1);
            }
            LogHolder.log(3, LogType.NET, "Received SOCKS request, but no SOCKS server is available.");
        }
        else {
            if (n == 0) {
                return this.m_Anon.createChannel(0);
            }
            if (n == 2 && this.m_Mixminion != null) {
                return this.m_Mixminion.createChannel(2);
            }
        }
        return null;
    }
    
    void reconnect() {
        synchronized (this.THREAD_SYNC) {
            if (this.m_Anon.isConnected() || this.bShuttingDown || Thread.currentThread().isInterrupted()) {
                return;
            }
            if (!this.m_currentMixCascade.isReconnectedAutomatically()) {
                this.stop();
                this.THREAD_SYNC.notifyAll();
                return;
            }
            if (this.m_bReconnecting) {
                return;
            }
            this.m_bReconnecting = true;
            while (this.threadRun != null && this.m_currentMixCascade.isReconnectedAutomatically() && !this.m_Anon.isConnected() && !this.bShuttingDown && !Thread.currentThread().isInterrupted()) {
                final MixCascade nextCascade = this.m_currentMixCascade.getNextCascade();
                LogHolder.log(4, LogType.NET, "Try reconnect to AN.ON service. Connecting to " + nextCascade.getName() + "...");
                if (this.m_Anon.initialize(nextCascade, this.m_currentMixCascade, this.termsConfirmation) == 0) {
                    this.m_currentMixCascade.keepCurrentService(true);
                    break;
                }
                try {
                    this.THREAD_SYNC.wait(5000L);
                }
                catch (InterruptedException ex) {
                    break;
                }
            }
            this.m_bReconnecting = false;
            if ((this.threadRun == null || !this.isConnected()) && !this.m_currentMixCascade.isReconnectedAutomatically()) {
                this.stop();
                this.THREAD_SYNC.notifyAll();
            }
        }
    }
    
    public void setProxyListener(final IProxyListener proxyListener) {
        this.m_ProxyListener = proxyListener;
    }
    
    public int start(final AbstractMixCascadeContainer abstractMixCascadeContainer) {
        synchronized (this.THREAD_SYNC) {
            boolean b = false;
            boolean b2 = false;
            AbstractMixCascadeContainer currentMixCascade;
            if (abstractMixCascadeContainer == null) {
                currentMixCascade = new DummyMixCascadeContainer();
            }
            else {
                currentMixCascade = new EncapsulatedMixCascadeContainer(abstractMixCascadeContainer);
            }
            Label_0173: {
                if (this.getMixCascade() != currentMixCascade.getCurrentCascade() && this.threadRun != null) {
                    b2 = true;
                    this.THREAD_SYNC.notifyAll();
                    synchronized (this.SHUTDOWN_SYNC) {
                        this.m_Anon.shutdown(false);
                        int n = 0;
                        while (this.threadRun.isAlive()) {
                            try {
                                this.threadRun.interrupt();
                                this.threadRun.join(1000L);
                                if (n > 3) {
                                    this.threadRun.stop();
                                }
                                ++n;
                            }
                            catch (InterruptedException ex) {}
                        }
                        break Label_0173;
                    }
                }
                if (this.threadRun == null) {
                    this.m_Anon.shutdown(true);
                }
            }
            this.m_currentMixCascade = currentMixCascade;
            final MixCascade nextCascade = this.m_currentMixCascade.getNextCascade();
            LogHolder.log(5, LogType.NET, "Connecting to AN.ON service " + nextCascade.getName() + "...");
            this.m_numChannels = 0;
            final int initialize = this.m_Anon.initialize(nextCascade, this.m_currentMixCascade, this.termsConfirmation);
            if (initialize != 0) {
                if (initialize == -24 || initialize == -32 || !this.m_currentMixCascade.isReconnectedAutomatically() || (!this.m_currentMixCascade.isServiceAutoSwitched() && (initialize == -22 || initialize == -23 || initialize == -10 || initialize == -27))) {
                    return initialize;
                }
                b = true;
            }
            else {
                this.m_currentMixCascade.keepCurrentService(true);
            }
            LogHolder.log(7, LogType.NET, "AN.ON initialized");
            Label_0528: {
                if (b2) {
                    synchronized (this.SHUTDOWN_SYNC) {
                        if (this.threadRun == null) {
                            LogHolder.log(5, LogType.NET, "Noticed shutdown. Stopping AN.ON...");
                            Thread.currentThread().interrupt();
                            return -24;
                        }
                        break Label_0528;
                    }
                }
                if (this.m_currentTorParams != null) {
                    (this.m_Tor = AnonServiceFactory.getAnonServiceInstance("TOR")).setProxy(this.m_proxyInterface);
                    this.m_Tor.initialize(this.m_currentTorParams, null, this.termsConfirmation);
                    LogHolder.log(7, LogType.NET, "Tor initialized");
                }
                if (this.m_currentMixminionParams != null) {
                    (this.m_Mixminion = AnonServiceFactory.getAnonServiceInstance("Mixminion")).setProxy(this.m_proxyInterface);
                    this.m_Mixminion.initialize(this.m_currentMixminionParams, null, this.termsConfirmation);
                    LogHolder.log(7, LogType.NET, "Mixminion initialized");
                }
            }
            (this.threadRun = new Thread(this, "JAP - AnonProxy")).setDaemon(true);
            this.threadRun.start();
            if (b) {
                this.connectionError();
                return initialize;
            }
            return 0;
        }
    }
    
    protected synchronized void decNumChannels() {
        --this.m_numChannels;
        if (this.m_ProxyListener != null) {
            this.m_ProxyListener.channelsChanged(this.m_numChannels);
        }
    }
    
    protected synchronized void incNumChannels() {
        ++this.m_numChannels;
        if (this.m_ProxyListener != null) {
            this.m_ProxyListener.channelsChanged(this.m_numChannels);
        }
    }
    
    protected synchronized void transferredBytes(final long n, final int n2) {
        if (this.m_ProxyListener != null) {
            this.m_ProxyListener.transferedBytes(n, n2);
        }
    }
    
    private void fireDisconnected() {
        synchronized (this.m_anonServiceListener) {
            final Enumeration<AnonServiceEventListener> elements = this.m_anonServiceListener.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().disconnected();
            }
        }
    }
    
    private void fireConnecting(final AnonServerDescription anonServerDescription) {
        synchronized (this.m_anonServiceListener) {
            final Enumeration<AnonServiceEventListener> elements = this.m_anonServiceListener.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().connecting(anonServerDescription);
            }
        }
    }
    
    private void fireConnectionEstablished(final AnonServerDescription anonServerDescription) {
        synchronized (this.m_anonServiceListener) {
            final Enumeration<AnonServiceEventListener> elements = this.m_anonServiceListener.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().connectionEstablished(anonServerDescription);
            }
        }
    }
    
    private void fireConnectionError() {
        synchronized (this.m_anonServiceListener) {
            final Enumeration<AnonServiceEventListener> elements = this.m_anonServiceListener.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().connectionError();
            }
        }
    }
    
    public void connecting(final AnonServerDescription anonServerDescription) {
        LogHolder.log(6, LogType.NET, "AnonProxy received connecting.");
        this.fireConnecting(anonServerDescription);
    }
    
    public void connectionEstablished(final AnonServerDescription anonServerDescription) {
        LogHolder.log(1, LogType.NET, "AnonProxy received connectionEstablished to '" + anonServerDescription + "'.");
        this.fireConnectionEstablished(anonServerDescription);
    }
    
    public void disconnected() {
        LogHolder.log(1, LogType.NET, "AnonProxy was disconnected.");
        this.fireDisconnected();
    }
    
    public void connectionError() {
        LogHolder.log(3, LogType.NET, "AnonProxy received connectionError", true);
        this.fireConnectionError();
        new Thread(new Runnable() {
            public void run() {
                AnonProxy.this.reconnect();
            }
        }, "Connection error reconnect thead").start();
    }
    
    public synchronized void addEventListener(final AnonServiceEventListener anonServiceEventListener) {
        synchronized (this.m_anonServiceListener) {
            final Enumeration<Object> elements = this.m_anonServiceListener.elements();
            while (elements.hasMoreElements()) {
                if (anonServiceEventListener.equals(elements.nextElement())) {
                    return;
                }
            }
            this.m_anonServiceListener.addElement(anonServiceEventListener);
        }
    }
    
    public synchronized void removeEventListener(final AnonServiceEventListener anonServiceEventListener) {
        this.m_anonServiceListener.removeElement(anonServiceEventListener);
    }
    
    public boolean isConnected() {
        final AnonService anon = this.m_Anon;
        return anon != null && anon.isConnected();
    }
    
    public void packetMixed(final long n) {
        if (this.isConnected() || n == 0L) {
            synchronized (this.m_anonServiceListener) {
                final Enumeration<AnonServiceEventListener> elements = this.m_anonServiceListener.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().packetMixed(n);
                }
            }
        }
    }
    
    public void dataChainErrorSignaled() {
        LogHolder.log(3, LogType.NET, "Proxy has been nuked");
        this.m_currentMixCascade.keepCurrentService(false);
        this.m_Anon.shutdown(false);
        synchronized (this.m_anonServiceListener) {
            final Enumeration<AnonServiceEventListener> elements = this.m_anonServiceListener.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().dataChainErrorSignaled();
            }
        }
        this.reconnect();
    }
    
    private class OpenSocketRequester implements Runnable
    {
        private ObjectQueue m_socketQueue;
        private AnonProxy m_proxy;
        private Object m_syncObject;
        private boolean m_bIsClosed;
        
        public OpenSocketRequester(final AnonProxy proxy, final Object syncObject) {
            this.m_socketQueue = new ObjectQueue();
            this.m_bIsClosed = false;
            this.m_proxy = proxy;
            this.m_syncObject = syncObject;
        }
        
        public void pushSocket(final Socket socket) {
            synchronized (this.m_socketQueue) {
                this.m_socketQueue.push(socket);
                this.m_socketQueue.notify();
            }
        }
        
        public void close() {
            this.m_bIsClosed = true;
            synchronized (this.m_socketQueue) {
                this.m_socketQueue.notify();
            }
        }
        
        public void run() {
            while (!Thread.currentThread().isInterrupted() && !this.m_bIsClosed) {
                if (this.m_socketQueue.getSize() > 0 && AnonProxyRequest.getNrOfRequests() < AnonProxy.this.m_maxRequests) {
                    try {
                        new AnonProxyRequest(this.m_proxy, (Socket)this.m_socketQueue.pop(), this.m_syncObject, AnonProxy.this.m_callbackHandler);
                    }
                    catch (Exception ex) {
                        LogHolder.log(3, LogType.NET, ex);
                    }
                }
                else {
                    try {
                        synchronized (this.m_socketQueue) {
                            if (AnonProxyRequest.getNrOfRequests() >= AnonProxy.this.m_maxRequests) {
                                this.m_socketQueue.wait(100L);
                            }
                            else {
                                this.m_socketQueue.wait();
                            }
                        }
                    }
                    catch (InterruptedException ex2) {
                        break;
                    }
                }
            }
            LogHolder.log(6, LogType.NET, "Open socket thread stopped.");
        }
    }
    
    private class DummyMixCascadeContainer extends AbstractMixCascadeContainer
    {
        public MixCascade getNextCascade() {
            return null;
        }
        
        public MixCascade getCurrentCascade() {
            return null;
        }
        
        public void keepCurrentService(final boolean b) {
        }
        
        public boolean isServiceAutoSwitched() {
            return false;
        }
        
        public boolean isReconnectedAutomatically() {
            return false;
        }
    }
    
    private class EncapsulatedMixCascadeContainer extends AbstractMixCascadeContainer
    {
        private AbstractMixCascadeContainer m_mixCascadeContainer;
        
        public EncapsulatedMixCascadeContainer(final AbstractMixCascadeContainer mixCascadeContainer) {
            this.m_mixCascadeContainer = mixCascadeContainer;
        }
        
        public void checkTrust(final MixCascade mixCascade) throws TrustException, SignatureException {
            this.m_mixCascadeContainer.checkTrust(mixCascade);
        }
        
        public MixCascade getNextCascade() {
            return this.m_mixCascadeContainer.getNextCascade();
        }
        
        public MixCascade getCurrentCascade() {
            return this.m_mixCascadeContainer.getCurrentCascade();
        }
        
        public void keepCurrentService(final boolean b) {
            this.m_mixCascadeContainer.keepCurrentService(b);
        }
        
        public boolean isServiceAutoSwitched() {
            return this.m_mixCascadeContainer.isServiceAutoSwitched();
        }
        
        public boolean isReconnectedAutomatically() {
            return !AnonProxy.this.m_forwardedConnection && this.m_mixCascadeContainer.isReconnectedAutomatically();
        }
    }
}
