// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.util;

import org.xmodel.IDispatcher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Aggregator
{
    private final Runnable D;
    private boolean C;
    private Thread B;
    private int E;
    private int F;
    private List<_A> A;
    
    public Aggregator(final long n) {
        this.D = new Runnable() {
            @Override
            public void run() {
                while (!Aggregator.this.C) {
                    synchronized (this) {
                        for (final _A a : Aggregator.this.A) {
                            if (a.C) {
                                a.C = false;
                                a.B = true;
                            }
                        }
                    }
                    for (final _A a2 : Aggregator.this.A) {
                        if (Aggregator.this.C) {
                            break;
                        }
                        if (!a2.B) {
                            continue;
                        }
                        a2.B = false;
                        a2.D.execute(a2.E);
                    }
                    try {
                        Thread.sleep(Aggregator.this.E, Aggregator.this.F);
                    }
                    catch (InterruptedException ex) {}
                }
            }
        };
        this.E = (int)(n % 1000000L);
        this.F = (int)(n / 1000000L);
        this.A = new ArrayList<_A>();
    }
    
    public int add(final IDispatcher d, final Runnable e) {
        final int size = this.A.size();
        final _A a = new _A((_A)null);
        a.E = e;
        a.D = d;
        this.A.add(a);
        return size;
    }
    
    public void remove(final Runnable runnable) {
        for (int i = 0; i < this.A.size(); ++i) {
            if (this.A.get(i).E == runnable) {
                this.A.remove(i);
                return;
            }
        }
    }
    
    public void start() {
        this.C = false;
        (this.B = new Thread(this.D, "Aggregator")).setDaemon(true);
        this.B.start();
    }
    
    public void stop() {
        if (this.B != null) {
            this.C = true;
            this.B.interrupt();
        }
    }
    
    public synchronized void dispatch(final int n) {
        this.A.get(n).C = true;
    }
    
    private class _A
    {
        Runnable E;
        IDispatcher D;
        boolean C;
        boolean B;
    }
}
