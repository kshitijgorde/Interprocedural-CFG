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

public final class ck extends p
{
    private aS b;
    private t g;
    public aS z;
    public static Checkbox d;
    public TextArea f;
    public bO a;
    
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
                        this.f.append(" " + this.a.a().f());
                        if (ck.d.getState()) {
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
        this.a.a();
        this.g.b.a(false);
        try {
            synchronized (this.g.b) {
                for (int i = 0; i < bh.g(); ++i) {
                    final bh a = bh.a(i);
                    if (a.d(36)) {
                        if (this.g.d(36)) {
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
            this.g.b.a();
        }
        this.a.a(this.g.s);
    }
    
    public ck(final Frame frame, final t g, final TextArea f) {
        super(frame, ao.e("Select an emoticon"), true);
        super.a = new aS(80, 20);
        this.b = new aS(80, 20);
        this.z = new aS(80, 20);
        if (ck.d == null) {
            (ck.d = new Checkbox(ao.e("Close after selection"))).setForeground(g.a.f);
            ck.d.setState(true);
        }
        this.setBackground(g.a.a);
        final cA ca = new cA();
        final cH ch = new cH();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(ao.e("Select an emoticon"));
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
        (this.a = new bO()).setSize(400, 220);
        this.y();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        ca.add(this.a);
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
        gridBagLayout.setConstraints(ck.d, gridBagConstraints);
        this.add(ck.d);
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
