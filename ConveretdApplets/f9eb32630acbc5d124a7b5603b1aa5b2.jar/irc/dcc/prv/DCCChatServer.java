// 
// Decompiled by Procyon v0.5.30
// 

package irc.dcc.prv;

import irc.EventDispatcher;
import irc.Source;
import java.util.Vector;
import java.util.Enumeration;
import irc.ServerListener;
import irc.IRCConfiguration;
import irc.ListenerGroup;
import irc.dcc.DCCChat;
import irc.CodingHandler;
import java.net.ServerSocket;
import java.net.Socket;
import irc.Server;
import irc.IRCObject;

public class DCCChatServer extends IRCObject implements Runnable, Server
{
    private Socket _socket;
    private ServerSocket _serverSocket;
    private CodingHandler _handler;
    private Thread _thread;
    private DCCChat _chat;
    private String _remoteNick;
    private String _thisNick;
    private ListenerGroup _listeners;
    private boolean _listening;
    private int _action;
    private boolean _connected;
    
    public DCCChatServer(final IRCConfiguration ircConfiguration, final String thisNick, final String remoteNick) {
        super(ircConfiguration);
        this._action = 0;
        this._listeners = new ListenerGroup();
        this._remoteNick = remoteNick;
        this._thisNick = thisNick;
        this._connected = false;
        this._chat = new DCCChat(super._ircConfiguration, this, this._remoteNick);
    }
    
    public void addServerListener(final ServerListener serverListener) {
        this._listeners.addListener(serverListener);
    }
    
    public void removeServerListener(final ServerListener serverListener) {
        this._listeners.removeListener(serverListener);
    }
    
    public void connect() {
    }
    
    public void disconnect() {
        this.close();
    }
    
    public boolean isConnected() {
        return this._connected;
    }
    
    public Enumeration getSources() {
        final Vector<DCCChat> vector = new Vector<DCCChat>();
        if (this._chat != null) {
            vector.insertElementAt(this._chat, 0);
        }
        return vector.elements();
    }
    
    public void enumerateSourcesAsCreated(final ServerListener serverListener) {
        if (this._chat != null) {
            serverListener.sourceCreated(this._chat, this, new Boolean(this._action == 2));
        }
    }
    
    public void enumerateSourcesAsRemoved(final ServerListener serverListener) {
        if (this._chat != null) {
            serverListener.sourceRemoved(this._chat, this);
        }
    }
    
    public void setDefaultSource(final Source source) {
    }
    
    public void release() {
        this.cleanup();
        this._chat = null;
        super.release();
    }
    
    public void openActive(String string, final String s) {
        this._serverSocket = null;
        this._action = 1;
        final long longValue = new Long(string);
        string = (int)(longValue >> 24 & 0xFFL) + "." + (int)(longValue >> 16 & 0xFFL) + "." + (int)(longValue >> 8 & 0xFFL) + "." + (int)(longValue & 0xFFL);
        try {
            this._socket = super._ircConfiguration.getSecurityProvider().getSocket(string, new Integer(s));
            this._handler = new CodingHandler(super._ircConfiguration, this._socket.getInputStream(), this._socket.getOutputStream());
            (this._thread = new Thread(this, "DCCChat thread")).start();
        }
        catch (Exception ex) {
            super._ircConfiguration.internalError("openActive failure", ex, "plouf@pjirc.com");
        }
    }
    
    public String openPassive() {
        this._action = 2;
        this._socket = null;
        try {
            this._serverSocket = super._ircConfiguration.getSecurityProvider().getServerSocket(0);
            final int localPort = this._serverSocket.getLocalPort();
            final byte[] address = super._ircConfiguration.getSecurityProvider().getLocalHost().getAddress();
            int n = address[0];
            if (n < 0) {
                n += 256;
            }
            int n2 = address[1];
            if (n2 < 0) {
                n2 += 256;
            }
            int n3 = address[2];
            if (n3 < 0) {
                n3 += 256;
            }
            int n4 = address[3];
            if (n4 < 0) {
                n4 += 256;
            }
            final String string = "" + (long)((n << 24) + (n2 << 16) + (n3 << 8) + n4);
            this._listening = false;
            (this._thread = new Thread(this, "DCCChat thread")).start();
            while (!this._listening) {
                Thread.yield();
            }
            return string + " " + localPort;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    private void reportChat(final String s) {
        EventDispatcher.dispatchEventAsync(this._chat, "report", new Object[] { s });
    }
    
    private void messageChat(final String s, final String s2) {
        EventDispatcher.dispatchEventAsync(this._chat, "messageReceived", new Object[] { s, s2 });
    }
    
    public void run() {
        int i = 0;
        if (this._action == 2) {
            try {
                this._listening = true;
                this.reportChat(this.getText(257));
                this._serverSocket.setSoTimeout(30000);
                this._socket = this._serverSocket.accept();
                this._handler = new CodingHandler(super._ircConfiguration, this._socket.getInputStream(), this._socket.getOutputStream());
            }
            catch (Exception ex) {
                this.reportChat(this.getText(258, ex.getMessage()));
                return;
            }
        }
        this.reportChat(this.getText(259));
        this._connected = true;
        this._listeners.sendEventAsync("serverConnected", this);
        while (i == 0) {
            try {
                final String read = this._handler.read();
                if (read == null) {
                    throw new Exception(this.getText(260));
                }
                try {
                    this.messageChat(this._remoteNick, read);
                }
                catch (Exception ex2) {
                    super._ircConfiguration.internalError("internal error", ex2, "plouf@pjirc.com");
                }
            }
            catch (Exception ex3) {
                i = 1;
                this.reportChat(this.getText(261, ex3.getMessage()));
            }
        }
        this._connected = false;
        this._listeners.sendEventAsync("serverDisconnected", this);
        this.cleanup();
    }
    
    public void say(final String s, final String s2) {
        if (s.equals(this._remoteNick)) {
            this.sendString(s2);
        }
        else {
            this._chat.report(this.getText(261, this.getText(262, s)));
        }
    }
    
    public void execute(final String s) {
        this._chat.report(this.getText(263));
    }
    
    private void sendString(final String s) {
        try {
            if (this._handler == null || !this._connected) {
                throw new Exception(this.getText(264));
            }
            this._handler.write(s);
        }
        catch (Exception ex) {
            this._chat.report(this.getText(261, ex.getMessage()));
        }
    }
    
    public void sendStatusMessage(final String s) {
        if (this._chat != null) {
            this._chat.report(s);
        }
    }
    
    private void cleanup() {
        try {
            if (this._socket != null) {
                this._socket.close();
            }
            if (this._serverSocket != null) {
                this._serverSocket.close();
            }
            this._handler.close();
        }
        catch (Exception ex) {
            super._ircConfiguration.internalError("cleanup failure", ex, "plouf@pjirc.com");
        }
    }
    
    public void close() {
        this.cleanup();
    }
    
    public void leave() {
        this.disconnect();
        final long currentTimeMillis = System.currentTimeMillis();
        while (this.isConnected()) {
            try {
                Thread.sleep(100L);
                if (System.currentTimeMillis() - currentTimeMillis > 10000L) {
                    break;
                }
                continue;
            }
            catch (InterruptedException ex) {}
        }
        this._listeners.sendEvent("sourceRemoved", this._chat, this);
        this._listeners.sendEvent("serverLeft", this);
        this._chat.release();
    }
    
    public String getNick() {
        return this._thisNick;
    }
    
    public String getUserName() {
        return "";
    }
    
    public String getServerName() {
        return this.getNick();
    }
}
