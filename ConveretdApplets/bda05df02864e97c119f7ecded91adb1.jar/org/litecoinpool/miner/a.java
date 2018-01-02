// 
// Decompiled by Procyon v0.5.30
// 

package org.litecoinpool.miner;

import java.util.Observable;
import java.net.MalformedURLException;
import java.security.PrivilegedAction;
import java.security.AccessController;
import java.net.URL;
import java.util.Observer;
import java.applet.Applet;

final class a extends Thread
{
    private /* synthetic */ EmbeddableMiner a;
    private final /* synthetic */ int b;
    
    a(final EmbeddableMiner a, final int b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public final void run() {
        try {
            Thread.sleep(this.b * 1000L);
        }
        catch (InterruptedException ex) {}
        this.a.e.b();
    }
}
