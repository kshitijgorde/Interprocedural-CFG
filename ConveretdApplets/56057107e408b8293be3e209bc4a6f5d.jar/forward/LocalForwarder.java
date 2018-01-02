// 
// Decompiled by Procyon v0.5.30
// 

package forward;

import anon.transport.connection.IConnection;
import anon.transport.address.IAddress;
import anon.transport.connection.ConnectionException;
import anon.transport.connection.RequestException;
import anon.transport.connection.IChunkConnection;
import anon.transport.connection.ChunkConnectionAdapter;
import anon.transport.connection.util.QueuedChunkConnection;
import anon.util.ObjectQueue;
import anon.transport.connection.CommunicationException;
import anon.transport.connection.IStreamConnection;
import logging.LogHolder;
import logging.LogType;
import forward.server.ForwardServerManager;
import forward.server.ForwardScheduler;
import anon.transport.connector.IConnector;
import forward.server.IServerManager;

public class LocalForwarder implements IServerManager, IConnector
{
    private static LocalForwarder m_instance;
    private static Object m_currentServerManagerId;
    private ForwardScheduler m_scheduler;
    private boolean m_isListing;
    
    public static void registerLocalForwarder(final int netBandwidth) {
        if (LocalForwarder.m_currentServerManagerId != null) {
            return;
        }
        ForwardServerManager.getInstance().startForwarding();
        ForwardServerManager.getInstance().setNetBandwidth(netBandwidth);
        ForwardServerManager.getInstance().setMaximumNumberOfConnections(1);
        LocalForwarder.m_currentServerManagerId = ForwardServerManager.getInstance().addServerManager(LocalForwarder.m_instance);
        LogHolder.log(5, LogType.TRANSPORT, "Local Forwarder registert");
    }
    
    public static void unregisterLocalForwarder() {
        if (LocalForwarder.m_currentServerManagerId == null) {
            return;
        }
        ForwardServerManager.getInstance().shutdownForwarding();
        ForwardServerManager.getInstance().removeServerManager(LocalForwarder.m_currentServerManagerId);
        LocalForwarder.m_currentServerManagerId = null;
        LogHolder.log(5, LogType.TRANSPORT, "Local Forwarder removed");
    }
    
    public static IServerManager getServerManager() {
        return LocalForwarder.m_instance;
    }
    
    public static IConnector getConnector() {
        return LocalForwarder.m_instance;
    }
    
    private LocalForwarder() {
        this.m_isListing = false;
    }
    
    public Object getId() {
        return this.getClass().getName();
    }
    
    public void shutdown() {
        this.m_isListing = false;
        this.m_scheduler = null;
    }
    
    public void startServerManager(final ForwardScheduler scheduler) throws Exception {
        this.m_scheduler = scheduler;
        this.m_isListing = true;
        LogHolder.log(5, LogType.TRANSPORT, "Local Forwarder listning");
    }
    
    public IStreamConnection connect(final LocalAddress localAddress) throws ConnectionException {
        if (!this.m_isListing) {
            throw new CommunicationException("Remoteend could not be reached");
        }
        final ObjectQueue objectQueue = new ObjectQueue();
        final ObjectQueue objectQueue2 = new ObjectQueue();
        final ChunkConnectionAdapter chunkConnectionAdapter = new ChunkConnectionAdapter(new QueuedChunkConnection(objectQueue, objectQueue2));
        final ChunkConnectionAdapter chunkConnectionAdapter2 = new ChunkConnectionAdapter(new QueuedChunkConnection(objectQueue2, objectQueue));
        this.m_scheduler.handleNewConnection(chunkConnectionAdapter);
        if (chunkConnectionAdapter.getCurrentState() == 2) {
            throw new RequestException("Reques denied for unknown Reason");
        }
        return chunkConnectionAdapter2;
    }
    
    public IConnection connect(final IAddress address) throws ConnectionException {
        return null;
    }
    
    static {
        LocalForwarder.m_instance = new LocalForwarder();
        LocalForwarder.m_currentServerManagerId = null;
    }
}
