import java.io.Reader;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class BClient implements Runnable
{
    BApplet parent;
    Thread runner;
    private Socket socket;
    private String ip;
    private int port;
    private String host;
    BufferedReader from_server;
    PrintWriter to_server;
    String buffer;
    
    public void writeData(final String s) {
        this.to_server.print(s + '\0');
        this.to_server.flush();
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (Thread.currentThread() == this.runner) {
            try {
                final char[] array = { '\0' };
                while (this.from_server.read(array, 0, 1) != -1) {
                    final StringBuffer sb = new StringBuffer(8192);
                    while (array[0] != '\0') {
                        sb.append(array[0]);
                        this.from_server.read(array, 0, 1);
                    }
                    this.parent.net = sb.toString();
                    this.parent.netEvent();
                }
            }
            catch (IOException ex) {
                this.printLog("Read error from network server");
                ex.printStackTrace();
            }
            try {
                Thread.sleep(8L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void printLog(String string) {
        final Calendar instance = Calendar.getInstance();
        string = "[" + instance.get(2) + '/' + instance.get(5) + '/' + instance.get(1) + ' ' + instance.get(11) + ':' + instance.get(12) + ':' + instance.get(13) + "] " + string + '\n';
        System.out.print(string);
    }
    
    public void killClient() {
        try {
            this.from_server.close();
            this.to_server.close();
            this.socket.close();
            this.runner = null;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void destroy() {
        this.runner = null;
        this.killClient();
    }
    
    public void stop() {
    }
    
    private final /* synthetic */ void this() {
        this.runner = null;
    }
    
    public BClient(final BApplet parent, final String host, final int port) {
        this.this();
        this.parent = parent;
        this.host = host;
        this.port = port;
        try {
            this.socket = new Socket(this.host, this.port);
            this.from_server = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.to_server = new PrintWriter(this.socket.getOutputStream());
            this.printLog("Network connection succesfull");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
