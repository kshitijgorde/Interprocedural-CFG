// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.net.SocketException;
import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;

final class EstablishConnection extends Thread
{
    String actual_host;
    int actual_port;
    IOException exception;
    Socket sock;
    SocksClient Socks_client;
    boolean close;
    
    EstablishConnection(final String actual_host, final int actual_port, final SocksClient socks_client) {
        super("EstablishConnection (" + actual_host + ":" + actual_port + ")");
        try {
            this.setDaemon(true);
        }
        catch (SecurityException ex) {}
        this.actual_host = actual_host;
        this.actual_port = actual_port;
        this.Socks_client = socks_client;
        this.exception = null;
        this.sock = null;
        this.close = false;
    }
    
    public void run() {
        try {
            if (this.Socks_client != null) {
                this.sock = this.Socks_client.getSocket(this.actual_host, this.actual_port);
            }
            else {
                final InetAddress[] allByName = InetAddress.getAllByName(this.actual_host);
                int i = 0;
                while (i < allByName.length) {
                    try {
                        this.sock = new Socket(allByName[i], this.actual_port);
                        break;
                    }
                    catch (SocketException exception) {
                        if (i == allByName.length - 1 || this.close) {
                            this.exception = exception;
                            break;
                        }
                        ++i;
                    }
                }
            }
        }
        catch (IOException exception2) {
            this.exception = exception2;
        }
        catch (Exception ex) {
            this.exception = new IOException("UnknownIOExcpetion in EstablishConnection: " + ex.getMessage());
        }
        if (this.close) {
            try {
                this.sock.close();
            }
            catch (Exception ex2) {}
            this.sock = null;
        }
    }
    
    IOException getException() {
        return this.exception;
    }
    
    Socket getSocket() {
        return this.sock;
    }
    
    void forget() {
        this.close = true;
    }
}
