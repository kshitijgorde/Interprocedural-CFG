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

public final class ai extends ah
{
    private g q;
    private cM q;
    private g w;
    private static Checkbox q;
    private TextArea q;
    private w q;
    private y q;
    private l q;
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
                    a.q(" " + ((cd)this.q.q()).getName(), this.q);
                    this.q.requestFocus();
                    if (ai.q.getState()) {
                        this.q.w();
                    }
                }
                if (event.target == this.q) {
                    a.q(" " + ((cd)this.q.q()).getName(), this.q);
                    this.q.requestFocus();
                    if (ai.q.getState()) {
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
        ai.q = false;
        super.hide();
    }
    
    private void q() {
        this.q.r();
        this.q.q();
        dW.q();
        try {
            for (int i = 0; i < this.q.q().q(); ++i) {
                final cd cd;
                if ((cd = (cd)this.q.q().q(i)).q(36)) {
                    final cL cl = new cL(cd);
                    this.q.e(cl);
                    this.q.q(cl, new Color(cl.y()), Color.pink);
                    if (cd.w().length() > this.q.length()) {
                        this.q = cd.w();
                    }
                }
            }
        }
        finally {
            this.q.q();
            dW.w();
        }
    }
    
    public ai(final l q, final Point location) {
        super(new Frame(), eb.q("Select a shortcut"), false);
        this.q = "";
        this.setModal(true);
        this.q = new g(80, 20);
        this.w = new g(80, 20);
        this.q = q;
        this.q = q.q();
        this.setLocation(location);
        this.setBackground(cf.w.q);
        final s s = new s();
        final q q2 = new q();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(eb.q("Select shortcut"));
        this.q = q.q();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        s.setLayout(gridBagLayout);
        s.setBackground(cf.w.i);
        s.setForeground(cf.w.u);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label.setFont(m.q);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        s.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(q2, gridBagConstraints);
        s.add(q2);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        (this.q = new y(eb.q("Shortcut"), "replace")).w(false);
        (this.q = new w()).w(this.q);
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
        final t t = new t(this.q);
        layout.setConstraints(t, gridBagConstraints2);
        panel.add(t);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        s.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(s, gridBagConstraints);
        this.add(s);
        if (ai.q == null) {
            (ai.q = new Checkbox(eb.q("Close after selection"))).setForeground(cf.w.u);
            ai.q.setState(true);
        }
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(ai.q, gridBagConstraints);
        this.add(ai.q);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.q.q(eb.q("Close"));
        this.q.t();
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.w.q(eb.q("Insert"));
        this.w.t();
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        this.pack();
        ai.q = true;
    }
    
    static {
        ai.q = false;
    }
}
