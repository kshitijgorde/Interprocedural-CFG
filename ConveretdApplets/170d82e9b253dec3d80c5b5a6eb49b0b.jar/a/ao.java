// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.Event;
import java.awt.TextField;

public final class ao extends ah
{
    private g q;
    private g w;
    private TextField q;
    private cV q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ef.q) {
                    this.q.r();
                    return true;
                }
                if (event.key == 27) {
                    this.w.r();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    final String trim;
                    if ((trim = this.q.getText().trim()).length() == 0) {
                        new b(null, eb.q("Note"), eb.q("You must provide a name for your Buddy.  Please re-enter this information."), this.q).setVisible(true);
                        return true;
                    }
                    if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
                        this.q.requestFocus();
                        this.q.selectAll();
                        new b(this.q.q, eb.q("Error"), eb.q("Names can not have commas or quotes.  Please re-enter this information."), this.q).setVisible(true);
                        return true;
                    }
                    final String s = trim;
                    final dj[] array = new dj[this.q.e.q()];
                    this.q.e.q(array);
                    while (true) {
                        for (int i = 0; i < array.length; ++i) {
                            if (array[i] != null && array[i].getName().equals(s)) {
                                final dt dt;
                                (dt = new dt(array[i].q(), array[i].getName())).y = true;
                                dt.e = array[i].e;
                                dt.q = array[i].q;
                                dt.q(array[i].q());
                                dt.o = array[i].o;
                                dt.q(array[i].q());
                                final dt dt3;
                                final dt dt2 = dt3 = dt;
                                dt dt4 = dt3;
                                if (dt2 == null) {
                                    (dt4 = new dt(-999, trim)).y = false;
                                }
                                if (this.q.q != null) {
                                    this.q.q.q(dt4, true);
                                }
                                this.dispose();
                                return true;
                            }
                        }
                        dt dt3;
                        final dt dt2 = dt3 = null;
                        continue;
                    }
                }
                else if (event.target == this.w) {
                    this.dispose();
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public ao(final Frame frame, final cV q) {
        super(frame, eb.q("Add Buddy"), true);
        this.q = new g(80, 20);
        this.w = new g(80, 20);
        this.setBackground(cf.w.i);
        this.setForeground(cf.w.u);
        this.q = q;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 13;
        this.q = new TextField(20);
        final Label label;
        (label = new Label(eb.q("Name"))).setFont(m.t);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final t t = new t(this.q);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        panel.add(t);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.w.q(eb.q("Cancel"));
        this.w.t();
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.q.q(eb.q("OK"));
        this.q.t();
        final f f = new f(this.q);
        gridBagLayout.setConstraints(f, gridBagConstraints);
        this.add(f);
        this.pack();
        this.q.requestFocus();
    }
}
