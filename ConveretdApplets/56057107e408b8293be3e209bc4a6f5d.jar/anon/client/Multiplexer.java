// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.io.IOException;
import java.util.Enumeration;
import java.io.InterruptedIOException;
import logging.LogHolder;
import logging.LogType;
import java.security.SecureRandom;
import anon.client.crypto.ControlChannelCipher;
import anon.client.crypto.SymCipher;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Vector;
import java.util.Observable;

public class Multiplexer extends Observable implements Runnable
{
    private Vector m_sendJobQueue;
    private Vector m_controlMessageQueue;
    private Object m_waitQueueObject;
    private ChannelTable m_channelTable;
    private InputStream m_inputStream;
    private OutputStream m_outputStream;
    private SymCipher m_inputStreamCipher;
    private SymCipher m_outputStreamCipher;
    private ControlChannelCipher m_controlchannelCiper;
    private Object m_internalEventSynchronization;
    private boolean m_bClosed;
    
    public Multiplexer(final InputStream inputStream, final OutputStream outputStream, final KeyExchangeManager keyExchangeManager, final SecureRandom secureRandom) {
        this.m_bClosed = false;
        this.m_internalEventSynchronization = new Object();
        this.m_sendJobQueue = new Vector();
        this.m_controlMessageQueue = new Vector();
        this.m_waitQueueObject = new Object();
        this.m_channelTable = new ChannelTable(new DefaultDataChannelFactory(keyExchangeManager, this), secureRandom);
        this.m_inputStream = inputStream;
        this.m_inputStreamCipher = keyExchangeManager.getMultiplexerInputStreamCipher();
        this.m_outputStream = outputStream;
        this.m_outputStreamCipher = keyExchangeManager.getMultiplexerOutputStreamCipher();
        this.m_controlchannelCiper = keyExchangeManager.getControlChannelCipher();
        final Thread thread = new Thread(this, "Multiplexer: Receive-Thread");
        thread.setDaemon(true);
        thread.start();
    }
    
    public void sendPacket(final MixPacket mixPacket) throws IOException {
        final Object o = new Object();
        final boolean controlChannelId = this.m_channelTable.isControlChannelId(mixPacket.getChannelId());
        final Vector vector = controlChannelId ? this.m_controlMessageQueue : this.m_sendJobQueue;
        synchronized (o) {
            boolean b = false;
            synchronized (this.m_waitQueueObject) {
                if (!controlChannelId) {
                    if (this.m_controlMessageQueue.size() > 0 || this.m_sendJobQueue.size() > 0) {
                        b = true;
                    }
                }
                else if (this.m_controlMessageQueue.size() > 0) {
                    b = true;
                    LogHolder.log(4, LogType.NET, "Control channel congestion");
                }
                vector.addElement(o);
            }
            if (b) {
                try {
                    o.wait();
                }
                catch (InterruptedException ex) {
                    Object o2 = null;
                    synchronized (this.m_waitQueueObject) {
                        vector.removeElement(o);
                        if (this.m_controlMessageQueue.size() > 0) {
                            o2 = this.m_controlMessageQueue.firstElement();
                        }
                        else if (this.m_sendJobQueue.size() > 0) {
                            o2 = this.m_sendJobQueue.firstElement();
                        }
                    }
                    if (o2 != null) {
                        synchronized (o2) {
                            o2.notify();
                        }
                    }
                    throw new InterruptedIOException(ex.toString());
                }
            }
        }
        final Enumeration<ISendCallbackHandler> elements = mixPacket.getSendCallbackHandlers().elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().finalizePacket(mixPacket);
        }
        final byte[] rawPacket = mixPacket.getRawPacket();
        if (this.m_outputStreamCipher != null) {
            this.m_outputStreamCipher.encryptAES1(rawPacket, 0, rawPacket, 0, 16);
        }
        try {
            this.m_outputStream.write(rawPacket);
            this.m_outputStream.flush();
            synchronized (this.m_internalEventSynchronization) {
                this.setChanged();
                if (controlChannelId) {
                    this.notifyObservers(new PacketProcessedEvent(6));
                }
                else {
                    this.notifyObservers(new PacketProcessedEvent(5));
                }
            }
        }
        finally {
            Object o3 = null;
            synchronized (this.m_waitQueueObject) {
                vector.removeElement(o);
                if (this.m_controlMessageQueue.size() > 0) {
                    o3 = this.m_controlMessageQueue.firstElement();
                }
                else if (this.m_sendJobQueue.size() > 0) {
                    o3 = this.m_sendJobQueue.firstElement();
                }
                else {
                    this.m_waitQueueObject.notify();
                }
            }
            if (o3 != null) {
                synchronized (o3) {
                    o3.notify();
                }
            }
        }
    }
    
    protected void close() {
        this.m_bClosed = true;
    }
    
    public void run() {
        try {
            while (true) {
                final MixPacket mixPacket = new MixPacket(this.m_inputStream, this.m_inputStreamCipher);
                final AbstractChannel channel = this.m_channelTable.getChannel(mixPacket.getChannelId());
                if (channel != null) {
                    synchronized (this.m_internalEventSynchronization) {
                        this.setChanged();
                        if (this.m_channelTable.isControlChannelId(mixPacket.getChannelId())) {
                            this.notifyObservers(new PacketProcessedEvent(2));
                        }
                        else {
                            this.notifyObservers(new PacketProcessedEvent(1));
                        }
                    }
                    channel.processReceivedPacket(mixPacket);
                }
                else {
                    if (LogHolder.isLogged(6, LogType.NET)) {
                        LogHolder.log(6, LogType.NET, "Received a packet for unknown channel '" + Integer.toString(mixPacket.getChannelId()) + "'.");
                    }
                    synchronized (this.m_internalEventSynchronization) {
                        this.setChanged();
                        if (this.m_channelTable.isControlChannelId(mixPacket.getChannelId())) {
                            this.notifyObservers(new PacketProcessedEvent(4));
                        }
                        else {
                            this.notifyObservers(new PacketProcessedEvent(3));
                        }
                    }
                }
                Thread.yield();
            }
        }
        catch (IOException ex) {
            if (this.m_bClosed) {
                if (LogHolder.isLogged(5, LogType.NET)) {
                    LogHolder.log(5, LogType.NET, Thread.currentThread().getName() + ": terminated!", ex);
                }
            }
            else {
                LogHolder.log(2, LogType.NET, Thread.currentThread().getName() + ": terminated!", ex);
            }
            this.m_channelTable.closeChannelTable();
        }
    }
    
    public ChannelTable getChannelTable() {
        return this.m_channelTable;
    }
    
    public ControlChannelCipher getControlChannelCipher() {
        return this.m_controlchannelCiper;
    }
}
