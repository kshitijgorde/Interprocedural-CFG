// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.common.bp;
import com.diginet.digichat.awt.m;
import com.diginet.digichat.common.k;

public class InviteRec extends k implements m
{
    public boolean fInvite;
    public bp bpIcon;
    
    public Object e(final String s) {
        if ("invite".equals(s)) {
            return new Boolean(this.fInvite);
        }
        if ("icon".equals(s)) {
            return (this.bpIcon == null) ? null : this.bpIcon.a;
        }
        return super.e(s);
    }
    
    public boolean a(final String s, final Object o) {
        if ("invite".equals(s)) {
            this.fInvite = (boolean)o;
        }
        return true;
    }
    
    public InviteRec(final bd bd, final boolean fInvite) {
        super(bd.w(), bd.x());
        this.bpIcon = bd.a;
        this.fInvite = fInvite;
    }
}
