// 
// Decompiled by Procyon v0.5.30
// 

package irc.com;

import java.util.Observable;
import java.text.ParseException;
import irc.com.messages.Message;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import irc.EIRC;
import irc.main;
import java.net.Socket;
import com.c.o;
import com.c.i;
import irc.com.interfaces.ClientProcess;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import irc.Testing;
import java.util.Observer;
import irc.com.interfaces.ServerProcess;

public class ServerThreadCry extends Thread implements ServerProcess, Observer, Runnable
{
    private Testing a;
    private BufferedReader br;
    private BufferedWriter bw;
    public ClientProcess client;
    private boolean disconnected;
    i is;
    o os;
    private Socket sock;
    
    public ServerThreadCry(final ClientProcess client, final Socket sock) {
        super("ServerThread");
        this.a = null;
        this.is = null;
        this.os = null;
        if (main.istest) {
            this.a = new Testing(((EIRC)client).getNick());
        }
        this.client = client;
        this.sock = sock;
        try {
            this.is = new i(this.sock.getInputStream());
            (this.os = new o(this.sock.getOutputStream(), 9)).setFlushMode(1);
            this.br = new BufferedReader(new InputStreamReader(this.is, "ISO-8859-15"));
            this.bw = new BufferedWriter(new OutputStreamWriter(this.os, "ISO-8859-15"));
        }
        catch (UnsupportedEncodingException ex2) {
            try {
                this.bw = new BufferedWriter(new OutputStreamWriter(this.os, "ISO-8859-1"));
                this.br = new BufferedReader(new InputStreamReader(this.is, "ISO-8859-1"));
            }
            catch (UnsupportedEncodingException ex3) {}
        }
        catch (IOException ex) {
            System.out.println("3" + ex);
            ex.printStackTrace();
        }
        this.setPriority(10);
    }
    
    @Override
    public void disconnect() {
        this.disconnected = true;
        try {
            this.br.close();
        }
        catch (IOException ex) {}
        try {
            this.bw.close();
        }
        catch (IOException ex2) {}
        try {
            this.sock.close();
        }
        catch (IOException ex3) {}
    }
    
    @Override
    public void enqueueMessage(final Message message) {
        final String[] slices = message.slices();
        try {
            if (this.a != null) {
                this.a.addd("S : " + slices[0]);
            }
            this.bw.write(slices[0]);
            this.bw.flush();
            if (slices.length > 1) {
                this.bw.write("PRIVMSG #applet-unknown " + slices[0]);
                this.bw.flush();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public Socket getSocket() {
        return this.sock;
    }
    
    private boolean processNextLine() {
        String line = null;
        try {
            line = this.br.readLine();
            if (this.a != null) {
                this.a.addd("E : " + line);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (line == null) {
            this.client.disconnect();
            this.client.notifyDisconnect();
            return false;
        }
        try {
            final Message message = new Message(line);
            synchronized (this) {
                this.client.processMessage(message);
            }
        }
        catch (ParseException ex2) {
            ex2.printStackTrace();
        }
        return true;
    }
    
    @Override
    public void run() {
        while (!this.disconnected) {
            this.processNextLine();
        }
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
    }
}
