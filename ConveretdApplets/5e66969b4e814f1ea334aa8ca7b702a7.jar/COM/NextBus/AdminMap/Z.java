// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import COM.NextBus.HttpMapClient.Update;
import COM.NextBus.Predictor2Comm.TitleInfo;
import COM.NextBus.HttpMapClient.e;
import java.util.Hashtable;
import COM.NextBus.util.c;
import java.util.Iterator;
import COM.NextBus.HttpMapClient.l;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import COM.NextBus.HttpMapClient.b;
import COM.NextBus.HttpMapClient.a;
import java.util.Map;
import COM.NextBus.HttpMapClient.ConnectionException;
import COM.NextBus.HttpMapClient.ResponseComponent;

final class Z implements Runnable
{
    private /* synthetic */ af a;
    
    Z(final af a) {
        this.a = a;
    }
    
    public final void run() {
        int n = 0;
        long n2 = 0L;
        while (this.a.a.c() != null) {
            try {
                final long currentTimeMillis = System.currentTimeMillis();
                final long n3 = n2 + this.a.a.f() * 1000;
                final boolean c = this.a.g;
                if (this.a.g) {
                    this.a.a.a("Doing immediate update");
                    af.a(this.a, false);
                }
                else {
                    if (currentTimeMillis <= n3) {
                        try {
                            Thread.sleep(100L);
                        }
                        catch (InterruptedException ex2) {}
                        continue;
                    }
                    this.a.a.a("Doing scheduled update " + (currentTimeMillis - n2));
                }
                final Map b = this.a.c.b();
                final long currentTimeMillis2 = System.currentTimeMillis();
                this.a.a.a("Update finished in " + (currentTimeMillis2 - currentTimeMillis) + "ms");
                boolean a;
                if (b == null) {
                    a = false;
                }
                else {
                    a = this.a.a(b);
                    if (n2 == 0L || c) {
                        aj.s();
                    }
                }
                n2 = currentTimeMillis2;
                n = 0;
                this.a.a.j.a();
                if (a) {
                    throw new ConnectionException(ResponseComponent.e, "Configuration data has changed");
                }
                continue;
            }
            catch (ConnectionException ex) {
                System.err.println("Caught " + ex + " with error code " + ex.a());
                if (ex.a() == ResponseComponent.e && this.a.b.a()) {
                    af.a(this.a);
                    return;
                }
                if (++n <= 3) {
                    continue;
                }
                this.a.a.j.a("Problem getting update from server.  Reconnecting.");
                if (this.a.b.a()) {
                    af.a(this.a);
                    return;
                }
                continue;
            }
        }
    }
}
