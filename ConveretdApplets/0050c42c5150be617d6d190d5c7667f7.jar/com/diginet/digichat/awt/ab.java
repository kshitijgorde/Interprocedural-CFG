// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.util.Vector;

public class ab implements Runnable
{
    private Thread a;
    private Vector b;
    
    public final void a(final Object o) {
        synchronized (this.b) {
            this.b.addElement(o);
        }
        // monitorexit(this.b)
    }
    
    public final void run() {
        try {
            while (true) {
                Thread.sleep(100L);
                synchronized (this.b) {
                    int i = 0;
                    while (i < this.b.size()) {
                        final aa aa = this.b.elementAt(i);
                        if (aa != null) {
                            aa.repaint();
                            ++i;
                        }
                        else {
                            this.b.removeElementAt(i);
                        }
                    }
                }
                // monitorexit(this.b)
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public ab() {
        this.a = new Thread(this);
        this.b = new Vector(10, 5);
        this.a.start();
    }
}
