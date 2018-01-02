// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

public class bz implements Runnable
{
    private bh a;
    private g b;
    private Thread c;
    
    public final void run() {
        try {
            this.b.bd.waitForID(10);
        }
        catch (InterruptedException ex) {}
        if (!this.b.bd.isErrorID(10) && !this.a.b.isVisible()) {
            this.a.b.setVisible(true);
        }
    }
    
    public bz(final bh a, final g b) {
        this.a = a;
        this.b = b;
        (this.c = new Thread(this, "Worker")).start();
    }
}
