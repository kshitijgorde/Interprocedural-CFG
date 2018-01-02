// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Event;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.esial.util.c;
import java.awt.Frame;
import com.diginet.digichat.awt.bj;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.af;

public abstract class SwitchBox extends af
{
    private r btnClose;
    protected i iUser;
    
    protected abstract bj createSwitch();
    
    public SwitchBox(final Frame frame, final i iUser, final String s, final boolean b) {
        super(frame, com.esial.util.c.a(s), b);
        this.iUser = iUser;
        final bj switch1 = this.createSwitch();
        this.setBackground(iUser.cc.c);
        switch1.setBackground(iUser.cc.d);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(3, 4, 3, 4);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        final double n = 1.0;
        gridBagConstraints3.weightx = n;
        gridBagConstraints2.weighty = n;
        layout.setConstraints(switch1, gridBagConstraints);
        this.add(switch1);
        final Component c = this.c(iUser.cc.v);
        c.setBackground(iUser.cc.f);
        c.setForeground(iUser.cc.e);
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(c, gridBagConstraints);
        this.add(c);
        this.a(iUser.cc.x);
        this.setShield(iUser.imgShield);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        layout.setConstraints(this.btnClose = new r(com.esial.util.c.a("Close")), gridBagConstraints);
        this.add(this.btnClose);
        this.btnClose.a(com.esial.util.c.a("Click here to close this window."), "");
        this.pack();
        this.show();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && event.target == this.btnClose) {
            this.dispose();
            return true;
        }
        return super.handleEvent(event);
    }
}
