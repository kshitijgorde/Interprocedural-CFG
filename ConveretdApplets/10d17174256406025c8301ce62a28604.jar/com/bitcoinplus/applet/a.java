// 
// Decompiled by Procyon v0.5.30
// 

package com.bitcoinplus.applet;

import java.applet.Applet;
import netscape.javascript.JSObject;
import com.bitcoinplus.applet.c.b;

public final class a
{
    public static String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private boolean f;
    private String g;
    private int h;
    private b i;
    private int j;
    
    public a(final String g, final String b, final String c, final String d, final String e, final int h, final b i) {
        this.g = g;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.h = h;
        this.i = i;
    }
    
    public final void a(final String s, final com.bitcoinplus.applet.b.a a) {
        new StringBuilder().append("Sending work, payment targets: ").append(a.a).append(", data: ").append(s).toString();
        if (!this.f) {
            int i = 0;
            while (i < 3) {
                try {
                    this.a(this.i.a(this.b(s, a)));
                    return;
                }
                catch (SecurityException ex) {
                    this.f = true;
                }
                catch (Exception ex2) {
                    com.bitcoinplus.applet.d.a.a(30000L);
                    ++i;
                    continue;
                }
                break;
            }
        }
        if (this.f) {
            try {
                JSObject.getWindow((Applet)MiningApplet.miningApplet).call(this.b, (Object[])new String[] { this.b(s, a) });
            }
            catch (Exception ex3) {}
        }
    }
    
    protected final void a(final String s) {
        try {
            new StringBuilder().append("workResponseJSON: ").append(s).toString();
            final String a = a.a.a.a.a.a(s, "\"valid\":", "}");
            final String s2 = ",";
            final String s3 = a;
            String substring;
            String s5;
            final int index;
            final String s4 = (a.a.a.a.a.a(a) || s2 == null) ? (s5 = (substring = s3)) : ((s2.length() == 0) ? (s5 = (substring = "")) : (((index = s3.indexOf(s2)) == -1) ? (s5 = (substring = s3)) : (s5 = (substring = s3.substring(0, index)))));
            final String s6 = (substring == null) ? null : s4.trim();
            if ("true".equals(s6)) {
                ++this.j;
                try {
                    JSObject.getWindow((Applet)MiningApplet.miningApplet).call(this.c, new Object[] { s });
                }
                catch (Exception ex) {}
            }
            new StringBuilder().append("submittedWorkToServer valid: ").append(s6).append(", successes: ").append(this.j).toString();
        }
        catch (Exception ex2) {}
    }
    
    private String b(String s, final com.bitcoinplus.applet.b.a a) {
        s = this.g + "/api/work/submitwork/?" + "a" + "=" + s + "&" + "b" + "=" + a.a + "&" + "c" + "=" + a.g() + "&" + "d" + "=" + a.h() + "&" + "e" + "=" + a.i() + "&" + "f" + "=" + this.h + "&" + "g" + "=" + this.d;
        if (!a.a.a.a.a.a(this.e)) {
            s = s + "&" + this.e;
        }
        return s;
    }
    
    public final int a() {
        return this.j;
    }
    
    static {
        com.bitcoinplus.applet.a.a = "";
    }
}
