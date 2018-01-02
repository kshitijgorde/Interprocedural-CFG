// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.e;

import com.bullionvault.chart.resources.Resources;
import com.bullionvault.chart.c.h;
import java.net.URLConnection;

final class b
{
    private String b;
    private String c;
    URLConnection a;
    
    public b(final String b, final String c) {
        this.b = b;
        this.c = c;
    }
    
    public final URLConnection a(final String s) {
        this.a = null;
        h.b("accessCodeURL - Trying to open....");
        try {
            this.b(this.c(s));
        }
        catch (i i) {
            h.d("accessCodeURL - Permission denied, requesting new Access Code.");
            h.b("accessCodeURL - reopen() - " + s);
            this.b(this.c(s));
            this.a = this.a;
        }
        return this.a;
    }
    
    private void b(final String s) {
        h.f("accessCodeURL - try_open() - " + s);
        final g g = new g(s, 8, 30000);
        this.a = g.a();
        final String b = g.b();
        h.e("accessCodeURL - Response Code was [" + b + "]");
        if (b == null || !b.equals("200")) {
            throw new i(Resources.b("http_response_code") + b);
        }
        h.e("Opened URL [" + s + "]");
    }
    
    private String c(final String s) {
        h.e("accessCodeURL -  buildURL() urlBase: " + this.b);
        h.e("accessCodeURL -  buildURL() seriesPath: " + this.c);
        h.e("accessCodeURL -  buildURL() urlEnd: " + s);
        String s2;
        if (this.c.indexOf("REALTIME") < 0) {
            s2 = this.b + this.c + s;
        }
        else {
            s2 = this.b + this.c;
        }
        h.e("accessCodeURL -  buildURL() fullURL: " + s2);
        return s2;
    }
}
