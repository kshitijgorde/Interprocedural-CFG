// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.util.Vector;
import java.net.Socket;

public class ServerProtocol extends IRCObject implements Runnable
{
    private ListenerGroup _listeners;
    private String _host;
    private int _port;
    private Socket _socket;
    private CodingHandler _handler;
    private Thread _thread;
    private boolean _connected;
    private boolean _connecting;
    
    public ServerProtocol(final IRCConfiguration ircConfiguration) {
        super(ircConfiguration);
        this._connected = false;
        this._connecting = false;
        this._listeners = new ListenerGroup();
    }
    
    public void connect(final String host, final int port) {
        if (this._connected) {
            this.disconnect();
        }
        this._connecting = true;
        this._host = host;
        this._port = port;
        (this._thread = new Thread(this, "Read thread")).start();
    }
    
    public boolean connected() {
        return this._connected;
    }
    
    public boolean connecting() {
        return this._connecting;
    }
    
    public synchronized void disconnect() {
        if (!this._connected) {
            return;
        }
        if (this._connecting) {
            return;
        }
        try {
            this._socket.close();
            this._handler.close();
        }
        catch (Exception ex) {}
        this._connected = false;
        this._listeners.sendEvent("disconnected", this._host);
    }
    
    public int getLocalPort() {
        return this._socket.getLocalPort();
    }
    
    private void decodeLine(String trim) {
        final Vector vector = new Vector<String>();
        while (trim.length() != 0) {
            if (trim.startsWith(":") && vector.size() != 0) {
                vector.insertElementAt(trim.substring(1), vector.size());
                trim = "";
            }
            else {
                final int index = trim.indexOf(32);
                if (index == -1) {
                    vector.insertElementAt(StringParser.trim(trim), vector.size());
                    trim = "";
                }
                else {
                    final String trim2 = StringParser.trim(trim.substring(0, index));
                    trim = StringParser.trim(trim.substring(index + 1));
                    vector.insertElementAt(trim2, vector.size());
                }
            }
        }
        if (vector.size() == 0) {
            return;
        }
        String substring = "";
        if (vector.elementAt(0).startsWith(":")) {
            substring = vector.elementAt(0).substring(1);
            vector.removeElementAt(0);
        }
        if (vector.size() == 0) {
            return;
        }
        final String s = vector.elementAt(0);
        vector.removeElementAt(0);
        final String[] array = new String[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {
            this._listeners.sendEventAsync("replyReceived", substring, s, array);
        }
        else {
            this._listeners.sendEventAsync("messageReceived", substring, s, array);
        }
    }
    
    public void run() {
        try {
            this._socket = super._ircConfiguration.getSecurityProvider().getSocket(this._host, this._port);
            this._handler = new CodingHandler(super._ircConfiguration, new BufferedInputStream(this._socket.getInputStream()), new BufferedOutputStream(this._socket.getOutputStream()));
            this._connected = true;
            this._connecting = false;
            this._listeners.sendEventAsync("connected", this._host);
        }
        catch (Exception ex) {
            this._connecting = false;
            if (ex.getMessage() != null) {
                this._listeners.sendEventAsync("connectionFailed", ex.getMessage(), this._host);
            }
            else {
                this._listeners.sendEventAsync("connectionFailed", ex.getClass().getName(), this._host);
            }
            return;
        }
        int i = 0;
        while (i == 0) {
            try {
                final String read = this._handler.read();
                if (read == null) {
                    throw new Exception();
                }
                try {
                    if (read == null) {
                        continue;
                    }
                    this.decodeLine(read);
                }
                catch (Exception ex2) {
                    super._ircConfiguration.internalError("internal error", ex2, "plouf@pjirc.com");
                }
            }
            catch (Exception ex3) {
                i = 1;
            }
        }
        EventDispatcher.dispatchEventAsync(this, "disconnect", new Object[0]);
    }
    
    public void addServerProtocolListener(final ServerProtocolListener serverProtocolListener) {
        this._listeners.addListener(serverProtocolListener);
    }
    
    public void removeServerProtocolListener(final ServerProtocolListener serverProtocolListener) {
        this._listeners.removeListener(serverProtocolListener);
    }
    
    public void sendString(final String s) throws Exception {
        if (!this.connected()) {
            throw new Exception(this.getText(1285));
        }
        this._handler.write(s);
    }
    
    public void sendCommand(final String s, final String[] array) throws Exception {
        String string = s;
        for (int i = 0; i < array.length; ++i) {
            String s2 = string + " ";
            if (array[i].indexOf(32) != -1) {
                s2 += ":";
            }
            string = s2 + array[i];
        }
        this.sendString(string);
    }
}
