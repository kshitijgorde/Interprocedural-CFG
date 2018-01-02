// 
// Decompiled by Procyon v0.5.30
// 

package gamelib;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

public class ActionBuffer extends Buffer implements Runnable
{
    protected Vector actors;
    private Thread animt;
    private volatile boolean stopped;
    
    public ActionBuffer() {
        this.actors = new Vector();
    }
    
    public void run() {
        while (!this.stopped) {
            final long currentTimeMillis = System.currentTimeMillis();
            for (int i = this.actors.size() - 1; i >= 0; --i) {
                ((OffComponent)this.actors.elementAt(i)).go();
            }
            final Rectangle updateContents = this.updateContents();
            if (updateContents != null) {
                final Graphics graphics = this.getGraphics();
                graphics.setClip(updateContents.x + super.xofs, updateContents.y + super.yofs, updateContents.width, updateContents.height);
                this.paint(graphics);
                graphics.dispose();
            }
            long n = 40L - (System.currentTimeMillis() - currentTimeMillis);
            if (n < 10L) {
                n = 10L;
            }
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized void start() {
        if (this.animt == null) {
            this.animt = new Thread(this);
            this.stopped = false;
            this.animt.start();
        }
    }
    
    public synchronized void stop() {
        this.stopped = true;
        if (this.animt != null) {
            try {
                this.animt.join(500L);
            }
            catch (InterruptedException ex) {}
            this.animt = null;
        }
    }
    
    public void traceComponent(final OffComponent offComponent, final Insets insets) {
    }
}
