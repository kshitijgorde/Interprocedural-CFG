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

public final class e extends Observable implements Runnable
{
    private URL a;
    private String b;
    private long c;
    private long d;
    private int e;
    private double f;
    private volatile d g;
    private URL h;
    private HttpURLConnection i;
    private AtomicLong j;
    private volatile boolean k;
    
    public e(final URL a, final String b, final long n, final long n2, final int e, final double n3) {
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = new AtomicLong(0L);
        this.k = false;
        this.a = a;
        this.b = b;
        this.c = 5000L;
        this.d = 30000L;
        if (e < 0) {
            throw new IllegalArgumentException();
        }
        this.e = e;
        if (n3 <= 0.0 || n3 > 1.0) {
            throw new IllegalArgumentException();
        }
        this.f = 1.0 / n3 - 1.0;
    }
    
    public final long a() {
        return this.j.get();
    }
    
    public final synchronized void b() {
        this.k = false;
        this.notifyAll();
    }
    
    @Override
    public final void run() {
        this.k = true;
        final Thread[] array;
        synchronized (this) {
            array = new Thread[1 + this.e];
            for (int i = 0; i < this.e; ++i) {
                (array[i + 1] = new Thread(new h(this, i))).start();
            }
            do {
                try {
                    if (this.g == null || this.g.a() >= 60000L || this.h == null) {
                        this.g = this.c();
                        if (this.h == null) {
                            try {
                                final URL b = this.g.b();
                                this.h = b;
                                if (b != null) {
                                    (array[0] = new Thread(new f(this))).start();
                                    this.setChanged();
                                    this.notifyObservers(org.litecoinpool.miner.g.g);
                                }
                            }
                            catch (Exception ex) {}
                        }
                        this.setChanged();
                        this.notifyObservers(org.litecoinpool.miner.g.i);
                    }
                    if (!this.k) {
                        break;
                    }
                    this.wait(Math.min(this.c, Math.max(1L, 60000L - this.g.a())));
                }
                catch (InterruptedException ex2) {}
                catch (NullPointerException ex3) {}
            } while (this.k);
            this.k = false;
        }
        if (this.i != null) {
            this.i.disconnect();
        }
        try {
            Thread[] array2;
            for (int length = (array2 = array).length, j = 0; j < length; ++j) {
                final Thread thread;
                if ((thread = array2[j]) != null) {
                    thread.join();
                }
            }
        }
        catch (InterruptedException ex4) {}
        this.g = null;
        this.setChanged();
        this.notifyObservers(org.litecoinpool.miner.g.l);
    }
    
    private synchronized d c() {
        while (this.k) {
            try {
                return new d(this.a, this.b, (byte)0);
            }
            catch (Exception ex) {
                if (!this.k) {
                    break;
                }
                this.setChanged();
                if (ex instanceof IllegalArgumentException) {
                    this.notifyObservers(org.litecoinpool.miner.g.d);
                    this.b();
                    break;
                }
                if (ex instanceof AccessControlException) {
                    this.notifyObservers(org.litecoinpool.miner.g.b);
                    this.b();
                    break;
                }
                if (ex instanceof IOException) {
                    this.notifyObservers(org.litecoinpool.miner.g.c);
                }
                else {
                    this.notifyObservers(org.litecoinpool.miner.g.e);
                }
                try {
                    this.g = null;
                    this.wait(this.d);
                }
                catch (InterruptedException ex2) {}
            }
        }
        return null;
    }
}
