// 
// Decompiled by Procyon v0.5.30
// 

package forward.server;

import java.util.Enumeration;
import logging.LogHolder;
import anon.transport.address.Endpoint;
import logging.LogType;
import anon.transport.connection.IStreamConnection;
import java.util.Hashtable;
import java.util.Vector;

public class ForwardScheduler implements Runnable
{
    private static final long CYCLE_TIME = 100L;
    private int m_nrOfConnections;
    private int m_netBandwidth;
    private Vector m_connectionHandler;
    private boolean m_shutdown;
    private Thread m_schedulerThread;
    private Hashtable m_serverManagers;
    private ForwardSchedulerStatistics m_statistics;
    
    public ForwardScheduler() {
        this.m_nrOfConnections = 0;
        this.m_netBandwidth = 0;
        this.m_connectionHandler = new Vector();
        this.m_serverManagers = new Hashtable();
        this.m_shutdown = false;
        (this.m_schedulerThread = new Thread(this, "ForwardScheduler")).setDaemon(true);
        this.m_schedulerThread.start();
        this.m_statistics = new ForwardSchedulerStatistics();
    }
    
    public void handleNewConnection(final IStreamConnection streamConnection) {
        boolean b = false;
        synchronized (this.m_connectionHandler) {
            if (this.m_connectionHandler.size() < this.m_nrOfConnections && !this.m_shutdown) {
                try {
                    this.m_connectionHandler.addElement(new ForwardConnection(streamConnection, this));
                    b = true;
                    LogHolder.log(6, LogType.NET, "ForwardScheduler: handleNewConnection: New forwarding connection from " + Endpoint.toURN(streamConnection.getRemoteAddress()) + " accepted.");
                    this.m_statistics.incrementAcceptedConnections();
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.NET, "ForwardScheduler: handleNewConnection: Error initializing protocol on forwarding connection from " + Endpoint.toURN(streamConnection.getRemoteAddress()) + " (" + ex.toString() + ").");
                }
            }
        }
        if (!b) {
            LogHolder.log(6, LogType.NET, "ForwardScheduler: handleNewConnection: New forwarding connection from " + Endpoint.toURN(streamConnection.getRemoteAddress()) + " rejected (maximum number of connections is reached).");
            this.m_statistics.incrementRejectedConnections();
            try {
                streamConnection.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void removeConnection(final ForwardConnection forwardConnection) {
        synchronized (this.m_connectionHandler) {
            this.m_connectionHandler.removeElement(forwardConnection);
        }
        LogHolder.log(6, LogType.NET, "ForwardScheduler: removeConnection: Forwarded connection from " + forwardConnection.toString() + " was closed.");
    }
    
    public void shutdown() {
        this.m_shutdown = true;
        this.removeAllServerManagers();
        synchronized (this.m_connectionHandler) {
            final Enumeration<ForwardConnection> elements = this.m_connectionHandler.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().closeConnection();
            }
        }
        try {
            this.m_schedulerThread.interrupt();
            this.m_schedulerThread.join();
        }
        catch (Exception ex) {}
    }
    
    public int getMaximumBandwidth() {
        return this.m_netBandwidth;
    }
    
    public int getGuaranteedBandwidth() {
        return this.m_netBandwidth / this.m_nrOfConnections;
    }
    
    public void setMaximumNumberOfConnections(final int n) {
        if (n >= 0) {
            if (n < this.m_nrOfConnections) {
                this.m_nrOfConnections = n;
                synchronized (this.m_connectionHandler) {
                    while (this.m_connectionHandler.size() > this.m_nrOfConnections) {
                        try {
                            this.m_connectionHandler.elementAt((int)Math.round(Math.abs(Math.random() * this.m_connectionHandler.size()))).closeConnection();
                        }
                        catch (Exception ex) {}
                    }
                    return;
                }
            }
            this.m_nrOfConnections = n;
        }
    }
    
    public void setNetBandwidth(final int netBandwidth) {
        this.m_netBandwidth = netBandwidth;
    }
    
    public void addServerManager(final IServerManager serverManager) throws Exception {
        synchronized (this.m_serverManagers) {
            if (!this.m_shutdown) {
                if (this.m_serverManagers.containsKey(serverManager.getId())) {
                    throw new Exception("ForwardScheduler: addServerManager: Already a ServerManager with this ID running.");
                }
                serverManager.startServerManager(this);
                this.m_serverManagers.put(serverManager.getId(), serverManager);
            }
        }
    }
    
    public void removeServerManager(final Object o) {
        if (o != null) {
            synchronized (this.m_serverManagers) {
                final IServerManager serverManager = this.m_serverManagers.get(o);
                if (serverManager != null) {
                    serverManager.shutdown();
                    this.m_serverManagers.remove(o);
                }
            }
        }
    }
    
    public void removeAllServerManagers() {
        synchronized (this.m_serverManagers) {
            final Enumeration<IServerManager> elements = this.m_serverManagers.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().shutdown();
            }
            this.m_serverManagers.clear();
        }
    }
    
    public ForwardSchedulerStatistics getStatistics() {
        return this.m_statistics;
    }
    
    public int getCurrentlyForwardedConnections() {
        int size = 0;
        synchronized (this.m_connectionHandler) {
            size = this.m_connectionHandler.size();
        }
        return size;
    }
    
    public void run() {
        while (!this.m_shutdown) {
            synchronized (this.m_connectionHandler) {
                final int size = this.m_connectionHandler.size();
                final int[] array = new int[size];
                final Vector<Integer> vector = new Vector<Integer>();
                for (int i = 0; i < size; ++i) {
                    array[i] = ((ForwardConnection)this.m_connectionHandler.elementAt(i)).getAvailableBytes();
                    int n = 0;
                    for (int j = 0; j < i; ++j) {
                        if (array[j] < array[i]) {
                            ++n;
                        }
                    }
                    vector.insertElementAt(new Integer(i), n);
                }
                final int n2 = this.m_netBandwidth * 100 / 1000;
                if (size > 0) {
                    int n3 = n2 / size;
                    for (int k = 0; k < size; ++k) {
                        final int intValue = vector.elementAt(k);
                        if (array[intValue] < n3 && k + 1 != size) {
                            n3 += (n3 - array[intValue]) / (size - (k + 1));
                            ((ForwardConnection)this.m_connectionHandler.elementAt(intValue)).allowTransfer(array[intValue]);
                        }
                        else {
                            ((ForwardConnection)this.m_connectionHandler.elementAt(intValue)).allowTransfer(n3);
                        }
                    }
                }
            }
            for (long n4 = (System.currentTimeMillis() / 100L + 1L) * 100L, n5 = System.currentTimeMillis(); n5 < n4; n5 = System.currentTimeMillis()) {
                try {
                    Thread.sleep(n4 - n5);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
}
