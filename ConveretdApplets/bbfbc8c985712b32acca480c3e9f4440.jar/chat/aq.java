// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.Event;

public final class aq extends bC
{
    private cr a;
    private cr b;
    private cx a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ce.a) {
                    this.a.c();
                    return true;
                }
                if (event.key == 27) {
                    this.b.c();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final r r;
                    (r = new r(66305, 1)).d = -1;
                    r.e = this.a.i;
                    r.a(0, 0, this.a.i);
                    r.a(0, 0, "restart");
                    this.a.o(r);
                    this.dispose();
                    return true;
                }
                if (event.target == this.b) {
                    this.dispose();
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public aq(final Frame frame, final cx a) {
        super(frame, aS.a(181), true);
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        this.a = a;
        this.setBackground(a.a.a);
        final A a2 = new A(aS.a(394));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        panel.setBackground(a.a.h);
        panel.setForeground(a.a.g);
        a2.setFont(bk.a);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(a2, gridBagConstraints);
        panel.add(a2);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = -1;
        this.b.a(aS.a(3));
        this.b.d();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.a.a(aS.a(2));
        this.a.d();
        final g g = new g(this.a);
        gridBagLayout.setConstraints(g, gridBagConstraints);
        this.add(g);
        this.pack();
    }
}
