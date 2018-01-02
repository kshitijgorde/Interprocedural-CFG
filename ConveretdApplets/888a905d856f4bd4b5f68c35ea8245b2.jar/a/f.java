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

public abstract class f extends g implements bn
{
    private ad q;
    private ad w;
    public Panel q;
    public boolean q;
    public Vector q;
    private Frame w;
    private dH q;
    
    public final void q(final int n) {
        if (this.q) {
            ((bB)this.q).w(2);
        }
    }
    
    public final void q(final cV cv) {
        cv.q = this.w;
        cv.setBackground(this.q.getBackground());
        if (this.q) {
            ((bB)this.q).q(cv.getName(), cv);
        }
        else {
            this.q.add("Center", cv);
        }
        this.q.addElement(cv);
    }
    
    public void setVisible(final boolean visible) {
        if (visible && !this.isVisible()) {
            this.pack();
            for (int i = 0; i < this.q.size(); ++i) {
                ((cV)this.q.elementAt(i)).w();
            }
        }
        super.setVisible(visible);
    }
    
    public boolean action(Event event, Object o2) {
        if (event.target == this.q) {
            cV cv;
            for (event = (Event)this, o = 0; o < ((f)event).q.size(); ++o) {
                try {
                    ((F)event).dispose();
                }
                catch (Exception ex) {}
                cv = ((f)event).q.elementAt((int)o);
                try {
                    cv.q();
                    ((f)event).q.Q = true;
                }
                catch (Q q) {
                    ((f)event).setVisible(true);
                    if (((f)event).q) {
                        ((bB)((f)event).q).q((int)o);
                    }
                    new dd(((f)event).w, be.w("Error"), new String[] { be.w("OK") }, new String[] { q.getMessage() }, null, ((f)event).q).setVisible(true);
                    return true;
                }
            }
            ((f)event).q();
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
            new dd(this.w, "Warning", new String[] { be.w("OK"), be.w("Cancel") }, new String[] { be.w("Your changes have not been saved.  Are you sure you want to close this window without saving changes?") }, this, this.q).setVisible(true);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public final void q(final Object o) {
        if ("OK".equals(o)) {
            this.setVisible(false);
            this.dispose();
            System.out.println("Alert disposed");
        }
    }
    
    public abstract void q();
    
    private f(final Frame frame, final String s, final int n, final Image image, final dH dh) {
        this(frame, s, 1, image, true, dh);
    }
    
    public f(final Frame frame, final String s, final dH dh) {
        this(frame, s, 1, bC.w.e(), dh);
    }
    
    private f(final Frame frame, final String s, final int n, final Image image, final boolean b, final dH q) {
        super(frame, s, false);
        this.q = new ad(80, 20);
        this.w = new ad(80, 20);
        this.q = true;
        this.q = new Vector();
        this.q = q;
        this.q = true;
        this.w = (Frame)this.getParent();
        this.setBackground(bC.w.q);
        this.q = new bB(2, 10, 2, 10, n, this.q);
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
        (q2 = this.q(bC.w.e)).setBackground(bC.w.r);
        q2.setForeground(bC.w.e);
        layout.setConstraints(q2, gridBagConstraints);
        this.add(q2);
        this.q(image);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.w.q(be.w("Cancel"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.q.q(be.w("Save"));
        this.q.t();
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.q.q(be.w("Click here to save changes to your preferences and close this window."), "");
        this.w.q(be.w("Click here to discard changes to your preferences and close this window."), "");
    }
}
