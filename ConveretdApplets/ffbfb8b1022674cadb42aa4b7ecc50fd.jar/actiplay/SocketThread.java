// 
// Decompiled by Procyon v0.5.30
// 

package actiplay;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketThread extends Thread
{
    private final Socket _sock;
    private final int _size;
    private final String _cmd;
    private byte[] _partFile;
    private float _percent;
    private int _state;
    
    public SocketThread(final Socket socket, final String command, final int size) {
        ActiSkuInstaller.println("DEBUG : SocketThread : SocketThread( socket , command_get , " + size + " ) ");
        this._sock = socket;
        this._cmd = command;
        this._size = size;
        this._percent = 0.0f;
        this._partFile = new byte[size];
        this._state = 300;
    }
    
    public void run() {
        try {
            ActiSkuInstaller.println("DEBUG : SocketThread : run() ");
            OutputStream oStream = null;
            InputStream iStream = null;
            oStream = this._sock.getOutputStream();
            iStream = this._sock.getInputStream();
            oStream.write(this._cmd.getBytes());
            String first = "";
            final byte[] ch = { 0 };
            for (boolean firstLine = true; firstLine && iStream.read(ch, 0, 1) >= 0; firstLine = false) {
                final String s = new String(ch, 0, 1);
                first += s;
                if (s.equals("\n")) {}
            }
            if (first.indexOf("HTTP/1.1 206") < 0) {
                ActiSkuInstaller.println("DEBUG : SocketThread :  Warning bad http response for get range");
                this._state = 302;
                return;
            }
            boolean breakWhile;
            for (breakWhile = false; !breakWhile && iStream.read(ch, 0, 1) >= 0; breakWhile = true) {
                final String s2 = new String(ch, 0, 1);
                first += s2;
                if (first.charAt(first.length() - 1) == '\n' && first.charAt(first.length() - 2) == '\r' && first.charAt(first.length() - 3) == '\n' && first.charAt(first.length() - 4) == '\r') {}
            }
            if (breakWhile) {
                int counter;
                byte[] c;
                int readed;
                int i;
                for (counter = 0, c = new byte[4096], readed = 0; (readed = iStream.read(c, 0, 4096)) >= 0 && counter < this._size; counter += readed, this._percent = counter / this._size * 100.0f) {
                    for (i = 0; i < readed; ++i) {
                        if (counter + i < this._partFile.length) {
                            this._partFile[counter + i] = c[i];
                        }
                    }
                }
                if (counter < this._size) {
                    ActiSkuInstaller.println("DEBUG : SocketThread :  Warning archive corrompu : " + readed + " Vs " + this._size);
                    this._state = 303;
                    return;
                }
            }
            oStream.close();
            iStream.close();
            this._state = 301;
        }
        catch (IOException ex) {
            this._state = 304;
        }
    }
    
    public int state() {
        return this._state;
    }
    
    public byte[] getPartFile() {
        return this._partFile;
    }
    
    public int getSize() {
        return this._size;
    }
    
    public float getPercent() {
        return this._percent;
    }
    
    public class SOCK_STATE
    {
        public static final int UNKNOW = -1;
        public static final int WAITING = 300;
        public static final int SUCCESS = 301;
        public static final int FAILED_SOCK_HTTP_REQUEST = 302;
        public static final int FAILED_SOCK_BAD_ARCHIVE = 303;
        public static final int FAILED_SOCK_DOWN_IO = 304;
    }
}
