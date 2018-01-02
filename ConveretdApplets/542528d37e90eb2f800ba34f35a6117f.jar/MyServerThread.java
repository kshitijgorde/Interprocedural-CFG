import java.io.IOException;
import java.util.StringTokenizer;
import java.io.DataInputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class MyServerThread extends Thread
{
    private Socket socket;
    private MyServer server;
    public int whichNick;
    String[] mssg;
    public boolean nickSent;
    public boolean ok;
    int type;
    int numElm;
    
    public MyServerThread(final MyServer server, final Socket socket) {
        this.mssg = new String[1000];
        this.nickSent = false;
        this.ok = false;
        this.socket = socket;
        this.server = server;
    }
    
    public void run() {
        this.whichNick = 91000;
        try {
            final DataInputStream dataInputStream = new DataInputStream(this.socket.getInputStream());
            this.nickSent = false;
            while (true) {
                if (!this.server.lasting) {
                    final String utf = dataInputStream.readUTF();
                    final StringTokenizer stringTokenizer = new StringTokenizer(utf, "^", false);
                    int numElm = 0;
                    while (stringTokenizer.hasMoreTokens()) {
                        this.mssg[numElm] = stringTokenizer.nextToken();
                        ++numElm;
                    }
                    this.numElm = numElm;
                    if (this.mssg[0].equals("0")) {
                        this.nickSent = true;
                        this.whichNick = MyServer.numNicks;
                        MyServer.nicks[this.whichNick] = this.mssg[1];
                        ++MyServer.numNicks;
                        this.server.sendNicks();
                    }
                    else {
                        if (!this.nickSent) {
                            continue;
                        }
                        this.ok = false;
                        this.type = Integer.parseInt(this.mssg[0]);
                        if (this.type > 1 && this.type < 7 && this.numElm == 6) {
                            this.ok = true;
                        }
                        if (this.type == 7 && this.numElm == 4) {
                            this.ok = true;
                        }
                        if (!this.ok) {
                            continue;
                        }
                        this.server.addPaint(utf);
                        this.server.sendToAll(utf);
                    }
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            while (this.server.removingNick) {}
            if (this.nickSent && this.whichNick != 91000) {
                this.server.removeNick(this.whichNick);
            }
        }
    }
}
