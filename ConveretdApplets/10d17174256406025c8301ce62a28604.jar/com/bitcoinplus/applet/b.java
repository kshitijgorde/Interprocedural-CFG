// 
// Decompiled by Procyon v0.5.30
// 

package com.bitcoinplus.applet;

import java.util.Iterator;
import com.bitcoinplus.applet.d.a;
import java.util.List;

public final class b implements Runnable
{
    private List b;
    private long c;
    int a;
    
    public b(final List b) {
        this.b = b;
        new Thread(this).start();
    }
    
    public final void run() {
        this.c = System.currentTimeMillis();
        while (true) {
            com.bitcoinplus.applet.d.a.a(1000L);
            int n = 0;
            for (final c c : this.b) {
                final int n2 = n;
                final c c2;
                final int a = (c2 = c).a;
                c2.a = 0;
                n = n2 + a;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            this.a = (int)(n / ((currentTimeMillis - this.c) / 1000.0));
            new StringBuilder().append("hashes per second: ").append(this.a).toString();
            this.c = currentTimeMillis;
        }
    }
}
