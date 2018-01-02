// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.awt.j;

public class az extends User2 implements j
{
    public boolean a;
    public bf b;
    
    public final Object h(final String s) {
        if (!"roomName".equals(s)) {
            return super.h(s);
        }
        if (this.b != null) {
            return this.b.getName();
        }
        return null;
    }
    
    public final String i(final String s) {
        if (this.a) {
            return StringSubst.Substitute(LanguageSupport.translate("Double-click here to enter a private conversation with %1."), new String[] { this.getName() });
        }
        return StringSubst.Substitute(LanguageSupport.translate("%1 is not online at this time."), new String[] { this.getName() });
    }
    
    public az(final int n, final String s) {
        super(n, s);
    }
}
