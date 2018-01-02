// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.u;
import com.diginet.digichat.awt.t;
import java.awt.Component;
import com.diginet.digichat.awt.p;
import java.awt.Insets;
import java.awt.LayoutManager;
import com.diginet.digichat.awt.ai;
import com.diginet.digichat.util.ap;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.awt.au;
import com.diginet.digichat.awt.ah;
import com.esial.util.d;
import com.diginet.digichat.common.x;
import com.diginet.digichat.util.ch;
import java.awt.Event;
import java.awt.Frame;
import java.awt.TextField;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.ShadedDialog;

public final class at extends ShadedDialog
{
    private r a;
    private r b;
    private TextField c;
    private bc d;
    private i e;
    private Frame f;
    private boolean g;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ch.a) {
                    this.a.e();
                    return true;
                }
                if (event.key == 27) {
                    this.b.e();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    if (new x(this.c.getText()).equals(this.d.h)) {
                        this.g = true;
                        this.dispose();
                        this.d.b = false;
                    }
                    else {
                        new ah(this.f, com.esial.util.d.a("Note"), com.esial.util.d.a("You have not entered the correct password for this room.  Please try again."), this.e).setVisible(true);
                    }
                }
                else if (event.target == this.b) {
                    this.g = true;
                    this.dispose();
                    if (this.e.b == -999 || !this.e.e) {
                        this.e.o();
                    }
                }
                return true;
            }
            case 201: {
                return this.g && super.handleEvent(event);
            }
        }
        return super.handleEvent(event);
    }
    
    public at(final Frame f, final i e, final bc d) {
        super(f, com.esial.util.d.a("Enter Password"), true);
        this.a = new r(80, 20);
        this.b = new r(80, 20);
        this.c = new TextField(15);
        this.g = false;
        this.setBackground(e.ca.j);
        this.setForeground(e.ca.i);
        this.setResizable(false);
        this.e = e;
        this.f = f;
        final au au = new au();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final ai ai = new ai(ap.a(com.esial.util.d.a("You must enter the correct password to enter %1."), new String[] { d.r() }));
        this.d = d;
        this.a.a(com.esial.util.d.a("OK"));
        this.a.f();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        ai.setFont(p.a);
        layout.setConstraints(ai, gridBagConstraints);
        this.add(ai);
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(au, gridBagConstraints);
        this.add(au);
        gridBagConstraints.insets = new Insets(6, 6, 6, 6);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 0;
        this.c.setEchoCharacter('*');
        final t t = new t(this.c);
        layout.setConstraints(t, gridBagConstraints);
        this.add(t);
        gridBagConstraints.insets = new Insets(2, 5, 4, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.b.a(com.esial.util.d.a("Cancel"));
        this.b.f();
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final u u = new u(this.a);
        layout.setConstraints(u, gridBagConstraints);
        this.add(u);
        this.pack();
        this.c.requestFocus();
    }
}
