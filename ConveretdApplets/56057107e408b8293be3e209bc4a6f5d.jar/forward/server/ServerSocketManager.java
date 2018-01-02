// 
// Decompiled by Procyon v0.5.30
// 

package forward.server;

import java.net.Socket;
import anon.transport.connection.IStreamConnection;
import anon.transport.connection.SocketConnection;
import java.net.ServerSocket;

public class ServerSocketManager implements Runnable, IServerManager
{
    private static final int MAXIMUM_CONNECTION_REQUESTS = 5;
    private ServerSocket m_serverSocket;
    private Thread m_managerThread;
    private ForwardScheduler m_parentScheduler;
    private int m_portNumber;
    
    public ServerSocketManager(final int portNumber) {
        this.m_portNumber = portNumber;
    }
    
    public Object getId() {
        return this.getClass().getName() + "%" + Integer.toString(this.m_portNumber);
    }
    
    public void startServerManager(final ForwardScheduler parentScheduler) throws Exception {
        (this.m_serverSocket = new ServerSocket(this.m_portNumber, 5)).setSoTimeout(0);
        this.m_parentScheduler = parentScheduler;
        (this.m_managerThread = new Thread(this)).setDaemon(true);
        this.m_managerThread.start();
    }
    
    public void shutdown() {
        try {
            this.m_serverSocket.close();
        }
        catch (Exception ex) {}
        try {
            this.m_managerThread.join();
        }
        catch (Exception ex2) {}
    }
    
    public void run() {
        boolean b = false;
        while (!b) {
            Socket accept = null;
            try {
                accept = this.m_serverSocket.accept();
            }
            catch (Exception ex) {
                b = true;
            }
            if (!b) {
                try {
                    accept.setSoTimeout(200000);
                    this.m_parentScheduler.handleNewConnection(new SocketConnection(accept));
                }
                catch (Exception ex2) {}
            }
        }
    }
}
