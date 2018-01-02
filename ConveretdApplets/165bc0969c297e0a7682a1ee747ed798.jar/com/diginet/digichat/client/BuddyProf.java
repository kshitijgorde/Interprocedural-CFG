// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.dx;
import java.awt.Event;
import com.diginet.digichat.awt.u;
import java.awt.GridLayout;
import com.diginet.digichat.util.a5;
import java.awt.Frame;
import javax.accessibility.Accessible;
import com.diginet.digichat.awt.aw;
import java.net.URL;
import com.esial.util.c;
import com.diginet.digichat.awt.bb;
import com.diginet.digichat.awt.dw;
import java.awt.Label;
import java.awt.Component;
import com.diginet.digichat.awt.a9;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import com.diginet.digichat.awt.bj;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.ag;

public class BuddyProf extends ag
{
    private r btnPriv;
    private r btnInv;
    private r btnNote;
    private r btnOK;
    private bj bjCont;
    private i iUser;
    private Buddy buddy;
    
    public Buddy getBuddy() {
        return this.buddy;
    }
    
    public void setProf(final Image image, final String s, final Image image2, final String s2, final String s3, final String s4) {
        this.bjCont.removeAll();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final Panel panel = new Panel(layout);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        gridBagConstraints.anchor = 17;
        if (image != null) {
            final a9 a9 = new a9();
            a9.b(image);
            layout.setConstraints(a9, gridBagConstraints);
            panel.add(a9);
        }
        final Label label;
        (label = new Label(this.buddy.x(), 1)).setFont(dw.a);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        this.bjCont.setLayout(layout);
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        layout.setConstraints(panel, gridBagConstraints);
        this.bjCont.add(panel);
        final bb bb = new bb();
        layout.setConstraints(bb, gridBagConstraints);
        this.bjCont.add(bb);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.anchor = 17;
        final Panel panel2 = new Panel(layout);
        if (s2 != null) {
            gridBagConstraints.gridwidth = 1;
            final Label label2;
            (label2 = new Label(c.a("Display Name"))).setFont(dw.d);
            layout.setConstraints(label2, gridBagConstraints);
            panel2.add(label2);
            gridBagConstraints.gridwidth = 0;
            final Label label3;
            layout.setConstraints(label3 = new Label(s2), gridBagConstraints);
            panel2.add(label3);
        }
        final Label label4;
        (label4 = new Label(c.a("Location"))).setFont(dw.d);
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(label4, gridBagConstraints);
        panel2.add(label4);
        URL url;
        Accessible accessible;
        try {
            url = new URL(s);
            accessible = new aw(this.buddy.location.x(), url, false);
        }
        catch (Throwable t) {
            url = null;
            accessible = new Label(this.buddy.location.x());
        }
        if (image2 == null) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints((Component)accessible, gridBagConstraints);
            panel2.add((Component)accessible);
        }
        else {
            final Panel panel3 = new Panel(layout);
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.anchor = 10;
            layout.setConstraints((Component)accessible, gridBagConstraints);
            panel3.add((Component)accessible);
            final a9 a10;
            (a10 = new a9()).b(image2);
            a10.setURL(url);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(a10, gridBagConstraints);
            panel3.add(a10);
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.fill = 2;
            layout.setConstraints(panel3, gridBagConstraints);
            panel2.add(panel3);
            gridBagConstraints.anchor = 17;
        }
        if (s3 != null) {
            final Label label5;
            (label5 = new Label(c.a("Comment"))).setFont(dw.d);
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.weightx = 0.0;
            layout.setConstraints(label5, gridBagConstraints);
            panel2.add(label5);
            final aw aw = new aw(s3, false);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(aw, gridBagConstraints);
            panel2.add(aw);
        }
        if (s4 != null) {
            final Label label6;
            (label6 = new Label(c.a("Status"))).setFont(dw.d);
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.weightx = 0.0;
            layout.setConstraints(label6, gridBagConstraints);
            panel2.add(label6);
            final aw aw2 = new aw(s4, false);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(aw2, gridBagConstraints);
            panel2.add(aw2);
        }
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        layout.setConstraints(panel2, gridBagConstraints);
        this.bjCont.add(panel2);
        this.pack();
        this.setVisible(true);
    }
    
    public BuddyProf(final Frame frame, final i iUser, final Buddy buddy) {
        super(frame, false);
        this.iUser = iUser;
        this.setBackground(iUser.cc.c);
        (this.bjCont = new bj()).setBackground(iUser.cc.j);
        this.bjCont.setForeground(iUser.cc.i);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setResizable(false);
        this.setTitle(a5.a(c.a("Profile of %1"), new String[] { buddy.getName() }));
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(this.bjCont, gridBagConstraints);
        this.add(this.bjCont);
        final Panel panel = new Panel(new GridLayout(1, 0, 5, 0));
        final r btnPriv = null;
        this.btnNote = btnPriv;
        this.btnInv = btnPriv;
        this.btnPriv = btnPriv;
        if (buddy.location.i(29)) {
            panel.add(this.btnPriv = new r(c.a("Send Message")));
        }
        if (buddy.location.i(27)) {
            panel.add(this.btnInv = new r(c.a("Send Invitation")));
        }
        if (buddy.location.i(25)) {
            panel.add(this.btnNote = new r(c.a("Draw Attention")));
        }
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 17;
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.insets = new Insets(3, 15, 3, 5);
        final u u = new u(this.btnOK = new r(c.a("OK")));
        layout.setConstraints(u, gridBagConstraints);
        this.add(u);
        this.buddy = buddy;
        buddy.location.getProf(this);
    }
    
    public void dispose() {
        this.buddy.bdProf = null;
        super.dispose();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == dx.a) {
                    this.btnOK.e();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.btnPriv) {
                    this.buddy.callPriv(null);
                    return true;
                }
                if (event.target == this.btnInv) {
                    this.buddy.location.callInv(this.buddy);
                    return true;
                }
                if (event.target == this.btnNote) {
                    this.buddy.location.callNote(this.buddy);
                    return true;
                }
                if (event.target == this.btnOK) {
                    this.dispose();
                    return true;
                }
                if (event.arg instanceof URL) {
                    this.iUser.a((URL)event.arg, "_blank");
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
}
