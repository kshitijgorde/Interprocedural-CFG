// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class g extends N
{
    private al a;
    private be a;
    public al b;
    public static Checkbox a;
    public TextArea a;
    public aM a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.a.e();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    this.dispose();
                    return true;
                }
                if (event.target == this.b) {
                    try {
                        this.a.append(" " + this.a.a().g());
                        if (g.a.getState()) {
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
        this.a.c();
        this.a.d.a(false);
        try {
            synchronized (this.a.d) {
                for (int i = 0; i < ba.a(); ++i) {
                    final ba a = ba.a(i);
                    if (a.c(36)) {
                        if (this.a.c(36)) {
                            this.a.a(a);
                        }
                    }
                    else {
                        this.a.a(a);
                    }
                }
            }
        }
        finally {
            this.a.d.c();
        }
        this.a.b(this.a.I);
    }
    
    public g(final Frame frame, final be a, final TextArea a2) {
        super(frame, aG.a("Select an emoticon"), true);
        super.c = new al(80, 20);
        this.a = new al(80, 20);
        this.b = new al(80, 20);
        if (g.a == null) {
            (g.a = new Checkbox(aG.a("Close after selection"))).setForeground(a.c.j);
            g.a.setState(true);
        }
        this.setBackground(a.c.c);
        final n n = new n();
        final ad ad = new ad();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(aG.a("Select an emoticon"));
        this.a = a;
        this.a = a2;
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        n.setLayout(gridBagLayout);
        n.setBackground(a.c.k);
        n.setForeground(a.c.j);
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
        (this.a = new aM()).setSize(400, 220);
        this.a();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        n.add(this.a);
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
        gridBagLayout.setConstraints(g.a, gridBagConstraints);
        this.add(g.a);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.a.a(aG.a("Close"));
        this.a.f();
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.b.a(aG.a("Insert"));
        this.b.f();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        this.pack();
    }
}
