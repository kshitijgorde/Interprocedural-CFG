// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.net.MalformedURLException;
import java.applet.AudioClip;
import java.net.URL;

class ao implements Runnable
{
    private final /* synthetic */ g a = a;
    private final /* synthetic */ URL b = b;
    
    public final void run() {
        this.a.be = new AudioClip[h.a.length];
        try {
            for (int i = 0; i < this.a.be.length; ++i) {
                this.a.be[i] = this.a.b(new URL(this.b, "Sounds/" + h.b[i]));
            }
        }
        catch (MalformedURLException ex) {
            this.a.be = null;
        }
    }
    
    private final void a(final g g) {
    }
}
