// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.util.TimeZone;

public final class F
{
    private TimeZone e;
    public static int a;
    private static int f;
    private static int g;
    private static int h;
    public static int b;
    public static int c;
    private static int i;
    public static int d;
    private long j;
    private int k;
    private int l;
    private O m;
    private int n;
    
    public F(final O m) {
        this.e = null;
        this.j = System.currentTimeMillis();
        this.k = 60;
        this.l = 0;
        this.n = 4;
        this.m = m;
        this.m.d = this;
    }
    
    public final synchronized void a() {
        final Thread thread;
        (thread = new Thread(new al(this), "ReplayManager-Updater")).setDaemon(true);
        thread.start();
    }
    
    private String j() {
        String string = "";
        for (int i = 0; i < this.n / 4; ++i) {
            string += ".";
        }
        ++this.n;
        return string;
    }
    
    public final String b() {
        String s;
        if (this.l == 0) {
            s = COM.NextBus.AdminMap.a.b("Stopped");
        }
        else if (this.l == F.f) {
            s = COM.NextBus.AdminMap.a.b("Server Initializing") + this.j();
        }
        else if (this.l == F.h) {
            s = COM.NextBus.AdminMap.a.b("Server Busy") + this.j();
        }
        else if (this.l == F.b) {
            s = COM.NextBus.AdminMap.a.b("Playing at") + " " + this.k() + ".";
        }
        else if (this.l == F.c || this.l == F.g) {
            s = COM.NextBus.AdminMap.a.b("Paused at") + " " + this.k() + ".";
        }
        else {
            s = COM.NextBus.AdminMap.a.b("Date") + " " + this.k() + " " + COM.NextBus.AdminMap.a.b("is in future!");
        }
        return s;
    }
    
    public final int c() {
        if (this.l == 0) {
            return 0;
        }
        if (this.l == F.c || this.l == F.g || this.l == F.i) {
            return F.c;
        }
        return F.b;
    }
    
    public final boolean a(final long j) {
        if (this.l == 0) {
            this.j = j;
        }
        if (this.l == 0) {
            this.n = 4;
            this.l = F.f;
        }
        else if (this.l == F.f) {
            this.l = F.g;
        }
        else if (this.l == F.g || this.l == F.i) {
            this.n = 4;
            this.l = F.f;
        }
        else if (this.l == F.h) {
            this.l = F.c;
        }
        else if (this.l == F.b) {
            this.l = F.c;
        }
        else if (this.l == F.c) {
            this.l = F.b;
        }
        return this.l == F.b || this.l == F.f || this.l == F.h;
    }
    
    public final void d() {
        this.l = 0;
        this.m.c.d();
    }
    
    public final void a(final int k) {
        this.k = k;
    }
    
    private String k() {
        if (this.e == null) {
            this.e = this.m.q();
        }
        return t.a(this.j, this.e);
    }
    
    public final long e() {
        return this.j;
    }
    
    public final int f() {
        if (this.k <= 10) {
            return this.k;
        }
        return 10;
    }
    
    static {
        F.a = 0;
        F.f = 1;
        F.g = 2;
        F.h = 3;
        F.b = 4;
        F.c = 5;
        F.i = 6;
        F.d = 10000;
    }
}
