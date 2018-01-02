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
import com.diginet.digichat.exceptions.dm;
import com.diginet.digichat.client.i;
import com.esial.util.c;
import java.awt.Component;
import com.diginet.digichat.client.h;
import java.awt.Frame;
import java.util.Vector;
import java.awt.Panel;
import com.diginet.digichat.util.ae;

public abstract class ad extends af implements ae
{
    private static final String strOK;
    private r a;
    private r b;
    private Panel c;
    private boolean d;
    private Vector e;
    private Frame f;
    private h g;
    
    public void a(final int n) {
        if (this.d) {
            ((bs)this.c).c(n);
        }
    }
    
    public int b() {
        return this.e.size();
    }
    
    public void b(final int n) {
        if (this.d) {
            ((bs)this.c).b(n);
        }
    }
    
    public void a(final ah ah) {
        ah.e = this.f;
        ah.setBackground(this.c.getBackground());
        if (this.d) {
            ((bs)this.c).a(ah.getName(), ah);
        }
        else {
            this.c.add("Center", ah);
        }
        this.e.addElement(ah);
    }
    
    private final void d() {
        for (int i = 0; i < this.e.size(); ++i) {
            final ah ah = this.e.elementAt(i);
            try {
                ah.b();
            }
            catch (dm dm) {
                if (this.d) {
                    ((bs)this.c).b(i);
                }
                new a6(this.f, com.esial.util.c.a("Error"), new String[] { com.esial.util.c.a("OK") }, new String[] { dm.getMessage() }, null, this.g).setVisible(true);
                return;
            }
        }
        try {
            this.dispose();
        }
        catch (StackOverflowError stackOverflowError) {}
        this.a();
    }
    
    public void setVisible(final boolean visible) {
        if (visible && !this.isVisible()) {
            this.pack();
            for (int i = 0; i < this.e.size(); ++i) {
                ((ah)this.e.elementAt(i)).c();
            }
        }
        super.setVisible(visible);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.a) {
            this.d();
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
            new a6(this.f, "Warning", new String[] { ad.strOK, com.esial.util.c.a("Cancel") }, new String[] { com.esial.util.c.a("Your changes have not been saved.  Are you sure you want to close this window without saving changes?") }, this, this.g).setVisible(true);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void a(final Object o, final Object o2) {
        if (ad.strOK.equals(o2)) {
            this.dispose();
        }
    }
    
    public abstract void a();
    
    public ad(final Frame frame, final String s, final int n, final Image image, final h h) {
        this(frame, s, n, image, true, h);
    }
    
    public ad(final Frame frame, final String s, final h h) {
        this(frame, s, 1, h.cc.x, h);
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
        this.setBackground(this.g.cc.c);
        if (d) {
            this.c = new bs(2, 10, 2, 10, n, this.g);
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
        final Component c = this.c(this.g.cc.v);
        c.setBackground(this.g.cc.f);
        c.setForeground(this.g.cc.e);
        layout.setConstraints(c, gridBagConstraints);
        this.add(c);
        this.a(image);
        this.setShield(g.imgShield);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.b.a(com.esial.util.c.a("Cancel"));
        this.b.f();
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.a.a(com.esial.util.c.a("Save"));
        this.a.f();
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        this.a.a(com.esial.util.c.a("Click here to save changes to your preferences and close this window."), "");
        this.b.a(com.esial.util.c.a("Click here to discard changes to your preferences and close this window."), "");
    }
    
    static {
        strOK = c.a("OK");
    }
}
