// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.common.User;
import com.diginet.digichat.awt.am;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.util.aj;

public class b8 implements aj
{
    private g a;
    protected int b;
    
    public final void a(final Object o, final Object o2) {
        try {
            if (o2 == null) {
                this.a.l(this.b);
                this.a.m(this.b);
            }
            else if (LanguageSupport.translate("Close").equals(((am)o).b())) {
                ((am)o).dispose();
                ((am)o).setVisible(false);
            }
            else if (LanguageSupport.translate("Cancel").equals(((am)o).b()) || "Cancel".equals(((am)o).b())) {
                this.a.l(this.b);
                this.a.m(this.b);
            }
            else if (LanguageSupport.translate("Ok").equals(((am)o).b()) || "Ok".equals(((am)o).b())) {
                final User user = (User)this.a.aj.d(this.b);
                this.a.j(this.b);
                this.a.k(this.b);
                this.a.a(user.getName(), this.b, false);
            }
        }
        catch (Exception ex) {}
    }
    
    public b8(final g a, final int b) {
        this.a = a;
        this.b = b;
    }
}
