// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.m;
import com.esial.util.c;
import com.diginet.digichat.awt.ap;
import com.diginet.digichat.awt.ao;

public class WebIMSwitch extends ao
{
    private i iUser;
    
    public WebIMSwitch(final i iUser) {
        this.iUser = iUser;
        final ap ap = new ap(null, "icon");
        final ap ap2 = new ap(null, "audio", false);
        final ap ap3 = new ap(null, "video", false);
        final ap ap4 = new ap(com.esial.util.c.a("Nickname"), "name");
        ap.d(24);
        ap.c(0);
        ap2.d(15);
        ap2.c(0);
        ap3.d(15);
        ap3.c(0);
        ap4.c(true);
        this.a(true);
        this.b(ap);
        this.b(ap2);
        this.b(ap3);
        this.b(ap4);
        this.a(ap4);
        this.l(24);
        this.resize(141, 192);
    }
    
    public void updateList() {
        synchronized (this.iUser.webIMUsers) {
            this.j();
            for (int i = 0; i < this.iUser.webIMUsers.b(); ++i) {
                final WebIMUser webIMUser;
                this.d(webIMUser = (WebIMUser)this.iUser.webIMUsers.d(i));
                bu.setBackground(this, webIMUser, webIMUser.bdUser);
            }
        }
        // monitorexit(this.iUser.webIMUsers)
    }
}
