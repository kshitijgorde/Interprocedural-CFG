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

public final class by extends F
{
    private ad q;
    private ad w;
    private TextField q;
    private ap q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == cK.q) {
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
                        new dd(null, be.w("Note"), be.w("You must provide a name for your Buddy.  Please re-enter this information."), this.q).setVisible(true);
                        return true;
                    }
                    if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
                        this.q.requestFocus();
                        this.q.selectAll();
                        new dd(this.q.q, be.w("Error"), be.w("Names can not have commas or quotes.  Please re-enter this information."), this.q).setVisible(true);
                        return true;
                    }
                    final String s = trim;
                    final aO[] array = new aO[this.q.a.q];
                    this.q.a.q(array);
                    while (true) {
                        for (int i = 0; i < array.length; ++i) {
                            if (array[i] != null && array[i].a.equals(s)) {
                                final eh eh;
                                (eh = new eh(array[i].s, array[i].a)).y = true;
                                eh.e = array[i].e;
                                eh.q = array[i].q;
                                eh.q = array[i].q;
                                eh.r = array[i].r;
                                eh.q(array[i].w());
                                final eh eh3;
                                final eh eh2 = eh3 = eh;
                                eh eh4 = eh3;
                                if (eh2 == null) {
                                    (eh4 = new eh(-999, trim)).y = false;
                                }
                                if (this.q.q != null) {
                                    this.q.q.q(eh4, true);
                                }
                                this.dispose();
                                return true;
                            }
                        }
                        eh eh3;
                        final eh eh2 = eh3 = null;
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
    
    public by(final Frame frame, final ap q) {
        super(frame, be.w("Add Buddy"), true);
        this.q = new ad(80, 20);
        this.w = new ad(80, 20);
        this.setBackground(bC.w.i);
        this.setForeground(bC.w.u);
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
        (label = new Label(be.w("Name"))).setFont(cb.t);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final bZ bz = new bZ(this.q);
        gridBagLayout.setConstraints(bz, gridBagConstraints);
        panel.add(bz);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.w.q(be.w("Cancel"));
        this.w.t();
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.q.q(be.w("OK"));
        this.q.t();
        final as as = new as(this.q);
        gridBagLayout.setConstraints(as, gridBagConstraints);
        this.add(as);
        this.pack();
        this.q.requestFocus();
    }
}
