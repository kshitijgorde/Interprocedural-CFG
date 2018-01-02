// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.u;
import com.diginet.digichat.awt.t;
import java.awt.Component;
import com.diginet.digichat.awt.p;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.awt.ah;
import com.esial.util.d;
import com.diginet.digichat.util.ch;
import java.awt.Event;
import java.awt.Frame;
import java.awt.TextField;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.ShadedDialog;

public final class cd extends ShadedDialog
{
    private r a;
    private r b;
    private TextField c;
    private h d;
    private Frame e;
    
    private final av a(final String s) {
        final aw[] array = new aw[this.d.aa.b()];
        this.d.aa.a(array);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null && array[i].r().equals(s)) {
                final av av = new av(array[i].q(), array[i].r());
                av.a = true;
                av.a = array[i].a;
                av.a = array[i].a;
                av.b = array[i].b;
                av.a(array[i].s());
                return av;
            }
        }
        return null;
    }
    
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
                    final String trim = this.c.getText().trim();
                    if (trim.length() == 0) {
                        new ah(this.e, com.esial.util.d.a("Note"), com.esial.util.d.a("You must provide a name for your Buddy.  Please re-enter this information."), this.d).setVisible(true);
                        return true;
                    }
                    if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
                        this.c.requestFocus();
                        this.c.selectAll();
                        new ah(this.d.a1, com.esial.util.d.a("Error"), com.esial.util.d.a("Names can not have commas or quotes.  Please re-enter this information."), this.d).setVisible(true);
                        return true;
                    }
                    av a = this.a(trim);
                    if (a == null) {
                        a = new av(-999, trim);
                        a.a = false;
                    }
                    if (this.d.x != null) {
                        ((aq)this.d.x).a(a, true, false);
                    }
                    this.dispose();
                }
                else if (event.target == this.b) {
                    this.dispose();
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public cd(final Frame frame, final h d) {
        super(frame, com.esial.util.d.a("Add Buddy"), true);
        this.a = new r(80, 20);
        this.b = new r(80, 20);
        this.setBackground(d.ca.j);
        this.setForeground(d.ca.i);
        this.d = d;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 13;
        this.c = new TextField(20);
        final Label label = new Label(com.esial.util.d.a("Name"));
        label.setFont(p.d);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final t t = new t(this.c);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        panel.add(t);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.b.a(com.esial.util.d.a("Cancel"));
        this.b.f();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.a.a(com.esial.util.d.a("OK"));
        this.a.f();
        final u u = new u(this.a);
        gridBagLayout.setConstraints(u, gridBagConstraints);
        this.add(u);
        this.pack();
        this.c.requestFocus();
    }
}
