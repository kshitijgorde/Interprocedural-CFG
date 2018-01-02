import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import de.bb.minissl.SslClient;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class Client implements Runnable
{
    Socket clsocket;
    PrintWriter ostream;
    BufferedReader reader;
    InputStream is;
    OutputStream os;
    Thread serverthread;
    int chatport;
    int sslchatport;
    int plainchatport;
    instahelp owner;
    
    Client(final instahelp owner, final String s, final String s2, final String s3, final boolean b, final int chatport) {
        this.chatport = 43;
        this.sslchatport = 143;
        this.plainchatport = 43;
        try {
            this.chatport = chatport;
            this.clsocket = new Socket(s, this.chatport);
            if (b) {
                final SslClient sslClient = new SslClient(this.clsocket.getInputStream(), this.clsocket.getOutputStream());
                this.is = sslClient.getInputStream();
                this.os = sslClient.getOutputStream();
            }
            else {
                this.is = this.clsocket.getInputStream();
                this.os = this.clsocket.getOutputStream();
            }
            instahelp.serverstarted = true;
            this.owner = owner;
            (this.ostream = new PrintWriter(new BufferedOutputStream(this.os))).println(s2 + "\n" + s3 + "\n");
            this.ostream.flush();
            (this.serverthread = new Thread(this)).start();
        }
        catch (Exception ex) {}
    }
    
    public void typingnotification() {
        this.ostream.println("typingnotification");
        this.ostream.flush();
    }
    
    public void sendmessage(final String s) {
        this.ostream.println("messagestarts\n" + s + "\nmessageends");
        this.ostream.flush();
    }
    
    public void run() {
        while (true) {
            try {
                this.reader = new BufferedReader(new InputStreamReader(this.is));
                String string = "";
                String line;
                while (!(line = this.reader.readLine()).startsWith("/endofmessage")) {
                    string += line;
                }
                if (string.compareTo("/finish") == 0) {
                    this.owner.terminate();
                    break;
                }
                if (string.compareTo("/typingnotification") == 0) {
                    this.owner.get_typingnotification();
                }
                else if (string.compareTo("/typingendnotification") == 0) {
                    this.owner.get_typingendnotification();
                }
                else {
                    this.owner.getmessage(string);
                }
            }
            catch (Exception ex) {
                this.owner.disconnected();
                break;
            }
        }
    }
    
    public void typingendnotification() {
        this.ostream.println("typingendnotification");
        this.ostream.flush();
    }
}
