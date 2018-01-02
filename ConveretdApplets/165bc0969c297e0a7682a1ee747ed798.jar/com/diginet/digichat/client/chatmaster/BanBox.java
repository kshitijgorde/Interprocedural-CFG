// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client.chatmaster;

import com.diginet.digichat.awt.a6;
import java.awt.Panel;
import com.diginet.digichat.awt.dw;
import java.awt.Label;
import com.esial.util.c;
import java.awt.Component;
import com.diginet.digichat.client.i;
import java.awt.TextField;
import java.awt.Choice;
import com.diginet.digichat.awt.ag;

public class BanBox extends ag
{
    private static final long[] lMults;
    protected Choice chcLimit;
    protected TextField txfLimit;
    private i iUser;
    
    public BanBox(final i iUser, final String s) {
        super(iUser.y.b(), s, true);
        this.iUser = iUser;
        this.setBackground(iUser.cc.c);
    }
    
    protected Component createLimit() {
        final Label label;
        (label = new Label(c.a("Ban time:"))).setFont(dw.a);
        this.txfLimit = new TextField(10);
        (this.chcLimit = new Choice()).addItem("minutes");
        this.chcLimit.addItem("hours");
        this.chcLimit.addItem("days");
        this.chcLimit.addItem("weeks");
        this.chcLimit.addItem("months");
        this.chcLimit.addItem("years");
        final Panel panel = new Panel();
        panel.add(label);
        panel.add(this.txfLimit);
        panel.add(this.chcLimit);
        return panel;
    }
    
    protected long getLimit() {
        final String trim;
        if ((trim = this.txfLimit.getText().trim()).length() == 0) {
            return Long.MAX_VALUE;
        }
        try {
            return System.currentTimeMillis() + (long)(new Double(trim) * BanBox.lMults[this.chcLimit.getSelectedIndex()]) - this.iUser.lDelta;
        }
        catch (NumberFormatException ex) {
            this.txfLimit.selectAll();
            this.txfLimit.requestFocus();
            new a6(this.iUser.y.b(), c.a("Note"), c.a("Invalid value of time of ban limit.  Please re-enter this information."), this.iUser).setVisible(true);
            return -1L;
        }
    }
    
    static {
        lMults = new long[] { 60000L, 3600000L, 86400000L, 604800000L, 2592000000L, 31104000000L };
    }
}
