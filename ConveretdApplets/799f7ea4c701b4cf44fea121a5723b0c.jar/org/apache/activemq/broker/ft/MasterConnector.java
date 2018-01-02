// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.ft;

import org.slf4j.LoggerFactory;
import org.apache.activemq.util.ServiceSupport;
import org.apache.activemq.transport.TransportDisposedIOException;
import org.apache.activemq.command.Response;
import org.apache.activemq.command.MessageDispatch;
import org.apache.activemq.util.ServiceStopper;
import org.apache.activemq.command.ShutdownInfo;
import org.apache.activemq.command.ConnectionId;
import org.apache.activemq.command.Command;
import org.apache.activemq.transport.TransportListener;
import java.io.IOException;
import org.apache.activemq.transport.DefaultTransportListener;
import org.apache.activemq.transport.TransportFactory;
import java.util.List;
import java.net.URISyntaxException;
import org.apache.activemq.command.BrokerInfo;
import org.apache.activemq.command.ProducerInfo;
import org.apache.activemq.command.SessionInfo;
import org.apache.activemq.command.ConnectionInfo;
import org.apache.activemq.util.IdGenerator;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.transport.Transport;
import java.net.URI;
import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.apache.activemq.broker.BrokerServiceAware;
import org.apache.activemq.Service;

public class MasterConnector implements Service, BrokerServiceAware
{
    private static final Logger LOG;
    private BrokerService broker;
    private URI remoteURI;
    private URI localURI;
    private Transport localBroker;
    private Transport remoteBroker;
    private TransportConnector connector;
    private AtomicBoolean started;
    private AtomicBoolean stoppedBeforeStart;
    private final IdGenerator idGenerator;
    private String userName;
    private String password;
    private ConnectionInfo connectionInfo;
    private SessionInfo sessionInfo;
    private ProducerInfo producerInfo;
    private final AtomicBoolean masterActive;
    private BrokerInfo brokerInfo;
    private boolean firstConnection;
    private boolean failedToStart;
    
    public MasterConnector() {
        this.started = new AtomicBoolean(false);
        this.stoppedBeforeStart = new AtomicBoolean(false);
        this.idGenerator = new IdGenerator();
        this.masterActive = new AtomicBoolean();
        this.firstConnection = true;
    }
    
    public MasterConnector(final String remoteUri) throws URISyntaxException {
        this.started = new AtomicBoolean(false);
        this.stoppedBeforeStart = new AtomicBoolean(false);
        this.idGenerator = new IdGenerator();
        this.masterActive = new AtomicBoolean();
        this.firstConnection = true;
        this.remoteURI = new URI(remoteUri);
    }
    
    @Override
    public void setBrokerService(final BrokerService broker) {
        this.broker = broker;
        if (this.localURI == null) {
            this.localURI = broker.getVmConnectorURI();
        }
        if (this.connector == null) {
            final List transportConnectors = broker.getTransportConnectors();
            if (!transportConnectors.isEmpty()) {
                this.connector = transportConnectors.get(0);
            }
        }
    }
    
    public boolean isSlave() {
        return this.masterActive.get();
    }
    
    protected void restartBridge() throws Exception {
        this.localBroker.oneway(this.connectionInfo);
        this.remoteBroker.oneway(this.connectionInfo);
        this.localBroker.oneway(this.sessionInfo);
        this.remoteBroker.oneway(this.sessionInfo);
        this.remoteBroker.oneway(this.producerInfo);
        this.remoteBroker.oneway(this.brokerInfo);
    }
    
    @Override
    public void start() throws Exception {
        if (!this.started.compareAndSet(false, true)) {
            return;
        }
        if (this.remoteURI == null) {
            throw new IllegalArgumentException("You must specify a remoteURI");
        }
        this.localBroker = TransportFactory.connect(this.localURI);
        this.remoteBroker = TransportFactory.connect(this.remoteURI);
        MasterConnector.LOG.info("Starting a slave connection between " + this.localBroker + " and " + this.remoteBroker);
        this.localBroker.setTransportListener(new DefaultTransportListener() {
            @Override
            public void onCommand(final Object command) {
            }
            
            @Override
            public void onException(final IOException error) {
                if (MasterConnector.this.started.get()) {
                    MasterConnector.this.serviceLocalException(error);
                }
            }
        });
        this.remoteBroker.setTransportListener(new DefaultTransportListener() {
            @Override
            public void onCommand(final Object o) {
                final Command command = (Command)o;
                if (MasterConnector.this.started.get()) {
                    MasterConnector.this.serviceRemoteCommand(command);
                }
            }
            
            @Override
            public void onException(final IOException error) {
                if (MasterConnector.this.started.get()) {
                    MasterConnector.this.serviceRemoteException(error);
                }
            }
            
            @Override
            public void transportResumed() {
                try {
                    if (!MasterConnector.this.firstConnection) {
                        MasterConnector.this.localBroker = TransportFactory.connect(MasterConnector.this.localURI);
                        MasterConnector.this.localBroker.setTransportListener(new DefaultTransportListener() {
                            @Override
                            public void onCommand(final Object command) {
                            }
                            
                            @Override
                            public void onException(final IOException error) {
                                if (MasterConnector.this.started.get()) {
                                    MasterConnector.this.serviceLocalException(error);
                                }
                            }
                        });
                        MasterConnector.this.localBroker.start();
                        MasterConnector.this.restartBridge();
                        MasterConnector.LOG.info("Slave connection between " + MasterConnector.this.localBroker + " and " + MasterConnector.this.remoteBroker + " has been reestablished.");
                    }
                    else {
                        MasterConnector.this.firstConnection = false;
                    }
                }
                catch (IOException e) {
                    MasterConnector.LOG.error("MasterConnector failed to send BrokerInfo in transportResumed:", e);
                }
                catch (Exception e2) {
                    MasterConnector.LOG.error("MasterConnector failed to restart localBroker in transportResumed:", e2);
                }
            }
        });
        try {
            this.localBroker.start();
            this.remoteBroker.start();
            this.startBridge();
            this.masterActive.set(true);
        }
        catch (Exception e) {
            this.masterActive.set(false);
            if (!this.stoppedBeforeStart.get()) {
                MasterConnector.LOG.error("Failed to start network bridge: " + e, e);
            }
            else {
                MasterConnector.LOG.info("Slave stopped before connected to the master.");
            }
            this.setFailedToStart(true);
        }
    }
    
    protected void startBridge() throws Exception {
        (this.connectionInfo = new ConnectionInfo()).setConnectionId(new ConnectionId(this.idGenerator.generateId()));
        this.connectionInfo.setClientId(this.idGenerator.generateId());
        this.connectionInfo.setUserName(this.userName);
        this.connectionInfo.setPassword(this.password);
        this.connectionInfo.setBrokerMasterConnector(true);
        this.sessionInfo = new SessionInfo(this.connectionInfo, 1L);
        (this.producerInfo = new ProducerInfo(this.sessionInfo, 1L)).setResponseRequired(false);
        if (this.connector != null) {
            this.brokerInfo = this.connector.getBrokerInfo();
        }
        else {
            this.brokerInfo = new BrokerInfo();
        }
        this.brokerInfo.setBrokerName(this.broker.getBrokerName());
        this.brokerInfo.setPeerBrokerInfos(this.broker.getBroker().getPeerBrokerInfos());
        this.brokerInfo.setSlaveBroker(true);
        this.brokerInfo.setPassiveSlave(this.broker.isPassiveSlave());
        this.restartBridge();
        MasterConnector.LOG.info("Slave connection between " + this.localBroker + " and " + this.remoteBroker + " has been established.");
    }
    
    @Override
    public void stop() throws Exception {
        if (!this.started.compareAndSet(true, false) || !this.masterActive.get()) {
            return;
        }
        this.masterActive.set(false);
        try {
            this.remoteBroker.oneway(new ShutdownInfo());
            this.localBroker.oneway(new ShutdownInfo());
        }
        catch (IOException e) {
            MasterConnector.LOG.debug("Caught exception stopping", e);
        }
        finally {
            final ServiceStopper ss = new ServiceStopper();
            ss.stop(this.localBroker);
            ss.stop(this.remoteBroker);
            ss.throwFirstException();
        }
    }
    
    public void stopBeforeConnected() throws Exception {
        this.masterActive.set(false);
        this.started.set(false);
        this.stoppedBeforeStart.set(true);
        final ServiceStopper ss = new ServiceStopper();
        ss.stop(this.localBroker);
        ss.stop(this.remoteBroker);
    }
    
    protected void serviceRemoteException(final IOException error) {
        MasterConnector.LOG.error("Network connection between " + this.localBroker + " and " + this.remoteBroker + " shutdown: " + error.getMessage(), error);
        this.shutDown();
    }
    
    protected void serviceRemoteCommand(Command command) {
        try {
            if (command.isMessageDispatch()) {
                final MessageDispatch md = (MessageDispatch)command;
                command = md.getMessage();
            }
            if (command.getDataStructureType() == 11) {
                MasterConnector.LOG.warn("The Master has shutdown");
                this.shutDown();
            }
            else {
                final boolean responseRequired = command.isResponseRequired();
                final int commandId = command.getCommandId();
                if (responseRequired) {
                    final Response response = (Response)this.localBroker.request(command);
                    response.setCorrelationId(commandId);
                    this.remoteBroker.oneway(response);
                }
                else {
                    this.localBroker.oneway(command);
                }
            }
        }
        catch (IOException e) {
            this.serviceRemoteException(e);
        }
    }
    
    protected void serviceLocalException(final Throwable error) {
        if (!(error instanceof TransportDisposedIOException) || this.localBroker.isDisposed()) {
            MasterConnector.LOG.info("Network connection between " + this.localBroker + " and " + this.remoteBroker + " shutdown: " + error.getMessage(), error);
            ServiceSupport.dispose(this);
        }
        else {
            MasterConnector.LOG.info(error.getMessage());
        }
    }
    
    public URI getLocalURI() {
        return this.localURI;
    }
    
    public void setLocalURI(final URI localURI) {
        this.localURI = localURI;
    }
    
    public URI getRemoteURI() {
        return this.remoteURI;
    }
    
    public void setRemoteURI(final URI remoteURI) {
        this.remoteURI = remoteURI;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    private void shutDown() {
        this.masterActive.set(false);
        this.broker.masterFailed();
        ServiceSupport.dispose(this);
    }
    
    public boolean isStoppedBeforeStart() {
        return this.stoppedBeforeStart.get();
    }
    
    public boolean isFailedToStart() {
        return this.failedToStart;
    }
    
    public void setFailedToStart(final boolean failedToStart) {
        this.failedToStart = failedToStart;
    }
    
    static {
        LOG = LoggerFactory.getLogger(MasterConnector.class);
    }
}
