// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import java.awt.Image;
import com.diginet.digichat.common.a1;
import com.diginet.digichat.awt.j;
import com.diginet.digichat.common.User;

public class User2 extends User implements j
{
    public a1 a;
    public Image b;
    public Image c;
    public Image d;
    public Image e;
    boolean f;
    boolean g;
    
    public Object h(final String s) {
        if ("audio".equals(s)) {
            return null;
        }
        if ("video".equals(s)) {
            return this.g ? this.d : (this.f ? this.b : null);
        }
        if ("room".equals(s)) {
            return new Integer(super.b);
        }
        if (this.a != null && "icon".equals(s)) {
            return this.a.a;
        }
        if (this.e != null && "availability".equals(s)) {
            return this.e;
        }
        return super.h(s);
    }
    
    public String i(final String s) {
        if ("video".equals(s) && this.u(7) && (this.u(64) || this.u(65)) && !this.u(6)) {
            return StringSubst.Substitute(LanguageSupport.translate("If you have configured your webcam double-click here to enter a video chat with %1."), new String[] { this.getName() });
        }
        return StringSubst.Substitute(LanguageSupport.translate("Double-click here to enter a private conversation with %1."), new String[] { this.getName() });
    }
    
    public User2(final int n, final String s) {
        super(n, s);
        this.f = false;
        this.g = false;
    }
    
    public User2(final int n, final String s, final boolean f, final boolean g) {
        this(n, s);
        this.f = f;
        this.g = g;
    }
}
