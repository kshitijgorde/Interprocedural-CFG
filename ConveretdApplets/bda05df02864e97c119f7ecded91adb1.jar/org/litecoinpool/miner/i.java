// 
// Decompiled by Procyon v0.5.30
// 

package org.litecoinpool.miner;

import java.security.AccessControlException;
import java.util.concurrent.atomic.AtomicLong;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;
import java.io.IOException;

final class i implements Runnable
{
    private d a;
    private int b;
    private /* synthetic */ e c;
    
    public i(final e c, final d a, final int b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public final void run() {
        try {
            final boolean a = this.a.a(this.b);
            this.c.setChanged();
            this.c.notifyObservers(a ? g.j : g.k);
        }
        catch (IOException ex) {}
    }
}
