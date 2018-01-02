// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.Event;

public final class bu extends p
{
    protected aS a;
    protected aS b;
    protected u a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == f.g) {
                    this.a.s();
                    return true;
                }
                if (event.key == 27) {
                    this.b.s();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final cD cd = new cD(66305, 1);
                    cd.o = -1;
                    cd.j = this.a.h();
                    cd.a(0, 0, this.a.h());
                    cd.a(0, 0, "restart");
                    this.a.o(cd);
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
    
    public bu(final Frame frame, final u a) {
        super(frame, ao.e("Restart Chat ..."), true);
        this.a = new aS(80, 20);
        this.b = new aS(80, 20);
        this.a = a;
        this.setBackground(a.a.a);
        final c c = new c(ao.e("Are you sure you want to restart the Chat ?"));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        panel.setBackground(a.a.g);
        panel.setForeground(a.a.f);
        c.setFont(bL.e);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(c, gridBagConstraints);
        panel.add(c);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = -1;
        this.b.a(ao.e("Cancel"));
        this.b.t();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.a.a(ao.e("OK"));
        this.a.t();
        final aQ aq = new aQ(this.a);
        gridBagLayout.setConstraints(aq, gridBagConstraints);
        this.add(aq);
        this.pack();
    }
}
