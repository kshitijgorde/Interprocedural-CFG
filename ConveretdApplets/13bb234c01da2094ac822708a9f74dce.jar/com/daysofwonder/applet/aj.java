// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import com.daysofwonder.util.t;

public class aj extends Thread
{
    final /* synthetic */ au a;
    
    protected aj(final au a) {
        this.a = a;
    }
    
    public void run() {
        while (!this.isInterrupted()) {
            try {
                t.a("sleeping");
                Thread.sleep(15000L);
                t.a("not sleeping anymore");
            }
            catch (InterruptedException ex) {
                return;
            }
            t.a("<<<<PING>>>>");
            this.a.c();
            t.a("<<<<END PING>>>>");
        }
    }
}
