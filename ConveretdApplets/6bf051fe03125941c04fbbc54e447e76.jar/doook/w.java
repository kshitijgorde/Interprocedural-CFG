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

public final class w extends aA
{
    private ax b;
    private as a;
    public ax c;
    public static Checkbox a;
    public TextArea a;
    public u d;
    public p e;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.b.l();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.b) {
                    this.dispose();
                    return true;
                }
                if (event.target == this.c) {
                    try {
                        this.a.append(" " + ((aq)this.d.a()).d());
                        if (w.a.getState()) {
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
    
    private void d() {
        this.d.j();
        this.a.j.a(false);
        try {
            for (int i = 0; i < this.a.j.b(); ++i) {
                final aq aq = (aq)this.a.j.a(i);
                if (aq.a(36)) {
                    final V v;
                    this.d.a((az)(v = new V(aq)));
                    this.d.a(v, new Color(aq.u), Color.white);
                }
            }
        }
        finally {
            this.a.j.f();
        }
    }
    
    public w(final Frame frame, final as a, final TextArea a2) {
        super(frame, ar.b("Select a shortcut"), true);
        super.c = new ax(80, 20);
        this.b = new ax(80, 20);
        this.c = new ax(80, 20);
        if (w.a == null) {
            (w.a = new Checkbox(ar.b("Close after selection"))).setForeground(a.b.f);
            w.a.setState(true);
        }
        this.setBackground(a.b.a);
        final aC ac = new aC();
        final K k = new K();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(ar.b("Select a shortcut"));
        this.a = a;
        this.a = a2;
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        ac.setLayout(gridBagLayout);
        ac.setBackground(a.b.g);
        ac.setForeground(a.b.f);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label.setFont(ay.d);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        ac.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(k, gridBagConstraints);
        ac.add(k);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        this.e = new p(ar.b("Shortcut"), "replace");
        (this.d = new u()).b(this.e);
        this.d.a(false);
        this.e.b(true);
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
        this.d.setSize(250, 220);
        this.d();
        final aw aw = new aw(this.d);
        layout.setConstraints(aw, gridBagConstraints2);
        panel.add(aw);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        ac.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(ac, gridBagConstraints);
        this.add(ac);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(w.a, gridBagConstraints);
        this.add(w.a);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.b.a(ar.b("Close"));
        this.b.p();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.c.a(ar.b("Insert"));
        this.c.p();
        gridBagLayout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        this.pack();
    }
}
