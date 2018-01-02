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

public abstract class c extends d implements aJ
{
    private N q;
    private N w;
    public Panel q;
    public boolean q;
    private Vector q;
    private Frame w;
    private co q;
    
    public final void q(final bN bn) {
        bn.setBackground(this.q.getBackground());
        if (this.q) {
            ((aS)this.q).q(bn.getName(), bn);
        }
        else {
            this.q.add("Center", bn);
        }
        this.q.addElement(bn);
    }
    
    public void setVisible(final boolean visible) {
        if (visible && !this.isVisible()) {
            this.pack();
            for (int i = 0; i < this.q.size(); ++i) {
                ((bN)this.q.elementAt(i)).w();
            }
        }
        super.setVisible(visible);
    }
    
    public boolean action(Event event, Object o2) {
        if (event.target == this.q) {
            bN bn;
            for (event = (Event)this, o = 0; o < ((c)event).q.size(); ++o) {
                try {
                    ((w)event).dispose();
                }
                catch (Exception ex) {}
                bn = ((c)event).q.elementAt((int)o);
                try {
                    bn.q();
                }
                catch (E e) {
                    ((c)event).setVisible(true);
                    if (((c)event).q) {
                        ((aS)((c)event).q).q((int)o);
                    }
                    new p(((c)event).w, al.q("Error"), new String[] { al.q("OK") }, new String[] { e.getMessage() }, null, ((c)event).q).setVisible(true);
                    break;
                }
            }
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
            new p(this.w, "Warning", new String[] { al.q("OK"), al.q("Cancel") }, new String[] { al.q("Your changes have not been saved.  Are you sure you want to close this window without saving changes?") }, this, this.q).setVisible(true);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public final void q(final Object o) {
        if ("OK".equals(o)) {
            this.dispose();
        }
    }
    
    private c(final Frame frame, final String s, final int n, final Image image, final co co) {
        this(frame, s, 1, image, true, co);
    }
    
    public c(final Frame frame, final String s, final co co) {
        this(frame, s, 1, aT.w.e(), co);
    }
    
    private c(final Frame frame, final String s, final int n, final Image image, final boolean b, final co q) {
        super(frame, s, false);
        this.q = new N(80, 20);
        this.w = new N(80, 20);
        this.q = true;
        this.q = new Vector();
        this.q = q;
        this.q = true;
        this.w = (Frame)this.getParent();
        this.setBackground(aT.w.q);
        this.q = new aS(2, 10, 2, 10, n, this.q);
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
        final int e = aT.w.e;
        if (super.q == null) {
            super.q = new cv(5, 7, 5, 7, e);
            super.q = new q();
            ((Container)(super.q = new aH())).setLayout(new BorderLayout(10, 3));
            super.q.add("West", super.q);
            super.q.add("Center", super.q);
            super.q.setFont(be.r);
            super.q.q = false;
            final FontMetrics fontMetrics = this.getFontMetrics(be.r);
            super.q.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        final cv q2;
        (q2 = super.q).setBackground(aT.w.r);
        q2.setForeground(aT.w.e);
        layout.setConstraints(q2, gridBagConstraints);
        this.add(q2);
        super.q.w(image);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.w.q(al.q("Cancel"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.q.q(al.q("Save"));
        this.q.t();
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.q.q(al.q("Click here to save changes to your preferences and close this window."), "");
        this.w.q(al.q("Click here to discard changes to your preferences and close this window."), "");
    }
}
