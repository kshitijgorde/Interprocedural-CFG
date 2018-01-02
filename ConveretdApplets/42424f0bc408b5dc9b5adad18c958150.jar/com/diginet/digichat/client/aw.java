// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.ap;
import com.esial.util.d;
import com.diginet.digichat.common.bd;
import com.diginet.digichat.awt.m;
import com.diginet.digichat.common.j;

public class aw extends j implements m
{
    public bd a;
    
    public Object e(final String s) {
        if ("room".equals(s)) {
            return new Integer(super.b);
        }
        if (this.a != null && "icon".equals(s)) {
            return this.a.a;
        }
        return super.e(s);
    }
    
    public String f(final String s) {
        return ap.a(com.esial.util.d.a("Double-click here to enter a private conversation with %1."), new String[] { this.r() });
    }
    
    public aw(final int n, final String s) {
        super(n, s);
    }
}
