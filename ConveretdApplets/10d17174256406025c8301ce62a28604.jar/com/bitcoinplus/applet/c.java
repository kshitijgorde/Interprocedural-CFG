// 
// Decompiled by Procyon v0.5.30
// 

package com.bitcoinplus.applet;

public final class c implements Runnable
{
    private int c;
    private final int d;
    int a;
    private e e;
    private a f;
    boolean b;
    
    public c(int n, final e e, final a f) {
        this.c = 1000;
        n = n;
        this.d = -1 << 32 - n;
        this.e = e;
        this.f = f;
    }
    
    public final void run() {
        while (this.b) {
            final com.bitcoinplus.applet.b.a a = this.e.a();
            new StringBuilder().append("BitcoinWorker got work midstate: ").append(a.b()).append(", start: ").append(a.e()).append(", end: ").append(a.f()).toString();
            long n = a.e();
            long currentTimeMillis;
            while ((currentTimeMillis = System.currentTimeMillis()) - a.j() <= 20000L && this.b) {
                this.a(a, (int)n, (int)Math.min(a.f() - n, this.c));
                final long currentTimeMillis2;
                if ((currentTimeMillis2 = System.currentTimeMillis()) - currentTimeMillis < 850L) {
                    this.c *= (int)1.1;
                }
                else if (currentTimeMillis2 - currentTimeMillis > 1150L) {
                    this.c *= (int)0.9;
                }
                this.c = Math.max(this.c, 100);
                if (this.c < 0) {
                    this.c = Integer.MAX_VALUE;
                }
                if ((n += this.c) >= a.f()) {
                    break;
                }
            }
        }
    }
    
    private void a(final com.bitcoinplus.applet.b.a a, final int n, final int n2) {
        final int[] a2 = com.bitcoinplus.applet.b.a.a(new int[16], a.c());
        final int[] a3 = com.bitcoinplus.applet.b.a.a(com.bitcoinplus.applet.b.a.a(new int[16], a.a()), a.b());
        for (int i = n; i < n + n2; ++i) {
            a2[3] = i;
            final int[] array = new int[a3.length];
            System.arraycopy(a3, 0, array, 0, a3.length);
            if ((com.bitcoinplus.applet.a.a.a(com.bitcoinplus.applet.a.a.a(), com.bitcoinplus.applet.a.a.a(array, a2))[7] & this.d) == 0x0) {
                this.f.a(a.d() + com.bitcoinplus.applet.b.a.a(a2), a);
            }
            ++this.a;
        }
    }
}
