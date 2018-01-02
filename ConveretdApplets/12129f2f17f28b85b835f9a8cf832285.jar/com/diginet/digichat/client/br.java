// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

public class br implements Runnable
{
    private be a;
    private h b;
    private Thread c;
    
    public void run() {
        try {
            this.b.ay.waitForID(10);
        }
        catch (InterruptedException ex) {}
        if (!this.b.ay.isErrorID(10) && !this.a.b.isVisible()) {
            this.a.b.setVisible(true);
        }
    }
    
    public br(final be a, final h b) {
        this.a = a;
        this.b = b;
        (this.c = new Thread(this, "Worker")).start();
    }
}
