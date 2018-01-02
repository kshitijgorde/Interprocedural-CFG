// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

final class J extends Thread
{
    private boolean c;
    av[] a;
    int b;
    
    J(int i) {
        super("SocketTimeout");
        this.c = true;
        try {
            this.setDaemon(true);
        }
        catch (SecurityException ex) {}
        this.setPriority(10);
        this.a = new av[60];
        av av;
        av av2;
        av av3;
        for (i = 0; i < 60; ++i) {
            this.a[i] = new av(this, null);
            av = this.a[i];
            av2 = this.a[i];
            av3 = this.a[i];
            av2.f = av3;
            av.e = av3;
        }
        this.b = 0;
    }
    
    public final av a(final bC bc) {
        final av av = new av(this, bc);
        synchronized (this.a) {
            av.e = this.a[this.b];
            av.f = this.a[this.b].f;
            av.f.e = av;
            av.e.f = av;
        }
        return;
    }
    
    public final void run() {
        av e = null;
        while (this.c) {
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
            synchronized (this.a) {
                for (av av = this.a[this.b].e; av != this.a[this.b]; av = av.e) {
                    av.a = false;
                }
                ++this.b;
                if (this.b >= this.a.length) {
                    this.b = 0;
                }
                for (av av2 = this.a[this.b].e; av2 != this.a[this.b]; av2 = av2.e) {
                    if (av2.c && !av2.b) {
                        final av f = av2.f;
                        av2.c();
                        av2.e = e;
                        e = av2;
                        av2 = f;
                    }
                }
            }
            while (e != null) {
                e.d.b((bd)null);
                e = e.e;
            }
        }
    }
    
    public final void a() {
        this.c = false;
    }
}
