// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Component;
import com.esial.util.c;
import com.diginet.digichat.awt.CommonStyle;
import java.util.Hashtable;
import com.diginet.digichat.network.v;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Label;
import com.diginet.digichat.awt.ah;

public class ct extends ah
{
    private static final String[] strElems;
    private Label lblStyles;
    private Label[] lblElems;
    private Choice a;
    private Choice chcSave;
    private Choice chcCheck;
    private Checkbox b;
    private Checkbox d;
    private Checkbox chkAnim;
    private Checkbox[] chkElems;
    private Checkbox[] chkStyles;
    private CheckboxGroup grpStyles;
    private h e;
    
    private void setElem(final int n, final boolean b) {
        this.lblElems[n].enable(b);
        final int n2;
        final Checkbox checkbox;
        (checkbox = this.chkElems[n2 = n << 1]).enable(b);
        final Checkbox checkbox2;
        (checkbox2 = this.chkElems[n2 + 1]).enable(b);
        if (b) {
            checkbox.setState(v.a(this.e.lElems, n2));
            checkbox2.setState(v.a(this.e.lElems, n2 + 1));
        }
        else {
            checkbox.setState(false);
            checkbox2.setState(false);
        }
    }
    
    private void setElem(final int n) {
        this.setElem(n, this.e.isElem(n));
    }
    
    public void enableBuddies(final boolean b) {
        this.setElem(5, b);
    }
    
    public void b() {
        this.e.bq = this.a.getSelectedIndex();
        final h e = this.e;
        final int selectedIndex = this.chcSave.getSelectedIndex();
        e.nLast = selectedIndex;
        if (selectedIndex == 1) {
            this.e.nSave = 0;
        }
        else if ((this.e.nSave = this.e.nLast) == 2) {
            this.e.saveProf(null, 0L);
        }
        this.e.b7 = (this.b.getState() || !this.b.isEnabled());
        this.e.bb = this.d.getState();
        this.e.nCheck = this.chcCheck.getSelectedIndex();
        if (this.chkAnim.isEnabled()) {
            CommonStyle.fAnim = this.chkAnim.getState();
        }
        if (this.grpStyles != null) {
            long n = 0L;
            for (int i = 0, n2 = 0; i < this.lblElems.length; ++i, n2 += 2) {
                n = (this.lblElems[i].isEnabled() ? v.a(v.a(n, n2, this.chkElems[n2].getState()), n2 + 1, this.chkElems[n2 + 1].getState()) : (n | (this.e.lElems & 3 << n2)));
            }
            int nStyles = this.e.nStyles;
            if (this.lblStyles.isEnabled()) {
                final Checkbox current = this.grpStyles.getCurrent();
                for (int j = 0; j < this.chkStyles.length; ++j) {
                    if (this.chkStyles[j] == current) {
                        nStyles = j;
                        break;
                    }
                }
            }
            this.e.setAppear(n, nStyles);
        }
    }
    
    public void c() {
        this.a.select(this.e.bq);
        this.chcSave.select(this.e.nSave);
        if (v.a(this.e.at, 61) || v.a(this.e.at, 62)) {
            this.b.disable();
            this.b.setState(true);
        }
        else {
            this.b.setState(this.e.b7);
        }
        this.chcCheck.select(this.e.nCheck);
        this.d.setState(this.e.bb);
        if (this.e.cc.getAnim()) {
            this.chkAnim.enable();
            this.chkAnim.setState(CommonStyle.fAnim);
        }
        else {
            this.chkAnim.disable();
            this.chkAnim.setState(false);
        }
        if (this.grpStyles != null) {
            for (int i = 0; i < this.lblElems.length; ++i) {
                this.setElem(i);
            }
            final boolean b;
            this.grpStyles.setCurrent((b = ((this.e.lPlus & 0x70000) != 0x0)) ? this.chkStyles[this.e.nStyles] : null);
            this.lblStyles.setEnabled(b);
            for (int j = 0; j < this.chkStyles.length; ++j) {
                this.chkStyles[j].setEnabled(b);
            }
        }
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return com.esial.util.c.a("Select how fast new chat messages will scroll.");
        }
        if (o != this.b) {
            return null;
        }
        if (this.b.isEnabled()) {
            return com.esial.util.c.a("Check this box to enable ChatWatch.  ChatWatch replaces vulgarities with less offensive words.");
        }
        return com.esial.util.c.a("This item is disabled because ChatWatch has been turned on for all users.");
    }
    
    public ct(final h e) {
        super(com.esial.util.c.a("Options"), e);
        this.chkStyles = new Checkbox[3];
        this.grpStyles = null;
        this.a = new Choice();
        this.chcSave = new Choice();
        this.chcCheck = new Choice();
        this.b = new Checkbox(com.esial.util.c.a("Enable ChatWatch"));
        this.d = new Checkbox(com.esial.util.c.a("Enable Auto-Popup of Private Messages"));
        this.chkAnim = new Checkbox(com.esial.util.c.a("Enable Animation"));
        this.e = e;
        this.chcSave.addItem(com.esial.util.c.a("Always"));
        this.chcSave.addItem(com.esial.util.c.a("Except Last"));
        this.chcSave.addItem(com.esial.util.c.a("Nothing"));
        if (e.d) {
            this.a(com.esial.util.c.a("Save Profile"), this.chcSave);
        }
        this.a.addItem(com.esial.util.c.a("Very Slow"));
        this.a.addItem(com.esial.util.c.a("Slow"));
        this.a.addItem(com.esial.util.c.a("Normal"));
        this.a.addItem(com.esial.util.c.a("Fast"));
        this.a.addItem(com.esial.util.c.a("Very Fast"));
        this.a(com.esial.util.c.a("Message Scroll Speed"), this.a);
        this.chcCheck.addItem(com.esial.util.c.a("Never"));
        this.chcCheck.addItem(com.esial.util.c.a("15 sec"));
        this.chcCheck.addItem(com.esial.util.c.a("1 min"));
        this.chcCheck.addItem(com.esial.util.c.a("5 min"));
        this.chcCheck.addItem(com.esial.util.c.a("15 min"));
        this.chcCheck.addItem(com.esial.util.c.a("30 min"));
        this.chcCheck.addItem(com.esial.util.c.a("1 hour"));
        this.a(com.esial.util.c.a("Check Buddies"), this.chcCheck);
        this.a("", this.b);
        this.a("", this.d);
        this.a("", this.chkAnim);
        if (e.i(109)) {
            this.grpStyles = new CheckboxGroup();
            this.chkStyles[0] = new Checkbox(com.esial.util.c.a("Embedded"), this.grpStyles, false);
            this.chkStyles[1] = new Checkbox(com.esial.util.c.a("Separate"), this.grpStyles, false);
            this.chkStyles[2] = new Checkbox(com.esial.util.c.a("Always"), this.grpStyles, false);
            final Panel panel = new Panel(new FlowLayout(0, 0, 0));
            panel.add(this.chkStyles[0]);
            panel.add(this.chkStyles[1]);
            panel.add(this.chkStyles[2]);
            this.lblStyles = this.a(com.esial.util.c.a("Text Styles"), panel);
            this.lblElems = new Label[e.isElem(7) ? ct.strElems.length : (ct.strElems.length - 1)];
            this.chkElems = new Checkbox[this.lblElems.length << 1];
            for (int i = 0, n = 0; i < this.lblElems.length; ++i, n += 2) {
                final Label[] lblElems = this.lblElems;
                final int n2 = i;
                final String a = com.esial.util.c.a(ct.strElems[i]);
                final Checkbox[] chkElems = this.chkElems;
                final int n3 = n;
                final Checkbox checkbox = new Checkbox(com.esial.util.c.a("As Button"));
                chkElems[n3] = checkbox;
                final Checkbox[] chkElems2 = this.chkElems;
                final int n4 = n + 1;
                final Checkbox checkbox2 = new Checkbox(com.esial.util.c.a("As Menu Item"));
                chkElems2[n4] = checkbox2;
                lblElems[n2] = this.a(a, checkbox, checkbox2);
            }
        }
    }
    
    static {
        strElems = new String[] { "User profile", "Private conversation", "1:1 conversation", "Flagging messages", "Ignoring messages", "Add buddy", "Kick user", "Advanced settings" };
    }
}
