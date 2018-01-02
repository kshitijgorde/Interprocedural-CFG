// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.NoSuchElementException;
import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;

final class bJ extends Thread
{
    private int q;
    private MediaTracker q;
    private final bI q;
    
    private bJ(final bI q, final byte b) {
        this.q = q;
        this.q = 1000;
        this.q = new MediaTracker(new e());
    }
    
    public final void run() {
        while (true) {
            try {
                while (true) {
                    if (this.q.w.q()) {
                        synchronized (this.q.w) {
                            this.q.w.wait();
                            continue;
                        }
                    }
                    final int q = this.q;
                    this.q.addImage(this.q(), q);
                    this.q.waitForID(q);
                    ++this.q;
                }
            }
            catch (InterruptedException ex) {
                continue;
            }
            break;
        }
    }
    
    private synchronized Image q() {
        try {
            return (Image)this.q.w.q();
        }
        catch (NoSuchElementException ex) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    bJ(final bI bi) {
        this(bi, (byte)0);
    }
}
