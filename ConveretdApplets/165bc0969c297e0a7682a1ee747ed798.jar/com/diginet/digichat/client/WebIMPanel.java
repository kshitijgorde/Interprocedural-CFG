// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Event;
import java.net.URL;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import com.diginet.digichat.awt.bj;
import com.diginet.digichat.awt.br;

public class WebIMPanel extends br implements WebIMList
{
    private i iUser;
    private WebIMSwitch usersList;
    
    public WebIMPanel(final i iUser) {
        this.iUser = iUser;
        this.setBackground(iUser.cc.c);
        final bj bj = new bj();
        bj.setBackground(iUser.cc.d);
        bj.add("Center", this.usersList = new WebIMSwitch(iUser));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(3, 4, 3, 4);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        final double n = 1.0;
        gridBagConstraints3.weightx = n;
        gridBagConstraints2.weighty = n;
        layout.setConstraints(bj, gridBagConstraints);
        this.add(bj);
        final Component a = this.a(iUser.cc.v);
        a.setBackground(iUser.cc.f);
        a.setForeground(iUser.cc.e);
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(a, gridBagConstraints);
        this.add(a);
        this.a(iUser.cc.x);
        this.setShield(iUser.imgShield, null);
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
    
    public void updateList() {
        this.usersList.updateList();
    }
}
