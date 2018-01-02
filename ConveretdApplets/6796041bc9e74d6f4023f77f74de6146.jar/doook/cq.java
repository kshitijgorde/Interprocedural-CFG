// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Panel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class cq extends p
{
    private aS b;
    private t g;
    public aS z;
    public static Checkbox d;
    public TextArea f;
    public l g;
    public j h;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.b.s();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.b) {
                    this.dispose();
                    return true;
                }
                if (event.target == this.z) {
                    try {
                        this.f.append(" " + ((ax)this.g.a()).f());
                        if (cq.d.getState()) {
                            this.dispose();
                        }
                    }
                    catch (Exception ex) {}
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    private void y() {
        this.g.f();
        this.g.h.a(false);
        try {
            for (int i = 0; i < this.g.h.b(); ++i) {
                final ax ax = (ax)this.g.h.a(i);
                if (ax.d(36)) {
                    final aT at;
                    this.g.c(at = new aT(ax));
                    this.g.a(at, new Color(ax.aN), Color.white);
                }
            }
        }
        finally {
            this.g.h.a();
        }
    }
    
    public cq(final Frame frame, final t g, final TextArea f) {
        super(frame, ao.e("Select a shortcut"), true);
        super.a = new aS(80, 20);
        this.b = new aS(80, 20);
        this.z = new aS(80, 20);
        if (cq.d == null) {
            (cq.d = new Checkbox(ao.e("Close after selection"))).setForeground(g.a.f);
            cq.d.setState(true);
        }
        this.setBackground(g.a.a);
        final cA ca = new cA();
        final cH ch = new cH();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(ao.e("Select a shortcut"));
        this.g = g;
        this.f = f;
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        ca.setLayout(gridBagLayout);
        ca.setBackground(g.a.g);
        ca.setForeground(g.a.f);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label.setFont(bL.e);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        ca.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(ch, gridBagConstraints);
        ca.add(ch);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        this.h = new j(ao.e("Shortcut"), "replace");
        (this.g = new l()).b(this.h);
        this.g.a(false);
        this.h.c(true);
        final Panel panel = new Panel();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        gridBagConstraints2.insets = new Insets(4, 4, 4, 4);
        gridBagConstraints2.gridwidth = -1;
        gridBagConstraints2.gridheight = 0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.fill = 1;
        this.g.setSize(250, 220);
        this.y();
        final aR ar = new aR(this.g);
        layout.setConstraints(ar, gridBagConstraints2);
        panel.add(ar);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        ca.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(ca, gridBagConstraints);
        this.add(ca);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(cq.d, gridBagConstraints);
        this.add(cq.d);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.b.a(ao.e("Close"));
        this.b.t();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.z.a(ao.e("Insert"));
        this.z.t();
        gridBagLayout.setConstraints(this.z, gridBagConstraints);
        this.add(this.z);
        this.pack();
    }
}
