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

public final class N extends C
{
    private i a;
    private bh a;
    private i b;
    private static Checkbox a;
    private TextArea a;
    private y a;
    private w a;
    
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
                        this.a.append(" " + ((at)this.a.a()).c);
                        if (N.a.getState()) {
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
    
    private void b() {
        this.a.d();
        try {
            for (int i = 0; i < this.a.h.a(); ++i) {
                final at at;
                if ((at = (at)this.a.h.a(i)).a(36)) {
                    final P p;
                    this.a.c(p = new P(at));
                    this.a.a(p, new Color(at.a), Color.white, j.c);
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    public N(final Frame frame, final bh a, final TextArea a2) {
        super(frame, ak.a(528), true);
        super.a = new i(80, 20);
        this.a = new i(80, 20);
        this.b = new i(80, 20);
        if (N.a == null) {
            (N.a = new Checkbox(ak.a(529))).setForeground(a.a.g);
            N.a.setState(true);
        }
        this.setBackground(a.a.a);
        final aM am = new aM();
        final bb bb = new bb();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final aw aw = new aw(ak.a(528), (byte)0);
        this.a = a;
        this.a = a2;
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        am.setLayout(gridBagLayout);
        am.setBackground(a.a.h);
        am.setForeground(a.a.g);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        aw.setFont(ay.a);
        gridBagLayout.setConstraints(aw, gridBagConstraints);
        am.add(aw);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(bb, gridBagConstraints);
        am.add(bb);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        this.a = new w(ak.a(98), "replace");
        (this.a = new y()).b(this.a);
        this.a.a();
        this.a.a = false;
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
        this.b();
        final g g = new g(this.a);
        layout.setConstraints(g, gridBagConstraints2);
        panel.add(g);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        am.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(am, gridBagConstraints);
        this.add(am);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(N.a, gridBagConstraints);
        this.add(N.a);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.a.a(ak.a(95));
        this.a.d();
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.b.a(ak.a(530));
        this.b.d();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        this.pack();
    }
}
