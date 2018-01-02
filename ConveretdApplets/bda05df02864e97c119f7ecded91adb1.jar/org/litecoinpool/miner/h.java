// 
// Decompiled by Procyon v0.5.30
// 

package org.litecoinpool.miner;

import java.io.IOException;
import java.security.AccessControlException;
import java.util.concurrent.atomic.AtomicLong;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;
import java.security.GeneralSecurityException;
import java.util.concurrent.locks.LockSupport;

final class h implements Runnable
{
    private int a;
    private int b;
    private /* synthetic */ e c;
    
    public h(final e c, final int a) {
        this.c = c;
        this.a = a;
        this.b = 1;
        while (this.b < c.e) {
            this.b <<= 1;
        }
    }
    
    @Override
    public final void run() {
        try {
            final c c = new c();
            int a = this.a;
            long n = System.nanoTime();
            while (this.c.k) {
                try {
                    if (this.c.g.a(a, c)) {
                        new Thread(new i(this.c, this.c.g, a)).start();
                        if (this.c.h == null) {
                            synchronized (this.c) {
                                e.a(this.c, (d)null);
                                this.c.notify();
                            }
                            // monitorexit(this.c)
                        }
                    }
                    a += this.b;
                    this.c.j.incrementAndGet();
                    final long n2;
                    if (this.c.f <= 0.0 || (n2 = System.nanoTime() - n) <= 100000000L) {
                        continue;
                    }
                    LockSupport.parkNanos(Math.max(0L, (long)(this.c.f * n2)));
                    n = System.nanoTime();
                }
                catch (NullPointerException ex) {
                    try {
                        Thread.sleep(1L);
                    }
                    catch (InterruptedException ex2) {}
                }
            }
        }
        catch (GeneralSecurityException ex3) {
            this.c.setChanged();
            this.c.notifyObservers(g.a);
            this.c.b();
        }
    }
}
