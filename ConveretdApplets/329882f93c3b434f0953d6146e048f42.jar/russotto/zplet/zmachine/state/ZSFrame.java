// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.zmachine.state;

import java.awt.Event;
import java.awt.FileDialog;
import java.awt.Frame;

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
    
    public ZSFrame(final String s) {
        super(s);
        this.ran = false;
        this.boxtype = 1;
        this.blockedthread = null;
    }
    
    public ZSFrame(final String s, final int boxtype) {
        this(s);
        this.boxtype = boxtype;
    }
    
    public boolean handleEvent(final Event event) {
        if (!this.ran) {
            (this.fd = new FileDialog(this, "Save game as...", this.boxtype)).show();
        }
        this.ran = true;
        if (this.blockedthread != null) {
            this.blockedthread.resume();
        }
        return super.handleEvent(event);
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
