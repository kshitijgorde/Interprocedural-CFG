// 
// Decompiled by Procyon v0.5.30
// 

package org.litecoinpool.miner;

import java.io.IOException;
import java.security.AccessControlException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Observable;
import java.net.URL;
import java.net.SocketTimeoutException;
import java.net.HttpURLConnection;

final class f implements Runnable
{
    private /* synthetic */ e a;
    
    private f(final e a, final byte b) {
        this.a = a;
    }
    
    @Override
    public final void run() {
        while (this.a.k) {
            try {
                e.a(this.a, (HttpURLConnection)this.a.h.openConnection());
                this.a.i.setReadTimeout(1800000);
                e.a(this.a, new d(this.a.i, this.a.a, this.a.b));
                if (!this.a.k) {
                    break;
                }
                synchronized (this.a) {
                    this.a.setChanged();
                    this.a.notifyObservers(g.h);
                    this.a.setChanged();
                    this.a.notifyObservers(g.i);
                }
                // monitorexit(this.a)
            }
            catch (SocketTimeoutException ex) {}
            catch (Exception ex2) {
                if (!this.a.k) {
                    break;
                }
                this.a.setChanged();
                this.a.notifyObservers(g.f);
                try {
                    Thread.sleep(this.a.d);
                }
                catch (InterruptedException ex3) {}
            }
        }
        e.a(this.a, (URL)null);
        e.a(this.a, (HttpURLConnection)null);
    }
}
