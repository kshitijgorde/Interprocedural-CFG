// 
// Decompiled by Procyon v0.5.30
// 

package forward.server;

import logging.LogHolder;
import logging.LogType;

public class ForwardServerManager
{
    public static final int CLIENT_CONNECTION_TIMEOUT = 200000;
    public static final int CLIENT_DUMMYTRAFFIC_INTERVAL = 180000;
    private static ForwardServerManager ms_fsmInstance;
    private int m_dummyTrafficInterval;
    private ForwardCascadeDatabase m_allowedCascadesDatabase;
    private ForwardScheduler m_forwardScheduler;
    
    public static ForwardServerManager getInstance() {
        if (ForwardServerManager.ms_fsmInstance == null) {
            ForwardServerManager.ms_fsmInstance = new ForwardServerManager();
        }
        return ForwardServerManager.ms_fsmInstance;
    }
    
    private ForwardServerManager() {
        this.m_dummyTrafficInterval = -1;
        this.m_allowedCascadesDatabase = new ForwardCascadeDatabase();
        this.m_forwardScheduler = null;
    }
    
    public void setDummyTrafficInterval(final int n) {
        if (n >= 0) {
            this.m_dummyTrafficInterval = Math.min(n, 180000);
        }
        else {
            this.m_dummyTrafficInterval = 180000;
        }
    }
    
    public int getDummyTrafficInterval() {
        return this.m_dummyTrafficInterval;
    }
    
    public ForwardCascadeDatabase getAllowedCascadesDatabase() {
        return this.m_allowedCascadesDatabase;
    }
    
    public void setMaximumNumberOfConnections(final int maximumNumberOfConnections) {
        synchronized (this) {
            if (this.m_forwardScheduler != null) {
                this.m_forwardScheduler.setMaximumNumberOfConnections(maximumNumberOfConnections);
            }
        }
    }
    
    public void setNetBandwidth(final int netBandwidth) {
        synchronized (this) {
            if (this.m_forwardScheduler != null) {
                this.m_forwardScheduler.setNetBandwidth(netBandwidth);
            }
        }
    }
    
    public Object addListenSocket(final int n) {
        Object id = null;
        synchronized (this) {
            if (this.m_forwardScheduler != null) {
                final ServerSocketManager serverSocketManager = new ServerSocketManager(n);
                try {
                    this.m_forwardScheduler.addServerManager(serverSocketManager);
                    id = serverSocketManager.getId();
                    LogHolder.log(7, LogType.NET, "Establishing ServerManager with ID '" + id.toString() + "' was successful.");
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.NET, "Error establishing socket at port " + Integer.toString(n) + ". Reason: " + ex.toString());
                }
            }
        }
        return id;
    }
    
    public Object addServerManager(final IServerManager serverManager) {
        Object id = null;
        synchronized (this) {
            if (this.m_forwardScheduler != null) {
                try {
                    this.m_forwardScheduler.addServerManager(serverManager);
                    id = serverManager.getId();
                    LogHolder.log(7, LogType.NET, "Establishing ServerManager with ID '" + id.toString() + "' was successful.");
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.NET, "Error adding Servermanager of Type " + serverManager.getClass().getName() + ". Reason: " + ex.toString());
                }
            }
        }
        return id;
    }
    
    public void removeServerManager(final Object o) {
        if (o != null) {
            synchronized (this) {
                if (this.m_forwardScheduler != null) {
                    this.m_forwardScheduler.removeServerManager(o);
                    LogHolder.log(7, LogType.NET, "ForwardServerManager: removeServerManager: ServerManager with ID '" + o.toString() + "' was removed (if it was running).");
                }
                return;
            }
        }
        LogHolder.log(2, LogType.NET, "ForwardServerManager: removeServerManager: ServerManager ID null is invalid.");
    }
    
    public void removeAllServerManagers() {
        synchronized (this) {
            if (this.m_forwardScheduler != null) {
                this.m_forwardScheduler.removeAllServerManagers();
                LogHolder.log(7, LogType.NET, "ForwardServerManager: removeAllServerManagers: All server managers removed.");
            }
        }
    }
    
    public void shutdownForwarding() {
        synchronized (this) {
            if (this.m_forwardScheduler != null) {
                this.m_forwardScheduler.shutdown();
                this.m_forwardScheduler = null;
            }
        }
    }
    
    public void startForwarding() {
        synchronized (this) {
            if (this.m_forwardScheduler == null) {
                this.m_forwardScheduler = new ForwardScheduler();
            }
        }
    }
    
    public ForwardSchedulerStatistics getSchedulerStatistics() {
        ForwardSchedulerStatistics statistics = null;
        synchronized (this) {
            if (this.m_forwardScheduler != null) {
                statistics = this.m_forwardScheduler.getStatistics();
            }
        }
        return statistics;
    }
    
    public int getCurrentlyForwardedConnections() {
        int currentlyForwardedConnections = 0;
        synchronized (this) {
            if (this.m_forwardScheduler != null) {
                currentlyForwardedConnections = this.m_forwardScheduler.getCurrentlyForwardedConnections();
            }
        }
        return currentlyForwardedConnections;
    }
    
    static {
        ForwardServerManager.ms_fsmInstance = null;
    }
}
