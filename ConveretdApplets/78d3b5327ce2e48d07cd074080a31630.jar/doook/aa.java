// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Event;
import java.awt.Component;
import java.awt.Frame;
import java.util.Vector;
import java.awt.Panel;

public abstract class aa extends Y implements P
{
    private ax a;
    private ax b;
    private Panel a;
    private boolean h;
    private Vector b;
    private Frame a;
    private at g;
    
    public void a(final int n) {
        if (this.h) {
            ((x)this.a).a(n);
        }
    }
    
    public void a(final W w) {
        w.b = this.a;
        w.setBackground(this.a.getBackground());
        if (this.h) {
            ((x)this.a).a(w.getName(), w);
        }
        else {
            this.a.add("Center", w);
        }
        this.b.addElement(w);
    }
    
    private final void g() {
        for (int i = 0; i < this.b.size(); ++i) {
            final W w = this.b.elementAt(i);
            try {
                w.f();
            }
            catch (j j) {
                if (this.h) {
                    ((x)this.a).a(i);
                }
                new S(this.a, ar.b("Error"), new String[] { ar.b("OK") }, new String[] { j.getMessage() }, null, this.g).setVisible(true);
                return;
            }
        }
        try {
            this.dispose();
        }
        catch (StackOverflowError stackOverflowError) {}
        this.c();
    }
    
    public void setVisible(final boolean visible) {
        if (visible && !this.isVisible()) {
            this.pack();
            for (int i = 0; i < this.b.size(); ++i) {
                ((W)this.b.elementAt(i)).c();
            }
        }
        super.setVisible(visible);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.a) {
            this.g();
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
            new S(this.a, "Warning", new String[] { ar.b("OK"), ar.b("Cancel") }, new String[] { ar.b("Your changes have not been saved.  Are you sure you want to close this window without saving changes?") }, this, this.g).setVisible(true);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void a(final Object o, final Object o2) {
        if (ar.b("OK").equals(o2)) {
            this.dispose();
        }
    }
    
    public abstract void c();
    
    public aa(final Frame frame, final String s, final int n, final Image image, final at at) {
        this(frame, s, n, image, true, at);
    }
    
    public aa(final Frame frame, final String s, final at at) {
        this(frame, s, 1, at.b.p, at);
    }
    
    public aa(final Frame frame, final String s, final int n, final Image image, final boolean h, final at g) {
        super(frame, s, false);
        this.a = new ax(80, 20);
        this.b = new ax(80, 20);
        this.h = true;
        this.b = new Vector();
        this.g = g;
        this.h = h;
        this.a = (Frame)this.getParent();
        this.setBackground(this.g.b.a);
        if (h) {
            this.a = new x(2, 10, 2, 10, n, this.g);
        }
        else {
            (this.a = new Panel()).setLayout(new BorderLayout());
        }
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(3, 4, 3, 4);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        final Component b = this.b(this.g.b.ap);
        b.setBackground(this.g.b.h);
        b.setForeground(this.g.b.l);
        layout.setConstraints(b, gridBagConstraints);
        this.add(b);
        this.a(image);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.b.a(ar.b("Cancel"));
        this.b.p();
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.a.a(ar.b("Save"));
        this.a.p();
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        this.a.a(ar.b("Click here to save changes to your preferences and close this window."), "");
        this.b.a(ar.b("Click here to discard changes to your preferences and close this window."), "");
    }
}
