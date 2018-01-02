// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.util.TimeZone;
import java.util.Map;
import COM.NextBus.HttpMapClient.ConnectionException;
import COM.NextBus.HttpMapClient.ResponseComponent;

final class al implements Runnable
{
    private /* synthetic */ F a;
    
    al(final F a) {
        this.a = a;
    }
    
    public final void run() {
        while (this.a.m.c() != null) {
            try {
                if (this.a.l == F.f || this.a.l == F.h || this.a.l == F.b) {
                    int n = this.a.k / this.a.f();
                    final long n2 = this.a.j + n * F.d;
                    if (this.a.l == F.f && n < 20) {
                        n = 20;
                    }
                    if (n2 > System.currentTimeMillis()) {
                        this.a.l = F.i;
                    }
                    else {
                        final Map a;
                        if ((a = this.a.m.e.a(n2, n)) != null) {
                            this.a.j = n2;
                            if (this.a.l == F.f || this.a.l == F.h) {
                                this.a.l = F.b;
                            }
                            this.a.m.c.a(a);
                        }
                        else if (this.a.l == F.b) {
                            this.a.l = F.h;
                        }
                    }
                }
            }
            catch (ConnectionException ex2) {
                final ConnectionException ex = ex2;
                if (ex2.a() == ResponseComponent.h) {
                    System.out.println("Replay server buffering.");
                }
                else {
                    System.err.println("ReplayManager caught exception: " + ex);
                }
            }
            try {
                Thread.sleep(this.a.m.f() * 1000 / this.a.f());
            }
            catch (InterruptedException ex3) {}
        }
    }
}
