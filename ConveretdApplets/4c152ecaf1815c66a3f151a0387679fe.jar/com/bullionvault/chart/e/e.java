// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.e;

import com.bullionvault.chart.a.s;
import java.text.DateFormat;
import com.bullionvault.chart.d.d;
import com.bullionvault.chart.resources.Resources;
import com.bullionvault.chart.c.k;
import com.bullionvault.chart.d.c;
import com.bullionvault.chart.a.t;

public final class e implements Runnable
{
    private h a;
    private t b;
    private boolean c;
    private b d;
    private String e;
    private String f;
    private c g;
    private boolean h;
    
    public e(final h a, final t b, final String f) {
        this.c = true;
        this.g = null;
        this.h = true;
        this.a = a;
        this.b = b;
        this.f = f;
        this.e = com.bullionvault.chart.g.b.a.a();
        this.d = new b(this.e, f);
    }
    
    public final void a() {
        this.c = true;
        k.a(this, "RealTimeReader");
        com.bullionvault.chart.c.h.e("RealTimeReader startThread() - started");
    }
    
    public final void b() {
        this.c = false;
        if (this.g != null) {
            this.g.b();
        }
        k.a(this);
        com.bullionvault.chart.c.h.e("RealTimeReader stopThread() - stopped");
    }
    
    public final void run() {
        com.bullionvault.chart.c.h.g("Running thread for RealTimeReader! ...");
        try {
            try {
                com.bullionvault.chart.c.h.e("RealTimeReader run() - Opening URL");
                (this.g = new c(true)).a(this.d.a(this.f));
                this.c = true;
                com.bullionvault.chart.d.b a;
                while (this.c && !Thread.interrupted() && (a = this.g.a()) != null) {
                    final com.bullionvault.chart.d.b d;
                    final String a2;
                    if (!(a2 = (d = a.d("envelope").d("message")).a("type")).equals("CHART_REALTIME_A")) {
                        com.bullionvault.chart.c.h.a("RealTimeReader parseTags() - Found message type [" + a2 + "], expecting [CHART_REALTIME_A]");
                        throw new RuntimeException(Resources.b("realtime.error.format"));
                    }
                    final float b;
                    if ((b = d.b("version")) != 0.1f) {
                        com.bullionvault.chart.c.h.a("RealTimeReader parseTags() - Incompatible file format [" + b + "] compared with program [" + 0.1f + "] - please upgrade.");
                        throw new RuntimeException(Resources.b("realtime.error.version"));
                    }
                    final d d2 = new d(d.d("latest_timestamp"));
                    final com.bullionvault.chart.f.c c;
                    (c = new com.bullionvault.chart.f.c()).c = d.d("point").b();
                    c.e = d2.a();
                    final com.bullionvault.chart.f.c c2 = c;
                    com.bullionvault.chart.c.h.e("Got another Real Time point value [" + c2.c + "]");
                    if (this.c) {
                        this.a.a(c2);
                        if (this.b != null) {
                            this.b.b(Resources.b("realtime.title") + " " + Resources.c().format(c2.c) + "/kg == " + Resources.c().format(c2.c / 32.15) + "/oz @ " + DateFormat.getTimeInstance().format(c2.e));
                        }
                    }
                    if (this.h) {
                        continue;
                    }
                    this.c = false;
                }
            }
            catch (Exception ex) {
                throw new s(Resources.b("realtime.error.unable_to_read"), ex);
            }
            this.g.b();
            if (this.c && !Thread.interrupted()) {
                if (this.b != null) {
                    this.b.b(Resources.b("realtime.finished"));
                }
                this.a.a(null);
            }
        }
        catch (s s) {
            throw new RuntimeException(s.getMessage(), s);
        }
        com.bullionvault.chart.c.h.e("RealTimeReader run() - Finished RealTime Session");
        k.b(this);
    }
}
