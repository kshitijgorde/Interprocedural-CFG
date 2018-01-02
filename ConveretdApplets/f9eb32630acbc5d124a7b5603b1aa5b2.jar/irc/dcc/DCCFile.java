// 
// Decompiled by Procyon v0.5.30
// 

package irc.dcc;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import irc.Server;
import irc.IRCConfiguration;
import irc.dcc.prv.DCCFileHandler;
import irc.ListenerGroup;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import irc.Source;

public class DCCFile extends Source
{
    private OutputStream _os;
    private InputStream _is;
    private File _file;
    private ListenerGroup _listeners;
    private boolean _down;
    private int _size;
    private int _count;
    private DCCFileHandler _handler;
    
    public DCCFile(final IRCConfiguration ircConfiguration, final File file, final DCCFileHandler handler) {
        super(ircConfiguration, handler);
        this._down = false;
        this._listeners = new ListenerGroup();
        this._handler = handler;
        this._count = 0;
        this._file = file;
    }
    
    public void addDCCFileListener(final DCCFileListener dccFileListener) {
        this._listeners.addListener(dccFileListener);
    }
    
    public void removeDCCFileListener(final DCCFileListener dccFileListener) {
        this._listeners.removeListener(dccFileListener);
    }
    
    public void prepareSend() {
        try {
            this._size = super._ircConfiguration.getSecurityProvider().getFileSize(this._file);
            this._is = new BufferedInputStream(super._ircConfiguration.getSecurityProvider().getFileInputStream(this._file));
            this._down = false;
        }
        catch (Exception ex) {
            super._ircConfiguration.internalError("prepareSend failure", ex, "plouf@pjirc.com");
        }
    }
    
    public int readBytes(final byte[] array, final int n, final int n2) throws IOException {
        final int read = this._is.read(array, n, n2);
        if (read >= 0) {
            this._count += read;
            this._listeners.sendEventAsync("transmitted", new Integer(this._count), this);
        }
        return read;
    }
    
    public int getSize() {
        return this._size;
    }
    
    public boolean isUploading() {
        return !this.isDownloading();
    }
    
    public boolean isDownloading() {
        return this._down;
    }
    
    public void fileSent() {
        try {
            this._listeners.sendEventAsync("finished", this);
            this._is.close();
        }
        catch (Exception ex) {
            super._ircConfiguration.internalError("fileSent failure", ex, "plouf@pjirc.com");
        }
    }
    
    public void fileSentFailed() {
        try {
            this._listeners.sendEventAsync("failed", this);
            this._is.close();
        }
        catch (Exception ex) {
            super._ircConfiguration.internalError("fileSentFailed failure", ex, "plouf@pjirc.com");
        }
    }
    
    public void prepareReceive(final int size) {
        this._down = true;
        this._size = size;
        try {
            this._os = new BufferedOutputStream(super._ircConfiguration.getSecurityProvider().getFileOutputStream(this._file));
        }
        catch (Exception ex) {
            this._os = null;
        }
    }
    
    public void bytesReceived(final byte[] array, final int n, final int n2) throws IOException {
        this._count += n2;
        this._os.write(array, n, n2);
        this._listeners.sendEventAsync("transmitted", new Integer(this._count), this);
    }
    
    public void fileReceived() {
        try {
            this._listeners.sendEventAsync("finished", this);
            this._os.close();
        }
        catch (Exception ex) {
            super._ircConfiguration.internalError("fileReceived failure", ex, "plouf@pjirc.com");
        }
    }
    
    public void fileReceiveFailed() {
        try {
            this._listeners.sendEventAsync("failed", this);
            this._os.close();
        }
        catch (Exception ex) {
            super._ircConfiguration.internalError("fileReceiveFailed failure", ex, "plouf@pjirc.com");
        }
    }
    
    public String getName() {
        return this._file.getName();
    }
    
    public void leave() {
        this._handler.close();
        this._handler.leave();
    }
    
    public boolean talkable() {
        return false;
    }
    
    public String getType() {
        return "DCCFile";
    }
}
