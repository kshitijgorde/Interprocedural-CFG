// 
// Decompiled by Procyon v0.5.30
// 

package com.bitcoinplus.applet;

import java.applet.Applet;
import netscape.javascript.JSObject;
import com.bitcoinplus.applet.b.a;
import java.util.LinkedList;
import java.util.List;
import com.bitcoinplus.applet.c.b;

public final class e
{
    private static final long[] a;
    private final String b;
    private boolean c;
    private String d;
    private int e;
    private b f;
    private List g;
    
    public e(final String d, final String b, final int e, final b f) {
        this.d = d;
        this.b = b;
        this.e = e;
        this.f = f;
        this.g = new LinkedList();
    }
    
    public final synchronized a a() {
        if (this.g.isEmpty()) {
            if (!this.c) {
                int n = 0;
                while (true) {
                    try {
                        this.a(this.f.a(this.b()));
                    }
                    catch (SecurityException ex) {
                        this.c = true;
                    }
                    catch (Exception ex2) {
                        final int n2;
                        if ((n2 = n) < 0 || n2 >= com.bitcoinplus.applet.e.a.length) {
                            com.bitcoinplus.applet.d.a.a(com.bitcoinplus.applet.e.a[com.bitcoinplus.applet.e.a.length - 1]);
                        }
                        com.bitcoinplus.applet.d.a.a(com.bitcoinplus.applet.e.a[n2]);
                        ++n;
                        continue;
                    }
                    break;
                }
            }
            if (this.c) {
                try {
                    JSObject.getWindow((Applet)MiningApplet.miningApplet).call(this.b, (Object[])new String[] { this.b() });
                }
                catch (Exception ex3) {}
            }
        }
        while (this.g.isEmpty()) {
            com.bitcoinplus.applet.d.a.a(100L);
        }
        return this.g.remove(0);
    }
    
    private String b() {
        return this.d + "/api/work/getwork?" + "a" + "=" + this.e;
    }
    
    public final void a(final String s) {
        final a a = new a(s);
        new StringBuilder().append("Got new work, midstate: ").append(a.b()).append(", start: ").append(a.e()).append(", end: ").append(a.f()).toString();
        final long n = (a.f() - a.e()) / 8L;
        for (int i = 0; i < 8; ++i) {
            final long n3;
            long n2;
            if ((n2 = (n3 = a.e() + i * n + i) + n) > a.f()) {
                n2 = a.f();
            }
            new StringBuilder().append("Splitting data, midstate: ").append(a.b()).append(", start: ").append(a(n3)).append(", end: ").append(a(n2)).toString();
            this.g.add(new a(a, a(n3), a(n2)));
        }
    }
    
    private static int a(final long n) {
        if (n > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int)n;
    }
    
    static {
        a = new long[] { 1000L, 5000L, 30000L, 60000L, 120000L, 300000L };
    }
}
