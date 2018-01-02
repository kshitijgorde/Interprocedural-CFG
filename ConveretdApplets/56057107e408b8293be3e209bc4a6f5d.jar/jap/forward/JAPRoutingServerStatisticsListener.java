// 
// Decompiled by Procyon v0.5.30
// 

package jap.forward;

import jap.JAPModel;
import forward.server.ForwardSchedulerStatistics;
import java.util.Observer;
import java.util.Observable;

public class JAPRoutingServerStatisticsListener extends Observable implements Observer, Runnable
{
    private static final long SERVER_STATISTICS_UPDATE_INTERVAL = 1000L;
    private int m_rejectedConnections;
    private int m_acceptedConnections;
    private int m_currentlyForwardedConnections;
    private long m_transferedBytes;
    private int m_currentBandwidthUsage;
    private ForwardSchedulerStatistics m_currentStatisticsInstance;
    private Thread m_statisticsThread;
    
    public JAPRoutingServerStatisticsListener() {
        this.m_rejectedConnections = 0;
        this.m_acceptedConnections = 0;
        this.m_currentlyForwardedConnections = 0;
        this.m_transferedBytes = 0L;
        this.m_currentBandwidthUsage = 0;
        this.m_currentStatisticsInstance = null;
        this.m_statisticsThread = null;
    }
    
    public int getRejectedConnections() {
        return this.m_rejectedConnections;
    }
    
    public int getAcceptedConnections() {
        return this.m_acceptedConnections;
    }
    
    public int getCurrentlyForwardedConnections() {
        return this.m_currentlyForwardedConnections;
    }
    
    public int getCurrentBandwidthUsage() {
        return this.m_currentBandwidthUsage;
    }
    
    public long getTransferedBytes() {
        return this.m_transferedBytes;
    }
    
    public void update(final Observable observable, final Object o) {
        try {
            if (observable == JAPModel.getInstance().getRoutingSettings() && ((JAPRoutingMessage)o).getMessageCode() == 1) {
                if (JAPModel.getInstance().getRoutingSettings().getRoutingMode() == 2) {
                    this.startStatistics();
                }
                else {
                    this.stopStatistics();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void run() {
        int i = 0;
        while (i == 0) {
            int n = 0;
            synchronized (this) {
                i = (Thread.interrupted() ? 1 : 0);
                if (i == 0) {
                    final int rejectedConnections = this.m_rejectedConnections;
                    final int acceptedConnections = this.m_acceptedConnections;
                    final int currentlyForwardedConnections = this.m_currentlyForwardedConnections;
                    final long transferedBytes = this.m_transferedBytes;
                    final int currentBandwidthUsage = this.m_currentBandwidthUsage;
                    this.m_rejectedConnections = this.m_currentStatisticsInstance.getRejectedConnections();
                    this.m_acceptedConnections = this.m_currentStatisticsInstance.getAcceptedConnections();
                    this.m_currentlyForwardedConnections = JAPModel.getInstance().getRoutingSettings().getCurrentlyForwardedConnections();
                    this.m_transferedBytes = this.m_currentStatisticsInstance.getTransferedBytes();
                    this.m_currentBandwidthUsage = this.m_currentStatisticsInstance.getCurrentBandwidthUsage();
                    if (rejectedConnections != this.m_rejectedConnections || acceptedConnections != this.m_acceptedConnections || currentlyForwardedConnections != this.m_currentlyForwardedConnections || transferedBytes != this.m_transferedBytes || currentBandwidthUsage != this.m_currentBandwidthUsage) {
                        n = 1;
                    }
                }
            }
            if (i == 0) {
                if (n == 1) {
                    this.setChanged();
                    this.notifyObservers(new JAPRoutingMessage(13));
                }
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex) {
                    i = 1;
                }
            }
        }
    }
    
    private void startStatistics() {
        synchronized (this) {
            this.stopStatistics();
            this.m_currentStatisticsInstance = JAPModel.getInstance().getRoutingSettings().getSchedulerStatistics();
            if (this.m_currentStatisticsInstance != null) {
                (this.m_statisticsThread = new Thread(this)).setDaemon(true);
                this.m_statisticsThread.start();
            }
        }
    }
    
    private void stopStatistics() {
        synchronized (this) {
            if (this.m_statisticsThread != null) {
                this.m_statisticsThread.interrupt();
                this.m_statisticsThread = null;
            }
            final int rejectedConnections = this.m_rejectedConnections;
            final int acceptedConnections = this.m_acceptedConnections;
            final int currentlyForwardedConnections = this.m_currentlyForwardedConnections;
            final long transferedBytes = this.m_transferedBytes;
            final int currentBandwidthUsage = this.m_currentBandwidthUsage;
            this.m_currentStatisticsInstance = null;
            this.m_rejectedConnections = 0;
            this.m_acceptedConnections = 0;
            this.m_currentlyForwardedConnections = 0;
            this.m_transferedBytes = 0L;
            this.m_currentBandwidthUsage = 0;
            if (rejectedConnections != this.m_rejectedConnections || acceptedConnections != this.m_acceptedConnections || currentlyForwardedConnections != this.m_currentlyForwardedConnections || transferedBytes != this.m_transferedBytes || currentBandwidthUsage != this.m_currentBandwidthUsage) {
                this.setChanged();
                this.notifyObservers(new JAPRoutingMessage(13));
            }
        }
    }
}
