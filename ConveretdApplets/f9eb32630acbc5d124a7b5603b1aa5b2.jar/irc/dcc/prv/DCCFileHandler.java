// 
// Decompiled by Procyon v0.5.30
// 

package irc.dcc.prv;

import irc.Source;
import irc.ServerListener;
import java.util.Vector;
import java.util.Enumeration;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import irc.IRCConfiguration;
import irc.ListenerGroup;
import irc.dcc.DCCFile;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import irc.Server;
import irc.IRCObject;

public class DCCFileHandler extends IRCObject implements Server, Runnable
{
    private Socket _socket;
    private ServerSocket _serverSocket;
    private Thread _thread;
    private OutputStream _os;
    private InputStream _is;
    private DCCFile _file;
    private int _action;
    private int _size;
    private boolean _listening;
    private boolean _connected;
    private ListenerGroup _listeners;
    
    public DCCFileHandler(final IRCConfiguration ircConfiguration, final String s, final File file) {
        super(ircConfiguration);
        this._action = 0;
        this._size = 0;
        this._connected = false;
        this._file = new DCCFile(ircConfiguration, file, this);
        this._listeners = new ListenerGroup();
    }
    
    public void release() {
        this.cleanup();
        this._file = null;
        super.release();
    }
    
    public void receive(String string, final String s, final String s2) {
        this._size = new Integer(s2);
        this._file.prepareReceive(this._size);
        this._serverSocket = null;
        this._action = 1;
        final long longValue = new Long(string);
        string = (int)(longValue >> 24 & 0xFFL) + "." + (int)(longValue >> 16 & 0xFFL) + "." + (int)(longValue >> 8 & 0xFFL) + "." + (int)(longValue & 0xFFL);
        try {
            this._socket = super._ircConfiguration.getSecurityProvider().getSocket(string, new Integer(s));
            this._is = new BufferedInputStream(this._socket.getInputStream());
            this._os = new BufferedOutputStream(this._socket.getOutputStream());
            (this._thread = new Thread(this, "DCCFile thread")).start();
        }
        catch (Exception ex) {
            super._ircConfiguration.internalError("receive failure", ex, "plouf@pjirc.com");
        }
    }
    
    public String send() {
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
            final long n5 = (n << 24) + (n2 << 16) + (n3 << 8) + n4;
            this._file.prepareSend();
            final int size = this._file.getSize();
            final String string = "" + n5;
            this._listening = false;
            (this._thread = new Thread(this, "DCCFile thread")).start();
            while (!this._listening) {
                Thread.yield();
            }
            return string + " " + localPort + " " + size;
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    private void writeConf(final OutputStream outputStream, final int n) throws IOException {
        outputStream.write(n >> 24 & 0xFF);
        outputStream.write(n >> 16 & 0xFF);
        outputStream.write(n >> 8 & 0xFF);
        outputStream.write(n & 0xFF);
        outputStream.flush();
    }
    
    private int readConf(final InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read < 0) {
            read += 256;
        }
        int read2 = inputStream.read();
        if (read2 < 0) {
            read2 += 256;
        }
        int read3 = inputStream.read();
        if (read3 < 0) {
            read3 += 256;
        }
        int read4 = inputStream.read();
        if (read4 < 0) {
            read4 += 256;
        }
        return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
    }
    
    private void connected() {
        this._connected = true;
        this._listeners.sendEventAsync("serverConnected", this);
    }
    
    private void disconnected() {
        this._connected = false;
        this._listeners.sendEventAsync("serverDisconnected", this);
    }
    
    public void run() {
        final byte[] array = new byte[4096];
        if (this._action == 1) {
            try {
                this.connected();
                int n = 0;
                while (this._size - n > 0) {
                    final int read = this._is.read(array, 0, array.length);
                    if (read == -1) {
                        throw new Exception(this.getText(267));
                    }
                    n += read;
                    this._file.bytesReceived(array, 0, read);
                    Thread.yield();
                    this.writeConf(this._os, n);
                }
                this.writeConf(this._os, this._size);
                this._file.fileReceived();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this._file.fileReceiveFailed();
            }
            this.disconnected();
            this.cleanup();
        }
        else if (this._action == 2) {
            this._listening = true;
            try {
                this._serverSocket.setSoTimeout(30000);
                this._socket = this._serverSocket.accept();
                this._os = new BufferedOutputStream(this._socket.getOutputStream());
                this._is = new BufferedInputStream(this._socket.getInputStream());
                this.connected();
                int i;
                final int n2 = i = this._file.getSize();
                int j = 0;
                while (i > 0) {
                    final int bytes = this._file.readBytes(array, 0, array.length);
                    if (bytes < 0) {
                        throw new Exception(this.getText(267));
                    }
                    this._os.write(array, 0, bytes);
                    i -= bytes;
                    if (this._is.available() <= 0) {
                        continue;
                    }
                    j = this.readConf(this._is);
                }
                this._os.flush();
                while (j != n2) {
                    j = this.readConf(this._is);
                }
                this._os.close();
                this._file.fileSent();
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                this._file.fileSentFailed();
            }
            this.disconnected();
            this.cleanup();
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
            this._is.close();
            this._os.close();
        }
        catch (Exception ex) {
            super._ircConfiguration.internalError("cleanup failure", ex, "plouf@pjirc.com");
        }
    }
    
    public void close() {
        this.cleanup();
    }
    
    public void say(final String s, final String s2) {
    }
    
    public void execute(final String s) {
    }
    
    public void sendStatusMessage(final String s) {
    }
    
    public String getNick() {
        return "";
    }
    
    public String getUserName() {
        return "";
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
        final Vector<DCCFile> vector = new Vector<DCCFile>();
        if (this._file != null) {
            vector.insertElementAt(this._file, vector.size());
        }
        return vector.elements();
    }
    
    public void enumerateSourcesAsCreated(final ServerListener serverListener) {
        if (this._file != null) {
            serverListener.sourceCreated(this._file, this, new Boolean(true));
        }
    }
    
    public void enumerateSourcesAsRemoved(final ServerListener serverListener) {
        if (this._file != null) {
            serverListener.sourceRemoved(this._file, this);
        }
    }
    
    public void setDefaultSource(final Source source) {
    }
    
    public void addServerListener(final ServerListener serverListener) {
        this._listeners.addListener(serverListener);
    }
    
    public void removeServerListener(final ServerListener serverListener) {
        this._listeners.removeListener(serverListener);
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
        this._listeners.sendEvent("sourceRemoved", this._file, this);
        this._listeners.sendEvent("serverLeft", this);
        this._file.release();
    }
    
    public String getServerName() {
        return this.getNick();
    }
}
