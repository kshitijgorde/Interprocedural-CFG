// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.m;
import java.awt.Event;
import com.esial.util.c;
import com.diginet.digichat.awt.ap;
import java.awt.Component;
import com.diginet.digichat.awt.t;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.awt.ao;
import java.awt.Panel;

public class WebIMTab extends Panel
{
    private i iUser;
    private ao usersList;
    
    public WebIMTab(final i iUser) {
        this.iUser = iUser;
        this.setBackground(iUser.cc.j);
        this.setForeground(iUser.cc.i);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final t t = new t(this.usersList = new ao());
        layout.setConstraints(t, gridBagConstraints);
        this.add(t);
        final ap ap = new ap(null, "icon");
        final ap ap2 = new ap(null, "audio", false);
        final ap ap3 = new ap(null, "video", false);
        final ap ap4 = new ap(c.a("Nickname"), "name");
        ap.d(24);
        ap.c(0);
        ap2.d(15);
        ap2.c(0);
        ap3.d(15);
        ap3.c(0);
        ap4.c(true);
        this.usersList.a(true);
        this.usersList.b(ap);
        this.usersList.b(ap2);
        this.usersList.b(ap3);
        this.usersList.b(ap4);
        this.usersList.a(ap4);
        this.usersList.l(24);
        this.resize(141, 192);
    }
    
    public void show() {
        super.show();
        this.usersList.requestFocus();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && event.target == this.usersList) {
            final WebIMUser webIMUser;
            if ((webIMUser = (WebIMUser)this.usersList.g()) != null) {
                this.iUser.callWebIM(webIMUser.bdUser);
            }
            return true;
        }
        return super.handleEvent(event);
    }
    
    private void setIMUser(final WebIMUser webIMUser, final bd bd) {
        bu.setBackground(this.usersList, webIMUser, bd);
        this.iUser.setIMIcons(webIMUser, bd.i(77), bd.i(76));
    }
    
    public void updateList() {
        this.usersList.j();
        for (int i = 0; i < this.iUser.ab.b(); ++i) {
            final bd bd;
            if ((bd = (bd)this.iUser.ab.d(i)).i(78)) {
                final WebIMUser webIMUser;
                this.usersList.d(webIMUser = new WebIMUser(bd));
                this.setIMUser(webIMUser, bd);
            }
        }
        this.usersList.b(0L);
    }
    
    public void removeList(final bd bd) {
        for (int i = 0; i < this.usersList.e(); ++i) {
            if (((WebIMUser)this.usersList.j(i)).bdUser == bd) {
                this.usersList.m(i);
                break;
            }
        }
    }
    
    public void updateList(final bd bd) {
        for (int i = 0; i < this.usersList.e(); ++i) {
            final WebIMUser webIMUser;
            if ((webIMUser = (WebIMUser)this.usersList.j(i)).bdUser == bd) {
                if (bd.i(78)) {
                    this.setIMUser(webIMUser, bd);
                }
                else {
                    this.usersList.m(i);
                }
                return;
            }
        }
        if (bd.i(78)) {
            final WebIMUser webIMUser2;
            this.usersList.d(webIMUser2 = new WebIMUser(bd));
            this.setIMUser(webIMUser2, bd);
        }
    }
}
