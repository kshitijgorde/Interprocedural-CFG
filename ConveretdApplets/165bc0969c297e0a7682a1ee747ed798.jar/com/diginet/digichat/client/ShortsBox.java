// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.common.k;
import java.awt.Component;
import com.diginet.digichat.awt.ap;
import com.esial.util.c;
import com.diginet.digichat.awt.m;
import com.diginet.digichat.common.ax;
import com.diginet.digichat.common.be;
import com.diginet.digichat.util.n;
import com.diginet.digichat.awt.ao;

public class ShortsBox extends InsertBox
{
    private i iUser;
    private ao aoShorts;
    private TextPanel txpPanel;
    
    private void addShorts(final n n, final boolean b, final boolean b2) {
        n.a(false);
        try {
            for (int i = 0; i < n.b(); ++i) {
                final be be = (be)n.d(i);
                if (be.i(29) && be.i(30)) {
                    final ax ax;
                    this.aoShorts.d(ax = new ax(be.w(), be.a));
                    ax.a(31, b);
                    ax.a(30, b2);
                }
            }
        }
        finally {
            n.a();
        }
    }
    
    public ShortsBox(final i iUser, final TextPanel txpPanel) {
        super(iUser, txpPanel);
        final ap ap;
        (this.aoShorts = new ao()).b(ap = new ap(c.a("Shortcut"), "name"));
        this.aoShorts.a(true);
        ap.a(true);
        this.iUser = iUser;
        this.addShorts(iUser.ag, false, false);
        this.addShorts(iUser.resWatch, true, false);
        this.addShorts(iUser.ah, true, true);
        this.txpPanel = txpPanel;
        this.addSelector(c.a("Select a shortcut"), this.aoShorts, iUser.fShorts);
    }
    
    protected void setClose(final boolean fShorts) {
        this.iUser.fShorts = fShorts;
    }
    
    protected boolean insert() {
        final k k;
        final k i;
        if ((k = (k)this.aoShorts.g()) == null || (i = (k)(k.i(31) ? (k.i(30) ? this.iUser.ah : this.iUser.resWatch) : this.iUser.ag).e(k.w())) == null) {
            return false;
        }
        this.txpPanel.appendElem(i.x());
        return this.txpPanel.incShorts() || this.iUser.fShorts;
    }
}
