// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Event;
import java.awt.Component;
import java.awt.Frame;
import java.util.Vector;
import java.awt.Panel;

public abstract class E extends F implements ed
{
    private g q;
    private g w;
    private Panel q;
    private boolean q;
    public Vector q;
    private Frame w;
    private cU q;
    
    public final void q(final int n) {
        if (this.q) {
            ((B)this.q).w(2);
        }
    }
    
    public final void w(final int n) {
        if (this.q) {
            ((B)this.q).q(n);
        }
    }
    
    public final void q(final G g) {
        g.q = this.w;
        g.setBackground(this.q.getBackground());
        if (this.q) {
            ((B)this.q).q(g.getName(), g);
        }
        else {
            this.q.add("Center", g);
        }
        this.q.addElement(g);
    }
    
    public void setVisible(final boolean visible) {
        if (visible && !this.isVisible()) {
            this.pack();
            for (int i = 0; i < this.q.size(); ++i) {
                ((G)this.q.elementAt(i)).w();
            }
        }
        super.setVisible(visible);
    }
    
    public boolean action(Event event, Object o2) {
        if (event.target == this.q) {
            G g;
            for (event = (Event)this, o = 0; o < ((E)event).q.size(); ++o) {
                try {
                    ((ah)event).dispose();
                }
                catch (Exception ex) {}
                g = ((E)event).q.elementAt((int)o);
                try {
                    g.q();
                    ((E)event).q.Q = true;
                }
                catch (cF cf) {
                    ((E)event).setVisible(true);
                    if (((E)event).q) {
                        ((B)((E)event).q).q((int)o);
                    }
                    new b(((E)event).w, eb.q("Error"), new String[] { eb.q("OK") }, new String[] { cf.getMessage() }, null, ((E)event).q).setVisible(true);
                    return true;
                }
            }
            ((E)event).q();
        }
        else {
            if (event.target != this.w) {
                return super.action(event, o2);
            }
            this.dispose();
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            new b(this.w, "Warning", new String[] { eb.q("OK"), eb.q("Cancel") }, new String[] { eb.q("Your changes have not been saved.  Are you sure you want to close this window without saving changes?") }, this, this.q).setVisible(true);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public final void q(final Object o) {
        if (eb.q("OK").equals(o)) {
            this.setVisible(false);
            this.dispose();
            System.out.println("Alert disposed");
        }
    }
    
    public abstract void q();
    
    private E(final Frame frame, final String s, final int n, final Image image, final cU cu) {
        this(frame, s, 1, image, true, cu);
    }
    
    public E(final Frame frame, final String s, final cU cu) {
        this(frame, s, 1, cf.w.e(), cu);
    }
    
    private E(final Frame frame, final String s, final int n, final Image image, final boolean b, final cU q) {
        super(frame, s, false);
        this.q = new g(80, 20);
        this.w = new g(80, 20);
        this.q = true;
        this.q = new Vector();
        this.q = q;
        this.q = true;
        this.w = (Frame)this.getParent();
        this.setBackground(cf.w.q);
        this.q = new B(2, 10, 2, 10, n, this.q);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(3, 4, 3, 4);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        final Component q2;
        (q2 = this.q(cf.w.e)).setBackground(cf.w.r);
        q2.setForeground(cf.w.e);
        layout.setConstraints(q2, gridBagConstraints);
        this.add(q2);
        this.q(image);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.w.q(eb.q("Cancel"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.q.q(eb.q("Save"));
        this.q.t();
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.q.q(eb.q("Click here to save changes to your preferences and close this window."), "");
        this.w.q(eb.q("Click here to discard changes to your preferences and close this window."), "");
    }
}
