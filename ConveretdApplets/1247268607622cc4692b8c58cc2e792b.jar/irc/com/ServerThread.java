// 
// Decompiled by Procyon v0.5.30
// 

package irc.com;

import java.util.Observable;
import java.text.ParseException;
import irc.com.messages.Message;
import java.io.OutputStream;
import java.io.InputStream;
import irc.EIRC;
import irc.main;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;
import irc.Testing;
import irc.com.commands.CommandFilter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import irc.com.interfaces.ClientProcess;
import java.net.Socket;
import java.util.Observer;
import irc.com.interfaces.ServerProcess;

public class ServerThread extends Thread implements ServerProcess, Observer, Runnable
{
    private Socket sock;
    private ClientProcess client;
    private BufferedReader br;
    private BufferedWriter bw;
    private boolean disconnected;
    private CommandFilter filter;
    private Testing a;
    
    public ServerThread(final ClientProcess client, final Socket sock) {
        super("ServerThread");
        this.a = null;
        this.setPriority(10);
        this.client = client;
        this.sock = sock;
        this.filter = new CommandFilter(this);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = this.sock.getInputStream();
            outputStream = this.sock.getOutputStream();
        }
        catch (IOException ex) {}
        try {
            this.br = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-15"));
            this.bw = new BufferedWriter(new OutputStreamWriter(outputStream, "ISO-8859-15"));
        }
        catch (UnsupportedEncodingException ex2) {
            try {
                this.bw = new BufferedWriter(new OutputStreamWriter(outputStream, "ISO-8859-1"));
                this.br = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
            }
            catch (UnsupportedEncodingException ex3) {}
        }
        this.setPriority(6);
        if (main.istest) {
            this.a = new Testing(((EIRC)client).getNick());
        }
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
        for (int i = 0; i < slices.length; ++i) {
            try {
                this.bw.write(slices[i], 0, slices[i].length());
                this.bw.flush();
            }
            catch (IOException ex) {}
            if (this.a != null) {
                this.a.addd("S : " + slices[0]);
            }
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
        catch (Exception ex2) {}
        if (line == null) {
            this.client.disconnect();
            this.client.notifyDisconnect();
            return false;
        }
        try {
            final Message message = new Message(line);
            if (!this.filter.filteredCompletely(message)) {
                synchronized (this) {
                    this.client.processMessage(message);
                }
            }
        }
        catch (ParseException ex) {
            ex.printStackTrace();
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
