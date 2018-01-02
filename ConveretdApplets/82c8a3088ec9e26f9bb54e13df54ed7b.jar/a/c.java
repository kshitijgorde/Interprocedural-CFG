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

public abstract class c extends d implements aI
{
    private M q;
    private M w;
    public Panel q;
    public boolean q;
    private Vector q;
    private Frame w;
    private co q;
    
    public final void q(final bM bm) {
        bm.setBackground(this.q.getBackground());
        if (this.q) {
            ((aR)this.q).q(bm.getName(), bm);
        }
        else {
            this.q.add("Center", bm);
        }
        this.q.addElement(bm);
    }
    
    public void setVisible(final boolean visible) {
        if (visible && !this.isVisible()) {
            this.pack();
            for (int i = 0; i < this.q.size(); ++i) {
                ((bM)this.q.elementAt(i)).w();
            }
        }
        super.setVisible(visible);
    }
    
    public boolean action(Event event, Object o2) {
        if (event.target == this.q) {
            bM bm;
            for (event = (Event)this, o = 0; o < ((c)event).q.size(); ++o) {
                try {
                    ((v)event).dispose();
                }
                catch (Exception ex) {}
                bm = ((c)event).q.elementAt((int)o);
                try {
                    bm.q();
                }
                catch (D d) {
                    ((c)event).setVisible(true);
                    if (((c)event).q) {
                        ((aR)((c)event).q).q((int)o);
                    }
                    new bR(((c)event).w, ak.q("Error"), new String[] { ak.q("OK") }, new String[] { d.getMessage() }, null, ((c)event).q).setVisible(true);
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
            new bR(this.w, "Warning", new String[] { ak.q("OK"), ak.q("Cancel") }, new String[] { ak.q("Your changes have not been saved.  Are you sure you want to close this window without saving changes?") }, this, this.q).setVisible(true);
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
        this(frame, s, 1, aS.w.e(), co);
    }
    
    private c(final Frame frame, final String s, final int n, final Image image, final boolean b, final co q) {
        super(frame, s, false);
        this.q = new M(80, 20);
        this.w = new M(80, 20);
        this.q = true;
        this.q = new Vector();
        this.q = q;
        this.q = true;
        this.w = (Frame)this.getParent();
        this.setBackground(aS.w.q);
        this.q = new aR(2, 10, 2, 10, n, this.q);
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
        final int e = aS.w.e;
        if (super.q == null) {
            super.q = new cw(5, 7, 5, 7, e);
            super.q = new p();
            ((Container)(super.q = new aG())).setLayout(new BorderLayout(10, 3));
            super.q.add("West", super.q);
            super.q.add("Center", super.q);
            super.q.setFont(bd.r);
            super.q.q = false;
            final FontMetrics fontMetrics = this.getFontMetrics(bd.r);
            super.q.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        final cw q2;
        (q2 = super.q).setBackground(aS.w.r);
        q2.setForeground(aS.w.e);
        layout.setConstraints(q2, gridBagConstraints);
        this.add(q2);
        super.q.w(image);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.w.q(ak.q("Cancel"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.q.q(ak.q("Save"));
        this.q.t();
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.q.q(ak.q("Click here to save changes to your preferences and close this window."), "");
        this.w.q(ak.q("Click here to discard changes to your preferences and close this window."), "");
    }
}
