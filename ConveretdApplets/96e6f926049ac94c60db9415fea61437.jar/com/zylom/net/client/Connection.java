// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.net.client;

import java.io.IOException;
import java.util.Vector;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;

public class Connection implements Runnable
{
    private final Object flushLock;
    private Thread flushThread;
    private boolean open;
    private boolean encrypted;
    private boolean useBrowser;
    private Location location;
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private Thread thread;
    private final transient Vector listeners;
    private final transient Vector readQ;
    private final transient Vector writeQ;
    
    public Connection(final String s) throws MalformedLocationException {
        this.flushLock = new Object();
        this.listeners = new Vector();
        this.readQ = new Vector();
        this.writeQ = new Vector();
        this.location = new Location(s);
        if (this.thread == null) {
            (this.thread = new Thread(this, "Connection")).setDaemon(true);
            this.thread.start();
        }
    }
    
    public Connection(final Location location) {
        this.flushLock = new Object();
        this.listeners = new Vector();
        this.readQ = new Vector();
        this.writeQ = new Vector();
        if (location == null) {
            throw new IllegalArgumentException("location");
        }
        this.location = location;
        if (this.thread == null) {
            (this.thread = new Thread(this, "Connection")).setDaemon(true);
            this.thread.start();
        }
    }
    
    protected void finalize() throws Throwable {
        this.thread = null;
        this.close();
        synchronized (this.writeQ) {
            this.writeQ.notifyAll();
        }
        // monitorexit(this.writeQ)
        synchronized (this.readQ) {
            this.readQ.notifyAll();
        }
        // monitorexit(this.readQ)
        this.removeAllConnectionListeners();
        super.finalize();
    }
    
    public int sendData(final PDU pdu) {
        if (pdu != null) {
            if (this.thread == null) {
                (this.thread = new Thread(this, "Connection")).setDaemon(true);
                this.thread.start();
            }
            synchronized (this.writeQ) {
                this.writeQ.addElement(pdu);
                this.writeQ.notifyAll();
            }
            // monitorexit(this.writeQ)
        }
        return this.writeQ.size();
    }
    
    public void clearBuffers() {
        this.readQ.removeAllElements();
        this.writeQ.removeAllElements();
    }
    
    public void flush(final int n) throws InterruptedException, IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException("millis must be >= 0");
        }
        final long n2 = n;
        long n3;
        if (n2 != 0) {
            n3 = n2 + System.currentTimeMillis();
        }
        else {
            n3 = Long.MAX_VALUE;
        }
        synchronized (this.writeQ) {
            for (long n4 = n3 - System.currentTimeMillis(); this.writeQ.size() > 0 && n4 > 0; n4 = n3 - System.currentTimeMillis()) {
                try {
                    this.writeQ.wait(n4 / 10L);
                }
                catch (Exception ex) {}
            }
        }
        // monitorexit(this.writeQ)
        synchronized (this.flushLock) {
            this.flushThread = this.thread;
            this.thread = null;
            final Vector writeQ = this.writeQ;
            // monitorenter(writeQ)
            try {
                this.writeQ.notifyAll();
            }
            // monitorexit(writeQ)
            finally {}
            for (long n5 = n3 - System.currentTimeMillis(); n5 > 0 && this.flushThread != null; n5 = n3 - System.currentTimeMillis()) {
                try {
                    this.flushLock.wait(n5 / 10L);
                }
                catch (Exception ex2) {}
            }
        }
        // monitorexit(this.flushLock)
        if (System.currentTimeMillis() >= n3) {
            throw new InterruptedException();
        }
    }
    
    public PDU readData() {
        PDU pdu = null;
        synchronized (this.readQ) {
            while (this.readQ.size() == 0 && this.open) {
                try {
                    this.readQ.wait();
                }
                catch (InterruptedException ex) {}
            }
            if (this.readQ.size() > 0) {
                pdu = this.readQ.elementAt(0);
                this.readQ.removeElementAt(0);
            }
        }
        // monitorexit(this.readQ)
        return pdu;
    }
    
    public boolean dataAvailable() {
        return this.readQ.size() > 0;
    }
    
    void queuePDU(final PDU pdu) {
        if (pdu != null) {
            synchronized (this.readQ) {
                this.readQ.addElement(pdu);
                this.readQ.notifyAll();
            }
            // monitorexit(this.readQ)
            if (this.readQ.size() > 0) {
                this.fireDataAvailable(new ConnectionEvent(this));
            }
        }
    }
    
    public void open() throws IOException {
        if (!this.open) {
            try {
                if (!this.useBrowser) {
                    this.socket = new Socket(this.location.getHost(), this.location.getPort());
                    this.in = this.socket.getInputStream();
                    this.out = this.socket.getOutputStream();
                }
                this.location.getProtocol().open(this, this.location, this.socket, this.in, this.out);
            }
            catch (IOException ex) {
                this.in = null;
                this.out = null;
                this.socket = null;
                throw ex;
            }
            this.open = true;
            this.fireConnectionOpen(new ConnectionEvent(this));
        }
    }
    
    public void close() {
        this.writeQ.removeAllElements();
        if (this.open) {
            this.location.getProtocol().close(this);
            try {
                this.in.close();
            }
            catch (Exception ex) {}
            try {
                this.out.close();
            }
            catch (Exception ex2) {}
            try {
                this.socket.close();
            }
            catch (Exception ex3) {}
            this.in = null;
            this.out = null;
            this.socket = null;
            this.open = false;
            synchronized (this.readQ) {
                this.readQ.notifyAll();
            }
            // monitorexit(this.readQ)
            this.fireConnectionClosed(new ConnectionEvent(this));
        }
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public boolean isOpen() {
        return this.open;
    }
    
    public boolean isEncrypted() {
        return this.encrypted;
    }
    
    public void setEncrypted(final boolean encrypted) {
        this.encrypted = encrypted;
    }
    
    public boolean getUseBrowser() {
        return this.useBrowser;
    }
    
    public void setUseBrowser(final boolean useBrowser) {
        this.useBrowser = useBrowser;
    }
    
    public void run() {
        PDU pdu = null;
        while (Thread.currentThread() == this.thread) {
            try {
                synchronized (this.writeQ) {
                    while (this.writeQ.size() == 0 && Thread.currentThread() == this.thread) {
                        try {
                            this.writeQ.wait();
                        }
                        catch (InterruptedException ex) {}
                    }
                    pdu = null;
                    if (this.writeQ.size() > 0) {
                        pdu = this.writeQ.elementAt(0);
                        this.writeQ.removeElementAt(0);
                    }
                }
                // monitorexit(this.writeQ)
                if (pdu != null) {
                    try {
                        this.location.getProtocol().sendData(this, pdu);
                    }
                    catch (IOException ex2) {
                        this.fireSendError(new ConnectionEvent(this, pdu));
                        this.close();
                    }
                    continue;
                }
                continue;
            }
            catch (Exception ex3) {
                continue;
            }
            break;
        }
        synchronized (this.flushLock) {
            this.flushThread = null;
            this.flushLock.notifyAll();
        }
        // monitorexit(this.flushLock)
    }
    
    public Vector getConnectionListeners() {
        return (Vector)this.listeners.clone();
    }
    
    public void removeConnectionListener(final ConnectionListener connectionListener) {
        if (connectionListener != null) {
            this.listeners.removeElement(connectionListener);
        }
    }
    
    public void removeAllConnectionListeners() {
        this.listeners.removeAllElements();
    }
    
    public void addConnectionListener(final ConnectionListener connectionListener) {
        if (connectionListener != null) {
            synchronized (this.listeners) {
                if (!this.listeners.contains(connectionListener)) {
                    this.listeners.addElement(connectionListener);
                }
            }
            // monitorexit(this.listeners)
        }
    }
    
    void fireConnectionOpen(final ConnectionEvent connectionEvent) {
        final Vector connectionListeners = this.getConnectionListeners();
        for (int i = 0; i < connectionListeners.size(); ++i) {
            try {
                connectionListeners.elementAt(i).connectionOpen(connectionEvent);
            }
            catch (Exception ex) {}
        }
    }
    
    void fireConnectionClosed(final ConnectionEvent connectionEvent) {
        final Vector connectionListeners = this.getConnectionListeners();
        for (int i = 0; i < connectionListeners.size(); ++i) {
            try {
                connectionListeners.elementAt(i).connectionClosed(connectionEvent);
            }
            catch (Exception ex) {}
        }
    }
    
    void fireDataAvailable(final ConnectionEvent connectionEvent) {
        final Vector connectionListeners = this.getConnectionListeners();
        for (int i = 0; i < connectionListeners.size(); ++i) {
            try {
                connectionListeners.elementAt(i).dataAvailable(connectionEvent);
            }
            catch (Exception ex) {}
        }
    }
    
    void fireDataSend(final ConnectionEvent connectionEvent) {
        final Vector connectionListeners = this.getConnectionListeners();
        for (int i = 0; i < connectionListeners.size(); ++i) {
            try {
                connectionListeners.elementAt(i).dataSend(connectionEvent);
            }
            catch (Exception ex) {}
        }
    }
    
    void fireSendError(final ConnectionEvent connectionEvent) {
        final Vector connectionListeners = this.getConnectionListeners();
        for (int i = 0; i < connectionListeners.size(); ++i) {
            try {
                connectionListeners.elementAt(i).sendError(connectionEvent);
            }
            catch (Exception ex) {}
        }
    }
    
    void fireReadError(final ConnectionEvent connectionEvent) {
        final Vector connectionListeners = this.getConnectionListeners();
        for (int i = 0; i < connectionListeners.size(); ++i) {
            try {
                connectionListeners.elementAt(i).readError(connectionEvent);
            }
            catch (Exception ex) {}
        }
    }
}
