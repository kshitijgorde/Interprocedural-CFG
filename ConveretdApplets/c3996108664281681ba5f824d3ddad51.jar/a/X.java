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

public final class X extends W
{
    private e q;
    private bz q;
    private e w;
    private static Checkbox q;
    private TextArea q;
    private u q;
    private w q;
    private j q;
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
                }
                if (event.target == this.w) {
                    a.q(" " + ((bc)this.q.q()).getName(), this.q);
                    this.q.requestFocus();
                    if (X.q.getState()) {
                        this.q.w();
                    }
                }
                if (event.target == this.q) {
                    a.q(" " + ((bc)this.q.q()).getName(), this.q);
                    this.q.requestFocus();
                    if (X.q.getState()) {
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
        X.q = false;
        super.hide();
    }
    
    private void q() {
        this.q.r();
        this.q.q();
        cq.q();
        try {
            for (int i = 0; i < this.q.q().q(); ++i) {
                final bc bc;
                if ((bc = (bc)this.q.q().q(i)).q(36)) {
                    final by by = new by(bc);
                    this.q.q((aq)by);
                    this.q.q(by, new Color(by.r()), Color.pink);
                    if (bc.w().length() > this.q.length()) {
                        this.q = bc.w();
                    }
                }
            }
        }
        finally {
            this.q.q();
            cq.w();
        }
    }
    
    public X(final j q, final Point location) {
        super(new Frame(), cv.q("Select a shortcut"), false);
        this.q = "";
        this.q = new e(80, 20);
        this.w = new e(80, 20);
        this.q = q;
        this.q = q.q();
        this.setLocation(location);
        this.setBackground(be.w.q);
        final q q2 = new q();
        final o o = new o();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(cv.q("Select shortcut"));
        this.q = q.q();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        q2.setLayout(gridBagLayout);
        q2.setBackground(be.w.i);
        q2.setForeground(be.w.u);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label.setFont(k.q);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        q2.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(o, gridBagConstraints);
        q2.add(o);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        (this.q = new w(cv.q("Shortcut"), "replace")).q(false);
        (this.q = new u()).w(this.q);
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
        final r r = new r(this.q);
        layout.setConstraints(r, gridBagConstraints2);
        panel.add(r);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        q2.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(q2, gridBagConstraints);
        this.add(q2);
        if (X.q == null) {
            (X.q = new Checkbox(cv.q("Close after selection"))).setForeground(be.w.u);
            X.q.setState(true);
        }
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(X.q, gridBagConstraints);
        this.add(X.q);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.q.q(cv.q("Close"));
        this.q.t();
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.w.q(cv.q("Insert"));
        this.w.t();
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        this.pack();
        X.q = true;
    }
    
    static {
        X.q = false;
    }
}
