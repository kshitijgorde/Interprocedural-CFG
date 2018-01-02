// 
// Decompiled by Procyon v0.5.30
// 

package jap.forward;

import anon.util.IPasswordReader;
import anon.infoservice.HTTPConnectionFactory;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.util.Enumeration;
import forward.server.ServerSocketPropagandist;
import anon.infoservice.InfoServiceDBEntry;
import forward.server.ForwardSchedulerStatistics;
import anon.infoservice.MixCascade;
import forward.client.ClientForwardException;
import forward.client.ForwardConnectionDescriptor;
import anon.terms.TermsAndConditionConfirmation;
import anon.proxy.AnonProxy;
import java.net.ServerSocket;
import anon.transport.connection.ConnectionException;
import anon.transport.address.SkypeAddress;
import anon.transport.address.AddressMappingException;
import anon.transport.address.MalformedURNException;
import anon.transport.address.Endpoint;
import anon.transport.address.TcpIpAddress;
import anon.infoservice.ImmutableProxyInterface;
import anon.infoservice.ProxyInterface;
import forward.ForwardUtils;
import logging.LogHolder;
import logging.LogType;
import forward.server.IServerManager;
import forward.server.SkypeServerManager;
import java.io.IOException;
import forward.LocalForwarder;
import jap.JAPModel;
import jap.JAPController;
import forward.server.ForwardServerManager;
import java.util.Observer;
import anon.transport.address.IAddress;
import java.util.Vector;
import forward.client.DefaultClientProtocolHandler;
import anon.transport.connection.IStreamConnection;
import anon.util.IXMLEncodable;
import java.util.Observable;

public final class JAPRoutingSettings extends Observable implements IXMLEncodable
{
    public static final int ROUTING_MODE_DISABLED = 0;
    public static final int ROUTING_MODE_CLIENT = 1;
    public static final int ROUTING_MODE_SERVER = 2;
    public static final int REGISTRATION_NO_INFOSERVICES = 1;
    public static final int REGISTRATION_UNKNOWN_ERRORS = 2;
    public static final int REGISTRATION_INFOSERVICE_ERRORS = 3;
    public static final int REGISTRATION_VERIFY_ERRORS = 4;
    public static final int REGISTRATION_INTERRUPTED = 5;
    public static final int REGISTRATION_SUCCESS = 0;
    public static final String DEFAULT_APP_NAME = "jap";
    private int m_routingMode;
    private int m_serverPort;
    private Object m_currentServerManagerId;
    private int m_bandwidth;
    private int m_connections;
    private IStreamConnection m_forwardedConnection;
    private boolean m_forwardInfoService;
    private boolean m_connectViaForwarder;
    private boolean m_waitForShutdownCall;
    private DefaultClientProtocolHandler m_protocolHandler;
    private int m_maxDummyTrafficInterval;
    private Vector m_runningPropagandists;
    private Thread m_startPropagandaThread;
    private JAPRoutingConnectionClassSelector m_connectionClassSelector;
    private JAPRoutingRegistrationInfoServices m_registrationInfoServicesStore;
    private boolean m_propagandaStarted;
    private JAPRoutingUseableMixCascades m_useableMixCascadesStore;
    private JAPRoutingServerStatisticsListener m_serverStatisticsListener;
    private JAPRoutingRegistrationStatusObserver m_registrationStatusObserver;
    private IAddress m_forwadingAddress;
    private String m_appName;
    private TransportMode m_transportMode;
    private IAddress m_userForwarder;
    private static final String DEFAULT_SKYPE_ADDRESS_URN = "urn:endpoint:skype:user(japforwarder):application(jap)";
    
    public JAPRoutingSettings() {
        this.m_routingMode = 0;
        this.m_serverPort = (int)Math.round(Math.abs(Math.random() * 64511.0)) + 1025;
        this.m_appName = "jap";
        this.m_connectionClassSelector = new JAPRoutingConnectionClassSelector();
        this.m_bandwidth = this.m_connectionClassSelector.getCurrentConnectionClass().getCurrentBandwidth();
        this.m_connections = this.m_bandwidth / 4000;
        this.m_forwardedConnection = null;
        this.m_forwardInfoService = false;
        this.m_connectViaForwarder = false;
        this.m_forwadingAddress = null;
        this.m_waitForShutdownCall = false;
        this.m_protocolHandler = null;
        this.m_maxDummyTrafficInterval = -1;
        this.m_runningPropagandists = new Vector();
        this.m_startPropagandaThread = null;
        this.m_propagandaStarted = false;
        this.m_currentServerManagerId = null;
        this.m_transportMode = TransportMode.TCPIP;
        this.addObserver(this.m_registrationInfoServicesStore = new JAPRoutingRegistrationInfoServices());
        this.addObserver(this.m_useableMixCascadesStore = new JAPRoutingUseableMixCascades());
        this.addObserver(this.m_serverStatisticsListener = new JAPRoutingServerStatisticsListener());
        this.addObserver(this.m_registrationStatusObserver = new JAPRoutingRegistrationStatusObserver());
    }
    
    public int getRoutingMode() {
        return this.m_routingMode;
    }
    
    public boolean setRoutingMode(final int routingMode) {
        boolean b = false;
        int n = 0;
        synchronized (this) {
            if (routingMode != this.m_routingMode) {
                if (this.m_routingMode == 2) {
                    ForwardServerManager.getInstance().shutdownForwarding();
                    this.stopPropaganda();
                    this.m_currentServerManagerId = null;
                }
                if (this.m_routingMode == 1) {
                    if (this.getForwardInfoService()) {
                        JAPController.getInstance().applyProxySettingsToInfoService(JAPModel.getInstance().isProxyAuthenticationUsed());
                    }
                    JAPController.getInstance().setAnonMode(false);
                    if (this.m_forwadingAddress.getTransportIdentifier().equals("local")) {
                        LocalForwarder.unregisterLocalForwarder();
                    }
                    try {
                        this.m_forwardedConnection.close();
                    }
                    catch (IOException ex) {}
                    this.m_forwardedConnection = null;
                    this.m_protocolHandler = null;
                }
                if ((this.m_routingMode = routingMode) == 2) {
                    ForwardServerManager.getInstance().startForwarding();
                    ForwardServerManager.getInstance().setNetBandwidth(this.getBandwidth());
                    ForwardServerManager.getInstance().setMaximumNumberOfConnections(this.getAllowedConnections());
                    if (this.m_transportMode != null) {
                        if (this.m_transportMode.equals(TransportMode.TCPIP)) {
                            this.m_currentServerManagerId = ForwardServerManager.getInstance().addListenSocket(this.getServerPort());
                        }
                        else if (this.m_transportMode.equals(TransportMode.SKYPE)) {
                            this.m_currentServerManagerId = ForwardServerManager.getInstance().addServerManager(new SkypeServerManager(this.getApplicationName()));
                        }
                    }
                    if (this.m_currentServerManagerId == null) {
                        ForwardServerManager.getInstance().shutdownForwarding();
                        this.m_routingMode = 0;
                    }
                    else {
                        b = true;
                    }
                }
                if (routingMode == 1) {
                    LogHolder.log(7, LogType.NET, "JAPRountingSettings:setRoutingMode() start the client");
                    if (JAPController.getInstance().getAnonMode()) {
                        this.m_waitForShutdownCall = true;
                        JAPController.getInstance().setAnonMode(false);
                        try {
                            this.wait();
                        }
                        catch (Exception ex2) {}
                        this.m_waitForShutdownCall = false;
                    }
                    if (this.m_forwadingAddress.getTransportIdentifier().equals("local")) {
                        LocalForwarder.registerLocalForwarder(this.getBandwidth());
                    }
                    LogHolder.log(7, LogType.NET, "JAPRountingSettings:setRoutingMode() tryto connect to forwarder");
                    this.m_forwardedConnection = ForwardUtils.getInstance().createForwardingConnection(this.m_forwadingAddress);
                    if (this.m_forwardedConnection != null) {
                        this.updateInfoServiceProxySettings();
                        this.m_protocolHandler = new DefaultClientProtocolHandler(this.m_forwardedConnection);
                        b = true;
                    }
                    else {
                        this.m_routingMode = 0;
                    }
                }
                if (routingMode == 0) {
                    b = true;
                }
                n = 1;
            }
            else {
                b = true;
            }
            if (n == 1) {
                this.setChanged();
                this.notifyObservers(new JAPRoutingMessage(1));
            }
        }
        return b;
    }
    
    public int getServerPort() {
        return this.m_serverPort;
    }
    
    public String getApplicationName() {
        return this.m_appName;
    }
    
    public boolean setApplicationName(final String s) {
        synchronized (this) {
            if (this.m_appName.equals(s)) {
                return true;
            }
            if (this.m_routingMode != 2 || this.m_transportMode != TransportMode.SKYPE) {
                this.m_appName = s;
                return true;
            }
            final Object addServerManager = ForwardServerManager.getInstance().addServerManager(new SkypeServerManager(s));
            if (addServerManager == null) {
                return false;
            }
            ForwardServerManager.getInstance().removeServerManager(this.m_currentServerManagerId);
            this.m_currentServerManagerId = addServerManager;
            this.m_appName = s;
            return true;
        }
    }
    
    public boolean setServerPort(final int n) {
        boolean b = false;
        if (n >= 1 && n <= 65535) {
            synchronized (this) {
                if (this.m_serverPort != n) {
                    if (this.m_routingMode != 2) {
                        this.m_serverPort = n;
                        b = true;
                    }
                    else {
                        final Object addListenSocket = ForwardServerManager.getInstance().addListenSocket(n);
                        if (addListenSocket != null) {
                            ForwardServerManager.getInstance().removeServerManager(this.m_currentServerManagerId);
                            this.m_serverPort = n;
                            this.m_currentServerManagerId = addListenSocket;
                            b = true;
                            if (this.m_propagandaStarted) {
                                this.startPropaganda(false);
                            }
                        }
                    }
                    if (b) {
                        this.setChanged();
                        this.notifyObservers(new JAPRoutingMessage(15));
                    }
                }
                else {
                    b = true;
                }
            }
        }
        return b;
    }
    
    public int getBandwidth() {
        return this.m_bandwidth;
    }
    
    public void setBandwidth(final int n) {
        synchronized (this) {
            this.m_bandwidth = n;
            ForwardServerManager.getInstance().setNetBandwidth(n);
            this.setAllowedConnections(this.getAllowedConnections());
        }
    }
    
    public int getBandwidthMaxConnections() {
        return this.getBandwidth() / 4000;
    }
    
    public int getAllowedConnections() {
        return this.m_connections;
    }
    
    public void setAllowedConnections(int bandwidthMaxConnections) {
        synchronized (this) {
            if (bandwidthMaxConnections > this.getBandwidthMaxConnections()) {
                bandwidthMaxConnections = this.getBandwidthMaxConnections();
            }
            this.m_connections = bandwidthMaxConnections;
            ForwardServerManager.getInstance().setMaximumNumberOfConnections(bandwidthMaxConnections);
        }
    }
    
    public void setNewProxySettings(final ProxyInterface proxySettings) {
        ForwardUtils.getInstance().setProxySettings(proxySettings);
    }
    
    public void setForwarder(final String s, final int n) {
        this.setForwarderAddress(new TcpIpAddress(s, n));
    }
    
    public void setForwarderAddress(final IAddress forwadingAddress) {
        synchronized (this) {
            if (this.m_routingMode != 1) {
                this.m_forwadingAddress = forwadingAddress;
            }
        }
    }
    
    public IAddress getForwarderAddress() {
        synchronized (this) {
            try {
                return this.m_forwadingAddress;
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    public IAddress getUserProvidetForwarder() {
        return this.m_userForwarder;
    }
    
    public void setUserProvidetForwarder(final String s) throws ConnectionException {
        if (s == null) {
            this.m_userForwarder = null;
            return;
        }
        final Endpoint endpoint = new Endpoint(s);
        if (endpoint == null) {
            throw new MalformedURNException("Unable to parse URN");
        }
        IAddress userForwarder = null;
        final TransportMode byIdentifier = TransportMode.getByIdentifier(endpoint.getTransportIdentifier());
        if (byIdentifier == TransportMode.UNKNOWN) {
            throw new AddressMappingException("Transportmode is not Supported");
        }
        if (byIdentifier == TransportMode.SKYPE) {
            userForwarder = new SkypeAddress(endpoint);
        }
        else if (byIdentifier == TransportMode.TCPIP) {
            userForwarder = new TcpIpAddress(endpoint);
        }
        this.m_userForwarder = userForwarder;
    }
    
    public TransportMode getTransportMode() {
        return this.m_transportMode;
    }
    
    public boolean setTransportMode(final TransportMode transportMode) {
        synchronized (this) {
            if (transportMode == TransportMode.UNKNOWN) {
                return false;
            }
            if (transportMode == this.m_transportMode) {
                return true;
            }
            if (this.m_routingMode == 0 || this.m_routingMode == 1) {
                this.m_transportMode = transportMode;
                if (this.m_transportMode.equals(TransportMode.SKYPE)) {
                    try {
                        this.setUserProvidetForwarder("urn:endpoint:skype:user(japforwarder):application(jap)");
                    }
                    catch (ConnectionException ex) {}
                }
                return true;
            }
            if (this.m_routingMode != 2) {
                return false;
            }
            Object currentServerManagerId = null;
            if (transportMode == TransportMode.TCPIP) {
                currentServerManagerId = ForwardServerManager.getInstance().addListenSocket(this.m_serverPort);
            }
            else if (transportMode == TransportMode.SKYPE) {
                currentServerManagerId = ForwardServerManager.getInstance().addServerManager(new SkypeServerManager(this.m_appName));
            }
            if (currentServerManagerId == null) {
                return false;
            }
            ForwardServerManager.getInstance().removeServerManager(this.m_currentServerManagerId);
            this.m_currentServerManagerId = currentServerManagerId;
            this.m_transportMode = transportMode;
            return true;
        }
    }
    
    public void setForwardInfoService(final boolean forwardInfoService) {
        synchronized (this) {
            if (this.m_forwardInfoService != forwardInfoService) {
                this.m_forwardInfoService = forwardInfoService;
                if (forwardInfoService && this.getRoutingMode() == 1) {
                    this.updateInfoServiceProxySettings();
                }
                if (!forwardInfoService && this.getRoutingMode() == 1) {
                    JAPController.getInstance().applyProxySettingsToInfoService(JAPModel.getInstance().isProxyAuthenticationUsed());
                }
                this.setChanged();
                this.notifyObservers(new JAPRoutingMessage(16));
            }
        }
    }
    
    public boolean getForwardInfoService() {
        return false;
    }
    
    public void setConnectViaForwarder(final boolean connectViaForwarder) {
        synchronized (this) {
            if (this.m_connectViaForwarder != connectViaForwarder) {
                this.m_connectViaForwarder = connectViaForwarder;
                this.setChanged();
                this.notifyObservers(new JAPRoutingMessage(16));
            }
        }
    }
    
    public boolean isConnectViaForwarder() {
        return this.m_connectViaForwarder;
    }
    
    public void httpListenerPortChanged() {
        synchronized (this) {
            if (this.getForwardInfoService() && this.getRoutingMode() == 1) {
                this.updateInfoServiceProxySettings();
            }
        }
    }
    
    public void anonConnectionClosed() {
        synchronized (this) {
            if (this.getRoutingMode() == 1) {
                if (this.m_waitForShutdownCall) {
                    this.notify();
                }
                else {
                    this.setRoutingMode(0);
                }
            }
        }
    }
    
    public AnonProxy getAnonProxyInstance(final ServerSocket serverSocket) {
        AnonProxy anonProxy = null;
        synchronized (this) {
            if (this.getRoutingMode() == 1) {
                anonProxy = new AnonProxy(serverSocket, this.m_forwardedConnection, this.m_maxDummyTrafficInterval, new TermsAndConditionConfirmation.AlwaysAccept());
            }
        }
        return anonProxy;
    }
    
    public ForwardConnectionDescriptor getConnectionDescriptor() throws ClientForwardException {
        DefaultClientProtocolHandler protocolHandler = null;
        synchronized (this) {
            if (this.getRoutingMode() == 1) {
                protocolHandler = this.m_protocolHandler;
            }
        }
        if (protocolHandler != null) {
            ForwardConnectionDescriptor connectionDescriptor = null;
            Label_0069: {
                try {
                    connectionDescriptor = protocolHandler.getConnectionDescriptor();
                    break Label_0069;
                }
                catch (ClientForwardException ex) {
                    this.setRoutingMode(0);
                    throw ex;
                }
                throw new ClientForwardException(255, "JAPRoutingSettings: getConnectionDescriptor: Not in client routing mode.");
            }
            synchronized (this) {
                this.m_maxDummyTrafficInterval = connectionDescriptor.getMinDummyTrafficInterval();
            }
            return connectionDescriptor;
        }
        throw new ClientForwardException(255, "JAPRoutingSettings: getConnectionDescriptor: Not in client routing mode.");
    }
    
    public void selectMixCascade(final MixCascade mixCascade) throws ClientForwardException {
        DefaultClientProtocolHandler protocolHandler = null;
        synchronized (this) {
            if (this.getRoutingMode() == 1) {
                protocolHandler = this.m_protocolHandler;
            }
        }
        if (protocolHandler != null) {
            try {
                protocolHandler.selectMixCascade(mixCascade);
                return;
            }
            catch (ClientForwardException ex) {
                this.setRoutingMode(0);
                throw ex;
            }
            throw new ClientForwardException(255, "JAPRoutingSettings: selectMixCascade: Not in client routing mode.");
        }
        throw new ClientForwardException(255, "JAPRoutingSettings: selectMixCascade: Not in client routing mode.");
    }
    
    public ForwardSchedulerStatistics getSchedulerStatistics() {
        return ForwardServerManager.getInstance().getSchedulerStatistics();
    }
    
    public int getCurrentlyForwardedConnections() {
        return ForwardServerManager.getInstance().getCurrentlyForwardedConnections();
    }
    
    public int startPropaganda(final boolean b) {
        final JAPRoutingSettingsPropagandaThreadLock japRoutingSettingsPropagandaThreadLock = new JAPRoutingSettingsPropagandaThreadLock();
        synchronized (this) {
            if (this.m_routingMode == 2) {
                this.stopPropaganda();
                final Vector registrationInfoServicesForStartup = this.getRegistrationInfoServicesStore().getRegistrationInfoServicesForStartup();
                final Vector runningPropagandists = new Vector();
                this.m_runningPropagandists = runningPropagandists;
                (this.m_startPropagandaThread = new Thread(new Runnable() {
                    public void run() {
                        JAPRoutingSettings.this.setChanged();
                        JAPRoutingSettings.this.notifyObservers(new JAPRoutingMessage(3, null));
                        final Enumeration elements = registrationInfoServicesForStartup.elements();
                        boolean interrupted = false;
                        while (elements.hasMoreElements() && !interrupted) {
                            final ServerSocketPropagandist serverSocketPropagandist = new ServerSocketPropagandist(JAPRoutingSettings.this.m_serverPort, elements.nextElement());
                            synchronized (JAPModel.getInstance().getRoutingSettings()) {
                                interrupted = Thread.interrupted();
                                if (interrupted) {
                                    serverSocketPropagandist.stopPropaganda();
                                    japRoutingSettingsPropagandaThreadLock.registrationWasInterrupted();
                                }
                                else {
                                    runningPropagandists.addElement(serverSocketPropagandist);
                                    japRoutingSettingsPropagandaThreadLock.updateRegistrationStatus(serverSocketPropagandist);
                                    JAPRoutingSettings.this.setChanged();
                                    JAPRoutingSettings.this.notifyObservers(new JAPRoutingMessage(2, runningPropagandists.clone()));
                                }
                            }
                        }
                        synchronized (JAPModel.getInstance().getRoutingSettings()) {
                            JAPRoutingSettings.this.m_startPropagandaThread = null;
                            if (!Thread.interrupted() && !interrupted) {
                                JAPRoutingSettings.this.setChanged();
                                JAPRoutingSettings.this.notifyObservers(new JAPRoutingMessage(4, runningPropagandists.clone()));
                            }
                        }
                        synchronized (japRoutingSettingsPropagandaThreadLock) {
                            japRoutingSettingsPropagandaThreadLock.propagandaThreadIsReady();
                            japRoutingSettingsPropagandaThreadLock.notify();
                        }
                    }
                })).setDaemon(true);
                this.m_propagandaStarted = true;
                this.m_startPropagandaThread.start();
            }
            else {
                japRoutingSettingsPropagandaThreadLock.propagandaThreadIsReady();
            }
        }
        int registrationStatus = 0;
        synchronized (japRoutingSettingsPropagandaThreadLock) {
            if (b && !japRoutingSettingsPropagandaThreadLock.isPropagandaThreadReady()) {
                try {
                    japRoutingSettingsPropagandaThreadLock.wait();
                    registrationStatus = japRoutingSettingsPropagandaThreadLock.getRegistrationStatus();
                }
                catch (InterruptedException ex) {}
            }
        }
        return registrationStatus;
    }
    
    public void stopPropaganda() {
        synchronized (this) {
            if (this.m_startPropagandaThread != null) {
                try {
                    this.m_startPropagandaThread.interrupt();
                }
                catch (Exception ex) {}
            }
            while (this.m_runningPropagandists.size() > 0) {
                this.m_runningPropagandists.firstElement().stopPropaganda();
                this.m_runningPropagandists.removeElementAt(0);
            }
            this.m_propagandaStarted = false;
            this.setChanged();
            this.notifyObservers(new JAPRoutingMessage(5));
        }
    }
    
    public void addPropagandaInstance(final InfoServiceDBEntry infoServiceDBEntry) {
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                int access$500 = 0;
                int access$501 = -1;
                synchronized (JAPModel.getInstance().getRoutingSettings()) {
                    access$500 = (JAPRoutingSettings.this.m_propagandaStarted ? 1 : 0);
                    access$501 = JAPRoutingSettings.this.m_serverPort;
                }
                if (access$500 == 1) {
                    final ServerSocketPropagandist serverSocketPropagandist = new ServerSocketPropagandist(access$501, infoServiceDBEntry);
                    synchronized (JAPModel.getInstance().getRoutingSettings()) {
                        if (JAPRoutingSettings.this.m_serverPort == access$501 && JAPRoutingSettings.this.m_propagandaStarted) {
                            JAPRoutingSettings.this.m_runningPropagandists.addElement(serverSocketPropagandist);
                            JAPRoutingSettings.this.setChanged();
                            JAPRoutingSettings.this.notifyObservers(new JAPRoutingMessage(2, JAPRoutingSettings.this.m_runningPropagandists.clone()));
                        }
                        else {
                            serverSocketPropagandist.stopPropaganda();
                        }
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    
    public Vector getRunningPropagandaInstances() {
        Vector vector = null;
        synchronized (this) {
            vector = (Vector)this.m_runningPropagandists.clone();
        }
        return vector;
    }
    
    public JAPRoutingConnectionClassSelector getConnectionClassSelector() {
        return this.m_connectionClassSelector;
    }
    
    public JAPRoutingRegistrationInfoServices getRegistrationInfoServicesStore() {
        return this.m_registrationInfoServicesStore;
    }
    
    public JAPRoutingUseableMixCascades getUseableMixCascadesStore() {
        return this.m_useableMixCascadesStore;
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("JapForwardingSettings");
        final Element element2 = document.createElement("ForwardingServer");
        final Element element3 = document.createElement("ServerPort");
        final Element element4 = document.createElement("ServerRunning");
        synchronized (this) {
            XMLUtil.setValue(element3, this.getServerPort());
            if (this.getRoutingMode() == 2) {
                XMLUtil.setValue(element4, true);
            }
            else {
                XMLUtil.setValue(element4, false);
            }
        }
        element2.appendChild(element3);
        element2.appendChild(element4);
        element2.appendChild(this.getConnectionClassSelector().getSettingsAsXml(document));
        element2.appendChild(this.getRegistrationInfoServicesStore().getSettingsAsXml(document));
        element2.appendChild(this.getUseableMixCascadesStore().getSettingsAsXml(document));
        element.appendChild(element2);
        final Element element5 = document.createElement("ForwardingClient");
        XMLUtil.setAttribute(element5, "type", this.getTransportMode().getIdentifier());
        final Element element6 = document.createElement("ConnectViaForwarder");
        final Element element7 = document.createElement("ForwardInfoService");
        XMLUtil.setValue(element6, this.isConnectViaForwarder());
        XMLUtil.setValue(element7, this.getForwardInfoService());
        element5.appendChild(element6);
        element5.appendChild(element7);
        element.appendChild(element5);
        return element;
    }
    
    public int loadSettingsFromXml(final Element element) {
        int n = 1;
        final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "ForwardingServer");
        if (element2 == null) {
            LogHolder.log(3, LogType.MISC, "JAPRoutingSettings: loadSettingsFromXml: Error in XML structure (ForwardingServer node): Using default forwarding server settings.");
        }
        else {
            final Element element3 = (Element)XMLUtil.getFirstChildByName(element2, "ServerPort");
            if (element3 == null) {
                LogHolder.log(3, LogType.MISC, "JAPRoutingSettings: loadSettingsFromXml: Error in XML structure (ServerPort node): Using default server port.");
                n = 0;
            }
            else {
                final int value = XMLUtil.parseValue(element3, -1);
                if (value == -1) {
                    LogHolder.log(3, LogType.MISC, "JAPRoutingSettings: loadSettingsFromXml: Invalid server port in XML structure: Using default server port.");
                    n = 0;
                }
                else if (!this.setServerPort(value)) {
                    LogHolder.log(3, LogType.MISC, "JAPRoutingSettings: loadSettingsFromXml: Error while setting the server port: Using default server port.");
                    n = 0;
                }
            }
            final Element element4 = (Element)XMLUtil.getFirstChildByName(element2, "ConnectionClassSettings");
            if (element4 == null) {
                LogHolder.log(3, LogType.MISC, "JAPRoutingSettings: loadSettingsFromXml: Error in XML structure (ConnectionClassSettings node): Using default connection class settings.");
                n = 0;
            }
            else if (!this.getConnectionClassSelector().loadSettingsFromXml(element4)) {
                n = 0;
            }
            final Element element5 = (Element)XMLUtil.getFirstChildByName(element2, "InfoServiceRegistrationSettings");
            if (element5 == null) {
                LogHolder.log(3, LogType.MISC, "JAPRoutingSettings: loadSettingsFromXml: Error in XML structure (InfoServiceRegistrationSettings node): Using default infoservice registration settings.");
                n = 0;
            }
            else if (!this.getRegistrationInfoServicesStore().loadSettingsFromXml(element5)) {
                n = 0;
            }
            final Element element6 = (Element)XMLUtil.getFirstChildByName(element2, "AllowedMixCascadesSettings");
            if (element6 == null) {
                LogHolder.log(3, LogType.MISC, "JAPRoutingSettings: loadSettingsFromXml: Error in XML structure (AllowedMixCascadesSettings node): Using default forwarding mixcascade settings.");
                n = 0;
            }
            else if (!this.getUseableMixCascadesStore().loadSettingsFromXml(element6)) {
                n = 0;
            }
            final Element element7 = (Element)XMLUtil.getFirstChildByName(element2, "ServerRunning");
            if (element7 == null) {
                LogHolder.log(3, LogType.MISC, "JAPRoutingSettings: loadSettingsFromXml: Error in XML structure (ServerRunning node): Server not started.");
            }
            else if (XMLUtil.parseValue(element7, false)) {
                if (n == 1) {
                    if (this.setRoutingMode(2)) {
                        this.startPropaganda(false);
                        LogHolder.log(6, LogType.MISC, "JAPRoutingSettings: loadSettingsFromXml: According to the configuration, the forwarding server was started.");
                    }
                    else {
                        LogHolder.log(3, LogType.MISC, "JAPRoutingSettings: loadSettingsFromXml: Error while starting the forwarding server.");
                    }
                }
                else {
                    LogHolder.log(3, LogType.MISC, "JAPRoutingSettings: loadSettingsFromXml: Because of errors while loading the configuration, the forwarding server was not started.");
                }
            }
        }
        final Element element8 = (Element)XMLUtil.getFirstChildByName(element, "ForwardingClient");
        if (element8 == null) {
            LogHolder.log(3, LogType.MISC, "JAPRoutingSettings: loadSettingsFromXml: Error in XML structure (ForwardingClient node): Using default forwarding client settings.");
        }
        else {
            final Element element9 = (Element)XMLUtil.getFirstChildByName(element8, "ConnectViaForwarder");
            if (element9 == null) {
                LogHolder.log(3, LogType.MISC, "JAPRoutingSettings: loadSettingsFromXml: Error in XML structure (ConnectViaForwarder node): Using default value when enabling anonymity mode.");
            }
            else {
                this.setTransportMode(TransportMode.getByIdentifier(XMLUtil.parseAttribute(element8, "type", this.getTransportMode().getIdentifier())));
                this.setConnectViaForwarder(XMLUtil.parseValue(element9, false));
            }
            final Element element10 = (Element)XMLUtil.getFirstChildByName(element8, "ForwardInfoService");
            if (element10 == null) {
                LogHolder.log(3, LogType.MISC, "JAPRoutingSettings: loadSettingsFromXml: Error in XML structure (ForwardInfoService node): Using default value when creating a forwarded connection.");
            }
            else {
                this.setForwardInfoService(XMLUtil.parseValue(element10, false));
            }
        }
        return 0;
    }
    
    public JAPRoutingServerStatisticsListener getServerStatisticsListener() {
        return this.m_serverStatisticsListener;
    }
    
    public JAPRoutingRegistrationStatusObserver getRegistrationStatusObserver() {
        return this.m_registrationStatusObserver;
    }
    
    private void updateInfoServiceProxySettings() {
        synchronized (this) {
            if (this.getForwardInfoService()) {
                HTTPConnectionFactory.getInstance().setNewProxySettings(new ProxyInterface("localhost", JAPModel.getHttpListenerPortNumber(), 1, null), JAPModel.getInstance().isProxyAuthenticationUsed());
            }
        }
    }
    
    public static final class TransportMode
    {
        public static final TransportMode SKYPE;
        public static final TransportMode TCPIP;
        public static final TransportMode LOCAL;
        public static final TransportMode UNKNOWN;
        private String m_identifier;
        
        private TransportMode(final String s) {
            this.m_identifier = s.intern();
        }
        
        public String getIdentifier() {
            return this.m_identifier;
        }
        
        public static TransportMode getByIdentifier(String intern) {
            intern = intern.intern();
            if (intern == TransportMode.SKYPE.getIdentifier()) {
                return TransportMode.SKYPE;
            }
            if (intern == TransportMode.TCPIP.getIdentifier()) {
                return TransportMode.TCPIP;
            }
            if (intern == TransportMode.LOCAL.getIdentifier()) {
                return TransportMode.LOCAL;
            }
            return TransportMode.UNKNOWN;
        }
        
        static {
            SKYPE = new TransportMode("skype");
            TCPIP = new TransportMode("tcpip");
            LOCAL = new TransportMode("local");
            UNKNOWN = new TransportMode("");
        }
    }
}
