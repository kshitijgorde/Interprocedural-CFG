// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Container;
import java.awt.FontMetrics;
import java.awt.BorderLayout;
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

public abstract class B extends C implements cw
{
    private e q;
    private e w;
    private Panel q;
    private boolean q;
    private Vector q;
    private Frame w;
    private bH q;
    
    public final void q(final int n) {
        if (this.q) {
            ((z)this.q).q(n);
        }
    }
    
    public final void q(final D d) {
        d.q = this.w;
        d.setBackground(this.q.getBackground());
        if (this.q) {
            ((z)this.q).q(d.getName(), d);
        }
        else {
            this.q.add("Center", d);
        }
        this.q.addElement(d);
    }
    
    public void setVisible(final boolean visible) {
        if (visible && !this.isVisible()) {
            this.pack();
            for (int i = 0; i < this.q.size(); ++i) {
                ((D)this.q.elementAt(i)).w();
            }
        }
        super.setVisible(visible);
    }
    
    public boolean action(Event event, Object o2) {
        if (event.target == this.q) {
            D d;
            for (event = (Event)this, o = 0; o < ((B)event).q.size(); ++o) {
                try {
                    ((W)event).dispose();
                }
                catch (Exception ex) {}
                d = ((B)event).q.elementAt((int)o);
                try {
                    d.q();
                    ((B)event).q.m = true;
                }
                catch (bs bs) {
                    ((B)event).setVisible(true);
                    if (((B)event).q) {
                        ((z)((B)event).q).q((int)o);
                    }
                    new b(((B)event).w, cv.q("Error"), new String[] { cv.q("OK") }, new String[] { bs.getMessage() }, null, ((B)event).q).setVisible(true);
                    return true;
                }
            }
            ((B)event).q();
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
            new b(this.w, "Warning", new String[] { cv.q("OK"), cv.q("Cancel") }, new String[] { cv.q("Your changes have not been saved.  Are you sure you want to close this window without saving changes?") }, this, this.q).setVisible(true);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public final void q(final Object o) {
        if (cv.q("OK").equals(o)) {
            this.setVisible(false);
            this.dispose();
            System.out.println("Alert disposed");
        }
    }
    
    public abstract void q();
    
    private B(final Frame frame, final String s, final int n, final Image image, final bH bh) {
        this(frame, s, 1, image, true, bh);
    }
    
    public B(final Frame frame, final String s, final bH bh) {
        this(frame, s, 1, be.w.e(), bh);
    }
    
    private B(final Frame frame, final String s, final int n, final Image image, final boolean b, final bH q) {
        super(frame, s, false);
        this.q = new e(80, 20);
        this.w = new e(80, 20);
        this.q = true;
        this.q = new Vector();
        this.q = q;
        this.q = true;
        this.w = (Frame)this.getParent();
        this.setBackground(be.w.q);
        this.q = new z(2, 10, 2, 10, n, this.q);
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
        final int u = be.w.u;
        if (super.q == null) {
            super.q = new q(5, 7, 5, 7, u);
            super.q = new E();
            ((Container)(super.q = new c())).setLayout(new BorderLayout(10, 3));
            super.q.add("West", super.q);
            super.q.add("Center", super.q);
            super.q.setFont(k.r);
            super.q.q(false);
            final FontMetrics fontMetrics = this.getFontMetrics(k.r);
            super.q.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        final q q2;
        (q2 = super.q).setBackground(be.w.r);
        q2.setForeground(be.w.e);
        layout.setConstraints(q2, gridBagConstraints);
        this.add(q2);
        super.q.w(image);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.w.q(cv.q("Cancel"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.q.q(cv.q("Save"));
        this.q.t();
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.q.q(cv.q("Click here to save changes to your preferences and close this window."), "");
        this.w.q(cv.q("Click here to discard changes to your preferences and close this window."), "");
    }
}
