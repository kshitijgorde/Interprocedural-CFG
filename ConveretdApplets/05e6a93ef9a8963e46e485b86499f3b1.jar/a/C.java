// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Panel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class C extends w
{
    private N q;
    private bq q;
    private static Checkbox q;
    private TextArea q;
    private bf q;
    private av q;
    private aj q;
    public static boolean q;
    private String q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.q.w();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    this.q.w();
                    return true;
                }
                if (event.target == this.q) {
                    cs.q(" " + ((aN)this.q.q()).o, this.q);
                    if (C.q.getState()) {
                        this.q.w();
                    }
                }
                return true;
            }
            case 201: {
                this.q.w();
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final synchronized void hide() {
        C.q = false;
        super.hide();
    }
    
    private void w() {
        this.q.r();
        try {
            for (int i = 0; i < this.q.w().q; ++i) {
                final aN an;
                if ((an = (aN)this.q.w().q(i)).q(36)) {
                    final L l = new L(an);
                    this.q.q((aX)l);
                    this.q.q(l, new Color(l.w()), Color.pink);
                    if (an.e().length() > this.q.length()) {
                        this.q = an.e();
                    }
                }
            }
        }
        finally {}
    }
    
    public C(final Frame frame, final aj q, final Point location) {
        super(frame, al.q("Select a shortcut"), false);
        this.q = "";
        this.q = new N(80, 20);
        this.q = q;
        this.q = q.q();
        this.setLocation(location);
        this.setBackground(aT.w.q);
        final cv cv = new cv();
        final m m = new m();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(al.q("Select shortcut"));
        this.q = q.q();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        cv.setLayout(gridBagLayout);
        cv.setBackground(aT.w.i);
        cv.setForeground(aT.w.u);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label.setFont(be.q);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        cv.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(m, gridBagConstraints);
        cv.add(m);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        (this.q = new av(al.q("Shortcut"), "replace")).q(false);
        (this.q = new bf()).w(this.q);
        final Panel panel = new Panel();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        this.w();
        gridBagConstraints2.insets = new Insets(4, 4, 4, 4);
        gridBagConstraints2.gridwidth = -1;
        gridBagConstraints2.gridheight = 0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.fill = 1;
        this.q.getFontMetrics(this.q.getFont());
        this.q.setSize(250, 241);
        final bc bc = new bc(this.q);
        layout.setConstraints(bc, gridBagConstraints2);
        panel.add(bc);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        cv.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(cv, gridBagConstraints);
        this.add(cv);
        if (C.q == null) {
            (C.q = new Checkbox(al.q("Close after selection"))).setForeground(aT.w.u);
            C.q.setState(true);
        }
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(C.q, gridBagConstraints);
        this.add(C.q);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.q.q(al.q("Close"));
        this.q.t();
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.pack();
        C.q = true;
    }
    
    static {
        C.q = false;
    }
}
