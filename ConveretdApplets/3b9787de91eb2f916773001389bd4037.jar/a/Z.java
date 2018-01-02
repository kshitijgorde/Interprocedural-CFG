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

public final class Z extends W
{
    private e q;
    private e w;
    private TextField q;
    private bI q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == cx.q) {
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
                        new b(null, cv.q("Note"), cv.q("You must provide a name for your Buddy.  Please re-enter this information."), this.q).setVisible(true);
                        return true;
                    }
                    if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
                        this.q.requestFocus();
                        this.q.selectAll();
                        new b(this.q.q, cv.q("Error"), cv.q("Names can not have commas or quotes.  Please re-enter this information."), this.q).setVisible(true);
                        return true;
                    }
                    final String s = trim;
                    final bV[] array = new bV[this.q.e.q()];
                    this.q.e.q(array);
                    while (true) {
                        for (int i = 0; i < array.length; ++i) {
                            if (array[i] != null && array[i].getName().equals(s)) {
                                final bZ bz;
                                (bz = new bZ(array[i].q(), array[i].getName())).t = true;
                                bz.u = array[i].u;
                                bz.q = array[i].q;
                                bz.q(array[i].q());
                                bz.i = array[i].i;
                                bz.q(array[i].q());
                                final bZ bz3;
                                final bZ bz2 = bz3 = bz;
                                bZ bz4 = bz3;
                                if (bz2 == null) {
                                    (bz4 = new bZ(-999, trim)).t = false;
                                }
                                if (this.q.q != null) {
                                    this.q.q.q(bz4, true);
                                }
                                this.dispose();
                                return true;
                            }
                        }
                        bZ bz3;
                        final bZ bz2 = bz3 = null;
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
    
    public Z(final Frame frame, final bI q) {
        super(frame, cv.q("Add Buddy"), true);
        this.q = new e(80, 20);
        this.w = new e(80, 20);
        this.setBackground(be.w.i);
        this.setForeground(be.w.u);
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
        (label = new Label(cv.q("Name"))).setFont(k.t);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final r r = new r(this.q);
        gridBagLayout.setConstraints(r, gridBagConstraints);
        panel.add(r);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.w.q(cv.q("Cancel"));
        this.w.t();
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.q.q(cv.q("OK"));
        this.q.t();
        final d d = new d(this.q);
        gridBagLayout.setConstraints(d, gridBagConstraints);
        this.add(d);
        this.pack();
        this.q.requestFocus();
    }
}
