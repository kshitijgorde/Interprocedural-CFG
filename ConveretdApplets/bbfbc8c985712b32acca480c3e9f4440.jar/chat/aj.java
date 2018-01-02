// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Panel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class aj extends bC
{
    private cr a;
    private cs a;
    private cr b;
    private static Checkbox a;
    private TextArea a;
    private K a;
    private I a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.a.c();
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
                        this.a.append(" " + ((ba)this.a.a()).d);
                        if (aj.a.getState()) {
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
        this.a.e();
        try {
            for (int i = 0; i < this.a.h.a(); ++i) {
                final ba ba;
                if ((ba = (ba)this.a.h.a(i)).a(36)) {
                    final cy cy;
                    this.a.c(cy = new cy(ba));
                    this.a.a(cy, new Color(ba.a), Color.white, o.c);
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    public aj(final Frame frame, final cs a, final TextArea a2) {
        super(frame, aS.a(528), true);
        super.a = new cr(80, 20);
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        if (aj.a == null) {
            (aj.a = new Checkbox(aS.a(529))).setForeground(a.a.g);
            aj.a.setState(true);
        }
        this.setBackground(a.a.a);
        final bF bf = new bF();
        final k k = new k();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final bi bi = new bi(aS.a(528), (byte)0);
        this.a = a;
        this.a = a2;
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        bf.setLayout(gridBagLayout);
        bf.setBackground(a.a.h);
        bf.setForeground(a.a.g);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        bi.setFont(bk.a);
        gridBagLayout.setConstraints(bi, gridBagConstraints);
        bf.add(bi);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(k, gridBagConstraints);
        bf.add(k);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        this.a = new I(aS.a(98), "replace");
        (this.a = new K()).b(this.a);
        this.a.a();
        this.a.b = false;
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
        this.a.setSize(250, 220);
        this.a();
        final i i = new i(this.a);
        layout.setConstraints(i, gridBagConstraints2);
        panel.add(i);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        bf.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(bf, gridBagConstraints);
        this.add(bf);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(aj.a, gridBagConstraints);
        this.add(aj.a);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.a.a(aS.a(95));
        this.a.d();
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.b.a(aS.a(530));
        this.b.d();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        this.pack();
    }
}
