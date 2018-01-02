import java.io.Reader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.Socket;
import java.io.IOException;
import java.util.Calendar;
import java.util.Enumeration;
import java.net.ServerSocket;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class BServer implements Runnable
{
    BApplet parent;
    Thread runner;
    private Vector clients;
    ServerSocket server;
    int port;
    int numClients;
    int nextclientID;
    
    public synchronized void broadcast(String string) {
        string += '\0';
        final Enumeration<BServerClient> elements = this.clients.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().send(string);
        }
    }
    
    public void removeClient(final BServerClient bServerClient) {
        this.printLog(bServerClient.getIP() + " has left the server");
        this.clients.removeElement(bServerClient);
        this.parent.netEvent(1);
    }
    
    public void printLog(String string) {
        final Calendar instance = Calendar.getInstance();
        string = "[" + instance.get(2) + '/' + instance.get(5) + '/' + instance.get(1) + ' ' + instance.get(11) + ':' + instance.get(12) + ':' + instance.get(13) + "] " + string + '\n';
        System.out.print(string);
    }
    
    private final void killServer() {
        try {
            this.server.close();
            this.printLog("Server stopped");
        }
        catch (IOException ex) {
            this.printLog("Error while stopping server");
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (Thread.currentThread() == this.runner) {
            try {
                final BServerClient bServerClient = new BServerClient(this, this.server.accept());
                this.printLog(bServerClient.getIP() + " connected to the server");
                this.clients.addElement(bServerClient);
                bServerClient.start();
                this.numClients = this.clients.size();
            }
            catch (IOException ex) {
                this.printLog("Network server error...stopping server");
                this.killServer();
            }
            try {
                Thread.sleep(8L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void destroy() {
        this.runner = null;
        this.killServer();
    }
    
    public void stop() {
    }
    
    private final /* synthetic */ void this() {
        this.runner = null;
        this.clients = new Vector();
        this.numClients = 0;
        this.nextclientID = 0;
    }
    
    public BServer(final BApplet parent, final int port) {
        this.this();
        this.parent = parent;
        this.port = port;
        this.printLog("Starting network server...");
        try {
            this.server = new ServerSocket(this.port);
            System.out.println("Network server started on port: " + port);
        }
        catch (IOException ex) {
            this.printLog("Network server error... stopping server");
            this.killServer();
        }
    }
    
    public class BServerClient extends Thread
    {
        private Thread thrThis;
        private Socket socket;
        private BServer server;
        private String ip;
        protected BufferedReader in;
        protected PrintWriter out;
        
        public void run() {
            try {
                final char[] array = { '\0' };
                while (this.in.read(array, 0, 1) != -1) {
                    final StringBuffer sb = new StringBuffer(8192);
                    while (array[0] != '\0') {
                        sb.append(array[0]);
                        this.in.read(array, 0, 1);
                    }
                    BServer.this.parent.net = sb.toString();
                    this.server.broadcast(sb.toString());
                    BServer.this.parent.netEvent();
                }
            }
            catch (IOException ex) {
                this.server.printLog("Client IP: " + this.ip + " caused a read error " + ex + " : " + ex.getMessage() + "and has been disconnected.");
            }
            finally {
                this.killClient();
            }
            this.killClient();
        }
        
        public String getIP() {
            return this.ip;
        }
        
        public void send(final String s) {
            this.out.print(s);
            this.out.flush();
            if (this.out.checkError()) {
                this.server.printLog("Client IP: " + this.ip + " caused a write error and has been disconnected.");
                this.killClient();
            }
        }
        
        private final void killClient() {
            this.server.removeClient(this);
            try {
                this.in.close();
                this.out.close();
                this.socket.close();
                this.thrThis = null;
            }
            catch (IOException ex) {
                this.server.printLog("Client IP: " + this.ip + " caused an error while disconnecting.");
            }
        }
        
        public BServerClient(final BServer server, final Socket socket) {
            this.server = server;
            this.socket = socket;
            this.ip = socket.getInetAddress().getHostAddress();
            try {
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.out = new PrintWriter(socket.getOutputStream(), true);
            }
            catch (IOException ex) {
                server.printLog("Client IP: " + this.ip + " could not be initialized and has been disconnected.");
                this.killClient();
            }
        }
    }
}
