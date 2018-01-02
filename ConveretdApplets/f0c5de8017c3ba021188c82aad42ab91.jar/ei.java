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

public class ei implements bf
{
    public long a;
    private final /* synthetic */ LHTML b;
    
    public ei(final LHTML b) {
        this.b = b;
        this.a = System.currentTimeMillis();
    }
    
    public boolean a(final du du, final int n, final bb bb) {
        final do a = du.a();
        if (a != null && a.toString().indexOf("status=200") > 0) {
            this.b.a(System.currentTimeMillis() - this.a + "", 4194304);
        }
        else {
            this.b.a("-1", 4194304);
        }
        return false;
    }
    
    public void c(final Exception ex, final int n, final String s) {
        this.b.a("-1", 4194304);
    }
    
    public boolean c() {
        return true;
    }
    
    public void a(final dq dq) {
    }
    
    public void b(final Exception ex, final int n, final String s) {
    }
}
