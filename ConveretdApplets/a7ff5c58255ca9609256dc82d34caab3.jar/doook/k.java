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

public final class k extends aA
{
    private ax b;
    private as a;
    public ax c;
    public static Checkbox a;
    public TextArea a;
    public G a;
    
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
                        this.a.append(" " + this.a.a().d());
                        if (k.a.getState()) {
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
        this.a.f();
        this.a.e.a(false);
        try {
            synchronized (this.a.e) {
                for (int i = 0; i < U.g(); ++i) {
                    final U a = U.a(i);
                    if (a.a(36)) {
                        if (this.a.a(36)) {
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
            this.a.e.f();
        }
        this.a.a(this.a.K);
    }
    
    public k(final Frame frame, final as a, final TextArea a2) {
        super(frame, ar.b("Select an emoticon"), true);
        super.c = new ax(80, 20);
        this.b = new ax(80, 20);
        this.c = new ax(80, 20);
        if (k.a == null) {
            (k.a = new Checkbox(ar.b("Close after selection"))).setForeground(a.b.f);
            k.a.setState(true);
        }
        this.setBackground(a.b.a);
        final aC ac = new aC();
        final K k = new K();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(ar.b("Select an emoticon"));
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
        (this.a = new G()).setSize(400, 220);
        this.d();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        ac.add(this.a);
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
        gridBagLayout.setConstraints(doook.k.a, gridBagConstraints);
        this.add(doook.k.a);
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
