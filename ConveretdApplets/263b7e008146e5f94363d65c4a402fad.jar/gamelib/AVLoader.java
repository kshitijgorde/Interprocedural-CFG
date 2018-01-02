// 
// Decompiled by Procyon v0.5.30
// 

package gamelib;

import java.awt.Component;
import java.awt.MediaTracker;
import java.util.Vector;
import java.applet.Applet;

class AVLoader implements Runnable
{
    Applet applet;
    Vector entries;
    MediaTracker tracker;
    private Thread loadt;
    
    AVLoader(final Applet applet) {
        this.applet = applet;
        this.entries = new Vector();
        this.tracker = new MediaTracker(this.applet);
        (this.loadt = new Thread(this)).setDaemon(true);
        this.loadt.start();
    }
    
    public void run() {
        int n = 0;
        while (true) {
            if (this.entries.size() > n) {
                final AVEntry avEntry = this.entries.elementAt(n++);
                synchronized (avEntry) {
                    avEntry.loadData();
                    // monitorexit(avEntry)
                    continue;
                }
            }
            synchronized (this) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {}
            }
        }
    }
}
