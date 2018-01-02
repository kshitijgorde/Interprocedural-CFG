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

public abstract class r extends q implements ad
{
    private aS a;
    private aS b;
    private Panel a;
    private boolean h;
    private Vector b;
    private Frame c;
    private u a;
    
    public void a(final int n) {
        if (this.h) {
            ((cr)this.a).c(n);
        }
    }
    
    public int b() {
        return this.b.size();
    }
    
    public void b(final int n) {
        if (this.h) {
            ((cr)this.a).b(n);
        }
    }
    
    public void a(final o o) {
        o.a = this.c;
        o.setBackground(this.a.getBackground());
        if (this.h) {
            ((cr)this.a).a(o.getName(), o);
        }
        else {
            this.a.add("Center", o);
        }
        this.b.addElement(o);
    }
    
    private final void b() {
        for (int i = 0; i < this.b.size(); ++i) {
            final o o = this.b.elementAt(i);
            try {
                o.c();
            }
            catch (aq aq) {
                if (this.h) {
                    ((cr)this.a).b(i);
                }
                new E(this.c, ao.e("Error"), new String[] { ao.e("OK") }, new String[] { aq.getMessage() }, null, this.a).setVisible(true);
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
            for (int i = 0; i < this.b.size(); ++i) {
                ((o)this.b.elementAt(i)).d();
            }
        }
        super.setVisible(visible);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.a) {
            this.b();
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
            new E(this.c, "Warning", new String[] { ao.e("OK"), ao.e("Cancel") }, new String[] { ao.e("Your changes have not been saved.  Are you sure you want to close this window without saving changes?") }, this, this.a).setVisible(true);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void a(final Object o, final Object o2) {
        if (ao.e("OK").equals(o2)) {
            this.dispose();
        }
    }
    
    public abstract void a();
    
    public r(final Frame frame, final String s, final int n, final Image image, final u u) {
        this(frame, s, n, image, true, u);
    }
    
    public r(final Frame frame, final String s, final u u) {
        this(frame, s, 1, u.a.t, u);
    }
    
    public r(final Frame frame, final String s, final int n, final Image image, final boolean h, final u a) {
        super(frame, s, false);
        this.a = new aS(80, 20);
        this.b = new aS(80, 20);
        this.h = true;
        this.b = new Vector();
        this.a = a;
        this.h = h;
        this.c = (Frame)this.getParent();
        this.setBackground(this.a.a.a);
        if (h) {
            this.a = new cr(2, 10, 2, 10, n, this.a);
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
        final Component a2 = this.a(this.a.a.aE);
        a2.setBackground(this.a.a.k);
        a2.setForeground(this.a.a.i);
        layout.setConstraints(a2, gridBagConstraints);
        this.add(a2);
        this.a(image);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.b.a(ao.e("Cancel"));
        this.b.t();
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.a.a(ao.e("Save"));
        this.a.t();
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        this.a.a(ao.e("Click here to save changes to your preferences and close this window."), "");
        this.b.a(ao.e("Click here to discard changes to your preferences and close this window."), "");
    }
}
