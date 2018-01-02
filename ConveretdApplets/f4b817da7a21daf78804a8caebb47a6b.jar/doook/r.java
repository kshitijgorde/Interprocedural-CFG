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

public final class r extends N
{
    private al d;
    private be d;
    public al b;
    public static Checkbox a;
    public TextArea a;
    public am b;
    public aB a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.d.e();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.d) {
                    this.dispose();
                    return true;
                }
                if (event.target == this.b) {
                    try {
                        this.a.append(" " + ((as)this.b.a()).g());
                        if (r.a.getState()) {
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
    
    private void a() {
        this.b.j();
        this.d.i.a(false);
        try {
            for (int i = 0; i < this.d.i.a(); ++i) {
                final as as = (as)this.d.i.a(i);
                if (as.c(36)) {
                    final aS as2;
                    this.b.a((bk)(as2 = new aS(as)));
                    this.b.a(as2, new Color(as.ag), Color.white);
                }
            }
        }
        finally {
            this.d.i.c();
        }
    }
    
    public r(final Frame frame, final be d, final TextArea a) {
        super(frame, aG.a("Select a shortcut"), true);
        super.c = new al(80, 20);
        this.d = new al(80, 20);
        this.b = new al(80, 20);
        if (r.a == null) {
            (r.a = new Checkbox(aG.a("Close after selection"))).setForeground(d.c.j);
            r.a.setState(true);
        }
        this.setBackground(d.c.c);
        final n n = new n();
        final ad ad = new ad();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(aG.a("Select a shortcut"));
        this.d = d;
        this.a = a;
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        n.setLayout(gridBagLayout);
        n.setBackground(d.c.k);
        n.setForeground(d.c.j);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label.setFont(aK.e);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        n.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(ad, gridBagConstraints);
        n.add(ad);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        this.a = new aB(aG.a("Shortcut"), "replace");
        (this.b = new am()).b(this.a);
        this.b.a(false);
        this.a.b(true);
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
        this.b.setSize(250, 220);
        this.a();
        final aX ax = new aX(this.b);
        layout.setConstraints(ax, gridBagConstraints2);
        panel.add(ax);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        n.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(n, gridBagConstraints);
        this.add(n);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(r.a, gridBagConstraints);
        this.add(r.a);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.d.a(aG.a("Close"));
        this.d.f();
        gridBagLayout.setConstraints(this.d, gridBagConstraints);
        this.add(this.d);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.b.a(aG.a("Insert"));
        this.b.f();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        this.pack();
    }
}
