// 
// Decompiled by Procyon v0.5.30
// 

package com.is_teledata.livehtml;

import java.util.StringTokenizer;
import java.util.Enumeration;
import java.awt.Graphics;
import java.util.Date;
import java.util.Vector;
import java.util.Hashtable;
import java.applet.Applet;

public class ej implements ch
{
    public int a;
    private final /* synthetic */ LHTML b;
    
    public ej(final LHTML b) {
        this.b = b;
        this.a = 0;
    }
    
    public boolean a(final du du, final int n, final bb bb) {
        this.b.a(this.a + "", 8388608);
        ++this.a;
        return true;
    }
    
    public void c(final Exception ex, final int n, final String s) {
        if (this.a != 10) {
            this.b.a("-1", 8388608);
        }
    }
    
    public boolean c() {
        return true;
    }
    
    public void a(final dq dq) {
    }
    
    public long b() {
        return 5000L;
    }
    
    public void b(final Exception ex, final int n, final String s) {
    }
    
    public long d() {
        return 0L;
    }
}
