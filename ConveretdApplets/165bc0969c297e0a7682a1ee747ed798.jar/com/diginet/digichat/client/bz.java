// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

public class bz implements Runnable
{
    private bq a;
    private h b;
    private Thread c;
    
    public void run() {
        try {
            this.b.a0.waitForID(10);
        }
        catch (InterruptedException ex) {}
        if (!this.b.a0.isErrorID(10) && !this.a.b.isVisible()) {
            this.a.b.setVisible(true);
        }
    }
    
    public bz(final bq a, final h b) {
        this.a = a;
        this.b = b;
        (this.c = new Thread(this, "Worker")).start();
    }
}
