// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import java.awt.Image;
import com.diginet.digichat.awt.j;
import com.diginet.digichat.common.a6;

public class bf extends a6 implements j
{
    public Image a;
    public boolean b;
    public boolean c;
    
    public final Object h(final String s) {
        if ("users".equals(s)) {
            return new Integer(super.c);
        }
        if (!"lock".equals(s)) {
            if (!"flashAV".equals(s) || this.u(44)) {}
            return super.h(s);
        }
        if (super.k != null && !super.k.a()) {
            if (com.diginet.digichat.client.h.c != null) {
                return com.diginet.digichat.client.h.c;
            }
            return "*";
        }
        else {
            if (!this.u(48)) {
                return null;
            }
            if (com.diginet.digichat.client.h.d != null) {
                return com.diginet.digichat.client.h.d;
            }
            if (com.diginet.digichat.client.h.c != null) {
                return com.diginet.digichat.client.h.c;
            }
            return "*";
        }
    }
    
    public final String i(final String s) {
        String s2;
        if (this.b) {
            s2 = StringSubst.Substitute(LanguageSupport.translate("You are in %1."), new String[] { this.getName() });
        }
        else {
            s2 = StringSubst.Substitute(LanguageSupport.translate("Double-click here to enter %1."), new String[] { this.getName() });
        }
        if (super.a != null) {
            s2 = s2.substring(0, s2.length() - 1) + ": " + super.a;
        }
        return s2;
    }
    
    public final int a(final j j, final String s) {
        if ("users".equals(s) && j instanceof a6) {
            return ((a6)j).c - super.c;
        }
        return super.a(j, s);
    }
    
    public bf(final int n, final String s) {
        super(n, s);
        this.b = false;
        this.c = true;
    }
}
