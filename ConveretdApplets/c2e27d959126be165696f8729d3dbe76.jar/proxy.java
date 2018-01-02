import java.io.IOException;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Date;
import java.net.ServerSocket;

// 
// Decompiled by Procyon v0.5.30
// 

public class proxy implements Runnable
{
    String remoteHost;
    int localPort;
    int remotePort;
    Thread listener;
    Thread connection;
    ServerSocket server;
    
    public proxy(final int lport, final String raddr, final int rport) {
        this.localPort = lport;
        this.remoteHost = raddr;
        this.remotePort = rport;
        this.log("destination host is " + this.remoteHost + " at port " + this.remotePort);
        try {
            this.server = new ServerSocket(this.localPort);
        }
        catch (Exception ex) {
            System.err.println("proxy: error: cannot create server socket");
        }
        this.log("listening on port " + this.localPort);
        (this.listener = new Thread(this)).setPriority(1);
        this.listener.start();
    }
    
    private void log(final String msg) {
        System.out.println("proxy: [" + new Date() + "] " + msg);
    }
    
    public static void main(final String[] args) {
        String remoteHost = "";
        int localPort = 0;
        int remotePort = 0;
        if (args.length < 2) {
            System.err.println("proxy: usage: proxy <port> <destination host> [<destination port>]");
            System.exit(1);
        }
        try {
            localPort = Integer.parseInt(args[0]);
        }
        catch (Exception ex) {
            System.err.println("proxy: parameter <port>: number expected");
            System.exit(1);
        }
        remoteHost = args[1];
        if (args.length > 2) {
            try {
                remotePort = Integer.parseInt(args[2]);
            }
            catch (Exception ex2) {
                System.err.println("proxy: parameter <destination port>: number expected");
                System.exit(1);
            }
        }
        final proxy me = new proxy(localPort, remoteHost, (remotePort == 0) ? 23 : remotePort);
    }
    
    public void run() {
        while (true) {
            Socket localSocket = null;
            try {
                localSocket = this.server.accept();
            }
            catch (Exception ex) {
                System.err.println("proxy: error: accept connection failed");
                continue;
            }
            this.log("accepted connection from " + localSocket.getInetAddress().getHostName());
            try {
                final Socket destinationSocket = new Socket(this.remoteHost, this.remotePort);
                this.log("connecting " + localSocket.getInetAddress().getHostName() + " <-> " + destinationSocket.getInetAddress().getHostName());
                final redirector r1 = new redirector(localSocket, destinationSocket);
                final redirector r2 = new redirector(destinationSocket, localSocket);
                r1.couple(r2);
                r2.couple(r1);
            }
            catch (Exception ex2) {
                System.err.println("proxy: error: cannot create sockets");
                try {
                    final DataOutputStream os = new DataOutputStream(localSocket.getOutputStream());
                    os.writeChars("Remote host refused connection.\n");
                    localSocket.close();
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
