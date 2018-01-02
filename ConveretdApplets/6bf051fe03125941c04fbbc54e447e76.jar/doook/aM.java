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

public final class aM extends aA
{
    private ax a;
    private ax b;
    private TextField a;
    private at h;
    private Frame b;
    
    private final b a(final String s) {
        final a[] array = new a[this.h.e.b()];
        this.h.e.a(array);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null && array[i].d().equals(s)) {
                final b b = new b(array[i].e(), array[i].d());
                b.a = true;
                b.c = array[i].c;
                b.a = array[i].a;
                b.t = array[i].t;
                b.a(array[i].a());
                return b;
            }
        }
        return null;
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == bs.c) {
                    this.a.l();
                    return true;
                }
                if (event.key == 27) {
                    this.b.l();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final String trim = this.a.getText().trim();
                    if (trim.length() == 0) {
                        new S(this.b, ar.b("Note"), ar.b("You must provide a name for your Buddy.  Please re-enter this information."), this.h).setVisible(true);
                        return true;
                    }
                    if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
                        this.a.requestFocus();
                        this.a.selectAll();
                        new S(this.h.c, ar.b("Error"), ar.b("Names can not have commas or quotes.  Please re-enter this information."), this.h).setVisible(true);
                        return true;
                    }
                    b a = this.a(trim);
                    if (a == null) {
                        a = new b(-999, trim);
                        a.a = false;
                    }
                    if (this.h.a != null) {
                        ((e)this.h.a).a(a, true, false);
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
    
    public aM(final Frame frame, final at h) {
        super(frame, ar.b("Add Buddy"), true);
        this.a = new ax(80, 20);
        this.b = new ax(80, 20);
        this.setBackground(h.b.g);
        this.setForeground(h.b.f);
        this.h = h;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 13;
        this.a = new TextField(20);
        final Label label = new Label(ar.b("Name"));
        label.setFont(ay.b);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aw aw = new aw(this.a);
        gridBagLayout.setConstraints(aw, gridBagConstraints);
        panel.add(aw);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.b.a(ar.b("Cancel"));
        this.b.p();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.a.a(ar.b("OK"));
        this.a.p();
        final au au = new au(this.a);
        gridBagLayout.setConstraints(au, gridBagConstraints);
        this.add(au);
        this.pack();
        this.a.requestFocus();
    }
}
