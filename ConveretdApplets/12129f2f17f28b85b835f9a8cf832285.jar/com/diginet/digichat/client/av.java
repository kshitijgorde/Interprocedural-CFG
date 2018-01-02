// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.ap;
import com.esial.util.d;
import com.diginet.digichat.awt.m;

public class av extends aw implements m
{
    public boolean a;
    
    public Object e(final String s) {
        return super.e(s);
    }
    
    public String f(final String s) {
        if (this.a) {
            return ap.a(com.esial.util.d.a("Double-click here to enter a private conversation with %1."), new String[] { this.r() });
        }
        return ap.a(com.esial.util.d.a("%1 is not online at this time."), new String[] { this.r() });
    }
    
    public av(final int n, final String s) {
        super(n, s);
    }
}
