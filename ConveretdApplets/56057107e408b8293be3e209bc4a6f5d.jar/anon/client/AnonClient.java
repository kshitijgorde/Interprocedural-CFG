// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import anon.pay.PayAccount;
import anon.pay.AIControlChannel;
import anon.client.replay.TimestampUpdater;
import anon.client.replay.ReplayControlChannel;
import java.security.SecureRandom;
import anon.util.XMLParseException;
import java.security.SignatureException;
import anon.terms.TermsAndConditionsReadException;
import anon.transport.connection.ConnectionException;
import HTTPClient.HTTPConnection;
import java.net.Socket;
import anon.transport.connection.SocketConnection;
import HTTPClient.ThreadInterruptedIOException;
import anon.infoservice.HTTPConnectionFactory;
import anon.infoservice.ImmutableProxyInterface;
import java.io.IOException;
import java.util.Observable;
import java.util.Enumeration;
import anon.AnonServiceEventListener;
import java.net.ConnectException;
import anon.NotConnectedToMixException;
import anon.AnonChannel;
import java.io.InterruptedIOException;
import logging.LogHolder;
import logging.LogType;
import anon.infoservice.MixCascade;
import anon.terms.TermsAndConditionConfirmation;
import anon.AnonServerDescription;
import anon.pay.IAIEventListener;
import anon.transport.connection.IStreamConnection;
import java.util.Vector;
import anon.IServiceContainer;
import anon.infoservice.IMutableProxyInterface;
import anon.util.JobQueue;
import java.util.Observer;
import anon.AnonService;

public class AnonClient implements AnonService, Observer, DataChainErrorListener
{
    private static boolean ENABLE_CONTROL_CHANNEL_TEST;
    public static final int DEFAULT_LOGIN_TIMEOUT = 30000;
    private static final int FAST_LOGIN_TIMEOUT = 4000;
    private static final int CONNECT_TIMEOUT = 8000;
    private static int m_loginTimeout;
    private static int m_loginTimeoutFastAvailable;
    private Multiplexer m_multiplexer;
    private JobQueue m_queuePacketCount;
    private IMutableProxyInterface m_proxyInterface;
    private Object m_internalSynchronization;
    private IServiceContainer m_serviceContainer;
    private Thread m_threadInitialise;
    private Object SYNC_SHUTDOWN;
    private Object m_internalSynchronizationForSocket;
    private Object m_internalSynchronizationForDummyTraffic;
    private SocketHandler m_socketHandler;
    private Vector m_eventListeners;
    private PacketCounter m_packetCounter;
    private DummyTrafficControlChannel m_dummyTrafficControlChannel;
    private int m_dummyTrafficInterval;
    private KeyExchangeManager m_keyExchangeManager;
    private IStreamConnection m_streamConnection;
    private boolean m_connected;
    private IAIEventListener m_aiEventListener;
    
    public AnonClient() {
        this.SYNC_SHUTDOWN = new Object();
        this.m_dummyTrafficInterval = 30000;
        this.m_socketHandler = null;
        this.m_multiplexer = null;
        this.m_packetCounter = null;
        this.m_dummyTrafficControlChannel = null;
        this.m_dummyTrafficInterval = -1;
        this.m_keyExchangeManager = null;
        this.m_streamConnection = null;
        this.m_internalSynchronization = new Object();
        this.m_internalSynchronizationForSocket = new Object();
        this.m_internalSynchronizationForDummyTraffic = new Object();
        this.m_eventListeners = new Vector();
        this.m_connected = false;
        this.m_proxyInterface = new IMutableProxyInterface.DummyMutableProxyInterface();
        this.m_queuePacketCount = new JobQueue("AnonClient Packet count updater");
    }
    
    public AnonClient(final IStreamConnection streamConnection) {
        this();
        this.m_streamConnection = streamConnection;
    }
    
    public int initialize(final AnonServerDescription anonServerDescription, final IServiceContainer serviceContainer, final TermsAndConditionConfirmation termsAndConditionConfirmation) {
        if (!(anonServerDescription instanceof MixCascade)) {
            return -5;
        }
        final MixCascade mixCascade = (MixCascade)anonServerDescription;
        this.m_serviceContainer = serviceContainer;
        final StatusThread statusThread = new StatusThread() {
            int status;
            
            public int getStatus() {
                return this.status;
            }
            
            public void run() {
                synchronized (AnonClient.this.m_internalSynchronization) {
                    if (AnonClient.this.isConnected()) {
                        LogHolder.log(3, LogType.NET, "AnonClient was already connected when connecting!");
                        this.status = -4;
                        synchronized (AnonClient.this.m_threadInitialise) {
                            AnonClient.this.m_threadInitialise.notifyAll();
                        }
                        return;
                    }
                    IStreamConnection streamConnection;
                    if (AnonClient.this.m_streamConnection != null) {
                        streamConnection = AnonClient.this.m_streamConnection;
                        AnonClient.this.m_streamConnection = null;
                    }
                    else {
                        try {
                            streamConnection = AnonClient.this.connectMixCascade(mixCascade, AnonClient.this.m_proxyInterface.getProxyInterface(false).getProxyInterface());
                        }
                        catch (InterruptedIOException ex) {
                            this.status = -24;
                            synchronized (AnonClient.this.m_threadInitialise) {
                                AnonClient.this.m_threadInitialise.notifyAll();
                            }
                            return;
                        }
                    }
                    if (streamConnection == null) {
                        this.status = -6;
                        synchronized (AnonClient.this.m_threadInitialise) {
                            AnonClient.this.m_threadInitialise.notifyAll();
                        }
                        return;
                    }
                    this.status = AnonClient.this.initializeProtocol(streamConnection, anonServerDescription, serviceContainer, termsAndConditionConfirmation);
                    synchronized (AnonClient.this.m_threadInitialise) {
                        AnonClient.this.m_threadInitialise.notifyAll();
                    }
                }
            }
        };
        synchronized (this.SYNC_SHUTDOWN) {
            this.m_threadInitialise = new Thread(statusThread);
        }
        this.m_threadInitialise.start();
        try {
            this.m_threadInitialise.join();
        }
        catch (InterruptedException ex) {
            synchronized (this.m_threadInitialise) {
                while (this.m_threadInitialise.isAlive()) {
                    this.m_threadInitialise.interrupt();
                    try {
                        this.m_threadInitialise.wait(500L);
                    }
                    catch (InterruptedException ex2) {}
                }
            }
            return -24;
        }
        return statusThread.getStatus();
    }
    
    public static void setLoginTimeout(final int loginTimeout) {
        if (loginTimeout >= 1000) {
            AnonClient.m_loginTimeout = loginTimeout;
        }
    }
    
    private static void resetInternalLoginTimeout() {
        final int loginTimeoutFastAvailable = 30;
        AnonClient.m_loginTimeoutFastAvailable = Math.max(AnonClient.m_loginTimeout / 1000, AnonClient.m_loginTimeout / 4000);
        if (AnonClient.m_loginTimeoutFastAvailable > loginTimeoutFastAvailable) {
            AnonClient.m_loginTimeoutFastAvailable = loginTimeoutFastAvailable;
        }
    }
    
    private static int getInternalLoginTimeout(final IServiceContainer serviceContainer) {
        if (serviceContainer != null && AnonClient.m_loginTimeoutFastAvailable > 0 && serviceContainer.isReconnectedAutomatically() && serviceContainer.isServiceAutoSwitched()) {
            --AnonClient.m_loginTimeoutFastAvailable;
            return 4000;
        }
        return AnonClient.m_loginTimeout;
    }
    
    public static int getLoginTimeout() {
        return AnonClient.m_loginTimeout;
    }
    
    public int setProxy(final IMutableProxyInterface proxyInterface) {
        synchronized (this.m_internalSynchronization) {
            if (proxyInterface == null) {
                this.m_proxyInterface = new IMutableProxyInterface.DummyMutableProxyInterface();
            }
            else {
                this.m_proxyInterface = proxyInterface;
            }
        }
        return 0;
    }
    
    public void shutdown(final boolean b) {
        synchronized (this.m_internalSynchronizationForSocket) {
            if (this.m_socketHandler != null) {
                this.m_socketHandler.deleteObservers();
            }
        }
        synchronized (this.m_internalSynchronization) {
            if (this.m_multiplexer != null) {
                this.m_multiplexer.close();
            }
        }
        synchronized (this.m_internalSynchronizationForSocket) {
            if (this.m_socketHandler != null) {
                this.m_socketHandler.closeSocket();
                this.m_socketHandler = null;
            }
        }
        synchronized (this.SYNC_SHUTDOWN) {
            if (this.m_threadInitialise != null) {
                synchronized (this.m_threadInitialise) {
                    while (this.m_threadInitialise.isAlive()) {
                        this.m_threadInitialise.interrupt();
                        try {
                            this.m_threadInitialise.wait(100L);
                        }
                        catch (InterruptedException ex) {
                            break;
                        }
                    }
                }
            }
        }
        synchronized (this.m_internalSynchronization) {
            if (this.m_multiplexer != null) {
                this.m_multiplexer.deleteObservers();
            }
            this.m_multiplexer = null;
            this.m_connected = false;
            synchronized (this.m_internalSynchronizationForDummyTraffic) {
                if (this.m_dummyTrafficControlChannel != null) {
                    this.m_dummyTrafficControlChannel.stop();
                    this.m_dummyTrafficControlChannel = null;
                }
            }
            if (this.m_packetCounter != null) {
                this.m_packetCounter.deleteObserver(this);
                if (b) {
                    this.m_packetCounter = null;
                }
            }
            if (this.m_keyExchangeManager != null) {
                this.m_keyExchangeManager.removeCertificateLock();
                this.m_keyExchangeManager = null;
            }
        }
    }
    
    public boolean isConnected() {
        return this.m_connected;
    }
    
    public AnonChannel createChannel(final int n) throws ConnectException {
        Multiplexer multiplexer = null;
        KeyExchangeManager keyExchangeManager = null;
        synchronized (this.m_internalSynchronization) {
            if (this.m_multiplexer == null) {
                throw new NotConnectedToMixException("AnonClient: createChannel(): The AN.ON client is currently not connected to a mixcascade.");
            }
            multiplexer = this.m_multiplexer;
            keyExchangeManager = this.m_keyExchangeManager;
        }
        final FixedRatioChannelsDescription fixedRatioChannelsDescription = keyExchangeManager.getFixedRatioChannelsDescription();
        if (fixedRatioChannelsDescription == null) {
            return new SingleChannelDataChain(multiplexer.getChannelTable(), this, n, keyExchangeManager.isChainProtocolWithFlowControl(), keyExchangeManager.getUpstreamSendMe(), keyExchangeManager.getDownstreamSendMe(), keyExchangeManager.isProtocolWithEnhancedChannelEncryption());
        }
        return new TypeFilterDataChain(new SequentialChannelDataChain(multiplexer.getChannelTable(), this, fixedRatioChannelsDescription.getChainTimeout()), n);
    }
    
    public void addEventListener(final AnonServiceEventListener anonServiceEventListener) {
        synchronized (this.m_eventListeners) {
            this.m_eventListeners.addElement(anonServiceEventListener);
        }
    }
    
    public void removeEventListeners() {
        this.m_eventListeners.removeAllElements();
    }
    
    public void removeEventListener(final AnonServiceEventListener anonServiceEventListener) {
        synchronized (this.m_eventListeners) {
            this.m_eventListeners.removeElement(anonServiceEventListener);
        }
    }
    
    private void reconnect(final Object o) {
        new Thread(new Runnable() {
            public void run() {
                AnonClient.this.shutdown(!AnonClient.this.m_serviceContainer.isReconnectedAutomatically());
                synchronized (AnonClient.this.m_eventListeners) {
                    final Thread thread = new Thread(new Runnable() {
                        private final /* synthetic */ Enumeration val$eventListenersList = AnonClient.this.m_eventListeners.elements();
                        
                        public void run() {
                            if (o != null && o instanceof Exception) {
                                LogHolder.log(6, LogType.NET, (Throwable)o);
                            }
                            while (this.val$eventListenersList.hasMoreElements()) {
                                this.val$eventListenersList.nextElement().connectionError();
                            }
                        }
                    }, "ConnectionError notification");
                    thread.setDaemon(true);
                    thread.start();
                }
            }
        }).start();
    }
    
    public void update(final Observable observable, final Object o) {
        if (observable == this.m_socketHandler && o instanceof IOException) {
            this.reconnect(o);
        }
        else if (observable == this.m_packetCounter) {
            this.m_queuePacketCount.addJob(new JobQueue.Job(true) {
                public void runJob() {
                    final Vector access$700 = AnonClient.this.m_eventListeners;
                    final PacketCounter access$701 = AnonClient.this.m_packetCounter;
                    if (access$700 != null && access$701 != null) {
                        synchronized (access$700) {
                            final Enumeration<AnonServiceEventListener> elements = access$700.elements();
                            while (elements.hasMoreElements()) {
                                elements.nextElement().packetMixed(access$701.getProcessedPackets() * MixPacket.getPacketSize());
                            }
                        }
                    }
                }
            });
        }
    }
    
    public void dataChainErrorSignaled() {
        synchronized (this.m_eventListeners) {
            final Thread thread = new Thread(new Runnable() {
                private final /* synthetic */ Enumeration val$eventListenersList = AnonClient.this.m_eventListeners.elements();
                
                public void run() {
                    while (this.val$eventListenersList.hasMoreElements()) {
                        this.val$eventListenersList.nextElement().dataChainErrorSignaled();
                    }
                }
            }, "AnonClient: DataChainErrorSignaled notification");
            thread.setDaemon(true);
            thread.start();
        }
    }
    
    public void setDummyTraffic(final int n) {
        synchronized (this.m_internalSynchronizationForDummyTraffic) {
            this.m_dummyTrafficInterval = n;
            if (this.m_dummyTrafficControlChannel != null) {
                this.m_dummyTrafficControlChannel.setDummyTrafficInterval(n);
            }
        }
    }
    
    private IStreamConnection connectMixCascade(final MixCascade mixCascade, final ImmutableProxyInterface immutableProxyInterface) throws InterruptedIOException {
        LogHolder.log(7, LogType.NET, "Trying to connect to MixCascade '" + mixCascade.toString() + "'...");
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                synchronized (AnonClient.this.m_eventListeners) {
                    final Enumeration<AnonServiceEventListener> elements = AnonClient.this.m_eventListeners.elements();
                    while (elements.hasMoreElements()) {
                        elements.nextElement().connecting(mixCascade);
                    }
                }
            }
        }, "AnonClient: Connecting notification");
        thread.setDaemon(true);
        thread.start();
        Socket connect = null;
        for (int n = 0; n < mixCascade.getNumberOfListenerInterfaces() && connect == null && !Thread.currentThread().isInterrupted(); ++n) {
            try {
                final HTTPConnection httpConnection = HTTPConnectionFactory.getInstance().createHTTPConnection(mixCascade.getListenerInterface(n), immutableProxyInterface);
                httpConnection.setTimeout(8000);
                connect = httpConnection.Connect();
            }
            catch (InterruptedIOException ex) {
                if (ex instanceof ThreadInterruptedIOException) {
                    LogHolder.log(5, LogType.NET, "Interrupted while connecting to MixCascade '" + mixCascade.toString() + "'.");
                    throw ex;
                }
                LogHolder.log(3, LogType.NET, "Timeout while connecting to MixCascade " + mixCascade.toString() + " via " + mixCascade.getListenerInterface(n).toString() + "!", ex);
            }
            catch (Exception ex2) {
                LogHolder.log(3, LogType.NET, "Could not connect to MixCascade " + mixCascade.toString() + " via " + mixCascade.getListenerInterface(n).toString() + "!", ex2);
            }
        }
        if (connect != null) {
            LogHolder.log(7, LogType.NET, "Connection to MixCascade '" + mixCascade.toString() + "' successfully established - starting key-exchange...");
            return new SocketConnection(connect);
        }
        LogHolder.log(3, LogType.NET, "Failed to connect to MixCascade '" + mixCascade.toString() + "'.");
        return null;
    }
    
    private int initializeProtocol(final IStreamConnection streamConnection, final AnonServerDescription anonServerDescription, final IServiceContainer serviceContainer, final TermsAndConditionConfirmation termsAndConditionConfirmation) {
        synchronized (this.m_internalSynchronization) {
            try {
                try {
                    streamConnection.setTimeout(getInternalLoginTimeout(serviceContainer));
                }
                catch (ConnectionException ex8) {}
                synchronized (this.m_internalSynchronizationForSocket) {
                    if (this.m_socketHandler != null) {
                        this.m_socketHandler.deleteObservers();
                    }
                    this.m_socketHandler = new SocketHandler(streamConnection);
                }
                final Vector vector = new Vector();
                final Thread thread = new Thread(new Runnable() {
                    public void run() {
                        boolean b = true;
                        int n = 0;
                        try {
                            while (b) {
                                try {
                                    AnonClient.this.m_keyExchangeManager = new KeyExchangeManager(AnonClient.this.m_socketHandler.getInputStream(), AnonClient.this.m_socketHandler.getOutputStream(), (MixCascade)anonServerDescription, serviceContainer);
                                    b = false;
                                }
                                catch (TermsAndConditionsReadException ex) {
                                    if (!termsAndConditionConfirmation.confirmTermsAndConditions(ex.getOperators(), ex.getTermsTermsAndConditonsToRead())) {
                                        serviceContainer.keepCurrentService(false);
                                        throw new InterruptedException("Client rejected T&C after reading.");
                                    }
                                    if (++n > 1) {
                                        LogHolder.log(3, LogType.NET, "Requesting t&cs after the first try is not allowed!");
                                        throw new InterruptedException("A second tc request must never be sent.");
                                    }
                                    AnonClient.this.m_socketHandler = new SocketHandler(AnonClient.this.connectMixCascade((MixCascade)anonServerDescription, AnonClient.this.m_proxyInterface.getProxyInterface(false).getProxyInterface()));
                                }
                            }
                        }
                        catch (Exception ex2) {
                            vector.addElement(ex2);
                        }
                    }
                }, "Login Thread");
                thread.start();
                try {
                    thread.join();
                }
                catch (InterruptedException ex) {
                    throw ex;
                }
                if (vector.size() > 0) {
                    throw (Exception)vector.firstElement();
                }
            }
            catch (UnknownProtocolVersionException ex2) {
                LogHolder.log(3, LogType.NET, ex2);
                this.closeSocketHandler();
                return -10;
            }
            catch (SignatureException ex3) {
                LogHolder.log(2, LogType.CRYPTO, ex3);
                this.closeSocketHandler();
                return -23;
            }
            catch (InterruptedException ex4) {
                LogHolder.log(6, LogType.NET, ex4);
                this.closeSocketHandler();
                return -24;
            }
            catch (TrustException ex5) {
                LogHolder.log(6, LogType.NET, ex5);
                this.closeSocketHandler();
                return -26;
            }
            catch (XMLParseException ex6) {
                LogHolder.log(3, LogType.NET, ex6);
                this.closeSocketHandler();
                return -27;
            }
            catch (Exception ex7) {
                LogHolder.log(3, LogType.NET, ex7);
                this.closeSocketHandler();
                return -1;
            }
            try {
                streamConnection.setTimeout(0);
            }
            catch (ConnectionException ex9) {}
            this.m_multiplexer = new Multiplexer(this.m_socketHandler.getInputStream(), this.m_socketHandler.getOutputStream(), this.m_keyExchangeManager, new SecureRandom());
            this.m_socketHandler.addObserver(this);
            if (this.m_packetCounter != null) {
                this.m_packetCounter = new PacketCounter(this.m_packetCounter.getProcessedPackets());
            }
            else {
                this.m_packetCounter = new PacketCounter();
            }
            this.m_multiplexer.addObserver(this.m_packetCounter);
            this.m_packetCounter.addObserver(this);
            synchronized (this.m_internalSynchronizationForDummyTraffic) {
                (this.m_dummyTrafficControlChannel = new DummyTrafficControlChannel(this.m_multiplexer, serviceContainer)).setDummyTrafficInterval(this.m_dummyTrafficInterval);
            }
            if (AnonClient.ENABLE_CONTROL_CHANNEL_TEST) {
                new TestControlChannel(this.m_multiplexer, serviceContainer).setMessageInterval(30000);
            }
            final int finishInitialization = this.finishInitialization(this.m_multiplexer, this.m_keyExchangeManager, this.m_packetCounter, streamConnection, serviceContainer, this.m_keyExchangeManager.getConnectedCascade());
            if (finishInitialization != 0) {
                this.shutdown(!serviceContainer.isReconnectedAutomatically());
                return finishInitialization;
            }
            this.connectionEstablished(anonServerDescription);
            return 0;
        }
    }
    
    public void connectionEstablished(final AnonServerDescription anonServerDescription) {
        resetInternalLoginTimeout();
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                synchronized (AnonClient.this.m_eventListeners) {
                    final Enumeration<AnonServiceEventListener> elements = AnonClient.this.m_eventListeners.elements();
                    while (elements.hasMoreElements()) {
                        elements.nextElement().connectionEstablished(anonServerDescription);
                    }
                }
            }
        }, "AnonClient: ConnectionEstablished notification");
        thread.setDaemon(true);
        thread.start();
        LogHolder.log(6, LogType.NET, "Connected to MixCascade '" + anonServerDescription.toString() + "'!");
        this.m_connected = true;
    }
    
    private int finishInitialization(final Multiplexer multiplexer, final KeyExchangeManager keyExchangeManager, final PacketCounter packetCounter, final IStreamConnection streamConnection, final IServiceContainer serviceContainer, final MixCascade mixCascade) {
        if (keyExchangeManager.isProtocolWithTimestamp()) {
            MixParameters[] mixParameters = keyExchangeManager.getMixParameters();
            if (keyExchangeManager.getFirstMixSymmetricCipher() != null) {
                mixParameters = new MixParameters[keyExchangeManager.getMixParameters().length - 1];
                for (int i = 0; i < keyExchangeManager.getMixParameters().length - 1; ++i) {
                    mixParameters[i] = keyExchangeManager.getMixParameters()[i + 1];
                }
            }
            try {
                final TimestampUpdater timestampUpdater = new TimestampUpdater(mixParameters, new ReplayControlChannel(multiplexer, serviceContainer));
            }
            catch (Exception ex) {
                LogHolder.log(3, LogType.NET, "Fetching of timestamps failed - closing connection.", ex);
                return -1;
            }
        }
        final AIControlChannel aiControlChannel = new AIControlChannel(multiplexer, packetCounter, serviceContainer, mixCascade);
        if (keyExchangeManager.isPaymentRequired()) {
            aiControlChannel.addAIListener(new IAIEventListener() {
                public void accountEmpty(final PayAccount payAccount, final MixCascade mixCascade) {
                    AnonClient.this.reconnect(null);
                }
            });
            aiControlChannel.setAILoginTimeout(AnonClient.m_loginTimeout);
            return aiControlChannel.sendAccountCert();
        }
        return 0;
    }
    
    private void closeSocketHandler() {
        synchronized (this.m_internalSynchronizationForSocket) {
            if (this.m_socketHandler != null) {
                this.m_socketHandler.closeSocket();
                this.m_socketHandler = null;
            }
        }
    }
    
    static {
        AnonClient.ENABLE_CONTROL_CHANNEL_TEST = false;
        AnonClient.m_loginTimeout = 30000;
        resetInternalLoginTimeout();
    }
    
    private interface StatusThread extends Runnable
    {
        int getStatus();
    }
}
