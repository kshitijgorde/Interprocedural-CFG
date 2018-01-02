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

public abstract class w extends ab implements G
{
    private al b;
    private al c;
    private Panel a;
    private boolean c;
    private Vector c;
    private Frame a;
    private aW d;
    
    public void c(final int n) {
        if (this.c) {
            ((p)this.a).b(n);
        }
    }
    
    public void a(final aZ az) {
        az.e = this.a;
        az.setBackground(this.a.getBackground());
        if (this.c) {
            ((p)this.a).a(az.getName(), az);
        }
        else {
            this.a.add("Center", az);
        }
        this.c.addElement(az);
    }
    
    private final void b() {
        for (int i = 0; i < this.c.size(); ++i) {
            final aZ az = this.c.elementAt(i);
            try {
                az.a();
            }
            catch (J j) {
                if (this.c) {
                    ((p)this.a).b(i);
                }
                new E(this.a, aG.a("Error"), new String[] { aG.a("OK") }, new String[] { j.getMessage() }, null, this.d).setVisible(true);
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
            for (int i = 0; i < this.c.size(); ++i) {
                ((aZ)this.c.elementAt(i)).c();
            }
        }
        super.setVisible(visible);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.b) {
            this.b();
        }
        else {
            if (event.target != this.c) {
                return super.action(event, o);
            }
            this.dispose();
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            new E(this.a, "Warning", new String[] { aG.a("OK"), aG.a("Cancel") }, new String[] { aG.a("Your changes have not been saved.  Are you sure you want to close this window without saving changes?") }, this, this.d).setVisible(true);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void a(final Object o, final Object o2) {
        if (aG.a("OK").equals(o2)) {
            this.dispose();
        }
    }
    
    public abstract void a();
    
    public w(final Frame frame, final String s, final int n, final Image image, final aW aw) {
        this(frame, s, n, image, true, aw);
    }
    
    public w(final Frame frame, final String s, final aW aw) {
        this(frame, s, 1, aw.c.l, aw);
    }
    
    public w(final Frame frame, final String s, final int n, final Image image, final boolean c, final aW d) {
        super(frame, s, false);
        this.b = new al(80, 20);
        this.c = new al(80, 20);
        this.c = true;
        this.c = new Vector();
        this.d = d;
        this.c = c;
        this.a = (Frame)this.getParent();
        this.setBackground(this.d.c.c);
        if (c) {
            this.a = new p(2, 10, 2, 10, n, this.d);
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
        final Component a = this.a(this.d.c.r);
        a.setBackground(this.d.c.g);
        a.setForeground(this.d.c.e);
        layout.setConstraints(a, gridBagConstraints);
        this.add(a);
        this.a(image);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.c.a(aG.a("Cancel"));
        this.c.f();
        layout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.b.a(aG.a("Save"));
        this.b.f();
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        this.b.a(aG.a("Click here to save changes to your preferences and close this window."), "");
        this.c.a(aG.a("Click here to discard changes to your preferences and close this window."), "");
    }
}
