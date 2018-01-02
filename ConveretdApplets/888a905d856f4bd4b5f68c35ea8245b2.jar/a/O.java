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
import java.awt.Frame;
import java.awt.Point;
import java.awt.Color;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class O extends F
{
    private ad q;
    private cs q;
    private static Checkbox q;
    private TextArea q;
    private cc q;
    private aX q;
    private aC q;
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
                    dN.q(" " + ((bw)this.q.q()).a, this.q);
                    if (O.q.getState()) {
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
        O.q = false;
        super.hide();
    }
    
    private void q() {
        this.q.r();
        try {
            for (int i = 0; i < this.q.e().q; ++i) {
                final bw bw;
                if ((bw = (bw)this.q.e().q(i)).q(36)) {
                    final aa aa = new aa(bw);
                    this.q.e(aa);
                    this.q.q(aa, new Color(aa.w()), Color.pink);
                    if (bw.e().length() > this.q.length()) {
                        this.q = bw.e();
                    }
                }
            }
        }
        finally {}
    }
    
    public O(final aC q, final Point location) {
        super(new Frame(), be.w("Select a shortcut"), false);
        this.q = "";
        this.q = new ad(80, 20);
        this.q = q;
        this.q = q.q();
        this.setLocation(location);
        this.setBackground(bC.w.q);
        final dT dt = new dT();
        final q q2 = new q();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(be.w("Select shortcut"));
        this.q = q.q();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        dt.setLayout(gridBagLayout);
        dt.setBackground(bC.w.i);
        dt.setForeground(bC.w.u);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label.setFont(cb.q);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        dt.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(q2, gridBagConstraints);
        dt.add(q2);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        (this.q = new aX(be.w("Shortcut"), "replace")).w(false);
        (this.q = new cc()).w(this.q);
        final Panel panel = new Panel();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        this.q();
        gridBagConstraints2.insets = new Insets(4, 4, 4, 4);
        gridBagConstraints2.gridwidth = -1;
        gridBagConstraints2.gridheight = 0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.fill = 1;
        this.q.getFontMetrics(this.q.getFont());
        this.q.setSize(250, 241);
        final bZ bz = new bZ(this.q);
        layout.setConstraints(bz, gridBagConstraints2);
        panel.add(bz);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        dt.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(dt, gridBagConstraints);
        this.add(dt);
        if (O.q == null) {
            (O.q = new Checkbox(be.w("Close after selection"))).setForeground(bC.w.u);
            O.q.setState(true);
        }
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(O.q, gridBagConstraints);
        this.add(O.q);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.q.q(be.w("Close"));
        this.q.t();
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.pack();
        O.q = true;
    }
    
    static {
        O.q = false;
    }
}
