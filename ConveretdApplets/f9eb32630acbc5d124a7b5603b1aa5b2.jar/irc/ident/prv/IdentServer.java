// 
// Decompiled by Procyon v0.5.30
// 

package irc.ident.prv;

import java.net.Socket;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import irc.ident.IdentListener;
import irc.IRCConfiguration;
import irc.ListenerGroup;
import java.net.ServerSocket;
import java.util.Hashtable;
import irc.IRCObject;

public class IdentServer extends IRCObject implements Runnable
{
    private Thread _thread;
    private boolean _running;
    private Hashtable _table;
    private ServerSocket _serverSocket;
    private boolean _defaultUser;
    private String _system;
    private String _id;
    private ListenerGroup _listeners;
    private int _port;
    
    public IdentServer(final IRCConfiguration ircConfiguration) {
        super(ircConfiguration);
        this.resetDefaultUser();
        this._table = new Hashtable();
        this._listeners = new ListenerGroup();
        this._thread = null;
    }
    
    public void start() throws Exception {
        this.start(113);
    }
    
    public void resetDefaultUser() {
        this._defaultUser = false;
    }
    
    public void setDefaultUser(final String system, final String id) {
        this._defaultUser = true;
        this._system = system;
        this._id = id;
    }
    
    public void start(final int port) throws Exception {
        this._port = port;
        this._running = false;
        this._serverSocket = super._ircConfiguration.getSecurityProvider().getServerSocket(this._port);
        (this._thread = new Thread(this, "IDENT server")).start();
        while (!this._running) {
            Thread.yield();
        }
    }
    
    public void stop() {
        if (this._thread == null) {
            return;
        }
        try {
            this._serverSocket.close();
            this._thread.join();
            this._thread = null;
        }
        catch (Exception ex) {}
    }
    
    public synchronized void registerLocalConnection(final int n, final String s, final String s2) {
        this._table.put(new Integer(n), new LocalInfo(n, s, s2));
    }
    
    public synchronized void unregisterLocalConnection(final int n) {
        this._table.remove(new Integer(n));
    }
    
    private synchronized LocalInfo processRequest(final int n) {
        return this._table.get(new Integer(n));
    }
    
    public synchronized void addIdentListener(final IdentListener identListener) {
        this._listeners.addListener(identListener);
    }
    
    public synchronized void removeIdentListener(final IdentListener identListener) {
        this._listeners.removeListener(identListener);
    }
    
    public void run() {
        int i = 0;
        this._running = true;
        this._listeners.sendEventAsync("identRunning", new Integer(this._port));
        while (i == 0) {
            try {
                final Socket accept = this._serverSocket.accept();
                String s = this.getText(522);
                this.getText(521);
                try {
                    try {
                        s = super._ircConfiguration.getSecurityProvider().resolve(accept.getInetAddress());
                    }
                    catch (Exception ex3) {
                        s = accept.getInetAddress().getHostAddress();
                    }
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                    final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
                    final String line = bufferedReader.readLine();
                    final int index = line.indexOf(44);
                    final String trim = line.substring(0, index).trim();
                    final String trim2 = line.substring(index + 1).trim();
                    final LocalInfo processRequest = this.processRequest(new Integer(trim));
                    final String string = trim + " , " + trim2 + " : ";
                    int n;
                    String s2;
                    if (processRequest == null) {
                        if (!this._defaultUser) {
                            n = 2;
                            s2 = string + "ERROR : NO-USER";
                        }
                        else {
                            n = 1;
                            s2 = string + "USERID : " + this._system + " : " + this._id;
                        }
                    }
                    else {
                        n = 0;
                        s2 = string + "USERID : " + processRequest.system + " : " + processRequest.id;
                    }
                    bufferedWriter.write(s2 + "\n");
                    bufferedWriter.flush();
                    bufferedReader.close();
                    bufferedWriter.close();
                    accept.close();
                    this._listeners.sendEventAsync("identRequested", s, new Integer(n), s2);
                }
                catch (Exception ex) {
                    this._listeners.sendEventAsync("identRequested", s, new Integer(-1), ex.getMessage());
                }
            }
            catch (Exception ex2) {
                this._listeners.sendEventAsync("identLeaving", ex2.getMessage());
                i = 1;
            }
        }
    }
}
