// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.util.Enumeration;
import logging.LogHolder;
import logging.LogType;
import java.security.SecureRandom;
import java.util.Hashtable;

public class ChannelTable implements IDataChannelCreator
{
    public static final int CONTROL_CHANNEL_ID_PAY = 2;
    public static final int CONTROL_CHANNEL_ID_REPLAY = 3;
    public static final int CONTROL_CHANNEL_ID_DUMMY = 4;
    public static final int CONTROL_CHANNEL_ID_TEST = 255;
    private static final int MAX_OPEN_DATACHANNELS = 50;
    private static final int MIN_RESERVED_CHANNEL_ID = 0;
    private static final int MAX_RESERVED_CHANNEL_ID = 255;
    private IDataChannelFactory m_dataChannelFactory;
    private Hashtable m_channelTable;
    private volatile int m_availableDataChannels;
    private SecureRandom m_channelIdGenerator;
    private volatile boolean m_tableClosed;
    
    public ChannelTable(final IDataChannelFactory dataChannelFactory, final SecureRandom channelIdGenerator) {
        this.m_dataChannelFactory = dataChannelFactory;
        this.m_channelTable = new Hashtable();
        this.m_availableDataChannels = 50;
        this.m_channelIdGenerator = channelIdGenerator;
        this.m_tableClosed = false;
    }
    
    public AbstractChannel getChannel(final int n) {
        AbstractChannel abstractChannel = null;
        synchronized (this.m_channelTable) {
            abstractChannel = this.m_channelTable.get(new Integer(n));
        }
        return abstractChannel;
    }
    
    public void removeChannel(final int n) {
        synchronized (this.m_channelTable) {
            if (!this.m_tableClosed) {
                final AbstractChannel abstractChannel = this.m_channelTable.remove(new Integer(n));
                if (abstractChannel instanceof AbstractDataChannel) {
                    ++this.m_availableDataChannels;
                    this.m_channelTable.notifyAll();
                }
                if (abstractChannel != null) {
                    LogHolder.log(7, LogType.NET, "ChannelTable: removeChannel(): Removed channel with ID '" + Integer.toString(n) + "' from table.");
                }
            }
        }
    }
    
    public void registerControlChannel(final int n, final AbstractControlChannel abstractControlChannel) {
        synchronized (this.m_channelTable) {
            if (!this.m_tableClosed) {
                this.m_channelTable.put(new Integer(n), abstractControlChannel);
                LogHolder.log(7, LogType.NET, "ChannelTable: registerControlChannel(): Registered ControlChannel with ID '" + Integer.toString(n) + "'.");
            }
            else {
                abstractControlChannel.multiplexerClosed();
            }
        }
    }
    
    public boolean isControlChannelId(final int n) {
        return n > 0 && n <= 255;
    }
    
    public AbstractDataChannel createDataChannel(final AbstractDataChain abstractDataChain) {
        AbstractDataChannel abstractDataChannel = null;
        synchronized (this.m_channelTable) {
            try {
                while (this.m_availableDataChannels <= 0 && !this.m_tableClosed) {
                    this.m_channelTable.wait();
                }
                if (!this.m_tableClosed) {
                    final int freeChannelId = this.getFreeChannelId();
                    abstractDataChannel = this.m_dataChannelFactory.createDataChannel(freeChannelId, abstractDataChain);
                    this.m_channelTable.put(new Integer(freeChannelId), abstractDataChannel);
                    --this.m_availableDataChannels;
                    LogHolder.log(7, LogType.NET, "ChannelTable: createDataChannel(): Created DataChannel with ID '" + Integer.toString(freeChannelId) + "'.");
                }
                else {
                    abstractDataChannel = this.m_dataChannelFactory.createDataChannel(0, abstractDataChain);
                    abstractDataChannel.multiplexerClosed();
                }
            }
            catch (InterruptedException ex) {}
        }
        return abstractDataChannel;
    }
    
    public void closeChannelTable() {
        synchronized (this.m_channelTable) {
            this.m_tableClosed = true;
            final Enumeration<AbstractChannel> elements = this.m_channelTable.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().multiplexerClosed();
            }
            this.m_channelTable.clear();
            this.m_availableDataChannels = 50;
            LogHolder.log(7, LogType.NET, "ChannelTable: closeChannelTable(): Removed all channels from table.");
            this.m_channelTable.notifyAll();
        }
    }
    
    private int getFreeChannelId() {
        int nextInt;
        do {
            nextInt = this.m_channelIdGenerator.nextInt();
        } while ((nextInt >= 0 && nextInt <= 255) || this.getChannel(nextInt) != null);
        return nextInt;
    }
}
