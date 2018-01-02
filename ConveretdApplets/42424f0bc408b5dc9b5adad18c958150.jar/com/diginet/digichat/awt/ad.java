// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Event;
import com.diginet.digichat.exceptions.cf;
import com.diginet.digichat.client.i;
import com.esial.util.d;
import java.awt.Component;
import com.diginet.digichat.client.h;
import java.awt.Frame;
import java.util.Vector;
import java.awt.Panel;
import com.diginet.digichat.util.ae;

public abstract class ad extends af implements ae
{
    private r a;
    private r b;
    private Panel c;
    private boolean d;
    private Vector e;
    private Frame f;
    private h g;
    
    public void a(final int n) {
        if (this.d) {
            ((bg)this.c).a(n);
        }
    }
    
    public void a(final ag ag) {
        ag.e = this.f;
        ag.setBackground(this.c.getBackground());
        if (this.d) {
            ((bg)this.c).a(ag.getName(), ag);
        }
        else {
            this.c.add("Center", ag);
        }
        this.e.addElement(ag);
    }
    
    private final void c() {
        for (int i = 0; i < this.e.size(); ++i) {
            final ag ag = this.e.elementAt(i);
            try {
                ag.a();
            }
            catch (cf cf) {
                if (this.d) {
                    ((bg)this.c).a(i);
                }
                new ah(this.f, com.esial.util.d.a("Error"), new String[] { com.esial.util.d.a("OK") }, new String[] { cf.getMessage() }, null, this.g).setVisible(true);
                return;
            }
        }
        try {
            this.dispose();
        }
        catch (StackOverflowError stackOverflowError) {}
        this.b();
    }
    
    public void setVisible(final boolean visible) {
        if (visible && !this.isVisible()) {
            this.pack();
            for (int i = 0; i < this.e.size(); ++i) {
                ((ag)this.e.elementAt(i)).b();
            }
        }
        super.setVisible(visible);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.a) {
            this.c();
        }
        else {
            if (event.target != this.b) {
                return super.action(event, o);
            }
            this.dispose();
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            new ah(this.f, "Warning", new String[] { com.esial.util.d.a("OK"), com.esial.util.d.a("Cancel") }, new String[] { com.esial.util.d.a("Your changes have not been saved.  Are you sure you want to close this window without saving changes?") }, this, this.g).setVisible(true);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void a(final Object o, final Object o2) {
        if ("OK".equals(o2)) {
            this.dispose();
        }
    }
    
    public abstract void b();
    
    public ad(final Frame frame, final String s, final int n, final Image image, final h h) {
        this(frame, s, n, image, true, h);
    }
    
    public ad(final Frame frame, final String s, final h h) {
        this(frame, s, 1, h.ca.x, h);
    }
    
    public ad(final Frame frame, final String s, final int n, final Image image, final boolean d, final h g) {
        super(frame, s, false);
        this.a = new r(80, 20);
        this.b = new r(80, 20);
        this.d = true;
        this.e = new Vector();
        this.g = g;
        this.d = d;
        this.f = (Frame)this.getParent();
        this.setBackground(this.g.ca.c);
        if (d) {
            this.c = new bg(2, 10, 2, 10, n, this.g);
        }
        else {
            (this.c = new Panel()).setLayout(new BorderLayout());
        }
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(3, 4, 3, 4);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        final Component b = this.b(this.g.ca.v);
        b.setBackground(this.g.ca.f);
        b.setForeground(this.g.ca.e);
        layout.setConstraints(b, gridBagConstraints);
        this.add(b);
        this.a(image);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.b.a(com.esial.util.d.a("Cancel"));
        this.b.f();
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.a.a(com.esial.util.d.a("Save"));
        this.a.f();
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        this.a.a(com.esial.util.d.a("Click here to save changes to your preferences and close this window."), "");
        this.b.a(com.esial.util.d.a("Click here to discard changes to your preferences and close this window."), "");
    }
}
