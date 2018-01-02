import java.awt.Event;
import java.awt.FileDialog;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class ZSFrame extends Frame
{
    FileDialog fd;
    boolean ran;
    int boxtype;
    Thread blockedthread;
    
    public ZSFrame() {
        this.ran = false;
        this.boxtype = 1;
        this.blockedthread = null;
    }
    
    public ZSFrame(final String title) {
        super(title);
        this.ran = false;
        this.boxtype = 1;
        this.blockedthread = null;
    }
    
    public ZSFrame(final String title, final int boxtype) {
        this(title);
        this.boxtype = boxtype;
    }
    
    public boolean handleEvent(final Event evt) {
        if (!this.ran) {
            (this.fd = new FileDialog(this, "Save game as...", this.boxtype)).show();
        }
        this.ran = true;
        if (this.blockedthread != null) {
            this.blockedthread.resume();
        }
        return super.handleEvent(evt);
    }
    
    public String getFile() {
        if (!this.ran) {
            (this.blockedthread = Thread.currentThread()).suspend();
        }
        return this.fd.getFile();
    }
    
    public String getDirectory() {
        if (!this.ran) {
            (this.blockedthread = Thread.currentThread()).suspend();
        }
        return this.fd.getDirectory();
    }
}
