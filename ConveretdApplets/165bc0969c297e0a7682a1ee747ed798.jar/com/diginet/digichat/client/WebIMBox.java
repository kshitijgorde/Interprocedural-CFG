// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Event;
import java.awt.Frame;
import com.esial.util.c;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.diginet.digichat.awt.bj;

public class WebIMBox extends SwitchBox implements WebIMList
{
    private WebIMSwitch usersList;
    
    protected bj createSwitch() {
        final bj bj = new bj();
        bj.setLayout(new BorderLayout());
        bj.add("Center", this.usersList = new WebIMSwitch(super.iUser));
        return bj;
    }
    
    public WebIMBox(final i i) {
        super(null, i, com.esial.util.c.a("1:1 Users"), false);
    }
    
    public void updateList() {
        this.usersList.updateList();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && event.target == this.usersList) {
            final WebIMUser webIMUser;
            if ((webIMUser = (WebIMUser)this.usersList.g()) != null) {
                super.iUser.callWebIM(webIMUser.bdUser);
            }
            return true;
        }
        return super.handleEvent(event);
    }
}
