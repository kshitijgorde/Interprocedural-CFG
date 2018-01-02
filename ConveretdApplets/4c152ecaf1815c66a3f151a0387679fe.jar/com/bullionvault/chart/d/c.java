// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.d;

import com.bullionvault.chart.e.f;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.URLConnection;

public final class c
{
    private com.bullionvault.chart.e.c a;
    private boolean b;
    private boolean c;
    
    public c(final boolean b) {
        this.b = false;
        this.c = false;
        this.c = true;
    }
    
    public final void a(final URLConnection urlConnection) {
        this.a = new com.bullionvault.chart.e.c(urlConnection);
    }
    
    public final b a() {
        if (!this.b && this.c) {
            this.a.a();
        }
        final f a;
        if ((a = this.a.a()) == null) {
            return null;
        }
        final b a2 = new a(new StringBufferInputStream(a.a)).a();
        if (!this.b) {
            this.b = true;
        }
        return a2;
    }
    
    public final void b() {
        this.a.b();
    }
}
