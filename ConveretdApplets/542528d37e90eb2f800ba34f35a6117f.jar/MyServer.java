import java.util.Enumeration;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class MyServer
{
    private static Hashtable ht;
    private ServerSocket serverSocket;
    public static int numNicks;
    public static String[] nicks;
    public static MyServerThread[] a;
    int numThreads;
    String[] lastPaint;
    int numLast;
    public boolean lasting;
    public boolean removingNick;
    
    public static void main(final String[] array) throws IOException {
        new MyServer();
    }
    
    public MyServer() {
        this.numThreads = 0;
        this.lastPaint = new String[10000];
        this.numLast = 0;
        this.lasting = false;
        this.removingNick = false;
        this.lastPaint[750] = " ";
    Label_0087:
        while (true) {
            try {
                this.serverSocket = new ServerSocket(8754);
                System.out.println("Server Is Running ...");
                break Label_0087;
            }
            catch (IOException ex) {
                System.err.println("Could not listen on port: 8754.");
                System.exit(-1);
                break Label_0087;
            }
            break Label_0087;
            while (true) {
                try {
                    while (true) {
                        final Socket accept = this.serverSocket.accept();
                        System.out.println("Connection from : " + accept);
                        final DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());
                        MyServer.ht.put(accept, dataOutputStream);
                        MyServer.a[this.numThreads] = new MyServerThread(this, accept);
                        MyServer.a[this.numThreads++].start();
                        this.lasting = true;
                        this.sendLast(dataOutputStream, accept);
                        this.lasting = false;
                    }
                }
                catch (IOException ex2) {
                    continue;
                }
                continue Label_0087;
            }
            break;
        }
    }
    
    public void sendToAll(final String s) {
        synchronized (MyServer.ht) {
            final Enumeration<DataOutputStream> elements = (Enumeration<DataOutputStream>)MyServer.ht.elements();
            while (elements.hasMoreElements()) {
                final DataOutputStream dataOutputStream = elements.nextElement();
                try {
                    dataOutputStream.writeUTF(s);
                }
                catch (IOException ex) {}
            }
        }
    }
    
    public void sendNicks() {
        String string = "1";
        for (int i = 0; i < MyServer.numNicks; ++i) {
            string = string + "^" + MyServer.nicks[i];
        }
        this.sendToAll(string);
    }
    
    public void removeNick(final int n) {
        this.removingNick = true;
        for (int i = n; i < MyServer.numNicks - 1; ++i) {
            MyServer.nicks[i] = MyServer.nicks[i + 1];
            --MyServer.a[i + 1].whichNick;
            MyServer.a[i] = MyServer.a[i + 1];
        }
        --this.numThreads;
        --MyServer.numNicks;
        this.sendNicks();
        this.removingNick = false;
    }
    
    public void sendLast(final DataOutputStream dataOutputStream, final Socket socket) {
        try {
            for (int i = 0; i < this.numLast; i += 3) {
                if (i < this.numLast) {
                    dataOutputStream.writeUTF(this.lastPaint[i]);
                }
                if (i + 1 < this.numLast) {
                    dataOutputStream.writeUTF(this.lastPaint[i + 1]);
                }
                if (i + 2 < this.numLast) {
                    dataOutputStream.writeUTF(this.lastPaint[i + 2]);
                }
            }
        }
        catch (IOException ex) {
            this.removeConnection(socket);
            if (MyServer.a[this.numThreads - 1].nickSent && MyServer.a[this.numThreads - 1].whichNick != 91000) {
                this.removeNick(MyServer.a[this.numThreads - 1].whichNick);
            }
        }
    }
    
    public void addPaint(final String s) {
        if (this.numLast < 750) {
            this.lastPaint[this.numLast++] = s;
        }
        else {
            for (int i = 0; i < 750; ++i) {
                this.lastPaint[i] = this.lastPaint[i + 1];
            }
            this.lastPaint[750] = s;
        }
    }
    
    public void removeConnection(final Socket socket) {
        synchronized (MyServer.ht) {
            System.out.println("Removing connection to " + socket);
            MyServer.ht.remove(socket);
            try {
                socket.close();
            }
            catch (IOException ex) {}
        }
    }
    
    static {
        MyServer.ht = new Hashtable();
        MyServer.nicks = new String[1000];
        MyServer.a = new MyServerThread[1000];
    }
}
