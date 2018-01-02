// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Event;
import java.awt.Frame;
import java.awt.TextField;

public final class af extends p
{
    private aS a;
    private aS b;
    private TextField c;
    private u j;
    private Frame a;
    
    private final ac a(final String s) {
        final ab[] array = new ab[this.j.c.b()];
        this.j.c.a(array);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null && array[i].f().equals(s)) {
                final ac ac = new ac(array[i].h(), array[i].f());
                ac.a = true;
                ac.g = array[i].g;
                ac.a = array[i].a;
                ac.h = array[i].h;
                ac.a(array[i].d());
                return ac;
            }
        }
        return null;
    }
    
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
                    final String trim = this.c.getText().trim();
                    if (trim.length() == 0) {
                        new E(this.a, ao.e("Note"), ao.e("You must provide a name for your Buddy.  Please re-enter this information."), this.j).setVisible(true);
                        return true;
                    }
                    if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
                        this.c.requestFocus();
                        this.c.selectAll();
                        new E(this.j.d, ao.e("Error"), ao.e("Names can not have commas or quotes.  Please re-enter this information."), this.j).setVisible(true);
                        return true;
                    }
                    ac a = this.a(trim);
                    if (a == null) {
                        a = new ac(-999, trim);
                        a.a = false;
                    }
                    if (this.j.a != null) {
                        ((au)this.j.a).a(a, true, false);
                    }
                    this.dispose();
                }
                else if (event.target == this.b) {
                    this.dispose();
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public af(final Frame frame, final u j) {
        super(frame, ao.e("Add Buddy"), true);
        this.a = new aS(80, 20);
        this.b = new aS(80, 20);
        this.setBackground(j.a.g);
        this.setForeground(j.a.f);
        this.j = j;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 13;
        this.c = new TextField(20);
        final Label label = new Label(ao.e("Name"));
        label.setFont(bL.g);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aR ar = new aR(this.c);
        gridBagLayout.setConstraints(ar, gridBagConstraints);
        panel.add(ar);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
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
        this.c.requestFocus();
    }
}
