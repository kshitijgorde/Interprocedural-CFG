// 
// Decompiled by Procyon v0.5.30
// 

package forward.server;

import anon.transport.address.Endpoint;
import anon.transport.connection.IStreamConnection;

public class ForwardConnection
{
    private IStreamConnection m_clientConnection;
    private IProtocolHandler m_serverConnection;
    private ForwardScheduler m_parentScheduler;
    private boolean m_closeConnection;
    private int m_transferFromClient;
    private int m_transferFromServer;
    private Thread m_clientReadThread;
    private Thread m_serverReadThread;
    private Thread m_timeoutThread;
    
    public ForwardConnection(final IStreamConnection clientConnection, final ForwardScheduler parentScheduler) throws Exception {
        this.m_clientConnection = clientConnection;
        this.m_parentScheduler = parentScheduler;
        this.m_serverConnection = new DefaultProtocolHandler(this);
        this.m_transferFromServer = 0;
        this.m_transferFromClient = 0;
        this.m_closeConnection = false;
        (this.m_clientReadThread = new Thread(new Runnable() {
            public void run() {
                while (!ForwardConnection.this.m_closeConnection) {
                    byte[] array = null;
                    synchronized (ForwardConnection.this.m_clientReadThread) {
                        if (ForwardConnection.this.m_transferFromClient > 0) {
                            array = new byte[ForwardConnection.this.m_transferFromClient];
                            try {
                                final int available = ForwardConnection.this.m_clientConnection.getInputStream().available();
                                if (array.length > available) {
                                    array = new byte[available];
                                }
                            }
                            catch (Exception ex) {
                                ForwardConnection.this.closeConnection();
                            }
                            ForwardConnection.this.m_transferFromClient = 0;
                        }
                        else {
                            array = new byte[] { 0 };
                        }
                    }
                    try {
                        final int read = ForwardConnection.this.m_clientConnection.getInputStream().read(array);
                        if (read == -1) {
                            ForwardConnection.this.closeConnection();
                        }
                        else {
                            if (read < array.length) {
                                final byte[] array2 = new byte[read];
                                System.arraycopy(array, 0, array2, 0, read);
                                array = array2;
                            }
                            if (read > 0) {
                                try {
                                    ForwardConnection.this.m_timeoutThread.interrupt();
                                }
                                catch (Exception ex2) {}
                                ForwardConnection.this.m_parentScheduler.getStatistics().incrementTransferVolume(read);
                                ForwardConnection.this.m_serverConnection.write(array);
                            }
                        }
                    }
                    catch (Exception ex3) {
                        ForwardConnection.this.closeConnection();
                    }
                    synchronized (ForwardConnection.this.m_clientReadThread) {
                        if (ForwardConnection.this.m_closeConnection) {
                            continue;
                        }
                        try {
                            ForwardConnection.this.m_clientReadThread.wait();
                        }
                        catch (Exception ex4) {}
                    }
                }
            }
        })).setDaemon(true);
        (this.m_serverReadThread = new Thread(new Runnable() {
            public void run() {
                while (!ForwardConnection.this.m_closeConnection) {
                    byte[] array = null;
                    synchronized (ForwardConnection.this.m_serverReadThread) {
                        array = new byte[ForwardConnection.this.m_transferFromServer];
                        try {
                            final int available = ForwardConnection.this.m_serverConnection.available();
                            if (array.length > available) {
                                array = new byte[available];
                            }
                        }
                        catch (Exception ex) {
                            ForwardConnection.this.closeConnection();
                        }
                        ForwardConnection.this.m_transferFromServer = 0;
                    }
                    try {
                        final int read = ForwardConnection.this.m_serverConnection.read(array);
                        if (read == -1) {
                            ForwardConnection.this.closeConnection();
                        }
                        else {
                            if (read < array.length) {
                                final byte[] array2 = new byte[read];
                                System.arraycopy(array, 0, array2, 0, read);
                                array = array2;
                            }
                            if (read > 0) {
                                try {
                                    ForwardConnection.this.m_timeoutThread.interrupt();
                                }
                                catch (Exception ex2) {}
                                ForwardConnection.this.m_parentScheduler.getStatistics().incrementTransferVolume(read);
                                ForwardConnection.this.m_clientConnection.getOutputStream().write(array);
                                ForwardConnection.this.m_clientConnection.getOutputStream().flush();
                            }
                        }
                    }
                    catch (Exception ex3) {
                        ForwardConnection.this.closeConnection();
                    }
                    synchronized (ForwardConnection.this.m_serverReadThread) {
                        if (ForwardConnection.this.m_closeConnection) {
                            continue;
                        }
                        try {
                            ForwardConnection.this.m_serverReadThread.wait();
                        }
                        catch (Exception ex4) {}
                    }
                }
            }
        })).setDaemon(true);
        (this.m_timeoutThread = new Thread(new Runnable() {
            public void run() {
                while (!ForwardConnection.this.m_closeConnection) {
                    try {
                        Thread.sleep(200000L);
                        ForwardConnection.this.closeConnection();
                    }
                    catch (Exception ex) {}
                }
            }
        })).setDaemon(true);
        this.m_clientReadThread.start();
        this.m_serverReadThread.start();
        this.m_timeoutThread.start();
    }
    
    public int getAvailableBytes() {
        int n = 0;
        try {
            n = this.m_clientConnection.getInputStream().available() + this.m_serverConnection.available();
        }
        catch (Exception ex) {
            this.closeConnection();
        }
        return n;
    }
    
    public ForwardScheduler getParentScheduler() {
        return this.m_parentScheduler;
    }
    
    public void closeConnection() {
        boolean closeConnection = false;
        synchronized (this.m_serverReadThread) {
            synchronized (this.m_clientReadThread) {
                closeConnection = this.m_closeConnection;
                this.m_closeConnection = true;
            }
        }
        if (!closeConnection) {
            try {
                this.m_clientConnection.close();
                if (this.m_serverConnection != null) {
                    this.m_serverConnection.close();
                }
            }
            catch (Exception ex) {}
            this.m_parentScheduler.removeConnection(this);
            synchronized (this.m_clientReadThread) {
                this.m_clientReadThread.notify();
            }
            synchronized (this.m_serverReadThread) {
                this.m_serverReadThread.notify();
            }
            try {
                this.m_timeoutThread.interrupt();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void allowTransfer(final int n) {
        int transferFromServer = 0;
        int transferFromClient = 0;
        if (n > 0) {
            try {
                final int available = this.m_clientConnection.getInputStream().available();
                final int available2 = this.m_serverConnection.available();
                if (available > available2) {
                    if (available2 > n / 2) {
                        transferFromServer = n / 2;
                    }
                    else {
                        transferFromServer = available2;
                    }
                    if (available > n - transferFromServer) {
                        transferFromClient = n - transferFromServer;
                    }
                    else {
                        transferFromClient = available;
                    }
                }
                else {
                    if (available > n / 2) {
                        transferFromClient = n / 2;
                    }
                    else {
                        transferFromClient = available;
                    }
                    if (available2 > n - transferFromClient) {
                        transferFromServer = n - transferFromClient;
                    }
                    else {
                        transferFromServer = available2;
                    }
                }
            }
            catch (Exception ex) {
                this.closeConnection();
            }
        }
        synchronized (this.m_clientReadThread) {
            this.m_transferFromClient = transferFromClient;
            this.m_clientReadThread.notify();
        }
        synchronized (this.m_serverReadThread) {
            this.m_transferFromServer = transferFromServer;
            this.m_serverReadThread.notify();
        }
    }
    
    public String toString() {
        return Endpoint.toURN(this.m_clientConnection.getRemoteAddress());
    }
}
